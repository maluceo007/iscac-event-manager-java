package visual;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import classes.Cliente;
import classes.Proprietario;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;

/**
 * método de interface gráfic para o proprietário realizar a gestão do Cliente
 * 
 *
 */
public class ProprietarioGestaoClienteGui extends JFrame implements ActionListener, FocusListener {

	private static final long serialVersionUID = 2097478076771254022L;
	private JPanel consultarPane;
	private JButton btnCarregar, btnSair ,btnPesquisa, btnApagar, btnAtualizar;
	private static HashMap<Integer, Cliente> conjuntoClientes = new HashMap<Integer, Cliente>();
	private JTextArea textEcra;
	private JTextField textPesquis;
	private JTextField textApagar;
	private ArrayList<Cliente> cliente = new ArrayList<Cliente> ();
	private JRadioButton rdbtnId, rdbtnApelido, rdbtnNif;
	private ButtonGroup rdbtnGrupo;
	private int rdbtnEscolhido = 0;
	private JSeparator separator_4;
	private JTextField textAlterar;
	
	/**
	 * construtor cria o frame.
	 */
	public ProprietarioGestaoClienteGui() {
		setTitle("Gest\u00E3o Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 942, 480);
		consultarPane = new JPanel();
		consultarPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(consultarPane);
		consultarPane.setLayout(null);
		this.setVisible(true);
		this.setResizable(false);		
		this.setLocationRelativeTo(null);
		
		btnCarregar = new JButton("Carregar");
		btnCarregar.addActionListener(this);
		btnCarregar.setBounds(10, 11, 89, 23);
		consultarPane.add(btnCarregar);
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(this);
		btnSair.setBounds(837, 340, 89, 23);
		consultarPane.add(btnSair);
		
		JScrollPane scrollPane = new JScrollPane ();
		scrollPane.setBounds (109, 12, 817, 315);
		consultarPane.add(scrollPane);
		
		textEcra = new JTextArea();		
		textEcra.setFont(new Font("Courier New", Font.PLAIN, 13));
		textEcra.setBackground(SystemColor.activeCaption);
		scrollPane.setViewportView(textEcra);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 323, 89, 14);
		consultarPane.add(separator);
		
		textPesquis = new JTextField();
		textPesquis.setBounds(113, 402, 166, 20);
		consultarPane.add(textPesquis);
		textPesquis.setColumns(10);
		
		JLabel lblPesquisa = new JLabel("Escolha o campo que pretende PESQUISAR:");
		lblPesquisa.setHorizontalAlignment(SwingConstants.CENTER);
		lblPesquisa.setBounds(10, 349, 269, 14);
		consultarPane.add(lblPesquisa);
		
		btnPesquisa = new JButton("Pesquisa");
		btnPesquisa.addActionListener(this);
		btnPesquisa.setBounds(10, 401, 89, 23);
		consultarPane.add(btnPesquisa);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(361, 344, 1, 96);
		consultarPane.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(306, 344, 11, 96);
		consultarPane.add(separator_2);
		
		JLabel lblApagar = new JLabel("Inserir Id para apagar:");
		lblApagar.setHorizontalAlignment(SwingConstants.CENTER);
		lblApagar.setBounds(319, 372, 128, 14);
		consultarPane.add(lblApagar);
		
		textApagar = new JTextField();
		textApagar.addFocusListener(this);
		textApagar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textApagar.setHorizontalAlignment(SwingConstants.LEFT);
		textApagar.setBounds(457, 369, 89, 20);
		consultarPane.add(textApagar);
		textApagar.setColumns(10);
		
		btnApagar = new JButton("Apagar");
		btnApagar.setEnabled(false);;
		btnApagar.addActionListener(this);
		btnApagar.setBounds(457, 401, 89, 23);
		consultarPane.add(btnApagar);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(556, 344, 17, 96);
		consultarPane.add(separator_3);
		
		rdbtnId = new JRadioButton("Id");
		rdbtnId.addActionListener(this);
		rdbtnId.setBounds(10, 368, 109, 23);
		consultarPane.add(rdbtnId);
		
		rdbtnApelido = new JRadioButton("Apelido");
		rdbtnApelido.addActionListener(this);
		rdbtnApelido.setBounds(129, 368, 109, 23);
		consultarPane.add(rdbtnApelido);
		
		rdbtnNif = new JRadioButton("Nif");
		rdbtnNif.addActionListener(this);
		rdbtnNif.setBounds(246, 368, 54, 23);
		consultarPane.add(rdbtnNif);
		
		// grupo de radioButtons
		rdbtnGrupo = new ButtonGroup();
		rdbtnGrupo.add(rdbtnId);
		rdbtnGrupo.add(rdbtnApelido);
		rdbtnGrupo.add(rdbtnNif);
		
		separator_4 = new JSeparator();
		separator_4.setOrientation(SwingConstants.VERTICAL);
		separator_4.setBounds(830, 344, 11, 96);
		consultarPane.add(separator_4);
		
		JLabel lblAlterar = new JLabel("Inserir Id para atualizar:");
		lblAlterar.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlterar.setBounds(573, 370, 144, 14);
		consultarPane.add(lblAlterar);
		
		textAlterar = new JTextField();
		textAlterar.addFocusListener(this);
		textAlterar.setHorizontalAlignment(SwingConstants.LEFT);
		textAlterar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textAlterar.setColumns(10);
		textAlterar.setBounds(727, 367, 89, 20);
		consultarPane.add(textAlterar);
		
		btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(this);
		btnAtualizar.setEnabled(false);
		btnAtualizar.setBounds(727, 399, 89, 23);
		consultarPane.add(btnAtualizar);
		
	}// end frame
	
