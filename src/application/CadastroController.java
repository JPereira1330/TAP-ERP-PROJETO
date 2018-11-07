package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CadastroController {

	// Variaveis da interface grafica
	@FXML TextField tfNome;
	@FXML TextField tfLogin;
	@FXML PasswordField tfPassw;

	@FXML // Metodo para cadastrar usuario no sistema
	public void cadastro() {
		
		// Capturando os dados informados pelo usuario
		String nome	 = tfNome.getText();
		String login = tfLogin.getText();
		String passw = tfPassw.getText();
		
		// Carrega proxima pagina
		try {
			
			// Aplicando novas configurações
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Cadastro.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
			// Capturando variaveis
			Stage window = Main.getWindow();
			
			// Aplicando configurações
			window.setResizable(false);
			window.setScene(scene);
			window.show();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
					
	}
	
}
