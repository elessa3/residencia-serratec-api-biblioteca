package br.com.residencia.biblioteca.dto;

import java.util.List;

public class EditoraDTO {	
	
	private Integer codigoEditora;	
	private String nome;
	private List<LivrosDTO> listaLivrosDTO;
	
		
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

	public List<LivrosDTO> getListaLivrosDTO() {
		return listaLivrosDTO;
	}

	public void setListaLivrosDTO(List<LivrosDTO> listaLivrosDTO) {
		this.listaLivrosDTO = listaLivrosDTO;
	}
	
	
	

}
