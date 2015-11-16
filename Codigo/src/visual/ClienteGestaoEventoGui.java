package visual;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import classes.Cliente;
import classes.Evento;

public class ClienteGestaoEventoGui extends JFrame implements ActionListener{

	private static final long serialVersionUID = 7283610462633701725L;
	private JPanel contentPane;
	private JTextArea textEcra;
	private JButton btnSair, btnApagar;
	//private static final String FICHEIRO_EVENTOS = "eventos.ser";
	private static HashMap<Integer, Evento> conjuntoEventos = new HashMap<Integer, Evento>();
	private JTextField textEventoId;
	private Evento evento;
	//private int utilizadorId;

	/**
	 * Class de interface grafica que o Cliente utiliza para gerir os seus Eventos
	 * @param conjuntoEventos
	 */
	public ClienteGestaoEventoGui(HashMap<Integer, Evento> conjuntoEventos){
		
		iniciarFrame();
		// carrega os eventos inicias do cliente		
		printEventos(ClienteGestaoEventoGui.conjuntoEventos = conjuntoEventos );
	}
	
	
	/**
	 * Create the frame.
	 */
	public void iniciarFrame() {
		
		setTitle("Gest\u00E3o de Eventos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 978, 329);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setVisible(true);
		this.setResizable(false);		
		this.setLocationRelativeTo(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 11, 926, 193);
		contentPane.add(scrollPane);
		
		textEcra = new JTextArea();
		textEcra.setEditable(false);
		textEcra.setFont(new Font("Courier New", Font.PLAIN, 13));
		textEcra.setBackground(SystemColor.activeCaption);
		scrollPane.setViewportView(textEcra);
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(this);
		btnSair.setBounds(863, 215, 89, 23);
		contentPane.add(btnSair);
		
		btnApagar = new JButton("Cancelar Evento");
		btnApagar.addActionListener(this);
		btnApagar.setBounds(693, 215, 140, 23);
		contentPane.add(btnApagar);
		
		textEventoId = new JTextField();
		textEventoId.setBounds(726, 241, 86, 20);
		contentPane.add(textEventoId);
		textEventoId.setColumns(10);
		
		JLabel lblEventoId = new JLabel("Evento Id:");
		lblEventoId.setBounds(641, 244, 78, 14);
		contentPane.add(lblEventoId);
	}// end frame
	
	/**
	 * método para implementar as acções dos botões
	 */
	
	public void actionPerformed (ActionEvent e){
		
		Object source = e.getSource();
		
		// butão para sair
		if (source == btnSair){
			this.dispose();
		}else if ( source == btnApagar){
			try{
				textEventoId.getText(); // valida a caixa de texto
				
				// procura o evento especifico que pertence ao cliente
				evento = Cliente.getEventoPorId(conjuntoEventos, textEventoId.getText());
				// evento não pode ter sido confirmado para poder apagar
				if ( evento.getEstadoDoEvento() == 0){
					conjuntoEventos = Cliente.removeEvento(conjuntoEventos, String.valueOf(evento.getEventoId()));
					gravarAlteracoes(conjuntoEventos);
					printEventos(conjuntoEventos);
					textEventoId.setText("");
				}
				else
					JOptionPane.showMessageDialog(null, "Este evento já não pode ser cancelado.", "ERRO-NÃO É POSSÍVEL ELIMINAR!", JOptionPane.ERROR_MESSAGE);
			}catch (NumberFormatException ei){ // caixa de texto vazia
				JOptionPane.showMessageDialog(null, "Caixa de Texto vazia", "ERRO", JOptionPane.ERROR_MESSAGE);
			}catch (NullPointerException ee){ // evento não existe
				JOptionPane.showMessageDialog(null, "Evento não existe", "ERRO", JOptionPane.ERROR_MESSAGE);
			}
			
			 
		}// fim else if
		
	}// fim metodo
	
	/**
	 * método para imprimir eventos
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
}
