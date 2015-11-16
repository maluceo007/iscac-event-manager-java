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
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import jxl.*;
import jxl.read.biff.BiffException;
import classes.*;
import com.toedter.calendar.JCalendar;

/**
 * Class de interface gráfica para criar e atualizar um evento de Festa de Aniversário 
 *
 */
public class EventoAniversarioGui extends JFrame {
	
	private JPanel contentPane;
	private JRadioButton rdbtnMenu1, rdbtnMenu2, rdbtnMenu3;	// radio buttons
	private ButtonGroup rdbtnGrupo;  							// grupo radio buttons
	private JTextPane textPane1,textPane2,textPane3;	
	private JTextField textNumParticipantes,textNumeroCliente;
	private JTextArea textObservacao;
	private JLabel lblDuracaoEvento, lblMostrarValidade,lblNumeroParticipantes;
	private JCheckBox chckbxExclusividade;	
	private JCalendar calendar;
	private JComboBox <String> horaFim;
	private JComboBox <String>horaInicio;
	private JComboBox <String>minutosInicio;
	private JComboBox <String>minutosFim;
	private String hIncio, mIncio, hfim, mfim,data;			// variaveis para jComboBox output	
	// arrays para preencher as JComboBox
	private String[] horaInicioFesta = new String[]{"", "11","12","13","14","15","16","17","18","19","20"};
	private String[] horaFimFesta = new String []{"","12","13","14","15","16","17","18","19","20","21","22"};
	private String[] minutosFesta = new String [] {"","00","15","30","45"};
	private JButton btnValidadDuracao,btnPesquisaCliente;	
	private Boolean exclusividade = false;
	private JLabel lblM, lblM_1;
	private int totalMinutos,numParticipantes, estadoDoEvento;  // numero total de minutos de duração da festa de pijama
	private Aniversario festaAniversario;
	private static String ficheiro = "eventos.ser";
	private double menu1, menu2, menu3, menuFinal;  			// preço dos menus
	private static Map <Integer, Aniversario> hashMapAniversario= new HashMap<Integer, Aniversario> ();
	private static HashMap <Integer, Evento> hashMapEventos= new HashMap<Integer, Evento> ();
	private Cliente utilizadorCliente;
	
	/**
	 * Construtor
	 */
	public EventoAniversarioGui (HashMap <Integer, Evento> hashMapEventos){
		this.hashMapEventos = hashMapEventos;
		estadoDoEvento = 1;
		iniciarFrame();
	}
	
	/**
	 * Construtor para o Cliente criar o Evento
	 * @param utilizadorCliente
	 */
	public EventoAniversarioGui ( Cliente utilizadorCliente){
		
		estadoDoEvento = 0;
		this.utilizadorCliente = utilizadorCliente;
		iniciarFrame();
		textNumeroCliente.setText(Integer.toString(this.utilizadorCliente.getClienteID()));
		textNumeroCliente.setEditable(false);
		btnPesquisaCliente.setVisible(false);
	}
	
