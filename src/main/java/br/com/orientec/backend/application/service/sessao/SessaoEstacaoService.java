package br.com.orientec.backend.application.service.sessao;

import br.com.orientec.backend.adapter.out.persistence.entity.sessao.*;
import br.com.orientec.backend.adapter.out.persistence.repository.sessao.*;
import br.com.orientec.backend.adapter.out.persistence.repository.sede.SedeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.Duration;
import java.util.HexFormat;
import java.util.UUID;

@Service
public class SessaoEstacaoService {
    public static final String COOKIE = "ORIENTEC_STATION";
    private final EstacaoRepository estacoes;
    private final SessaoEstacaoRepository sessoes;
    private final CodigoLancamentoEstacaoRepository codigos;
    private final SedeRepository sedes;
    private final Duration sessaoTtl;
    private final LogAcessoService logs;
    private final Duration launchTtl;
    private final SecureRandom random = new SecureRandom();

    public SessaoEstacaoService(EstacaoRepository estacoes, SessaoEstacaoRepository sessoes,
                                CodigoLancamentoEstacaoRepository codigos, SedeRepository sedes,
                                @Value("${orientec.estacao.sessao-ttl:PT12H}") Duration sessaoTtl,
                                @Value("${orientec.estacao.launch-ttl:PT1M}") Duration launchTtl, LogAcessoService logs) {
        this.estacoes=estacoes; this.sessoes=sessoes; this.codigos=codigos; this.sedes=sedes; this.logs=logs;
        this.sessaoTtl=sessaoTtl; this.launchTtl=launchTtl;
    }

    @Transactional
    public Launch handshake(Long sedeCodigo, String nomeMaquina, String ipLocal, String ipPublico) {
        if (sedeCodigo == null || nomeMaquina == null || nomeMaquina.isBlank()) { logs.registrar("ESTACAO_HANDSHAKE","FALHA","ESTACAO_DADOS_INVALIDOS",null,sedeCodigo,null,null,nomeMaquina,ipLocal,ipPublico,"Dados da estação inválidos"); throw new StationAccessException("ESTACAO_DADOS_INVALIDOS", "Dados da estação inválidos."); }
        var sede = sedes.findById(sedeCodigo).orElseThrow(() -> { logs.registrar("ESTACAO_HANDSHAKE","FALHA","SEDE_INVALIDA",null,sedeCodigo,null,null,nomeMaquina,ipLocal,ipPublico,"Sede inexistente"); return new StationAccessException("SEDE_INVALIDA", "A sede informada não existe."); });
        if (!sede.isAtivo()) { logs.registrar("ESTACAO_HANDSHAKE","FALHA","SEDE_INATIVA",null,sedeCodigo,null,null,nomeMaquina,ipLocal,ipPublico,"Sede inativa"); throw new StationAccessException("SEDE_INATIVA", "A sede informada está inativa."); }

        var estacao = estacoes.findBySedeCodigoAndNomeMaquina(sedeCodigo, nomeMaquina.trim()).orElseGet(Estacao::nova);
        estacao.setSedeCodigo(sedeCodigo); estacao.setNomeMaquina(nomeMaquina.trim()); estacao.setIpLocal(ipLocal); estacao.setIpPublico(ipPublico); estacao.setAtivo(true); estacao.setUltimoHeartbeatEm(now());
        estacao = estacoes.save(estacao);

        var sessao = SessaoEstacao.nova();
        sessao.setId(UUID.randomUUID()); sessao.setEstacaoCodigo(estacao.getCodigo()); sessao.setCriadaEm(now()); sessao.setUltimoHeartbeatEm(now()); sessao.setExpiraEm(now().plus(sessaoTtl)); sessao.setIpLocal(ipLocal); sessao.setIpPublico(ipPublico);
        sessoes.save(sessao);

        String rawCode = randomCode();
        var code = CodigoLancamentoEstacao.novo();
        code.setId(UUID.randomUUID()); code.setSessaoEstacaoId(sessao.getId()); code.setCodigoHash(sha256(rawCode)); code.setCriadoEm(now()); code.setExpiraEm(now().plus(launchTtl));
        codigos.save(code);
        logs.registrar("ESTACAO_HANDSHAKE","SUCESSO",null,null,sedeCodigo,estacao.getCodigo(),null,nomeMaquina,ipLocal,ipPublico,null);
        return new Launch(rawCode, sessao.getId(), code.getExpiraEm());
    }

