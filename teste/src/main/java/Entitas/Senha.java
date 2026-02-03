package Entitas;

public class Senha {
	private int ID;
	private String Nome;
	private String Senha; 
	
	public Senha(int iD, String nome, String senha) {
		super();
		ID = iD;
		Nome = nome;
		Senha = senha;
	}	
	
	public Senha() {
		super();
	}

	public Senha(int iD, String nome) {
		super();
		ID = iD;
		Nome = nome;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getSenha() {
		return Senha;
	}

	public void setSenha(String senha) {
		Senha = senha;
	}

	
	
	
	
}
