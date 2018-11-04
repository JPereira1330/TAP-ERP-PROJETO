package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import application.Produto;
import db.Conexao;

public class LoginModel {

	/**
	 * Metodo responsavel por inserir um novo usuario no banco de dados
	 * 
	 * @param user <String> Nome do usuario
	 * @param pass <String> Senha do usuario
	 * @param nome <String> nome do usuario
	 */
	public static void inserir(String user, String pass, String nome) {
		
		try {
			
			// Criando a conex�o com o banco de dados
			Connection conn = Conexao.getConexao();
			
			// Preparando o codigo de inser��o no banco de dados
			String sql = "INSERT INTO usuarios (user, pass, nome) VALUES (?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user);
			ps.setString(2, pass);
			ps.setString(3, nome);
			
			// Executando comando
			ps.executeUpdate();
			
			// Finalizando conex�o
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Metodo responsavel por procurar o usuario no banco de dados
	 * 
	 * @param login <String> Login do usuario
	 * @param senha <String> Senha do usuario
	 * 
	 * @return boolean informando se existe ou n�o o usuario
	 */
	public static boolean validar(String login, String senha) {

		try {
			
			// Criando a conex�o com o banco de dados
			Connection conn = Conexao.getConexao();
			
			// Preparando o codigo de inser��o no banco de dados
			String sql = "SELECT * FROM usuario";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			// Converte os dados capturados para uma arrayList
			while(rs.next()) {
				if(rs.getString("User").equals(login))
					if(rs.getString("Senha").equals(senha))
						return true;
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Metodo responsavel por remover a quantia dos produto no estoque
	 * 
	 * @return boolean Retornar para informar se funcionou ou n�o a opera��o 
	 */
	public static boolean remover (int id, int quantia) {
		
		// Variavel auxiliar
		boolean continuar = true;
		
		// Capturar os dados
		ArrayList<Produto> prod = EstoqueModel.select();
		
		// Retirando quantia informada pelo usuario
		for (int i = 0; i < prod.size(); i++) {
			
			// Buscando por produto
			if(prod.get(i).getPropId() == id) {
				
				// Calculando diferen�a
				int temp = prod.get(i).getPropQuantia() - quantia;
				
				// Verificando se o numero fica negativo
				if( temp >= 0)
					quantia = temp;
				else
					continuar = false;
				
			}
		}
		
		try {
			
			// Continua caso a remo��o n�o deixa a quantia negativa
			if(continuar) {
			
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
			
				// Retornando sucesso da remo��o
				return true;
				
			}else
				
				// Retornando erro na remo��o
				return false;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return true;
	} 
}
