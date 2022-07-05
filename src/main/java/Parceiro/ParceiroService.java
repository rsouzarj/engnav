/*     */ package Parceiro;
/*     */ 
/*     */ import ClausulaSQL.ClausulaWhere;
/*     */ import ClausulaSQL.GeneroCondicaoWhere;
/*     */ import ClausulaSQL.OperacaoCondicaoWhere;
/*     */ import ClausulaSQL.TipoCondicaoWhere;
/*     */ import Empresa.Empresa;
/*     */ import Empresa.EmpresaService;
/*     */ import Integracao_CIGAM_Empresa.CIGAMEmpresaService;
/*     */ import Usuario.Usuario;
/*     */ import Usuario.UsuarioService;
/*     */ import Util.Retorno;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;

/*     */ public class ParceiroService
/*     */ {
/*  29 */   private Empresa empresa = new Empresa();
/*     */   
/*     */   public Retorno salvar(Parceiro pParceiro) {
/*  32 */     Retorno retorno = new Retorno();
/*  33 */     EmpresaService empresaService = new EmpresaService();
/*     */     
/*  35 */     Empresa empresa = empresaService.buscarEmpresaPorID(pParceiro.getSeqEmpresa());
/*     */     
/*  37 */     if (empresa.getIntegracao().equals("CIGAM")) {
/*  38 */       CIGAMEmpresaService cigamEmpresaService = new CIGAMEmpresaService();
/*  39 */       retorno = cigamEmpresaService.salvar(pParceiro, empresa);
/*  40 */     } else if (empresa.getIntegracao().equals("CROSS")) {
/*  44 */       ParceiroDAO dao = new ParceiroDAO();
/*  45 */       if (pParceiro.getSeqParceiro() == null) {
/*  46 */         System.out.println("Novo");
/*  47 */         pParceiro.setSituacao("ATIVO");
/*  48 */         pParceiro.setDataCadastro(new Date());
/*  49 */         pParceiro = dao.inserir(pParceiro);
/*     */       } else {
/*  51 */         System.out.println("Alteração");
/*  52 */         pParceiro = dao.alterar(pParceiro);
/*     */       }
/*     */       
/*  55 */       retorno.setClasse(pParceiro);
/*  56 */       retorno.setMsg("Informações Armazenadas com sucesso!");
/*  57 */       retorno.setTransacaoOK(true);
/*     */     }
/*     */     
/*  60 */     return retorno;
/*     */   }
/*     */   
/*     */   public List<Parceiro> listarParceiro(String pSeqUsuario, String pConteudo)
/*     */   {
/*  65 */     List<Parceiro> retorno = new ArrayList();
/*  66 */     UsuarioService usuarioService = new UsuarioService();
/*  67 */     Usuario usuario = usuarioService.buscarUsuarioPorID(pSeqUsuario);
/*     */     
/*  69 */     EmpresaService empresaService = new EmpresaService();
/*  70 */     Empresa empresa = empresaService.buscarEmpresaPorID(usuario.getSeqEmpresa());
/*     */     
/*  72 */     ParceiroDAO parceiroDAO = new ParceiroDAO();
/*  73 */     ClausulaWhere condicao = new ClausulaWhere();
/*     */     
/*  75 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "parceiro.seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(usuario.getSeqEmpresa()), TipoCondicaoWhere.Numero);
/*  76 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "parceiro.nome", GeneroCondicaoWhere.contem, pConteudo, TipoCondicaoWhere.Texto);
/*  77 */     retorno = parceiroDAO.listar(condicao);
/*     */     
/*  79 */     return retorno;
/*     */   }

/*     */   public List<Parceiro> listar(String pSeqEmpresa, String pString)
/*     */   {
/*  65 */     List<Parceiro> retorno = new ArrayList();
/*  66 */     UsuarioService usuarioService = new UsuarioService();
/*  67 */     Usuario usuario = usuarioService.buscarUsuarioPorID(pSeqEmpresa);
/*     */     
/*  69 */     EmpresaService empresaService = new EmpresaService();
/*  70 */     Empresa empresa = empresaService.buscarEmpresaPorID(usuario.getSeqEmpresa());
/*     */     
/*  72 */     ParceiroDAO parceiroDAO = new ParceiroDAO();
/*  73 */     ClausulaWhere condicao = new ClausulaWhere();
/*     */     
/*  75 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "parceiro.seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(usuario.getSeqEmpresa()), TipoCondicaoWhere.Numero);
/*  76 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "parceiro.nome", GeneroCondicaoWhere.contem, pString, TipoCondicaoWhere.Texto);
/*  77 */     retorno = parceiroDAO.listar(condicao);
/*     */     
/*  79 */     return retorno;
/*     */   }

/*     */   public List<Parceiro> listarTudo(String pSeqEmpresa)
/*     */   {
/*  65 */     List<Parceiro> retorno = new ArrayList();
/*  66 */     UsuarioService usuarioService = new UsuarioService();
/*  67 */     Usuario usuario = usuarioService.buscarUsuarioPorID(pSeqEmpresa);
/*     */     
/*  69 */     EmpresaService empresaService = new EmpresaService();
/*  70 */     Empresa empresa = empresaService.buscarEmpresaPorID(usuario.getSeqEmpresa());
/*     */     
/*  72 */     ParceiroDAO parceiroDAO = new ParceiroDAO();
/*  73 */     ClausulaWhere condicao = new ClausulaWhere();
/*     */     
/*  75 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "parceiro.seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(usuario.getSeqEmpresa()), TipoCondicaoWhere.Numero);
/*  77 */     retorno = parceiroDAO.listar(condicao);
/*     */     
/*  79 */     return retorno;
/*     */   }

/*    */   public List<Parceiro> listar(ClausulaWhere sClausula) {
/* 67 */     ParceiroDAO dao = new ParceiroDAO();
/* 68 */     return dao.listar(sClausula);
/*    */   }
/*     */   
/*     */   public Parceiro buscarParceiroV(String pConteudo)
/*     */   {
/*  84 */     Parceiro retorno = new Parceiro();
/*     */     
/*  86 */     ParceiroDAO parceiroDAO = new ParceiroDAO();
/*  87 */     ClausulaWhere condicao = new ClausulaWhere();
/*     */     
/*  89 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "parceiro.nome", GeneroCondicaoWhere.contem, pConteudo, TipoCondicaoWhere.Texto);
/*  90 */     retorno = (Parceiro)parceiroDAO.listar(condicao).get(0);
/*     */     
/*  92 */     return retorno;
/*     */   }
/*     */   
/*     */   public Parceiro buscar(String pSeqEmpresa, String pCodigo) {
/*  96 */     Parceiro retorno = new Parceiro();
/*     */     
/*  98 */     EmpresaService empresaService = new EmpresaService();
/*  99 */     Empresa empresa = empresaService.buscarEmpresaPorID(pSeqEmpresa);
/*     */     
/* 101 */     if (empresa.getIntegracao().equals("CROSS")) {
/* 102 */       ParceiroDAO dao = new ParceiroDAO();
/* 103 */       ClausulaWhere condicao = new ClausulaWhere();
/* 104 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "parceiro.seq_parceiro", GeneroCondicaoWhere.igual, pCodigo, TipoCondicaoWhere.Numero);
/* 105 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "parceiro.seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(pSeqEmpresa), TipoCondicaoWhere.Numero);
/* 106 */       retorno = (Parceiro)dao.listar(condicao).get(0);
/* 107 */     } 
/* 113 */     return retorno;
/*     */   }
/*     */   
/*     */   public List<Parceiro> listarParceiroTipo(String pSeqUsuario, String pSeqTipoParceiro)
/*     */   {
/* 118 */     List<Parceiro> retorno = new ArrayList();
/* 119 */     UsuarioService usuarioService = new UsuarioService();
/* 120 */     Usuario usuario = usuarioService.buscarUsuarioPorID(pSeqUsuario);
/*     */     
/* 122 */     EmpresaService empresaService = new EmpresaService();
/* 123 */     Empresa empresa = empresaService.buscarEmpresaPorID(usuario.getSeqEmpresa());
/*     */     
/* 125 */     ParceiroDAO parceiroDAO = new ParceiroDAO();
/* 126 */     ClausulaWhere condicao = new ClausulaWhere();
/*     */     
/* 128 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "parceiro.seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(usuario.getSeqEmpresa()), TipoCondicaoWhere.Numero);
/* 129 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "parceiro.seq_tipo_parceiro", GeneroCondicaoWhere.igual, pSeqTipoParceiro, TipoCondicaoWhere.Numero);
/* 130 */     retorno = parceiroDAO.listar(condicao);
/*     */     
/* 132 */     return retorno;
/*     */   }

/*     */   public List<Parceiro> listarParceiroTipoDoc(String pSeqEmpresa)
/*     */   {
/* 118 */     List<Parceiro> retorno = new ArrayList();

     
/* 125 */     ParceiroDAO parceiroDAO = new ParceiroDAO();
/* 126 */     ClausulaWhere condicao = new ClausulaWhere();
/*     */     
/* 128 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "parceiro.seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(pSeqEmpresa), TipoCondicaoWhere.Numero);
/* 129 */     
              
/* 130 */     retorno = parceiroDAO.listartipo(condicao);
/*     */     
/* 132 */     return retorno;
/*     */   }
/*     */   
/*     */   public List<Parceiro> listarParceiroTipoNome(String pSeqUsuario, String pTipoParceiroNome) {
/* 136 */     List<Parceiro> retorno = new ArrayList();
/* 137 */     UsuarioService usuarioService = new UsuarioService();
/* 138 */     Usuario usuario = usuarioService.buscarUsuarioPorID(pSeqUsuario);
/*     */     
/* 140 */     EmpresaService empresaService = new EmpresaService();
/* 141 */     Empresa empresa = empresaService.buscarEmpresaPorID(usuario.getSeqEmpresa());
/*     */     
/* 143 */     ParceiroDAO parceiroDAO = new ParceiroDAO();
/* 144 */     ClausulaWhere condicao = new ClausulaWhere();
/*     */     
/* 146 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "parceiro.seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(usuario.getSeqEmpresa()), TipoCondicaoWhere.Numero);
/* 147 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "tipo_parceiro.NOME", GeneroCondicaoWhere.contem, pTipoParceiroNome, TipoCondicaoWhere.Texto);
/* 148 */     retorno = parceiroDAO.listar(condicao);
/*     */     
/* 150 */     return retorno;
/*     */   }
/*     */   
/*     */   public Parceiro buscarPorSeqParceiro(String pSeqParceiro) {
/* 154 */     Parceiro retorno = new Parceiro();
/*     */     
/* 156 */     ParceiroDAO dao = new ParceiroDAO();
/* 157 */     ClausulaWhere condicao = new ClausulaWhere();
/*     */     
/* 159 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "parceiro.seq_parceiro", GeneroCondicaoWhere.igual, String.valueOf(pSeqParceiro), TipoCondicaoWhere.Numero);
/*     */     
/* 161 */     if (dao.listar(condicao).isEmpty()) {
/* 162 */       return null;
/*     */     }
/* 164 */     retorno = (Parceiro)dao.listar(condicao).get(0);
/* 165 */     return retorno;
/*     */   }
/*     */   


/*     */ 
/*     */   public boolean buscarDocumentoParceiro(String pDocumentoParceiro)
/*     */   {
/* 172 */     ParceiroDAO dao = new ParceiroDAO();
/* 173 */     ClausulaWhere condicao = new ClausulaWhere();
/*     */     
/* 175 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "parceiro.documento", GeneroCondicaoWhere.igual, pDocumentoParceiro, TipoCondicaoWhere.Texto);
/*     */     
/* 177 */     if (dao.listar(condicao).isEmpty()) {
/* 178 */       return false;
/*     */     }
/*     */     
/* 181 */     return true;
/*     */   }
/*     */   
/*     */   public List<Parceiro> listarParceiroFiltro(ClausulaWhere pCondicao)
/*     */   {
/* 186 */     ParceiroDAO dao = new ParceiroDAO();
/* 187 */     return dao.listar(pCondicao);
/*     */   }
/*     */   
/*     */   public Retorno deletar(String pSeqEmpresa, String pCodigo) {
/* 191 */     Retorno retorno = new Retorno();
/* 192 */     EmpresaService empresaService = new EmpresaService();
/* 193 */     Empresa empresa = empresaService.buscarEmpresaPorID(pSeqEmpresa);
/*     */     
/* 195 */     boolean ok = false;
/* 196 */     if (empresa.getIntegracao().equals("CROSS")) {
/* 197 */       ParceiroDAO dao = new ParceiroDAO();
/* 198 */       ok = dao.deletar(pSeqEmpresa, pCodigo);
/* 199 */     
/*     */     }
/*     */     
/* 206 */     if (ok == true) {
/* 207 */       retorno.setClasse(null);
/* 208 */       retorno.setMsg("Registro Eliminado com sucesso.");
/* 209 */       retorno.setTransacaoOK(true);
/*     */     } else {
/* 211 */       retorno.setClasse(null);
/* 212 */       retorno.setMsg("Falha ao Elimir Registro");
/* 213 */       retorno.setTransacaoOK(false);
/*     */     }
/*     */     
/* 216 */     return retorno;
/*     */   }
/*     */ }
