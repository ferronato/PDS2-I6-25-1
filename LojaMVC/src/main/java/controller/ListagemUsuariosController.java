package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Usuario;
import model.UsuarioDAO;
import util.AlertaUtil;

public class ListagemUsuariosController {

    Stage stageListagemUsuarios;
    Usuario usuario;

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

    void ajustarElementosJanela() throws SQLException {
        carregarUsuariosTabela();
    }

    //Método para listar usuarios no TableView
    private void carregarUsuariosTabela() throws SQLException {
        ObservableList<Usuario> lista
                = FXCollections.observableArrayList(listarUsuarios());
        if (!lista.isEmpty()) {
            tabelaUsuarios.getColumns().clear();

            TableColumn<Usuario, Number> colunaID
                    = new TableColumn<>("ID");
            colunaID.setCellValueFactory(u -> u.getValue().idProperty());
            colunaID.setPrefWidth(40);

            TableColumn<Usuario, String> colunaNome
                    = new TableColumn<>("Nome");
            colunaNome.setCellValueFactory(u
                    -> u.getValue().nomeProperty());
            colunaNome.setStyle("-fx-alignment: CENTER;");

            TableColumn<Usuario, String> colunaFone
                    = new TableColumn<>("Telefone");
            colunaFone.setCellValueFactory(u
                    -> u.getValue().foneProperty());

            TableColumn<Usuario, String> colunaLogin
                    = new TableColumn<>("Login");
            colunaLogin.setCellValueFactory(u
                    -> u.getValue().loginProperty());

            TableColumn<Usuario, String> colunaPerfil
                    = new TableColumn<>("Perfil");
            colunaPerfil.setCellValueFactory(u
                    -> u.getValue().perfilProperty());

            tabelaUsuarios.getColumns().addAll(colunaID,
                    colunaNome, colunaFone, colunaLogin,
                    colunaPerfil);

            tabelaUsuarios.setItems(lista);
        } else {
            AlertaUtil.mostrarErro("Erro", "Erro ao carregar usuários");
        }
    }

    //Método para buscar do banco de dados
    private ObservableList<Usuario> listarUsuarios() throws SQLException {
        UsuarioDAO dao = new UsuarioDAO();
        return dao.selecionarUsuarios();
    }

    @FXML
    void TableViewClick(MouseEvent event) throws IOException {
        if (event.getClickCount() == 1) {
            this.usuario = tabelaUsuarios.getSelectionModel().getSelectedItem();
            if (this.usuario != null) {
                URL url = new File("src/main/java/view/CadastroUsuarios.fxml").toURI().toURL();
                FXMLLoader loader = new FXMLLoader(url);
                Parent root = loader.load();

                Stage telaCadastroUsuarios = new Stage();

                CadastroUsuariosController cadc = loader.getController();

                cadc.setStage(telaCadastroUsuarios);

                telaCadastroUsuarios.setOnShown(evento -> {
                    cadc.ajustarElementosJanela(this.usuario);
                });

                Scene scene = new Scene(root);

                telaCadastroUsuarios.setTitle("Cadastro de Usuários");
                telaCadastroUsuarios.setScene(scene);
                telaCadastroUsuarios.show();
            }
        }
    }

}
