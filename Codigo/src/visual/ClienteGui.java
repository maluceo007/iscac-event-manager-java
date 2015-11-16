package visual;

import classes.*;

import java.awt.event.*;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/**
 * Class de interface grafica para o Proprietario, e Cliente inserir/atualizar os dados para um novo Cliente. * 
 *
 */
public class ClienteGui extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel clientePane;
	private JTextField textNome,textApelido,textEmail,textContacto,
						textMorada,textCodigoPostal,textNib,textUtilizador,
						textPassword;
	
	private JButton btnGravar, btnSair, btnCriaLogin;
	private Cliente novoCliente, clienteParaAtualizar;
	private static Map <Integer, Cliente> conjuntoClientes = new HashMap<Integer, Cliente> ();	
	private static boolean eNovoCliente = true;  // flag utilizda para detetar quando o Jframe esta a realizar
												 // uma atualização ao cliente. Fazendo assim que o JButton gravar tenha
												 // um comportamento diferente
	
	/**
	 * Create the frame quando temos um cliente novo para incerir
	 */
	public ClienteGui() {
		 
		iniciarFrame();
		
	}
	/**
	 * Construtor para carregar o cliente para ser atualizado
	 * @param clienteParaAtualizar
	 */
	public ClienteGui(Cliente clienteParaAtualizar, Map <Integer, Cliente> conjuntoClientes){
		iniciarFrame();
		this.textNome.setText(clienteParaAtualizar.getNome());
		this.textApelido.setText(clienteParaAtualizar.getApelido());
		this.textEmail.setText(clienteParaAtualizar.getEmail());
		this.textContacto.setText(Integer.toString(clienteParaAtualizar.getContacto()));
		this.textCodigoPostal.setText(clienteParaAtualizar.getCodigoPostal());
		this.textNib.setText(Integer.toString(clienteParaAtualizar.getNib()));
		this.textUtilizador.setText(clienteParaAtualizar.getUtilizador());
		this.textPassword.setText(clienteParaAtualizar.getPassword());
		btnCriaLogin.setVisible(false);
		eNovoCliente = false;
		this.clienteParaAtualizar = clienteParaAtualizar;
		ClienteGui.conjuntoClientes = conjuntoClientes;
	}
	 
	/**
	 * Iniciar Frame
	 */
	public void iniciarFrame(){
		
		setTitle("Novo Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 610, 351);
		clientePane = new JPanel();
		clientePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(clientePane);
		clientePane.setLayout(null);
		this.setResizable(false);		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		JLabel lblPrimeiroNome = new JLabel("Nome:*");
		lblPrimeiroNome.setBounds(37, 24, 99, 20);
		clientePane.add(lblPrimeiroNome);
		
		JLabel lblNewLabel = new JLabel("Apelido:*");
		lblNewLabel.setBounds(314, 24, 99, 20);
		clientePane.add(lblNewLabel);
		
		textNome = new JTextField();
		textNome.setBounds(146, 24, 144, 20);
		clientePane.add(textNome);
		textNome.setColumns(10);
		
		textApelido = new JTextField();
		textApelido.setBounds(423, 23, 144, 20);
		clientePane.add(textApelido);
		textApelido.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail:*");
		lblEmail.setMinimumSize(new Dimension(31, 14));
		lblEmail.setMaximumSize(new Dimension(31, 14));
		lblEmail.setBounds(37, 59, 99, 20);
		clientePane.add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setBounds(146, 56, 227, 20);
		clientePane.add(textEmail);
		textEmail.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Num Telefone:");
		lblTelefone.setBounds(314, 89, 99, 20);
		clientePane.add(lblTelefone);
		
		textContacto = new JTextField();
		textContacto.setBounds(423, 89, 144, 20);
		clientePane.add(textContacto);
		textContacto.setColumns(10);
		
		JLabel lblMorada = new JLabel("Morada:");
		lblMorada.setBounds(37, 122, 99, 20);
		clientePane.add(lblMorada);
		
		textMorada = new JTextField();
		textMorada.setBounds(146, 119, 227, 20);
		clientePane.add(textMorada);
		textMorada.setColumns(10);
		
		JLabel lblCodigoPostal = new JLabel("Codigo Postal:");
		lblCodigoPostal.setBounds(37, 148, 99, 20);
		clientePane.add(lblCodigoPostal);
		
		textCodigoPostal = new JTextField();
		textCodigoPostal.setBounds(146, 148, 144, 20);
		clientePane.add(textCodigoPostal);
		textCodigoPostal.setColumns(10);
		
		JLabel lblNif = new JLabel("NIF:*");
		lblNif.setPreferredSize(new Dimension(31, 14));
		lblNif.setMinimumSize(new Dimension(31, 14));
		lblNif.setMaximumSize(new Dimension(31, 14));
		lblNif.setBounds(37, 89, 99, 20);
		clientePane.add(lblNif);
		
		textNib = new JTextField();
		textNib.setBounds(146, 88, 144, 20);
		clientePane.add(textNib);
		textNib.setColumns(10);
		
		JLabel lblPassword = new JLabel("Utilizador:*");
		lblPassword.setBounds(37, 179, 99, 20);
		clientePane.add(lblPassword);
		
		textUtilizador = new JTextField();
		textUtilizador.setBounds(146, 179, 144, 20);
		clientePane.add(textUtilizador);
		textUtilizador.setColumns(10);
		
		JLabel lblUtilizador = new JLabel("Password:*");
		lblUtilizador.setBounds(37, 209, 99, 20);
		clientePane.add(lblUtilizador);
		
		textPassword = new JTextField();
		textPassword.setBounds(146, 208, 144, 20);
		clientePane.add(textPassword);
		textPassword.setColumns(10);
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(this);
		btnSair.setBounds(478, 261, 89, 23);
		clientePane.add(btnSair);
		
		btnGravar = new JButton("Gravar");
		btnGravar.addActionListener(this);
		btnGravar.setBounds(284, 261, 89, 23);
		clientePane.add(btnGravar);
		
		btnCriaLogin = new JButton("Gerar Chaves");
		btnCriaLogin.addActionListener(this);
		btnCriaLogin.setBounds(37, 261, 112, 23);
		clientePane.add(btnCriaLogin);
		
		JLabel lblNewLabel_1 = new JLabel("* Preenchimento obrigat\u00F3rio");
		lblNewLabel_1.setBounds(37, 295, 195, 14);
		clientePane.add(lblNewLabel_1);
		
	}// end frame
	
	/**
	 * método para implementar as acções dos botões
	 */
	public void actionPerformed (ActionEvent e){
		String user;
		Object source = e.getSource();
		
		// gravar
		if (source == btnGravar ){
			// comfirmar se não existem campos obrigatórios vazios
			if (campoVazios()){ 
				JOptionPane.showMessageDialog(null, "Preencher todos os campos marcados com asteriscos *.", "ERRO-Campos vazios!", JOptionPane.ERROR_MESSAGE);
				return;
			}
			// verificar se o nif é  integers			
			try { 
		        Integer.parseInt(textNib.getText()); 
		    } catch(NumberFormatException ae) { 
		    	JOptionPane.showMessageDialog(null, " O NIF tem que ser um numero.", "ERRO-NIF!", JOptionPane.ERROR_MESSAGE);
				return;
		    }		
			
			// verificar se o nib tem 9 digitos
			if (textNib.getText().length()!=9){
				JOptionPane.showMessageDialog(null, " O NIF não tem 9 digitos.", "ERRO-NIF!", JOptionPane.ERROR_MESSAGE);
				return;
			}
			// validar email
			if (!validoEmail(textEmail.getText())){
				JOptionPane.showMessageDialog(null, " O email não é valido.", "ERRO-Email!", JOptionPane.ERROR_MESSAGE);
				return;
			}	
			
			// verificar se o numero de contacto não estiver vazio é um numero
			if (textContacto.getText().length() > 0 ){
				try { 
						Integer.parseInt(textContacto.getText()); 
					} catch(NumberFormatException ae) { 
		    		JOptionPane.showMessageDialog(null, " O numero de telefone tem que ser um numero.", 
						"ERRO-Numero Telefone!", JOptionPane.ERROR_MESSAGE);
		    		return;
		    		}	
			
			}// frim try
			
			if (eNovoCliente){
				//criar o novo cliente 
				criarNovoCliente();
			
			}// fim if	
			// atualizar um cliente, não é cliente novo	
			else{ 
				
				clienteParaAtualizar.setNome(textNome.getText());
				clienteParaAtualizar.setApelido(textApelido.getText());
				clienteParaAtualizar.setNib(Integer.parseInt(textNib.getText()));
				clienteParaAtualizar.setContacto(validarNumTelefone()); 
				clienteParaAtualizar.setEmail(textEmail.getText());
				clienteParaAtualizar.setMorada(textMorada.getText());
				clienteParaAtualizar.setCodigoPostal(textCodigoPostal.getText());
				clienteParaAtualizar.setPassword(textPassword.getText());
				clienteParaAtualizar.setUtilizador(textUtilizador.getText());
				
				// é necessario para colocar o novo objeto no HashMap!!
				conjuntoClientes.put(clienteParaAtualizar.getClienteID(), clienteParaAtualizar);
				
			}// fim else
			
			// guardar cliente no ficheiro
			try {
				new Utilidades().serialize(conjuntoClientes, Cliente.getFicheiro());
			} catch (IOException e1) {
				System.out.print("Problemas a gravar ficeiro:");
				e1.printStackTrace();
			}
			// reset textfields
			resetClientesTextfields();
			
		// criar login e password
		}else if (source == btnCriaLogin ){
			textPassword.setText(textNib.getText());			
			try {
				user = textNome.getText(0, 1) + textApelido.getText();
				textUtilizador.setText(user);
			} catch (BadLocationException e1) {
				e1.printStackTrace();
			}
			
		}		
		// sair
		else if (source == btnSair){
			this.dispose();			
		}
	}// fim actonPerformed()
	
	/**
	 * método para limpar as caixas de texto 
	 */
	public void resetClientesTextfields(){
		textNome.setText("");
		textApelido.setText("");
		textEmail.setText("");
		textContacto.setText("");
		textMorada.setText("");
		textCodigoPostal.setText("");
		textNib.setText("");
		textUtilizador.setText("");
		textPassword.setText("");
		
	}// fim resetClientes()
	
	/**
	 * método para verifiar se caixas de texto estão vazias
	 */
	public boolean campoVazios(){ 
		
		if (textNome.getText().length() == 0) return true;
		if (textApelido.getText().length() == 0) return true;
		if (textEmail.getText().length() == 0) return true;
		if (textNib.getText().length() == 0) return true;
		if (textUtilizador.getText().length() == 0) return true;
		if (textPassword.getText().length() == 0) return true;
				
		return false;
	}// fim campoVazios()
	
	/**
	 * método para criar um novo cliente
	 */
	public void criarNovoCliente(){		
		
		novoCliente = new Cliente(	textNome.getText(),
									textApelido.getText(),
									textUtilizador.getText(),
									textPassword.getText(),
									textEmail.getText(),
									Integer.parseInt(textNib.getText()),
									validarNumTelefone(),
									textMorada.getText(),
									textCodigoPostal.getText());
		
		
		
		//desirializar o ficheiro para adicionar novo cliente
		if (new File(Cliente.getFicheiro()).exists()){
		  		try {
		  			
					conjuntoClientes = (HashMap) new Utilidades().deserialize(Cliente.getFicheiro());
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		Integer idCliente = novoCliente.getClienteID();				
		conjuntoClientes.put(idCliente,novoCliente);
		
		
	}// fim criarNovoCliente()
	
	/**
	 * método para validar o email
	 * @param email - email cliente
	 * @return boolean valido 
	 */
	public boolean validoEmail( String email){
		
		String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
			     
		Boolean valido = email.matches(EMAIL_REGEX);
		return valido;
	}
	
	/**
	 * método para validar num telefone
	 */
	public int validarNumTelefone(){
		// porque o numero de contacto não é obrigatório, se fical como null
		// temos que atribuir o valor de 0, pois casting de null String para int não é possivel
		int numTelefone = 0;
		if (textContacto.getText().length() != 0)
		numTelefone = Integer.parseInt(textContacto.getText());
		
		return numTelefone;
	}
}
