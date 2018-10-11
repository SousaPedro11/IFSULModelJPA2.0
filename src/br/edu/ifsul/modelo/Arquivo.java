
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@Entity
@Table(schema = "IFSUL", name = "arquivo")
public class Arquivo implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_arquivo", sequenceName = "seq_arquivo_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_arquivo", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotNull(message = "O nome deve ser informado")
    @NotBlank(message = "O nome não pode ser em branco")
    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @NotNull(message = "A descrição deve ser informada")
    @NotBlank(message = "A descrição não pode ser em branco")
    @Column(name = "descricao", nullable = false, length = 50)
    private String descricao;

    @NotNull(message = "O arquivo deve ser informado")
    @Column(name = "arquivo", nullable = false)
    @Lob
    private byte[] arquivo;

    @NotNull(message = "O produto deve ser informado")
    @ManyToOne
    @JoinColumn(name = "produto", referencedColumnName = "id", nullable = false)
    private Produto produto;

    public Arquivo() {

    }

    public Integer getId() {

        return this.id;
    }

    public void setId(final Integer id) {

        this.id = id;
    }

    public String getNome() {

        return this.nome;
    }

    public void setNome(final String nome) {

        this.nome = nome;
    }

    public String getDescricao() {

        return this.descricao;
    }

    public void setDescricao(final String descricao) {

        this.descricao = descricao;
    }

    public byte[] getArquivo() {

        return this.arquivo;
    }

    public void setArquivo(final byte[] arquivo) {

        this.arquivo = arquivo;
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
        hash = (79 * hash) + Objects.hashCode(this.id);
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
        final Arquivo other = (Arquivo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
