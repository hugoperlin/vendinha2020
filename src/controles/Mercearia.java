package controles;

import modelos.Cliente;

public class Mercearia {

    private static int MAX_CLIENTES = 50;
    private static int totalClientes = 0;


    private String nome;
    private Cliente[] clientes;

    public Mercearia(String nome){
        this.nome = nome;
        this.clientes = new Cliente[MAX_CLIENTES];
    }

    public boolean adiciona(Cliente cliente){

        if(buscarCliente(cliente.getNome())==null){
            this.clientes[totalClientes] = cliente;
            totalClientes += 1;

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

    public String toString(){
        String str = nome+"\n";

        str += "Clientes:[\n";

        for(int i=0;i<totalClientes;i++){
            str += "\t"+this.clientes[i]+";\n";
        }

        str += "]\n";

        return str;

    }


}
