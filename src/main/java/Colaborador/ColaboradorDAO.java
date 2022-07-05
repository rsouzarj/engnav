/*     */ package Colaborador;
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
/*     */ public class ColaboradorDAO
/*     */ {
/*     */   public Colaborador inserir(Colaborador colaborador)
/*     */   {
/*     */     try
/*     */     {
/*  27 */       String seq = Sequence.buscarNumeroSequence("SEQ_COLABORADOR");
/*  28 */       colaborador.setSeqColaborador(seq);
/*  29 */       Conexao conexao = new Conexao();
/*  30 */       Connection conn = Conexao.getConnection();
/*  31 */       String sql = "insert into COLABORADOR (SEQ_COLABORADOR,SITUACAO,DATA_CADASTRO,SEQ_TIPO_COLABORADOR,NOME_ASSINATURA,DOCUMENTO,INFORMACAO,SEQ_EMPRESA,NOME,VISTORIADOR) values  (?,?,?,?,?,?,?,?,?,?)";
/*     */       
/*     */ 
/*     */ 
/*  35 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*  37 */       ps.setString(1, colaborador.getSeqColaborador());
/*  38 */       ps.setString(2, colaborador.getSituacao());
/*     */       try {
/*  40 */         ps.setDate(3, new java.sql.Date(colaborador.getDataCadastro().getTime()));
/*     */       } catch (NullPointerException e) {
/*  42 */         ps.setDate(3, null);
/*     */       }
/*  44 */       ps.setString(4, colaborador.getSeqTipoColaborador());
/*  45 */       ps.setString(5, colaborador.getNomeAssinatura());
/*  46 */       ps.setString(6, colaborador.getDocumento());
/*  47 */       ps.setString(7, colaborador.getInformacao());
/*  48 */       ps.setString(8, colaborador.getSeqEmpresa());
/*  49 */       ps.setString(9, colaborador.getNome());
                ps.setString(10, colaborador.getVistoriador());
/*     */       
/*  51 */       ps.execute();
/*  52 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/*  55 */       Logger.getLogger(ColaboradorDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  56 */       System.out.println(ex.getMessage());
/*     */     }
/*  58 */     return colaborador;
/*     */   }
/*     */   
/*     */   public Colaborador alterar(Colaborador colaborador) {
/*     */     try {
/*  63 */       Conexao conexao = new Conexao();
/*  64 */       Connection conn = Conexao.getConnection();
/*  65 */       String sql = "update COLABORADOR set SITUACAO = ?,DATA_CADASTRO = ?,SEQ_TIPO_COLABORADOR = ?,NOME_ASSINATURA = ?,DOCUMENTO = ?,INFORMACAO = ?,SEQ_EMPRESA = ?,NOME = ?,VISTORIADOR = ? where SEQ_COLABORADOR = ?";
/*     */       
/*  67 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*  69 */       ps.setString(1, colaborador.getSituacao());
/*     */       try {
/*  71 */         ps.setDate(2, new java.sql.Date(colaborador.getDataCadastro().getTime()));
/*     */       } catch (NullPointerException e) {
/*  73 */         ps.setDate(2, null);
/*     */       }
/*  75 */       ps.setString(3, colaborador.getSeqTipoColaborador());
/*  76 */       ps.setString(4, colaborador.getNomeAssinatura());
/*  77 */       ps.setString(5, colaborador.getDocumento());
/*  78 */       ps.setString(6, colaborador.getInformacao());
/*  79 */       ps.setString(7, colaborador.getSeqEmpresa());
/*  80 */       ps.setString(8, colaborador.getNome());
                ps.setString(9, colaborador.getVistoriador());
/*  81 */       ps.setString(10, colaborador.getSeqColaborador());
/*  82 */       ps.execute();
/*  83 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/*  86 */       Logger.getLogger(ColaboradorDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  87 */       System.out.println(ex.getMessage());
/*     */     }
/*     */     
/*  90 */     return colaborador;
/*     */   }
/*     */   
/*     */   public List<Colaborador> listar(ClausulaWhere sClausula) {
/*     */     try {
/*  95 */       Conexao conexao = new Conexao();
/*  96 */       Connection conn = Conexao.getConnection();
/*  97 */       String sql = "SELECT * FROM COLABORADOR" + sClausula.montarsClausula();
/*     */       
/*     */ 
/* 100 */       List<Colaborador> listaColaborador = new ArrayList();
/* 101 */       PreparedStatement ps = conn.prepareStatement(sql);
/* 102 */       ResultSet rs = ps.executeQuery();
/*     */       
/* 104 */       while (rs.next()) {
/* 105 */         Colaborador colaborador = new Colaborador();
/* 106 */         colaborador.setSeqColaborador(rs.getString("SEQ_COLABORADOR"));
/* 107 */         colaborador.setSituacao(rs.getString("SITUACAO"));
/* 108 */         colaborador.setDataCadastro(rs.getDate("DATA_CADASTRO"));
/* 109 */         colaborador.setSeqTipoColaborador(rs.getString("SEQ_TIPO_COLABORADOR"));
/* 110 */         colaborador.setNomeAssinatura(rs.getString("NOME_ASSINATURA"));
/* 111 */         colaborador.setDocumento(rs.getString("DOCUMENTO"));
/* 112 */         colaborador.setInformacao(rs.getString("INFORMACAO"));
/* 113 */         colaborador.setSeqEmpresa(rs.getString("SEQ_EMPRESA"));
/* 114 */         colaborador.setNome(rs.getString("NOME"));
/* 115 */         listaColaborador.add(colaborador);
/*     */       }
/*     */       
/* 118 */       ps.execute();
/* 119 */       ps.close();
/*     */       
/* 121 */       return listaColaborador;
/*     */     } catch (SQLException ex) {
/* 123 */       Logger.getLogger(ColaboradorDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 124 */       System.out.println(ex.getMessage()); }
/* 125 */     return null;
/*     */   }
/*     */   

/*     */   public List<Colaborador> listarv(ClausulaWhere sClausula) {
/*     */     try {
/*  95 */       Conexao conexao = new Conexao();
/*  96 */       Connection conn = Conexao.getConnection();
/*  97 */       String sql = "SELECT * FROM COLABORADOR WHERE VISTORIADOR = 'S' ";
/*     */       
/*     */ 
/* 100 */       List<Colaborador> listaColaborador = new ArrayList();
/* 101 */       PreparedStatement ps = conn.prepareStatement(sql);
/* 102 */       ResultSet rs = ps.executeQuery();
/*     */       
/* 104 */       while (rs.next()) {
/* 105 */         Colaborador colaborador = new Colaborador();
/* 106 */         colaborador.setSeqColaborador(rs.getString("SEQ_COLABORADOR"));
/* 107 */         colaborador.setSituacao(rs.getString("SITUACAO"));
/* 108 */         colaborador.setDataCadastro(rs.getDate("DATA_CADASTRO"));
/* 109 */         colaborador.setSeqTipoColaborador(rs.getString("SEQ_TIPO_COLABORADOR"));
/* 110 */         colaborador.setNomeAssinatura(rs.getString("NOME_ASSINATURA"));
/* 111 */         colaborador.setDocumento(rs.getString("DOCUMENTO"));
/* 112 */         colaborador.setInformacao(rs.getString("INFORMACAO"));
/* 113 */         colaborador.setSeqEmpresa(rs.getString("SEQ_EMPRESA"));
/* 114 */         colaborador.setNome(rs.getString("NOME"));
                  colaborador.setVistoriador(rs.getString("VISTORIADOR"));
/* 115 */         listaColaborador.add(colaborador);
/*     */       }
/*     */       
/* 118 */       ps.execute();
/* 119 */       ps.close();
/*     */       
/* 121 */       return listaColaborador;
/*     */     } catch (SQLException ex) {
/* 123 */       Logger.getLogger(ColaboradorDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 124 */       System.out.println(ex.getMessage()); }
/* 125 */     return null;
/*     */   }



/*     */   public boolean deletar(Colaborador colaborador)
/*     */   {
/*     */     try {
/* 131 */       Conexao conexao = new Conexao();
/* 132 */       Connection conn = Conexao.getConnection();
/* 133 */       String sql = "DELETE FROM COLABORADOR WHERE SEQ_COLABORADOR =  ? ";
/*     */       
/* 135 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/* 137 */       ps.setString(1, colaborador.getSeqColaborador());
/*     */       
/* 139 */       ps.execute();
/* 140 */       ps.close();
/*     */       
/* 142 */       return true;
/*     */     } catch (SQLException ex) {
/* 144 */       System.out.println(ex.getMessage()); }
/* 145 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/diogo.lima/Documents/PEDIDO.jar!/Colaborador/ColaboradorDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */