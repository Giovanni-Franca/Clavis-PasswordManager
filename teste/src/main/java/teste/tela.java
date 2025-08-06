package teste;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class tela {

	private JFrame frmSenhasFodas;
	private JTable table;
	private JTextField txtDesc;
	private JTextField txtSenha;
	private JLabel lblAA;
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
					tela window = new tela();
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
	public tela() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSenhasFodas = new JFrame();
		frmSenhasFodas.setAutoRequestFocus(false);
		frmSenhasFodas.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		frmSenhasFodas.setTitle("Senhas Fodas");
		frmSenhasFodas.setBounds(560, 400, 720, 480);
		frmSenhasFodas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		String[] colunas = {"ID", "Descricao", "Senha","Acao","Acao2"};
		DefaultTableModel model = new DefaultTableModel(colunas, 0);
				
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 500, 442);
		scrollPane.setViewportBorder(null);
		frmSenhasFodas.getContentPane().add(scrollPane);
		table = new JTable(model);
		scrollPane.setViewportView(table);
		FazTudo.carregarDados(table);
		
		
		
		txtDesc = new JTextField();
		txtDesc.setBounds(510, 60, 103, 20);
		frmSenhasFodas.getContentPane().add(txtDesc);
		txtDesc.setColumns(10);
		
		txtSenha = new JTextField();
		txtSenha.setBounds(510, 137, 167, 20);
		txtSenha.setColumns(10);
		frmSenhasFodas.getContentPane().add(txtSenha);
		
		lblAA = new JLabel("");
		lblAA.setBounds(563, 375, 114, 40);
		frmSenhasFodas.getContentPane().add(lblAA);
		
		lblNewLabel = new JLabel("Descrição");
		lblNewLabel.setBounds(510, 35, 71, 14);
		frmSenhasFodas.getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Senha");
		lblNewLabel_1.setBounds(510, 112, 46, 14);
		frmSenhasFodas.getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Tamanho Senha");
		lblNewLabel_2.setBounds(601, 35, 114, 14);
		frmSenhasFodas.getContentPane().add(lblNewLabel_2);
		
		JSpinner spnCaracteres = new JSpinner();
		spnCaracteres.setModel(new SpinnerNumberModel(8, 8, 255, 1));
		spnCaracteres.setBounds(623, 60, 71, 20);
		frmSenhasFodas.getContentPane().add(spnCaracteres);

		JButton btnNewButton = new JButton("");
		btnNewButton.setBounds(668, 137, 21, 20);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int tamanha = (int) spnCaracteres.getValue();
				txtSenha.setText(FazTudo.senhaAleatoria(tamanha));
			}
		});

		frmSenhasFodas.getContentPane().setLayout(null);
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Giovanni\\Desktop\\Iconsmind-Outline-Reload-2-2.16.png"));
		frmSenhasFodas.getContentPane().add(btnNewButton);
		
		JButton BtnSalvar = new JButton("Salvar Senha");
		BtnSalvar.setBounds(529, 186, 148, 52);
		BtnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FazTudo.inserirSenha(txtDesc.getText(), txtSenha.getText());
				FazTudo.carregarDados(table);
			}
		});
		frmSenhasFodas.getContentPane().add(BtnSalvar);
		
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
		    
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*
 * public ButtonExclude(JCheckBox checkBox, JTable table) {
		    	super(checkBox);
		    	button = new JButton();
		    	button.setOpaque(true);
		    	
		    	button.addActionListener(e ->){
		    		fireEditingStoped();
		    		
		    	}
		    }
 */
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
		table.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
		table.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(new JCheckBox(), table));
		table.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
		table.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JCheckBox(), table));
		
		txtSenha.getDocument().addDocumentListener(new DocumentListener(){
			@Override
			public void insertUpdate(DocumentEvent e) {
				String senha = txtSenha.getText();
				lblAA.setText(senha);
			}
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				String senha = txtSenha.getText();
				lblAA.setText(senha);
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				String senha = txtSenha.getText();
				lblAA.setText(senha);			
			}			
		}
		// Fazer um verificador se a senha tem todos os tipos de caracteres
		);
	}
}
