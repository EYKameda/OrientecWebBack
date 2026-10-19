package br.com.orientec.backend.adapter.out.persistence.entity.cadastro;

import jakarta.persistence.*;

@Entity
@Table(name = "credenciado")
public class Credenciado {
    @Id
    private Integer id;

    @Column(name = "nome", length = 150) private String nome;
    @Column(name = "fantasia", length = 150) private String fantasia;
    @Column(name = "cnpj", length = 30) private String cnpj;
    @Column(name = "cpf", length = 30) private String cpf;
    @Column(name = "empresa_codigo") private Long empresaCodigo;
    @Column(name = "ativo", nullable = false) private boolean ativo;

    public Credenciado() {}
    public Integer getId() { return id; }
    public void setId(Integer v) { id = v; }
    public String getNome() { return nome; }
    public String getFantasia() { return fantasia; }
    public String getCnpj() { return cnpj; }
    public String getCpf() { return cpf; }
    public Long getEmpresaCodigo() { return empresaCodigo; }
    public boolean isAtivo() { return ativo; }
    public void setNome(String v) { nome = v; }
    public void setFantasia(String v) { fantasia = v; }
    public void setCnpj(String v) { cnpj = v; }
    public void setCpf(String v) { cpf = v; }
    public void setEmpresaCodigo(Long v) { empresaCodigo = v; }
    public void setAtivo(boolean v) { ativo = v; }
}
