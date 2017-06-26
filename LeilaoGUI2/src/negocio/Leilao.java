package negocio;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import gui.CadastroUsuarioController;

public class Leilao {
	int natureza, tipoLances;
	LocalDateTime dataInicio, dataFim;
	String cpf; // ?
	String horaInicio, horaFim;

	CadastroUsuarioController cuc = new CadastroUsuarioController();

	ArrayList<Usuario> compradores = new ArrayList<>();
	ArrayList<Usuario> vendedores = new ArrayList<>();

	public Leilao(int natureza, String cpf, int tipoLances, LocalDateTime dataInicio, LocalDateTime dataFim, String horaInicio, String horaFim){
		this.natureza = natureza;
		this.cpf = cpf;
		this.tipoLances = tipoLances;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
	}

	public int getNatureza() {
		return natureza;
	}

	public int getTipoLances() {
		return tipoLances;
	}

	public Date getDataInicio() {
		LocalDate locald =  LocalDate.of(dataInicio.getYear(),dataInicio.getMonth(),dataInicio.getDayOfMonth());
		Date date = Date.valueOf(locald); // Magic happens here!
		return date;
	}

	public Date getDataFim() {
		LocalDate locald =  LocalDate.of(dataFim.getYear(),dataFim.getMonth(),dataFim.getDayOfMonth());
		Date date = Date.valueOf(locald); // Magic happens here!
		return date;
	}

	public String getCpf() {
		return cpf;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public String getHoraFim() {
		return horaFim;
	}

	public CadastroUsuarioController getCuc() {
		return cuc;
	}

	public ArrayList<Usuario> getCompradores() {
		return compradores;
	}

	public ArrayList<Usuario> getVendedores() {
		return vendedores;
	}

	public void addComprador(Usuario u){
		compradores.add(u);
	}

	public void addVendedor(Usuario u){
		vendedores.add(u);
	}
}
