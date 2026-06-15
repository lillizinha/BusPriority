import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            TelaAluno TelaAluno =
                    new TelaAluno();

            TelaAluno.setVisible(true);

        });

    }

}
