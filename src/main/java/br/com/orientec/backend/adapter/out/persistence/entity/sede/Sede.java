package br.com.orientec.backend.adapter.out.persistence.entity.sede;

import jakarta.persistence.*;

@Entity
@Table(name = "sede")
public class Sede {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(nullable = false, length = 120)
    private String descricao;

    @Column(length = 30)
    private String sigla;

    @Column(nullable = false)
    private boolean ativo = true;

    public Sede() {}

    public Long getCodigo() { return codigo; }
    public String getDescricao() { return descricao; }
    public String getSigla() { return sigla; }
    public boolean isAtivo() { return ativo; }
    public void setDescricao(String v) { descricao = v; }
    public void setSigla(String v) { sigla = v; }
    public void setAtivo(boolean v) { ativo = v; }
}
