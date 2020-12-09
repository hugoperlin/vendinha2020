package mercearia;

import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import mercearia.controles.JanelaCadastroCliente;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mercearia.controles.Principal;
import mercearia.modelos.Mercearia;

public class MainGui extends Application {

    public static final String PRINCIPAL = "/fxml/Principal.fxml";
    public static final String CADASTROCLIENTE = "/fxml/JanelaCadastroCliente.fxml";



    private static Mercearia mercearia;

    private static StackPane base;


    public static void main(String[] args) {


        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        mercearia = new Mercearia("Teste...");
        base = new StackPane();


        stage.setScene(new Scene(base, Region.USE_PREF_SIZE,Region.USE_PREF_SIZE));
        stage.setTitle("Controle de Mercearia");


        mudaCena(MainGui.PRINCIPAL,(aClass) -> new Principal(mercearia));

        stage.show();


    }


    public static void mudaCena(String fxml, Callback controllerFactory){
        try{

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainGui.class.getResource(fxml));
            loader.setControllerFactory(controllerFactory);

            Parent novoRoot = loader.load();

            //ja existe alguma coisa sendo mostrada, entao remover
            if(base.getChildren().stream().count()>0){
                base.getChildren().remove(0);
            }
            base.getChildren().add(novoRoot);

        }catch (Exception e){
            e.printStackTrace();
        }



    }



}
