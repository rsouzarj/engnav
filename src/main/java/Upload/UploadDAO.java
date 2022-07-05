/*     */ package Upload;
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

/*     */ public class UploadDAO
/*     */ {
/*     */   public Upload inserir(Upload upload)
/*     */   {
/*     */     try
/*     */     {
/*  27 */       String seq = Sequence.buscarNumeroSequence("SEQ_UPLOAD");
/*  28 */       upload.setSeqUpload(seq);
/*  29 */       Conexao conexao = new Conexao();
/*  30 */       Connection conn = Conexao.getConnection();
/*  31 */       String sql = "insert into UPLOAD (SEQ_UPLOAD,DATA_CADASTRO,SEQ_DOCUMENTO,ORIGEM,SEQ_USUARIO,URL,NOME_ARQUIVO,SEQ_EMPRESA,SEQ_NV_VISTORIA,TIPO_ARQUIVO,SEQ_FINANCEIRO,SEQ_NV_EMBARCACAO,DATA_EMISSAO,DATA_VALIDADE,SEQ_PARCEIRO,SEQ_CERTIFICADO) values  (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

/*  35 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*  37 */       ps.setString(1, upload.getSeqUpload());
/*     */       try {
/*  39 */         ps.setDate(2, new java.sql.Date(upload.getDataCadastro().getTime()));
/*     */       } catch (NullPointerException e) {
/*  41 */         ps.setDate(2, null);
/*     */       }
/*  43 */       ps.setString(3, upload.getSeqDocumento());
/*  44 */       ps.setString(4, upload.getOrigem());
/*  45 */       ps.setString(5, upload.getSeqUsuario());
/*  46 */       ps.setString(6, upload.getUrl());
/*  47 */       ps.setString(7, upload.getNomeArquivo());
/*  48 */       ps.setString(8, upload.getSeqEmpresa());
/*  49 */       ps.setString(9, upload.getSeqNvVistoria());
/*  50 */       ps.setString(10, upload.getTipoArquivo());
/*  50 */       ps.setString(11, upload.getSeqFinanceiro());
                ps.setString(12, upload.getSeqNvEmbarcacao());
                ps.setDate(13, new java.sql.Date(upload.getDataEmissao().getTime()));
                ps.setDate(14, new java.sql.Date(upload.getDataValidade().getTime())); 
                ps.setString(15, upload.getSeqParceiro());
                ps.setString(16, upload.getSeqCertificado());
                
/*  52 */       ps.execute();
/*  53 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/*  56 */       Logger.getLogger(UploadDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  57 */       System.out.println(ex.getMessage());
/*     */     }
/*  59 */     return upload;
/*     */   }
/*     */   
/*     */   public Upload alterar(Upload upload) {
/*     */     try {
/*  64 */       Conexao conexao = new Conexao();
/*  65 */       Connection conn = Conexao.getConnection();
/*  66 */       String sql = "update UPLOAD set DATA_CADASTRO = ?,SEQ_DOCUMENTO = ?,ORIGEM = ?,SEQ_USUARIO = ?,URL = ?,NOME_ARQUIVO = ?,SEQ_EMPRESA = ?,SEQ_NV_VISTORIA = ?, TIPO_ARQUIVO = ?, SEQ_FINANCEIRO = ? , SEQ_NV_EMBARCACAO = ? , DATA_EMISSAO = ?,DATA_VALIDADE = ?,SEQ_PARCEIRO = ?, SEQ_CERTIFICADO = ? where SEQ_UPLOAD = ?";
/*     */       
/*  68 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       try
/*     */       {
/*  71 */         ps.setDate(1, new java.sql.Date(upload.getDataCadastro().getTime()));
/*     */       } catch (NullPointerException e) {
/*  73 */         ps.setDate(1, null);
/*     */       }
/*  75 */       ps.setString(2, upload.getSeqDocumento());
/*  76 */       ps.setString(3, upload.getOrigem());
/*  77 */       ps.setString(4, upload.getSeqUsuario());
/*  78 */       ps.setString(5, upload.getUrl());
/*  79 */       ps.setString(6, upload.getNomeArquivo());
/*  80 */       ps.setString(7, upload.getSeqEmpresa());
/*  81 */       ps.setString(8, upload.getSeqNvVistoria());
/*  82 */       ps.setString(9, upload.getTipoArquivo());
/*  83 */       ps.setString(10, upload.getSeqFinanceiro()); 
                ps.setString(11 ,upload.getSeqNvEmbarcacao());
                ps.setDate(12, new java.sql.Date(upload.getDataEmissao().getTime()));
                ps.setDate(13, new java.sql.Date(upload.getDataValidade().getTime()));
                ps.setString(14 ,upload.getSeqParceiro());
                ps.setString(15 ,upload.getSeqCertificado());
                ps.setString(16, upload.getSeqUpload());
/*  84 */       ps.execute();
/*  85 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/*  88 */       Logger.getLogger(UploadDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  89 */       System.out.println(ex.getMessage());
/*     */     }
/*     */     
/*  92 */     return upload;
/*     */   }
/*     */   
/*     */   public List<Upload> listar(ClausulaWhere sClausula) {
/*     */     try {
/*  97 */       Conexao conexao = new Conexao();
/*  98 */       Connection conn = Conexao.getConnection();
/*  99 */       String sql = "SELECT UPLOAD.*, usuario.usuario nomeUsuario FROM UPLOAD inner join usuario on usuario.seq_usuario = upload.seq_usuario left join nv_embarcacao on nv_embarcacao.seq_nv_embarcacao = upload.seq_nv_embarcacao " + sClausula.montarsClausula();
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 104 */       System.out.println(sql);
/*     */       
/* 106 */       List<Upload> listaUpload = new ArrayList();
/* 107 */       PreparedStatement ps = conn.prepareStatement(sql);
/* 108 */       ResultSet rs = ps.executeQuery();
/*     */       
/* 110 */       while (rs.next()) {
/* 111 */         Upload upload = new Upload();
/* 112 */         upload.setSeqUpload(rs.getString("SEQ_UPLOAD"));
/* 113 */         upload.setDataCadastro(rs.getDate("DATA_CADASTRO"));
/* 114 */         upload.setSeqDocumento(rs.getString("SEQ_DOCUMENTO"));
/* 115 */         upload.setOrigem(rs.getString("ORIGEM"));
/* 116 */         upload.setSeqUsuario(rs.getString("SEQ_USUARIO"));
/* 117 */         upload.setUrl(rs.getString("URL"));
/* 118 */         upload.setNomeArquivo(rs.getString("NOME_ARQUIVO"));
/* 119 */         upload.setSeqEmpresa(rs.getString("SEQ_EMPRESA"));
/* 120 */         upload.setSeqNvVistoria(rs.getString("SEQ_NV_VISTORIA"));
/* 121 */         upload.setTipoArquivo(rs.getString("TIPO_ARQUIVO"));
/* 122 */         upload.setNomeUsuario(rs.getString("nomeUsuario"));
                  upload.setSeqFinanceiro(rs.getString("SEQ_FINANCEIRO"));
                  upload.setSeqParceiro(rs.getString("SEQ_PARCEIRO"));
                  upload.setSeqNvEmbarcacao(rs.getString("SEQ_NV_EMBARCACAO"));
                  upload.setSeqCertificado(rs.getString("SEQ_CERTIFICADO"));
                  upload.setDataEmissao(rs.getDate("DATA_EMISSAO"));
                  upload.setDataValidade(rs.getDate("DATA_VALIDADE"));
/* 123 */         listaUpload.add(upload);
/*     */       }
/*     */       
/* 126 */       ps.execute();
/* 127 */       ps.close();
/*     */       
/* 129 */       return listaUpload;
/*     */     } catch (SQLException ex) {
/* 131 */       Logger.getLogger(UploadDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 132 */       System.out.println(ex.getMessage()); }
/* 133 */     return null;
/*     */   }
/*     */   
/*     */   public boolean deletar(Upload upload)
/*     */   {
/*     */     try {
/* 139 */       Conexao conexao = new Conexao();
/* 140 */       Connection conn = Conexao.getConnection();
/* 141 */       String sql = "DELETE FROM UPLOAD WHERE SEQ_UPLOAD =  ? ";
/*     */       
/* 143 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/* 145 */       ps.setString(1, upload.getSeqUpload());
/*     */       
/* 147 */       ps.execute();
/* 148 */       ps.close();
/*     */       
/* 150 */       return true;
/*     */     } catch (SQLException ex) {
/* 152 */       System.out.println(ex.getMessage()); }
/* 153 */     return false;
/*     */   }
/*     */ }
