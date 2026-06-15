package view;

import exception.ValidacaoException;
import model.Aluno;
import model.Autorizacao;
import service.AlunoService;
import service.SistemaAutorizacoes;

import javax.swing.*;
import java.awt.event.*;

public class TelaAluno extends JFrame {

    private JTextField txtNome;
    private JTextField txtMatricula;
    private JTextField txtTurma;
    private JTextField txtLinha;

    private JTextArea areaResultado;

    private JButton btnConsultar;
    private JButton btnSolicitar;

    private final AlunoService alunoService = new AlunoService();
    private Aluno aluno;
    private String prioridadeAtual = "";
    private String codigoGerado = "";

    public TelaAluno() {

        setTitle("BusPriority - Sistema de Saída Antecipada");

        setSize(700, 550);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);

        JLabel titulo = new JLabel("BUSPRIORITY");

        titulo.setBounds(280, 10, 200, 30);

        add(titulo);

        JLabel lblNome = new JLabel("Nome:");

        lblNome.setBounds(20, 60, 100, 25);

        add(lblNome);

        txtNome = new JTextField();

        txtNome.setBounds(140, 60, 250, 25);

        add(txtNome);

        JLabel lblMatricula =
                new JLabel("Matrícula:");

        lblMatricula.setBounds(20, 100, 100, 25);

        add(lblMatricula);

        txtMatricula = new JTextField();

        txtMatricula.setBounds(140, 100, 250, 25);

        add(txtMatricula);

        JLabel lblTurma =
                new JLabel("Turma:");

        lblTurma.setBounds(20, 140, 100, 25);

        add(lblTurma);

        txtTurma = new JTextField();

        txtTurma.setBounds(140, 140, 250, 25);

        add(txtTurma);

        JLabel lblLinha =
                new JLabel("Linha do ônibus:");

        lblLinha.setBounds(20, 180, 120, 25);

        add(lblLinha);

        txtLinha = new JTextField();

        txtLinha.setBounds(140, 180, 250, 25);

        add(txtLinha);

        btnConsultar =
                new JButton("Consultar Ônibus");

        btnConsultar.setBounds(
                50,
                240,
                180,
                35
        );

        add(btnConsultar);

        btnSolicitar =
                new JButton("Solicitar Saída");

        btnSolicitar.setBounds(
                260,
                240,
                180,
                35
        );

        add(btnSolicitar);

        areaResultado =
                new JTextArea();

        areaResultado.setEditable(false);

        JScrollPane scroll =
                new JScrollPane(areaResultado);

        scroll.setBounds(
                20,
                300,
                640,
                180
        );

        add(scroll);

        btnConsultar.addActionListener(
                new ActionListener() {

                @Override
                public void actionPerformed(
                        ActionEvent e) {

                        consultarOnibus();
                }
                }
        );

        btnSolicitar.addActionListener(
                new ActionListener() {

                @Override
                public void actionPerformed(
                        ActionEvent e) {

                        solicitarSaida();
                }
                }
        );

        setVisible(true);
}

private void consultarOnibus() {

        try {
            String nome = txtNome.getText().trim();
            String matricula = txtMatricula.getText().trim();
            String turma = txtTurma.getText().trim();
            String linha = txtLinha.getText().trim();

            alunoService.validarDadosAluno(nome, matricula, turma, linha);

            aluno = new Aluno(nome, matricula, turma, linha);

            int minutoProximo = alunoService.calcularMinutoProximoOnibus();
            int minutoSeguinte = alunoService.calcularMinutoSeguinte(minutoProximo);
            prioridadeAtual = alunoService.calcularPrioridade(minutoProximo);
            aluno.setPrioridade(prioridadeAtual);

            areaResultado.setText(
                    "CONSULTA \n\n"
                            + "Nome: "
                            + nome
                            + "\nLinha: "
                            + linha
                            + "\n\nPróximo ônibus: 16:"
                            + String.format("%02d", minutoProximo)
                            + "\nÔnibus seguinte: 17:"
                            + String.format("%02d", minutoSeguinte % 60)
                            + "\n\nHorário padrão da escola: 16:40"
                            + "\n\nPrioridade: "
                            + prioridadeAtual
                            + "\n\nClique em 'Solicitar Saída' caso deseje autorização."
            );

        } catch (ValidacaoException erro) {
            JOptionPane.showMessageDialog(this, erro.getMessage());
        }
}

private void solicitarSaida() {

        try {
            if (prioridadeAtual.isEmpty() || aluno == null) {
                throw new ValidacaoException("Consulte o ônibus primeiro.");
            }

            Autorizacao aut = alunoService.criarAutorizacao();
            codigoGerado = aut.getCodigo();
            aluno.setCodigoAutorizacao(codigoGerado);
            SistemaAutorizacoes.adicionarCodigo(codigoGerado);

            areaResultado.append(
                    "\n\n===== AUTORIZAÇÃO ====="
                            + "\nCódigo gerado: "
                            + codigoGerado
                            + "\n\nApresente este código ao porteiro."
            );

        } catch (ValidacaoException erro) {
            JOptionPane.showMessageDialog(this, erro.getMessage());
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(this, erro.getMessage());
        }
}

public String getCodigoGerado() {

        return codigoGerado;
}
}