	/**
	 * método para implementar as acções dos botões
	 */
	public void actionPerformed (ActionEvent e){
		
		Object source = e.getSource();
		
		// butão para carregar valores do HashMap e mostrar na JTextArea 
		if (source == btnCarregar ){
			getConjuntoClientes (Cliente.getFicheiro());
			parseTreeMap (ProprietarioGestaoClienteGui.conjuntoClientes);
		}
		// butão pesquisa o cliente
		else if ( source == btnPesquisa){
			// se a caixa de texto estiver vazia, ou o tipo de pesquisa não for escolhida, sai não pesquisa
			if (textPesquis.getText().length() == 0 || rdbtnEscolhido == 0){ return; }

			// pesquisar os clientes por id  ou apelido ou nif, consoante o radioButton		
			
			switch (rdbtnEscolhido){  
			case 1:
				cliente = Proprietario.getClientePorID(conjuntoClientes, textPesquis.getText());
				break;
			case 2:
				cliente = Proprietario.getClientesPorApelido(conjuntoClientes, textPesquis.getText());
				break;
			case 3:
				cliente = Proprietario.getClientesPorNif(conjuntoClientes, textPesquis.getText());
				break;
			default:
				break;
			}// fim switch
			
			printCliente(cliente);	
			resetPesquisa();  		// reset area de pesquisa
		}
		//butão apaga o cliente
		else if ( source == btnApagar)	{
			conjuntoClientes = Proprietario.removeCliente(conjuntoClientes, textApagar.getText());
			setConjuntoClientes(conjuntoClientes);
			
			textApagar.setText("");
			this.btnApagar.setEnabled(false);			
		}
		//butão atualiza o cliente    
		else if ( source == btnAtualizar){
			
			Proprietario.atualizarCliente(conjuntoClientes, textAlterar.getText());  
			textAlterar.setText("");		
			this.btnAtualizar.setEnabled(false);				
			
		}
		
		// butão para sair do gui
		else if (source == btnSair){
			this.dispose();
		}
		// radioButton Id
		else if (source == rdbtnId){
			rdbtnEscolhido = 1;
		}
		// radioButton Apelido
		else if (source == rdbtnApelido){
			rdbtnEscolhido = 2;
		}
		// radioButton Nif
		else if (source == rdbtnNif){
			rdbtnEscolhido = 3;
		}	
		
	}// fim actionPerformed();
	
