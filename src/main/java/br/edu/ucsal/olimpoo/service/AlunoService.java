package br.edu.ucsal.olimpoo.service;

import br.edu.ucsal.olimpoo.domain.Aluno;
import br.edu.ucsal.olimpoo.repository.AlunoRepository;
import br.edu.ucsal.olimpoo.service.dto.AlunoDTO;

import java.util.ArrayList;
import java.util.List;

public class AlunoService {

	private AlunoRepository alunoRepository;

	public AlunoService(AlunoRepository alunoRepository) {
		this.alunoRepository = alunoRepository;
	}

	public AlunoDTO cadastrarAluno(String matricula, String nome, String email) {
		if (matricula.length() < 4) {
			throw new IllegalArgumentException("Número de matrícula inválido.");
		} else if (nome.length() < 3) {
			throw new IllegalArgumentException("Nome muito pequeno.");
		} else if (!email.contains("@")) {
			throw new IllegalArgumentException("Email inválido.");
		} else {
			Aluno aluno = this.alunoRepository.cadastrarAluno(matricula, nome, email);
			AlunoDTO alunoDTO = parseAluno(aluno);

			return alunoDTO;
		}
	}

	public List<AlunoDTO> listarAlunos() {
		List<Aluno> alunos = this.alunoRepository.listar();
		List<AlunoDTO> alunosDTO = new ArrayList<>();
		
		for (Aluno aluno : alunos) {
			AlunoDTO alunoDTO = parseAluno(aluno);
			alunosDTO.add(alunoDTO);
		}
		return alunosDTO;
	}

	public AlunoDTO buscarAluno(int id) {
		Aluno aluno = this.alunoRepository.buscarPorId(id);

		if (aluno == null) {
			throw new IllegalArgumentException("Aluno não encontrado.");
		}

		AlunoDTO alunoDTO = parseAluno(aluno);
		return alunoDTO;
	}

	private AlunoDTO parseAluno(Aluno aluno) {
		if (aluno == null) {
			return null;
		}
		AlunoDTO alunoDTO = new AlunoDTO(aluno.getId(), aluno.getMatricula(), aluno.getNome(), aluno.getEmail());

		return alunoDTO;
	}
}