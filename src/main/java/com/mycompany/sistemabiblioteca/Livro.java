
package com.mycompany.sistemabiblioteca;

/**
 * Classe Livro é subclasse da Classe abstrata Item e é um tipo de item que possui
 * uma editora e um padrão numérico.
 * @author Julia
 */
public class Livro extends Item{
    private String editora;
    private String isbn;
    
    /**
     * Construtor cria novo Livro.
     * @param titulo o título do livro
     * @param autor o autor do livro
     * @param anoPublicacao o ano de publicação do livro
     * @param quanDisponivel a quantidade disponível de livros
     * @param editora a editora do livro
     * @param isbn o isbn do livro
     */
    public Livro(String titulo, String autor, int anoPublicacao, int quanDisponivel, String editora, String isbn){
        super(titulo,autor,anoPublicacao,quanDisponivel);
        this.editora = editora;
        this.isbn = isbn;
    }
}