    @Transactional
    public UUID exchange(String rawCode) {
        var code = codigos.findByCodigoHash(sha256(rawCode)).orElseThrow(() -> new StationAccessException("CODIGO_ESTACAO_INVALIDO", "Código de inicialização inválido ou expirado."));
        if (code.getUtilizadoEm()!=null || now().isAfter(code.getExpiraEm())) throw new StationAccessException("CODIGO_ESTACAO_EXPIRADO", "O código de inicialização expirou.");
        var sessao = sessoes.findById(code.getSessaoEstacaoId()).orElseThrow(() -> new StationAccessException("SESSAO_ESTACAO_INEXISTENTE", "A sessão da estação não existe."));
        if (sessao.getEncerradaEm()!=null || now().isAfter(sessao.getExpiraEm())) throw new StationAccessException("SESSAO_ESTACAO_EXPIRADA", "A sessão da estação expirou.");
        code.setUtilizadoEm(now()); codigos.save(code);
        return sessao.getId();
    }

    @Transactional
    public void heartbeat(UUID sessaoId) {
        var s = sessoes.findById(sessaoId).orElseThrow(() -> new StationAccessException("SESSAO_ESTACAO_INEXISTENTE", "Sessão da estação não encontrada."));
        if (s.getEncerradaEm()!=null || now().isAfter(s.getExpiraEm())) throw new StationAccessException("SESSAO_ESTACAO_EXPIRADA", "Sessão da estação expirada.");
        s.setUltimoHeartbeatEm(now()); s.setExpiraEm(now().plus(sessaoTtl)); sessoes.save(s);
        estacoes.findById(s.getEstacaoCodigo()).ifPresent(e -> { e.setUltimoHeartbeatEm(now()); estacoes.save(e); });
    }

    @Transactional(readOnly=true)
    public Long sedeCodigo(UUID sessaoId) {
        var s=sessoes.findById(sessaoId).orElseThrow(() -> new StationAccessException("SESSAO_ESTACAO_INEXISTENTE", "Sessão da estação não encontrada."));
        if(s.getEncerradaEm()!=null || now().isAfter(s.getExpiraEm())) throw new StationAccessException("SESSAO_ESTACAO_EXPIRADA", "Sessão da estação expirada.");
        var e=estacoes.findById(s.getEstacaoCodigo()).orElseThrow(() -> new StationAccessException("ESTACAO_INEXISTENTE", "Estação não encontrada."));
        if(!e.isAtivo()) throw new StationAccessException("ESTACAO_INATIVA", "Estação inativa.");
        return e.getSedeCodigo();
    }

    @Transactional(readOnly=true)
    public void validar(UUID sessaoId) {
        var s=sessoes.findById(sessaoId).orElseThrow(() -> new StationAccessException("SESSAO_ESTACAO_INEXISTENTE", "Sessão da estação não encontrada."));
        if (s.getEncerradaEm()!=null || now().isAfter(s.getExpiraEm())) throw new StationAccessException("SESSAO_ESTACAO_EXPIRADA", "Sessão da estação expirada.");
        var e=estacoes.findById(s.getEstacaoCodigo()).orElseThrow(() -> new StationAccessException("ESTACAO_INEXISTENTE", "Estação não encontrada."));
        if (!e.isAtivo()) throw new StationAccessException("ESTACAO_INATIVA", "Estação inativa.");
    }

    private OffsetDateTime now(){return OffsetDateTime.now(ZoneOffset.UTC);}
    private String randomCode(){byte[] b=new byte[32]; random.nextBytes(b); return HexFormat.of().formatHex(b);}
    private String sha256(String s){try{var d=MessageDigest.getInstance("SHA-256"); return HexFormat.of().formatHex(d.digest(s.getBytes(StandardCharsets.UTF_8)));}catch(Exception e){throw new IllegalStateException(e);}}
    public record Launch(String launchCode, UUID stationSessionId, OffsetDateTime expiresAt) {}
    public static class StationAccessException extends RuntimeException { public final String code; public StationAccessException(String code,String message){super(message);this.code=code;} }
}
