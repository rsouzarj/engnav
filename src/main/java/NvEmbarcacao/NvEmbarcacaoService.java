/*    */ package NvEmbarcacao;
/*    */ 
/*    */ import ClausulaSQL.ClausulaWhere;
/*    */ import ClausulaSQL.GeneroCondicaoWhere;
/*    */ import ClausulaSQL.OperacaoCondicaoWhere;
/*    */ import ClausulaSQL.TipoCondicaoWhere;
         import NvCertificado.NvCertificado;
         import NvCertificado.NvCertificadoDAO;
         import NvVistoria.NvVistoria;
         import NvVistoria.NvVistoriaDAO;
         import NvLicenca.NvLicenca;
         import NvLicenca.NvLicencaDAO;
         import NvCertificadoDetalhe.NvCertificadoDetalhe;
         import NvCertificadoDetalhe.NvCertificadoDetalheDAO;
         import Upload.Upload;
         import Upload.UploadDAO;
/*    */ import Util.Situacao;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Date;
/*    */ import java.util.List;
 
/*    */ public class NvEmbarcacaoService
/*    */ {
/*    */   public NvEmbarcacao salvar(NvEmbarcacao nvEmbarcacao)
/*    */   {
/* 25 */     NvEmbarcacaoDAO dao = new NvEmbarcacaoDAO();
/* 26 */     if (nvEmbarcacao.getSeqNvEmbarcacao() == null) {
/* 27 */       nvEmbarcacao.setDataCadastro(new Date());
/* 28 */       return dao.inserir(nvEmbarcacao);
/*    */     }
/* 30 */     return dao.alterar(nvEmbarcacao);
/*    */   }
/*    */   
/*    */   public List<NvEmbarcacao> listar(String pSeqEmpresa, String pString, Situacao pSituacao)
/*    */   {
/* 35 */     NvEmbarcacaoDAO dao = new NvEmbarcacaoDAO();
/* 36 */     List<NvEmbarcacao> listaNvEmbarcacao = new ArrayList();
/* 37 */     ClausulaWhere condicao = new ClausulaWhere();
/*    */     
/* 39 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "nv_embarcacao.nome", GeneroCondicaoWhere.contem, pString, TipoCondicaoWhere.Texto);
/* 40 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "nv_embarcacao.seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(pSeqEmpresa), TipoCondicaoWhere.Numero);
/*    */     
/* 42 */     if (pSituacao == Situacao.ATIVO) {
/* 43 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "nv_embarcacao.situacao", GeneroCondicaoWhere.igual, "ATIVO", TipoCondicaoWhere.Texto);
/* 44 */     } else if (pSituacao == Situacao.INATIVO) {
/* 45 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "nv_embarcacao.situacao", GeneroCondicaoWhere.igual, "INATIVO", TipoCondicaoWhere.Texto);
/*    */     }
/*    */     
/* 48 */     listaNvEmbarcacao = dao.listar(condicao);
/* 49 */     return listaNvEmbarcacao;
/*    */   }

/*    */   public List<NvEmbarcacao> listarPCliente(String pSeqUsuario, String pString, Situacao pSituacao)
/*    */   {
/* 35 */     NvEmbarcacaoDAO dao = new NvEmbarcacaoDAO();
/* 36 */     List<NvEmbarcacao> listaNvEmbarcacao = new ArrayList();
/* 37 */     ClausulaWhere condicao = new ClausulaWhere();
/*    */     
/* 39 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "nv_embarcacao.nome", GeneroCondicaoWhere.contem, pString, TipoCondicaoWhere.Texto);
/* 40 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "nv_embarcacao_usuario.seq_usuario", GeneroCondicaoWhere.igual, String.valueOf(pSeqUsuario), TipoCondicaoWhere.Numero);
/*    */     
/* 42 */     if (pSituacao == Situacao.ATIVO) {
/* 43 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "nv_embarcacao.situacao", GeneroCondicaoWhere.igual, "ATIVO", TipoCondicaoWhere.Texto);
/* 44 */     } else if (pSituacao == Situacao.INATIVO) {
/* 45 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "nv_embarcacao.situacao", GeneroCondicaoWhere.igual, "INATIVO", TipoCondicaoWhere.Texto);
/*    */     }
/*    */     
/* 48 */     listaNvEmbarcacao = dao.listarPCliente(condicao);
/* 49 */     return listaNvEmbarcacao;
/*    */   }
/*    */   
/*    */   public NvEmbarcacao buscar(String pSeqNvEmbarcacao) {
/* 53 */     NvEmbarcacaoDAO dao = new NvEmbarcacaoDAO();
/* 54 */     List<NvEmbarcacao> listaNvEmbarcacao = new ArrayList();
/* 55 */     ClausulaWhere condicao = new ClausulaWhere();
/*    */     
/* 57 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "nv_embarcacao.seq_nv_embarcacao", GeneroCondicaoWhere.igual, String.valueOf(pSeqNvEmbarcacao), TipoCondicaoWhere.Numero);
/*    */     
/* 59 */     listaNvEmbarcacao = dao.listar(condicao);
/* 60 */     return (NvEmbarcacao)listaNvEmbarcacao.get(0);
/*    */   }

            public List<NvCertificado> listarC(String pSeqNvEmbarcacao)
/*    */   {
/* 31 */     NvCertificadoDAO dao = new NvCertificadoDAO();
/* 32 */     List<NvCertificado> listaNvCertificado = new ArrayList();
/* 33 */     ClausulaWhere condicao = new ClausulaWhere();
/*    */     
/* 35 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "NV_CERTIFICADO.seq_nv_embarcacao", GeneroCondicaoWhere.igual, String.valueOf(pSeqNvEmbarcacao), TipoCondicaoWhere.Numero);
/*    */     
/* 37 */     listaNvCertificado = dao.listar(condicao);
/* 38 */     return  listaNvCertificado;
/*    */   }
            
            public List<NvVistoria> listarV(String pSeqNvEmbarcacao)
/*    */   {
/* 31 */     NvVistoriaDAO dao = new NvVistoriaDAO();
/* 32 */     List<NvVistoria> listaNvVistoria = new ArrayList();
/* 33 */     ClausulaWhere condicao = new ClausulaWhere();
/*    */     
              condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "NV_VISTORIA.seq_nv_embarcacao", GeneroCondicaoWhere.igual,String.valueOf(pSeqNvEmbarcacao), TipoCondicaoWhere.Numero);

/* 37 */     listaNvVistoria = dao.listar(condicao);
/* 38 */     return listaNvVistoria;
                        }
            
            public List<NvLicenca> listarL(String pSeqNvEmbarcacao)
