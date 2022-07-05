/*    */ package NvCertificadoDetalhe;
/*    */ 
/*    */ import ClausulaSQL.ClausulaWhere;
/*    */ import ClausulaSQL.GeneroCondicaoWhere;
/*    */ import ClausulaSQL.OperacaoCondicaoWhere;
/*    */ import ClausulaSQL.TipoCondicaoWhere;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NvCertificadoDetalheService
/*    */ {
/*    */   public NvCertificadoDetalhe salvar(NvCertificadoDetalhe nvCertificadoDetalhe)
/*    */   {
/* 22 */     NvCertificadoDetalheDAO dao = new NvCertificadoDetalheDAO();
/* 23 */     if (nvCertificadoDetalhe.getSeqNvCertificadoDetalhe() == null) {
/* 24 */       return dao.inserir(nvCertificadoDetalhe);
/*    */     }
/* 26 */     return dao.alterar(nvCertificadoDetalhe);
/*    */   }
/*    */   
/*    */   public List<NvCertificadoDetalhe> listar(String pSeqNvCertificado)
/*    */   {
/* 31 */     NvCertificadoDetalheDAO dao = new NvCertificadoDetalheDAO();
/* 32 */     List<NvCertificadoDetalhe> listaNvCertificadoDetalhe = new ArrayList();
/* 33 */     ClausulaWhere condicao = new ClausulaWhere();
/*    */     
/* 35 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "seq_nv_certificado", GeneroCondicaoWhere.igual, String.valueOf(pSeqNvCertificado), TipoCondicaoWhere.Numero);
/*    */     
/* 37 */     listaNvCertificadoDetalhe = dao.listar(condicao);
/* 38 */     return listaNvCertificadoDetalhe;
/*    */   }
/*    */   
/*    */   public NvCertificadoDetalhe deletar(NvCertificadoDetalhe nvCertificadoDetalhe) {
/* 42 */     NvCertificadoDetalheDAO dao = new NvCertificadoDetalheDAO();
/* 43 */     nvCertificadoDetalhe.setLugar(null);
/* 44 */     nvCertificadoDetalhe.setDataRealizacao(null);
/* 45 */     nvCertificadoDetalhe.setStatus(null);
/* 46 */     nvCertificadoDetalhe.setPrazo(null);
/*    */     
/* 48 */     return dao.alterar(nvCertificadoDetalhe);
/*    */   }
/*    */   
/*    */   public NvCertificadoDetalhe alterar(NvCertificadoDetalhe nvCertificadoDetalhe) {
/* 52 */     NvCertificadoDetalheDAO dao = new NvCertificadoDetalheDAO();
/* 53 */     return dao.alterar(nvCertificadoDetalhe);
/*    */   }
/*    */ }

