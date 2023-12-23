/*     */ package NvPais;
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

/*     */ public class NvPaisDAO
/*     */ {
/*     */   public NvPais inserir(NvPais nvPais)
/*     */   {
/*     */     try
/*     */     {
/*  27 */       String seq = Sequence.buscarNumeroSequence("SEQ_NV_PAIS");
/*  28 */       nvPais.setSeqNvPais(seq);
/*  29 */       Conexao conexao = new Conexao();
/*  30 */       Connection conn = Conexao.getConnection();
/*  31 */       String sql = "insert into NV_PAIS (SEQ_NV_PAIS,CODIGO,NOME_PORTUGUES,NOME_INGLES,SITUACAO,DATA_CADASTRO) values  (?,?,?,?,?,?)";
/*     */       
/*     */ 
/*     */ 
/*  35 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*  37 */       ps.setString(1, nvPais.getSeqNvPais());
/*  38 */       ps.setString(2, nvPais.getCodigo());
/*  39 */       ps.setString(3, nvPais.getNomePortugues());
/*  40 */       ps.setString(4, nvPais.getNomeIngles());
/*  41 */       ps.setString(5, nvPais.getSituacao());
/*  42 */       ps.setDate(6, new java.sql.Date(nvPais.getDataCadastro().getTime()));
/*     */       
/*  44 */       ps.execute();
/*  45 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/*  48 */       Logger.getLogger(NvPaisDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  49 */       System.out.println(ex.getMessage());
/*     */     }
/*  51 */     return nvPais;
/*     */   }
/*     */   
/*     */   public NvPais alterar(NvPais nvPais) {
/*     */     try {
/*  56 */       Conexao conexao = new Conexao();
/*  57 */       Connection conn = Conexao.getConnection();
/*  58 */       String sql = "update NV_PAIS set CODIGO = ?,NOME_PORTUGUES = ?,NOME_INGLES = ?,SITUACAO = ?,DATA_CADASTRO = ? where SEQ_NV_PAIS = ?";
/*     */       
/*  60 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*  62 */       ps.setString(1, nvPais.getCodigo());
/*  63 */       ps.setString(2, nvPais.getNomePortugues());
/*  64 */       ps.setString(3, nvPais.getNomeIngles());
/*  65 */       ps.setString(4, nvPais.getSituacao());
/*  66 */       ps.setDate(5, new java.sql.Date(nvPais.getDataCadastro().getTime()));
/*  67 */       ps.setString(6, nvPais.getSeqNvPais());
/*  68 */       ps.execute();
/*  69 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/*  72 */       Logger.getLogger(NvPaisDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  73 */       System.out.println(ex.getMessage());
/*     */     }
/*     */     
/*  76 */     return nvPais;
/*     */   }
/*     */   
/*     */   public List<NvPais> listar(ClausulaWhere sClausula) {
/*     */     try {
/*  81 */       Conexao conexao = new Conexao();
/*  82 */       Connection conn = Conexao.getConnection();
/*  83 */       String sql = "SELECT * FROM NV_PAIS" + sClausula.montarsClausula();
/*  84 */       System.out.println(sql);
/*     */       
/*  86 */       List<NvPais> listaNvPais = new ArrayList();
/*  87 */       PreparedStatement ps = conn.prepareStatement(sql);
/*  88 */       ResultSet rs = ps.executeQuery();
/*     */       
/*  90 */       while (rs.next()) {
/*  91 */         NvPais nvPais = new NvPais();
/*  92 */         nvPais.setSeqNvPais(rs.getString("SEQ_NV_PAIS"));
/*  93 */         nvPais.setCodigo(rs.getString("CODIGO"));
/*  94 */         nvPais.setNomePortugues(rs.getString("NOME_PORTUGUES"));
/*  95 */         nvPais.setNomeIngles(rs.getString("NOME_INGLES"));
/*  96 */         nvPais.setSituacao(rs.getString("SITUACAO"));
/*  97 */         nvPais.setDataCadastro(rs.getDate("DATA_CADASTRO"));
/*  98 */         listaNvPais.add(nvPais);
/*     */       }
/*     */       
/* 101 */       ps.execute();
/* 102 */       ps.close();
/*     */       
/* 104 */       return listaNvPais;
/*     */     } catch (SQLException ex) {
/* 106 */       Logger.getLogger(NvPaisDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 107 */       System.out.println(ex.getMessage()); }
/* 108 */     return null;
/*     */   }
/*     */   
/*     */   public boolean deletar(NvPais nvPais)
/*     */   {
/*     */     try {
/* 114 */       Conexao conexao = new Conexao();
/* 115 */       Connection conn = Conexao.getConnection();
/* 116 */       String sql = "DELETE FROM NV_PAIS WHERE SEQ_NV_PAIS =  ? ";
/*     */       
/* 118 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/* 120 */       ps.setString(1, nvPais.getSeqNvPais());
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


/* Location:              /Users/diogo.lima/Documents/PEDIDO.jar!/NvPais/NvPaisDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */