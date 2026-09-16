package br.edu.ucsal.olimpoo.repository;

import java.util.ArrayList;
import java.util.List;

import br.edu.ucsal.olimpoo.domain.Aluno;


public class AlunoRepositoryArrayList implements AlunoRepository {

    private static final ArrayList<Aluno> ALUNOS = new ArrayList<>();
    private int proximoAlunoId = 1;

    public Aluno cadastrarAluno(String matricula, String nome, String email) {
    	Aluno aluno = new Aluno(proximoAlunoId, matricula, nome, email);
        ALUNOS.add(aluno);
        proximoAlunoId++;
        return aluno;
    }

    public Aluno buscarPorId(int id) {
        for (Aluno aluno : ALUNOS) {
            if (aluno.getId() == id) {
                return aluno;
            }
        }

        return null;
    }

    public List<Aluno> listar() {
        return List.copyOf(ALUNOS);
    }
}