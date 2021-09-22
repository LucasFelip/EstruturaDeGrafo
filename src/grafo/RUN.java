/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo;

import java.util.ArrayList;
import java.util.Scanner;
import util.ManiArquivo;

/**
 *
 * @author Lucas Reis
 */
public class RUN {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Grafo inic = new Grafo();
        Grafo resul = new Grafo();
        Scanner ler = new Scanner(System.in);
        
        //// VARIAVEIS 
        
        Aresta arestaAux;
        Vertice verticeAux1, verticeAux2;
        int op = 0, conv;
        String arquivo = "src/entrada/dados.txt", linha, origem, destino;
        String[] convert = (ManiArquivo.lerArquivo(arquivo));
        
        //// CARREGAMENTO DOS DADOS DO ARQUIVO 
        
        System.out.println("- - - CARREGANDO GRAFO - - - ");
        System.out.println(" peso " + " - " +" Origem "+ " - " + "Destino\n");
        for(int i = 0; i < convert.length; i++){
            linha = convert[i];
            String[] texto = linha.split(";");
            
            System.out.println("         "+texto[0] + " - " + texto[1] + " - " + texto[2]);
            conv = Integer.parseInt(texto[0]);
            inic.addAresta(conv, texto[1], texto[2]);
        }
        System.out.println("\n- - - GRAFO CARREGADO - - - ");
        
        ///// EXECUÇÃO DO PROGRAMA E DE TODAS AS FUNÇÕES 
        
        do{
            System.out.print("\n\n - - - PROJETO DE GRAFO - - - \n 1 - Exibir Grafo \n 2 - Busca em Profundidade \n 3 - Busca em Largura \n 4 - Algoritmo de Dijkstra \n 5 - Algoritmo de Kruskal \n 6 - Ordenação Topológica \n 0 - Sair \n-----------------------\n Insira um numero valido: ");
            op = ler.nextInt();
            
            System.out.println("\n");
            switch(op){
                case 0:
                    System.out.println(" ENCERRRANDO PROGRAMA");
                    break;
                    
                case 1:
                    inic.imprimeArvore();
                    break;
                    
                case 2:
                    System.out.print("Insira a origem: ");
                    origem = ler.next();
                    System.out.print("Insira o destino: ");
                    destino = ler.next();
                    
                    System.out.println("\n");
                    resul.setArestas(inic.buscaEmProfundidade(origem, destino));
                    
                    resul.imprimeArvore();
                    break;
                    
                case 3:
                    System.out.print("Insira a origem: ");
                    origem = ler.next();
                    System.out.print("Insira o destino: ");
                    destino = ler.next();
                    
                    System.out.println("\n");
                    resul.setArestas(inic.buscaEmLargura(origem, destino));
                    
                    resul.imprimeArvore();
                    break;
                    
                case 4:
                    System.out.print("Insira a origem: ");
                    origem = ler.next();
                    System.out.print("Insira o destino: ");
                    destino = ler.next();
                    
                    verticeAux1 = inic.acharVertice(origem);
                    verticeAux2 = inic.acharVertice(destino);
                    resul.setVertices(inic.encontrarMenorCaminhoDijkstra(verticeAux1, verticeAux2));
                    
                    System.out.println(resul.getVertices());
                    break;
                    
                case 5:
                    for(int i = 0; i < inic.getArestas().size(); i++){
                        arestaAux = inic.menorPeso();
                        if(!resul.temCiclo(arestaAux)){
                            resul.addAresta(arestaAux.getPeso(), 
                                    arestaAux.getOrigem().getNome(), 
                                    arestaAux.getDestino().getNome());
                        }
                    }
                    resul.imprimeArvore();
                    break;
                
                case 6:
                    ArrayList<Vertice> vertices = inic.topologicalSort();
                    int som = 1;
                    for (Vertice vertice: vertices) {
                        System.out.println(som + " - " + vertice.getNome());
                        som++;
                    }
                    break;
                
                default:
                    System.out.println("Opção Invalida! \n");
                    break;
            }
            System.out.println("\n");
        }while(op != 0);
    }
    
}
