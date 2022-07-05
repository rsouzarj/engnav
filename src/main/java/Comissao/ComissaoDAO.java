/*     */ package Comissao;
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
/*     */ public class ComissaoDAO
/*     */ {
/*     */   public Comissao inserir(Comissao comissao)
/*     */   {
/*     */     try
/*     */     {
/*  27 */       String seq = Sequence.buscarNumeroSequence("SEQ_COMISSAO");
/*  28 */       comissao.setSeqComissao(seq);
/*  29 */       Conexao conexao = new Conexao();
/*  30 */       Connection conn = Conexao.getConnection();
/*  31 */       String sql = "insert into COMISSAO (SEQ_COMISSAO,DATA_CADASTRO,SEQ_Usuario,DESCRICAO,DATA_PREV_BAIXA,DATA_BAIXA,STATUS,OPERACAO,SEQ_EMPRESA,VALOR, disponivel_app) values  (?,?,?,?,?,?,?,?,?,?,?)";
/*     */       
/*     */ 
/*     */ 
/*  35 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*  37 */       ps.setString(1, comissao.getSeqComissao());
/*     */       try {
/*  39 */         ps.setDate(2, new java.sql.Date(comissao.getDataCadastro().getTime()));
/*     */       } catch (NullPointerException e) {
/*  41 */         ps.setDate(2, null);
/*     */       }
/*  43 */       ps.setString(3, comissao.getSeqUsuario());
/*  44 */       ps.setString(4, comissao.getDescricao());
/*     */       try {
/*  46 */         ps.setDate(5, new java.sql.Date(comissao.getDataPrevBaixa().getTime()));
/*     */       } catch (NullPointerException e) {
/*  48 */         ps.setDate(5, null);
/*     */       }
/*     */       try {
/*  51 */         ps.setDate(6, new java.sql.Date(comissao.getDataBaixa().getTime()));
/*     */       } catch (NullPointerException e) {
/*  53 */         ps.setDate(6, null);
/*     */       }
/*  55 */       ps.setString(7, comissao.getStatus());
/*  56 */       ps.setString(8, comissao.getOperacao());
/*  57 */       ps.setString(9, comissao.getSeqEmpresa());
/*  58 */       ps.setBigDecimal(10, comissao.getValor());
/*  59 */       ps.setString(11, comissao.getDisponivelApp());
/*     */       
/*  61 */       ps.execute();
/*  62 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/*  65 */       Logger.getLogger(ComissaoDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  66 */       System.out.println(ex.getMessage());
/*     */     }
/*  68 */     return comissao;
/*     */   }
/*     */   
/*     */   public Comissao alterar(Comissao comissao) {
/*     */     try {
/*  73 */       Conexao conexao = new Conexao();
/*  74 */       Connection conn = Conexao.getConnection();
/*  75 */       String sql = "update COMISSAO set DATA_CADASTRO = ?,SEQ_usuario = ?,DESCRICAO = ?,DATA_PREV_BAIXA = ?,DATA_BAIXA = ?,STATUS = ?,OPERACAO = ?,SEQ_EMPRESA = ?,VALOR = ?, disponivel_app = ? where SEQ_COMISSAO = ?";
/*     */       
/*  77 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       try
/*     */       {
/*  80 */         ps.setDate(1, new java.sql.Date(comissao.getDataCadastro().getTime()));
/*     */       } catch (NullPointerException e) {
/*  82 */         ps.setDate(2, null);
/*     */       }
/*  84 */       ps.setString(2, comissao.getSeqUsuario());
/*  85 */       ps.setString(3, comissao.getDescricao());
/*     */       try {
/*  87 */         ps.setDate(4, new java.sql.Date(comissao.getDataPrevBaixa().getTime()));
/*     */       } catch (NullPointerException e) {
/*  89 */         ps.setDate(4, null);
/*     */       }
/*     */       try {
/*  92 */         ps.setDate(5, new java.sql.Date(comissao.getDataBaixa().getTime()));
/*     */       } catch (NullPointerException e) {
/*  94 */         ps.setDate(5, null);
/*     */       }
/*  96 */       ps.setString(6, comissao.getStatus());
/*  97 */       ps.setString(7, comissao.getOperacao());
/*  98 */       ps.setString(8, comissao.getSeqEmpresa());
/*  99 */       ps.setBigDecimal(9, comissao.getValor());
/* 100 */       ps.setString(10, comissao.getDisponivelApp());
/* 101 */       ps.setString(11, comissao.getSeqComissao());
/* 102 */       ps.execute();
/* 103 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/* 106 */       Logger.getLogger(ComissaoDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 107 */       System.out.println(ex.getMessage());
/*     */     }
/*     */     
/* 110 */     return comissao;
/*     */   }
/*     */   
/*     */   public List<Comissao> listar() {
/*     */     try {
/* 115 */       Conexao conexao = new Conexao();
/* 116 */       Connection conn = Conexao.getConnection();
/* 117 */       String sql = "SELECT * FROM COMISSAO" ;
/* 118 */       System.out.println(sql);
/*     */       
/* 120 */       List<Comissao> listaComissao = new ArrayList();
/* 121 */       PreparedStatement ps = conn.prepareStatement(sql);
/* 122 */       ResultSet rs = ps.executeQuery();
/*     */       
/* 124 */       while (rs.next()) {
/* 125 */         Comissao comissao = new Comissao();
/* 126 */         comissao.setSeqComissao(rs.getString("SEQ_COMISSAO"));
/* 127 */         comissao.setDataCadastro(rs.getDate("DATA_CADASTRO"));
/* 128 */         comissao.setSeqUsuario(rs.getString("SEQ_USUARIO"));
/* 129 */         comissao.setDescricao(rs.getString("DESCRICAO"));
/* 130 */         comissao.setDataPrevBaixa(rs.getDate("DATA_PREV_BAIXA"));
/* 131 */         comissao.setDataBaixa(rs.getDate("DATA_BAIXA"));
/* 132 */         comissao.setStatus(rs.getString("STATUS"));
/* 133 */         comissao.setOperacao(rs.getString("OPERACAO"));
/* 134 */         comissao.setSeqEmpresa(rs.getString("SEQ_EMPRESA"));
/* 135 */         comissao.setValor(rs.getBigDecimal("VALOR"));
/* 136 */         comissao.setDisponivelApp(rs.getString("disponivel_app"));
/* 137 */         listaComissao.add(comissao);
/*     */       }
/*     */       
/* 140 */       ps.execute();
/* 141 */       ps.close();
/*     */       
/* 143 */       return listaComissao;
/*     */     } catch (SQLException ex) {
/* 145 */       Logger.getLogger(ComissaoDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 146 */       System.out.println(ex.getMessage()); }
/* 147 */     return null;
/*     */   }
/*     */   
/*     */   public boolean deletar(Comissao comissao)
/*     */   {
/*     */     try {
/* 153 */       Conexao conexao = new Conexao();
/* 154 */       Connection conn = Conexao.getConnection();
/* 155 */       String sql = "DELETE FROM COMISSAO WHERE SEQ_COMISSAO =  ? ";
/*     */       
/* 157 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/* 159 */       ps.setString(1, comissao.getSeqComissao());
/*     */       
/* 161 */       ps.execute();
/* 162 */       ps.close();
/*     */       
/* 164 */       return true;
/*     */     } catch (SQLException ex) {
/* 166 */       System.out.println(ex.getMessage()); }
/* 167 */     return false;
/*     */   }
/*     */ }

