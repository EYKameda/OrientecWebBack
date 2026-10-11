package br.com.orientec.backend.adapter.in.web.dto.cadastro;
public class ClienteResponse {
    private Integer id;
    private String nome;
    private String cpfCnpj;
    private String telefone;
    private String celular;
    private String email;
    private String endereco;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;
    private boolean ativo;
    public Integer getId() { return id; }
    public void setId(Integer value) { this.id = value; }
    public String getNome() { return nome; }
    public void setNome(String value) { this.nome = value; }
    public String getCpfCnpj() { return cpfCnpj; }
    public void setCpfCnpj(String value) { this.cpfCnpj = value; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String value) { this.telefone = value; }
    public String getCelular() { return celular; }
    public void setCelular(String value) { this.celular = value; }
    public String getEmail() { return email; }
    public void setEmail(String value) { this.email = value; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String value) { this.endereco = value; }
    public String getNumero() { return numero; }
    public void setNumero(String value) { this.numero = value; }
    public String getComplemento() { return complemento; }
    public void setComplemento(String value) { this.complemento = value; }
    public String getBairro() { return bairro; }
    public void setBairro(String value) { this.bairro = value; }
    public String getCidade() { return cidade; }
    public void setCidade(String value) { this.cidade = value; }
    public String getUf() { return uf; }
    public void setUf(String value) { this.uf = value; }
    public String getCep() { return cep; }
    public void setCep(String value) { this.cep = value; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean value) { this.ativo = value; }
}
