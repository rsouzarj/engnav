/*    */ package NvCertificadoEquipamento;
/*    */ 

/*    */ import ClausulaSQL.ClausulaWhere;
/*    */ import ClausulaSQL.GeneroCondicaoWhere;
/*    */ import ClausulaSQL.OperacaoCondicaoWhere;
/*    */ import ClausulaSQL.TipoCondicaoWhere;
/*    */ import Util.Situacao;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NvCertificadoEquipamentoService
/*    */ {
/*    */   public NvCertificadoEquipamento salvar(NvCertificadoEquipamento nvCertificadoEquipamento)
/*    */   {
/* 23 */     NvCertificadoEquipamentoDAO dao = new NvCertificadoEquipamentoDAO();
/* 24 */     if (nvCertificadoEquipamento.getSeqCertificado()== null) {
/* 25 */       nvCertificadoEquipamento.setDataCadastro(new Date());
/* 26 */       dao.inserir(nvCertificadoEquipamento);
/* 27 */       return nvCertificadoEquipamento;
/*    */     }
/* 29 */     dao.alterar(nvCertificadoEquipamento);
/* 30 */     return nvCertificadoEquipamento;
/*    */   }
/*    */   
/*    */   public List<NvCertificadoEquipamento> listar(ClausulaWhere sClausula)
/*    */   {
/* 35 */     NvCertificadoEquipamentoDAO dao = new NvCertificadoEquipamentoDAO();
/* 36 */     return dao.listar(sClausula);
/*    */   }
/*    */   
/*    */   public List<NvCertificadoEquipamento> listar(String pSeqEmpresa, String pString, Situacao pSituacao) {
/* 40 */     NvCertificadoEquipamentoDAO dao = new NvCertificadoEquipamentoDAO();
/* 41 */     List<NvCertificadoEquipamento> listaNvCertificadoEquipamento = new ArrayList();
/* 42 */     ClausulaWhere condicao = new ClausulaWhere();
/*    */     
/* 44 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "NV_CERTIFICADO_EQUIPAMENTO.identificacao", GeneroCondicaoWhere.contem, pString, TipoCondicaoWhere.Texto);
/* 45 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "NV_CERTIFICADO_EQUIPAMENTO.seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(pSeqEmpresa), TipoCondicaoWhere.Numero);
/*    */     
/* 47 */     if (pSituacao == Situacao.ATIVO) {
/* 48 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "NV_CERTIFICADO_EQUIPAMENTO.situacao", GeneroCondicaoWhere.igual, "ATIVO", TipoCondicaoWhere.Texto);
/* 49 */     } else if (pSituacao == Situacao.INATIVO) {
/* 50 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "NV_CERTIFICADO_EQUIPAMENTO.situacao", GeneroCondicaoWhere.igual, "INATIVO", TipoCondicaoWhere.Texto);
/*    */     }
/*    */     
/* 53 */     listaNvCertificadoEquipamento = dao.listar(condicao);
/* 54 */     return listaNvCertificadoEquipamento;
/*    */   }
/*    */   
           public NvCertificadoEquipamento buscar(String pSeqNvCertificado) {
/* 51 */    NvCertificadoEquipamentoDAO dao = new NvCertificadoEquipamentoDAO();
/* 52 */     List<NvCertificadoEquipamento> listaNvCertificadoEquipamento = new ArrayList();
/* 53 */     ClausulaWhere condicao = new ClausulaWhere();
/*    */     
/* 55 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "nv_certificado_equipamento.seq_nv_certificado", GeneroCondicaoWhere.igual, String.valueOf(pSeqNvCertificado), TipoCondicaoWhere.Numero);
/*    */     
/* 57 */     listaNvCertificadoEquipamento = dao.listar(condicao);
/* 58 */     return (NvCertificadoEquipamento)listaNvCertificadoEquipamento.get(0);
/*    */   }  



/*    */   public boolean deletar(NvCertificadoEquipamento nvCertificadoEquipamento) {
/* 58 */     NvCertificadoEquipamentoDAO dao = new NvCertificadoEquipamentoDAO();
/* 59 */     return dao.deletar(nvCertificadoEquipamento);
/*    */   }
/*    */ }
