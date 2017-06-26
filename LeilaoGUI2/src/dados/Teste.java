package dados;

import negocio.Usuario;

public class Teste {

	public static void main(String[] args) throws CadastroException {
		CadastroDB  cdb = new CadastroDB();
		//cdb.queryqualquer("DROP TABLE usuarios");
		//cdb.adicionar(new Usuario("teste","858270610354","teste"));
		System.out.println(cdb.getTodos());
	}

}
