package modelos;

public class VendaAPrazo extends Venda{

    private double juros;

    public VendaAPrazo(Cliente cliente, double juros) {
        super(cliente);
        this.juros = juros;
    }


    public double getJuros() {
        return juros;
    }

    public void setJuros(double juros) {
        this.juros = juros;
    }

    @Override
    public double calculaTotal() {

        double subTotal = calculaSubTotal();

        this.total = subTotal + juros;


        return this.total;
    }

    @Override
    public String toString() {

        String str = super.toString();

        str = str.replace("Venda","Venda à prazo");

        str += "(juros: R$ "+juros+")";

        return str;
    }
}
