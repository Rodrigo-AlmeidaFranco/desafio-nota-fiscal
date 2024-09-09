package br.com.itau.geradornotafiscal.service.regiao;

import static br.com.itau.geradornotafiscal.model.Finalidade.COBRANCA_ENTREGA;
import static br.com.itau.geradornotafiscal.model.Finalidade.ENTREGA;

import br.com.itau.geradornotafiscal.model.Destinatario;
import br.com.itau.geradornotafiscal.model.Pedido;
import br.com.itau.geradornotafiscal.model.Endereco;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class CalculadoraFreteService {

  public double calcularFreteComPercentual(Pedido pedido) {
    RegiaoTaxaFreteEnum regiaoTaxaFreteEnum = obterRegiaoEntrega(pedido.getDestinatario()).orElseThrow(() -> new IllegalArgumentException("Região de entrega não encontrada."));
    return regiaoTaxaFreteEnum.calcularFrete(pedido.getValorFrete());
  }

  private Optional<RegiaoTaxaFreteEnum> obterRegiaoEntrega(Destinatario destinatario) {
    return destinatario.getEnderecos().stream()
        .filter(endereco -> endereco.getFinalidade() == ENTREGA || endereco.getFinalidade() == COBRANCA_ENTREGA)
        .map(Endereco::getRegiao)
        .map(regiao -> RegiaoTaxaFreteEnum.valueOf(regiao.name()))
        .findFirst();
  }
}
