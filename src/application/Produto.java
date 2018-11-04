package application;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Produto {

	private SimpleIntegerProperty propId = new SimpleIntegerProperty();
	private SimpleStringProperty propProduto = new SimpleStringProperty("");
	private SimpleIntegerProperty propQuantia = new SimpleIntegerProperty();
	private SimpleFloatProperty propPreco = new SimpleFloatProperty();
	
	public Produto() {}
	
	// Declarando objeto com apenas produtom, quantia e preço
	public Produto(String produto, int quantia, float preco) {
		setPropProduto(produto);
		setPropQuantia(quantia);
		setPropPreco(preco);
	}
	
	public Produto(int id, String produto, int quantia, float preco) {
		
	}

	public final SimpleIntegerProperty propIdProperty() {
		return this.propId;
	}
	

	public final int getPropId() {
		return this.propIdProperty().get();
	}
	

	public final void setPropId(final int propId) {
		this.propIdProperty().set(propId);
	}
	

	public final SimpleStringProperty propProdutoProperty() {
		return this.propProduto;
	}
	

	public final String getPropProduto() {
		return this.propProdutoProperty().get();
	}
	

	public final void setPropProduto(final String propProduto) {
		this.propProdutoProperty().set(propProduto);
	}
	

	public final SimpleIntegerProperty propQuantiaProperty() {
		return this.propQuantia;
	}
	

	public final int getPropQuantia() {
		return this.propQuantiaProperty().get();
	}
	

	public final void setPropQuantia(final int propQuantia) {
		this.propQuantiaProperty().set(propQuantia);
	}
	

	public final SimpleFloatProperty propPrecoProperty() {
		return this.propPreco;
	}
	

	public final float getPropPreco() {
		return this.propPrecoProperty().get();
	}
	

	public final void setPropPreco(final float propPreco) {
		this.propPrecoProperty().set(propPreco);
	}
	
}
