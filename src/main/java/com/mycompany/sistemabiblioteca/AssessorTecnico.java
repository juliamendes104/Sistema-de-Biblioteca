
package com.mycompany.sistemabiblioteca;

/**
 * Classe AssessorTecnico é uma subclasse da Classe abstrata Usuario e é um tipo
 * de usuário do sistema que presta serviço a uma seção.
 * @author Julia
 */
public class AssessorTecnico extends Usuario {
    private String secao;
    
    /**
     * Construtor cria um novo Assessor Técnico.
     * @param nome nome do assessor técnico
     * @param matricula a matrícula do assessor técnico
     * @param senha a senha do assessor técnico
     * @param email o email do assessor técnico
     * @param secao a secao do assessor técnico
     * @throws SenhaInvalidaException se a senha fornecida não atender as exigências
     */
    public AssessorTecnico(String nome, String matricula, String senha, String email, String secao)throws SenhaInvalidaException{
        super(nome,matricula,senha, email);
        this.secao = secao;
    }
    
    /**
     * Sobreescrito para calcular a multa multiplicando os dias de atraso por 5.0
     * e aplicando uma taxa de 15%.
     * @param dias os dias de atraso
     * @return a multa calculada
     */
    @Override
    public double calcularMulta(int dias){
        double multa = dias * 5.0;
        return multa * 1.15;
    }
}
