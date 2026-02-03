package Elementis;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Entitas.Senha;
import velum.Munus;

public class TelaDetalhe extends JDialog{
	private Runnable atualizarCallback;
	
	public TelaDetalhe(Senha s, Runnable atualizarCallback) {
		this.atualizarCallback = atualizarCallback;
		
		setTitle("Detalhes");
		setSize(300,200);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(0,1));
		add(new JLabel("nome: " +s.getNome()));
		add(new JLabel("Senha: "+s.getSenha()));
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null,"Quer mesmo exlcuir esta senha?", 
						"Excluir", 
						JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE);

				if (result == JOptionPane.YES_OPTION) {
					Munus.excluirSenha(s.getID());
					atualizarCallback.run();
					dispose();
				}
			
			}
		});
		
		JButton btnCopiar = new JButton("Copiar");
		btnCopiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringSelection senha = new StringSelection(s.getSenha());
				Toolkit.getDefaultToolkit()
				.getSystemClipboard()
				.setContents(senha, null);;
			}
		});
		add(btnExcluir);
		add(btnCopiar);
		setVisible(true);
	}
}
