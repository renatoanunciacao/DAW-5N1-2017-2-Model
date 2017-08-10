/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.modelo.Grupo;
import br.edu.ifsul.modelo.Marca;
import br.edu.ifsul.modelo.Produto;
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
public class TestePersistirProduto {
    EntityManagerFactory emf;
    EntityManager em;
    
    public TestePersistirProduto() {
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
           Produto p = new Produto();
           p.setNome("Smartphone Samsung");
           p.setDescricao("Celular Samsung modelo J5");
           p.setPreco(500.00);
           p.setEstoque(3.0);
           Marca m = em.find(Marca.class, 1);
           p.setMarca(m);
           Grupo g = em.find(Grupo.class, 1);
           p.setGrupo(g);
           em.getTransaction().begin();
           em.persist(p);
           em.getTransaction().commit();
              
       }catch(Exception e){
           e.printStackTrace();
           exception = true;
       }
       Assert.assertEquals(false, exception);
    }
    
}
