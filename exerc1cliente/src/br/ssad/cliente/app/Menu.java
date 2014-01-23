package br.ssad.cliente.app;

import javax.swing.JOptionPane;

import br.ssad.classes.Diretor;
import br.ssad.classes.Exemplar;
import br.ssad.classes.Filme;
import br.ssad.modelo.Locadora;

public class Menu {
	private Locadora locadora;

	public Menu(Locadora locadora) {
		this.locadora = locadora;
	}

	public int criaMenu(){
		String op = "-1";
		String texto = ">>>>> Locadora <<<<<\n\n" + 
					"1 - Cadastrar Cliente:\n" +
					"2 - Cadastrar Diretor:\n" +
					"3 - Cadastrar Filme:\n" +
					"4 - Cadastrar Exemplar:\n" +
					"5 - Cadastrar Empréstimo:\n" +
					"6 - Devolução Empréstimo:\n" + 
					"7 - Excluir Diretor:\n" +
					"0 - Sair:\n" + 
					"\nEscolha sua opção"; 
		while(!op.equalsIgnoreCase("0")){
			op = JOptionPane.showInputDialog(texto);
			if(op.equals("1")){
				return 1;
			}else if(op.equals("2")){
				return 2;
			}else if(op.equals("3")){
				return 3;
			}else if(op.equals("4")){
				return 4;
			}else if(op.equals("5")){
				return 5;
			}else if(op.equals("6")){
				return 6;
			}else if(op.equals("7")){
				return 7;
			}else if(op.equals("8")){
				return 8;
			}else if(op.equals("0")){
				System.exit(0);
			}else{
				JOptionPane.showMessageDialog(null, "Opção incorreta", "Locadora", JOptionPane.ERROR_MESSAGE);
			}
		}
		return 0;
	}
	
	public boolean criarDiretor(){
		try{
			Diretor diretor = new Diretor();
			diretor.setNome(JOptionPane.showInputDialog("Forneça o Nome do diretor:"));
			try{
				this.locadora.cadastrarDiretor(diretor);
				JOptionPane.showMessageDialog(null, "Diretor cadastrado com sucesso!", "Locadora", JOptionPane.INFORMATION_MESSAGE);
			}catch(Exception e){
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Erro ao cadastrar diretor!", "Locadora", JOptionPane.ERROR_MESSAGE);
			}
			return true;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Você forneceu algum valor incorreto!", "Locadora", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	public boolean criarFilme(){
		try{
			Filme filme = new Filme();
			filme.setNome(JOptionPane.showInputDialog("Forneça o Nome do filme:"));
			filme.setAnoLancamento(Integer.parseInt(JOptionPane.showInputDialog("Forneça o ano de lançamento:")));
			filme.setDuracao(Double.parseDouble(JOptionPane.showInputDialog("Forneça a duração do filme:")));
			filme.setGenero(JOptionPane.showInputDialog("Forneça o gênero do filme:"));
			int id = Integer.parseInt(JOptionPane.showInputDialog("Forneça o id do diretor:"));
			filme.setValorLocacao(Double.parseDouble(JOptionPane.showInputDialog("Forneça o valor de locação do filme:")));
			try{
				this.locadora.cadastrarFilme(filme, id);
				JOptionPane.showMessageDialog(null, "Filme cadastrado com sucesso!", "Locadora", JOptionPane.INFORMATION_MESSAGE);
			}catch(Exception e){
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Erro ao cadastrar filme!", "Locadora", JOptionPane.ERROR_MESSAGE);
			}
			return true;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Você forneceu algum valor incorreto!", "Locadora", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	public boolean criarExemplar(){
		try{
			Exemplar exemplar = new Exemplar();
			int id = Integer.parseInt(JOptionPane.showInputDialog("Forneça o id do filme:"));
			exemplar.setDataAquisicao(JOptionPane.showInputDialog("Forneça a data de aquisição do Exemplar:"));
			exemplar.setEmprestado("n");
			try{
				this.locadora.cadastrarExemplar(exemplar, id);
				JOptionPane.showMessageDialog(null, "Exemplar cadastrado com sucesso!", "Locadora", JOptionPane.INFORMATION_MESSAGE);
			}catch(Exception e){
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Erro ao cadastrar exemplar!", "Locadora", JOptionPane.ERROR_MESSAGE);
			}
			return true;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Você forneceu algum valor incorreto!", "Locadora", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
}
