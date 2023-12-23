/*    */ package Upload;
/*    */ 
/*    */ import ClausulaSQL.ClausulaWhere;
/*    */ import ClausulaSQL.GeneroCondicaoWhere;
/*    */ import ClausulaSQL.OperacaoCondicaoWhere;
/*    */ import ClausulaSQL.TipoCondicaoWhere;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Date;
/*    */ import java.util.List;



/*    */ public class UploadService
/*    */ {
/*    */   public Upload salvar(Upload upload)
/*    */   {
/* 23 */     UploadDAO dao = new UploadDAO();
/* 24 */     if (upload.getSeqUpload() == null) {
/* 25 */       upload.setDataCadastro(new Date());
/* 26 */       return dao.inserir(upload);
/*    */     }
/* 28 */     return dao.alterar(upload);
/*    */   }
/*    */   
/*    */   public List<Upload> listar(String pSeqEmpresa, String pSeq)
/*    */   {
/* 33 */     UploadDAO dao = new UploadDAO();
/* 34 */     List<Upload> listaUpload = new ArrayList();
/* 35 */     ClausulaWhere condicao = new ClausulaWhere();
/*    */     
/* 37 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "upload.seq_nv_vistoria", GeneroCondicaoWhere.igual, pSeq, TipoCondicaoWhere.Texto);
/* 38 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.or, "upload.seq_documento", GeneroCondicaoWhere.igual, pSeq, TipoCondicaoWhere.Texto);
             condicao.AdicionarCondicao(OperacaoCondicaoWhere.or, "upload.seq_nv_embarcacao", GeneroCondicaoWhere.igual, pSeq, TipoCondicaoWhere.Texto);
             condicao.AdicionarCondicao(OperacaoCondicaoWhere.or, "upload.seq_parceiro", GeneroCondicaoWhere.igual, pSeq, TipoCondicaoWhere.Texto);
             condicao.AdicionarCondicao(OperacaoCondicaoWhere.or, "upload.seq_certificado", GeneroCondicaoWhere.igual, pSeq, TipoCondicaoWhere.Texto);
/* 39 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "upload.seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(pSeqEmpresa), TipoCondicaoWhere.Numero);
             
/*    */     
/* 41 */     listaUpload = dao.listar(condicao);
/* 42 */     return listaUpload;
/*    */   }

/*    */   public List<Upload> listarP(String pSeqEmpresa, String pSeq)
/*    */   {
/* 33 */     UploadDAO dao = new UploadDAO();
/* 34 */     List<Upload> listaUpload = new ArrayList();
/* 35 */     ClausulaWhere condicao = new ClausulaWhere();
/*    */     

             condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "upload.seq_financeiro", GeneroCondicaoWhere.igual, pSeq, TipoCondicaoWhere.Texto);
/* 39 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "upload.seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(pSeqEmpresa), TipoCondicaoWhere.Numero);
             
/*    */     
/* 41 */     listaUpload = dao.listar(condicao);
/* 42 */     return listaUpload;
/*    */   }
/*    */   
/*    */   public List<Upload> listarFiltro(ClausulaWhere condicao) {
/* 46 */     UploadDAO dao = new UploadDAO();
/* 47 */     List<Upload> listaUpload = new ArrayList();
/* 48 */     listaUpload = dao.listar(condicao);
/* 49 */     return listaUpload;
/*    */   }
/*    */   
/*    */   public boolean deletar(Upload upload) {
/* 53 */     UploadDAO dao = new UploadDAO();
/* 54 */     return dao.deletar(upload);
/*    */   }
/*    */ }

