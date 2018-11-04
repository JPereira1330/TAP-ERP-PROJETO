package application;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.EstoqueModel;

public class EstoqueController {
	
	// Aba Cadastro
	@FXML TextField tfProduto;
	@FXML TextField tfQuantia;
	@FXML TextField tfPreco;
	
	// Aba Remover
	@FXML TextField tfIdentificador;
	@FXML TextField tfQuantia_r;
	
	// Tabela
	@FXML TableView<Produto> tbTabela;
	@FXML TableColumn<Produto, Number> cId;
	@FXML TableColumn<Produto, String> cProduto;
	@FXML TableColumn<Produto, Number> cQuantia;
	@FXML TableColumn<Produto, Number> cPreco;

	// Lista do estoque
	ArrayList<Produto> estoque = new ArrayList<Produto>();
	
	@FXML // Metodo responsavel por Adicionar no banco de dados
	public void adicionar() {
		
		// Capturando informações Adicionadas pelo usuario
		String produto = tfProduto.getText();
		int quantia = Integer.parseInt(tfQuantia.getText());
		Float preco = Float.parseFloat(tfPreco.getText());

		// Adicionando no banco de dados
		//EstoqueModel.inserir(produto, quantia, preco);
		
		// Adicionando na ArrayList
		//estoque.clear();
		//estoque.add()
		
		Produto objProduto = new Produto(produto,quantia,preco);
		estoque.add(objProduto);
		
		// Atualiza lista de estoque
		tbTabela.setItems(FXCollections.observableArrayList(estoque));
	}
	
	@FXML // Metodo responsavel por remover no banco de dados
	public void remover() {
		
	}
	
	@FXML // Metodo para abrir janela de seleção
	public void voltar() {
		
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
	}
	
	@FXML
	public void initialize() {
		inicializaTB();
	}
	
	@FXML
	private void inicializaTB() {
		cId.setCellValueFactory(cellData -> cellData.getValue().propIdProperty());
		cProduto.setCellValueFactory(cellData -> cellData.getValue().propProdutoProperty());
		cQuantia.setCellValueFactory(cellData -> cellData.getValue().propQuantiaProperty());
		cPreco.setCellValueFactory(cellData -> cellData.getValue().propPrecoProperty());
	}
}
