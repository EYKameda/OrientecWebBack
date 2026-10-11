package br.com.orientec.backend.adapter.out.persistence.entity.cadastro;
import br.com.orientec.backend.adapter.out.persistence.entity.cadastro.*;
import br.com.orientec.backend.adapter.out.persistence.repository.cadastro.*;
import br.com.orientec.backend.application.service.cadastro.*;
import jakarta.persistence.*;
@Entity @Table(name="cliente") public class Cliente {
 @Id private Integer id;
 @Column(name="nome")
 private String nome;
 @Column(name="cpf_cnpj") private String cpfCnpj;
 @Column(name="telefone") private String telefone;
 @Column(name="celular") private String celular;
 @Column(name="email") private String email;
 @Column(name="endereco") private String endereco;
 @Column(name="numero") private String numero;
 @Column(name="complemento") private String complemento;
 @Column(name="bairro") private String bairro;
 @Column(name="cidade") private String cidade;
 @Column(name = "uf", columnDefinition = "char(2)") private String uf;
 @Column(name="cep") private String cep;
 @Column(name="ativo") private boolean ativo;
 public Integer getId(){return id;}
 public void setId(Integer v){id=v;}
 public Cliente(){}
 public String getNome(){return nome;}
 public String getCpfCnpj(){return cpfCnpj;}
 public String getTelefone(){return telefone;}
 public String getCelular(){return celular;}
 public String getEmail(){return email;}
 public String getEndereco(){return endereco;}
 public String getNumero(){return numero;}
 public String getComplemento(){return complemento;}
 public String getBairro(){return bairro;}
 public String getCidade(){return cidade;}
 public String getUf(){return uf;}
 public String getCep(){return cep;}
 public boolean isAtivo(){return ativo;}
 public void setNome(String v){this.nome=v;}
 public void setCpfCnpj(String v){this.cpfCnpj=v;}
 public void setTelefone(String v){this.telefone=v;}
 public void setCelular(String v){this.celular=v;}
 public void setEmail(String v){this.email=v;}
 public void setEndereco(String v){this.endereco=v;}
 public void setNumero(String v){this.numero=v;}
 public void setComplemento(String v){this.complemento=v;}
 public void setBairro(String v){this.bairro=v;}
 public void setCidade(String v){this.cidade=v;}
 public void setUf(String v){this.uf=v;}
 public void setCep(String v){this.cep=v;}
 public void setAtivo(boolean v){this.ativo=v;}
}
