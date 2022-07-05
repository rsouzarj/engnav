/*    */ package Agenda;
/*    */ 
/*    */ import ClausulaSQL.ClausulaWhere;
/*    */ import ClausulaSQL.GeneroCondicaoWhere;
/*    */ import ClausulaSQL.OperacaoCondicaoWhere;
/*    */ import ClausulaSQL.TipoCondicaoWhere;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Date;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AgendaService
/*    */ {
/*    */   public Agenda salvar(Agenda agenda)
/*    */   {
/* 21 */     AgendaDAO dao = new AgendaDAO();
/* 22 */     if (agenda.getSeqAgenda() == null) {
/* 23 */       agenda.setDataCadastro(new Date());
                return dao.inserir(agenda);
/*    */     }
/* 26 */     return dao.alterar(agenda);
/* 27 */  }
/*    */    
/*    */   public List<Agenda> listarPorUsuario(String pSeqUsuario)
/*    */   {
/* 33 */     AgendaDAO dao = new AgendaDAO();
/* 34 */     List<Agenda> listaAgenda = new ArrayList();
/* 35 */     ClausulaWhere condicao = new ClausulaWhere();
/* 36 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "agenda.seq_usuario", GeneroCondicaoWhere.igual, String.valueOf(pSeqUsuario), TipoCondicaoWhere.Numero);
/* 37 */     listaAgenda = dao.listar(condicao);
/* 38 */     return listaAgenda;
/*    */   }

/*    */   public List<Agenda> listarTodosUsuarios(String pSeqUsuario)
/*    */   {
/* 33 */     AgendaDAO dao = new AgendaDAO();
/* 34 */     List<Agenda> listaAgenda = new ArrayList();
/* 35 */     ClausulaWhere condicao = new ClausulaWhere();
/* 36 */     listaAgenda = dao.listar(condicao);
/* 38 */     return listaAgenda;
/*    */   }

/*    */   
/*    */   public boolean deletar(Agenda agenda) {
/* 42 */     AgendaDAO dao = new AgendaDAO();
/* 43 */     return dao.deletar(agenda);
/*    */   }
/*    */ }


