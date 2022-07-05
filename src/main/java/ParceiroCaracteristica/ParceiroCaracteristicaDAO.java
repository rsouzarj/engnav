/*     */ package ParceiroCaracteristica;
/*     */ 
/*     */ import ClausulaSQL.ClausulaWhere;
/*     */ import Util.Conexao;
import Util.Sequence;
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
/*     */ 
/*     */ public class ParceiroCaracteristicaDAO
/*     */ {
/*     */   public ParceiroCaracteristica inserir(ParceiroCaracteristica parceiroCaracteristica)
/*     */   {
/*     */     try
/*     */     {
                String seq = Sequence.buscarNumeroSequence("SEQ_PARCEIRO_CARACTERISTICA");
/*  28 */       parceiroCaracteristica.setSeqParceiroCaracteristica(seq);
/*  27 */       Conexao conexao = new Conexao();
/*  28 */       Connection conn = Conexao.getConnection();
/*  29 */       String sql = "insert into PARCEIRO_CARACTERISTICA (SEQ_PARCEIRO_CARACTERISTICA, SEQ_TIPO_CARACTERISTICA,SEQ_PARCEIRO) values  (?,?,?)";
/*     */       
/*     */ 
/*     */ 
/*  33 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*  35 */       ps.setString(1, parceiroCaracteristica.getSeqParceiroCaracteristica());
/*  36 */       ps.setString(2, parceiroCaracteristica.getSeqParceiro());
                ps.setString(3, parceiroCaracteristica.getSeqTipoCaracteristica());
/*     */       
/*  38 */       ps.execute();
/*  39 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/*  42 */       Logger.getLogger(ParceiroCaracteristicaDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  43 */       System.out.println(ex.getMessage());
/*     */     }
/*  45 */     return parceiroCaracteristica;
/*     */   }
/*     */   
/*     */   public ParceiroCaracteristica alterar(ParceiroCaracteristica parceiroCaracteristica) {
/*     */     try {
/*  50 */       Conexao conexao = new Conexao();
/*  51 */       Connection conn = Conexao.getConnection();
/*  52 */       String sql = "update PARCEIRO_CARACTERISTICA set SEQ_PARCEIRO = ? where SEQ_TIPO_CARACTERISTICA = ?";
/*     */       
/*  54 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*  56 */       ps.setString(1, parceiroCaracteristica.getSeqParceiro());
/*  57 */       ps.setString(2, parceiroCaracteristica.getSeqTipoCaracteristica());
                ps.setString(3, parceiroCaracteristica.getSeqParceiroCaracteristica());
/*  58 */       ps.execute();
/*  59 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/*  62 */       Logger.getLogger(ParceiroCaracteristicaDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  63 */       System.out.println(ex.getMessage());
/*     */     }
/*     */     
/*  66 */     return parceiroCaracteristica;
/*     */   }
/*     */   
/*     */   public List<ParceiroCaracteristica> listar(ClausulaWhere sClausula) {
/*     */     try {
/*  71 */       Conexao conexao = new Conexao();
/*  72 */       Connection conn = Conexao.getConnection();
/*  73 */       String sql = "SELECT * FROM PARCEIRO_CARACTERISTICA" + sClausula.montarsClausula();
/*  74 */       System.out.println(sql);
/*     */       
/*  76 */       List<ParceiroCaracteristica> listaParceiroCaracteristica = new ArrayList();
/*  77 */       PreparedStatement ps = conn.prepareStatement(sql);
/*  78 */       ResultSet rs = ps.executeQuery();
/*     */       
/*  80 */       while (rs.next()) {
/*  81 */         ParceiroCaracteristica parceiroCaracteristica = new ParceiroCaracteristica();
                  parceiroCaracteristica.setSeqParceiroCaracteristica(rs.getString("SEQ_PARCEIRO_CARACTERISTICA"));
/*  82 */         parceiroCaracteristica.setSeqTipoCaracteristica(rs.getString("SEQ_TIPO_CARACTERISTICA"));
/*  83 */         parceiroCaracteristica.setSeqParceiro(rs.getString("SEQ_PARCEIRO"));
/*  84 */         listaParceiroCaracteristica.add(parceiroCaracteristica);
/*     */       }
/*     */       
/*  87 */       ps.execute();
/*  88 */       ps.close();
/*     */       
/*  90 */       return listaParceiroCaracteristica;
/*     */     } catch (SQLException ex) {
/*  92 */       Logger.getLogger(ParceiroCaracteristicaDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  93 */       System.out.println(ex.getMessage()); }
/*  94 */     return null;
/*     */   }
/*     */   
/*     */   public boolean deletar(String pSeqParceiro)
/*     */   {
/*  99 */     if (pSeqParceiro == null) {
/* 100 */       return true;
/*     */     }
/*     */     try
/*     */     {
/* 104 */       Conexao conexao = new Conexao();
/* 105 */       Connection conn = Conexao.getConnection();
/* 106 */       String sql = "DELETE FROM PARCEIRO_CARACTERISTICA WHERE seq_parceiro = ?";
/*     */       
/* 108 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/* 110 */       ps.setString(1, pSeqParceiro);
/*     */       
/* 112 */       ps.execute();
/* 113 */       ps.close();
/*     */       
/* 115 */       return true;
/*     */     } catch (SQLException ex) {
/* 117 */       System.out.println(ex.getMessage()); }
/* 118 */     return false;
/*     */   }
/*     */ }

