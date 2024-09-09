-- Faixas para Pessoa Jurídica - Simples Nacional
INSERT INTO faixa_aliquota (tipo_pessoa, regime_tributario, valor_minimo, valor_maximo, aliquota) VALUES
('JURIDICA', 'SIMPLES_NACIONAL', 0, 1000, 0.03),
('JURIDICA', 'SIMPLES_NACIONAL', 1001, 2000, 0.07),
('JURIDICA', 'SIMPLES_NACIONAL', 2001, 5000, 0.13),
('JURIDICA', 'SIMPLES_NACIONAL', 5001, 99999999, 0.19);

-- Faixas para Pessoa Jurídica - Lucro Real
INSERT INTO faixa_aliquota (tipo_pessoa, regime_tributario, valor_minimo, valor_maximo, aliquota) VALUES
('JURIDICA', 'LUCRO_REAL', 0, 1000, 0.03),
('JURIDICA', 'LUCRO_REAL', 1001, 2000, 0.09),
('JURIDICA', 'LUCRO_REAL', 2001, 5000, 0.15),
('JURIDICA', 'LUCRO_REAL', 5001, 99999999, 0.20);

-- Faixas para Pessoa Jurídica - Lucro Presumido
INSERT INTO faixa_aliquota (tipo_pessoa, regime_tributario, valor_minimo, valor_maximo, aliquota) VALUES
 ('JURIDICA', 'LUCRO_PRESUMIDO', 0, 1000, 0.03),
 ('JURIDICA', 'LUCRO_PRESUMIDO', 1001, 2000, 0.09),
 ('JURIDICA', 'LUCRO_PRESUMIDO', 2001, 5000, 0.16),
 ('JURIDICA', 'LUCRO_PRESUMIDO', 5001, 99999999, 0.20);

-- Faixas para Pessoa Física
INSERT INTO faixa_aliquota (tipo_pessoa, regime_tributario, valor_minimo, valor_maximo, aliquota) VALUES
('FISICA', 'PADRAO', 0.0, 500.0, 0.0),
('FISICA', 'PADRAO', 500.01, 2000.0, 0.12),
('FISICA', 'PADRAO', 2000.01, 3500.0, 0.15),
('FISICA', 'PADRAO', 3500.01, 99999999, 0.17);