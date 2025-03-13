package principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexaoBD {
    private final String URL_MYSQL = 
            "jdbc:mysql://localhost:3306/escola";
    private final String DRIVER_CLASS = 
            "com.mysql.cj.jdbc.Driver";
    private final String USER = "root";
    private final String PASSWORD = "aluno";
    
    public void conectarBanco() throws SQLException{
        try {
            Class.forName(DRIVER_CLASS);
            Connection conexao = 
                    DriverManager.getConnection(
                            URL_MYSQL,USER, PASSWORD);
            if(conexao != null){
                System.out.println("Conexao com o BD estabelecida!");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver nao encontrado!");
        } catch (SQLException e){
            System.out.println("Erro na conexao com o BD");
        }
    }
}
