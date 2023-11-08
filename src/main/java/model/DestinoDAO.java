package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DestinoDAO {

	private Connection conexao;
	private String sql;
	
	public DestinoDAO() throws Exception {
		conexao = Conexao.createConnectionToMySQL();
	}
	
	
	public void fecharConexao() {
		try {
			if (conexao != null && !conexao.isClosed()) {
				conexao.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void inserirDestino(Destino destino) {
		sql = "INSERT INTO destinos (cidade, pais, valor) VALUES (?,?,?)";
		
		try(PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setString(1,destino.getCidade());
			stmt.setString(2,destino.getPais());
			stmt.setDouble(3,destino.getValor());
			
			
			
			stmt.executeUpdate();
			
			System.out.println("Destino criado com sucesso!");
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void alterarDestino(Destino destino) {

		String sql = "UPDATE destinos SET cidade = ?, pais = ?, valor = ? WHERE id = ?";

		
		try(PreparedStatement stmt = conexao.prepareStatement(sql)) {
			
			stmt.setString(1, destino.getCidade());
			stmt.setString(2, destino.getPais());
			stmt.setDouble(3, destino.getValor());
			 
			ResultSet resultado = stmt.executeQuery();
			
			if(resultado.next()) {
				
				
				destino.setId(resultado.getInt("id"));
				destino.setCidade(resultado.getString("cidade"));
				destino.setPais(resultado.getString("pais"));
				destino.setValor(resultado.getDouble("valor"));
				
			}
			
		}  catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public List<Destino> listarDestinos() {
		String sql = "SELECT * FROM destinos";

		List<Destino> destinos = new ArrayList<Destino>();

		ResultSet resultado = null;
		
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			resultado = stmt.executeQuery();

			while (resultado.next()) {
				
				Destino destino = new Destino();
				
				destino = new Destino();
				
				destino.setId(resultado.getInt("id"));
				destino.setCidade(resultado.getString("cidade"));
				destino.setPais(resultado.getString("pais"));
				destino.setValor(resultado.getDouble("valor"));
				
				destinos.add(destino);
			}
		} catch (Exception e) {

			e.printStackTrace();

		}
		return destinos;
	}

	
	
	public void excluirDestinos(int id) {
		sql = "DELETE FROM destinos WHERE id = ?";
		try(PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}