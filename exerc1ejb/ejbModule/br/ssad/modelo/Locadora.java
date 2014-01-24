package br.ssad.modelo;

import java.util.List;

import javax.ejb.Remote;

import br.ssad.classes.Cliente;
import br.ssad.classes.Diretor;
import br.ssad.classes.Emprestimo;
import br.ssad.classes.Exemplar;
import br.ssad.classes.Filme;

@Remote
public interface Locadora {
	
	boolean cadastrarFilme(Filme filme, int idDiretor);
	boolean cadastrarExemplar(Exemplar exemplar, int idFilme);
	boolean cadastrarCliente(Cliente cliente);
	boolean cadastrarDiretor(Diretor diretor);
	boolean cadastrarLocacao(Emprestimo emprestimo, int idCliente, int idFilme);
	
	Filme buscarFilmePorId(int idFilme);
	Filme buscarFilmePorNome(String nome);
	List<Filme> buscarFilmesPorDiretor(int idDiretor);
	List<Exemplar> buscarExemplaresFilme(int idFilme);
	List<Diretor> buscarDiretoresPorFilme(int idDfilme);
	List<Filme> buscarFilmesPorGeneroAno(String genero, int ano);
	Cliente buscarClientePorId(int idCliente);
	Cliente buscarClientePorNome(String nome);
	
	void excluirDiretor(int idDiretor);
	void encerrarLocacao(int idEmprestimo);
}
