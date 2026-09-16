package br.edu.ucsal.olimpoo.repository;

import java.util.List;

import br.edu.ucsal.olimpoo.domain.Participacao;
import br.edu.ucsal.olimpoo.domain.ResultadoOficial;

public interface ResultadoRepository {
	ResultadoOficial registrarResultadoOficial(Participacao participacao, String premiacao, String classificacao,
			String fonte);
	List<ResultadoOficial> listarResultadoOficial();
	ResultadoOficial buscarResultadoOficial(int id);
}
