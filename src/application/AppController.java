package application;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AppController {
	
	@FXML // Metodo para abrir janela que gerencia o estoque
	public void estoque() {
		
		try {
			
			// Aplicando novas configurações
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Estoque.fxml"));
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
