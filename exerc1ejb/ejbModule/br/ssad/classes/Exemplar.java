package br.ssad.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Exemplar implements Serializable {

	private static final long serialVersionUID = -4629891900001378550L;
	private int id;
	private String dataAquisicao;
	private String emprestado;
	private Filme filme;
	private List<ItemEmprestimo> itemEmprestimos = new ArrayList<ItemEmprestimo>();
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDataAquisicao() {
		return dataAquisicao;
	}
	
	public void setDataAquisicao(String dataAquisicao) {
		this.dataAquisicao = dataAquisicao;
	}
	
	public String getEmprestado() {
		return emprestado;
	}
	
	public void setEmprestado(String emprestado) {
		this.emprestado = emprestado;
	}

	@ManyToOne(cascade={CascadeType.ALL})
	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	@OneToMany(mappedBy="exemplar",cascade={CascadeType.ALL})
	public List<ItemEmprestimo> getItemEmprestimos() {
		return itemEmprestimos;
	}

	public void setItemEmprestimos(List<ItemEmprestimo> itemEmprestimos) {
		this.itemEmprestimos = itemEmprestimos;
	}

	@Override
	public String toString() {
		return "Exemplar [id=" + id + ", dataAquisicao=" + dataAquisicao
				+ ", emprestado=" + emprestado + ", filme=" + filme
				+ ", itemEmprestimos=" + itemEmprestimos + "]";
	}
}