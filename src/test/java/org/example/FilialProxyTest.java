package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FilialProxyTest {

    @BeforeEach
    void setUp() {
        BancoFiliais.addFilial(new Filial(1, "Centro", "Juiz de Fora", 10000.0f, 2500.0f));
        BancoFiliais.addFilial(new Filial(2, "São Mateus", "Juiz de Fora", 12500.0f, 3000.0f));
        Filial.zerarCarregamentos();
    }

    @Test
    void deveRetornarDadosGeraisFilial() {
        FilialProxy filial = new FilialProxy(1);

        assertEquals(Arrays.asList("Centro", "Juiz de Fora"), filial.obterDadosGerais());
    }

    @Test
    void deveRetornarFaturamentoFilial() {
        Usuario usuario = new Usuario("Ana", true);
        FilialProxy filial = new FilialProxy(2);

        assertEquals(Arrays.asList(12500.0f, 3000.0f), filial.obterFaturamento(usuario));
    }

    @Test
    void deveRetornarExcecaoUsuarioNaoAutorizadoConsultarFaturamentoFilial() {
        try {
            Usuario usuario = new Usuario("Joana", false);
            FilialProxy filial = new FilialProxy(2);

            filial.obterFaturamento(usuario);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Usuário não autorizado", e.getMessage());
        }
    }

    @Test
    void naoDeveCarregarFilialAoCriarOProxy() {
        new FilialProxy(1);

        assertEquals(0, Filial.getTotalCarregamentos());
    }

    @Test
    void deveCarregarFilialSomenteQuandoNecessario() {
        FilialProxy filial = new FilialProxy(1);
        assertEquals(0, Filial.getTotalCarregamentos());

        filial.obterDadosGerais();

        assertEquals(1, Filial.getTotalCarregamentos());
    }

    @Test
    void deveCarregarFilialUmaUnicaVez() {
        Usuario usuario = new Usuario("Ana", true);
        FilialProxy filial = new FilialProxy(1);

        filial.obterDadosGerais();
        filial.obterDadosGerais();
        filial.obterFaturamento(usuario);

        assertEquals(1, Filial.getTotalCarregamentos());
    }

    @Test
    void naoDeveCarregarFilialQuandoUsuarioNaoAutorizado() {
        FilialProxy filial = new FilialProxy(1);
        try {
            filial.obterFaturamento(new Usuario("Joana", false));
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals(0, Filial.getTotalCarregamentos());
        }
    }

    @Test
    void deveRetornarExcecaoFilialInexistente() {
        try {
            FilialProxy filial = new FilialProxy(99);
            filial.obterDadosGerais();
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Filial não encontrada", e.getMessage());
        }
    }
}
