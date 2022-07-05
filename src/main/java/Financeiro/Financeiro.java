/*     */ package Financeiro;
/*     */ 
/*     */ import FinanceiroItem.FinanceiroItem;
/*     */ import FinanceiroOcorrencia.FinanceiroOcorrencia;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ 

/*     */ public class Financeiro
/*     */ {
/*     */   private String seqFinanceiro;
/*     */   private String seqEmpresa;
/*     */   private Date dataCadastro;
/*     */   private String seqTipoMovimento;
/*     */   private String seqParceiro;
/*     */   private String seqDocumento;
/*     */   private String seqColaborador;
/*     */   private Date dataLancamento;
/*     */   private Date dataVencimento;
/*     */   private Date dataPagamento;
/*     */   private Date dataEmissao;
/*     */   private Date dataPeriodoInicio;
/*     */   private Date dataPeriodoFinal;
/*     */   private Date dataPeriodoInicialRLBM;
/*     */   private Date dataPeriodoFinalRLBM;
/*     */   private String moedaOrigem;
/*     */   private String moedaDestino;
/*     */   private BigDecimal valor;
/*     */   private BigDecimal valorPagamento;
/*     */   private String seqTipoDocumento;
/*     */   private String operacao;
/*     */   private String seqUsuario;
/*     */   private String responsavel;
/*     */   private String aprovador;
/*     */   private String descricao;
/*     */   private String seqUnidadeNegocio;
/*     */   private String UnidadeNegocioRazao;
/*     */   private String origemParceiro;
/*     */   private BigDecimal retencaoISSQN;
/*     */   private String seqAliquotaISSQN;
/*     */   private String seqAliquotaIRRF;
/*     */   private String seqAliquotaCSLL;
/*     */   private String seqAliquotaPIS;
/*     */   private String seqAliquotaCOFINS;
/*     */   private String seqCentroCusto;
/*     */   private String seqDocumentoFiscal;
/*     */   private String numeroDocFiscal;
/*     */   private String origemLCM;
/*     */   private String descricaoServicosPrestados;
/*     */   private String codigoFiscalMunicipal;
/*     */   private String outrasInformacoes;
/*     */   private BigDecimal valorDesconto;
/*     */   private BigDecimal valorJuros;
/*     */   private BigDecimal valorMulta;
/*     */   private BigDecimal valorTotalMovimentacao;
/*     */   private BigDecimal outrosAcrescimos;
/*     */   private BigDecimal tarifaBancaria;
/*     */   private BigDecimal valorRecebido;
/*     */   private BigDecimal valorConvertido;
/*     */   private BigDecimal taxaCambio;
/*     */   private BigDecimal contaSaldo;
/*     */   private String seqFormaPagamento;
/*     */   private String parceiro;
/*     */   private String tipoLancamento;
/*     */   private String etapa;
/*     */   private String seqParceiroContato;
/*     */   private String seqCategoria;
/*     */   private String tipoDespesa;
/*     */   private String seqCondicaoPagamento;
/*     */   private String seqConta;
/*     */   private String seqContaDestino;
/*     */   private String nomeCategoria;
/*     */   private Integer ocorrencias;
/*     */   private Integer parcelaInicio;
/*     */   private Integer parcelaFim;
/*     */   private Integer intervaloNumero;
/*     */   private String indefinidamente;
/*     */   private String intervaloTempo;
/*     */   private String nomeParceiroDoc;
/*     */   private boolean chkTaxaCambio;
/*     */   private boolean chkRepeticao;
/*     */   private String motivoCancelamento;
/*     */   private String responsavelQuitacao;
/*     */   private String seqFinanceiroOrdemPagamento;
/*     */   private String seqFinanceiroFaturamento;
/*     */   private String seqAliquotaCodigoFiscal;
/*     */   private String seqEquipamento;
/*     */   private String seqNvEmbarcacao;
/*     */   private String localServico;
/*     */   private String nomeColaborador;
/*     */   private String nomeParceiroPC;
/*     */   private String seqMaterial;
/*     */   private String fatura;
/*     */   private String contaDestino;
/*     */   private String contaOrigem;
/*     */   private String tipoQuitacao;
/*     */   private BigDecimal valorParcial;
/*     */   List<FinanceiroItem> listaFinanceiroItem;
/*     */   List<FinanceiroOcorrencia> listaFinanceiroOcorrencia;
/*     */   
/*     */   public Financeiro()
/*     */   {
/* 125 */     this.listaFinanceiroItem = new ArrayList();
/* 126 */     this.listaFinanceiroOcorrencia = new ArrayList();
/*     */   }
/*     */   
/*     */   public String getSeqUnidadeNegocio()
/*     */   {
/* 131 */     return this.seqUnidadeNegocio;
/*     */   }
/*     */   
/*     */   public void setSeqUnidadeNegocio(String seqUnidadeNegocio) {
/* 135 */     this.seqUnidadeNegocio = seqUnidadeNegocio;
/*     */   }
/*     */   
/*     */   public String getSeqUsuario() {
/* 139 */     return this.seqUsuario;
/*     */   }
/*     */   
/*     */   public String getDescricao() {
/* 143 */     return this.descricao;
/*     */   }
/*     */   
/*     */   public void setDescricao(String descricao) {
/* 147 */     this.descricao = descricao;
/*     */   }
/*     */   
/*     */   public void setSeqUsuario(String seqUsuario) {
/* 151 */     this.seqUsuario = seqUsuario;
/*     */   }
/*     */   
/*     */   public String getSeqFinanceiro() {
/* 155 */     return this.seqFinanceiro;
/*     */   }
/*     */   
/*     */   public void setSeqFinanceiro(String seqFinanceiro) {
/* 159 */     this.seqFinanceiro = seqFinanceiro;
/*     */   }
/*     */   
/*     */   public String getSeqEmpresa() {
/* 163 */     return this.seqEmpresa;
/*     */   }
/*     */   
/*     */   public void setSeqEmpresa(String seqEmpresa) {
/* 167 */     this.seqEmpresa = seqEmpresa;
/*     */   }
/*     */   
/*     */   public Date getDataCadastro() {
/* 171 */     return this.dataCadastro;
/*     */   }
/*     */   
/*     */   public void setDataCadastro(Date dataCadastro) {
/* 175 */     this.dataCadastro = dataCadastro;
/*     */   }
/*     */   
/*     */   public String getSeqTipoMovimento() {
/* 179 */     return this.seqTipoMovimento;
/*     */   }
/*     */   
/*     */   public void setSeqTipoMovimento(String seqTipoMovimento) {
/* 183 */     this.seqTipoMovimento = seqTipoMovimento;
/*     */   }
/*     */   
/*     */   public String getSeqParceiro() {
/* 187 */     return this.seqParceiro;
/*     */   }
/*     */   
/*     */   public void setSeqParceiro(String seqParceiro) {
/* 191 */     this.seqParceiro = seqParceiro;
/*     */   }
/*     */   
/*     */   public String getSeqDocumento() {
/* 195 */     return this.seqDocumento;
/*     */   }
/*     */   
/*     */   public void setSeqDocumento(String seqDocumento) {
/* 199 */     this.seqDocumento = seqDocumento;
/*     */   }
/*     */   
/*     */   public String getSeqColaborador() {
/* 203 */     return this.seqColaborador;
/*     */   }
/*     */   
/*     */   public void setSeqColaborador(String seqColaborador) {
/* 207 */     this.seqColaborador = seqColaborador;
/*     */   }
/*     */   
/*     */   public Date getDataLancamento() {
/* 211 */     return this.dataLancamento;
/*     */   }
/*     */   
/*     */   public void setDataLancamento(Date dataLancamento) {
/* 215 */     this.dataLancamento = dataLancamento;
/*     */   }
/*     */   
/*     */   public Date getDataVencimento() {
/* 219 */     return this.dataVencimento;
/*     */   }
/*     */   
/*     */   public void setDataVencimento(Date dataVencimento) {
/* 223 */     this.dataVencimento = dataVencimento;
/*     */   }
/*     */   
/*     */   public Date getDataPagamento() {
/* 227 */     return this.dataPagamento;
/*     */   }
/*     */   
/*     */   public void setDataPagamento(Date dataPagamento) {
/* 231 */     this.dataPagamento = dataPagamento;
/*     */   }
/*     */   
/*     */   public String getMoedaOrigem() {
/* 235 */     return this.moedaOrigem;
/*     */   }
/*     */   
/*     */   public void setMoedaOrigem(String moedaOrigem) {
/* 239 */     this.moedaOrigem = moedaOrigem;
/*     */   }
/*     */   
/*     */   public String getMoedaDestino() {
/* 243 */     return this.moedaDestino;
/*     */   }
/*     */   
/*     */   public void setMoedaDestino(String moedaDestino) {
/* 247 */     this.moedaDestino = moedaDestino;
/*     */   }
/*     */   
/*     */   public BigDecimal getValor() {
/* 251 */     return this.valor;
/*     */   }
/*     */   
/*     */   public void setValor(BigDecimal valor) {
/* 255 */     this.valor = valor;
/*     */   }
/*     */   
/*     */   public BigDecimal getValorPagamento() {
/* 259 */     return this.valorPagamento;
/*     */   }
/*     */   
/*     */   public void setValorPagamento(BigDecimal valorPagamento) {
/* 263 */     this.valorPagamento = valorPagamento;
/*     */   }
/*     */   
/*     */   public String getSeqTipoDocumento() {
/* 267 */     return this.seqTipoDocumento;
/*     */   }
/*     */   
/*     */   public void setSeqTipoDocumento(String seqTipoDocumento) {
/* 271 */     this.seqTipoDocumento = seqTipoDocumento;
/*     */   }
/*     */   
/*     */   public String getOperacao() {
/* 275 */     return this.operacao;
/*     */   }
/*     */   
/*     */   public void setOperacao(String operacao) {
/* 279 */     this.operacao = operacao;
/*     */   }
/*     */   
/*     */   public String getResponsavel() {
/* 283 */     return this.responsavel;
/*     */   }
/*     */   
/*     */   public void setResponsavel(String responsavel) {
/* 287 */     this.responsavel = responsavel;
/*     */   }
/*     */   public String getAprovador() {
/* 283 */     return this.aprovador;
/*     */   }
/*     */   
/*     */   public void setAprovador(String aprovador) {
/* 287 */     this.aprovador = aprovador;
/*     */   }
/*     */   
/*     */   public String getUnidadeNegocioRazao() {
/* 291 */     return this.UnidadeNegocioRazao;
/*     */   }
/*     */   
/*     */   public void setUnidadeNegocioRazao(String UnidadeNegocioRazao) {
/* 295 */     this.UnidadeNegocioRazao = UnidadeNegocioRazao;
/*     */   }
/*     */   
/*     */   public String getOrigemParceiro() {
/* 299 */     return this.origemParceiro;
/*     */   }
/*     */   
/*     */   public void setOrigemParceiro(String origemParceiro) {
/* 303 */     this.origemParceiro = origemParceiro;
/*     */   }
/*     */   
/*     */   public Date getDataEmissao() {
/* 307 */     return this.dataEmissao;
/*     */   }
/*     */   
/*     */   public void setDataEmissao(Date dataEmissao) {
/* 311 */     this.dataEmissao = dataEmissao;
/*     */   }
/*     */   
/*     */   public BigDecimal getRetencaoISSQN() {
/* 315 */     return this.retencaoISSQN;
/*     */   }
/*     */   
/*     */   public void setRetencaoISSQN(BigDecimal retencaoISSQN) {
/* 319 */     this.retencaoISSQN = retencaoISSQN;
/*     */   }
/*     */   
/*     */   public String getSeqAliquotaISSQN() {
/* 323 */     return this.seqAliquotaISSQN;
/*     */   }
/*     */   
/*     */   public void setSeqAliquotaISSQN(String seqAliquotaISSQN) {
/* 327 */     this.seqAliquotaISSQN = seqAliquotaISSQN;
/*     */   }
/*     */   
/*     */   public String getSeqAliquotaIRRF() {
/* 331 */     return this.seqAliquotaIRRF;
/*     */   }
/*     */   
/*     */   public void setSeqAliquotaIRRF(String seqAliquotaIRRF) {
/* 335 */     this.seqAliquotaIRRF = seqAliquotaIRRF;
/*     */   }
/*     */   
/*     */   public String getSeqAliquotaCSLL() {
/* 339 */     return this.seqAliquotaCSLL;
/*     */   }
/*     */   
/*     */   public void setSeqAliquotaCSLL(String seqAliquotaCSLL) {
/* 343 */     this.seqAliquotaCSLL = seqAliquotaCSLL;
/*     */   }
/*     */   
/*     */   public String getSeqAliquotaPIS() {
/* 347 */     return this.seqAliquotaPIS;
/*     */   }
/*     */   
/*     */   public void setSeqAliquotaPIS(String seqAliquotaPIS) {
/* 351 */     this.seqAliquotaPIS = seqAliquotaPIS;
/*     */   }
/*     */   
/*     */   public String getSeqAliquotaCOFINS() {
/* 355 */     return this.seqAliquotaCOFINS;
/*     */   }
/*     */   
/*     */   public void setSeqAliquotaCOFINS(String seqAliquotaCOFINS) {
/* 359 */     this.seqAliquotaCOFINS = seqAliquotaCOFINS;
/*     */   }
/*     */   
/*     */   public String getSeqCentroCusto() {
/* 363 */     return this.seqCentroCusto;
/*     */   }
/*     */   
/*     */   public void setSeqCentroCusto(String seqCentroCusto) {
/* 367 */     this.seqCentroCusto = seqCentroCusto;
/*     */   }
/*     */   
/*     */   public String getSeqDocumentoFiscal() {
/* 371 */     return this.seqDocumentoFiscal;
/*     */   }
/*     */   
/*     */   public void setSeqDocumentoFiscal(String seqDocumentoFiscal) {
/* 375 */     this.seqDocumentoFiscal = seqDocumentoFiscal;
/*     */   }
/*     */   
/*     */   public String getNumeroDocFiscal() {
/* 379 */     return this.numeroDocFiscal;
/*     */   }
/*     */   
/*     */   public void setNumeroDocFiscal(String numeroDocFiscal) {
/* 383 */     this.numeroDocFiscal = numeroDocFiscal;
/*     */   }
/*     */   
/*     */   public String getOrigemLCM() {
/* 387 */     return this.origemLCM;
/*     */   }
/*     */   
/*     */   public void setOrigemLCM(String origemLCM) {
/* 391 */     this.origemLCM = origemLCM;
/*     */   }
/*     */   
/*     */   public String getDescricaoServicosPrestados() {
/* 395 */     return this.descricaoServicosPrestados;
/*     */   }
/*     */   
/*     */   public void setDescricaoServicosPrestados(String descricaoServicosPrestados) {
/* 399 */     this.descricaoServicosPrestados = descricaoServicosPrestados;
/*     */   }
/*     */   
/*     */   public String getCodigoFiscalMunicipal() {
/* 403 */     return this.codigoFiscalMunicipal;
/*     */   }
/*     */   
/*     */   public void setCodigoFiscalMunicipal(String codigoFiscalMunicipal) {
/* 407 */     this.codigoFiscalMunicipal = codigoFiscalMunicipal;
/*     */   }
/*     */   
/*     */   public String getOutrasInformacoes() {
/* 411 */     return this.outrasInformacoes;
/*     */   }
/*     */   
/*     */   public void setOutrasInformacoes(String outrasInformacoes) {
/* 415 */     this.outrasInformacoes = outrasInformacoes;
/*     */   }
/*     */   
/*     */   public BigDecimal getValorDesconto() {
/* 419 */     return this.valorDesconto;
/*     */   }
/*     */   
/*     */   public void setValorDesconto(BigDecimal valorDesconto) {
/* 423 */     this.valorDesconto = valorDesconto;
/*     */   }
/*     */   
/*     */   public BigDecimal getValorJuros() {
/* 427 */     return this.valorJuros;
/*     */   }
/*     */   
/*     */   public void setValorJuros(BigDecimal valorJuros) {
/* 431 */     this.valorJuros = valorJuros;
/*     */   }
/*     */   
/*     */   public BigDecimal getValorMulta() {
/* 435 */     return this.valorMulta;
/*     */   }
/*     */   
/*     */   public void setValorMulta(BigDecimal valorMulta) {
/* 439 */     this.valorMulta = valorMulta;
/*     */   }
/*     */   
/*     */   public BigDecimal getTarifaBancaria() {
/* 443 */     return this.tarifaBancaria;
/*     */   }
/*     */   
/*     */   public void setTarifaBancaria(BigDecimal tarifaBancaria) {
/* 447 */     this.tarifaBancaria = tarifaBancaria;
/*     */   }
/*     */   
/*     */   public BigDecimal getValorRecebido() {
/* 451 */     return this.valorRecebido;
/*     */   }
/*     */   
/*     */   public void setValorRecebido(BigDecimal valorRecebido) {
/* 455 */     this.valorRecebido = valorRecebido;
/*     */   }
/*     */   
/*     */   public String getSeqFormaPagamento() {
/* 459 */     return this.seqFormaPagamento;
/*     */   }
/*     */   
/*     */   public void setSeqFormaPagamento(String seqFormaPagamento) {
/* 463 */     this.seqFormaPagamento = seqFormaPagamento;
/*     */   }
/*     */   
/*     */   public String getParceiro() {
/* 467 */     return this.parceiro;
/*     */   }
/*     */   
/*     */   public void setParceiro(String parceiro) {
/* 471 */     this.parceiro = parceiro;
/*     */   }
/*     */   
/*     */   public String getTipoLancamento() {
/* 475 */     return this.tipoLancamento;
/*     */   }
/*     */   
/*     */   public void setTipoLancamento(String tipoLancamento) {
/* 479 */     this.tipoLancamento = tipoLancamento;
/*     */   }
/*     */   
/*     */   public String getEtapa() {
/* 483 */     return this.etapa;
/*     */   }
/*     */   
/*     */   public void setEtapa(String etapa) {
/* 487 */     this.etapa = etapa;
/*     */   }
/*     */   
/*     */   public List<FinanceiroItem> getListaFinanceiroItem() {
/* 491 */     return this.listaFinanceiroItem;
/*     */   }
/*     */   
/*     */   public void setListaFinanceiroItem(List<FinanceiroItem> listaFinanceiroItem) {
/* 495 */     this.listaFinanceiroItem = listaFinanceiroItem;
/*     */   }
/*     */   
/*     */   public String getSeqParceiroContato() {
/* 499 */     return this.seqParceiroContato;
/*     */   }
/*     */   
/*     */   public void setSeqParceiroContato(String seqParceiroContato) {
/* 503 */     this.seqParceiroContato = seqParceiroContato;
/*     */   }
/*     */   
/*     */   public String getSeqCategoria() {
/* 507 */     return this.seqCategoria;
/*     */   }
/*     */   
/*     */   public void setSeqCategoria(String seqCategoria) {
/* 511 */     this.seqCategoria = seqCategoria;
/*     */   }
/*     */   
/*     */   public String getTipoDespesa() {
/* 515 */     return this.tipoDespesa;
/*     */   }
/*     */   
/*     */   public void setTipoDespesa(String tipoDespesa) {
/* 519 */     this.tipoDespesa = tipoDespesa;
/*     */   }
/*     */   
/*     */   public String getSeqCondicaoPagamento() {
/* 523 */     return this.seqCondicaoPagamento;
/*     */   }
/*     */   
/*     */   public void setSeqCondicaoPagamento(String seqCondicaoPagamento) {
/* 527 */     this.seqCondicaoPagamento = seqCondicaoPagamento;
/*     */   }
/*     */   
/*     */   public String getSeqConta() {
/* 531 */     return this.seqConta;
/*     */   }
/*     */   
/*     */   public void setSeqConta(String seqConta) {
/* 535 */     this.seqConta = seqConta;
/*     */   }
/*     */   
/*     */   public BigDecimal getOutrosAcrescimos() {
/* 539 */     return this.outrosAcrescimos;
/*     */   }
/*     */   
/*     */   public void setOutrosAcrescimos(BigDecimal outrosAcrescimos) {
/* 543 */     this.outrosAcrescimos = outrosAcrescimos;
/*     */   }
/*     */   
/*     */   public String getNomeCategoria() {
/* 547 */     return this.nomeCategoria;
/*     */   }
/*     */   
/*     */   public void setNomeCategoria(String nomeCategoria) {
/* 551 */     this.nomeCategoria = nomeCategoria;
/*     */   }
/*     */   
/*     */   public Integer getOcorrencias() {
/* 555 */     return this.ocorrencias;
/*     */   }
/*     */   
/*     */   public void setOcorrencias(Integer ocorrencias) {
/* 559 */     this.ocorrencias = ocorrencias;
/*     */   }
/*     */   
/*     */   public Integer getParcelaInicio() {
/* 563 */     return this.parcelaInicio;
/*     */   }
/*     */   
/*     */   public void setParcelaInicio(Integer parcelaInicio) {
/* 567 */     this.parcelaInicio = parcelaInicio;
/*     */   }
/*     */   
/*     */   public Integer getParcelaFim() {
/* 571 */     return this.parcelaFim;
/*     */   }
/*     */   
/*     */   public void setParcelaFim(Integer parcelaFim) {
/* 575 */     this.parcelaFim = parcelaFim;
/*     */   }
/*     */   
/*     */   public Integer getIntervaloNumero() {
/* 579 */     return this.intervaloNumero;
/*     */   }
/*     */   
/*     */   public void setIntervaloNumero(Integer intervaloNumero) {
/* 583 */     this.intervaloNumero = intervaloNumero;
/*     */   }
/*     */   
/*     */   public String getIndefinidamente() {
/* 587 */     return this.indefinidamente;
/*     */   }
/*     */   
/*     */   public void setIndefinidamente(String indefinidamente) {
/* 591 */     this.indefinidamente = indefinidamente;
/*     */   }
/*     */   
/*     */   public String getIntervaloTempo() {
/* 595 */     return this.intervaloTempo;
/*     */   }
/*     */   
/*     */   public void setIntervaloTempo(String intervaloTempo) {
/* 599 */     this.intervaloTempo = intervaloTempo;
/*     */   }
/*     */   
/*     */   public String getNomeParceiroDoc() {
/* 603 */     return this.nomeParceiroDoc;
/*     */   }
/*     */   
/*     */   public void setNomeParceiroDoc(String nomeParceiroDoc) {
/* 607 */     this.nomeParceiroDoc = nomeParceiroDoc;
/*     */   }
/*     */   
/*     */   public BigDecimal getTaxaCambio() {
/* 611 */     return this.taxaCambio;
/*     */   }
/*     */   
/*     */   public void setTaxaCambio(BigDecimal taxaCambio) {
/* 615 */     this.taxaCambio = taxaCambio;
/*     */   }
/*     */   
/*     */   public boolean isChkTaxaCambio() {
/* 619 */     return this.chkTaxaCambio;
/*     */   }
/*     */   
/*     */   public void setChkTaxaCambio(boolean chkTaxaCambio) {
/* 623 */     this.chkTaxaCambio = chkTaxaCambio;
/*     */   }
/*     */   
/*     */   public boolean isChkRepeticao() {
/* 627 */     return this.chkRepeticao;
/*     */   }
/*     */   
/*     */   public void setChkRepeticao(boolean chkRepeticao) {
/* 631 */     this.chkRepeticao = chkRepeticao;
/*     */   }
/*     */   
/*     */   public String getMotivoCancelamento() {
/* 635 */     return this.motivoCancelamento;
/*     */   }
/*     */   
/*     */   public void setMotivoCancelamento(String motivoCancelamento) {
/* 639 */     this.motivoCancelamento = motivoCancelamento;
/*     */   }
/*     */   
/*     */   public String getResponsavelQuitacao() {
/* 643 */     return this.responsavelQuitacao;
/*     */   }
/*     */   
/*     */   public void setResponsavelQuitacao(String responsávelQuitacao) {
/* 647 */     this.responsavelQuitacao = responsávelQuitacao;
/*     */   }
/*     */   
/*     */   public String getSeqFinanceiroOrdemPagamento() {
/* 651 */     return this.seqFinanceiroOrdemPagamento;
/*     */   }
/*     */   
/*     */   public void setSeqFinanceiroOrdemPagamento(String seqFinanceiroOrdemPagamento) {
/* 655 */     this.seqFinanceiroOrdemPagamento = seqFinanceiroOrdemPagamento;
/*     */   }
/*     */   
/*     */   public BigDecimal getValorConvertido() {
/* 659 */     return this.valorConvertido;
/*     */   }
/*     */   
/*     */   public void setValorConvertido(BigDecimal valorConvertido) {
/* 663 */     this.valorConvertido = valorConvertido;
/*     */   }
/*     */   
/*     */   public String getSeqFinanceiroFaturamento() {
/* 667 */     return this.seqFinanceiroFaturamento;
/*     */   }
/*     */   
/*     */   public void setSeqFinanceiroFaturamento(String seqFinanceiroFaturamento) {
/* 671 */     this.seqFinanceiroFaturamento = seqFinanceiroFaturamento;
/*     */   }
/*     */   
/*     */   public String getSeqAliquotaCodigoFiscal() {
/* 675 */     return this.seqAliquotaCodigoFiscal;
/*     */   }
/*     */   
/*     */   public void setSeqAliquotaCodigoFiscal(String seqAliquotaCodigoFiscal) {
/* 679 */     this.seqAliquotaCodigoFiscal = seqAliquotaCodigoFiscal;
/*     */   }
/*     */   
/*     */   public String getSeqContaDestino() {
/* 683 */     return this.seqContaDestino;
/*     */   }
/*     */   
/*     */   public void setSeqContaDestino(String seqContaDestino) {
/* 687 */     this.seqContaDestino = seqContaDestino;
/*     */   }
/*     */   
/*     */   public BigDecimal getContaSaldo() {
/* 691 */     return this.contaSaldo;
/*     */   }
/*     */   
/*     */   public void setContaSaldo(BigDecimal contaSaldo) {
/* 695 */     this.contaSaldo = contaSaldo;
/*     */   }
/*     */   
/*     */   public String getSeqEquipamento() {
/* 699 */     return this.seqEquipamento;
/*     */   }
/*     */   
/*     */   public void setSeqEquipamento(String seqEquipamento) {
/* 703 */     this.seqEquipamento = seqEquipamento;
/*     */   }
/*     */   
/*     */   public String getSeqNvEmbarcacao() {
/* 707 */     return this.seqNvEmbarcacao;
/*     */   }
/*     */   
/*     */   public void setSeqNvEmbarcacao(String seqNvEmbarcacao) {
/* 711 */     this.seqNvEmbarcacao = seqNvEmbarcacao;
/*     */   }
/*     */   
/*     */   public String getLocalServico() {
/* 715 */     return this.localServico;
/*     */   }
/*     */   
/*     */   public void setLocalServico(String localServico) {
/* 719 */     this.localServico = localServico;
/*     */   }
/*     */   
/*     */   public BigDecimal getValorTotalMovimentacao() {
/* 723 */     return this.valorTotalMovimentacao;
/*     */   }
/*     */   
/*     */   public void setValorTotalMovimentacao(BigDecimal valorTotalMovimentacao) {
/* 727 */     this.valorTotalMovimentacao = valorTotalMovimentacao;
/*     */   }
/*     */   
/*     */   public String getNomeColaborador() {
/* 731 */     return this.nomeColaborador;
/*     */   }
/*     */   
/*     */   public void setNomeColaborador(String nomeColaborador) {
/* 735 */     this.nomeColaborador = nomeColaborador;
/*     */   }
/*     */   
/*     */   public String getNomeParceiroPC() {
/* 739 */     return this.nomeParceiroPC;
/*     */   }
/*     */   
/*     */   public void setNomeParceiroPC(String nomeParceiroPC) {
/* 743 */     this.nomeParceiroPC = nomeParceiroPC;
/*     */   }
/*     */   
/*     */   public Date getDataPeriodoInicio() {
/* 747 */     return this.dataPeriodoInicio;
/*     */   }
/*     */   
/*     */   public void setDataPeriodoInicio(Date dataPeriodoInicio) {
/* 751 */     this.dataPeriodoInicio = dataPeriodoInicio;
/*     */   }
/*     */   
/*     */   public Date getDataPeriodoFinal() {
/* 755 */     return this.dataPeriodoFinal;
/*     */   }
/*     */   
/*     */   public void setDataPeriodoFinal(Date dataPeriodoFinal) {
/* 759 */     this.dataPeriodoFinal = dataPeriodoFinal;
/*     */   }
/*     */   
/*     */   public String getSeqMaterial() {
/* 763 */     return this.seqMaterial;
/*     */   }
/*     */   
/*     */   public void setSeqMaterial(String seqMaterial) {
/* 767 */     this.seqMaterial = seqMaterial;
/*     */   }
/*     */   
/*     */   public String getFatura() {
/* 771 */     return this.fatura;
/*     */   }
/*     */   
/*     */   public void setFatura(String fatura) {
/* 775 */     this.fatura = fatura;
/*     */   }
/*     */   
/*     */   public String getContaDestino() {
/* 779 */     return this.contaDestino;
/*     */   }
/*     */   
/*     */   public void setContaDestino(String contaDestino) {
/* 783 */     this.contaDestino = contaDestino;
/*     */   }
/*     */   
/*     */   public String getContaOrigem() {
/* 787 */     return this.contaOrigem;
/*     */   }
/*     */   
/*     */   public void setContaOrigem(String contaOrigem) {
/* 791 */     this.contaOrigem = contaOrigem;
/*     */   }
/*     */   
/*     */   public List<FinanceiroOcorrencia> getListaFinanceiroOcorrencia() {
/* 795 */     return this.listaFinanceiroOcorrencia;
/*     */   }
/*     */   
/*     */   public void setListaFinanceiroOcorrencia(List<FinanceiroOcorrencia> listaFinanceiroOcorrencia) {
/* 799 */     this.listaFinanceiroOcorrencia = listaFinanceiroOcorrencia;
/*     */   }
/*     */   
/*     */   public String getTipoQuitacao() {
/* 803 */     return this.tipoQuitacao;
/*     */   }
/*     */   
/*     */   public void setTipoQuitacao(String tipoQuitacao) {
/* 807 */     this.tipoQuitacao = tipoQuitacao;
/*     */   }
/*     */   
/*     */   public BigDecimal getValorParcial() {
/* 811 */     return this.valorParcial;
/*     */   }
/*     */   
/*     */   public void setValorParcial(BigDecimal valorParcial) {
/* 815 */     this.valorParcial = valorParcial;
/*     */   }
/*     */   
/*     */   public Date getDataPeriodoFinalRLBM() {
/* 819 */     return this.dataPeriodoFinalRLBM;
/*     */   }
/*     */   
/*     */   public void setDataPeriodoFinalRLBM(Date dataPeriodoFinalRLBM) {
/* 823 */     this.dataPeriodoFinalRLBM = dataPeriodoFinalRLBM;
/*     */   }
/*     */   
/*     */   public Date getDataPeriodoInicialRLBM() {
/* 827 */     return this.dataPeriodoInicialRLBM;
/*     */   }
/*     */   
/*     */   public void setDataPeriodoInicialRLBM(Date dataPeriodoInicialRLBM) {
/* 831 */     this.dataPeriodoInicialRLBM = dataPeriodoInicialRLBM;
/*     */   }
/*     */ }
