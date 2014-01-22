package br.ssad.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Filme implements Serializable {

	private static final long serialVersionUID = 5207494699357115399L;
	private int id;
	private String nome;
	private String genero;
	private int anoLancamento;
	private double duracao;
	private double valorLocacao;
	private List<Diretor> diretores = new ArrayList<Diretor>();
	private List<Exemplar> exemplares = new ArrayList<Exemplar>();
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getGenero() {
		return genero;
	}
	
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public int getAnoLancamento() {
		return anoLancamento;
	}
	
	public void setAnoLancamento(int anoLancamento) {
		this.anoLancamento = anoLancamento;
	}
	
	public double getDuracao() {
		return duracao;
	}

	public void setDuracao(double duracao) {
		this.duracao = duracao;
	}

	public double getValorLocacao() {
		return valorLocacao;
	}

	public void setValorLocacao(double valorLocacao) {
		this.valorLocacao = valorLocacao;
	}
	
	@ManyToMany(cascade={CascadeType.ALL})
	public List<Diretor> getDiretores() {
		return diretores;
	}

	public void setDiretores(List<Diretor> diretores) {
		this.diretores = diretores;
	}

	@OneToMany(mappedBy="filme",cascade={CascadeType.ALL})
	public List<Exemplar> getExemplares() {
		return exemplares;
	}

	public void setExemplares(List<Exemplar> exemplares) {
		this.exemplares = exemplares;
	}

	@Override
	public String toString() {
		return "Filme [id=" + id + ", nome=" + nome + ", genero=" + genero
				+ ", anoLancamento=" + anoLancamento + ", duracao=" + duracao
				+ ", valorLocacao=" + valorLocacao + ", diretores=" + diretores
				+ ", exemplares=" + exemplares + "]";
	}
}