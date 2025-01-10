
package com.mycompany.sistemabiblioteca;

/**
 * Classe Professor é subclasse da Classe abstrata Usuario e é um tipo de usuario
 * do sistema que pertence a um departamento e possui uma titulação.
 * @author Julia
 */
public class Professor extends Usuario{
    private String departamento;
    private String titulacao;
    
    /**
     * Construtor cria novo Professor.
     * @param nome o nome do professor
     * @param matricula a matricula do professor
     * @param senha a senha do professor
     * @param email o email do professor
     * @param departamento o departamento do professor
     * @param titulacao a titulação do professor
     * @throws SenhaInvalidaException se a senha fornecida não atender as exigências
     */
    public Professor(String nome, String matricula, String senha, String email, String departamento, String titulacao) throws SenhaInvalidaException{
        super(nome,matricula,senha,email);
        this.departamento = departamento;
        this.titulacao = titulacao;
    }
    
    /**
     * Sobreescrito para calcular a multa multiplicando os dias de atraso por 5.0
     * e aplicando uma taxa de 25%.
     * @param dias os dias de atraso
     * @return a multa calculada
     */
    @Override
    public double calcularMulta(int dias){
        double multa = dias * 5.0;
        return multa * 1.25;
    }
}
