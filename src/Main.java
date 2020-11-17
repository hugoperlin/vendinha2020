import controles.Mercearia;
import modelos.Cliente;
import modelos.Produto;

import java.util.Scanner;

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

    private static String menu(){

        String str="";

        str += "####Vendinha####\n";
        str += "1 - Cadastrar Cliente\n";
        str += "2 - Buscar Cliente\n";
        str += "3 - Cadastrar Produto\n";
        str += "4 - Buscar Produto\n";
        str += "5 - Realizar Venda\n";
        str += "6 - Mostrar Relatório\n";
        str += "0 - Sair\n";
        str += "Digite uma opção:";

        return str;

    }

    public static void inicializaMercearia(Mercearia mercearia){
        Cliente[] clientes = geraClientes();

        for(int i=0;i<clientes.length;i++){
            mercearia.adiciona(clientes[i]);
        }

        Produto[] produtos = geraProdutos();
        for(int i=0;i<produtos.length;i++){
            mercearia.adiciona(produtos[i]);
        }

    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int op = 0;
        String nome,email,telefone;
        double preco;
        Produto produto;
        Cliente cliente;

        Mercearia mercearia = new Mercearia("Vendinha");

        //isso serve somente para testes...
        inicializaMercearia(mercearia);

        do{
            System.out.println(menu());
            op = scan.nextInt();

            switch (op){
                case 1: //cadastrar cliente

                    scan.nextLine(); //limpar o buffer
                    System.out.print("Nome:");
                    nome = scan.nextLine();
                    System.out.println("Telefone:");
                    telefone = scan.nextLine();
                    System.out.println("E-mail:");
                    email = scan.nextLine();

                    Cliente c = new Cliente(nome,email,telefone);
                    if(mercearia.adiciona(c)){
                        System.out.println("Cliente cadastrado!!");
                    }else{
                        System.out.println("Erro ao cadastrar cliente!!");
                    }

                    break;
                case 2: //buscar cliente
                    scan.nextLine();
                    System.out.println("Nome:");
                    nome = scan.nextLine();
                    cliente = mercearia.buscarCliente(nome);
                    if(cliente != null){
                        System.out.println("Cliente encontrado!!");
                        System.out.println(cliente);
                    }else{
                        System.out.println("Cliente não encontrado!!");
                    }

                    break;
                case 3: //cadastrar produto
                    scan.nextLine();
                    System.out.println("Nome:");
                    nome = scan.nextLine();
                    System.out.println("Preço:");
                    preco = scan.nextDouble();

                    produto = new Produto(nome,preco);

                    if(mercearia.adiciona(produto)){
                        System.out.println("Produto cadastrado com sucesso!!");
                    }else{
                        System.out.println("Erro ao cadastrar produto");
                    }


                    break;
                case 4: // buscar produto
                    scan.nextLine();
                    System.out.println("Nome:");
                    nome = scan.nextLine();

                    produto = mercearia.buscarProduto(nome);

                    if(produto != null){
                        System.out.println("Produto encontrado!!");
                        System.out.println(produto);
                    }else{
                        System.out.println("Produto não encontrado!!");
                    }


                    break;
                case 5: // realizar venda

                    Cliente[] clientes = mercearia.getClientes();
                    for(int i=0;i< clientes.length;i++){
                        if(clientes[i] != null){
                            System.out.println(""+i+"-"+clientes[i]);
                        }

                    }
                    System.out.println("Digite o número do cliente:");
                    op = scan.nextInt();
                    if(op >=0 && op < clientes.length){
                        cliente = clientes[op];

                        //montando o vetor de produtos para serem vendidos
                        Produto[] produtosVenda = new Produto[10];
                        Produto[] produtos = mercearia.getProdutos();
                        int indexProdutos = 0;
                        do{
                            for(int i=0;i<produtos.length;i++){
                                if(produtos[i] != null){
                                    System.out.println(""+i+"-"+produtos[i]);
                                }

                            }
                            System.out.println("Digite o número do produto (-1 finalizar):");
                            op = scan.nextInt();
                            if(op >= 0 && op < produtos.length){
                                produtosVenda[indexProdutos] = produtos[op];
                                indexProdutos += 1;
                            }
                        }while(op != -1);

                        System.out.println("Digite o tipo de venda:1 - à vista 2 - à prazo");
                        op = scan.nextInt();

                        double valor=0;
                        System.out.println("Digite o valor "+(op==1?"de desconto:":"de juros:"));
                        valor = scan.nextDouble();

                        if(op==1){
                            mercearia.realizaVendaAVista(cliente,produtosVenda,valor);
                        }else{
                            mercearia.realizaVendaAPrazo(cliente,produtosVenda,valor);
                        }





                        System.out.println("Venda finalizada!!");

                    }


                    break;
                case 6: // mostrar relatorio
                    System.out.println(mercearia);
                    break;
            }


        }while(op != 0);




    }


}
