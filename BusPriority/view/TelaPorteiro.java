package view;

import javax.swing.*;
import java.awt.event.*;
import service.SistemaAutorizacoes;

public class TelaPorteiro extends JFrame {

private JTextField txtCodigo;
private JTextField txtSenha;
private JTextArea areaResultado;
private JButton btnVerificar;

public TelaPorteiro() {

        setTitle("BusPriority - Porteiro");

        setSize(500, 300);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(null);

        JLabel lblCodigo =
                new JLabel("Código:");

        lblCodigo.setBounds(
                20,
                30,
                100,
                25
        );

        add(lblCodigo);

        txtCodigo =
                new JTextField();

        txtCodigo.setBounds(
                100,
                30,
                200,
                25
        );

        add(txtCodigo);

        JLabel lblSenha =
                new JLabel("Senha Porteiro:");

        lblSenha.setBounds(
                20,
                60,
                120,
                25
        );

        add(lblSenha);

        txtSenha =
                new JTextField();

        txtSenha.setBounds(
                140,
                60,
                160,
                25
        );

        add(txtSenha);

        btnVerificar =
                new JButton("Verificar");

        btnVerificar.setBounds(
                320,
                30,
                120,
                25
        );

        add(btnVerificar);

        areaResultado =
                new JTextArea();

        areaResultado.setEditable(false);

        JScrollPane scroll =
                new JScrollPane(areaResultado);

        scroll.setBounds(
                20,
                120,
                420,
                90
        );

        add(scroll);

        btnVerificar.addActionListener(
                new ActionListener() {

                @Override
                public void actionPerformed(
                        ActionEvent e) {

                        verificarCodigo();
                }
                }
        );

        setVisible(true);
}

public void setCodigo(String codigo) {
        if (codigo == null) return;
        txtCodigo.setText(codigo);
}

private void verificarCodigo() {

        String codigo =
                txtCodigo.getText().trim();
        String senha = txtSenha.getText().trim();

        if (senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite a senha do porteiro.");
            return;
        }

        if (!SistemaAutorizacoes.verificarPorteiroSenha(senha)) {
            JOptionPane.showMessageDialog(this, "Senha do porteiro inválida.");
            return;
        }

        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite um código.");
            return;
        }

        SistemaAutorizacoes.Resultado resultado = SistemaAutorizacoes.verificarCodigoComMotivo(codigo);

        switch (resultado) {
            case AUTORIZADO:
                areaResultado.setText(
                        "RESULTADO DA ANÁLISE\n\n"
                                + "Código: " + codigo
                                + "\n\nSTATUS: AUTORIZADO"
                                + "\n\nO aluno possui autorização para sair.");
                break;
            case NAO_EXISTE:
                areaResultado.setText(
                        " RESULTADO DA ANÁLISE\n\n"
                                + "Código: " + codigo
                                + "\n\nSTATUS: NÃO AUTORIZADO"
                                + "\n\nCódigo inexistente.");
                break;
            case NEGADO_PRIORIDADE:
                areaResultado.setText(
                        " RESULTADO DA ANÁLISE\n\n"
                                + "Código: " + codigo
                                + "\n\nSTATUS: NÃO AUTORIZADO"
                                + "\n\nHá pessoas com prioridade maior na fila. Aguarde o horário de saída padrão (16:40).");
                break;
            case NEGADO_PROBABILISTICO:
                areaResultado.setText(
                        "RESULTADO DA ANÁLISE\n\n"
                                + "Código: " + codigo
                                + "\n\nSTATUS: NÃO AUTORIZADO"
                                + "\n\nA maioria na fila possui prioridade BAIXA. Você deve aguardar até o horário padrão (16:40)."
                                + "\n\nExiste uma chance muito pequena de autorização; tente novamente mais tarde.");
                break;
            default:
                areaResultado.setText(
                        "RESULTADO DA ANÁLISE\n\n"
                                + "Código: " + codigo
                                + "\n\nSTATUS: NÃO AUTORIZADO"
                                + "\n\nOperação não permitida.");
                break;
        }
}
}
