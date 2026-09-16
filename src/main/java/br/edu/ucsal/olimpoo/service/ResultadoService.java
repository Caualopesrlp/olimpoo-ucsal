package br.edu.ucsal.olimpoo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.edu.ucsal.olimpoo.domain.Participacao;
import br.edu.ucsal.olimpoo.domain.ResultadoOficial;
import br.edu.ucsal.olimpoo.repository.ParticipacaoRepository;
import br.edu.ucsal.olimpoo.repository.ResultadoRepository;
import br.edu.ucsal.olimpoo.service.dto.AlunoDTO;
import br.edu.ucsal.olimpoo.service.dto.OlimpiadaDTO;
import br.edu.ucsal.olimpoo.service.dto.ParticipacaoDTO;
import br.edu.ucsal.olimpoo.service.dto.ResultadoDTO;

public class ResultadoService {
	private ResultadoRepository resultadoRepository;
	private ParticipacaoRepository participacaoRepository;

	public ResultadoService(ResultadoRepository resultadoRepository, ParticipacaoRepository participacaoRepository) {
		this.resultadoRepository = resultadoRepository;
		this.participacaoRepository = participacaoRepository;
	}

	public ResultadoDTO registrarResultadoOficial(ParticipacaoDTO participacao, String premiacao, String classificacao,
			String fonte) {
		if (participacao == null) {
			throw new IllegalArgumentException("Participação não encontrada.");
		}

		for (ResultadoOficial resultado : this.resultadoRepository.listarResultadoOficial()) {
			if (resultado.getParticipacao().getId() == participacao.getId()) {
				throw new IllegalArgumentException("Essa participação já possui resultado oficial.");
			}
		}

		if (premiacao.isBlank() || classificacao.isBlank() || fonte.length() < 5) {
			throw new IllegalArgumentException("Resultado incompleto. Nenhum dado foi armazenado.");
		}
		
		Participacao participacaoEntidade = participacaoRepository.buscarParticipacao(participacao.getId());

		ResultadoOficial resultado = this.resultadoRepository.registrarResultadoOficial(participacaoEntidade, premiacao,
				classificacao, fonte);
		
		ResultadoDTO resultadoDTO = parseResultado(resultado);
		return resultadoDTO;
	}

	public List<ResultadoDTO> listarResultadoOficial() {
		List<ResultadoOficial> resultados = this.resultadoRepository.listarResultadoOficial();
		List<ResultadoDTO> resultadosDTO = new ArrayList<>();

		for (ResultadoOficial resultado : resultados) {
			ResultadoDTO resultadoDTO = parseResultado(resultado);
			resultadosDTO.add(resultadoDTO);
		}

		return resultadosDTO;
	}

	public ResultadoDTO buscarResultadoOficial(int id) {
		ResultadoOficial resultado = resultadoRepository.buscarResultadoOficial(id);

		if (resultado == null) {
			return null;
		}
		ResultadoDTO resultadoDTO = parseResultado(resultado);

		return resultadoDTO;
	}

	private ResultadoDTO parseResultado(ResultadoOficial resultado) {
		if (resultado == null) {
			return null;
		}

		AlunoDTO alunoDTO = new AlunoDTO(resultado.getParticipacao().getAluno().getId(),
				resultado.getParticipacao().getAluno().getMatricula(), resultado.getParticipacao().getAluno().getNome(),
				resultado.getParticipacao().getAluno().getEmail());

		OlimpiadaDTO olimpiadaDTO = new OlimpiadaDTO(resultado.getParticipacao().getOlimpiada().getId(),
				resultado.getParticipacao().getOlimpiada().getNome(),
				resultado.getParticipacao().getOlimpiada().getEdicao());

		ParticipacaoDTO participacaoDTO = new ParticipacaoDTO(resultado.getParticipacao().getId(), alunoDTO,
				olimpiadaDTO, resultado.getParticipacao().getSituacao());

		return new ResultadoDTO(resultado.getId(), participacaoDTO, resultado.getPremiacao(),
				resultado.getClassificacao(), resultado.getFonte(), LocalDateTime.now());
	}
}
