package br.com.orientec.backend.adapter.out.persistence.entity.cadastro;
import br.com.orientec.backend.adapter.out.persistence.entity.cadastro.*;
import br.com.orientec.backend.adapter.out.persistence.repository.cadastro.*;
import br.com.orientec.backend.application.service.cadastro.*;
import jakarta.persistence.*;
@Entity @Table(name="credenciado") public class Credenciado {
 @Id private Integer id;
 @Column(name="nome") private String nome; @Column(name="fantasia") private String fantasia; @Column(name="cnpj") private String cnpj; @Column(name="cpf") private String cpf; @Column(name="ativo") private boolean ativo;
 public Integer getId(){return id;} public void setId(Integer v){id=v;}
 public Credenciado(){}
 public String getNome(){return nome;} public String getFantasia(){return fantasia;} public String getCnpj(){return cnpj;} public String getCpf(){return cpf;} public boolean isAtivo(){return ativo;} public void setNome(String v){this.nome=v;} public void setFantasia(String v){this.fantasia=v;} public void setCnpj(String v){this.cnpj=v;} public void setCpf(String v){this.cpf=v;} public void setAtivo(boolean v){this.ativo=v;}
}
