/*    */ package ParceiroVinculoEmbarcacao;
/*    */ 
/*    */ import ClausulaSQL.ClausulaWhere;
/*    */ import ClausulaSQL.GeneroCondicaoWhere;
/*    */ import ClausulaSQL.OperacaoCondicaoWhere;
/*    */ import ClausulaSQL.TipoCondicaoWhere;
/*    */ import Util.Situacao;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Date;
/*    */ import java.util.List;

/*    */ public class ParceiroVinculoEmbarcacaoService
/*    */ {
/*    */   public ParceiroVinculoEmbarcacao salvar(ParceiroVinculoEmbarcacao parceiroVinculoEmbarcacao)
/*    */   {
/* 23 */     ParceiroVinculoEmbarcacaoDAO dao = new ParceiroVinculoEmbarcacaoDAO();
/* 24 */     if (parceiroVinculoEmbarcacao.getSeqParceiroVEmbarcacao() == null) {
/* 25 */       parceiroVinculoEmbarcacao.setDataCadastro(new Date());
/* 26 */       return dao.inserir(parceiroVinculoEmbarcacao);
/*    */     }
/* 28 */     return dao.alterar(parceiroVinculoEmbarcacao);
/*    */   }
/*    */   
/*    */   public List<ParceiroVinculoEmbarcacao> listar(String pSeqEmpresa, String pString, Situacao pSituacao)
/*    */   {
/* 33 */     ParceiroVinculoEmbarcacaoDAO dao = new ParceiroVinculoEmbarcacaoDAO();
/* 34 */     List<ParceiroVinculoEmbarcacao> listaParceiroVinculoEmbarcacao = new ArrayList();
/* 35 */     ClausulaWhere condicao = new ClausulaWhere();
/* 36 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "nv_embarcacao.seq_nv_embarcacao", GeneroCondicaoWhere.igual, String.valueOf(pString), TipoCondicaoWhere.Numero);
/* 37 */     listaParceiroVinculoEmbarcacao = dao.listar(condicao);

/* 54 */     return listaParceiroVinculoEmbarcacao;
/*    */   }
/*    */   
/*    */   public boolean deletar(ParceiroVinculoEmbarcacao parceiroVinculoEmbarcacao) {
/* 58 */     ParceiroVinculoEmbarcacaoDAO dao = new ParceiroVinculoEmbarcacaoDAO();
/* 59 */     return dao.deletar(parceiroVinculoEmbarcacao);
/*    */   }
/*    */ }
