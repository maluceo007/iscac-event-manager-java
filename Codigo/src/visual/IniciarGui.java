package visual;
/**
 * Class utilizada para iniciar o programa
 */

import java.awt.EventQueue;
import java.awt.SystemColor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IniciarGui extends JFrame implements ActionListener {
	private JButton btnLogin;
	private static final long serialVersionUID = 6502358902169521065L;
	private JPanel contentPane;
	private JLabel lblLeonelFontes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IniciarGui frame = new IniciarGui();
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
	public IniciarGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 233, 185);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlDkShadow);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setResizable(false);		
		this.setLocationRelativeTo(null);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(this);
		btnLogin.setBounds(67, 54, 89, 23);
		contentPane.add(btnLogin);
		
		JLabel lblNewLabel = new JLabel("Samuel Anjos 13305 \r\n");
		lblNewLabel.setBounds(29, 11, 133, 14);
		contentPane.add(lblNewLabel);
		
		lblLeonelFontes = new JLabel("Leonel Fontes 11883");
		lblLeonelFontes.setBounds(29, 29, 127, 14);
		contentPane.add(lblLeonelFontes);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
	
		
		if ( source == btnLogin){
			// ******  fazer login
			LoginGui loginGui = new LoginGui();
			loginGui.setVisible(true);
			this.dispose(); 
			
			//new NovoEmpregadoGui();
		}
	}
}
