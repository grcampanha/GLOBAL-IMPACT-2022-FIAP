package br.com.fiap.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.function.ToIntFunction;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import br.com.fiap.view.Janela;
import br.com.fiap.dao.PostoEletricoDao;
import br.com.fiap.model.PostoEletrico;

public class BotaoListener implements ActionListener {

	private Janela janela;
	PostoEletricoDao dao = new PostoEletricoDao();

	public BotaoListener(Janela janela) {
		this.janela = janela;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand() == "Salvar") {
				PostoEletrico postos = new PostoEletrico();
				postos.setNome(janela.getNome().getText());
				postos.setRua(janela.getRua().getText());
				postos.setBairro(janela.getBairro().getText());
				postos.setCidade(janela.getCidade().getText());
				postos.setPrecoKwh(new Float(Float.parseFloat(janela.getPreco().getText())));
				postos.setPlugins(janela.getPlugs());
				postos.setEstado(janela.getEstado().getSelectedItem().toString());
				postos.setStarRate(janela.getStarRater().getSelection());
				dao.inserir(postos);
				janela.carregarDados();
				janela.limpar();
				JOptionPane.showMessageDialog(null, "Posto Elétrico criado");	
		}
		if (e.getActionCommand() == "Ordenar") {
			janela.carregarDadosOrdenados();
			JOptionPane.showMessageDialog(null, "Lista Ordenada");
		}
	}
}
