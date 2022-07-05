/*     */ package Agenda;
/*     */ 
/*     */ import ClausulaSQL.ClausulaWhere;
/*     */ import Util.Conexao;
/*     */ import Util.Sequence;
/*     */ import java.io.PrintStream;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Timestamp;
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
/*     */ public class AgendaDAO
/*     */ {
/*     */   public Agenda inserir(Agenda agenda)
/*     */   {
/*     */     try
/*     */     {
/*  28 */       String seq = Sequence.buscarNumeroSequence("SEQ_AGENDA");
/*  29 */       agenda.setSeqAgenda(seq);
/*  30 */       Conexao conexao = new Conexao();
/*  31 */       Connection conn = Conexao.getConnection();
/*  32 */       String sql = "insert into AGENDA (SEQ_AGENDA,SEQ_USUARIO,SEQ_PARCEIRO,ASSUNTO, DESCRICAO,DT_INICIO,DT_FIM,DATA_CADASTRO,COR,seq_tipo_agenda) values  (?,?,?,?,?,?,?,?,?,?)";
/*     */       
/*     */ 
/*     */ 
/*  36 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*  38 */       ps.setObject(1, agenda.getSeqAgenda());
/*  39 */       ps.setObject(2, agenda.getSeqUsuario());
/*  40 */       ps.setObject(3, agenda.getSeqParceiro());
/*  41 */       ps.setObject(4, agenda.getAssunto());
/*  42 */       ps.setObject(5, agenda.getDescricao());
/*     */       
/*  44 */       Timestamp inicio = new Timestamp(agenda.getDtInicio().getTime());
/*  45 */       ps.setTimestamp(6, inicio);
/*     */       
/*  47 */       Timestamp fim = new Timestamp(agenda.getDtFim().getTime());
/*  48 */       ps.setTimestamp(7, fim);
/*     */       
/*  50 */       ps.setDate(8, new java.sql.Date(agenda.getDataCadastro().getTime()));
/*  51 */       ps.setObject(9, agenda.getCor());
/*  52 */       ps.setObject(10, agenda.getSeqTipoAgenda());
/*     */       
/*  54 */       ps.execute();
/*  55 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/*  58 */       Logger.getLogger(AgendaDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  59 */       System.out.println(ex.getMessage());
/*     */     }
/*  61 */     return agenda;
/*     */   }
/*     */   
/*     */   public Agenda alterar(Agenda agenda) {
/*     */     try {
/*  66 */       Conexao conexao = new Conexao();
/*  67 */       Connection conn = Conexao.getConnection();
/*  68 */       String sql = "update AGENDA set SEQ_USUARIO = ?,SEQ_PARCEIRO = ?,ASSUNTO = ?,DESCRICAO=?,DT_INICIO = ?,DT_FIM = ?, COR = ?, seq_tipo_agenda = ? where SEQ_AGENDA = ?";
/*     */       
/*  70 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*  72 */       ps.setObject(1, agenda.getSeqUsuario());
/*  73 */       ps.setObject(2, agenda.getSeqParceiro());
/*  74 */       ps.setObject(3, agenda.getAssunto());
/*  75 */       ps.setObject(4, agenda.getDescricao());
/*     */       
/*  77 */       Timestamp inicio = new Timestamp(agenda.getDtInicio().getTime());
/*  78 */       ps.setTimestamp(5, inicio);
/*     */       
/*  80 */       Timestamp fim = new Timestamp(agenda.getDtFim().getTime());
/*  81 */       ps.setTimestamp(6, fim);
/*     */       
/*  83 */       ps.setObject(7, agenda.getCor());
/*  84 */       ps.setObject(8, agenda.getSeqTipoAgenda());
/*     */       
/*  86 */       ps.setString(9, agenda.getSeqAgenda());
/*     */       
/*  88 */       ps.execute();
/*  89 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/*  92 */       Logger.getLogger(AgendaDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  93 */       System.out.println(ex.getMessage());
/*     */     }
/*     */     
/*  96 */     return agenda;
/*     */   }
/*     */   
/*     */   public List<Agenda> listar(ClausulaWhere sClausula) {
/*     */     try {
/* 101 */       Conexao conexao = new Conexao();
/* 102 */       Connection conn = Conexao.getConnection();
/* 103 */       String sql = "SELECT AGENDA.*, parceiro.nome parceiro_nome,tipo_agenda.nome tp_ag_nome, usuario.usuario usuario FROM AGENDA INNER JOIN PARCEIRO ON PARCEIRO.SEQ_PARCEIRO = AGENDA.SEQ_PARCEIRO INNER JOIN TIPO_AGENDA ON  TIPO_AGENDA.SEQ_TIPO_AGENDA = AGENDA.SEQ_TIPO_AGENDA INNER JOIN USUARIO ON USUARIO.SEQ_USUARIO=AGENDA.SEQ_USUARIO" + sClausula.montarsClausula();
/*     */       
/* 105 */       List<Agenda> listaAgenda = new ArrayList();
/* 106 */       PreparedStatement ps = conn.prepareStatement(sql);
/* 107 */       ResultSet rs = ps.executeQuery();
/*     */       
/* 109 */       while (rs.next()) {
/* 110 */         Agenda agenda = new Agenda();
/* 111 */         agenda.setSeqAgenda(rs.getString("SEQ_AGENDA"));
/* 112 */         agenda.setSeqUsuario(rs.getString("SEQ_USUARIO"));
/* 113 */         agenda.setSeqParceiro(rs.getString("SEQ_PARCEIRO"));
/* 114 */         agenda.setAssunto(rs.getString("ASSUNTO"));
/* 115 */         agenda.setDescricao(rs.getString("DESCRICAO"));
/* 116 */         agenda.setDtInicio(rs.getTimestamp("DT_INICIO"));
/* 117 */         agenda.setDtFim(rs.getTimestamp("DT_FIM"));
/* 118 */         agenda.setDataCadastro(rs.getDate("DATA_CADASTRO"));
/* 119 */         agenda.setCor(rs.getString("COR"));
/* 120 */         agenda.setSeqTipoAgenda(rs.getString("seq_tipo_agenda"));
                  agenda.setNomeParceiro(rs.getString("parceiro_nome"));
                  agenda.setSeqTipoAgendaNome(rs.getString("tp_ag_nome"));
                  agenda.setNomeUsuario(rs.getString("usuario"));
/* 121 */         
                  listaAgenda.add(agenda);
/*     */       }
/*     */       
/* 124 */       ps.execute();
/* 125 */       ps.close();
/*     */       
/* 127 */       return listaAgenda;
/*     */     } catch (SQLException ex) {
/* 129 */       Logger.getLogger(AgendaDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 130 */       System.out.println(ex.getMessage()); }
/* 131 */     return null;
/*     */   }
/*     */   
/*     */   public boolean deletar(Agenda agenda)
/*     */   {
/*     */     try {
/* 137 */       Conexao conexao = new Conexao();
/* 138 */       Connection conn = Conexao.getConnection();
/* 139 */       String sql = "DELETE FROM AGENDA WHERE SEQ_AGENDA =  ? ";
/*     */       
/* 141 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/* 143 */       ps.setString(1, agenda.getSeqAgenda());
/*     */       
/* 145 */       ps.execute();
/* 146 */       ps.close();
/*     */       
/* 148 */       return true;
/*     */     } catch (SQLException ex) {
/* 150 */       System.out.println(ex.getMessage()); }
/* 151 */     return false;
/*     */   }
/*     */ }


