
package com.mycompany.sistemabiblioteca;

import java.util.ArrayList;
import java.util.List;

/**
 * A classe abstrata Usuario representa um usuário do sistema da biblioteca com
 * nome, matrícula, senha, email e lista de empréstimos.
 * @author Julia
 */
public abstract class Usuario {
    private String nome;
    private String matricula;
    private String senha;
    private String email;
    private List<Emprestimo> emprestimos;
    
    /**
     * Construtor cria novo Usuario.
     * @param nome o nome do usuario
     * @param matricula a matricula do usuario
     * @param senha a senha do usuario
     * @param email o email do usuario
     * @throws SenhaInvalidaException se a senha fornecida não atende as especificações
     */
    public Usuario(String nome, String matricula,String senha,String email) throws SenhaInvalidaException{
        if(!verificarSenha(senha)){
            throw new SenhaInvalidaException("Senha Inválida");
        }
        this.nome = nome;
        this.matricula = matricula;
        this.senha = senha;
        this.email = email;
        emprestimos = new ArrayList<>();
    }
    
    /**
     * Verifica se a senha possui mais de 4 caracteres e se possui caracteres especiais.
     * @param senha a senha fornecida
     * @return verdadeiro, se a senha é válida ou falso, se a senha é inválida
     */
    private boolean verificarSenha(String senha){
        boolean caractereEspecial = false;
        if(senha.length()>4){
            for(int i=0; i<senha.length(); i++){
                char c = senha.charAt(i);
                if(c=='@' || c==';' || c=='-' || c=='_'){
                    caractereEspecial = true;
                }
            }
        }
        return caractereEspecial;
    }
    
    /**
     * Adiciona um empréstimo na lista de empréstimos do usuário.
     * @param emprestimo o objeto do tipo Emprestimo
     */
    public void adicionarEmprestimo(Emprestimo emprestimo){
        emprestimos.add(emprestimo);
    }
    
    /**
     * Busca um empréstimo de um item na lista de empréstimos do usuário que corresponda
     * com o item fornecido.
     * @param emprestado o objeto do tipo Item
     * @return o empréstimo, se encontrado, caso contrário, retorna vazio
     */
    public Emprestimo buscarEmprestimo(Item emprestado){
        for(Emprestimo item: emprestimos){
            if(emprestado == item.getEmprestado()){
                return item;
            }
        }
        return null;
    }
    
    /**
     * Imprime o título dos itens emprestados e a data de empréstimo da lista de
     * empréstimos do usuário.
     */
    public void listarEmprestimos(){
        for(Emprestimo item: emprestimos){
            System.out.println("Título: " + item.getEmprestado().getTitulo() + " Data de empréstimo: " + item.getDataEmprestimo());
        }
    }
    
    /**
     * Acessa e devolve o atributo privado matricula.
     * @return o campo matricula
     */
    public String getMatricula(){
        return this.matricula;
    }
    
    /**
     * Acessa e devolve o atributo privado email.
     * @return o campo email
     */
    public String getEmail(){
        return this.email;
    }
    
    /**
     * Valida a senha fornecida com o valor da senha do usuário.
     * @param senhaComparar a senha fornecida
     * @return verdadeira, se a senha for a mesma ou falso, se for diferente
     */
    public boolean validarSenha(String senhaComparar){
        return this.senha.equals(senhaComparar);
    }
    
    /**
     * Método abstrato para as classes mais específicas implementarem o cálcula
     * da multa.
     * @param dias dias de atraso
     * @return a multa calculada
     */
    public abstract double calcularMulta(int dias);
}
