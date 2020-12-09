package mercearia.controles;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import mercearia.MainGui;
import mercearia.modelos.Cliente;
import mercearia.modelos.Mercearia;

public class JanelaCadastroCliente {

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfTelefone;


    Mercearia mercearia;


    public JanelaCadastroCliente(Mercearia mercearia ){
        this.mercearia = mercearia;
    }


    @FXML
    private void cadastrar(){

        String nome = tfNome.getText();
        String email = tfEmail.getText();
        String telefone = tfTelefone.getText();


        if(nome.isBlank() || nome.isEmpty()){
            //TODO melhorar a validação
            mensagem(Alert.AlertType.ERROR,"Nome invalido!!");
            return;
        }
        if(email.isBlank() || email.isEmpty()){
            //TODO melhorar a validação
            mensagem(Alert.AlertType.ERROR,"Email invalido!!");
            return;
        }
        if(telefone.isBlank() || telefone.isBlank()){
            //TODO melhorar a validação
            mensagem(Alert.AlertType.ERROR,"Telefone invalido!!");
            return;
        }


        //cadastrar a cliente
        if(!mercearia.adiciona(new Cliente(nome,email,telefone))){
            mensagem(Alert.AlertType.ERROR,"Cliente não cadastrado!!");
        }else{
            mensagem(Alert.AlertType.INFORMATION,"Cliente cadastrado!!");

;
            System.out.println(mercearia);

        }


    }




    private void mensagem(Alert.AlertType type, String msg){
        Alert alert = new Alert(type,msg);
        alert.showAndWait();
    }





}
