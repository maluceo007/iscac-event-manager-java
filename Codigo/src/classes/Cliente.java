package classes;


import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import visual.*;


// TODO: Auto-generated Javadoc
/**
 * The Class Cliente.
 */
public class Cliente extends Utilizador  {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4223203251486708400L;
	
	/** The email. */
	private String email;
	
	/** The nib. */
	private int nif;
	
	/** The contacto. */
	private int contacto;
	
	/** The morada. */
	private String morada;
	
	/** The codigo postal. */
	private String codigoPostal;	
	
	/** The cliente id. */
	private int clienteID;	
	
	/** The ficheiro. */
	private static String ficheiro = "clientes.ser";
	
	/** The id generator. */
	private static AtomicInteger ID_GENERATOR = new AtomicInteger(Utilidades.ultimoId("ultimoId.txt"));
	
	
	/**
	 * Instantiates a new cliente.
	 *
	 * @param nome the nome
	 * @param apelido the apelido
	 * @param utilizador the utilizador
	 * @param password the password
	 * @param email the email
	 * @param nib the nib
	 * @param contacto the contacto
	 * @param morada the morada
	 * @param codigoPostal the codigo postal
	 */
	public Cliente(String nome, String apelido,String utilizador, String password,	
				String email,int nib, int contacto, String morada,String codigoPostal){
		
		super (nome,apelido,utilizador, password);
		this.email = email;
		this.nif = nib;
		this.contacto = contacto;
		this.morada = morada;
		this.codigoPostal = codigoPostal;
		Utilidades.atualizaUltimoId("ultimoId.txt");
		this.clienteID = ID_GENERATOR.getAndIncrement();
		
	}
	
	// getters
	/**
	 * Gets the cliente id.
	 *
	 * @return the cliente id
	 */
	public int getClienteID() { return clienteID;}	
	
	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {	return email; }
	
	/**
	 * Gets the nif.
	 *
	 * @return the nif
	 */
	public int getNib() { return nif;}
	
	/**
	 * Gets the contacto.
	 *
	 * @return the contacto
	 */
	public int getContacto() {return contacto;}
	
	/**
	 * Gets the morada.
	 *
	 * @return the morada
	 */
	public String getMorada() {	return morada;}
	
	/**
	 * Gets the codigo postal.
	 *
	 * @return the codigo postal
	 */
	public String getCodigoPostal() {return codigoPostal;}
	
	/**
	 * Gets the ficheiro.
	 *
	 * @return the ficheiro
	 */
	public static String getFicheiro() {return ficheiro;}

	// setters	
	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {this.email = email;	}
	
	/**
	 * Sets the nib.
	 *
	 * @param nib the new nib
	 */
	public void setNib(int nib) {this.nif = nib;}
	
	/**
	 * Sets the contacto.
	 *
	 * @param contacto the new contacto
	 */
	public void setContacto(int contacto) {	this.contacto = contacto;}
	
	/**
	 * Sets the morada.
	 *
	 * @param morada the new morada
	 */
	public void setMorada(String morada) {this.morada = morada;	}
	
	/**
	 * Sets the codigo postal.
	 *
	 * @param codigoPostal the new codigo postal
	 */
	public void setCodigoPostal(String codigoPostal) {this.codigoPostal = codigoPostal;	}
	
	/**
	 * metodo para atualizar o cadatro do cliente.
	 *
	 * @param utilizador - o actual cliente validado
	 * @param conjuntoClientes - conjunto de clientes
	 */
	public static void atualizarCadastro(Cliente utilizador, HashMap <Integer,Cliente> conjuntoClientes){
	
		new ClienteGui(utilizador, conjuntoClientes);
	}
	
	/**
	 * Cria nova festa pijama.
	 *
	 * @param utilizador the utilizador
	 */
	public static void criaNovaFestaPijama (Cliente utilizador){
		new EventoFestaPijamaGui(utilizador);
	}
	
	/**
	 * Cria nova festa aniversario.
	 *
	 * @param utilizador the utilizador
	 */
	public static void criaNovaFestaAniversario(Cliente utilizador){
		new EventoAniversarioGui(utilizador);
	}
	
	/**
	 * metodo para realizar busca dos eventos que pertencem ao utilizador.
	 *
	 * @param objetoColecao - HashMap com os eventos
	 * @param utilizador - id do utilizador
	 * @return HashMap < Integer, Evento>
	 */
	public static HashMap < Integer, Evento> getEventosDoCliente(HashMap<Integer, Evento> objetoColecao, int utilizador){
				
		HashMap < Integer, Evento> eventosPorClinteId = new HashMap <Integer, Evento>();
			
		Set<Integer>  set = objetoColecao.keySet();
		Iterator <Integer> iterador = set.iterator();		
			
		while( iterador.hasNext()){
			Integer chave = (Integer) iterador.next();
			Evento valores = (Evento) objetoColecao.get(chave);
				
			if (valores.getClienteId()== utilizador){
				eventosPorClinteId.put(chave, valores);
			}
		}// fim while
			
		return eventosPorClinteId;
		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		
		return String.format("%-5s%-18s%-18s%-25s%-10s%-15s%-25s%-15s%n", 
				this.clienteID, super.getNome() , super.getApelido(), this.email, this.nif, this.contacto, this.morada,this.codigoPostal);
	}// fim toString()
	
}
