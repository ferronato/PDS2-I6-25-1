package controller;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class PrincipalController {

    private Stage stagePrincipal;

    @FXML
    private Label lblUsuario;

    @FXML
    private Menu menuAjuda;

    @FXML
    private Menu menuCadastro;

    @FXML
    private MenuItem menuCadastroUsuarios;

    @FXML
    private MenuItem menuFechar;

    @FXML
    private MenuItem menuRelatorioUsuarios;

    @FXML
    private Menu menuRelatorios;

    @FXML
    private MenuItem menuSobre;

    @FXML
    void menuCadastroUsuariosClick(ActionEvent event) {

    }

    @FXML
    void menuFecharClick(ActionEvent event) {
        System.exit(0);
    }

    void setStage(Stage telaPrincipal) {
        this.stagePrincipal = telaPrincipal;
    }

    void ajustarElementosJanela(ArrayList<String> dados) {
        System.out.println("Aqui chegam os parâmetros do login "
                + dados.get(0) + " - " + dados.get(1));
        lblUsuario.setText(dados.get(0));
        if (dados.get(1).equals("admin")) {
            System.out.println("Acesso completo");
        } else {
            System.out.println("Acesso restrito");
            menuRelatorios.setDisable(true);
        }
    }

}
