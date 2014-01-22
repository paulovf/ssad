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
public class Emprestimo implements Serializable {

	private static final long serialVersionUID = 8652947886203982192L;
	private int id;
	private String dataEmprestimo;
	private String enderecoEntrega;
	private double valorTotal;
	private Cliente cliente;
	private List<ItemEmprestimo> itemEmprestimos = new ArrayList<ItemEmprestimo>();
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(String dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public String getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(String enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	@ManyToOne(cascade={CascadeType.ALL})
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@OneToMany(mappedBy="emprestimo",cascade={CascadeType.ALL})
	public List<ItemEmprestimo> getItemEmprestimos() {
		return itemEmprestimos;
	}

	public void setItemEmprestimos(List<ItemEmprestimo> itemEmprestimos) {
		this.itemEmprestimos = itemEmprestimos;
	}

	@Override
	public String toString() {
		return "Emprestimo [id=" + id + ", dataEmprestimo=" + dataEmprestimo
				+ ", enderecoEntrega=" + enderecoEntrega + ", valorTotal="
				+ valorTotal + ", cliente=" + cliente + ", itemEmprestimos="
				+ itemEmprestimos + "]";
	}
}