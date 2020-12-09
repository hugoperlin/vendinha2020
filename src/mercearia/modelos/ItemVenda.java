package mercearia.modelos;


import java.io.Serializable;

public class ItemVenda implements Serializable {

    private Produto produto;
    private double preco;

    public ItemVenda(Produto produto) {
        this.produto = produto;
        this.preco = produto.getPreco();
    }

    public Produto getProduto() {
        return produto;
    }

    public double getPreco() {
        return preco;
    }

    public String toString(){
        return produto.getNome()+"( R$ "+preco+")";
    }

}
