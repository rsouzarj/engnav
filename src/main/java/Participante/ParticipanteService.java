/*    */ package Participante;
/*    */ 
         import Participante.*;
/*    */ import ClausulaSQL.ClausulaWhere;
/*    */ import ClausulaSQL.GeneroCondicaoWhere;
/*    */ import ClausulaSQL.OperacaoCondicaoWhere;
/*    */ import ClausulaSQL.TipoCondicaoWhere;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Date;
/*    */ import java.util.List;

/*    */ public class ParticipanteService
/*    */ {
/*    */   public Participante salvar(Participante participante)
/*    */   {
/* 22 */     ParticipanteDAO dao = new ParticipanteDAO();
/* 23 */     if (participante.getSeqParticipante() == null) {
/* 24 */       participante.setDataCadastro(new Date());
/* 25 */       dao.inserir(participante);
/* 26 */       return participante;
/*    */     }
/* 28 */     dao.alterar(participante);
/* 29 */     return participante;
/*    */   }
/*    */   
/*    */   public List<Participante> listar(String pSeqParticipante)
/*    */   {
/* 34 */     ParticipanteDAO dao = new ParticipanteDAO();
/* 35 */     List<Participante> listaParticipante = new ArrayList();
/* 36 */     ClausulaWhere condicao = new ClausulaWhere();
/* 37 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "participante_ag.seq_participante", GeneroCondicaoWhere.igual, String.valueOf(pSeqParticipante), TipoCondicaoWhere.Numero);
/* 38 */     listaParticipante = dao.listar(condicao);
/* 39 */     return listaParticipante;
/*    */   }
/*    */   
/*    */   public boolean deletar(Participante participante) {
/* 43 */     ParticipanteDAO dao = new ParticipanteDAO();
/* 44 */     return dao.deletar(participante);
/*    */   }
/*    */ }

