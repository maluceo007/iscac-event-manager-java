package classes;
import java.util.*;
import visual.EventoFestaPijamaGui;
import visual.ClienteGui;


// TODO: Auto-generated Javadoc
/**
 * The Class Proprietario.
 */
public class Proprietario extends Utilizador{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3880251135628057465L;
	/** The ficheiro. */
	private static String ficheiro = "empregados.ser";
	//private static Map < Integer, Evento> eventos = new HashMap <Integer, Evento>();
	/** The clientes. */
	private static ArrayList <Cliente> clientes = new ArrayList<Cliente>();
	//private static ArrayList <Evento> eventoArrayList = new ArrayList <Evento>();

	/**
	 * Construtor para o Proprietario.
	 *
	 * @param nome String
	 * @param apelido String
	 * @param utilizador String
	 * @param password String
	 */
	public Proprietario (String nome, String apelido, String utilizador, String password){
		super (nome, apelido,utilizador, password );
	}
	
	/**
	 * Getter.
	 *
	 * @return String
	 */
	public static String getFicheiro() {return ficheiro;}
	
	/**
	 * Gets the cliente por id.
	 *
	 * @param objetoColecao HashMap<Integer, Cliente>
	 * @param valorInserido String
	 * @return ArrayList<Cliente>
	 */	
	public static ArrayList<Cliente> getClientePorID ( HashMap<Integer, Cliente> objetoColecao,	
													   String valorInserido){	
		Set<Integer>  set = objetoColecao.keySet();
		Iterator <Integer> iterador = set.iterator();		
		
		while( iterador.hasNext()){
			Integer chave = (Integer) iterador.next();
			Cliente valores = (Cliente) objetoColecao.get(chave);
			
			if (valores.getClienteID() == Integer.parseInt(valorInserido)){
				Proprietario.clientes.add(valores);
			}
		}// fim while
		
		return clientes;
	}// fim metodo
	
	/**
	 * metodo para validar se o clienteId já existe.
	 *
	 * @param objetoColecao HashMap <Integer, Evento>
	 * @param id int
	 * @return  boolean
	 */
	public static boolean validateClienteId (HashMap <Integer, Cliente> objetoColecao, int id){
		
		Set<Integer>  set = objetoColecao.keySet();
		Iterator <Integer> iterador = set.iterator();		
		
		while( iterador.hasNext()){
			Integer chave = (Integer) iterador.next();
			Cliente valores = (Cliente) objetoColecao.get(chave);
			
			if (valores.getClienteID()== id){
				return true;
			}
		}// fim while
		return false;
	} // end metodo
	
	/**
	 * metodo para pesquisar Eventos pro ClienteId.
	 *
	 * @param objetoColecao - HashMap<Integer, Evento> conjunto de Eventos
	 * @param clienteId String
	 * @return  HashMap < Integer, Evento>
	 */
	public static HashMap < Integer, Evento> getEventosPorClienteId (HashMap<Integer, Evento> objetoColecao, String clienteId){
		
		HashMap < Integer, Evento> eventosPorClinteId = new HashMap <Integer, Evento>();
		
		Set<Integer>  set = objetoColecao.keySet();
		Iterator <Integer> iterador = set.iterator();		
		
		while( iterador.hasNext()){
			Integer chave = (Integer) iterador.next();
			Evento valores = (Evento) objetoColecao.get(chave);
			
			if (valores.getClienteId()== Integer.parseInt(clienteId)){
				eventosPorClinteId.put(chave, valores);
			}
		}// fim while
		
		return eventosPorClinteId;
	}
	
