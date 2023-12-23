/*    */ package ParceiroVinculo;
/*    */ 
/*    */ import Parceiro.Parceiro;
/*    */ import Parceiro.ParceiroService;

/*    */ public class ParceiroTeste
/*    */ {
/*    */   public static void main(String[] args)
/*    */   {
/* 19 */     ParceiroService parceiroService = new ParceiroService();
/*    */     
/* 21 */     Parceiro parceiro = new Parceiro();
/*    */     
/* 23 */     parceiro.setNome("TESTE");
/* 24 */     parceiro.setSeqTipoParceiro("777");
/* 25 */     parceiro.setSeqEmpresa("77");
/* 26 */     parceiro.setSeqParceiroInclusao("7777");
/* 27 */     parceiroService.salvar(parceiro);
/*    */   }
/*    */ }
