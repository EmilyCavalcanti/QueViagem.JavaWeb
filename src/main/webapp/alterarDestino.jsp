package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DestinoDAO {

	Connection conn = null;
	PreparedStatement pstm = null;

	public void save(Destino destino) {


		String sql = "INSERT INTO destinos(cidade, pais, valor) VALUES(?,?,?)";

		try {
			conn = Conexao.createConnectionToMySQL();

			pstm = conn.prepareStatement(sql);

			pstm.setString(1, destino.getCidade());
			pstm.setString(2, destino.getPais());
			pstm.setDouble(3, destino.getValor());

			pstm.execute();

		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {
				if (pstm != null) {

					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}

			} catch (Exception e) {

				e.printStackTrace();
			}
		}
	}

	public void removeById(int id) {

		String sql = "DELETE FROM destinos WHERE id = ?";

		try {
			conn = Conexao.createConnectionToMySQL();

			pstm = conn.prepareStatement(sql);

			pstm.setInt(1, id);

			pstm.execute();

		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {
				if (pstm != null) {

					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}

			} catch (Exception e) {

				e.printStackTrace();
			}
		}
	}

	public void update(Destino destino) {

		String sql = "UPDATE destinos SET cidade = ?, pais = ?, valor = ? WHERE id = ?";

		try {
			conn = Conexao.createConnectionToMySQL();

			pstm = conn.prepareStatement(sql);

			pstm.setString(1, destino.getCidade());
			pstm.setString(2, destino.getPais());
			pstm.setDouble(3, destino.getValor());

			pstm.setInt(4, destino.getId());

			pstm.execute();

		} catch (Exception e) {

			e.printStackTrace();
		} finally {


			try {
				if (pstm != null) {

					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}

			} catch (Exception e) {

				e.printStackTrace();
			}
		}
	}

	public List<Destino> getDestinos() {

		String sql = "SELECT * FROM destinos";

		List<Destino> destinos = new ArrayList<Destino>();

		ResultSet rset = null;

		try {
			conn = Conexao.createConnectionToMySQL();

			pstm = conn.prepareStatement(sql);

			rset = pstm.executeQuery();

			while (rset.next()) {

				Destino destino = new Destino();

				destino.setId(rset.getInt("id"));

				destino.setCidade(rset.getString("cidade"));

				destino.setPais(rset.getString("pais"));

				destino.setValor(rset.getDouble("valor"));

				destinos.add(destino);
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {

				if (rset != null) {

					rset.close();
				}

				if (pstm != null) {

					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}

			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		return destinos;
	}


	public Destino getDestinoById(int id) {

		String sql = "SELECT * FROM destinos where id = ?";
		Destino destino = new Destino();

		ResultSet rset = null;

		try {
			conn = Conexao.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			rset = pstm.executeQuery();

			rset.next();

			destino.setCidade(rset.getString("cidade"));
			destino.setPais(rset.getString("pais"));
			destino.setValor(rset.getDouble("valor"));
			destino.setId(rset.getInt("id"));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return destino;
	}
}