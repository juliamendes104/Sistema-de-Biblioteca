
package com.mycompany.sistemabiblioteca;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Classe DisplayBiblioteca representa um display de uma biblioteca e é responsável
 * por exibir suas informações relevantes.
 * @author Julia
 */
public class DisplayBiblioteca {
    private Biblioteca biblioteca;
    
    /**
     * Construtor para cadastrar os novos itens.
     * @param usuario 
     */
    public DisplayBiblioteca(String[] usuario){
        this.biblioteca = new Biblioteca();
        
        for(int i=0; i<usuario.length; i++){
            if(usuario[i].equals("Livro")){
                biblioteca.cadastrarLivro(usuario[i+1],usuario[i+2],Integer.parseInt(usuario[i+3]),Integer.parseInt(usuario[i+4]),usuario[i+5],usuario[i+6]);
            }
            if(usuario[i].equals("Revista")){
                biblioteca.cadastrarRevista(usuario[i+1],usuario[i+2],Integer.parseInt(usuario[i+3]),Integer.parseInt(usuario[i+4]),Integer.parseInt(usuario[i+5]),Integer.parseInt(usuario[i+6]));
            }
            if(usuario[i].equals("CD")){
                biblioteca.cadastrarCD(usuario[i+1],usuario[i+2],Integer.parseInt(usuario[i+3]),Integer.parseInt(usuario[i+4]),Integer.parseInt(usuario[i+5]),usuario[i+6]);
            }
        }
    }
    
    /**
     * Fornece as opções de cadastro de usuário, login, busca de itens ou lista de
     * empréstimos.
     */
    public void telaOperacao(){
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Login");
        System.out.println("2. Cadastro");
        System.out.println("3. Busca por nome de item");
        System.out.println("4. Lista de empréstimos de um usuário");
        int op = sc.nextInt();
        
        switch(op){
            case 1: login();
                break;
            case 2: cadastro();
                break;
            case 3: buscarItem();
                break;
            case 4: listarEmprestimos();
                break;
            default: System.out.println("Selecione um número de 1 a 4");
                telaOperacao();
                break;
        }
    }
    
    /**
     * Realiza um login com as informações fornecidas.
     */
    private void login(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite seu email:");
        String email = sc.nextLine();
        
        System.out.println("Digite sua senha:");
        String senha = sc.nextLine();
        
        if(!biblioteca.logarUsuario(email,senha)){
            System.out.println("Algo deu errado");
            login();
        }
        else{
            System.out.println("Usuário logado");
            telaUsuario();
        }
    }
    
    /**
     * Fornece na tela as opções possíveis após o login de um usuário.
     */
    private void telaUsuario(){
        Scanner sc = new Scanner(System.in);
        int op;
        do{
            System.out.println("1. Realizar empréstimo de item");
            System.out.println("2. Realizar devolução de item");
            System.out.println("3. Busca por nome de item");
            System.out.println("4. Lista de empréstimos realizados");
            System.out.println("5. Sair");
            op = sc.nextInt();
            
            switch(op){
                case 1: emprestimo();
                    break;
                case 2: devolucao();
                    break;
                case 3: buscarItem();
                    break;
                case 4: listarEmprestimos();
                    break;
                case 5: sair();
                    break;
                default: System.out.println("Selecione um número de 1 a 5");
                    break;
            }
        }while(op!=5);
    }
    
