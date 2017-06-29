package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

public class LeiloesCorrentesController implements Initializable {

	@FXML
	private ComboBox<String> naturezaLeilao;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		naturezaLeilao.getItems().addAll("Oferta","Demanda");
	}
}
