package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Entitas.Senha;
import velum.Nexum;

public class SenhaDAO {
	
	public static List<Senha> listarNome() {
		List<Senha> lista = new ArrayList<>();
		
		String sql = "SELECT ID, Nome FROM contas order by ID";
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
	
	public Senha listarSenha(int id){
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
	
}
