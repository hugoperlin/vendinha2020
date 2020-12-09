package mercearia;

import mercearia.modelos.Mercearia;
import mercearia.modelos.Cliente;
import mercearia.modelos.Produto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
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


    public static void salvarBinario(Mercearia mercearia){

        try(ObjectOutputStream obs = new ObjectOutputStream(
                new FileOutputStream(
                        new File("vendinha.bin")
                ))){


            obs.writeObject(mercearia);


        }catch (IOException e){
            System.out.println("Problema ao escrever arquivo...!!!");
            e.printStackTrace();
        }
    }

    public static Mercearia lerBinario(){

        try(ObjectInputStream obi = new ObjectInputStream(
                new FileInputStream(
                        new File("vendinha.bin")
                ))){

            Mercearia mercearia = (Mercearia) obi.readObject();
            return mercearia;


        }catch (IOException e){
            System.out.println("Problema ao ler arquivo...!!!");
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            System.out.println("Problema com a classe!!!");
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int op = 0;
        String nome,email,telefone;
        double preco;
        Produto produto;
        Cliente cliente;

        File f = new File("vendinha.bin");

        Mercearia mercearia = null;
        if(f.exists()){
            System.out.println("Deseja carregar os dados do arquivo? (1-sim)");
            op = scan.nextInt();
            if(op == 1){
                mercearia = lerBinario();
            }
        }

        if(mercearia == null){
            mercearia = new Mercearia("Vendinha");
            System.out.println("Deseja iniciar com dados sintéticos?(1-sim)");
            op = scan.nextInt();
            if(op == 1){
                //isso serve somente para testes...
                inicializaMercearia(mercearia);
            }
        }


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

                    List<Cliente> clientes = mercearia.getClientes();
                    for(int i=0;i< clientes.size();i++){
                        System.out.println(""+i+"-"+clientes.get(i));

                    }
                    System.out.println("Digite o número do cliente:");
                    op = scan.nextInt();
                    if(op >=0 && op < clientes.size()){
                        cliente = clientes.get(op);

                        //montando o vetor de produtos para serem vendidos
                        ArrayList<Produto> produtosVenda = new ArrayList<>();
                        List<Produto> produtos = mercearia.getProdutos();
                        int indexProdutos = 0;
                        do{
                            for(int i=0;i<produtos.size();i++){
                                System.out.println(""+i+"-"+produtos.get(i));
                            }
                            System.out.println("Digite o número do produto (-1 finalizar):");
                            op = scan.nextInt();
                            if(op >= 0 && op < produtos.size()){
                                produtosVenda.add(produtos.get(op));
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

        System.out.println("Deseja salvar os dados em arquivo? (1-sim)");
        op = scan.nextInt();
        if(op == 1){
            salvarBinario(mercearia);
        }


    }


}
