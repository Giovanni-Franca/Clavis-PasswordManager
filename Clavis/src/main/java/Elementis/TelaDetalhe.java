package Elementis;

import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;

import Entitas.Senha;

public class TelaDetalhe extends JDialog{
	public TelaDetalhe(Senha s) {
		setTitle("Detalhes");
		setSize(300,200);
		setLocationRelativeTo(null);
		
		setLayout(new GridLayout(3,1));
		
		add(new JLabel("nome: " +s.getNome()));
		add(new JLabel("Senha: "+s.getSenha()));
		
		setVisible(true);
	}
}
