package br.com.itau.geradornotafiscal.service.regiao;


public enum RegiaoTaxaFreteEnum {

  NORTE(1.08),
  NORDESTE(1.085),
  CENTRO_OESTE(1.07),
  SUDESTE(1.048),
  SUL(1.06);

  private final double percentualFrete;

  RegiaoTaxaFreteEnum(double percentualFrete) {
    this.percentualFrete = percentualFrete;
  }

  public double calcularFrete(double valorFrete) {
    return valorFrete * this.percentualFrete;
  }
}
