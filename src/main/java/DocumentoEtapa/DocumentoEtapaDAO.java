/*     */ package DocumentoEtapa;
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
/*     */ 
/*     */ public class DocumentoEtapaDAO
/*     */ {
/*     */   public DocumentoEtapa inserir(DocumentoEtapa documentoEtapa)
/*     */   {
/*     */     try
/*     */     {
/*  28 */       String seq = Sequence.buscarNumeroSequence("SEQ_DOCUMENTO_ETAPA");
/*  29 */       documentoEtapa.setSeqDocumentoEtapa(seq);
/*  30 */       Conexao conexao = new Conexao();
/*  31 */       Connection conn = Conexao.getConnection();
/*  32 */       String sql = "insert into DOCUMENTO_ETAPA (SEQ_DOCUMENTO_ETAPA,SEQ_TIPO_DOCUMENTO,NOME,DATA_CADASTRO,SITUACAO,SEQ_EMPRESA,ORDEM,PRINCIPAL) values  (?,?,?,?,?,?,?,?)";
/*     */       
/*     */ 
/*     */ 
/*  36 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*  38 */       ps.setString(1, documentoEtapa.getSeqDocumentoEtapa());
/*  39 */       ps.setString(2, documentoEtapa.getSeqTipoDocumento());
/*  40 */       ps.setString(3, documentoEtapa.getNome());
/*  41 */       ps.setDate(4, new java.sql.Date(documentoEtapa.getDataCadastro().getTime()));
/*  42 */       ps.setString(5, documentoEtapa.getSituacao());
/*  43 */       ps.setString(6, documentoEtapa.getSeqEmpresa());
/*  44 */       ps.setString(7, documentoEtapa.getOrdem());
/*  45 */       ps.setString(8, documentoEtapa.getPrincipal());
/*     */       
/*  47 */       ps.execute();
/*  48 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/*  51 */       Logger.getLogger(DocumentoEtapaDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  52 */       System.out.println(ex.getMessage());
/*     */     }
/*  54 */     return documentoEtapa;
/*     */   }
/*     */   
/*     */   public DocumentoEtapa alterar(DocumentoEtapa documentoEtapa) {
/*     */     try {
/*  59 */       Conexao conexao = new Conexao();
/*  60 */       Connection conn = Conexao.getConnection();
/*  61 */       String sql = "update DOCUMENTO_ETAPA set SEQ_TIPO_DOCUMENTO = ?,NOME = ?,DATA_CADASTRO = ?,SITUACAO = ?,SEQ_EMPRESA = ?,ORDEM = ?,PRINCIPAL = ? where SEQ_DOCUMENTO_ETAPA = ?";
/*     */       
/*  63 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*  65 */       ps.setString(1, documentoEtapa.getSeqTipoDocumento());
/*  66 */       ps.setString(2, documentoEtapa.getNome());
/*  67 */       ps.setDate(3, new java.sql.Date(documentoEtapa.getDataCadastro().getTime()));
/*  68 */       ps.setString(4, documentoEtapa.getSituacao());
/*  69 */       ps.setString(5, documentoEtapa.getSeqEmpresa());
/*  70 */       ps.setString(6, documentoEtapa.getOrdem());
/*  71 */       ps.setString(7, documentoEtapa.getPrincipal());
/*  72 */       ps.setString(8, documentoEtapa.getSeqDocumentoEtapa());
/*  73 */       ps.execute();
/*  74 */       ps.close();
/*     */ 
/*     */     }
/*     */     catch (SQLException ex)
/*     */     {
/*  79 */       Logger.getLogger(DocumentoEtapaDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  80 */       System.out.println(ex.getMessage());
/*     */     }
/*     */     
/*  83 */     return documentoEtapa;
/*     */   }
/*     */   
/*     */   public List<DocumentoEtapa> listar(ClausulaWhere sClausula) {
/*     */     try {
/*  88 */       Conexao conexao = new Conexao();
/*  89 */       Connection conn = Conexao.getConnection();
/*  90 */       String sql = "SELECT * FROM DOCUMENTO_ETAPA " + sClausula.montarsClausula();
/*     */       
/*     */ 
/*  93 */       List<DocumentoEtapa> listaDocumentoEtapa = new ArrayList();
/*  94 */       PreparedStatement ps = conn.prepareStatement(sql);
/*  95 */       ResultSet rs = ps.executeQuery();
/*     */       
/*  97 */       while (rs.next()) {
/*  98 */         DocumentoEtapa documentoEtapa = new DocumentoEtapa();
/*  99 */         documentoEtapa.setSeqDocumentoEtapa(rs.getString("SEQ_DOCUMENTO_ETAPA"));
/* 100 */         documentoEtapa.setSeqTipoDocumento(rs.getString("SEQ_TIPO_DOCUMENTO"));
/* 101 */         documentoEtapa.setNome(rs.getString("NOME"));
/* 102 */         documentoEtapa.setDataCadastro(rs.getDate("DATA_CADASTRO"));
/* 103 */         documentoEtapa.setSituacao(rs.getString("SITUACAO"));
/* 104 */         documentoEtapa.setSeqEmpresa(rs.getString("SEQ_EMPRESA"));
/* 105 */         documentoEtapa.setOrdem(rs.getString("ORDEM"));
/* 106 */         documentoEtapa.setPrincipal(rs.getString("PRINCIPAL"));
/* 107 */         listaDocumentoEtapa.add(documentoEtapa);
/*     */       }
/*     */       
/* 110 */       ps.execute();
/* 111 */       ps.close();
/*     */       
/* 113 */       return listaDocumentoEtapa;
/*     */     } catch (SQLException ex) {
/* 115 */       Logger.getLogger(DocumentoEtapaDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 116 */       System.out.println(ex.getMessage()); }
/* 117 */     return null;
/*     */   }
/*     */   
/*     */   public boolean deletar(DocumentoEtapa documentoEtapa)
/*     */   {
/*     */     try {
/* 123 */       Conexao conexao = new Conexao();
/* 124 */       Connection conn = Conexao.getConnection();
/* 125 */       String sql = "DELETE FROM DOCUMENTO_ETAPA WHERE SEQ_DOCUMENTO_ETAPA =  ? ";
/*     */       
/* 127 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/* 129 */       ps.setString(1, documentoEtapa.getSeqDocumentoEtapa());
/*     */       
/* 131 */       ps.execute();
/* 132 */       ps.close();
/*     */       
/* 134 */       return true;
/*     */     } catch (SQLException ex) {
/* 136 */       System.out.println(ex.getMessage()); }
/* 137 */     return false;
/*     */   }
/*     */ }
