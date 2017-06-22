package negocio;

public class Bens {
	private String descBreve, descCompleta, categoria;

	public Bens(String db, String dc, String catg){
		this.descBreve = db;
		this.descCompleta = dc;
		this.categoria = catg;
	}

	public String getDescBreve() {
		return descBreve;
	}

	public String getDescCompleta() {
		return descCompleta;
	}

	public String getCategoria() {
		return categoria;
	}
}
