package mercearia.controles;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import mercearia.MainGui;
import mercearia.modelos.Cliente;
import mercearia.modelos.Mercearia;
import mercearia.modelos.Produto;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Principal implements Initializable {

    @FXML
    private ListView<Cliente> lstwClientes;

    @FXML
    private ListView<Produto> lstwProdutos;

    private Mercearia mercearia;

    public Principal(Mercearia mercearia){
        this.mercearia = mercearia;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //mudando a forma de renderizar as células da lista
        //mostrando somente o nome
        lstwClientes.setCellFactory(clienteListView -> new ListCell<>(){
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

        atualizaListaClientes();


        lstwProdutos.setCellFactory((produtoListView -> new ListCell<>(){
            @Override
            protected void updateItem(Produto produto, boolean b) {
                super.updateItem(produto, b);

                if(produto != null){
                    setText(produto.getNome());
                }else{
                    setText("");
                }
            }
        }));


        autalizaListaProdutos();



    }

    private void autalizaListaProdutos() {

        List<Produto> produtos = mercearia.getProdutos();

        lstwProdutos.getItems().clear();

        for(Produto p:produtos){
            lstwProdutos.getItems().add(p);
        }

    }


    private void atualizaListaClientes(){
        List<Cliente> clientes = this.mercearia.getClientes();

        lstwClientes.getItems().clear();

        for(Cliente c:clientes){
            lstwClientes.getItems().add(c);
        }



    }



    @FXML
    private void mostrarCadastroCliente(){

        MainGui.mudaCena(MainGui.CADASTROCLIENTE,(aClass)->new JanelaCadastroCliente(mercearia));

    }


    @FXML
    private void mostrarCadastroProduto(){

        MainGui.mudaCena(MainGui.CADASTROPRODUTO,(aClass)->new JanelaCadastroProduto(mercearia));

    }



}
