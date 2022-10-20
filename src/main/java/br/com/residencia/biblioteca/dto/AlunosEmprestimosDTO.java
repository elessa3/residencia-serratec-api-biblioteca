package br.com.residencia.biblioteca.dto;

import java.util.List;

public class AlunosEmprestimosDTO {

	private Integer numeroMatriculaAluno;
	private String nome;
	private String cpf;
	private List<EmprestimosResumoDTO> listaEmprestimosResumoDTO;
	
	
	public Integer getNumeroMatriculaAluno() {
		return numeroMatriculaAluno;
	}
	
	public void setNumeroMatriculaAluno(Integer numeroMatriculaAluno) {
		this.numeroMatriculaAluno = numeroMatriculaAluno;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<EmprestimosResumoDTO> getListaEmprestimosResumoDTO() {
		return listaEmprestimosResumoDTO;
	}

	public void setListaEmprestimosResumoDTO(List<EmprestimosResumoDTO> listaEmprestimosResumoDTO) {
		this.listaEmprestimosResumoDTO = listaEmprestimosResumoDTO;
	}
	
}
