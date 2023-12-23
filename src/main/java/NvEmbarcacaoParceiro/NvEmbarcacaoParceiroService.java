/*    */ package NvEmbarcacaoParceiro;
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
/*    */ public class NvEmbarcacaoParceiroService
/*    */ {
/*    */   public NvEmbarcacaoParceiro salvar(NvEmbarcacaoParceiro nvEmbarcacaoParceiro)
/*    */   {
/* 21 */     NvEmbarcacaoParceiroDAO dao = new NvEmbarcacaoParceiroDAO();
/* 22 */     if (nvEmbarcacaoParceiro.getSeqNvEmbarcacaoParceiro() == null) {
/* 23 */       return dao.inserir(nvEmbarcacaoParceiro);
/*    */     }
/* 25 */     return dao.alterar(nvEmbarcacaoParceiro);
/*    */   }
/*    */   
/*    */   public List<NvEmbarcacaoParceiro> listarPorEmbarcacao(String pSeqEmbarcacao)
/*    */   {
/* 30 */     NvEmbarcacaoParceiroDAO dao = new NvEmbarcacaoParceiroDAO();
/* 31 */     List<NvEmbarcacaoParceiro> listaNvEmbarcacaoParceiro = new ArrayList();
/* 32 */     ClausulaWhere condicao = new ClausulaWhere();
/*    */     
/* 34 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "NV_EMBARCACAO_PARCEIRO.seq_embarcacao", GeneroCondicaoWhere.igual, String.valueOf(pSeqEmbarcacao), TipoCondicaoWhere.Numero);
/*    */     
/* 36 */     listaNvEmbarcacaoParceiro = dao.listar(condicao);
/* 37 */     return listaNvEmbarcacaoParceiro;
/*    */   }
/*    */   
/*    */   public List<NvEmbarcacaoParceiro> listarPorParceiro(String pSeqParceiro) {
/* 41 */     NvEmbarcacaoParceiroDAO dao = new NvEmbarcacaoParceiroDAO();
/* 42 */     List<NvEmbarcacaoParceiro> listaNvEmbarcacaoParceiro = new ArrayList();
/* 43 */     ClausulaWhere condicao = new ClausulaWhere();
/*    */     
/* 45 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "NV_EMBARCACAO_PARCEIRO.seq_parceiro", GeneroCondicaoWhere.igual, String.valueOf(pSeqParceiro), TipoCondicaoWhere.Numero);
/*    */     
/* 47 */     listaNvEmbarcacaoParceiro = dao.listar(condicao);
/* 48 */     return listaNvEmbarcacaoParceiro;
/*    */   }

/*    */   public List<NvEmbarcacaoParceiro> listarPorArmador(String pSeqParceiro) {
/* 41 */     NvEmbarcacaoParceiroDAO dao = new NvEmbarcacaoParceiroDAO();
/* 42 */     List<NvEmbarcacaoParceiro> listaNvEmbarcacaoParceiro = new ArrayList();
/* 43 */     ClausulaWhere condicao = new ClausulaWhere();
/*    */     
/* 45 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "parceiro.seq_parceiro", GeneroCondicaoWhere.igual, String.valueOf(pSeqParceiro), TipoCondicaoWhere.Numero);
/*    */     
/* 47 */     listaNvEmbarcacaoParceiro = dao.listar(condicao);
/* 48 */     return listaNvEmbarcacaoParceiro;
/*    */   }

/*    */   
/*    */   public List<NvEmbarcacaoParceiro> listarPorDono(String pSeqDono) {
/* 52 */     NvEmbarcacaoParceiroDAO dao = new NvEmbarcacaoParceiroDAO();
/* 53 */     List<NvEmbarcacaoParceiro> listaNvEmbarcacaoParceiro = new ArrayList();
/* 54 */     ClausulaWhere condicao = new ClausulaWhere();
/*    */     
/* 56 */     condicao.AdicionarCondicaoManual(" inner join documento_item_embarcacao on documento_item_embarcacao.SEQ_NV_EMBARCACAO = NV_EMBARCACAO_PARCEIRO.SEQ_EMBARCACAO ");
/* 57 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "documento_item_embarcacao.SEQ_DOCUMENTO", GeneroCondicaoWhere.igual, String.valueOf(pSeqDono), TipoCondicaoWhere.Numero);
/* 58 */     listaNvEmbarcacaoParceiro = dao.listar(condicao);
/* 59 */     return listaNvEmbarcacaoParceiro;
/*    */   }
/*    */   
/*    */   public boolean deletar(NvEmbarcacaoParceiro nvEmbarcacaoParceiro) {
/* 63 */     NvEmbarcacaoParceiroDAO dao = new NvEmbarcacaoParceiroDAO();
/* 64 */     return dao.deletar(nvEmbarcacaoParceiro);
/*    */   }
/*    */ }


/* Location:              /Users/diogo.lima/Documents/PEDIDO.jar!/NvEmbarcacaoParceiro/NvEmbarcacaoParceiroService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */