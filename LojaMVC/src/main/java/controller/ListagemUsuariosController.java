package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Usuario;

public class ListagemUsuariosController {

    Stage stageListagemUsuarios;
    
    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnFechar;

    @FXML
    private TableView<Usuario> tabelaUsuarios;

    @FXML
    private TextField txtPesquisar;

    @FXML
    void btnCadastrarClick(ActionEvent event) {

    }

    @FXML
    void btnFecharClick(ActionEvent event) {
        stageListagemUsuarios.close();
    }

    void setStage(Stage telaListagemUsuarios) {
        this.stageListagemUsuarios = telaListagemUsuarios;
    }

    void ajustarElementosJanela() {
        carregarUsuariosTabela();
    }

    private void carregarUsuariosTabela() {
        System.out.println("Aqui será mostrada a listagem de usuarios!");
    }

}
