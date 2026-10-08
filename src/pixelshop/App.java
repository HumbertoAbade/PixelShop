package pixelshop;

import pixelshop.modelo.JogoDigital;
import pixelshop.modelo.JogoFisico;
import pixelshop.modelo.Produto;
import pixelshop.modelo.Promovivel;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        int opcao;
        Produto p1 = null;
        Produto p2 = null;

        System.out.println("Bem-vindos à Pixel Shop!");

        do {
            System.out.println("\nEscolha uma das opções abaixo:");
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Consultar dados dos produtos");
            System.out.println("3 - Adicionar ao estoque");
            System.out.println("4 - Remover do estoque");
            System.out.println("5 - Aplicar desconto promocional");
            System.out.println("6 - Exibir totalizador global de produtos");
            System.out.println("7 - Sair");
            System.out.print("Opção desejada: ");
            opcao = Integer.parseInt(teclado.nextLine());

            switch (opcao) {

                case 1 -> {
                    if (p1 != null && p2 != null) {
                        System.out.println("O estoque está cheio.");
                        continue;
                    }

                    System.out.print("Informe o nome do produto: ");
                    String nome = teclado.nextLine();

                    // Validação de duplicidade via equals()
                    if ((p1 != null && p1.getNome().equalsIgnoreCase(nome)) ||
                            (p2 != null && p2.getNome().equalsIgnoreCase(nome))) {
                        System.out.println("Erro: Já existe um produto cadastrado com esse nome!");
                        continue;
                    }

                    double preco;
                    while (true) {
                        System.out.print("Informe o preço do produto: R$ ");
                        preco = Double.parseDouble(teclado.nextLine());
                        if (preco > 0) break;
                        System.out.println("Valor inválido, o preço não pode ser menor ou igual a zero.");
                    }

                    int quantidadeEstoque;
                    while (true) {
                        System.out.print("Informe a quantidade em estoque: ");
                        quantidadeEstoque = Integer.parseInt(teclado.nextLine());
                        if (quantidadeEstoque >= 0) break;
                        System.out.println("Valor inválido, a quantidade em estoque não pode ser negativa.");
                    }

                    System.out.println("Qual o tipo do produto?");
                    System.out.println("1 - Jogo Físico");
                    System.out.println("2 - Jogo Digital");
                    System.out.print("Opção: ");
                    int tipo = Integer.parseInt(teclado.nextLine());

                    Produto novoProduto = null;

                    if (tipo == 1) {
                        System.out.print("Informe a plataforma: ");
                        String plataforma = teclado.nextLine();
                        System.out.print("Possui manual impresso? (s/n): ");
                        boolean temManual = teclado.nextLine().trim().equalsIgnoreCase("s");

                        novoProduto = new JogoFisico(nome, preco, quantidadeEstoque, plataforma, temManual);
                    } else if (tipo == 2) {
                        System.out.print("Informe o tamanho do jogo em GB: ");
                        int tamanhoGb = Integer.parseInt(teclado.nextLine());

                        novoProduto = new JogoDigital(nome, preco, quantidadeEstoque, tamanhoGb);
                    } else {
                        System.out.println("Tipo de produto inválido! Cadastro cancelado.");
                        continue;
                    }

                    if (p1 == null) {
                        p1 = novoProduto;
                    } else {
                        p2 = novoProduto;
                    }
                    System.out.println("Produto cadastrado com sucesso!");
                }

                case 2 -> {
                    if (p1 == null && p2 == null) {
                        System.out.println("Nenhum produto cadastrado no sistema.");
                    } else {
                        System.out.println("\n--- Consulta de Produtos ---");
                        if (p1 != null) {
                            System.out.println("1 - " + p1);
                            double totalP1 = p1.getPreco() * p1.getQuantidadeEstoque();
                            System.out.printf("   Valor total em estoque: R$ %.2f\n", totalP1);
                        }
                        if (p2 != null) {
                            System.out.println("2 - " + p2);
                            double totalP2 = p2.getPreco() * p2.getQuantidadeEstoque();
                            System.out.printf("   Valor total em estoque: R$ %.2f\n", totalP2);
                        }
                    }
                }

                case 3 -> {
                    Produto produtoSelecionado = selecionarProduto(p1, p2, teclado);
                    if (produtoSelecionado != null) {
                        System.out.print("Informe a quantidade para adicionar ao estoque: ");
                        int qtd = Integer.parseInt(teclado.nextLine());

                        if (produtoSelecionado.adicionarEstoque(qtd)) {
                            System.out.println("Estoque atualizado! Novo saldo: " + produtoSelecionado.getQuantidadeEstoque());
                        } else {
                            System.out.println("Erro: A quantidade informada deve ser maior que zero.");
                        }
                    }
                }

                case 4 -> {
                    Produto produtoSelecionado = selecionarProduto(p1, p2, teclado);
                    if (produtoSelecionado != null) {
                        System.out.print("Informe a quantidade para remover do estoque: ");
                        int qtd = Integer.parseInt(teclado.nextLine());

                        if (produtoSelecionado.removerEstoque(qtd)) {
                            System.out.println("Estoque atualizado! Novo saldo: " + produtoSelecionado.getQuantidadeEstoque());
                        } else {
                            System.out.println("Erro: Quantidade inválida ou saldo insuficiente.");
                        }
                    }
                }

                case 5 -> {
                    if (p1 == null && p2 == null) {
                        System.out.println("Nenhum produto cadastrado no sistema.");
                    } else {
                        System.out.print("Informe a porcentagem de desconto (%): ");
                        double porcentagem = Double.parseDouble(teclado.nextLine());

                        boolean aplicou = false;
                        Produto[] produtos = {p1, p2};

                        for (Produto p : produtos) {
                            if (p != null) {
                                if (p instanceof Promovivel promovivel) {
                                    promovivel.aplicarDesconto(porcentagem);
                                    System.out.println("Desconto aplicado ao produto: " + p.getNome());
                                    System.out.printf("Novo Preço: R$ %.2f\n", p.getPreco());
                                    aplicou = true;
                                } else {
                                    System.out.println("O produto '" + p.getNome() + "' não é elegível para descontos promocionais.");
                                }
                            }
                        }

                        if (!aplicou) {
                            System.out.println("Nenhum produto cadastrado era elegível a promoção.");
                        }
                    }
                }

                case 6 -> {
                    System.out.println("Total de produtos cadastrados no sistema: " + Produto.getTotalProdutosCadastrados());
                }

                case 7 -> {
                    System.out.println("Encerrando o programa. Até logo!");
                }

                default -> {
                    System.out.println("Opção inválida!");
                }
            }

        } while (opcao != 7);

        teclado.close();
    }

    private static Produto selecionarProduto(Produto p1, Produto p2, Scanner teclado) {
        if (p1 == null && p2 == null) {
            System.out.println("Nenhum produto cadastrado no sistema.");
            return null;
        }

        System.out.println("Selecione o produto:");
        if (p1 != null) System.out.println("1 - " + p1.getNome());
        if (p2 != null) System.out.println("2 - " + p2.getNome());

        System.out.print("Opção: ");
        int escolha = Integer.parseInt(teclado.nextLine());

        if (escolha == 1 && p1 != null) return p1;
        if (escolha == 2 && p2 != null) return p2;

        System.out.println("Opção de produto inválida.");
        return null;
    }
}