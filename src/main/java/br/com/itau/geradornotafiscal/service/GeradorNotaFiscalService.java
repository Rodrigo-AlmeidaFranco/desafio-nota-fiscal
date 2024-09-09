package br.com.itau.geradornotafiscal.service;

import br.com.itau.geradornotafiscal.model.ItemNotaFiscal;
import br.com.itau.geradornotafiscal.model.NotaFiscal;
import br.com.itau.geradornotafiscal.model.Pedido;
import br.com.itau.geradornotafiscal.service.impl.EntregaService;
import br.com.itau.geradornotafiscal.service.impl.EstoqueService;
import br.com.itau.geradornotafiscal.service.impl.FinanceiroService;
import br.com.itau.geradornotafiscal.service.impl.RegistroService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface GeradorNotaFiscalService{

	NotaFiscal gerarNotaFiscal(Pedido pedido);

	default NotaFiscal createNotaFiscal(Pedido pedido, double valorFreteComPercentual,
											   List<ItemNotaFiscal> itemNotaFiscalList) {
      return NotaFiscal.builder()
				.idNotaFiscal(UUID.randomUUID().toString())
				.data(LocalDateTime.now())
				.valorTotalItens(pedido.getValorTotalItens())
				.valorFrete(valorFreteComPercentual)
				.itens(itemNotaFiscalList)
				.destinatario(pedido.getDestinatario())
				.build();
	}
	
}
