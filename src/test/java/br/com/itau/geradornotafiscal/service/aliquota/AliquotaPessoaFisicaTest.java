package br.com.itau.geradornotafiscal.service.aliquota;

import br.com.itau.geradornotafiscal.mocks.PedidoMock;
import br.com.itau.geradornotafiscal.model.Finalidade;
import br.com.itau.geradornotafiscal.model.Item;
import br.com.itau.geradornotafiscal.model.Pedido;
import br.com.itau.geradornotafiscal.model.Regiao;
import br.com.itau.geradornotafiscal.model.TipoPessoa;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AliquotaPessoaFisicaTest {

  @InjectMocks
  private AliquotaPessoaFisica aliquotaPessoaFisica;

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
    Pedido pedido = PedidoMock.geraPedidoMock(900D, TipoPessoa.FISICA, Regiao.SUDESTE, Finalidade.ENTREGA, List.of(item));
    when(faixaAliquotaService.findAliquotaPessoaFisica(pedido.getValorTotalItens())).thenReturn(response);
    double actualGetAliquotaResult = aliquotaPessoaFisica.getAliquota(pedido);

    assertEquals(response, actualGetAliquotaResult);
    verify(faixaAliquotaService).findAliquotaPessoaFisica(pedido.getValorTotalItens());
  }
}
