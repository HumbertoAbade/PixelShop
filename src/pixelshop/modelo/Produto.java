package pixelshop.modelo;
import java.util.Objects;

public abstract class Produto implements Promovivel{
    private String nome;
    private double preco;
    private int quantidadeEstoque;
    private static int totalProdutosCadastrados = 0;

    public String getNome() {
        return nome;
    }
    public double getPreco() {
        return preco;
    }
    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }
    public static int getTotalProdutosCadastrados () {
        return totalProdutosCadastrados;
    };

    public Produto(String nome, double preco, int quantidadeEstoque) {
        this.nome = nome;
        setPreco(preco);
        setQuantidadeEstoque(quantidadeEstoque);
        totalProdutosCadastrados++;
    }

    public boolean setPreco(double preco) {
        if (preco > 0) {
            this.preco = preco;
            return true;
        } else {
            System.out.println("Valor invalido, o preço não pode ser negativo");
            return false;
        }
    }

    public boolean setQuantidadeEstoque(int qtd) {
        if (qtd >= 0) {
            this.quantidadeEstoque = qtd;
            return true;
        } else {
            System.out.println("Valor invalido, a quantidade estoque não pode ser negativa");
            return false;
        }
    }

    public boolean adicionarEstoque(int qtd) {
        if (qtd > 0) {
            this.quantidadeEstoque += qtd;
            return true;
        }
        return false;
    }

    public boolean removerEstoque(int qtd) {
        if (qtd > 0 && quantidadeEstoque - qtd >= 0) {
            this.quantidadeEstoque -= qtd;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("Nome: %s | Preço: R$ %.2f | Estoque: %d un",
                nome, preco, quantidadeEstoque);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Produto produto = (Produto) obj;
        return Objects.equals(nome.toLowerCase(), produto.nome.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome.toLowerCase());
    }

    @Override
    public double aplicarDesconto(double desconto) {
        if (desconto > 0 && desconto <= 100) {
            double novoPreco = getPreco() * (desconto / 100.0);
            setPreco(novoPreco);
        }
        return desconto;
    }
}
