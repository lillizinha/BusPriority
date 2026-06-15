package service;

import exception.ValidacaoException;
import model.Aluno;
import model.Autorizacao;

import java.util.Random;

public class AlunoService {

    public void validarDadosAluno(String nome, String matricula, String turma, String linha) throws ValidacaoException {
        if (nome == null || nome.isBlank() ||
            matricula == null || matricula.isBlank() ||
            turma == null || turma.isBlank() ||
            linha == null || linha.isBlank()) {
            throw new ValidacaoException("Preencha todos os campos.");
        }

        if (!nome.matches("[a-zA-ZÀ-ÿ ]+")) {
            throw new ValidacaoException("Nome deve conter apenas letras.");
        }

        if (!matricula.matches("\\d+")) {
            throw new ValidacaoException("Matrícula deve conter apenas números.");
        }

        if (!linha.matches("\\d+")) {
            throw new ValidacaoException("Linha deve conter apenas números.");
        }
    }

    public int calcularMinutoProximoOnibus() {
        return 25 + new Random().nextInt(31);
    }

    public int calcularMinutoSeguinte(int minutoProximo) {
        return minutoProximo + 20 + new Random().nextInt(21);
    }

    public String calcularPrioridade(int minutoProximo) {
        CalculadoraPrioridade calc;

        if (minutoProximo < 40) {
            calc = new PrioridadeAlta();
        } else if (minutoProximo <= 45) {
            calc = new PrioridadeMedia();
        } else {
            calc = new PrioridadeBaixa();
        }

        return calc.calcular(minutoProximo);
    }

    public void aplicarDadosAoAluno(Aluno aluno, String prioridade, String codigoAutorizacao) {
        aluno.setPrioridade(prioridade);
        aluno.setCodigoAutorizacao(codigoAutorizacao);
    }

    public Autorizacao criarAutorizacao() {
        return new Autorizacao();
    }
}
