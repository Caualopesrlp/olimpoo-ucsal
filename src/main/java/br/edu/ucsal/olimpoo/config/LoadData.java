package br.edu.ucsal.olimpoo.config;

import br.edu.ucsal.olimpoo.service.AlunoService;
import br.edu.ucsal.olimpoo.service.OlimpiadaService;
import br.edu.ucsal.olimpoo.service.ParticipacaoService;
import br.edu.ucsal.olimpoo.service.ResultadoService;
import br.edu.ucsal.olimpoo.service.dto.AlunoDTO;
import br.edu.ucsal.olimpoo.service.dto.OlimpiadaDTO;
import br.edu.ucsal.olimpoo.service.dto.ParticipacaoDTO;

public class LoadData {

	private AlunoService alunoService;
	private OlimpiadaService olimpiadaService;
	private ParticipacaoService participacaoService;
	private ResultadoService resultadoService;

	public LoadData(AlunoService alunoService, OlimpiadaService olimpiadaService, ParticipacaoService participacaoService, ResultadoService resultadoService) {
		this.alunoService = alunoService;
		this.olimpiadaService = olimpiadaService;
		this.participacaoService = participacaoService;
		this.resultadoService = resultadoService;
		
	}

	public void carregarDadosDemonstracao() {
		AlunoDTO aluno = alunoService.cadastrarAluno("2026001", "Ana Souza", "ana.souza@ucsal.edu.br");
		OlimpiadaDTO olimpiada = olimpiadaService.cadastrarOlimpiada("OBI", 2026);
		ParticipacaoDTO participacao = participacaoService.registrarParticipacao(aluno, olimpiada);
		resultadoService.registrarResultadoOficial(participacao, "Bronze", "42ª colocação", "planilha oficial da OBI");
	}
}
