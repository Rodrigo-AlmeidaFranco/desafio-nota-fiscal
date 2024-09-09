package br.com.itau.geradornotafiscal.service.aliquota;

import br.com.itau.geradornotafiscal.model.ItemNotaFiscal;
import br.com.itau.geradornotafiscal.model.Pedido;
import br.com.itau.geradornotafiscal.model.TipoPessoa;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public interface CalculadoraAliquota {

  double getAliquota(Pedido pedido);

  default List<ItemNotaFiscal> calcularAliquota(Pedido pedido) {
    final double aliquotaPercentual = getAliquota(pedido);
    NumberFormat nf = NumberFormat.getInstance(Locale.US);
    nf.setMaximumFractionDigits(2);
    return pedido.getItens().stream()
        .parallel()
        .map(item -> ItemNotaFiscal.builder()
            .idItem(item.getIdItem())
            .descricao(item.getDescricao())
            .valorUnitario(item.getValorUnitario())
            .quantidade(item.getQuantidade())
            .valorTributoItem(item.getValorUnitario() * aliquotaPercentual)
            .build())
        .collect(Collectors.toList());
  }

  TipoPessoa getTipoPessoa();

}
