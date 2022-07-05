package Equipamento;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import ClausulaSQL.ClausulaWhere;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;
import Util.Conexao;
import Util.Sequence;

public class EquipamentoDAO
{
    public Equipamento inserir(final Equipamento equipamento) {
        try {
            final String seq = Sequence.buscarNumeroSequence("SEQ_EQUIPAMENTO");
            equipamento.setSeqEquipamento(seq);
            final Conexao conexao = new Conexao();
            final Connection conn = Conexao.getConnection();
            final String sql = "insert into EQUIPAMENTO (SEQ_EQUIPAMENTO,NOME,SITUACAO,SEQ_EMPRESA,DATA_CADASTRO,SEQ_PARCEIRO,FMAS,CARGA,MSPR,TIPO_ACIONAMENTO,CABO_TRACAO,DIAMETRO,PERNAS,DIMENSAO_CLA,PESO,TIPO_EQUIPAMENTO,MODELO,ANO,NSERIE,MSERIE,POTENCIA,RPM,MBL,codigo,capacidade_max,qtd_ocupantes,comp_lanca,comp_cabo,construcao,diam_nominal,comprimento,tag,quantidade,dimensao_ad,material,localizacao,id_cabeco,embarcacao,pressao_trabalho,acionamento,carga_ruptura,grau,diametro_corpo,moitao_swl) values  (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            final PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, equipamento.getSeqEquipamento());
            ps.setString(2, equipamento.getNome());
            ps.setString(3, equipamento.getSituacao());
            ps.setString(4, equipamento.getSeqEmpresa());
            try {
                ps.setDate(5, new Date(equipamento.getDataCadastro().getTime()));
            }
            catch (NullPointerException e) {
                ps.setDate(5, null);
            }
            ps.setString(6, equipamento.getSeqParceiro());
            ps.setString(7, equipamento.getFmas());
            ps.setString(8, equipamento.getCarga());
            ps.setString(9, equipamento.getMspr());
            ps.setString(10, equipamento.getTipoAcionamento());
            ps.setString(11, equipamento.getCaboTracao());
            ps.setString(12, equipamento.getDiametro());
            ps.setString(13, equipamento.getPernas());
            ps.setString(14, equipamento.getDimensaoCla());
            ps.setString(15, equipamento.getPeso());
            ps.setString(16, equipamento.getTipoequipamento());
            ps.setString(17, equipamento.getModelo());
            ps.setString(18, equipamento.getAno());
            ps.setString(19, equipamento.getNserie());
            ps.setString(20, equipamento.getMserie());
            ps.setString(21, equipamento.getPotencia());
            ps.setString(22, equipamento.getRpm());
            ps.setString(23, equipamento.getMbl());
            ps.setString(24, equipamento.getCodigo());
            ps.setString(25, equipamento.getCapacidadeMax());
            ps.setString(26, equipamento.getQtdOcupantes());
            ps.setString(27, equipamento.getCompLanca());
            ps.setString(28, equipamento.getCompCabo());
            ps.setString(29, equipamento.getConstrucao());
            ps.setString(30, equipamento.getDiamNominal());
            ps.setString(31, equipamento.getComprimento());
            ps.setString(32, equipamento.getTag());
            ps.setString(33, equipamento.getQuantidade());
            ps.setString(34, equipamento.getDimensaoAd());
            ps.setString(35, equipamento.getMaterial());
            ps.setString(36, equipamento.getLocalizacao());
            ps.setString(37, equipamento.getIdCabeco());
            ps.setString(38, equipamento.getEmbarcacao());
            ps.setString(39, equipamento.getPressTrabalho());
            ps.setString(40, equipamento.getAcionamento());
            ps.setString(41, equipamento.getCargaRuptura());
            ps.setString(42, equipamento.getGrau());
            ps.setString(43, equipamento.getDiametroCorpo());
            ps.setString(44, equipamento.getMoitao());
            ps.execute();
            ps.close();
        }
        catch (SQLException ex) {
            Logger.getLogger(EquipamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return equipamento;
    }
    
    public Equipamento alterar(final Equipamento equipamento) {
        try {
            final Conexao conexao = new Conexao();
            final Connection conn = Conexao.getConnection();
            final String sql = "update EQUIPAMENTO set NOME = ?,SITUACAO = ?,SEQ_EMPRESA = ?,DATA_CADASTRO = ?, SEQ_PARCEIRO = ?,FMAS = ?,CARGA = ?,MSPR = ?,TIPO_ACIONAMENTO = ?,CABO_TRACAO = ?,DIAMETRO = ?,PERNAS = ?,DIMENSAO_CLA = ?,PESO = ?,TIPO_EQUIPAMENTO = ?,MODELO = ?,ANO = ?,NSERIE = ?,MSERIE = ?,POTENCIA = ?,RPM = ?,MBL = ?,codigo = ?,capacidade_max = ?,qtd_ocupantes = ?,comp_lanca = ?,comp_cabo = ?,construcao = ?,diam_nominal = ?,comprimento = ?,tag = ?,quantidade = ?,dimensao_ad = ?,material = ?,localizacao = ?,id_cabeco = ?,embarcacao = ?,pressao_trabalho = ?,acionamento = ?,carga_ruptura = ?,grau = ?,diametro_corpo = ?,moitao_swl = ? where SEQ_EQUIPAMENTO = ?";
            final PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, equipamento.getNome());
            ps.setString(2, equipamento.getSituacao());
            ps.setString(3, equipamento.getSeqEmpresa());
            try {
                ps.setDate(4, new Date(equipamento.getDataCadastro().getTime()));
            }
            catch (NullPointerException e) {
                ps.setDate(4, null);
            }
            ps.setString(5, equipamento.getSeqParceiro());
            ps.setString(6, equipamento.getFmas());
            ps.setString(7, equipamento.getCarga());
            ps.setString(8, equipamento.getMspr());
            ps.setString(9, equipamento.getTipoAcionamento());
            ps.setString(10, equipamento.getCaboTracao());
            ps.setString(11, equipamento.getDiametro());
            ps.setString(12, equipamento.getPernas());
            ps.setString(13, equipamento.getDimensaoCla());
            ps.setString(14, equipamento.getPeso());
            ps.setString(15, equipamento.getTipoequipamento());
            ps.setString(16, equipamento.getModelo());
            ps.setString(17, equipamento.getAno());
            ps.setString(18, equipamento.getNserie());
            ps.setString(19, equipamento.getMserie());
            ps.setString(20, equipamento.getPotencia());
            ps.setString(21, equipamento.getRpm());
            ps.setString(22, equipamento.getMbl());
            ps.setString(23, equipamento.getCodigo());
            ps.setString(24, equipamento.getCapacidadeMax());
            ps.setString(25, equipamento.getQtdOcupantes());
            ps.setString(26, equipamento.getCompLanca());
            ps.setString(27, equipamento.getCompCabo());
            ps.setString(28, equipamento.getConstrucao());
            ps.setString(29, equipamento.getDiamNominal());
            ps.setString(30, equipamento.getComprimento());
            ps.setString(31, equipamento.getTag());
            ps.setString(32, equipamento.getQuantidade());
            ps.setString(33, equipamento.getDimensaoAd());
            ps.setString(34, equipamento.getMaterial());
            ps.setString(35, equipamento.getLocalizacao());
            ps.setString(36, equipamento.getIdCabeco());
            ps.setString(37, equipamento.getEmbarcacao());
            ps.setString(38, equipamento.getPressTrabalho());
            ps.setString(39, equipamento.getAcionamento());
            ps.setString(40, equipamento.getCargaRuptura());
            ps.setString(41, equipamento.getGrau());
            ps.setString(42, equipamento.getDiametroCorpo());
            ps.setString(43, equipamento.getMoitao());
            ps.setString(44, equipamento.getSeqEquipamento());
            ps.execute();
            ps.close();
        }
        catch (SQLException ex) {
            Logger.getLogger(EquipamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return equipamento;
    }
    
    public List<Equipamento> listar(final ClausulaWhere sClausula) {
        try {
            final Conexao conexao = new Conexao();
            final Connection conn = Conexao.getConnection();
            final String sql = "SELECT equipamento.*, \nequipamento_parceiro.*, \nparceiro.nome nomeCliente \n FROM EQUIPAMENTO inner join equipamento_parceiro on equipamento_parceiro.seq_equipamento = equipamento.seq_equipamento \n inner join parceiro on parceiro.seq_parceiro = equipamento_parceiro.seq_parceiro" + sClausula.montarsClausula();
            final List<Equipamento> listaEquipamento = new ArrayList<Equipamento>();
            final PreparedStatement ps = conn.prepareStatement(sql);
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                final Equipamento equipamento = new Equipamento();
                equipamento.setSeqEquipamento(rs.getString("SEQ_EQUIPAMENTO"));
                equipamento.setNome(rs.getString("NOME"));
                equipamento.setSituacao(rs.getString("SITUACAO"));
                equipamento.setSeqEmpresa(rs.getString("SEQ_EMPRESA"));
                equipamento.setDataCadastro((java.util.Date)rs.getDate("DATA_CADASTRO"));
                equipamento.setSeqParceiro(rs.getString("SEQ_PARCEIRO"));
                equipamento.setFmas(rs.getString("FMAS"));
                equipamento.setCarga(rs.getString("CARGA"));
                equipamento.setMspr(rs.getString("MSPR"));
                equipamento.setTipoAcionamento(rs.getString("TIPO_ACIONAMENTO"));
                equipamento.setCaboTracao(rs.getString("CABO_TRACAO"));
                equipamento.setDiametro(rs.getString("DIAMETRO"));
                equipamento.setPernas(rs.getString("PERNAS"));
                equipamento.setDimensaoCla(rs.getString("DIMENSAO_CLA"));
                equipamento.setPeso(rs.getString("PESO"));
                equipamento.setTipoequipamento(rs.getString("TIPO_EQUIPAMENTO"));
                equipamento.setModelo(rs.getString("MODELO"));
                equipamento.setAno(rs.getString("ANO"));
                equipamento.setNserie(rs.getString("NSERIE"));
                equipamento.setMserie(rs.getString("MSERIE"));
                equipamento.setPotencia(rs.getString("POTENCIA"));
                equipamento.setRpm(rs.getString("RPM"));
                equipamento.setMbl(rs.getString("MBL"));
                equipamento.setCodigo(rs.getString("CODIGO"));
                equipamento.setCapacidadeMax(rs.getString("CAPACIDADE_MAX"));
                equipamento.setQtdOcupantes(rs.getString("QTD_OCUPANTES"));
                equipamento.setCompLanca(rs.getString("COMP_LANCA"));
                equipamento.setCompCabo(rs.getString("COMP_CABO"));
                equipamento.setConstrucao(rs.getString("CONSTRUCAO"));
                equipamento.setDiamNominal(rs.getString("DIAM_NOMINAL"));
                equipamento.setComprimento(rs.getString("COMPRIMENTO"));
                equipamento.setTag(rs.getString("TAG"));
                equipamento.setQuantidade(rs.getString("QUANTIDADE"));
                equipamento.setDimensaoAd(rs.getString("DIMENSAO_AD"));
                equipamento.setMaterial(rs.getString("MATERIAL"));
                equipamento.setLocalizacao(rs.getString("LOCALIZACAO"));
                equipamento.setIdCabeco(rs.getString("ID_CABECO"));
                equipamento.setEmbarcacao(rs.getString("EMBARCACAO"));
                equipamento.setPressTrabalho(rs.getString("PRESSAO_TRABALHO"));
                equipamento.setAcionamento(rs.getString("ACIONAMENTO"));
                equipamento.setCargaRuptura(rs.getString("CARGA_RUPTURA"));
                equipamento.setGrau(rs.getString("GRAU"));
                equipamento.setDiametroCorpo(rs.getString("DIAMETRO_CORPO"));
                equipamento.setMoitao(rs.getString("MOITAO_SWL"));
                equipamento.setNomeCliente(rs.getString("nomeCliente"));
                listaEquipamento.add(equipamento);
            }
            ps.execute();
            ps.close();
            return listaEquipamento;
        }
        catch (SQLException ex) {
            Logger.getLogger(EquipamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    public List<Equipamento> listarPorDono(final ClausulaWhere sClausula) {
        try {
            final Conexao conexao = new Conexao();
            final Connection conn = Conexao.getConnection();
            final String sql = "SELECT * FROM EQUIPAMENTO \n  inner join documento_item_equipamento on documento_item_equipamento.SEQ_EQUIPAMENTO = equipamento.seq_equipamento" + sClausula.montarsClausula();
            System.out.println(sql);
            final List<Equipamento> listaEquipamento = new ArrayList<Equipamento>();
            final PreparedStatement ps = conn.prepareStatement(sql);
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                final Equipamento equipamento = new Equipamento();
                equipamento.setSeqEquipamento(rs.getString("SEQ_EQUIPAMENTO"));
                equipamento.setNome(rs.getString("NOME"));
                equipamento.setSituacao(rs.getString("SITUACAO"));
                equipamento.setSeqEmpresa(rs.getString("SEQ_EMPRESA"));
                equipamento.setDataCadastro((java.util.Date)rs.getDate("DATA_CADASTRO"));
                equipamento.setSeqParceiro(rs.getString("SEQ_PARCEIRO"));
                equipamento.setFmas(rs.getString("FMAS"));
                equipamento.setModelo(rs.getString("MODELO"));
                equipamento.setCarga(rs.getString("CARGA"));
                equipamento.setMspr(rs.getString("MSPR"));
                equipamento.setTipoAcionamento(rs.getString("TIPO_ACIONAMENTO"));
                equipamento.setCaboTracao(rs.getString("CABO_TRACAO"));
                equipamento.setDiametro(rs.getString("DIAMETRO"));
                equipamento.setPernas(rs.getString("PERNAS"));
                equipamento.setDimensaoCla(rs.getString("DIMENSAO_CLA"));
                equipamento.setPeso(rs.getString("PESO"));
                equipamento.setTipoequipamento(rs.getString("TIPO_EQUIPAMENTO"));
                equipamento.setLocalizacao(rs.getString("LOCALIZACAO"));
                listaEquipamento.add(equipamento);
            }
            ps.execute();
            ps.close();
            return listaEquipamento;
        }
        catch (SQLException ex) {
            Logger.getLogger(EquipamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    public List<Equipamento> listarParceiro(final ClausulaWhere sClausula) {
        try {
            final Conexao conexao = new Conexao();
            final Connection conn = Conexao.getConnection();
            final String sql = "SELECT equipamento.*, \nequipamento_parceiro.* \n FROM EQUIPAMENTO inner join equipamento_parceiro on equipamento_parceiro.seq_equipamento = equipamento.seq_equipamento \n inner join parceiro on parceiro.seq_parceiro = equipamento_parceiro.seq_parceiro " + sClausula.montarsClausula();
            System.out.println(sql);
            final List<Equipamento> listaEquipamento = new ArrayList<Equipamento>();
            final PreparedStatement ps = conn.prepareStatement(sql);
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                final Equipamento equipamento = new Equipamento();
                equipamento.setSeqEquipamento(rs.getString("SEQ_EQUIPAMENTO"));
                equipamento.setNome(rs.getString("NOME"));
                equipamento.setSituacao(rs.getString("SITUACAO"));
                equipamento.setSeqEmpresa(rs.getString("SEQ_EMPRESA"));
                equipamento.setDataCadastro((java.util.Date)rs.getDate("DATA_CADASTRO"));
                equipamento.setSeqParceiro(rs.getString("SEQ_PARCEIRO"));
                equipamento.setFmas(rs.getString("FMAS"));
                equipamento.setFmas(rs.getString("MODELO"));
                equipamento.setCarga(rs.getString("CARGA"));
                equipamento.setMspr(rs.getString("MSPR"));
                equipamento.setTipoAcionamento(rs.getString("TIPO_ACIONAMENTO"));
                equipamento.setCaboTracao(rs.getString("CABO_TRACAO"));
                equipamento.setDiametro(rs.getString("DIAMETRO"));
                equipamento.setPernas(rs.getString("PERNAS"));
                equipamento.setDimensaoCla(rs.getString("DIMENSAO_CLA"));
                equipamento.setPeso(rs.getString("PESO"));
                equipamento.setTipoequipamento(rs.getString("TIPO_EQUIPAMENTO"));
                equipamento.setTag(rs.getString("TAG"));
                equipamento.setLocalizacao(rs.getString("LOCALIZACAO"));
                listaEquipamento.add(equipamento);
            }
            ps.execute();
            ps.close();
            return listaEquipamento;
        }
        catch (SQLException ex) {
            Logger.getLogger(EquipamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    public boolean deletar(final Equipamento equipamento) {
        try {
            final Conexao conexao = new Conexao();
            final Connection conn = Conexao.getConnection();
            final String sql = "DELETE FROM EQUIPAMENTO WHERE SEQ_EQUIPAMENTO =  ? ";
            final PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, equipamento.getSeqEquipamento());
            ps.execute();
            ps.close();
            return true;
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}