	/**
	 * metodo para pesquisar Eventos que iniciam durante uma certa hora.
	 *
	 * @param objetoColecao HashMap<Integer, Object>  - conjunto de Eventos
	 * @param horaParaPesquisar String- hora para pesquisar em
	 * @return HashMap < Integer,Object>
	 */
	public static HashMap < Integer,Object> getEventosPorHora (HashMap<Integer, Object> objetoColecao, String horaParaPesquisar){
		
		HashMap < Integer, Object> eventosPorHora = new HashMap <Integer, Object>();		
		int horaPesquisar = Integer.parseInt(horaParaPesquisar) *60;
		
		Set<Integer>  set = objetoColecao.keySet();
		Iterator <Integer> iterador = set.iterator();			
		
		while( iterador.hasNext()){
			Integer chave = (Integer) iterador.next();
			Aniversario valores = (Aniversario) objetoColecao.get(chave);			
			
			// separa a hora do Eveto que esta guardada com String em minutos 
			int horaEvento = convertStringToTime (valores.getHoraInicio().substring(0, 2), valores.getHoraInicio().substring(3, 5));
			System.out.print ("horaEvento=" + horaEvento + " horaPesquisa:" + horaPesquisar);
			if (horaEvento >= horaPesquisar && horaEvento < horaPesquisar + 60){
				eventosPorHora.put(chave, valores);
			}
			
		}// fim while
		
		return eventosPorHora;
	}// fim metodo
	
	/**
	 * Gets the eventos por estado.
	 *
	 * @param objetoColecao the objeto colecao
	 * @param estado the estado
	 * @return the eventos por estado
	 */
	public static HashMap <Integer, Evento> getEventosPorEstado (HashMap <Integer, Evento> objetoColecao , int estado ){
		HashMap <Integer, Evento> eventosParaConfirmar = new HashMap <Integer, Evento>();
		
			Set<Integer> set = objetoColecao.keySet();
			Iterator <Integer> iterador = set.iterator();
		
			while (iterador.hasNext()){
				Integer chave = (Integer) iterador.next();
				Evento valores = (Evento) objetoColecao.get(chave);
			
				if (valores.getEstadoDoEvento() == estado){
					eventosParaConfirmar.put(chave, valores);
				}
			
			}	
		
			return eventosParaConfirmar;
		
	}// fim metodo
	
	/**
	 * metodo para pesquisar Eventos que iniciam numa determinada data.
	 *
	 * @param objetoColecao - HashMap <Integer, Evento> conjunto de Eventos
	 * @param data - String
	 * @return HashMap <Integer, Evento>
	 */
	public static HashMap <Integer, Evento> getEventosPorData (HashMap <Integer, Evento> objetoColecao, String data){
		HashMap <Integer, Evento> eventosPorData = new HashMap <Integer, Evento>();
		
		Set<Integer> set = objetoColecao.keySet();
		Iterator <Integer> iterador = set.iterator();
		
		while (iterador.hasNext()){
			Integer chave = (Integer) iterador.next();
			Evento valores = (Evento) objetoColecao.get(chave);
			
			if (valores.getDataEvento().equals(data)){
				eventosPorData.put(chave, valores);
			}
			
		}	
		
		return eventosPorData;
	}// fim metodo
	
	
	
	/**
	 * metodo para pesquisar Clientes por Apelido.
	 *
	 * @param objetoColecao - HashMap<Integer, Cliente> conjunto Clientes
	 * @param valorInserido - String
	 * @return ArrayList<Cliente>
	 */	
	public static ArrayList<Cliente> getClientesPorApelido ( HashMap<Integer, Cliente> objetoColecao,	
			   												 String valorInserido){	
		Set<Integer>  set = objetoColecao.keySet();
		Iterator <Integer> iterador = set.iterator();		
		
		while( iterador.hasNext()){
			Integer chave = (Integer) iterador.next();
			Cliente valores = (Cliente) objetoColecao.get(chave);
			
			if (valores.getApelido().equals(valorInserido)){				
				Proprietario.clientes.add(valores);
			}// fim if

		}// fim while
		return clientes;
	}// fim metodo
	
	/**
	 * metodo para pesquisar Cliente por nif.
	 *
	 * @param objetoColecao - HashMap<Integer, Cliente>
	 * @param valorInserido - Sgring
	 * @return ArrayList<Cliente>
	 */
	
