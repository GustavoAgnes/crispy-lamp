package negocio;

import java.time.LocalDateTime;
import java.util.ArrayList;

import gui.CadastroUsuarioController;

public class Leilao {
	int natureza, tipoLances;
	LocalDateTime dataInicio, dataFim;
	Usuario u; // ?
	String horaInicio, horaFim;

	CadastroUsuarioController cuc = new CadastroUsuarioController();

	ArrayList<Usuario> compradores = new ArrayList<>();
	ArrayList<Usuario> vendedores = new ArrayList<>();

	public Leilao(int natureza, Usuario u, int tipoLances, LocalDateTime dataInicio, LocalDateTime dataFim, String horaInicio, String horaFim){
		this.natureza = natureza;
		this.u = u;
		this.tipoLances = tipoLances;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
	}

	public void addComprador(Usuario u){
		compradores.add(u);
	}

	public void addVendedor(Usuario u){
		vendedores.add(u);
	}
}
