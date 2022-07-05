/*     */ package DocumentoEtapa;
/*     */ 
/*     */ import ClausulaSQL.ClausulaWhere;
/*     */ import ClausulaSQL.GeneroCondicaoWhere;
/*     */ import ClausulaSQL.OperacaoCondicaoWhere;
/*     */ import ClausulaSQL.TipoCondicaoWhere;
/*     */ import Util.Situacao;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DocumentoEtapaService
/*     */ {
/*     */   public DocumentoEtapa salvar(DocumentoEtapa documentoEtapa)
/*     */   {
/*  22 */     DocumentoEtapaDAO dao = new DocumentoEtapaDAO();
/*  23 */     if (documentoEtapa.getSeqDocumentoEtapa() == null) {
/*  24 */       dao.inserir(documentoEtapa);
/*  25 */       return documentoEtapa;
/*     */     }
/*  27 */     dao.alterar(documentoEtapa);
/*  28 */     return documentoEtapa;
/*     */   }
/*     */   
/*     */   public List<DocumentoEtapa> listar(String pSeqEmpresa, String pString, Situacao pSituacao)
/*     */   {
/*  33 */     DocumentoEtapaDAO dao = new DocumentoEtapaDAO();
/*  34 */     List<DocumentoEtapa> listaDocumentoEtapa = new ArrayList();
/*  35 */     ClausulaWhere condicao = new ClausulaWhere();
/*     */     
/*  37 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "nome", GeneroCondicaoWhere.contem, pString, TipoCondicaoWhere.Texto);
/*  38 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(pSeqEmpresa), TipoCondicaoWhere.Numero);
/*     */     
/*  40 */     if (pSituacao == Situacao.ATIVO) {
/*  41 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "situacao", GeneroCondicaoWhere.igual, "ATIVO", TipoCondicaoWhere.Texto);
/*  42 */     } else if (pSituacao == Situacao.INATIVO) {
/*  43 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "situacao", GeneroCondicaoWhere.igual, "INATIVO", TipoCondicaoWhere.Texto);
/*     */     }
/*     */     
/*  46 */     listaDocumentoEtapa = dao.listar(condicao);
/*  47 */     return listaDocumentoEtapa;
/*     */   }
/*     */   
/*     */   public List<DocumentoEtapa> listarEtapa(String pSeqEmpresa, String pString, Situacao pSituacao) {
/*  51 */     DocumentoEtapaDAO dao = new DocumentoEtapaDAO();
/*  52 */     List<DocumentoEtapa> listaDocumentoEtapa = new ArrayList();
/*  53 */     ClausulaWhere condicao = new ClausulaWhere();
/*     */     
/*  55 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "seq_tipo_documento", GeneroCondicaoWhere.contem, pString, TipoCondicaoWhere.Texto);
/*  56 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(pSeqEmpresa), TipoCondicaoWhere.Numero);
              condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "nome", GeneroCondicaoWhere.diferente, "Cancelada", TipoCondicaoWhere.Texto);
/*     */     
/*  58 */     if (pSituacao == Situacao.ATIVO) {
/*  59 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "situacao", GeneroCondicaoWhere.igual, "ATIVO", TipoCondicaoWhere.Texto);
/*  60 */     } else if (pSituacao == Situacao.INATIVO) {
/*  61 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "situacao", GeneroCondicaoWhere.igual, "INATIVO", TipoCondicaoWhere.Texto);
/*     */     }
/*     */     
/*  64 */     listaDocumentoEtapa = dao.listar(condicao);
/*  65 */     return listaDocumentoEtapa;
/*     */   }
/*     */   
/*     */   public boolean deletar(DocumentoEtapa documentoEtapa) {
/*  69 */     DocumentoEtapaDAO dao = new DocumentoEtapaDAO();
/*  70 */     return dao.deletar(documentoEtapa);
/*     */   }
/*     */   
/*     */   public List<DocumentoEtapa> listarPorTipoDocumento(String pSeqTipoDocumento, Situacao pSituacao) {
/*  74 */     DocumentoEtapaDAO dao = new DocumentoEtapaDAO();
/*  75 */     List<DocumentoEtapa> listaTipoDocumentoEtapa = new ArrayList();
/*  76 */     ClausulaWhere condicao = new ClausulaWhere();
/*  77 */     if (pSeqTipoDocumento.equals("381")) {
/*  78 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "seq_tipo_documento", GeneroCondicaoWhere.igual, pSeqTipoDocumento, TipoCondicaoWhere.Texto);
/*  79 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.or, "seq_tipo_documento", GeneroCondicaoWhere.igual, "441", TipoCondicaoWhere.Texto);
/*     */       
/*  81 */       listaTipoDocumentoEtapa = dao.listar(condicao);
/*     */       
/*  83 */       if (pSituacao == Situacao.ATIVO) {
/*  84 */         condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "situacao", GeneroCondicaoWhere.igual, "ATIVO", TipoCondicaoWhere.Texto);
/*  85 */       } else if (pSituacao == Situacao.INATIVO) {
/*  86 */         condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "situacao", GeneroCondicaoWhere.igual, "INATIVO", TipoCondicaoWhere.Texto);
/*     */       }
/*     */     } else {
/*  89 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "seq_tipo_documento", GeneroCondicaoWhere.igual, pSeqTipoDocumento, TipoCondicaoWhere.Texto);
/*     */       
/*  91 */       if (pSituacao == Situacao.ATIVO) {
/*  92 */         condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "situacao", GeneroCondicaoWhere.igual, "ATIVO", TipoCondicaoWhere.Texto);
/*  93 */       } else if (pSituacao == Situacao.INATIVO) {
/*  94 */         condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "situacao", GeneroCondicaoWhere.igual, "INATIVO", TipoCondicaoWhere.Texto);
/*     */       }
/*     */       
/*  97 */       condicao.AdicionarCondicaoManual(" order by ordem");
/*     */       
/*  99 */       listaTipoDocumentoEtapa = dao.listar(condicao);
/*     */     }
/* 101 */     return listaTipoDocumentoEtapa;
/*     */   }
/*     */   
/*     */   public List<DocumentoEtapa> listarPorDocumento(String pSeqDocumento, Situacao pSituacao) {
/* 105 */     DocumentoEtapaDAO dao = new DocumentoEtapaDAO();
/* 106 */     List<DocumentoEtapa> listaTipoDocumentoEtapa = new ArrayList();
/* 107 */     ClausulaWhere condicao = new ClausulaWhere();
/*     */     
/* 109 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "seq_documento", GeneroCondicaoWhere.igual, String.valueOf(pSeqDocumento), TipoCondicaoWhere.Texto);
/*     */     
/* 111 */     if (pSituacao == Situacao.ATIVO) {
/* 112 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "situacao", GeneroCondicaoWhere.igual, "ATIVO", TipoCondicaoWhere.Texto);
/* 113 */     } else if (pSituacao == Situacao.INATIVO) {
/* 114 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "situacao", GeneroCondicaoWhere.igual, "INATIVO", TipoCondicaoWhere.Texto);
/*     */     }
/*     */     
/* 117 */     listaTipoDocumentoEtapa = dao.listar(condicao);
/* 118 */     return listaTipoDocumentoEtapa;
/*     */   }
/*     */   
/*     */   public String buscarIdPrincipal(String pSeqTipoDocumento) {
/* 122 */     DocumentoEtapaDAO dao = new DocumentoEtapaDAO();
/* 123 */     ClausulaWhere condicao = new ClausulaWhere();
/* 124 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "seq_tipo_documento", GeneroCondicaoWhere.igual, String.valueOf(pSeqTipoDocumento), TipoCondicaoWhere.Texto);
/* 125 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "principal", GeneroCondicaoWhere.igual, "Sim", TipoCondicaoWhere.Texto);
/*     */     
/* 127 */     return ((DocumentoEtapa)dao.listar(condicao).get(0)).getSeqDocumentoEtapa();
/*     */   }
/*     */   
/*     */   public DocumentoEtapa buscarPorId(String pSeqDocumentoEtapa) {
/* 131 */     DocumentoEtapaDAO dao = new DocumentoEtapaDAO();
/* 132 */     ClausulaWhere condicao = new ClausulaWhere();
/* 133 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "seq_documento_etapa", GeneroCondicaoWhere.igual, String.valueOf(pSeqDocumentoEtapa), TipoCondicaoWhere.Texto);
/*     */     
/* 135 */     return (DocumentoEtapa)dao.listar(condicao).get(0);
/*     */   }
/*     */ }

