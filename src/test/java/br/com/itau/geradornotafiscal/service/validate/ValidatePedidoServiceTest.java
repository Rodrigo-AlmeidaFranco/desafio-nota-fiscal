package br.com.itau.geradornotafiscal.service.validate;

import static br.com.itau.geradornotafiscal.model.TipoPessoa.FISICA;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import br.com.itau.geradornotafiscal.mocks.PedidoMock;
import br.com.itau.geradornotafiscal.model.Destinatario;
import br.com.itau.geradornotafiscal.model.Item;
import br.com.itau.geradornotafiscal.model.Pedido;
import br.com.itau.geradornotafiscal.service.impl.GeradorNotaFiscalServiceImpl;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class ValidatePedidoServiceTest {

  @InjectMocks
  ValidatePedidoService validatePedidoService;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldReturnFalseWhenPedidoIsInvalidEmptyItemList() {
    Destinatario destinatario = new Destinatario();
    destinatario.setTipoPessoa(FISICA);
    Pedido pedido = PedidoMock.geraPedidoMock(100D, Collections.emptyList(), destinatario);

    assertFalse(validatePedidoService.isPedidoValido(pedido));
  }

  @Test
  void shouldReturnFalseWhenPedidoIsInvalidDestinatarioNull() {
    Pedido pedido = PedidoMock.geraPedidoMock(100D,List.of(new Item()), null);

    assertFalse(validatePedidoService.isPedidoValido(pedido));
  }

  @Test
  void shouldReturnFalseWhenPedidoIsInvalidTotalValueNoEqual() {
    Destinatario destinatario = new Destinatario();
    destinatario.setTipoPessoa(FISICA);

    Item item = PedidoMock.geraItemMock(100D, 2);
    Pedido pedido = PedidoMock.geraPedidoMock(100D, List.of(item), destinatario);

    assertFalse(validatePedidoService.isPedidoValido(pedido));
  }

  @Test
  void shouldReturnTrueWhenPedidoIsValid() {
    Destinatario destinatario = new Destinatario();
    destinatario.setTipoPessoa(FISICA);

    Item item = PedidoMock.geraItemMock(100D, 1);
    Pedido pedido = PedidoMock.geraPedidoMock(100D, List.of(item), destinatario);

    assertTrue(validatePedidoService.isPedidoValido(pedido));
  }
}