/*    */   {
/* 31 */     NvLicencaDAO dao = new NvLicencaDAO();
/* 32 */     List<NvLicenca> listaLicenca = new ArrayList();
/* 33 */     ClausulaWhere condicao = new ClausulaWhere();
/*    */     
              condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "NV_LICENCA.seq_nv_embarcacao", GeneroCondicaoWhere.igual,String.valueOf(pSeqNvEmbarcacao), TipoCondicaoWhere.Numero);

/* 37 */     listaLicenca = dao.listar(condicao);
/* 38 */     return listaLicenca;
                        }            
         
            public List<Upload> listarU(String pSeqEmpresa,String pSeqNvEmbarcacao)
/*    */   {
/* 31 */     UploadDAO dao = new UploadDAO();
/* 32 */     List<Upload> listaUpload = new ArrayList();
/* 33 */     ClausulaWhere condicao = new ClausulaWhere();
/*    */     
              condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "UPLOAD.seq_nv_embarcacao", GeneroCondicaoWhere.igual,String.valueOf(pSeqNvEmbarcacao), TipoCondicaoWhere.Numero);
              condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "upload.seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(pSeqEmpresa), TipoCondicaoWhere.Numero);
/* 37 */     listaUpload = dao.listar(condicao);
/* 38 */     return listaUpload;
                        }
            
            public List<NvCertificadoDetalhe> listarD(String pSeqNvEmbarcacao)
/*    */   {
/* 31 */     NvCertificadoDetalheDAO dao = new NvCertificadoDetalheDAO();
/* 32 */     List<NvCertificadoDetalhe> listaCertificadoDetalhe = new ArrayList();
/* 33 */     ClausulaWhere condicao = new ClausulaWhere();
/*    */     
              condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "VW_BI_LISTA_CONVALIDACAO.seq_nv_embarcacao", GeneroCondicaoWhere.igual,String.valueOf(pSeqNvEmbarcacao),TipoCondicaoWhere.Numero);

/* 37 */     listaCertificadoDetalhe = dao.listarE(condicao);
/* 38 */     return listaCertificadoDetalhe;
                        }            
      
/*    */   
/*    */   public boolean deletar(NvEmbarcacao nvEmbarcacao) {
/* 64 */     NvEmbarcacaoDAO dao = new NvEmbarcacaoDAO();
/* 65 */     return dao.deletar(nvEmbarcacao);
/*    */   }
/*    */ }

