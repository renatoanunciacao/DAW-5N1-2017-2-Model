/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.modelo.Marca;
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
public class TestePersistirMarca {
    
    EntityManagerFactory emf;
    EntityManager em;
    
    public TestePersistirMarca() {
        
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
    public void teste(){
        boolean exception = false;
        try{
            Marca m = new Marca();
            m.setNome("NIKE");
            em.getTransaction().begin();
            em.persist(m);
            em.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            exception = true;
        }
        Assert.assertEquals(false, exception);
    }
    
}
