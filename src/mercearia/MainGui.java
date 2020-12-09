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

import java.io.*;

public class MainGui extends Application {

    public static final String PRINCIPAL = "/fxml/Principal.fxml";
    public static final String CADASTROCLIENTE = "/fxml/JanelaCadastroCliente.fxml";
    public static final String CADASTROPRODUTO = "/fxml/JanelaCadastroProduto.fxml";
    public static final String REALIZAVENDA = "/fxml/JanelaRealizaVenda.fxml";



    private static Mercearia mercearia;

    private static StackPane base;


    public static void main(String[] args) {


        Application.launch(args);
    }

    //método executado na inicialização do sistema
    @Override
    public void init() throws Exception {
        super.init();


        //carregando os dados do disco...
        File vendinha = new File("vendinha.bin");

        if(vendinha.exists()){
            try(ObjectInputStream obi = new ObjectInputStream(new FileInputStream(vendinha))){

                mercearia = (Mercearia) obi.readObject();

            }catch (IOException e){
                e.printStackTrace();
            }catch (ClassNotFoundException e){
                e.printStackTrace();
            }
        }else{
            mercearia = new Mercearia("Teste...");
        }


    }

    //método que prepara a janela
    @Override
    public void start(Stage stage) throws Exception {

        base = new StackPane();


        stage.setScene(new Scene(base, Region.USE_PREF_SIZE,Region.USE_PREF_SIZE));
        stage.setTitle("Controle de Mercearia");


        mudaCena(MainGui.PRINCIPAL,(aClass) -> new Principal(mercearia));

        stage.show();


    }

    //método executado quando a aplicação é fechada
    @Override
    public void stop() throws Exception {
        super.stop();


        //salvando os dados em disco
        try(ObjectOutputStream obs = new ObjectOutputStream(new FileOutputStream(new File("vendinha.bin")))){

            obs.writeObject(mercearia);

        }catch (IOException e){
            e.printStackTrace();
        }

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
