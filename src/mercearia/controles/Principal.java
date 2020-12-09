package mercearia.controles;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import mercearia.MainGui;
import mercearia.modelos.Cliente;
import mercearia.modelos.Mercearia;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Principal implements Initializable {

    @FXML
    private ListView<Cliente> lstwClientes;




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




}
