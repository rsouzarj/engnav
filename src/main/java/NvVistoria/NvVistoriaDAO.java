/*     */ package NvVistoria;
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
/*     */ 
/*     */ 
/*     */ public class NvVistoriaDAO
/*     */ {
/*     */   public NvVistoria inserir(NvVistoria nvVistoria)
/*     */   {
/*  27 */     String sql = "insert into NV_VISTORIA (SEQ_NV_VISTORIA,IDENTIFICACAO,SEQ_EMPRESA,DATA_CADASTRO,TRANSPORTE_COMBUSTIVEL, \nSEQ_NV_TIPO_VISTORIA,SEQ_NV_EMBARCACAO,DATA_VISTORIA,CIDADE_VISTORIA, \nESTADO_VISTORIA,SEQ_COLABORADOR,SITUACAO, DATA_DOCAGEM, \nseq_nv_vistoria_status, DATA_EMS_LAUDO,APROVADOR,SEQ_UNIDADE_NEGOCIO ) \n values \n (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
 
/*     */     try
/*     */     {
/*  36 */       String seq = Sequence.buscarNumeroSequence("SEQ_NV_VISTORIA");
/*  37 */       nvVistoria.setSeqNvVistoria(seq);
/*  38 */       Conexao conexao = new Conexao();
/*  39 */       Connection conn = Conexao.getConnection();
/*  40 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*  42 */       ps.setString(1, nvVistoria.getSeqNvVistoria());
/*  43 */       ps.setString(2, nvVistoria.getIdentificacao());
/*  44 */       ps.setString(3, nvVistoria.getSeqEmpresa());
/*     */       try {
/*  46 */         ps.setDate(4, new java.sql.Date(nvVistoria.getDataCadastro().getTime()));
/*     */       } catch (NullPointerException e) {
/*  48 */         ps.setDate(4, null);
/*     */       }
/*  50 */       ps.setString(5, nvVistoria.getTransporteCombustivel());
/*  51 */       ps.setString(6, nvVistoria.getSeqNvTipoVistoria());
/*  52 */       ps.setString(7, nvVistoria.getSeqNvEmbarcacao());
/*     */       try {
/*  54 */         ps.setDate(8, new java.sql.Date(nvVistoria.getDataVistoria().getTime()));
/*     */       } catch (NullPointerException e) {
/*  56 */         ps.setDate(8, null);
/*     */       }
/*  58 */       ps.setString(9, nvVistoria.getCidadeVistoria());
/*  59 */       ps.setString(10, nvVistoria.getEstadoVistoria());
/*  60 */       ps.setObject(11, nvVistoria.getSeqColaborador(), 1);
/*  61 */       ps.setString(12, nvVistoria.getSituacao());
/*     */       try {
/*  63 */         ps.setDate(13, new java.sql.Date(nvVistoria.getDataDocagem().getTime()));
/*     */       } catch (NullPointerException e) {
/*  65 */         ps.setDate(13, null);
/*     */       }
/*  67 */       ps.setString(14, nvVistoria.getSeqNvVistoriaStatus());
/*     */       try {
/*  69 */         ps.setDate(15, new java.sql.Date(nvVistoria.getDataEmissaoLaudo().getTime()));
/*     */       } catch (NullPointerException e) {
/*  71 */         ps.setDate(15, null);
/*     */       }
                ps.setString(16, nvVistoria.getAprovador());
                ps.setString(17, nvVistoria.getSeqUnidadeNegocio());
/*  73 */       ps.execute();
/*  74 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/*  77 */       Logger.getLogger(NvVistoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  78 */       System.out.println(ex.getMessage());
/*     */     }
/*     */     
/*  81 */     System.out.println("Insert: " + sql);
/*  82 */     return nvVistoria;
/*     */   }
/*     */   
/*     */   public NvVistoria alterar(NvVistoria nvVistoria) {
/*     */     try {
/*  87 */       Conexao conexao = new Conexao();
/*  88 */       Connection conn = Conexao.getConnection();
/*  89 */       String sql = "update NV_VISTORIA set IDENTIFICACAO = ?,SEQ_EMPRESA = ?,DATA_CADASTRO = ?,TRANSPORTE_COMBUSTIVEL = ?,SEQ_NV_TIPO_VISTORIA = ?,SEQ_NV_EMBARCACAO = ?,DATA_VISTORIA = ?,CIDADE_VISTORIA = ?,ESTADO_VISTORIA = ?,SEQ_COLABORADOR = ?,SITUACAO = ?, DATA_DOCAGEM = ?, seq_nv_vistoria_status = ?, DATA_EMS_LAUDO = ?, APROVADOR = ?, SEQ_UNIDADE_NEGOCIO = ?  where SEQ_NV_VISTORIA = ?";

/*  95 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*  97 */       ps.setString(1, nvVistoria.getIdentificacao());
/*  98 */       ps.setString(2, nvVistoria.getSeqEmpresa());
/*     */       try {
/* 100 */         ps.setDate(3, new java.sql.Date(nvVistoria.getDataCadastro().getTime()));
/*     */       } catch (NullPointerException e) {
/* 102 */         ps.setDate(3, null);
/*     */       }
/* 104 */       ps.setString(4, nvVistoria.getTransporteCombustivel());
/* 105 */       ps.setString(5, nvVistoria.getSeqNvTipoVistoria());
/* 106 */       ps.setString(6, nvVistoria.getSeqNvEmbarcacao());
/*     */       try {
/* 108 */         ps.setDate(7, new java.sql.Date(nvVistoria.getDataVistoria().getTime()));
/*     */       } catch (NullPointerException e) {
/* 110 */         ps.setDate(7, null);
/*     */       }
/* 112 */       ps.setString(8, nvVistoria.getCidadeVistoria());
/* 113 */       ps.setString(9, nvVistoria.getEstadoVistoria());
/* 114 */       ps.setObject(10, nvVistoria.getSeqColaborador(), 1);
/* 115 */       ps.setString(11, nvVistoria.getSituacao());
/*     */       try
/*     */       {
/* 118 */         ps.setDate(12, new java.sql.Date(nvVistoria.getDataDocagem().getTime()));
/*     */       } catch (NullPointerException e) {
/* 120 */         ps.setDate(12, null);
/*     */       }
/* 122 */       ps.setString(13, nvVistoria.getSeqNvVistoriaStatus());
/*     */       try
/*     */       {
/* 125 */         ps.setDate(14, new java.sql.Date(nvVistoria.getDataEmissaoLaudo().getTime()));
/*     */       } catch (NullPointerException e) {
/* 127 */         ps.setDate(14, null);
/*     */       }
/*     */       ps.setString(15, nvVistoria.getAprovador());
                ps.setString(16, nvVistoria.getSeqUnidadeNegocio());
/* 130 */       ps.setString(17, nvVistoria.getSeqNvVistoria());
/* 131 */       ps.execute();
/* 132 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/* 135 */       Logger.getLogger(NvVistoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 136 */       System.out.println(ex.getMessage());
/*     */     }
/*     */     
/* 139 */     return nvVistoria;
/*     */   }




               public NvVistoria aprovar(NvVistoria nvVistoria) {
/*     */     try {
/*  87 */       Conexao conexao = new Conexao();
/*  88 */       Connection conn = Conexao.getConnection();
/*  89 */       String sql = "update NV_VISTORIA set IDENTIFICACAO = ?,SEQ_EMPRESA = ?,DATA_CADASTRO = ?,TRANSPORTE_COMBUSTIVEL = ?,SEQ_NV_TIPO_VISTORIA = ?,SEQ_NV_EMBARCACAO = ?,DATA_VISTORIA = ?,CIDADE_VISTORIA = ?,ESTADO_VISTORIA = ?,SEQ_COLABORADOR = ?,SITUACAO = ?, DATA_DOCAGEM = ?, seq_nv_vistoria_status = ?, DATA_EMS_LAUDO = ?, APROVADOR = ? where SEQ_NV_VISTORIA = ?";
/*     */       
                PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*  97 */       ps.setString(1, nvVistoria.getIdentificacao());
/*  98 */       ps.setString(2, nvVistoria.getSeqEmpresa());
/*     */       try {
/* 100 */         ps.setDate(3, new java.sql.Date(nvVistoria.getDataCadastro().getTime()));
/*     */       } catch (NullPointerException e) {
/* 102 */         ps.setDate(3, null);
/*     */       }
/* 104 */       ps.setString(4, nvVistoria.getTransporteCombustivel());
/* 105 */       ps.setString(5, nvVistoria.getSeqNvTipoVistoria());
/* 106 */       ps.setString(6, nvVistoria.getSeqNvEmbarcacao());
/*     */       try {
/* 108 */         ps.setDate(7, new java.sql.Date(nvVistoria.getDataVistoria().getTime()));
/*     */       } catch (NullPointerException e) {
/* 110 */         ps.setDate(7, null);
/*     */       }
/* 112 */       ps.setString(8, nvVistoria.getCidadeVistoria());
/* 113 */       ps.setString(9, nvVistoria.getEstadoVistoria());
/* 114 */       ps.setObject(10, nvVistoria.getSeqColaborador(), 1);
/* 115 */       ps.setString(11, nvVistoria.getSituacao());
/*     */       try
/*     */       {
/* 118 */         ps.setDate(12, new java.sql.Date(nvVistoria.getDataDocagem().getTime()));
/*     */       } catch (NullPointerException e) {
/* 120 */         ps.setDate(12, null);
/*     */       }
/* 122 */       ps.setString(13, nvVistoria.getSeqNvVistoriaStatus());
/*     */       try
/*     */       {
/* 125 */         ps.setDate(14, new java.sql.Date(nvVistoria.getDataEmissaoLaudo().getTime()));
/*     */       } catch (NullPointerException e) {
/* 127 */         ps.setDate(14, null);
/*     */       }
                ps.setString(15, nvVistoria.getAprovador());
                ps.setString(16, nvVistoria.getSeqNvVistoria());
/* 131 */       ps.execute();
/* 132 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/* 135 */       Logger.getLogger(NvVistoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 136 */       System.out.println(ex.getMessage());
/*     */     }
/*     */     
/* 139 */     return nvVistoria;
/*     */   }

/*     */   public List<NvVistoria> listar(ClausulaWhere sClausula) {
/*     */     try {
/* 144 */       Conexao conexao = new Conexao();
/* 145 */       Connection conn = Conexao.getConnection();
/* 146 */       String sql = "SELECT NV_VISTORIA.*, nv_embarcacao.nome embarcacaoNome, nv_tipo_vistoria.nome tipo_vistoria, colaborador.nome colaborador_nome,usuario.usuario FROM NV_VISTORIA inner join nv_tipo_vistoria on nv_tipo_vistoria.seq_nv_tipo_vistoria = NV_VISTORIA.seq_nv_tipo_vistoria inner join nv_embarcacao on nv_embarcacao.seq_nv_embarcacao = NV_VISTORIA.seq_nv_embarcacao left join  colaborador on colaborador.seq_colaborador = NV_VISTORIA.seq_colaborador left join usuario on usuario.seq_usuario = nv_vistoria.aprovador left join unidade_negocio on unidade_negocio.seq_unidade_negocio = nv_vistoria.seq_unidade_negocio " + sClausula.montarsClausula();
 
/* 154 */       System.out.println(sql);
/*     */       
/* 156 */       List<NvVistoria> listaNvVistoria = new ArrayList();
/* 157 */       PreparedStatement ps = conn.prepareStatement(sql);
/* 158 */       ResultSet rs = ps.executeQuery();
/*     */       
/* 160 */       while (rs.next()) {
/* 161 */         NvVistoria nvVistoria = new NvVistoria();
/* 162 */         nvVistoria.setSeqNvVistoria(rs.getString("SEQ_NV_VISTORIA"));
/* 163 */         nvVistoria.setIdentificacao(rs.getString("IDENTIFICACAO"));
/* 164 */         nvVistoria.setSeqEmpresa(rs.getString("SEQ_EMPRESA"));
/* 165 */         nvVistoria.setDataCadastro(rs.getDate("DATA_CADASTRO"));
/* 166 */         nvVistoria.setTransporteCombustivel(rs.getString("TRANSPORTE_COMBUSTIVEL"));
/* 167 */         nvVistoria.setSeqNvTipoVistoria(rs.getString("SEQ_NV_TIPO_VISTORIA"));
/* 168 */         nvVistoria.setSeqNvEmbarcacao(rs.getString("SEQ_NV_EMBARCACAO"));
/* 169 */         nvVistoria.setDataVistoria(rs.getDate("DATA_VISTORIA"));
/* 170 */         nvVistoria.setCidadeVistoria(rs.getString("CIDADE_VISTORIA"));
/* 171 */         nvVistoria.setEstadoVistoria(rs.getString("ESTADO_VISTORIA"));
/* 172 */         nvVistoria.setSeqColaborador(rs.getString("SEQ_COLABORADOR"));
/* 173 */         nvVistoria.setSituacao(rs.getString("SITUACAO"));
/* 174 */         nvVistoria.setDataDocagem(rs.getDate("DATA_DOCAGEM"));
/* 175 */         nvVistoria.setDataEmissaoLaudo(rs.getDate("DATA_EMS_LAUDO"));
/* 176 */         nvVistoria.setSeqNvVistoriaStatus(rs.getString("seq_nv_vistoria_status"));
/* 177 */         nvVistoria.setEmbarcacaoNome(rs.getString("embarcacaoNome"));
/* 178 */         nvVistoria.setTipoVistoria(rs.getString("tipo_vistoria"));
/* 179 */         nvVistoria.setColaboradorNome(rs.getString("colaborador_nome"));
/*     */         nvVistoria.setAprovador(rs.getString("APROVADOR"));
                  nvVistoria.setUsuario(rs.getString("usuario"));
                  nvVistoria.setSeqUnidadeNegocio(rs.getString("seq_unidade_negocio"));
/* 181 */         listaNvVistoria.add(nvVistoria);
/*     */       }
/*     */       
/* 184 */       ps.execute();
/* 185 */       ps.close();
/*     */       
/* 187 */       return listaNvVistoria;
/*     */     } catch (SQLException ex) {
/* 189 */       Logger.getLogger(NvVistoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 190 */       System.out.println(ex.getMessage()); }
/* 191 */     return null;
/*     */   }
/*     */   
/*     */   public boolean deletar(NvVistoria nvVistoria)
/*     */   {
/*     */     try {
/* 197 */       Conexao conexao = new Conexao();
/* 198 */       Connection conn = Conexao.getConnection();
/* 199 */       String sql = "DELETE FROM NV_VISTORIA WHERE SEQ_NV_VISTORIA =  ? ";
/*     */       
/* 201 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/* 203 */       ps.setString(1, nvVistoria.getSeqNvVistoria());
/*     */       
/* 205 */       ps.execute();
/* 206 */       ps.close();
/*     */       
/* 208 */       return true;
/*     */     } catch (SQLException ex) {
/* 210 */       System.out.println(ex.getMessage()); }
/* 211 */     return false;
/*     */   }
/*     */ }

