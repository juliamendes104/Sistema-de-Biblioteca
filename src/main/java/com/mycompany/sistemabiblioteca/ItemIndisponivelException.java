
package com.mycompany.sistemabiblioteca;

/**
 * Classe ItemIndisponivelException é uma subclasse de Exception e pode ser lançada
 * quando ocorre uma operacao de emprestimo de um item com quantidade disponivel
 * menor que zero.
 * @author Julia
 */
public class ItemIndisponivelException extends Exception{
    public ItemIndisponivelException(String mensagem){
        super(mensagem);
    }
}
