package br.ssad.cliente.app;



public class LocadoraApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Menu().criaMenu();
		/*try{
			InitialContext contexto = new InitialContext();
			Locadora locadora = (Locadora)contexto.lookup("LocadoraBean/remote");
			Filme filme = new Filme();
			filme.setNome("teste");
			filme.setValorLocacao(8);
			filme.setGenero("drama");
			filme.setDuracao(120);
			filme.setAnoLancamento(1699);
			locadora.cadastrarFilme(filme, "bene");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}