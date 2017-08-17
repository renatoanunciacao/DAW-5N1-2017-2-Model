/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.modelo.Cidade;
import br.edu.ifsul.modelo.Estado;
import br.edu.ifsul.modelo.Grupo;
import br.edu.ifsul.modelo.Marca;
import br.edu.ifsul.modelo.Produto;
import br.edu.ifsul.modelo.Telefone;
import br.edu.ifsul.modelo.Usuario;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Renato
 */
public class TestePersistirUsuario {

    EntityManagerFactory emf;
    EntityManager em;

    public TestePersistirUsuario() {
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
            Usuario u = new Usuario();
            u.setApelido("Wagner");
            u.setSenha("da123456");
            u.setAtivo(true);
            u.setAdministador(false);
            u.setNome("Wagner da Silva");
            u.setCpf("465.275.042-09");
            u.setRg("1254745693");
            u.setBairro("Centro");
            u.setCep("99150-000");
            u.setComplemento("Apartamento");
            u.setNascimento(new GregorianCalendar(2000, Calendar.AUGUST, 03));
            u.setEmail("wagner.programmer@outlook.com");
            u.setEndereco("Sete de Setembro");
            Cidade c = new Cidade();
            c.setNome("Floripa");
            u.setCidade(c);
            Estado e = new Estado();
            e.setNome("Santa Catarina");
            e.setUf("SC");
            c.setEstado(e);

            em.getTransaction().begin();
            em.persist(e);
            em.persist(c);
            em.persist(u);
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            exception = true;
        }
        Assert.assertEquals(false, exception);
    }

}
