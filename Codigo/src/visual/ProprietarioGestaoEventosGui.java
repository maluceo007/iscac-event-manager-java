package visual;


import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import classes.*;
import com.toedter.calendar.JDateChooser;
import javax.swing.DefaultComboBoxModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

/**
 * Class de interface gráfica para o proprietario gerir os Eventos 
 *
 */
public class ProprietarioGestaoEventosGui extends JFrame implements ActionListener{

	private static final long serialVersionUID = -6938576411098551572L;
	private JPanel contentPane;
	private JTextArea textEcra;
	private JButton btnSair,btnClienteGestao,btnPesquisa,btnRefrescar,btnEvento,btnApagar,btnConfirmar;
	private JRadioButton rdbtnData,rdbtnHora,rdbtnClienteId,rdbtInvisible;
	private ButtonGroup rdbtnGrupoPesquisaEvento;
	private JCheckBox chckbxAniversario,chckbxPijama;
	private JDateChooser dateChooser;
	private String data;
	private static final String FICHEIRO_EVENTOS = "eventos.ser";
	private static HashMap<Integer, Evento> conjuntoEventos = new HashMap<Integer, Evento>();
	private static HashMap<Integer, Object> conjuntoFestaAniversario,
											conjuntoFestaPijama	= new HashMap<Integer,Object>();
	private JTextField textClienteId;
	//private Proprietario proprietario;
	private JComboBox<String> horaPesquisa;
	private String horaDePesquisa;
	private JLabel lblPesquisarEventoPor;
	private JTextField textGetId;
	private Evento alterarEvento;
	
	/**
	 * construtor
	 * @param conjuntoEventos - HashMap<Integer, Evento>
	 */
	public ProprietarioGestaoEventosGui(HashMap<Integer, Evento> conjuntoEventos){
		//this.conjuntoEventos = conjuntoEventos;
		
		iniciarFrame();
		printEventos(this.conjuntoEventos = conjuntoEventos);
	}
	
