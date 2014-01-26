package br.ssad.cliente.app;

import javax.swing.JOptionPane;

public class Menu {

	public void criaMenu(){
		Operacoes operacoes = new Operacoes();
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
			boolean erro = false;
			if(op.equals("1")){
				while(!erro)
					erro = operacoes.criarCliente();
			}else if(op.equals("2")){
				while(!erro)
					erro = operacoes.criarDiretor();
			}else if(op.equals("3")){
				while(!erro)
					erro = operacoes.criarFilme();
			}else if(op.equals("4")){
				while(!erro)
					erro = operacoes.criarExemplar();
			}else if(op.equals("5")){
				while(!erro)
					erro = operacoes.criarEmprestimo();
			}else if(op.equals("6")){
				while(!erro)
					erro = operacoes.encerrarEmprestimo();
			}else if(op.equals("7")){
				while(!erro)
					erro = operacoes.excluirDiretor();
			}else if(op.equals("0")){
				System.exit(0);
			}else{
				JOptionPane.showMessageDialog(null, "Opção incorreta", "Locadora", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}