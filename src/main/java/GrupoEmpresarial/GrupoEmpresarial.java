/*    */ package GrupoEmpresarial;
/*    */ 
/*    */ import java.util.Date;

/*    */ public class GrupoEmpresarial
/*    */ {
/*    */   private String seqGrupo;
/*    */   private String info;
/*    */   private String nome;
/*    */   private String situacao;
/*    */   private Date dataCadastro;
/*    */   private String seqEmpresa;
/*    */   
/*    */   public String getSeqGrupo()
/*    */   {
/* 22 */     return this.seqGrupo;
/*    */   }
/*    */   
/*    */   public void setSeqGrupo(String seqGrupo) {
/* 26 */     this.seqGrupo = seqGrupo;
/*    */   }
/*    */   
/*    */   public String getInfo() {
/* 30 */     return this.info;
/*    */   }
/*    */   
/*    */   public void setInfo(String info) {
/* 34 */     this.info = info;
/*    */   }
/*    */   
/*    */   public String getNome() {
/* 38 */     return this.nome;
/*    */   }
/*    */   
/*    */   public void setNome(String nome) {
/* 42 */     this.nome = nome;
/*    */   }
/*    */   
/*    */   public String getSituacao() {
/* 46 */     return this.situacao;
/*    */   }
/*    */   
/*    */   public void setSituacao(String situacao) {
/* 50 */     this.situacao = situacao;
/*    */   }
/*    */   
/*    */   public Date getDataCadastro() {
/* 54 */     return this.dataCadastro;
/*    */   }
/*    */   
/*    */   public void setDataCadastro(Date dataCadastro) {
/* 58 */     this.dataCadastro = dataCadastro;
/*    */   }
/*    */   
/*    */   public String getSeqEmpresa() {
/* 62 */     return this.seqEmpresa;
/*    */   }
/*    */   
/*    */   public void setSeqEmpresa(String seqEmpresa) {
/* 66 */     this.seqEmpresa = seqEmpresa;
/*    */   }
/*    */ }

