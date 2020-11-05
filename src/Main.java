import controles.Mercearia;
import modelos.Cliente;

public class Main {

    public static void main(String[] args) {

        Cliente c1 = new Cliente("Cliente 1","12345","cliente1@teste.com");
        Cliente c2 = new Cliente("Cliente 2","123456","cliente2@teste.com");
        Cliente c3 = new Cliente("Cliente 3","1234567","cliente3@teste.com");
        Cliente c4 = new Cliente("Cliente 4","1234568","cliente4@teste.com");

        Mercearia mercearia = new Mercearia("Teste");

        mercearia.adiciona(c1);
        mercearia.adiciona(c2);
        mercearia.adiciona(c3);
        mercearia.adiciona(c4);

        System.out.println(mercearia);

        Cliente res = mercearia.buscarCliente("Cliente 5");
        System.out.println(res);




    }

}
