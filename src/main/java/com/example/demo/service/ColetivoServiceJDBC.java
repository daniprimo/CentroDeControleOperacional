package com.example.demo.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.InfoGaragemDTO;
import com.example.demo.dto.relatorio.ColetivoDTO;
import com.example.demo.entities.Coletivo;
import com.example.demo.repositoryJDBC.RepositoryJDBC;

@Component
public class ColetivoServiceJDBC implements RepositoryJDBC {
	
	@Autowired
	private Connection connection;

	@Override
	public void criarTabela() throws SQLException {

		String sql = "CREATE TABLE tb_coletivo " + "(id LONG AUTO_INCREMENT," + "prefixo VARCHAR (6) NOT NULL,"
				+ "placa VARCHAR (7) NOT NULL," + "modelo VARCHAR (50)," + "doc VARCHAR (50)," + "cor VARCHAR (50),"
				+ "status VARCHAR (50)," + "GARAGEM_ID int," + "PRIMARY KEY (id),"
				+ "FOREIGN KEY (GARAGEM_ID) REFERENCES tb_garagem (id));";

		try (PreparedStatement prepareStatement = connection.prepareStatement(sql)) {
			prepareStatement.execute();
			System.out.println("Tabela de coletivo crida com sucesso");
		}
	}

	@Override
	public List<Coletivo> listarColetivos()  {
		List<Coletivo> coletivos = new ArrayList<>();

		String sql = "SELECT * FROM tb_coletivo;";

		try (PreparedStatement prepareStatement = connection.prepareStatement(sql)) {
			prepareStatement.execute();

			try (ResultSet resultSet = prepareStatement.getResultSet()) {
				while (resultSet.next()) {
					Coletivo coletivo = new Coletivo(resultSet.getLong(1), resultSet.getString(4),
							resultSet.getString(5), resultSet.getString(3), resultSet.getString(6),
							resultSet.getString(2), resultSet.getString(7));
					coletivos.add(coletivo);
				}
			}
		} catch (SQLException e) {
			System.out.println("Erro no repositorio listar coletivos");
			e.printStackTrace();
		}

		return coletivos;
	}

	@Override
	public Long adicionarColetivo(ColetivoDTO dto)  {		
		String sql = "INSERT INTO tb_coletivo (prefixo," + " placa, modelo, cor, doc, status)"
				+ "VALUES (? , ? , ? , ? , ? ,?);";
		Long id = null;
		
		try (PreparedStatement prepareStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			prepareStatement.setString(1, dto.getPrefixo());
			prepareStatement.setString(2, dto.getPlaca());
			prepareStatement.setString(3, dto.getModelo());
			prepareStatement.setString(4, dto.getCor());
			prepareStatement.setString(5, dto.getDoc());
			prepareStatement.setString(6, dto.getStatus());

			prepareStatement.execute();

			try (ResultSet resultSet = prepareStatement.getGeneratedKeys()) {
				while (resultSet.next()) {
					System.out.println("Coletivo adicionado ID: " + resultSet.getLong(1));
					id = resultSet.getLong(1);

				}
			}
		} catch (SQLException e) {
			System.out.println("Erro ao adicionar coletivo");
			e.printStackTrace();
		}

		return id;
	}

	@Override
	public Coletivo buscarColetivoPeloPrefixo(String prefixo) {
		Coletivo resultado = new Coletivo();

		String sql = "SELECT * FROM tb_coletivo WHERE prefixo = ?;";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, prefixo);
			preparedStatement.execute();

