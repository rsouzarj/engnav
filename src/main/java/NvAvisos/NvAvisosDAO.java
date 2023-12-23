package NvAvisos;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import ClausulaSQL.ClausulaWhere;
import NvCertificadoDetalhe.NvCertificadoDetalheDAO;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Util.Conexao;


public class NvAvisosDAO
{
       
    public List<NvAvisos> listar(ClausulaWhere sClausula) {
        try {
            final Connection conn = Conexao.getConnection();
            final String sql = "select * from VW_BI_CERTIFICADO_AVISOS" + sClausula.montarsClausula();
            System.out.println(sql);
            final List<NvAvisos> listaNvAvisos = new ArrayList<NvAvisos>();
            final PreparedStatement ps = conn.prepareStatement(sql);
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                final NvAvisos NvAvisos = new NvAvisos();
                NvAvisos.setSeqEmpresa(rs.getString("SEQ_EMPRESA"));
                NvAvisos.setSeqCertificado(rs.getString("SEQ_NV_CERTIFICADO"));
                NvAvisos.setIdentificacao(rs.getString("IDENTIFICACAO"));
                NvAvisos.setTipoCertificado(rs.getString("NOME"));
                NvAvisos.setEmbarcacao(rs.getString("EMBARCACAO"));
                NvAvisos.setCliente(rs.getString("CLIENTE"));
                NvAvisos.setArealiza(rs.getString("AREALIZA"));
                NvAvisos.setFilial(rs.getString("FILIAL"));
                NvAvisos.setLocal(rs.getString("LOCAL_EMISSAO"));
                NvAvisos.setDataCadastro(rs.getDate("DATA_CADASTRO"));
                NvAvisos.setDataEmissao(rs.getDate("DATA_EMISSAO"));
                NvAvisos.setDataValidade(rs.getDate("DATA_VALIDADE"));
                NvAvisos.setDataInicial(rs.getDate("DATA_INICIAL"));
                NvAvisos.setDataFinal(rs.getDate("DATA_FINAL"));
                NvAvisos.setEmail(rs.getString("EMAIL"));
                NvAvisos.setVdate(rs.getString("ALERTA"));
                listaNvAvisos.add(NvAvisos);
            }
            ps.execute();
            ps.close();
            return listaNvAvisos;
        }
        catch (SQLException ex) {
            Logger.getLogger(NvAvisosDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
     public List<NvAvisos> listar60(ClausulaWhere sClausula) {
        try {
            final Connection conn = Conexao.getConnection();
            final String sql = "select * from VW_BI_CERTIFICADO_AVISOS60" + sClausula.montarsClausula();
            System.out.println(sql);
            final List<NvAvisos> listaNvAvisos60 = new ArrayList<NvAvisos>();
            final PreparedStatement ps = conn.prepareStatement(sql);
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                final NvAvisos NvAvisos = new NvAvisos();
                NvAvisos.setSeqEmpresa(rs.getString("SEQ_EMPRESA"));
                NvAvisos.setSeqCertificado(rs.getString("SEQ_NV_CERTIFICADO"));
                NvAvisos.setIdentificacao(rs.getString("IDENTIFICACAO"));
                NvAvisos.setTipoCertificado(rs.getString("NOME"));
                NvAvisos.setEmbarcacao(rs.getString("EMBARCACAO"));
                NvAvisos.setCliente(rs.getString("CLIENTE"));
                NvAvisos.setArealiza(rs.getString("AREALIZA"));
                NvAvisos.setFilial(rs.getString("FILIAL"));
                NvAvisos.setLocal(rs.getString("LOCAL_EMISSAO"));
                NvAvisos.setDataCadastro(rs.getDate("DATA_CADASTRO"));
                NvAvisos.setDataEmissao(rs.getDate("DATA_EMISSAO"));
                NvAvisos.setDataValidade(rs.getDate("DATA_VALIDADE"));
                NvAvisos.setDataInicial(rs.getDate("DATA_INICIAL"));
                NvAvisos.setDataFinal(rs.getDate("DATA_FINAL"));           
                listaNvAvisos60.add(NvAvisos);
            }
            ps.execute();
            ps.close();
            return listaNvAvisos60;
        }
        catch (SQLException ex) {
            Logger.getLogger(NvAvisosDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return null;
        }
    }
     
      public List<NvAvisos> listar30(ClausulaWhere sClausula) {
        try {
            final Connection conn = Conexao.getConnection();
            final String sql = "select * from VW_BI_CERTIFICADO_AVISOS30" + sClausula.montarsClausula();
            System.out.println(sql);
            final List<NvAvisos> listaNvAvisos30 = new ArrayList<NvAvisos>();
            final PreparedStatement ps = conn.prepareStatement(sql);
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                final NvAvisos NvAvisos = new NvAvisos();
                NvAvisos.setSeqEmpresa(rs.getString("SEQ_EMPRESA"));
                NvAvisos.setSeqCertificado(rs.getString("SEQ_NV_CERTIFICADO"));
                NvAvisos.setIdentificacao(rs.getString("IDENTIFICACAO"));
                NvAvisos.setTipoCertificado(rs.getString("NOME"));
                NvAvisos.setEmbarcacao(rs.getString("EMBARCACAO"));
                NvAvisos.setCliente(rs.getString("CLIENTE"));
                NvAvisos.setArealiza(rs.getString("AREALIZA"));
                NvAvisos.setFilial(rs.getString("FILIAL"));
                NvAvisos.setLocal(rs.getString("LOCAL_EMISSAO"));
                NvAvisos.setDataCadastro(rs.getDate("DATA_CADASTRO"));
                NvAvisos.setDataEmissao(rs.getDate("DATA_EMISSAO"));
                NvAvisos.setDataValidade(rs.getDate("DATA_VALIDADE"));
                NvAvisos.setDataInicial(rs.getDate("DATA_INICIAL"));
                NvAvisos.setDataFinal(rs.getDate("DATA_FINAL"));           
                listaNvAvisos30.add(NvAvisos);
            }
            ps.execute();
            ps.close();
            return listaNvAvisos30;
        }
        catch (SQLException ex) {
            Logger.getLogger(NvAvisosDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return null;
        }
    
      }
      
       public List<NvAvisos> listarjanelas(ClausulaWhere sClausula) {
        try {
            final Connection conn = Conexao.getConnection();
            final String sql = "select * from VW_BI_JANELAS_AVISOS" + sClausula.montarsClausula();
            System.out.println(sql);
            final List<NvAvisos> listaNvAvisosJanelas = new ArrayList<NvAvisos>();
            final PreparedStatement ps = conn.prepareStatement(sql);
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                final NvAvisos NvAvisos = new NvAvisos();
                NvAvisos.setSeqEmpresa(rs.getString("SEQ_EMPRESA"));
                NvAvisos.setSeqCertificado(rs.getString("SEQ_NV_CERTIFICADO"));
                NvAvisos.setSeqNvCertificadoDetalhe(rs.getString("SEQ_NV_CERTIFICADO_DETALHE"));
                NvAvisos.setIdentificacao(rs.getString("IDENTIFICACAO"));
                NvAvisos.setTipoCertificado(rs.getString("NOME"));
                NvAvisos.setEmbarcacao(rs.getString("EMBARCACAO"));
                NvAvisos.setCliente(rs.getString("CLIENTE"));
                NvAvisos.setArealiza(rs.getString("AREALIZA"));
                NvAvisos.setFilial(rs.getString("FILIAL"));
                NvAvisos.setLocal(rs.getString("LOCAL_EMISSAO"));
                NvAvisos.setDataCadastro(rs.getDate("DATA_CADASTRO"));
                NvAvisos.setDataEmissao(rs.getDate("DATA_EMISSAO"));
                NvAvisos.setDataValidade(rs.getDate("DATA_VALIDADE"));
                NvAvisos.setDataInicial(rs.getDate("DATA_INICIAL"));
                NvAvisos.setDataFinal(rs.getDate("DATA_FINAL"));
                NvAvisos.setEmail(rs.getString("EMAIL"));
                NvAvisos.setAviso(rs.getString("AVISO"));
                NvAvisos.setVdate(rs.getString("ALERTA"));
                listaNvAvisosJanelas.add(NvAvisos);
            }
            ps.execute();
            ps.close();
            return listaNvAvisosJanelas;
        }
        catch (SQLException ex) {
            Logger.getLogger(NvAvisosDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return null;
        }
    
      }
      
      
      public List<NvAvisos> listarjanelas30(ClausulaWhere sClausula) {
        try {
            final Connection conn = Conexao.getConnection();
            final String sql = "select * from VW_BI_JANELAS_AVISOS30" + sClausula.montarsClausula();
            System.out.println(sql);
            final List<NvAvisos> listaNvAvisosJanelas30 = new ArrayList<NvAvisos>();
            final PreparedStatement ps = conn.prepareStatement(sql);
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                final NvAvisos NvAvisos = new NvAvisos();
                NvAvisos.setSeqEmpresa(rs.getString("SEQ_EMPRESA"));
                NvAvisos.setSeqCertificado(rs.getString("SEQ_NV_CERTIFICADO"));
                NvAvisos.setIdentificacao(rs.getString("IDENTIFICACAO"));
                NvAvisos.setTipoCertificado(rs.getString("NOME"));
                NvAvisos.setEmbarcacao(rs.getString("EMBARCACAO"));
                NvAvisos.setCliente(rs.getString("CLIENTE"));
                NvAvisos.setArealiza(rs.getString("AREALIZA"));
                NvAvisos.setFilial(rs.getString("FILIAL"));
                NvAvisos.setLocal(rs.getString("LOCAL_EMISSAO"));
                NvAvisos.setDataCadastro(rs.getDate("DATA_CADASTRO"));
                NvAvisos.setDataEmissao(rs.getDate("DATA_EMISSAO"));
                NvAvisos.setDataValidade(rs.getDate("DATA_VALIDADE"));
                NvAvisos.setDataInicial(rs.getDate("DATA_INICIAL"));
                NvAvisos.setDataFinal(rs.getDate("DATA_FINAL"));
                NvAvisos.setEmail(rs.getString("EMAIL"));
                listaNvAvisosJanelas30.add(NvAvisos);
            }
            ps.execute();
            ps.close();
            return listaNvAvisosJanelas30;
        }
        catch (SQLException ex) {
            Logger.getLogger(NvAvisosDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return null;
        }
    
      }
      
       public List<NvAvisos> listarjanelas60(ClausulaWhere sClausula) {
        try {
            final Connection conn = Conexao.getConnection();
            final String sql = "select * from VW_BI_JANELAS_AVISOS60" + sClausula.montarsClausula();
            System.out.println(sql);
            final List<NvAvisos> listaNvAvisosJanelas60 = new ArrayList<NvAvisos>();
            final PreparedStatement ps = conn.prepareStatement(sql);
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                final NvAvisos NvAvisos = new NvAvisos();
                NvAvisos.setSeqEmpresa(rs.getString("SEQ_EMPRESA"));
                NvAvisos.setSeqCertificado(rs.getString("SEQ_NV_CERTIFICADO"));
                NvAvisos.setIdentificacao(rs.getString("IDENTIFICACAO"));
                NvAvisos.setTipoCertificado(rs.getString("NOME"));
                NvAvisos.setEmbarcacao(rs.getString("EMBARCACAO"));
                NvAvisos.setCliente(rs.getString("CLIENTE"));
                NvAvisos.setArealiza(rs.getString("AREALIZA"));
                NvAvisos.setFilial(rs.getString("FILIAL"));
                NvAvisos.setLocal(rs.getString("LOCAL_EMISSAO"));
                NvAvisos.setDataCadastro(rs.getDate("DATA_CADASTRO"));
                NvAvisos.setDataEmissao(rs.getDate("DATA_EMISSAO"));
                NvAvisos.setDataValidade(rs.getDate("DATA_VALIDADE"));
                NvAvisos.setDataInicial(rs.getDate("DATA_INICIAL"));
                NvAvisos.setDataFinal(rs.getDate("DATA_FINAL"));
                NvAvisos.setEmail(rs.getString("EMAIL"));
                listaNvAvisosJanelas60.add(NvAvisos);
            }
            ps.execute();
            ps.close();
            return listaNvAvisosJanelas60;
        }
        catch (SQLException ex) {
            Logger.getLogger(NvAvisosDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return null;
        }

    }

    public NvAvisos inserirAviso(NvAvisos nvAvisos) {
        try {
            Conexao conexao = new Conexao();
            Connection conn = Conexao.getConnection();
            String sql = "insert into NV_CERTIFICADO_AVISO (seq_nv_certificado_detalhe,aviso) values (?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nvAvisos.getSeqNvCertificadoDetalhe());
            ps.setString(2,nvAvisos.getAviso());
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(NvAvisos.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return nvAvisos;
    }
       
       
           
}

