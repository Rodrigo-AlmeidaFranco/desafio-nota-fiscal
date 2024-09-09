package br.com.itau.geradornotafiscal.service.validate;

import br.com.itau.geradornotafiscal.model.Pedido;
import org.springframework.stereotype.Service;

@Service
public class ValidatePedidoService {

  public boolean isPedidoValido(Pedido pedido) {
    return !pedido.getItens().isEmpty() &&
        pedido.getDestinatario() != null &&
        pedido.getValorTotalItens() == pedido.getItens()
            .stream()
            .mapToDouble(item -> item.getValorUnitario() * item.getQuantidade())
            .sum();
  }
}
