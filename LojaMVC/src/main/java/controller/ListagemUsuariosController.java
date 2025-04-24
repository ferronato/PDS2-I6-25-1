package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    void btnCadastrarClick(ActionEvent event) throws IOException {
        URL url = new File("src/main/java/view/CadastroUsuarios.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        
        Stage telaCadastroUsuarios = new Stage();
        
        CadastroUsuariosController cadc = loader.getController();

        cadc.setStage(telaCadastroUsuarios);

        telaCadastroUsuarios.setOnShown(evento -> {
            cadc.ajustarElementosJanela(null);
        });

        Scene scene = new Scene(root);
        
        telaCadastroUsuarios.setTitle("Cadastro de Usuários");
        telaCadastroUsuarios.setScene(scene);
        telaCadastroUsuarios.show();
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
