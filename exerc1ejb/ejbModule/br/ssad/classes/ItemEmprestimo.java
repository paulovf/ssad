package br.ssad.classes;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ItemEmprestimo implements Serializable {

	private static final long serialVersionUID = -5894737440775170378L;
	private int id;
	private double valorEmprestimo;
	private double dataDevolucao;
	private Emprestimo emprestimo;
	private Exemplar exemplar;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public double getValorEmprestimo() {
		return valorEmprestimo;
	}
	
	public void setValorEmprestimo(double valorEmprestimo) {
		this.valorEmprestimo = valorEmprestimo;
	}

	public double getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(double dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	@ManyToOne(cascade={CascadeType.ALL})
	public Emprestimo getEmprestimo() {
		return emprestimo;
	}

	public void setEmprestimo(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
	}

	@ManyToOne(cascade={CascadeType.ALL})
	public Exemplar getExemplar() {
		return exemplar;
	}

	public void setExemplar(Exemplar exemplar) {
		this.exemplar = exemplar;
	}

	@Override
	public String toString() {
		return "ItemEmprestimo [id=" + id + ", valorEmprestimo="
				+ valorEmprestimo + ", dataDevolucao=" + dataDevolucao
				+ ", emprestimo=" + emprestimo + ", exemplar=" + exemplar + "]";
	}
}