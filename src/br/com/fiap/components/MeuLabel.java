package br.com.fiap.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class MeuLabel extends JLabel{

	
	private static final long serialVersionUID = 1L;

	public MeuLabel (String text){
		super(text);
		init();
	}
	
	public MeuLabel(String text, int tamanho) {
		super(text);
		setFonte(tamanho);
	}

	private void setFonte(int tamanho) {
		this.setFont(new Font("Serif", Font.PLAIN, tamanho));
		
	}

	private void init() {
		
		this.setForeground(Color.black);
		
	}
	
	

}
