package org.example;

import java.util.List;

public interface IFilial {
    List<String> obterDadosGerais();
    List<Float> obterFaturamento(Usuario usuario);
}
