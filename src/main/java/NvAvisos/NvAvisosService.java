package NvAvisos;
import ClausulaSQL.TipoCondicaoWhere;
import ClausulaSQL.GeneroCondicaoWhere;
import ClausulaSQL.OperacaoCondicaoWhere;
import ClausulaSQL.ClausulaWhere;
import ClausulaSQL.ClausulaWhere;
import ClausulaSQL.GeneroCondicaoWhere;
import ClausulaSQL.OperacaoCondicaoWhere;
import ClausulaSQL.TipoCondicaoWhere;
import NvCertificadoDetalhe.NvCertificadoDetalhe;
import NvCertificadoDetalhe.NvCertificadoDetalheDAO;
import java.util.List;
import java.util.Date;

public class NvAvisosService
{   
    public List<NvAvisos> listar(String seqEmpresa) {
        final NvAvisosDAO dao = new NvAvisosDAO();
        final ClausulaWhere condicao = new ClausulaWhere();
        condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "VW_BI_CERTIFICADO_AVISOS.seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(seqEmpresa), TipoCondicaoWhere.Numero);
        return (List<NvAvisos>)dao.listar(condicao);
    } 
    
     public List<NvAvisos> listar60(String seqEmpresa) {
        final NvAvisosDAO dao = new NvAvisosDAO();
        final ClausulaWhere condicao = new ClausulaWhere();
        condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "VW_BI_CERTIFICADO_AVISOS60.seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(seqEmpresa), TipoCondicaoWhere.Numero);
        return (List<NvAvisos>)dao.listar60(condicao);
    } 
     public List<NvAvisos> listar30(String seqEmpresa) {
        final NvAvisosDAO dao = new NvAvisosDAO();
        final ClausulaWhere condicao = new ClausulaWhere();
        condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "VW_BI_CERTIFICADO_AVISOS30.seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(seqEmpresa), TipoCondicaoWhere.Numero);
        return (List<NvAvisos>)dao.listar30(condicao);
    } 
    
       public List<NvAvisos> listarJanelas(String seqEmpresa) {
        final NvAvisosDAO dao = new NvAvisosDAO();
        final ClausulaWhere condicao = new ClausulaWhere();
        condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "VW_BI_JANELAS_AVISOS.seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(seqEmpresa), TipoCondicaoWhere.Numero);
        return (List<NvAvisos>)dao.listarjanelas(condicao);
    }
     
    public List<NvAvisos> listarJanelas30(String seqEmpresa) {
        final NvAvisosDAO dao = new NvAvisosDAO();
        final ClausulaWhere condicao = new ClausulaWhere();
        condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "VW_BI_JANELAS_AVISOS30.seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(seqEmpresa), TipoCondicaoWhere.Numero);
        return (List<NvAvisos>) dao.listarjanelas30(condicao);
    }
     
    public List<NvAvisos> listarJanelas60(String seqEmpresa) {
        final NvAvisosDAO dao = new NvAvisosDAO();
        final ClausulaWhere condicao = new ClausulaWhere();
        condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "VW_BI_JANELAS_AVISOS60.seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(seqEmpresa), TipoCondicaoWhere.Numero);
        return (List<NvAvisos>) dao.listarjanelas60(condicao);
    } 
     
    public List<NvAvisos> listarPEnvio(String seqCertificado) {
        final NvAvisosDAO dao = new NvAvisosDAO();
        final ClausulaWhere condicao = new ClausulaWhere();
        condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "VW_BI_JANELAS_AVISOS60.seq_nv_certificado", GeneroCondicaoWhere.igual, String.valueOf(seqCertificado), TipoCondicaoWhere.Numero);
        return (List<NvAvisos>) dao.listarjanelas(condicao);

    }

    public NvAvisos salvar(NvAvisos nvAvisos) {
        NvAvisosDAO dao = new NvAvisosDAO();       
        return dao.inserirAviso(nvAvisos);
    }
    
    

}
