package visual;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import classes.Evento;
import classes.Proprietario;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;

/**
 * Class de interface gráfica para o menu do Proprietario
 * 
 *
 */
public class MenuProprietarioGui extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnCriarCliente, btnGerirCliente, 
					btnCriarFestaAniversario, btnGerirEventos,
					btnGerirFaturas, btnSair, btnNovoEmpregado,
					btnCriarFestaPijama;
	
	private static final String FICHEIRO_EVENTOS = "eventos.ser";
	private static HashMap<Integer, Evento> conjuntoEventos = new HashMap<Integer, Evento>();

	
	/**
	 * Create the frame.
	 */
	public MenuProprietarioGui() {
		setTitle("Menu Proprietario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 303, 342);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocation(0, 0);
		this.setVisible(true);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBackground(SystemColor.activeCaption);
		lblCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCliente.setBounds(0, 11, 89, 14);
		contentPane.add(lblCliente);
		
		JLabel lblEventos = new JLabel("Eventos");
		lblEventos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEventos.setHorizontalAlignment(SwingConstants.CENTER);
		lblEventos.setBounds(99, 11, 89, 14);
		contentPane.add(lblEventos);
		
		JLabel lblFaturao = new JLabel("Fatura\u00E7\u00E3o");
		lblFaturao.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFaturao.setHorizontalAlignment(SwingConstants.CENTER);
		lblFaturao.setBounds(198, 13, 89, 14);
		contentPane.add(lblFaturao);
		
		btnCriarCliente = new JButton("Criar");
		btnCriarCliente.addActionListener(this);
		btnCriarCliente.setBounds(0, 54, 89, 23);
		contentPane.add(btnCriarCliente);
		
		btnGerirCliente = new JButton("Gerir");
		btnGerirCliente.addActionListener(this);
		btnGerirCliente.setBounds(0, 88, 89, 23);
		contentPane.add(btnGerirCliente);
		
		btnCriarFestaAniversario = new JButton("Anivers\u00E1rio");
		btnCriarFestaAniversario.addActionListener(this);
		btnCriarFestaAniversario.setBounds(99, 54, 89, 23);
		contentPane.add(btnCriarFestaAniversario);
		
		btnGerirEventos = new JButton("Gerir");
		btnGerirEventos.addActionListener(this);
		btnGerirEventos.setBounds(99, 122, 89, 23);
		contentPane.add(btnGerirEventos);
		
		btnGerirFaturas = new JButton("Gerir");
		btnGerirFaturas.addActionListener(this);
		btnGerirFaturas.setBounds(198, 54, 89, 23);
		contentPane.add(btnGerirFaturas);
		
		btnNovoEmpregado = new JButton("Novo Empregado");
		btnNovoEmpregado.addActionListener(this);
		btnNovoEmpregado.setBounds(0, 229, 137, 23);
		contentPane.add(btnNovoEmpregado);
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(this);
		btnSair.setBounds(198, 269, 89, 23);
		contentPane.add(btnSair);
		
		btnCriarFestaPijama = new JButton("Pijama");
		btnCriarFestaPijama.addActionListener(this);
		btnCriarFestaPijama.setBounds(99, 88, 89, 23);
		contentPane.add(btnCriarFestaPijama);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 36, 287, 2);
		contentPane.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 224, 287, 2);
		contentPane.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(0, 258, 287, 2);
		contentPane.add(separator_3);
	
	}// fim de frame
	
	/**
	 * método para implementar as acções dos botões
	 */
	public void actionPerformed (ActionEvent e){
		
		Object source = e.getSource();
		// criar cliente
		if (source == btnCriarCliente ){
			new ClienteGui();
		}
		// gerir clientes
		else if (source == btnGerirCliente){
			new ProprietarioGestaoClienteGui();			
		}
		// criar Festa Aniversario
		else if (source == btnCriarFestaAniversario){
			new EventoAniversarioGui(getConjuntoEventos());
		}
		// criar Festa Pijama
		else if (source == btnCriarFestaPijama){
			new EventoFestaPijamaGui();
		}
		// gerir eventos
		else if (source == btnGerirEventos){
			new ProprietarioGestaoEventosGui(getConjuntoEventos());
		}
		// gerir fatura
		else if (source == btnGerirFaturas){
			//só Eventos com estado = 1;
			new GestaoFaturasGui( Proprietario.getEventosPorEstado( getConjuntoEventos(), 1));
		}
		else if (source == btnNovoEmpregado){
			new NovoEmpregadoGui();
		}
		// sair
		else if (source == btnSair){
			System.exit(0);
		}
	}
	
	/**
	 * metodo para preencher um HashMap com o conjunto de Eventos
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
}
