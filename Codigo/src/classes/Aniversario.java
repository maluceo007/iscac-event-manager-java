package classes;

// TODO: Auto-generated Javadoc
/**
 * The Class Aniversario.
 * 
 */
public class Aniversario extends Evento{
	

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 976451826578773268L;
	
	/** The hora inicio. */
	private String horaInicio;
	
	/** The duracao evento. */
	private int duracaoEvento;
	
	
	/**
	 * Instantiates a new aniversario.
	 *
	 * @param clienteId the cliente id
	 * @param dataEvento the data evento
	 * @param numParticipantes the num participantes
	 * @param precoMenu the preco menu
	 * @param exclusividade the exclusividade
	 * @param lembrete the lembrete
	 * @param estadoDoEvento the estado do evento
	 * @param horaInicio the hora inicio
	 * @param duracaoEvento the duracao evento
	 */
	public Aniversario (int clienteId, String dataEvento, int numParticipantes, 
						double precoMenu, boolean exclusividade, String lembrete, int estadoDoEvento,
						String horaInicio, int duracaoEvento){
		
		super (clienteId,dataEvento,numParticipantes,precoMenu,exclusividade, lembrete, estadoDoEvento);
		this.horaInicio = horaInicio;
		this.duracaoEvento = duracaoEvento;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		
		return String.format("%-8s%-9s%-13s%-10s%-10s%-10s%-10s%-12s%-7s%-8s%-40s%n", 
				super.getEventoId(), super.getClienteId(),  super.getDataEvento(), this.horaInicio, this.duracaoEvento, super.getNumParticipantes(), super.getPrecoMenu(), super.precoSemIva(),
				super.isExclusividade(), super.getEstadoDoEvento(), super.getLembrete());
	}// fim toString()

	
	/**
	 * Gets the hora inicio.
	 *
	 * @return the hora inicio
	 */
	public String getHoraInicio() {return horaInicio;}
	
	/**
	 * Gets the duracao evento.
	 *
	 * @return the duracao evento
	 */
	public int getDuracaoEvento() {return duracaoEvento;}
	
}