	/**
	 * método utilizao para deserializar o ficheiro dos clientes para um HashMap
	 * @param ficheiro - Nome do ficheiro dos clientes
	 * @return HashMap com os clientes
	 */
	public HashMap <Integer, Cliente> getConjuntoClientes(String ficheiro){
		try {			
			this.conjuntoClientes = (HashMap<Integer, Cliente>)new Utilidades().deserialize(ficheiro);
			
		} catch (ClassNotFoundException | IOException e) {
			System.out.println ("Erro ao deserializar o ficheiro dos clientes.");
			e.printStackTrace();
		}
		return conjuntoClientes;
	} // fim getConteudo()
	
	/**
	 * método para serializar o conjunto de Clientes
	 * @param conjuntoClientesParaGravar - Map <Integer, Cliente>
	 */
	public void setConjuntoClientes (Map <Integer, Cliente> conjuntoClientesParaGravar){
		try {
			Utilidades.serialize(conjuntoClientesParaGravar, Cliente.getFicheiro());   
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * método para parsar HashMap para TreeMap, podendo assim imprimir 
	 * @param objetoColecao
	 */
	public void parseTreeMap (Map <Integer, Cliente> objetoColecao){
		
		TreeMap<Integer, Object> HashMapOrdenado = new TreeMap<Integer, Object> (conjuntoClientes);
		printMap (HashMapOrdenado); // chamar o metodo pritMap para imprimir
	}	
	
	/**
	 * metodo utilizado para imprimir um TreeMap ordenado por id
	 * @param objeto - TreeMap<Integer, Object>
	 */
	public void printMap (TreeMap<Integer, Object> objeto){
		textEcra.setText("");  // limpar a zona do JTextArea
		textEcra.append( String.format("%-5s%-18s%-18s%-25s%-10s%-15s%-25s%-15s%n", 
				"ID", "NOME" , "APELIDO", "E-MAIL", "NIF", "CONTACTO", "MORADA", "COD POSTAL"));
		
		Set <Integer>  set = objeto.keySet();
		Iterator<Integer> iterador = set.iterator();		
		
		while( iterador.hasNext()){
			Integer chave = (Integer) iterador.next();
			Cliente valores = (Cliente) objeto.get(chave);
			
			//System.out.print (valores);
			textEcra.append(String.valueOf(valores));
		}
	}// fim printHashMap()
	
	/**
	 * método para ativar botões consuante o focus da caixa de texto correspondente ao proprio butão
	 */
	public void focusLost (FocusEvent e){
		
		Object source = e.getSource();
		// ativar o butão de apagar
		if (source == textApagar && textApagar.getText().length() >0){			
			btnApagar.setEnabled(true);
		}
		// ativar o butão de atualizar
		else if (source == textAlterar && textAlterar.getText().length() >0){
			btnAtualizar.setEnabled(true);
		}
	} // fim focusLost

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * imprime Cliente
	 * @param cliente - ArrayList<Cliente>
	 */
	public void printCliente ( ArrayList<Cliente> cliente){
		textEcra.setText("");  // limpar a zona do JTextArea
		textEcra.append( String.format("%-5s%-18s%-18s%-25s%-10s%-15s%-25s%-15s%n", 
				"ID", "NOME" , "APELIDO", "E-MAIL", "NIF", "CONTACTO", "MORADA", "COD POSTAL"));
		
		if (cliente.size() != 0){ 
			
			for (int i=0 ; i<cliente.size(); i++)
				textEcra.append(String.valueOf(cliente.get(i)));
		}
		cliente.clear(); // clear ArrayList
	}
	
	/**
	 * reset  area de pesquisa
	 */
	public void resetPesquisa(){
		textPesquis.setText("");
		rdbtnGrupo.clearSelection();
		rdbtnEscolhido = 0;
	}
}// fim class
