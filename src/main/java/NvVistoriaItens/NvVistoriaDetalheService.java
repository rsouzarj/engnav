/*    */ package NvVistoriaItens;
/*    */ 
import NvCertificadoDetalhe.*;
/*    */ import ClausulaSQL.ClausulaWhere;
/*    */ import ClausulaSQL.GeneroCondicaoWhere;
/*    */ import ClausulaSQL.OperacaoCondicaoWhere;
/*    */ import ClausulaSQL.TipoCondicaoWhere;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;

/*    */ public class NvVistoriaDetalheService
/*    */ {
/*    */   public NvVistoriaDetalheVa salvar(NvVistoriaDetalheVa nvCertificadoDetalhe)
/*    */   {
/* 22 */     NvVistoriaDetalheDAO dao = new NvVistoriaDetalheDAO();
/* 23 */     if (nvCertificadoDetalhe.getSeqNvCertificadoDetalhe() == null) {
/* 24 */       return dao.inserir(nvCertificadoDetalhe);
/*    */     }
/* 26 */     return dao.alterar(nvCertificadoDetalhe);
/*    */   }
/*    */   
/*    */   public List<NvVistoriaDetalheVa> listar(String pSeqNvCertificado)
/*    */   {
/* 31 */     NvVistoriaDetalheDAO dao = new NvVistoriaDetalheDAO();
/* 32 */     List<NvVistoriaDetalheVa> listaNvCertificadoDetalhe = new ArrayList();
/* 33 */     ClausulaWhere condicao = new ClausulaWhere();
/*    */     
/* 35 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "seq_nv_certificado", GeneroCondicaoWhere.igual, String.valueOf(pSeqNvCertificado), TipoCondicaoWhere.Numero);
/*    */     
/* 37 */     listaNvCertificadoDetalhe = dao.listar(condicao);
/* 38 */     return listaNvCertificadoDetalhe;
/*    */   }
/*    */   
/*    */   public NvVistoriaDetalheVa deletar(NvVistoriaDetalheVa nvCertificadoDetalhe) {
/* 42 */     NvVistoriaDetalheDAO dao = new NvVistoriaDetalheDAO();
/* 43 */     nvCertificadoDetalhe.setLugar(null);
/* 44 */     nvCertificadoDetalhe.setDataRealizacao(null);
/* 45 */     nvCertificadoDetalhe.setStatus(null);
/* 46 */     nvCertificadoDetalhe.setPrazo(null);
/*    */     
/* 48 */     return dao.alterar(nvCertificadoDetalhe);
/*    */   }
/*    */   
/*    */   public NvVistoriaDetalheVa alterar(NvVistoriaDetalheVa nvCertificadoDetalhe) {
/* 52 */     NvVistoriaDetalheDAO dao = new NvVistoriaDetalheDAO();
/* 53 */     return dao.alterar(nvCertificadoDetalhe);
/*    */   }
/*    */ }

