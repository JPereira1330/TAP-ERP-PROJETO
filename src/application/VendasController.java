package application;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.EstoqueModel;

public class VendasController {

	// Campos da interface
	@FXML ComboBox<Produto> cbProduto;
	@FXML TextField tfQuantia;
	@FXML TextField tfQuantiaEstoque;
	@FXML TextField tfPreco;
	@FXML TextField tfPrecoTotal;
	
	// Tabela
	@FXML TableView<Produto> tbTabela;
	@FXML TableColumn<Produto, String> cProduto;
	@FXML TableColumn<Produto, Number> cQuantia;
	@FXML TableColumn<Produto, Number> cPreco;
	
	// Lista do estoque
	ArrayList<Produto> carrinhoDeCompras = new ArrayList<Produto>();
	
	@FXML  // Metodo responsavel por Adicionar no carrinho de compras
	public void adicionar() {
		
		// Capturando informações Adicionadas pelo usuario
		String produto = cbProduto.getSelectionModel().getSelectedItem().getPropProduto();
		int quantia = Integer.parseInt(tfQuantia.getText());
		Float preco = Float.parseFloat(tfPreco.getText());

		// Verificando se tem estoque
		if(quantia <= Integer.parseInt(tfQuantiaEstoque.getText())) {
		
			// Adicionando no banco de dados
			int id = cbProduto.getSelectionModel().getSelectedItem().getPropId();
			EstoqueModel.remover(id, quantia);
			
			// Atualiza estoque
			inicializaCB();
			
			// Adicionando na ArrayList
			Produto prod = new Produto(id, produto, quantia, preco);
			carrinhoDeCompras.add(prod);
			
			// Atualiza lista de estoque
			tbTabela.setItems(FXCollections.observableArrayList(carrinhoDeCompras));
			
			// Atualizando valor total
			tfPrecoTotal.setText("R$ "+calcValorTotal());
	
		}
		
		// Caso quantia maior do que estoque
		else {
			JOptionPane.showConfirmDialog(null, "Quantia não disponivel em estoque");
		}
	}
	
	// Metodo para calcular valor total dos itens do carrinho de compras
	public float calcValorTotal() {
		
		float preco = 0;
		
		// Realizando calculo
		for (Produto produto : carrinhoDeCompras) {
			preco += produto.getPropPreco() * produto.getPropQuantia();
		}
		
		return preco;
	}
	
	@FXML	// Inicializador
	public void initialize() {
		
		// Inicializando Tabela
		inicializaTB();
		
		// Inicializar ComboBox
		inicializaCB();
		
	}
	
	// Metodo para inicializar tabela
	public void inicializaTB() {
		cProduto.setCellValueFactory(cellData -> cellData.getValue().propProdutoProperty());
		cQuantia.setCellValueFactory(cellData -> cellData.getValue().propQuantiaProperty());
		cPreco.setCellValueFactory(cellData -> cellData.getValue().propPrecoProperty());
	}
	
	// Metodo para inicializar combobox
	public void inicializaCB() {
		
		// Preenchendo tabela
		ArrayList<Produto> estoque = EstoqueModel.select();
		
		// Atribuindo os produtos no combobox
		for (Produto produto : estoque) {
			cbProduto.getItems().add(produto);
		}

	}
	
	// Metodo para atribuir valor aos campos dependentes do combobox
	public void inicializaCBcampos() {
		
		// Verifica se algo foi selecionado
		if(cbProduto.getSelectionModel().getSelectedItem() != null) {
			
			// Capturando item selecionado
			Produto selecionado = cbProduto.getSelectionModel().getSelectedItem();
			
			// Setando valores
			tfQuantiaEstoque.setText(selecionado.getPropQuantia()+"");
			tfPreco.setText(selecionado.getPropPreco()+"");
			
		}

	}
}
