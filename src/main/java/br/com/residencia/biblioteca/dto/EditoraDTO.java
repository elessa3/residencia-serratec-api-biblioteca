package br.com.residencia.biblioteca.dto;

import java.util.Set;

public class EditoraDTO {	
	
	private Integer codigoEditora;	
	private String nome;
	private Set<LivrosDTO> listaLivrosDTO;
	
		
	public Integer getCodigoEditora() {
		return codigoEditora;
	}

	public void setCodigoEditora(Integer codigoEditora) {
		this.codigoEditora = codigoEditora;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<LivrosDTO> getListaLivrosDTO() {
		return listaLivrosDTO;
	}

	public void setListaLivrosDTO(Set<LivrosDTO> listaLivrosDTO) {
		this.listaLivrosDTO = listaLivrosDTO;
	}
	
	
	

}
