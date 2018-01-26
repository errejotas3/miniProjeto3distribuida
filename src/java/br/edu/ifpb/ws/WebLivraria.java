/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.ws;

import br.edu.ifpb.managers.LivroManager;
import br.edu.ifpb.models.Livro;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


@WebService(serviceName = "WebLivraria")
public class WebLivraria {

    private static LivroManager bibliotecario = new LivroManager();

    @WebMethod(operationName = "list")
    public List<Livro> list() {
        
        bibliotecario.iniciar();
        List<Livro> livros = bibliotecario.listar();
        bibliotecario.fechar();

        return livros;
    }
    
    @WebMethod(operationName = "create")
    public Livro create(@WebParam(name = "titulo") String titulo,
            @WebParam(name = "autor") String autor,
            @WebParam(name = "isbn") String isbn,
            @WebParam(name = "editora") String editora,
            @WebParam(name = "edicao") String edicao) {
        System.out.println(titulo + autor + isbn + editora + edicao);
        bibliotecario.iniciar();
        Livro livro = bibliotecario.create(titulo, autor, isbn, editora, edicao);
        bibliotecario.fechar();

        return livro;
    }

    @WebMethod(operationName = "show")
    public Livro show(@WebParam(name = "isbn") String isbn) {
        try {
            bibliotecario.iniciar();
            Livro livro = bibliotecario.search(isbn);
            bibliotecario.fechar();
            return livro;
        } catch (Exception e) {
            e.getStackTrace();
        }
        return null;
    }

    @WebMethod(operationName = "update")
    public Livro update(@WebParam(name = "id") int id,
            @WebParam(name = "titulo") String titulo) {

        bibliotecario.iniciar();
        Livro livro = bibliotecario.update(id, titulo);
        bibliotecario.fechar();

        return livro;
    }

    @WebMethod(operationName = "delete")
    public String delete(@WebParam(name = "isbn") String isbn) {
        try {
            bibliotecario.iniciar();
            bibliotecario.remove(isbn);
            bibliotecario.fechar();
            return "Livro removido com sucesso!";
        } catch (Exception e) {
            e.getStackTrace();
        }
        return "Livro não encontrado!";
    }
}
