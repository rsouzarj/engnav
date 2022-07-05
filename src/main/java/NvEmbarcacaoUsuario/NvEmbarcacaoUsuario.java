/*    */ package NvEmbarcacaoUsuario;
/*    */ 

/*    */ import java.util.Date;

/*    */ public class NvEmbarcacaoUsuario
/*    */ {
/*    */   private String seqNvEmbarcacaoUsuario;
/*    */   private Date dataFim;
/*    */   private Date dataInicio;
/*    */   private String seqEmbarcacao;
/*    */   private String seqUsuario;
/*    */   private String usuarioNome;
/*    */  
           public String getSeqNvEmbarcacaoUsuario()
/*    */   {
/* 25 */     return this.seqNvEmbarcacaoUsuario;
/*    */   }
/*    */   
/*    */   public void setSeqNvEmbarcacaoUsuario(String seqNvEmbarcacaoUsuario) {
/* 29 */     this.seqNvEmbarcacaoUsuario = seqNvEmbarcacaoUsuario;
/*    */   }
/*    */   
/*    */   public Date getDataFim() {
/* 33 */     return this.dataFim;
/*    */   }
/*    */   
/*    */   public void setDataFim(Date dataFim) {
/* 37 */     this.dataFim = dataFim;
/*    */   }
/*    */   
/*    */   
/*    */   public Date getDataInicio() {
/* 49 */     return this.dataInicio;
/*    */   }
/*    */   
/*    */   public void setDataInicio(Date dataInicio) {
/* 53 */     this.dataInicio = dataInicio;
/*    */   }
/*    */   
/*    */   public String getSeqEmbarcacao() {
/* 57 */     return this.seqEmbarcacao;
/*    */   }
/*    */   
/*    */   public void setSeqEmbarcacao(String seqEmbarcacao) {
/* 61 */     this.seqEmbarcacao = seqEmbarcacao;
/*    */   }
/*    */   
/*    */   public String getSeqUsuario() {
/* 65 */     return this.seqUsuario;
/*    */   }
/*    */   
/*    */   public void setSeqUsuario(String seqUsuario) {
/* 69 */     this.seqUsuario = seqUsuario;
/*    */   }
/*    */   
           public String getNvUsuarioNome() {
/* 89 */     return this.usuarioNome;
/*    */   }
/*    */   
/*    */   public void setNvUsuarioNome(String usuarioNome) {
/* 93 */     this.usuarioNome = usuarioNome;
/*    */   }
/*    */ }
