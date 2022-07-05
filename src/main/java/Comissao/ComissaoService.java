/*    */ package Comissao;
/*    */ 
/*    */ import ClausulaSQL.ClausulaWhere;
/*    */ import ClausulaSQL.GeneroCondicaoWhere;
/*    */ import ClausulaSQL.OperacaoCondicaoWhere;
/*    */ import ClausulaSQL.TipoCondicaoWhere;
/*    */ import java.io.PrintStream;
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
/*    */ public class ComissaoService
/*    */ {
/*    */   public static void main(String[] args)
/*    */   {
/* 23 */     ComissaoService cs = new ComissaoService();
/* 24 */     List<Comissao> listaComissao = new ArrayList();
/*    */     
/*    */ 
/* 27 */     for (Comissao comissao : listaComissao) {
/* 28 */       System.out.println(comissao.getValor());
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public Comissao salvar(Comissao comissao)
/*    */   {
/* 35 */     ComissaoDAO dao = new ComissaoDAO();
/* 36 */     if (comissao.getSeqComissao() == null) {
/* 37 */       comissao.setDataCadastro(new Date());
/* 38 */       return dao.inserir(comissao);
/*    */     }
/* 40 */     return dao.alterar(comissao);
/*    */   }
/*    */   
/*    */   public List<Comissao> listarPorUsuario(String pSeqUsuario)
/*    */   {
/* 45 */     ComissaoDAO dao = new ComissaoDAO();
/* 46 */     List<Comissao> listaComissao = new ArrayList();
/* 47      ClausulaWhere condicao = new ClausulaWhere();
   
/    condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "comissao.seq_usuario", GeneroCondicaoWhere.igual, String.valueOf(pSeqUsuario), TipoCondicaoWhere.Numero);*/
/*    */     
/* 51 */     listaComissao = dao.listar();
/* 52 */     return listaComissao;
/*    */   }
/*    */   
/*    */   public List<Comissao> listarLiberadoPorUsuario(String pSeqUsuario) {
/* 56 */     ComissaoDAO dao = new ComissaoDAO();
/* 57 */     List<Comissao> listaComissao = new ArrayList();
/* 58 */     ClausulaWhere condicao = new ClausulaWhere();
/*    */     
/* 60 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "comissao.seq_usuario", GeneroCondicaoWhere.igual, String.valueOf(pSeqUsuario), TipoCondicaoWhere.Numero);
/* 61 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "comissao.disponivel_app", GeneroCondicaoWhere.igual, "Sim", TipoCondicaoWhere.Texto);
/*    */     
/* 63 */     listaComissao = dao.listar();
/* 64 */     return listaComissao;
/*    */   }
/*    */   
/*    */   public boolean deletar(Comissao comissao) {
/* 68 */     ComissaoDAO dao = new ComissaoDAO();
/* 69 */     return dao.deletar(comissao);
/*    */   }
/*    */ }

