/*     */ package NvEmbarcacao;
/*     */ 
/*     */ import OperacionalOcorrencia.OperacionalOcorrencia;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;

/*     */ public class NvEmbarcacao
/*     */ {
/*     */   private String seqNvEmbarcacao;
/*     */   private String situacao;
/*     */   private Date dataCadastro;
/*     */   private String nome;
/*     */   private String numeroInscricao;
/*     */   private Date dataInscricao;
/*     */   private String livroInscricao;
/*     */   private String folhaInscricao;
/*     */   private String indRadioInternac;
/*     */   private String seqNvTipoEmbarcacao;
/*     */   private String areaNavegacao;
/*     */   private String tipoPropulsao;
/*     */   private String tipoAtividadeMar;
/*     */   private String tipoAtividadeInterior;
/*     */   private String tripulantes;
/*     */   private String passageiros;
/*     */   private String anoConstrucao;
/*     */   private String construtor;
/*     */   private String materialConstruCasco;
/*     */   private String materialSupertrutura;
/*     */   private String potenciaMotor;
/*     */   private String totalMaquina;
/*     */   private String tipoCombustivel;
/*     */   private String capacArmazenamento;
/*     */   private String arqueacaoBruta;
/*     */   private String arqueacaoLiquida;
/*     */   private String tonelagemPorteBruta;
/*     */   private String comprimentoTotal;
/*     */   private String boca;
/*     */   private String pontal;
/*     */   private String caladoLeve;
/*     */   private String caladoCarregado;
/*     */   private String nomeProprietario;
/*     */   private String cpfCnpj;
/*     */   private String endereco;
/*     */   private String cidadeEstado;
/*     */   private String nomeProprietario2;
/*     */   private String cpfCnpj2;
/*     */   private String endereco2;
/*     */   private String cidadeEstado2;
/*     */   private String imo;
/*     */   private String seqParceiro;
/*     */   private String indicativo;
/*     */   private String portoInscricao;
/*     */   private String tipo;
/*     */   private String centroDiscoConves;
/*     */   private String centroDiscoProa;
/*     */   private String linhaConves;
/*     */   private String marcaLinhaDagua;
/*     */   private String seqEmpresa;
/*     */   private String areaNavegacaoTipo;
/*     */   private String seqNvCertificadoCalculoCSN;
/*     */   private String seqNvCertificadoCalculoBLR;
/*     */   private String seqNvCertificadoCalculoARQ;
/*     */   private String tipoEmbarcacaoCodigo;
/*     */   private String tipoEmbarcacaoNome;
/*     */   private String parceiroNome;
/*     */   private String numeroCasco;
/*     */   private String comprimentoEntrePerpend;
/*     */   private String correcaoNavegacaoAguaDoce;
/*     */   private String correcaoNavegacaoAguaSalgada;
/*     */   private String marcaLinhaCargaArea1;
/*     */   private String marcaLinhaCargaArea2;
/*     */   private String espacosFechadosAbaixoConves;
/*     */   private String espacosFechadosAcimaConves;
/*     */   private String volumeTotalEspacos;
/*     */   private String espacoCarga;
/*     */   private String totalPassageirosCamarote;
/*     */   private String dataLocalArqueacaoOriginal;
/*     */   private String dataLocalUltimaRearqueacao;
/*     */   private String tipoMarcaMotor;
/*     */   private String numeroMotor;
/*     */   private String autorizadoTransportarCargaConves;
/*     */   private String bordaLivre;
/*     */   private String destinacaoReboque;
/*     */   private String comprimentoRegra;
/*     */   private String comentarioEtapa;
/*     */   private String tipoPlantaPropulsora;
/*     */   private String potenciaNominalEletrica;
/*     */   private String mercadoriasPerigosas;
/*     */   private String apoioPortuario;
/*     */   private String cepArmador;
/*     */   private String cepEstaleiroConstrutor;
/*     */   private String requisitosTransporteColetivo;
            private String bandeira;
            private String seqUsuario;
            private String apmaritimo;  
            private String cabotagem;    
            private String longocurso;  
            private String maarea1;     
            private String maarea12;    
            private String maarea123;   
            private String maarea1234;  
            private String navint;      
            private String navarea1;    
            private String navarea2;
            private String apportuario; 
            private String aparea1;
            private String aparea2; 
/*     */   List<OperacionalOcorrencia> listaOperacionalOcorrencia;
/*     */   
/*     */   public NvEmbarcacao()
/*     */   {
/* 125 */     this.listaOperacionalOcorrencia = new ArrayList();
/*     */   }
/*     */   
/*     */   public String getSeqNvEmbarcacao()
/*     */   {
/* 130 */     return this.seqNvEmbarcacao;
/*     */   }
/*     */   
/*     */   public void setSeqNvEmbarcacao(String seqNvEmbarcacao) {
/* 134 */     this.seqNvEmbarcacao = seqNvEmbarcacao;
/*     */   }
/*     */   
/*     */   public String getSituacao() {
/* 138 */     return this.situacao;
/*     */   }
/*     */   
/*     */   public void setSituacao(String situacao) {
/* 142 */     this.situacao = situacao;
/*     */   }
/*     */   
/*     */   public Date getDataCadastro() {
/* 146 */     return this.dataCadastro;
/*     */   }
/*     */   
/*     */   public void setDataCadastro(Date dataCadastro) {
/* 150 */     this.dataCadastro = dataCadastro;
/*     */   }
/*     */   
/*     */   public String getNome() {
/* 154 */     return this.nome;
/*     */   }
/*     */   
/*     */   public void setNome(String nome) {
/* 158 */     this.nome = nome;
/*     */   }
/*     */   
/*     */   public String getNumeroInscricao() {
/* 162 */     return this.numeroInscricao;
/*     */   }
/*     */   
/*     */   public void setNumeroInscricao(String numeroInscricao) {
/* 166 */     this.numeroInscricao = numeroInscricao;
/*     */   }
/*     */   
/*     */   public Date getDataInscricao() {
/* 170 */     return this.dataInscricao;
/*     */   }
/*     */   
/*     */   public void setDataInscricao(Date dataInscricao) {
/* 174 */     this.dataInscricao = dataInscricao;
/*     */   }
/*     */   
/*     */   public String getLivroInscricao() {
/* 178 */     return this.livroInscricao;
/*     */   }
/*     */   
/*     */   public void setLivroInscricao(String livroInscricao) {
/* 182 */     this.livroInscricao = livroInscricao;
/*     */   }
/*     */   
/*     */   public String getFolhaInscricao() {
/* 186 */     return this.folhaInscricao;
/*     */   }
/*     */   
/*     */   public void setFolhaInscricao(String folhaInscricao) {
/* 190 */     this.folhaInscricao = folhaInscricao;
/*     */   }
/*     */   
/*     */   public String getIndRadioInternac() {
/* 194 */     return this.indRadioInternac;
/*     */   }
/*     */   
/*     */   public void setIndRadioInternac(String indRadioInternac) {
/* 198 */     this.indRadioInternac = indRadioInternac;
/*     */   }
/*     */   
/*     */   public String getSeqNvTipoEmbarcacao() {
/* 202 */     return this.seqNvTipoEmbarcacao;
/*     */   }
/*     */   
/*     */   public void setSeqNvTipoEmbarcacao(String seqNvTipoEmbarcacao) {
/* 206 */     this.seqNvTipoEmbarcacao = seqNvTipoEmbarcacao;
/*     */   }
/*     */   
/*     */   public String getAreaNavegacao() {
/* 210 */     return this.areaNavegacao;
/*     */   }
/*     */   
/*     */   public void setAreaNavegacao(String areaNavegacao) {
/* 214 */     this.areaNavegacao = areaNavegacao;
/*     */   }
/*     */   
/*     */   public String getTipoPropulsao() {
/* 218 */     return this.tipoPropulsao;
/*     */   }
/*     */   
/*     */   public void setTipoPropulsao(String tipoPropulsao) {
/* 222 */     this.tipoPropulsao = tipoPropulsao;
/*     */   }
/*     */   
/*     */   public String getTipoAtividadeMar() {
/* 226 */     return this.tipoAtividadeMar;
/*     */   }
/*     */   
/*     */   public void setTipoAtividadeMar(String tipoAtividadeMar) {
/* 230 */     this.tipoAtividadeMar = tipoAtividadeMar;
/*     */   }
/*     */   
/*     */   public String getTripulantes() {
/* 234 */     return this.tripulantes;
/*     */   }
/*     */   
/*     */   public void setTripulantes(String tripulantes) {
/* 238 */     this.tripulantes = tripulantes;
/*     */   }
/*     */   
/*     */   public String getPassageiros() {
/* 242 */     return this.passageiros;
/*     */   }
/*     */   
/*     */   public void setPassageiros(String passageiros) {
/* 246 */     this.passageiros = passageiros;
/*     */   }
/*     */   
/*     */   public String getAnoConstrucao() {
/* 250 */     return this.anoConstrucao;
/*     */   }
/*     */   
/*     */   public void setAnoConstrucao(String anoConstrucao) {
/* 254 */     this.anoConstrucao = anoConstrucao;
/*     */   }
/*     */   
/*     */   public String getConstrutor() {
/* 258 */     return this.construtor;
/*     */   }
/*     */   
/*     */   public void setConstrutor(String construtor) {
/* 262 */     this.construtor = construtor;
/*     */   }
/*     */   
/*     */   public String getMaterialConstruCasco() {
/* 266 */     return this.materialConstruCasco;
/*     */   }
/*     */   
/*     */   public void setMaterialConstruCasco(String materialConstruCasco) {
/* 270 */     this.materialConstruCasco = materialConstruCasco;
/*     */   }
/*     */   
/*     */   public String getNumeroCasco() {
/* 274 */     return this.numeroCasco;
/*     */   }
/*     */   
/*     */   public void setNumeroCasco(String numeroCasco) {
/* 278 */     this.numeroCasco = numeroCasco;
/*     */   }
/*     */   
/*     */   public String getMaterialSupertrutura() {
/* 282 */     return this.materialSupertrutura;
/*     */   }
/*     */   
/*     */   public void setMaterialSupertrutura(String materialSupertrutura) {
/* 286 */     this.materialSupertrutura = materialSupertrutura;
/*     */   }
/*     */   
/*     */   public String getPotenciaMotor() {
/* 290 */     return this.potenciaMotor;
/*     */   }
/*     */   
/*     */   public void setPotenciaMotor(String potenciaMotor) {
/* 294 */     this.potenciaMotor = potenciaMotor;
/*     */   }
/*     */   
/*     */   public String getTotalMaquina() {
/* 298 */     return this.totalMaquina;
/*     */   }
/*     */   
/*     */   public void setTotalMaquina(String totalMaquina) {
/* 302 */     this.totalMaquina = totalMaquina;
/*     */   }
/*     */   
/*     */   public String getTipoCombustivel() {
/* 306 */     return this.tipoCombustivel;
/*     */   }
/*     */   
/*     */   public void setTipoCombustivel(String tipoCombustivel) {
/* 310 */     this.tipoCombustivel = tipoCombustivel;
/*     */   }
/*     */   
/*     */   public String getCapacArmazenamento() {
/* 314 */     return this.capacArmazenamento;
/*     */   }
/*     */   
/*     */   public void setCapacArmazenamento(String capacArmazenamento) {
/* 318 */     this.capacArmazenamento = capacArmazenamento;
/*     */   }
/*     */   
/*     */   public String getArqueacaoBruta() {
/* 322 */     return this.arqueacaoBruta;
/*     */   }
/*     */   
/*     */   public void setArqueacaoBruta(String arqueacaoBruta) {
/* 326 */     this.arqueacaoBruta = arqueacaoBruta;
/*     */   }
/*     */   
/*     */   public String getArqueacaoLiquida() {
/* 330 */     return this.arqueacaoLiquida;
/*     */   }
/*     */   
/*     */   public void setArqueacaoLiquida(String arqueacaoLiquida) {
/* 334 */     this.arqueacaoLiquida = arqueacaoLiquida;
/*     */   }
/*     */   
/*     */   public String getTonelagemPorteBruta() {
/* 338 */     return this.tonelagemPorteBruta;
/*     */   }
/*     */   
/*     */   public void setTonelagemPorteBruta(String tonelagemPorteBruta) {
/* 342 */     this.tonelagemPorteBruta = tonelagemPorteBruta;
/*     */   }
/*     */   
/*     */   public String getComprimentoTotal() {
/* 346 */     return this.comprimentoTotal;
/*     */   }
/*     */   
/*     */   public void setComprimentoTotal(String comprimentoTotal) {
/* 350 */     this.comprimentoTotal = comprimentoTotal;
/*     */   }
/*     */   
/*     */   public String getBoca() {
/* 354 */     return this.boca;
/*     */   }
/*     */   
/*     */   public void setBoca(String boca) {
/* 358 */     this.boca = boca;
/*     */   }
/*     */   
/*     */   public String getPontal() {
/* 362 */     return this.pontal;
/*     */   }
/*     */   
/*     */   public void setPontal(String pontal) {
/* 366 */     this.pontal = pontal;
/*     */   }
/*     */   
/*     */   public String getCaladoLeve() {
/* 370 */     return this.caladoLeve;
/*     */   }
/*     */   
/*     */   public void setCaladoLeve(String caladoLeve) {
/* 374 */     this.caladoLeve = caladoLeve;
/*     */   }
/*     */   
/*     */   public String getCaladoCarregado() {
/* 378 */     return this.caladoCarregado;
/*     */   }
/*     */   
/*     */   public void setCaladoCarregado(String caladoCarregado) {
/* 382 */     this.caladoCarregado = caladoCarregado;
/*     */   }
/*     */   
/*     */   public String getNomeProprietario() {
/* 386 */     return this.nomeProprietario;
/*     */   }
/*     */   
/*     */   public void setNomeProprietario(String nomeProprietario) {
/* 390 */     this.nomeProprietario = nomeProprietario;
/*     */   }
/*     */   
/*     */   public String getCpfCnpj() {
/* 394 */     return this.cpfCnpj;
/*     */   }
/*     */   
/*     */   public void setCpfCnpj(String cpfCnpj) {
/* 398 */     this.cpfCnpj = cpfCnpj;
/*     */   }
/*     */   
/*     */   public String getEndereco() {
/* 402 */     return this.endereco;
/*     */   }
/*     */   
/*     */   public void setEndereco(String endereco) {
/* 406 */     this.endereco = endereco;
/*     */   }
/*     */   
/*     */   public String getCidadeEstado() {
/* 410 */     return this.cidadeEstado;
/*     */   }
/*     */   
/*     */   public void setCidadeEstado(String cidadeEstado) {
/* 414 */     this.cidadeEstado = cidadeEstado;
/*     */   }
/*     */   
/*     */   public String getNomeProprietario2() {
/* 418 */     return this.nomeProprietario2;
/*     */   }
/*     */   
/*     */   public void setNomeProprietario2(String nomeProprietario2) {
/* 422 */     this.nomeProprietario2 = nomeProprietario2;
/*     */   }
/*     */   
/*     */   public String getCpfCnpj2() {
/* 426 */     return this.cpfCnpj2;
/*     */   }
/*     */   
/*     */   public void setCpfCnpj2(String cpfCnpj2) {
/* 430 */     this.cpfCnpj2 = cpfCnpj2;
/*     */   }
/*     */   
/*     */   public String getEndereco2() {
/* 434 */     return this.endereco2;
/*     */   }
/*     */   
/*     */   public void setEndereco2(String endereco2) {
/* 438 */     this.endereco2 = endereco2;
/*     */   }
/*     */   
/*     */   public String getCidadeEstado2() {
/* 442 */     return this.cidadeEstado2;
/*     */   }
/*     */   
/*     */   public void setCidadeEstado2(String cidadeEstado2) {
/* 446 */     this.cidadeEstado2 = cidadeEstado2;
/*     */   }
/*     */   
/*     */   public String getImo() {
/* 450 */     return this.imo;
/*     */   }
/*     */   
/*     */   public void setImo(String imo) {
/* 454 */     this.imo = imo;
/*     */   }
/*     */   
/*     */   public String getSeqParceiro() {
/* 458 */     return this.seqParceiro;
/*     */   }
/*     */   
/*     */   public void setSeqParceiro(String seqParceiro) {
/* 462 */     this.seqParceiro = seqParceiro;
/*     */   }
/*     */   
/*     */   public String getIndicativo() {
/* 466 */     return this.indicativo;
/*     */   }
/*     */   
/*     */   public void setIndicativo(String indicativo) {
/* 470 */     this.indicativo = indicativo;
/*     */   }
/*     */   
/*     */   public String getPortoInscricao() {
/* 474 */     return this.portoInscricao;
/*     */   }
/*     */   
/*     */   public void setPortoInscricao(String portoInscricao) {
/* 478 */     this.portoInscricao = portoInscricao;
/*     */   }
/*     */   
/*     */   public String getTipo() {
/* 482 */     return this.tipo;
/*     */   }
/*     */   
/*     */   public void setTipo(String tipo) {
/* 486 */     this.tipo = tipo;
/*     */   }
/*     */   
/*     */   public String getCentroDiscoConves() {
/* 490 */     return this.centroDiscoConves;
/*     */   }
/*     */   
/*     */   public void setCentroDiscoConves(String centroDiscoConves) {
/* 494 */     this.centroDiscoConves = centroDiscoConves;
/*     */   }
/*     */   
/*     */   public String getCentroDiscoProa() {
/* 498 */     return this.centroDiscoProa;
/*     */   }
/*     */   
/*     */   public void setCentroDiscoProa(String centroDiscoProa) {
/* 502 */     this.centroDiscoProa = centroDiscoProa;
/*     */   }
/*     */   
/*     */   public String getLinhaConves() {
/* 506 */     return this.linhaConves;
/*     */   }
/*     */   
/*     */   public void setLinhaConves(String linhaConves) {
/* 510 */     this.linhaConves = linhaConves;
/*     */   }
/*     */   
/*     */   public String getMarcaLinhaDagua() {
/* 514 */     return this.marcaLinhaDagua;
/*     */   }
/*     */   
/*     */   public void setMarcaLinhaDagua(String marcaLinhaDagua) {
/* 518 */     this.marcaLinhaDagua = marcaLinhaDagua;
/*     */   }
/*     */   
/*     */   public String getSeqEmpresa() {
/* 522 */     return this.seqEmpresa;
/*     */   }
/*     */   
/*     */   public void setSeqEmpresa(String seqEmpresa) {
/* 526 */     this.seqEmpresa = seqEmpresa;
/*     */   }
/*     */   
/*     */   public String getTipoEmbarcacaoCodigo() {
/* 530 */     return this.tipoEmbarcacaoCodigo;
/*     */   }
/*     */   
/*     */   public void setTipoEmbarcacaoCodigo(String tipoEmbarcacaoCodigo) {
/* 534 */     this.tipoEmbarcacaoCodigo = tipoEmbarcacaoCodigo;
/*     */   }
/*     */   
/*     */   public String getTipoEmbarcacaoNome() {
/* 538 */     return this.tipoEmbarcacaoNome;
/*     */   }
/*     */   
/*     */   public void setTipoEmbarcacaoNome(String tipoEmbarcacaoNome) {
/* 542 */     this.tipoEmbarcacaoNome = tipoEmbarcacaoNome;
/*     */   }
/*     */   
/*     */   public String getParceiroNome() {
/* 546 */     return this.parceiroNome;
/*     */   }
/*     */   
/*     */   public void setParceiroNome(String parceiroNome) {
/* 550 */     this.parceiroNome = parceiroNome;
/*     */   }
/*     */   
/*     */   public String getAreaNavegacaoTipo() {
/* 554 */     return this.areaNavegacaoTipo;
/*     */   }
/*     */   
/*     */   public void setAreaNavegacaoTipo(String areaNavegacaoTipo) {
/* 558 */     this.areaNavegacaoTipo = areaNavegacaoTipo;
/*     */   }
/*     */   
/*     */   public String getSeqNvCertificadoCalculoCSN() {
/* 562 */     return this.seqNvCertificadoCalculoCSN;
/*     */   }
/*     */   
/*     */   public void setSeqNvCertificadoCalculoCSN(String seqNvCertificadoCalculoCSN) {
/* 566 */     this.seqNvCertificadoCalculoCSN = seqNvCertificadoCalculoCSN;
/*     */   }
/*     */   
/*     */   public String getSeqNvCertificadoCalculoBLR() {
/* 570 */     return this.seqNvCertificadoCalculoBLR;
/*     */   }
/*     */   
/*     */   public void setSeqNvCertificadoCalculoBLR(String seqNvCertificadoCalculoBLR) {
/* 574 */     this.seqNvCertificadoCalculoBLR = seqNvCertificadoCalculoBLR;
/*     */   }
/*     */   
/*     */   public String getSeqNvCertificadoCalculoARQ() {
/* 578 */     return this.seqNvCertificadoCalculoARQ;
/*     */   }
/*     */   
/*     */   public void setSeqNvCertificadoCalculoARQ(String seqNvCertificadoCalculoARQ) {
/* 582 */     this.seqNvCertificadoCalculoARQ = seqNvCertificadoCalculoARQ;
/*     */   }
/*     */   
/*     */   public String getComprimentoEntrePerpend() {
/* 586 */     return this.comprimentoEntrePerpend;
/*     */   }
/*     */   
/*     */   public void setComprimentoEntrePerpend(String comprimentoEntrePerpend) {
/* 590 */     this.comprimentoEntrePerpend = comprimentoEntrePerpend;
/*     */   }
/*     */   
/*     */   public String getCorrecaoNavegacaoAguaDoce() {
/* 594 */     return this.correcaoNavegacaoAguaDoce;
/*     */   }
/*     */   
/*     */   public void setCorrecaoNavegacaoAguaDoce(String correcaoNavegacaoAguaDoce) {
/* 598 */     this.correcaoNavegacaoAguaDoce = correcaoNavegacaoAguaDoce;
/*     */   }
/*     */   
/*     */   public String getCorrecaoNavegacaoAguaSalgada() {
/* 602 */     return this.correcaoNavegacaoAguaSalgada;
/*     */   }
/*     */   
/*     */   public void setCorrecaoNavegacaoAguaSalgada(String correcaoNavegacaoAguaSalgada) {
/* 606 */     this.correcaoNavegacaoAguaSalgada = correcaoNavegacaoAguaSalgada;
/*     */   }
/*     */   
/*     */   public String getMarcaLinhaCargaArea1() {
/* 610 */     return this.marcaLinhaCargaArea1;
/*     */   }
/*     */   
/*     */   public void setMarcaLinhaCargaArea1(String marcaLinhaCargaArea1) {
/* 614 */     this.marcaLinhaCargaArea1 = marcaLinhaCargaArea1;
/*     */   }
/*     */   
/*     */   public String getMarcaLinhaCargaArea2() {
/* 618 */     return this.marcaLinhaCargaArea2;
/*     */   }
/*     */   
/*     */   public void setMarcaLinhaCargaArea2(String marcaLinhaCargaArea2) {
/* 622 */     this.marcaLinhaCargaArea2 = marcaLinhaCargaArea2;
/*     */   }
/*     */   
/*     */   public String getEspacosFechadosAbaixoConves() {
/* 626 */     return this.espacosFechadosAbaixoConves;
/*     */   }
/*     */   
/*     */   public void setEspacosFechadosAbaixoConves(String espacosFechadosAbaixoConves) {
/* 630 */     this.espacosFechadosAbaixoConves = espacosFechadosAbaixoConves;
/*     */   }
/*     */   
/*     */   public String getEspacosFechadosAcimaConves() {
/* 634 */     return this.espacosFechadosAcimaConves;
/*     */   }
/*     */   
/*     */   public void setEspacosFechadosAcimaConves(String espacosFechadosAcimaConves) {
/* 638 */     this.espacosFechadosAcimaConves = espacosFechadosAcimaConves;
/*     */   }
/*     */   
/*     */   public String getVolumeTotalEspacos() {
/* 642 */     return this.volumeTotalEspacos;
/*     */   }
/*     */   
/*     */   public void setVolumeTotalEspacos(String volumeTotalEspacos) {
/* 646 */     this.volumeTotalEspacos = volumeTotalEspacos;
/*     */   }
/*     */   
/*     */   public String getTotalPassageirosCamarote() {
/* 650 */     return this.totalPassageirosCamarote;
/*     */   }
/*     */   
/*     */   public void setTotalPassageirosCamarote(String totalPassageirosCamarote) {
/* 654 */     this.totalPassageirosCamarote = totalPassageirosCamarote;
/*     */   }
/*     */   
/*     */   public String getDataLocalArqueacaoOriginal() {
/* 658 */     return this.dataLocalArqueacaoOriginal;
/*     */   }
/*     */   
/*     */   public void setDataLocalArqueacaoOriginal(String dataLocalArqueacaoOriginal) {
/* 662 */     this.dataLocalArqueacaoOriginal = dataLocalArqueacaoOriginal;
/*     */   }
/*     */   
/*     */   public String getDataLocalUltimaRearqueacao() {
/* 666 */     return this.dataLocalUltimaRearqueacao;
/*     */   }
/*     */   
/*     */   public void setDataLocalUltimaRearqueacao(String dataLocalUltimaRearqueacao) {
/* 670 */     this.dataLocalUltimaRearqueacao = dataLocalUltimaRearqueacao;
/*     */   }
/*     */   
/*     */   public String getEspacoCarga() {
/* 674 */     return this.espacoCarga;
/*     */   }
/*     */   
/*     */   public void setEspacoCarga(String espacoCarga) {
/* 678 */     this.espacoCarga = espacoCarga;
/*     */   }
/*     */   
/*     */   public String getTipoMarcaMotor() {
/* 682 */     return this.tipoMarcaMotor;
/*     */   }
/*     */   
/*     */   public void setTipoMarcaMotor(String tipoMarcaMotor) {
/* 686 */     this.tipoMarcaMotor = tipoMarcaMotor;
/*     */   }
/*     */   
/*     */   public String getNumeroMotor() {
/* 690 */     return this.numeroMotor;
/*     */   }
/*     */   
/*     */   public void setNumeroMotor(String numeroMotor) {
/* 694 */     this.numeroMotor = numeroMotor;
/*     */   }
/*     */   
/*     */   public String getAutorizadoTransportarCargaConves() {
/* 698 */     return this.autorizadoTransportarCargaConves;
/*     */   }
/*     */   
/*     */   public void setAutorizadoTransportarCargaConves(String autorizadoTransportarCargaConves) {
/* 702 */     this.autorizadoTransportarCargaConves = autorizadoTransportarCargaConves;
/*     */   }
/*     */   
/*     */   public String getBordaLivre() {
/* 706 */     return this.bordaLivre;
/*     */   }
/*     */   
/*     */   public void setBordaLivre(String bordaLivre) {
/* 710 */     this.bordaLivre = bordaLivre;
/*     */   }
/*     */   
/*     */   public String getDestinacaoReboque() {
/* 714 */     return this.destinacaoReboque;
/*     */   }
/*     */   
/*     */   public void setDestinacaoReboque(String destinacaoReboque) {
/* 718 */     this.destinacaoReboque = destinacaoReboque;
/*     */   }
/*     */   
/*     */   public String getBandeira() {
/* 714 */     return this.bandeira;
/*     */   }
/*     */   
/*     */   public void setBandeira(String bandeira) {
/* 718 */     this.bandeira = bandeira;
/*     */   }

/*     */   public String getPotenciaNominalEletrica()
/*     */   {
/* 859 */     return this.potenciaNominalEletrica;
/*     */   }
/*     */   
/*     */   public void setPotenciaNominalEletrica(String potenciaNominalEletrica) {
/* 863 */     this.potenciaNominalEletrica = potenciaNominalEletrica;
/*     */   }
/*     */   
/*     */   public String getMercadoriasPerigosas() {
/* 867 */     return this.mercadoriasPerigosas;
/*     */   }
/*     */   
/*     */   public void setMercadoriasPerigosas(String mercadoriasPerigosas) {
/* 871 */     this.mercadoriasPerigosas = mercadoriasPerigosas;
/*     */   }
/*     */   
/*     */   public String getApoioPortuario() {
/* 875 */     return this.apoioPortuario;
/*     */   }
/*     */   
/*     */   public void setApoioPortuario(String apoioPortuario) {
/* 879 */     this.apoioPortuario = apoioPortuario;
/*     */   }
/*     */   
/*     */   public String getTipoAtividadeInterior() {
/* 883 */     return this.tipoAtividadeInterior;
/*     */   }
/*     */   
/*     */   public void setTipoAtividadeInterior(String tipoAtividadeInterior) {
/* 887 */     this.tipoAtividadeInterior = tipoAtividadeInterior;
/*     */   }
/*     */   
/*     */   public String getCepArmador() {
/* 891 */     return this.cepArmador;
/*     */   }
/*     */   
/*     */   public void setCepArmador(String cepArmador) {
/* 895 */     this.cepArmador = cepArmador;
/*     */   }
/*     */   
/*     */   public String getCepEstaleiroConstrutor() {
/* 899 */     return this.cepEstaleiroConstrutor;
/*     */   }
/*     */   
/*     */   public void setCepEstaleiroConstrutor(String cepEstaleiroConstrutor) {
/* 903 */     this.cepEstaleiroConstrutor = cepEstaleiroConstrutor;
/*     */   }
/*     */   
/*     */   public String getRequisitosTransporteColetivo() {
/* 907 */     return this.requisitosTransporteColetivo;
/*     */   }
/*     */   
/*     */   public void setRequisitosTransporteColetivo(String requisitosTransporteColetivo) {
/* 911 */     this.requisitosTransporteColetivo = requisitosTransporteColetivo;
/*     */   }
/*     */   
/*     */   public String getTipoPlantaPropulsora() {
/* 915 */     return this.tipoPlantaPropulsora;
/*     */   }
/*     */   
/*     */   public void setTipoPlantaPropulsora(String tipoPlantaPropulsora) {
/* 919 */     this.tipoPlantaPropulsora = tipoPlantaPropulsora;
/*     */   }
/*     */   
/*     */   public String getComprimentoRegra() {
/* 923 */     return this.comprimentoRegra;
/*     */   }
/*     */   
/*     */   public void setComprimentoRegra(String comprimentoRegra) {
/* 927 */     this.comprimentoRegra = comprimentoRegra;
/*     */   }
/*     */   
/*     */   public List<OperacionalOcorrencia> getListaOperacionalOcorrencia() {
/* 931 */     return this.listaOperacionalOcorrencia;
/*     */   }
/*     */   
/*     */   public void setListaOperacionalOcorrencia(List<OperacionalOcorrencia> listaOperacionalOcorrencia) {
/* 935 */     this.listaOperacionalOcorrencia = listaOperacionalOcorrencia;
/*     */   }
/*     */   
/*     */   public String getComentarioEtapa() {
/* 939 */     return this.comentarioEtapa;
/*     */   }
/*     */   
/*     */   public void setComentarioEtapa(String comentarioEtapa) {
/* 943 */     this.comentarioEtapa = comentarioEtapa;
/*     */   }
/*     */   public String getSeqUsuario() {
/* 154 */     return this.seqUsuario;
/*     */   }
/*     */   
/*     */   public void setSeqUsuario(String seqUsuario) {
/* 158 */     this.seqUsuario = seqUsuario;
/*     */   }

/*     */   public String getApmaritimo() {
/* 154 */     return this.apmaritimo;
/*     */   }
/*     */   
/*     */   public void setApmaritimo(String apmaritimo) {
/* 158 */     this.apmaritimo = apmaritimo;
/*     */   }

/*     */   public String getCabotagem() {
/* 154 */     return this.cabotagem;
/*     */   }
/*     */   
/*     */   public void setCabotagem(String cabotagem) {
/* 158 */     this.cabotagem = cabotagem;
/*     */   }
/*     */   
            public String getLongoCurso() {
/* 154 */     return this.longocurso;
/*     */   }
/*     */   
/*     */   public void setLongoCurso(String longocurso) {
/* 158 */     this.longocurso = longocurso;
/*     */   }

/*     */   public String getMaarea1() {
/* 154 */     return this.maarea1;
/*     */   }
/*     */   
/*     */   public void setMaarea1(String maarea1) {
/* 158 */     this.maarea1 = maarea1;
/*     */   }

/*     */   public String getMaarea12() {
/* 154 */     return this.maarea12;
/*     */   }
/*     */   
/*     */   public void setMaarea12(String maarea12) {
/* 158 */     this.maarea12 = maarea12;
/*     */   }

/*     */   public String getMaarea123() {
/* 154 */     return this.maarea123;
/*     */   }
/*     */   
/*     */   public void setMaarea123(String maarea123) {
/* 158 */     this.maarea123 = maarea123;
/*     */   }

/*     */   public String getMaarea1234() {
/* 154 */     return this.maarea1234;
/*     */   }
/*     */   
/*     */   public void setMaarea1234(String maarea1234) {
/* 158 */     this.maarea1234 = maarea1234;
/*     */   }

/*     */   public String getNavint() {
/* 154 */     return this.navint;
/*     */   }
/*     */   
/*     */   public void setNavint(String navint) {
/* 158 */     this.navint = navint;
/*     */   }

/*     */   public String getNavarea1() {
/* 154 */     return this.navarea1;
/*     */   }
/*     */   
/*     */   public void setNavarea1(String navarea1) {
/* 158 */     this.navarea1 = navarea1;
/*     */   }

/*     */   public String getNavarea2() {
/* 154 */     return this.navarea2;
/*     */   }
/*     */   
/*     */   public void setNavarea2(String navarea2) {
/* 158 */     this.navarea2 = navarea2;
            }

/*     */   public String getApportuario() {
/* 154 */     return this.apportuario;
/*     */   }
/*     */   
/*     */   public void setApportuario(String apportuario) {
/* 158 */     this.apportuario = apportuario;
/*     */   }

/*     */   public String getAparea1() {
/* 154 */     return this.aparea1;
/*     */   }
/*     */   
/*     */   public void setAparea1(String aparea1) {
/* 158 */     this.aparea1 = aparea1;
/*     */   }

/*     */   public String getAparea2() {
/* 154 */     return this.aparea2;
/*     */   }
/*     */   
/*     */   public void setAparea2(String aparea2) {
/* 158 */     this.aparea2 = aparea2;
/*     */   }


/*     */ }

