package service;

import java.util.ArrayList;

public class SistemaAutorizacoes {

    private static ArrayList<String> codigos =
            new ArrayList<>();

    public static void adicionarCodigo(String codigo) {

        codigos.add(codigo);
    }

    public static boolean verificarCodigo(String codigo) {

        return codigos.contains(codigo);
    }

}
