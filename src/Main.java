import controles.Mercearia;
import modelos.Cliente;
import modelos.Produto;
import modelos.Venda;

public class Main {

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

        Venda venda = new Venda(c);
        venda.adiciona(p);
        venda.adiciona(p1);

        System.out.println(venda);
        System.out.println(venda.calculaTotal());



    }

}
