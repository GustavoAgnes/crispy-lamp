package negocio;
import java.util.ArrayList;

import javafx.application.Application;
public class AppTeste {

	public static void main(String[] args) throws CadastroException {
		ArrayList<Integer> lista = new ArrayList<>();
		lista.add(1);
		lista.add(1);
		lista.add(2);
		lista.add(1);
		System.out.println(lista.contains(2));
	}

}
