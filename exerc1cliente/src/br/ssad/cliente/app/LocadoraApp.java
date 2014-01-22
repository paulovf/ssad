package br.ssad.cliente.app;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.ssad.classes.Diretor;
import br.ssad.classes.Filme;
import br.ssad.modelo.Locadora;

public class LocadoraApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			InitialContext contexto = new InitialContext();
			Locadora locadora = (Locadora)contexto.lookup("LocadoraBean/remote");
			Diretor diretor = new Diretor();
			Filme filme = new Filme();
			filme.setAnoLancamento(2010);
			filme.setDuracao(120);
			filme.setGenero("ação");
			filme.setNome("filme 2");
			filme.setValorLocacao(2.50);
			filme.getDiretores().add(new Diretor());
			diretor.setNome("ze2");
			diretor.getFilmes().add(filme);
			locadora.addDiretor(diretor);
			filme.getDiretores().add(diretor);
			locadora.addFilme(filme);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}