package visual;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import jxl.*;
import jxl.read.biff.BiffException;
import classes.Aniversario;
import classes.Cliente;
import classes.Evento;
import classes.FestaPijama;

import com.toedter.calendar.JCalendar;
/**
 * Class de interface gráfica para a criação/alteração de uma Festa de Pijama
 *
 *
 */
public class EventoFestaPijamaGui extends JFrame {
	
	
	private static final long serialVersionUID = 1246022899591142628L;
	private JPanel contentPane;
	private JRadioButton rdbtnMenu1, rdbtnMenu2;	// radio buttons
	private ButtonGroup rdbtnGrupo;  							// grupo de radio buttons
	private JTextPane textPane1,textPane2,textPane3;	
	private JTextField textNumParticipantes,textNumeroCliente;
	private JTextArea textObservacao;
	private JLabel lblMostrarValidade,lblNumeroParticipantes;
	private JCheckBox chckbxExclusividade;	
	private JCalendar calendar;
	private String data;
	//private Date date;
	private JButton btnValidadDuracao,btnPesquisaCliente;	
	private Boolean exclusividade = true;
	private int numParticipantes, estadoDoEvento, eventoId;  // numero total de minutos de duração da festa de pijama
	private FestaPijama festaPijama, actualizarFestaPijama;
	private static final String ficheiro = "eventos.ser";
	private double menu1, menu2, menuFinal;  			// preço dos menus
	private static Map <Integer, FestaPijama> hashMapEventos= new HashMap<Integer, FestaPijama> ();
	private static HashMap<Integer, Evento> conjuntoObjectoEventos = new HashMap<Integer, Evento>();    // utilizado para a colecção de Festa de Pijama passad pelo Gui Gestão Eventos
	private JTextField textPrecoMenu;
	private boolean isNovoEvento = true;
	private Cliente clienteUtilizador;
	
	/**
	 * Construtor para a creação de um evento de Festa de Pijama pelo Proprietario
	 */	
	public EventoFestaPijamaGui (){
		this.estadoDoEvento = 1;
		iniciarFrame();
	}
	
	/**
	 * Construtor para a creação de um evento de Festa de Pijama pelo Cliente
	 * @param utilizador - Cliente
	 */
	public EventoFestaPijamaGui ( Cliente utilizador){
		this.estadoDoEvento = 0;
		iniciarFrame();
		clienteUtilizador = utilizador;
		this.textNumeroCliente.setText(Integer.toString (clienteUtilizador.getClienteID()));
		this.textNumeroCliente.setEditable(false);
		this.btnPesquisaCliente.setVisible(false);
	}
	
	/**
	 * Construtor para a atualização de dados da Festa de Pijama. 
	 * O Proprietario ao gravar este envento está a aceitar o pedido para marcar o evento, o estado é alterado de 0 (pedido para marcar)
	 * para 1 (marcado).
	 * @param festaPijamaActualizar
	 * @param conjuntoEventos
	 */
	public EventoFestaPijamaGui ( Evento festaPijamaActualizar , HashMap<Integer, Evento> conjuntoEventos){
		iniciarFrame();
		this.eventoId = festaPijamaActualizar.getEventoId();
		this.textNumeroCliente.setText(Integer.toString(festaPijamaActualizar.getClienteId()));
		this.textNumeroCliente.setEditable(false);
		this.btnPesquisaCliente.setVisible(false);
		this.estadoDoEvento = festaPijamaActualizar.getEstadoDoEvento();
		this.textNumParticipantes.setText(Integer.toString(festaPijamaActualizar.getNumParticipantes()));
		//this.data = dataEvento;
		parseStringToDate(festaPijamaActualizar.getDataEvento());
		this.textPrecoMenu.setVisible(true);
		this.textPrecoMenu.setText(Double.toString(festaPijamaActualizar.getPrecoMenu()));
		this.rdbtnMenu1.setVisible(false);
		this.rdbtnMenu2.setVisible(false);
		this.isNovoEvento = false;
		this.actualizarFestaPijama = (FestaPijama) festaPijamaActualizar;
		this.conjuntoObjectoEventos = conjuntoEventos;
		confirmarEvento(festaPijamaActualizar); // metodo para alterar o estado do evento se aindar não estiver confirmado
	}
	
