package negocio;

import java.time.LocalDateTime;

public class Leilao {
	int natureza, tipoLances;
	LocalDateTime dataInicio, dataFim;
	Usuario u;
	String horaInicio, horaFim;

	public Leilao(int natureza, Usuario u, int tipoLances, LocalDateTime dataInicio, LocalDateTime dataFim, String horaInicio, String horaFim){
		this.natureza = natureza;
		this.u = u;
		this.tipoLances = tipoLances;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
	}
}
