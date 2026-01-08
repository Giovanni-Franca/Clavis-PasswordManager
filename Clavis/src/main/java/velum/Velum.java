package velum;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class Velum {
	private JPanel contentPane;
	private JFrame frmSenhasFodas;
	private JTable table;
	private JTextField txtDesc;
	private JTextField txtSenha;
	private JLabel lbl_aviso;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Velum window = new Velum();
					window.frmSenhasFodas.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Velum() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	CardLayout cardLayout = new CardLayout();
	JPanel pPrincipal = new JPanel(cardLayout);
	
	private void initialize() {
		frmSenhasFodas = new JFrame();
		frmSenhasFodas.setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\eclipse-workspace\\Clavis\\src\\main\\resources\\chave.png"));
		frmSenhasFodas.setAutoRequestFocus(false);
		frmSenhasFodas.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		frmSenhasFodas.setTitle("Gerenciador de senhas");
		frmSenhasFodas.setBounds(0, 0, 880, 480);
		frmSenhasFodas.setLocationRelativeTo(null);
		frmSenhasFodas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSenhasFodas.getContentPane().setLayout(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		String[] colunas = {"ID", "Descricao", "Senha","Copiar","Excluir"};
		DefaultTableModel model = new DefaultTableModel(colunas, 0);

		// criação do menu lateral com botões
		JPanel menuLateral = new JPanel(new GridLayout(5, 1, 0, 5));
		menuLateral.setBounds(0, 0, 120, 442);

		JButton btnHome = new JButton("Home");
        JButton btnNovaSenha = new JButton("Nova Senha");

        menuLateral.add(btnHome);
        menuLateral.add(btnNovaSenha);
        frmSenhasFodas.getContentPane().add(menuLateral);
		
        // criação das "telas" de acordo com os botões
        JPanel pHome = new JPanel();
        pHome.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
                
        JPanel pNovaSenha = new JPanel();
        pPrincipal.setBounds(118, 0, 521, 442);
        
        pPrincipal.add(pHome, "home");
        pPrincipal.add(pNovaSenha, "nova senha");
        
        // adiciona ação aos botões
        btnHome.addActionListener(e-> cardLayout.show(pPrincipal, "home"));
        btnNovaSenha.addActionListener(e -> cardLayout.show(pPrincipal, "nova senha"));
        
        frmSenhasFodas.getContentPane().add(menuLateral, BorderLayout.WEST);
        frmSenhasFodas.getContentPane().add(pPrincipal,BorderLayout.CENTER);
        
        setVisible(true);
        
        /*
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(119, 0, 500, 442);
		scrollPane.setViewportBorder(null);
		frmSenhasFodas.getContentPane().add(scrollPane);
		table = new JTable(model);
		scrollPane.setViewportView(table);
		Nexum.carregarDados(table);
		*/
        
		
		
		/*
		// editores e criadores dos botoes da tabela
		class ButtonRenderer extends JButton implements TableCellRenderer {
		    public ButtonRenderer() {
		        setOpaque(true);
		    }

		    @Override
		    public Component getTableCellRendererComponent(JTable table, Object value,
		            boolean isSelected, boolean hasFocus, int row, int column) {
		        setText((value == null) ? "Botão" : value.toString());
		        return this;
		    }
		}
		
		class ButtonEditor extends DefaultCellEditor {
		    private JButton button;
		    private String label;
		    private boolean clicado;
		    private int row;

		    public ButtonEditor(JCheckBox checkBox, JTable table) {
		        super(checkBox);
		        button = new JButton();
		        button.setOpaque(true);

		        // ação ao clicar no botão
		        button.addActionListener(e -> {
		            fireEditingStopped(); // necessário para parar edição
		            String senha = table.getValueAt(row, 2).toString(); // coluna da senha
		            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(senha), null);
		            JOptionPane.showMessageDialog(null, "Senha copiada: " + senha);
		        });
		    }
		    
		    @Override
		    public Component getTableCellEditorComponent(JTable table, Object value,boolean isSelected, int row, int column) {
		        label = (value == null) ? "Botão" : value.toString();
		        button.setText(label);
		        this.row = row;
		        clicado = true;
		        return button;
		    }

		    @Override
		    public Object getCellEditorValue() {
		        clicado = false;
		        return label;
		    }
		}
		
		class ButtonExcludeEditor extends DefaultCellEditor{
	    	private JButton button;
	    	private JTable table;
	    	private int row;
	    	
  			public ButtonExcludeEditor(JCheckBox checkBox, JTable table) {
		    	super(checkBox);
		    	this.table = table;
		    	
		    	button = new JButton("excluir");
		    	button.setOpaque(true);
		    	
		    	button.addActionListener(e ->{
		    		fireEditingStopped();
		    		int id = (int) table.getValueAt(row,0);
		    		String desc = table.getValueAt(row,1).toString();
		    		String mensagem = String.format("Tem certeza que deseja excluir a senha de '%s'?",desc);
		    		int result = JOptionPane.showConfirmDialog(null,mensagem,"Teste",
										    				   JOptionPane.YES_NO_OPTION,
										    				   JOptionPane.QUESTION_MESSAGE);
		    		if (result == JOptionPane.YES_OPTION) {
		    			Munus.excluirSenha(id);
		    			Nexum.carregarDados(table);	    			
		    		}
		    	});
		    }	
  			 @Override
  		    public Component getTableCellEditorComponent(JTable table, Object value,
  		            boolean isSelected, int row, int column) {
  		        this.row = row;
  		        return button;
  		    }

  		    @Override
  		    public Object getCellEditorValue() {
  		        return "Excluir";
  		    }
	    }
		
		//botoes da tabela
		table.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
		table.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(new JCheckBox(), table));
		table.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
		table.getColumnModel().getColumn(4).setCellEditor(new ButtonExcludeEditor(new JCheckBox(), table));		
		
		*/
		
		txtSenha.getDocument().addDocumentListener(new DocumentListener(){			
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
				String senha = txtSenha.getText();
				lbl_aviso.setText(Munus.validate(senha));
			}
		}
		);
		
	}

	private void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}

	private void setContentPane(JPanel contentPane2) {
		// TODO Auto-generated method stub
		
	}

	private Container getContentPane() {
		// TODO Auto-generated method stub
		return null;
	}
}
