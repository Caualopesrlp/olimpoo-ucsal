package br.edu.ucsal.olimpoo.repository;

import java.util.List;

import br.edu.ucsal.olimpoo.domain.Aluno;

public interface AlunoRepository {
	Aluno cadastrarAluno(String matricula, String nome, String email);
	Aluno buscarPorId(int id);
	List<Aluno> listar();
	
}
