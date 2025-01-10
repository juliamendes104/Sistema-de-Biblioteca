
package com.mycompany.sistemabiblioteca;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Classe Emprestimo representa um empréstimo de um item feito por um usuário.
 * @author Julia
 */
public class Emprestimo {
    private Item emprestado;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoReal;
    
    /**
     * Construtor cria novo Emprestimo
     * @param emprestado o item emprestado
     * @param dataEmprestimo a data do empéstimo
     * @param dataDevolucaoPrevista a data calculada para devolução
     */
    public Emprestimo(Item emprestado, LocalDate dataEmprestimo, LocalDate dataDevolucaoPrevista){
        this.emprestado = emprestado;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }
    
    public Item getEmprestado(){
        return this.emprestado;
    }
    
    /**
     * Formata o objeto LocalDate em uma String em formato de data.
     * @return 
     */
    public String getDataEmprestimo(){
        String data = this.dataEmprestimo.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return data;
    }
    
    public void setDataDevolucaoReal(LocalDate dataDevolucao){
        this.dataDevolucaoReal = dataDevolucao;
    }
    
    /**
     * Determina se houve atraso na data de devolução real comparada com a prevista.
     * @return verdadeiro, se devolveu mais tardeou falso, se devolveu antes ou no dia
     */
    public boolean atraso(){
        return this.dataDevolucaoReal.isAfter(this.dataDevolucaoPrevista);
    }
    
    /**
     * Calcula a quantidade de dias de atraso entre as datas de devolução real e
     * prevista.
     * @return dias de atraso
     */
    public int calcularAtraso(){
        long diasAtraso = ChronoUnit.DAYS.between(this.dataDevolucaoPrevista,this.dataDevolucaoReal);
        int dias = (int) diasAtraso;
        return dias;
    }
}