	public static ArrayList<Cliente> getClientesPorNif ( HashMap<Integer, Cliente> objetoColecao,	
														 String valorInserido){	
		Set<Integer>  set = objetoColecao.keySet();
		Iterator <Integer> iterador = set.iterator();		
		
		while( iterador.hasNext()){
			Integer chave = (Integer) iterador.next();
			Cliente valores = (Cliente) objetoColecao.get(chave);
			
			if (valores.getNib() == Integer.parseInt(valorInserido)){
				Proprietario.clientes.add(valores);
			}// fim if
			
		}// fim while
		return clientes;
	}// fim metodo
	
	
	/**
	 * método utilizado para remover cliente.
	 *
	 * @param conjuntoClientes the conjunto clientes
	 * @param id - String
	 * @return the hash map
	 */	
	public static HashMap<Integer, Cliente> removeCliente (HashMap<Integer, Cliente> conjuntoClientes,String id){		
		
		 conjuntoClientes.remove(Integer.parseInt(id));
		 return conjuntoClientes;
		
	}// fim removeCliente
	
	/**
	 * método para atualizar cliente.
	 *
	 * @param objetoColecao - HashMap<Integer, Cliente>
	 * @param id - String
	 */
	public static void atualizarCliente (HashMap<Integer, Cliente> objetoColecao, String id){
		
		new ClienteGui(objetoColecao.get(Integer.parseInt(id)), objetoColecao);
	}// fim metodo
		
	
	
	/**
	 * método para separar os Eventos em Sub-Eventos (Festa Aniversário ou Festa Pijama).
	 *
	 * @param objetoColecao - HashMap<Integer, Evento> conjunto de Eventos
	 * @param subEventos - String com o nome do Ojbecto para comparar
	 * @return HashMap < Integer, Object>
	 */	
	public static HashMap < Integer, Object> getSubEventos (HashMap<Integer, Evento> objetoColecao, String subEventos){
		HashMap < Integer, Object> subEventosHashMap = new HashMap <Integer, Object>();
		
		Set<Integer>  set = objetoColecao.keySet();
		Iterator <Integer> iterador = set.iterator();		
		
		while( iterador.hasNext()){
			Integer chave = (Integer) iterador.next();
			Evento valores = (Evento) objetoColecao.get(chave);
			
			if (valores.getClass().getSimpleName().equals(subEventos)){
				subEventosHashMap.put(chave, valores);
			}
			
		}// fim while
		
		return subEventosHashMap;
	}
	
	/**
	 * metodo para atualizar a festa de pijama.
	 *
	 * @param alterarEvento - Evento
	 * @param conjuntoEventos - HashMap<Integer, Evento> conjuntoEventos
	 */
	public static void atualizarFestaDoPijama( Evento alterarEvento, HashMap<Integer, Evento> conjuntoEventos){
		new EventoFestaPijamaGui( alterarEvento, conjuntoEventos);
	}
	
	/**
	 * metodo para atualizar a festa de Aniversario.
	 *
	 * @param alterarEvento - Evento
	 * @param conjuntoEventos - HashMap<Integer, Evento> conjunto eventos
	 */
	public static void atualizarFestaAniversario( Evento alterarEvento, HashMap<Integer, Evento> conjuntoEventos ){
		
		
	}
	
	/**
	 * metodo para converter uma String de tempo em minutos.
	 *
	 * @param horas - String
	 * @param minutos -  minutos
	 * @return int
	 */
	public static int convertStringToTime (String horas, String minutos){
		
		int horasInt = Integer.parseInt(horas)* 60;
		int minutosInt = Integer.parseInt(minutos);
		
		return horasInt+ minutosInt;
	}
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		
		return String.format("%-10s%-10s%-15s%-15s%n", 
				 super.getNome() , super.getApelido(), super.getUtilizador(), super.getPassword());
	}// fim toString()

}
