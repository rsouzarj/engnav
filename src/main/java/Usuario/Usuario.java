/*     */ package Usuario;
/*     */ 
/*     */ import java.util.Date;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Usuario
/*     */ {
/*     */   private String seqUsuario;
/*     */   private Date dataCadastro;
/*     */   private String acParceiro;
/*     */   private String acDocumento;
/*     */   private String acOperacional;
/*     */   private String acCadastro;
/*     */   private String acOrganizacional;
/*     */   private String acRelParceiro;
/*     */   private String acRelDocumento;
/*     */   private String acRelPesqSatisfacao;
/*     */   private String acParNovo;
/*     */   private String acParTodos;
/*     */   private String seqEmpresa;
/*     */   private String seqUnidadeNegocio;
/*     */   private String seqDepartamento;
/*     */   private String nivel;
/*     */   private String seqParceiro;
/*     */   private String acessoErp;
/*     */   private String chaveOrigem;
/*     */   private String acDocNovo;
/*     */   private String acessoComercial;
/*     */   private String acRelatorio;
/*     */   private String acOpEmbarcacao;
/*     */   private String acOpEquipamento;
/*     */   private String acOpVistoria;
/*     */   private String acOpLicenca;
/*     */   private String acOpCertificado;
/*     */   private String acOpCadastro;
/*     */   private String acOpTvinculo;
/*     */   private String acOpTvistoria;
/*     */   private String acOpTcertificado;
/*     */   private String acOpTlicenca;
/*     */   private String acOpStatusVistoria;
/*     */   private String acOpCalculoData;
/*     */   private String acCadParceiro;
/*     */   private String acCadTparceiro;
/*     */   private String acCadTvinculo;
/*     */   private String acCadTcaracteristica;
/*     */   private String acCadTendereco;
/*     */   private String acCadDocumento;
/*     */   private String acCadTdocumento;
/*     */   private String acFinanceiro;
/*     */   private String acAdministrativo;
/*     */   private String acFinTLancamento;
/*     */   private String acFinTunidade;
/*     */   private String acFinEquipamento;
/*     */   private String acFinContas;
/*     */   private String acFinContasPagar;
/*     */   private String acFinContasReceber;
/*     */   private String acFinSolicitacao;
/*     */   private String acFinFFaturamento;
/*     */   private String acFinFpagamento;
/*     */   private String acFinCpagamento;
/*     */   private String acFinCategoria;
/*     */   private String acFinAliquotaRetencaoFederal;
/*     */   private String acFinInformacaoImportante;
/*     */   private String acFinProjecaoTributaria;
/*     */   private String acFinCadastro;
/*     */   private String acFinRLBM;
/*     */   private String acFinFaturamento;
/*     */   private String acFinBoletim;
/*     */   private String acFinCentroCusto;
/*     */   private String acComPlanoItem;
/*     */   private String acFinDocFiscal;
/*     */   private String acFinOrdemPagamento;
/*     */   private String acFinContaCorrente;
/*     */   private String acFinNotaDebito;
/*     */   private String acFinPrestacaoContas;
/*     */   private String acComercial;
/*     */   private String acComServico;
/*     */   private String acComTpreco;
/*     */   private String acComEscopo;
/*     */   private String acComPservico;
/*     */   private String acOrgUsuario;
/*     */   private String acOrgUnegocio;
/*     */   private String acOrgDepartamento;
/*     */   private String acOrgFuncao;
/*     */   private String acOrgColaborador;
/*     */   private String acOrgParametro;
            private String acAprovaVistoria;
            private String acOrgGerArquivo;
            private String acOrgListArquivo;
            private String acAprovaPO;
/*     */   private String usuario;
/*     */   private String senha;
/*     */   private String email;
/*     */   private String cor;
/*     */   private String situacao;
/*     */   private String calcularComissao;
/*     */   private String appSessao;
/*     */   
/*     */   public String getSeqUsuario()
/*     */   {
/* 104 */     return this.seqUsuario;
/*     */   }
/*     */   
/*     */   public void setSeqUsuario(String seqUsuario) {
/* 108 */     this.seqUsuario = seqUsuario;
/*     */   }
/*     */   
/*     */   public Date getDataCadastro() {
/* 112 */     return this.dataCadastro;
/*     */   }
/*     */   
/*     */   public void setDataCadastro(Date dataCadastro) {
/* 116 */     this.dataCadastro = dataCadastro;
/*     */   }
/*     */   
/*     */   public String getAcParceiro() {
/* 120 */     return this.acParceiro;
/*     */   }
/*     */   
/*     */   public void setAcParceiro(String acParceiro) {
/* 124 */     this.acParceiro = acParceiro;
/*     */   }
/*     */ 
/*     */   public String getAcOrgGerArquivo() {
/* 120 */     return this.acOrgGerArquivo;
/*     */   }
/*     */   
/*     */   public void setAcOrgGerArquivo(String acOrgGerArquivo) {
/* 124 */     this.acOrgGerArquivo = acOrgGerArquivo;
/*     */   }

/*     */   public String getAcDocumento() {
/* 128 */     return this.acDocumento;
/*     */   }
/*     */   
/*     */   public void setAcDocumento(String acDocumento) {
/* 132 */     this.acDocumento = acDocumento;
/*     */   }
/*     */   
/*     */   public String getAcOperacional() {
/* 136 */     return this.acOperacional;
/*     */   }
/*     */   
/*     */   public void setAcOperacional(String acOperacional) {
/* 140 */     this.acOperacional = acOperacional;
/*     */   }
/*     */   
/*     */   public String getAcCadastro() {
/* 144 */     return this.acCadastro;
/*     */   }
/*     */   
/*     */   public void setAcCadastro(String acCadastro) {
/* 148 */     this.acCadastro = acCadastro;
/*     */   }
/*     */   
/*     */   public String getAcOrganizacional() {
/* 152 */     return this.acOrganizacional;
/*     */   }
/*     */   
/*     */   public void setAcOrganizacional(String acOrganizacional) {
/* 156 */     this.acOrganizacional = acOrganizacional;
/*     */   }
/*     */   
/*     */   public String getAcRelParceiro() {
/* 160 */     return this.acRelParceiro;
/*     */   }
/*     */   
/*     */   public void setAcRelParceiro(String acRelParceiro) {
/* 164 */     this.acRelParceiro = acRelParceiro;
/*     */   }
/*     */   
/*     */   public String getAcRelDocumento() {
/* 168 */     return this.acRelDocumento;
/*     */   }
/*     */   
/*     */   public void setAcRelDocumento(String acRelDocumento) {
/* 172 */     this.acRelDocumento = acRelDocumento;
/*     */   }
/*     */   
/*     */   public String getAcParNovo() {
/* 176 */     return this.acParNovo;
/*     */   }
/*     */   
/*     */   public void setAcParNovo(String acParNovo) {
/* 180 */     this.acParNovo = acParNovo;
/*     */   }
/*     */   
/*     */   public String getAcParTodos() {
/* 184 */     return this.acParTodos;
/*     */   }
/*     */   
/*     */   public void setAcParTodos(String acParTodos) {
/* 188 */     this.acParTodos = acParTodos;
/*     */   }
/*     */   
/*     */   public String getSeqEmpresa() {
/* 192 */     return this.seqEmpresa;
/*     */   }
/*     */   
/*     */   public void setSeqEmpresa(String seqEmpresa) {
/* 196 */     this.seqEmpresa = seqEmpresa;
/*     */   }
/*     */   
/*     */   public String getSeqUnidadeNegocio() {
/* 200 */     return this.seqUnidadeNegocio;
/*     */   }
/*     */   
/*     */   public String getAcFinDocFiscal() {
/* 204 */     return this.acFinDocFiscal;
/*     */   }
/*     */   
/*     */   public void setAcFinDocFiscal(String acFinDocFiscal) {
/* 208 */     this.acFinDocFiscal = acFinDocFiscal;
/*     */   }
/*     */   
/*     */   public void setSeqUnidadeNegocio(String seqUnidadeNegocio) {
/* 212 */     this.seqUnidadeNegocio = seqUnidadeNegocio;
/*     */   }
/*     */   
/*     */   public String getSeqDepartamento() {
/* 216 */     return this.seqDepartamento;
/*     */   }
/*     */   
/*     */   public void setSeqDepartamento(String seqDepartamento) {
/* 220 */     this.seqDepartamento = seqDepartamento;
/*     */   }
/*     */   
/*     */   public String getNivel() {
/* 224 */     return this.nivel;
/*     */   }
/*     */   
/*     */   public void setNivel(String nivel) {
/* 228 */     this.nivel = nivel;
/*     */   }
/*     */   
/*     */   public String getSeqParceiro() {
/* 232 */     return this.seqParceiro;
/*     */   }
/*     */   
/*     */   public void setSeqParceiro(String seqParceiro) {
/* 236 */     this.seqParceiro = seqParceiro;
/*     */   }
/*     */   
/*     */   public String getAcessoErp() {
/* 240 */     return this.acessoErp;
/*     */   }
/*     */   
/*     */   public void setAcessoErp(String acessoErp) {
/* 244 */     this.acessoErp = acessoErp;
/*     */   }
/*     */   
/*     */   public String getChaveOrigem() {
/* 248 */     return this.chaveOrigem;
/*     */   }
/*     */   
/*     */   public void setChaveOrigem(String chaveOrigem) {
/* 252 */     this.chaveOrigem = chaveOrigem;
/*     */   }
/*     */   
/*     */   public String getAcDocNovo() {
/* 256 */     return this.acDocNovo;
/*     */   }
/*     */   
/*     */   public void setAcDocNovo(String acDocNovo) {
/* 260 */     this.acDocNovo = acDocNovo;
/*     */   }
/*     */   
/*     */   public String getAcessoComercial() {
/* 264 */     return this.acessoComercial;
/*     */   }
/*     */   
/*     */   public void setAcessoComercial(String acessoComercial) {
/* 268 */     this.acessoComercial = acessoComercial;
/*     */   }
/*     */   
/*     */   public String getAcRelatorio() {
/* 272 */     return this.acRelatorio;
/*     */   }
/*     */   
/*     */   public void setAcRelatorio(String acRelatorio) {
/* 276 */     this.acRelatorio = acRelatorio;
/*     */   }
/*     */   
/*     */   public String getAcOpEmbarcacao() {
/* 280 */     return this.acOpEmbarcacao;
/*     */   }
/*     */   
/*     */   public void setAcOpEmbarcacao(String acOpEmbarcacao) {
/* 284 */     this.acOpEmbarcacao = acOpEmbarcacao;
/*     */   }
/*     */   
/*     */   public String getAcOpEquipamento() {
/* 288 */     return this.acOpEquipamento;
/*     */   }
/*     */   
/*     */   public void setAcOpEquipamento(String acOpEquipamento) {
/* 292 */     this.acOpEquipamento = acOpEquipamento;
/*     */   }
/*     */   
/*     */   public String getAcOpVistoria() {
/* 296 */     return this.acOpVistoria;
/*     */   }
/*     */   
/*     */   public void setAcOpVistoria(String acOpVistoria) {
/* 300 */     this.acOpVistoria = acOpVistoria;
/*     */   }
/*     */   
/*     */   public String getAcOpLicenca() {
/* 304 */     return this.acOpLicenca;
/*     */   }
/*     */   
/*     */   public void setAcOpLicenca(String acOpLicenca) {
/* 308 */     this.acOpLicenca = acOpLicenca;
/*     */   }
/*     */   
/*     */   public String getAcOpCertificado() {
/* 312 */     return this.acOpCertificado;
/*     */   }
/*     */   
/*     */   public void setAcOpCertificado(String acOpCertificado) {
/* 316 */     this.acOpCertificado = acOpCertificado;
/*     */   }
/*     */   
/*     */   public String getAcOpCadastro() {
/* 320 */     return this.acOpCadastro;
/*     */   }
/*     */   
/*     */   public void setAcOpCadastro(String acOpCadastro) {
/* 324 */     this.acOpCadastro = acOpCadastro;
/*     */   }
/*     */   
/*     */   public String getAcOpTvinculo() {
/* 328 */     return this.acOpTvinculo;
/*     */   }
/*     */   
/*     */   public void setAcOpTvinculo(String acOpTvinculo) {
/* 332 */     this.acOpTvinculo = acOpTvinculo;
/*     */   }
/*     */   
/*     */   public String getAcOpTvistoria() {
/* 336 */     return this.acOpTvistoria;
/*     */   }
/*     */   
/*     */   public void setAcOpTvistoria(String acOpTvistoria) {
/* 340 */     this.acOpTvistoria = acOpTvistoria;
/*     */   }
/*     */   
/*     */   public String getAcOpTcertificado() {
/* 344 */     return this.acOpTcertificado;
/*     */   }
/*     */   
/*     */   public void setAcOpTcertificado(String acOpTcertificado) {
/* 348 */     this.acOpTcertificado = acOpTcertificado;
/*     */   }
/*     */   
/*     */   public String getAcOpTlicenca() {
/* 352 */     return this.acOpTlicenca;
/*     */   }
/*     */   
/*     */   public void setAcOpTlicenca(String acOpTlicenca) {
/* 356 */     this.acOpTlicenca = acOpTlicenca;
/*     */   }
/*     */   
/*     */   public String getAcOpStatusVistoria() {
/* 360 */     return this.acOpStatusVistoria;
/*     */   }
/*     */   
/*     */   public void setAcOpStatusVistoria(String acOpStatusVistoria) {
/* 364 */     this.acOpStatusVistoria = acOpStatusVistoria;
/*     */   }
/*     */   
/*     */   public String getAcOpCalculoData() {
/* 368 */     return this.acOpCalculoData;
/*     */   }
/*     */   
/*     */   public void setAcOpCalculoData(String acOpCalculoData) {
/* 372 */     this.acOpCalculoData = acOpCalculoData;
/*     */   }
/*     */   
/*     */   public String getAcCadParceiro() {
/* 376 */     return this.acCadParceiro;
/*     */   }
/*     */   
/*     */   public void setAcCadParceiro(String acCadParceiro) {
/* 380 */     this.acCadParceiro = acCadParceiro;
/*     */   }
/*     */   
/*     */   public String getAcCadTparceiro() {
/* 384 */     return this.acCadTparceiro;
/*     */   }
/*     */   
/*     */   public void setAcCadTparceiro(String acCadTparceiro) {
/* 388 */     this.acCadTparceiro = acCadTparceiro;
/*     */   }
/*     */   
/*     */   public String getAcCadTvinculo() {
/* 392 */     return this.acCadTvinculo;
/*     */   }
/*     */   
/*     */   public void setAcCadTvinculo(String acCadTvinculo) {
/* 396 */     this.acCadTvinculo = acCadTvinculo;
/*     */   }
/*     */   
/*     */   public String getAcCadTcaracteristica() {
/* 400 */     return this.acCadTcaracteristica;
/*     */   }
/*     */   
/*     */   public void setAcCadTcaracteristica(String acCadTcaracteristica) {
/* 404 */     this.acCadTcaracteristica = acCadTcaracteristica;
/*     */   }
/*     */   
/*     */   public String getAcCadTendereco() {
/* 408 */     return this.acCadTendereco;
/*     */   }
/*     */   
/*     */   public void setAcCadTendereco(String acCadTendereco) {
/* 412 */     this.acCadTendereco = acCadTendereco;
/*     */   }
/*     */   
/*     */   public String getAcCadDocumento() {
/* 416 */     return this.acCadDocumento;
/*     */   }
/*     */   
/*     */   public void setAcCadDocumento(String acCadDocumento) {
/* 420 */     this.acCadDocumento = acCadDocumento;
/*     */   }
/*     */   
/*     */   public String getAcCadTdocumento() {
/* 424 */     return this.acCadTdocumento;
/*     */   }
/*     */   
/*     */   public void setAcCadTdocumento(String acCadTdocumento) {
/* 428 */     this.acCadTdocumento = acCadTdocumento;
/*     */   }
/*     */   
/*     */   public String getAcFinanceiro() {
/* 432 */     return this.acFinanceiro;
/*     */   }
/*     */   
/*     */   public void setAcFinanceiro(String acFinanceiro) {
/* 436 */     this.acFinanceiro = acFinanceiro;
/*     */   }
/*     */   
/*     */   public String getAcFinTLancamento() {
/* 440 */     return this.acFinTLancamento;
/*     */   }
/*     */   
/*     */   public void setAcFinTLancamento(String acFinTLancamento) {
/* 444 */     this.acFinTLancamento = acFinTLancamento;
/*     */   }
/*     */   
/*     */   public String getAcFinContas() {
/* 448 */     return this.acFinContas;
/*     */   }
/*     */   
/*     */   public void setAcFinContas(String acFinContas) {
/* 452 */     this.acFinContas = acFinContas;
/*     */   }
/*     */   
/*     */   public String getAcFinFpagamento() {
/* 456 */     return this.acFinFpagamento;
/*     */   }
/*     */   
/*     */   public void setAcFinFpagamento(String acFinFpagamento) {
/* 460 */     this.acFinFpagamento = acFinFpagamento;
/*     */   }
/*     */   
/*     */   public String getAcFinCpagamento() {
/* 464 */     return this.acFinCpagamento;
/*     */   }
/*     */   
/*     */   public void setAcFinCpagamento(String acFinCpagamento) {
/* 468 */     this.acFinCpagamento = acFinCpagamento;
/*     */   }
/*     */   
/*     */   public String getAcComercial() {
/* 472 */     return this.acComercial;
/*     */   }
/*     */   
/*     */   public void setAcComercial(String acComercial) {
/* 476 */     this.acComercial = acComercial;
/*     */   }
/*     */   
/*     */   public String getAcComServico() {
/* 480 */     return this.acComServico;
/*     */   }
/*     */   
/*     */   public void setAcComServico(String acComServico) {
/* 484 */     this.acComServico = acComServico;
/*     */   }
/*     */   
/*     */   public String getAcComTpreco() {
/* 488 */     return this.acComTpreco;
/*     */   }
/*     */   
/*     */   public void setAcComTpreco(String acComTpreco) {
/* 492 */     this.acComTpreco = acComTpreco;
/*     */   }
/*     */   
/*     */   public String getAcComEscopo() {
/* 496 */     return this.acComEscopo;
/*     */   }
/*     */   
/*     */   public void setAcComEscopo(String acComEscopo) {
/* 500 */     this.acComEscopo = acComEscopo;
/*     */   }
/*     */   
/*     */   public String getAcComPservico() {
/* 504 */     return this.acComPservico;
/*     */   }
/*     */   
/*     */   public void setAcComPservico(String acComPservico) {
/* 508 */     this.acComPservico = acComPservico;
/*     */   }
/*     */   
/*     */   public String getAcOrgUsuario() {
/* 512 */     return this.acOrgUsuario;
/*     */   }
/*     */   
/*     */   public void setAcOrgUsuario(String acOrgUsuario) {
/* 516 */     this.acOrgUsuario = acOrgUsuario;
/*     */   }
/*     */   
/*     */   public String getAcOrgUnegocio() {
/* 520 */     return this.acOrgUnegocio;
/*     */   }
/*     */   
/*     */   public void setAcOrgUnegocio(String acOrgUnegocio) {
/* 524 */     this.acOrgUnegocio = acOrgUnegocio;
/*     */   }
/*     */   
/*     */   public String getAcOrgDepartamento() {
/* 528 */     return this.acOrgDepartamento;
/*     */   }
/*     */   
/*     */   public void setAcOrgDepartamento(String acOrgDepartamento) {
/* 532 */     this.acOrgDepartamento = acOrgDepartamento;
/*     */   }
/*     */   
/*     */   public String getAcOrgFuncao() {
/* 536 */     return this.acOrgFuncao;
/*     */   }
/*     */   
/*     */   public void setAcOrgFuncao(String acOrgFuncao) {
/* 540 */     this.acOrgFuncao = acOrgFuncao;
/*     */   }
/*     */   
/*     */   public String getAcOrgColaborador() {
/* 544 */     return this.acOrgColaborador;
/*     */   }
/*     */   
/*     */   public void setAcOrgColaborador(String acOrgColaborador) {
/* 548 */     this.acOrgColaborador = acOrgColaborador;
/*     */   }
/*     */   
/*     */   public String getAcOrgParametro() {
/* 552 */     return this.acOrgParametro;
/*     */   }
/*     */   
/*     */   public void setAcOrgParametro(String acOrgParametro) {
/* 556 */     this.acOrgParametro = acOrgParametro;
/*     */   }
/*     */   
/*     */   public String getUsuario() {
/* 560 */     return this.usuario;
/*     */   }
/*     */   
/*     */   public void setUsuario(String usuario) {
/* 564 */     this.usuario = usuario;
/*     */   }
/*     */   
/*     */   public String getSenha() {
/* 568 */     return this.senha;
/*     */   }
/*     */   
/*     */   public void setSenha(String senha) {
/* 572 */     this.senha = senha;
/*     */   }
/*     */   
/*     */   public String getEmail() {
/* 576 */     return this.email;
/*     */   }
/*     */   
/*     */   public void setEmail(String email) {
/* 580 */     this.email = email;
/*     */   }
/*     */   
/*     */   public String getCor() {
/* 584 */     return this.cor;
/*     */   }
/*     */   
/*     */   public void setCor(String cor) {
/* 588 */     this.cor = cor;
/*     */   }
/*     */   
/*     */   public String getSituacao() {
/* 592 */     return this.situacao;
/*     */   }
/*     */   
/*     */   public void setSituacao(String situacao) {
/* 596 */     this.situacao = situacao;
/*     */   }
/*     */   
/*     */   public String getCalcularComissao() {
/* 600 */     return this.calcularComissao;
/*     */   }
/*     */   
/*     */   public void setCalcularComissao(String calcularComissao) {
/* 604 */     this.calcularComissao = calcularComissao;
/*     */   }
/*     */   
/*     */   public String getAppSessao() {
/* 608 */     return this.appSessao;
/*     */   }
/*     */   
/*     */   public void setAppSessao(String appSessao) {
/* 612 */     this.appSessao = appSessao;
/*     */   }
/*     */   
/*     */   public String getAcFinTunidade() {
/* 616 */     return this.acFinTunidade;
/*     */   }
/*     */   
/*     */   public void setAcFinTunidade(String acFinTunidade) {
/* 620 */     this.acFinTunidade = acFinTunidade;
/*     */   }
/*     */   
/*     */   public String getAcFinAliquotaRetencaoFederal() {
/* 624 */     return this.acFinAliquotaRetencaoFederal;
/*     */   }
/*     */   
/*     */   public void setAcFinAliquotaRetencaoFederal(String acFinAliquotaRetencaoFederal) {
/* 628 */     this.acFinAliquotaRetencaoFederal = acFinAliquotaRetencaoFederal;
/*     */   }
/*     */   
/*     */   public String getAcFinProjecaoTributaria() {
/* 632 */     return this.acFinProjecaoTributaria;
/*     */   }
/*     */   
/*     */   public void setAcFinProjecaoTributaria(String acFinProjecaoTributaria) {
/* 636 */     this.acFinProjecaoTributaria = acFinProjecaoTributaria;
/*     */   }
/*     */   
/*     */   public String getAcFinCadastro() {
/* 640 */     return this.acFinCadastro;
/*     */   }
/*     */   
/*     */   public void setAcFinCadastro(String acFinCadastro) {
/* 644 */     this.acFinCadastro = acFinCadastro;
/*     */   }
/*     */   
/*     */   public String getAcFinInformacaoImportante() {
/* 648 */     return this.acFinInformacaoImportante;
/*     */   }
/*     */   
/*     */   public void setAcFinInformacaoImportante(String acFinInformacaoImportante) {
/* 652 */     this.acFinInformacaoImportante = acFinInformacaoImportante;
/*     */   }
/*     */   
/*     */   public String getAcFinFaturamento() {
/* 656 */     return this.acFinFaturamento;
/*     */   }
/*     */   
/*     */   public void setAcFinFaturamento(String acFinFaturamento) {
/* 660 */     this.acFinFaturamento = acFinFaturamento;
/*     */   }
/*     */   
/*     */   public String getAcFinBoletim() {
/* 664 */     return this.acFinBoletim;
/*     */   }
/*     */   
/*     */   public void setAcFinBoletim(String acFinBoletim) {
/* 668 */     this.acFinBoletim = acFinBoletim;
/*     */   }
/*     */   
/*     */   public String getAcFinContasPagar() {
/* 672 */     return this.acFinContasPagar;
/*     */   }
/*     */   
/*     */   public void setAcFinContasPagar(String acFinContasPagar) {
/* 676 */     this.acFinContasPagar = acFinContasPagar;
/*     */   }
/*     */   
/*     */   public String getAcFinFFaturamento() {
/* 680 */     return this.acFinFFaturamento;
/*     */   }
/*     */   
/*     */   public void setAcFinFFaturamento(String acFinFFaturamento) {
/* 684 */     this.acFinFFaturamento = acFinFFaturamento;
/*     */   }
/*     */   
/*     */   public String getAcFinCentroCusto() {
/* 688 */     return this.acFinCentroCusto;
/*     */   }
/*     */   
/*     */   public void setAcFinCentroCusto(String acFinCentroCusto) {
/* 692 */     this.acFinCentroCusto = acFinCentroCusto;
/*     */   }
/*     */   
/*     */   public String getAcFinContasReceber() {
/* 696 */     return this.acFinContasReceber;
/*     */   }
/*     */   
/*     */   public void setAcFinContasReceber(String acFinContasReceber) {
/* 700 */     this.acFinContasReceber = acFinContasReceber;
/*     */   }
/*     */   
/*     */   public String getAcFinSolicitacao() {
/* 704 */     return this.acFinSolicitacao;
/*     */   }
/*     */   
/*     */   public void setAcFinSolicitacao(String acFinSolicitacao) {
/* 708 */     this.acFinSolicitacao = acFinSolicitacao;
/*     */   }
/*     */   
/*     */   public String getAcFinOrdemPagamento() {
/* 712 */     return this.acFinOrdemPagamento;
/*     */   }
/*     */   
/*     */   public void setAcFinOrdemPagamento(String acFinOrdemPagamento) {
/* 716 */     this.acFinOrdemPagamento = acFinOrdemPagamento;
/*     */   }
/*     */   
/*     */   public String getAcFinCategoria() {
/* 720 */     return this.acFinCategoria;
/*     */   }
/*     */   
/*     */   public void setAcFinCategoria(String acFinCategoria) {
/* 724 */     this.acFinCategoria = acFinCategoria;
/*     */   }
/*     */   
/*     */   public String getAcAdministrativo() {
/* 728 */     return this.acAdministrativo;
/*     */   }
/*     */   
/*     */   public void setAcAdministrativo(String acAdministrativo) {
/* 732 */     this.acAdministrativo = acAdministrativo;
/*     */   }
/*     */   
/*     */   public String getAcComPlanoItem() {
/* 736 */     return this.acComPlanoItem;
/*     */   }
/*     */   
/*     */   public void setAcComPlanoItem(String acComPlanoItem) {
/* 740 */     this.acComPlanoItem = acComPlanoItem;
/*     */   }
/*     */   
/*     */   public String getAcFinContaCorrente()
/*     */   {
/* 745 */     return this.acFinContaCorrente;
/*     */   }
/*     */   
/*     */   public void setAcFinContaCorrente(String acFinContaCorrente) {
/* 749 */     this.acFinContaCorrente = acFinContaCorrente;
/*     */   }
/*     */   
/*     */   public String getAcFinNotaDebito() {
/* 753 */     return this.acFinNotaDebito;
/*     */   }
/*     */   
/*     */   public void setAcFinNotaDebito(String acFinNotaDebito) {
/* 757 */     this.acFinNotaDebito = acFinNotaDebito;
/*     */   }
/*     */   
/*     */   public String getAcFinPrestacaoContas() {
/* 761 */     return this.acFinPrestacaoContas;
/*     */   }
/*     */   
/*     */   public void setAcFinPrestacaoContas(String acFinPrestacaoContas) {
/* 765 */     this.acFinPrestacaoContas = acFinPrestacaoContas;
/*     */   }
/*     */   
/*     */   public String getAcFinRLBM() {
/* 769 */     return this.acFinRLBM;
/*     */   }
/*     */   
/*     */   public void setAcFinRLBM(String acFinRLBM) {
/* 773 */     this.acFinRLBM = acFinRLBM;
/*     */   }
/*     */   
/*     */   public String getAcRelPesqSatisfacao() {
/* 777 */     return this.acRelPesqSatisfacao;
/*     */   }
/*     */   
/*     */   public void setAcRelPesqSatisfacao(String acRelPesqSatisfacao) {
/* 781 */     this.acRelPesqSatisfacao = acRelPesqSatisfacao;
/*     */   }
/*     */   
/*     */   public String getAcFinEquipamento() {
/* 785 */     return this.acFinEquipamento;
/*     */   }
/*     */   
/*     */   public void setAcFinEquipamento(String acFinEquipamento) {
/* 789 */     this.acFinEquipamento = acFinEquipamento;
/*     */   }
            
              public String getAcAprovaVistoria()
/*     */   {
/* 104 */     return this.acAprovaVistoria;
/*     */   }
/*     */   
/*     */   public void setAcAprovaVistoria(String acAprovaVistoria) {
/* 108 */     this.acAprovaVistoria = acAprovaVistoria;
/*     */   }

              public String getAcAprovaPO()
/*     */   {
/* 104 */     return this.acAprovaPO;
/*     */   }
/*     */   
/*     */   public void setAcAprovaPO(String acAprovaPO) {
/* 108 */     this.acAprovaPO = acAprovaPO;
/*     */   }


/*     */   public String getAcOrgListArquivo() {
/* 120 */     return this.acOrgListArquivo;
/*     */   }
/*     */   
/*     */   public void setAcOrgListArquivo(String acOrgListaArquivo) {
/* 124 */     this.acOrgListArquivo = acOrgListaArquivo;
/*     */   }
/*     */ }
          
          

/* Location:              /Users/diogo.lima/Documents/PEDIDO.jar!/Usuario/Usuario.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */