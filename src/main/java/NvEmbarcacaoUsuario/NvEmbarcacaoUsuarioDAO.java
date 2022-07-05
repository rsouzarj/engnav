/*     */ package NvEmbarcacaoUsuario;
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

/*     */ public class NvEmbarcacaoUsuarioDAO
/*     */ {
/*     */   public NvEmbarcacaoUsuario inserir(NvEmbarcacaoUsuario nvEmbarcacaoUsuario)
/*     */   {
/*     */     try
/*     */     {
/*  27 */       String seq = Sequence.buscarNumeroSequence("SEQ_NV_EMBARCACAO_USUARIO");
/*  28 */       nvEmbarcacaoUsuario.setSeqNvEmbarcacaoUsuario(seq);
/*  29 */       Conexao conexao = new Conexao();
/*  30 */       Connection conn = Conexao.getConnection();
/*  31 */       String sql = "insert into NV_EMBARCACAO_USUARIO (SEQ_NV_EMBARCACAO_USUARIO,DATA_FIM,DATA_INICIO,SEQ_EMBARCACAO,SEQ_USUARIO) values  (?,?,?,?,?)";
/*     */       
/*     */ 
/*     */ 
/*  35 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*  37 */       ps.setString(1, nvEmbarcacaoUsuario.getSeqNvEmbarcacaoUsuario());
/*     */       try {
/*  39 */         ps.setDate(2, new java.sql.Date(nvEmbarcacaoUsuario.getDataFim().getTime()));
/*     */       } catch (NullPointerException e) {
/*  41 */         ps.setDate(2, null);
/*     */       }
/*  43 */       
/*     */       try {
/*  45 */         ps.setDate(3, new java.sql.Date(nvEmbarcacaoUsuario.getDataInicio().getTime()));
/*     */       } catch (NullPointerException e) {
/*  47 */         ps.setDate(3, null);
/*     */       }
/*  49 */       ps.setString(4, nvEmbarcacaoUsuario.getSeqEmbarcacao());
/*  50 */       ps.setString(5, nvEmbarcacaoUsuario.getSeqUsuario());
/*     */       
/*  52 */       ps.execute();
/*  53 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/*  56 */       Logger.getLogger(NvEmbarcacaoUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  57 */       System.out.println(ex.getMessage());
/*     */     }
/*  59 */     return nvEmbarcacaoUsuario;
/*     */   }

/*     */   public NvEmbarcacaoUsuario alterar(NvEmbarcacaoUsuario nvEmbarcacaoUsuario) {
/*     */     try {
/*  64 */       Conexao conexao = new Conexao();
/*  65 */       Connection conn = Conexao.getConnection();
/*  66 */       String sql = "update NV_EMBARCACAO_USUARIO set DATA_FIM = ?,DATA_INICIO = ?,SEQ_EMBARCACAO = ?,SEQ_USUARIO = ? where SEQ_NV_EMBARCACAO_USUARIO = ?";
/*     */       
/*  68 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       try
/*     */       {
/*  71 */         ps.setDate(1, new java.sql.Date(nvEmbarcacaoUsuario.getDataFim().getTime()));
/*     */       } catch (NullPointerException e) {
/*  73 */         ps.setDate(1, null);
/*     */       }
/*  75 */         try {
/*  77 */         ps.setDate(3, new java.sql.Date(nvEmbarcacaoUsuario.getDataInicio().getTime()));
/*     */       } catch (NullPointerException e) {
/*  79 */         ps.setDate(3, null);
/*     */       }
/*  81 */       ps.setString(4, nvEmbarcacaoUsuario.getSeqEmbarcacao());
/*  82 */       ps.setString(5, nvEmbarcacaoUsuario.getSeqUsuario());
/*  83 */       ps.setString(6, nvEmbarcacaoUsuario.getSeqNvEmbarcacaoUsuario());
/*  84 */       ps.execute();
/*  85 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/*  88 */       Logger.getLogger(NvEmbarcacaoUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  89 */       System.out.println(ex.getMessage());
/*     */     }
/*     */     
/*  92 */     return nvEmbarcacaoUsuario;
/*     */   }

/*     */   public List<NvEmbarcacaoUsuario> listar(ClausulaWhere sClausula) {
/*     */     try {
/*  97 */       Conexao conexao = new Conexao();
/*  98 */       Connection conn = Conexao.getConnection();
/*  99 */       String sql = "SELECT NV_EMBARCACAO_USUARIO.*, usuario.usuario  FROM NV_EMBARCACAO_USUARIO inner join usuario on usuario.seq_usuario = NV_EMBARCACAO_USUARIO.seq_usuario inner join nv_embarcacao on nv_embarcacao.seq_nv_embarcacao = NV_EMBARCACAO_USUARIO.seq_embarcacao " + sClausula.montarsClausula();

/* 112 */       List<NvEmbarcacaoUsuario> listaNvEmbarcacaoUsuario = new ArrayList();
/* 113 */       PreparedStatement ps = conn.prepareStatement(sql);
/* 114 */       ResultSet rs = ps.executeQuery();
/*     */       
/* 116 */       while (rs.next()) {
/* 117 */         NvEmbarcacaoUsuario nvEmbarcacaoUsuario = new NvEmbarcacaoUsuario();
/* 118 */         nvEmbarcacaoUsuario.setSeqNvEmbarcacaoUsuario(rs.getString("SEQ_NV_EMBARCACAO_USUARIO"));
/* 119 */         nvEmbarcacaoUsuario.setDataFim(rs.getDate("DATA_FIM"));
/* 121 */         nvEmbarcacaoUsuario.setDataInicio(rs.getDate("DATA_INICIO"));
/* 122 */         nvEmbarcacaoUsuario.setSeqEmbarcacao(rs.getString("SEQ_EMBARCACAO"));
/* 123 */         nvEmbarcacaoUsuario.setSeqUsuario(rs.getString("SEQ_USUARIO"));
/* 126 */         nvEmbarcacaoUsuario.setNvUsuarioNome(rs.getString("usuario"));
/*     */         
/* 128 */         listaNvEmbarcacaoUsuario.add(nvEmbarcacaoUsuario);
/*     */       }
/*     */       
/* 131 */       ps.execute();
/* 132 */       ps.close();
/*     */       
/* 134 */       return listaNvEmbarcacaoUsuario;
/*     */     } catch (SQLException ex) {
/* 136 */       Logger.getLogger(NvEmbarcacaoUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 137 */       System.out.println(ex.getMessage()); }
/* 138 */     return null;
/*     */   }
/*     */   
/*     */   public boolean deletar(NvEmbarcacaoUsuario nvEmbarcacaoUsuario)
/*     */   {
/*     */     try {
/* 144 */       Conexao conexao = new Conexao();
/* 145 */       Connection conn = Conexao.getConnection();
/* 146 */       String sql = "DELETE FROM NV_EMBARCACAO_USUARIO WHERE SEQ_NV_EMBARCACAO_USUARIO =  ? ";
/*     */       
/* 148 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/* 150 */       ps.setString(1, nvEmbarcacaoUsuario.getSeqNvEmbarcacaoUsuario());
/*     */       
/* 152 */       ps.execute();
/* 153 */       ps.close();
/*     */       
/* 155 */       return true;
/*     */     } catch (SQLException ex) {
/* 157 */       System.out.println(ex.getMessage()); }
/* 158 */     return false;
/*     */   }
/*     */ }
