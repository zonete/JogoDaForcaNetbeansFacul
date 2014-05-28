/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author DaviZonete
 */
public class JdbcDao {

    private Connection connection;
    protected Statement statement;

    protected void abrirConexao() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class.forName("org.gjt.mm.mysql.Driver").newInstance();
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jogodaforca", "root", "");
        statement = connection.createStatement();
    }

    protected void fecharConexao() throws SQLException {
        statement.close();
        connection.close();
    }

    public JdbcDao() throws SQLException {
        try {
            abrirConexao();
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        fecharConexao();
    }
}
