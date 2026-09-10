package pixelshop.modelo;

public class Testes {
    public static void main(String [] agrs){
        Produto p1 = new Produto("Mouse Gamer", 200, 50);
        Produto p2 = new Produto("Notebook", 4500, 70);
        Produto p3 = new Produto("Iphone 18", -22000, 0);

        System.out.println("Produtos cadastrados:");
        System.out.println("Nome:" + p1.getNome());
        System.out.println("Preço:" + p1.getPreco());
        System.out.println("Quantidade:" + p1.getQuantidadeEstoque());
        System.out.println();
        System.out.println("Nome:" + p2.getNome());
        System.out.println("Preço:" + p2.getPreco());
        System.out.println("Quantidade:" + p2.getQuantidadeEstoque());
        System.out.println();
        System.out.println("Nome:" + p3.getNome());
        System.out.println("Preço:" + p3.getPreco());
        System.out.println("Quantidade:" + p3.getQuantidadeEstoque());

    }
}
