package br.edu.ucsal.olimpoo.repository;

import java.util.List;

import br.edu.ucsal.olimpoo.domain.Olimpiada;

public interface OlimpiadaRepository {
	Olimpiada cadastrarOlimpiada(String nome, int edicao);
	List<Olimpiada> listarOlimpiada();
	Olimpiada buscarOlimpiadaPorId(int id);
}