			try (ResultSet resultSet = preparedStatement.getResultSet()) {
				while (resultSet.next()) {
					System.out.println(resultSet.getString(2));
					Coletivo coletivo = new Coletivo(resultSet.getLong(1), resultSet.getString(4),
							resultSet.getString(5), resultSet.getString(3), resultSet.getString(6),
							resultSet.getString(2), resultSet.getString(7));
					resultado = coletivo;
				}
			}
		} catch (SQLException e) {
			System.out.println("Erro buscar coletivos por prefixos");
			e.printStackTrace();
		}

		return resultado;
	}

	@Override
	public Coletivo buscarColetivoPelaPlaca(String placa) {
		Coletivo resultado = new Coletivo();

		String sql = "SELECT * FROM tb_coletivo WHERE placa = ?;";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, placa);
			preparedStatement.execute();

			try (ResultSet resultSet = preparedStatement.getResultSet()) {
				while (resultSet.next()) {
					Coletivo coletivo = new Coletivo(resultSet.getLong(1), resultSet.getString(4),
							resultSet.getString(5), resultSet.getString(3), resultSet.getString(6),
							resultSet.getString(2), resultSet.getString(7));
					resultado = coletivo;
				}
			}
		} catch (SQLException e) {
			System.out.println("Erro buscar coletivos pela placa");
			e.printStackTrace();
		}

		return resultado;
	}

	@Override
	public Coletivo buscarColetivoPeloId(Long id)  {
		Coletivo resultado = new Coletivo();

		String sql = "SELECT * FROM tb_coletivo WHERE id = ?;";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setLong(1, id);
			preparedStatement.execute();

			try (ResultSet resultSet = preparedStatement.getResultSet()) {
				while (resultSet.next()) {
					Coletivo coletivo = new Coletivo(resultSet.getLong(1), resultSet.getString(4),
							resultSet.getString(5), resultSet.getString(3), resultSet.getString(6),
							resultSet.getString(2), resultSet.getString(7));
					resultado = coletivo;
				}
			}
		} catch (SQLException e) {
			System.out.println("Erro buscar coletivos por id");
			e.printStackTrace();
		}

		return resultado;

	}

	@Override
	public void editarColetivo(Long id, ColetivoDTO coletivo)  {

		String sql = "update tb_coletivo set prefixo = ?," + "placa = ?," + "modelo = ?," + "cor = ?," + "doc = ?,"
				+ "status = ?" + "where id = ?;";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setLong(7, id);
			preparedStatement.setString(1, coletivo.getPrefixo());
			preparedStatement.setString(2, coletivo.getPlaca());
			preparedStatement.setString(3, coletivo.getModelo());
			preparedStatement.setString(4, coletivo.getCor());
			preparedStatement.setString(5, coletivo.getDoc());
			preparedStatement.setString(6, coletivo.getStatus());

			preparedStatement.execute();

		} catch (SQLException e) {
			System.out.println("Erro ao editar coletivo");
			e.printStackTrace();
		}

	}

	@Override
	public void excluirColetivoPorPrefixo(String prefixo){

		String sql = "DELETE FROM tb_coletivo WHERE prefixo = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, prefixo);
			preparedStatement.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao excluir coletivos");
			e.printStackTrace();
		}

		System.out.println(prefixo + " Prefixo excluido com sucesso!!");

	}

	@Override
	public void excluirColetivoPorPlaca(String placa) {
		String sql = "DELETE FROM tb_coletivo WHERE placa = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, placa);
			preparedStatement.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao excluir coletivo por placa");
			e.printStackTrace();
		}

		System.out.println(placa + " Prefixo excluido com sucesso!!");

	}

	@Override
	public InfoGaragemDTO quantosColetivosNaGaragem() {
		String sql = "SELECT c.prefixo, g.nome FROM tb_coletivo as C"
				+ " INNER JOIN tb_garagem as G on G.ID = C.GARAGEM_ID";
		int filial = 0;
		int matriz = 0;

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.execute();

			try (ResultSet resultSet = preparedStatement.getResultSet()) {
				while (resultSet.next()) {
					if ("Garagem Filial".equals(resultSet.getString(2)))
						filial++;
					if ("Garagem Matriz".equals(resultSet.getString(2)))
						matriz++;
				}
			}
		} catch (SQLException e) {
			System.out.println("Erro na contagem de coletivos bas garagens");
			e.printStackTrace();
		}

		InfoGaragemDTO garagem = new InfoGaragemDTO(matriz, filial);
		return garagem;

	}

}
