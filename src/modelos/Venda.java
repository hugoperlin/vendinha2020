package modelos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class Venda {



    private String data;
    private Cliente cliente;
    private ArrayList<ItemVenda> items;
    protected double total;

    public Venda(Cliente cliente){
        this.cliente = cliente;
        this.data = LocalDate.now().toString();

        this.items = new ArrayList<>();
    }

    public boolean adiciona(Produto produto){

        ItemVenda item = new ItemVenda(produto);

        this.items.add(item);

        return true;
    }

    public abstract double calculaTotal();

    protected double calculaSubTotal(){

        double soma=0;

        for(int i=0;i<this.items.size();i++){
            soma += this.items.get(i).getPreco();
        }

        return soma;

    }


    public String getData() {
        return data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<ItemVenda> getItems() {
        return Collections.unmodifiableList(this.items);
    }

    public double getTotal() {
        return total;
    }

    public String toString(){
        String str = "Venda ("+data+")\n";
        str += "\t "+cliente+"\n";

        str += "\t Items:[\n";
        for(ItemVenda item:this.items){
            str += "\t\t"+item+"\n";
        }
        str += "\t]";
        str += "\t Total: R$"+total;
        return str;
    }

}
