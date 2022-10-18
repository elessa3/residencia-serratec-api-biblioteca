package br.com.residencia.biblioteca.dto;

import java.math.BigDecimal;
import java.time.Instant;

public class EmprestimoDTO {
	
	private Integer codigoEmprestimo;	
	private Instant dataEntrega;
	private Instant dataEmprestimo;
	private BigDecimal valorEmprestimo;
	
	
	public EmprestimoDTO() {		
	}
	
	public EmprestimoDTO(Integer codigoEmprestimo,Instant dataEntrega, Instant dataEmprestimo, BigDecimal valorEmprestimo) {
		
		this.codigoEmprestimo = codigoEmprestimo;
		this.dataEntrega = dataEntrega;
		this.dataEmprestimo = dataEmprestimo;
		this.valorEmprestimo = valorEmprestimo;
	}

	public Integer getCodigoEmprestimo() {
		return codigoEmprestimo;
	}

	public void setCodigoEmprestimo(Integer codigoEmprestimo) {
		this.codigoEmprestimo = codigoEmprestimo;
	}

	public Instant getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Instant dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Instant getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(Instant dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public BigDecimal getValorEmprestimo() {
		return valorEmprestimo;
	}

	public void setValorEmprestimo(BigDecimal valorEmprestimo) {
		this.valorEmprestimo = valorEmprestimo;
	}

	
}