    /**
     * Cadastra um usuário com as informações fornecidas e trata as excessões da
     * senha inválida e do período inválido.
     */
    private void cadastro(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome:");
        String nome = sc.nextLine();
        
        System.out.println("Digite a matrícula:");
        String matricula = sc.nextLine();
        
        System.out.println("Digite o email:");
        String email = sc.nextLine();
        
        System.out.println("Digite a senha:");
        String senha = sc.nextLine();
        
        int op;
        do{
            System.out.println("Selecione:");
            System.out.println("1. Aluno");
            System.out.println("2. Professor");
            System.out.println("3. Assessor técnico");
            op = sc.nextInt();
            
            sc.nextLine();//quebra de linha
            
            try{
            switch(op){
                case 1:System.out.println("Digite o curso:");
                    String curso = sc.nextLine();
                    
                    System.out.println("Digite o período:");
                    int periodo = sc.nextInt();
                    biblioteca.cadastrarAluno(nome, matricula, senha, email, curso, periodo);
                    break;
                case 2:System.out.println("Digite o departamento:");
                    String departamento = sc.nextLine();
                    
                    System.out.println("Digite a titulação:");
                    String titulacao = sc.nextLine();
                    biblioteca.cadastrarProfessor(nome, matricula, senha, email, departamento, titulacao);
                    break;
                case 3:System.out.println("Digite a seção:");
                    String secao = sc.nextLine();
                    biblioteca.cadastrarAssessorTecnico(nome, matricula, senha, email, secao);
                    break;
                default: System.out.println("Operação inválida");
            }
            } catch(SenhaInvalidaException | PeriodoInvalidoException e){
                System.err.println(e.getMessage());
                cadastro();
            }
        }while(op<1 || op>3);
        
        telaOperacao();
    }
    
    /**
     * Realiza o empréstimo com o título do item fornecido e data de empréstimo e
     * trata a excessão do item indisponível.
     */
    private void emprestimo(){
        Scanner sc = new Scanner(System.in);
        try{
            System.out.println("Digite o título do item:");
            String titulo = sc.nextLine();

            System.out.println("Digite a data do empréstimo(Utilize /):");
            String data = sc.nextLine();
            String[] campos = data.split("/");
            LocalDate dataEmprestimo = LocalDate.of(Integer.parseInt(campos[2]), Integer.parseInt(campos[1]), Integer.parseInt(campos[0]));
            //criar excessão para NumberFormatException
            
            biblioteca.emprestimo(titulo, dataEmprestimo);
            
        } catch(ItemIndisponivelException e){
            System.err.println(e.getMessage());
            telaUsuario();
        }
    }
    
    /**
     * Realiza a devolução com o título do item fornecido e a data real da devolução
     * e trata a excessão do item não emprestado.
     */
    private void devolucao(){
        Scanner sc = new Scanner(System.in);
        try{
            System.out.println("Digite o título do item:");
            String titulo = sc.nextLine();

            System.out.println("Digite a data da devolução(Utilize /):");
            String data = sc.nextLine();
            String[] campos = data.split("/");
            LocalDate dataDevolucao = LocalDate.of(Integer.parseInt(campos[2]), Integer.parseInt(campos[1]), Integer.parseInt(campos[0]));
            //criar excessão para NumberFormatException
            
            biblioteca.devolucao(titulo, dataDevolucao);
            
        } catch(ItemNaoEmprestadoException e){
            System.err.println(e.getMessage());
            telaUsuario();
        }
    }
    
    /**
     * Busca o item pelo título fornecido e imprime suas informações.
     */
    private void buscarItem(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o título do item:");
        String titulo = sc.nextLine();
        
        Item item = biblioteca.buscarItem(titulo);
        biblioteca.imprimirItem(item);
        if(item==null){
            if(biblioteca.getLogado()==null){
                telaOperacao();
            }
            else{
                telaUsuario();
            }
        }
        else{
            if(biblioteca.getLogado()==null){
                telaOperacao();
            }
        }
    }
    
    /**
     * Listar os empréstimos do usuário logado ou do usuário que corresponde a
     * matrícula fornecida.
     */
    private void listarEmprestimos(){
        if(!biblioteca.listarEmprestimos()){
            System.out.println("Matrícula incorreta.");
            telaOperacao();
        }
        else{
            if(biblioteca.getLogado()==null){
                telaOperacao();
            }
        }
    }
    
    /**
     * Desalogar o usuário.
     */
    private void sair(){
        biblioteca.sair();
        telaOperacao();
    }
}
