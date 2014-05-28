/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socketThreadsAtivosTCP;

import conexao.JdbcDao;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author DaviZonete
 */
public class PalavrasBanco extends JdbcDao {

    public String Dica;
    public String Palavra;

    public String getDica() {
        return Dica;
    }

    public void setDica(String Dica) {
        this.Dica = Dica;
    }

    public String getPalavra() {
        return Palavra;
    }

    public void setPalavra(String Palavra) {
        this.Palavra = Palavra;
    }

    public void PegarAleatoria() throws SQLException {
        ResultSet result = statement.executeQuery("SELECT palavra,dica FROM palavras ORDER BY RAND() LIMIT 1");
        result.next();
        this.setPalavra(result.getString("palavra").toUpperCase());
        this.setDica(result.getString("dica").toUpperCase());

    }

    public PalavrasBanco() throws SQLException {
        super();
    }

   
    
}
