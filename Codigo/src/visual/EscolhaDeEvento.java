package visual;

import java.awt.Window;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import classes.Cliente;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Class de interface gráfica para o Cliente decidir que tipoe de evento criar (Festa Pijama ou Festa Aniversário)
 * @author Sam
 *
 */

public class EscolhaDeEvento extends JFrame {

	private static final long serialVersionUID = 8900243383619617804L;
	private JPanel contentPane;
	private Cliente clienteUtilizador;
	
	/**
	 * Contrutor 
	 * @param utilizador - Cliente
	 */	
	public EscolhaDeEvento(Cliente utilizador){
		this.clienteUtilizador = utilizador;
		IniciarFrame();
	
	}

	/**
	 * Create the frame.
	 */
	public void IniciarFrame() {
		setTitle("Tipo de Eventos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 195, 178);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAniversario = new JButton("Festa Anivers\u00E1rio");
		btnAniversario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Cliente.criaNovaFestaAniversario(clienteUtilizador);  // criar Festa de aniversrio
			}
		});
		btnAniversario.setBounds(10, 11, 157, 23);
		contentPane.add(btnAniversario);
		
		JButton btnPijama = new JButton("Festa Pijama");
		btnPijama.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Cliente.criaNovaFestaPijama(clienteUtilizador);		// criar Festa de Pijama
			}
		});
		btnPijama.setBounds(10, 45, 157, 23);
		contentPane.add(btnPijama);
		
		final JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Window window = SwingUtilities.windowForComponent(btnSair);
				window.dispose();
			}
		});
		btnSair.setBounds(42, 88, 89, 23);
		contentPane.add(btnSair);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 76, 157, 1);
		contentPane.add(separator);
		this.setResizable(false);		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
