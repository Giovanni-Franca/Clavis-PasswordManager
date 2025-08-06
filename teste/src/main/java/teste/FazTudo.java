package teste;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FazTudo {
    private static final String URL = "jdbc:h2:./test";
    private static final String USUARIO = "sa";
    private static final String SENHA = "";
    public static String sql;
    public static ResultSet rs;

    public static Connection conectar() throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
        sql = "create table if not exists contas "
        		+ "(ID IDENTITY PRIMARY KEY, "
        		+ "Descricao VARCHAR(255) NOT NULL,"
        		+ "Senha VARCHAR(255) NOT NULL);";
        Statement stmt = conn.createStatement();
        stmt.execute(sql);
        return conn;
    }
    
    public static JTable carregarDados(JTable table) {
    	DefaultTableModel model = (DefaultTableModel) table.getModel();
    	model.setRowCount(0);
    	try (Connection conn = conectar()) {
    		sql = "select * from contas";
    		Statement stmt = conn.createStatement();
    		rs = stmt.executeQuery(sql);
    		while (rs.next()) {
    			int id = rs.getInt("ID");
    			String desc = rs.getString("Descricao");
    			String senha = rs.getString("Senha");
    			model.addRow(new Object[] {id,desc,senha,"","a"});
    		}
    	} catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao acessar banco: " + e.getMessage());
        }
		return table;
    }

    public static void inserirSenha(String desc, String senha) {
    	String sql = "insert into contas (Descricao,Senha) values (?,?)";
    	try (Connection conn = conectar();
    		PreparedStatement stmt = conn.prepareStatement(sql)){
    		 	stmt.setString(1, desc);
    	        stmt.setString(2, senha);
    	        stmt.executeUpdate();
    	}catch (SQLException e){
    		System.err.println("❌ Erro ao inserir conta: " + e.getMessage());
    	}
    }
    
    public static String senhaAleatoria(int numCaracter) {
	 	SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder();
		StringBuilder senha = new StringBuilder();
		String simbolo = "!@#$%&*()/?;:|~£€¥^°{}[]";
		char letra;

		for (int i = 0; i<numCaracter; i++) {
			sb.append(random.nextInt(4) + 1);
		}
		String b = sb.toString();		
		int len = b.length();
		
		for (int i = 0; i < len; i++) {
			switch (b.charAt(i)) {
				case '1': 
					letra = (char) ('A' + random.nextInt(26));
					senha.append(letra);
					break;
				case '2':
					letra = (char) ('a' + random.nextInt(26));
					senha.append(letra);
					break;
				case '3':
					senha.append(random.nextInt(10));
					break;
				case '4':
					senha.append(simbolo.charAt(random.nextInt(simbolo.length())));
					break;
			}
		}
		return senha.toString();
	 }
}
