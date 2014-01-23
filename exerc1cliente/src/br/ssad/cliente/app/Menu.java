package br.ssad.cliente.app;

import javax.swing.JOptionPane;

public class Menu {

	void criaMenu(){
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
		while(op.equalsIgnoreCase("0")){
			op = JOptionPane.showInputDialog(texto);
			if(op.equals("1")){
				
			}else if(op.equals("1")){
				
			}else if(op.equals("2")){
				
			}else if(op.equals("3")){
				
			}else if(op.equals("4")){
				
			}else if(op.equals("5")){
				
			}else if(op.equals("6")){
				
			}else if(op.equals("7")){
				
			}else if(op.equals("0")){
				System.exit(0);
			}else{
				JOptionPane.showMessageDialog(null, "Opção incorreta", "Locadora", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
