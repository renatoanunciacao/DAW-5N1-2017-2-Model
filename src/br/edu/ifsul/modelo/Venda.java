/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Renato
 */
@Entity
@Table(name = "venda")
public class Venda implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_venda", sequenceName = "seq_venda_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_venda", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotNull(message = "A data não pode ser nula")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data", nullable = false)
    private Calendar data;

    @NotNull(message = "O valor total não pode ser nulo")
    @Min(value = 0, message = "O valot total não pode ser menor do que {value}")
    @Column(name = "valor_total", nullable = false, columnDefinition = "numeric(10,2)")
    private Double valor_total;

    @NotNull(message = "A quantidade de parcelas não pode ser nulo")
    @Min(value = 0, message = "A quantidade de parcelas não pode ser menor do que {value}")
    @Column(name = "parcelas", nullable = false)
    private Integer parcelas;

    @NotNull(message = "O pagamento não pode ser nulo")
    @Length(max = 30, message = "O pagamento deve ser menor do que {max} caracteres")
    @Column(name = "pagamento", length = 30)
    private String pagamento;

    @NotNull(message = "O usuário deve ser informado")
    @ManyToOne
    @JoinColumn(name = "usuario", referencedColumnName = "id", nullable = false)
    @ForeignKey(name = "fk_usuario_id")
    private Usuario usuario;

    @NotNull(message = "A pessoa fisica deve ser informada")
    @ManyToOne
    @JoinColumn(name = "pessoa_fisica", referencedColumnName = "rg", nullable = false)
    @ForeignKey(name = "fk_pessoa_fisica_id")
    private PessoaFisica pessoaFisica;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<VendaItens> itens = new ArrayList<>();

    @OneToMany(mappedBy = "parcelaID.venda", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Parcela> listaParcelas = new ArrayList<>();

    public Venda() {
        this.valor_total = 0.0;
    }

    public void geraParcelas() {
        this.listaParcelas.clear();
        Double valorParcela = this.valor_total / this.parcelas;
        for (int i = 1; i <= this.parcelas; i++) {
            Parcela par = new Parcela();
            ParcelaID ident = new ParcelaID();
            ident.setNumero(i);
            ident.setVenda(this);
            par.setParcelaID(ident);
            par.setValor(valorParcela);
            //maneira errada de copiar data
            //Calendar vencimento = this.data;
            //maneira correta de alterar datas
            Calendar vencimento = (Calendar)this.data.clone();
            vencimento.add(Calendar.MONTH, i);
            par.setVencimento(vencimento);
            this.listaParcelas.add(par);
        }
    }

    public void adicionarItem(VendaItens obj) {
        obj.setVenda(this);
        this.valor_total += obj.getValorTotal();
        this.itens.add(obj);
    }

    public void removerItem(int index) {
        VendaItens obj = this.itens.get(index);
        this.valor_total -= obj.getValorTotal();
        this.itens.remove(index);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public Double getValor_total() {
        return valor_total;
    }

    public void setValor_total(Double valor_total) {
        this.valor_total = valor_total;
    }

    public Integer getParcelas() {
        return parcelas;
    }

    public void setParcelas(Integer parcelas) {
        this.parcelas = parcelas;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final Venda other = (Venda) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public PessoaFisica getPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(PessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }

    public List<VendaItens> getItens() {
        return itens;
    }

    public void setItens(List<VendaItens> itens) {
        this.itens = itens;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Parcela> getListaParcelas() {
        return listaParcelas;
    }

    public void setListaParcelas(List<Parcela> listaParcelas) {
        this.listaParcelas = listaParcelas;
    }

}
