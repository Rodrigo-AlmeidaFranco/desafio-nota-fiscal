package br.com.itau.geradornotafiscal.service.aliquota;

import static br.com.itau.geradornotafiscal.model.TipoPessoa.FISICA;

import br.com.itau.geradornotafiscal.model.Pedido;
import br.com.itau.geradornotafiscal.model.TipoPessoa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AliquotaPessoaFisica implements CalculadoraAliquota {

  final FaixaAliquotaService faixaAliquotaService;
  @Override
  public double getAliquota(Pedido pedido) {
    return faixaAliquotaService.findAliquotaPessoaFisica(pedido.getValorTotalItens());
  }

  @Override
  public TipoPessoa getTipoPessoa() {
    return FISICA;
  }
}
