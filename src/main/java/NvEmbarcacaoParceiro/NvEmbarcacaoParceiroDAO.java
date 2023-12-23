/*     */ package NvEmbarcacaoParceiro;
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
/*     */ public class NvEmbarcacaoParceiroDAO
/*     */ {
/*     */   public NvEmbarcacaoParceiro inserir(NvEmbarcacaoParceiro nvEmbarcacaoParceiro)
/*     */   {
/*     */     try
/*     */     {
/*  27 */       String seq = Sequence.buscarNumeroSequence("SEQ_NV_EMBARCACAO_PARCEIRO");
/*  28 */       nvEmbarcacaoParceiro.setSeqNvEmbarcacaoParceiro(seq);
/*  29 */       Conexao conexao = new Conexao();
/*  30 */       Connection conn = Conexao.getConnection();
/*  31 */       String sql = "insert into NV_EMBARCACAO_PARCEIRO (SEQ_NV_EMBARCACAO_PARCEIRO,DATA_FIM,SEQ_NV_TIPO_VINC_PARC_EMBARCA,DATA_INICIO,SEQ_EMBARCACAO,SEQ_PARCEIRO) values  (?,?,?,?,?,?)";
/*     */       
/*     */ 
/*     */ 
/*  35 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*  37 */       ps.setString(1, nvEmbarcacaoParceiro.getSeqNvEmbarcacaoParceiro());
/*     */       try {
/*  39 */         ps.setDate(2, new java.sql.Date(nvEmbarcacaoParceiro.getDataFim().getTime()));
/*     */       } catch (NullPointerException e) {
/*  41 */         ps.setDate(2, null);
/*     */       }
/*  43 */       ps.setString(3, nvEmbarcacaoParceiro.getSeqNvTipoVincParcEmbarca());
/*     */       try {
/*  45 */         ps.setDate(4, new java.sql.Date(nvEmbarcacaoParceiro.getDataInicio().getTime()));
/*     */       } catch (NullPointerException e) {
/*  47 */         ps.setDate(4, null);
/*     */       }
/*  49 */       ps.setString(5, nvEmbarcacaoParceiro.getSeqEmbarcacao());
/*  50 */       ps.setString(6, nvEmbarcacaoParceiro.getSeqParceiro());
/*     */       
/*  52 */       ps.execute();
/*  53 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/*  56 */       Logger.getLogger(NvEmbarcacaoParceiroDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  57 */       System.out.println(ex.getMessage());
/*     */     }
/*  59 */     return nvEmbarcacaoParceiro;
/*     */   }
/*     */   
/*     */   public NvEmbarcacaoParceiro alterar(NvEmbarcacaoParceiro nvEmbarcacaoParceiro) {
/*     */     try {
/*  64 */       Conexao conexao = new Conexao();
/*  65 */       Connection conn = Conexao.getConnection();
/*  66 */       String sql = "update NV_EMBARCACAO_PARCEIRO set DATA_FIM = ?,SEQ_NV_TIPO_VINC_PARC_EMBARCA = ?,DATA_INICIO = ?,SEQ_EMBARCACAO = ?,SEQ_PARCEIRO = ? where SEQ_NV_EMBARCACAO_PARCEIRO = ?";
/*     */       
/*  68 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       try
/*     */       {
/*  71 */         ps.setDate(1, new java.sql.Date(nvEmbarcacaoParceiro.getDataFim().getTime()));
/*     */       } catch (NullPointerException e) {
/*  73 */         ps.setDate(1, null);
/*     */       }
/*  75 */       ps.setString(2, nvEmbarcacaoParceiro.getSeqNvTipoVincParcEmbarca());
/*     */       try {
/*  77 */         ps.setDate(3, new java.sql.Date(nvEmbarcacaoParceiro.getDataInicio().getTime()));
/*     */       } catch (NullPointerException e) {
/*  79 */         ps.setDate(3, null);
/*     */       }
/*  81 */       ps.setString(4, nvEmbarcacaoParceiro.getSeqEmbarcacao());
/*  82 */       ps.setString(5, nvEmbarcacaoParceiro.getSeqParceiro());
/*  83 */       ps.setString(6, nvEmbarcacaoParceiro.getSeqNvEmbarcacaoParceiro());
/*  84 */       ps.execute();
/*  85 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/*  88 */       Logger.getLogger(NvEmbarcacaoParceiroDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  89 */       System.out.println(ex.getMessage());
/*     */     }
/*     */     
/*  92 */     return nvEmbarcacaoParceiro;
/*     */   }
/*     */   
/*     */   public List<NvEmbarcacaoParceiro> listar(ClausulaWhere sClausula) {
/*     */     try {
/*  97 */       Conexao conexao = new Conexao();
/*  98 */       Connection conn = Conexao.getConnection();
/*  99 */       String sql = "SELECT NV_EMBARCACAO_PARCEIRO.*, parceiro.nome parceiro_nome, tipo_vinculo.nome tipo_vinc_nome,nv_embarcacao.nome nvEmbarcacaoNome FROM NV_EMBARCACAO_PARCEIRO inner join parceiro on parceiro.seq_parceiro = NV_EMBARCACAO_PARCEIRO.seq_parceiro inner join tipo_vinculo on tipo_vinculo.seq_TIPO_VINCulo = NV_EMBARCACAO_PARCEIRO.seq_NV_TIPO_VINC_PARC_EMBARCA inner join nv_embarcacao on nv_embarcacao.seq_nv_embarcacao = NV_EMBARCACAO_PARCEIRO.seq_embarcacao " + sClausula.montarsClausula();

/* 112 */       List<NvEmbarcacaoParceiro> listaNvEmbarcacaoParceiro = new ArrayList();
/* 113 */       PreparedStatement ps = conn.prepareStatement(sql);
/* 114 */       ResultSet rs = ps.executeQuery();
/*     */       
/* 116 */       while (rs.next()) {
/* 117 */         NvEmbarcacaoParceiro nvEmbarcacaoParceiro = new NvEmbarcacaoParceiro();
/* 118 */         nvEmbarcacaoParceiro.setSeqNvEmbarcacaoParceiro(rs.getString("SEQ_NV_EMBARCACAO_PARCEIRO"));
/* 119 */         nvEmbarcacaoParceiro.setDataFim(rs.getDate("DATA_FIM"));
/* 120 */         nvEmbarcacaoParceiro.setSeqNvTipoVincParcEmbarca(rs.getString("SEQ_NV_TIPO_VINC_PARC_EMBARCA"));
/* 121 */         nvEmbarcacaoParceiro.setDataInicio(rs.getDate("DATA_INICIO"));
/* 122 */         nvEmbarcacaoParceiro.setSeqEmbarcacao(rs.getString("SEQ_EMBARCACAO"));
/* 123 */         nvEmbarcacaoParceiro.setSeqParceiro(rs.getString("SEQ_PARCEIRO"));
/* 124 */         nvEmbarcacaoParceiro.setParceiroNome(rs.getString("parceiro_nome"));
/* 125 */         nvEmbarcacaoParceiro.setTipoVinculoNome(rs.getString("tipo_vinc_nome"));
/* 126 */         nvEmbarcacaoParceiro.setNvEmbarcacaoNome(rs.getString("nvEmbarcacaoNome"));
/*     */         
/* 128 */         listaNvEmbarcacaoParceiro.add(nvEmbarcacaoParceiro);
/*     */       }
/*     */       
/* 131 */       ps.execute();
/* 132 */       ps.close();
/*     */       
/* 134 */       return listaNvEmbarcacaoParceiro;
/*     */     } catch (SQLException ex) {
/* 136 */       Logger.getLogger(NvEmbarcacaoParceiroDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 137 */       System.out.println(ex.getMessage()); }
/* 138 */     return null;
/*     */   }


/*     */   
/*     */   public boolean deletar(NvEmbarcacaoParceiro nvEmbarcacaoParceiro)
/*     */   {
/*     */     try {
/* 144 */       Conexao conexao = new Conexao();
/* 145 */       Connection conn = Conexao.getConnection();
/* 146 */       String sql = "DELETE FROM NV_EMBARCACAO_PARCEIRO WHERE SEQ_NV_EMBARCACAO_PARCEIRO =  ? ";
/*     */       
/* 148 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/* 150 */       ps.setString(1, nvEmbarcacaoParceiro.getSeqNvEmbarcacaoParceiro());
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