/*    */ package NvCertificado;
/*    */ 
/*    */ import ClausulaSQL.ClausulaWhere;
/*    */ import ClausulaSQL.GeneroCondicaoWhere;
/*    */ import ClausulaSQL.OperacaoCondicaoWhere;
/*    */ import ClausulaSQL.TipoCondicaoWhere;
/*    */ import Util.Situacao;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Date;
/*    */ import java.util.List;

/*    */ public class NvCertificadoService
/*    */ {
/*    */   public NvCertificado salvar(NvCertificado nvCertificado)
/*    */   {
/* 23 */     NvCertificadoDAO dao = new NvCertificadoDAO();
/* 24 */     if (nvCertificado.getSeqNvCertificado() == null) {
/* 25 */       nvCertificado.setDataCadastro(new Date());
/* 26 */       dao.inserir(nvCertificado);
/* 27 */       return nvCertificado;
/*    */     }
/* 29 */     dao.alterar(nvCertificado);
/* 30 */     return nvCertificado;
/*    */   }
/*    */   
/*    */   public List<NvCertificado> listar(String pSeqEmpresa, String pString, Situacao pSituacao)
/*    */   {
/* 35 */     NvCertificadoDAO dao = new NvCertificadoDAO();
/* 36 */     List<NvCertificado> listaNvCertificado = new ArrayList();
/* 37 */     ClausulaWhere condicao = new ClausulaWhere();
/*    */     
/* 39 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "NV_CERTIFICADO.seq_nv_certificado", GeneroCondicaoWhere.igual, pString, TipoCondicaoWhere.Numero);
/* 40 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "NV_CERTIFICADO.seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(pSeqEmpresa), TipoCondicaoWhere.Numero);
/*    */     
/* 42 */     if (pSituacao == Situacao.ATIVO) {
/* 43 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "NV_CERTIFICADO.situacao", GeneroCondicaoWhere.igual, "ATIVO", TipoCondicaoWhere.Texto);
/* 44 */     } else if (pSituacao == Situacao.INATIVO) {
/* 45 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "NV_CERTIFICADO.situacao", GeneroCondicaoWhere.igual, "INATIVO", TipoCondicaoWhere.Texto);
/*    */     }
/*    */     
/* 48 */     listaNvCertificado = dao.listar(condicao);
/* 49 */     return listaNvCertificado;
/*    */   }
/*    */   
/*    */   public List<NvCertificado> listarPorVistoria(String pSeqVistoria) {
/* 53 */     NvCertificadoDAO dao = new NvCertificadoDAO();
/* 54 */     List<NvCertificado> listaNvCertificado = new ArrayList();
/* 55 */     ClausulaWhere condicao = new ClausulaWhere();
/*    */     
/* 57 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "NV_CERTIFICADO.seq_nv_vistoria", GeneroCondicaoWhere.igual,String.valueOf(pSeqVistoria), TipoCondicaoWhere.Texto);
/*    */     
/* 59 */     listaNvCertificado = dao.listar(condicao);
/* 60 */     return listaNvCertificado;
/*    */   }
   
            public List<NvCertificado> listarC(String pSeqNvEmbarcacao)
/*    */   {
/* 31 */     NvCertificadoDAO dao = new NvCertificadoDAO();
/* 32 */     List<NvCertificado> listaNvCertificado = new ArrayList();
/* 33 */     ClausulaWhere condicao = new ClausulaWhere();
/*    */     
              condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "NV_CERTIFICADO.seq_nv_embarcacao", GeneroCondicaoWhere.igual,String.valueOf(pSeqNvEmbarcacao), TipoCondicaoWhere.Numero);

/* 37 */     listaNvCertificado = dao.listar(condicao);
/* 38 */     return listaNvCertificado;
/*    */   }
            
            
/*    */   public boolean deletar(NvCertificado nvCertificado) {
/* 64 */     NvCertificadoDAO dao = new NvCertificadoDAO();
/* 65 */     return dao.deletar(nvCertificado);
/*    */   }
/*    */   
/*    */   public List<NvCertificado> listar(ClausulaWhere sClausula) {
/* 69 */     NvCertificadoDAO dao = new NvCertificadoDAO();
/* 70 */     return dao.listar(sClausula);
/*    */   }
/*    */ }