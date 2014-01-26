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
	
	boolean cadastrarFilme(Filme filme, String nomeDiretor);
	boolean cadastrarExemplar(Exemplar exemplar, String nomeFilme);
	boolean cadastrarCliente(Cliente cliente);
	boolean cadastrarDiretor(Diretor diretor);
	boolean cadastrarLocacao(Emprestimo emprestimo, String nomeCliente, String nomeFilme, String dataEntrega);
	
	Filme buscarFilmePorId(int idFilme);
	Filme buscarFilmePorNome(String nome);
	List<Filme> buscarFilmesPorDiretor(String nome);
	List<Exemplar> buscarExemplaresFilme(int idFilme);
	List<Diretor> buscarDiretoresPorFilme(String nome);
	List<Filme> buscarFilmesPorGeneroAno(String genero, int ano);
	Cliente buscarClientePorId(int idCliente);
	Cliente buscarClientePorNome(String nome);
	
	boolean excluirDiretor(String nomeDiretor);
	boolean encerrarLocacao(String nomeCliente, String nomeFilme, String dataEntrega);
}
