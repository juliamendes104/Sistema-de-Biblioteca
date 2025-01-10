
package com.mycompany.sistemabiblioteca;

/**
 * Classe Revista é subclasse da classe abstrata Item e é um tipo de item que possui
 * volume e número de edição.
 * @author julia
 */
public class Revista extends Item{
    private int volume;
    private int numero;
    
    /**
     * Construtor cria nova Revista.
     * @param titulo o título da revista
     * @param autor o autor da revista
     * @param anoPublicacao o ano de publicação da revista
     * @param quanDisponivel a quantidade disponível de revistas
     * @param volume o volume da revista
     * @param numero o número da revista
     */
    public Revista(String titulo, String autor, int anoPublicacao, int quanDisponivel, int volume, int numero){
        super(titulo,autor,anoPublicacao,quanDisponivel);
        this.volume = volume;
        this.numero = numero;
    }
}
