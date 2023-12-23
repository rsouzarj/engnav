/*     */ package NvCertificadoDetalhe;
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

/*     */ public class NvCertificadoDetalheDAO
/*     */ {
/*     */   public NvCertificadoDetalhe inserir(NvCertificadoDetalhe nvCertificadoDetalhe)
/*     */   {
/*     */     try
/*     */     {
/*  27 */       String seq = Sequence.buscarNumeroSequence("SEQ_NV_CERTIFICADO_DETALHE");
/*  28 */       nvCertificadoDetalhe.setSeqNvCertificadoDetalhe(seq);
/*  29 */       Conexao conexao = new Conexao();
/*  30 */       Connection conn = Conexao.getConnection();
/*  31 */       String sql = "insert into NV_CERTIFICADO_DETALHE (SEQ_NV_CERTIFICADO_DETALHE,AREALIZA,NOME_VISTORIADOR,DATA_FINAL,DATA_REALIZACAO,LUGAR,SEQ_NV_CERTIFICADO,DATA_INICIAL, status, prazo, documento_vistoriador, aviso) values  (?,?,?,?,?,?,?,?,?,?,?,?)";
/*     */       
/*     */ 
/*     */ 
/*  35 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*  37 */       ps.setString(1, nvCertificadoDetalhe.getSeqNvCertificadoDetalhe());
/*  38 */       ps.setString(2, nvCertificadoDetalhe.getArealiza());
/*  39 */       ps.setString(3, nvCertificadoDetalhe.getNomeVistoriador());
/*     */       try {
/*  41 */         ps.setDate(4, new java.sql.Date(nvCertificadoDetalhe.getDataFinal().getTime()));
/*     */       } catch (NullPointerException e) {
/*  43 */         ps.setDate(4, null);
/*     */       }
/*     */       try {
/*  46 */         ps.setDate(5, new java.sql.Date(nvCertificadoDetalhe.getDataRealizacao().getTime()));
/*     */       } catch (NullPointerException e) {
/*  48 */         ps.setDate(5, null);
/*     */       }
/*  50 */       ps.setString(6, nvCertificadoDetalhe.getLugar());
/*  51 */       ps.setString(7, nvCertificadoDetalhe.getSeqNvCertificado());
/*     */       try {
/*  53 */         ps.setDate(8, new java.sql.Date(nvCertificadoDetalhe.getDataInicial().getTime()));
/*     */       } catch (NullPointerException e) {
/*  55 */         ps.setDate(8, null);
/*     */       }
/*  57 */       ps.setString(9, nvCertificadoDetalhe.getStatus());
/*     */       try {
/*  59 */         ps.setDate(10, new java.sql.Date(nvCertificadoDetalhe.getPrazo().getTime()));
/*     */       } catch (NullPointerException e) {
/*  61 */         ps.setDate(10, null);
/*     */       }
/*  63 */       ps.setString(11, nvCertificadoDetalhe.getDocumentoVistoriador());
/*     */       ps.setString(12, nvCertificadoDetalhe.getAviso());
/*  65 */       ps.execute();
/*  66 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/*  69 */       Logger.getLogger(NvCertificadoDetalheDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  70 */       System.out.println(ex.getMessage());
/*     */     }
/*  72 */     return nvCertificadoDetalhe;
/*     */   }
/*     */   
/*     */   public NvCertificadoDetalhe alterar(NvCertificadoDetalhe nvCertificadoDetalhe) {
/*     */     try {
/*  77 */       Conexao conexao = new Conexao();
/*  78 */       Connection conn = Conexao.getConnection();
/*  79 */       String sql = "update NV_CERTIFICADO_DETALHE set AREALIZA = ?,NOME_VISTORIADOR = ?,DATA_FINAL = ?,DATA_REALIZACAO = ?,LUGAR = ?,SEQ_NV_CERTIFICADO = ?,DATA_INICIAL = ?, status = ?, prazo = ?, documento_vistoriador = ?, aviso = ? where SEQ_NV_CERTIFICADO_DETALHE = ?";
/*     */       
/*  81 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*  83 */       ps.setString(1, nvCertificadoDetalhe.getArealiza());
/*  84 */       ps.setString(2, nvCertificadoDetalhe.getNomeVistoriador());
/*     */       try {
/*  86 */         ps.setDate(3, new java.sql.Date(nvCertificadoDetalhe.getDataFinal().getTime()));
/*     */       } catch (NullPointerException e) {
/*  88 */         ps.setDate(3, null);
/*     */       }
/*     */       try {
/*  91 */         ps.setDate(4, new java.sql.Date(nvCertificadoDetalhe.getDataRealizacao().getTime()));
/*     */       } catch (NullPointerException e) {
/*  93 */         ps.setDate(4, null);
/*     */       }
/*  95 */       ps.setString(5, nvCertificadoDetalhe.getLugar());
/*  96 */       ps.setString(6, nvCertificadoDetalhe.getSeqNvCertificado());
/*     */       try {
/*  98 */         ps.setDate(7, new java.sql.Date(nvCertificadoDetalhe.getDataInicial().getTime()));
/*     */       } catch (NullPointerException e) {
/* 100 */         ps.setDate(7, null);
/*     */       }
/* 102 */       ps.setString(8, nvCertificadoDetalhe.getStatus());
/*     */       try {
/* 104 */         ps.setDate(9, new java.sql.Date(nvCertificadoDetalhe.getPrazo().getTime()));
/*     */       } catch (NullPointerException e) {
/* 106 */         ps.setDate(9, null);
/*     */       }
/* 108 */       ps.setString(10, nvCertificadoDetalhe.getDocumentoVistoriador());
                ps.setString(11, nvCertificadoDetalhe.getAviso());
/*     */       
/* 110 */       ps.setString(12, nvCertificadoDetalhe.getSeqNvCertificadoDetalhe());
/* 111 */       ps.execute();
/* 112 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/* 115 */       Logger.getLogger(NvCertificadoDetalheDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 116 */       System.out.println(ex.getMessage());
/*     */     }
/*     */     
/* 119 */     return nvCertificadoDetalhe;
/*     */   }
/*     */   
/*     */   public List<NvCertificadoDetalhe> listar(ClausulaWhere sClausula) {
/*     */     try {
/* 124 */       Conexao conexao = new Conexao();
/* 125 */       Connection conn = Conexao.getConnection();
/* 126 */       String sql = "SELECT * FROM NV_CERTIFICADO_DETALHE" + sClausula.montarsClausula();
/* 127 */       System.out.println(sql);
/*     */       
/* 129 */       List<NvCertificadoDetalhe> listaNvCertificadoDetalhe = new ArrayList();
/* 130 */       PreparedStatement ps = conn.prepareStatement(sql);
/* 131 */       ResultSet rs = ps.executeQuery();
/*     */       
/* 133 */       while (rs.next()) {
/* 134 */         NvCertificadoDetalhe nvCertificadoDetalhe = new NvCertificadoDetalhe();
/* 135 */         nvCertificadoDetalhe.setSeqNvCertificadoDetalhe(rs.getString("SEQ_NV_CERTIFICADO_DETALHE"));
/* 136 */         nvCertificadoDetalhe.setArealiza(rs.getString("AREALIZA"));
/* 137 */         nvCertificadoDetalhe.setNomeVistoriador(rs.getString("NOME_VISTORIADOR"));
/* 138 */         nvCertificadoDetalhe.setDataFinal(rs.getDate("DATA_FINAL"));
/* 139 */         nvCertificadoDetalhe.setDataRealizacao(rs.getDate("DATA_REALIZACAO"));
/* 140 */         nvCertificadoDetalhe.setLugar(rs.getString("LUGAR"));
/* 141 */         nvCertificadoDetalhe.setSeqNvCertificado(rs.getString("SEQ_NV_CERTIFICADO"));
/* 142 */         nvCertificadoDetalhe.setDataInicial(rs.getDate("DATA_INICIAL"));
/* 143 */         nvCertificadoDetalhe.setStatus(rs.getString("STATUS"));
/* 144 */         nvCertificadoDetalhe.setPrazo(rs.getDate("PRAZO"));
/* 145 */         nvCertificadoDetalhe.setDocumentoVistoriador(rs.getString("documento_vistoriador"));
                  nvCertificadoDetalhe.setAviso(rs.getString("aviso"));
/* 146 */         listaNvCertificadoDetalhe.add(nvCertificadoDetalhe);
/*     */       }
/*     */       
/* 149 */       ps.execute();
/* 150 */       ps.close();
/*     */       
/* 152 */       return listaNvCertificadoDetalhe;
/*     */     } catch (SQLException ex) {
/* 154 */       Logger.getLogger(NvCertificadoDetalheDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 155 */       System.out.println(ex.getMessage()); }
/* 156 */     return null;
/*     */   }

/*     */   public List<NvCertificadoDetalhe> listarE(ClausulaWhere sClausula) {
/*     */     try {
/* 124 */       Conexao conexao = new Conexao();
/* 125 */       Connection conn = Conexao.getConnection();
/* 126 */       String sql = "SELECT * FROM VW_BI_LISTA_CONVALIDACAO" + sClausula.montarsClausula() + "ORDER BY VW_BI_LISTA_CONVALIDACAO.DATA_INICIAL";
/* 127 */       System.out.println(sql);
/*     */       
/* 129 */       List<NvCertificadoDetalhe> listaNvCertificadoDetalhe = new ArrayList();
/* 130 */       PreparedStatement ps = conn.prepareStatement(sql);
/* 131 */       ResultSet rs = ps.executeQuery();
/*     */       
/* 133 */       while (rs.next()) {
/* 134 */         NvCertificadoDetalhe nvCertificadoDetalhe = new NvCertificadoDetalhe();

/* 136 */         nvCertificadoDetalhe.setArealiza(rs.getString("AREALIZA"));
/* 137 */         nvCertificadoDetalhe.setNomeVistoriador(rs.getString("NOME_VISTORIADOR"));
/* 138 */         nvCertificadoDetalhe.setDataFinal(rs.getDate("DATA_FINAL"));
/* 139 */         nvCertificadoDetalhe.setDataRealizacao(rs.getDate("DATA_REALIZACAO"));
/* 140 */         nvCertificadoDetalhe.setLugar(rs.getString("LUGAR"));
/* 141 */         nvCertificadoDetalhe.setSeqNvCertificado(rs.getString("SEQ_NV_CERTIFICADO"));
/* 142 */         nvCertificadoDetalhe.setDataInicial(rs.getDate("DATA_INICIAL"));
/* 143 */         nvCertificadoDetalhe.setStatus(rs.getString("STATUS"));
/* 144 */         nvCertificadoDetalhe.setPrazo(rs.getDate("PRAZO"));
                  nvCertificadoDetalhe.setSeqNvEmbarcacao(rs.getString("SEQ_NV_EMBARCACAO"));
                  nvCertificadoDetalhe.setAviso(rs.getString("AVISO"));
/* 146 */         listaNvCertificadoDetalhe.add(nvCertificadoDetalhe);

/*     */       }
/*     */       
/* 149 */       ps.execute();
/* 150 */       ps.close();
/*     */       
/* 152 */       return listaNvCertificadoDetalhe;
/*     */     } catch (SQLException ex) {
/* 154 */       Logger.getLogger(NvCertificadoDetalheDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 155 */       System.out.println(ex.getMessage()); }
/* 156 */     return null;
/*     */   }


/*     */   
/*     */   public boolean deletar(NvCertificadoDetalhe nvCertificadoDetalhe)
/*     */   {
/*     */     try {
/* 162 */       Conexao conexao = new Conexao();
/* 163 */       Connection conn = Conexao.getConnection();
/* 164 */       String sql = "DELETE FROM NV_CERTIFICADO_DETALHE WHERE SEQ_NV_CERTIFICADO_DETALHE =  ? ";
/*     */       
/* 166 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/* 168 */       ps.setString(1, nvCertificadoDetalhe.getSeqNvCertificadoDetalhe());
/*     */       
/* 170 */       ps.execute();
/* 171 */       ps.close();
/*     */       
/* 173 */       return true;
/*     */     } catch (SQLException ex) {
/* 175 */       System.out.println(ex.getMessage()); }
/* 176 */     return false;
/*     */   }


/*     */ }


