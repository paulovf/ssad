package br.ssad.modelo;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ssad.classes.Diretor;
import br.ssad.classes.Filme;

@Stateful
@Remote(Locadora.class)
public class LocadoraBean implements Locadora {

	@PersistenceContext
	private EntityManager manager;
	@Override
	public void addDiretor(Diretor diretor) {
		// TODO Auto-generated method stub
		System.out.println(diretor.toString());
		manager.persist(diretor);
	}

	@Override
	public void addFilme(Filme filme) {
		// TODO Auto-generated method stub
		manager.persist(filme);
	}

}
