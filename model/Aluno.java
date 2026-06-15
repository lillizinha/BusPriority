package model;

public class Aluno extends Pessoa {

    private String turma;
    private String linhaOnibus;
    private String prioridade;
    private String codigoAutorizacao;

    public Aluno(String nome,
                String matricula,
                String turma,
                String linhaOnibus) {

        super(nome, matricula);

        this.turma = turma;
        this.linhaOnibus = linhaOnibus;
        this.prioridade = "Não definida";
        this.codigoAutorizacao = "";
    }

    // GETTERS

    public String getTurma() {
        return turma;
    }

    public String getLinhaOnibus() {
        return linhaOnibus;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public String getCodigoAutorizacao() {
        return codigoAutorizacao;
    }

    // SETTERS

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public void setLinhaOnibus(String linhaOnibus) {
        this.linhaOnibus = linhaOnibus;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public void setCodigoAutorizacao(String codigoAutorizacao) {
        this.codigoAutorizacao = codigoAutorizacao;
    }

    @Override
    public String exibirDados() {

        return
            "Nome: " + nome +
            "\nMatrícula: " + matricula +
            "\nTurma: " + turma +
            "\nLinha do ônibus: " + linhaOnibus +
            "\nPrioridade: " + prioridade +
            "\nCódigo: " + codigoAutorizacao;
    }
}
