package application;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.LoginModel;

public class LoginController {

	// Variaveis da interface grafica
	@FXML TextField tfLogin;
	@FXML PasswordField tfPassw;
	
	@FXML // Metodo pra logar no sistema
	public void login() {
		
		// Capturando os dados informados pelo usuario
		String login = tfLogin.getText();
		String passw = tfPassw.getText();
		
		// Validando existencia do usuario
		if(LoginModel.validar(login, passw)) {
			
			// Carrega proxima pagina
			try {
				
				// Aplicando novas configurações
				AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("App.fxml"));
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
			
		}else
			JOptionPane.showMessageDialog(null, "Usuario não encontrado", "Simple ERP", JOptionPane.OK_OPTION);
			
	}

	@FXML // Metodo pra abrir tela de cadastro no sistema
	public void cadastro() {
	
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
	
	@FXML // Finaliza o programa
	public void sair() {
		System.exit(1);
	}

}
