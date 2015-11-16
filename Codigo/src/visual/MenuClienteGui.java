package visual;

import java.awt.event.*;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import classes.Cliente;
import classes.Evento;
/**
 * Class utilizada para Cliente realizar as suas escolhas 
 *
 */

public class MenuClienteGui extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JButton btnCadastro, btnCriarEvento, btnSair, btnConsultarEvento;
	
	private JPanel contentPane;
	private Cliente utilizadorCliente;  // identificaçao do cliente actual
	private HashMap <Integer, Cliente> conjuntoClientes = new HashMap <Integer,Cliente>();
	private static HashMap <Integer, Evento> conjuntoEeventos = new HashMap <Integer,Evento>();
	
	/**
	 * Construtor da Class
	 * @param utilizadorCliente - cliente que efetuou o login
	 */
	public MenuClienteGui( Cliente utilizadorCliente){
		
		this.utilizadorCliente = utilizadorCliente;
		iniciarFrame();
	
	}
	
	/**
	 * Create the frame.
	 */
	public void iniciarFrame() {
		
		setResizable(false);
		setTitle("Menu Cliente" ); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 342, 190);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocation(0, 0);
		this.setVisible(true);
		
		btnCadastro = new JButton("Atualizar Cadastro");
		btnCadastro.addActionListener(this);
		btnCadastro.setBounds(13, 11, 149, 23);
		contentPane.add(btnCadastro);
		
		btnCriarEvento = new JButton("Criar Evento");
		btnCriarEvento.addActionListener(this);
		btnCriarEvento.setBounds(196, 11, 130, 23);
		contentPane.add(btnCriarEvento);
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(this);
		btnSair.setBounds(113, 92, 130, 23);
		contentPane.add(btnSair);
		
		btnConsultarEvento = new JButton("Consultar Evento");
		btnConsultarEvento.addActionListener(this);
		btnConsultarEvento.setBounds(196, 45, 130, 23);
		contentPane.add(btnConsultarEvento);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(13, 79, 316, 2);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 47, 155, 3);
		contentPane.add(separator_1);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setText("Ol\u00E1 " + this.utilizadorCliente.getNome() + " " + this.utilizadorCliente.getApelido());
		lblNewLabel.setBounds(13, 126, 313, 14);
		contentPane.add(lblNewLabel);
	}// fim frame
	
	/**
	 * método para implementar as acções dos botões
	 */
	public void actionPerformed (ActionEvent e){
		
		Object source = e.getSource();
		
		// atualizar o seu proprio cadastro
		if (source == btnCadastro ){		
			Cliente.atualizarCadastro(utilizadorCliente, getConjuntoClientes());
		}
		
		// novo evento
		else if (source == btnCriarEvento){
			new EscolhaDeEvento(utilizadorCliente);
		}
	
		// consultar e gerir os seus eventos
		else if (source == btnConsultarEvento){
			// chama a parte grafica passado os eventos que lhe pertencem e a sua id
			new ClienteGestaoEventoGui(Cliente.getEventosDoCliente(getConjuntoEventos(),utilizadorCliente.getClienteID()));
			
		}// sair
		else if (source == btnSair){
			System.exit(0);			
		}
	}// fim metodo
	
	/**
	 * metodo utilizado para carregar o hashmap com os clientes
	 * @param ficheiro
	 * @return
	 */
	public HashMap <Integer, Cliente> getConjuntoClientes(){
		//HashMap <Integer, Cliente> conjuntoClientes = new HashMap <Integer,Cliente>();
		
		try {			
			conjuntoClientes = (HashMap<Integer, Cliente>)new Utilidades().deserialize("clientes.ser");
			
		} catch (ClassNotFoundException | IOException e) {
			System.out.println ("Erro ao deserializar o ficheiro dos clientes.");
			e.printStackTrace();
		}
		return conjuntoClientes;
	} // fim getConteudo()
	
	/**
	 * metodo para carregar os eventos 
	 * @return conjuntoEventos
	 */
	public HashMap <Integer, Evento> getConjuntoEventos(){
		try {	
			new Utilidades ();
			MenuClienteGui.conjuntoEeventos = (HashMap<Integer, Evento>) Utilidades.deserialize("eventos.ser");
			
		} catch (ClassNotFoundException | IOException e) {
			System.out.println ("Erro ao deserializar o ficheiro dos clientes.");
			e.printStackTrace();
		}
		return conjuntoEeventos;
	} // fim getConteudo()
	
}