	/**
	 * Create the frame.
	 */
	public void iniciarFrame() {
		setTitle("Festa Anivers\u00E1rio");
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
		//dateChooser.setDate(arg0)		
		
		JLabel lblHoraInicio = new JLabel("Hora Inicio:");
		lblHoraInicio.setBounds(10, 188, 75, 14);
		contentPane.add(lblHoraInicio);
		
		JLabel lblHoraFim = new JLabel("Hora Fim:");
		lblHoraFim.setBounds(10, 219, 75, 14);
		contentPane.add(lblHoraFim);
		 
		//**********************jComboBox******************************
		//displayComboBox();
		
		horaInicio  = new JComboBox (horaInicioFesta);
		horaInicio.setSelectedIndex(0);
		horaInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hIncio = horaInicio.getSelectedItem().toString();
			}
		});
		horaInicio.setBounds(115, 182, 46, 20);
		contentPane.add(horaInicio);
		
		horaFim = new JComboBox (horaFimFesta);
		horaFim.setSelectedIndex(0);
		horaFim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hfim = horaFim.getSelectedItem().toString();
			}
		});
		horaFim.setBounds(115, 213, 46, 20);
		contentPane.add(horaFim);
		
		minutosInicio = new JComboBox(minutosFesta);
		minutosInicio.setSelectedIndex(0);
		minutosInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mIncio = minutosInicio.getSelectedItem().toString();
			}
		});
		minutosInicio.setBounds(184, 182, 46, 20);
		contentPane.add(minutosInicio);
		
		minutosFim = new JComboBox (minutosFesta);
		minutosFim.setSelectedIndex(0);
		minutosFim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mfim = minutosFim.getSelectedItem().toString();
			}
		});
		minutosFim.setBounds(184, 213, 46, 20);
		contentPane.add(minutosFim);
		
		//**********************Validacão data******************************
		lblDuracaoEvento = new JLabel("Tempo Dura\u00E7\u00E3o do Evento");
		lblDuracaoEvento.setHorizontalAlignment(SwingConstants.CENTER);
		lblDuracaoEvento.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblDuracaoEvento.setBounds(20, 244, 191, 28);
		contentPane.add(lblDuracaoEvento);
		
		// label the validação para a data
		lblMostrarValidade = new JLabel("");
		lblMostrarValidade.setBackground(SystemColor.activeCaption);
		lblMostrarValidade.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMostrarValidade.setHorizontalAlignment(SwingConstants.CENTER);
		lblMostrarValidade.setBounds(47, 265, 131, 20);
		contentPane.add(lblMostrarValidade);
		
		
		// botão de validação ************************************
		//********************************************************
		btnValidadDuracao = new JButton("Gravar");
		btnValidadDuracao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean validacaoAniversario = true;
				
				// validar duração do evento
				if (!Evento.validarDuracaoEvento(hIncio, mIncio, hfim, mfim)){
					if (hIncio!=null &&  mIncio != null &&  hfim != null && mfim != null)
						lblMostrarValidade.setText (parseDataToString( hIncio, mIncio, hfim, mfim));
					JOptionPane.showMessageDialog(null, "A festa tem que durar entre 1 a 3 horas.", "ERRO-DURAÇÃO EVENTO!", JOptionPane.ERROR_MESSAGE);					
					validacaoAniversario = false;
					return;
				}else{
					
					lblMostrarValidade.setText (parseDataToString( hIncio, mIncio, hfim, mfim));
				}
				// validar numero participantes
				numParticipantes = Integer.parseInt(textNumParticipantes.getText());
				if (numParticipantes  < 15){
					JOptionPane.showMessageDialog(null, "A festa tem minimo de 15 participantes.", "ERRO-NUMERO PARTICIPANTES!", JOptionPane.ERROR_MESSAGE);
					validacaoAniversario = false;
					return;
				}
				// informar exclusividade
				if (chckbxExclusividade.isSelected()== true && numParticipantes < 50){
					if (JOptionPane.showConfirmDialog(null, "A festa so tem "+ numParticipantes + "!", "CUIDADO EXCLUSIVIDADE!",
					        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					    	numParticipantes = 50;	
					    	exclusividade = true;
					} else {
					    validacaoAniversario = false;
					    return;
					}
				
				}
				// validar se existe um cliente inserido
				if (textNumeroCliente.getText().length()== 0){
					JOptionPane.showMessageDialog(null, "O Evento tem que ter um cliente.", "ERRO-NUMERO CLIENTE!", JOptionPane.ERROR_MESSAGE);
					validacaoAniversario = false;
				
				} // validar se o cliente existe
				else
					try {
						if ( !Proprietario.validateClienteId ((HashMap<Integer, Cliente>)new Utilidades().deserialize("clientes.ser"),
																	Integer.parseInt(textNumeroCliente.getText()))){
							JOptionPane.showMessageDialog(null, "O Cliente não existe.", "ERRO-NUMERO CLIENTE!", JOptionPane.ERROR_MESSAGE);
							validacaoAniversario = false;
						}
					} catch (NumberFormatException | HeadlessException
							| ClassNotFoundException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}			
				
				
				// evento valido - criar evento
				if (validacaoAniversario){
					// criar o novo Evento
					criaNovoEventoAniversario();				

					//System.out.print(String.format("%-8s%-9s%-13s%-10s%-10s%-10s%-10s%-12s%-7s%-8s%-40s%n",
					//		"Evento", "Cliente", "Data", "Hora", "Duração", "# Partc", "Prc Mnu", "Total", "Excl","Estado", "Lembrete"));
					
					
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
				
		rdbtnMenu3 = new JRadioButton("Menu 3");
		rdbtnMenu3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuFinal = menu3;
			}
		});
		rdbtnMenu3.setBounds(565, 174, 109, 23);
		contentPane.add(rdbtnMenu3);
		// criar grupo de botões
		rdbtnGrupo = new ButtonGroup();
		rdbtnGrupo.add(rdbtnMenu1);
		rdbtnGrupo.add(rdbtnMenu2);
		rdbtnGrupo.add(rdbtnMenu3);	
		
		//********************text participantes***************
		lblNumeroParticipantes = new JLabel("Numero participantes:");
		lblNumeroParticipantes.setBounds(274, 213, 115, 14);
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
		
		JLabel lblH = new JLabel("H-");
		lblH.setBounds(95, 188, 17, 14);
		contentPane.add(lblH);
		
		JLabel label = new JLabel("H-");
		label.setBounds(95, 219, 17, 14);
		contentPane.add(label);
		
		lblM = new JLabel("m-");
		lblM.setBounds(161, 188, 17, 14);
		contentPane.add(lblM);
		
		lblM_1 = new JLabel("m-");
		lblM_1.setBounds(161, 219, 17, 14);
		contentPane.add(lblM_1);
			
	}// fim iniciarFrame()
	
	/**
	 * método utilizado para converter o tempo de duração do evento das caixas de texto para string
	 * 
	 * @param hInicio - hora inicio
	 * @param mInicio - minutos inicio
	 * @param hfim - hora fim
	 * @param mfim - hora inicio
	 * @return - String com tempo de duração do Evento
	 */
	public  String parseDataToString ( String hInicio, String mInicio, 
										 String hfim, String mfim){
		
		totalMinutos = (Integer.parseInt(hfim)*60 + Integer.parseInt(mfim))-(Integer.parseInt(hIncio)*60 + Integer.parseInt(mIncio));
		return ( totalMinutos/60 + ":" + totalMinutos%60 );		
	}
	
	
	
	/**
	 * método para limpar e colocar os valores iniciais do Evento 
	 * 
	 */
	public void resetForm (){
		horaInicio.setSelectedIndex(0);	
		horaFim.setSelectedIndex(0);	
		minutosInicio.setSelectedIndex(0);	
		minutosFim.setSelectedIndex(0);	
		lblMostrarValidade.setText("");
		textNumParticipantes.setText("15");
		chckbxExclusividade.setText("");
		textObservacao.setText("");
		textNumeroCliente.setText("");
		rdbtnMenu1.setSelected(true);
	}	// end resetForm
	/**
	 * método que cria o novo evento de Festa de Aniversario
	 */
	public void criaNovoEventoAniversario(){
		
		String horaInicio = hIncio +":" + mIncio;
		festaAniversario = new Aniversario (Integer.parseInt(textNumeroCliente.getText()),
														data,
														Integer.parseInt(textNumParticipantes.getText()),
														menuFinal,
														exclusividade,
														textObservacao.getText(),
														estadoDoEvento,
														horaInicio,
														totalMinutos);
		
		loadEventoAniversario();  												// preencher o hashMapEventos com os eventos no ficheiro 	
		hashMapAniversario.put(festaAniversario.getEventoId(), festaAniversario);	// addicionar o novo evento ao hashMapEventos
		gravarEventoAniversario(); 												// grava o evento do aniversario
		
	}// fim metodo
	
	public void gravarEventoAniversario(){
		// guardar proprietario no ficheiro
		try {
			new Utilidades();
			Utilidades.serialize(hashMapAniversario, ficheiro);
		} catch (IOException e1) {
			System.out.print("Problemas a gravar ficeiro:");
			e1.printStackTrace();
		}

					
	} // fim gravarAniversario
	
	public void loadEventoAniversario(){
		try {
  			
			new Utilidades();
			hashMapAniversario = (Map<Integer, Aniversario>) Utilidades.deserialize(ficheiro);
		} catch (ClassNotFoundException | IOException e) {
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
			
			
			Sheet folha1 = wrk1.getSheet(0);
		
			// obter os valores das cells
			Cell row2 = folha1 .getCell(0, 1);
            Cell row3 = folha1 .getCell(0, 2);
            Cell row4 = folha1 .getCell(0, 3);
            Cell row5 = folha1 .getCell(0, 4);
            Cell row6 = folha1 .getCell(0, 5);
            Cell row7 = folha1 .getCell(0, 6);
            Cell row8 = folha1 .getCell(1, 7);
           
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
			Cell row2M2 = folha1 .getCell(2, 1);
            Cell row3M2 = folha1 .getCell(2, 2);
            Cell row4M2 = folha1 .getCell(2, 3);
            Cell row5M2 = folha1 .getCell(2, 4);
            Cell row6M2 = folha1 .getCell(2, 5);
            Cell row7M2 = folha1 .getCell(2, 6);
            Cell row8M2 = folha1 .getCell(3, 7);
            
         
           
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
			Cell row2M4 = folha1 .getCell(4, 1);
            Cell row3M4 = folha1 .getCell(4, 2);
            Cell row4M4 = folha1 .getCell(4, 3);
            Cell row5M4 = folha1 .getCell(4, 4);
            Cell row6M4 = folha1 .getCell(4, 5);
            Cell row7M4 = folha1 .getCell(4, 6);
            Cell row8M4 = folha1 .getCell(4, 7);
            Cell row9M4 = folha1 .getCell(5, 8);
                      
            // imprimir of valores
            textPane3.setText(row2M4.getContents() + "\n" +
            				row3M4.getContents() +	"\n" +
            				row4M4.getContents() + "\n" +
            				row5M4.getContents() + "\n" +
            				row6M4.getContents() + "\n" +
            				row7M4.getContents() + "\n" +
            				row8M4.getContents() + "\n" +
            				row9M4.getContents() + "€");
            
            // valor da variavel para o preço do menu3
            menu3 = Double.parseDouble(row9M4.getContents());
            
			
		} catch (BiffException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}// frim getExcelSpreadSheet ()f
}
