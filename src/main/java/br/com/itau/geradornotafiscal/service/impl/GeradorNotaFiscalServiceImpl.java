package br.com.itau.geradornotafiscal.service.impl;

import br.com.itau.geradornotafiscal.model.*;
import br.com.itau.geradornotafiscal.service.GeradorNotaFiscalService;
import br.com.itau.geradornotafiscal.service.aliquota.CalculadoraAliquota;
import br.com.itau.geradornotafiscal.service.regiao.CalculadoraFreteService;
import br.com.itau.geradornotafiscal.service.validate.ValidatePedidoService;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GeradorNotaFiscalServiceImpl implements GeradorNotaFiscalService {

	final List<CalculadoraAliquota> calculadoraAliquotaSet;
	final CalculadoraFreteService calculadoraFreteService;
	final ValidatePedidoService validatePedidoService;

	@Override
	public NotaFiscal gerarNotaFiscal(Pedido pedido) {

		if(!validatePedidoService.isPedidoValido(pedido)) {
			throw new IllegalArgumentException("Pedido inválido");
		}

		Destinatario destinatario = pedido.getDestinatario();

		List<ItemNotaFiscal> itemNotaFiscalList = calculadoraAliquotaSet.stream()
						.filter(calculadoraAliquota -> calculadoraAliquota.getTipoPessoa() == destinatario.getTipoPessoa())
						.findFirst()
						.orElseThrow(() -> new IllegalArgumentException("Tipo de pessoa não encontrado"))
				.calcularAliquota(pedido);

		final NotaFiscal notaFiscal = createNotaFiscal(pedido, calculadoraFreteService.calcularFreteComPercentual(pedido),
				itemNotaFiscalList);
		enviarNotaFiscal(notaFiscal);
		return notaFiscal;
	}

	private void enviarNotaFiscal(NotaFiscal notaFiscal) {
		CompletableFuture<Void> estoqueFuture = CompletableFuture.runAsync(() ->
				new EstoqueService().enviarNotaFiscalParaBaixaEstoque(notaFiscal)
		);
		CompletableFuture<Void> registroFuture = CompletableFuture.runAsync(() ->
				new RegistroService().registrarNotaFiscal(notaFiscal)
		);
		CompletableFuture<Void> entregaFuture = CompletableFuture.runAsync(() ->
				new EntregaService().agendarEntrega(notaFiscal)
		);
		CompletableFuture<Void> financeiroFuture = CompletableFuture.runAsync(() ->
				new FinanceiroService().enviarNotaFiscalParaContasReceber(notaFiscal)
		);
		CompletableFuture<Void> allOf = CompletableFuture.allOf(
				estoqueFuture, registroFuture, entregaFuture, financeiroFuture
		);
		allOf.join();
	}
}