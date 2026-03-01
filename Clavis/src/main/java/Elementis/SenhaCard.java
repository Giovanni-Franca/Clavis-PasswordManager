package Elementis;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		
	///////////////////////////////////////////////////////
	// Define as caracteristicas do card e cria seus elementos
	///////////////////////////////////////////////////////
		setLayout(new BorderLayout());
		setBackground(new Color(255,255,255));
		setPreferredSize(new Dimension(0,120));
		setOpaque(false);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JLabel nome = new JLabel(s.getNome());
		nome.setHorizontalAlignment(SwingConstants.CENTER);
		nome.setVerticalAlignment(SwingConstants.CENTER);
		nome.setFont(nome.getFont().deriveFont(Font.BOLD,14f));
		add(nome);
			
	///////////////////////////////////////////////////////
	// Adiciona ação ao clicar no card, além de efeito de hover
	///////////////////////////////////////////////////////
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				abrirDetalhes();
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(Color.getColor("#D3D3D3"));
				repaint();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				setBackground(Color.white);
				repaint();
			}
		});
		
	}
	///////////////////////////////////////////////////////
	// Abre a tela com a senha em si
	///////////////////////////////////////////////////////
	private void abrirDetalhes() {
		senha = Munus.listarSenha(senha.getID());
		new TelaDetalhe(senha, atualizarCallback);
	}
	
	///////////////////////////////////////////////////////
	// Gera melhor interface ao card
	///////////////////////////////////////////////////////
	@Override
	protected void paintComponent(Graphics g) {
	    Graphics2D g2 = (Graphics2D) g.create();
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                         RenderingHints.VALUE_ANTIALIAS_ON);

	    int arc = 16;

	    // fundo
	    g2.setColor(getBackground());
	    g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);

	    // borda
	    g2.setColor(Color.GRAY);
	    g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);

	    g2.dispose();
	}
}
