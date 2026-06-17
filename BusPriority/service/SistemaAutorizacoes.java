package service;

import java.util.ArrayList;
import java.util.Iterator;

public class SistemaAutorizacoes {

    public enum Resultado {
        NAO_EXISTE,
        AUTORIZADO,
        NEGADO_PRIORIDADE,
        NEGADO_PROBABILISTICO
    }

    private static class Entrada {
        String codigo;
        String prioridade; // "ALTA", "MÉDIA", "BAIXA"

        Entrada(String c, String p) {
            codigo = c;
            prioridade = p;
        }
    }

    private static ArrayList<Entrada> fila = new ArrayList<>();

    private static final String SENHA_PORTEIRO = "1234";

    public static void adicionarCodigo(String codigo, String prioridade) {
        fila.add(new Entrada(codigo, prioridade));
    }

    public static boolean verificarPorteiroSenha(String senha) {
        return SENHA_PORTEIRO.equals(senha);
    }

    
    public static boolean verificarCodigo(String codigo) {
        return verificarCodigoComMotivo(codigo) == Resultado.AUTORIZADO;
    }

    public static Resultado verificarCodigoComMotivo(String codigo) {

        Entrada alvo = null;

        for (Entrada e : fila) {
            if (e.codigo.equals(codigo)) {
                alvo = e;
                break;
            }
        }

        if (alvo == null) {
            return Resultado.NAO_EXISTE;
        }

        int nivelAlvo = nivelPrioridade(alvo.prioridade);

        // se existir alguém com prioridade maior (número maior) na fila, negar
        for (Entrada e : fila) {
            if (e == alvo) continue;
            int nivelOutro = nivelPrioridade(e.prioridade);
            if (nivelOutro > nivelAlvo) {
                return Resultado.NEGADO_PRIORIDADE;
            }
        }

        // Se a prioridade do alvo for BAIXA, negar a saída; deve aguardar horário padrão.
        if (alvo.prioridade != null && alvo.prioridade.equalsIgnoreCase("BAIXA")) {
            return Resultado.NEGADO_PROBABILISTICO;
        }

        // autorizado: remover da fila
        Iterator<Entrada> it = fila.iterator();
        while (it.hasNext()) {
            if (it.next().codigo.equals(codigo)) {
                it.remove();
                break;
            }
        }

        return Resultado.AUTORIZADO;
    }

    private static int nivelPrioridade(String p) {
        if (p == null) return 0;
        switch (p.toUpperCase()) {
            case "ALTA":
                return 3;
            case "MÉDIA":
            case "MEDIA":
                return 2;
            case "BAIXA":
                return 1;
            default:
                return 0;
        }
    }

}
