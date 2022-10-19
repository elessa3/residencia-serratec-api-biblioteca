package br.com.residencia.biblioteca.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.biblioteca.dto.AlunosDTO;
import br.com.residencia.biblioteca.dto.EmprestimoDTO;
import br.com.residencia.biblioteca.entity.Alunos;
import br.com.residencia.biblioteca.entity.Emprestimo;
import br.com.residencia.biblioteca.repository.AlunosRepository;
import br.com.residencia.biblioteca.repository.EmprestimoRepository;

@Service
public class AlunosService {
	
	@Autowired
	AlunosRepository alunosRepository;
	
	@Autowired
	EmprestimoRepository emprestimoRepository;
	
	@Autowired
	EmprestimoService emprestimoService;
	
	public List<Alunos> getAllAlunos() {
		return alunosRepository.findAll();		
	}
	
	public List<AlunosDTO> getAllAlunosDTO() {
		List<Alunos> listaAlunos = alunosRepository.findAll();
		List<AlunosDTO> listaAlunosDTO = new ArrayList<>();
		
		for(Alunos alunos : listaAlunos) {
			AlunosDTO alunosDTO = toDTO(alunos);					
			listaAlunosDTO.add(alunosDTO);
		}	
		return listaAlunosDTO;
	}
	
	public Alunos getAlunosById(Integer id) {		
		return alunosRepository.findById(id).orElse(null);	
	}
	
	public Alunos saveAluno(Alunos alunos) {
		return alunosRepository.save(alunos);		
	}
	
	public AlunosDTO saveAlunosDTO(AlunosDTO alunosDTO) {
		Alunos alunos = toEntidade(alunosDTO);			
		Alunos novoAlunos = alunosRepository.save(alunos);
		
		AlunosDTO AlunosAtualizadaDTO = toDTO(novoAlunos); 
		return AlunosAtualizadaDTO;				
	}
	
	public Alunos updateAlunos(Alunos alunos, Integer id) {
		//Aluno alunoExistenteNoBanco = alunosRepository.findById(id).get(); - outro metodo
		Alunos alunoExistenteNoBanco = getAlunosById(id);
		
		alunoExistenteNoBanco.setBairro(alunos.getBairro());
		alunoExistenteNoBanco.setCidade(alunos.getCidade());
		alunoExistenteNoBanco.setComplemento(alunos.getComplemento());
		alunoExistenteNoBanco.setCpf(alunos.getCpf());
		alunoExistenteNoBanco.setDataNascimento(alunos.getDataNascimento());
		alunoExistenteNoBanco.setLogradouro(alunos.getLogradouro());
		alunoExistenteNoBanco.setNome(alunos.getNome());
		alunoExistenteNoBanco.setNumeroLogradouro(alunos.getNumeroLogradouro());
				
		return alunosRepository.save(alunoExistenteNoBanco);		
	}
	
public AlunosDTO updateAlunosDTO(AlunosDTO alunosDTO, Integer id) {
		
		Alunos AlunosExistenteNoBanco = getAlunosById(id);
		AlunosDTO AlunosAtualizadoDTO = new AlunosDTO();
		
		if(AlunosExistenteNoBanco != null) {
			
			AlunosExistenteNoBanco = toEntidade(alunosDTO);			
			Alunos alunosAtualizado = alunosRepository.save(AlunosExistenteNoBanco);			
			AlunosAtualizadoDTO = toDTO(alunosAtualizado);					
		}
		return AlunosAtualizadoDTO;				
	}
	
	public Alunos toEntidade(AlunosDTO alunosDTO) {
		Alunos alunos = new Alunos();
		
		alunos.setBairro(alunosDTO.getBairro());		
		alunos.setCidade(alunosDTO.getCidade());
		alunos.setComplemento(alunosDTO.getComplemento());
		alunos.setCpf(alunosDTO.getCpf());
		alunos.setDataNascimento(alunosDTO.getDataNascimento());
		alunos.setLogradouro(alunosDTO.getLogradouro());
		alunos.setNumeroLogradouro(alunosDTO.getNumeroLogradouro());
		alunos.setNome(alunosDTO.getNome());
		
		return alunos;
	}
	
	public AlunosDTO toDTO(Alunos alunos) {
		AlunosDTO alunosDTO = new AlunosDTO();
		
		alunosDTO.setBairro(alunos.getBairro());		
		alunosDTO.setCidade(alunos.getCidade());
		alunosDTO.setComplemento(alunos.getComplemento());
		alunosDTO.setCpf(alunos.getCpf());
		alunosDTO.setDataNascimento(alunos.getDataNascimento());
		alunosDTO.setLogradouro(alunos.getLogradouro());
		alunosDTO.setNumeroLogradouro(alunos.getNumeroLogradouro());
		alunosDTO.setNome(alunos.getNome());		
		
		return alunosDTO;
	}
	
	public Alunos deleteAluno(Integer id) {
		alunosRepository.deleteById(id);
		return getAlunosById(id);	
	}
	
	//**************************
	
	/*public List<AlunosDTO> getAllAlunosEmprestimos() {
			
			List<Alunos> listaAlunos = alunosRepository.findAll();
			List<AlunosDTO> listaAlunosDTO = new ArrayList<>();		
		
			//Alunos alunos = new Alunos();
			AlunosDTO alunosDTO = new AlunosDTO();
			
			for(Alunos alunos : listaAlunos) {			
								
				alunos.setNumeroMatriculaAluno(alunosDTO.getNumeroMatriculaAluno());
				alunos.setNome(alunosDTO.getNome());
				alunos.setCpf(alunosDTO.getCpf());
								
				List<Emprestimo> listaEmprestimo = new ArrayList<>();
				List<EmprestimoDTO> listaEmprestimoResumoDTO = new ArrayList<>();
						
				listaEmprestimo = emprestimoRepository.findById(id);
				
				for(Emprestimo Emprestimo : listaEmprestimo) {
					EmprestimoDTO emprestimoDTO = new EmprestimoDTO());					
					Emprestimo emprestimo = new Emprestimo();
					
					emprestimo.setCodigoEmprestimo(emprestimoDTO.getCodigoEmprestimo());
					emprestimo.setDataEmprestimo(emprestimoDTO.getDataEmprestimo());
					emprestimo.setDataEntrega(emprestimoDTO.getDataEntrega());
					
					//listaEmprestimoResumoDTO = emprestimoService(emprestimo);
					listaEmprestimoResumoDTO.add(emprestimoDTO);
				}			
				
				alunosDTO.setlistaEmprestimoResumoDTO(listaEmprestimoResumoDTO);	
				
				listaAlunosDTO.add(listaEmprestimo);
			}			
			return listaAlunosDTO;
		}*/

}
