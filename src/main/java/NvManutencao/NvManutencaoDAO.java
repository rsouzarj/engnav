/*     */ package NvManutencao;
/*     */ 
          import NvManutencao.*;
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
 
/*     */ public class NvManutencaoDAO
/*     */ {
/*     */   public NvManutencao inserir(NvManutencao nvManutencao)
/*     */   {
/*     */     try
/*     */     {
/*  27 */       String seq = Sequence.buscarNumeroSequence("SEQ_NV_MANUTENCAO");
/*  28 */       nvManutencao.setSeqmanutencao(seq);
/*  29 */       Conexao conexao = new Conexao();
/*  30 */       Connection conn = Conexao.getConnection();
/*  31 */       String sql = "insert into NV_MANUTENCAO (SEQ_NV_MANUTENCAO,SEQ_EMPRESA,SEQ_NV_EMBARCACAO,SEQ_NV_EQUIPAMENTO,DETALHE,DATA,DATA_GARANTIA) values  (?,?,?,?,?,?,?)";
/*     */       
/*     */ 
/*     */ 
/*  35 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*  37 */       ps.setString(1, nvManutencao.getSeqmanutencao());
/*  38 */       ps.setString(2, nvManutencao.getSeqEmpresa());
/*  39 */       ps.setString(3, nvManutencao.getSeqNvEmbarcacao());
/*  40 */       ps.setString(4, nvManutencao.getSeqNvEquipamento());
/*  41 */       ps.setString(5, nvManutencao.getDetalhe());
/*  42 */       ps.setDate(6, new java.sql.Date(nvManutencao.getData().getTime()));
/*     */       ps.setDate(7, new java.sql.Date(nvManutencao.getDataGarantia().getTime()));
/*  44 */       ps.execute();
/*  45 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/*  48 */       Logger.getLogger(NvManutencaoDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  49 */       System.out.println(ex.getMessage());
/*     */     }
/*  51 */     return nvManutencao;
/*     */   }
/*     */   /*     */   
/*     */   public NvManutencao alterar(NvManutencao nvManutencao) {
/*     */     try {
/*  56 */       Conexao conexao = new Conexao();
/*  57 */       Connection conn = Conexao.getConnection();
/*  58 */       String sql = "update NV_MANUTENCAO set SEQ_EMPRESA = ?,SEQ_NV_EMBARCACAO = ?,SEQ_NV_EQUIPAMENTO = ?,DETALHE = ?,DATA = ?, DATA_GARANTIA = ? where SEQ_MANUTENCAO = ?";
/*     */       
/*  60 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*  62 */       ps.setString(1, nvManutencao.getSeqEmpresa());
/*  39 */       ps.setString(2, nvManutencao.getSeqNvEmbarcacao());
/*  40 */       ps.setString(3, nvManutencao.getSeqNvEquipamento());
/*  41 */       ps.setString(4, nvManutencao.getDetalhe());
/*  42 */       ps.setDate(5, new java.sql.Date(nvManutencao.getData().getTime()));
/*     */       ps.setDate(6, new java.sql.Date(nvManutencao.getDataGarantia().getTime()));
                ps.setString(7, nvManutencao.getSeqmanutencao());
/*  68 */       ps.execute();
/*  69 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/*  72 */       Logger.getLogger(NvManutencaoDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  73 */       System.out.println(ex.getMessage());
/*     */     }
/*     */     
/*  76 */     return nvManutencao;
/*     */   }
/*     */   /*     */   
/*     */   public List<NvManutencao> listar(ClausulaWhere sClausula) {
/*     */     try {
/*  81 */       Conexao conexao = new Conexao();
/*  82 */       Connection conn = Conexao.getConnection();
/*  83 */       String sql = "SELECT * FROM NV_MANUTENCAO" + sClausula.montarsClausula();
/*  84 */       System.out.println(sql);
/*     */       
/*  86 */       List<NvManutencao> listaNvManutencao = new ArrayList();
/*  87 */       PreparedStatement ps = conn.prepareStatement(sql);
/*  88 */       ResultSet rs = ps.executeQuery();
/*     */       
/*  90 */       while (rs.next()) {
/*  91 */         NvManutencao nvManutencao = new NvManutencao();
/*  92 */         nvManutencao.setSeqmanutencao(rs.getString("SEQ_MANUTENCAO"));
/*  93 */         nvManutencao.setSeqEmpresa(rs.getString("SEQ_EMPRESA"));
/*  94 */         nvManutencao.setSeqNvEmbarcacao(rs.getString("SEQ_NV_EMBARCACAO"));
/*  95 */         nvManutencao.setSeqNvEquipamento(rs.getString("SEQ_NV_EQUIPAMENTO"));
/*  96 */         nvManutencao.setDetalhe(rs.getString("DETALHE"));
/*  97 */         nvManutencao.setData(rs.getDate("DATA"));
                  nvManutencao.setDataGarantia(rs.getDate("DATA_GARANTIA"));
/*  98 */         listaNvManutencao.add(nvManutencao);
/*     */       }
/*     */       
/* 101 */       ps.execute();
/* 102 */       ps.close();
/*     */       
/* 104 */       return listaNvManutencao;
/*     */     } catch (SQLException ex) {
/* 106 */       Logger.getLogger(NvManutencaoDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 107 */       System.out.println(ex.getMessage()); }
/* 108 */     return null;
/*     */   }
/*     */   
/*     */   public boolean deletar(NvManutencao nvManutencao)
/*     */   {
/*     */     try {
/* 114 */       Conexao conexao = new Conexao();
/* 115 */       Connection conn = Conexao.getConnection();
/* 116 */       String sql = "DELETE FROM NV_MANUTENCAO WHERE SEQ_MANUTENCAO =  ? ";
/*     */       
/* 118 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/* 120 */       ps.setString(1, nvManutencao.getSeqmanutencao());
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
