package Elementis;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Entitas.Senha;

public class SenhaCard extends JPanel{
	private Senha senha;
	
	public SenhaCard(Senha s) {
		this.senha = s;
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		add(new JLabel(s.getNome()), BorderLayout.NORTH);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				abrirDetalhes();
			}
		});
	}
	private void abrirDetalhes() {
		new TelaDetalhe(senha);
	}
}
