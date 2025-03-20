package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    
    private Stage stageLogin;
    
     @FXML
    private Button btnFechar;

    @FXML
    private Button btnLogar;

    @FXML
    private Label lblSenha;

    @FXML
    private Label lblUsuario;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private TextField txtUsuario;
    
        @FXML
    void btnFecharClick(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void btnLogarClick(ActionEvent event) {

    }
    
    public void setStage(Stage stage){
        this.stageLogin = stage;
    }
    
    public void abrirJanela(){
        System.out.println("Janela foi exibida!");
    }
    
}
