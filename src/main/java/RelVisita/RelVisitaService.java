 package RelVisita;
 
 import ClausulaSQL.ClausulaWhere;
 import ClausulaSQL.GeneroCondicaoWhere;
 import ClausulaSQL.OperacaoCondicaoWhere;
 import ClausulaSQL.TipoCondicaoWhere;
 import Util.Situacao;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;

 public class RelVisitaService
 {
   public RelVisita salvar(RelVisita relVisita)
   {
     RelVisitaDAO dao = new RelVisitaDAO();
     if (relVisita.getSeqRel()== null) {
       relVisita.setDataCadastro(new Date());
       return dao.inserir(relVisita);
     }
     return dao.alterar(relVisita);
   }
   
   public List<RelVisita> listar(String pSeqEmpresa, String pString)
   {
     RelVisitaDAO dao = new RelVisitaDAO();
     List<RelVisita> listaRelVisita = new ArrayList();
     ClausulaWhere condicao = new ClausulaWhere();
     
     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "cliente", GeneroCondicaoWhere.contem, pString, TipoCondicaoWhere.Texto);
     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(pSeqEmpresa), TipoCondicaoWhere.Numero);
         
     listaRelVisita = dao.listar(condicao);
     return listaRelVisita;
   }
   
   public boolean deletar(RelVisita relVisita) {
     RelVisitaDAO dao = new RelVisitaDAO();
     return dao.deletar(relVisita);
   }
 }
