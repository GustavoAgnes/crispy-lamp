package gui;

import java.awt.Event;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dados.CadastroDB;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import negocio.CadastroException;
import negocio.Leilao;
import negocio.Usuario;

public class CadastroUsuarioController implements Initializable {
	ArrayList<Usuario> usuarios = new ArrayList<>(); // ?? os usuarios vao para o banco e não para a lista

	@FXML
	private TextField nome,cpf,email;

	@FXML
	private Button btnImage;

	CadastroDB databaseTF = new CadastroDB();


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

	@FXML
	public void clickSubmit() throws dados.CadastroException{
		Usuario u = new Usuario(nome.getText().toString(),cpf.getText().toString(),email.getText().toString());
		if(u.validacaoUsuario(cpf.getText(), email.getText())){
			if(!databaseTF.checarSeExiste(u)){
				databaseTF.adicionar(u);

			this.cadastradoSucessoAlerta();
		}
			else{
				this.erroAoCadastrarUsuario("O usuário já existe!");
			}
		}
		else{
			this.erroAoCadastrarUsuario("CPF ou E-mail inválido");
		}
	}

	public void cadastradoSucessoAlerta(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Sucesso");
		alert.setHeaderText(null);
		alert.setContentText("O usuário foi cadastrado com sucesso!");
		alert.showAndWait();
	}

	public void erroAoCadastrarUsuario(String msgErro){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Erro!");
		alert.setHeaderText(null);
		alert.setContentText(msgErro);
		alert.showAndWait();
	}

	public ArrayList<Usuario> getLista(){
		return usuarios;
	}
}