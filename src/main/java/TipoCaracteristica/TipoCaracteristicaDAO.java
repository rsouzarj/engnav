/*     */ package TipoCaracteristica;
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
/*     */ public class TipoCaracteristicaDAO
/*     */ {
/*     */   public TipoCaracteristica inserir(TipoCaracteristica tipoCaracteristica)
/*     */   {
/*     */     try
/*     */     {
/*  27 */       String seq = Sequence.buscarNumeroSequence("SEQ_TIPO_CARACTERISTICA");
/*  28 */       tipoCaracteristica.setSeqTipoCaracteristica(seq);
/*  29 */       Conexao conexao = new Conexao();
/*  30 */       Connection conn = Conexao.getConnection();
/*  31 */       String sql = "insert into TIPO_CARACTERISTICA (SEQ_TIPO_CARACTERISTICA,SEQ_EMPRESA,SITUACAO,NOME,DATA_CADASTRO) values  (?,?,?,?,?)";
/*     */       
/*     */ 
/*     */ 
/*  35 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*  37 */       ps.setString(1, tipoCaracteristica.getSeqTipoCaracteristica());
/*  38 */       ps.setString(2, tipoCaracteristica.getSeqEmpresa());
/*  39 */       ps.setString(3, tipoCaracteristica.getSituacao());
/*  40 */       ps.setString(4, tipoCaracteristica.getNome());
/*     */       try {
/*  42 */         ps.setDate(5, new java.sql.Date(tipoCaracteristica.getDataCadastro().getTime()));
/*     */       } catch (NullPointerException e) {
/*  44 */         ps.setDate(5, null);
/*     */       }
/*     */       
/*  47 */       ps.execute();
/*  48 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/*  51 */       Logger.getLogger(TipoCaracteristicaDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  52 */       System.out.println(ex.getMessage());
/*     */     }
/*  54 */     return tipoCaracteristica;
/*     */   }
/*     */   
/*     */   public TipoCaracteristica alterar(TipoCaracteristica tipoCaracteristica) {
/*     */     try {
/*  59 */       Conexao conexao = new Conexao();
/*  60 */       Connection conn = Conexao.getConnection();
/*  61 */       String sql = "update TIPO_CARACTERISTICA set SEQ_EMPRESA = ?,SITUACAO = ?,NOME = ?,DATA_CADASTRO = ? where SEQ_TIPO_CARACTERISTICA = ?";
/*     */       
/*  63 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*  65 */       ps.setString(1, tipoCaracteristica.getSeqEmpresa());
/*  66 */       ps.setString(2, tipoCaracteristica.getSituacao());
/*  67 */       ps.setString(3, tipoCaracteristica.getNome());
/*     */       try {
/*  69 */         ps.setDate(4, new java.sql.Date(tipoCaracteristica.getDataCadastro().getTime()));
/*     */       } catch (NullPointerException e) {
/*  71 */         ps.setDate(4, null);
/*     */       }
/*  73 */       ps.setString(5, tipoCaracteristica.getSeqTipoCaracteristica());
/*  74 */       ps.execute();
/*  75 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/*  78 */       Logger.getLogger(TipoCaracteristicaDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  79 */       System.out.println(ex.getMessage());
/*     */     }
/*     */     
/*  82 */     return tipoCaracteristica;
/*     */   }
/*     */   
/*     */   public List<TipoCaracteristica> listar(ClausulaWhere sClausula) {
/*     */     try {
/*  87 */       Conexao conexao = new Conexao();
/*  88 */       Connection conn = Conexao.getConnection();
/*  89 */       String sql = "SELECT * FROM TIPO_CARACTERISTICA" + sClausula.montarsClausula() + "order by tipo_caracteristica.nome";
/*  90 */       System.out.println(sql);
/*     */       
/*  92 */       List<TipoCaracteristica> listaTipoCaracteristica = new ArrayList();
/*  93 */       PreparedStatement ps = conn.prepareStatement(sql);
/*  94 */       ResultSet rs = ps.executeQuery();
/*     */       
/*  96 */       while (rs.next()) {
/*  97 */         TipoCaracteristica tipoCaracteristica = new TipoCaracteristica();
/*  98 */         tipoCaracteristica.setSeqTipoCaracteristica(rs.getString("SEQ_TIPO_CARACTERISTICA"));
/*  99 */         tipoCaracteristica.setSeqEmpresa(rs.getString("SEQ_EMPRESA"));
/* 100 */         tipoCaracteristica.setSituacao(rs.getString("SITUACAO"));
/* 101 */         tipoCaracteristica.setNome(rs.getString("NOME"));
/* 102 */         tipoCaracteristica.setDataCadastro(rs.getDate("DATA_CADASTRO"));
/* 103 */         listaTipoCaracteristica.add(tipoCaracteristica);
/*     */       }
/*     */       
/* 106 */       ps.execute();
/* 107 */       ps.close();
/*     */       
/* 109 */       return listaTipoCaracteristica;
/*     */     } catch (SQLException ex) {
/* 111 */       Logger.getLogger(TipoCaracteristicaDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 112 */       System.out.println(ex.getMessage()); }
/* 113 */     return null;
/*     */   }
/*     */   
/*     */   public boolean deletar(TipoCaracteristica tipoCaracteristica)
/*     */   {
/*     */     try {
/* 119 */       Conexao conexao = new Conexao();
/* 120 */       Connection conn = Conexao.getConnection();
/* 121 */       String sql = "DELETE FROM TIPO_CARACTERISTICA WHERE SEQ_TIPO_CARACTERISTICA =  ? ";
/*     */       
/* 123 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/* 125 */       ps.setString(1, tipoCaracteristica.getSeqTipoCaracteristica());
/*     */       
/* 127 */       ps.execute();
/* 128 */       ps.close();
/*     */       
/* 130 */       return true;
/*     */     } catch (SQLException ex) {
/* 132 */       System.out.println(ex.getMessage()); }
/* 133 */     return false;
/*     */   }
/*     */ }
