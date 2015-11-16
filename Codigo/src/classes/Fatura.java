package classes;

import java.sql.Date;
import java.util.concurrent.atomic.AtomicInteger;
import visual.Utilidades;

// TODO: Auto-generated Javadoc
/**
 * The Class Fatura.
 */
public class Fatura {

	/** The fatura id. */
	private int faturaId;
	
	/** The id generator. */
	private static AtomicInteger ID_GENERATOR = new AtomicInteger(Utilidades.ultimoId("faturaId.txt"));
	
	/** The evento para faturar. */
	private Evento eventoParaFaturar;

	
	/**
	 * Instantiates a new fatura.
	 *
	 * @param eventoParaFaturar the evento para faturar
	 * @param desconto the desconto
	 */
	public Fatura( Evento eventoParaFaturar, double desconto){
		
		Utilidades.atualizaUltimoId("faturaId.txt");
		this.faturaId = ID_GENERATOR.getAndIncrement();
		this.eventoParaFaturar = eventoParaFaturar;
		this.eventoParaFaturar.setDesconto(desconto);
		setEstadoEvento();
	}
	
	/**
	 * Sets the estado evento.
	 */
	public void setEstadoEvento (){
		eventoParaFaturar.setEstadoDoEvento(4);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		Date data = new Date(System.currentTimeMillis());
	
		return 					"Numbero Fatura: " + this.faturaId + "     Data:" + data +  
								"\n______________________"+
								"\nNumero Evento: " + eventoParaFaturar.getEventoId() +
								"\n______________________"+
								"\nPreço sem Iva:   " + String.format("%.2f",eventoParaFaturar.precoSemIva()) +
								"\nDeconto:         " + String.format("%.2f",eventoParaFaturar.getDesconto() * eventoParaFaturar.precoSemIva()) +
								"\n______________________"+
								"\nTotal:           " + String.format("%.2f",eventoParaFaturar.precoComIva());
				
				
		
	}// fim toString()
}
