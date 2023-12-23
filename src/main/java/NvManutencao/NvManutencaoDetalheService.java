/*    */ package NvManutencao;
/*    */ 
import NvPais.*;
/*    */ import ClausulaSQL.ClausulaWhere;
/*    */ import ClausulaSQL.GeneroCondicaoWhere;
/*    */ import ClausulaSQL.OperacaoCondicaoWhere;
/*    */ import ClausulaSQL.TipoCondicaoWhere;
/*    */ import Util.Situacao;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Date;
/*    */ import java.util.List;

/*    */ public class NvManutencaoDetalheService
/*    */ {
/*    */   public NvPais salvar(NvPais nvPais)
/*    */   {
/* 23 */     NvPaisDAO dao = new NvPaisDAO();
/* 24 */     if (nvPais.getSeqNvPais() == null) {
/* 25 */       nvPais.setDataCadastro(new Date());
/* 26 */       dao.inserir(nvPais);
/* 27 */       return nvPais;
/*    */     }
/* 29 */     dao.alterar(nvPais);
/* 30 */     return nvPais;
/*    */   }
/*    */   
/*    */   public List<NvPais> listar(String pSeqEmpresa, String pString, Situacao pSituacao)
/*    */   {
/* 35 */     NvPaisDAO dao = new NvPaisDAO();
/* 36 */     List<NvPais> listaNvPais = new ArrayList();
/* 37 */     ClausulaWhere condicao = new ClausulaWhere();
/*    */     
/* 39 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "nome", GeneroCondicaoWhere.contem, pString, TipoCondicaoWhere.Texto);
/* 40 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(pSeqEmpresa), TipoCondicaoWhere.Numero);
/*    */     
/* 42 */     if (pSituacao == Situacao.ATIVO) {
/* 43 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "situacao", GeneroCondicaoWhere.igual, "ATIVO", TipoCondicaoWhere.Texto);
/* 44 */     } else if (pSituacao == Situacao.INATIVO) {
/* 45 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "situacao", GeneroCondicaoWhere.igual, "INATIVO", TipoCondicaoWhere.Texto);
/*    */     }
/*    */     
/* 48 */     listaNvPais = dao.listar(condicao);
/* 49 */     return listaNvPais;
/*    */   }
/*    */   
/*    */   public boolean deletar(NvPais nvPais) {
/* 53 */     NvPaisDAO dao = new NvPaisDAO();
/* 54 */     return dao.deletar(nvPais);
/*    */   }
/*    */ }


/* Location:              /Users/diogo.lima/Documents/PEDIDO.jar!/NvPais/NvPaisService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */