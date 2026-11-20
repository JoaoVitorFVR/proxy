package org.example;

import java.util.HashMap;
import java.util.Map;

public class BancoFiliais {

    private static Map<Integer, Filial> filiais = new HashMap<>();

    public static Filial getFilial(Integer codigo) {
        return filiais.get(codigo);
    }

    public static void addFilial(Filial filial) {
        filiais.put(filial.getCodigo(), filial);
    }
}
