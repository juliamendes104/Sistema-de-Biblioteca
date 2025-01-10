
package com.mycompany.sistemabiblioteca;

import java.time.LocalDate;

/**
 * Interface Emprestavel estabelece um contrato com a biblioteca.
 * @author julia
 */
public interface Emprestavel {
    
    /**
     * Realiza um empréstimo.
     * @param usuario o usuário que pede um empréstimo do item
     * @param dataEmprestimo a data do empréstimo
     * @throws ItemIndisponivelException se a quantidade do item for insuficiente
     */
    public abstract void emprestimo(Usuario usuario, LocalDate dataEmprestimo) throws ItemIndisponivelException;
    
    /**
     * Realiza uma devolução.
     * @param usuario o usuário que devolve o item
     * @param dataDevolucaoReal a data da devolução real
     * @throws ItemNaoEmprestadoException se o usuário não ter feito um empréstimo
     * desse item;
     */
    public abstract void devolucao(Usuario usuario, LocalDate dataDevolucaoReal) throws ItemNaoEmprestadoException;
}
