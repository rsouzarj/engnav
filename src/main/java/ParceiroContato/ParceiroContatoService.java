/*    */ package ParceiroContato;
/*    */ 
/*    */ import ClausulaSQL.ClausulaWhere;
/*    */ import ClausulaSQL.GeneroCondicaoWhere;
/*    */ import ClausulaSQL.OperacaoCondicaoWhere;
/*    */ import ClausulaSQL.TipoCondicaoWhere;
/*    */ import Util.Situacao;
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
/*    */ public class ParceiroContatoService
/*    */ {
/*    */   public ParceiroContato salvar(ParceiroContato parceiroContato)
/*    */   {
/* 23 */     ParceiroContatoDAO dao = new ParceiroContatoDAO();
/* 24 */     if (parceiroContato.getSeqParceiroContato() == null) {
/* 25 */       parceiroContato.setDataCadastro(new Date());
/* 26 */       parceiroContato.setSituacao("ATIVO");
/* 27 */       return dao.inserir(parceiroContato);
/*    */     }
/* 29 */     return dao.alterar(parceiroContato);
/*    */   }
/*    */   
/*    */   public List<ParceiroContato> listarPorParceiro(String pSeqParceiro, Situacao pSituacao)
/*    */   {
/* 34 */     ParceiroContatoDAO dao = new ParceiroContatoDAO();
/* 35 */     List<ParceiroContato> listaParceiroContato = new ArrayList();
/* 36 */     ClausulaWhere condicao = new ClausulaWhere();
/*    */     
/* 38 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "seq_parceiro", GeneroCondicaoWhere.igual, String.valueOf(pSeqParceiro), TipoCondicaoWhere.Numero);
/*    */     
/* 40 */     if (pSituacao == Situacao.ATIVO) {
/* 41 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "situacao", GeneroCondicaoWhere.igual, "ATIVO", TipoCondicaoWhere.Texto);
/* 42 */     } else if (pSituacao == Situacao.INATIVO) {
/* 43 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "situacao", GeneroCondicaoWhere.igual, "INATIVO", TipoCondicaoWhere.Texto);
/*    */     }
/*    */     
/* 46 */     listaParceiroContato = dao.listar(condicao);
/* 47 */     return listaParceiroContato;
/*    */   }
/*    */   
/*    */   public boolean deletar(ParceiroContato parceiroContato) {
/* 51 */     ParceiroContatoDAO dao = new ParceiroContatoDAO();
/* 52 */     return dao.deletar(parceiroContato);
/*    */   }
/*    */ }
