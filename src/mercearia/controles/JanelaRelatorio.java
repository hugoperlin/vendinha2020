package mercearia.controles;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import mercearia.MainGui;
import mercearia.modelos.Mercearia;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Janela para mostrar um relatório atual da merceria
 */

public class JanelaRelatorio implements Initializable {

    @FXML
    private TextArea taRelatorio;

    private Mercearia mercearia;

    /**
     *
     * Cria um novo objeto de controle da interface de relatório
     *
     * @param mercearia referência para um objeto mercearia criado externamente
     */

    public JanelaRelatorio(Mercearia mercearia) {
        this.mercearia = mercearia;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        taRelatorio.setText(mercearia.toString());
    }

    /**
     * Fechar
     * Ao usuário clicar no método cancelar,
     * muda a cena para a janela principal
     * */
    @FXML
    private void fechar(){

        MainGui.mudaCena(MainGui.PRINCIPAL,(aClass)->new Principal(mercearia));

    }

}
