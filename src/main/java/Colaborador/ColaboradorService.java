/*    */ package Colaborador;
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
/*    */ public class ColaboradorService
/*    */ {
/*    */   public Colaborador salvar(Colaborador colaborador)
/*    */   {
/* 23 */     ColaboradorDAO dao = new ColaboradorDAO();
/* 24 */     if (colaborador.getSeqColaborador() == null) {
/* 25 */       colaborador.setDataCadastro(new Date());
/* 26 */       return dao.inserir(colaborador);
/*    */     }
/* 28 */     return dao.alterar(colaborador);
/*    */   }
/*    */   
/*    */   public List<Colaborador> listar(String pSeqEmpresa, String pString, Situacao pSituacao)
/*    */   {
/* 33 */     ColaboradorDAO dao = new ColaboradorDAO();
/* 34 */     List<Colaborador> listaColaborador = new ArrayList();
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
/* 46 */     listaColaborador = dao.listar(condicao);
/* 47 */     return listaColaborador;
/*    */   }

/*    */   public List<Colaborador> listarv(String pSeqEmpresa, String pString, Situacao pSituacao)
/*    */   {
/* 33 */     ColaboradorDAO dao = new ColaboradorDAO();
/* 34 */     List<Colaborador> listaColaborador = new ArrayList();
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
/* 46 */     listaColaborador = dao.listarv(condicao);
/* 47 */     return listaColaborador;
/*    */   }





/*    */   
/*    */   public boolean deletar(Colaborador colaborador) {
/* 51 */     ColaboradorDAO dao = new ColaboradorDAO();
/* 52 */     return dao.deletar(colaborador);
/*    */   }
/*    */ }


/* Location:              /Users/diogo.lima/Documents/PEDIDO.jar!/Colaborador/ColaboradorService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */