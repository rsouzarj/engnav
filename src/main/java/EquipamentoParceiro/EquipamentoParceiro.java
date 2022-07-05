/*    */ package EquipamentoParceiro;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EquipamentoParceiro
/*    */ {
/*    */   private String seqEquipamentoParceiro;
           private String seqParceiro;
           private String seqEquipamento;

/*    */   private String parceiroNome;
/*    */   
/*    */   private String equipamentoNome;
/*    */   private String fmas;
/*    */   private String modelo;
           private String nserie;
           private String capacidadeMax;
           private String tag;


/*    */   public String getSeqEquipamentoParceiro()
/*    */   {
/* 22 */     return this.seqEquipamentoParceiro;
/*    */   }
/*    */   
/*    */   public void setSeqEquipamentoParceiro(String seqEquipamentoParceiro) {
/* 26 */     this.seqEquipamentoParceiro = seqEquipamentoParceiro;
/*    */   }
/*    */   
/*    */   public String getSeqParceiro() {
/* 30 */     return this.seqParceiro;
/*    */   }
/*    */   
/*    */   public void setSeqParceiro(String seqParceiro) {
/* 34 */     this.seqParceiro = seqParceiro;
/*    */   }
/*    */   
/*    */   public String getSeqEquipamento() {
/* 38 */     return this.seqEquipamento;
/*    */   }
/*    */   
/*    */   public void setSeqEquipamento(String seqEquipamento) {
/* 42 */     this.seqEquipamento = seqEquipamento;
/*    */   }
/*    */   
/*    */   public String getParceiroNome() {
/* 46 */     return this.parceiroNome;
/*    */   }
/*    */   
/*    */   public void setParceiroNome(String parceiroNome) {
/* 50 */     this.parceiroNome = parceiroNome;
/*    */   }
/*    */   
/*    */   public String getEquipamentoNome() {
/* 54 */     return this.equipamentoNome;
/*    */   }
/*    */   
/*    */   public void setEquipamentoNome(String equipamentoNome) {
/* 58 */     this.equipamentoNome = equipamentoNome;
/*    */   }
    public String getCapacidadeMax() {
        /* 48 */ return this.capacidadeMax;
        /*    */    }

    /*    */ public void setCapacidadeMax(String capacidadeMax) {
        /* 52 */ this.capacidadeMax = capacidadeMax;
        /*    */    }
        /*    */ public void setModelo(String modelo) {
        /* 52 */ this.modelo = modelo;
        /*    */    }

    public String getModelo() {
        /* 48 */ return this.modelo;
        /*    */    }
        /*    */ public void setNserie(String nserie) {
        /* 52 */ this.nserie = nserie;
        /*    */    }

    public String getNserie() {
        /* 48 */ return this.nserie;
        /*    */    }
        public String getFmas() {
        /* 48 */ return this.fmas;
        /*    */    }

    /*    */
 /*    */ public void setFmas(String fmas) {
        /* 52 */ this.fmas = fmas;
        /*    */    }
     public String getTag() {
        /* 48 */ return this.tag;
        /*    */    }

    public void setTag(String tag) {
        /* 52 */ this.tag = tag;
        /*    */    }


/*    */ }

