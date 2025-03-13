
package principal;

import java.sql.SQLException;

public class BancoDeDados {

    public static void main(String[] args) throws SQLException {
        ConexaoBD conexaoBD = new ConexaoBD();
        conexaoBD.conectarBanco();
    }
}
