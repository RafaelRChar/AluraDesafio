package Cep.verifica.alura.principal;

import Cep.verifica.alura.modelos.Cidade;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        // Declarando classes usadas no programa;
        Scanner leitura = new Scanner(System.in);
        while (true) {
            try {
                // String para printar o menu.
                String menuDeOpcoes =
                        """
                                \n
                                === Consulta de Cep ===
                                > 1: Consultar por CEP
                                > 2: Encerrar Programa
                                > 3: Sair do programa
                                Escolha a opção: 
                                """;
                System.out.print(menuDeOpcoes);
                // Para poder escolher switch.
                int escolherOpcao = leitura.nextInt();
                // Limpeza de Buffer
                leitura.nextLine();
                // Switch para poder escolher melhor
                switch (escolherOpcao) {
                    // Switch para consultar o CEP:
                    case 1:
                        // Um try para evitar erros
                        try {
                            // Para poder chamar o método de Cidade
                            Cidade cidadeAtual = new Cidade();
                            String busca = "";
                            // Um while para evitar alguns erros, e porque um CEP tem 8 digitos
                            while (busca.length() != 8) {
                                System.out.println("8 digitos | Procure um cep:");
                                busca = leitura.nextLine();
                            }
                            // Para chamar o método e fazer a pesquisa do cep
                            cidadeAtual.pesquisaCepHttp(busca);
                            // Coloquei um catch bem "Amplo" porque não entendi totalmente como usar ainda.
                        } catch (Exception e) {
                            // Mensagem para mostrar que deu algum erro, e o erro.
                            System.out.println("\n Erro durante a pesquisa do CEP: " + e.getMessage());
                        }
                        break;
                    // Switch para sair do programa;
                    case 2:
                        System.out.println("Encerrando o programa.");
                        // Fechando a Leitura para poder encerrar certinho.
                        leitura.close();
                        // Dando exit no programa para sair.
                        System.exit(0);
                        break;
                    default:
                        // Para a pessoa escolher a coisa certa.
                        System.out.println("Escolha uma opção válida!");
                        break;
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Entrada inválida, digitou algo que não devia >:(");
                leitura.nextLine(); // para limpar o buffer, isso me deu uma dor de cabeça :)
            }
        }
    }
}