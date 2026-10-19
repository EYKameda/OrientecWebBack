package br.com.orientec.backend.adapter.in.web.dto.cadastro;

public class CredenciadoResponse {
    private Integer id;
    private String nome;
    private String fantasia;
    private String cnpj;
    private String cpf;
    private Long empresaCodigo;
    private boolean ativo;
    public Integer getId() { return id; }
    public void setId(Integer value) { this.id = value; }
    public String getNome() { return nome; }
    public void setNome(String value) { this.nome = value; }
    public String getFantasia() { return fantasia; }
    public void setFantasia(String value) { this.fantasia = value; }
    public String getCnpj() { return cnpj; }
    public void setCnpj(String value) { this.cnpj = value; }
    public String getCpf() { return cpf; }
    public void setCpf(String value) { this.cpf = value; }
    public Long getEmpresaCodigo() { return empresaCodigo; }
    public void setEmpresaCodigo(Long value) { this.empresaCodigo = value; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean value) { this.ativo = value; }
}
