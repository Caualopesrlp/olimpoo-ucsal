package br.edu.ucsal.olimpoo.service.dto;


public class ParticipacaoDTO {
	private int id;
	private AlunoDTO aluno;
	private OlimpiadaDTO olimpiada;
	private String situacao;

	public ParticipacaoDTO(int id, AlunoDTO aluno, OlimpiadaDTO olimpiada, String situacao) {
		this.id = id;
		this.aluno = aluno;
		this.olimpiada = olimpiada;
		this.situacao = situacao;
	}

	public int getId() {
		return id;
	}

	public AlunoDTO getAluno() {
		return aluno;
	}

	public OlimpiadaDTO getOlimpiada() {
		return olimpiada;
	}

	public String getSituacao() {
		return situacao;
	}

	@Override
	public String toString() {
		return "#" + id + " | " + aluno.getNome() + " | " + olimpiada.getNome() + " " + olimpiada.getEdicao() + " | "
				+ situacao;
	}
}
