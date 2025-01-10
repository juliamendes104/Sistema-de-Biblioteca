
package com.mycompany.sistemabiblioteca;

/**
 * Classe PeriodoInvalidoException é uma subclasse de Exception e pode ser lançada
 * quando o ocorre o cadastro de um aluno com periodo menor que zero.
 * @author julia
 */
public class PeriodoInvalidoException extends Exception{
    public PeriodoInvalidoException(String mensagem){
        super(mensagem);
    }
}
