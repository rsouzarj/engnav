/*     */ package WSConsumir;
/*     */ 
/*     */ import Comissao.Comissao;
/*     */ import CondicaoPagamento.CondicaoPagamento;
/*     */ import Documento.Documento;
/*     */ import FormaPagamento.FormaPagamento;
/*     */ import MaterialPreco.MaterialPreco;
/*     */ import Parceiro.Parceiro;
/*     */ import TipoParceiro.TipoParceiro;
/*     */ import Uf.Uf;
/*     */ import Usuario.Usuario;
/*     */ import Util.Retorno;
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.google.gson.reflect.TypeToken;
/*     */ import java.io.PrintStream;
/*     */ import java.lang.reflect.Type;
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
/*     */ 
/*     */ 
/*     */ public class WSConsumir
/*     */ {
/*  32 */   private String url = "http://localhost:8081/ERP/recursos/ws";
/*     */   
/*     */   public static void main(String[] args)
/*     */   {
/*  36 */     WSConsumir ws = new WSConsumir();
/*     */     
/*  38 */     Usuario usuario = ws.validarAcesso("teste", "admin", "123");
/*  39 */     if (usuario != null) {
/*  40 */       System.out.println("seq_usuario" + usuario.getSeqUsuario());
/*     */     } else {
/*  42 */       System.out.println("Mostrar msg de usuário/senha inválidos");
/*     */     }
/*     */   }
/*     */   

/*     */   public Usuario validarAcesso(String pChave, String pUsuario, String pSenha)
/*     */   {
/* 218 */     Usuario usuario = new Usuario();
/* 219 */     WSUtil wsUtil = new WSUtil();
/*     */     
/* 221 */     String caminho = this.url + "/Usuario/ValidarUsuario/" + pChave + "/" + pUsuario + "/" + pSenha;
/*     */     
/* 223 */     System.out.println(caminho);
/*     */     
/* 225 */     Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HH:mm:ss").create();
/*     */     
/* 227 */     Type tipo = new TypeToken() {}.getType();
/* 230 */     String jSon = WSUtil.sendGet(caminho, "GET");
/* 231 */     System.out.println(jSon);
/*     */     
/* 233 */     usuario = (Usuario)gson.fromJson(jSon, tipo);
/*     */     
/* 235 */     return usuario;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean enviarSenhaEmail(String pEmail)
/*     */   {
/* 247 */     WSUtil wsUtil = new WSUtil();
/*     */     
/* 249 */     String caminho = this.url + "/Usuario/EnviarSenhaEmail/" + pEmail;
/*     */     
/* 251 */     System.out.println(caminho);
/*     */     
/* 253 */     Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HH:mm:ss").create();
/*     */     
/* 255 */     Type tipo = new TypeToken() {}.getType();
/* 258 */     String jSon = WSUtil.sendGet(caminho, "GET");
/* 259 */     boolean ok = ((Boolean)gson.fromJson(jSon, tipo)).booleanValue();
/*     */     
/* 261 */     return ok;
/*     */   }
/*     */   
/*     */   public Usuario validarChaveSessao(String pChaveSessao) {
/* 265 */     Usuario usuario = new Usuario();
/* 266 */     WSUtil wsUtil = new WSUtil();
/*     */     
/* 268 */     String caminho = this.url + "/Usuario/ValidarChaveSessao/" + pChaveSessao;
/*     */     
/* 270 */     System.out.println(caminho);
/*     */     
/* 272 */     Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HH:mm:ss").create();
/*     */     
/* 274 */     Type tipo = new TypeToken() {}.getType();
/* 277 */     String jSon = WSUtil.sendGet(caminho, "GET");
/* 278 */     usuario = (Usuario)gson.fromJson(jSon, tipo);
/*     */     
/* 280 */     return usuario;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<Comissao> listarComissao(String pSeqUsuario)
/*     */   {
/* 291 */     List<Comissao> retorno = new ArrayList();
/* 292 */     String caminho = this.url + "/Usuario/ListarComissao/" + pSeqUsuario + "/";
/*     */     
/* 294 */     System.out.println(caminho);
/*     */     
/* 296 */     Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HH:mm:ss").create();
/*     */     
/* 298 */     Type tipo = new TypeToken() {}.getType();
/* 301 */     String jSon = WSUtil.sendGet(caminho, "GET");
/*     */     
/* 303 */     retorno = (List)gson.fromJson(jSon, tipo);
/*     */     
/* 305 */     return retorno;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Usuario salvarUsuario(Usuario pUsuario)
/*     */   {
/* 317 */     String caminho = this.url + "/Usuario/SalvarUsuario/";
/*     */     
/* 319 */     System.out.println(caminho);
/*     */     
/* 321 */     Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HH:mm:ss").create();
/*     */     
/* 323 */     Type tipo = new TypeToken() {}.getType();
/* 325 */     String jSon = gson.toJson(pUsuario, tipo);
/*     */     
/* 327 */     String retorno = WSUtil.sendPost(caminho, jSon, "POST");
/*     */     
/* 329 */     System.out.println(retorno);
/* 330 */     Usuario usuario = (Usuario)gson.fromJson(retorno, tipo);
/* 331 */     return usuario;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<Parceiro> listarParceiro(String pSeqUsuario)
/*     */   {
/* 343 */     String caminho = this.url + "/Parceiro/ListarParceiro/" + String.valueOf(pSeqUsuario);
/*     */     
/* 345 */     System.out.println(caminho);
/*     */     
/* 347 */     Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HH:mm:ss").create();
/*     */     
/* 349 */     Type tipo = new TypeToken() {}.getType();
/* 352 */     String retorno = WSUtil.sendGet(caminho, "GET");
/*     */     
/* 354 */     List<Parceiro> listaparceiro = (List)gson.fromJson(retorno, tipo);
/*     */     
/* 356 */     return listaparceiro;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Parceiro buscarParceiro(String pSeqEmpresa, String pCodigo)
/*     */   {
/* 369 */     String caminho = this.url + "/Parceiro/BuscarParceiro/" + String.valueOf(pSeqEmpresa) + "/" + pCodigo + "/";
/*     */     
/* 371 */     System.out.println(caminho);
/*     */     
/* 373 */     Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HH:mm:ss").create();
/*     */     
/* 375 */     Type tipo = new TypeToken() {}.getType();
/* 378 */     String retorno = WSUtil.sendGet(caminho, "GET");
/*     */     
/* 380 */     Parceiro parceiro = (Parceiro)gson.fromJson(retorno, tipo);
/*     */     
/* 382 */     return parceiro;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Retorno salvarParceiro(Parceiro pParceiro)
/*     */   {
/* 394 */     String caminho = this.url + "/Parceiro/SalvarParceiro/";
/*     */     
/* 396 */     System.out.println(caminho);
/*     */     
/* 398 */     Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HH:mm:ss").create();
/*     */     
/* 400 */     Type tipo = new TypeToken() {}.getType();
/* 402 */     String jSon = gson.toJson(pParceiro, tipo);
/*     */     
/* 404 */     System.out.println(jSon);
/*     */     
/* 406 */     String retorno = WSUtil.sendPost(caminho, jSon, "POST");
/*     */     
/* 408 */     System.out.println(retorno);
/* 409 */     Type tipoRetorno = new TypeToken() {}.getType();
/* 412 */     Retorno ret = (Retorno)gson.fromJson(retorno, tipoRetorno);
/* 413 */     return ret;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<TipoParceiro> listarTipoParceiro(String pSeqEmpresa)
/*     */   {
/* 425 */     String caminho = this.url + "/Parceiro/ListarTipoParceiro/" + pSeqEmpresa;
/* 426 */     System.out.println(caminho);
/* 427 */     Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HH:mm:ss").create();
/*     */     
/* 429 */     Type tipo = new TypeToken() {}.getType();
/* 432 */     String retorno = WSUtil.sendGet(caminho, "GET");
/*     */     
/* 434 */     List<TipoParceiro> listaTipoParceiro = (List)gson.fromJson(retorno, tipo);
/* 435 */     return listaTipoParceiro;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<Uf> listarUf()
/*     */   {
/* 446 */     String caminho = this.url + "/Util/ListarUF";
/* 447 */     System.out.println(caminho);
/* 448 */     Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HH:mm:ss").create();
/*     */     
/* 450 */     Type tipo = new TypeToken() {}.getType();
/* 453 */     String retorno = WSUtil.sendGet(caminho, "GET");
/*     */     
/* 455 */     List<Uf> listaUf = (List)gson.fromJson(retorno, tipo);
/* 456 */     return listaUf;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Parceiro buscarDadosReceita(Parceiro pParceiro)
/*     */   {
/* 468 */     String caminho = this.url + "/Parceiro/BuscarDadosReceita/";
/*     */     
/* 470 */     System.out.println(caminho);
/*     */     
/* 472 */     Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HH:mm:ss").create();
/*     */     
/* 474 */     Type tipo = new TypeToken() {}.getType();
/* 476 */     String jSon = gson.toJson(pParceiro, tipo);
/*     */     
/* 478 */     String retorno = WSUtil.sendPost(caminho, jSon, "POST");
/*     */     
/* 480 */     System.out.println(retorno);
/* 481 */     Parceiro parceiro = (Parceiro)gson.fromJson(retorno, tipo);
/* 482 */     return parceiro;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Retorno salvarPedido(Documento pDocumento)
/*     */   {
/* 493 */     String caminho = this.url + "/Pedido/SalvarPedido/";
/*     */     
/* 495 */     System.out.println(caminho);
/*     */     
/* 497 */     Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HH:mm:ss").create();
/*     */     
/* 499 */     Type tipo = new TypeToken() {}.getType();
/* 501 */     String jSon = gson.toJson(pDocumento, tipo);
/*     */     
/* 503 */     System.out.println(jSon);
/*     */     
/* 505 */     String retorno = WSUtil.sendPost(caminho, jSon, "POST");
/*     */     
/* 507 */     System.out.println(retorno);
/* 508 */     Type tipoRetorno = new TypeToken() {}.getType();
/* 511 */     Retorno ret = (Retorno)gson.fromJson(retorno, tipoRetorno);
/* 512 */     return ret;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<Documento> listarPedidoParceiro(String pSeqEmpresa, String pSeqParceiro)
/*     */   {
/* 526 */     String caminho = this.url + "/Pedido/ListarPedidoParceiro/" + pSeqEmpresa + "/" + pSeqParceiro;
/*     */     
/* 528 */     System.out.println(caminho);
/*     */     
/* 530 */     Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HH:mm:ss").create();
/*     */     
/* 532 */     Type tipo = new TypeToken() {}.getType();
/* 535 */     String retorno = WSUtil.sendGet(caminho, "GET");
/*     */     
/* 537 */     List<Documento> listadocumento = (List)gson.fromJson(retorno, tipo);
/*     */     
/* 539 */     return listadocumento;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<Documento> listarPedidoVendedor(String pSeqEmpresa, String pSeqUsuario)
/*     */   {
/* 552 */     String caminho = this.url + "/Pedido/ListarPedidoVendedor/" + pSeqEmpresa + "/" + pSeqUsuario;
/*     */     
/* 554 */     System.out.println(caminho);
/*     */     
/* 556 */     Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HH:mm:ss").create();
/*     */     
/* 558 */     Type tipo = new TypeToken() {}.getType();
/* 561 */     String retorno = WSUtil.sendGet(caminho, "GET");
/*     */     
/* 563 */     List<Documento> listadocumento = (List)gson.fromJson(retorno, tipo);
/*     */     
/* 565 */     return listadocumento;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<MaterialPreco> listarProduto(String pSeqEmpresa, String pChaveOrigem)
/*     */   {
/* 578 */     String caminho = this.url + "/Produto/ListarProduto/" + pSeqEmpresa + "/" + pChaveOrigem;
/*     */     
/* 580 */     System.out.println(caminho);
/*     */     
/* 582 */     Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HH:mm:ss").create();
/*     */     
/* 584 */     Type tipo = new TypeToken() {}.getType();
/* 587 */     String retorno = WSUtil.sendGet(caminho, "GET");
/*     */     
/* 589 */     List<MaterialPreco> listamaterial = (List)gson.fromJson(retorno, tipo);
/*     */     
/* 591 */     return listamaterial;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<CondicaoPagamento> listarCondicaoPagamento(String pSeqEmpresa)
/*     */   {
/* 604 */     String caminho = this.url + "/Pagamento/ListarCondicaoPagamento/" + pSeqEmpresa;
/*     */     
/* 606 */     System.out.println(caminho);
/*     */     
/* 608 */     Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HH:mm:ss").create();
/*     */     
/* 610 */     Type tipo = new TypeToken() {}.getType();
/* 613 */     String retorno = WSUtil.sendGet(caminho, "GET");
/*     */     
/* 615 */     List<CondicaoPagamento> listacondicaopagamento = (List)gson.fromJson(retorno, tipo);
/*     */     
/* 617 */     return listacondicaopagamento;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<FormaPagamento> listarFormaPagamento(String pSeqEmpresa)
/*     */   {
/* 629 */     String caminho = this.url + "/Pagamento/ListarFormaPagamento/" + pSeqEmpresa;
/*     */     
/* 631 */     System.out.println(caminho);
/*     */     
/* 633 */     Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HH:mm:ss").create();
/*     */     
/* 635 */     Type tipo = new TypeToken() {}.getType();
/* 638 */     String retorno = WSUtil.sendGet(caminho, "GET");
/*     */     
/* 640 */     List<FormaPagamento> listaformapagamento = (List)gson.fromJson(retorno, tipo);
/*     */     
/* 642 */     return listaformapagamento;
/*     */   }
/*     */ }


