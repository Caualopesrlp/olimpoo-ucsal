package br.edu.ucsal.olimpoo.repository;

import java.util.List;

import br.edu.ucsal.olimpoo.domain.Aluno;
import br.edu.ucsal.olimpoo.domain.Olimpiada;
import br.edu.ucsal.olimpoo.domain.Participacao;

public interface ParticipacaoRepository {
	Participacao registrarParticipacao(Aluno aluno, Olimpiada olimpiada, String situacao);
	List<Participacao> listarParticipacoes();
	Participacao buscarParticipacao(int id);
}
