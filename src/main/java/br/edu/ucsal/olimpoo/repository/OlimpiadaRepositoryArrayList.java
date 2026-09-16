package br.edu.ucsal.olimpoo.repository;

import java.util.ArrayList;
import java.util.List;

import br.edu.ucsal.olimpoo.domain.Olimpiada;

public class OlimpiadaRepositoryArrayList implements OlimpiadaRepository {
	private static final ArrayList<Olimpiada> OLIMPIADAS = new ArrayList<>();
	private int proximaOlimpiadaId = 1;
	
	public Olimpiada cadastrarOlimpiada(String nome, int edicao) {
		Olimpiada olimpiada = new Olimpiada(proximaOlimpiadaId, nome, edicao);
		OLIMPIADAS.add(olimpiada);
		proximaOlimpiadaId++;
		return olimpiada;
	}
	
	public List<Olimpiada> listarOlimpiada() {
		return List.copyOf(OLIMPIADAS);
	}
	
	public Olimpiada buscarOlimpiadaPorId(int id) {
		for (Olimpiada olimpiada : OLIMPIADAS) {
			if(olimpiada.getId() == id) {
				return olimpiada;
			}
		}
		
		return null;
	}

}
