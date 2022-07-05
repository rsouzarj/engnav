/*     */ package ParceiroContato;
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
/*     */ public class ParceiroContatoDAO
/*     */ {
/*     */   public ParceiroContato inserir(ParceiroContato parceiroContato)
/*     */   {
/*     */     try
/*     */     {
/*  27 */       String seq = Sequence.buscarNumeroSequence("SEQ_PARCEIRO_CONTATO");
/*  28 */       parceiroContato.setSeqParceiroContato(seq);
/*  29 */       Conexao conexao = new Conexao();
/*  30 */       Connection conn = Conexao.getConnection();
/*  31 */       String sql = "insert into PARCEIRO_CONTATO (SEQ_PARCEIRO_CONTATO,DATA_CADASTRO,SITUACAO,INFO,NOME,TELEFONE,EMAIL,SEQ_EMPRESA,SEQ_PARCEIRO,TELEFONE_2,CARGO) values  (?,?,?,?,?,?,?,?,?,?,?)";
/*     */       
/*     */ 
/*     */ 
/*  35 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*  37 */       ps.setString(1, parceiroContato.getSeqParceiroContato());
/*     */       try {
/*  39 */         ps.setDate(2, new java.sql.Date(parceiroContato.getDataCadastro().getTime()));
/*     */       } catch (NullPointerException e) {
/*  41 */         ps.setDate(2, null);
/*     */       }
/*  43 */       ps.setString(3, parceiroContato.getSituacao());
/*  44 */       ps.setString(4, parceiroContato.getInfo());
/*  45 */       ps.setString(5, parceiroContato.getNome());
/*  46 */       ps.setString(6, parceiroContato.getTelefone());
/*  47 */       ps.setString(7, parceiroContato.getEmail());
/*  48 */       ps.setString(8, parceiroContato.getSeqEmpresa());
/*  49 */       ps.setString(9, parceiroContato.getSeqParceiro());
/*  50 */       ps.setString(10, parceiroContato.getTelefone2());
/*  50 */       ps.setString(11, parceiroContato.getCargo());
/*     */       
/*  52 */       ps.execute();
/*  53 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/*  56 */       Logger.getLogger(ParceiroContatoDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  57 */       System.out.println(ex.getMessage());
/*     */     }
/*  59 */     return parceiroContato;
/*     */   }
/*     */   
/*     */   public ParceiroContato alterar(ParceiroContato parceiroContato) {
/*     */     try {
/*  64 */       Conexao conexao = new Conexao();
/*  65 */       Connection conn = Conexao.getConnection();
/*  66 */       String sql = "update PARCEIRO_CONTATO set DATA_CADASTRO = ?,SITUACAO = ?,INFO = ?,NOME = ?,TELEFONE = ?,EMAIL = ?,SEQ_EMPRESA = ?,SEQ_PARCEIRO = ?,TELEFONE_2 = ?, CARGO = ? where SEQ_PARCEIRO_CONTATO = ?";
/*     */       
/*  68 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       try
/*     */       {
/*  71 */         ps.setDate(1, new java.sql.Date(parceiroContato.getDataCadastro().getTime()));
/*     */       } catch (NullPointerException e) {
/*  73 */         ps.setDate(1, null);
/*     */       }
/*  75 */       ps.setString(2, parceiroContato.getSituacao());
/*  76 */       ps.setString(3, parceiroContato.getInfo());
/*  77 */       ps.setString(4, parceiroContato.getNome());
/*  78 */       ps.setString(5, parceiroContato.getTelefone());
/*  79 */       ps.setString(6, parceiroContato.getEmail());
/*  80 */       ps.setString(7, parceiroContato.getSeqEmpresa());
/*  81 */       ps.setString(8, parceiroContato.getSeqParceiro());
/*  82 */       ps.setString(9, parceiroContato.getTelefone2());
/*  50 */       ps.setString(10, parceiroContato.getCargo());
/*  83 */       ps.setString(11, parceiroContato.getSeqParceiroContato());
/*  84 */       ps.execute();
/*  85 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/*  88 */       Logger.getLogger(ParceiroContatoDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  89 */       System.out.println(ex.getMessage());
/*     */     }
/*     */     
/*  92 */     return parceiroContato;
/*     */   }
/*     */   
/*     */   public List<ParceiroContato> listar(ClausulaWhere sClausula) {
/*     */     try {
/*  97 */       Conexao conexao = new Conexao();
/*  98 */       Connection conn = Conexao.getConnection();
/*  99 */       String sql = "SELECT * FROM PARCEIRO_CONTATO" + sClausula.montarsClausula();
/*     */       
/*     */ 
/* 102 */       List<ParceiroContato> listaParceiroContato = new ArrayList();
/* 103 */       PreparedStatement ps = conn.prepareStatement(sql);
/* 104 */       ResultSet rs = ps.executeQuery();
/*     */       
/* 106 */       while (rs.next()) {
/* 107 */         ParceiroContato parceiroContato = new ParceiroContato();
/* 108 */         parceiroContato.setSeqParceiroContato(rs.getString("SEQ_PARCEIRO_CONTATO"));
/* 109 */         parceiroContato.setDataCadastro(rs.getDate("DATA_CADASTRO"));
/* 110 */         parceiroContato.setSituacao(rs.getString("SITUACAO"));
/* 111 */         parceiroContato.setInfo(rs.getString("INFO"));
/* 112 */         parceiroContato.setNome(rs.getString("NOME"));
/* 113 */         parceiroContato.setTelefone(rs.getString("TELEFONE"));
/* 114 */         parceiroContato.setTelefone2(rs.getString("TELEFONE_2"));
/* 115 */         parceiroContato.setEmail(rs.getString("EMAIL"));
/* 116 */         parceiroContato.setSeqEmpresa(rs.getString("SEQ_EMPRESA"));
/* 117 */         parceiroContato.setSeqParceiro(rs.getString("SEQ_PARCEIRO"));
/* 117 */         parceiroContato.setCargo(rs.getString("CARGO"));
/* 118 */         listaParceiroContato.add(parceiroContato);
/*     */       }
/*     */       
/* 121 */       ps.execute();
/* 122 */       ps.close();
/*     */       
/* 124 */       return listaParceiroContato;
/*     */     } catch (SQLException ex) {
/* 126 */       Logger.getLogger(ParceiroContatoDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 127 */       System.out.println(ex.getMessage()); }
/* 128 */     return null;
/*     */   }
/*     */   
/*     */   public boolean deletar(ParceiroContato parceiroContato)
/*     */   {
/*     */     try {
/* 134 */       Conexao conexao = new Conexao();
/* 135 */       Connection conn = Conexao.getConnection();
/* 136 */       String sql = "DELETE FROM PARCEIRO_CONTATO WHERE SEQ_PARCEIRO_CONTATO =  ? ";
/*     */       
/* 138 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/* 140 */       ps.setString(1, parceiroContato.getSeqParceiroContato());
/*     */       
/* 142 */       ps.execute();
/* 143 */       ps.close();
/*     */       
/* 145 */       return true;
/*     */     } catch (SQLException ex) {
/* 147 */       System.out.println(ex.getMessage()); }
/* 148 */     return false;
/*     */   }
/*     */ }
