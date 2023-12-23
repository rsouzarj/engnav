/*    */ package Participante;


import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/*    */ public class Participante
/*    */ {
/*    */   private String seqParticipante;
/*    */   private String seqAgenda;
/*    */   private Date dataCadastro;
/*    */   private String participante;
          List<Participante> listaParticipante;
/*    */   
/*    */   public String getSeqParticipante()
/*    */   {
/* 25 */     return this.seqParticipante;
/*    */   }
/*    */   
/*    */   public void setSeqParticipante(String seqParticipante) {
/* 29 */     this.seqParticipante = seqParticipante;
/*    */   }
/*    */   
/*    */   public String getSeqAgenda() {
/* 33 */     return this.seqAgenda;
/*    */   }
/*    */   
/*    */   public void setSeqAgenda(String seqAgenda) {
/* 37 */     this.seqAgenda = seqAgenda;
/*    */   }
  
/*    */   public Date getDataCadastro() {
/* 49 */     return this.dataCadastro;
/*    */   }
/*    */   
/*    */   public void setDataCadastro(Date dataCadastro) {
/* 53 */     this.dataCadastro = dataCadastro;
/*    */   }
/*    */   
/*    */   public String getParticipante() {
/* 57 */     return this.participante;
/*    */   }
/*    */   
/*    */   public void setParticipante(String participante) {
/* 61 */     this.participante = participante;
/*    */   }

/*    */ }
