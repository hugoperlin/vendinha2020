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


    /***
     * Cadastro de cliente
     * Ao usuário clicar no botão cadastrar na janela de cadastro de cliente,
     * este método será executado.
     * 1 - Acessa os dados inseridos pelo usuário nos TextField.
     * 2 - Valida os dados inseridos pelo usuário
     * 3 - Insere o novo cliente na mercearia
     * 4 - Dependendo do resultado, mostra uma mensagem de sucesso e retorna para a
     * janela principal ou mostra uma mensagem de erro e fica na mesma janela.     *
     */

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

            //Retorna para a janela principal. Note que é necessário passar uma lambda expression
            //para indicar qual controlador da janela deve ser utilizado.
;           MainGui.mudaCena(MainGui.PRINCIPAL,(aClass)->new Principal(mercearia));

        }


    }

    /**
     * Cancelar
     * Ao usuário clicar no método cancelar,
     * muda a cena para a janela principal
     * */


    @FXML
    private void cancelar(){

        MainGui.mudaCena(MainGui.PRINCIPAL,(aClass)->new Principal(mercearia));

    }

    /**
     *
     * Mostra um pop-up com uma mensagem e um ícone
     *
     * Ícones:
     * Alert.AlertType.ERROR - indica erro
     * Alert.AlertType.INFORMATION - indica uma informação
     * */

    private void mensagem(Alert.AlertType type, String msg){
        Alert alert = new Alert(type,msg);
        alert.showAndWait();
    }





}
