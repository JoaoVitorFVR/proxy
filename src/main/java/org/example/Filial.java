package org.example;

import java.util.Arrays;
import java.util.List;

public class Filial implements IFilial {

    private static int totalCarregamentos = 0;

    private Integer codigo;
    private String nome;
    private String cidade;
    private Float faturamentoMes;
    private Float lucroMes;

    public Filial(int codigo) {
        Filial objeto = BancoFiliais.getFilial(codigo);
        if (objeto == null) {
            throw new IllegalArgumentException("Filial não encontrada");
        }
        totalCarregamentos++;
        this.codigo = codigo;
        this.nome = objeto.nome;
        this.cidade = objeto.cidade;
        this.faturamentoMes = objeto.faturamentoMes;
        this.lucroMes = objeto.lucroMes;
    }

    public Filial(Integer codigo, String nome, String cidade, Float faturamentoMes, Float lucroMes) {
        this.codigo = codigo;
        this.nome = nome;
        this.cidade = cidade;
        this.faturamentoMes = faturamentoMes;
        this.lucroMes = lucroMes;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public static int getTotalCarregamentos() {
        return totalCarregamentos;
    }

    public static void zerarCarregamentos() {
        totalCarregamentos = 0;
    }

    @Override
    public List<String> obterDadosGerais() {
        return Arrays.asList(this.nome, this.cidade);
    }

    @Override
    public List<Float> obterFaturamento(Usuario usuario) {
        return Arrays.asList(this.faturamentoMes, this.lucroMes);
    }
}
