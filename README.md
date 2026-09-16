# 🏆 OlimPOO — Sistema de Gestão de Olimpíadas Acadêmicas

> **Projeto da disciplina Programação Orientada a Objetos Avançada (UCSal)**  
> **Professor:** [Mário Jorge Pereira](https://github.com/mariojp)

---

## 📌 Nota sobre o Histórico de Commits

Como o desenvolvimento e as refatorações foram realizados localmente antes do envio ao GitHub, o histórico de commits do repositório contém apenas a transição direta do código legado original para a versão atual refatorada.

---

## 🛠️ Estrutura Atual do Projeto

O código atual está organizado em uma **Arquitetura em Camadas** com separação clara de responsabilidades:

```text
br.edu.ucsal.olimpoo
├── domain/                      # Entidades do Domínio (Aluno, Olimpiada, etc.)
├── repository/                  # Interfaces de Persistência e Implementações em Memória
├── service/                     # Camada de Negócio e Conversão (Services)
│   └── dto/                     # Data Transfer Objects (DTOs)
├── config/                      # Carga de dados demonstrativos (LoadData)
├── OlimPOOApp.java              # Main e Bootstrapping (Injeção de Dependências)
└── OlimPOOConsole.java          # Interface de Apresentação (View)
