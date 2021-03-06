
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@Entity
@Table(schema = "IFSUL", name = "compra")
public class Compra implements Serializable {

    @EmbeddedId
    private CompraID id;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "data", nullable = false)
    private Calendar data;

    @NotNull(message = "O valor total deve ser informado")
    @Column(name = "valor_total", nullable = false, columnDefinition = "numeric(10,2)")
    private Double valorTotal;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CompraItem> itens = new ArrayList<>();

    public Compra() {

        this.valorTotal = 0.0;
    }

    public void adicionarItem(final CompraItem obj) {

        obj.setCompra(this);
        this.valorTotal += obj.getValorTotal();
        this.itens.add(obj);
    }

    public void removerItem(final int index) {

        final CompraItem obj = this.itens.get(index);
        this.valorTotal -= obj.getValorTotal();
        this.itens.remove(index);
    }

    public CompraID getId() {

        return this.id;
    }

    public void setId(final CompraID id) {

        this.id = id;
    }

    public Calendar getData() {

        return this.data;
    }

    public void setData(final Calendar data) {

        this.data = data;
    }

    public Double getValorTotal() {

        return this.valorTotal;
    }

    public void setValorTotal(final Double valorTotal) {

        this.valorTotal = valorTotal;
    }

    @Override
    public int hashCode() {

        int hash = 5;
        hash = (31 * hash) + Objects.hashCode(this.id);
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
        final Compra other = (Compra) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public List<CompraItem> getItens() {

        return this.itens;
    }

    public void setItens(final List<CompraItem> itens) {

        this.itens = itens;
    }
}
