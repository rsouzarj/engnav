/*     */ package NvCertificadoEquipamento;
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

/*     */ public class NvCertificadoEquipamentoDAO
/*     */ {
/*     */   public NvCertificadoEquipamento inserir(NvCertificadoEquipamento nvCertificadoEquipamento)
/*     */   {
/*     */     try
/*     */     {
/*  27 */       String seq = Sequence.buscarNumeroSequence("SEQ_NV_CERTIFICADO_EQP");
/*  28 */       nvCertificadoEquipamento.setSeqCertificado(seq);
/*  29 */       Conexao conexao = new Conexao();
/*  30 */       Connection conn = Conexao.getConnection();
/*  31 */       String sql = "insert into NV_CERTIFICADO_EQUIPAMENTO (SEQ_NV_CERTIFICADO, IDENTIFICACAO, SEQ_EMPRESA, DATA_CADASTRO, SITUACAO, SEQ_NV_EQUIPAMENTO, DATA_EMISSAO, NOME_VISTORIADOR, SEQ_COLABORADOR, OBSERVACAO, LOCAL_EMISSAO, ASSINATURA_DIGITAL, DATA_VALIDADE, NORMAS, REL_ENSAIO, REL_CONFORM_TEC, CONFORM_TEC, DATA_EMS_TCARGA, DATA_VAL_TCARGA, FILIAL, SEQ_PARCEIRO) values  (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

/*  35 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*  37 */       ps.setString(1, nvCertificadoEquipamento.getSeqCertificado());
/*  38 */       ps.setString(2, nvCertificadoEquipamento.getIdentificacao());
/*  39 */       ps.setString(3, nvCertificadoEquipamento.getSeqEmpresa());
/*     */       try {
/*  41 */         ps.setDate(4, new java.sql.Date(nvCertificadoEquipamento.getDataCadastro().getTime()));
/*     */       } catch (NullPointerException e) {
/*  43 */         ps.setDate(4, null);
/*     */       }
/*  45 */       ps.setString(5, nvCertificadoEquipamento.getSituacao());
/*  46 */       ps.setString(6, nvCertificadoEquipamento.getSeqEquipamento());
/*     */       try {
/*  49 */         ps.setDate(7, new java.sql.Date(nvCertificadoEquipamento.getDataEmissao().getTime()));
/*     */       } catch (NullPointerException e) {
/*  51 */         ps.setDate(7, null);
/*     */       }
/*  53 */       ps.setString(8,  nvCertificadoEquipamento.getNomeVistoriador());
/*  54 */       ps.setString(9,  nvCertificadoEquipamento.getSeqColaborador());
/*  56 */       ps.setString(10, nvCertificadoEquipamento.getObservacao());
/*  57 */       ps.setString(11, nvCertificadoEquipamento.getLocalEmissao());
/*     */       ps.setString(12, nvCertificadoEquipamento.getAssinaturaDigital());
                try {
                ps.setDate(13, new java.sql.Date(nvCertificadoEquipamento.getValidade().getTime()));
                } catch (NullPointerException e) {
                 ps.setDate(13, null);
                }       
/*     */       ps.setString(14, nvCertificadoEquipamento.getNormas());
                ps.setString(15, nvCertificadoEquipamento.getRealtorioEnsaio());
                ps.setString(16, nvCertificadoEquipamento.getRelatorioConformidade());
                ps.setString(17, nvCertificadoEquipamento.getConformidadeTecnica());
                try {
                ps.setDate(18, new java.sql.Date(nvCertificadoEquipamento.getEmstCarga().getTime()));
                } catch (NullPointerException e) {
                 ps.setDate(18, null);
                }
                try {
                ps.setDate(19, new java.sql.Date(nvCertificadoEquipamento.getValtCarga().getTime()));
                } catch (NullPointerException e) {
                 ps.setDate(19, null);
                }
                ps.setString(20, nvCertificadoEquipamento.getFilial());
                ps.setString(21, nvCertificadoEquipamento.getSeqParceiro());
                ps.execute();
/*  67 */       ps.close();
/*     */     }
/*     */     catch (SQLException var11) {
              Logger.getLogger(NvCertificadoEquipamentoDAO.class.getName()).log(Level.SEVERE, (String)null, var11);
               System.out.println(var11.getMessage());
/*     */     }
/*  73 */     return nvCertificadoEquipamento;
/*     */   }

/*     */   




                public NvCertificadoEquipamento alterar(NvCertificadoEquipamento nvCertificadoEquipamento) {
/*     */     try {
/*  78 */       Conexao conexao = new Conexao();
/*  79 */       Connection conn = Conexao.getConnection();
/*  80 */       String sql = "UPDATE NV_CERTIFICADO_EQUIPAMENTO set IDENTIFICACAO = ?,SITUACAO = ?,SEQ_NV_EQUIPAMENTO = ?,DATA_EMISSAO = ?,NOME_VISTORIADOR = ?, SEQ_COLABORADOR = ?, OBSERVACAO = ?, LOCAL_EMISSAO = ?, DATA_VALIDADE = ?, ASSINATURA_DIGITAL = ?, NORMAS = ?,REL_ENSAIO = ?,REL_CONFORM_TEC = ?,CONFORM_TEC = ?,FILIAL = ?, DATA_EMS_TCARGA = ?, DATA_VAL_TCARGA = ?, SEQ_PARCEIRO = ?  where SEQ_NV_CERTIFICADO = ?";
 
/*     */ 
/*  87 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*  89 */       
/*  38 */       ps.setString(1, nvCertificadoEquipamento.getIdentificacao());
/*  45 */       ps.setString(2, nvCertificadoEquipamento.getSituacao());
/*  46 */       ps.setString(3, nvCertificadoEquipamento.getSeqEquipamento());
/*     */       try {
/*  49 */         ps.setDate(4, new java.sql.Date(nvCertificadoEquipamento.getDataEmissao().getTime()));
/*     */       } catch (NullPointerException e) {
/*  51 */         ps.setDate(4, null);
/*     */       }
/*  53 */       ps.setString(5, nvCertificadoEquipamento.getNomeVistoriador());
/*  54 */       ps.setString(6, nvCertificadoEquipamento.getSeqColaborador());
/*  56 */       ps.setString(7, nvCertificadoEquipamento.getObservacao());
/*  57 */       ps.setString(8, nvCertificadoEquipamento.getLocalEmissao());
/*     */       try {
                ps.setDate(9, new java.sql.Date(nvCertificadoEquipamento.getValidade().getTime()));
                } catch (NullPointerException e) {
                 ps.setDate(9, null);
                }
/*  64 */       ps.setString(10, nvCertificadoEquipamento.getAssinaturaDigital());
/*     */       ps.setString(11, nvCertificadoEquipamento.getNormas());
                ps.setString(12, nvCertificadoEquipamento.getRealtorioEnsaio());
                ps.setString(13, nvCertificadoEquipamento.getRelatorioConformidade());
                ps.setString(14, nvCertificadoEquipamento.getConformidadeTecnica());
                ps.setString(15, nvCertificadoEquipamento.getFilial());
                try {
                ps.setDate(16, new java.sql.Date(nvCertificadoEquipamento.getEmstCarga().getTime()));
                } catch (NullPointerException e) {
                 ps.setDate(16, null);
                }
                try {
                ps.setDate(17, new java.sql.Date(nvCertificadoEquipamento.getValtCarga().getTime()));
                } catch (NullPointerException e) {
                 ps.setDate(17, null);
                }
                ps.setString(18, nvCertificadoEquipamento.getSeqParceiro());
                ps.setString(19, nvCertificadoEquipamento.getSeqCertificado());
/* 109 */       ps.execute();
/* 110 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/* 113 */       Logger.getLogger(NvCertificadoEquipamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 114 */       System.out.println(ex.getMessage());
/*     */     }
/*     */     
/* 117 */     return nvCertificadoEquipamento;
/*     */   }
 
/*     */   public List<NvCertificadoEquipamento> listar(ClausulaWhere sClausula) {
/*     */     try {
/* 122 */       Conexao conexao = new Conexao();
/* 123 */       Connection conn = Conexao.getConnection();
/* 124 */       String sql = "SELECT NV_CERTIFICADO_EQUIPAMENTO.*, equipamento.nome equipamento_nome, parceiro.nome nome_parceiro FROM NV_CERTIFICADO_EQUIPAMENTO\ninner join equipamento on equipamento.seq_equipamento = NV_CERTIFICADO_EQUIPAMENTO.seq_nv_equipamento\ninner join parceiro on parceiro.seq_parceiro = nv_certificado_equipamento.seq_parceiro " + sClausula.montarsClausula();
/*     */       
/*     */ 
/* 128 */       System.out.println(sql);
/*     */       
/* 130 */       List<NvCertificadoEquipamento> listaNvCertificadoEquipamento = new ArrayList();
/* 131 */       PreparedStatement ps = conn.prepareStatement(sql);
/* 132 */       ResultSet rs = ps.executeQuery();
    
/* 134 */       while (rs.next()) {
/* 135 */         NvCertificadoEquipamento NvCertificadoEquipamento = new NvCertificadoEquipamento();
/* 136 */         NvCertificadoEquipamento.setSeqCertificado(rs.getString("SEQ_NV_CERTIFICADO"));
/* 137 */         NvCertificadoEquipamento.setIdentificacao(rs.getString("IDENTIFICACAO"));
/* 138 */         NvCertificadoEquipamento.setSeqEmpresa(rs.getString("SEQ_EMPRESA"));
/* 139 */         NvCertificadoEquipamento.setDataCadastro(rs.getDate("DATA_CADASTRO"));
/* 143 */         NvCertificadoEquipamento.setDataEmissao(rs.getDate("DATA_EMISSAO"));
                  NvCertificadoEquipamento.setValidade(rs.getDate("DATA_VALIDADE"));
                  NvCertificadoEquipamento.setEmstCarga(rs.getDate("DATA_EMS_TCARGA"));
                  NvCertificadoEquipamento.setValtCarga(rs.getDate("DATA_VAL_TCARGA"));
/* 140 */         NvCertificadoEquipamento.setSituacao(rs.getString("SITUACAO"));
/* 141 */         NvCertificadoEquipamento.setSeqEquipamento(rs.getString("SEQ_NV_EQUIPAMENTO"));
/* 144 */         NvCertificadoEquipamento.setNomeVistoriador(rs.getString("NOME_VISTORIADOR"));
/* 145 */         NvCertificadoEquipamento.setEquipamentoNome(rs.getString("equipamento_nome"));
/* 146 */         NvCertificadoEquipamento.setSeqColaborador(rs.getString("SEQ_COLABORADOR"));
/* 148 */         NvCertificadoEquipamento.setObservacao(rs.getString("OBSERVACAO"));
/* 149 */         NvCertificadoEquipamento.setLocalEmissao(rs.getString("LOCAL_EMISSAO"));
/* 151 */         NvCertificadoEquipamento.setAssinaturaDigital(rs.getString("ASSINATURA_DIGITAL"));
                  NvCertificadoEquipamento.setNormas(rs.getString("NORMAS"));
/* 149 */         NvCertificadoEquipamento.setRealtorioEnsaio(rs.getString("REL_ENSAIO"));
/* 151 */         NvCertificadoEquipamento.setRelatorioConformidade(rs.getString("REL_CONFORM_TEC"));
/* 152 */         NvCertificadoEquipamento.setConformidadeTecnica(rs.getString("CONFORM_TEC"));
                  NvCertificadoEquipamento.setFilial(rs.getString("FILIAL"));
                  NvCertificadoEquipamento.setSeqParceiro(rs.getString("SEQ_PARCEIRO"));
                  NvCertificadoEquipamento.setNomeParceiro(rs.getString("nome_parceiro"));
/* 153 */         listaNvCertificadoEquipamento.add(NvCertificadoEquipamento);
/*     */       }
/*     */       
/* 156 */       ps.execute();
/* 157 */       ps.close();
/*     */       
/* 159 */       return listaNvCertificadoEquipamento;
/*     */     } catch (SQLException ex) {
/* 161 */       Logger.getLogger(NvCertificadoEquipamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 162 */       System.out.println(ex.getMessage()); }
/* 163 */     return null;
/*     */   }





  
/*     */   public boolean deletar(NvCertificadoEquipamento nvCertificadoEquipamento)
/*     */   {
/*     */     try {
/* 169 */       Conexao conexao = new Conexao();
/* 170 */       Connection conn = Conexao.getConnection();
/* 171 */       String sql = "DELETE FROM NV_CERTIFICADO_EQUIPAMENTO WHERE SEQ_NV_CERTIFICADO =  ? ";
/*     */       
/* 173 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/* 175 */       ps.setString(1, nvCertificadoEquipamento.getSeqCertificado());
/*     */       
/* 177 */       ps.execute();
/* 178 */       ps.close();
/*     */       
/* 180 */       return true;
/*     */     } catch (SQLException ex) {
/* 182 */       System.out.println(ex.getMessage()); }
/* 183 */     return false;
/*     */   }
/*     */ }
