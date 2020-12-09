package mercearia;

import mercearia.modelos.Mercearia;
import mercearia.modelos.Cliente;
import mercearia.modelos.Produto;

import java.util.ArrayList;

public class MainTeste {

    private static Cliente[] geraClientes(){
        Cliente[] clientes = new Cliente[10];

        for(int i=0;i<10;i++){
            clientes[i] = new Cliente("Cliente "+i,"123-"+i+"cliente"+i+"@teste.com");
        }

        return clientes;
    }

    private static Produto[] geraProdutos(){
        Produto[] produtos = new Produto[10];

        for(int i=0;i<10;i++){
            produtos[i] = new Produto("Produto "+i,10.0+i);
        }

        return produtos;
    }

    private static Mercearia geraMercearia(){
        Mercearia mercearia = new Mercearia("Teste");
        Cliente[] clientes = geraClientes();

        for(int i=0;i<clientes.length;i++){
            mercearia.adiciona(clientes[i]);
        }

        Produto[] produtos = geraProdutos();
        for(int i=0;i< produtos.length;i++){
            mercearia.adiciona(produtos[i]);
        }
        return mercearia;
    }


    public static void main(String[] args) {

        Mercearia mercearia = geraMercearia();
        Cliente c = mercearia.buscarCliente("Cliente 0");
        Produto p = mercearia.buscarProduto("Produto 0");
        Produto p1 = mercearia.buscarProduto("Produto 1");

        ArrayList<Produto> produtos = new ArrayList<>();

        produtos.add(p);
        produtos.add(p1);
        //mercearia.realizaVenda(c,produtos



        mercearia.realizaVendaAVista(c,produtos,5);

        mercearia.realizaVendaAPrazo(c,produtos,5);



        System.out.println(mercearia);


    }

}
