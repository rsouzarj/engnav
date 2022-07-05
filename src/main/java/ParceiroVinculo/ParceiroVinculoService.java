/*    */ package ParceiroVinculo;
/*    */ 
/*    */ import ClausulaSQL.ClausulaWhere;
/*    */ import ClausulaSQL.GeneroCondicaoWhere;
/*    */ import ClausulaSQL.OperacaoCondicaoWhere;
/*    */ import ClausulaSQL.TipoCondicaoWhere;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ParceiroVinculoService
/*    */ {
/*    */   public ParceiroVinculo salvar(ParceiroVinculo parceiroVinculo)
/*    */   {
/* 22 */     ParceiroVinculoDAO dao = new ParceiroVinculoDAO();
/* 23 */     if (parceiroVinculo.getSeqParceiroVinculo() == null) {
/* 24 */       parceiroVinculo.setDataCadastro(new Date());
/* 25 */       dao.inserir(parceiroVinculo);
/* 26 */       return parceiroVinculo;
/*    */     }
/* 28 */     dao.alterar(parceiroVinculo);
/* 29 */     return parceiroVinculo;
/*    */   }
/*    */   
/*    */   public List<ParceiroVinculo> listar(String pSeqParceiro)
/*    */   {
/* 34 */     ParceiroVinculoDAO dao = new ParceiroVinculoDAO();
/* 35 */     List<ParceiroVinculo> listaParceiroVinculo = new ArrayList();
/* 36 */     ClausulaWhere condicao = new ClausulaWhere();
/* 37 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "parceiro.seq_parceiro", GeneroCondicaoWhere.igual, String.valueOf(pSeqParceiro), TipoCondicaoWhere.Numero);
/* 38 */     listaParceiroVinculo = dao.listar(condicao);
/* 39 */     return listaParceiroVinculo;
/*    */   }
/*    */   
/*    */   public boolean deletar(ParceiroVinculo parceiroVinculo) {
/* 43 */     ParceiroVinculoDAO dao = new ParceiroVinculoDAO();
/* 44 */     return dao.deletar(parceiroVinculo);
/*    */   }
/*    */ }

