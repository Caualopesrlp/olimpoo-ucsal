package br.edu.ucsal.olimpoo.service;

import java.util.ArrayList;
import java.util.List;

import br.edu.ucsal.olimpoo.domain.Olimpiada;
import br.edu.ucsal.olimpoo.repository.OlimpiadaRepository;
import br.edu.ucsal.olimpoo.service.dto.OlimpiadaDTO;

public class OlimpiadaService {
	private OlimpiadaRepository olimpiadaRepository;
	
	
	public OlimpiadaService(OlimpiadaRepository olimpiadaRepository) {
		this.olimpiadaRepository = olimpiadaRepository;
		
	}
	
	public OlimpiadaDTO cadastrarOlimpiada(String nome, int edicao) {
		if (nome.length() < 3 || edicao < 2020 || edicao > 2100) {
			throw new IllegalArgumentException("Nome ou edição inválido.");
        }
        Olimpiada olimpiada = this.olimpiadaRepository.cadastrarOlimpiada(nome, edicao);
        OlimpiadaDTO olimpiadaDTO = parseOlimpiada(olimpiada);
        return olimpiadaDTO;
	}
	
	public List<OlimpiadaDTO> listarOlimpiadas() {
		List<Olimpiada> olimpiadas = this.olimpiadaRepository.listarOlimpiada();
		List<OlimpiadaDTO> olimpiadasDTO = new ArrayList<>();
		for(Olimpiada olimpiada : olimpiadas) {
			OlimpiadaDTO olimpiadaDTO = parseOlimpiada(olimpiada);
			olimpiadasDTO.add(olimpiadaDTO);
		}
		return olimpiadasDTO;
	}
	
	public OlimpiadaDTO buscarOlimpiadaPorId(int id) {
		Olimpiada olimpiada = this.olimpiadaRepository.buscarOlimpiadaPorId(id);
		if(olimpiada == null) {
			return null;
		}
		OlimpiadaDTO olimpiadaDTO = parseOlimpiada(olimpiada);
		
		return olimpiadaDTO;
	}
	
	private OlimpiadaDTO parseOlimpiada(Olimpiada olimpiada) {
		if (olimpiada == null) {
			return null;
		}
		OlimpiadaDTO olimpiadaDTO = new OlimpiadaDTO(olimpiada.getId(), olimpiada.getNome(), olimpiada.getEdicao());

		return olimpiadaDTO;
	}
}
