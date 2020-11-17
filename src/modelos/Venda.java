package modelos;

import java.time.LocalDate;

public abstract class Venda {

    private static int MAX_ITEMS=10;
    private int totalItems=0;

    private String data;
    private Cliente cliente;
    private ItemVenda[] items;
    protected double total;

    public Venda(Cliente cliente){
        this.cliente = cliente;
        this.data = LocalDate.now().toString();
        this.items = new ItemVenda[MAX_ITEMS];
    }

    public boolean adiciona(Produto produto){

        ItemVenda item = new ItemVenda(produto);

        this.items[totalItems] = item;

        totalItems += 1;

        return true;
    }

    public abstract double calculaTotal();

    protected double calculaSubTotal(){

        double soma=0;

        for(int i=0;i<totalItems;i++){
            soma += items[i].getPreco();
        }

        return soma;

    }


    public String getData() {
        return data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ItemVenda[] getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    public String toString(){
        String str = "Venda ("+data+")\n";
        str += "\t "+cliente+"\n";

        str += "\t Items:[\n";
        for(int i=0;i<totalItems;i++){
            str += "\t\t"+this.items[i]+"\n";
        }
        str += "\t]";
        str += "\t Total: R$"+total;
        return str;
    }

}
