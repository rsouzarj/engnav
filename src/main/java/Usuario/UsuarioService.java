/*     */ package Usuario;
/*     */ 
/*     */ import ClausulaSQL.ClausulaWhere;
/*     */ import ClausulaSQL.GeneroCondicaoWhere;
/*     */ import ClausulaSQL.OperacaoCondicaoWhere;
/*     */ import ClausulaSQL.TipoCondicaoWhere;
/*     */ import Util.Modulo;
/*     */ import Util.Situacao;
/*     */ import Util.Util;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Random;

/*     */ public class UsuarioService
/*     */ {
/*     */   public Usuario validarAcesso(String pUsuario, String pSenha, String pChave, Modulo pModulo)
/*     */   {
/*  28 */     UsuarioDAO dao = new UsuarioDAO();
/*  29 */     List<Usuario> listaUsuario = new ArrayList();
/*  30 */     ClausulaWhere condicao = new ClausulaWhere();
/*  31 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "usuario.usuario", GeneroCondicaoWhere.igual, pUsuario.trim(), TipoCondicaoWhere.Texto);
/*  32 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "usuario.senha", GeneroCondicaoWhere.igual, pSenha.trim(), TipoCondicaoWhere.Texto);
/*  33 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "usuario.situacao", GeneroCondicaoWhere.igual, "ATIVO", TipoCondicaoWhere.Texto);
/*  34 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "empresa.chave", GeneroCondicaoWhere.igual, pChave, TipoCondicaoWhere.Texto);
/*     */     
/*  36 */     Usuario usuario = null;
/*     */     
/*  38 */     listaUsuario = dao.listar(condicao);
/*  39 */     if (listaUsuario.size() > 0) {
/*  40 */       UsuarioService usuarioService = new UsuarioService();
/*     */       
/*  42 */       usuario = (Usuario)listaUsuario.get(0);
/*  43 */       usuario.setAppSessao(Util.gerarChave());
/*  44 */       usuario = usuarioService.salvarUsuario(usuario);
/*     */     }
/*     */     
/*  47 */     return usuario;
/*     */   }
/*     */   
/*     */   public boolean enviarEmailSenha(String pEmail) {
/*  51 */     boolean retorno = false;
/*     */     
/*  53 */     Usuario usuario = new Usuario();
/*  54 */     UsuarioDAO dao = new UsuarioDAO();
/*     */     
/*  56 */     List<Usuario> listaUsuario = new ArrayList();
/*  57 */     ClausulaWhere condicao = new ClausulaWhere();
/*  58 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "usuario.email", GeneroCondicaoWhere.igual, pEmail.trim(), TipoCondicaoWhere.Texto);
/*  59 */     listaUsuario = dao.listar(condicao);
/*     */     
/*  61 */     if (listaUsuario.size() == 0) {
/*  62 */       retorno = false;
/*     */     }
/*     */     else {
/*  65 */       usuario = (Usuario)listaUsuario.get(0);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  71 */       Util util = new Util();
/*  72 */       Util.enviarEmail(pEmail, usuario);
/*  73 */       System.out.println(((Usuario)listaUsuario.get(0)).getSenha());
/*  74 */       retorno = true;
/*     */     }
/*  76 */     return retorno;
/*     */   }
/*     */   
/*     */   public Usuario salvarUsuario(Usuario usuario) {
/*  80 */     UsuarioDAO dao = new UsuarioDAO();
/*  81 */     System.out.println("==> " + usuario.getSeqUsuario());
/*  82 */     if (usuario.getSeqUsuario() == null) {
/*  83 */       Random ran = new Random();
/*  84 */       String senha = "@123";
/*  85 */       usuario.setSenha(senha);
/*  86 */       usuario.setDataCadastro(new Date());
/*  87 */       return dao.inserir(usuario);
/*     */     }
/*  89 */     return dao.alterar(usuario);
/*     */   }
/*     */   
/*     */   public List<Usuario> listarTodosOsUsuarios(String pSeqEmpresa, Situacao pSituacao)
/*     */   {
/*  94 */     UsuarioDAO dao = new UsuarioDAO();
/*  95 */     List<Usuario> listaUsuario = new ArrayList();
/*  96 */     ClausulaWhere condicao = new ClausulaWhere();
/*  97 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "usuario.seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(pSeqEmpresa), TipoCondicaoWhere.Numero);
/*     */     
/*  99 */     if (pSituacao == Situacao.ATIVO) {
/* 100 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "usuario.situacao", GeneroCondicaoWhere.igual, "ATIVO", TipoCondicaoWhere.Texto);
/* 101 */     } else if (pSituacao == Situacao.INATIVO) {
/* 102 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "usuario.situacao", GeneroCondicaoWhere.igual, "INATIVO", TipoCondicaoWhere.Texto);
/*     */     }
/*     */     
/* 105 */     listaUsuario = dao.listar(condicao);
/* 106 */     return listaUsuario;
/*     */   }
/*     */   
/*     */   public List<Usuario> listarUsuarioPorNome(String pSeqEmpresa, String pNome) {
/* 110 */     UsuarioDAO dao = new UsuarioDAO();
/* 111 */     List<Usuario> listaUsuario = new ArrayList();
/* 112 */     ClausulaWhere condicao = new ClausulaWhere();
/* 113 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "usuario.usuario", GeneroCondicaoWhere.contem, pNome, TipoCondicaoWhere.Texto);
/* 114 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "usuario.seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(pSeqEmpresa), TipoCondicaoWhere.Numero);
/* 115 */     listaUsuario = dao.listar(condicao);
/* 116 */     return listaUsuario;
/*     */   }
/*     */   
/*     */   public List<Usuario> listarUsuarioQueRecebeComissao(String pSeqEmpresa) {
/* 120 */     UsuarioDAO dao = new UsuarioDAO();
/* 121 */     List<Usuario> listaUsuario = new ArrayList();
/* 122 */     ClausulaWhere condicao = new ClausulaWhere();
/* 123 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "usuario.CALCULAR_COMISSAO", GeneroCondicaoWhere.igual, "Sim", TipoCondicaoWhere.Texto);
/* 124 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "usuario.SITUACAO", GeneroCondicaoWhere.igual, "ATIVO", TipoCondicaoWhere.Texto);
/* 125 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "usuario.seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(pSeqEmpresa), TipoCondicaoWhere.Numero);
/* 126 */     listaUsuario = dao.listar(condicao);
/* 127 */     return listaUsuario;
/*     */   }
/*     */   
/*     */   public Usuario buscarUsuarioPorID(String pId) {
/* 131 */     Usuario retorno = null;
/*     */     
/* 133 */     UsuarioDAO dao = new UsuarioDAO();
/* 134 */     ClausulaWhere condicao = new ClausulaWhere();
/* 135 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "usuario.seq_usuario", GeneroCondicaoWhere.igual, String.valueOf(pId), TipoCondicaoWhere.Numero);
/* 136 */     List<Usuario> listaUsuario = dao.listar(condicao);
/* 137 */     if ((listaUsuario != null) && 
/* 138 */       (listaUsuario.size() > 0)) {
/* 139 */       retorno = (Usuario)listaUsuario.get(0);
/*     */     }
/*     */     
/* 142 */     return retorno;
/*     */   }

/*     */   public boolean deletarUsuario(Usuario usuario)
/*     */   {
/* 162 */     UsuarioDAO dao = new UsuarioDAO();
/* 163 */     return dao.deletar(usuario);
/*     */   }
/*     */ }

