package br.com.itau.geradornotafiscal.service.aliquota;

import static br.com.itau.geradornotafiscal.model.TipoPessoa.JURIDICA;

import br.com.itau.geradornotafiscal.model.Pedido;
import br.com.itau.geradornotafiscal.model.TipoPessoa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AliquotaPessoaJuridica implements CalculadoraAliquota {

  final FaixaAliquotaService faixaAliquotaService;

  @Override
  public double getAliquota(Pedido pedido) {
    return faixaAliquotaService.findAliquotaPessoaJuridica(pedido.getValorTotalItens(), pedido.getDestinatario().getRegimeTributacao());
  }

  @Override
  public TipoPessoa getTipoPessoa() {
    return JURIDICA;
  }
}
