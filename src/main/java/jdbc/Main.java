package jdbc;

import java.sql.SQLException;
import java.util.List;

public class Main {

	public static void main(String[] args) throws SQLException {
		ProdutoJdbc produtoJdbc = new ProdutoJdbc();

		// insere produtos
		produtoJdbc.inserir(new Produto("Playstation 5", 5900.00));
		produtoJdbc.inserir(new Produto("iPhone 11", 3500.00));
		produtoJdbc.inserir(new Produto("Kindle 10", 426.00));

		// lista todos os produtos
		List<Produto> todos = produtoJdbc.buscarTodos();
		exibirProdutos(todos);

		// busca e atualiza preco para Playstation 5
		Produto playstatio5 = produtoJdbc.buscarPor("Playstation 5");
		playstatio5.setPreco(5275.0);
		produtoJdbc.atualizar(playstatio5);
		exibirProdutos(produtoJdbc.buscarTodos());

		// busca e deleta iPhone 11
		Produto iphone11 = produtoJdbc.buscarPor("iPhone 11");
		iphone11.setPreco(5275.0);
		produtoJdbc.excluir(iphone11.getId());
		exibirProdutos(produtoJdbc.buscarTodos());
	}

	private static void exibirProdutos(List<Produto> produtos) {
		System.out.println("*** Produtos ***");
		for (Produto produto : produtos) {
			System.out.println(
					"ID: " + produto.getId() + ", Nome: " + produto.getNome() + ", Preço: " + produto.getPreco());
		}
	}

}
