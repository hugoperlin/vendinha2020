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

            MainGui.mudaCena(MainGui.PRINCIPAL,(aClass)-> new Principal(mercearia));

        }else{
            mensagem(Alert.AlertType.ERROR,"Produto ja existe!!");

        }



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
