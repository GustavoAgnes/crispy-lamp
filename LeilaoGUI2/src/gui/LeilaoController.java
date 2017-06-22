package gui;


import java.awt.Event;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LeilaoController implements Initializable {
	@FXML
	private TextField nome,cpf,email;
	@FXML
	public Button btnBeginTargeting;
	@FXML
	private Button btnExit, oferta, demanda, aberto1, fechado1, aberto2, fechado2, btnLote;
	@FXML
	public void buttonClicked(ActionEvent event)throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("CadastroUsuario.fxml"));
		Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root, 400, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
		stage.setResizable(false);
	}

	public void clickCadastroBens(ActionEvent event)throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("Bens.fxml"));
		Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root, 400, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}

	public void comecarLeilao(ActionEvent event) throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("LeilaoAndamento.fxml"));
		Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root, 400, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
