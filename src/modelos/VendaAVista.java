package modelos;

public class VendaAVista extends Venda{

    private double desconto;

    public VendaAVista(Cliente cliente, double desconto) {
        super(cliente);
        this.desconto = desconto;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    @Override
    public double calculaTotal() {

        double subTotal = calculaSubTotal();

        this.total = subTotal - desconto;

        return this.total;
    }

    @Override
    public String toString() {

        String str = super.toString();

        str = str.replace("Venda","Venda à Vista");
        str += "(desc: R$"+desconto+")";

        return str;
    }
}
