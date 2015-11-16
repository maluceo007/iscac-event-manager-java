package classes;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import visual.Utilidades;

// TODO: Auto-generated Javadoc
/**
 * The Class Evento.
 */
public abstract class Evento implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8953752500548355230L;
	
	/** The cliente id. */
	private int clienteId;
	
	/** The data evento. */
	private String dataEvento;	
	
	/** The num participantes. */
	private int numParticipantes;
	
	/** The preco com iva. */
	private double precoMenu, precoSemIva, precoComIva;
	
	/** The exclusividade. */
	private boolean exclusividade;
	
	/** The evento id. */
	private int eventoId;
	
	/** The lembrete. */
	private String lembrete;
	
	/** The estado do evento. */
	private int estadoDoEvento;
	
	/** The iva. */
	private static double iva = 0.23;
	
	/** The desconto. */
	private double desconto = 0.05;
	
	
	/** The id generator. */
	private static AtomicInteger ID_GENERATOR = new AtomicInteger(Utilidades.ultimoId("eventoId.txt"));
	
	/**
	 * Instantiates a new evento.
	 *
	 * @param clienteId the cliente id
	 * @param dataEvento the data evento
	 * @param numParticipantes the num participantes
	 * @param precoMenu the preco menu
	 * @param exclusividade the exclusividade
	 * @param lembrete the lembrete
	 * @param estadoDoEvento the estado do evento
	 */
	public Evento (int clienteId, String dataEvento, int numParticipantes, 
			      	double precoMenu, boolean exclusividade,String lembrete, int estadoDoEvento){
		
		this.clienteId = clienteId;
		this.dataEvento = dataEvento;
		this.numParticipantes = numParticipantes;
		this.precoMenu = precoMenu;
		this.exclusividade = exclusividade;	
		this.lembrete = lembrete;
		this.estadoDoEvento = estadoDoEvento;
		Utilidades.atualizaUltimoId("eventoId.txt");
		this.eventoId = ID_GENERATOR.getAndIncrement();
	}

	
	/**
	 * getters.
	 *
	 * @return the cliente id
	 */
	public int getClienteId() {	return clienteId;}
	
	/**
	 * Gets the data evento.
	 *
	 * @return the data evento
	 */
	public String getDataEvento() {return dataEvento;	}
	
	/**
	 * Gets the num participantes.
	 *
	 * @return the num participantes
	 */
	public int getNumParticipantes() {return numParticipantes;}
	
	/**
	 * Gets the preco menu.
	 *
	 * @return the preco menu
	 */
	public double getPrecoMenu() {return precoMenu;	}
	
	/**
	 * Checks if is exclusividade.
	 *
	 * @return true, if is exclusividade
	 */
	public boolean isExclusividade() {return exclusividade;	}
	
	/**
	 * Gets the lembrete.
	 *
	 * @return the lembrete
	 */
	public String getLembrete() {return lembrete;}
	
	/**
	 * Gets the evento id.
	 *
	 * @return the evento id
	 */
	public int getEventoId() {return eventoId;}
	
	/**
	 * Gets the estado do evento.
	 *
	 * @return the estado do evento
	 */
	public int getEstadoDoEvento () { return estadoDoEvento;}
	
	/**
	 * Gets the preco sem iva.
	 *
	 * @return the preco sem iva
	 */
	public double getPrecoSemIva() {return precoSemIva;}
	
	/**
	 * Gets the desconto.
	 *
	 * @return the desconto
	 */
	public double getDesconto() {return desconto;}
	
	/**
	 * Gets the iva.
	 *
	 * @return the iva
	 */
	public static double getIva() {	return iva;	}
	
	/**
	 * Gets the preco com iva.
	 *
	 * @return the preco com iva
	 */
	public double getPrecoComIva() {return precoComIva;	}
	
	/**
	 * setters.
	 *
	 * @param clienteId the new cliente id
	 */
	public void setClienteId(int clienteId) {this.clienteId = clienteId;}
	
	/**
	 * Sets the data evento.
	 *
	 * @param dataEvento the new data evento
	 */
	public void setDataEvento(String dataEvento) {this.dataEvento = dataEvento;}
	
	/**
	 * Sets the num participantes.
	 *
	 * @param numParticipantes the new num participantes
	 */
	public void setNumParticipantes(int numParticipantes) {	this.numParticipantes = numParticipantes;}
	
	/**
	 * Sets the preco menu.
	 *
	 * @param precoMenu the new preco menu
	 */
	public void setPrecoMenu(double precoMenu) {this.precoMenu = precoMenu;	}
	
	/**
	 * Sets the exclusividade.
	 *
	 * @param exclusividade the new exclusividade
	 */
	public void setExclusividade(boolean exclusividade) {this.exclusividade = exclusividade;}
	
	/**
	 * Sets the lembrete.
	 *
	 * @param lembrete the new lembrete
	 */
	public void setLembrete(String lembrete) {this.lembrete = lembrete;	}
	
	/**
	 * Sets the estado do evento.
	 *
	 * @param estadoDoEvento the new estado do evento
	 */
	public void setEstadoDoEvento(int estadoDoEvento) {this.estadoDoEvento = estadoDoEvento;}
	
	/**
	 * Sets the evento id.
	 *
	 * @param eventoId the new evento id
	 */
	public void setEventoId(int eventoId) {	this.eventoId = eventoId;}
	
	/**
	 * Sets the preco sem iva.
	 *
	 * @param precoSemIva the new preco sem iva
	 */
	public void setPrecoSemIva(double precoSemIva) {this.precoSemIva = precoSemIva;	}
	
	/**
	 * Sets the preco com iva.
	 *
	 * @param precoComIva the new preco com iva
	 */
	public void setPrecoComIva(double precoComIva) {this.precoComIva = precoComIva;	}
	
	/**
	 * Sets the iva.
	 *
	 * @param iva the new iva
	 */
	public static void setIva(double iva) {	Evento.iva = iva;}
	
	/**
	 * Sets the desconto.
	 *
	 * @param desconto the new desconto
	 */
	public void setDesconto(double desconto) {this.desconto = desconto;	}

	
	/**
	 * Preco sem iva.
	 *
	 * @return the double
	 */
	public double precoSemIva(){
		return this.numParticipantes * this.precoMenu;
		
	}
	
	/**
	 * Preco do Evento com Iva
	 * @return
	 */
	public double precoComIva(){
		return ((precoSemIva()-(precoSemIva * this.desconto)) * ( 1 + Evento.iva ));
	}
	
	/**
	 * metodo para validar a data.
	 *
	 * @param objetoColecao HashMap <Integer, Evento>
	 * @param data String
	 * @return boolean
	 */
	public static boolean validarData (HashMap <Integer, Evento> objetoColecao, String data){
		
		Set<Integer> set = objetoColecao.keySet();
		Iterator <Integer> iterador = set.iterator();
		
		while (iterador.hasNext()){
			Integer chave = (Integer) iterador.next();
			Evento valores = (Evento) objetoColecao.get(chave);
			
			if ( valores instanceof FestaPijama){
				if (valores.getDataEvento().equals(data)){
					return true;
				}
			}else{
				if (valores.getDataEvento().equals(data) && valores.isExclusividade()){
					return true;
				}
				
			}
			
		}// fim while
		return false;		
	}

	/**
	 * método utilizaddo para validar o tempo de durção do Evento tem que ser entre 1 e 3 horas
	 * 
	 * @param hIncio - hora inicio
	 * @param mIncio - minutos inicio
	 * @param hfim - hora fim
	 * @param mfim - minutos fim
	 * @return - boolean se a duração do Evento é valida
	 */
	public static boolean validarDuracaoEvento(String hIncio, String mIncio,
										String hfim, String mfim ){
			
		// valida a duração da festa de anos
		if ( hIncio != null && mIncio != null && hfim != null && mfim != null){
			
			int inicio = Integer.parseInt(hIncio)*60 + Integer.parseInt(mIncio);
			int fim = Integer.parseInt(hfim)*60 + Integer.parseInt(mfim);
		
			if ( ((fim - inicio) < 60) || ( (fim - inicio) > 180))
				return false;
		
		}else
			return false;
		
		return true;
	}
	
	
	
}
