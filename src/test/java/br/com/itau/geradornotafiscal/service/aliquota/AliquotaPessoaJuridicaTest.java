package br.com.itau.geradornotafiscal.service.aliquota;

import br.com.itau.geradornotafiscal.mocks.PedidoMock;
import br.com.itau.geradornotafiscal.model.Destinatario;
import br.com.itau.geradornotafiscal.model.Item;
import br.com.itau.geradornotafiscal.model.Pedido;
import br.com.itau.geradornotafiscal.model.RegimeTributacaoPJ;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AliquotaPessoaJuridicaTest {

  @InjectMocks
  private AliquotaPessoaJuridica aliquotaPessoaJuridica;

  @Mock
  private FaixaAliquotaService faixaAliquotaService;


  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testGetAliquota() {
    final double response = 0.03;
    Item item = PedidoMock.geraItemMock(100D, 1);
    Destinatario destinatario = new Destinatario();
    destinatario.setRegimeTributacao(RegimeTributacaoPJ.SIMPLES_NACIONAL);
    Pedido pedido = PedidoMock.geraPedidoMock(100D, List.of(item),destinatario);

    when(faixaAliquotaService.findAliquotaPessoaJuridica(pedido.getValorTotalItens(), pedido.getDestinatario().getRegimeTributacao())).thenReturn(response);

    double actualGetAliquotaResult = aliquotaPessoaJuridica.getAliquota(pedido);

    assertEquals(response, actualGetAliquotaResult);
    verify(faixaAliquotaService).findAliquotaPessoaJuridica(pedido.getValorTotalItens(), pedido.getDestinatario().getRegimeTributacao());
  }
}
