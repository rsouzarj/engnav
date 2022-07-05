/*     */ package TipoVinculo;
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
/*     */ public class TipoVinculoDAO
/*     */ {
/*     */   public TipoVinculo inserir(TipoVinculo tipoVinculo)
/*     */   {
/*     */     try
/*     */     {
/*  27 */       String seq = Sequence.buscarNumeroSequence("SEQ_TIPO_VINCULO");
/*  28 */       tipoVinculo.setSeqTipoVinculo(seq);
/*  29 */       Conexao conexao = new Conexao();
/*  30 */       Connection conn = Conexao.getConnection();
/*  31 */       String sql = "insert into TIPO_VINCULO (SEQ_TIPO_VINCULO,CODIGO,NOME,SITUACAO,DATA_CADASTRO,SEQ_EMPRESA) values  (?,?,?,?,?,?)";
/*     */       
/*     */ 
/*     */ 
/*  35 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*  37 */       ps.setString(1, tipoVinculo.getSeqTipoVinculo());
/*  38 */       ps.setString(2, tipoVinculo.getCodigo());
/*  39 */       ps.setString(3, tipoVinculo.getNome());
/*  40 */       ps.setString(4, tipoVinculo.getSituacao());
/*  41 */       ps.setDate(5, new java.sql.Date(tipoVinculo.getDataCadastro().getTime()));
/*  42 */       ps.setString(6, tipoVinculo.getSeqEmpresa());
/*     */       
/*  44 */       ps.execute();
/*  45 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/*  48 */       Logger.getLogger(TipoVinculoDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  49 */       System.out.println(ex.getMessage());
/*     */     }
/*  51 */     return tipoVinculo;
/*     */   }
/*     */   
/*     */   public TipoVinculo alterar(TipoVinculo tipoVinculo) {
/*     */     try {
/*  56 */       Conexao conexao = new Conexao();
/*  57 */       Connection conn = Conexao.getConnection();
/*  58 */       String sql = "update TIPO_VINCULO set CODIGO = ?,NOME = ?,SITUACAO = ?,DATA_CADASTRO = ?,SEQ_EMPRESA = ? where SEQ_TIPO_VINCULO = ?";
/*     */       
/*  60 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*  62 */       ps.setString(1, tipoVinculo.getCodigo());
/*  63 */       ps.setString(2, tipoVinculo.getNome());
/*  64 */       ps.setString(3, tipoVinculo.getSituacao());
/*  65 */       ps.setDate(4, new java.sql.Date(tipoVinculo.getDataCadastro().getTime()));
/*  66 */       ps.setString(5, tipoVinculo.getSeqEmpresa());
/*  67 */       ps.setString(6, tipoVinculo.getSeqTipoVinculo());
/*  68 */       ps.execute();
/*  69 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/*  72 */       Logger.getLogger(TipoVinculoDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  73 */       System.out.println(ex.getMessage());
/*     */     }
/*     */     
/*  76 */     return tipoVinculo;
/*     */   }
/*     */   
/*     */   public List<TipoVinculo> listar(ClausulaWhere sClausula) {
/*     */     try {
/*  81 */       Conexao conexao = new Conexao();
/*  82 */       Connection conn = Conexao.getConnection();
/*  83 */       String sql = "SELECT * FROM TIPO_VINCULO " + sClausula.montarsClausula();
/*  84 */       System.out.println(sql);
/*     */       
/*  86 */       List<TipoVinculo> listaTipoVinculo = new ArrayList();
/*  87 */       PreparedStatement ps = conn.prepareStatement(sql);
/*  88 */       ResultSet rs = ps.executeQuery();
/*     */       
/*  90 */       while (rs.next()) {
/*  91 */         TipoVinculo tipoVinculo = new TipoVinculo();
/*  92 */         tipoVinculo.setSeqTipoVinculo(rs.getString("SEQ_TIPO_VINCULO"));
/*  93 */         tipoVinculo.setCodigo(rs.getString("CODIGO"));
/*  94 */         tipoVinculo.setNome(rs.getString("NOME"));
/*  95 */         tipoVinculo.setSituacao(rs.getString("SITUACAO"));
/*  96 */         tipoVinculo.setDataCadastro(rs.getDate("DATA_CADASTRO"));
/*  97 */         tipoVinculo.setSeqEmpresa(rs.getString("SEQ_EMPRESA"));
/*  98 */         listaTipoVinculo.add(tipoVinculo);
/*     */       }
/*     */       
/* 101 */       ps.execute();
/* 102 */       ps.close();
/*     */       
/* 104 */       return listaTipoVinculo;
/*     */     } catch (SQLException ex) {
/* 106 */       Logger.getLogger(TipoVinculoDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 107 */       System.out.println(ex.getMessage()); }
/* 108 */     return null;
/*     */   }
/*     */   
/*     */   public boolean deletar(TipoVinculo tipoVinculo)
/*     */   {
/*     */     try {
/* 114 */       Conexao conexao = new Conexao();
/* 115 */       Connection conn = Conexao.getConnection();
/* 116 */       String sql = "DELETE FROM TIPO_VINCULO WHERE SEQ_TIPO_VINCULO =  ? ";
/*     */       
/* 118 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/* 120 */       ps.setString(1, tipoVinculo.getSeqTipoVinculo());
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


/* Location:              /Users/diogo.lima/Documents/PEDIDO.jar!/TipoVinculo/TipoVinculoDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */