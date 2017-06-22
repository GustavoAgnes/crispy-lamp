package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LeilaoAndamentoController implements Initializable {

	@FXML
	private Button btnHome;

	@FXML
	private ChoiceBox<String> naturezaLeilao, formaLances;

	@FXML
	private ComboBox<String> horaInicio, horaTermino, minutosTermino, minutosInicio;

	@FXML
	public void botaoHome(ActionEvent event)throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("Leilao.fxml"));
		Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root, 400, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		naturezaLeilao.getItems().addAll("Oferta","Demanda");
		formaLances.getItems().addAll("Aberto","Fechado");
		horaInicio.getItems().addAll("00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16"
				,"17","18","19","20","21","22","23","24");
		horaTermino.getItems().addAll("00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16"
				,"17","18","19","20","21","22","23","24");
		minutosInicio.getItems().addAll("00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16"
				,"17","18","19","20","21","22","23","24","25","26","27","28","29","30",
				"31","32","33","34","35","36","37","38","39","40",
				"41","42","43","44","45","46","47","48","49","50",
				"51","52","53","54","55","56","57","58","59");
		minutosTermino.getItems().addAll("00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16"
				,"17","18","19","20","21","22","23","24","25","26","27","28","29","30",
				"31","32","33","34","35","36","37","38","39","40",
				"41","42","43","44","45","46","47","48","49","50",
				"51","52","53","54","55","56","57","58","59");

		minutosInicio.getStylesheets().add(
                getClass().getResource(
                        "LeilaoAndamento.css").toExternalForm());
	}

}