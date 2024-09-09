package br.com.itau.geradornotafiscal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "faixa_aliquota")
public class FaixaAliquota {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "tipo_pessoa")
  private String tipoPessoa;

  @Column(name = "valor_minimo")
  private double valorMinimo;

  @Column(name = "regime_tributario")
  private String regimeTributario;

  @Column(name = "valor_maximo")
  private double valorMaximo;
  private double aliquota;
}
