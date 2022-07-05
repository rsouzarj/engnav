/*     */ package NvCertificado;
/*     */ 
/*     */ import java.util.Date;

/*     */ public class NvCertificado
/*     */ {
/*     */   private String seqNvCertificado;
/*     */   private String identificacao;
/*     */   private String seqEmpresa;
/*     */   private Date dataCadastro;
/*     */   private String situacao;
/*     */   private String seqNvTipoCertificado;
/*     */   private String seqNvEmbarcacao;
/*     */   private Date dataEmissao;
/*     */   private Date dataValidade;
/*     */   private Date dataEmissaoLaudo;
/*     */   private String localEmissao;
/*     */   private String seqColaborador;
/*     */   private String seqNvVistoria;
/*     */   private String embarcacaoNome;
/*     */   private String observacao;
/*     */   private String status;
/*     */   private String filial;
/*     */   private String tipoCertificado;
/*     */   private short prazo;
/*     */   private short prazoTotal;
/*     */   private String assinaturaDigital;
            private String identvistoria;
            private String seqUnidadeNegocio;            
/*     */   private String unidadenegocioNome;
            private String maraberto;
            private String navint;
            private String area1;
            private String area2;

            
/*     */   
            public String getSeqNvCertificado()
/*     */   {
/*  39 */     return this.seqNvCertificado;
/*     */   }
/*     */   
/*     */   public void setSeqNvCertificado(String seqNvCertificado) {
/*  43 */     this.seqNvCertificado = seqNvCertificado;
/*     */   }
/*     */   
/*     */   public String getIdentificacao() {
/*  47 */     return this.identificacao;
/*     */   }
/*     */   
/*     */   public void setIdentificacao(String identificacao) {
/*  51 */     this.identificacao = identificacao;
/*     */   }
/*     */   
/*     */   public String getSeqEmpresa() {
/*  55 */     return this.seqEmpresa;
/*     */   }
/*     */   
/*     */   public void setSeqEmpresa(String seqEmpresa) {
/*  59 */     this.seqEmpresa = seqEmpresa;
/*     */   }
/*     */   
/*     */   public Date getDataCadastro() {
/*  63 */     return this.dataCadastro;
/*     */   }
/*     */   
/*     */   public void setDataCadastro(Date dataCadastro) {
/*  67 */     this.dataCadastro = dataCadastro;
/*     */   }
/*     */   
/*     */   public String getSituacao() {
/*  71 */     return this.situacao;
/*     */   }
/*     */   
/*     */   public void setSituacao(String situacao) {
/*  75 */     this.situacao = situacao;
/*     */   }
/*     */   
/*     */   public String getSeqNvTipoCertificado() {
/*  79 */     return this.seqNvTipoCertificado;
/*     */   }
/*     */   
/*     */   public void setSeqNvTipoCertificado(String seqNvTipoCertificado) {
/*  83 */     this.seqNvTipoCertificado = seqNvTipoCertificado;
/*     */   }
/*     */   
/*     */   public String getSeqNvEmbarcacao() {
/*  87 */     return this.seqNvEmbarcacao;
/*     */   }
/*     */   
/*     */   public void setSeqNvEmbarcacao(String seqNvEmbarcacao) {
/*  91 */     this.seqNvEmbarcacao = seqNvEmbarcacao;
/*     */   }
/*     */   
/*     */   public Date getDataEmissao() {
/*  95 */     return this.dataEmissao;
/*     */   }
/*     */   
/*     */   public void setDataEmissao(Date dataEmissao) {
/*  99 */     this.dataEmissao = dataEmissao;
/*     */   }
/*     */   
/*     */   public Date getDataValidade() {
/* 103 */     return this.dataValidade;
/*     */   }
/*     */   
/*     */   public void setDataValidade(Date dataValidade) {
/* 107 */     this.dataValidade = dataValidade;
/*     */   }
/*     */   
/*     */   public String getEmbarcacaoNome() {
/* 111 */     return this.embarcacaoNome;
/*     */   }
/*     */   
/*     */   public void setEmbarcacaoNome(String embarcacaoNome) {
/* 115 */     this.embarcacaoNome = embarcacaoNome;
/*     */   }
/*     */   
/*     */   public String getLocalEmissao() {
/* 119 */     return this.localEmissao;
/*     */   }
/*     */   
/*     */   public void setLocalEmissao(String localEmissao) {
/* 123 */     this.localEmissao = localEmissao;
/*     */   }
/*     */   
/*     */   public String getSeqColaborador() {
/* 127 */     return this.seqColaborador;
/*     */   }
/*     */   
/*     */   public void setSeqColaborador(String seqColaborador) {
/* 131 */     this.seqColaborador = seqColaborador;
/*     */   }
/*     */   
/*     */   public String getObservacao() {
/* 135 */     return this.observacao;
/*     */   }
/*     */   
/*     */   public void setObservacao(String observacao) {
/* 139 */     this.observacao = observacao;
/*     */   }
/*     */   
/*     */   public String getStatus() {
/* 143 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(String status) {
/* 147 */     this.status = status;
/*     */   }
/*     */   
/*     */   public String getSeqNvVistoria() {
/* 151 */     return this.seqNvVistoria;
/*     */   }
/*     */   
/*     */   public void setSeqNvVistoria(String seqNvVistoria) {
/* 155 */     this.seqNvVistoria = seqNvVistoria;
/*     */   }
/*     */   
/*     */   public String getTipoCertificado() {
/* 159 */     return this.tipoCertificado;
/*     */   }
/*     */   
/*     */   public void setTipoCertificado(String tipoCertificado) {
/* 163 */     this.tipoCertificado = tipoCertificado;
/*     */   }
/*     */   
/*     */   public short getPrazo() {
/* 167 */     return this.prazo;
/*     */   }
/*     */   
/*     */   public void setPrazo(short prazo) {
/* 171 */     this.prazo = prazo;
/*     */   }
/*     */   
/*     */   public short getPrazoTotal() {
/* 175 */     return this.prazoTotal;
/*     */   }
/*     */   
/*     */   public void setPrazoTotal(short prazoTotal) {
/* 179 */     this.prazoTotal = prazoTotal;
/*     */   }
/*     */   
/*     */   public String getAssinaturaDigital() {
/* 183 */     return this.assinaturaDigital;
/*     */   }
/*     */   
/*     */   public void setAssinaturaDigital(String assinaturaDigital) {
/* 187 */     this.assinaturaDigital = assinaturaDigital;
/*     */   }
/*     */   
/*     */   public String getFilial() {
/* 191 */     return this.filial;
/*     */   }
/*     */   
/*     */   public void setFilial(String filial) {
/* 195 */     this.filial = filial;
/*     */   }
/*     */   
/*     */   public Date getDataEmissaoLaudo() {
/* 199 */     return this.dataEmissaoLaudo;
/*     */   }
/*     */   
/*     */   public void setDataEmissaoLaudo(Date dataEmissaoLaudo) {
/* 203 */     this.dataEmissaoLaudo = dataEmissaoLaudo;
/*     */   }

/*     */   public String getIdentVistoria() {
/* 191 */     return this.identvistoria;
/*     */   }
/*     */   
/*     */   public void setIdentVistoria(String identvistoria) {
/* 195 */     this.identvistoria = identvistoria;
/*     */   }

/*     */   public String getSeqUnidadeNegocio() {
/*  55 */     return this.seqUnidadeNegocio;
/*     */   }
/*     */   
            public void setSeqUnidadeNegocio(String seqUnidadeNegocio) {
              this.seqUnidadeNegocio = seqUnidadeNegocio;
            }
            
            public String getunidadenegocioNome() {
              return this.unidadenegocioNome;
            }
                       
            public void setunidadenegocioNome(String unidadenegocioNome) {
              this.unidadenegocioNome = unidadenegocioNome;
            }
            
/*     */   public String getMaraberto() {
/* 143 */     return this.maraberto;
/*     */   }
/*     */   
/*     */   public void setMaraberto(String maraberto) {
/* 147 */     this.maraberto = maraberto;
/*     */   }            
            
/*     */   public String getNavint() {
/* 143 */     return this.navint;
/*     */   }
/*     */   
/*     */   public void setNavint(String navint) {
/* 147 */     this.navint = navint;
/*     */   }

/*     */   public String getArea1() {
/* 143 */     return this.area1;
/*     */   }
/*     */   
/*     */   public void setArea1(String area1) {
/* 147 */     this.area1 = area1;
/*     */   }

/*     */   public String getArea2() {
/* 143 */     return this.area2;
/*     */   }
/*     */   
/*     */   public void setArea2(String area2) {
/* 147 */     this.area2 = area2;
/*     */   }




}
