package br.edu.ucsal.olimpoo;

import br.edu.ucsal.olimpoo.config.LoadData;
import br.edu.ucsal.olimpoo.repository.AlunoRepositoryArrayList;
import br.edu.ucsal.olimpoo.repository.OlimpiadaRepositoryArrayList;
import br.edu.ucsal.olimpoo.repository.ParticipacaoRepositoryArrayList;
import br.edu.ucsal.olimpoo.repository.ResultadoRepositoryArrayList;
import br.edu.ucsal.olimpoo.service.AlunoService;
import br.edu.ucsal.olimpoo.service.OlimpiadaService;
import br.edu.ucsal.olimpoo.service.ParticipacaoService;
import br.edu.ucsal.olimpoo.service.ResultadoService;

public class OlimPOOApp {

	private static AlunoService alunoService;
	private static OlimpiadaService olimpiadaService;
	private static ParticipacaoService participacaoService;
	private static ResultadoService resultadoService;
	private static LoadData loadData;

	public static void main(String[] args) {
		AlunoRepositoryArrayList alunoRepository = new AlunoRepositoryArrayList();
		alunoService = new AlunoService(alunoRepository);

		OlimpiadaRepositoryArrayList olimpiadaRepository = new OlimpiadaRepositoryArrayList();
		olimpiadaService = new OlimpiadaService(olimpiadaRepository);

		ParticipacaoRepositoryArrayList participacaoRepository = new ParticipacaoRepositoryArrayList();
		participacaoService = new ParticipacaoService(participacaoRepository, alunoRepository, olimpiadaRepository);

		ResultadoRepositoryArrayList resultadoRepository = new ResultadoRepositoryArrayList();
		resultadoService = new ResultadoService(resultadoRepository, participacaoRepository);

		loadData = new LoadData(alunoService, olimpiadaService, participacaoService, resultadoService);

		loadData.carregarDadosDemonstracao();
		
		OlimPOOConsole console = new OlimPOOConsole(alunoService, olimpiadaService, participacaoService, resultadoService);
		console.iniciar();

	}

}