	/**
	 * Create the frame.
	 */
	public void iniciarFrame() {
		setTitle("Festa Pijama");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 718, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setVisible(true);
		
		JLabel lblDataEvento = new JLabel("Data Evento");
		lblDataEvento.setBounds(80, 11, 75, 14);
		contentPane.add(lblDataEvento);
		
		// label the validação para a data
		lblMostrarValidade = new JLabel("");
		lblMostrarValidade.setBackground(SystemColor.activeCaption);
		lblMostrarValidade.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMostrarValidade.setHorizontalAlignment(SwingConstants.CENTER);
		lblMostrarValidade.setBounds(47, 265, 131, 20);
		contentPane.add(lblMostrarValidade);
		
		
		// botão de validação
		btnValidadDuracao = new JButton("Gravar");
		btnValidadDuracao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean validacaoAniversario = true;
				
				
				// validar numero participantes
				numParticipantes = Integer.parseInt(textNumParticipantes.getText());
				if (numParticipantes  < 15){
					JOptionPane.showMessageDialog(null, "A festa tem minimo de 15 participantes.", "ERRO-NUMERO PARTICIPANTES!", JOptionPane.ERROR_MESSAGE);
					validacaoAniversario = false;
					return;
				}
				
				// valiar se exite num cliente inserido
				if (textNumeroCliente.getText().length()== 0){
					JOptionPane.showMessageDialog(null, "O Evento tem que ter um cliente.", "ERRO-NUMERO CLIENTE!", JOptionPane.ERROR_MESSAGE);
					validacaoAniversario = false;
				}
				
				// validar data				
				if  (FestaPijama.validarData(conjuntoObjectoEventos, data)){
					JOptionPane.showMessageDialog(null, "O Evento tem que ter um cliente.", "ERRO-NUMERO CLIENTE!", JOptionPane.ERROR_MESSAGE);
					validacaoAniversario = false;
				}
				
				// evento valido - criar evento ou actualizar 
				if (validacaoAniversario){
					
					if (isNovoEvento){
						// criar o novo Evento
						criaNovoEventoAniversario();
					}else{
						/* se estivermos a alterar o evento os butões não estão visiveis mas sim a textBox
						*  temos que obter o preço final da caixa de texto
						*/
						if (textPrecoMenu.isVisible()){
							menuFinal = Double.parseDouble(textPrecoMenu.getText());
						}
						actualizarFestaPijama.setEventoId(eventoId);
						actualizarFestaPijama.setClienteId(Integer.parseInt(textNumeroCliente.getText()));
						actualizarFestaPijama.setDataEvento(data);
						actualizarFestaPijama.setNumParticipantes(Integer.parseInt(textNumParticipantes.getText()));
						actualizarFestaPijama.setPrecoMenu(menuFinal);								
						actualizarFestaPijama.setExclusividade(exclusividade);
						actualizarFestaPijama.setLembrete(textObservacao.getText());									
						actualizarFestaPijama.setEstadoDoEvento(estadoDoEvento);					
						
						System.out.println (" festa actualizada:" + actualizarFestaPijama);
						//loadEventos ();																			// preencher o hashMapEventos com os eventos no ficheiro 
						conjuntoObjectoEventos.put(actualizarFestaPijama.getEventoId(), actualizarFestaPijama);		// addicionar o novo evento ao hashMapEventos
						gravarEvento(conjuntoObjectoEventos); 														// grava o evento da FestaDoPijama
						
						
						// close window
						Window window = SwingUtilities.windowForComponent(btnPesquisaCliente);
						window.dispose();
						
					}// fim else			
					

					//.out.print(String.format("%-8s%-9s%-13s%-10s%-10s%-10s%-10s%-12s%-7s%-8s%-40s%n",
						//	"Evento", "Cliente", "Data", "Hora", "Duração", "# Partc", "Prc Mnu", "Total", "Excl","Estado", "Lembrete"));
					// imprime Aniversario
					System.out.println(conjuntoObjectoEventos);
					
					resetForm();
					
				}
				
			} // end actionPerformed
		});// end ActionListener
		btnValidadDuracao.setBounds(565, 265, 115, 23);
		contentPane.add(btnValidadDuracao);
		
		// ******************** textPane para os menus ****************************
		
		textPane1 = new JTextPane();
		textPane1.setBackground(SystemColor.activeCaption);
		textPane1.setEditable(false);
		textPane1.setBounds(274, 36, 115, 131);
		contentPane.add(textPane1);
		
		textPane2 = new JTextPane();
		textPane2.setEditable(false);
		textPane2.setBackground(SystemColor.activeCaption);
		textPane2.setBounds(422, 36, 115, 131);
		contentPane.add(textPane2);
		
		textPane3 = new JTextPane();
		textPane3.setDisabledTextColor(Color.BLUE);
		textPane3.setForeground(Color.BLACK);
		textPane3.setEnabled(false);
		textPane3.setEditable(false);
		textPane3.setBackground(SystemColor.activeCaption);
		textPane3.setBounds(565, 36, 115, 131);
		contentPane.add(textPane3);
		
		
		
		//***********load excel para os menus ********************************
		getExcelSpreadSheet ();
		// *****************Radio Buttons*********************
		rdbtnMenu1 = new JRadioButton("Menu 1");
		rdbtnMenu1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuFinal = menu1;
			}
		});
		rdbtnMenu1.setSelected(true);
		rdbtnMenu1.setBounds(274, 174, 109, 23);
		contentPane.add(rdbtnMenu1);
		menuFinal = menu1; // escolha inicial 
				
		rdbtnMenu2 = new JRadioButton("Menu 2");
		rdbtnMenu2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				menuFinal = menu2;
			}
		});
		rdbtnMenu2.setBounds(422, 174, 109, 23);
		contentPane.add(rdbtnMenu2);
		// criar grupo de botões
		rdbtnGrupo = new ButtonGroup();
		rdbtnGrupo.add(rdbtnMenu1);
		rdbtnGrupo.add(rdbtnMenu2);
		
		//********************text participantes***************
		lblNumeroParticipantes = new JLabel("Numero participantes:");
		lblNumeroParticipantes.setBounds(274, 213, 138, 14);
		contentPane.add(lblNumeroParticipantes);
		
		textNumParticipantes = new JTextField();
		textNumParticipantes.setText("15");
		textNumParticipantes.setBounds(422, 210, 46, 20);
		contentPane.add(textNumParticipantes);
		textNumParticipantes.setColumns(10);
		
		//*********************check box Exclusividade************
		JLabel lblExclusividade = new JLabel("Exclusividade:");
		lblExclusividade.setBounds(494, 213, 83, 14);
		contentPane.add(lblExclusividade);
		
		chckbxExclusividade = new JCheckBox("");
		chckbxExclusividade.setEnabled(false);
		chckbxExclusividade.setFocusable(false);
		chckbxExclusividade.setSelected(true);
		chckbxExclusividade.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxExclusividade.setBounds(583, 209, 27, 23);
		contentPane.add(chckbxExclusividade);
		
		//***********************text area exclusividade******************
		JLabel lblObservacoes = new JLabel("Observa\u00E7\u00F5es:");
		lblObservacoes.setBounds(274, 244, 98, 14);
		contentPane.add(lblObservacoes);
		
		textObservacao = new JTextArea();
		textObservacao.setLineWrap(true);
		textObservacao.setBackground(SystemColor.controlShadow);
		textObservacao.setBounds(274, 265, 258, 73);
		contentPane.add(textObservacao);
		
		//************************jCalendar*********************************
		calendar = new JCalendar();
		calendar.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				java.util.Date utilDate = (java.util.Date) calendar.getDate();
				java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
				//texObservacao.setText("date." + sqlDate);
				
				DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
				data = df.format(sqlDate);
				//data = (String) sqlDate;
				textObservacao.setText(" date " + data );
			}
		});
		calendar.setBounds(10, 36, 220, 137);
		contentPane.add(calendar);
		
		
		java.util.Date utilDate = (java.util.Date) calendar.getDate();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		//texObservacao.setText("date." + sqlDate);
		textObservacao.setText(" date " + sqlDate );
		
		
		//******* Numero Cliente*************************************
		
		JLabel lblNumeroCliente = new JLabel("Numero Cliente:");
		lblNumeroCliente.setBounds(47, 291, 90, 20);
		contentPane.add(lblNumeroCliente);
		
		textNumeroCliente = new JTextField();
		textNumeroCliente.setBounds(47, 316, 86, 20);
		contentPane.add(textNumeroCliente);
		textNumeroCliente.setColumns(10);
		
		btnPesquisaCliente = new JButton("P");
		btnPesquisaCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ProprietarioGestaoClienteGui();
			}
		});
		btnPesquisaCliente.setToolTipText("Pesquisa numero cliente.");
		btnPesquisaCliente.setBounds(10, 318, 27, 20);
		contentPane.add(btnPesquisaCliente);
		
		final JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//contentPane.setVisible(false);
				Window window = SwingUtilities.windowForComponent(btnSair);
				window.dispose();
			}
		});
		btnSair.setBounds(565, 315, 115, 23);
		contentPane.add(btnSair);
		
		textPrecoMenu = new JTextField();
		textPrecoMenu.setVisible(false);
		textPrecoMenu.setBounds(565, 178, 66, 20);
		contentPane.add(textPrecoMenu);
		textPrecoMenu.setColumns(10);
			
	}// fim iniciarFrame()
	
	
	
	/**
	 * método para limpar e colocar os valores iniciais do Evento 
	 * 
	 */
	public void resetForm (){
		textNumParticipantes.setText("15");
		textObservacao.setText("");
		textNumeroCliente.setText("");
		rdbtnMenu1.setSelected(true);
	}	// end resetForm
	
	/**
	 *Metodo para alterar o estado do evento e aceitar o mesmo.
	 *Quado o proprietario grava as alterações o estado é alterado de 0 para 1.
	 * 
	 * @param eventoAlterarEstado
	 */
	public void confirmarEvento(Evento eventoAlterarEstado){
		if (eventoAlterarEstado.getEstadoDoEvento() == 0){
			estadoDoEvento = 1;
		}
	}
	
	/**
	 * método que cria o novo evento de Festa de Aniversario
	 */
	public void criaNovoEventoAniversario(){
		
			
		festaPijama = new FestaPijama (Integer.parseInt(textNumeroCliente.getText()),
														data,
														Integer.parseInt(textNumParticipantes.getText()),
														menuFinal,
														exclusividade,
														textObservacao.getText(),
														estadoDoEvento );
		
		
		loadEventos ();										// preencher o hashMapEventos com os eventos no ficheiro 
		hashMapEventos.put(festaPijama.getEventoId(), festaPijama);		// addicionar o novo evento ao hashMapEventos
		gravarEvento(); 										// grava o evento da FestaDoPijama
		
	}// fim metodo
	
	
	/**
	 * método para serializar HashMap <Integer, Festapijama>
	 */
	
	public void gravarEvento() {
		// guardar proprietario no ficheiro
		try {
			new Utilidades();
			Utilidades.serialize(hashMapEventos, ficheiro);
		} catch (IOException e1) {
			System.out.print("Problemas a gravar ficeiro:");
			e1.printStackTrace();
		}
		
					
	} // fim gravarAniversario
	/**
	 * método para serializar HashMap <Integer, Evento>
	 * @param conjuntoObjetos - Map<Integer, Evento>
	 */
	
	public void gravarEvento(Map<Integer, Evento> conjuntoObjetos){
		// guardar proprietario no ficheiro
		try {
			new Utilidades();
			Utilidades.serialize(conjuntoObjetos, ficheiro);
		} catch (IOException e1) {
			System.out.print("Problemas a gravar ficeiro:");
			e1.printStackTrace();
		}	
					
	} // fim gravarAniversario

	/**
	 * deserializa Festas Pijama para um coleção HashMap
	 */
	
	public void loadEventos(){
		try {
  			
			new Utilidades();
			hashMapEventos = (Map<Integer, FestaPijama>) Utilidades.deserialize(ficheiro);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * metodo para alterar tipo de dados, String para data
	 * @param data - String
	 */
	
	public void parseStringToDate(String data){
		
		try {
			SimpleDateFormat formatter = new SimpleDateFormat ("yyyy/MM/dd");
			java.util.Date date = formatter.parse(data);
			//java.util.Date date = new SimpleDateFormat("yyyy/MM/dd").parse(data);
			this.calendar.setDate(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * metodo utilizado para aceder a uma folha de Exce e disponibilizar os menus e preços dinamicamente.
	 */
	public void getExcelSpreadSheet(){
		try {
			// criar o workbook
			Workbook wrk1 = Workbook.getWorkbook(new File ("Menus.xls"));
			
			
			Sheet folha2 = wrk1.getSheet(1);
		
			// obter os valores das cells
			Cell row2 = folha2 .getCell(0, 1);
            Cell row3 = folha2 .getCell(0, 2);
            Cell row4 = folha2 .getCell(0, 3);
            Cell row5 = folha2 .getCell(0, 4);
            Cell row6 = folha2 .getCell(0, 5);
            Cell row7 = folha2 .getCell(0, 6);
            Cell row8 = folha2 .getCell(1, 7);
           
            // imprimir of valores
            textPane1.setText( 	row2.getContents() + "\n" +
            					row3.getContents() + "\n" +
            					row4.getContents() + "\n" +
            					row5.getContents() + "\n" +
            					row6.getContents() + "\n" +
            					row7.getContents() + "\n" +
            					row8.getContents() + "€");  // linha do preço
            		
            // valor da variavel para o preço do menu1
            menu1 = Double.parseDouble(row8.getContents());
            
            //***********load excel menus2
            // obter os valores das cells
			Cell row2M2 = folha2 .getCell(2, 1);
            Cell row3M2 = folha2 .getCell(2, 2);
            Cell row4M2 = folha2 .getCell(2, 3);
            Cell row5M2 = folha2 .getCell(2, 4);
            Cell row6M2 = folha2 .getCell(2, 5);
            Cell row7M2 = folha2 .getCell(2, 6);
            Cell row8M2 = folha2 .getCell(3, 7);
            
         
           
            // imprimir of valores
            textPane2.setText(	row2M2.getContents() + "\n" +
            					row3M2.getContents() +	"\n" +
            					row4M2.getContents() + "\n" +
            					row5M2.getContents() + "\n" +
            					row6M2.getContents() + "\n" +
            					row7M2.getContents() + "\n" +
            					row8M2.getContents() + "€"); //preço do menu 2
         // valor da variavel para o preço do menu2
            menu2 = Double.parseDouble(row8M2.getContents());
            
            //***********load excel menus3
            // obter os valores das cells
			Cell row2M4 = folha2 .getCell(4, 1);
            Cell row3M4 = folha2 .getCell(4, 2);
            Cell row4M4 = folha2 .getCell(4, 3);
            Cell row5M4 = folha2 .getCell(4, 4);
            Cell row6M4 = folha2 .getCell(4, 5);
            Cell row7M4 = folha2 .getCell(4, 6);
            Cell row8M4 = folha2 .getCell(4, 7);
            Cell row9M4 = folha2 .getCell(5, 8);
                      
            // imprimir of valores
            textPane3.setText(row2M4.getContents() + "\n" +
            				row3M4.getContents() +	"\n" +
            				row4M4.getContents() + "\n" +
            				row5M4.getContents() + "\n" +
            				row6M4.getContents() + "\n" +
            				row7M4.getContents() + "\n" +
            				row8M4.getContents() + "\n" +
            				row9M4.getContents() );
            
            // valor da variavel para o preço do menu3
            //menu3 = Double.parseDouble(row9M4.getContents());
            
			
		} catch (BiffException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}// frim getExcelSpreadSheet ()f
}
