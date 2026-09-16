package br.edu.ucsal.olimpoo;

import br.edu.ucsal.olimpoo.service.AlunoService;
import br.edu.ucsal.olimpoo.service.OlimpiadaService;
import br.edu.ucsal.olimpoo.service.ParticipacaoService;
import br.edu.ucsal.olimpoo.service.ResultadoService;
import br.edu.ucsal.olimpoo.service.dto.AlunoDTO;
import br.edu.ucsal.olimpoo.service.dto.OlimpiadaDTO;
import br.edu.ucsal.olimpoo.service.dto.ParticipacaoDTO;
import br.edu.ucsal.olimpoo.service.dto.ResultadoDTO;

import java.util.Scanner;

public class OlimPOOConsole {
	private static final Scanner ENTRADA = new Scanner(System.in);

	private  AlunoService alunoService;
	private  OlimpiadaService olimpiadaService;
	private  ParticipacaoService participacaoService;
	private  ResultadoService resultadoService;
	
	public OlimPOOConsole(AlunoService alunoService, OlimpiadaService olimpiadaService, ParticipacaoService participacaoService, ResultadoService resultadoService) {
		this.alunoService = alunoService;
		this.olimpiadaService = olimpiadaService;
		this.participacaoService = participacaoService;
		this.resultadoService = resultadoService;
	}

	public  void iniciar() {

		boolean executando = true;
		while (executando) {
			exibirMenu();
			String opcao = ENTRADA.nextLine().trim();
			switch (opcao) {
			case "1" -> cadastrarAluno();
			case "2" -> cadastrarOlimpiada();
			case "3" -> registrarParticipacao();
			case "4" -> registrarResultadoOficial();
			case "5" -> listarPainel();
			case "6" -> enviarLembrete();
			case "0" -> executando = false;
			default -> System.out.println("Opção inválida.");
			}
		}
		System.out.println("OlimPOO encerrado.");
	}

	private void exibirMenu() {

		System.out.println("\n=== OlimPOO ===");
		System.out.println("1 - Cadastrar aluno");
		System.out.println("2 - Cadastrar olimpíada");
		System.out.println("3 - Registrar participação");
		System.out.println("4 - Registrar resultado oficial");
		System.out.println("5 - Listar painel");
		System.out.println("6 - Enviar lembrete simulado");
		System.out.println("0 - Sair");
		System.out.print("Opção: ");
	}

	private void cadastrarAluno() {
		try {
			System.out.print("Matrícula: ");
			String matricula = ENTRADA.nextLine().trim();

			System.out.print("Nome: ");
			String nome = ENTRADA.nextLine().trim();

			System.out.print("E-mail: ");
			String email = ENTRADA.nextLine().trim();

			alunoService.cadastrarAluno(matricula, nome, email);
		} catch (IllegalArgumentException e) {
			System.out.println("Erro:" + e.getMessage());
		}

	}

	private void cadastrarOlimpiada() {
		try {
			System.out.print("Nome da olimpíada: ");
			String nome = ENTRADA.nextLine().trim();
			System.out.print("Edição/ano: ");
			int edicao = lerInteiro();
			OlimpiadaDTO olimpiada = olimpiadaService.cadastrarOlimpiada(nome, edicao);
			System.out.println("Olimpíada cadastrada: " + olimpiada);
		} catch (IllegalArgumentException e) {
			System.out.println("Erro: " + e.getMessage());
		}

	}

	private void registrarParticipacao() {
		try {
			listarAlunos();
			System.out.print("ID do aluno: ");
			AlunoDTO aluno = buscarAluno(lerInteiro());

			listarOlimpiadas();
			System.out.print("ID da olimpíada: ");
			OlimpiadaDTO olimpiada = buscarOlimpiada(lerInteiro());

			participacaoService.registrarParticipacao(aluno, olimpiada);

			System.out.println("E-mail simulado: confirmação enviada para " + aluno.getEmail());
		}catch(IllegalArgumentException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
	}

	private void registrarResultadoOficial() {
		try {
			listarParticipacoes();
			System.out.print("ID da participação: ");
			ParticipacaoDTO participacao = buscarParticipacao(lerInteiro());

			System.out.print("Premiação oficial (ouro, prata, bronze, menção ou nenhuma): ");
			String premiacao = ENTRADA.nextLine().trim();
			System.out.print("Classificação informada pela olimpíada: ");
			String classificacao = ENTRADA.nextLine().trim();
			System.out.print("Fonte oficial do resultado: ");
			String fonte = ENTRADA.nextLine().trim();

			ResultadoDTO resultado = resultadoService.registrarResultadoOficial(participacao, premiacao, classificacao,
					fonte);

			System.out.println("Resultado oficial registrado: " + resultado);
			System.out.println("E-mail simulado: resultado enviado para " + participacao.getAluno().getEmail());
			System.out.println("Auditoria simulada: operação registrada no console.");
		}catch (IllegalArgumentException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
	}

	private void enviarLembrete() {
		listarParticipacoes();
		System.out.print("ID da participação: ");
		ParticipacaoDTO participacao = buscarParticipacao(lerInteiro());
		if (participacao == null) {
			System.out.println("Participação não encontrada.");
			return;
		}
		String mensagem = "Lembrete: acompanhe o cronograma da " + participacao.getOlimpiada().getNome() + " "
				+ participacao.getOlimpiada().getEdicao() + ".";
		System.out.println("E-mail simulado para " + participacao.getAluno().getEmail() + ": " + mensagem);
	}

	private void listarPainel() {
		System.out.println("\n--- ALUNOS ---" + alunoService.listarAlunos().size());
		listarAlunos();
		System.out.println("\n--- OLIMPÍADAS ---" + olimpiadaService.listarOlimpiadas().size());
		listarOlimpiadas();
		System.out.println("\n--- PARTICIPAÇÕES ---" + participacaoService.listarParticipacoes().size());
		listarParticipacoes();
		System.out.println("\n--- RESULTADOS OFICIAIS ---" + resultadoService.listarResultadoOficial().size());
		if (resultadoService.listarResultadoOficial().isEmpty()) {
			System.out.println("Nenhum Resultado Registrado");
		} else {
			for (ResultadoDTO resultado : resultadoService.listarResultadoOficial()) {
				System.out.println(resultado);
			}
		}
	}

	private void listarAlunos() {
		alunoService.listarAlunos();
	}

	private void listarOlimpiadas() {
		olimpiadaService.listarOlimpiadas();
	}

	private void listarParticipacoes() {
		for (ParticipacaoDTO participacao : participacaoService.listarParticipacoes()) {
			System.out.println(participacao);
		}
	}

	private AlunoDTO buscarAluno(int id) {
		return alunoService.buscarAluno(id);
	}

	private OlimpiadaDTO buscarOlimpiada(int id) {
		return olimpiadaService.buscarOlimpiadaPorId(id);
	}

	private ParticipacaoDTO buscarParticipacao(int id) {
		return participacaoService.buscarParticipacao(id);
	}

	private static int lerInteiro() {
		String texto = ENTRADA.nextLine().trim();
		try {
			return Integer.parseInt(texto);
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	
}
