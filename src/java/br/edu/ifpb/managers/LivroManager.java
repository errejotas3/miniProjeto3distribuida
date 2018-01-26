/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.managers;

import br.edu.ifpb.models.Livro;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class LivroManager {

    protected static EntityManager manager;

    public LivroManager() {
    }

    public static void iniciar() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("LivrariaPU");
        manager = factory.createEntityManager();
    }

    public static void fechar() {
        if (manager != null) {
            manager = null;
        }
    }

    public List<Livro> listar() {
        Query query = manager.createQuery("select l from " + Livro.class.getSimpleName() + " l");
        System.out.println(query.getResultList().size());
        return (List<Livro>) query.getResultList();
    }

    public static Livro create(String titulo, String autor, String isbn, String editora, String edicao) {
        try {
            manager.getTransaction().begin();

            Livro livro = new Livro();
            livro.setTitulo(titulo);
            livro.setAutor(autor);
            livro.setIsbn(isbn);
            livro.setEditora(editora);
            livro.setEdicao(edicao);

            manager.persist(livro);
            manager.getTransaction().commit();

            return livro;
        } catch (Exception e) {
            e.printStackTrace();
            manager.getTransaction().rollback();
        } finally {
            manager.close();
        }
        return null;
    }

    public static void remove(String isbn) {
        try {
            manager.getTransaction().begin();

            Query q = manager.createQuery("select l from Livro l where l.isbn = '" + isbn + "'");
            Livro livro = (Livro) q.getSingleResult();
            System.out.println(livro.getTitulo());
            manager.remove(livro);
            manager.getTransaction().commit();
        } catch (NoResultException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            manager.getTransaction().rollback();
        } finally {
            manager.close();
        }
    }

    public static Livro search(String isbn) {
        try {
            Query q = manager.createQuery("select l from Livro l where l.isbn = '" + isbn + "'");
            return (Livro) q.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Livro update(int id, String titulo) {
        try {
            manager.getTransaction().begin();
            Query q = manager.createQuery("select l from Livro l where l.id= " + id);
            Livro livro = (Livro) q.getSingleResult();
            livro.setTitulo(titulo);

            manager.merge(livro);
            manager.getTransaction().commit();

            return livro;
        } catch (Exception e) {
            e.printStackTrace();
            manager.getTransaction().rollback();
        } finally {
            manager.close();
        }
        return null;
    }
}
