package org.example;

import java.util.List;

public class FilialProxy implements IFilial {

    private Filial filial;

    private Integer codigo;

    public FilialProxy(Integer codigo) {
        this.codigo = codigo;
    }

    @Override
    public List<String> obterDadosGerais() {
        if (this.filial == null) {
            this.filial = new Filial(this.codigo);
        }
        return this.filial.obterDadosGerais();
    }

    @Override
    public List<Float> obterFaturamento(Usuario usuario) {
        if (!usuario.isAdministrador()) {
            throw new IllegalArgumentException("Usuário não autorizado");
        }
        if (this.filial == null) {
            this.filial = new Filial(this.codigo);
        }
        return this.filial.obterFaturamento(usuario);
    }
}
