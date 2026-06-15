import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class TelaAluno extends JFrame {

private JTextField txtNome;
private JTextField txtMatricula;
private JTextField txtTurma;
private JTextField txtLinha;

private JTextArea areaResultado;

private JButton btnConsultar;
private JButton btnSolicitar;

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

        String nome =
                txtNome.getText().trim();

        String matricula =
                txtMatricula.getText().trim();

        String turma =
                txtTurma.getText().trim();

        String linha =
                txtLinha.getText().trim();

        if(nome.isEmpty()
                || matricula.isEmpty()
                || turma.isEmpty()
                || linha.isEmpty()) {

                throw new Exception(
                        "Preencha todos os campos."
                );
        }

        if(!nome.matches("[a-zA-ZÀ-ÿ ]+")) {

                throw new Exception(
                        "Nome deve conter apenas letras."
                );
        }

        if(!matricula.matches("\\d+")) {

                throw new Exception(
                        "Matrícula deve conter apenas números."
                );
        }

        if(!linha.matches("\\d+")) {

                throw new Exception(
                        "Linha deve conter apenas números."
                );
        }

        Random random =
                new Random();

        int minutoProximo =
                25 + random.nextInt(31);

        int minutoSeguinte =
                minutoProximo +
                20 +
                random.nextInt(21);

        int horaSaida = 16;
        int minutoSaida = 40;

        int diferenca =
                minutoProximo -
                minutoSaida;

        CalculadoraPrioridade calc;

        if(minutoProximo < 40) {

                calc =
                        new PrioridadeAlta();

        } else if(minutoProximo <= 45) {

                calc =
                        new PrioridadeMedia();

        } else {

                calc =
                        new PrioridadeBaixa();
        }

        prioridadeAtual =
                calc.calcular(diferenca);

        areaResultado.setText(

                "CONSULTA \n\n"

                + "Nome: "
                + nome

                + "\nLinha: "
                + linha

                + "\n\nPróximo ônibus: 16:"
                + String.format(
                        "%02d",
                        minutoProximo
                )

                + "\nÔnibus seguinte: 17:"
                + String.format(
                        "%02d",
                        minutoSeguinte % 60
                )

                + "\n\nHorário padrão da escola: 16:40"

                + "\n\nPrioridade: "
                + prioridadeAtual

                + "\n\nClique em 'Solicitar Saída' caso deseje autorização."
        );

        } catch(Exception erro) {

        JOptionPane.showMessageDialog(
                this,
                erro.getMessage()
        );
        }
}

private void solicitarSaida() {

        try {

        if(prioridadeAtual.isEmpty()) {

                throw new Exception(
                        "Consulte o ônibus primeiro."
                );
        }

        Autorizacao aut =
                new Autorizacao();

        codigoGerado =
                aut.getCodigo();

        areaResultado.append(

                "\n\n===== AUTORIZAÇÃO ====="

                + "\nCódigo gerado: "
                + codigoGerado

                + "\n\nApresente este código ao porteiro."
        );

        } catch(Exception erro) {

        JOptionPane.showMessageDialog(
                this,
                erro.getMessage()
        );
        }
}

public String getCodigoGerado() {

        return codigoGerado;
}
}
