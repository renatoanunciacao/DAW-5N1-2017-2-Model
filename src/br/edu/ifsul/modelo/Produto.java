package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Renato
 */
@Entity
@Table(name = "produto")
public class Produto implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_produto", sequenceName = "seq_produto_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_produto", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @NotNull(message = "O produto não pode ser em branco")
    @NotBlank(message = "O produto não pode ser em branco")
    @Length(max = 50, message = "O produto não pode ter mais de {max} caracteres")
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;
    @Column(name = "descricao", nullable = false, columnDefinition = "text")
    private String descricao;
    @NotNull(message = "O preço deve ser informado")
    @Column(name = "preco", nullable = false, columnDefinition = "numeric(10,2)")
    private Double preco;
    @NotNull(message = "O estoque não pode ser em branco")
    @Min(value = 0, message = "O estoque não pode ser menor que {value}")
    @Column(name = "estoque", nullable = false, columnDefinition = "numeric(10,2)")
    private Double estoque;
    @NotNull(message = "A marca não pode ser nula")
    @ManyToOne
    @JoinColumn(name = "marca", referencedColumnName = "id", nullable = false)
    @ForeignKey(name = "fk_marca_id")
    private Marca marca;
    @NotNull(message = "O grupo não pode ser nulo")
    @ManyToOne
    @JoinColumn(name = "grupo", referencedColumnName = "id", nullable = false)
    @ForeignKey(name = "fk_grupo_id")
    private Grupo grupo;
    @ManyToMany
    @JoinTable(name = "desejos", joinColumns = 
            @JoinColumn(name = "produto", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "pessoa_fisica", referencedColumnName =  "id", nullable = false))
    private List<PessoaFisica> desejam = new ArrayList<>();

    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Double getEstoque() {
        return estoque;
    }

    public void setEstoque(Double estoque) {
        this.estoque = estoque;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produto other = (Produto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public List<PessoaFisica> getDesejam() {
        return desejam;
    }

    public void setDesejam(List<PessoaFisica> desejam) {
        this.desejam = desejam;
    }

}
