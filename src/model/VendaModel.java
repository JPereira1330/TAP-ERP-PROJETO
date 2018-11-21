package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import application.Produto;
import db.Conexao;
import javafx.scene.control.Alert.AlertType;

public class VendaModel {

	/**
	 * Metodo responsavel por inserir dados no banco de dados
	 * 
	 * @param produto
	 * @param quantia
	 * @param preco
	 */
	public static void inserir(String produto, int quantia, float preco) {
		
		try {
			
			// Criando a conexão com o banco de dados
			Connection conn = Conexao.getConexao();
			
			// Preparando o codigo de inserção no banco de dados
			String sql = "INSERT INTO venda (produto, quantia, preco) VALUES (?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, produto);
			ps.setInt(2, quantia);
			ps.setFloat(3, preco);
			
			// Executando comando
			ps.executeUpdate();
			
			// Finalizando conexão
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Metodo responsavel por retornar as vendas
	 * 
	 * @return ArrayList<Produto> Retornar os proutos vendidos
	 */
	public static ArrayList<Produto> select() {
		
		// Criando variavel de retorno
		ArrayList<Produto> listaProdutos = new ArrayList<Produto>();
		
		// Craindo objeto Produto para retorno
		Produto produto;
		
		// Limpando ArrayList
		listaProdutos.clear();
		
		try {
			
			// Criando a conexão com o banco de dados
			Connection conn = Conexao.getConexao();
			
			// Preparando o codigo de inserção no banco de dados
			String sql = "SELECT * FROM venda";
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
	
}
