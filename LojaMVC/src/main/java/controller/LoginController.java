package controller;

import dal.ConexaoBD;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.LoginDAO;
import model.Usuario;

public class LoginController {

    private Stage stageLogin;
    private Connection conexao;
    private final LoginDAO dao = new LoginDAO();
    private ArrayList<String> listaDados;
    private Usuario user;

    @FXML
    private Button bntFechar;

    @FXML
    private Button bntLogar;

    @FXML
    private Label lblDB;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private TextField txtUsuario;

    @FXML
    void bntFecharClick(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void bntLogarClick(ActionEvent event) throws IOException, SQLException {
        processarLogin();
    }

    public void setStage(Stage stage) {
        this.stageLogin = stage;
    }

    public void verificarBanco() {
        this.conexao = ConexaoBD.conectar();

        if (this.conexao != null) {
            System.out.println("Conectou no banco de dados");
        } else {
            System.out.println("Problemas na conexão com o banco de dados");
        }

    }

    public void abrirJanela() {
        verificarBanco();
    }

    public void processarLogin() throws IOException, SQLException {
        if (!dao.bancoOnline()) {
            System.out.println("Banco de dados desconectado!");
        } else if (txtUsuario.getText() != null && !txtUsuario.getText().isEmpty() && txtSenha.getText() != null && !txtSenha.getText().isEmpty()) {
            listaDados = autenticar(txtUsuario.getText(),
                    txtSenha.getText());
            if (listaDados != null) {
                System.out.println("Bem vindo "
                        + listaDados.get(0) + " acesso liberado!");
                if (stageLogin != null) {
                    stageLogin.close();
                }
                abrirTelaPrincipal(listaDados);
            } else {
                System.out.println("Usuário e senha invalidos!");
            }
        } else {
            System.out.println("Verifique as informações!");
        }

    }

    private ArrayList<String> autenticar(String login, String senha) throws SQLException {
        user = dao.autenticar(login, senha);
        if (user != null) {
            ArrayList<String> listaDados = new ArrayList<>();
            listaDados.add(user.getNome());
            listaDados.add(user.getPerfil());
            return listaDados;
        }
        return null;
    }

    private void abrirTelaPrincipal(ArrayList<String> dados) throws MalformedURLException, IOException {
        URL url = new File("src/main/java/view/Principal.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        Stage telaPrincipal = new Stage();
        PrincipalController pc = loader.getController();

        pc.setStage(telaPrincipal);

        telaPrincipal.setOnShown(evento -> {
            pc.ajustarElementosJanela(dados);
        });

        Scene scene = new Scene(root);

        telaPrincipal.setTitle("Tela principal do Sistema");
        telaPrincipal.setScene(scene);
        telaPrincipal.show();
    }

}
