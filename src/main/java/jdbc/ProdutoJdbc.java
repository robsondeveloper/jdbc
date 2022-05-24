package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoJdbc {

	public void inserir(Produto produto) {
		String sql = "INSERT INTO produto (nome, preco) VALUES (?, ?);";
		try (Connection conexao = new ConexaoUtil().obterConexao();
				PreparedStatement prepareStatement = conexao.prepareStatement(sql);) {

			prepareStatement.setString(1, produto.getNome());
			prepareStatement.setDouble(2, produto.getPreco());
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Produto> buscarTodos() {
		List<Produto> produtos = new ArrayList<>();
		String sql = "SELECT id, nome, preco FROM produto;";
		try (Connection conexao = new ConexaoUtil().obterConexao();
				PreparedStatement prepareStatement = conexao.prepareStatement(sql);) {

			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String nome = resultSet.getString("nome");
				double preco = resultSet.getDouble("preco");
				Produto produto = new Produto(id, nome, preco);
				produtos.add(produto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return produtos;
	}

	public Produto buscarPor(String nomeProduto) {
		String sql = "SELECT id, nome, preco FROM produto WHERE nome = ?;";
		try (Connection conexao = new ConexaoUtil().obterConexao();
				PreparedStatement prepareStatement = conexao.prepareStatement(sql);) {

			prepareStatement.setString(1, nomeProduto);

			ResultSet resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String nome = resultSet.getString("nome");
				double preco = resultSet.getDouble("preco");
				return new Produto(id, nome, preco);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void excluir(Long produtoId) {
		String sql = "DELETE FROM produto WHERE id = ?;";
		try (Connection conexao = new ConexaoUtil().obterConexao();
				PreparedStatement prepareStatement = conexao.prepareStatement(sql);) {

			prepareStatement.setLong(1, produtoId);
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void atualizar(Produto produto) {
		String sql = "UPDATE produto SET nome = ?, preco = ? WHERE id = ?;";
		try (Connection conexao = new ConexaoUtil().obterConexao();
				PreparedStatement prepareStatement = conexao.prepareStatement(sql);) {

			prepareStatement.setString(1, produto.getNome());
			prepareStatement.setDouble(2, produto.getPreco());
			prepareStatement.setLong(3, produto.getId());
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
