
package br.com.orientec.backend.application.service.os;

import br.com.orientec.backend.adapter.out.persistence.entity.os.RegraOperacaoOs;
import br.com.orientec.backend.adapter.out.persistence.entity.usuario.Usuario;
import br.com.orientec.backend.application.port.out.UsuarioPermissionPort;
import br.com.orientec.backend.application.port.out.UsuarioPersistencePort;
import br.com.orientec.backend.application.port.out.os.RegraOperacaoPersistencePort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutorizacaoOperacaoOsService {

    private final RegraOperacaoPersistencePort regras;
    private final UsuarioPersistencePort usuarios;
    private final UsuarioPermissionPort permissoes;
    private final PasswordEncoder encoder;

    public AutorizacaoOperacaoOsService(
            RegraOperacaoPersistencePort regras,
            UsuarioPersistencePort usuarios,
            UsuarioPermissionPort permissoes,
            PasswordEncoder encoder) {
        this.regras = regras;
        this.usuarios = usuarios;
        this.permissoes = permissoes;
        this.encoder = encoder;
    }

    public RegraOperacaoOs validar(
            String codigoOperacao,
            Long solicitante,
            Long autorizador,
            String senhaAutorizador) {

        RegraOperacaoOs regra = regras
                .findByCodigoAndAtivoTrue(codigoOperacao)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Operação sem regra ativa: " + codigoOperacao));

        validarPermissao(solicitante, regra.getPermissaoExecucao());

        String metodo = regra.getMetodoAutorizacao();

        if (!regra.isExigeAutorizacao()) {
            if (metodo != null && !"NENHUM".equals(metodo)) {
                throw new IllegalArgumentException(
                        "Configuração inconsistente: a operação não exige autorização, "
                        + "mas possui método de autorização configurado.");
            }
            return regra;
        }

        if (autorizador == null) {
            throw new IllegalArgumentException(
                    "Esta operação exige outro acesso para liberação.");
        }

        if (regra.isExigeOutroUsuario() || "SENHA_OUTRO_ACESSO".equals(metodo)) {
            if (solicitante.equals(autorizador)) {
                throw new IllegalArgumentException(
                        "Solicitante e autorizador devem ser acessos diferentes.");
            }
        }

        Usuario auth = usuarios.findById(autorizador)
                .filter(Usuario::isAtivo)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Autorizador inválido ou inativo."));

        if (regra.getPermissaoAutorizacao() != null
                && !permissoes.findCodigosByUsuario(autorizador)
                        .contains(regra.getPermissaoAutorizacao())) {
            throw new IllegalArgumentException(
                    "Autorizador não possui "
                    + regra.getPermissaoAutorizacao() + ".");
        }

        if ("SENHA_OUTRO_ACESSO".equals(metodo)) {
            if (senhaAutorizador == null
                    || senhaAutorizador.isBlank()
                    || !encoder.matches(senhaAutorizador, auth.getSenhaHash())) {
                throw new IllegalArgumentException(
                        "Credencial do autorizador inválida.");
            }
        } else if ("CHAVE".equals(metodo)) {
            throw new IllegalArgumentException(
                    "Esta operação requer uma chave de liberação; "
                    + "o provedor de chaves ainda não foi configurado.");
        } else if ("OUTRO".equals(metodo)) {
            throw new IllegalArgumentException(
                    "O método de autorização OUTRO ainda não possui "
                    + "um mecanismo de validação implementado.");
        } else {
            throw new IllegalArgumentException(
                    "Método de autorização inválido ou não implementado: "
                    + metodo);
        }

        return regra;
    }

    private void validarPermissao(Long usuario, String permissao) {
        if (usuario == null) {
            throw new IllegalArgumentException(
                    "Usuário solicitante não informado.");
        }

        usuarios.findById(usuario)
                .filter(Usuario::isAtivo)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Solicitante inválido ou inativo."));

        if (permissao != null
                && !permissoes.findCodigosByUsuario(usuario).contains(permissao)) {
            throw new IllegalArgumentException(
                    "Usuário não possui " + permissao + ".");
        }
    }
}
