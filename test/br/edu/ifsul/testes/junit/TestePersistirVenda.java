package br.edu.ifsul.testes.junit;

import java.util.Calendar;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.PessoaFisica;
import br.edu.ifsul.modelo.Produto;
import br.edu.ifsul.modelo.Venda;
import br.edu.ifsul.modelo.VendaItens;

/**
 *
 * @author jorge
 */
public class TestePersistirVenda {

    EntityManager em;

    public TestePersistirVenda() {

    }

    @Before
    public void setUp() {

        this.em = EntityManagerUtil.getEntityManager();
    }

    @After
    public void tearDown() {

        this.em.close();
    }

    @Test
    public void teste() {

        // o teste não deve gerar exceção se tudo estiver correto
        boolean exception = false;
        try {

            final Produto p = this.em.find(Produto.class, 2);
            final PessoaFisica pf = this.em.find(PessoaFisica.class, 2);
            final Venda v = new Venda();
            v.setData(Calendar.getInstance());
            v.setParcelas(3);
            v.setPessoaFisica(pf);
            final VendaItens vi = new VendaItens();
            vi.setProduto(p);
            vi.setQuantidade(5.0);
            vi.setValorUnitario(p.getPreco());
            vi.setValorTotal(vi.getQuantidade() * vi.getValorUnitario());
            v.adicionarItem(vi);
            this.em.getTransaction().begin();
            this.em.persist(v);
            this.em.persist(p);
            this.em.getTransaction().commit();
        } catch (final Exception e) {
            // se gerar exceção
            exception = true;
            e.printStackTrace();
        }
        // compara se não ocorreu erro
        Assert.assertEquals(false, exception);
    }

}
