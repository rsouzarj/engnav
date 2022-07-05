/*     */ package TipoDocumento;
/*     */ 
/*     */ import ClausulaSQL.ClausulaWhere;
/*     */ import ClausulaSQL.GeneroCondicaoWhere;
/*     */ import ClausulaSQL.OperacaoCondicaoWhere;
/*     */ import ClausulaSQL.TipoCondicaoWhere;
/*     */ import Util.Situacao;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TipoDocumentoService
/*     */ {
/*     */   public TipoDocumento salvar(TipoDocumento tipoDocumento)
/*     */   {
/*  23 */     TipoDocumentoDAO dao = new TipoDocumentoDAO();
/*  24 */     if (tipoDocumento.getSeqTipoDocumento() == null) {
/*  25 */       tipoDocumento.setProximoCodigo("1");
/*  26 */       dao.inserir(tipoDocumento);
/*  27 */       return tipoDocumento;
/*     */     }
/*  29 */     dao.alterar(tipoDocumento);
/*  30 */     return tipoDocumento;
/*     */   }
/*     */   
/*     */   public List<TipoDocumento> listar(String pSeqEmpresa, String pString, Situacao pSituacao, String pSeqUsuario)
/*     */   {
/*  35 */     TipoDocumentoDAO dao = new TipoDocumentoDAO();
/*  36 */     List<TipoDocumento> listaTipoProcesso = new ArrayList();
/*  37 */     ClausulaWhere condicao = new ClausulaWhere();
/*     */     
/*  39 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "TIPO_DOCUMENTO.nome", GeneroCondicaoWhere.contem, pString, TipoCondicaoWhere.Texto);
/*  40 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "TIPO_DOCUMENTO.seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(pSeqEmpresa), TipoCondicaoWhere.Numero);
/*  41 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "USUARIO_TIPO_DOCUMENTO.seq_usuario", GeneroCondicaoWhere.igual, String.valueOf(pSeqUsuario), TipoCondicaoWhere.Numero);
/*     */     
/*  43 */     if (pSituacao == Situacao.ATIVO) {
/*  44 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "TIPO_DOCUMENTO.situacao", GeneroCondicaoWhere.igual, "ATIVO", TipoCondicaoWhere.Texto);
/*  45 */     } else if (pSituacao == Situacao.INATIVO) {
/*  46 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "TIPO_DOCUMENTO.situacao", GeneroCondicaoWhere.igual, "INATIVO", TipoCondicaoWhere.Texto);
/*     */     }
/*     */     
/*  49 */     listaTipoProcesso = dao.listar(condicao);
/*  50 */     return listaTipoProcesso;
/*     */   }
/*     */   
/*  53 */   public List<TipoDocumento> listarEscopo(String pSeqEmpresa, String pString, Situacao pSituacao, String pSeqUsuario) { TipoDocumentoDAO dao = new TipoDocumentoDAO();
/*  54 */     List<TipoDocumento> listaTipoProcesso = new ArrayList();
/*  55 */     ClausulaWhere condicao = new ClausulaWhere();
/*     */     
/*  57 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "TIPO_DOCUMENTO.nome", GeneroCondicaoWhere.contem, pString, TipoCondicaoWhere.Texto);
/*  58 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "TIPO_DOCUMENTO.seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(pSeqEmpresa), TipoCondicaoWhere.Numero);
/*  59 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "USUARIO_TIPO_DOCUMENTO.seq_usuario", GeneroCondicaoWhere.igual, String.valueOf(pSeqUsuario), TipoCondicaoWhere.Numero);
/*  60 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "tipo_documento.seq_tipo_documento", GeneroCondicaoWhere.diferente, "381", TipoCondicaoWhere.Texto);
/*  61 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "tipo_documento.seq_tipo_documento", GeneroCondicaoWhere.diferente, "301", TipoCondicaoWhere.Texto);
/*     */     
/*  63 */     if (pSituacao == Situacao.ATIVO) {
/*  64 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "TIPO_DOCUMENTO.situacao", GeneroCondicaoWhere.igual, "ATIVO", TipoCondicaoWhere.Texto);
/*  65 */     } else if (pSituacao == Situacao.INATIVO) {
/*  66 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "TIPO_DOCUMENTO.situacao", GeneroCondicaoWhere.igual, "INATIVO", TipoCondicaoWhere.Texto);
/*     */     }
/*     */     
/*  69 */     listaTipoProcesso = dao.listar(condicao);
/*  70 */     return listaTipoProcesso;
/*     */   }
/*     */   
/*     */   public List<TipoDocumento> listarTodos(String pSeqEmpresa, String pString) {
/*  74 */     TipoDocumentoDAO dao = new TipoDocumentoDAO();
/*  75 */     List<TipoDocumento> listaTipoProcesso = new ArrayList();
/*  76 */     ClausulaWhere condicao = new ClausulaWhere();
/*     */     
/*  78 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "TIPO_DOCUMENTO.nome", GeneroCondicaoWhere.contem, pString, TipoCondicaoWhere.Texto);
/*  79 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "TIPO_DOCUMENTO.seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(pSeqEmpresa), TipoCondicaoWhere.Numero);
/*  80 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "TIPO_DOCUMENTO.seq_tipo_documento", GeneroCondicaoWhere.diferente, String.valueOf("441"), TipoCondicaoWhere.Numero);
/*     */     
/*     */ 
/*  83 */     listaTipoProcesso = dao.listarTodos(condicao);
/*  84 */     return listaTipoProcesso;
/*     */   }
/*     */   
/*     */   public TipoDocumento buscarPorId(String pSeqTipoProcesso) {
/*  88 */     TipoDocumentoDAO dao = new TipoDocumentoDAO();
/*  89 */     List<TipoDocumento> listaTipoProcesso = new ArrayList();
/*  90 */     ClausulaWhere condicao = new ClausulaWhere();
/*     */     
/*  92 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "tipo_documento.seq_tipo_documento", GeneroCondicaoWhere.igual, String.valueOf(pSeqTipoProcesso), TipoCondicaoWhere.Numero);
/*  93 */     listaTipoProcesso = dao.listar(condicao);
/*  94 */     return (TipoDocumento)listaTipoProcesso.get(0);
/*     */   }
/*     */   
/*     */   public boolean deletar(TipoDocumento tipoProcesso) {
/*  98 */     TipoDocumentoDAO dao = new TipoDocumentoDAO();
/*  99 */     return dao.deletar(tipoProcesso);
/*     */   }
/*     */   
/*     */   public List<TipoDocumento> montarMenu(String pSeqEmpresa, String pSeqUsuario) {
/* 103 */     TipoDocumentoDAO dao = new TipoDocumentoDAO();
/* 104 */     List<TipoDocumento> listaTipoProcesso = new ArrayList();
/* 105 */     ClausulaWhere condicao = new ClausulaWhere();
/* 106 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "TIPO_DOCUMENTO.SITUACAO", GeneroCondicaoWhere.igual, "ATIVO", TipoCondicaoWhere.Texto);
/* 107 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "TIPO_DOCUMENTO.seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(pSeqEmpresa), TipoCondicaoWhere.Numero);
/* 108 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "USUARIO_TIPO_DOCUMENTO.seq_usuario", GeneroCondicaoWhere.igual, String.valueOf(pSeqUsuario), TipoCondicaoWhere.Numero);
/* 109 */     condicao.AdicionarCondicaoManual(" order by TIPO_DOCUMENTO.ordem");
/* 110 */     listaTipoProcesso = dao.listar(condicao);
/* 111 */     return listaTipoProcesso;
/*     */   }
/*     */   
/*     */   public HashMap<String, String> listarContador(String pSeqEmpresa, String pSeqUsuario) {
/* 115 */     TipoDocumentoDAO dao = new TipoDocumentoDAO();
/* 116 */     return dao.listarContador(pSeqEmpresa, pSeqUsuario);
/*     */   }
/*     */ }

