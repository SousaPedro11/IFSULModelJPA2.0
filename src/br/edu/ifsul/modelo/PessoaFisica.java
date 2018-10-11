
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@Entity
@Table(schema = "IFSUL", name = "pessoa_fisica")
public class PessoaFisica extends Pessoa implements Serializable {

    @NotNull(message = "O RG não pode ser nulo")
    @NotBlank(message = "O RG não ser em branco")
    @Length(max = 10, message = "O RG não pode ter mais de {max} caracteres")
    @Column(name = "RG", length = 10, nullable = false)
    private String rg;

    @NotNull(message = "O CPF não pode ser nulo")
    @NotBlank(message = "O CPF não ser em branco")
    @Length(max = 14, message = "O CPF não pode ter mais de {max} caracteres")
    @CPF(message = "O CPF deve ser válido")
    @Column(name = "CPF", length = 14, nullable = false)
    private String cpf;

    @NotNull(message = "O nascimento não pode ser nulo")
    @Temporal(TemporalType.DATE)
    @Column(name = "nascimento", nullable = false)
    private Calendar nascimento;

    @NotNull(message = "O nome de usuario não pode ser nulo")
    @NotBlank(message = "O nome de usuario não ser em branco")
    @Length(max = 20, message = "O nome de usuario não pode ter mais de {max} caracteres")
    @Column(name = "nome_usuario", length = 20, nullable = false, unique = true)
    private String nomeUsuario;

    @NotNull(message = "A senha não pode ser nula")
    @NotBlank(message = "A senha não ser em branco")
    @Length(max = 10, message = "A senha não pode ter mais de {max} caracteres")
    @Column(name = "senha", length = 10, nullable = false)
    private String senha;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(schema = "IFSUL", name = "desejos", joinColumns = @JoinColumn(name = "pessoa_fisica", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "produto", referencedColumnName = "id", nullable = false), uniqueConstraints = {
                    @UniqueConstraint(columnNames = { "pessoa_fisica", "produto" }) })
    private List<Produto> desejos = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(schema = "IFSUL", name = "permissoes", joinColumns = @JoinColumn(name = "nome_usuario", referencedColumnName = "nome_usuario", nullable = false), inverseJoinColumns = @JoinColumn(name = "permissao", referencedColumnName = "nome", nullable = false), uniqueConstraints = {
                    @UniqueConstraint(columnNames = { "nome_usuario", "permissao" }) })
    private List<Permissao> permissoes = new ArrayList<>();

    public PessoaFisica() {

    }

    public String getRg() {

        return this.rg;
    }

    public void setRg(final String rg) {

        this.rg = rg;
    }

    public String getCpf() {

        return this.cpf;
    }

    public void setCpf(final String cpf) {

        this.cpf = cpf;
    }

    public Calendar getNascimento() {

        return this.nascimento;
    }

    public void setNascimento(final Calendar nascimento) {

        this.nascimento = nascimento;
    }

    public String getNomeUsuario() {

        return this.nomeUsuario;
    }

    public void setNomeUsuario(final String nomeUsuario) {

        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {

        return this.senha;
    }

    public void setSenha(final String senha) {

        this.senha = senha;
    }

    public List<Produto> getDesejos() {

        return this.desejos;
    }

    public void setDesejos(final List<Produto> desejos) {

        this.desejos = desejos;
    }

    public List<Permissao> getPermissoes() {

        return this.permissoes;
    }

    public void setPermissoes(final List<Permissao> permissoes) {

        this.permissoes = permissoes;
    }

}
