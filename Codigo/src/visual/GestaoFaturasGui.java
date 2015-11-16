package visual;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import classes.*;
import javax.swing.JTextField;
import javax.swing.JLabel;

/**
 * Class de interface gráfica para gestão de faturas
 *
 */
public class GestaoFaturasGui extends JFrame implements ActionListener{

	private static final long serialVersionUID = 6770703040434428445L;
	private JPanel contentPane;
	private JTextArea textEcra;
	private JButton btnSair, btnAtualizar ;
	private JButton btnCriar;
	private static final String FICHEIRO_EVENTOS = "eventos.ser";
	private static HashMap<Integer, Evento> conjuntoEventos = new HashMap<Integer, Evento>();
	private static Evento evento;
	private JTextField textId;
	private JLabel lblNumeroDeFatura;
	private Fatura eventoParaFaturar;

	/**
	 * Construtor
	 * @param conjuntoEventos - HashMap<Integer, Evento>
	 */
	public GestaoFaturasGui(HashMap<Integer, Evento> conjuntoEventos){
		
		this.conjuntoEventos = conjuntoEventos;
		iniciarFrame();
		printEventos(conjuntoEventos );
	}
	
	/**
	 * Iniciar frame
	 */
	public void iniciarFrame() {
		setTitle("Gest\u00E3o de Eventos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 978, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setVisible(true);
		this.setResizable(false);		
		this.setLocationRelativeTo(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 11, 927, 290);
		contentPane.add(scrollPane);
		
		textEcra = new JTextArea();
		textEcra.setEditable(false);
		textEcra.setFont(new Font("Courier New", Font.PLAIN, 13));
		textEcra.setBackground(SystemColor.activeCaption);
		scrollPane.setViewportView(textEcra);
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(this);
		btnSair.setBounds(862, 332, 89, 23);
		contentPane.add(btnSair);
		
		btnCriar = new JButton("Criar");
		btnCriar.addActionListener(this);
		btnCriar.setBounds(590, 364, 89, 23);
		contentPane.add(btnCriar);
		
		textId = new JTextField();
		textId.setBounds(593, 333, 86, 20);
		contentPane.add(textId);
		textId.setColumns(10);
		
		btnAtualizar = new JButton("Atualizar Ecra");
		btnAtualizar.addActionListener(this);
		btnAtualizar.setBounds(25, 332, 143, 23);
		contentPane.add(btnAtualizar);
		
		lblNumeroDeFatura = new JLabel("Numero de Evento:");
		lblNumeroDeFatura.setBounds(463, 336, 120, 14);
		contentPane.add(lblNumeroDeFatura);
	}// end frame
	
	/**
	 * método que implementa as acções dos botões
	 */	
	public void actionPerformed (ActionEvent e){
		
		Object source = e.getSource();
		
		// butão para sair
		if (source == btnSair){
			this.dispose();
		}
		else if (source == btnCriar){
			//printEventos(getConjuntoEventos());
			evento = Proprietario.getEventoPorId(conjuntoEventos, textId.getText());
			eventoParaFaturar = new Fatura(evento , 0.05);
			
			// imprime fatura
			imprimeFatura(eventoParaFaturar);
			
			// guarda a alteração do estado do evento de 1 para 4
			this.conjuntoEventos.put( evento.getEventoId(), evento);
			gravarAlteracoes(conjuntoEventos);   // novo estado do Evento alterado
		}
		else if (source == btnAtualizar){
			printEventos (Proprietario.getEventosPorEstado( conjuntoEventos, 1));
		}
		
	}
	
	/**
	 * método para imprimir Eventos
	 * @param eventosHashMap - HashMap<Integer, Evento>
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
		 * gravar conjuntas Eventos depois de realizadas as alterações
		 * @param conjuntoEventos
		 */
		public void gravarAlteracoes(HashMap<Integer, Evento> conjuntoEventos){
			// guardar proprietario no ficheiro
			try {
				new Utilidades();
				Utilidades.serialize( conjuntoEventos, "eventos.ser");
			} catch (IOException e1) {
				System.out.print("Problemas a gravar ficeiro:");
				e1.printStackTrace();
			}

						
		} // fim
		
		public  void imprimeFatura (Fatura evento){
			textEcra.setText("");  // limpar a zona do JTextArea
			textEcra.append(String.valueOf(eventoParaFaturar));
		}
}
