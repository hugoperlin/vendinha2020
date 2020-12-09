package mercearia.controles;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import mercearia.MainGui;
import mercearia.modelos.Mercearia;

import java.net.URL;
import java.util.ResourceBundle;

public class JanelaRelatorio implements Initializable {

    @FXML
    private TextArea taRelatorio;

    private Mercearia mercearia;

    public JanelaRelatorio(Mercearia mercearia) {
        this.mercearia = mercearia;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        taRelatorio.setText(mercearia.toString());
    }

    @FXML
    private void fechar(){

        MainGui.mudaCena(MainGui.PRINCIPAL,(aClass)->new Principal(mercearia));

    }

}
