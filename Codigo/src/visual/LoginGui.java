package visual;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import classes.Cliente;
import classes.Proprietario;
import classes.Utilizador;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;



// TODO: Auto-generated Javadoc
/**
 *  Class LoginGui é utilizada para a realização do login tanto pelo Cliento como pelo Proprietario.
 */
public class LoginGui extends JFrame implements ActionListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The login pane. */
	private JPanel loginPane;
	
	/** The text username. */
	private JTextField textUsername;
	
	/** The txt password. */
	private JPasswordField txtPassword;
	
	/** The btn registar. */
	private JButton btnEntrar, btnSair, btnRegistar;
	
	/** The propriatario. */
	private boolean propriatario;
	
	/** The conjunto clientes. */
	private static Map <Integer, Cliente> conjuntoClientes = new HashMap <Integer, Cliente>();
	
	/** The colaboradores. */
	private static Map <Integer, Proprietario> colaboradores = new HashMap <Integer, Proprietario>();
	
	/** The conjunto utilizadores. */
	private static Map <Integer, Object> conjuntoUtilizadores = new HashMap<Integer, Object>();
	
	/** The utilizador cliente. */
	private static Cliente utilizadorCliente;
	
	/** 
	 * construtor para a class Login
	 * login. 
	 */	
	public LoginGui() {
		
		iniciarFrame();
		
	}
	
	/**
	 * Iniciar frame.
	 */
	public void iniciarFrame (){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 375, 174);
		loginPane = new JPanel();
		loginPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(loginPane);
		loginPane.setLayout(null);
		this.setResizable(false);		
		this.setLocationRelativeTo(null);
		
		// ******** labels *****************
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(53, 11, 92, 23);
		loginPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(53, 45, 92, 23);
		loginPane.add(lblPassword);		
		
		// ******** text boxes**************
		textUsername = new JTextField();
		textUsername.setBounds(155, 11, 152, 22);
		loginPane.add(textUsername);
		textUsername.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(155, 45, 152, 22);
		loginPane.add(txtPassword);
		
		// ******** buttons *****************
		btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(this);
		btnEntrar.setBounds(140, 88, 89, 23);
		loginPane.add(btnEntrar);
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(this);
		btnSair.setBounds(260, 88, 89, 23);
		loginPane.add(btnSair);
		
		
		btnRegistar = new JButton("Registar");
		btnRegistar.addActionListener(this);
		btnRegistar.setBounds(10, 88, 89, 23);
		loginPane.add(btnRegistar);
		if(propriatario){ btnRegistar.setVisible(false);}
	}
	
	/**
	 * método para implementar as acções dos botões.
	 *
	 * @param e the e
	 */
	public void actionPerformed (ActionEvent e){
		
		Object source = e.getSource();
		
		// realizar login
		if (source == btnEntrar ){
			int resultado = loginUtilizador(textUsername.getText(), txtPassword.getText());
			
			switch (resultado)	{
				case 0: JOptionPane.showMessageDialog(null, "O Username não existe.", "ERRO-Username!", JOptionPane.ERROR_MESSAGE);
					break;
				case 1: JOptionPane.showMessageDialog(null, "A Password está incorreta.", "ERRO-Password!", JOptionPane.ERROR_MESSAGE);
					break;
				case 2: new MenuClienteGui(LoginGui.utilizadorCliente);		
						this.dispose();
						break;
				case 3: new MenuProprietarioGui();
						this.dispose();				default:
						break;
			}// fim switch		
		
			
		}// botão para um novo cliente se registar
		else if (source == btnRegistar){
			new ClienteGui();			
			this.dispose();
			
		}// sair
		else if (source == btnSair){
			System.exit(0);
			
		}
	}// fim action
	
	/**
	 * Login utilizador.
	 *
	 * @param utilizadorDigitado the utilizador digitado
	 * @param passwordDigitada the password digitada
	 * @return the integer
	 */
	public static Integer loginUtilizador (String utilizadorDigitado, String passwordDigitada){
		// 0 - utilizador não exite
		// 1 - palavavrapass errada
		// 2 - successo Cliente
		// 3 - successo Prorietario
		int flag = 0;
		
		
		deserializaClientes(); // carrega o  hashMap de clientes
		deserializaProprietario(); // carrega o hashMap de Proprietario
		
		// junta ambos os hashMaps em um só
		conjuntoUtilizadores.putAll(colaboradores);
		conjuntoUtilizadores.putAll(conjuntoClientes);
		
		// iterar pelos utilizadores para confirmar a validade do login
		Set <Integer>  set = conjuntoUtilizadores.keySet();
		Iterator <Integer> iterador = set.iterator();
		  
		while( iterador.hasNext()){
			Integer chave = (Integer) iterador.next();
			Object valores = (Object) conjuntoUtilizadores.get(chave);
			
			if (((Utilizador) valores).getUtilizador().equals(utilizadorDigitado) ){
				flag = 1;
				if (((Utilizador) valores).getPassword().equals(passwordDigitada)){
					// ver se a instancia do Ojbecto encontrado é Cliente
					if (valores instanceof Cliente){
							utilizadorCliente = (Cliente) valores;							
							return flag = 2;
					}
					// ver se a instancia do Ojbecto encontrado é Proprietario
					return  flag = 3;
				}
			}// fim if
		} // fim while
		return flag;
		
	}// fim login
	
	/**
	 * metodo para deserializar Clientes para um HashMap.
	 *
	 * @return HashMap - conjunto de Clientes
	 */
	public static Map<Integer, Cliente> deserializaClientes(){  
		try {
			conjuntoClientes = (Map<Integer, Cliente>) Utilidades.deserialize(Cliente.getFicheiro());
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  conjuntoClientes;
	}
	 
	/**
	 * metodo ara deserializar proprietario/empregados para um HashMap .
	 *
	 * @return HashMap - conjunt de proprietarios/empregados
	 */
	
	public static Map<Integer, Proprietario> deserializaProprietario(){  
		try {
			colaboradores = (Map<Integer, Proprietario>) Utilidades.deserialize(Proprietario.getFicheiro());
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return  colaboradores;
	}
		
	
	
}
