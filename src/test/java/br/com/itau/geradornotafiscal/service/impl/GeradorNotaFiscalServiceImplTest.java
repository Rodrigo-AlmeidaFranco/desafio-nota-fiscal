package br.com.itau.geradornotafiscal.service.impl;

import br.com.itau.geradornotafiscal.mocks.PedidoMock;
import br.com.itau.geradornotafiscal.model.*;
import br.com.itau.geradornotafiscal.service.aliquota.AliquotaPessoaFisica;
import br.com.itau.geradornotafiscal.service.regiao.CalculadoraFreteService;
import br.com.itau.geradornotafiscal.service.validate.ValidatePedidoService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GeradorNotaFiscalServiceImplTest {

    @InjectMocks
    private GeradorNotaFiscalServiceImpl geradorNotaFiscalService;

    @Mock
    private CalculadoraFreteService calculadoraFreteService;

    @Mock
    private AliquotaPessoaFisica aliquotaPessoaFisica;

    @Mock
    private ValidatePedidoService validatePedidoService;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        geradorNotaFiscalService = new GeradorNotaFiscalServiceImpl(List.of(aliquotaPessoaFisica), calculadoraFreteService, validatePedidoService);
    }


    @Test
    public void shouldGenerateNotaFiscalPessoaFisica() {
        Item item = PedidoMock.geraItemMock(100D, 1);
        Pedido pedido = PedidoMock.geraPedidoMock(100D, TipoPessoa.FISICA, Regiao.SUDESTE, Finalidade.ENTREGA, List.of(item));

        when(aliquotaPessoaFisica.getTipoPessoa()).thenReturn(TipoPessoa.FISICA);
        when(aliquotaPessoaFisica.calcularAliquota(pedido)).thenReturn(List.of(new ItemNotaFiscal()));
        when(validatePedidoService.isPedidoValido(pedido)).thenReturn(true);

        NotaFiscal notaFiscal = geradorNotaFiscalService.gerarNotaFiscal(pedido);

        assertEquals(pedido.getValorTotalItens(), notaFiscal.getValorTotalItens());
        assertEquals(1, notaFiscal.getItens().size());
        assertEquals(0, notaFiscal.getItens().get(0).getValorTributoItem());
        verify(validatePedidoService).isPedidoValido(pedido);
        verify(calculadoraFreteService).calcularFreteComPercentual(pedido);
        verify(aliquotaPessoaFisica).calcularAliquota(pedido);
        verify(aliquotaPessoaFisica).getTipoPessoa();
    }

    @Test
    public void shouldGenerateNotaFiscalPessoaJuridica() {
        Item item = PedidoMock.geraItemMock(100D, 1);
        Pedido pedido = PedidoMock.geraPedidoMock(100D, TipoPessoa.JURIDICA, Regiao.SUDESTE, Finalidade.ENTREGA, List.of(item));

        when(aliquotaPessoaFisica.getTipoPessoa()).thenReturn(TipoPessoa.JURIDICA);
        when(aliquotaPessoaFisica.calcularAliquota(pedido)).thenReturn(List.of(new ItemNotaFiscal()));
        when(validatePedidoService.isPedidoValido(pedido)).thenReturn(true);

        NotaFiscal notaFiscal = geradorNotaFiscalService.gerarNotaFiscal(pedido);

        assertEquals(pedido.getValorTotalItens(), notaFiscal.getValorTotalItens());
        assertEquals(1, notaFiscal.getItens().size());
        assertEquals(0, notaFiscal.getItens().get(0).getValorTributoItem());
        verify(validatePedidoService).isPedidoValido(pedido);
        verify(calculadoraFreteService).calcularFreteComPercentual(pedido);
        verify(aliquotaPessoaFisica).calcularAliquota(pedido);
        verify(aliquotaPessoaFisica).getTipoPessoa();
    }

    @Test
    public void shouldNotGenerateNotaFiscal() {
        Item item = PedidoMock.geraItemMock(100D, 1);
        Pedido pedido = PedidoMock.geraPedidoMock(100D, TipoPessoa.JURIDICA, Regiao.SUDESTE, Finalidade.ENTREGA, List.of(item));

        when(aliquotaPessoaFisica.getTipoPessoa()).thenReturn(TipoPessoa.FISICA);
        when(aliquotaPessoaFisica.calcularAliquota(pedido)).thenReturn(List.of(new ItemNotaFiscal()));
        when(validatePedidoService.isPedidoValido(pedido)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> geradorNotaFiscalService.gerarNotaFiscal(pedido));

        verify(validatePedidoService).isPedidoValido(pedido);
    }

}