
package com.mycompany.sistemabiblioteca;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe responsável por aplicar o gerenciamento do sistema de uma biblioteca.
 * @author Julia
 */
public class SistemaBiblioteca {

    
    public static void main(String[] args) {
        
        File arquivo = new File ("C:\\Users\\julia\\OneDrive\\Documentos\\BCC\\items.txt") ;
        
        Scanner scanner = null;
        ArrayList<String[]> itensList = new ArrayList<>();
        
        try {
            scanner = new Scanner ( arquivo ) ;
        } catch ( FileNotFoundException ex) {
            Logger.getLogger ( SistemaBiblioteca.class.getName()).log( Level .SEVERE , null ,ex) ;
        }
        
        // Le cada linha do arquivo
        while ( scanner.hasNextLine () ) {
            String linha = scanner.nextLine () ;
            
            // Divide a linha em campos separados por #
            String[] campos = linha.split ( "#") ;
            itensList.add(campos);
            // Imprime o contéudo de cada campo ( pode usar para testar se a leitura est́a correta , nesse caso ele imprime com | como separador )
            
            System.out.println () ;
        }
        String[] camposItem = new String[itensList.size() * itensList.get(0).length];
        int i = 0;
        
        for(String[] item : itensList){
            for(String campo : item){
                camposItem[i] = campo;
                i++;
            }
        }
        
        DisplayBiblioteca display = new DisplayBiblioteca(camposItem);
        display.telaOperacao();
    }
}
