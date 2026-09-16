package br.edu.ucsal.olimpoo.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.edu.ucsal.olimpoo.domain.Participacao;
import br.edu.ucsal.olimpoo.domain.ResultadoOficial;

public class ResultadoRepositoryArrayList implements ResultadoRepository {
	private static final ArrayList<ResultadoOficial> RESULTADOS = new ArrayList<>();
	private static int proximoResultadoId = 1;

	public ResultadoOficial registrarResultadoOficial(Participacao participacao, String premiacao, String classificacao,
			String fonte) {
		ResultadoOficial resultado = new ResultadoOficial(proximoResultadoId, participacao, premiacao, classificacao,
				fonte, LocalDateTime.now());
		RESULTADOS.add(resultado);
		proximoResultadoId++;
		return resultado;
	}

	public List<ResultadoOficial> listarResultadoOficial() {
		return List.copyOf(RESULTADOS);
	}

	public ResultadoOficial buscarResultadoOficial(int id) {
		for (ResultadoOficial resultado : RESULTADOS) {
			if (resultado.getId() == id) {
				return resultado;
			}
		}

		return null;
	}
}
