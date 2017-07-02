package negocio;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import gui.CadastroUsuarioController;

public class Leilao {
	String natureza, tipoLances;
	LocalDate dataInicio, dataFim;
	Date dataInicioS, dataFimS;
	String cpf; // ?
	int horaInicio, horaFim, minutoInicio, minutoFim;


	ArrayList<Usuario> compradores = new ArrayList<>();
	ArrayList<Usuario> vendedores = new ArrayList<>();

	public Leilao(String natureza, String cpf, String tipoLances, LocalDate dataInicio, LocalDate dataFim, int horaInicio, int horaFim, int minutoInicio, int minutoFim){
		this.natureza = natureza;
		this.cpf = cpf;
		this.tipoLances = tipoLances;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
		this.minutoInicio = minutoInicio;
		this.minutoFim = minutoFim;
	}

	public Leilao(String natureza, String cpf, String tipoLances, Date dataInicio, Date dataFim, int horaInicio, int horaFim, int minutoInicio, int minutoFim){
		this.natureza = natureza;
		this.cpf = cpf;
		this.tipoLances = tipoLances;
		this.dataInicioS = dataInicio;
		this.dataFimS = dataFim;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
		this.minutoInicio = minutoInicio;
		this.minutoFim = minutoFim;
	}

	public String getNatureza() {
		return natureza;
	}

	public String getTipoLances() {
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

	public int getHoraInicio() {
		return horaInicio;
	}

	public int getHoraFim() {
		return horaFim;
	}

	public int getMinutoInicio(){
		return minutoInicio;
	}

	public int getMinutoFim(){
		return minutoFim;
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

	@Override
	public String toString() {
		return "Natureza do leilão: " + natureza + ", tipo dos Lances: " + tipoLances + ",\n"
				+ " dia de Inicio: " + dataInicioS + ", dia do término: " + dataFimS + ",\n CPF do usuario associado ao leilão: " + cpf
				+ ", \nHora de Inicio do leilão: " + horaInicio+":"+minutoInicio + ", hora do término do leilão: " + horaFim + ":" + minutoFim
				+ ""+"";
	}
}
