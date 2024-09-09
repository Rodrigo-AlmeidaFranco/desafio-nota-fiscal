package br.com.itau.geradornotafiscal.repository;

import br.com.itau.geradornotafiscal.model.FaixaAliquota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FaixaAliquotaRepository  extends JpaRepository<FaixaAliquota, Long> {

  @Query("SELECT f.aliquota FROM FaixaAliquota f WHERE :valorTotalItens BETWEEN f.valorMinimo AND f.valorMaximo AND f.tipoPessoa = :tipoPessoa AND f.regimeTributario = :regimeTributario")
  Double findAliquotaByValorTotalItens(
      @Param("valorTotalItens") double valorTotalItens,
      @Param("tipoPessoa") String tipoPessoa,
      @Param("regimeTributario") String regimeTributario
  );
}
