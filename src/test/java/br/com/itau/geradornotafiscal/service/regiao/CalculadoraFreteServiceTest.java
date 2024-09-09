package br.com.itau.geradornotafiscal.service.regiao;


import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.itau.geradornotafiscal.mocks.PedidoMock;
import br.com.itau.geradornotafiscal.model.Destinatario;
import br.com.itau.geradornotafiscal.model.Endereco;
import br.com.itau.geradornotafiscal.model.Finalidade;
import br.com.itau.geradornotafiscal.model.Item;
import br.com.itau.geradornotafiscal.model.Pedido;
import br.com.itau.geradornotafiscal.model.RegimeTributacaoPJ;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import br.com.itau.geradornotafiscal.model.Regiao;

public class CalculadoraFreteServiceTest {

  @InjectMocks
  CalculadoraFreteService calculadoraFreteService;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }


  @ParameterizedTest
  @CsvSource(value = {
      "NORTE:1.08:ENTREGA",
      "NORDESTE:1.085:ENTREGA",
      "CENTRO_OESTE:1.07:COBRANCA_ENTREGA",
      "SUDESTE:1.048:COBRANCA_ENTREGA",
      "SUL:1.06:ENTREGA"},
      delimiter = ':')
  void shouldProperlyCalculateFreteQuota(String regiao, double percentualFrete, String finalidade) {
    Item item = PedidoMock.geraItemMock(100D, 1);

    Destinatario destinatario = new Destinatario();
    Endereco endereco = new Endereco();
    endereco.setRegiao(Regiao.valueOf(regiao));
    endereco.setFinalidade(Finalidade.valueOf(finalidade));

    destinatario.setEnderecos(List.of(endereco));
    destinatario.setRegimeTributacao(RegimeTributacaoPJ.SIMPLES_NACIONAL);

    Pedido pedido = PedidoMock.geraPedidoMock(100D, List.of(item),destinatario);

    assertEquals((percentualFrete * pedido.getValorFrete()), calculadoraFreteService.calcularFreteComPercentual(pedido));
  }
}
