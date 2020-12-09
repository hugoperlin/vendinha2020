package mercearia.controles;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import mercearia.MainGui;
import mercearia.modelos.Cliente;
import mercearia.modelos.Mercearia;
import mercearia.modelos.Produto;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Janela principal, permite acessar todas as outras janelas do sistema
 */

public class Principal implements Initializable {

    @FXML
    private ListView<Cliente> lstwClientes;

    @FXML
    private ListView<Produto> lstwProdutos;

    private Mercearia mercearia;


    /**
     * Criar um novo controlador para a janela principal
     *
     * @param mercearia recebe uma referência para o objeto mercearia
     */

    public Principal(Mercearia mercearia){
        this.mercearia = mercearia;
    }


    /**
     *
     * Inicializa os componentes da janela principal,
     * inserindo a lista de clientes e a lista de produtos.
     * Note que a forma de renderização das células dos ListView
     * foram alteradas.
     *
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //mudando a forma de renderizar as células da lista
        //mostrando somente o nome
        lstwClientes.setCellFactory(clienteListView -> new ListCell<>(){
            @Override
            protected void updateItem(Cliente cliente, boolean b) {
                super.updateItem(cliente, b);
                if(cliente != null){
                    setText(cliente.getNome());
                }else{
                    setText("");
                }
            }
        });

        atualizaListaClientes();


        lstwProdutos.setCellFactory((produtoListView -> new ListCell<>(){
            @Override
            protected void updateItem(Produto produto, boolean b) {
                super.updateItem(produto, b);

                if(produto != null){
                    setText(produto.getNome());
                }else{
                    setText("");
                }
            }
        }));


        autalizaListaProdutos();



    }

    /**
     * Percorre a lista de produtos e insere no ListView
     */

    private void autalizaListaProdutos() {

        List<Produto> produtos = mercearia.getProdutos();

        lstwProdutos.getItems().clear();

        for(Produto p:produtos){
            lstwProdutos.getItems().add(p);
        }

    }


    /**
     * Percorre a lista de clientes e insere no ListView
     */
    private void atualizaListaClientes(){
        List<Cliente> clientes = this.mercearia.getClientes();

        lstwClientes.getItems().clear();

        for(Cliente c:clientes){
            lstwClientes.getItems().add(c);
        }
    }


    /**
     * Solicita a alteração de cena, conduzindo o usuário
     * para o cadastro de clientes. Passa uma lambda expression
     * com um controlador da janela de cadastro de cliente
     */

    @FXML
    private void mostrarCadastroCliente(){

        MainGui.mudaCena(MainGui.CADASTROCLIENTE,(aClass)->new JanelaCadastroCliente(mercearia));

    }

    /**
     * Solicita a alteração de cena, conduzindo o usuário
     * para o cadastro de produto. Passa uma lambda expression
     * com um controlador da janela de cadastro de produto
     */
    @FXML
    private void mostrarCadastroProduto(){

        MainGui.mudaCena(MainGui.CADASTROPRODUTO,(aClass)->new JanelaCadastroProduto(mercearia));

    }

    /**
     * Solicita a alteração de cena, conduzindo o usuário
     * para a realização de uma venda. Passa uma lambda expression
     * com um controlador da janela de realizar venda
     */
    @FXML
    private void mostrarRealizaVenda(){

        MainGui.mudaCena(MainGui.REALIZAVENDA,(aClass)->new JanelaRealizaVenda(mercearia));

    }

    /**
     * Solicita a alteração de cena, conduzindo o usuário
     * para o relatório. Passa uma lambda expression
     * com um controlador da janela de relatório.
     */

    @FXML
    private void mostrarRelatorio(){

        MainGui.mudaCena(MainGui.RELATORIO,(aClass)->new JanelaRelatorio(mercearia));

    }


}
