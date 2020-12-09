package mercearia.controles;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import mercearia.MainGui;
import mercearia.modelos.Cliente;
import mercearia.modelos.Mercearia;

import java.net.URL;
import java.util.ResourceBundle;

public class JanelaRealizaVenda implements Initializable {



    private Mercearia mercearia;

    public JanelaRealizaVenda(Mercearia mercearia){
        this.mercearia = mercearia;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {






    }

    @FXML
    private void cadastrar(){

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
