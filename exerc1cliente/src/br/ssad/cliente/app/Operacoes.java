package br.ssad.cliente.app;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JOptionPane;

import br.ssad.classes.Cliente;
import br.ssad.classes.Diretor;
import br.ssad.classes.Emprestimo;
import br.ssad.classes.Exemplar;
import br.ssad.classes.Filme;
import br.ssad.modelo.Locadora;

public class Operacoes {
	
	private InitialContext contexto;
	private Locadora locadora;
	
	public Operacoes() {
		try{
			this.contexto = new InitialContext();
			this.locadora = (Locadora)contexto.lookup("LocadoraBean/remote");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public boolean criarCliente(){
		try{
			Cliente cliente = new Cliente();
			cliente.setNome(JOptionPane.showInputDialog("Forneça o Nome do cliente:"));
			cliente.setEndereco(JOptionPane.showInputDialog("Forneça o endereço do cliente:"));
			cliente.setDataNascimento(JOptionPane.showInputDialog("Forneça a data de nascimento do cliente:"));
			cliente.setEmail(JOptionPane.showInputDialog("Forneça o e-mail do cliente:"));
			boolean erro = this.locadora.cadastrarCliente(cliente);
			if(erro)
				JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!", "Locadora", JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, "Erro no banco de dados", "Locadora", JOptionPane.ERROR_MESSAGE);
			return true;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Você forneceu algum valor incorreto!", "Locadora", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	public boolean criarDiretor(){
		try{
			Diretor diretor = new Diretor();
			diretor.setNome(JOptionPane.showInputDialog("Forneça o Nome do diretor:"));
			boolean erro = this.locadora.cadastrarDiretor(diretor);
			if(erro)
				JOptionPane.showMessageDialog(null, "Diretor cadastrado com sucesso!", "Locadora", JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, "Erro no banco de dados", "Locadora", JOptionPane.ERROR_MESSAGE);
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
			String nomeDiretor = JOptionPane.showInputDialog("Forneça o nome do diretor:");
			filme.setValorLocacao(Double.parseDouble(JOptionPane.showInputDialog("Forneça o valor de locação do filme:")));
			boolean erro = this.locadora.cadastrarFilme(filme, nomeDiretor);
			if(erro)
				JOptionPane.showMessageDialog(null, "Filme cadastrado com sucesso!", "Locadora", JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, "Erro no banco de dados", "Locadora", JOptionPane.ERROR_MESSAGE);
			return true;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Você forneceu algum valor incorreto!", "Locadora", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	public boolean criarExemplar(){
		try{
			Exemplar exemplar = new Exemplar();
			String nomeFilme = JOptionPane.showInputDialog("Forneça o nome do filme:");
			exemplar.setDataAquisicao(JOptionPane.showInputDialog("Forneça a data de aquisição do Exemplar:"));
			exemplar.setEmprestado("n");
			boolean erro = this.locadora.cadastrarExemplar(exemplar, nomeFilme);
			if(erro)
				JOptionPane.showMessageDialog(null, "Exemplar cadastrado com sucesso!", "Locadora", JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, "Erro no banco de dados", "Locadora", JOptionPane.ERROR_MESSAGE);
			return true;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Você forneceu algum valor incorreto!", "Locadora", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	public boolean criarEmprestimo(){
		try{
			Emprestimo emprestimo = new Emprestimo();
			String nomeCliente = JOptionPane.showInputDialog("Forneça o nome do cliente:");
			String nomeFilme = JOptionPane.showInputDialog("Forneça o nome do filme:");
			emprestimo.setDataEmprestimo(JOptionPane.showInputDialog("Forneça a data do Empréstimo:"));
			emprestimo.setEnderecoEntrega(JOptionPane.showInputDialog("Forneça o endereço de entrega:"));
			String dataEntrega = JOptionPane.showInputDialog("Forneça a data de entrega:");
			emprestimo.setValorTotal(Double.parseDouble(JOptionPane.showInputDialog("Forneça o valor total do Empréstimo:")));
			boolean erro = this.locadora.cadastrarLocacao(emprestimo, nomeCliente, nomeFilme, dataEntrega);
			if(erro)
				JOptionPane.showMessageDialog(null, "Empréstimo cadastrado com sucesso!", "Locadora", JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, "Erro no banco de dados", "Locadora", JOptionPane.ERROR_MESSAGE);
			return true;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Você forneceu algum valor incorreto!", "Locadora", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	public boolean encerrarEmprestimo(){
		try{
			String nomeCliente = JOptionPane.showInputDialog("Forneça o nome do cliente:");
			String nomeFilme = JOptionPane.showInputDialog("Forneça o nome do filme:");
			String dataEntrega = JOptionPane.showInputDialog("Forneça a data de entrega:");
			boolean erro = this.locadora.encerrarLocacao(nomeCliente, nomeFilme, dataEntrega);
			if(erro)
				JOptionPane.showMessageDialog(null, "Empréstimo encerrado com sucesso!", "Locadora", JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, "Erro no banco de dados", "Locadora", JOptionPane.ERROR_MESSAGE);
			return true;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Você forneceu algum valor incorreto!", "Locadora", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	public boolean excluirDiretor(){
		try{
			String nome = JOptionPane.showInputDialog("Forneça o Nome do diretor:");
			boolean erro = this.locadora.excluirDiretor(nome);
			if(erro)
				JOptionPane.showMessageDialog(null, "Diretor removido com sucesso!", "Locadora", JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, "Erro no banco de dados", "Locadora", JOptionPane.ERROR_MESSAGE);
			return true;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Você forneceu algum valor incorreto!", "Locadora", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}	
}