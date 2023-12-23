/*    */ package NvManutencao;
/*    */ 
import NvManutencao.*;
/*    */ import ClausulaSQL.ClausulaWhere;
/*    */ import ClausulaSQL.GeneroCondicaoWhere;
/*    */ import ClausulaSQL.OperacaoCondicaoWhere;
/*    */ import ClausulaSQL.TipoCondicaoWhere;
/*    */ import Util.Situacao;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Date;
/*    */ import java.util.List;
 
/*    */ public class NvManutencaoService
/*    */ {
/*    */   public NvManutencao salvar(NvManutencao nvManutencao)
/*    */   {
/* 23 */     NvManutencaoDAO dao = new NvManutencaoDAO();
/* 24 */     if (nvManutencao.getSeqmanutencao() == null) {
/* 25 */         dao.inserir(nvManutencao);
/* 27 */       return nvManutencao;
/*    */     }
/* 29 */     dao.alterar(nvManutencao);
/* 30 */     return nvManutencao;
/*    */   }
/*    */   
/*    */   public List<NvManutencao> listar(String pSeqEmpresa, String pString)
/*    */   {
/* 35 */     NvManutencaoDAO dao = new NvManutencaoDAO();
/* 36 */     List<NvManutencao> listaNvManutencao = new ArrayList();
/* 37 */     ClausulaWhere condicao = new ClausulaWhere();
/*    */     
/* 39 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "nome", GeneroCondicaoWhere.contem, pString, TipoCondicaoWhere.Texto);
/* 40 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(pSeqEmpresa), TipoCondicaoWhere.Numero);
   
/* 48 */     listaNvManutencao = dao.listar(condicao);
/* 49 */     return listaNvManutencao;
/*    */   }
/*    */   
/*    */   public boolean deletar(NvManutencao nvManutencao) {
/* 53 */     NvManutencaoDAO dao = new NvManutencaoDAO();
/* 54 */     return dao.deletar(nvManutencao);
/*    */   }
/*    */ }

