/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.modelo.Cidade;
import br.edu.ifsul.modelo.Estado;
import br.edu.ifsul.modelo.PessoaFisica;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Renato
 */
public class TestePersistirPessoaFisica {

    EntityManagerFactory emf;
    EntityManager em;

    public TestePersistirPessoaFisica() {
    }

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("DAW-5N1-2017-2-PU");
        em = emf.createEntityManager();
    }

    @After
    public void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    public void teste() {
        boolean exception = false;
        try {
            Cidade c = em.find(Cidade.class, 2);
            PessoaFisica pf = new PessoaFisica();
            pf.setCidade(c);
            pf.setNome("Renato Anunciação");
            pf.setBairro("Lucas Araújo");
            pf.setCep("99072-100");
            pf.setComplemento("blablabla");
            pf.setCpf("025.847.580-32");
            pf.setEmail("wagnerpunk002@gmail.com");
            pf.setEndereco("Padre Valentim");
            pf.setNascimento(new GregorianCalendar(1991, Calendar.JULY, 02));
            pf.setRg("5109797422");
            em.getTransaction().begin();
            em.persist(pf);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            exception = true;
        }
        //verifica que o valor do atributo exception continua falso
        Assert.assertEquals(false, exception);
    }
}
