package br.com.itau.geradornotafiscal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.itau.geradornotafiscal.model.RegimeTributacaoPJ;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FaixaAliquotaRepositoryTest {

  @Autowired
  private FaixaAliquotaRepository faixaAliquotaRepository;


  @ParameterizedTest
  @CsvSource(value = {"499:0.0", "501:0.12", "1000:0.12", "2001:0.15", "3000:0.15", "9000:0.17"}, delimiter = ':')
  public void testFindAliquotaPessoaFisica(Double valorTotalItens, Double expectedAliquota) {
    double aliquota = faixaAliquotaRepository.findAliquotaByValorTotalItens(valorTotalItens, "FISICA", "PADRAO");
    assertEquals(expectedAliquota, aliquota);
  }

  @ParameterizedTest
  @CsvSource(value = {"499:0.03:SIMPLES_NACIONAL", "1501:0.07:SIMPLES_NACIONAL", "4220:0.13:SIMPLES_NACIONAL", "22001:0.19:SIMPLES_NACIONAL"}, delimiter = ':')
  public void testFindAliquotaPessoaJuridicaSimples(Double valorTotalItens, Double expectedAliquota, RegimeTributacaoPJ regimeTributacaoPJ) {
    double aliquota = faixaAliquotaRepository.findAliquotaByValorTotalItens(valorTotalItens, "JURIDICA", regimeTributacaoPJ.name());
    assertEquals(expectedAliquota, aliquota);
  }

  @ParameterizedTest
  @CsvSource(value = {"499:0.03:LUCRO_PRESUMIDO", "1501:0.09:LUCRO_PRESUMIDO", "4220:0.16:LUCRO_PRESUMIDO", "22001:0.20:LUCRO_PRESUMIDO"}, delimiter = ':')
  public void testFindAliquotaPessoaJuridicaLucroPresumido(Double valorTotalItens, Double expectedAliquota, RegimeTributacaoPJ regimeTributacaoPJ) {
    double aliquota = faixaAliquotaRepository.findAliquotaByValorTotalItens(valorTotalItens, "JURIDICA", regimeTributacaoPJ.name());
    assertEquals(expectedAliquota, aliquota);
  }

  @ParameterizedTest
  @CsvSource(value = {"499:0.03:LUCRO_REAL", "1501:0.09:LUCRO_REAL", "4220:0.15:LUCRO_REAL", "22001:0.20:LUCRO_REAL"}, delimiter = ':')
  public void testFindAliquotaPessoaJuridicaLucroReal(Double valorTotalItens, Double expectedAliquota, RegimeTributacaoPJ regimeTributacaoPJ) {
    double aliquota = faixaAliquotaRepository.findAliquotaByValorTotalItens(valorTotalItens, "JURIDICA", regimeTributacaoPJ.name());
    assertEquals(expectedAliquota, aliquota);
  }
}
