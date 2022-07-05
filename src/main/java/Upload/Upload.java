/*     */ package Upload;
/*     */ 
/*     */ import java.util.Date;
/*     */ 

/*     */ public class Upload
/*     */ {
/*     */   private String seqUpload;
/*     */   private Date dataCadastro;
/*     */   private String seqDocumento;
/*     */   private String origem;
/*     */   private String seqUsuario;
/*     */   private String url;
/*     */   private String nomeArquivo;
/*     */   private String seqEmpresa;
/*     */   private String seqNvVistoria;
/*     */   private String tipoArquivo;
/*     */   private String nomeUsuario;
            private String seqFinanceiro;
            private String seqNvEmbarcacao;
            private Date   dataEmissao;
            private Date   dataValidade;
            private String seqParceiro;
            private String seqCertificado;
           
/*     */   
/*     */   public String getSeqUpload()
/*     */   {
/*  29 */     return this.seqUpload;
/*     */   }
/*     */   
/*     */   public void setSeqUpload(String seqUpload) {
/*  33 */     this.seqUpload = seqUpload;
/*     */   }
/*     */   
/*     */   public Date getDataCadastro() {
/*  37 */     return this.dataCadastro;
/*     */   }
/*     */   
/*     */   public void setDataCadastro(Date dataCadastro) {
/*  41 */     this.dataCadastro = dataCadastro;
/*     */   }

/*     */   public Date getDataEmissao() {
/*  37 */     return this.dataEmissao;
/*     */   }
/*     */   
/*     */   public void setDataEmissao(Date dataEmissao) {
/*  41 */     this.dataEmissao = dataEmissao;
/*     */   }

/*     */   public Date getDataValidade() {
/*  37 */     return this.dataValidade;
/*     */   }
/*     */   
/*     */   public void setDataValidade(Date dataValidade) {
/*  41 */     this.dataValidade = dataValidade;
/*     */   }
/*     */   
/*     */   public String getSeqDocumento() {
/*  45 */     return this.seqDocumento;
/*     */   }
/*     */   
/*     */   public void setSeqDocumento(String seqDocumento) {
/*  49 */     this.seqDocumento = seqDocumento;
/*     */   }
/*     */   
/*     */   public String getOrigem() {
/*  53 */     return this.origem;
/*     */   }
/*     */   
/*     */   public void setOrigem(String origem) {
/*  57 */     this.origem = origem;
/*     */   }
/*     */   
/*     */   public String getSeqUsuario() {
/*  61 */     return this.seqUsuario;
/*     */   }
/*     */   
/*     */   public void setSeqUsuario(String seqUsuario) {
/*  65 */     this.seqUsuario = seqUsuario;
/*     */   }
/*     */   
/*     */   public String getUrl() {
/*  69 */     return this.url;
/*     */   }
/*     */   
/*     */   public void setUrl(String url) {
/*  73 */     this.url = url;
/*     */   }
/*     */   
/*     */   public String getNomeArquivo() {
/*  77 */     return this.nomeArquivo;
/*     */   }
/*     */   
/*     */   public void setNomeArquivo(String nomeArquivo) {
/*  81 */     this.nomeArquivo = nomeArquivo;
/*     */   }
/*     */   
/*     */   public String getSeqEmpresa() {
/*  85 */     return this.seqEmpresa;
/*     */   }
/*     */   
/*     */   public void setSeqEmpresa(String seqEmpresa) {
/*  89 */     this.seqEmpresa = seqEmpresa;
/*     */   }
/*     */   
/*     */   public String getSeqNvVistoria() {
/*  93 */     return this.seqNvVistoria;
/*     */   }
/*     */   
/*     */   public void setSeqNvVistoria(String seqNvVistoria) {
/*  97 */     this.seqNvVistoria = seqNvVistoria;
/*     */   }
/*     */   
/*     */   public String getTipoArquivo() {
/* 101 */     return this.tipoArquivo;
/*     */   }
/*     */   
/*     */   public void setTipoArquivo(String tipoArquivo) {
/* 105 */     this.tipoArquivo = tipoArquivo;
/*     */   }
/*     */   
/*     */   public String getNomeUsuario() {
/* 109 */     return this.nomeUsuario;
/*     */   }
/*     */   
/*     */   public void setNomeUsuario(String nomeUsuario) {
/* 113 */     this.nomeUsuario = nomeUsuario;
/*     */   }
/*     */   public String getSeqFinanceiro() {
/*  45 */     return this.seqFinanceiro;
/*     */   }
/*     */   
/*     */   public void setSeqFinanceiro(String seqFinanceiro) {
/*  49 */     this.seqFinanceiro = seqFinanceiro;
            }

/*     */   public String getSeqNvEmbarcacao() {
/*  45 */     return this.seqNvEmbarcacao;
/*     */   }
/*     */   
/*     */   public void setSeqNvEmbarcacao(String seqNvEmbarcacao) {
/*  49 */     this.seqNvEmbarcacao = seqNvEmbarcacao;
            }
/*     */   public String getSeqParceiro() {
/*  45 */     return this.seqParceiro;
/*     */   }
/*     */   
/*     */   public void setSeqParceiro(String seqParceiro) {
/*  49 */     this.seqParceiro = seqParceiro;
            }

/*     */   public String getSeqCertificado() {
/*  45 */     return this.seqCertificado;
/*     */   }
/*     */   
/*     */   public void setSeqCertificado(String seqCertificado) {
/*  49 */     this.seqCertificado = seqCertificado;
            }
         
/*     */ }
