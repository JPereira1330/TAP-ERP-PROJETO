package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import application.Produto;
import db.Conexao;
import javafx.scene.control.Alert.AlertType;

public class EstoqueModel {

	/**
	 * Metodo responsavel por inserir um novo produto no estoque
	 * 
	 * @param produto <String> Nome do produto
	 * @param quantia <Integer> Quantia de pe�as do produto em estoque
	 * @param preco <Float>	Pre�o de cada pe�a
	 */
	public static void inserir(String produto, int quantia, float preco) {
		
		try {
			
			// Criando a conex�o com o banco de dados
			Connection conn = Conexao.getConexao();
			
			// Preparando o codigo de inser��o no banco de dados
			String sql = "INSERT INTO estoque (Produto, Quantia, Preco) VALUES (?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, produto);
			ps.setInt(2, quantia);
			ps.setFloat(3, preco);
			
			// Executando comando
			ps.executeUpdate();
			
			// Finalizando conex�o
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Metodo responsavel por retornar os produto no estoque
	 * 
	 * @return ArrayList<Produto> Retornar o estoque dos produto 
	 */
	public static ArrayList<Produto> select() {
		
		// Criando variavel de retorno
		ArrayList<Produto> listaProdutos = new ArrayList<Produto>();
		
		// Craindo objeto Produto para retorno
		Produto produto;
		
		// Limpando ArrayList
		listaProdutos.clear();
		
		try {
			
			// Criando a conex�o com o banco de dados
			Connection conn = Conexao.getConexao();
			
			// Preparando o codigo de inser��o no banco de dados
			String sql = "SELECT * FROM estoque";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			// Converte os dados capturados para uma arrayList
			while(rs.next()) {
				produto = new Produto(
						rs.getInt("Id"), 
						rs.getString("Produto"), 
						rs.getInt("Quantia"), 
						rs.getFloat("Preco")
				);
				listaProdutos.add(produto);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return listaProdutos;
	}
	
	/**
	 * Metodo responsavel por remover a quantia dos produto no estoque
	 */
	public static void remover(int id, int quantia) {
		
		// Capturar os dados
		ArrayList<Produto> prod = EstoqueModel.select();
		
		// Retirando quantia informada pelo usuario
		for (int i = 0; i < prod.size(); i++) {
			if(prod.get(i).getPropId() == id)
				quantia = prod.get(i).getPropQuantia() - quantia;
		}
		
		try {
			
			// Criando a conex�o com o banco de dados
			Connection conn = Conexao.getConexao();
			
			// Preparando o codigo de inser��o no banco de dados
			String sql = "UPDATE estoque SET Quantia=(?) WHERE Id = (?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, quantia);
			ps.setInt(2, id);
			
			// Executando comando
			ps.executeUpdate();
			
			// Finalizando conex�o
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
