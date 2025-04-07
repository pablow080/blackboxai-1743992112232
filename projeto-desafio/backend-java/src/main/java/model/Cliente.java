package model;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPessoa tipoPessoa;
    
    @Column(unique = true, nullable = false, length = 14)
    private String documento;
    
    // Campos para pessoa física
    @Column(length = 100)
    private String nome;
    
    @Column(length = 12)
    private String rg;
    
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    
    // Campos para pessoa jurídica
    @Column(length = 150)
    private String razaoSocial;
    
    @Column(length = 16)
    private String inscricaoEstadual;
    
    @Temporal(TemporalType.DATE)
    private Date dataCriacao;
    
    @Column(length = 255)
    private String email;
    
    private boolean ativo = true;
    
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> enderecos;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoPessoa getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(TipoPessoa tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    // Demais getters e setters...
    
    public enum TipoPessoa {
        FISICA, JURIDICA
    }
}