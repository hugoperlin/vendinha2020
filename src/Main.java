import controles.Mercearia;
import modelos.Cliente;
import modelos.Produto;

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


        System.out.println(mercearia.buscarProduto("Produto 10"));


    }

}
