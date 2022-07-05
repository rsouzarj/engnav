/*     */ package Financeiro;
/*     */ 
/*     */ import ClausulaSQL.ClausulaWhere;
/*     */ import FinanceiroItem.FinanceiroItem;
/*     */ import FinanceiroItem.FinanceiroItemService;
/*     */ import Util.Conexao;
/*     */ import Util.Sequence;
/*     */ import java.io.PrintStream;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;

/*     */ public class FinanceiroDAO
/*     */ {
/*     */   public Financeiro inserir(Financeiro financeiro)
/*     */   {
/*     */     try
/*     */     {
/*  32 */       String seq = Sequence.buscarNumeroSequence("SEQ_FINANCEIRO");
/*  33 */       financeiro.setSeqFinanceiro(seq);
/*  34 */       Conexao conexao = new Conexao();
/*  35 */       Connection conn = Conexao.getConnection();
/*  36 */       String sql = "insert into FINANCEIRO (SEQ_FINANCEIRO,SEQ_EMPRESA,DATA_CADASTRO,SEQ_TIPO_MOVIMENTO,SEQ_PARCEIRO,SEQ_DOCUMENTO,SEQ_COLABORADOR,DATA_LANCAMENTO,DATA_VENCIMENTO,DATA_PAGAMENTO,MOEDA_ORIGEM,MOEDA_DESTINO,VALOR,VALOR_PAGAMENTO,SEQ_TIPO_DOCUMENTO,OPERACAO,seq_usuario,descricao,seq_unidade_negocio,origem_parceiro,data_emissao,RETENCAO_ISSQN,SEQ_ALIQUOTA_ISSQN,SEQ_ALIQUOTA_IRRF, SEQ_ALIQUOTA_PIS, SEQ_ALIQUOTA_CSLL,SEQ_ALIQUOTA_COFINS,seq_documento_fiscal,seq_centro_custo,numero_doc_fiscal,origem_lcm,DESCRICAO_SERVICO_PRESTADO,CODIGO_FISCAL_MUNICIPAL,OUTRAS_INFORMACOES,valor_multa,valor_desconto,valor_recebido,valor_juros,tarifa_bancaria,seq_forma_pagamento,status,tipo_despesa,seq_condicao_pagamento,seq_parceiro_contato,seq_conta,outros_acrescimos,taxa_cambio,chk_cambio,ocorrencias,parcela_inicio,parcela_fim,intervalo_numero,intervalo_tempo,indefinidamente,motivo_cancelamento,chk_repeticao,responsavel_quitacao,seq_financeiro_ordem_pagamento,valor_convertido,seq_financeiro_faturamento,seq_aliquota_codigo_fiscal,seq_conta_destino,seq_nv_embarcacao,seq_equipamento,local_servico,valor_total_movimentacao,data_periodo_inicio,data_periodo_final,seq_material,fatura,valor_parcial,tipo_quitacao,DATAPERIODO_INICIALRLBM,DATAPERIODO_FINALRLBM) values  (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  43 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*  45 */       ps.setString(1, financeiro.getSeqFinanceiro());
/*  46 */       ps.setString(2, financeiro.getSeqEmpresa());
/*     */       try {
/*  48 */         ps.setDate(3, new java.sql.Date(financeiro.getDataCadastro().getTime()));
/*     */       } catch (NullPointerException e) {
/*  50 */         ps.setDate(3, null);
/*     */       }
/*  52 */       ps.setString(4, financeiro.getSeqTipoMovimento());
/*  53 */       ps.setString(5, financeiro.getSeqParceiro());
/*  54 */       ps.setString(6, financeiro.getSeqDocumento());
/*  55 */       ps.setString(7, financeiro.getSeqColaborador());
/*     */       try {
/*  57 */         ps.setDate(8, new java.sql.Date(financeiro.getDataLancamento().getTime()));
/*     */       } catch (NullPointerException e) {
/*  59 */         ps.setDate(8, null);
/*     */       }
/*     */       try {
/*  62 */         ps.setDate(9, new java.sql.Date(financeiro.getDataVencimento().getTime()));
/*     */       } catch (NullPointerException e) {
/*  64 */         ps.setDate(9, null);
/*     */       }
/*     */       try {
/*  67 */         ps.setDate(10, new java.sql.Date(financeiro.getDataPagamento().getTime()));
/*     */       } catch (NullPointerException e) {
/*  69 */         ps.setDate(10, null);
/*     */       }
/*  71 */       ps.setString(11, financeiro.getMoedaOrigem());
/*  72 */       ps.setString(12, financeiro.getMoedaDestino());
/*  73 */       ps.setBigDecimal(13, financeiro.getValor());
/*  74 */       ps.setBigDecimal(14, financeiro.getValorPagamento());
/*  75 */       ps.setString(15, financeiro.getSeqTipoDocumento());
/*  76 */       ps.setString(16, financeiro.getOperacao());
/*  77 */       ps.setString(17, financeiro.getSeqUsuario());
/*  78 */       ps.setString(18, financeiro.getDescricao());
/*  79 */       ps.setString(19, financeiro.getSeqUnidadeNegocio());
/*  80 */       ps.setString(20, financeiro.getOrigemParceiro());
/*     */       try {
/*  82 */         ps.setDate(21, new java.sql.Date(financeiro.getDataEmissao().getTime()));
/*     */       } catch (NullPointerException e) {
/*  84 */         ps.setDate(21, null);
/*     */       }
/*  86 */       ps.setBigDecimal(22, financeiro.getRetencaoISSQN());
/*  87 */       ps.setString(23, financeiro.getSeqAliquotaISSQN());
/*  88 */       ps.setString(24, financeiro.getSeqAliquotaIRRF());
/*  89 */       ps.setString(25, financeiro.getSeqAliquotaPIS());
/*  90 */       ps.setString(26, financeiro.getSeqAliquotaCSLL());
/*  91 */       ps.setString(27, financeiro.getSeqAliquotaCOFINS());
/*  92 */       ps.setString(28, financeiro.getSeqDocumentoFiscal());
/*  93 */       ps.setString(29, financeiro.getSeqCentroCusto());
/*  94 */       ps.setString(30, financeiro.getNumeroDocFiscal());
/*  95 */       ps.setString(31, financeiro.getOrigemLCM());
/*  96 */       ps.setString(32, financeiro.getDescricaoServicosPrestados());
/*  97 */       ps.setString(33, financeiro.getCodigoFiscalMunicipal());
/*  98 */       ps.setString(34, financeiro.getOutrasInformacoes());
/*  99 */       ps.setBigDecimal(35, financeiro.getValorMulta());
/* 100 */       ps.setBigDecimal(36, financeiro.getValorDesconto());
/* 101 */       ps.setBigDecimal(37, financeiro.getValorRecebido());
/* 102 */       ps.setBigDecimal(38, financeiro.getValorJuros());
/* 103 */       ps.setBigDecimal(39, financeiro.getTarifaBancaria());
/* 104 */       ps.setString(40, financeiro.getSeqFormaPagamento());
/* 105 */       ps.setString(41, financeiro.getEtapa());
/* 106 */       ps.setString(42, financeiro.getTipoDespesa());
/* 107 */       ps.setString(43, financeiro.getSeqCondicaoPagamento());
/* 108 */       ps.setString(44, financeiro.getSeqParceiroContato());
/* 109 */       ps.setString(45, financeiro.getSeqConta());
/* 110 */       ps.setBigDecimal(46, financeiro.getOutrosAcrescimos());
/* 111 */       ps.setBigDecimal(47, financeiro.getTaxaCambio());
/* 112 */       ps.setBoolean(48, financeiro.isChkTaxaCambio());
/* 113 */       ps.setInt(49, financeiro.getOcorrencias().intValue());
/* 114 */       ps.setInt(50, financeiro.getParcelaInicio().intValue());
/* 115 */       ps.setInt(51, financeiro.getParcelaFim().intValue());
/* 116 */       ps.setInt(52, financeiro.getIntervaloNumero().intValue());
/* 117 */       ps.setString(53, financeiro.getIntervaloTempo());
/* 118 */       ps.setString(54, financeiro.getIndefinidamente());
/* 119 */       ps.setString(55, financeiro.getMotivoCancelamento());
/* 120 */       ps.setBoolean(56, financeiro.isChkRepeticao());
/* 121 */       ps.setString(57, financeiro.getResponsavelQuitacao());
/* 122 */       ps.setString(58, financeiro.getSeqFinanceiroOrdemPagamento());
/* 123 */       ps.setBigDecimal(59, financeiro.getValorConvertido());
/* 124 */       ps.setString(60, financeiro.getSeqFinanceiroFaturamento());
/* 125 */       ps.setString(61, financeiro.getSeqAliquotaCodigoFiscal());
/* 126 */       ps.setString(62, financeiro.getSeqContaDestino());
/* 127 */       ps.setString(63, financeiro.getSeqNvEmbarcacao());
/* 128 */       ps.setString(64, financeiro.getSeqEquipamento());
/* 129 */       ps.setString(65, financeiro.getLocalServico());
/* 130 */       ps.setBigDecimal(66, financeiro.getValorTotalMovimentacao());
/*     */       try {
/* 132 */         ps.setDate(67, new java.sql.Date(financeiro.getDataPeriodoInicio().getTime()));
/*     */       } catch (NullPointerException e) {
/* 134 */         ps.setDate(67, null);
/*     */       }
/*     */       try {
/* 137 */         ps.setDate(68, new java.sql.Date(financeiro.getDataPeriodoFinal().getTime()));
/*     */       } catch (NullPointerException e) {
/* 139 */         ps.setDate(68, null);
/*     */       }
/* 141 */       ps.setString(69, financeiro.getSeqMaterial());
/* 142 */       ps.setString(70, financeiro.getFatura());
/* 143 */       ps.setBigDecimal(71, financeiro.getValorParcial());
/* 144 */       ps.setString(72, financeiro.getTipoQuitacao());
/*     */       try {
/* 146 */         ps.setDate(73, new java.sql.Date(financeiro.getDataPeriodoInicialRLBM().getTime()));
/*     */       } catch (NullPointerException e) {
/* 148 */         ps.setDate(73, null);
/*     */       }
/*     */       try {
/* 151 */         ps.setDate(74, new java.sql.Date(financeiro.getDataPeriodoFinalRLBM().getTime()));
/*     */       } catch (NullPointerException e) {
/* 153 */         ps.setDate(74, null);
/*     */       }
/* 155 */       ps.execute();
/* 156 */       ps.close();
/*     */       
/*     */ 
/* 159 */       FinanceiroItemService financeiroItemService = new FinanceiroItemService();
/* 160 */       for (FinanceiroItem item : financeiro.getListaFinanceiroItem()) {
/* 161 */         item.setSeqFinanceiroItem(null);
/* 162 */         item.setSeqEmpresa(financeiro.getSeqEmpresa());
/* 163 */         item.setSeqFinanceiro(financeiro.getSeqFinanceiro());
/* 164 */         financeiroItemService.salvar(item);
/*     */       }
/*     */     }
/*     */     catch (SQLException ex)
/*     */     {
/*     */       FinanceiroItemService financeiroItemService;
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 177 */       Logger.getLogger(FinanceiroDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 178 */       System.out.println(ex.getMessage());
/*     */     }
/* 180 */     return financeiro;
/*     */   }
/*     */   
/*     */   public Financeiro alterar(Financeiro financeiro) {
/*     */     try {
/* 185 */       Conexao conexao = new Conexao();
/* 186 */       Connection conn = Conexao.getConnection();
/* 187 */       String sql = "update FINANCEIRO set SEQ_EMPRESA = ?,DATA_CADASTRO = ?,SEQ_TIPO_MOVIMENTO = ?,SEQ_PARCEIRO = ?,SEQ_DOCUMENTO = ?,SEQ_COLABORADOR = ?,DATA_LANCAMENTO = ?,DATA_VENCIMENTO = ?,DATA_PAGAMENTO = ?,MOEDA_ORIGEM = ?,MOEDA_DESTINO = ?,VALOR = ?,VALOR_PAGAMENTO = ?,SEQ_TIPO_DOCUMENTO = ?,OPERACAO = ?, seq_usuario = ? , descricao = ?, seq_unidade_negocio = ?, origem_parceiro = ?, data_emissao = ?, RETENCAO_ISSQN = ?, SEQ_ALIQUOTA_ISSQN = ?,SEQ_ALIQUOTA_IRRF = ?, SEQ_ALIQUOTA_PIS = ?, SEQ_ALIQUOTA_CSLL = ?,SEQ_ALIQUOTA_COFINS =?, seq_documento_fiscal = ?, seq_centro_custo = ?, numero_doc_fiscal = ?, origem_lcm = ?, DESCRICAO_SERVICO_PRESTADO = ?, CODIGO_FISCAL_MUNICIPAL = ?, OUTRAS_INFORMACOES = ?,valor_multa = ? ,valor_desconto = ?,valor_recebido = ?,valor_juros = ?,tarifa_bancaria = ?,seq_forma_pagamento = ?, status = ?, tipo_despesa = ?,seq_condicao_pagamento =?,seq_parceiro_contato = ?, seq_conta = ?, outros_acrescimos = ?,taxa_cambio = ?, chk_cambio = ?, ocorrencias = ?,parcela_inicio = ?,parcela_fim = ?, intervalo_numero = ?,intervalo_tempo = ?,indefinidamente = ?,motivo_cancelamento = ?, chk_repeticao = ?, responsavel_quitacao = ?, seq_financeiro_ordem_pagamento = ?,valor_convertido = ?, seq_financeiro_faturamento = ?, seq_aliquota_codigo_fiscal = ?, seq_conta_destino = ?,seq_nv_embarcacao = ?,seq_equipamento = ?,local_servico =?, valor_total_movimentacao = ?, data_periodo_inicio = ?,data_periodo_final = ?,seq_material=?, fatura = ?, valor_parcial = ?, tipo_quitacao = ?, DATAPERIODO_INICIALRLBM = ?, DATAPERIODO_FINALRLBM = ?, APROVADOR = ? where SEQ_FINANCEIRO = ?";
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 192 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/* 194 */       ps.setString(1, financeiro.getSeqEmpresa());
/*     */       try {
/* 196 */         ps.setDate(2, new java.sql.Date(financeiro.getDataCadastro().getTime()));
/*     */       } catch (NullPointerException e) {
/* 198 */         ps.setDate(2, null);
/*     */       }
/* 200 */       ps.setString(3, financeiro.getSeqTipoMovimento());
/* 201 */       ps.setString(4, financeiro.getSeqParceiro());
/* 202 */       ps.setString(5, financeiro.getSeqDocumento());
/* 203 */       ps.setString(6, financeiro.getSeqColaborador());
/*     */       try {
/* 205 */         ps.setDate(7, new java.sql.Date(financeiro.getDataLancamento().getTime()));
/*     */       } catch (NullPointerException e) {
/* 207 */         ps.setDate(7, null);
/*     */       }
/*     */       try {
/* 210 */         ps.setDate(8, new java.sql.Date(financeiro.getDataVencimento().getTime()));
/*     */       } catch (NullPointerException e) {
/* 212 */         ps.setDate(8, null);
/*     */       }
/*     */       try {
/* 215 */         ps.setDate(9, new java.sql.Date(financeiro.getDataPagamento().getTime()));
/*     */       } catch (NullPointerException e) {
/* 217 */         ps.setDate(9, null);
/*     */       }
/* 219 */       ps.setString(10, financeiro.getMoedaOrigem());
/* 220 */       ps.setString(11, financeiro.getMoedaDestino());
/* 221 */       ps.setBigDecimal(12, financeiro.getValor());
/* 222 */       ps.setBigDecimal(13, financeiro.getValorPagamento());
/* 223 */       ps.setString(14, financeiro.getSeqTipoDocumento());
/* 224 */       ps.setString(15, financeiro.getOperacao());
/* 225 */       ps.setString(16, financeiro.getSeqUsuario());
/* 226 */       ps.setString(17, financeiro.getDescricao());
/* 227 */       ps.setString(18, financeiro.getSeqUnidadeNegocio());
/* 228 */       ps.setString(19, financeiro.getOrigemParceiro());
/*     */       try {
/* 230 */         ps.setDate(20, new java.sql.Date(financeiro.getDataEmissao().getTime()));
/*     */       } catch (NullPointerException e) {
/* 232 */         ps.setDate(20, null);
/*     */       }
/* 234 */       ps.setBigDecimal(21, financeiro.getRetencaoISSQN());
/* 235 */       ps.setString(22, financeiro.getSeqAliquotaISSQN());
/* 236 */       ps.setString(23, financeiro.getSeqAliquotaIRRF());
/* 237 */       ps.setString(24, financeiro.getSeqAliquotaPIS());
/* 238 */       ps.setString(25, financeiro.getSeqAliquotaCSLL());
/* 239 */       ps.setString(26, financeiro.getSeqAliquotaCOFINS());
/* 240 */       ps.setString(27, financeiro.getSeqDocumentoFiscal());
/* 241 */       ps.setString(28, financeiro.getSeqCentroCusto());
/* 242 */       ps.setString(29, financeiro.getNumeroDocFiscal());
/* 243 */       ps.setString(30, financeiro.getOrigemLCM());
/* 244 */       ps.setString(31, financeiro.getDescricaoServicosPrestados());
/* 245 */       ps.setString(32, financeiro.getCodigoFiscalMunicipal());
/* 246 */       ps.setString(33, financeiro.getOutrasInformacoes());
/* 247 */       ps.setBigDecimal(34, financeiro.getValorMulta());
/* 248 */       ps.setBigDecimal(35, financeiro.getValorDesconto());
/* 249 */       ps.setBigDecimal(36, financeiro.getValorRecebido());
/* 250 */       ps.setBigDecimal(37, financeiro.getValorJuros());
/* 251 */       ps.setBigDecimal(38, financeiro.getTarifaBancaria());
/* 252 */       ps.setString(39, financeiro.getSeqFormaPagamento());
/* 253 */       ps.setString(40, financeiro.getEtapa());
/* 254 */       ps.setString(41, financeiro.getTipoDespesa());
/* 255 */       ps.setString(42, financeiro.getSeqCondicaoPagamento());
/* 256 */       ps.setString(43, financeiro.getSeqParceiroContato());
/* 257 */       ps.setString(44, financeiro.getSeqConta());
/* 258 */       ps.setBigDecimal(45, financeiro.getOutrosAcrescimos());
/* 259 */       ps.setBigDecimal(46, financeiro.getTaxaCambio());
/* 260 */       ps.setBoolean(47, financeiro.isChkTaxaCambio());
/* 261 */       ps.setInt(48, financeiro.getOcorrencias().intValue());
/* 262 */       ps.setInt(49, financeiro.getParcelaInicio().intValue());
/* 263 */       ps.setInt(50, financeiro.getParcelaFim().intValue());
/* 264 */       ps.setInt(51, financeiro.getIntervaloNumero().intValue());
/* 265 */       ps.setString(52, financeiro.getIntervaloTempo());
/* 266 */       ps.setString(53, financeiro.getIndefinidamente());
/* 267 */       ps.setString(54, financeiro.getMotivoCancelamento());
/* 268 */       ps.setBoolean(55, financeiro.isChkRepeticao());
/* 269 */       ps.setString(56, financeiro.getResponsavelQuitacao());
/* 270 */       ps.setString(57, financeiro.getSeqFinanceiroOrdemPagamento());
/* 271 */       ps.setBigDecimal(58, financeiro.getValorConvertido());
/* 272 */       ps.setString(59, financeiro.getSeqFinanceiroFaturamento());
/* 273 */       ps.setString(60, financeiro.getSeqAliquotaCodigoFiscal());
/* 274 */       ps.setString(61, financeiro.getSeqContaDestino());
/* 275 */       ps.setString(62, financeiro.getSeqNvEmbarcacao());
/* 276 */       ps.setString(63, financeiro.getSeqEquipamento());
/* 277 */       ps.setString(64, financeiro.getLocalServico());
/* 278 */       ps.setBigDecimal(65, financeiro.getValorTotalMovimentacao());
/*     */       try {
/* 280 */         ps.setDate(66, new java.sql.Date(financeiro.getDataPeriodoInicio().getTime()));
/*     */       } catch (NullPointerException e) {
/* 282 */         ps.setDate(66, null);
/*     */       }
/*     */       try {
/* 285 */         ps.setDate(67, new java.sql.Date(financeiro.getDataPeriodoFinal().getTime()));
/*     */       } catch (NullPointerException e) {
/* 287 */         ps.setDate(67, null);
/*     */       }
/* 289 */       ps.setString(68, financeiro.getSeqMaterial());
/* 290 */       ps.setString(69, financeiro.getFatura());
/* 291 */       ps.setBigDecimal(70, financeiro.getValorParcial());
/* 292 */       ps.setString(71, financeiro.getTipoQuitacao());
/*     */       try {
/* 294 */         ps.setDate(72, new java.sql.Date(financeiro.getDataPeriodoInicialRLBM().getTime()));
/*     */       } catch (NullPointerException e) {
/* 296 */         ps.setDate(72, null);
/*     */       }
/*     */       try {
/* 299 */         ps.setDate(73, new java.sql.Date(financeiro.getDataPeriodoFinalRLBM().getTime()));
/*     */       } catch (NullPointerException e) {
/* 301 */         ps.setDate(73, null);
/*     */       }
/* 303 */       ps.setString(74, financeiro.getAprovador());
/* 303 */       ps.setString(75, financeiro.getSeqFinanceiro());
/* 304 */       ps.execute();
/* 305 */       ps.close();
/*     */       
/*     */ 
/* 308 */       FinanceiroItemService financeiroItemService = new FinanceiroItemService();
/* 309 */       for (FinanceiroItem item : financeiro.getListaFinanceiroItem()) {
/* 310 */         item.setSeqEmpresa(financeiro.getSeqEmpresa());
/* 311 */         item.setSeqFinanceiro(financeiro.getSeqFinanceiro());
/* 312 */         financeiroItemService.salvar(item);
/*     */       }
/*     */       
/* 315 */       List<FinanceiroItem> listaMaterialOriginal = financeiroItemService.listarPorSeqFinanceiro(financeiro.getSeqFinanceiro());
/* 316 */       for (FinanceiroItem itemOriginal : listaMaterialOriginal) {
/* 317 */         boolean encontrou = false;
/* 318 */         for (FinanceiroItem item : financeiro.getListaFinanceiroItem()) {
/* 319 */           if (itemOriginal.getSeqFinanceiroItem().equals(item.getSeqFinanceiroItem())) {
/* 320 */             encontrou = true;
/*     */           }
/*     */         }
/* 323 */         if (!encontrou) {
/* 324 */           financeiroItemService.deletar(itemOriginal);
/*     */         }
/*     */       }
/*     */     } catch (SQLException ex) {
/*     */       FinanceiroItemService financeiroItemService;
/* 329 */       Logger.getLogger(FinanceiroDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 330 */       System.out.println(ex.getMessage());
/*     */     }
/*     */     
/* 333 */     return financeiro;
/*     */   }
/*     */   
/*     */   public List<Financeiro> listar(ClausulaWhere sClausula) {
/*     */     try {
/* 338 */       Conexao conexao = new Conexao();
/* 339 */       Connection conn = Conexao.getConnection();
/* 340 */       String sql = "SELECT financeiro.*,usuario.usuario,\n parceiro.nome parceiro, \ntipo_movimento_financeiro.nome tipo_movimento,\nunidade_negocio.nome unidade_negocio_razao,\nfinanceiro_categoria.nome nomeCategoria,\ndocumento.seq_parceiro seqParceiroDoc,\n p.nome nomeParceiroDoc,parceiroPC.nome nomeParceiroPC, conta.saldo_inicial contaSaldo, conta.nome contaOrigem, contaDestino.nome contaDestino,COLABORADOR.NOME nomeColaborador FROM FINANCEIRO inner join usuario on usuario.seq_usuario = financeiro.seq_usuario \n left join parceiro on parceiro.seq_parceiro = financeiro.seq_parceiro \nleft join tipo_movimento_financeiro on tipo_movimento_financeiro.seq_tipo_movimento_financeiro = financeiro.seq_tipo_movimento \nleft join unidade_negocio on unidade_negocio.seq_unidade_negocio = financeiro.seq_unidade_negocio \nleft join financeiro_categoria on financeiro_categoria.seq_financeiro_categoria = tipo_movimento_financeiro.seq_financeiro_categoria \nleft join documento on documento.seq_documento = financeiro.seq_documento\nleft join parceiro p on p.seq_parceiro = documento.seq_parceiro\nleft join parceiro parceiroPC on parceiroPC.seq_parceiro = financeiro.seq_parceiro\nleft join conta on conta.seq_conta = financeiro.seq_conta\nleft join conta contaDestino on contaDestino.seq_conta = financeiro.seq_conta_destino\nleft join colaborador on colaborador.seq_colaborador = financeiro.seq_colaborador" + sClausula.montarsClausula();

/* 366 */       System.out.println(sql);
/*     */       
/* 368 */       List<Financeiro> listaFinanceiro = new ArrayList();
/* 369 */       PreparedStatement ps = conn.prepareStatement(sql);
/* 370 */       ResultSet rs = ps.executeQuery();
/*     */       
/* 372 */       while (rs.next()) {
/* 373 */         Financeiro financeiro = new Financeiro();
/* 374 */         financeiro.setSeqFinanceiro(rs.getString("SEQ_FINANCEIRO"));
/* 375 */         financeiro.setSeqEmpresa(rs.getString("SEQ_EMPRESA"));
/* 376 */         financeiro.setDataCadastro(rs.getDate("DATA_CADASTRO"));
/* 377 */         financeiro.setSeqTipoMovimento(rs.getString("SEQ_TIPO_MOVIMENTO"));
/* 378 */         financeiro.setSeqParceiro(rs.getString("SEQ_PARCEIRO"));
/* 379 */         financeiro.setSeqDocumento(rs.getString("SEQ_DOCUMENTO"));
/* 380 */         financeiro.setSeqColaborador(rs.getString("SEQ_COLABORADOR"));
/* 381 */         financeiro.setDataLancamento(rs.getDate("DATA_LANCAMENTO"));
/* 382 */         financeiro.setDataVencimento(rs.getDate("DATA_VENCIMENTO"));
/* 383 */         financeiro.setDataPagamento(rs.getDate("DATA_PAGAMENTO"));
/* 384 */         financeiro.setMoedaOrigem(rs.getString("MOEDA_ORIGEM"));
/* 385 */         financeiro.setMoedaDestino(rs.getString("MOEDA_DESTINO"));
/* 386 */         financeiro.setValor(rs.getBigDecimal("VALOR"));
/* 387 */         financeiro.setRetencaoISSQN(rs.getBigDecimal("RETENCAO_ISSQN"));
/* 388 */         financeiro.setValorPagamento(rs.getBigDecimal("VALOR_PAGAMENTO"));
/* 389 */         financeiro.setSeqTipoDocumento(rs.getString("SEQ_TIPO_DOCUMENTO"));
/* 390 */         financeiro.setSeqUsuario(rs.getString("SEQ_usuario"));
/* 391 */         financeiro.setSeqAliquotaISSQN(rs.getString("SEQ_ALIQUOTA_ISSQN"));
/* 392 */         financeiro.setSeqAliquotaIRRF(rs.getString("SEQ_ALIQUOTA_IRRF"));
/* 393 */         financeiro.setSeqAliquotaPIS(rs.getString("SEQ_ALIQUOTA_PIS"));
/* 394 */         financeiro.setSeqAliquotaCSLL(rs.getString("SEQ_ALIQUOTA_CSLL"));
/* 395 */         financeiro.setSeqAliquotaCOFINS(rs.getString("SEQ_ALIQUOTA_COFINS"));
/* 396 */         financeiro.setOperacao(rs.getString("OPERACAO"));
/* 397 */         financeiro.setResponsavel(rs.getString("usuario"));
/* 397 */         financeiro.setAprovador(rs.getString("APROVADOR"));
/* 398 */         financeiro.setDescricao(rs.getString("descricao"));
/* 399 */         financeiro.setOrigemParceiro(rs.getString("origem_parceiro"));
/* 400 */         financeiro.setSeqDocumentoFiscal(rs.getString("seq_documento_fiscal"));
/* 401 */         financeiro.setSeqCentroCusto(rs.getString("seq_centro_custo"));
/* 402 */         financeiro.setNumeroDocFiscal(rs.getString("numero_doc_fiscal"));
/* 403 */         financeiro.setOrigemLCM(rs.getString("origem_lcm"));
/* 404 */         financeiro.setDescricaoServicosPrestados(rs.getString("DESCRICAO_SERVICO_PRESTADO"));
/* 405 */         financeiro.setCodigoFiscalMunicipal(rs.getString("CODIGO_FISCAL_MUNICIPAL"));
/* 406 */         financeiro.setOutrasInformacoes(rs.getString("OUTRAS_INFORMACOES"));
/* 407 */         financeiro.setDataEmissao(rs.getDate("data_emissao"));
/* 408 */         financeiro.setValorMulta(rs.getBigDecimal("valor_multa"));
/* 409 */         financeiro.setValorDesconto(rs.getBigDecimal("valor_desconto"));
/* 410 */         financeiro.setValorRecebido(rs.getBigDecimal("valor_recebido"));
/* 411 */         financeiro.setValorJuros(rs.getBigDecimal("valor_juros"));
/* 412 */         financeiro.setTarifaBancaria(rs.getBigDecimal("tarifa_bancaria"));
/* 413 */         financeiro.setSeqFormaPagamento(rs.getString("seq_forma_pagamento"));
/* 414 */         financeiro.setParceiro(rs.getString("parceiro"));
/* 415 */         financeiro.setTipoLancamento(rs.getString("tipo_movimento"));
/* 416 */         financeiro.setSeqUnidadeNegocio(rs.getString("seq_unidade_negocio"));
/* 417 */         financeiro.setUnidadeNegocioRazao(rs.getString("unidade_negocio_razao"));
/* 418 */         financeiro.setEtapa(rs.getString("status"));
/* 419 */         financeiro.setTipoDespesa(rs.getString("tipo_despesa"));
/* 420 */         financeiro.setSeqCondicaoPagamento(rs.getString("seq_condicao_pagamento"));
/* 421 */         financeiro.setSeqParceiroContato(rs.getString("seq_parceiro_contato"));
/* 422 */         financeiro.setSeqConta(rs.getString("seq_conta"));
/* 423 */         financeiro.setOutrosAcrescimos(rs.getBigDecimal("outros_acrescimos"));
/* 424 */         financeiro.setNomeCategoria(rs.getString("nomeCategoria"));
/* 425 */         financeiro.setNomeParceiroDoc(rs.getString("nomeParceiroDoc"));
/* 426 */         financeiro.setTaxaCambio(rs.getBigDecimal("taxa_cambio"));
/* 427 */         financeiro.setChkTaxaCambio(rs.getBoolean("chk_cambio"));
/* 428 */         financeiro.setOcorrencias(Integer.valueOf(rs.getInt("ocorrencias")));
/* 429 */         financeiro.setParcelaInicio(Integer.valueOf(rs.getInt("parcela_inicio")));
/* 430 */         financeiro.setParcelaFim(Integer.valueOf(rs.getInt("parcela_fim")));
/* 431 */         financeiro.setIntervaloNumero(Integer.valueOf(rs.getInt("intervalo_numero")));
/* 432 */         financeiro.setIntervaloTempo(rs.getString("intervalo_tempo"));
/* 433 */         financeiro.setIndefinidamente(rs.getString("indefinidamente"));
/* 434 */         financeiro.setMotivoCancelamento(rs.getString("motivo_cancelamento"));
/* 435 */         financeiro.setChkRepeticao(rs.getBoolean("chk_repeticao"));
/* 436 */         financeiro.setResponsavelQuitacao(rs.getString("responsavel_quitacao"));
/* 437 */         financeiro.setSeqFinanceiroOrdemPagamento(rs.getString("seq_financeiro_ordem_pagamento"));
/* 438 */         financeiro.setSeqFinanceiroFaturamento(rs.getString("seq_financeiro_faturamento"));
/* 439 */         financeiro.setValorConvertido(rs.getBigDecimal("valor_convertido"));
/* 440 */         financeiro.setSeqAliquotaCodigoFiscal(rs.getString("seq_aliquota_codigo_fiscal"));
/* 441 */         financeiro.setSeqContaDestino(rs.getString("seq_conta_destino"));
/* 442 */         financeiro.setContaSaldo(rs.getBigDecimal("contaSaldo"));
/* 443 */         financeiro.setSeqNvEmbarcacao(rs.getString("seq_nv_embarcacao"));
/* 444 */         financeiro.setSeqEquipamento(rs.getString("seq_equipamento"));
/* 445 */         financeiro.setLocalServico(rs.getString("local_servico"));
/* 446 */         financeiro.setValorTotalMovimentacao(rs.getBigDecimal("valor_total_movimentacao"));
/* 447 */         financeiro.setNomeColaborador(rs.getString("nomeColaborador"));
/* 448 */         financeiro.setNomeParceiroPC(rs.getString("nomeParceiroPC"));
/* 449 */         financeiro.setDataPeriodoInicio(rs.getDate("data_periodo_inicio"));
/* 450 */         financeiro.setDataPeriodoFinal(rs.getDate("data_periodo_final"));
/* 451 */         financeiro.setSeqMaterial(rs.getString("seq_material"));
/* 452 */         financeiro.setFatura(rs.getString("fatura"));
/* 453 */         financeiro.setContaDestino(rs.getString("contaDestino"));
/* 454 */         financeiro.setContaOrigem(rs.getString("contaOrigem"));
/* 455 */         financeiro.setValorParcial(rs.getBigDecimal("valor_parcial"));
/* 456 */         financeiro.setTipoQuitacao(rs.getString("tipo_quitacao"));
/* 457 */         financeiro.setDataPeriodoInicialRLBM(rs.getDate("DataPeriodo_InicialRLBM"));
/* 458 */         financeiro.setDataPeriodoFinalRLBM(rs.getDate("DataPeriodo_FinalRLBM"));
/*     */         
/* 460 */         listaFinanceiro.add(financeiro);
/*     */       }
/*     */       
/* 463 */       ps.execute();
/* 464 */       ps.close();
/*     */       
/* 466 */       return listaFinanceiro;
/*     */     } catch (SQLException ex) {
/* 468 */       Logger.getLogger(FinanceiroDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 469 */       System.out.println(ex.getMessage()); }
/* 470 */     return null;
/*     */   }
/*     */   
/*     */   public boolean deletar(Financeiro financeiro)
/*     */   {
/*     */     try {
/* 476 */       Conexao conexao = new Conexao();
/* 477 */       Connection conn = Conexao.getConnection();
/* 478 */       String sql = "DELETE FROM FINANCEIRO WHERE SEQ_FINANCEIRO =  ? ";
/*     */       
/* 480 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/* 482 */       ps.setString(1, financeiro.getSeqFinanceiro());
/*     */       
/* 484 */       ps.execute();
/* 485 */       ps.close();
/*     */       
/* 487 */       return true;
/*     */     } catch (SQLException ex) {
/* 489 */       System.out.println(ex.getMessage()); }
/* 490 */     return false;
/*     */   }
/*     */ }
