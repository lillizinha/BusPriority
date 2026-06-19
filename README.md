# BusPriority

Sistema de gerenciamento de prioridades para embarque em ônibus, com controle de autorizações e prioridades diferenciadas por tipo de urgência de alunos.

---

## 📋 Requisitos Funcionais

### RF1 - Cadastro e Validação de Alunos
- [x] Registrar alunos com matrícula, nome, série, turma e tipo de deficiência (se houver)
- [x] Validar formato da matrícula (deve ser numérica)
- [x] Validar campos obrigatórios (não podem estar vazios)
- [x] Validar série (deve estar entre 1 e 12)

### RF2 - Cálculo de Prioridade
- [x] Classificar passageiros em níveis de prioridade: Alta, Média e Baixa
- [x] **Prioridade Alta**: Alunos com urgência a sair
- [x] **Prioridade Média**: Alunos com leve urgência de sair
- [x] **Prioridade Baixa**: Alunos sem urgência de sair
- [x] Aplicar regras de prioridade de forma polimórfica

### RF3 - Sistema de Autorizações
- [x] Gerar código de autorização único por passageiro
- [x] Armazenar autorizações no sistema
- [x] Validar se um código de autorização existe
- [x] Recuperar dados do passageiro através do código de autorização

### RF4 - Interface para Alunos
- [x] Formulário para registrar aluno
- [x] Campos: Matrícula, Nome, Série, Turma, Deficiência
- [x] Botão para solicitar autorização
- [x] Exibição do código de autorização gerado
- [x] Mensagens de erro para validações inválidas

### RF5 - Interface para Porteiro
- [x] Campo para consultar código de autorização
- [x] Exibição de dados do passageiro autorizado
- [x] Identificação visual da prioridade do passageiro
- [x] Mensagem de acesso negado para código inválido

### RF6 - Segurança e Validação
- [x] Lançar exceções personalizadas para erros de validação
- [x] Tratamento de exceções em toda a aplicação
- [x] Validação em múltiplas camadas (service, view)

---

## 📊 Requisitos Não-Funcionais

### RNF1 - Performance
- [x] Sistema deve responder a consultas de autorização em tempo real
- [x] Cálculo de prioridade deve ser instantâneo
- [x] Interface gráfica deve ser responsiva

### RNF2 - Usabilidade
- [x] Interface intuitiva e de fácil navegação
- [x] Mensagens de erro claras e em português
- [x] Campos de entrada com validação visual

### RNF3 - Manutenibilidade
- [x] Código organizado em camadas (model, service, view, exception)
- [x] Separação de responsabilidades
- [x] Fácil adição de novos tipos de prioridade

### RNF4 - Confiabilidade
- [x] Códigos de autorização únicos e não repetidos
- [x] Dados persistidos corretamente em memória durante execução
- [x] Tratamento de erros sem travamento do sistema

---

## 🏗️ Estrutura do Projeto

O projeto foi organizado usando pacotes Java para separar claramente modelo, serviços, tratamento de exceções e visão:

### `model/` - Entidades do Domínio
- **`Pessoa.java`** - Classe abstrata base para todos os tipos de usuários
- **`Aluno.java`** - Classe que representa um aluno com seus dados
- **`Autorizacao.java`** - Classe que representa uma autorização de embarque

### `service/` - Lógica de Negócio
- **`CalculadoraPrioridade.java`** - Interface/classe abstrata para cálculo de prioridades
- **`PrioridadeAlta.java`** - Implementação para prioridade alta (idosos, gestantes, deficientes)
- **`PrioridadeMedia.java`** - Implementação para prioridade média (deficiências leves)
- **`PrioridadeBaixa.java`** - Implementação para prioridade baixa (sem deficiência)
- **`SistemaAutorizacoes.java`** - Gerenciamento centralizado de autorizações
- **`AlunoService.java`** - Centralizador de validações e lógica de aluno

### `exception/` - Exceções Personalizadas
- **`ValidacaoException.java`** - Exceção para erros de validação de entrada

### `view/` - Interface com Usuário
- **`Main.java`** - Ponto de entrada da aplicação
- **`TelaAluno.java`** - Interface gráfica para alunos
- **`TelaPorteiro.java`** - Interface gráfica para porteiro

---

## 🔄 Fluxo de Funcionamento

### Fluxo - Cadastro de Aluno e Geração de Autorização

```
1. Aluno acessa TelaAluno
   ↓
2. Preenche formulário (matrícula, nome, série, turma, deficiência)
   ↓
3. Clica em "Solicitar Autorização"
   ↓
4. AlunoService valida os dados
   ├→ Se inválido: lança ValidacaoException
   └→ Se válido: continua
   ↓
5. CalculadoraPrioridade determina nível de prioridade
   ↓
6. SistemaAutorizacoes gera código único
   ↓
7. Código é exibido na tela para o aluno
```

### Fluxo - Consulta de Autorização (Porteiro)

```
1. Porteiro acessa TelaPorteiro
   ↓
2. Digita código de autorização
   ↓
3. Clica em "Consultar"
   ↓
4. SistemaAutorizacoes busca autorização
   ├→ Se não encontrada: mensagem "Acesso Negado"
   └→ Se encontrada: continua
   ↓
5. Exibe dados do passageiro e nível de prioridade
```

---

## 🛠️ Melhorias de Arquitetura

- ✅ **Lógica extraída** - Validações e cálculo de prioridade movidos de `TelaAluno` para `AlunoService`
- ✅ **Polimorfismo** - Uso de `CalculadoraPrioridade` e suas subclasses para extensibilidade
- ✅ **Centralização** - `SistemaAutorizacoes` gerencia todos os códigos autorizados
- ✅ **Exceções personalizadas** - `ValidacaoException` para melhor tratamento de erros
- ✅ **Separação de responsabilidades** - Cada camada tem um propósito específico
- ✅ **Entry point limpo** - `Main.java` apenas inicia a aplicação

---

## 📦 Como Compilar

Execute no diretório raiz do projeto:

```bash
find . -name "*.java" | sort | xargs javac
```

Ou compile manualmente todos os arquivos:

```bash
javac model/*.java service/*.java exception/*.java view/*.java
```

## 🚀 Como Executar

```bash
java view.Main
```

---

## 📝 Exemplo de Uso

### Aluno
1. Abra a interface de aluno
2. Digite:
   - Matrícula: `123456`
   - Nome: `João Silva`
   - Série: `10`
   - Turma: `A`
   - Deficiência: `Nenhuma`
3. Clique em "Solicitar Autorização"
4. Receberá um código único

### Porteiro
1. Abra a interface do porteiro
2. Digite o código recebido pelo aluno
3. Clique em "Consultar"
4. Verá os dados e prioridade do aluno

---

## 📌 Notas Importantes

- ✅ Toda a lógica existente foi preservada
- ✅ Nenhuma funcionalidade foi removida
- ✅ Arquitetura foi melhorada mantendo compatibilidade
- ✅ Código está pronto para novas extensões (novos tipos de prioridade, novas validações)

---

## 👨‍💻 Tecnologias Utilizadas

- **Linguagem**: Java
- **Paradigma**: Orientado a Objetos
- **Padrões**: Strategy (para cálculo de prioridade), Singleton (para autorização)
- **Interface**: Swing (GUI)

---

## 📄 Licença

Este projeto é de uso educacional.
