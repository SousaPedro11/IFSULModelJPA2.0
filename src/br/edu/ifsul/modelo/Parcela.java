package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@Entity
@Table(schema = "IFSUL", name = "parcela")
public class Parcela implements Serializable {

    @EmbeddedId
    private ParcelaID parcelaID;

    @NotNull(message = "O valor deve ser informado")
    @Min(value = 0, message = "O valor  não pode ser negativo")
    @Column(name = "valor", nullable = false, columnDefinition = "decimal(12,2)")
    private Double valor;

    @NotNull(message = "O vencimento não pode ser nulo")
    @Temporal(TemporalType.DATE)
    @Column(name = "vencimento", nullable = false)
    private Calendar vencimento;

    @Min(value = 0, message = "O valor do pagamento não pode ser negativo")
    @Column(name = "valor_pagamento", columnDefinition = "decimal(12,2)")
    private Double valorPagamento;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_pagamento")
    private Calendar dataPagamento;

    public Parcela() {

    }

    public ParcelaID getParcelaID() {

        return this.parcelaID;
    }

    public void setParcelaID(final ParcelaID parcelaID) {

        this.parcelaID = parcelaID;
    }

    public Double getValor() {

        return this.valor;
    }

    public void setValor(final Double valor) {

        this.valor = valor;
    }

    public Calendar getVencimento() {

        return this.vencimento;
    }

    public void setVencimento(final Calendar vencimento) {

        this.vencimento = vencimento;
    }

    public Double getValorPagamento() {

        return this.valorPagamento;
    }

    public void setValorPagamento(final Double valorPagamento) {

        this.valorPagamento = valorPagamento;
    }

    public Calendar getDataPagamento() {

        return this.dataPagamento;
    }

    public void setDataPagamento(final Calendar dataPagamento) {

        this.dataPagamento = dataPagamento;
    }

    @Override
    public int hashCode() {

        int hash = 3;
        hash = (83 * hash) + Objects.hashCode(this.parcelaID);
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
        final Parcela other = (Parcela) obj;
        if (!Objects.equals(this.parcelaID, other.parcelaID)) {
            return false;
        }
        return true;
    }
}
