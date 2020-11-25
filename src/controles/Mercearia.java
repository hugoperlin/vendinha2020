package controles;

import modelos.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mercearia {

    private String nome;
    private ArrayList<Cliente> clientes;
    private ArrayList<Produto> produtos;
    private ArrayList<Venda> vendas;

    public Mercearia(String nome){
        this.nome = nome;
        this.clientes = new ArrayList<>();
        this.produtos = new ArrayList<>();
        this.vendas = new ArrayList<>();
    }

    public boolean adiciona(Cliente cliente){

        if(buscarCliente(cliente.getNome())==null){
            this.clientes.add(cliente);

            return true;
        }

        return false;

    }

    public boolean adiciona(Produto produto){

        if(buscarProduto(produto.getNome())==null){
            this.produtos.add(produto);

            return true;
        }

        return false;

    }

    public Cliente buscarCliente(String nome){

        Cliente c = new Cliente(nome);
        int pos = this.clientes.indexOf(c);

        if(pos != -1){
            return this.clientes.get(pos);
        }

        return null;

    }

    public Produto buscarProduto(String nome){

        Produto p = new Produto(nome,0);

        int pos = this.produtos.indexOf(p);

        if(pos != -1){
            return this.produtos.get(pos);
        }

        return null;

    }

    private Venda registraVenda(Venda venda, ArrayList<Produto> produtos){


        for(Produto produto:produtos){
            venda.adiciona(produto);
        }

        venda.calculaTotal();

        this.vendas.add(venda);

        return venda;

    }


    public VendaAVista realizaVendaAVista(Cliente cliente, ArrayList<Produto> produtos, double desconto){

        VendaAVista venda = new VendaAVista(cliente,desconto);

        registraVenda(venda, produtos);

        return venda;

    }

    public VendaAPrazo realizaVendaAPrazo(Cliente cliente, ArrayList<Produto> produtos, double juros){
        VendaAPrazo venda = new VendaAPrazo(cliente,juros);

        registraVenda(venda,produtos);

        return venda;
    }




    public List<Cliente> getClientes() {
        return Collections.unmodifiableList(this.clientes);
    }

    public List<Produto> getProdutos() {
        return Collections.unmodifiableList(this.produtos);
    }

    public String toString(){
        String str = nome+"\n";

        str += "Clientes:[\n";

        for(Cliente cliente:this.clientes){
            str += "\t"+cliente+";\n";
        }

        str += "]\n";

        str += "Produtos:[\n";

        for(Produto produto:this.produtos){
            str += "\t"+produto+";\n";
        }
        str += "]\n";

        str += "Vendas:[\n";

        for(Venda venda:this.vendas){
            str += "\t"+venda+";\n";
        }
        str += "]\n";

        return str;

    }


}
