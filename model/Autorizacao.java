package model;

import java.util.Random;

public class Autorizacao {

    private String codigo;

    public Autorizacao() {
        gerarCodigo();
    }

    private void gerarCodigo() {

        Random random = new Random();

        int numero = 1000 + random.nextInt(9000);

        codigo = "BP-" + numero;
    }

    public String getCodigo() {
        return codigo;
    }
}
