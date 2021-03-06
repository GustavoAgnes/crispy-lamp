package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import dados.CadastroDB;
import dados.CadastroException;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import negocio.Leilao;
import negocio.Usuario;

public class CadastroDeLeilaoController implements Initializable {

	@FXML
	private Button btnHome;

	@FXML
	private ChoiceBox<String> naturezaLeilao, formaLances;

	@FXML
	private ComboBox<String> horaInicio, horaTermino, minutosTermino, minutosInicio;

	@FXML
	private Label identificacao;

	@FXML
	private ComboBox<String> listaUsers;

	@FXML
	private DatePicker dataInicio, dataFim;

	ArrayList<Usuario> usuarios = new ArrayList<>();

	CadastroDB databaseTF = new CadastroDB();

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

	public void mudarTexto() throws IOException, CadastroException{
		naturezaLeilao.getSelectionModel()
	    .selectedItemProperty()
	    .addListener( (ObservableValue<? extends String> observable, String oldValue, String newValue) ->{
	    	if(naturezaLeilao.getSelectionModel().getSelectedItem().toString()=="Oferta"){
				identificacao.setText(identificacao.getText().toString().substring(0,16).concat(" comprador"));
			}
	    	if(naturezaLeilao.getSelectionModel().getSelectedItem().toString()=="Demanda"){
				identificacao.setText(identificacao.getText().toString().substring(0,16).concat(" vendedor"));
			}
	    });
		}

	@FXML
	public void botaoSubmit(ActionEvent event) throws IOException, NumberFormatException, CadastroException {
		if(naturezaLeilao.getSelectionModel().getSelectedItem() == null ||
				formaLances.getSelectionModel().getSelectedItem() == null ||
				horaInicio.getSelectionModel().getSelectedItem() == null ||
				horaTermino.getSelectionModel().getSelectedItem() == null ||
				minutosTermino.getSelectionModel().getSelectedItem() == null ||
				minutosInicio.getSelectionModel().getSelectedItem() == null ||
				listaUsers.getSelectionModel().getSelectedItem() == null ||
				dataInicio.getValue() == null ||
				dataFim.getValue() == null
				)
		{
			alertaErro();
		}
		else{
		Leilao l = new Leilao(naturezaLeilao.getSelectionModel().getSelectedItem().toString(),
				pegarCpfs(),
				formaLances.getSelectionModel().getSelectedItem().toString(),
				dataInicio.getValue(),
				dataFim.getValue(),
				Integer.parseInt(horaInicio.getSelectionModel().getSelectedItem().toString().toString()),
				Integer.parseInt(horaTermino.getSelectionModel().getSelectedItem().toString().toString()),
				Integer.parseInt(minutosInicio.getSelectionModel().getSelectedItem().toString()),
				Integer.parseInt(minutosTermino.getSelectionModel().getSelectedItem().toString()));
				databaseTF.cadastrarLeilao(l);
				System.out.println(databaseTF.getTodosLeiloes());
	}
	}

	public void alertaErro(){
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Erro");
		alert.setHeaderText(null);
		alert.setContentText("Todos os campos devem estar preenchidos!");
		alert.showAndWait();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		naturezaLeilao.getItems().addAll("Oferta","Demanda");
		formaLances.getItems().addAll("Aberto","Fechado");
		naturezaLeilao.getSelectionModel().selectFirst();
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

		try {
			usuarios = (ArrayList<Usuario>) databaseTF.getTodosUsuarios();
			for (Usuario u : usuarios) {
				listaUsers.getItems().add("Nome: "+u.getNome().toString() +", CPF: "+u.getCpf().toString());
			}
		} catch (dados.CadastroException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String pegarCpfs() throws CadastroException{
			String completa = listaUsers.getSelectionModel().getSelectedItem().toString();
			String[] apenasCpf = completa.split(",");
			String apenasCpfSub = apenasCpf[1].substring(6,17);
			return apenasCpfSub;
	}
}

