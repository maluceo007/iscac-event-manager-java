package visual;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import classes.Proprietario;

/**
 * Parte grafica de segurança para solução de possiveis erros
 *
 */
public class NovoEmpregadoGui extends JFrame  implements ActionListener{

	private static final long serialVersionUID = -682060586400972750L;
	private JPanel proprietariotPane;
	private JTextField textUsername;
	private JTextField textPassword;
	private JTextField textApelido;
	private JTextField textNome;
	private JButton btnRegistar,btnSair;
	private JLabel lblPassword;
	private Proprietario novoEmpregado;
	private static Map <Integer, Proprietario> colaboradores = new HashMap<Integer, Proprietario> ();

	/**
	 * Create the frame.
	 */
	public NovoEmpregadoGui() {
		setTitle("Propriet\u00E1rio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 312, 236);
		proprietariotPane = new JPanel();
		proprietariotPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(proprietariotPane);
		proprietariotPane.setLayout(null);
		this.setVisible(true);
		
		textUsername = new JTextField();
		textUsername.setBounds(129, 76, 158, 20);
		proprietariotPane.add(textUsername);
		textUsername.setColumns(10);
		
		textPassword = new JTextField();
		textPassword.setBounds(129, 107, 158, 20);
		proprietariotPane.add(textPassword);
		textPassword.setColumns(10);		
		
		textApelido = new JTextField();
		textApelido.setBounds(129, 45, 158, 20);
		proprietariotPane.add(textApelido);
		textApelido.setColumns(10);
		
		textNome = new JTextField();
		textNome.setBounds(129, 11, 158, 20);
		proprietariotPane.add(textNome);
		textNome.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 14, 75, 14);
		proprietariotPane.add(lblNome);
		
		JLabel lblApelido = new JLabel("Apelido:");
		lblApelido.setBounds(10, 48, 75, 14);
		proprietariotPane.add(lblApelido);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(10, 79, 75, 14);
		proprietariotPane.add(lblUsername);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setBounds(10, 110, 75, 14);
		proprietariotPane.add(lblPassword);
		
		
		btnRegistar = new JButton("Registar");
		btnRegistar.addActionListener(this);
		btnRegistar.setBounds(10, 156, 89, 23);
		proprietariotPane.add(btnRegistar);
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(this);
		btnSair.setBounds(198, 156, 89, 23);
		proprietariotPane.add(btnSair);
		
	}
	
	public void actionPerformed (ActionEvent e){
		
		Object source = e.getSource();
		
		// Registar novo Proprietario
		if (source == btnRegistar){
			novoEmpregado();
			
			
		}else if (source == btnSair){
			this.dispose();
		}
	}
	
	public void novoEmpregado(){
	
		novoEmpregado = new Proprietario(textNome.getText(), 
											textApelido.getText(), 
											textUsername.getText(), 
											textPassword.getText());
		
		if (validateTextBoxes()){
			//desirializar o ficheiro para adicionar novo cliente
			/*
			if (new File(Proprietario.getFicheiro()).exists()){
			  		try {
			  			
						colaboradores = (HashMap) new Utilidades().deserialize(Proprietario.getFicheiro());
					} catch (ClassNotFoundException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}*/
			//Integer idCliente = novoCliente.getClienteID();				
			colaboradores.put(1,novoEmpregado);
			
			// guardar proprietario no ficheiro
			try {
				new Utilidades().serialize(colaboradores, Proprietario.getFicheiro());
			} catch (IOException e1) {
				System.out.print("Problemas a gravar ficeiro:");
				e1.printStackTrace();
			}
			System.out.println (colaboradores);
		} else
			JOptionPane.showMessageDialog(null, "Preencher todos os campos.", "ERRO-Campos vazios!", JOptionPane.ERROR_MESSAGE);
			
		
	}
	
	public boolean validateTextBoxes (){
		if (textNome.getText().length() == 0) { return false;}
		if (textApelido.getText().length() == 0) { return false;}
		if (textUsername.getText().length() == 0) { return false;}
		if (textUsername.getText().length() == 0) { return false;}
		
		return true;
	}
}
