/*     */ package NvManutencao;
/*     */ 
          import NvManutencao.NvManutencaoDetalhe.*;
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
 
/*     */ public class NvManutencaoDetalheDAO
/*     */ {
          public NvManutencaoDetalhe inserir(NvManutencaoDetalhe nvManutencaoDetalhe)
/*     */   {
/*     */     try
/*     */     {
/*  27 */       String seq = Sequence.buscarNumeroSequence("SEQ_NV_MANUTENCAO_DETALHE");
/*  28 */       nvManutencaoDetalhe.setSeqManutencaoDetalhe(seq);
/*  29 */       Conexao conexao = new Conexao();
/*  30 */       Connection conn = Conexao.getConnection();
/*  31 */       String sql = "insert into NV_MANUTENCAO_DETALHE (SEQ_NV_MANUTENCAO_DETALHE,SEQ_NV_MANUTENCAO,DESCRICAO,VALOR) values  (?,?,?,?)";
/*     */       
/*     */ 
/*     */ 
/*  35 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*  37 */       ps.setString(1, nvManutencaoDetalhe.getSeqManutencaoDetalhe());
/*  38 */       ps.setString(2, nvManutencaoDetalhe.getSeqManutencao());
/*  39 */       ps.setString(3, nvManutencaoDetalhe.getDescricao());
/*  40 */       ps.setBigDecimal(4, nvManutencaoDetalhe.getValor());
/*  44 */       ps.execute();
/*  45 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/*  48 */       Logger.getLogger(NvManutencaoDetalheDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  49 */       System.out.println(ex.getMessage());
/*     */     }
/*  51 */     return nvManutencaoDetalhe;
/*     */   }
/*     */   /*     */   
/*     */   public NvManutencaoDetalhe alterar(NvManutencaoDetalhe nvManutencaoDetalhe) {
/*     */     try {
/*  56 */       Conexao conexao = new Conexao();
/*  57 */       Connection conn = Conexao.getConnection();
/*  58 */       String sql = "update NV_MANUTENCAO_DETALHE set SEQ_MANUTENCAO = ?,DESCRICAO = ?,VALOR = ? where SEQ_NV_MANUTENCAO_DETALHE = ?";
/*     */       
/*  60 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*  38 */       ps.setString(2, nvManutencaoDetalhe.getSeqManutencao());
/*  39 */       ps.setString(3, nvManutencaoDetalhe.getDescricao());
/*  40 */       ps.setBigDecimal(4, nvManutencaoDetalhe.getValor());
/*  37 */       ps.setString(1, nvManutencaoDetalhe.getSeqManutencaoDetalhe());
/*  68 */       ps.execute();
/*  69 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/*  72 */       Logger.getLogger(NvManutencaoDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  73 */       System.out.println(ex.getMessage());
/*     */     }
/*     */     
/*  76 */     return nvManutencaoDetalhe;
/*     */   }
/*     */   /*     */   
/*     */   public List<NvManutencaoDetalhe> listar(ClausulaWhere sClausula) {
/*     */     try {
/*  81 */       Conexao conexao = new Conexao();
/*  82 */       Connection conn = Conexao.getConnection();
/*  83 */       String sql = "SELECT * FROM NV_MANUTENCAO_DETALHE" + sClausula.montarsClausula();
/*  84 */       System.out.println(sql);
/*     */       
/*  86 */       List<NvManutencaoDetalhe> listaNvManutencaoDetalhe = new ArrayList();
/*  87 */       PreparedStatement ps = conn.prepareStatement(sql);
/*  88 */       ResultSet rs = ps.executeQuery();
/*     */       
/*  90 */       while (rs.next()) {
/*  91 */         NvManutencaoDetalhe nvManutencaoDetalhe = new NvManutencaoDetalhe();
/*  92 */         nvManutencaoDetalhe.setSeqManutencaoDetalhe(rs.getString("SEQ_MANUTENCAO_DETALHE"));
/*  93 */         nvManutencaoDetalhe.setSeqManutencao(rs.getString("SEQ_MANUTENCAO"));
/*  94 */         nvManutencaoDetalhe.setDescricao(rs.getString("DESCRICAO"));
/*  95 */         nvManutencaoDetalhe.setValor(rs.getBigDecimal("VALOR"));
/*  98 */         listaNvManutencaoDetalhe.add(nvManutencaoDetalhe);
/*     */       }
/*     */       
/* 101 */       ps.execute();
/* 102 */       ps.close();
/*     */       
/* 104 */       return listaNvManutencaoDetalhe;
/*     */     } catch (SQLException ex) {
/* 106 */       Logger.getLogger(NvManutencaoDetalheDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 107 */       System.out.println(ex.getMessage()); }
/* 108 */     return null;
/*     */   }
/*     */   
/*     */   public boolean deletar(NvManutencaoDetalhe nvManutencaoDetalhe)
/*     */   {
/*     */     try {
/* 114 */       Conexao conexao = new Conexao();
/* 115 */       Connection conn = Conexao.getConnection();
/* 116 */       String sql = "DELETE FROM NV_MANUTENCAO_DETALHE WHERE SEQ_NV_MANUTENCAO_DETALHE =  ? ";
/*     */       
/* 118 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/* 120 */       ps.setString(1, nvManutencaoDetalhe.getSeqManutencaoDetalhe());
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
