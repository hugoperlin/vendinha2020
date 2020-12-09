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


/**
 * Classe principal para a interface gráfica da vendinha.
 * Além de incializar o sistema também permite a mudança de cena
 */

public class MainGui extends Application {

    /**
     * Atributos estáticos com o caminho para cada fxml. Note que o caminho
     * é relativo para a pasta resources do projeto. Utilizado pelo método mudaCena.
     * Caso queira inserir uma nova janela, crie um novo atributo e indique o caminho
     * do fxml.
     */

    public static final String PRINCIPAL = "/fxml/Principal.fxml";
    public static final String CADASTROCLIENTE = "/fxml/JanelaCadastroCliente.fxml";
    public static final String CADASTROPRODUTO = "/fxml/JanelaCadastroProduto.fxml";
    public static final String REALIZAVENDA = "/fxml/JanelaRealizaVenda.fxml";
    public static final String RELATORIO = "/fxml/JanelaRelatorio.fxml";


    /**
     * Atributo que representa a mercearia. Será utilizado
     * por todas as janelas, através da injeção de dependência.
     */
    private static Mercearia mercearia;


    /**
     * Atrituto que repreenta o gerenciador base que será inserido
     * no stage. Por ser um StackPane, podemos inserir outros gerenciadores
     * de layout que sempre serão mostrados por cima.
     */

    private static StackPane base;


    public static void main(String[] args) {


        Application.launch(args);
    }

    /**
     * Método executado no iníco da aplicação JavaFX.
     * Utilizamos para carregar os dados do disco e criar
     * uma mercearia a partir do arquivo binário.
     * Se o arquivo não existir, criamos uma vendinha nova.
     *
     * @throws Exception
     */
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

    /**
     *
     * Método que é executado antes de mostrar a janela.
     * Alocamos o StackPane e colocamos na cena base.
     *
     * Invocamos o método mudaCena para carregar a cena principal.
     *
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {

        base = new StackPane();


        stage.setScene(new Scene(base, Region.USE_PREF_SIZE,Region.USE_PREF_SIZE));
        stage.setTitle("Controle de Mercearia");


        mudaCena(MainGui.PRINCIPAL,(aClass) -> new Principal(mercearia));

        stage.show();


    }

    /**
     *
     * Método executado sempre quando a aplicação é fechada.
     * Utilizamos para salvar o objeto mercearia no disco.
     *
     * @throws Exception
     */
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

    /**
     *
     * Método que permite mudar o conteúdo da janela, permitindo
     * assim a troca de cena, navegando o usuário entre as funcionalidades do sistema.
     * Pode ser invocado de qualquer uma das classes do projeto.
     *
     * @param fxml o conteúdo deve ser o especificado por um dos atributos estáticos da classe MainGui
     * @param controllerFactory lamba expression com um objeto do controlador da janela que será criada
     */


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
