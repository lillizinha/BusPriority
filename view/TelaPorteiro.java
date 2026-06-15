package view;

import javax.swing.*;
import java.awt.event.*;
import service.SistemaAutorizacoes;

public class TelaPorteiro extends JFrame {

private JTextField txtCodigo;
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
                80,
                420,
                130
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

private void verificarCodigo() {

        String codigo =
                txtCodigo.getText().trim();

        if(codigo.isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "Digite um código."
        );

        return;
        }

        boolean autorizado =
                SistemaAutorizacoes
                        .verificarCodigo(
                                codigo
                        );

        if(autorizado) {

        areaResultado.setText(

                "===== RESULTADO =====\n\n"

                + "Código: "
                + codigo

                + "\n\nSTATUS: AUTORIZADO"

                + "\n\nO aluno possui autorização para sair."
        );

        } else {

        areaResultado.setText(

                "===== RESULTADO =====\n\n"

                + "Código: "
                + codigo

                + "\n\nSTATUS: NÃO AUTORIZADO"

                + "\n\nCódigo inexistente."
        );
        }
}
}
