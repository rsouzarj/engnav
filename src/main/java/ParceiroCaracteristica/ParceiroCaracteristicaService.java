/*    */ package ParceiroCaracteristica;
/*    */ 
/*    */ import ClausulaSQL.ClausulaWhere;
/*    */ import ClausulaSQL.GeneroCondicaoWhere;
/*    */ import ClausulaSQL.OperacaoCondicaoWhere;
/*    */ import ClausulaSQL.TipoCondicaoWhere;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ParceiroCaracteristicaService
/*    */ {
/*    */   public ParceiroCaracteristica salvar(ParceiroCaracteristica parceiroCaracteristica)
/*    */   {
/* 22 */     ParceiroCaracteristicaDAO dao = new ParceiroCaracteristicaDAO();
/* 23 */     return dao.inserir(parceiroCaracteristica);
/*    */   }
/*    */   
/*    */   public List<ParceiroCaracteristica> listarPorParceiro(String pSeqParceiro) {
/* 27 */     ParceiroCaracteristicaDAO dao = new ParceiroCaracteristicaDAO();
/* 28 */     List<ParceiroCaracteristica> listaParceiroCaracteristica = new ArrayList();
/* 29 */     ClausulaWhere condicao = new ClausulaWhere();
/*    */     
/* 31 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "parceiro_caracteristica.seq_parceiro", GeneroCondicaoWhere.igual, String.valueOf(pSeqParceiro), TipoCondicaoWhere.Texto);
/* 32 */     listaParceiroCaracteristica = dao.listar(condicao);
/* 33 */     return listaParceiroCaracteristica;
/*    */   }
/*    */   
/*    */   public boolean deletar(String pSeqParceiro) {
/* 37 */     ParceiroCaracteristicaDAO dao = new ParceiroCaracteristicaDAO();
/* 38 */     return dao.deletar(pSeqParceiro);
/*    */   }
/*    */ }
