package br.com.itau.geradornotafiscal.service.aliquota;

import br.com.itau.geradornotafiscal.model.RegimeTributacaoPJ;
import br.com.itau.geradornotafiscal.model.TipoPessoa;
import br.com.itau.geradornotafiscal.repository.FaixaAliquotaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FaixaAliquotaService {

  private final FaixaAliquotaRepository repository;


  public double findAliquotaPessoaFisica(double valorTotalItens) {
    return repository.findAliquotaByValorTotalItens(valorTotalItens, TipoPessoa.FISICA.name(), "PADRAO");
  }

  public double findAliquotaPessoaJuridica(double valorTotalItens, RegimeTributacaoPJ regimeTributacaoPJ) {
    return repository.findAliquotaByValorTotalItens(valorTotalItens,TipoPessoa.JURIDICA.name(), regimeTributacaoPJ.name());
  }
}
