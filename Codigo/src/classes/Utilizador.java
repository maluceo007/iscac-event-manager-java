package classes;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;


// TODO: Auto-generated Javadoc
/**
 * The Class Utilizador.
 */
public class Utilizador implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The nome. */
	private String nome;
	
	/** The apelido. */
	private String apelido;
	
	/** The utilizador. */
	private String utilizador;
	
	/** The password. */
	private String password;
	
	/**
	 * construtor.
	 *
	 * @param nome -String
	 * @param apelido -String
	 * @param utilizador -String
	 * @param password -String
	 */
	
	public Utilizador(String nome, String apelido, String utilizador,String password) {
		this.nome = nome;
		this.apelido = apelido;
		this.utilizador = utilizador;
		this.password = password;
	}

	// getters
	/**
	 * Gets the nome.
	 *
	 * @return the nome
	 */
	public String getNome() {return nome;}
	
	/**
	 * Gets the apelido.
	 *
	 * @return the apelido
	 */
	public String getApelido() {return apelido;	}
	
	/**
	 * Gets the utilizador.
	 *
	 * @return the utilizador
	 */
	public String getUtilizador() {	return utilizador;}
	
	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {return password;}
	
	// setters
	/**
	 * Sets the nome.
	 *
	 * @param nome the new nome
	 */
	public void setNome(String nome) {this.nome = nome;	}	
	
	/**
	 * Sets the apelido.
	 *
	 * @param apelido the new apelido
	 */
	public void setApelido(String apelido) {this.apelido = apelido;	}	
	
	/**
	 * Sets the utilizador.
	 *
	 * @param utilizador the new utilizador
	 */
	public void setUtilizador(String utilizador) {this.utilizador = utilizador;	}	
	
	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {this.password = password;}	
	
	/**
	 * metodo utilizado para apagar Eventos atraves da identificacão do seu id.
	 *
	 * @param conjuntoEventos - HashMap <Integer, Evento>
	 * @param id - String
	 * @return - HashMap <Integer, Evento>
	 */	
	public static HashMap <Integer, Evento> removeEvento (HashMap <Integer, Evento> conjuntoEventos, String id){
		conjuntoEventos.remove( Integer.parseInt(id));
		return conjuntoEventos;
	}
	
	/**
	 * metodo para pesquisra por id um HashMap de Eventos .
	 *
	 * @param objetoColecao - HashMap de Eventos
	 * @param id - id do Evento
	 * @return Evento ou null
	 */
	public static Evento getEventoPorId (HashMap<Integer, Evento> objetoColecao, String id){		
		
		Set<Integer>  set = objetoColecao.keySet();
		Iterator <Integer> iterador = set.iterator();		
		
		while( iterador.hasNext()){
			Integer chave = (Integer) iterador.next();
			Evento valores = (Evento) objetoColecao.get(chave);
			
			if (valores.getEventoId() == Integer.parseInt(id)){
			   return valores;
			}
			
		}// fim while
		
		return null;
		
	}
	
}
