package pixelshop.modelo;

public class Produto {
    private String nome;
    private double preco;
    private int quantidadeEstoque;

    public String getNome(){return nome;}
    public double getPreco(){return preco;}
    public int getQuantidadeEstoque(){return quantidadeEstoque;}

    public boolean setPreco(double preco) {
        if (preco > 0) {
            this.preco = preco;
            return true;
        } else {
            this.preco = 0;
            return false;
        }
    }

    public boolean setQuantidadeEstoque(int qtd) {
        if (qtd >= 0) {
            this.quantidadeEstoque = qtd;
            return true;
        } else {
            this.quantidadeEstoque = 0;
            return false;
        }
    }

    public boolean adicionarEstoque(int qtd){
        if(qtd > 0){
            this.quantidadeEstoque += qtd;
            return true;
        }
        return false;
    }

    public boolean removerEstoque(int qtd){
        if(qtd > 0 && quantidadeEstoque - qtd >= 0){
            this.quantidadeEstoque -= qtd;
            return true;
        }
        return false;
    }

    public Produto(String nome, double preco, int quantidadeEstoque) {
        this.nome = nome;
        setPreco(preco);
        setQuantidadeEstoque(quantidadeEstoque);
    }

}
