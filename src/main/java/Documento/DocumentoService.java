/*     */ package Documento;
/*     */ 
/*     */ import ClausulaSQL.ClausulaWhere;
/*     */ import ClausulaSQL.GeneroCondicaoWhere;
/*     */ import ClausulaSQL.OperacaoCondicaoWhere;
/*     */ import ClausulaSQL.TipoCondicaoWhere;
/*     */ import DocumentoEtapa.DocumentoEtapaService;
/*     */ import DocumentoItemEmbarcacao.DocumentoItemEmbarcacaoService;
/*     */ import DocumentoItemEquipamento.DocumentoItemEquipamentoService;
/*     */ import DocumentoItemMaterial.DocumentoItemMaterialService;
/*     */ import DocumentoItemServico.DocumentoItemServicoService;
/*     */ import DocumentoOcorrencia.DocumentoOcorrenciaService;
/*     */ import Empresa.Empresa;
/*     */ import Empresa.EmpresaService;
/*     */ import Usuario.Usuario;
/*     */ import Usuario.UsuarioService;
/*     */ import Util.Util;
import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
import javax.mail.MessagingException;
/*     */ import javax.mail.Multipart;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DocumentoService
/*     */ {
/*     */   public Documento salvar(Documento pDocumento)
/*     */   {
/*  35 */     Documento retorno = new Documento();
/*  36 */     EmpresaService empresaService = new EmpresaService();
/*  37 */     Empresa empresa = empresaService.buscarEmpresaPorID(pDocumento.getSeqEmpresa());
/*     */     
/*  39 */     if (empresa.getIntegracao().equals("CROSS")) {
/*  40 */       DocumentoDAO dao = new DocumentoDAO();
/*  41 */       if (pDocumento.getSeqDocumento() == null)
/*     */      {
/*  43 */         if ((pDocumento.getTipoDocumento() != null) && (pDocumento.getTipoDocumento().equals("Boletim de Medição"))) {
/*  44 */           Documento dono = buscarPorID(pDocumento.getSeqEmpresa(), pDocumento.getSeqDocumentoDono());
/*  45 */           if (dono.getEtapa().equals("Rejeitado")) {
/*  46 */             pDocumento.setSeqDocumentoEtapa("443");
/*     */           }
/*     */           
/*     */         }
/*     */         else
/*     */         {
/*  52 */           DocumentoEtapaService documentoEtapaService = new DocumentoEtapaService();
/*  53 */           pDocumento.setSeqDocumentoEtapa(documentoEtapaService.buscarIdPrincipal(pDocumento.getSeqTipoDocumento()));
/*     */         }
/*     */         
/*     */ 
/*  57 */         pDocumento.setDataCadastro(new Date());
/*  58 */         dao.inserir(pDocumento);
/*     */         
/*  60 */         retorno = pDocumento;
/*     */       } else {
/*  62 */         dao.alterar(pDocumento);
/*  63 */         retorno = pDocumento;
/*     */       }
/*     */     }
/*  66 */        
/*     */ 
/*  72 */     return retorno;
/*     */   }
/*     */   
/*     */   public boolean atualizarSeqDocDonoFilho(String seqDocDono, String seqDocDonoNovo) {
/*  76 */     DocumentoDAO dao = new DocumentoDAO();
/*     */     
/*  78 */     boolean retorno = dao.atualizar(seqDocDono, seqDocDonoNovo);
/*  79 */     return retorno;
/*     */   }
/*     */   
/*     */   public List<Documento> listarPorEtapa(String pSeqDocumentoEtapa) {
/*  83 */     DocumentoDAO dao = new DocumentoDAO();
/*  84 */     List<Documento> listaDocumento = new ArrayList();
/*  85 */     ClausulaWhere condicao = new ClausulaWhere();
/*     */     
/*  87 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "documento.seq_documento_etapa", GeneroCondicaoWhere.igual, String.valueOf(pSeqDocumentoEtapa), TipoCondicaoWhere.Numero);
/*     */     
/*  89 */     listaDocumento = dao.listar(condicao);
/*  90 */     return listaDocumento;
/*     */   }
/*     */   
/*     */   public List<Documento> listarPorEtapaOS(String pSeqDocumentoEtapa) {
/*  94 */     DocumentoDAO dao = new DocumentoDAO();
/*  95 */     List<Documento> listaDocumento = new ArrayList();
/*  96 */     ClausulaWhere condicao = new ClausulaWhere();
/*     */     
/*  98 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "documento.seq_documento_etapa", GeneroCondicaoWhere.igual, String.valueOf(pSeqDocumentoEtapa), TipoCondicaoWhere.Numero);
/*     */     
/* 100 */     listaDocumento = dao.listarOS(condicao);
/* 101 */     return listaDocumento;
/*     */   }
/*     */   
/*     */   public List<Documento> listarPorEtapaOC(String pSeqDocumentoEtapa) {
/* 105 */     DocumentoDAO dao = new DocumentoDAO();
/* 106 */     List<Documento> listaDocumento = new ArrayList();
/* 107 */     ClausulaWhere condicao = new ClausulaWhere();
/*     */     
/* 109 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "documento.seq_documento_etapa", GeneroCondicaoWhere.igual, String.valueOf(pSeqDocumentoEtapa), TipoCondicaoWhere.Numero);
/*     */     
/* 111 */     listaDocumento = dao.listarOC(condicao);
/* 112 */     return listaDocumento;
/*     */   }
/*     */   
/*     */   public List<Documento> listarPorTipo(String pSeqTipoDocumento) {
/* 116 */     DocumentoDAO dao = new DocumentoDAO();
/* 117 */     List<Documento> listaDocumento = new ArrayList();
/* 118 */     ClausulaWhere condicao = new ClausulaWhere();
/*     */     
/* 120 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "documento.seq_tipo_documento", GeneroCondicaoWhere.igual, String.valueOf(pSeqTipoDocumento), TipoCondicaoWhere.Numero);
/*     */     
/* 122 */     listaDocumento = dao.listar(condicao);
/* 123 */     return listaDocumento;
/*     */   }
/*     */   
/*     */   public List<Documento> listarBMFaturamento(String pSeqTipoDocumento) {
/* 127 */     DocumentoDAO dao = new DocumentoDAO();
/* 128 */     List<Documento> listaDocumento = new ArrayList();
/* 129 */     ClausulaWhere condicao = new ClausulaWhere();
/*     */     
/* 131 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "documento.seq_tipo_documento", GeneroCondicaoWhere.igual, String.valueOf(pSeqTipoDocumento), TipoCondicaoWhere.Numero);
/* 132 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "documento.seq_documento_etapa", GeneroCondicaoWhere.igual, String.valueOf("441"), TipoCondicaoWhere.Numero);
/*     */     
/* 134 */     listaDocumento = dao.listar(condicao);
/* 135 */     return listaDocumento;
/*     */   }
/*     */   
/*     */   public List<Documento> listarPorTipoSeq(String pSeqTipoDocumento, String SeqDocumentoDono) {
/* 139 */     DocumentoDAO dao = new DocumentoDAO();
/* 140 */     List<Documento> listaDocumento = new ArrayList();
/* 141 */     ClausulaWhere condicao = new ClausulaWhere();
/*     */     
/* 143 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "documento.seq_tipo_documento", GeneroCondicaoWhere.igual, String.valueOf(pSeqTipoDocumento), TipoCondicaoWhere.Numero);
/* 144 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "documento.seq_documento_dono", GeneroCondicaoWhere.igual, String.valueOf(SeqDocumentoDono), TipoCondicaoWhere.Numero);
/*     */     
/* 146 */     listaDocumento = dao.listar(condicao);
/* 147 */     return listaDocumento;
/*     */   }
/*     */   
/*     */   public List<Documento> buscarTipoDocumentoDono(String pSeqDocumento) {
/* 151 */     DocumentoDAO dao = new DocumentoDAO();
/* 152 */     List<Documento> listaDocumento = new ArrayList();
/* 153 */     ClausulaWhere condicao = new ClausulaWhere();
/*     */     
/* 155 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "documento.seq_documento", GeneroCondicaoWhere.igual, String.valueOf(pSeqDocumento), TipoCondicaoWhere.Numero);
/*     */     
/* 157 */     listaDocumento = dao.listar(condicao);
/* 158 */     return listaDocumento;
/*     */   }
/*     */   
/*     */   public List<Documento> listarDocumentosFilhos(String pSeqDocumento) {
/* 162 */     DocumentoDAO dao = new DocumentoDAO();
/* 163 */     List<Documento> listaDocumento = new ArrayList();
/* 164 */     ClausulaWhere condicao = new ClausulaWhere();
/*     */     
/* 166 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "documento.seq_documento_dono", GeneroCondicaoWhere.igual, pSeqDocumento, TipoCondicaoWhere.Numero);
/*     */     
/* 168 */     listaDocumento = dao.listar(condicao);
/* 169 */     return listaDocumento;
/*     */   }
/*     */   
/*     */   public Documento buscarPorID(String pSeqEmpresa, String pSeqDocumento) {
/* 173 */     EmpresaService empresaService = new EmpresaService();
/* 174 */     Empresa empresa = empresaService.buscarEmpresaPorID(pSeqEmpresa);
/* 175 */     Documento retorno = null;
/* 176 */     System.out.println(empresa.getIntegracao());
/* 177 */     if (empresa.getIntegracao().equals("CROSS")) {
/* 178 */       DocumentoDAO dao = new DocumentoDAO();
/* 179 */       List<Documento> listaDocumento = new ArrayList();
/* 180 */       ClausulaWhere condicao = new ClausulaWhere();
/*     */       
/* 182 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "documento.seq_documento", GeneroCondicaoWhere.igual, String.valueOf(pSeqDocumento), TipoCondicaoWhere.Numero);
/*     */       
/* 184 */       listaDocumento = dao.listar(condicao);
/*     */       
/* 186 */       if (listaDocumento.size() > 0) {
/* 187 */         retorno = (Documento)listaDocumento.get(0);
/*     */         
/* 189 */         DocumentoItemMaterialService documentoItemMaterialService = new DocumentoItemMaterialService();
/* 190 */         retorno.setListaDocumentoItemMaterial(documentoItemMaterialService.listarPorDocumento(pSeqDocumento));
/*     */         
/* 192 */         DocumentoItemServicoService documentoItemServicoService = new DocumentoItemServicoService();
/* 193 */         retorno.setListaDocumentoItemServico(documentoItemServicoService.listarPorDocumento(pSeqDocumento));
/*     */         
/* 195 */         DocumentoItemEquipamentoService documentoItemEquipamentoService = new DocumentoItemEquipamentoService();
/* 196 */         retorno.setListaDocumentoItemEquipamento(documentoItemEquipamentoService.listarPorDocumento(pSeqDocumento));
/*     */         
/* 198 */         DocumentoItemEmbarcacaoService documentoItemEmbarcacaoService = new DocumentoItemEmbarcacaoService();
/* 199 */         retorno.setListaDocumentoItemEmbarcacao(documentoItemEmbarcacaoService.listarPorDocumento(pSeqDocumento));
/*     */         
/* 201 */         DocumentoOcorrenciaService documentoOcorrenciaService = new DocumentoOcorrenciaService();
/* 202 */         retorno.setListaDocumentoOcorrencia(documentoOcorrenciaService.listarPorDocumento(pSeqDocumento));
/*     */       }
/*     */     }
/* 205 */     
/* 209 */     return retorno;
/*     */   }
/*     */   
/*     */   public List<Documento> listarPorParceiro(String pSeqEmpresa, String pSeqParceiro) {
/* 213 */     EmpresaService empresaService = new EmpresaService();
/* 214 */     Empresa empresa = empresaService.buscarEmpresaPorID(pSeqEmpresa);
/*     */     
/* 216 */     List<Documento> retorno = new ArrayList();
/*     */     
/* 218 */     if (empresa.getIntegracao().equals("CROSS"))
/*     */     {
/* 220 */       DocumentoDAO dao = new DocumentoDAO();
/* 221 */       ClausulaWhere condicao = new ClausulaWhere();
/*     */       
/* 223 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "documento.seq_parceiro", GeneroCondicaoWhere.igual, String.valueOf(pSeqParceiro), TipoCondicaoWhere.Numero);
/*     */       
/* 225 */       retorno = dao.listar(condicao);
/* 226 */     } 
/* 230 */     return retorno;
/*     */   }
/*     */   
/*     */   public List<Documento> listarPorVendedor(String pSeqEmpresa, String pSeqUsuario) {
/* 234 */     EmpresaService empresaService = new EmpresaService();
/* 235 */     Empresa empresa = empresaService.buscarEmpresaPorID(pSeqEmpresa);
/*     */     
/* 237 */     List<Documento> retorno = new ArrayList();
/*     */     
/* 239 */     if (empresa.getIntegracao().equals("CROSS"))
/*     */     {
/* 241 */       DocumentoDAO dao = new DocumentoDAO();
/* 242 */       ClausulaWhere condicao = new ClausulaWhere();
/*     */       
/* 244 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "parceiro.seq_usuario", GeneroCondicaoWhere.igual, String.valueOf(pSeqUsuario), TipoCondicaoWhere.Numero);
/*     */       
/* 246 */       retorno = dao.listar(condicao);
/* 247 */     } else if (empresa.getIntegracao().equals("SAP")) {
/* 248 */       UsuarioService usuarioService = new UsuarioService();
/* 249 */       Usuario usuario = usuarioService.buscarUsuarioPorID(pSeqUsuario);
/* 250 */       
/*     */     }
/* 253 */     return retorno;
/*     */   }
/*     */   
/*     */   public boolean deletar(Documento documento) {
/* 257 */     DocumentoDAO dao = new DocumentoDAO();
/* 258 */     return dao.deletar(documento);
/*     */   }
/*     */   
/*     */   public List<Documento> listarDocumentoFiltro(ClausulaWhere pCondicao) {
/* 262 */     DocumentoDAO dao = new DocumentoDAO();
/* 263 */     return dao.listar(pCondicao);
/*     */   }
/*     */   
/*     */   public List<Documento> itensBotaoGerar(String pSeqDocumentoPai) {
/* 267 */     DocumentoDAO dao = new DocumentoDAO();
/* 268 */     List<Documento> listaDocumento = new ArrayList();
/* 269 */     ClausulaWhere condicao = new ClausulaWhere();
/*     */     
/* 271 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "documento.seq_documento_dono", GeneroCondicaoWhere.igual, String.valueOf(pSeqDocumentoPai), TipoCondicaoWhere.Numero);
/* 272 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "documento.seq_tipo_documento", GeneroCondicaoWhere.igual, String.valueOf(301), TipoCondicaoWhere.Numero);
/*     */     
/* 274 */     listaDocumento = dao.listar(condicao);
/* 275 */     return listaDocumento;
/*     */   }
/*     */   
/*     */   public boolean enviarEmailParceiro(String pEmail, Multipart pConteudo) {
/* 279 */     Util util = new Util();
/* 280 */     return Util.enviarEmailParceiro(pEmail, pConteudo);
/*     */   }

/*     */   
/* 283 */   public List<Documento> listarFiltro(ClausulaWhere pCondicao) { DocumentoDAO dao = new DocumentoDAO();
/* 284 */     List<Documento> listaDocumento = new ArrayList();
/*     */     
/* 286 */     listaDocumento = dao.listar(pCondicao);
/*     */     
/*     */ 
/* 289 */     return listaDocumento;
/*     */   }
/*     */



 }


