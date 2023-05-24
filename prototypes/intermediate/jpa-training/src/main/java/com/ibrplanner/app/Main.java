package com.ibrplanner.app;

import com.ibrplanner.app.domains.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        Pessoa p1 = new Pessoa(null, "Charles Borges", "charles@gmail.com");
        Pessoa p2 = new Pessoa(null, "Carlos da Silva", "carlos@gmail.com");
        Pessoa p3 = new Pessoa(null, "Ana Maria", "ana@gmail.com");
        Pessoa p4 = new Pessoa(null, "Francisco Robson", "francisco@gmail.com");

        // exemplo-jpa: apelido criado no arquivo persistence.xml
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
        EntityManager em = emf.createEntityManager();

        // salvando os objetos no DB
        /* Inserção
        em.getTransaction().begin();
        em.persist(p1);
        em.persist(p2);
        em.persist(p3);
        em.persist(p4);
        em.getTransaction().commit();
        */

        // Buscando (Select) um registro identificado pelo Id
        Pessoa p = em.find(Pessoa.class, 3);

        // Deletando a Pessoa em p
        em.getTransaction().begin();
        em.remove(p);
        em.getTransaction().commit();

        //System.out.println(p);
        System.out.println("Finalizado!");

        em.close();
        emf.close();
    }
}
