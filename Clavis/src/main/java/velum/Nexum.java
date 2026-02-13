package velum;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Nexum {
    private static final String URL = "jdbc:h2:./Clavis";
    private static final String USUARIO = "sa";
    private static final String SENHA = "";
    public static String sql;
    public static ResultSet rs;

    
    // conecta no banco e retorna a conexao
    public static Connection conectar() throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
        sql = "create table if not exists contas "
        		+ "(ID IDENTITY PRIMARY KEY, "
        		+ "Nome VARCHAR(255) NOT NULL,"
        		+ "Senha VARCHAR(255) NOT NULL);";
        Statement stmt = conn.createStatement();
        stmt.execute(sql);
        return conn;
    }
    
}
