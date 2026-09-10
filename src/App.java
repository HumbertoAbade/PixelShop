import pixelshop.modelo.Produto;
import java.util.Scanner;

public class App {
    public static void main(String[] args){

        Scanner teclado = new Scanner(System.in);
        int opcao;
        Produto p1 = null;
        Produto p2 = null;

        System.out.println("Bem-vindos a Pixel Shop!");

        do {
            System.out.println("Escolha um das opções abaixo:");
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Consultar dados");
            System.out.println("3 - Adicionar ao estoque");
            System.out.println("4 - Remover produto do estoque");
            System.out.println("5 - Alterar preço do produto");
            System.out.println("6 - Sair");
            System.out.println("Opção desejada: ");
            opcao = Integer.parseInt(teclado.nextLine());

            switch (opcao) {
                case 1 -> {
                    if (p1 != null && p2 != null){
                        System.out.println("O estoque está lotado");
                        continue;
                    }

                    if (p1 == null){
                        System.out.println("Informe o nome do produto: ");
                        String nome = teclado.nextLine();

                        System.out.println("Informe o preço do produto: ");
                        double preco = Double.parseDouble(teclado.nextLine());

                        System.out.println("Informe a quantidade do produto: ");
                        int quantidadeEstoque = Integer.parseInt(teclado.nextLine());

                        p1 = new Produto(nome, preco, quantidadeEstoque);
                        System.out.println("Produto criado com sucesso.");
                        continue;

                    }

                    if (p2 == null){
                        System.out.println("Informe o nome do produto: ");
                        String nome = teclado.nextLine();

                        System.out.println("Informe o preço do produto: ");
                        double preco = Double.parseDouble(teclado.nextLine());

                        System.out.println("Informe a quantidade do produto: ");
                        int quantidadeEstoque = Integer.parseInt(teclado.nextLine());

                        p2 = new Produto(nome, preco, quantidadeEstoque);
                        System.out.println("Produto criado com sucesso.");

                    }

                }

                case 2 -> {
                }

                case 3 -> {
                }

                case 4 -> {
                }

                case 5 -> {
                }

                case 6 -> {
                    System.out.println("Encerrando o programa");
                }

                default -> {
                    System.out.println("Programa encerrado");
                }
            }

        }while (opcao != 6) ;
        System.out.println("Programa encerrado");
        teclado.close();
    }
}
