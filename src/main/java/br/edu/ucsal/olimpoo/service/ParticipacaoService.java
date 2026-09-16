package br.edu.ucsal.olimpoo.service;

import java.util.ArrayList;
import java.util.List;

import br.edu.ucsal.olimpoo.domain.Aluno;
import br.edu.ucsal.olimpoo.domain.Olimpiada;
import br.edu.ucsal.olimpoo.domain.Participacao;
import br.edu.ucsal.olimpoo.repository.AlunoRepository;
import br.edu.ucsal.olimpoo.repository.OlimpiadaRepository;
import br.edu.ucsal.olimpoo.repository.ParticipacaoRepository;
import br.edu.ucsal.olimpoo.service.dto.AlunoDTO;
import br.edu.ucsal.olimpoo.service.dto.OlimpiadaDTO;
import br.edu.ucsal.olimpoo.service.dto.ParticipacaoDTO;

public class ParticipacaoService {
	private ParticipacaoRepository participacaoRepository;
	private AlunoRepository alunoRepository;
	private OlimpiadaRepository olimpiadaRepository;

	public ParticipacaoService(ParticipacaoRepository participacaoRepository, AlunoRepository alunoRepository,
			OlimpiadaRepository olimpiadaRepository) {
		this.participacaoRepository = participacaoRepository;
		this.alunoRepository = alunoRepository;
		this.olimpiadaRepository = olimpiadaRepository;
	}

	public ParticipacaoDTO registrarParticipacao(AlunoDTO aluno, OlimpiadaDTO olimpiada) {
		if (aluno == null || olimpiada == null) {
			throw new IllegalArgumentException("Aluno ou olimpiada não encontrado.");
		}

		for (Participacao participacao : this.participacaoRepository.listarParticipacoes()) {
			if (participacao.getAluno().getId() == aluno.getId()
					&& participacao.getOlimpiada().getId() == olimpiada.getId()) {
				throw new IllegalArgumentException("Participação já registrada.");
			}
		}

		Aluno alunoEntidade = this.alunoRepository.buscarPorId(aluno.getId());

		Olimpiada olimpiadaEntidade = this.olimpiadaRepository.buscarOlimpiadaPorId(olimpiada.getId());

		Participacao participacao = this.participacaoRepository.registrarParticipacao(alunoEntidade, olimpiadaEntidade,
				"INSCRITO");
		ParticipacaoDTO participacaoDTO = parseParticipacao(participacao);
		return participacaoDTO;
	}

	public List<ParticipacaoDTO> listarParticipacoes() {
		List<Participacao> participacoes = this.participacaoRepository.listarParticipacoes();
		List<ParticipacaoDTO> participacoesDTO = new ArrayList<>();

		for (Participacao participacao : participacoes) {
			ParticipacaoDTO participacaoDTO = parseParticipacao(participacao);
			participacoesDTO.add(participacaoDTO);
		}

		return participacoesDTO;
	}

	public ParticipacaoDTO buscarParticipacao(int id) {
		Participacao participacao = this.participacaoRepository.buscarParticipacao(id);

		if (participacao == null) {
			return null;
		}

		ParticipacaoDTO participacaoDTO = parseParticipacao(participacao);

		return participacaoDTO;
	}

	private ParticipacaoDTO parseParticipacao(Participacao participacao) {
		if (participacao == null) {
			return null;
		}

		AlunoDTO alunoDTO = new AlunoDTO(participacao.getAluno().getId(), participacao.getAluno().getMatricula(),
				participacao.getAluno().getNome(), participacao.getAluno().getEmail());

		OlimpiadaDTO olimpiadaDTO = new OlimpiadaDTO(participacao.getOlimpiada().getId(),
				participacao.getOlimpiada().getNome(), participacao.getOlimpiada().getEdicao());

		return new ParticipacaoDTO(participacao.getId(), alunoDTO, olimpiadaDTO, participacao.getSituacao());
	}

}
