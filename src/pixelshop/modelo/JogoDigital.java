package pixelshop.modelo;

public class JogoDigital extends Produto {

    private int tamanhoDoJogo;

    public JogoDigital(String nome, double preco, int quantidadeEstoque, int tamanhoDoJogo) {
        super(nome, preco, quantidadeEstoque);
        this.tamanhoDoJogo = tamanhoDoJogo;
    }

    public int getTamanhoDoJogo() {
        return tamanhoDoJogo;
    }

    public void setTamanhoDoJogo(int tamanhoDoJogo) {
        this.tamanhoDoJogo = tamanhoDoJogo;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Jogo Digital - Tamanho: %d GB", tamanhoDoJogo);
    }
}
