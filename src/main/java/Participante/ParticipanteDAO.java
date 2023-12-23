/*     */ package Participante;
/*     */ 
import Participante.*;
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

/*     */ public class ParticipanteDAO
/*     */ {
/*     */   public Participante inserir(Participante participante)
/*     */   {
/*     */     try
/*     */     {
/*  27 */       String seq = Sequence.buscarNumeroSequence("SEQ_PARTICIPANTE");
/*  28 */       participante.setSeqParticipante(seq);
/*  29 */       Conexao conexao = new Conexao();
/*  30 */       Connection conn = Conexao.getConnection();
/*  31 */       String sql = "insert into PARTICIPANTE_AG (SEQ_PARTICIPANTE,SEQ_AGENDA,PARTICIPANTE,DATA_CADASTRO) values  (?,?,?,?)";
/*     */       
/*     */ 
/*     */ 
/*  35 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*  37 */       ps.setString(1, participante.getSeqParticipante());
/*  38 */       ps.setString(2, participante.getSeqAgenda());
/*  39 */       ps.setString(3, participante.getParticipante());
/*  41 */       ps.setDate(4, new java.sql.Date(participante.getDataCadastro().getTime()));
/*     */       
/*  43 */       ps.execute();
/*  44 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/*  47 */       Logger.getLogger(ParticipanteDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  48 */       System.out.println(ex.getMessage());
/*     */     }
/*  50 */     return participante;
/*     */   }
/*     */   /*     */   
/*     */   public Participante alterar(Participante participante) {
/*     */     try {
/*  55 */       Conexao conexao = new Conexao();
/*  56 */       Connection conn = Conexao.getConnection();
/*  57 */       String sql = "UPDATE PARTICIPANTE_AG set SEQ_AGENDA = ?,PARTICIPANTE = ?,DATA_CADASTRO = ? where SEQ_PARTICIPANTE = ?";
/*     */       
/*  59 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*  61 */       ps.setString(1, participante.getSeqAgenda());
/*  62 */       ps.setString(2, participante.getParticipante());
/*  64 */       ps.setString(3, participante.getSeqParticipante());
/*  65 */       ps.execute();
/*  66 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/*  69 */       Logger.getLogger(ParticipanteDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  70 */       System.out.println(ex.getMessage());
/*     */     }
/*     */     
/*  73 */     return participante;
/*     */   }
/*     */   /*     */   
/*     */   public List<Participante> listar(ClausulaWhere sClausula) {
/*     */     try {
/*  78 */       Conexao conexao = new Conexao();
/*  79 */       Connection conn = Conexao.getConnection();
/*  80 */       String sql = "select * from PARTICIPANTE_AG" + sClausula.montarsClausula();

/*  89 */       System.out.println(sql);
/*     */       
/*  91 */       List<Participante> listaParticipante = new ArrayList();
/*  92 */       PreparedStatement ps = conn.prepareStatement(sql);
/*  93 */       ResultSet rs = ps.executeQuery();
/*     */       
/*  95 */       while (rs.next()) {
/*  96 */         Participante participante = new Participante();
/*  97 */         participante.setSeqParticipante(rs.getString("SEQ_PARTICIPANTE"));
/*  98 */         participante.setSeqAgenda(rs.getString("SEQ_AGENDA"));
/*  99 */         participante.setParticipante(rs.getString("PARTICIPANTE"));
/* 101 */         participante.setDataCadastro(rs.getDate("DATA_CADASTRO"));
/* 106 */         listaParticipante.add(participante);
/*     */       }
/*     */       
/* 109 */       ps.execute();
/* 110 */       ps.close();
/*     */       
/* 112 */       return listaParticipante;
/*     */     } catch (SQLException ex) {
/* 114 */       Logger.getLogger(ParticipanteDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 115 */       System.out.println(ex.getMessage()); }
/* 116 */     return null;
/*     */   }
/*     */   
/*     */   public boolean deletar(Participante participante)
/*     */   {
/*     */     try {
/* 122 */       Conexao conexao = new Conexao();
/* 123 */       Connection conn = Conexao.getConnection();
/* 124 */       String sql = "DELETE FROM PARTICIPANTE_AG WHERE SEQ_PARTICIPANTE =  ? ";
/*     */       
/* 126 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/* 128 */       ps.setString(1, participante.getSeqParticipante());
/*     */       
/* 130 */       ps.execute();
/* 131 */       ps.close();
/*     */       
/* 133 */       return true;
/*     */     } catch (SQLException ex) {
/* 135 */       System.out.println(ex.getMessage()); }
/* 136 */     return false;
/*     */   }
/*     */ }
