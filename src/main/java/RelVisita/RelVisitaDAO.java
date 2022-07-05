 package RelVisita;


import ClausulaSQL.ClausulaWhere;
import Util.Conexao;
import Util.Sequence;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RelVisitaDAO
{
  public RelVisita inserir(RelVisita relVisita)
  {
    try
    {
      String seq = Sequence.buscarNumeroSequence("SEQ_REL_VISITA");
      relVisita.setSeqRel(seq);
      Conexao conexao = new Conexao();
      Connection conn = Conexao.getConnection();
      String sql = "insert into REL_VISITA (SEQ_REL,SEQ_USUARIO, CLIENTE, ASSUNTO, DESCRICAO, SEQ_EMPRESA,DT_INICIO,DT_FIM,DATA_CADASTRO, SEQ_PARCEIRO, RESPONSAVEL) values  (?,?,?,?,?,?,?,?,?,?,?)";
      


      PreparedStatement ps = conn.prepareStatement(sql);
      
      ps.setObject(1, relVisita.getSeqRel());
      ps.setObject(2, relVisita.getSeqUsuario());
      ps.setObject(3, relVisita.getCliente());
      ps.setObject(4, relVisita.getAssunto());
      ps.setObject(5, relVisita.getDescricao());
      ps.setObject(6, relVisita.getSeqEmpresa());
      try {
      ps.setDate(7, new java.sql.Date(relVisita.getDtInicio().getTime()));
      } catch (NullPointerException e) {
      ps.setDate(7, null);
      }
      try {
      ps.setDate(8, new java.sql.Date(relVisita.getDtFim().getTime()));
      } catch (NullPointerException e) {
      ps.setDate(8, null);
      }
      try {
      ps.setDate(9, new java.sql.Date(relVisita.getDataCadastro().getTime()));
      } catch (NullPointerException e) {
      ps.setDate(9, null);
      }
      ps.setObject(10, relVisita.getSeqParceiro());
      ps.setObject(11, relVisita.getResponsavel());
      ps.execute();
      ps.close();
    }
    catch (SQLException ex) {
      Logger.getLogger(RelVisitaDAO.class.getName()).log(Level.SEVERE, null, ex);
      System.out.println(ex.getMessage());
    }
    return relVisita;
  }

  public RelVisita alterar(RelVisita relVisita) {
    try {
      Conexao conexao = new Conexao();
      Connection conn = Conexao.getConnection();
      String sql = "update REL_VISITA set SEQ_USUARIO = ?,SEQ_PARCEIRO = ?,ASSUNTO = ?,DESCRICAO=?,DT_INICIO = ?,DT_FIM = ?, CLIENTE = ?, RESPONSAVEL = ?  where SEQ_REL = ?";
      
      PreparedStatement ps = conn.prepareStatement(sql);
      
      ps.setObject(1, relVisita.getSeqUsuario());
      ps.setObject(2, relVisita.getSeqParceiro());
      ps.setObject(3, relVisita.getAssunto());
      ps.setObject(4, relVisita.getDescricao());
      try {
      ps.setDate(5, new java.sql.Date(relVisita.getDtInicio().getTime()));
      } catch (NullPointerException e) {
      ps.setDate(5, null);
      }
      try {
      ps.setDate(6, new java.sql.Date(relVisita.getDtFim().getTime()));
      } catch (NullPointerException e) {
      ps.setDate(6, null);
      }
      ps.setString(7, relVisita.getCliente());
      ps.setObject(8, relVisita.getResponsavel());
      ps.setString(9, relVisita.getSeqRel());
      
      ps.execute();
      ps.close();
    }
    catch (SQLException ex) {
      Logger.getLogger(RelVisitaDAO.class.getName()).log(Level.SEVERE, null, ex);
      System.out.println(ex.getMessage());
    }
    
    return relVisita;
  }

  public List<RelVisita> listar(ClausulaWhere sClausula) {
    try {
      Conexao conexao = new Conexao();
      Connection conn = Conexao.getConnection();
      String sql = "SELECT * FROM REL_VISITA " + sClausula.montarsClausula();
      
      List<RelVisita> listaAgenda = new ArrayList();
      PreparedStatement ps = conn.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
      
      while (rs.next()) {
        RelVisita relVisita = new RelVisita();
        relVisita.setSeqRel(rs.getString("SEQ_REL"));
        relVisita.setSeqUsuario(rs.getString("SEQ_USUARIO"));
        relVisita.setSeqParceiro(rs.getString("SEQ_PARCEIRO"));
        relVisita.setAssunto(rs.getString("ASSUNTO"));
        relVisita.setDescricao(rs.getString("DESCRICAO"));
        relVisita.setDtInicio(rs.getTimestamp("DT_INICIO"));
        relVisita.setDtFim(rs.getTimestamp("DT_FIM"));
        relVisita.setDataCadastro(rs.getDate("DATA_CADASTRO"));
        relVisita.setCliente(rs.getString("CLIENTE"));
        relVisita.setResponsavel(rs.getString("RESPONSAVEL"));
        listaAgenda.add(relVisita);
      }
      
      ps.execute();
      ps.close();
      
      return listaAgenda;
    } catch (SQLException ex) {
      Logger.getLogger(RelVisitaDAO.class.getName()).log(Level.SEVERE, null, ex);
      System.out.println(ex.getMessage()); }
    return null;
  }
  
  public boolean deletar(RelVisita relVisita)
  {
    try {
      Conexao conexao = new Conexao();
      Connection conn = Conexao.getConnection();
      String sql = "DELETE FROM REL_VISITA WHERE SEQ_REL =  ? ";
      
      PreparedStatement ps = conn.prepareStatement(sql);
      
      ps.setString(1, relVisita.getSeqRel());
      
      ps.execute();
      ps.close();
      
      return true;
    } catch (SQLException ex) {
      System.out.println(ex.getMessage()); }
    return false;
  }
	}