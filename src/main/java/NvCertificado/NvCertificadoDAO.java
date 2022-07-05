/*     */ package NvCertificado;
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

/*     */ public class NvCertificadoDAO
/*     */ {
/*     */   public NvCertificado inserir(NvCertificado nvCertificado)
/*     */   {
/*     */     try
/*     */     {
/*  27 */       String seq = Sequence.buscarNumeroSequence("SEQ_NV_CERTIFICADO");
/*  28 */       nvCertificado.setSeqNvCertificado(seq);
/*  29 */       Conexao conexao = new Conexao();
/*  30 */       Connection conn = Conexao.getConnection();
/*  31 */       String sql = "insert into NV_CERTIFICADO(SEQ_NV_CERTIFICADO,IDENTIFICACAO,SEQ_EMPRESA,DATA_CADASTRO,SITUACAO,SEQ_NV_TIPO_CERTIFICADO,SEQ_NV_EMBARCACAO,DATA_EMISSAO,DATA_VALIDADE,local_emissao, seq_colaborador, observacao, status,seq_nv_vistoria, prazo, prazo_total, assinatura_digital,filial,seq_unidade_negocio) values  (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  38 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*  40 */       ps.setString(1, nvCertificado.getSeqNvCertificado());
/*  41 */       ps.setString(2, nvCertificado.getIdentificacao());
/*  42 */       ps.setString(3, nvCertificado.getSeqEmpresa());
/*     */       try {
/*  44 */         ps.setDate(4, new java.sql.Date(nvCertificado.getDataCadastro().getTime()));
/*     */       } catch (NullPointerException ex) {
/*  46 */         ps.setDate(4, null);
/*     */       }
/*  48 */       ps.setString(5, nvCertificado.getSituacao());
/*  49 */       ps.setString(6, nvCertificado.getSeqNvTipoCertificado());
/*  50 */       ps.setString(7, nvCertificado.getSeqNvEmbarcacao());
/*     */       try {
/*  52 */         ps.setDate(8, new java.sql.Date(nvCertificado.getDataEmissao().getTime()));
/*     */       } catch (NullPointerException ex) {
/*  54 */         ps.setDate(8, null);
/*     */       }
/*     */       try
/*     */       {
/*  58 */         ps.setDate(9, new java.sql.Date(nvCertificado.getDataValidade().getTime()));
/*     */       } catch (NullPointerException ex) {
/*  60 */         ps.setDate(9, null);
/*     */       }
/*  62 */       ps.setString(10, nvCertificado.getLocalEmissao());
/*  63 */       ps.setString(11, nvCertificado.getSeqColaborador());
/*  64 */       ps.setString(12, nvCertificado.getObservacao());
/*  65 */       ps.setString(13, nvCertificado.getStatus());
/*  66 */       ps.setString(14, nvCertificado.getSeqNvVistoria());
/*  67 */       ps.setShort(15, nvCertificado.getPrazo());
/*  68 */       ps.setShort(16, nvCertificado.getPrazoTotal());
/*  69 */       ps.setString(17, nvCertificado.getAssinaturaDigital());
/*  70 */       ps.setString(18, nvCertificado.getFilial());
/*  70 */       ps.setString(19, nvCertificado.getSeqUnidadeNegocio());
/*     */       
/*  72 */       ps.execute();
/*  73 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/*  76 */       Logger.getLogger(NvCertificadoDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  77 */       System.out.println(ex.getMessage());
/*     */     }
/*  79 */     return nvCertificado;
/*     */   }
/*     */   
/*     */   public NvCertificado alterar(NvCertificado nvCertificado)
/*     */   {
/*     */     try {
/*  85 */       Conexao conexao = new Conexao();
/*  86 */       Connection conn = Conexao.getConnection();
/*  87 */       String sql = "update NV_CERTIFICADO set IDENTIFICACAO = ?,SEQ_EMPRESA = ?,DATA_CADASTRO = ?,SITUACAO = ?,SEQ_NV_TIPO_CERTIFICADO = ?,SEQ_NV_EMBARCACAO = ?,DATA_EMISSAO = ?,DATA_VALIDADE = ?, local_emissao = ?, seq_colaborador = ?, observacao = ?, status = ?, prazo = ?, prazo_total = ?, ASSINATURA_DIGITAL = ?, filial = ?, SEQ_UNIDADE_NEGOCIO = ?, MARABERTO = ?, NAVINT = ?, AREA1 = ?, AREA2 = ? where SEQ_NV_CERTIFICADO = ?";

/*  93 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*  95 */       ps.setString(1, nvCertificado.getIdentificacao());
/*  96 */       ps.setString(2, nvCertificado.getSeqEmpresa());
/*     */       try {
/*  98 */         ps.setDate(3, new java.sql.Date(nvCertificado.getDataCadastro().getTime()));
/*     */       } catch (NullPointerException ex) {
/* 100 */         ps.setDate(3, null);
/*     */       }
/*     */       
/* 103 */       ps.setString(4, nvCertificado.getSituacao());
/* 104 */       ps.setString(5, nvCertificado.getSeqNvTipoCertificado());
/* 105 */       ps.setString(6, nvCertificado.getSeqNvEmbarcacao());
/*     */       try {
/* 107 */         ps.setDate(7, new java.sql.Date(nvCertificado.getDataEmissao().getTime()));
/*     */       } catch (NullPointerException ex) {
/* 109 */         ps.setDate(7, null);
/*     */       }
/*     */       try
/*     */       {
/* 113 */         ps.setDate(8, new java.sql.Date(nvCertificado.getDataValidade().getTime()));
/*     */       } catch (NullPointerException ex) {
/* 115 */         ps.setDate(8, null);
/*     */       }
/*     */       
/* 118 */       ps.setString(9, nvCertificado.getLocalEmissao());
/* 119 */       ps.setString(10, nvCertificado.getSeqColaborador());
/* 120 */       ps.setString(11, nvCertificado.getObservacao());
/* 121 */       ps.setString(12, nvCertificado.getStatus());
/* 122 */       ps.setShort(13, nvCertificado.getPrazo());
/* 123 */       ps.setShort(14, nvCertificado.getPrazoTotal());
/* 124 */       ps.setString(15, nvCertificado.getAssinaturaDigital());
/* 125 */       ps.setString(16, nvCertificado.getFilial());
/* 126 */       ps.setString(17, nvCertificado.getSeqUnidadeNegocio());
                ps.setString(18, nvCertificado.getMaraberto());
                ps.setString(19, nvCertificado.getNavint());
                ps.setString(20, nvCertificado.getArea1());
                ps.setString(21, nvCertificado.getArea2());
                ps.setString(22, nvCertificado.getSeqNvCertificado());
/* 127 */       ps.execute();
/* 128 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/* 131 */       Logger.getLogger(NvCertificadoDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 132 */       System.out.println(ex.getMessage());
/*     */     }
/*     */     
/* 135 */     return nvCertificado;
/*     */   }
/*     */   
/*     */   public List<NvCertificado> listar(ClausulaWhere sClausula) {
/*     */     try {
/* 140 */       Conexao conexao = new Conexao();
/* 141 */       Connection conn = Conexao.getConnection();
/* 142 */       String sql = "SELECT NV_CERTIFICADO.*,nv_embarcacao.nome embarcacao_nome,nv_tipo_certificado.nome tipo_certificado,nv_vistoria.identificacao idt_vistoria, unidade_negocio.identificacao nome_unidade FROM NV_CERTIFICADO\ninner join nv_embarcacao on nv_embarcacao.seq_nv_embarcacao = NV_CERTIFICADO.seq_nv_embarcacao inner join nv_tipo_certificado on nv_tipo_certificado.seq_nv_tipo_certificado = NV_CERTIFICADO.seq_nv_tipo_certificado\ninner join nv_vistoria on nv_vistoria.seq_nv_vistoria = nv_certificado.seq_nv_vistoria\nleft join unidade_negocio on unidade_negocio.seq_unidade_negocio = nv_certificado.seq_unidade_negocio " + sClausula.montarsClausula();
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 147 */       System.out.println(sql);
/*     */       
/* 149 */       List<NvCertificado> listaNvCertificado = new ArrayList();
/* 150 */       PreparedStatement ps = conn.prepareStatement(sql);
/* 151 */       ResultSet rs = ps.executeQuery();
/*     */       
/* 153 */       while (rs.next()) {
/* 154 */         NvCertificado nvCertificado = new NvCertificado();
/* 155 */         nvCertificado.setSeqNvCertificado(rs.getString("SEQ_NV_CERTIFICADO"));
/* 156 */         nvCertificado.setIdentificacao(rs.getString("IDENTIFICACAO"));
/* 157 */         nvCertificado.setSeqEmpresa(rs.getString("SEQ_EMPRESA"));
/* 158 */         nvCertificado.setDataCadastro(rs.getDate("DATA_CADASTRO"));
/* 159 */         nvCertificado.setSituacao(rs.getString("SITUACAO"));
/* 160 */         nvCertificado.setSeqNvTipoCertificado(rs.getString("SEQ_NV_TIPO_CERTIFICADO"));
/* 161 */         nvCertificado.setSeqNvEmbarcacao(rs.getString("SEQ_NV_EMBARCACAO"));
/* 162 */         nvCertificado.setDataEmissao(rs.getDate("DATA_EMISSAO"));
/* 163 */         nvCertificado.setDataValidade(rs.getDate("DATA_VALIDADE"));
/* 164 */         nvCertificado.setEmbarcacaoNome(rs.getString("embarcacao_nome"));
/* 165 */         nvCertificado.setLocalEmissao(rs.getString("local_emissao"));
/* 166 */         nvCertificado.setSeqColaborador(rs.getString("seq_colaborador"));
/* 167 */         nvCertificado.setObservacao(rs.getString("observacao"));
/* 168 */         nvCertificado.setStatus(rs.getString("status"));
/* 169 */         nvCertificado.setSeqNvVistoria(rs.getString("seq_nv_vistoria"));
/* 170 */         nvCertificado.setTipoCertificado(rs.getString("tipo_certificado"));
/* 171 */         nvCertificado.setFilial(rs.getString("filial"));
/* 172 */         nvCertificado.setPrazo(rs.getShort("prazo"));
/* 173 */         nvCertificado.setPrazoTotal(rs.getShort("prazo_total"));
                  nvCertificado.setIdentVistoria(rs.getString("idt_vistoria"));
/*     */         nvCertificado.setAssinaturaDigital(rs.getString("ASSINATURA_DIGITAL"));
                  nvCertificado.setSeqUnidadeNegocio(rs.getString("SEQ_UNIDADE_NEGOCIO"));
                  nvCertificado.setunidadenegocioNome(rs.getString("nome_unidade"));
                  nvCertificado.setMaraberto(rs.getString("MARABERTO"));
                  nvCertificado.setNavint(rs.getString("NAVINT"));
                  nvCertificado.setArea1(rs.getString("AREA1"));
                  nvCertificado.setArea2(rs.getString("AREA2"));
/* 176 */         listaNvCertificado.add(nvCertificado);
/*     */       }
/*     */       
/* 179 */       ps.execute();
/* 180 */       ps.close();
/*     */       
/* 182 */       return listaNvCertificado;
/*     */     } catch (SQLException ex) {
/* 184 */       Logger.getLogger(NvCertificadoDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 185 */       System.out.println(ex.getMessage()); }
/* 186 */     return null;
/*     */   }
/*     */   
/*     */   public boolean deletar(NvCertificado nvCertificado)
/*     */   {
/*     */     try {
/* 192 */       Conexao conexao = new Conexao();
/* 193 */       Connection conn = Conexao.getConnection();
/* 194 */       String sql = "DELETE FROM NV_CERTIFICADO WHERE SEQ_NV_CERTIFICADO =  ? ";
/*     */       
/* 196 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/* 198 */       ps.setString(1, nvCertificado.getSeqNvCertificado());
/*     */       
/* 200 */       ps.execute();
/* 201 */       ps.close();
/*     */       
/* 203 */       return true;
/*     */     } catch (SQLException ex) {
/* 205 */       System.out.println(ex.getMessage()); }
/* 206 */     return false;
/*     */   }
/*     */ }

