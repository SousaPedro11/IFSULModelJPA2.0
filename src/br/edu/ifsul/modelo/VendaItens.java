
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

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@Entity
@Table(schema = "IFSUL", name = "venda_itens")
public class VendaItens implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_venda_itens", sequenceName = "seq_venda_itens_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_venda_itens", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotNull(message = "O quantidade deve ser informada")
    @Min(value = 0, message = "A quantidade não pode ser negativa")
    @Column(name = "quantidade", nullable = false, columnDefinition = "decimal(12,2)")
    private Double quantidade;

    @NotNull(message = "O valor unitario deve ser informado")
    @Min(value = 0, message = "O valor unitario não pode ser negativo")
    @Column(name = "valor_unitario", nullable = false, columnDefinition = "decimal(12,2)")
    private Double valorUnitario;

    @NotNull(message = "O valor total deve ser informado")
    @Min(value = 0, message = "O valor total não pode ser negativo")
    @Column(name = "valor_total", nullable = false, columnDefinition = "decimal(12,2)")
    private Double valorTotal;

    @NotNull(message = "A venda não pode ser nula")
    @ManyToOne
    @JoinColumn(name = "venda", referencedColumnName = "id", nullable = false)
    private Venda venda;

    @NotNull(message = "O produto não pode ser nulo")
    @ManyToOne
    @JoinColumn(name = "produto", referencedColumnName = "id", nullable = false)
    private Produto produto;

    public VendaItens() {

    }

    public Integer getId() {

        return this.id;
    }

    public void setId(final Integer id) {

        this.id = id;
    }

    public Double getQuantidade() {

        return this.quantidade;
    }

    public void setQuantidade(final Double quantidade) {

        this.quantidade = quantidade;
    }

    public Double getValorUnitario() {

        return this.valorUnitario;
    }

    public void setValorUnitario(final Double valorUnitario) {

        this.valorUnitario = valorUnitario;
    }

    public Double getValorTotal() {

        return this.valorTotal;
    }

    public void setValorTotal(final Double valorTotal) {

        this.valorTotal = valorTotal;
    }

    public Venda getVenda() {

        return this.venda;
    }

    public void setVenda(final Venda venda) {

        this.venda = venda;
    }

    public Produto getProduto() {

        return this.produto;
    }

    public void setProduto(final Produto produto) {

        this.produto = produto;
    }

    @Override
    public int hashCode() {

        int hash = 7;
        hash = (23 * hash) + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final VendaItens other = (VendaItens) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
