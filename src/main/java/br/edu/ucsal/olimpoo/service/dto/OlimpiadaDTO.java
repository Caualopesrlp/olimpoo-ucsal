package br.edu.ucsal.olimpoo.service.dto;

public class OlimpiadaDTO {
	private final int id;
	private final String nome;
	private final int edicao;

	public OlimpiadaDTO(int id, String nome, int edicao) {
		this.id = id;
		this.nome = nome;
		this.edicao = edicao;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public int getEdicao() {
		return edicao;
	}

	@Override
	public String toString() {
		return "OlimpiadaDTO [id=" + id + ", nome=" + nome + ", edicao=" + edicao + "]";
	}
	
	
}
