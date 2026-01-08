package velum;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;

public class aaaaaaaaaaaa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					aaaaaaaaaaaa frame = new aaaaaaaaaaaa();
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
    JPanel painelPrincipal = new JPanel(cardLayout);
    
	public aaaaaaaaaaaa() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 860, 505);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		
		setTitle("Navegação com CardLayout");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        // Painel da esquerda com botões
        JPanel menu = new JPanel(new GridLayout(5, 2, 0, 5));

        JButton btnHome = new JButton("Home");
        JButton btnProdutos = new JButton("Produtos");
        JButton btnConfig = new JButton("Configurações");

        menu.add(btnHome);
        menu.add(btnProdutos);
        menu.add(btnConfig);

        // Painéis de conteúdo
        JPanel pHome = new JPanel();
        pHome.add(new JLabel("Tela Home"));

        JPanel pProdutos = new JPanel();
        pProdutos.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        JLabel label = new JLabel("Tela de Produtos");
        pProdutos.add(label);

        JPanel pConfig = new JPanel();
        pConfig.add(new JLabel("Tela de Configurações"));

        painelPrincipal.add(pHome, "home");
        painelPrincipal.add(pProdutos, "produtos");
        painelPrincipal.add(pConfig, "config");

        // Ações dos botões
        btnHome.addActionListener(e -> cardLayout.show(painelPrincipal, "home"));
        btnProdutos.addActionListener(e -> cardLayout.show(painelPrincipal, "produtos"));
        btnConfig.addActionListener(e -> cardLayout.show(painelPrincipal, "config"));

        getContentPane().add(menu, BorderLayout.WEST);
        getContentPane().add(painelPrincipal, BorderLayout.CENTER);

        setVisible(true);
    }


	

}
