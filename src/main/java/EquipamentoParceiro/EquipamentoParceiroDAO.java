/*     */ package EquipamentoParceiro;
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

/*     */ public class EquipamentoParceiroDAO
/*     */ {
/*     */   public EquipamentoParceiro inserir(EquipamentoParceiro equipamentoParceiro)
/*     */   {
/*     */     try
/*     */     {
/*  27 */       String seq = Sequence.buscarNumeroSequence("SEQ_EQUIPAMENTO_PARCEIRO");
/*  28 */       equipamentoParceiro.setSeqEquipamentoParceiro(seq);
/*  29 */       Conexao conexao = new Conexao();
/*  30 */       Connection conn = Conexao.getConnection();
/*  31 */       String sql = "insert into EQUIPAMENTO_PARCEIRO (SEQ_EQUIPAMENTO_PARCEIRO,SEQ_PARCEIRO,SEQ_EQUIPAMENTO) values  (?,?,?)";
/*     */       
/*     */ 
/*     */ 
/*  35 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*  37 */       ps.setString(1, equipamentoParceiro.getSeqEquipamentoParceiro());
/*  38 */       ps.setString(2, equipamentoParceiro.getSeqParceiro());
/*  39 */       ps.setString(3, equipamentoParceiro.getSeqEquipamento());
/*     */       
/*  41 */       ps.execute();
/*  42 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/*  45 */       Logger.getLogger(EquipamentoParceiroDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  46 */       System.out.println(ex.getMessage());
/*     */     }
/*  48 */     return equipamentoParceiro;
/*     */   }
/*     */   
/*     */   public EquipamentoParceiro alterar(EquipamentoParceiro equipamentoParceiro) {
/*     */     try {
/*  53 */       Conexao conexao = new Conexao();
/*  54 */       Connection conn = Conexao.getConnection();
/*  55 */       String sql = "update EQUIPAMENTO_PARCEIRO set SEQ_PARCEIRO = ?,SEQ_EQUIPAMENTO = ? where SEQ_EQUIPAMENTO_PARCEIRO = ?";
/*     */       
/*  57 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*  59 */       ps.setString(1, equipamentoParceiro.getSeqParceiro());
/*  60 */       ps.setString(2, equipamentoParceiro.getSeqEquipamento());
/*  61 */       ps.setString(3, equipamentoParceiro.getSeqEquipamentoParceiro());
/*  62 */       ps.execute();
/*  63 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/*  66 */       Logger.getLogger(EquipamentoParceiroDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  67 */       System.out.println(ex.getMessage());
/*     */     }
/*     */     
/*  70 */     return equipamentoParceiro;
/*     */   }
/*     */   
/*     */   public List<EquipamentoParceiro> listar(ClausulaWhere sClausula) {
/*     */     try {
/*  75 */       Conexao conexao = new Conexao();
/*  76 */       Connection conn = Conexao.getConnection();
/*  77 */       String sql = "SELECT EQUIPAMENTO.*, equipamento_parceiro.seq_equipamento_parceiro, equipamento_parceiro.seq_parceiro ,parceiro.nome nomeParceiro, equipamento.nome nomeEquipamento, equipamento.nserie, equipamento.capacidade_max , equipamento.modelo, equipamento.fmas FROM EQUIPAMENTO inner join equipamento_parceiro on equipamento_parceiro.seq_equipamento = equipamento.seq_equipamento inner join parceiro on parceiro.seq_parceiro = EQUIPAMENTO_PARCEIRO.seq_parceiro " + sClausula.montarsClausula();

/*  85 */       System.out.println(sql);
/*     */       
/*  87 */       List<EquipamentoParceiro> listaEquipamentoParceiro = new ArrayList();
/*  88 */       PreparedStatement ps = conn.prepareStatement(sql);
/*  89 */       ResultSet rs = ps.executeQuery();
/*     */       
/*  91 */       while (rs.next()) {
/*  92 */         EquipamentoParceiro equipamentoParceiro = new EquipamentoParceiro();
/*  93 */         equipamentoParceiro.setSeqEquipamentoParceiro(rs.getString("SEQ_EQUIPAMENTO_PARCEIRO"));
/*  94 */         equipamentoParceiro.setSeqParceiro(rs.getString("SEQ_PARCEIRO"));
/*  95 */         equipamentoParceiro.setSeqEquipamento(rs.getString("SEQ_EQUIPAMENTO"));
/*  96 */         equipamentoParceiro.setParceiroNome(rs.getString("nomeParceiro"));
/*  97 */         equipamentoParceiro.setEquipamentoNome(rs.getString("nomeEquipamento"));
                  equipamentoParceiro.setCapacidadeMax(rs.getString("capacidade_max"));
                  equipamentoParceiro.setModelo(rs.getString("MODELO"));
/*  97 */         equipamentoParceiro.setNserie(rs.getString("NSERIE"));
                  equipamentoParceiro.setFmas(rs.getString("FMAS"));

/*  98 */         listaEquipamentoParceiro.add(equipamentoParceiro);
/*     */       }
/*     */       
/* 101 */       ps.execute();
/* 102 */       ps.close();
/*     */       
/* 104 */       return listaEquipamentoParceiro;
/*     */     } catch (SQLException ex) {
/* 106 */       Logger.getLogger(EquipamentoParceiroDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 107 */       System.out.println(ex.getMessage()); }
/* 108 */     return null;
/*     */   }
/*     */   
/*     */   public boolean deletar(EquipamentoParceiro equipamentoParceiro)
/*     */   {
/*     */     try {
/* 114 */       Conexao conexao = new Conexao();
/* 115 */       Connection conn = Conexao.getConnection();
/* 116 */       String sql = "DELETE FROM EQUIPAMENTO_PARCEIRO WHERE SEQ_EQUIPAMENTO_PARCEIRO =  ? ";
/*     */       
/* 118 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/* 120 */       ps.setString(1, equipamentoParceiro.getSeqEquipamentoParceiro());
/*     */       
/* 122 */       ps.execute();
/* 123 */       ps.close();
/*     */       
/* 125 */       return true;
/*     */     } catch (SQLException ex) {
/* 127 */       System.out.println(ex.getMessage()); }
/* 128 */     return false;
/*     */   }
/*     */ }

