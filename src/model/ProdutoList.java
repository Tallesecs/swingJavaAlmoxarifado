package model;

import java.util.ArrayList;

public class ProdutoList {
	private ArrayList<Produto> produtoList;

	public ProdutoList() {
		produtoList = new ArrayList<Produto>();
	}

	public void add(Produto produto) {
		if (produtoList.size() < 10) {
			produto.setId(produtoList.size() + 1);
			produtoList.add(produto);
		}
	}

	public ArrayList<Produto> getProdutos() {
		return produtoList;
	}

	public void remove(Produto produto) {
		produtoList.remove(produto);
	}

	private int procurarProduto(Produto produto) {
		int index = 0;
		for (Produto p : produtoList) {
			if (p.getNome().equals(produto.getNome())) {
				index = p.getId() - 1;
			}
		}

		return index;
	}

	public void atualizarProduto(Produto produto) {
		int index = procurarProduto(produto);
		produto.setId(index + 1);
		produtoList.set(index, produto);
	}

	public Produto procurarPorNome(String nome) {
		int index = 0;
		for (Produto p : produtoList) {
			if (p.getNome().equals(nome)) {
				index = p.getId() - 1;
			}
		}
		return produtoList.get(index);
	}

	public int getQtdeEstoque(String nome) {
		Produto p = procurarPorNome(nome);
		return p.getQuant_estoque();
	}

	public void diminuirEstoque(String nome, int qtde) {
		Produto p = procurarPorNome(nome);
		p.setQuant_estoque(p.getQuant_estoque() - qtde);
	}

	public void acrescentarEstoque(String nome, int qtde) {
		Produto p = procurarPorNome(nome);
		p.setQuant_estoque(p.getQuant_estoque() + qtde);
	}

	public boolean existeProduto(String nome) {
		for (Produto p : produtoList) {
			if (p.getNome().equals(nome)) {
				return true;
			}
		}

		return false;
	}

}
