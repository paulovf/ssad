package br.ssad.modelo;

import javax.ejb.Remote;

import br.ssad.classes.Diretor;
import br.ssad.classes.Filme;

@Remote
public interface Locadora {
	void addDiretor(Diretor diretor);
	void addFilme(Filme filme);
}
