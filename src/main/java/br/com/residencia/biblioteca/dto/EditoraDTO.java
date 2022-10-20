package br.com.residencia.biblioteca.dto;

import java.util.List;

import javax.persistence.Column;

public class EditoraDTO {	
	
	private Integer codigoEditora;	
	private String nome;
	private List<LivrosDTO> listaLivrosDTO;
	
	
	
	public EditoraDTO() {
		
	}
		
	public EditoraDTO(Integer codigoEditora, String nome) {
		super();
		this.codigoEditora = codigoEditora;
		this.nome = nome;
	}

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
	
	//******************************

	@Column(name = "imagem_nome")
	private String imagemNome;
	
	@Column(name = "imagem_filename")
	private String imagemFileName;
	
	@Column(name = "imagem_url")
	
	private String imagemUrl;
	private String imagemNome;
	private String imagemFileName;
	private String imagemUrl;
}
