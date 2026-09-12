import pixelshop.modelo.Produto;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

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
                    if (p1 != null && p2 != null) {
                        System.out.println("O estoque está lotado");
                        continue;
                    }

                    System.out.println("Informe o nome do produto: ");
                    String nome = teclado.nextLine();

                    double preco;
                    while (true) {
                        System.out.println("Informe o preço do produto: ");
                        preco = Double.parseDouble(teclado.nextLine());

                        if (preco > 0) {
                            break;
                        } else {
                            System.out.println("Valor invalido, o preço não pode ser negativo");
                        }
                    }

                    int quantidadeEstoque;
                    while (true) {
                        System.out.println("Informe a quantidade do produto: ");
                        quantidadeEstoque = Integer.parseInt(teclado.nextLine());

                        if (quantidadeEstoque >= 0) {
                            break;
                        } else {
                            System.out.println("Valor invalido, a quantidade estoque não pode ser negativa");
                        }
                    }

                    if (p1 == null) {
                        p1 = new Produto(nome, preco, quantidadeEstoque);
                        System.out.println("Produto criado com sucesso.");
                    } else {
                        p2 = new Produto(nome, preco, quantidadeEstoque);
                        System.out.println("Produto criado com sucesso.");
                    }
                }

                case 2 -> {
                    // Opção 2: Consultar dados e valor total em estoque do produto
                    Produto produtoSelecionado = selecionarProduto(teclado, p1, p2);

                    if (produtoSelecionado != null) {
                        double valorTotalEmEstoque = produtoSelecionado.getPreco() * produtoSelecionado.getQuantidadeEstoque();

                        System.out.println("\n--- Dados do Produto ---");
                        System.out.println("Nome: " + produtoSelecionado.getNome());
                        System.out.println("Preço Unitário: R$ " + String.format("%.2f", produtoSelecionado.getPreco()));
                        System.out.println("Quantidade em Estoque: " + produtoSelecionado.getQuantidadeEstoque());
                        System.out.println("Valor Total em Estoque: R$ " + String.format("%.2f", valorTotalEmEstoque));
                    }
                }

                case 3 -> {
                    // Opção 3: Realizar entrada (adição) de estoque
                    Produto produtoSelecionado = selecionarProduto(teclado, p1, p2);

                    if (produtoSelecionado != null) {
                        System.out.print("Informe a quantidade para adicionar ao estoque: ");
                        int qtd = Integer.parseInt(teclado.nextLine());

                        boolean sucesso = produtoSelecionado.adicionarEstoque(qtd);
                        if (sucesso) {
                            System.out.println("Estoque atualizado com sucesso! Novo estoque: " + produtoSelecionado.getQuantidadeEstoque());
                        } else {
                            System.out.println("Erro: A quantidade informada para entrada deve ser maior que zero.");
                        }
                    }
                }

                case 4 -> {
                    // Opção 4: Realizar saída (remoção) de estoque
                    Produto produtoSelecionado = selecionarProduto(teclado, p1, p2);

                    if (produtoSelecionado != null) {
                        System.out.print("Informe a quantidade para remover do estoque: ");
                        int qtd = Integer.parseInt(teclado.nextLine());

                        boolean sucesso = produtoSelecionado.removerEstoque(qtd);
                        if (sucesso) {
                            System.out.println("Estoque atualizado com sucesso! Novo estoque: " + produtoSelecionado.getQuantidadeEstoque());
                        } else {
                            System.out.println("Erro: Quantidade inválida ou saldo insuficiente em estoque.");
                        }
                    }
                }

                case 5 -> {
                    // Opção 5: Alterar o preço de um produto utilizando o setter validado
                    Produto produtoSelecionado = selecionarProduto(teclado, p1, p2);

                    if (produtoSelecionado != null) {
                        System.out.print("Informe o novo preço do produto: R$ ");
                        double novoPreco = Double.parseDouble(teclado.nextLine());

                        boolean sucesso = produtoSelecionado.setPreco(novoPreco);
                        if (sucesso) {
                            System.out.println("Preço alterado com sucesso! Novo preço: R$ " + String.format("%.2f", produtoSelecionado.getPreco()));
                        } else {
                            System.out.println("Erro: O preço deve ser maior que zero.");
                        }
                    }
                }

                case 6 -> {
                    System.out.println("Encerrando o programa");
                }

                default -> {
                    System.out.println("Opção inválida!");
                }
            }

        } while (opcao != 6);

        teclado.close();
    }

    // Método auxiliar estático - lógicas de checagem do produto cadastrado
    private static Produto selecionarProduto(Scanner teclado, Produto p1, Produto p2) {
        if (p1 == null && p2 == null) {
            System.out.println("Nenhum produto cadastrado no sistema.");
            return null;
        }

        System.out.println("Selecione o produto:");
        if (p1 != null) {
            System.out.println("1 - " + p1.getNome());
        }
        if (p2 != null) {
            System.out.println("2 - " + p2.getNome());
        }

        System.out.print("Opção: ");
        int escolha = Integer.parseInt(teclado.nextLine());

        if (escolha == 1 && p1 != null) {
            return p1;
        } else if (escolha == 2 && p2 != null) {
            return p2;
        } else {
            System.out.println("Opção de produto inválida.");
            return null;
        }
    }
}