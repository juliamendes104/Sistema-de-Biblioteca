
package com.mycompany.sistemabiblioteca;

/**
 * Classe SenhaInvalidaException é uma subclasse de Exception e pode ser lançada
 * quando ocorre o cadastro de um usuário com senha menor que 5 caracteres e sem
 * caracteres especiais.
 * @author Julia
 */
public class SenhaInvalidaException extends Exception{
    public SenhaInvalidaException(String mensagem){
        super(mensagem);
    }
}
