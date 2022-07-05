/*    */ package NvTipoEmbarcacao;
/*    */ 
/*    */ import ClausulaSQL.ClausulaWhere;
/*    */ import ClausulaSQL.GeneroCondicaoWhere;
/*    */ import ClausulaSQL.OperacaoCondicaoWhere;
/*    */ import ClausulaSQL.TipoCondicaoWhere;
/*    */ import Util.Situacao;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Date;
/*    */ import java.util.List;

/*    */ public class NvTipoEmbarcacaoService
/*    */ {
/*    */   public NvTipoEmbarcacao salvar(NvTipoEmbarcacao nvTipoEmbarcacao)
/*    */   {
/* 23 */     NvTipoEmbarcacaoDAO dao = new NvTipoEmbarcacaoDAO();
/* 24 */     if (nvTipoEmbarcacao.getSeqNvTipoEmbarcacao() == null) {
/* 25 */       nvTipoEmbarcacao.setDataCadastro(new Date());
/* 26 */       dao.inserir(nvTipoEmbarcacao);
/* 27 */       return nvTipoEmbarcacao;
/*    */     }
/* 29 */     dao.alterar(nvTipoEmbarcacao);
/* 30 */     return nvTipoEmbarcacao;
/*    */   }
/*    */   
/*    */   public List<NvTipoEmbarcacao> listar(String pString, Situacao pSituacao)
/*    */   {
/* 35 */     NvTipoEmbarcacaoDAO dao = new NvTipoEmbarcacaoDAO();
/* 36 */     List<NvTipoEmbarcacao> listaNvTipoEmbarcacao = new ArrayList();
/* 37 */     ClausulaWhere condicao = new ClausulaWhere();
/*    */     
/* 39 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "nome", GeneroCondicaoWhere.contem, pString, TipoCondicaoWhere.Texto);
/*    */     
/* 41 */     if (pSituacao == Situacao.ATIVO) {
/* 42 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "situacao", GeneroCondicaoWhere.igual, "ATIVO", TipoCondicaoWhere.Texto);
/* 43 */     } else if (pSituacao == Situacao.INATIVO) {
/* 44 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "situacao", GeneroCondicaoWhere.igual, "INATIVO", TipoCondicaoWhere.Texto);
/*    */     }
/*    */     
/* 47 */     listaNvTipoEmbarcacao = dao.listar(condicao);
/* 48 */     return listaNvTipoEmbarcacao;
/*    */   }
/*    */   
/*    */   public boolean deletar(NvTipoEmbarcacao nvTipoEmbarcacao) {
/* 52 */     NvTipoEmbarcacaoDAO dao = new NvTipoEmbarcacaoDAO();
/* 53 */     return dao.deletar(nvTipoEmbarcacao);
/*    */   }
/*    */ }

