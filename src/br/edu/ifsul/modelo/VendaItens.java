/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.ForeignKey;

/**
 *
 * @author Renato
 */
@Entity
@Table(name = "venda_itens")
public class VendaItens implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_venda_itens", sequenceName = "seq_venda_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_venda_itens", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotNull(message = "A quantidade  não pode ser nulo")
    @Min(value = 0, message = "A quantidade não pode ser menor do que {value}")
    @Column(name = "quantidade", nullable = false)
    private Double quantidade;

    @NotNull(message = "O valor unitário não pode ser nulo")
    @Min(value = 0, message = "O valor unitário não pode ser menor do que {value}")
    @Column(name = "valor_unitario", nullable = false, columnDefinition = "numeric(10,2)")
    private Double valorUnitario;

    @NotNull(message = "O valor total não pode ser nulo")
    @Min(value = 0, message = "O valor total não pode ser menor do que {value}")
    @Column(name = "valor_total", nullable = false)
    private Double ValorTotal;

    @NotNull(message = "A venda não pode ser nula")
    @ManyToOne
    @JoinColumn(name = "venda", referencedColumnName = "id", nullable = false)
    @ForeignKey(name = "fk_venda_id")
    private Venda venda;

    @NotNull(message = "O produto não pode ser nulo")
    @ManyToOne
    @JoinColumn(name = "produto", referencedColumnName = "id", nullable = false)
    @ForeignKey(name = "fk_produto_id")
    private Produto produto;

    public VendaItens() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Double getValorTotal() {
        return ValorTotal;
    }

    public void setValorTotal(Double ValorTotal) {
        this.ValorTotal = ValorTotal;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final VendaItens other = (VendaItens) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
