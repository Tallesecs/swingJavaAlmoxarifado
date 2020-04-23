import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dialog;

import javax.swing.JMenuBar;
import javax.swing.JScrollPane;

import model.Produto;
import model.ProdutoList;
import model.ProdutoTableModel;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.plaf.TableUI;
import java.awt.Font;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

public class estoque {

	private static JFrame frame;
	private JTable table;
	private static ProdutoList produtoList = new ProdutoList();
	private ProdutoTableModel produtoModel = new ProdutoTableModel(produtoList);
	private Produto produto;
	private JTextField produtoNome;
	private JTextField qtdeEstoque;
	private JTextField valor;
	private JTextField remEstoqProd;
	private JTextField remEstoqQtde;
	private JTextField addProduto;
	private JTextField addEstoque;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					estoque window = new estoque();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public estoque() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.PINK);
		frame.setBounds(100, 100, 760, 474);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tabela de Produtos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(194, 185, 423, 119);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 16, 418, 107);
		panel.add(scrollPane);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		table = new JTable(produtoModel);
		table.setBackground(Color.BLUE);
		table.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		scrollPane.setViewportView(table);

		JButton btnListar = new JButton("Listar");

		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProdutoTableModel produtoTable = new ProdutoTableModel(produtoList);
				table.setModel(produtoTable);
			}
		});

		btnListar.setBounds(353, 154, 69, 20);
		btnListar.setVisible(false);
		frame.getContentPane().add(btnListar);

		JLabel lblNewLabel = new JLabel("Produto");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel.setBounds(146, 11, 64, 14);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblEstoque = new JLabel("Estoque");
		lblEstoque.setFont(new Font("Arial", Font.BOLD, 15));
		lblEstoque.setBounds(373, 11, 64, 14);
		frame.getContentPane().add(lblEstoque);

		JLabel lblValor = new JLabel("Valor");
		lblValor.setFont(new Font("Arial", Font.BOLD, 15));
		lblValor.setBounds(531, 11, 46, 14);
		frame.getContentPane().add(lblValor);

		produtoNome = new JTextField();
		produtoNome.setBounds(224, 9, 133, 20);
		frame.getContentPane().add(produtoNome);
		produtoNome.setColumns(10);

		qtdeEstoque = new JTextField();
		qtdeEstoque.setBounds(447, 9, 64, 20);
		frame.getContentPane().add(qtdeEstoque);
		qtdeEstoque.setColumns(10);

		valor = new JTextField();
		valor.setBounds(587, 9, 62, 20);
		frame.getContentPane().add(valor);
		valor.setColumns(10);

		JLabel mensagem = new JLabel("");
		mensagem.setForeground(Color.RED);
		mensagem.setFont(new Font("Arial", Font.BOLD, 15));
		mensagem.setBounds(10, 11, 223, 30);
		frame.getContentPane().add(mensagem);

		JButton btnSalvar = new JButton("Add produto");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (estoque.validarCampos(produtoNome.getText(), qtdeEstoque.getText(), valor.getText())) {
					mensagem.setText("Campos Obrigatórios");
				} else {
					if (produtoList.getProdutos().isEmpty() || !produtoList.existeProduto(produtoNome.getText())) {
						mensagem.setText("");
						String preco = valor.getText().replace(",", ".");
						produtoList.add(new Produto(produtoNome.getText(), Integer.parseInt(qtdeEstoque.getText()),
								Double.parseDouble(preco)));
						btnListar.doClick();
					} else {
						mensagem.setText("Produto já está cadastrado");
					}
				}
			}
		});
		btnSalvar.setBounds(313, 53, 149, 20);
		frame.getContentPane().add(btnSalvar);

		JButton btnNewButton = new JButton("Update produto");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (estoque.validarCampos(produtoNome.getText(), qtdeEstoque.getText(), valor.getText())) {
					mensagem.setText("Campos obrigatórios!");
				} else {
					mensagem.setText("");
					String preco = valor.getText().replace(",", ".");
					produtoList.atualizarProduto(new Produto(produtoNome.getText(),
							Integer.parseInt(qtdeEstoque.getText()), Double.parseDouble(preco)));
					btnListar.doClick();
				}
			}
		});
		btnNewButton.setBounds(313, 84, 149, 20);
		frame.getContentPane().add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel("Tirar do estoque");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1.setBounds(305, 308, 292, 14);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel label = new JLabel("Produto");
		label.setFont(new Font("Arial", Font.BOLD, 15));
		label.setBounds(243, 332, 64, 14);
		frame.getContentPane().add(label);

		remEstoqProd = new JTextField();
		remEstoqProd.setColumns(10);
		remEstoqProd.setBounds(315, 333, 133, 20);
		frame.getContentPane().add(remEstoqProd);

		JLabel label_1 = new JLabel("Estoque");
		label_1.setFont(new Font("Arial", Font.BOLD, 15));
		label_1.setBounds(478, 333, 64, 14);
		frame.getContentPane().add(label_1);

		remEstoqQtde = new JTextField();
		remEstoqQtde.setColumns(10);
		remEstoqQtde.setBounds(553, 333, 64, 20);
		frame.getContentPane().add(remEstoqQtde);

		JLabel mensagem2 = new JLabel("");
		mensagem2.setForeground(Color.RED);
		mensagem2.setFont(new Font("Arial", Font.BOLD, 15));
		mensagem2.setBounds(10, 323, 223, 30);
		frame.getContentPane().add(mensagem2);

		JButton btnNewButton_1 = new JButton("Tirar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (produtoList.existeProduto(remEstoqProd.getText())) {
					boolean pode = podeDiminuir(remEstoqProd.getText(), Integer.parseInt(remEstoqQtde.getText()));
					if (pode) {
						mensagem2.setText("");
						produtoList.diminuirEstoque(remEstoqProd.getText(), Integer.parseInt(remEstoqQtde.getText()));
						btnListar.doClick();
					} else {
						mensagem2.setText("Estoque menor do que saída!");
					}
				} else {
					mensagem2.setText("Não existe Produto!");
				}
			}
		});

		btnNewButton_1.setBounds(627, 329, 89, 23);
		frame.getContentPane().add(btnNewButton_1);

		JLabel lblAdicionarQuantidadeEstoque = new JLabel("Add Estoque");
		lblAdicionarQuantidadeEstoque.setFont(new Font("Arial", Font.BOLD, 15));
		lblAdicionarQuantidadeEstoque.setBounds(305, 375, 292, 14);
		frame.getContentPane().add(lblAdicionarQuantidadeEstoque);

		JLabel label_3 = new JLabel("Produto");
		label_3.setFont(new Font("Arial", Font.BOLD, 15));
		label_3.setBounds(243, 400, 64, 14);
		frame.getContentPane().add(label_3);

		addProduto = new JTextField();
		addProduto.setColumns(10);
		addProduto.setBounds(315, 400, 133, 20);
		frame.getContentPane().add(addProduto);

		JLabel label_4 = new JLabel("Estoque");
		label_4.setFont(new Font("Arial", Font.BOLD, 15));
		label_4.setBounds(478, 401, 64, 14);
		frame.getContentPane().add(label_4);

		addEstoque = new JTextField();
		addEstoque.setColumns(10);
		addEstoque.setBounds(553, 400, 64, 20);
		frame.getContentPane().add(addEstoque);

		JButton btnAcrescentar = new JButton("Add");
		btnAcrescentar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (produtoList.existeProduto(addProduto.getText())) {
					mensagem2.setText("");
					produtoList.acrescentarEstoque(addProduto.getText(), Integer.parseInt(addEstoque.getText()));
					btnListar.doClick();
				} else {
					mensagem2.setText("Não existe Produto!");
				}
			}
		});
		btnAcrescentar.setBounds(627, 397, 89, 23);
		frame.getContentPane().add(btnAcrescentar);

		JButton btnRemoverProduto = new JButton("Tirar Produto");
		btnRemoverProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (produtoNome.getText().isEmpty()) {
					mensagem.setText("Coloque o nome do produto");
				} else {
					produtoList.remove(produtoList.procurarPorNome(produtoNome.getText()));
					btnListar.doClick();
				}

			}
		});
		btnRemoverProduto.setBounds(313, 123, 149, 20);
		frame.getContentPane().add(btnRemoverProduto);
	}

	private static boolean validarCampos(String nome, String qtde, String valor) {
		if (nome.isEmpty() || qtde.isEmpty() || valor.isEmpty()) {
			return true;
		}
		return false;
	}

	private boolean podeDiminuir(String nome, int qtde) {
		int qtdeEstoque = produtoList.getQtdeEstoque(nome);

		if (qtdeEstoque >= qtde) {
			return true;
		}

		return false;

	}
}
