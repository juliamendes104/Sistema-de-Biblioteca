
package com.mycompany.sistemabiblioteca;

import java.time.LocalDate;

/**
 * Classe Abstrata Item implementa uma interface Emprestavel e representa um item
 * de uma biblioteca com título, autor, ano de publicação, quantidade disponível
 * e quantidade emprestada.
 * @author Julia
 */
public abstract class Item implements Emprestavel{
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private int quanDisponivel;
    private int quanEmprestada = 0;
    
    /**
     * Construtor cria um novo Item.
     * @param titulo o título do item
     * @param autor o autor do item
     * @param anoPublicacao o ano de publicação do item
     * @param quanDisponivel a quantidade disponível de itens
     */
    public Item(String titulo, String autor, int anoPublicacao, int quanDisponivel){
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.quanDisponivel = quanDisponivel;
    }
    
    public String getTitulo(){
        return titulo;
    }
    
    public String getAutor(){
        return autor;
    }
    
    public int getQuanDisponivel(){
        return quanDisponivel;
    }
    
    /**
     * Realiza um empréstimo do item que chama o método ao usuário na data, calcula
     * a data de devolução, cria um empréstimo e o adiciona na lista do usuário.
     * @param usuario o objeto do tipo Usuario que realiza o empréstimo
     * @param dataEmprestimo a data do empréstimo
     * @throws ItemIndisponivelException se a quantidade de itens for insuficiente
     */
    @Override
    public void emprestimo(Usuario usuario, LocalDate dataEmprestimo) throws ItemIndisponivelException{
        LocalDate dataDevolucaoPrevista = dataEmprestimo; 
        
        if(this.quanDisponivel > 0){
            if(usuario instanceof Aluno){
                dataDevolucaoPrevista = dataEmprestimo.plusDays(15);
                System.out.println("Devolução em 15 dias.");
            }
            if(usuario instanceof Professor){
                dataDevolucaoPrevista = dataEmprestimo.plusDays(30);
                System.out.println("Devolução em 30 dias.");
            }
            if(usuario instanceof AssessorTecnico){
                dataDevolucaoPrevista = dataEmprestimo.plusDays(10);
                System.out.println("Devolução em 10 dias.");
            }
        
            Emprestimo emprestimo = new Emprestimo(this,dataEmprestimo,dataDevolucaoPrevista);
            if(usuario!=null){
                usuario.adicionarEmprestimo(emprestimo);
            }
            this.quanDisponivel--;
            this.quanEmprestada++;
        }
        else{
            throw new ItemIndisponivelException("Item indisponível");
        }
    }
    
    /**
     * Realiza a devolução de um item que chamou o método ao usuário na data real,
     * busca o empréstimo na lista de empréstimos do usuário e calcula, se houver,
     * o atraso.
     * @param usuario o objeto do tipo Usuario que devolve o item
     * @param dataDevolucaoReal a data da devolução real
     * @throws ItemNaoEmprestadoException se nenhum empréstimo com o item for encontrado
     * na lista de empréstimos do usuário
     */
    @Override
    public void devolucao(Usuario usuario, LocalDate dataDevolucaoReal) throws ItemNaoEmprestadoException{
        Emprestimo emprestimo = usuario.buscarEmprestimo(this);
        if(emprestimo != null){
            this.quanDisponivel++;
            this.quanEmprestada--;
            emprestimo.setDataDevolucaoReal(dataDevolucaoReal);
            if(emprestimo.atraso()){
                int dias = emprestimo.calcularAtraso();
                double multa = 0;
                if(usuario instanceof Aluno){
                    multa = ((Aluno)usuario).calcularMulta(dias);
                }
                if(usuario instanceof Professor){
                    multa = ((Professor)usuario).calcularMulta(dias);
                }
                if(usuario instanceof AssessorTecnico){
                    multa = ((AssessorTecnico)usuario).calcularMulta(dias);
                }
                System.out.println("Multa de $" + multa);
            }
            System.out.println("Devolvido com sucesso.");
        }
        else{
            throw new ItemNaoEmprestadoException("Item não emprestado");
        }
    }
}
