package mercearia.controles;

/**
 * Janela para realizar uma venda
 */

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import mercearia.MainGui;
import mercearia.modelos.Cliente;
import mercearia.modelos.Mercearia;
import mercearia.modelos.Produto;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class JanelaRealizaVenda implements Initializable {

    @FXML
    private ComboBox<Cliente> cbClientes;

    @FXML
    private ListView<Produto> lstwProdutos;

    @FXML
    private TextField tfTotal;

    @FXML
    private RadioButton rbAVista;

    @FXML
    private RadioButton rbAPrazo;

    @FXML
    private TextField tfTipoValor;

    @FXML
    private Label lbTipoValor;


    private Mercearia mercearia;

    public JanelaRealizaVenda(Mercearia mercearia){
        this.mercearia = mercearia;
    }


    /**
     *Inicializa a cena povoando o ComboBox com a lista de clientes e
     * também a ListView com os produtos.
     * Note que a forma de renderizar as células do ComboBox e da ListView
     * foram alteradas através do setCellFactory. Para o cliente é utilizado
     * o nome e para o produto é utilizado o nome seguido do preço.
     * Também são desabilitados e escondidos alguns componentes para
     * aumetar o controle da interação do usuário.
    ***/


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cbClientes.setCellFactory(clienteListView -> new ListCell<>(){
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


        populaComboClientes();

        lstwProdutos.setCellFactory(produtoListView -> new ListCell<>(){
            @Override
            protected void updateItem(Produto produto, boolean b) {
                super.updateItem(produto, b);

                if(produto != null){
                    setText(produto.getNome()+"(R$ "+produto.getPreco()+")");
                }else{
                    setText("");
                }
            }
        });

        lstwProdutos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        populaListaProdutos();


        //ao setar visible como false, o componente é escondido
        lbTipoValor.setVisible(false);
        tfTipoValor.setVisible(false);
        //ao setar disable como true, o componente não permite intereção
        rbAVista.setDisable(true);
        rbAPrazo.setDisable(true);

    }


    /**
     * Percorre a lista de clientes da mercearia e insere no ComboBox
     */
    private void populaComboClientes(){

        List<Cliente> clientes = mercearia.getClientes();

        cbClientes.getItems().clear();

        for(Cliente c:clientes){
            cbClientes.getItems().add(c);
        }

    }

    /**
     * Percorre a lista de produtos da mercearia e insere no ListView
     * */

    private void populaListaProdutos(){

        List<Produto> produtos = mercearia.getProdutos();

        lstwProdutos.getItems().clear();

        for(Produto p:produtos){
            lstwProdutos.getItems().add(p);
        }

    }


    /**
     * Quando o usuário seleciona um cliente no ComboBox,
     * habilitamos a ListView para que ele possa escolher os produtos.
     */

    @FXML
    private void habilitarListView(){
        lstwProdutos.setDisable(false);
    }


    /**
     * Quando o usuário escolhe um produto na ListView,
     * atualizamos o valor total mostrado.
     * Além disso, verificamos se o usuário selecionou ao menos
     * um produto, para então habilitar a escolha do tipo de venda.
     *
     */

    @FXML
    private void atualizarTotal(){

        List<Produto> produtosSelecionados = lstwProdutos.getSelectionModel().getSelectedItems();

        double valor = 0;

        for(Produto p:produtosSelecionados){
            valor += p.getPreco();
        }

        tfTotal.setText("R$" + valor);

        if(produtosSelecionados.size() >0){
            rbAPrazo.setDisable(false);
            rbAVista.setDisable(false);
        }else{
            rbAPrazo.setDisable(true);
            rbAVista.setDisable(true);
            tfTipoValor.setVisible(false);
            lbTipoValor.setVisible(false);
        }


    }

    /**
     * Quando o usuário clica em um dos RadioButtons,
     * alteramos o label do tipo de valor, para melhor
     * informar o que está acontecendo.
     * @param event
     */


    @FXML
    private void atualizaLabelTipoValor(Event event){

        String texto = event.getSource()==rbAVista?"Desconto:":"Juros:";

        lbTipoValor.setText(texto);

        lbTipoValor.setVisible(true);
        tfTipoValor.setVisible(true);

    }

    /***
     * Quando o usuário clicar no botão de cadastrar a venda,
     * pegamos os dados inseridos pelo usuário nos componentes,
     * validamos e inserimos na mercearia. Caso não existam erros, retornamos
     * para a janela principal.
     */
    @FXML
    private void cadastrar(){

        Cliente cliente = cbClientes.getValue();
        List<Produto> produtos = lstwProdutos.getSelectionModel().getSelectedItems();
        boolean aVista = rbAVista.isSelected();
        double valorAlteracao = 0;

        try{
            valorAlteracao = Double.valueOf(tfTipoValor.getText());
        }catch (Exception e){
            mensagem(Alert.AlertType.ERROR,"Formato do valor de desconto/juros é inválido!!");
            return;
        }

        if(valorAlteracao < 0){
            mensagem(Alert.AlertType.ERROR,"Valor de desconto/juros é inválido!!");
            return;
        }

        if(!aVista){
            mercearia.realizaVendaAPrazo(cliente,new ArrayList<>(produtos),valorAlteracao);
        }else{
            mercearia.realizaVendaAVista(cliente,new ArrayList<>(produtos),valorAlteracao);
        }

        mensagem(Alert.AlertType.INFORMATION,"Venda realizada!!!");

        MainGui.mudaCena(MainGui.PRINCIPAL,(aClass)->new Principal(mercearia));
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
