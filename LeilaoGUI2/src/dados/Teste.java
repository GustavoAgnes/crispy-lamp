package dados;

import negocio.Usuario;

public class Teste {

	public static void main(String[] args) throws CadastroException {
		CadastroDB  cdb = new CadastroDB();
	//cdb.queryqualquer("DROP TABLE LEILOES");
		cdb.adicionarUsuario(new Usuario("teste","85827061034","teste"));
//	System.out.println(cdb.getTodosUsuarios());
		//cdb.queryqualquer("CREATE TABLE IF NOT EXISTS"
		///		+ " leiloes(natureza TEXT, cpf TEXT, tipoLances TEXT, dataInicio DATE, dataFim DATE, horaInicio INTEGER, horaFim INTEGER, minutoInicio INTEGER, minutoFim INTEGER)");
		//System.out.println(cdb.queryqualquer("SELECT * FROM LEILOES"));
		//cdb.queryqualquer("DROP TABLE USUARIOS");
	//System.out.println(cdb.getTodosLeiloes());
	System.out.println(cdb.getLeilaoPeloUsuario("85827061034"));
	}

}
