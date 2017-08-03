/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes;

import br.edu.ifsul.modelo.Estado;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Renato
 */
public class TesteListarEstados {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DAW-5N1-2017-2-PU");
        EntityManager em = emf.createEntityManager();
       List<Estado> lista = em.createQuery("from Estado").getResultList();
      for(Estado e: lista){
          System.out.println("ID: "+e.getId() + " Nome: "+e.getNome()+ " UF: "+e.getUf());
      }
    
}
    
}
