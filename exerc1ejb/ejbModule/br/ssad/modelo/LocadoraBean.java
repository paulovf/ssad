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
	public boolean cadastrarFilme(Filme filme, int idDiretor) {
		// TODO Auto-generated method stub
		try{
			manager.persist(filme);
			associarDiretorFilme(idDiretor, filme);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean cadastrarExemplar(Exemplar exemplar, int idFilme) {
		// TODO Auto-generated method stub
		try{
			Filme filme = manager.find(Filme.class, idFilme);
	        List<Exemplar> exemplares = filme.getExemplares();
	        exemplar.setFilme(filme);
	        manager.persist(exemplar);
	        exemplares.add(exemplar);
	        manager.merge(filme);
	        manager.flush();
	        return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean cadastrarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		manager.persist(cliente);
		return true;
	}

	@Override
	public boolean cadastrarDiretor(Diretor diretor) {
		// TODO Auto-generated method stub
		try{
			manager.persist(diretor);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
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
	
    public boolean associarDiretorFilme(int idDiretor, Filme filme) {
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