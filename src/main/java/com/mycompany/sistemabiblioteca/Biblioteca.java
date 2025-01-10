
package com.mycompany.sistemabiblioteca;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Classe Biblioteca representa um sistema de gerenciamento de biblioteca.
 * @author julia
 */
public class Biblioteca {
    private List<Item> itens = new ArrayList<>();
    private List<Usuario> cadastrados = new ArrayList<>();
    private Usuario logado;
    
    /**
     * Loga o usuário se o email e senha fornecido corresponderem com um usuário
     * cadastrado.
     * @param email o email fornecido
     * @param senha a senha fornecida
     * @return verdadeiro, se o usuário for logadoo ou falso, se as informações
     * fornecidas estiverem incorretas.
     */
    public boolean logarUsuario(String email, String senha){
        for(Usuario item: cadastrados){
            if(item.getEmail().equals(email)){
                if(item.validarSenha(senha)){
                    this.logado = item;
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        return false;
    }
    
    /**
     * Encontra o item e, se existir, realiza o empréstimo ao usuário logado.
     * @param titulo o titulo do item
     * @param dataEmprestimo a data do empréstimo
     * @throws ItemIndisponivelException lança a excessão para outro método da pilha
     */
    public void emprestimo(String titulo, LocalDate dataEmprestimo) throws ItemIndisponivelException{
        Item item = buscarItem(titulo);
            if(item!=null){
                item.emprestimo(logado, dataEmprestimo);
            }
            else{
                System.out.println("Item não encontrado");
            } 
    }
    
    /**
     * Encontra o item e, se existir, realiza a devolução ao usuário logado.
     * @param titulo o título do item
     * @param dataDevolucaoReal a data real da devolução
     * @throws ItemNaoEmprestadoException lança a excessão para outro método da pilha
     */
    public void devolucao(String titulo, LocalDate dataDevolucaoReal) throws ItemNaoEmprestadoException{
        Item item = buscarItem(titulo);
        if(item!=null){
                item.devolucao(logado, dataDevolucaoReal);
            }
            else{
                System.out.println("Item não encontrado");
            } 
    }
    
    /**
     * Busca o item pelo seu título
     * @param titulo o título do item
     * @return o item, se encontrado, ou vazio se não for encontrado
     */
    public Item buscarItem(String titulo){
        for(Item item: itens){
            if(item.getTitulo().equals(titulo)){
                return item;
            }
        }
        return null;
    }
    
    /**
     * Imprime o título, autor e quantidade disponível do item, se encontrado.
     * @param item o item que se quer imprimir
     */
    public void imprimirItem(Item item){
        if(item!=null){
            if(item instanceof Livro){
                System.out.print("Livro: ");
            }
            if(item instanceof Revista){
                System.out.print("Revista: ");
            }
            if(item instanceof CD){
                System.out.print("CD: ");
            }
            System.out.println(item.getTitulo() + " Autor: " + item.getAutor() + " Quantidade disponível: " + item.getQuanDisponivel());
        }
        else{
            System.out.println("Item não encontrado");
        }
    }
    
    /**
     * Lista os empréstimo du usuário logado ou os empréstimos do aluno que pertence
     * a matrícula fornecida.
     * @return verdadeiro, se a matrícula está correta ou se o usuário está logado ou
     * falso, se a matrícula estiver incorreta
     */
    public boolean listarEmprestimos(){
        Scanner sc = new Scanner(System.in);
        if(this.logado==null){
            System.out.println("Digite a matrícula do usuário:");
            String matricula = sc.nextLine();
            
            for(Usuario item: cadastrados){
                if(item.getMatricula().equals(matricula)){
                    item.listarEmprestimos();
                    return true;
                }
            }
            return false;
        }
        this.logado.listarEmprestimos();
        return true;
    }
    
    public Usuario getLogado(){
        return logado;
    }
    
    /**
     * Atribui vazio ao campo do usuário logado.
     */
    public void sair(){
        this.logado = null;
    }
    
    /**
     * Cadastra um novo aluno e o adiciona na lista de cadastrados.
     * @param nome o nome do aluno
     * @param matricula a matricula do aluno
     * @param senha a senha do aluno
     * @param email o email do aluno
     * @param curso o curso do aluno
     * @param periodo o periodo do aluno
     * @throws SenhaInvalidaException se a senha fornecido não atender as especificações
     * @throws PeriodoInvalidoException se o periodo fornecido for inválido
     */
    public void cadastrarAluno(String nome, String matricula, String senha, String email, String curso, int periodo) throws SenhaInvalidaException, PeriodoInvalidoException{
        Usuario aluno = new Aluno(nome,matricula,senha,email,curso,periodo);
        this.cadastrados.add(aluno);
    }
    
    /**
     * Cadastra um novo professor e o adiciona na lista de cadastrados.
     * @param nome o nome do professor
     * @param matricula a matricula do professor
     * @param senha a senha do professor
     * @param email o email do professor
     * @param departamento o departamento do professor
     * @param titulacao a titulação do professor
     * @throws SenhaInvalidaException lança a excessão
     */
    public void cadastrarProfessor(String nome, String matricula, String senha, String email, String departamento, String titulacao) throws SenhaInvalidaException{
        Usuario professor = new Professor(nome,matricula,senha,email,departamento,titulacao);
        this.cadastrados.add(professor);
    }
    
    /**
     * Cadastra um novo assessor técnico e o adiciona na lista de cadastrados.
     * @param nome o nome do assessor técnico
     * @param matricula a matrícula do assessor técnico
     * @param senha a senha do assessor técnico
     * @param email o email do assessor técnico
     * @param secao a seção do assessor técnico
     * @throws SenhaInvalidaException lança a excessão
     */
    public void cadastrarAssessorTecnico(String nome, String matricula, String senha, String email, String secao) throws SenhaInvalidaException{
        Usuario tecnico = new AssessorTecnico(nome,matricula,senha,email,secao);
        this.cadastrados.add(tecnico);
    }
    
    /**
     * Cadastra um novo livro e o adiciona na lista de itens.
     * @param titulo o título do livro
     * @param autor o autor do livro
     * @param anoPublicacao o ano de publicação do livro
     * @param quanDisponivel a quantidade disponível de livros
     * @param editora a editora do livro
     * @param isbn o isbn do livro
     */
    public void cadastrarLivro(String titulo, String autor, int anoPublicacao, int quanDisponivel, String editora, String isbn){
        Item livro = new Livro(titulo,autor,anoPublicacao,quanDisponivel,editora,isbn);
        this.itens.add(livro);
    }
    
    /**
     * Cadastra uma nova revista e a adiciona na lista de itens.
     * @param titulo o título da revista
     * @param autor o autor da revista
     * @param anoPublicacao o ano de publicação da revista
     * @param quanDisponivel a quantidade disponível de revistas
     * @param volume o volume da revista
     * @param numero o número da revista 
     */
    public void cadastrarRevista(String titulo, String autor, int anoPublicacao, int quanDisponivel, int volume, int numero){
        Item revista = new Revista(titulo,autor,anoPublicacao,quanDisponivel,volume,numero);
        this.itens.add(revista);
    }
    
    /**
     * Cadastra um novo cd e o adiciona na lista de itens.
     * @param titulo o título do cd
     * @param autor o autor do cd
     * @param anoPublicacao o ano de publicação do cd
     * @param quanDisponivel a quantidade disponível de cds
     * @param volume o volume do cd
     * @param gravadora a gravadora do cd
     */
    public void cadastrarCD(String titulo, String autor, int anoPublicacao, int quanDisponivel, int volume, String gravadora){
        Item cd = new CD(titulo,autor,anoPublicacao,quanDisponivel,volume,gravadora);
        this.itens.add(cd);
    }
}
