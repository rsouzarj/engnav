/*     */ package FinanceiroEquipamento;
/*     */ 
/*     */ import ClausulaSQL.ClausulaWhere;
/*     */ import Util.Conexao;
/*     */ import Util.Sequence;
/*     */ import java.io.PrintStream;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FinanceiroEquipamentoDAO
/*     */ {
/*     */   public FinanceiroEquipamento inserir(FinanceiroEquipamento financeiroEquipamento)
/*     */   {
/*     */     try
/*     */     {
/*  27 */       String seq = Sequence.buscarNumeroSequence("SEQ_FINANCEIRO_EQUIPAMENTO");
/*  28 */       financeiroEquipamento.setSeqFinanceiroEquipamento(seq);
/*  29 */       Conexao conexao = new Conexao();
/*  30 */       Connection conn = Conexao.getConnection();
/*  31 */       String sql = "insert into FINANCEIRO_EQUIPAMENTO (SEQ_FINANCEIRO_EQUIPAMENTO,SEQ_EQUIPAMENTO,VALOR,SEQ_FINANCEIRO,comentario) values  (?,?,?,?,?)";
/*     */       
/*     */ 
/*     */ 
/*  35 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*  37 */       ps.setString(1, financeiroEquipamento.getSeqFinanceiroEquipamento());
/*  38 */       ps.setString(2, financeiroEquipamento.getSeqEquipamento());
/*  39 */       ps.setBigDecimal(3, financeiroEquipamento.getValor());
/*  40 */       ps.setString(4, financeiroEquipamento.getSeqFinanceiro());
/*  41 */       ps.setString(5, financeiroEquipamento.getComentario());
/*     */       
/*  43 */       ps.execute();
/*  44 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/*  47 */       Logger.getLogger(FinanceiroEquipamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  48 */       System.out.println(ex.getMessage());
/*     */     }
/*  50 */     return financeiroEquipamento;
/*     */   }
/*     */   
/*     */   public FinanceiroEquipamento alterar(FinanceiroEquipamento financeiroEquipamento) {
/*     */     try {
/*  55 */       Conexao conexao = new Conexao();
/*  56 */       Connection conn = Conexao.getConnection();
/*  57 */       String sql = "update FINANCEIRO_EQUIPAMENTO set SEQ_EQUIPAMENTO = ?,VALOR = ?,SEQ_FINANCEIRO = ?, comentario = ? where SEQ_FINANCEIRO_EQUIPAMENTO = ?";
/*     */       
/*  59 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*  61 */       ps.setString(1, financeiroEquipamento.getSeqEquipamento());
/*  62 */       ps.setBigDecimal(2, financeiroEquipamento.getValor());
/*  63 */       ps.setString(3, financeiroEquipamento.getSeqFinanceiro());
/*  64 */       ps.setString(4, financeiroEquipamento.getComentario());
/*  65 */       ps.setString(5, financeiroEquipamento.getSeqFinanceiroEquipamento());
/*  66 */       ps.execute();
/*  67 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/*  70 */       Logger.getLogger(FinanceiroEquipamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  71 */       System.out.println(ex.getMessage());
/*     */     }
/*     */     
/*  74 */     return financeiroEquipamento;
/*     */   }
/*     */   
/*     */   public List<FinanceiroEquipamento> listar(ClausulaWhere sClausula) {
/*     */     try {
/*  79 */       Conexao conexao = new Conexao();
/*  80 */       Connection conn = Conexao.getConnection();
/*  81 */       String sql = "SELECT FINANCEIRO_EQUIPAMENTO.*,\n equipamento.nome nomeEquipamento  FROM FINANCEIRO_EQUIPAMENTO inner join equipamento on equipamento.seq_equipamento = FINANCEIRO_EQUIPAMENTO.seq_equipamento" + sClausula.montarsClausula();
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*  86 */       System.out.println(sql);
/*     */       
/*  88 */       List<FinanceiroEquipamento> listaFinanceiroEquipamento = new ArrayList();
/*  89 */       PreparedStatement ps = conn.prepareStatement(sql);
/*  90 */       ResultSet rs = ps.executeQuery();
/*     */       
/*  92 */       while (rs.next()) {
/*  93 */         FinanceiroEquipamento financeiroEquipamento = new FinanceiroEquipamento();
/*  94 */         financeiroEquipamento.setSeqFinanceiroEquipamento(rs.getString("SEQ_FINANCEIRO_EQUIPAMENTO"));
/*  95 */         financeiroEquipamento.setSeqEquipamento(rs.getString("SEQ_EQUIPAMENTO"));
/*  96 */         financeiroEquipamento.setValor(rs.getBigDecimal("VALOR"));
/*  97 */         financeiroEquipamento.setSeqFinanceiro(rs.getString("SEQ_FINANCEIRO"));
/*  98 */         financeiroEquipamento.setNomeEquipamento(rs.getString("nomeEquipamento"));
/*  99 */         financeiroEquipamento.setComentario(rs.getString("comentario"));
/* 100 */         listaFinanceiroEquipamento.add(financeiroEquipamento);
/*     */       }
/*     */       
/* 103 */       ps.execute();
/* 104 */       ps.close();
/*     */       
/* 106 */       return listaFinanceiroEquipamento;
/*     */     } catch (SQLException ex) {
/* 108 */       Logger.getLogger(FinanceiroEquipamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 109 */       System.out.println(ex.getMessage()); }
/* 110 */     return null;
/*     */   }
/*     */   
/*     */   public boolean deletar(FinanceiroEquipamento financeiroEquipamento)
/*     */   {
/*     */     try {
/* 116 */       Conexao conexao = new Conexao();
/* 117 */       Connection conn = Conexao.getConnection();
/* 118 */       String sql = "DELETE FROM FINANCEIRO_EQUIPAMENTO WHERE SEQ_FINANCEIRO_EQUIPAMENTO =  ? ";
/*     */       
/* 120 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/* 122 */       ps.setString(1, financeiroEquipamento.getSeqFinanceiroEquipamento());
/*     */       
/* 124 */       ps.execute();
/* 125 */       ps.close();
/*     */       
/* 127 */       return true;
/*     */     } catch (SQLException ex) {
/* 129 */       System.out.println(ex.getMessage()); }
/* 130 */     return false;
/*     */   }
/*     */ }

