package br.ssad.modelo;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ssad.classes.Cliente;
import br.ssad.classes.Diretor;
import br.ssad.classes.Exemplar;
import br.ssad.classes.Filme;

@Stateful
@Remote(Locadora.class)
public class LocadoraBean implements Locadora {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public void cadastrarFilme(Filme filme) {
		// TODO Auto-generated method stub
		manager.persist(filme);
	}

	@Override
	public void cadastrarExemplar(Exemplar exemplar) {
		// TODO Auto-generated method stub
		manager.persist(exemplar);
	}

	@Override
	public void cadastrarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		manager.persist(cliente);
	}

	@Override
	public void cadastrarDiretor(Diretor diretor) {
		// TODO Auto-generated method stub
		manager.persist(diretor);
	}

	@Override
	public Filme buscarFilmePorId(int idFilme) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Filme buscarFilmePorNome(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Filme> buscarFilmesPorDiretor(int idDiretor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Exemplar> buscarExemplaresFilme(int idFilme) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Diretor> buscarDiretoresPorFilme(int idDfilme) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Filme> buscarFilmesPorGeneroAno(String genero, int ano) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente buscarClientePorId(int idCliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente buscarClientePorNome(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void excluirDiretor(int idDiretor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void encerrarLocacao(int idEmprestimo) {
		// TODO Auto-generated method stub
		
	}
	
	public Diretor buscarDiretorPorID(int idDiretor){
        Query q = manager.createQuery("select d from Diretor d where d.id = :idDiretor");
        q.setParameter("idDiretor", idDiretor);
        try{
        	Diretor diretor = (Diretor) q.getSingleResult();
            return diretor;
        }catch(NoResultException e) {
            return null;
        }
	}
	
    public boolean associarDiretorFilme(int idDiretor, int idFilme) {
    	Filme filme = buscarFilmePorId(idFilme);
    	Diretor diretor = buscarDiretorPorID(idDiretor);
    	if (filme != null && diretor != null){
	    	filme.getDiretores().add(diretor);
	    	this.manager.merge(filme);
	    	this.manager.flush();
	    	return true;
    	}
    	return false;
    }
}