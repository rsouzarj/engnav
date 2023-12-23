/*     */ package GrupoEmpresarial;


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

/*     */ public class GrupoEmpresarialDAO
/*     */ {
/*     */   public GrupoEmpresarial inserir(GrupoEmpresarial grupoEmpresarial)
/*     */   {
/*     */     try
/*     */     {
/*  27 */       String seq = Sequence.buscarNumeroSequence("SEQ_GRUPO");
/*  28 */       grupoEmpresarial.setSeqGrupo(seq);
/*  29 */       Conexao conexao = new Conexao();
/*  30 */       Connection conn = Conexao.getConnection();
/*  31 */       String sql = "insert into GRUPO_EMPRESARIAL (SEQ_GRUPO,INFO,NOME,SITUACAO,DATA_CADASTRO,SEQ_EMPRESA) values  (?,?,?,?,?,?)";
 
/*  35 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*  37 */       ps.setString(1, grupoEmpresarial.getSeqGrupo());
/*  38 */       ps.setString(2, grupoEmpresarial.getInfo());
/*  39 */       ps.setString(3, grupoEmpresarial.getNome());
/*  40 */       ps.setString(4, grupoEmpresarial.getSituacao());
/*  41 */       ps.setDate(5, new java.sql.Date(grupoEmpresarial.getDataCadastro().getTime()));
/*  42 */       ps.setString(6, grupoEmpresarial.getSeqEmpresa());
/*     */       
/*  44 */       ps.execute();
/*  45 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/*  48 */       Logger.getLogger(GrupoEmpresarialDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  49 */       System.out.println(ex.getMessage());
/*     */     }
/*  51 */     return grupoEmpresarial;
/*     */   }

/*     */   public GrupoEmpresarial alterar(GrupoEmpresarial grupoEmpresarial) {
/*     */     try {
/*  56 */       Conexao conexao = new Conexao();
/*  57 */       Connection conn = Conexao.getConnection();
/*  58 */       String sql = "update GRUPO_EMPRESARIAL set INFO = ?,NOME = ?,SITUACAO = ?,DATA_CADASTRO = ?,SEQ_EMPRESA = ? where SEQ_GRUPO = ?";
/*     */       
/*  60 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*  62 */       ps.setString(1, grupoEmpresarial.getInfo());
/*  63 */       ps.setString(2, grupoEmpresarial.getNome());
/*  64 */       ps.setString(3, grupoEmpresarial.getSituacao());
/*  65 */       ps.setDate(4, new java.sql.Date(grupoEmpresarial.getDataCadastro().getTime()));
/*  66 */       ps.setString(5, grupoEmpresarial.getSeqEmpresa());
/*  67 */       ps.setString(6, grupoEmpresarial.getSeqGrupo());
/*  68 */       ps.execute();
/*  69 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/*  72 */       Logger.getLogger(GrupoEmpresarialDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  73 */       System.out.println(ex.getMessage());
/*     */     }
/*     */     
/*  76 */     return grupoEmpresarial;
/*     */   }

/*     */   public List<GrupoEmpresarial> listar(ClausulaWhere sClausula) {
/*     */     try {
/*  81 */       Conexao conexao = new Conexao();
/*  82 */       Connection conn = Conexao.getConnection();
/*  83 */       String sql = "SELECT * FROM GRUPO_EMPRESARIAL " + sClausula.montarsClausula();
/*  84 */       System.out.println(sql);
/*     */       
/*  86 */       List<GrupoEmpresarial> listaGrupoEmpresarial = new ArrayList();
/*  87 */       PreparedStatement ps = conn.prepareStatement(sql);
/*  88 */       ResultSet rs = ps.executeQuery();
/*     */       
/*  90 */       while (rs.next()) {
/*  91 */         GrupoEmpresarial grupoEmpresarial = new GrupoEmpresarial();
/*  92 */         grupoEmpresarial.setSeqGrupo(rs.getString("SEQ_GRUPO"));
/*  93 */         grupoEmpresarial.setInfo(rs.getString("INFO"));
/*  94 */         grupoEmpresarial.setNome(rs.getString("NOME"));
/*  95 */         grupoEmpresarial.setSituacao(rs.getString("SITUACAO"));
/*  96 */         grupoEmpresarial.setDataCadastro(rs.getDate("DATA_CADASTRO"));
/*  97 */         grupoEmpresarial.setSeqEmpresa(rs.getString("SEQ_EMPRESA"));
/*  98 */         listaGrupoEmpresarial.add(grupoEmpresarial);
/*     */       }
/*     */       
/* 101 */       ps.execute();
/* 102 */       ps.close();
/*     */       
/* 104 */       return listaGrupoEmpresarial;
/*     */     } catch (SQLException ex) {
/* 106 */       Logger.getLogger(GrupoEmpresarialDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 107 */       System.out.println(ex.getMessage()); }
/* 108 */     return null;
/*     */   }
/*     */   
/*     */   public boolean deletar(GrupoEmpresarial grupoEmpresarial)
/*     */   {
/*     */     try {
/* 114 */       Conexao conexao = new Conexao();
/* 115 */       Connection conn = Conexao.getConnection();
/* 116 */       String sql = "DELETE FROM GRUPO_EMPRESARIAL WHERE SEQ_GRUPO =  ? ";
/*     */       
/* 118 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/* 120 */       ps.setString(1, grupoEmpresarial.getSeqGrupo());
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
