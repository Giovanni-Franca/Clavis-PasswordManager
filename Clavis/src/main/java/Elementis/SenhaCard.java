package Elementis;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Entitas.Senha;
import velum.Munus;

public class SenhaCard extends JPanel{
	private Senha senha;
	private Runnable atualizarCallback;
	
	public SenhaCard(Senha s, Runnable atualizarCallback) {
		this.senha = s;
		this.atualizarCallback = atualizarCallback;
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createLineBorder(Color.GRAY));
		setBackground(new Color(255,255,255));
		setPreferredSize(new Dimension(0,120));
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JLabel nome = new JLabel(s.getNome());
		nome.setHorizontalAlignment(SwingConstants.CENTER);
		nome.setVerticalAlignment(SwingConstants.CENTER);
		add(nome);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				abrirDetalhes();
			}
		});
		
	}
	
	private void abrirDetalhes() {
		senha = Munus.listarSenha(senha.getID());
		new TelaDetalhe(senha, atualizarCallback);
	}
}
