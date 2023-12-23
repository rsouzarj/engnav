/*     */ package Documento;

/*     */
 /*     */ import DocumentoComentario.DocumentoComentario;
/*     */ import DocumentoItemEmbarcacao.DocumentoItemEmbarcacao;
/*     */ import DocumentoItemEquipamento.DocumentoItemEquipamento;
/*     */ import DocumentoItemFinanceiro.DocumentoItemFinanceiro;
/*     */ import DocumentoItemMaterial.DocumentoItemMaterial;
/*     */ import DocumentoItemServico.DocumentoItemServico;
/*     */ import DocumentoOcorrencia.DocumentoOcorrencia;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;

/*     */
 /*     */
 /*     */ public class Documento /*     */ {

    /*     */ private String seqDocumento;
    /*     */    private String codigo;
    /*     */    private String seqEmpresa;
    /*     */    private String seqParceiro;
    /*     */    private String seqTipoDocumento;
    /*     */    private String seqDocumentoEtapa;
    /*     */    private Date dataCadastro;
    /*     */    private String seqUsuarioCadastro;
    /*     */    private String seqAssColaborador;
    /*     */    private String nContrato;
    /*     */    private String seqFormaPagamento;
    /*     */    private String seqConta;
    /*     */    private String seqCondicaoPagamento;
    /*     */    private String seqTipoMovimentoFinanceiro;
    /*     */    private String descricao;
    /*     */    private Date data;
    /*     */    private String parceiro;
    /*     */    private String parceiroTipoPessoa;
    /*     */    private String parceiroEndereco;
    /*     */    private String parceiroDocumento;
    /*     */    private String parceiroTelefone;
    /*     */    private String parceiroEmail;
    /*     */    private String tipoParceiro;
    /*     */    private String etapa;
    /*     */    private String tipoDocumento;
    /*     */    private String usuarioCadastro;
    /*     */    private String seqLugar;
    /*     */    private Date dataPrevisaoConclusao;
    /*     */    private String seqDocumentoDono;
    /*     */    private String DocDonoTipo;
    /*     */    private String DocDonoSeqTipo;
    /*     */    private String DocDonoCodigo;
    /*     */    private String template;
    /*     */    private String reembolsoTransporte;
    /*     */    private String reembolsoAlimentacao;
    /*     */    private String reembolsoHospedagem;
    /*     */    private String reembolsoOutros;
    /*     */    private String reembolsoPlotagens;
    /*     */    private String localServico;
    /*     */    private BigDecimal vlEntrada;
    /*     */    private BigDecimal qtdeParcela;
    /*     */    private String DatasParcelado;
    /*     */    private String tributosImpostos;
    /*     */    private BigDecimal vlTotal;
    /*     */    private String seqParceiroContato;
    /*     */    private String seqParceiroContaPagar;
    /*     */    private String origemParceiro;
    /*     */    private String seqUnidadeNegocio;
    /*     */    private String comentarioEtapa;
    /*     */    private Long idRevisao;
    /*     */    private Long idComplementar;
    /*     */    private BigDecimal retencaoISSQN;
    /*     */    private BigDecimal seqProjecaoTributaria;
    /*     */    private BigDecimal aliquotaISSQNretido;
    /*     */    private BigDecimal seqAliquotaFederal;
    /*     */    private BigDecimal vlExecutadoAcumulado;
    /*     */    private BigDecimal vlTributosNaoIncluso;
    /*     */    private BigDecimal vlBruto;
    /*     */    private BigDecimal vlISSQNRetido;
    /*     */    private BigDecimal vlRetencaoFederal;
    /*     */    private BigDecimal vlLiquidoAReceber;
    /*     */    private BigDecimal tarifaBancaria;
    /*     */    private BigDecimal taxaCambio;
    /*     */    private int sequencia;
    /*     */    private String fechado;
    /*     */    private String grupoServico;
    /*     */    private String moeda;
    /*     */    private String moedaDestino;
    /*     */    private String filial;
    /*     */    private String unidadeNegocioUsuario;
    /*     */    private String nvEmbarcacaoNome;
    /*     */    private String equipamentoNome;
                 private String materialNome;
    /*     */    private String ordemCobranca;
    /*     */    private String boletimMedicao;
    /*     */    private String observacao;
    /*     */    private String natureza;
    /*     */    private String categoria;
    /*     */    private String estadoBM;
    /*     */    private String identificacaoListaPlanos;
    /*     */    private String escopo;
    /*     */    private String mostraTotal;
    /*     */    private String caminho;
    /*     */    private String objetivo;
    /*     */    private String validade;
    /*     */    private String descRemuneracao;
    /*     */    private String condicoesGerais;
    /*     */    private String condpag;
    /*     */    private String assunto;
                 private String comprimento;
                 private String boca;
                 private String ab;
    /*     */

    private BigDecimal vlTotalcambio;
    /*     */    List<DocumentoItemMaterial> listaDocumentoItemMaterial;
    /*     */    List<DocumentoItemFinanceiro> listaDocumentoItemFinanceiro;
    /*     */    List<DocumentoItemServico> listaDocumentoItemServico;
    /*     */    List<DocumentoItemEmbarcacao> listaDocumentoItemEmbarcacao;
    /*     */    List<DocumentoItemEquipamento> listaDocumentoItemEquipamento;
    /*     */    List<DocumentoOcorrencia> listaDocumentoOcorrencia;
    /*     */    List<DocumentoComentario> listaDocumentoComentario;
    /*     */    List<String> listaNomeEquipamento;

    /*     */
 /*     */ public Documento() /*     */ {
        /* 128 */ this.listaDocumentoItemMaterial = new ArrayList();
        /* 129 */ this.listaDocumentoItemFinanceiro = new ArrayList();
        /* 130 */ this.listaDocumentoItemServico = new ArrayList();
        /* 131 */ this.listaDocumentoItemEmbarcacao = new ArrayList();
        /* 132 */ this.listaDocumentoItemEquipamento = new ArrayList();
        /* 133 */ this.listaDocumentoOcorrencia = new ArrayList();
        /* 134 */ this.listaNomeEquipamento = new ArrayList();
        /*     */    }

    /*     */
 /*     */ public String getSeqDocumento() /*     */ {
        /* 139 */ return this.seqDocumento;
        /*     */    }

    /*     */
 /*     */ public void setSeqDocumento(String seqDocumento) {
        /* 143 */ this.seqDocumento = seqDocumento;
        /*     */    }

    /*     */
 /*     */ public String getCodigo() {
        /* 147 */ return this.codigo;
        /*     */    }

    /*     */
 /*     */ public void setCodigo(String codigo) {
        /* 151 */ this.codigo = codigo;
        /*     */    }

    /*     */
 /*     */ public String getSeqEmpresa() {
        /* 155 */ return this.seqEmpresa;
        /*     */    }

    /*     */
 /*     */ public void setSeqEmpresa(String seqEmpresa) {
        /* 159 */ this.seqEmpresa = seqEmpresa;
        /*     */    }

    /*     */
 /*     */ public String getSeqParceiro() {
        /* 163 */ return this.seqParceiro;
        /*     */    }

    /*     */
 /*     */ public void setSeqParceiro(String seqParceiro) {
        /* 167 */ this.seqParceiro = seqParceiro;
        /*     */    }

    /*     */
 /*     */ public String getSeqTipoDocumento() {
        /* 171 */ return this.seqTipoDocumento;
        /*     */    }

    /*     */
 /*     */ public void setSeqTipoDocumento(String seqTipoDocumento) {
        /* 175 */ this.seqTipoDocumento = seqTipoDocumento;
        /*     */    }

    /*     */
 /*     */ public String getSeqDocumentoEtapa() {
        /* 179 */ return this.seqDocumentoEtapa;
        /*     */    }

    /*     */
 /*     */ public void setSeqDocumentoEtapa(String seqDocumentoEtapa) {
        /* 183 */ this.seqDocumentoEtapa = seqDocumentoEtapa;
        /*     */    }

    /*     */
 /*     */ public Date getDataCadastro() {
        /* 187 */ return this.dataCadastro;
        /*     */    }

    /*     */
 /*     */ public void setDataCadastro(Date dataCadastro) {
        /* 191 */ this.dataCadastro = dataCadastro;
        /*     */    }

    /*     */
 /*     */ public String getSeqUsuarioCadastro() {
        /* 195 */ return this.seqUsuarioCadastro;
        /*     */    }

    /*     */
 /*     */ public void setSeqUsuarioCadastro(String seqUsuarioCadastro) {
        /* 199 */ this.seqUsuarioCadastro = seqUsuarioCadastro;
        /*     */    }

    /*     */
 /*     */ public String getSeqFormaPagamento() {
        /* 203 */ return this.seqFormaPagamento;
        /*     */    }

    /*     */
 /*     */ public void setSeqFormaPagamento(String seqFormaPagamento) {
        /* 207 */ this.seqFormaPagamento = seqFormaPagamento;
        /*     */    }

    /*     */
 /*     */ public String getSeqConta() {
        /* 211 */ return this.seqConta;
        /*     */    }

    /*     */
 /*     */ public void setSeqConta(String seqConta) {
        /* 215 */ this.seqConta = seqConta;
        /*     */    }

    /*     */
 /*     */ public String getSeqCondicaoPagamento() {
        /* 219 */ return this.seqCondicaoPagamento;
        /*     */    }

    /*     */
 /*     */ public void setSeqCondicaoPagamento(String seqCondicaoPagamento) {
        /* 223 */ this.seqCondicaoPagamento = seqCondicaoPagamento;
        /*     */    }

    /*     */
 /*     */ public String getSeqTipoMovimentoFinanceiro() {
        /* 227 */ return this.seqTipoMovimentoFinanceiro;
        /*     */    }

    /*     */
 /*     */ public void setSeqTipoMovimentoFinanceiro(String seqTipoMovimentoFinanceiro) {
        /* 231 */ this.seqTipoMovimentoFinanceiro = seqTipoMovimentoFinanceiro;
        /*     */    }

    /*     */
 /*     */ public String getDescricao() {
        /* 235 */ return this.descricao;
        /*     */    }

    /*     */
 /*     */ public void setDescricao(String descricao) {
        /* 239 */ this.descricao = descricao;
        /*     */    }

    /*     */
 /*     */ public Date getData() {
        /* 243 */ return this.data;
        /*     */    }

    /*     */
 /*     */ public void setData(Date data) {
        /* 247 */ this.data = data;
        /*     */    }

    /*     */
 /*     */ public String getParceiro() {
        /* 251 */ return this.parceiro;
        /*     */    }

    /*     */
 /*     */ public void setParceiro(String parceiro) {
        /* 255 */ this.parceiro = parceiro;
        /*     */    }

    /*     */
 /*     */ public String getParceiroTipoPessoa() {
        /* 259 */ return this.parceiroTipoPessoa;
        /*     */    }

    /*     */
 /*     */ public void setParceiroTipoPessoa(String parceiroTipoPessoa) {
        /* 263 */ this.parceiroTipoPessoa = parceiroTipoPessoa;
        /*     */    }

    /*     */
 /*     */ public String getParceiroEndereco() {
        /* 267 */ return this.parceiroEndereco;
        /*     */    }

    /*     */
 /*     */ public void setParceiroEndereco(String parceiroEndereco) {
        /* 271 */ this.parceiroEndereco = parceiroEndereco;
        /*     */    }

    /*     */
 /*     */ public String getParceiroDocumento() {
        /* 275 */ return this.parceiroDocumento;
        /*     */    }

    /*     */
 /*     */ public void setParceiroDocumento(String parceiroDocumento) {
        /* 279 */ this.parceiroDocumento = parceiroDocumento;
        /*     */    }

    /*     */
 /*     */ public String getParceiroTelefone() {
        /* 283 */ return this.parceiroTelefone;
        /*     */    }

    /*     */
 /*     */ public void setParceiroTelefone(String parceiroTelefone) {
        /* 287 */ this.parceiroTelefone = parceiroTelefone;
        /*     */    }

    /*     */
 /*     */ public String getParceiroEmail() {
        /* 291 */ return this.parceiroEmail;
        /*     */    }

    /*     */
 /*     */ public void setParceiroEmail(String parceiroEmail) {
        /* 295 */ this.parceiroEmail = parceiroEmail;
        /*     */    }

    /*     */
 /*     */ public String getTipoParceiro() {
        /* 299 */ return this.tipoParceiro;
        /*     */    }

    /*     */
 /*     */ public void setTipoParceiro(String tipoParceiro) {
        /* 303 */ this.tipoParceiro = tipoParceiro;
        /*     */    }

    /*     */
 /*     */ public String getEtapa() {
        /* 307 */ return this.etapa;
        /*     */    }

    /*     */
 /*     */ public void setEtapa(String etapa) {
        /* 311 */ this.etapa = etapa;
        /*     */    }

    /*     */
 /*     */ public String getTipoDocumento() {
        /* 315 */ return this.tipoDocumento;
        /*     */    }

    /*     */
 /*     */ public void setTipoDocumento(String tipoDocumento) {
        /* 319 */ this.tipoDocumento = tipoDocumento;
        /*     */    }

    /*     */
 /*     */ public String getUsuarioCadastro() {
        /* 323 */ return this.usuarioCadastro;
        /*     */    }

    /*     */
 /*     */ public void setUsuarioCadastro(String usuarioCadastro) {
        /* 327 */ this.usuarioCadastro = usuarioCadastro;
        /*     */    }

    /*     */
 /*     */ public String getSeqLugar() {
        /* 331 */ return this.seqLugar;
        /*     */    }

    /*     */
 /*     */ public void setSeqLugar(String seqLugar) {
        /* 335 */ this.seqLugar = seqLugar;
        /*     */    }

    /*     */
 /*     */ public Date getDataPrevisaoConclusao() {
        /* 339 */ return this.dataPrevisaoConclusao;
        /*     */    }

    /*     */
 /*     */ public void setDataPrevisaoConclusao(Date dataPrevisaoConclusao) {
        /* 343 */ this.dataPrevisaoConclusao = dataPrevisaoConclusao;
        /*     */    }

    /*     */
 /*     */ public List<DocumentoItemMaterial> getListaDocumentoItemMaterial() {
        /* 347 */ return this.listaDocumentoItemMaterial;
        /*     */    }

    /*     */
 /*     */ public void setListaDocumentoItemMaterial(List<DocumentoItemMaterial> listaDocumentoItemMaterial) {
        /* 351 */ this.listaDocumentoItemMaterial = listaDocumentoItemMaterial;
        /*     */    }

    /*     */
 /*     */ public List<DocumentoItemFinanceiro> getListaDocumentoItemFinanceiro() {
        /* 355 */ return this.listaDocumentoItemFinanceiro;
        /*     */    }

    /*     */
 /*     */ public void setListaDocumentoItemFinanceiro(List<DocumentoItemFinanceiro> listaDocumentoItemFinanceiro) {
        /* 359 */ this.listaDocumentoItemFinanceiro = listaDocumentoItemFinanceiro;
        /*     */    }

    /*     */
 /*     */ public List<DocumentoItemServico> getListaDocumentoItemServico() {
        /* 363 */ return this.listaDocumentoItemServico;
        /*     */    }

    /*     */
 /*     */ public void setListaDocumentoItemServico(List<DocumentoItemServico> listaDocumentoItemServico) {
        /* 367 */ this.listaDocumentoItemServico = listaDocumentoItemServico;
        /*     */    }

    /*     */
 /*     */ public List<DocumentoItemEmbarcacao> getListaDocumentoItemEmbarcacao() {
        /* 371 */ return this.listaDocumentoItemEmbarcacao;
        /*     */    }

    /*     */
 /*     */ public void setListaDocumentoItemEmbarcacao(List<DocumentoItemEmbarcacao> listaDocumentoItemEmbarcacao) {
        /* 375 */ this.listaDocumentoItemEmbarcacao = listaDocumentoItemEmbarcacao;
        /*     */    }

    /*     */
 /*     */ public List<DocumentoItemEquipamento> getListaDocumentoItemEquipamento() {
        /* 379 */ return this.listaDocumentoItemEquipamento;
        /*     */    }

    /*     */
 /*     */ public void setListaDocumentoItemEquipamento(List<DocumentoItemEquipamento> listaDocumentoItemEquipamento) {
        /* 383 */ this.listaDocumentoItemEquipamento = listaDocumentoItemEquipamento;
        /*     */    }

    /*     */
 /*     */ public List<DocumentoOcorrencia> getListaDocumentoOcorrencia() {
        /* 387 */ return this.listaDocumentoOcorrencia;
        /*     */    }

    /*     */
 /*     */ public void setListaDocumentoOcorrencia(List<DocumentoOcorrencia> listaDocumentoOcorrencia) {
        /* 391 */ this.listaDocumentoOcorrencia = listaDocumentoOcorrencia;
        /*     */    }

    /*     */
 /*     */ public String getTemplate() {
        /* 395 */ return this.template;
        /*     */    }

    /*     */
 /*     */ public void setTemplate(String template) {
        /* 399 */ this.template = template;
        /*     */    }

    /*     */
 /*     */ public String getSeqDocumentoDono() {
        /* 403 */ return this.seqDocumentoDono;
        /*     */    }

    /*     */
 /*     */ public void setSeqDocumentoDono(String seqDocumentoDono) {
        /* 407 */ this.seqDocumentoDono = seqDocumentoDono;
        /*     */    }

    /*     */
 /*     */ public String getDocDonoTipo() {
        /* 411 */ return this.DocDonoTipo;
        /*     */    }

    /*     */
 /*     */ public void setDocDonoTipo(String DocDonoTipo) {
        /* 415 */ this.DocDonoTipo = DocDonoTipo;
        /*     */    }

    /*     */
 /*     */ public String getDocDonoCodigo() {
        /* 419 */ return this.DocDonoCodigo;
        /*     */    }

    /*     */
 /*     */ public void setDocDonoCodigo(String DocDonoCodigo) {
        /* 423 */ this.DocDonoCodigo = DocDonoCodigo;
        /*     */    }

    /*     */
 /*     */ public String getSeqAssColaborador() {
        /* 427 */ return this.seqAssColaborador;
        /*     */    }

    /*     */
 /*     */ public void setSeqAssColaborador(String seqAssColaborador) {
        /* 431 */ this.seqAssColaborador = seqAssColaborador;
        /*     */    }

    /*     */
 /*     */ public String getnContrato() {
        /* 435 */ return this.nContrato;
        /*     */    }

    /*     */
 /*     */ public void setnContrato(String nContrato) {
        /* 439 */ this.nContrato = nContrato;
        /*     */    }

    /*     */
 /*     */ public String getReembolsoTransporte() {
        /* 443 */ return this.reembolsoTransporte;
        /*     */    }

    /*     */
 /*     */ public void setReembolsoTransporte(String reembolsoTransporte) {
        /* 447 */ this.reembolsoTransporte = reembolsoTransporte;
        /*     */    }

    /*     */
 /*     */ public String getReembolsoAlimentacao() {
        /* 451 */ return this.reembolsoAlimentacao;
        /*     */    }

    /*     */
 /*     */ public void setReembolsoAlimentacao(String reembolsoAlimentacao) {
        /* 455 */ this.reembolsoAlimentacao = reembolsoAlimentacao;
        /*     */    }

    /*     */
 /*     */ public String getReembolsoHospedagem() {
        /* 459 */ return this.reembolsoHospedagem;
        /*     */    }

    /*     */
 /*     */ public void setReembolsoHospedagem(String reembolsoHospedagem) {
        /* 463 */ this.reembolsoHospedagem = reembolsoHospedagem;
        /*     */    }

    /*     */
 /*     */ public String getReembolsoOutros() {
        /* 467 */ return this.reembolsoOutros;
        /*     */    }

    /*     */
 /*     */ public String getNvEmbarcacaoNome() {
        /* 471 */ return this.nvEmbarcacaoNome;
        /*     */    }

    /*     */
 /*     */ public void setNvEmbarcacaoNome(String nvEmbarcacaoNome) {
        /* 475 */ this.nvEmbarcacaoNome = nvEmbarcacaoNome;
        /*     */    }

    /*     */ public String getMaterialNome() {
        /* 471 */ return this.materialNome;
        /*     */    }

    /*     */
 /*     */ public void setMaterialNome(String materialNome) {
        /* 475 */ this.materialNome = materialNome;
        /*     */    }

    /*     */
 /*     */ public void setReembolsoOutros(String reembolsoOutros) {
        /* 479 */ this.reembolsoOutros = reembolsoOutros;
        /*     */    }

    /*     */
 /*     */ public String getReembolsoPlotagens() {
        /* 483 */ return this.reembolsoPlotagens;
        /*     */    }

    /*     */
 /*     */ public void setReembolsoPlotagens(String reembolsoPlotagens) {
        /* 487 */ this.reembolsoPlotagens = reembolsoPlotagens;
        /*     */    }

    /*     */
 /*     */ public String getLocalServico() {
        /* 491 */ return this.localServico;
        /*     */    }

    /*     */
 /*     */ public void setLocalServico(String localServico) {
        /* 495 */ this.localServico = localServico;
        /*     */    }

    /*     */
 /*     */ public BigDecimal getVlEntrada() {
        /* 499 */ return this.vlEntrada;
        /*     */    }

    /*     */
 /*     */ public void setVlEntrada(BigDecimal vlEntrada) {
        /* 503 */ this.vlEntrada = vlEntrada;
        /*     */    }

    /*     */
 /*     */ public BigDecimal getQtdeParcela() {
        /* 507 */ return this.qtdeParcela;
        /*     */    }

    /*     */
 /*     */ public void setQtdeParcela(BigDecimal qtdeParcela) {
        /* 511 */ this.qtdeParcela = qtdeParcela;
        /*     */    }

    /*     */
 /*     */ public String getDatasParcelado() {
        /* 515 */ return this.DatasParcelado;
        /*     */    }

    /*     */
 /*     */ public void setDatasParcelado(String DatasParcelado) {
        /* 519 */ this.DatasParcelado = DatasParcelado;
        /*     */    }

    /*     */
 /*     */ public BigDecimal getVlTotal() {
        /* 523 */ return this.vlTotal;
        /*     */    }

    /*     */
 /*     */ public void setVlTotal(BigDecimal vlTotal) {
        /* 527 */ this.vlTotal = vlTotal;
        /*     */    }

    /*     */
 /*     */ public String getTributosImpostos() {
        /* 531 */ return this.tributosImpostos;
        /*     */    }

    /*     */
 /*     */ public void setTributosImpostos(String tributosImpostos) {
        /* 535 */ this.tributosImpostos = tributosImpostos;
        /*     */    }

    /*     */
 /*     */ public String getSeqParceiroContato() {
        /* 539 */ return this.seqParceiroContato;
        /*     */    }

    /*     */
 /*     */ public void setSeqParceiroContato(String seqParceiroContato) {
        /* 543 */ this.seqParceiroContato = seqParceiroContato;
        /*     */    }

    /*     */
 /*     */ public String getSeqParceiroContaPagar() {
        /* 547 */ return this.seqParceiroContaPagar;
        /*     */    }

    /*     */
 /*     */ public void setSeqParceiroContaPagar(String seqParceiroContaPagar) {
        /* 551 */ this.seqParceiroContaPagar = seqParceiroContaPagar;
        /*     */    }

    /*     */
 /*     */ public String getOrigemParceiro() {
        /* 555 */ return this.origemParceiro;
        /*     */    }

    /*     */
 /*     */ public void setOrigemParceiro(String origemParceiro) {
        /* 559 */ this.origemParceiro = origemParceiro;
        /*     */    }

    /*     */
 /*     */ public String getSeqUnidadeNegocio() {
        /* 563 */ return this.seqUnidadeNegocio;
        /*     */    }

    /*     */
 /*     */ public void setSeqUnidadeNegocio(String seqUnidadeNegocio) {
        /* 567 */ this.seqUnidadeNegocio = seqUnidadeNegocio;
        /*     */    }

    /*     */
 /*     */ public String getComentarioEtapa() {
        /* 571 */ return this.comentarioEtapa;
        /*     */    }

    /*     */
 /*     */ public void setComentarioEtapa(String comentarioEtapa) {
        /* 575 */ this.comentarioEtapa = comentarioEtapa;
        /*     */    }

    /*     */
 /*     */ public Long getIdRevisao() {
        /* 579 */ return this.idRevisao;
        /*     */    }

    /*     */
 /*     */ public void setIdRevisao(Long idRevisao) {
        /* 583 */ this.idRevisao = idRevisao;
        /*     */    }

    /*     */
 /*     */ public BigDecimal getRetencaoISSQN() {
        /* 587 */ return this.retencaoISSQN;
        /*     */    }

    /*     */
 /*     */ public void setRetencaoISSQN(BigDecimal retencaoISSQN) {
        /* 591 */ this.retencaoISSQN = retencaoISSQN;
        /*     */    }

    /*     */
 /*     */ public BigDecimal getSeqProjecaoTributaria() {
        /* 595 */ return this.seqProjecaoTributaria;
        /*     */    }

    /*     */
 /*     */ public void setSeqProjecaoTributaria(BigDecimal seqProjecaoTributaria) {
        /* 599 */ this.seqProjecaoTributaria = seqProjecaoTributaria;
        /*     */    }

    /*     */
 /*     */ public BigDecimal getAliquotaISSQNretido() {
        /* 603 */ return this.aliquotaISSQNretido;
        /*     */    }

    /*     */
 /*     */ public void setAliquotaISSQNretido(BigDecimal aliquotaISSQNretido) {
        /* 607 */ this.aliquotaISSQNretido = aliquotaISSQNretido;
        /*     */    }

    /*     */
 /*     */ public BigDecimal getSeqAliquotaFederal() {
        /* 611 */ return this.seqAliquotaFederal;
        /*     */    }

    /*     */
 /*     */ public void setSeqAliquotaFederal(BigDecimal seqAliquotaFederal) {
        /* 615 */ this.seqAliquotaFederal = seqAliquotaFederal;
        /*     */    }

    /*     */
 /*     */ public BigDecimal getVlExecutadoAcumulado() {
        /* 619 */ return this.vlExecutadoAcumulado;
        /*     */    }

    /*     */
 /*     */ public void setVlExecutadoAcumulado(BigDecimal vlExecutadoAcumulado) {
        /* 623 */ this.vlExecutadoAcumulado = vlExecutadoAcumulado;
        /*     */    }

    /*     */
 /*     */ public BigDecimal getVlTributosNaoIncluso() {
        /* 627 */ return this.vlTributosNaoIncluso;
        /*     */    }

    /*     */
 /*     */ public void setVlTributosNaoIncluso(BigDecimal vlTributosNaoIncluso) {
        /* 631 */ this.vlTributosNaoIncluso = vlTributosNaoIncluso;
        /*     */    }

    /*     */
 /*     */ public BigDecimal getVlBruto() {
        /* 635 */ return this.vlBruto;
        /*     */    }

    /*     */
 /*     */ public void setVlBruto(BigDecimal vlBruto) {
        /* 639 */ this.vlBruto = vlBruto;
        /*     */    }

    /*     */
 /*     */ public BigDecimal getVlISSQNRetido() {
        /* 643 */ return this.vlISSQNRetido;
        /*     */    }

    /*     */
 /*     */ public void setVlISSQNRetido(BigDecimal vlISSQNRetido) {
        /* 647 */ this.vlISSQNRetido = vlISSQNRetido;
        /*     */    }

    /*     */
 /*     */ public BigDecimal getVlRetencaoFederal() {
        /* 651 */ return this.vlRetencaoFederal;
        /*     */    }

    /*     */
 /*     */ public void setVlRetencaoFederal(BigDecimal vlRetencaoFederal) {
        /* 655 */ this.vlRetencaoFederal = vlRetencaoFederal;
        /*     */    }

    /*     */
 /*     */ public BigDecimal getVlLiquidoAReceber() {
        /* 659 */ return this.vlLiquidoAReceber;
        /*     */    }

    /*     */
 /*     */ public void setVlLiquidoAReceber(BigDecimal vlLiquidoAReceber) {
        /* 663 */ this.vlLiquidoAReceber = vlLiquidoAReceber;
        /*     */    }

    /*     */
 /*     */ public BigDecimal getTarifaBancaria() {
        /* 667 */ return this.tarifaBancaria;
        /*     */    }

    /*     */
 /*     */ public void setTarifaBancaria(BigDecimal tarifaBancaria) {
        /* 671 */ this.tarifaBancaria = tarifaBancaria;
        /*     */    }

    /*     */
 /*     */ public int getSequencia() {
        /* 675 */ return this.sequencia;
        /*     */    }

    /*     */
 /*     */ public void setSequencia(int sequencia) {
        /* 679 */ this.sequencia = sequencia;
        /*     */    }

    /*     */
 /*     */ public String getFechado() {
        /* 683 */ return this.fechado;
        /*     */    }

    /*     */
 /*     */ public void setFechado(String fechado) {
        /* 687 */ this.fechado = fechado;
        /*     */    }

    /*     */
 /*     */ public String getGrupoServico() {
        /* 691 */ return this.grupoServico;
        /*     */    }

    /*     */
 /*     */ public void setGrupoServico(String grupoServico) {
        /* 695 */ this.grupoServico = grupoServico;
        /*     */    }

    /*     */
 /*     */ public String getDocDonoSeqTipo() {
        /* 699 */ return this.DocDonoSeqTipo;
        /*     */    }

    /*     */
 /*     */ public void setDocDonoSeqTipo(String DocDonoSeqTipo) {
        /* 703 */ this.DocDonoSeqTipo = DocDonoSeqTipo;
        /*     */    }

    /*     */
 /*     */ public String getMoeda() {
        /* 707 */ return this.moeda;
        /*     */    }

    /*     */
 /*     */ public void setMoeda(String moeda) {
        /* 711 */ this.moeda = moeda;
        /*     */    }

    /*     */
 /*     */ public List<DocumentoComentario> getListaDocumentoComentario() {
        /* 715 */ return this.listaDocumentoComentario;
        /*     */    }

    /*     */
 /*     */ public void setListaDocumentoComentario(List<DocumentoComentario> listaDocumentoComentario) {
        /* 719 */ this.listaDocumentoComentario = listaDocumentoComentario;
        /*     */    }

    /*     */
 /*     */ public Long getIdComplementar() {
        /* 723 */ return this.idComplementar;
        /*     */    }

    /*     */
 /*     */ public void setIdComplementar(Long idComplementar) {
        /* 727 */ this.idComplementar = idComplementar;
        /*     */    }

    /*     */
 /*     */ public String getFilial() {
        /* 731 */ return this.filial;
        /*     */    }

    /*     */
 /*     */ public void setFilial(String filial) {
        /* 735 */ this.filial = filial;
        /*     */    }

    /*     */
 /*     */ public String getOrdemCobranca() {
        /* 739 */ return this.ordemCobranca;
        /*     */    }

    /*     */
 /*     */ public void setOrdemCobranca(String ordemCobranca) {
        /* 743 */ this.ordemCobranca = ordemCobranca;
        /*     */    }

    /*     */
 /*     */ public String getBoletimMedicao() {
        /* 747 */ return this.boletimMedicao;
        /*     */    }

    /*     */
 /*     */ public void setBoletimMedicao(String boletimMedicao) {
        /* 751 */ this.boletimMedicao = boletimMedicao;
        /*     */    }

    /*     */
 /*     */ public List<String> getListaNomeEquipamento() {
        /* 755 */ return this.listaNomeEquipamento;
        /*     */    }

    /*     */
 /*     */ public void setListaNomeEquipamento(List<String> listaNomeEquipamento) {
        /* 759 */ this.listaNomeEquipamento = listaNomeEquipamento;
        /*     */    }

    /*     */
 /*     */ public String getEquipamentoNome() {
        /* 763 */ return this.equipamentoNome;
        /*     */    }

    /*     */
 /*     */ public void setEquipamentoNome(String equipamentoNome) {
        /* 767 */ this.equipamentoNome = equipamentoNome;
        /*     */    }

    /*     */
 /*     */ public String getObservacao() {
        /* 771 */ return this.observacao;
        /*     */    }

    /*     */
 /*     */ public void setObservacao(String observacao) {
        /* 775 */ this.observacao = observacao;
        /*     */    }

    /*     */
 /*     */ public BigDecimal getTaxaCambio() {
        /* 779 */ return this.taxaCambio;
        /*     */    }

    /*     */
 /*     */ public void setTaxaCambio(BigDecimal taxaCambio) {
        /* 783 */ this.taxaCambio = taxaCambio;
        /*     */    }

    /*     */
 /*     */ public String getNatureza() {
        /* 787 */ return this.natureza;
        /*     */    }

    /*     */
 /*     */ public void setNatureza(String natureza) {
        /* 791 */ this.natureza = natureza;
        /*     */    }

    /*     */
 /*     */ public String getCategoria() {
        /* 795 */ return this.categoria;
        /*     */    }

    /*     */
 /*     */ public void setCategoria(String categoria) {
        /* 799 */ this.categoria = categoria;
        /*     */    }

    /*     */
 /*     */ public String getEstadoBM() {
        /* 803 */ return this.estadoBM;
        /*     */    }

    /*     */
 /*     */ public void setEstadoBM(String estadoBM) {
        /* 807 */ this.estadoBM = estadoBM;
        /*     */    }

    /*     */
 /*     */ public String getMoedaDestino() {
        /* 811 */ return this.moedaDestino;
        /*     */    }

    /*     */
 /*     */ public void setMoedaDestino(String moedaDestino) {
        /* 815 */ this.moedaDestino = moedaDestino;
        /*     */    }

    /*     */
 /*     */ public String getIdentificacaoListaPlanos() {
        /* 819 */ return this.identificacaoListaPlanos;
        /*     */    }

    /*     */
 /*     */ public void setIdentificacaoListaPlanos(String identificacaoListaPlanos) {
        /* 823 */ this.identificacaoListaPlanos = identificacaoListaPlanos;
        /*     */    }

    /*     */
 /*     */ public String getUnidadeNegocioUsuario() {
        /* 827 */ return this.unidadeNegocioUsuario;
        /*     */    }

    /*     */
 /*     */ public void setUnidadeNegocioUsuario(String unidadeNegocioUsuario) {
        /* 831 */ this.unidadeNegocioUsuario = unidadeNegocioUsuario;
    }

    /*     */ public String getEscopo() {
        /* 827 */ return this.escopo;
        /*     */    }

    /*     */
 /*     */ public void setEscopo(String escopo) {
        /* 831 */ this.escopo = escopo;

        /*     */    }

    /*     */ public BigDecimal getVltotalcambio() {
        /* 827 */ return this.vlTotalcambio;
        /*     */    }

    /*     */
 /*     */ public void setVltotalcambio(BigDecimal vlTotalcambio) {
        /* 831 */ this.vlTotalcambio = vlTotalcambio;

        /*     */    }

    /*     */ public String getMostraTotal() {
        /* 827 */ return this.mostraTotal;
        /*     */    }

    /*     */
 /*     */ public void setMostraTotal(String mostraTotal) {
        /* 831 */ this.mostraTotal = mostraTotal;

        /*     */    }

    /*     */ public String getCaminho() {
        /* 827 */ return this.caminho;
        /*     */    }

    /*     */
 /*     */ public void setCaminho(String caminho) {
        /* 831 */ this.caminho = caminho;

        /*     */    }

    /*     */ public String getObjetivo() {
        /* 827 */ return this.objetivo;
        /*     */    }

    /*     */
 /*     */ public void setObjetivo(String objetivo) {
        /* 831 */ this.objetivo = objetivo;

        /*     */    }

    /*     */ public String getValidade() {
        /* 827 */ return this.validade;
        /*     */    }

    /*     */
 /*     */ public void setValidade(String validade) {
        /* 831 */ this.validade = validade;

        /*     */    }

    /*     */ public String getDescRemuneracao() {
        /* 827 */ return this.descRemuneracao;
        /*     */    }

    /*     */
 /*     */ public void setDescRemuneracao(String descRemuneracao) {
        /* 831 */ this.descRemuneracao = descRemuneracao;

        /*     */    }

    /*     */ public String getCondicoesGerais() {
        /* 827 */ return this.condicoesGerais;
        /*     */    }

    /*     */
 /*     */ public void setCondicoesGerais(String condicoesGerais) {
        /* 831 */ this.condicoesGerais = condicoesGerais;

        /*     */    }

    /*     */ public String getCondpag() {
        /* 827 */ return this.condpag;
        /*     */    }

    /*     */
 /*     */ public void setCondpag(String condpag) {
        /* 831 */ this.condpag = condpag;

        /*     */    }

    /*     */ public String getAssunto() {
        /* 827 */ return this.assunto;
        /*     */    }

    /*     */
 /*     */ public void setAssunto(String assunto) {
        /* 831 */ this.assunto = assunto;

        /*     */    }

    public String getBoca() {
        return boca;
    }

    public void setBoca(String boca) {
        this.boca = boca;
    }

    public String getAb() {
        return ab;
    }

    public void setAb(String ab) {
        this.ab = ab;
    }
        public String getComprimento() {
        return comprimento;
    }

    public void setComprimento(String comprimento) {
        this.comprimento = comprimento;
    }
    /*     */ }
