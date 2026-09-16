package br.edu.ucsal.olimpoo.repository;

import java.util.ArrayList;
import java.util.List;

import br.edu.ucsal.olimpoo.domain.Aluno;
import br.edu.ucsal.olimpoo.domain.Olimpiada;
import br.edu.ucsal.olimpoo.domain.Participacao;

public class ParticipacaoRepositoryArrayList implements ParticipacaoRepository {
	private static final ArrayList<Participacao> PARTICIPACOES = new ArrayList<>();
	private static int proximaParticipacaoId = 1;
	
	public Participacao registrarParticipacao(Aluno aluno, Olimpiada olimpiada, String situacao) {
		Participacao participacao = new Participacao (proximaParticipacaoId, aluno, olimpiada, situacao);
		PARTICIPACOES.add(participacao);
		proximaParticipacaoId++;
		return participacao;
	}
	
	public List<Participacao> listarParticipacoes(){
		return List.copyOf(PARTICIPACOES);
		
	}

	public Participacao buscarParticipacao(int id) {
		for (Participacao participacao : PARTICIPACOES) {
			if(participacao.getId() == id) {
				return participacao;
			}
		}
		
		return null;
		
	}

	
}
