
package com.mycompany.sistemabiblioteca;

/**
 * Classe Aluno é subclasse da Classe abstrata Usuario e é um tipo de usuario do
 * sistema que cursa uma graduacao e está em um periodo.
 * @author Julia
 */
public class Aluno extends Usuario{
    private String curso;
    private int periodo;
    
    /**
     * Construtor que cria um novo aluno.
     * @param nome o nome do aluno
     * @param matricula a matricula do aluno 
     * @param senha a senha do aluno
     * @param email o email do aluno
     * @param curso o curso do aluno
     * @param periodo o periodo do aluno
     * @throws SenhaInvalidaException se a senha fornecida não atender as exigências
     * @throws PeriodoInvalidoException se o periodo fornecido não for um numero
     * válido
     */
    public Aluno(String nome, String matricula, String senha, String email, String curso, int periodo)throws SenhaInvalidaException, PeriodoInvalidoException{
        super(nome,matricula,senha,email);
        if(!verificarPeriodo(periodo)){
            throw new PeriodoInvalidoException("Periodo deve ser maior que 0");
        }
        this.curso = curso;
        this.periodo = periodo;
    }
    
    /**
     * Verifica se o periodo fornecido é maior que zero.
     * @param periodo o periodo do aluno
     * @return verdadeiro, se o periodo for maior que zero ou falso, se não for
     * maior que zero
     */
    private boolean verificarPeriodo(int periodo){
        return periodo>0;
    }
    
    /**
     * Sobreescrito para calcular a multa multiplicando os dias de atraso por 5.0
     * @param dias os dias de atraso
     * @return a multa calculada
     */
    @Override
    public double calcularMulta(int dias){
        return dias * 5.0;
    }
}
