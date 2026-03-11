package velum;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Nexum {
	private static final String pastaBanco = System.getenv("LOCALAPPDATA");
	private static File pasta = new File(pastaBanco,"Clavis");
    private static final String URL = "jdbc:h2:" + pasta.getAbsolutePath() +  "./Clavis";
    private static final String USUARIO = "5B$4I09^5O#c)2jDT2¥p^i2g9Y8°b¥2zY62HG4|2?7}#S7^G755z2E°hU%2SrF/i";
    private static final String SENHA = ":sxP3nZd&KC}F{|xD*BDj1a[V}UAP^U60/#O]$bdDXbqg[29zM#¥ar5EOnV@z$jB";
    public static String sql;
    public static ResultSet rs;

    
    // conecta no banco e retorna a conexao
    public static Connection conectar() throws SQLException {
    	// cria past na APPDATA caso não exista
    	if (!pasta.exists()) {
    		pasta.mkdirs();
    	}
    	
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