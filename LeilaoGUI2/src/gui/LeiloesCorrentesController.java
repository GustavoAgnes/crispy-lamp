package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dados.CadastroDB;
import dados.CadastroException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import negocio.Leilao;
import negocio.Usuario;

public class LeiloesCorrentesController implements Initializable {

	@FXML
	private ComboBox<String> naturezaLeilao, donoLeilao;
	@FXML
	private Label naturezaLeilaoLabel, donoLeilaoLabel;// naturezaLeilaoLabel2, formaDosLances, dataDeInicio, dataDeTermino, horaInicio, horaTermino;

	@FXML
	ListView<Leilao> listaLeiloes = new ListView<Leilao>();

	@FXML
	private Button btnPesquisar;

	ArrayList<Usuario> usuarios = new ArrayList<>();

	CadastroDB databaseTF = new CadastroDB();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		naturezaLeilao.getItems().addAll("Oferta","Demanda");
		/*
		naturezaLeilaoLabel2.setVisible(false);
		formaDosLances.setVisible(false);
		dataDeInicio.setVisible(false);
		dataDeTermino.setVisible(false);
		horaInicio.setVisible(false);
		horaTermino.setVisible(false);
		*/
		try {
			usuarios = (ArrayList<Usuario>) databaseTF.getTodosUsuarios();
			for (Usuario u : usuarios) {
				donoLeilao.getItems().add("Nome: "+u.getNome().toString() +", CPF: "+u.getCpf().toString());
			}
		} catch (dados.CadastroException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		listaLeiloes.setVisible(false);
	}

	@FXML
	public void botaoHome(ActionEvent event)throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("SistemaDeLeilao.fxml"));
		Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root, 400, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}

	@FXML
	public void botaoPesquisar(ActionEvent event) throws IOException, CadastroException{
	//	if(databaseTF.getLeilaoPeloUsuario(donoLeilao.getS))
		String completa = donoLeilao.getSelectionModel().getSelectedItem().toString();
		String[] apenasCpf = completa.split(",");
		String apenasCpfSub = apenasCpf[1].substring(6,17);
		if(databaseTF.getLeilaoPeloUsuario(apenasCpfSub.toString()).isEmpty()){
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Alerta");
			alert.setHeaderText(null);
			alert.setContentText("Nenhum leilão associado ao usuário");
			alert.showAndWait();
		}
		else{
			naturezaLeilaoLabel.setVisible(false);
			donoLeilaoLabel.setVisible(false);
			naturezaLeilao.setVisible(false);
			donoLeilao.setVisible(false);
			btnPesquisar.setVisible(false);
			ObservableList<Leilao> items = FXCollections.observableArrayList (databaseTF.getLeilaoPeloUsuario(apenasCpfSub.toString()));
			listaLeiloes.setItems(items);
			listaLeiloes.setVisible(true);
			/*
			naturezaLeilaoLabel2.setVisible(true);
			formaDosLances.setVisible(true);
			dataDeInicio.setVisible(true);
			dataDeTermino.setVisible(true);
			horaInicio.setVisible(true);
			horaTermino.setVisible(true);
			*/
		}
	}
}
