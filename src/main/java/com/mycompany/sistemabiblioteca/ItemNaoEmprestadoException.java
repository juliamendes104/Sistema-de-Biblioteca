
package com.mycompany.sistemabiblioteca;

/**
 * Classe ItemNaoEmprestadoException é uma subclasse de Exception e pode ser lançada
 * quando ocorre uma operação de devolução de um item que não está emprestado.
 * @author Julia
 */
public class ItemNaoEmprestadoException extends Exception{
    public ItemNaoEmprestadoException(String mensagem){
        super(mensagem);
    }
}