	/**
	 * Create the frame.
	 */
	public void iniciarFrame() {
		setTitle("Gest\u00E3o de Eventos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 978, 493);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setVisible(true);
		this.setResizable(false);		
		this.setLocationRelativeTo(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 942, 290);
		contentPane.add(scrollPane);
		
		textEcra = new JTextArea();
		textEcra.setEditable(false);
		textEcra.setFont(new Font("Courier New", Font.PLAIN, 13));
		textEcra.setBackground(SystemColor.activeCaption);
		scrollPane.setViewportView(textEcra);
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(this);
		btnSair.setBounds(831, 430, 101, 23);
		contentPane.add(btnSair);
		
		btnRefrescar = new JButton("Atualizar Eventos");
		btnRefrescar.addActionListener(this);
		btnRefrescar.setBounds(821, 396, 131, 23);
		contentPane.add(btnRefrescar);
		
		JLabel lblPesquisa = new JLabel("Pesquisar Eventos:");
		lblPesquisa.setBounds(10, 312, 137, 14);
		contentPane.add(lblPesquisa);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 333, 418, 2);
		contentPane.add(separator);

		rdbtnData = new JRadioButton("Data");
		rdbtnData.setBounds(169, 352, 109, 23);
		contentPane.add(rdbtnData);
		
		rdbtnHora = new JRadioButton("Hora");
		rdbtnHora.setBounds(169, 378, 109, 23);
		contentPane.add(rdbtnHora);
		
		rdbtnClienteId = new JRadioButton("Cliente Id");
		rdbtnClienteId.setBounds(169, 404, 109, 23);
		contentPane.add(rdbtnClienteId);
		
		rdbtInvisible = new JRadioButton("");
		rdbtInvisible.setVisible(false);
		rdbtInvisible.setBounds(169, 430, 109, 23);
		contentPane.add(rdbtInvisible);
		
		rdbtnGrupoPesquisaEvento = new ButtonGroup();
		rdbtnGrupoPesquisaEvento.add(rdbtnData);
		rdbtnGrupoPesquisaEvento.add(rdbtnHora);
		rdbtnGrupoPesquisaEvento.add(rdbtnClienteId);
		rdbtnGrupoPesquisaEvento.add(rdbtInvisible);
		
		//_____________________checkBox____________________________
		chckbxAniversario = new JCheckBox("Festa de Anivers\u00E1rio");
		chckbxAniversario.setBounds(10, 352, 137, 23);
		contentPane.add(chckbxAniversario);
		
		chckbxPijama = new JCheckBox("Festa de Pijama");
		chckbxPijama.setBounds(10, 378, 137, 23);
		contentPane.add(chckbxPijama);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(153, 338, 16, 115);
		contentPane.add(separator_1);
		
		btnPesquisa = new JButton("Pesquisar");
		btnPesquisa.addActionListener(this);
		btnPesquisa.setBounds(136, 308, 109, 23);
		contentPane.add(btnPesquisa);
		
		textClienteId = new JTextField();
		textClienteId.setBounds(284, 405, 91, 20);
		contentPane.add(textClienteId);
		textClienteId.setColumns(10);
		
		dateChooser = new JDateChooser();
		dateChooser.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				if (rdbtnData.isSelected()){
					java.util.Date utilDate = (java.util.Date) dateChooser.getDate();
					java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
					//texObservacao.setText("date." + sqlDate);
				
					DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
					data = df.format(sqlDate);
					//System.out.println (" date " + data );
				}
			}
		});
		dateChooser.setBounds(284, 355, 109, 20);
		contentPane.add(dateChooser);
		
		btnClienteGestao = new JButton("id");
		btnClienteGestao.addActionListener(this);
		btnClienteGestao.setBounds(384, 404, 16, 23);
		contentPane.add(btnClienteGestao);
		
		horaPesquisa = new JComboBox<String>();
		horaPesquisa.setModel(new DefaultComboBoxModel<String>(new String[] {"", "11","12","13","14","15","16","17","18","19","20"}));
		horaPesquisa.setSelectedIndex(0);
		horaPesquisa.addActionListener(this);
		horaPesquisa.setBounds(284, 379, 42, 20);
		contentPane.add(horaPesquisa);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(429, 338, 16, 115);
		contentPane.add(separator_2);
		
		btnEvento = new JButton("Atualizar");
		btnEvento.addActionListener(this);
		btnEvento.setBounds(480, 378, 89, 23);
		contentPane.add(btnEvento);
		
		lblPesquisarEventoPor = new JLabel("Pesquisar Evento por Id:");
		lblPesquisarEventoPor.setHorizontalAlignment(SwingConstants.CENTER);
		lblPesquisarEventoPor.setBounds(429, 333, 210, 14);
		contentPane.add(lblPesquisarEventoPor);
		
		textGetId = new JTextField();
		textGetId.setBounds(480, 353, 86, 20);
		contentPane.add(textGetId);
		textGetId.setColumns(10);
		
		btnApagar = new JButton("Apagar");
		btnApagar.addActionListener(this);
		btnApagar.setBounds(480, 404, 89, 23);
		contentPane.add(btnApagar);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(638, 352, 16, 101);
		contentPane.add(separator_3);
		
		btnConfirmar = new JButton("Confirmar Eventos");
		btnConfirmar.addActionListener(this);
		btnConfirmar.setBounds(674, 352, 144, 23);
		contentPane.add(btnConfirmar);
		
		JLabel lblNewLabel = new JLabel("Eventos crieados pelos Clientes:");
		lblNewLabel.setBounds(649, 333, 191, 14);
		contentPane.add(lblNewLabel);
		
	}// fim frame
	/**
	 * método para implementar as acções dos botões
	 */
	
	public void actionPerformed (ActionEvent e){
		
		Object source = e.getSource();
		
		// botão para sair
		if (source == btnSair){
			this.dispose();
		}
		// recarregar eventos do HashMap inicial
		else if (source == btnRefrescar){
			printEventos(getConjuntoEventos());			
		}
		
		else if (source ==btnClienteGestao ){
			new ProprietarioGestaoClienteGui();
		}
		else if (source ==btnPesquisa ){
			//_____________________________________________________________________________________
			// realizar a pesquisa se uma ou duas das opções do tipo de festa estão selecionadas
			if (chckbxAniversario.isSelected() || chckbxPijama.isSelected()){
				
				// mostra só festas de Aniversário ***********************************************
				if ( chckbxAniversario.isSelected() && !chckbxPijama.isSelected()){
					
					parseFestaAniversario();
					// pesquisar os eventos de aniversario por hora
					if (rdbtnHora.isSelected() && horaDePesquisa != " "){
						pesquisaPorHora();
					}
					// pesquisa por data
					if (rdbtnData.isSelected() && dateChooser.getDate()!= null){
						printEventos(Proprietario.getEventosPorData(conjuntoEventos, data));
						rdbtInvisible.setSelected(true);
						dateChooser.setCalendar(null);
					}
					
					// mostrar por cliente id
					if (rdbtnClienteId.isSelected() && textClienteId.getText().length() > 0){
						printEventos(Proprietario.getEventosPorClienteId(conjuntoEventos, textClienteId.getText()));
						rdbtInvisible.setSelected(true);
						textClienteId.setText("");
					}						
					
				}// fim if festaAniversario
				
				// mostar só festas de Pijama ********************************************************
				if ( !chckbxAniversario.isSelected() && chckbxPijama.isSelected()){
					parseFestaPijama();
					// pesquisa por data
					if (rdbtnData.isSelected() && dateChooser.getDate()!= null){
						printEventos(Proprietario.getEventosPorData(conjuntoEventos, data));
						rdbtInvisible.setSelected(true);
						dateChooser.setCalendar(null);
					}
					// mostrar por cliente id
					if (rdbtnClienteId.isSelected() && textClienteId.getText().length() > 0){
						printEventos(Proprietario.getEventosPorClienteId(conjuntoEventos, textClienteId.getText()));
						rdbtInvisible.setSelected(true);
						textClienteId.setText("");
					}		
				} // fim if Aniversario
				
				// mostra ambos os Eventos ************************************************************
				if (chckbxAniversario.isSelected() && chckbxPijama.isSelected()){
					printEventos(conjuntoEventos );
					 // mostrar por cliente id
					if (rdbtnClienteId.isSelected() && textClienteId.getText().length() > 0){
						printEventos(Proprietario.getEventosPorClienteId(conjuntoEventos, textClienteId.getText()));
						rdbtInvisible.setSelected(true);
						textClienteId.setText("");
					}
					// por hora
					if (rdbtnHora.isSelected() && horaDePesquisa != " "){
						parseFestaAniversario();
						pesquisaPorHora();
					}	
					// por data
					if (rdbtnData.isSelected() && dateChooser.getDate()!= null){
						printEventos(Proprietario.getEventosPorData(conjuntoEventos, data));
						rdbtInvisible.setSelected(true);
						dateChooser.setCalendar(null);
					}					
						
				}// fim if ambos Eventos
				
			}// fim primeiro if
			
			//_________________________________________________________________________________________
			//limpa o ecrã nenhuma tipo de pesquisa foi selecionado
			if (!chckbxAniversario.isSelected() && !chckbxPijama.isSelected()){
				textEcra.setText("");
				textEcra.append(String.format("%-13s%-8s%-9s%-13s%-10s%-10s%-10s%-10s%-12s%-7s%-8s%-40s%n",
						"Tipo Evento","Id", "Cliente", "Data", "Hora", "Duração", "# Partc", "Prc Mnu", "Total", "Excl","Estado", "Lembrete"));
			}
			
		} // fim elseif 
		
		// pesquisar por hora
		else if (source == horaPesquisa ){
			
			horaDePesquisa = horaPesquisa.getSelectedItem().toString();
		}
		
		else if (source == btnEvento ){
			// evento para ser alterador
			alterarEvento = Proprietario.getEventoPorId(conjuntoEventos, textGetId.getText());
			
			
			if ( alterarEvento instanceof Aniversario){
				textGetId.setText("");
				new EventoAniversarioGui(conjuntoEventos); // alterar				
				
			}
			// **************** alterear Festa Pijama*********************
			if ( alterarEvento instanceof FestaPijama){
				textGetId.setText("");
				Proprietario.atualizarFestaDoPijama( alterarEvento, conjuntoEventos);
			}
			
		}// apagar evento
		else if (source == btnApagar ){
			conjuntoEventos =  Proprietario.removeEvento(conjuntoEventos, textGetId.getText());
			printEventos (conjuntoEventos);
			textGetId.setText("");
			gravarAlteracoes(conjuntoEventos);
		}
		// pesquisar pedido de Evento para confirmar
		else if (source == btnConfirmar){
			printEventos(Proprietario.getEventosPorEstado(conjuntoEventos,0));
		
		}
		
	}
	
	
	
	/**
	 * metod para separar as festas de aniversario dos Eventos
	 * 
	 */	
	public void parseFestaAniversario(){
		// separar as festas de Aniversari dos Eventos para apresentar só as festas aniversario
		conjuntoFestaAniversario = Proprietario.getSubEventos (conjuntoEventos, "Aniversario");
		printSubEventos(conjuntoFestaAniversario);
	}
	/**
	 * metodo para separar as festas de pijama dos Eventos
	 */
	public void parseFestaPijama(){
		conjuntoFestaPijama = Proprietario.getSubEventos (conjuntoEventos,"FestaPijama");
		printSubEventos(conjuntoFestaPijama);
	}
	/**
	 * metodo que valida as condições no Gui para chamar o metodo do objeto Proprietaro para pesquisar por hora
	 */
	public void pesquisaPorHora (){
		// pesquisar os evento por hora de inicio
		conjuntoFestaAniversario = Proprietario.getEventosPorHora(conjuntoFestaAniversario, horaDePesquisa);
		printSubEventos (conjuntoFestaAniversario); 
		horaPesquisa.setSelectedIndex(0);
		rdbtInvisible.setSelected(true);
	}
	
	/**
	 * metodo para deserializar os Eventos
	 * @return HashMap <Integer, Evento>
	 */
	
	public HashMap <Integer, Evento> getConjuntoEventos(){
		try {	
			new Utilidades ();
			this.conjuntoEventos = (HashMap<Integer, Evento>) Utilidades.deserialize(FICHEIRO_EVENTOS);
			
		} catch (ClassNotFoundException | IOException e) {
			System.out.println ("Erro ao deserializar o ficheiro dos clientes.");
			e.printStackTrace();
		}
		return conjuntoEventos;
	} // fim getConteudo()
	
	/**
	 * metodo para imprimir os Eventos
	 * @param eventosHashMap
	 */
	public void printEventos (HashMap<Integer, Evento> eventosHashMap){
		textEcra.setText("");  // limpar a zona do JTextArea
			
		textEcra.append(String.format("%-13s%-8s%-9s%-13s%-10s%-10s%-10s%-10s%-12s%-7s%-8s%-40s%n",
				"Tipo Evento","Id", "Cliente", "Data", "Hora", "Duração", "# Partc", "Prc Mnu", "Total", "Excl","Estado", "Lembrete"));
		
		Set <Integer>  set = eventosHashMap.keySet();
		Iterator<Integer> iterador = set.iterator();		
			
		while( iterador.hasNext()){
			Integer chave = (Integer) iterador.next();
			Evento valores = (Evento) eventosHashMap.get(chave);
				
			//System.out.print (valores.getClass().getSimpleName());
			textEcra.append (valores.getClass().getSimpleName() + " ");
			textEcra.append(String.valueOf(valores));
		}// while
	}// fim printHashMap() 

	/**
	 * metodo para imprimir os sub-Eventos ( Festa Aniversário e Festa Pijama)
	 * @param eventosHashMap
	 */
	public void printSubEventos (HashMap<Integer, Object> eventosHashMap){
		textEcra.setText("");  // limpar a zona do JTextArea
		
		textEcra.append(String.format("%-13s%-8s%-9s%-13s%-10s%-10s%-10s%-10s%-12s%-7s%-8s%-40s%n",
				"Tipo Evento","Id", "Cliente", "Data", "Hora", "Duração", "# Partc", "Prc Mnu", "Total", "Excl","Estado", "Lembrete"));
		
		Set <Integer>  set = eventosHashMap.keySet();
		Iterator<Integer> iterador = set.iterator();		
			
		while( iterador.hasNext()){
			Integer chave = (Integer) iterador.next();
			Object valores = (Object) eventosHashMap.get(chave);
			
				textEcra.append (valores.getClass().getSimpleName() + " ");
				textEcra.append(String.valueOf(valores));
				
		}// while
	}// fim printFestaAniversario() 
	
	/**
	 * gravar conjuntas Eventos depois de realizadas as alterações
	 * @param conjuntoEventos
	 */
	public void gravarAlteracoes(HashMap<Integer, Evento> conjuntoEventos){
		// guardar proprietario no ficheiro
		try {
			new Utilidades();
			Utilidades.serialize( conjuntoEventos, FICHEIRO_EVENTOS);
		} catch (IOException e1) {
			System.out.print("Problemas a gravar ficeiro:");
			e1.printStackTrace();
		}

					
	} // fim
}
