
package com.mycompany.sistemabiblioteca;

/**
 * Classe CD é subclasse da Classe abstrata Item é um tipo de item que possui volume
 * e uma gravadora.
 * @author julia
 */
public class CD extends Item{
    private int volume;
    private String gravadora;
    
    
    /**
     * Construtor cria novo CD.
     * @param titulo o título do cd
     * @param autor o autor do cd
     * @param anoPublicacao o ano de publicação do cd
     * @param quanDisponivel a quantidade disponível de cds
     * @param volume o volume do cd
     * @param gravadora a gravadora do cd
     */
    public CD(String titulo, String autor, int anoPublicacao, int quanDisponivel, int volume, String gravadora){
        super(titulo,autor,anoPublicacao,quanDisponivel);
        this.volume = volume;
        this.gravadora = gravadora;
    }
}
