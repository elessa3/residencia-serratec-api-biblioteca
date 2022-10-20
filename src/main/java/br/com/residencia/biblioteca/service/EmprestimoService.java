package br.com.residencia.biblioteca.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.biblioteca.dto.EmprestimoDTO;
import br.com.residencia.biblioteca.dto.EmprestimosResumoDTO;
import br.com.residencia.biblioteca.entity.Emprestimo;
import br.com.residencia.biblioteca.repository.EmprestimoRepository;

@Service
public class EmprestimoService {
	
	@Autowired
	EmprestimoRepository emprestimoRepository;
	
	public List<Emprestimo> getAllEmprestimo() {
		return emprestimoRepository.findAll();
	}
	
	public List<EmprestimoDTO> getAllEmprestimoDTO() {
		List<Emprestimo> listaEmprestimo = emprestimoRepository.findAll();
		List<EmprestimoDTO> listaEmprestimoDTO = new ArrayList<>();
		
		for(Emprestimo emprestimo : listaEmprestimo) {
			EmprestimoDTO emprestimoDTO = toDTO(emprestimo);					
			listaEmprestimoDTO.add(emprestimoDTO);
		}	
		return listaEmprestimoDTO;
	}
	
	public Emprestimo getEmprestimoById(Integer id) {
		return emprestimoRepository.findById(id).get();
	}
	
	public Emprestimo saveEmprestimo(Emprestimo emprestimo) {
		return emprestimoRepository.save(emprestimo);
	}
	
	public EmprestimoDTO saveEmprestimoDTO(EmprestimoDTO emprestimoDTO) {
		Emprestimo emprestimo = toEntidade(emprestimoDTO);			
		Emprestimo novoEmprestimo = emprestimoRepository.save(emprestimo);
		
		EmprestimoDTO emprestimoAtualizadaDTO = toDTO(novoEmprestimo); 
		return emprestimoAtualizadaDTO;				
	}
	
	public Emprestimo updateEmprestimo(Emprestimo emprestimo, Integer id) {
		
		Emprestimo emprestimoExistenteNoBanco = getEmprestimoById(id);
		
		
		emprestimoExistenteNoBanco.setDataEmprestimo(emprestimo.getDataEmprestimo());
		emprestimoExistenteNoBanco.setDataEntrega(emprestimo.getDataEntrega());
		emprestimoExistenteNoBanco.setValorEmprestimo(emprestimo.getValorEmprestimo());
		
		return emprestimoRepository.save(emprestimoExistenteNoBanco);
	}
	
public EmprestimoDTO updateEmprestimoDTO(EmprestimoDTO EmprestimoDTO, Integer id) {
		
		Emprestimo emprestimoExistenteNoBanco = getEmprestimoById(id);
		EmprestimoDTO EmprestimoAtualizadoDTO = new EmprestimoDTO();
		
		if(emprestimoExistenteNoBanco != null) {
			
			emprestimoExistenteNoBanco = toEntidade(EmprestimoDTO);			
			Emprestimo emprestimoAtualizado = emprestimoRepository.save(emprestimoExistenteNoBanco);			
			EmprestimoAtualizadoDTO = toDTO(emprestimoAtualizado);					
		}
		return EmprestimoAtualizadoDTO;				
	}
	
	private Emprestimo toEntidade(EmprestimoDTO emprestimoDTO) {
		Emprestimo emprestimo = new Emprestimo();
		
		emprestimo.setDataEmprestimo(emprestimoDTO.getDataEmprestimo());
		emprestimo.setDataEntrega(emprestimoDTO.getDataEntrega());
		emprestimo.setValorEmprestimo(emprestimoDTO.getValorEmprestimo());
		
		return emprestimo;
	}
	
	private EmprestimoDTO toDTO(Emprestimo emprestimo) {
		EmprestimoDTO emprestimoDTO = new EmprestimoDTO();
		
		emprestimoDTO.setCodigoEmprestimo(emprestimo.getCodigoEmprestimo());
		emprestimoDTO.setDataEmprestimo(emprestimo.getDataEmprestimo());
		emprestimoDTO.setDataEntrega(emprestimo.getDataEntrega());
		emprestimoDTO.setValorEmprestimo(emprestimo.getValorEmprestimo());
		
		return emprestimoDTO;
	}
	
	public Emprestimo deletarEmprestimo(Integer id) {
		emprestimoRepository.deleteById(id);
		return deletarEmprestimo(id);
	}	
	
	public EmprestimosResumoDTO toEmprestimosResumoDTO(Emprestimo emprestimo) {
		EmprestimosResumoDTO emprestimosResumoDTO = new EmprestimosResumoDTO();
		
		emprestimosResumoDTO.setCodigoEmprestimo(emprestimo.getCodigoEmprestimo());
		emprestimosResumoDTO.setDataEmprestimo(emprestimo.getDataEmprestimo());
		emprestimosResumoDTO.setDataEntrega(emprestimo.getDataEntrega());
				
		return emprestimosResumoDTO;
	}

}
