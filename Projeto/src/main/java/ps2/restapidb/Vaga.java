package ps2.restapidb;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Vaga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @Column(length = 2000)
    private String descricao;
    private LocalDate publicacao;
    private Boolean ativo;

    @ManyToOne
    @JoinColumn(name="empresa_id")
    private Empresa empresa;

    public Vaga() {}
    public Vaga(Long id, String titulo, String descricao, LocalDate publicacao, Boolean ativo, Empresa empresa) {
        this.id = id; this.titulo = titulo; this.descricao = descricao; this.publicacao = publicacao; this.ativo = ativo; this.empresa = empresa;
    }

    // getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public LocalDate getPublicacao() { return publicacao; }
    public void setPublicacao(LocalDate publicacao) { this.publicacao = publicacao; }
    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
    public Empresa getEmpresa() { return empresa; }
    public void setEmpresa(Empresa empresa) { this.empresa = empresa; }
}