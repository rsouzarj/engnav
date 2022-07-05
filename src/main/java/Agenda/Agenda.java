/*     */ package Agenda;
/*     */ 
/*     */ import java.util.Date;
 
/*     */ public class Agenda
/*     */ {
/*     */   private String seqTipoAgendaNome;
            private String seqAgenda;
/*     */   private String seqUsuario;
/*     */   private String seqParceiro;
/*     */   private String assunto;
/*     */   private String descricao;
/*     */   private Date dtInicio;
/*     */   private Date dtFim;
/*     */   private Date dataCadastro;
/*     */   private String cor;
/*     */   private String seqTipoAgenda;
/*     */   private String nomeParceiro;
            private String nomeUsuario;
/*     */   
            public String getSeqAgenda()
/*     */   {
/*  29 */     return this.seqAgenda;
/*     */   }
/*     */   
/*     */   public void setSeqTipoAgendaNome(String seqTipoAgendaNome) {
/*  33 */     this.seqTipoAgendaNome = seqTipoAgendaNome;
/*     */   }

            public String getSeqTipoAgendaNome()
/*     */   {
/*  29 */     return this.seqTipoAgendaNome;
/*     */   }
/*     */   

/*     */   public void setSeqAgenda(String seqAgenda) {
/*  33 */     this.seqAgenda = seqAgenda;
/*     */   }
/*     */   
/*     */   public String getSeqUsuario() {
/*  37 */     return this.seqUsuario;
/*     */   }
/*     */   
/*     */   public void setSeqUsuario(String seqUsuario) {
/*  41 */     this.seqUsuario = seqUsuario;
/*     */   }
/*     */   
/*     */   public String getSeqParceiro() {
/*  45 */     return this.seqParceiro;
/*     */   }
/*     */   
/*     */   public void setSeqParceiro(String seqParceiro) {
/*  49 */     this.seqParceiro = seqParceiro;
/*     */   }
/*     */   
/*     */   public String getAssunto() {
/*  53 */     return this.assunto;
/*     */   }
/*     */   
/*     */   public void setAssunto(String assunto) {
/*  57 */     this.assunto = assunto;
/*     */   }
/*     */   
/*     */   public String getDescricao() {
/*  61 */     return this.descricao;
/*     */   }
/*     */   
/*     */   public void setDescricao(String descricao) {
/*  65 */     this.descricao = descricao;
/*     */   }
/*     */   
/*     */   public Date getDtInicio() {
/*  69 */     return this.dtInicio;
/*     */   }
/*     */   
/*     */   public void setDtInicio(Date dtInicio) {
/*  73 */     this.dtInicio = dtInicio;
/*     */   }
/*     */   
/*     */   public Date getDtFim() {
/*  77 */     return this.dtFim;
/*     */   }
/*     */   
/*     */   public void setDtFim(Date dtFim) {
/*  81 */     this.dtFim = dtFim;
/*     */   }
/*     */   
/*     */   public Date getDataCadastro() {
/*  85 */     return this.dataCadastro;
/*     */   }
/*     */   
/*     */   public void setDataCadastro(Date dataCadastro) {
/*  89 */     this.dataCadastro = dataCadastro;
/*     */   }
/*     */   
/*     */   public String getCor() {
/*  93 */     return this.cor;
/*     */   }
/*     */   
/*     */   public void setCor(String cor) {
/*  97 */     this.cor = cor;
/*     */   }
/*     */   
/*     */   public String getSeqTipoAgenda() {
/* 101 */     return this.seqTipoAgenda;
/*     */   }
/*     */   
/*     */   public void setSeqTipoAgenda(String seqTipoAgenda) {
/* 105 */     this.seqTipoAgenda = seqTipoAgenda;
/*     */   }

/*     */   public String getNomeUsuario() {
/* 101 */     return this.nomeUsuario;
/*     */   }
/*     */   
/*     */   public void setNomeUsuario(String nomeUsuario) {
/* 105 */     this.nomeUsuario = nomeUsuario;
/*     */   }

            public String getNomeParceiro() {
/*  29 */     return this.nomeParceiro;
/*     */   }
            
/*     */   public void setNomeParceiro(String nomeParceiro) {
/*  33 */     this.nomeParceiro = nomeParceiro;
/*     */   }
/*     */ }
