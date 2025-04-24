package controller;

import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Usuario;
import model.UsuarioDAO;
import util.AlertaUtil;

public class CadastroUsuariosController {
    Stage stageCadastroUsuarios;
    Usuario usuarioSelecionado;
    
      @FXML
    private Button btnExcluir;

    @FXML
    private Button btnFechar;

    @FXML
    private Button btnIncluirAlterar;

    @FXML
    private ComboBox<String> cbPerfil;

    @FXML
    private TextField txtLogin;

    @FXML
    private TextField txtNome;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private TextField txtTelefone;

    @FXML
    void btnExcluirClick(ActionEvent event) {

    }

    @FXML
    void btnFecharClick(ActionEvent event) {
        stageCadastroUsuarios.close();
    }

    @FXML
    void btnIncluirAlterarClick(ActionEvent event) throws SQLException {
        if(usuarioSelecionado == null){
            incluir(txtNome.getText(),
            txtTelefone.getText(), txtLogin.getText(),
            txtSenha.getText(), cbPerfil.getValue());
        } else {
            
        }
    }

    
    void setStage(Stage telaCadastroUsuarios){
        this.stageCadastroUsuarios = telaCadastroUsuarios;
    }
    
    void ajustarElementosJanela(Usuario user){
        this.usuarioSelecionado = user;
        if(user == null){
            txtNome.requestFocus();
            btnExcluir.setVisible(false);
            btnIncluirAlterar.setText("Salvar");
            cbPerfil.getItems().addAll("admin", "user");
        } else {
            System.out.println("Estamos alterando ou excluindo..");
        }
    }

    void incluir(String nome, String fone, 
        String login, String senha, String perfil) throws SQLException {
        Usuario usuario = new Usuario(nome, fone, login,
        senha, perfil);
        new UsuarioDAO().salvar(usuario);
        AlertaUtil.mostrarInformacao("Informação",
                "Registro inserido com sucesso!");
        stageCadastroUsuarios.close();
    }
    
}
