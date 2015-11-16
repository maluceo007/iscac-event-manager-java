package classes;

// TODO: Auto-generated Javadoc
/**
 * The Class FestaPijama.
 */
public class FestaPijama extends Evento {

	private static final long serialVersionUID = 4913431691216305161L;

	/**
	 * Instantiates a new festa pijama.
	 *
	 * @param clienteId the cliente id
	 * @param dataEvento the data evento
	 * @param numParticipantes the num participantes
	 * @param precoMenu the preco menu
	 * @param exclusividade the exclusividade
	 * @param lembrete the lembrete
	 * @param estadoDoEvento the estado do evento
	 */
	public FestaPijama (int clienteId, String dataEvento, int numParticipantes, 
			double precoMenu, boolean exclusividade, String lembrete, int estadoDoEvento){

		super (clienteId,dataEvento,numParticipantes,precoMenu,exclusividade, lembrete, estadoDoEvento);
		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		
		return String.format("%-8s%-9s%-13s%-10s%-10s%-10s%-10s%-12s%-7s%-8s%-40s%n", 
				super.getEventoId(), super.getClienteId(),  super.getDataEvento(), "-", "-", super.getNumParticipantes(), super.getPrecoMenu(), super.precoSemIva(),
				super.isExclusividade(), super.getEstadoDoEvento(), super.getLembrete());
	}// fim toString()
}
