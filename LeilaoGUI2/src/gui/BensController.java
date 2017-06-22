package gui;

import java.awt.Event;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import negocio.Bens;

public class BensController  implements Initializable {

	ArrayList<Bens> bens = new ArrayList<>();

	@FXML
	TextField descBreve, categoria;
	@FXML
	TextArea descCompleta;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

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

	public void salvarBens(ActionEvent event) throws IOException
	{
		Bens b = new Bens(descBreve.getText().toString(), descCompleta.getText().toString(), categoria.getText().toString());
		if((descBreve.getText().toString().isEmpty() || descCompleta.getText().toString().isEmpty() || categoria.getText().toString().isEmpty())){
			this.erroAoCadastrar();
		}
		else{
			this.bemCadastrado();
			bens.add(b);
		}
		}

	public void bemCadastrado(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Sucesso");
		alert.setHeaderText(null);
		alert.setContentText("O bem foi cadastrado com sucesso!");
		alert.showAndWait();
		descBreve.clear();
		categoria.clear();
		descCompleta.clear();
	}

	public void erroAoCadastrar(){
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Erro");
		alert.setHeaderText(null);
		alert.setContentText("Todos os campos devem estar preenchidos!");
		alert.showAndWait();
	}

	public void descBreveNchars(){
		if(descBreve.getText().length() > 40){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Alerta");
			alert.setHeaderText(null);
			alert.setContentText("O número máximo de caracteres é 40!");
			alert.showAndWait();
			descBreve.clear(); //descobrir abordagem menos agressiva do que apagar texto.
		}
	}
}
