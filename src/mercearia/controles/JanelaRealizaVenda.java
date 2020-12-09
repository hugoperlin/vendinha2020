package mercearia.controles;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import mercearia.MainGui;
import mercearia.modelos.Cliente;
import mercearia.modelos.Mercearia;
import mercearia.modelos.Produto;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class JanelaRealizaVenda implements Initializable {

    @FXML
    private ComboBox<Cliente> cbClientes;

    @FXML
    private ListView<Produto> lstwProdutos;

    @FXML
    private TextField tfTotal;

    @FXML
    private RadioButton rbAVista;

    @FXML
    private RadioButton rbAPrazo;

    @FXML
    private TextField tfTipoValor;

    @FXML
    private Label lbTipoValor;


    private Mercearia mercearia;

    public JanelaRealizaVenda(Mercearia mercearia){
        this.mercearia = mercearia;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cbClientes.setCellFactory(clienteListView -> new ListCell<>(){
            @Override
            protected void updateItem(Cliente cliente, boolean b) {
                super.updateItem(cliente, b);
                if(cliente != null){
                    setText(cliente.getNome());
                }else{
                    setText("");
                }
            }
        });


        populaComboClientes();

        lstwProdutos.setCellFactory(produtoListView -> new ListCell<>(){
            @Override
            protected void updateItem(Produto produto, boolean b) {
                super.updateItem(produto, b);

                if(produto != null){
                    setText(produto.getNome()+"(R$ "+produto.getPreco()+")");
                }else{
                    setText("");
                }
            }
        });

        lstwProdutos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        populaListaProdutos();


        lbTipoValor.setVisible(false);
        tfTipoValor.setVisible(false);
        rbAVista.setDisable(true);
        rbAPrazo.setDisable(true);

    }


    private void populaComboClientes(){

        List<Cliente> clientes = mercearia.getClientes();

        cbClientes.getItems().clear();

        for(Cliente c:clientes){
            cbClientes.getItems().add(c);
        }

    }

    private void populaListaProdutos(){

        List<Produto> produtos = mercearia.getProdutos();

        lstwProdutos.getItems().clear();

        for(Produto p:produtos){
            lstwProdutos.getItems().add(p);
        }

    }

    @FXML
    private void habilitarListView(){
        lstwProdutos.setDisable(false);
    }


    @FXML
    private void atualizarTotal(){

        List<Produto> produtosSelecionados = lstwProdutos.getSelectionModel().getSelectedItems();

        double valor = 0;

        for(Produto p:produtosSelecionados){
            valor += p.getPreco();
        }

        tfTotal.setText("R$" + valor);

        if(produtosSelecionados.size() >0){
            rbAPrazo.setDisable(false);
            rbAVista.setDisable(false);
        }else{
            rbAPrazo.setDisable(true);
            rbAVista.setDisable(true);
            tfTipoValor.setVisible(false);
            lbTipoValor.setVisible(false);
        }


    }

    @FXML
    private void atualizaLabelTipoValor(Event event){

        String texto = event.getSource()==rbAVista?"Desconto:":"Juros:";

        lbTipoValor.setText(texto);

        lbTipoValor.setVisible(true);
        tfTipoValor.setVisible(true);

    }


    @FXML
    private void cadastrar(){

        Cliente cliente = cbClientes.getValue();
        List<Produto> produtos = lstwProdutos.getSelectionModel().getSelectedItems();
        boolean aVista = rbAVista.isSelected();
        double valorAlteracao = 0;

        try{
            valorAlteracao = Double.valueOf(tfTipoValor.getText());
        }catch (Exception e){
            mensagem(Alert.AlertType.ERROR,"Formato do valor de desconto/juros é inválido!!");
            return;
        }

        if(valorAlteracao < 0){
            mensagem(Alert.AlertType.ERROR,"Valor de desconto/juros é inválido!!");
            return;
        }

        if(!aVista){
            mercearia.realizaVendaAPrazo(cliente,new ArrayList<>(produtos),valorAlteracao);
        }else{
            mercearia.realizaVendaAVista(cliente,new ArrayList<>(produtos),valorAlteracao);
        }

        mensagem(Alert.AlertType.INFORMATION,"Venda realizada!!!");

        MainGui.mudaCena(MainGui.PRINCIPAL,(aClass)->new Principal(mercearia));
    }


    @FXML
    private void cancelar(){

        MainGui.mudaCena(MainGui.PRINCIPAL,(aClass)->new Principal(mercearia));

    }

    private void mensagem(Alert.AlertType type, String msg){
        Alert alert = new Alert(type,msg);
        alert.showAndWait();
    }


}
