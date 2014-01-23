package br.ssad.modelo;

import java.util.List;

import javax.ejb.Remote;

import br.ssad.classes.Cliente;
import br.ssad.classes.Diretor;
import br.ssad.classes.Exemplar;
import br.ssad.classes.Filme;

@Remote
public interface Locadora {
	
	void cadastrarFilme(Filme filme);
	void cadastrarExemplar(Exemplar exemplar);
	void cadastrarCliente(Cliente cliente);
	void cadastrarDiretor(Diretor diretor);
	
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
