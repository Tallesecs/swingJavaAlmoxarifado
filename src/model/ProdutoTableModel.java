package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ProdutoTableModel extends AbstractTableModel {
	private String[] columnNames = { "Produto", "Quantidade", "Valor" };

	private ArrayList<Produto> produtos;

	public ProdutoTableModel(ProdutoList list) {
			produtos = list.getProdutos();
	   }

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		int size;
		if (produtos == null) {
			size = 0;
		} else {
			size = produtos.size();
		}
		return size;
	}

	public Object getValueAt(int row, int col) {
		Object temp = null;
		if (col == 0) {
			temp = produtos.get(row).getNome();
		}else if (col == 1) {
			temp = produtos.get(row).getQuant_estoque();
		} else if (col == 2) {
			temp = new Double(produtos.get(row).getValor());
		}
		return temp;
	}

	// needed to show column names in JTable
	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Class getColumnClass(int col) {
		if (col == 2) {
			return Double.class;
		} else {
			return String.class;
		}
	}
}
