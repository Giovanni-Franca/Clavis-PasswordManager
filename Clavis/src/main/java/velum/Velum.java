package velum;

import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Elementis.SenhaCard;
import Entitas.Senha;
import javax.swing.border.EmptyBorder;

public class Velum extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Velum frame = new Velum();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	CardLayout cardLayout = new CardLayout();
    JPanel pPrincipal = new JPanel(cardLayout);
    private JPanel pLista;
	public Velum() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\eclipse-workspace\\Clavis\\src\\main\\resources\\chave.png"));
		setTitle("Gerenciador de senhas");
		setBounds(0, 0, 920, 480);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
	///////////////////////////////////////////////////////
	// Menu lateral para navegação
	///////////////////////////////////////////////////////

		JPanel menuLateral = new JPanel(new GridLayout(4, 0, 0, 15));
		menuLateral.setBorder(new EmptyBorder(10, 10, 0, 0));
		menuLateral.setBounds(0, 0, 120, 0);

		JButton btnHome = new JButton("Home");
        JButton btnNovaSenha = new JButton("Nova Senha");

        btnHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnNovaSenha.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        menuLateral.add(btnHome);
        menuLateral.add(btnNovaSenha);
     ///////////////////////////////////////////////////////
     // adiciona ação aos botões
     ///////////////////////////////////////////////////////
     /// 
        btnHome.addActionListener(e-> cardLayout.show(pPrincipal, "home"));
        btnNovaSenha.addActionListener(e -> cardLayout.show(pPrincipal, "nova senha"));
                
     ///////////////////////////////////////////////////////   
	 // criação das "telas" de acordo com os botões
     ///////////////////////////////////////////////////////
     /// 
        JPanel pHome = new JPanel();
        pHome.setBorder(new EmptyBorder(0, 10, 0, 0));
        JPanel pNovaSenha = new JPanel();
        
        pPrincipal.setBounds(118, 0, 676, 442);

        pPrincipal.add(pHome, "home");
        pPrincipal.add(pNovaSenha, "nova senha");
        
        ///////////////////////////////////////////////////////
        // Painel inicial que mostra as senhas
        ///////////////////////////////////////////////////////
        /// 
        pHome.setLayout(new BoxLayout(pHome, BoxLayout.Y_AXIS));
        
        pLista= new JPanel(new GridLayout(0, 3, 15, 15));
        pLista.setBorder(new EmptyBorder(10, 0, 0, 10));
        JScrollPane scrollPane = new JScrollPane(pLista);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(24);
        scrollPane.getVerticalScrollBar().setBlockIncrement(100);
        
        pHome.add(scrollPane);
        
        carregarSenhas();
        
        ///////////////////////////////////////////////////////
        // Painel para adição das senhas
        ///////////////////////////////////////////////////////

        GridBagLayout gbl_pNovaSenha = new GridBagLayout();
        pNovaSenha.setLayout(gbl_pNovaSenha);
        
        
        JLabel lbl_Nome = new JLabel("Nome");
        GridBagConstraints gbc_lbl_Nome = new GridBagConstraints();
        gbc_lbl_Nome.insets = new Insets(0, 0, 10, 5);
        gbc_lbl_Nome.gridx = 0;
        gbc_lbl_Nome.gridy = 1;
        pNovaSenha.add(lbl_Nome, gbc_lbl_Nome);
        
        JLabel lbl_Tamanho = new JLabel("Tamanho");
        GridBagConstraints gbc_lbl_Tamanho = new GridBagConstraints();
        gbc_lbl_Tamanho.insets = new Insets(0, 0, 10, 0);
        gbc_lbl_Tamanho.gridx = 3;
        gbc_lbl_Tamanho.gridy = 1;
        pNovaSenha.add(lbl_Tamanho, gbc_lbl_Tamanho);
        
        JTextField txt_nome = new JTextField();
        GridBagConstraints gbc_txt_nome = new GridBagConstraints();
        gbc_txt_nome.insets = new Insets(0, 0, 20, 5);
        gbc_txt_nome.fill = GridBagConstraints.HORIZONTAL;
        gbc_txt_nome.gridx = 0;
        gbc_txt_nome.gridy = 2;
        pNovaSenha.add(txt_nome, gbc_txt_nome);
        txt_nome.setPreferredSize(new Dimension(180,24));;
        
        JSpinner spinner = new JSpinner();
        spinner.setModel(new SpinnerNumberModel(8, 8, 255, 1));
        GridBagConstraints gbc_spinner = new GridBagConstraints();
        gbc_spinner.insets = new Insets(0, 0, 20, 0);
        gbc_spinner.gridx = 3;
        gbc_spinner.gridy = 2;
        pNovaSenha.add(spinner, gbc_spinner);
        spinner.setPreferredSize(new Dimension(65,24));
        
        JLabel lbl_Senha = new JLabel("Senha");
        GridBagConstraints gbc_lbl_Senha = new GridBagConstraints();
        gbc_lbl_Senha.insets = new Insets(0, 0, 10, 5);
        gbc_lbl_Senha.gridx = 1;
        gbc_lbl_Senha.gridy = 3;
        pNovaSenha.add(lbl_Senha, gbc_lbl_Senha);
        
        JTextField txt_senha = new JTextField();
        GridBagConstraints gbc_txt_senha = new GridBagConstraints();
        gbc_txt_senha.insets = new Insets(0, 0, 50, 5);
        gbc_txt_senha.fill = GridBagConstraints.HORIZONTAL;
        gbc_txt_senha.gridx = 1;
        gbc_txt_senha.gridy = 4;
        pNovaSenha.add(txt_senha, gbc_txt_senha);
        txt_senha.setPreferredSize(new Dimension(210,24));
        
        JButton btn_SenhaAleatoria = new JButton("");
        btn_SenhaAleatoria.setHorizontalAlignment(SwingConstants.LEFT);
        btn_SenhaAleatoria.setToolTipText("Gerar senha");
        btn_SenhaAleatoria.setIcon(new ImageIcon(getClass().getResource("/Iconsmind-Outline-Reload-2-2.16.png")));
        GridBagConstraints gbc_btn_SenhaAleatoria = new GridBagConstraints();
        gbc_btn_SenhaAleatoria.anchor = GridBagConstraints.NORTHWEST;
        gbc_btn_SenhaAleatoria.insets = new Insets(0, 0, 5, 5);
        gbc_btn_SenhaAleatoria.gridx = 2;
        gbc_btn_SenhaAleatoria.gridy = 4;
        pNovaSenha.add(btn_SenhaAleatoria, gbc_btn_SenhaAleatoria);
        btn_SenhaAleatoria.addActionListener(e -> txt_senha.setText(Munus.senhaAleatoria((int) spinner.getValue())));
        
        JLabel lbl_aviso = new JLabel("");
        GridBagConstraints gbc_lbl_aviso = new GridBagConstraints();
        gbc_lbl_aviso.insets = new Insets(0, 0, 5, 5);
        gbc_lbl_aviso.gridx = 1;
        gbc_lbl_aviso.gridy = 5;
        pNovaSenha.add(lbl_aviso, gbc_lbl_aviso);
        
        JButton btnSalvar = new JButton("Salvar Senha");
        btnSalvar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {        
        		Munus.inserirSenha( txt_nome.getText(), txt_senha.getText());
        		txt_senha.setText("");
        		txt_nome.setText("");
        		lbl_aviso.setText("Senha Salva!");
        		
        		carregarSenhas();
        	}
        });
        GridBagConstraints gbc_btnSalvar = new GridBagConstraints();
        gbc_btnSalvar.insets = new Insets(0, 0, 0, 5);
        gbc_btnSalvar.gridx = 1;
        gbc_btnSalvar.gridy = 6;
        pNovaSenha.add(btnSalvar, gbc_btnSalvar);
        
        ///////////////////////////////////////////////////////
        // Adiciona as telas ao aplicativo
        ///////////////////////////////////////////////////////

        getContentPane().add(menuLateral);
        getContentPane().add(pPrincipal);

        ///////////////////////////////////////////////////////
        // verifica se a senha possui caracteres recomendados e exibe uma mensagem
        ///////////////////////////////////////////////////////
         
        txt_senha.getDocument().addDocumentListener(new DocumentListener(){			
			@Override
			public void insertUpdate(DocumentEvent e) {		
				processar();
			}
			
			@Override
			public void removeUpdate(DocumentEvent e) {		
				processar();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				processar();
			}	
			private void processar() {
				String senha = txt_senha.getText();
				lbl_aviso.setText(Munus.validate(senha));
			}
		}
		);
        
        setVisible(true);
    }
	
	private void carregarSenhas() {
		if (pLista == null) {
	        System.out.println("pLista ainda é null");
	        return;
	    }
	    pLista.removeAll();

	    List<Senha> lista = Munus.listarNome();

	    for (Senha s : lista) {
	        pLista.add(new SenhaCard(s, this::carregarSenhas));
	    }

	    pLista.revalidate();
	    pLista.repaint();
	}

}
