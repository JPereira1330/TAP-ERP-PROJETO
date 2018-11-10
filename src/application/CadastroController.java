package application;

import java.awt.TrayIcon.MessageType;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.CadastroModel;

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
		
		if(!nome.isEmpty()) {			// Verificando nome
			if(!login.isEmpty()) {		// Verificando login
				if(!passw.isEmpty()) {	// Verificando passwd
					
					// Verifica se já existe
					if(!CadastroModel.validar(login))
						// Cadastrando
						CadastroModel.inserir(login, passw, nome);
					else
						JOptionPane.showMessageDialog(null, "Usuario Já Cadastrado");
				}
				else {	JOptionPane.showMessageDialog(null, "Senha vazia");  }
			}else { JOptionPane.showMessageDialog(null, "Login vazio"); }
		}else {	JOptionPane.showMessageDialog(null, "Nome vazio"); }
		
		// Carrega proxima pagina
		try {
			
			// Aplicando novas configurações
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Login.fxml"));
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
	
		try {
			
			// Aplicando novas configurações
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
			// Capturando variaveis
			Stage window = Main.getWindow();
			
			// Aplicando configurações
			window.setResizable(false);
			window.setScene(scene);
			window.show();
		
		}catch(Exception e) {
			JOptionPane.showConfirmDialog(null, "ERRO - Inicializar modulo estoque");
		}
	}
	
}
