/*    */ package EquipamentoParceiro;
/*    */ 
/*    */ import ClausulaSQL.ClausulaWhere;
/*    */ import ClausulaSQL.GeneroCondicaoWhere;
/*    */ import ClausulaSQL.OperacaoCondicaoWhere;
/*    */ import ClausulaSQL.TipoCondicaoWhere;
/*    */ import Util.Situacao;
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
/*    */ 
/*    */ public class EquipamentoParceiroService
/*    */ {
/*    */   public EquipamentoParceiro salvar(EquipamentoParceiro equipamentoParceiro)
/*    */   {
/* 23 */     EquipamentoParceiroDAO dao = new EquipamentoParceiroDAO();
/* 24 */     if (equipamentoParceiro.getSeqEquipamentoParceiro() == null) {
/* 25 */       return dao.inserir(equipamentoParceiro);
/*    */     }
/* 27 */     return dao.alterar(equipamentoParceiro);
/*    */   }
/*    */   
/*    */   public List<EquipamentoParceiro> listar(String pSeqEmpresa, String pString, Situacao pSituacao)
/*    */   {
/* 32 */     EquipamentoParceiroDAO dao = new EquipamentoParceiroDAO();
/* 33 */     List<EquipamentoParceiro> listaEquipamentoParceiro = new ArrayList();
/* 34 */     ClausulaWhere condicao = new ClausulaWhere();
/*    */     
/* 36 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "nome", GeneroCondicaoWhere.contem, pString, TipoCondicaoWhere.Texto);
/* 37 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(pSeqEmpresa), TipoCondicaoWhere.Numero);
/*    */     
/* 39 */     if (pSituacao == Situacao.ATIVO) {
/* 40 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "situacao", GeneroCondicaoWhere.igual, "ATIVO", TipoCondicaoWhere.Texto);
/* 41 */     } else if (pSituacao == Situacao.INATIVO) {
/* 42 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "situacao", GeneroCondicaoWhere.igual, "INATIVO", TipoCondicaoWhere.Texto);
/*    */     }
/*    */     
/* 45 */     listaEquipamentoParceiro = dao.listar(condicao);
/* 46 */     return listaEquipamentoParceiro;
/*    */   }
/*    */   
/*    */   public List<EquipamentoParceiro> listarParceiro(String pSeqEquipamento) {
/* 50 */     EquipamentoParceiroDAO dao = new EquipamentoParceiroDAO();
/* 51 */     List<EquipamentoParceiro> listaEquipamentoParceiro = new ArrayList();
/* 52 */     ClausulaWhere condicao = new ClausulaWhere();
/*    */     
/* 54 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "equipamento.seq_equipamento", GeneroCondicaoWhere.igual, pSeqEquipamento, TipoCondicaoWhere.Numero);
/*    */     
/* 56 */     listaEquipamentoParceiro = dao.listar(condicao);
/* 57 */     return listaEquipamentoParceiro;
/*    */   }
/*    */   
/*    */   public boolean deletar(EquipamentoParceiro equipamentoParceiro) {
/* 61 */     EquipamentoParceiroDAO dao = new EquipamentoParceiroDAO();
/* 62 */     return dao.deletar(equipamentoParceiro);
/*    */   }
/*    */ }

