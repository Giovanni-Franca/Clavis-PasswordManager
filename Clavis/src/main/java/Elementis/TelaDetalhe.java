package Elementis;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

import Entitas.Senha;
import velum.Munus;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class TelaDetalhe extends JDialog{
	private Runnable atualizarCallback;
	
	public TelaDetalhe(Senha s, Runnable atualizarCallback) {
		this.atualizarCallback = atualizarCallback;
		
		setTitle("Detalhes");
		setSize(500,200);
		setLocationRelativeTo(null);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowHeights = new int[]{33, 31, 0, 0};
		gridBagLayout.columnWidths = new int[]{197};
		getContentPane().setLayout(gridBagLayout);
		
		GridBagConstraints gbc_nome = new GridBagConstraints();
		gbc_nome.fill = GridBagConstraints.BOTH;
		gbc_nome.insets = new Insets(0, 0, 5, 0);
		gbc_nome.gridx = 0;
		gbc_nome.gridy = 0;
		JLabel label_1 = new JLabel("nome: " +s.getNome());
		getContentPane().add(label_1, gbc_nome);
		
		GridBagConstraints gbc_senha = new GridBagConstraints();
		gbc_senha.fill = GridBagConstraints.BOTH;
		gbc_senha.insets = new Insets(0, 0, 5, 0);
		gbc_senha.gridx = 0;
		gbc_senha.gridy = 1;
		JLabel label = new JLabel("Senha: "+s.getSenha());
		getContentPane().add(label, gbc_senha);
		
		GridBagConstraints gbc_btnExcluir = new GridBagConstraints();
		gbc_btnExcluir.fill = GridBagConstraints.VERTICAL;
		gbc_btnExcluir.insets = new Insets(5, 0, 0, 0);
		gbc_btnExcluir.gridx = 0;
		gbc_btnExcluir.gridy = 3;
		
		
		GridBagConstraints gbc_btnCopiar = new GridBagConstraints();
		gbc_btnCopiar.fill = GridBagConstraints.VERTICAL;
		gbc_btnCopiar.gridx = 0;
		gbc_btnCopiar.gridy = 2;
		
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
				.setContents(senha, null);
				
				dispose();
				JOptionPane.showMessageDialog(rootPane, "Senha copiada para área de transferencia.");
			}
		});
		
		getContentPane().add(btnCopiar, gbc_btnCopiar);
		getContentPane().add(btnExcluir, gbc_btnExcluir);
		
		setVisible(true);
	}
}
