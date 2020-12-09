package mercearia.controles;

import javafx.fxml.FXML;
import mercearia.MainGui;
import mercearia.modelos.Mercearia;

public class Principal {


    private Mercearia mercearia;

    public Principal(Mercearia mercearia){
        this.mercearia = mercearia;
    }


    @FXML
    private void mostrarCadastroCliente(){

        MainGui.mudaCena(MainGui.CADASTROCLIENTE,(aClass)->new JanelaCadastroCliente(mercearia));

    }




}
