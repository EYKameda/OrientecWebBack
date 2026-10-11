package br.com.orientec.backend.application.service.usuario;

import br.com.orientec.backend.adapter.out.persistence.entity.usuario.Usuario;
import br.com.orientec.backend.adapter.out.persistence.entity.usuario.UsuarioPermissao;
import br.com.orientec.backend.application.port.out.PermissaoPersistencePort;
import br.com.orientec.backend.application.port.out.UsuarioPermissaoPersistencePort;
import br.com.orientec.backend.application.port.out.UsuarioPersistencePort;
import br.com.orientec.backend.application.port.out.model.PageQuery;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class UsuarioAdminService {

 private final UsuarioPersistencePort repo;
 private final PasswordEncoder encoder;
 private final UsuarioPermissaoPersistencePort up;
 private final PermissaoPersistencePort perm;

 public UsuarioAdminService(
         UsuarioPersistencePort r,
         PasswordEncoder e,
         UsuarioPermissaoPersistencePort u,
         PermissaoPersistencePort p) {

  repo = r;
  encoder = e;
  up = u;
  perm = p;
 }

 @Transactional(readOnly = true)
 public List<Usuario> listar() {
  return repo.findAll(new PageQuery(0, 200, "codigo,asc")).content();
 }

 @Transactional(readOnly = true)
 public Usuario buscar(Long id) {
  return repo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));
 }

 @Transactional
 public Usuario criar(UsuarioRequest r) {
  if (repo.findByLoginIgnoreCase(r.login()).isPresent()) {
   throw new IllegalArgumentException("Login já cadastrado.");
  }

  Usuario u = Usuario.novo();
  preencher(u, r);
  u.setSenhaHash(encoder.encode(r.senha()));

  return repo.save(u);
 }

 @Transactional
 public Usuario atualizar(Long id, UsuarioRequest r) {
  Usuario u = buscar(id);
  preencher(u, r);

  if (r.senha() != null && !r.senha().isBlank()) {
   u.setSenhaHash(encoder.encode(r.senha()));
  }

  return repo.save(u);
 }

 private void preencher(Usuario u, UsuarioRequest r) {
  u.setNome(r.nome());
  u.setApelido(r.apelido());
  u.setLogin(r.login());
  u.setNivelCodigo(r.nivelCodigo());
  u.setSedeCodigo(r.sedeCodigo());
  u.setAtivo(r.ativo());
  u.setTempoOciosoMinutos(r.tempoOciosoMinutos());
  u.setTempoMaximoSessaoMinutos(r.tempoMaximoSessaoMinutos());
  u.setModuloOs(r.moduloOs());
  u.setModuloCadastros(r.moduloCadastros());
  u.setModuloCaixa(r.moduloCaixa());
  u.setModuloEstoque(r.moduloEstoque());
  u.setModuloFinanceiro(r.moduloFinanceiro());
  u.setModuloGerencial(r.moduloGerencial());
  u.setModuloPonto(r.moduloPonto());
  u.setModuloTabelas(r.moduloTabelas());
  u.setModuloVendas(r.moduloVendas());
 }

 @Transactional
 public void excluir(Long id) {
  repo.deleteById(id);
 }

 @Transactional(readOnly = true)
 public Set<String> permissoes(Long id) {
  buscar(id);
  return up.findCodigosByUsuario(id);
 }

 @Transactional
 public void substituirPermissoes(Long id, Set<Long> ids) {
  buscar(id);

  for (Long pid : ids) {
   perm.findById(pid)
           .orElseThrow(() -> new IllegalArgumentException(
                   "Permissão inexistente: " + pid));

   up.save(new UsuarioPermissao(
           new UsuarioPermissao.Id(id, pid)
   ));
  }
 }

 public record UsuarioRequest(
         String nome,
         String apelido,
         String login,
         String senha,
         Short nivelCodigo,
         Long sedeCodigo,
         boolean ativo,
         Integer tempoOciosoMinutos,
         Integer tempoMaximoSessaoMinutos,
         boolean moduloOs,
         boolean moduloCadastros,
         boolean moduloCaixa,
         boolean moduloEstoque,
         boolean moduloFinanceiro,
         boolean moduloGerencial,
         boolean moduloPonto,
         boolean moduloTabelas,
         boolean moduloVendas) {
 }
}