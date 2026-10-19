package br.com.orientec.backend.adapter.out.persistence.entity.cadastro;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "empresa")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(name = "codigo_legado", unique = true)
    private Long codigoLegado;

    @Column(name = "razao_social", nullable = false, length = 180)
    private String razaoSocial;

    @Column(name = "nome_fantasia", length = 180)
    private String nomeFantasia;

    @Column(length = 18) private String cnpj;
    @Column(length = 14) private String cpf;
    @Column(name = "inscricao_estadual", length = 40) private String inscricaoEstadual;
    @Column(length = 40) private String telefone;
    @Column(length = 180) private String email;
    @Column(length = 255) private String endereco;
    @Column(length = 20) private String numero;
    @Column(length = 100) private String complemento;
    @Column(length = 100) private String bairro;
    @Column(length = 100) private String cidade;
    @Column(length = 2) private String uf;
    @Column(length = 12) private String cep;
    @Column(nullable = false) private boolean ativo = true;

    @Column(name = "criado_em", insertable = false, updatable = false)
    private OffsetDateTime criadoEm;
    @Column(name = "atualizado_em", insertable = false, updatable = false)
    private OffsetDateTime atualizadoEm;

    public Empresa() {}
    public Long getCodigo() { return codigo; }
    public Long getCodigoLegado() { return codigoLegado; }
    public String getRazaoSocial() { return razaoSocial; }
    public String getNomeFantasia() { return nomeFantasia; }
    public String getCnpj() { return cnpj; }
    public String getCpf() { return cpf; }
    public String getInscricaoEstadual() { return inscricaoEstadual; }
    public String getTelefone() { return telefone; }
    public String getEmail() { return email; }
    public String getEndereco() { return endereco; }
    public String getNumero() { return numero; }
    public String getComplemento() { return complemento; }
    public String getBairro() { return bairro; }
    public String getCidade() { return cidade; }
    public String getUf() { return uf; }
    public String getCep() { return cep; }
    public boolean isAtivo() { return ativo; }

    public void setCodigoLegado(Long v) { codigoLegado = v; }
    public void setRazaoSocial(String v) { razaoSocial = v; }
    public void setNomeFantasia(String v) { nomeFantasia = v; }
    public void setCnpj(String v) { cnpj = v; }
    public void setCpf(String v) { cpf = v; }
    public void setInscricaoEstadual(String v) { inscricaoEstadual = v; }
    public void setTelefone(String v) { telefone = v; }
    public void setEmail(String v) { email = v; }
    public void setEndereco(String v) { endereco = v; }
    public void setNumero(String v) { numero = v; }
    public void setComplemento(String v) { complemento = v; }
    public void setBairro(String v) { bairro = v; }
    public void setCidade(String v) { cidade = v; }
    public void setUf(String v) { uf = v; }
    public void setCep(String v) { cep = v; }
    public void setAtivo(boolean v) { ativo = v; }
}
