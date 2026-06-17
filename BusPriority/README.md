# BusPriority

## Estrutura do Projeto

O projeto foi organizado usando pacotes Java para separar claramente modelo, serviços, tratamento de exceções e visão.

Pacotes e arquivos:

- `model`
  - `Pessoa.java`
  - `Aluno.java`
  - `Autorizacao.java`

- `service`
  - `CalculadoraPrioridade.java`
  - `PrioridadeAlta.java`
  - `PrioridadeMedia.java`
  - `PrioridadeBaixa.java`
  - `SistemaAutorizacoes.java`
  - `AlunoService.java`

- `exception`
  - `ValidacaoException.java`

- `view`
  - `Main.java`
  - `TelaAluno.java`
  - `TelaPorteiro.java`

## O que foi reorganizado

- `model`: classes que representam entidades do domínio.
- `service`: regras de negócio, cálculo de prioridade e gerenciamento de autorizações.
- `exception`: exceções personalizadas para validação de entrada.
- `view`: interface gráfica e inicialização da aplicação.

## Novas classes criadas

- `service.AlunoService`
  - Centraliza validações de dados de aluno.
  - Calcula horários e prioridade.
  - Cria autorizações.

- `exception.ValidacaoException`
  - Representa erros de validação de entrada.

## Melhorias de arquitetura

- Lógica de validação e prioridade foi extraída de `TelaAluno` para `AlunoService`.
- A classe `Main` permanece apenas como entry point da aplicação.
- O uso de `CalculadoraPrioridade` e suas subclasses mantém polimorfismo e abstração.
- `SistemaAutorizacoes` mantém controle de códigos autorizados centralizado.

## Compilação

Para compilar, execute no diretório raiz do projeto:

```bash
find . -name "*.java" | sort | xargs javac
```

## Observações

- A lógica principal existente foi preservada.
- Foram criadas apenas as classes novas necessárias para melhorar a arquitetura.
- Nenhuma funcionalidade existente foi removida.
