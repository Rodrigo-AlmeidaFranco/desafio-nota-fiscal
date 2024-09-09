package br.com.itau.geradornotafiscal.mocks;


import br.com.itau.geradornotafiscal.model.Destinatario;
import br.com.itau.geradornotafiscal.model.Endereco;
import br.com.itau.geradornotafiscal.model.Finalidade;
import br.com.itau.geradornotafiscal.model.Item;
import br.com.itau.geradornotafiscal.model.Pedido;
import br.com.itau.geradornotafiscal.model.Regiao;
import br.com.itau.geradornotafiscal.model.TipoPessoa;
import java.util.Arrays;
import java.util.List;

public class PedidoMock {


  public static Pedido geraPedidoMock(Double valorTotalItens, TipoPessoa tipoPessoa, Regiao regiao,
                                      Finalidade finalidade, List<Item> itens){
    Destinatario destinatario = new Destinatario();
    destinatario.setTipoPessoa(tipoPessoa);

    Endereco endereco = new Endereco();
    endereco.setFinalidade(finalidade);
    endereco.setRegiao(regiao);
    destinatario.setEnderecos(List.of(endereco));

    return geraPedidoMock(valorTotalItens, itens, destinatario);
  }

  public static Pedido geraPedidoMock(Double valorTotalItens, List<Item> itens,
                                      Destinatario destinatario){
    return Pedido.builder()
        .valorTotalItens(valorTotalItens)
        .valorFrete(100)
        .destinatario(destinatario)
        .itens(itens)
        .build();
  }

  public static Item geraItemMock(Double valorUnitario, int quantidade){
    Item item = new Item();
    item.setValorUnitario(valorUnitario);
    item.setQuantidade(quantidade);

    return item;
  }
}
