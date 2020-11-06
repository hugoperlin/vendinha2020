package controles;

import modelos.Cliente;
import modelos.Produto;
import modelos.Venda;

public class Mercearia {

    private static int MAX_CLIENTES = 50;
    private static int totalClientes = 0;

    private static int MAX_PRODUTOS = 50;
    private static int totalProdutos = 0;

    private static int MAX_VENDAS = 50;
    private static int totalVendas = 0;

    private String nome;
    private Cliente[] clientes;
    private Produto[] produtos;
    private Venda[] vendas;

    public Mercearia(String nome){
        this.nome = nome;
        this.clientes = new Cliente[MAX_CLIENTES];
        this.produtos = new Produto[MAX_PRODUTOS];
        this.vendas = new Venda[MAX_VENDAS];
    }

    public boolean adiciona(Cliente cliente){

        if(buscarCliente(cliente.getNome())==null){
            this.clientes[totalClientes] = cliente;
            totalClientes += 1;

            return true;
        }

        return false;

    }

    public boolean adiciona(Produto produto){

        if(buscarProduto(produto.getNome())==null){
            this.produtos[totalProdutos] = produto;
            totalProdutos += 1;

            return true;
        }

        return false;

    }

    public Cliente buscarCliente(String nome){

        for(int i=0;i<totalClientes;i++){
            if(this.clientes[i].getNome().equals(nome)){
                return this.clientes[i];
            }
        }

        return null;

    }

    public Produto buscarProduto(String nome){

        for(int i=0;i<totalProdutos;i++){
            if(this.produtos[i].getNome().equals(nome)){
                return this.produtos[i];
            }
        }

        return null;

    }

    public Venda realizaVenda(Cliente cliente, Produto[] produtos){

        Venda venda = new Venda(cliente);

        for(int i=0;i<produtos.length;i++){
            if(produtos[i] != null){
                venda.adiciona(produtos[i]);
            }

        }

        venda.calculaTotal();

        this.vendas[totalVendas] = venda;

        totalVendas += 1;

        return venda;

    }

    public Cliente[] getClientes() {
        return clientes;
    }

    public Produto[] getProdutos() {
        return produtos;
    }

    public String toString(){
        String str = nome+"\n";

        str += "Clientes:[\n";

        for(int i=0;i<totalClientes;i++){
            str += "\t"+this.clientes[i]+";\n";
        }

        str += "]\n";

        str += "Produtos:[\n";

        for(int i=0;i<totalProdutos;i++){
            str += "\t"+this.produtos[i]+";\n";
        }
        str += "]\n";

        str += "Vendas:[\n";

        for(int i=0;i<totalVendas;i++){
            str += "\t"+this.vendas[i]+";\n";
        }
        str += "]\n";

        return str;

    }


}
