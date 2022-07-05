/*    */ package TipoTarefa;
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
/*    */ public class TipoTarefaService
/*    */ {
/*    */   public TipoTarefa salvar(TipoTarefa tipoTarefa)
/*    */   {
/* 23 */     TipoTarefaDAO dao = new TipoTarefaDAO();
/* 24 */     if (tipoTarefa.getSeqTipoTarefa() == null) {
/* 25 */       tipoTarefa.setDataCadastro(new Date());
/* 26 */       return dao.inserir(tipoTarefa);
/*    */     }
/* 28 */     return dao.alterar(tipoTarefa);
/*    */   }
/*    */   
/*    */   public List<TipoTarefa> listar(String pSeqEmpresa, String pString, Situacao pSituacao)
/*    */   {
/* 33 */     TipoTarefaDAO dao = new TipoTarefaDAO();
/* 34 */     List<TipoTarefa> listaTipoTarefa = new ArrayList();
/* 35 */     ClausulaWhere condicao = new ClausulaWhere();
/*    */     
/* 37 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "nome", GeneroCondicaoWhere.contem, pString, TipoCondicaoWhere.Texto);
/* 38 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(pSeqEmpresa), TipoCondicaoWhere.Numero);
/*    */     
/* 40 */     if (pSituacao == Situacao.ATIVO) {
/* 41 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "situacao", GeneroCondicaoWhere.igual, "ATIVO", TipoCondicaoWhere.Texto);
/* 42 */     } else if (pSituacao == Situacao.INATIVO) {
/* 43 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "situacao", GeneroCondicaoWhere.igual, "INATIVO", TipoCondicaoWhere.Texto);
/*    */     }
/*    */     
/* 46 */     listaTipoTarefa = dao.listar(condicao);
/* 47 */     return listaTipoTarefa;
/*    */   }
/*    */   
/*    */   public boolean deletar(TipoTarefa tipoTarefa) {
/* 51 */     TipoTarefaDAO dao = new TipoTarefaDAO();
/* 52 */     return dao.deletar(tipoTarefa);
/*    */   }
/*    */ }

