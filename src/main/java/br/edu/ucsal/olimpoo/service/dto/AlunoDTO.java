package br.edu.ucsal.olimpoo.service.dto;

public class AlunoDTO {
	private int id;
	private String matricula;
	private String nome;
	private String email;
	
	public AlunoDTO(int id, String matricula, String nome, String email) {
		this.id = id;
		this.matricula = matricula;
		this.nome = nome;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public String getMatricula() {
		return matricula;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return "AlunoDTO [id=" + id + ", matricula=" + matricula + ", nome=" + nome + ", email=" + email + "]";
	}
	
	
	
}
