package br.ssad.cliente.app;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.ssad.modelo.Locadora;

public class Operacoes {
	
	private InitialContext contexto;
	private Locadora locadora;
	private int operacao = -1;
	
	public Operacoes() {
		try{
			this.contexto = new InitialContext();
			this.locadora = (Locadora)contexto.lookup("LocadoraBean/remote");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void iniciarAplicacao(){
		Menu menu = new Menu(locadora);
		while(this.operacao != 0){
			this.operacao = menu.criaMenu();
			boolean erro = false;
			switch(this.operacao){
			case 1:
				
				break;
			case 2:
				while(!erro)
					erro = menu.criarDiretor();
				break;
			case 3:
				while(!erro)
				erro = menu.criarFilme();
				break;
			case 4:
				while(!erro)
					erro = menu.criarExemplar();
				break;
			case 5:
				
				break;
			case 6:
				
				break;
			case 7:
				
				break;
			default:
				break;
			}
		}
	}
}