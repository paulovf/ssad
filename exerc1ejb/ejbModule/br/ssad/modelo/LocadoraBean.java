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
import br.ssad.classes.Emprestimo;
import br.ssad.classes.Exemplar;
import br.ssad.classes.Filme;
import br.ssad.classes.ItemEmprestimo;

@Stateful
@Remote(Locadora.class)
public class LocadoraBean implements Locadora {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public boolean cadastrarFilme(Filme filme, String nomeDiretor) {
		try{
			String nomeFilme = filme.getNome();
			manager.persist(filme);
			manager.flush();
			if(associarDiretorFilme(buscarDiretorPorNome(nomeDiretor).getId(), nomeFilme)){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean cadastrarExemplar(Exemplar exemplar, String nomeFilme) {
		try{
			Filme filme = buscarFilmePorNome(nomeFilme);
	        exemplar.setFilme(filme);
	        manager.persist(exemplar);
	        manager.flush();
	        filme.getExemplares().add(exemplar);
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
		try{
			manager.persist(cliente);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean cadastrarDiretor(Diretor diretor) {
		try{
			manager.persist(diretor);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public boolean cadastrarLocacao(Emprestimo emprestimo, String nomeCliente, String nomeFilme, String dataEntrega) {
		try{
	        Cliente cliente = buscarClientePorNome(nomeCliente);
	        Filme filme = buscarFilmePorNome(nomeFilme);
	        cliente.getEmprestimos().add(emprestimo);
	        emprestimo.setCliente(cliente);
	        manager.persist(emprestimo);
	        manager.flush();
	        manager.merge(cliente);
			
			Query q = manager.createQuery("select e from Exemplar e");	        
	        List<Exemplar> exemplares = q.getResultList();
			ItemEmprestimo itemEmprestimo = new ItemEmprestimo();
			Exemplar exemplar = new Exemplar();
			for (Exemplar e : exemplares) {
				if(e.getFilme().getId() == filme.getId()){
					if (e.getEmprestado().equalsIgnoreCase("n")) {
						e.setEmprestado("s");
						exemplar = e;
						itemEmprestimo.setExemplar(e);
						break;
					}
				}
			}
			if (itemEmprestimo.getExemplar() == null)
				return false;
			itemEmprestimo.setEmprestimo(emprestimo);
			itemEmprestimo.setDataDevolucao(dataEntrega);
			itemEmprestimo.setValorEmprestimo(emprestimo.getValorTotal());
			manager.persist(itemEmprestimo);
			manager.flush();
			exemplar.getItemEmprestimos().add(itemEmprestimo);
			manager.merge(exemplar);
			manager.flush();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Filme buscarFilmePorId(int idFilme) {
		try{
			Query q = manager.createQuery("select f from Filme f join fetch f.diretores where f.id = :id");
	        q.setParameter("id", idFilme);
        	return (Filme)q.getSingleResult();
        }catch(Exception e){
        	e.printStackTrace();
            return null;
        }
	}

	@Override
	public Filme buscarFilmePorNome(String nome) {
		try{
			Query q = manager.createQuery("select f from Filme f where f.nome like :nome");
	        q.setParameter("nome", "%"+nome+"%");
        	return (Filme)q.getSingleResult();
        }catch(Exception e){
        	e.printStackTrace();
            return null;
        }
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Filme> buscarFilmesPorDiretor(String nome) {
		try{       
        	Query q = manager.createNamedQuery("buscarFilmesPorDiretor");
            q.setParameter("nome", nome);
            return q.getResultList();
        }catch(Exception e){
        	e.printStackTrace();
            return null;
        }
	}

	@Override
	public List<Exemplar> buscarExemplaresFilme(int idFilme) {
		Filme filme = manager.find(Filme.class, idFilme);
		List<Exemplar> exemplares = filme.getExemplares();
		return exemplares;
	}
	
	public List<Emprestimo> buscarEmprestimosCliente(int idCliente) {
		Cliente cliente = manager.find(Cliente.class, idCliente);
		List<Emprestimo> emprestimos = cliente.getEmprestimos();
		return emprestimos;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Diretor> buscarDiretoresPorFilme(String nome) {
		try{       
        	Query q = manager.createNamedQuery("buscarDiretoresPorFilme");
            q.setParameter("nome", nome);
            return q.getResultList();
        }catch(Exception e){
        	e.printStackTrace();
            return null;
        }
	}
	
	public Diretor buscarDiretorPorNome(String nomeDiretor) {
		try{
			Query q = manager.createQuery("select d from Diretor d where d.nome like :nome");
	        q.setParameter("nome", "%"+nomeDiretor+"%");
        	return (Diretor)q.getSingleResult();
        }catch(Exception e){
        	e.printStackTrace();
            return null;
        }
	}

	@Override
	public List<Filme> buscarFilmesPorGeneroAno(String genero, int ano) {
		 try{
			Query q = manager.createQuery("select f from Filme f where f.anoLancamento = :ano and f.genero LIKE :genero");
	        q.setParameter("ano", ano);
	        q.setParameter("genero", "%"+genero+"%");   
        	@SuppressWarnings("unchecked")
			List<Filme> filmes = q.getResultList();
        	return filmes;
        }catch(Exception e){
        	e.printStackTrace();
            return null;
        }
	}

	@Override
	public Cliente buscarClientePorId(int idCliente) {
		try{
			Query q = manager.createQuery("select c from Cliente c join fetch c.emprestimos where c.id = :id");
	        q.setParameter("id", idCliente);   
        	Cliente cliente = (Cliente) q.getSingleResult();
        	return cliente;
        }catch(Exception e){
        	e.printStackTrace();
            return null;
        }
	}

	@Override
	public Cliente buscarClientePorNome(String nome) {
		try{
			Query q = manager.createQuery("select c from Cliente c where c.nome like :nome");
	        q.setParameter("nome", "%"+nome+"%");  
        	Cliente cliente =  (Cliente) q.getSingleResult();
        	return cliente;
        }catch(Exception e){
        	e.printStackTrace();
            return null;
        }
	}

	@Override
	public boolean excluirDiretor(String nomeDiretor) {
		try{
			Diretor diretor = buscarDiretorPorNome(nomeDiretor);
			List<Filme> filmes = diretor.getFilmes();
			for(Filme f: filmes){
				f.getDiretores().remove(diretor);
			}
			diretor.getFilmes().clear();
			manager.remove(diretor);
	        return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean encerrarLocacao(String nomeCliente, String nomeFilme, String dataEntrega) {
		try{
			Cliente cliente = buscarClientePorNome(nomeCliente);
	        Filme filme = buscarFilmePorNome(nomeFilme);
	        List<Exemplar> exemplares = buscarExemplaresFilme(filme.getId());
	        List<Emprestimo> emprestimos = buscarEmprestimosCliente(cliente.getId());
			
	        Query q = manager.createQuery("select i from ItemEmprestimo i");
	        List<ItemEmprestimo> itemEmprestimos = q.getResultList();
	        Exemplar exemplar = null;
	        boolean sair = false;
	        for(Emprestimo e: emprestimos){
				for (ItemEmprestimo i : itemEmprestimos) {
					if(i.getEmprestimo().getId() == e.getId()){
						for(Exemplar ex: exemplares){
							if(ex.getId() == i.getExemplar().getId()){
								exemplar = ex;
								sair = true;
								break;
							}
						}
						if(sair)
							break;
					}
				}
				if(sair)
					break;
	        }
			if(exemplar == null)
				return false;
	        
			exemplar.setEmprestado("n");
			manager.merge(exemplar);
			manager.flush();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
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
	
	public boolean associarDiretorFilme(int idDiretor, String nomeFilme) {
    	try{
	    	Diretor diretor = buscarDiretorPorID(idDiretor);
	    	System.out.println(diretor.getNome() + "<<<1<<<<");
	    	Query q = manager.createQuery("select f from Filme f where f.nome like :nome");
	    	q.setParameter("nome", nomeFilme);
	    	Filme filme = (Filme)q.getSingleResult();

	    	if (diretor != null && filme != null){
		    	filme.getDiretores().add(diretor);
		    	this.manager.merge(filme);
		    	this.manager.flush();
		    	return true;
	    	}else{
	    		return false;
	    	}
    	}catch(Exception e){
    		e.printStackTrace();
    		return false;
    	}
    }
}