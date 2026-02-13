package velum;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Elementis.SenhaCard;
import Entitas.Senha;

public class Munus {
	
	///////////////////////////////////////////////////////
	// Envia a senha e dados ao banco
	///////////////////////////////////////////////////////
	public static void inserirSenha(String desc, String senha) {
    	String sql = "insert into contas (Nome,Senha) values (?,?)";
    	if(senha == null || senha.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Senha vazia!","AVISO",JOptionPane.WARNING_MESSAGE);
			return;
    	}else if(desc == null || desc.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Descrição vazia!","AVISO",JOptionPane.WARNING_MESSAGE);
			return;
    	}
    	try (Connection conn = Nexum.conectar();		
    		PreparedStatement stmt = conn.prepareStatement(sql)){
    		 	stmt.setString(1, desc);
    	        stmt.setString(2, senha);
    	        stmt.executeUpdate();
    	}catch (SQLException e){
    		System.err.println("Erro ao inserir senha: " + e.getMessage());
    	}
    }
	
	///////////////////////////////////////////////////////
	// Lista todas as senhas
	///////////////////////////////////////////////////////
	public static List<Senha> listarNome() {
		List<Senha> lista = new ArrayList<>();
		String sql = "SELECT ID, Nome FROM contas order by ID desc";
		try (Connection conn = Nexum.conectar();
			 PreparedStatement ps = conn.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery()){
				
				while (rs.next()) {
					Senha s = new Senha();
					s.setID(rs.getInt("ID"));
					s.setNome(rs.getString("Nome"));
					lista.add(s);
				}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;	
	}
	
	///////////////////////////////////////////////////////
	// Busca a senha por ID
	///////////////////////////////////////////////////////
 	public static Senha listarSenha(int id){
		String sql = "SELECT * from contas where id = ?";
		try (Connection conn = Nexum.conectar();
			 PreparedStatement ps = conn.prepareStatement(sql)){
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()) {
					Senha s = new Senha();
					s.setID(id);
					s.setNome(rs.getString("Nome"));
					s.setSenha(rs.getString("Senha"));
					return s;
				}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	///////////////////////////////////////////////////////
	// exclui senha/objeto do banco
	///////////////////////////////////////////////////////
    public static void excluirSenha(int id) {
    	String sql = "delete from contas where ID = ?";
    	try (Connection conn = Nexum.conectar();
    			PreparedStatement stmt = conn.prepareStatement(sql)){
    				stmt.setInt(1, id);
    				stmt.executeUpdate();
    	}catch (SQLException e) {
    		System.err.println("Erro ao apagar dados: " + e.getMessage());
    	}
    }
    
	///////////////////////////////////////////////////////
    // Gera uma senha
	///////////////////////////////////////////////////////
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
    
	///////////////////////////////////////////////////////
    // Verifica se uma senha possui diferentes caracteres 
	///////////////////////////////////////////////////////
	public static String validate(String senha) {
		final Pattern letras = Pattern.compile(".*[a-zA-Z].*");
		final Pattern num = Pattern.compile(".*\\d.*");
		final Pattern simbolos = Pattern.compile(".*[^a-zA-Z0-9].*");
		
		if(!letras.matcher(senha).matches()) {
			return "Insira Letras na Senha.";
		}else if(!num.matcher(senha).matches()) {
			return "Insira Números na Senha";
		}else if(!simbolos.matcher(senha).matches()) {
			return "Insira simbolos na Senha";
		}
		return "";
	}
}
