package br.edu.ucsal.olimpoo.service.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ResultadoDTO {
	private final int id;
	private final ParticipacaoDTO participacao;
	private final String premiacao;
	private final String classificacao;
	private final String fonte;
	private final LocalDateTime registradoEm;

	public ResultadoDTO(int id, ParticipacaoDTO participacao, String premiacao, String classificacao, String fonte,
			LocalDateTime registradoEm) {
		this.id = id;
		this.participacao = participacao;
		this.premiacao = premiacao;
		this.classificacao = classificacao;
		this.fonte = fonte;
		this.registradoEm = registradoEm;
	}

	public int getId() {
		return id;
	}

	public ParticipacaoDTO getParticipacao() {
		return participacao;
	}

	public String getPremiacao() {
		return premiacao;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public String getFonte() {
		return fonte;
	}

	@Override
	public String toString() {
		String data = registradoEm.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
		return "#" + id + " | " + participacao.getAluno().getNome() + " | " + participacao.getOlimpiada().getNome()
				+ " | " + premiacao + " | classificação: " + classificacao + " | fonte: " + fonte + " | registrado em "
				+ data;
	}
}
