package br.com.fiap.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.fiap.components.InputText;
import br.com.fiap.components.MeuLabel;
import br.com.fiap.components.StarRater;
import br.com.fiap.controller.BotaoListener;
import br.com.fiap.dao.PostoEletricoDao;
import br.com.fiap.model.PostoEletrico;

public class Janela extends JFrame {

	InputText inputNome = new InputText(15);
	InputText inputRua = new InputText(16);
	InputText inputBairro = new InputText(15);
	InputText inputCidade = new InputText(15);
	InputText inputPreco = new InputText(5);

	MeuLabel nomeLabel = new MeuLabel("NOME", 15);
	MeuLabel endereçoLabel = new MeuLabel("ENDEREÇO", 15);
	MeuLabel avaliacaoLabel = new MeuLabel("AVALIAÇÃO: ", 15);
	MeuLabel ruaLabel = new MeuLabel("RUA", 15);
	MeuLabel bairroLabel = new MeuLabel("BAIRRO", 15);
	MeuLabel cidadeLabel = new MeuLabel("CIDADE", 15);
	MeuLabel precoLabel = new MeuLabel("PREÇO DO KWH: ", 15);
	MeuLabel estadoLabel = new MeuLabel("ESTADO", 15);
	MeuLabel plugsLabel = new MeuLabel("PLUGS:", 15);
	
	String[] estadosBrasil = { " ", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MS", "MT", "PA", "PB", "PR",
			"PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO", };
	JComboBox<String> estados = new JComboBox<>(estadosBrasil);
	
	StarRater starRater = new StarRater();

	JCheckBox tipo1 = new JCheckBox("TIPO1");
	JCheckBox tipo2 = new JCheckBox("TIPO2");
	JCheckBox css2 = new JCheckBox("CSS2");
	JCheckBox chaDemo = new JCheckBox("CHADEMO");
	List<String> checkedBoxes = new ArrayList<String>();

	JPanel cadastroPainel = new JPanel(new GridLayout(0, 1, 0, 0));
	JTabbedPane abas = new JTabbedPane();


	JPanel ruaPainel = new JPanel();
	JPanel bairroPainel = new JPanel();
	JPanel cidadePainel = new JPanel();
	JPanel estadoPainel = new JPanel();
	JPanel PlugsPainel = new JPanel();

	JPanel nome = new JPanel();
	JPanel preco = new JPanel();
	JPanel botaos = new JPanel();
	JButton salvar = new JButton("Salvar");
	JButton ordenar = new JButton("Ordenar");

	JPanel avalia = new JPanel(new FlowLayout());

	BotaoListener botaoListener = new BotaoListener(this);

	String[] colunas = { "ID", "NOME", "RUA", "BAIRRO", "CIDADE", "ESTADO", "PLUGINS", "AVALIAÇÃO", "PREÇOS" };
	DefaultTableModel tableModel = new DefaultTableModel(colunas, 0);
	JTable tabela = new JTable(tableModel);

	public Janela() {
		setSize(390, 370);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void init() {
		cadastroPainel.setBorder(BorderFactory.createTitledBorder("Posto Elétrico"));
		nome.add(nomeLabel);
		nome.add(inputNome);

		cadastroPainel.add(nome);
		ruaPainel.add(ruaLabel);
		ruaPainel.add(inputRua);
		bairroPainel.add(bairroLabel);
		bairroPainel.add(inputBairro);
		cidadePainel.add(cidadeLabel);
		cidadePainel.add(inputCidade);
		estadoPainel.add(estadoLabel);
		estadoPainel.add(estados);
		cadastroPainel.add(ruaPainel);
		cadastroPainel.add(bairroPainel);
		cadastroPainel.add(cidadePainel);
		cadastroPainel.add(estadoPainel);

		avalia.add(avaliacaoLabel);
		avalia.add(starRater);
		cadastroPainel.add(avalia);
		PlugsPainel.add(tipo1);
		PlugsPainel.add(tipo2);
		PlugsPainel.add(css2);
		PlugsPainel.add(chaDemo);
		cadastroPainel.add(PlugsPainel);
		preco.add(precoLabel);
		preco.add(inputPreco);
		cadastroPainel.add(preco);

		botaos.add(salvar);
		botaos.add(ordenar);

		cadastroPainel.add(botaos);

		abas.add("Cadastro", cadastroPainel);
		abas.add("Lista", new JScrollPane(tabela));

		add(abas);
		
		salvar.addActionListener(botaoListener);
		ordenar.addActionListener(botaoListener);
		setVisible(true);

	}

	public void carregarDados() {

		tableModel.setRowCount(0);
		List<PostoEletrico> lista = new PostoEletricoDao().listarTodos();
		lista.forEach(posto -> tableModel.addRow(posto.getData()));

	}

	public void carregarDadosOrdenados() {
		tableModel.setRowCount(0);
		List<PostoEletrico> list = new PostoEletricoDao().ordenarTodos();
		list.forEach(posto -> tableModel.addRow(posto.getData()));
	}

	public void limpar() {
		inputNome.setText("");
		inputRua.setText("");
		inputBairro.setText("");
		inputCidade.setText("");
		estados.setSelectedIndex(0);
		tipo1.setSelected(false);
		tipo2.setSelected(false);
		css2.setSelected(false);
		chaDemo.setSelected(false);
		inputPreco.setText("");
		starRater.setSelection(0);
	}

	public InputText getNome() {
		return inputNome;
	}

	public InputText getRua() {
		return inputRua;
	}

	public InputText getCidade() {
		return inputCidade;
	}

	public InputText getBairro() {
		return inputBairro;
	}

	public InputText getPreco() {
		return inputPreco;
	}

	public JComboBox<String> getEstado() {
		return estados;
	}

	public List<String> getPlugs() {
		for (java.awt.Component child : PlugsPainel.getComponents()) {
			if (child instanceof JCheckBox) {
				JCheckBox checkBox = (JCheckBox) child;
				if (checkBox.isSelected()) {
					checkedBoxes.add(checkBox.getText());
				}
			}
		}
		return checkedBoxes;
	}

	public StarRater getStarRater() {
		return starRater;
	}
}