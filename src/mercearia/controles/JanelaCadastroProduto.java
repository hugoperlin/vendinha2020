package mercearia.controles;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import mercearia.MainGui;
import mercearia.modelos.Mercearia;
import mercearia.modelos.Produto;

public class JanelaCadastroProduto {


    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfPreco;


    private Mercearia mercearia;


    public JanelaCadastroProduto(Mercearia mercearia){
        this.mercearia = mercearia;
    }



    /***
     * Cadastro de produto
     * Ao usuário clicar no botão cadastrar na janela de cadastro de produto,
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
        double preco = -1;

        if(nome.isBlank() || nome.isEmpty()){
            mensagem(Alert.AlertType.ERROR, "Nome invalido!!");
            return;
        }

        try{
            preco = Double.valueOf(tfPreco.getText());
        }catch (Exception e){
            mensagem(Alert.AlertType.ERROR, "Formato  numero invalido!!");
            return;
        }



        if(preco < 0 ){
            mensagem(Alert.AlertType.ERROR, "Preço invalido!!");
            return;
        }


        if(mercearia.adiciona(new Produto(nome,preco))){
            mensagem(Alert.AlertType.INFORMATION,"Produto cadastrado com sucesso!!");


            //retorna para a janela principal
            MainGui.mudaCena(MainGui.PRINCIPAL,(aClass)-> new Principal(mercearia));

        }else{
            mensagem(Alert.AlertType.ERROR,"Produto ja existe!!");

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
