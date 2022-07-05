/*     */ package MaterialPreco;
/*     */ 
/*     */ import ClausulaSQL.ClausulaWhere;
/*     */ import ClausulaSQL.GeneroCondicaoWhere;
/*     */ import ClausulaSQL.OperacaoCondicaoWhere;
/*     */ import ClausulaSQL.TipoCondicaoWhere;
/*     */ import Empresa.Empresa;
/*     */ import Empresa.EmpresaService;
/*     */ import Material.Material;
/*     */ import TabelaPreco.TabelaPreco;
/*     */ import TabelaPreco.TabelaPrecoService;
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
/*     */ public class MaterialPrecoService
/*     */ {
/*     */   public List<MaterialPreco> salvar(List<MaterialPreco> listaMaterialPreco)
/*     */   {
/*  28 */     MaterialPrecoDAO dao = new MaterialPrecoDAO();
/*  29 */     return dao.alterar(listaMaterialPreco);
/*     */   }
/*     */   
/*     */   public List<MaterialPreco> listarPorMaterial(Material pMaterial, Situacao pSituacao) {
/*  33 */     MaterialPrecoDAO dao = new MaterialPrecoDAO();
/*     */     
/*     */ 
/*  36 */     dao.popuparPrecos(pMaterial.getSeqEmpresa());
/*     */     
/*  38 */     List<MaterialPreco> listaMaterialPreco = new ArrayList();
/*  39 */     ClausulaWhere condicao = new ClausulaWhere();
/*     */     
/*  41 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "material_preco.seq_material", GeneroCondicaoWhere.igual, String.valueOf(pMaterial.getSeqMaterial()), TipoCondicaoWhere.Numero);
/*     */     
/*  43 */     if (pSituacao == Situacao.ATIVO) {
/*  44 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "material_preco.situacao", GeneroCondicaoWhere.igual, "ATIVO", TipoCondicaoWhere.Texto);
/*  45 */     } else if (pSituacao == Situacao.INATIVO) {
/*  46 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "material_preco.situacao", GeneroCondicaoWhere.igual, "INATIVO", TipoCondicaoWhere.Texto);
/*     */     }
/*     */     
/*  49 */     listaMaterialPreco = dao.listar(condicao);
/*  50 */     return listaMaterialPreco;
/*     */   }
/*     */   
/*     */ 
/*     */   public List<MaterialPreco> listarPorTabela(TabelaPreco pTabelaPreco, Situacao pSituacao)
/*     */   {
/*  56 */     if (pTabelaPreco == null) {
/*  57 */       TabelaPrecoService tabelaPrecoService = new TabelaPrecoService();
/*  58 */       pTabelaPreco = tabelaPrecoService.buscarTabelaPrincipal();
/*     */     }
/*     */     
/*  61 */     MaterialPrecoDAO dao = new MaterialPrecoDAO();
/*     */     
/*     */ 
/*  64 */     dao.popuparPrecos(pTabelaPreco.getSeqEmpresa());
/*     */     
/*  66 */     List<MaterialPreco> listaMaterialPreco = new ArrayList();
/*  67 */     ClausulaWhere condicao = new ClausulaWhere();
/*     */     
/*  69 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "material_preco.seq_tabela_preco", GeneroCondicaoWhere.igual, String.valueOf(pTabelaPreco.getSeqTabelaPreco()), TipoCondicaoWhere.Numero);
/*     */     
/*  71 */     if (pSituacao == Situacao.ATIVO) {
/*  72 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "material_preco.situacao", GeneroCondicaoWhere.igual, "ATIVO", TipoCondicaoWhere.Texto);
/*  73 */     } else if (pSituacao == Situacao.INATIVO) {
/*  74 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "material_preco.situacao", GeneroCondicaoWhere.igual, "INATIVO", TipoCondicaoWhere.Texto);
/*     */     }
/*     */     
/*  77 */     listaMaterialPreco = dao.listar(condicao);
/*  78 */     return listaMaterialPreco;
/*     */   }
/*     */   
/*     */   public List<MaterialPreco> listarTabelaPrincipalAtivos(String pSeqEmpresa)
/*     */   {
/*  83 */     MaterialPrecoDAO dao = new MaterialPrecoDAO();
/*     */     
/*  85 */     List<MaterialPreco> listaMaterialPreco = new ArrayList();
/*  86 */     ClausulaWhere condicao = new ClausulaWhere();
/*     */     
/*  88 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "material_preco.seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(pSeqEmpresa), TipoCondicaoWhere.Numero);
/*  89 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "material_preco.situacao", GeneroCondicaoWhere.igual, "ATIVO", TipoCondicaoWhere.Texto);
/*  90 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "tabela_preco.principal", GeneroCondicaoWhere.igual, "SIM", TipoCondicaoWhere.Texto);
/*     */     
/*  92 */     listaMaterialPreco = dao.listar(condicao);
/*  93 */     return listaMaterialPreco;
/*     */   }
/*     */   
/*     */   public List<MaterialPreco> listarPorParceiro(String pSeqEmpresa, String pSeqParceiro) {
/*  97 */     EmpresaService empresaService = new EmpresaService();
/*  98 */     Empresa empresa = empresaService.buscarEmpresaPorID(pSeqEmpresa);
/*  99 */     List<MaterialPreco> retorno = new ArrayList();
/*     */     
/* 101 */     if (empresa.getIntegracao().equals("CROSS"))
/*     */     {
/* 103 */       MaterialPrecoDAO dao = new MaterialPrecoDAO();
/*     */       
/* 105 */       ClausulaWhere condicao = new ClausulaWhere();
/*     */       
/* 107 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "material_preco.seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(pSeqEmpresa), TipoCondicaoWhere.Numero);
/* 108 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "material_preco.situacao", GeneroCondicaoWhere.igual, "ATIVO", TipoCondicaoWhere.Texto);
/* 109 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "parceiro.seq_parceiro", GeneroCondicaoWhere.igual, pSeqParceiro, TipoCondicaoWhere.Numero);
/*     */       
/* 111 */       retorno = dao.listarComParceiro(condicao);
/* 112 */     } 
/*     */     
/*     */ 
/*     */ 
/* 119 */     return retorno;
/*     */   }
/*     */   
/*     */   public List<MaterialPreco> listarGrupoPorParceiro(String pSeqEmpresa, String pSeqParceiro) {
/* 123 */     EmpresaService empresaService = new EmpresaService();
/* 124 */     Empresa empresa = empresaService.buscarEmpresaPorID(pSeqEmpresa);
/* 125 */     List<MaterialPreco> retorno = new ArrayList();
/*     */     
/* 127 */     if (empresa.getIntegracao().equals("CROSS")) {
/* 128 */       MaterialPrecoDAO dao = new MaterialPrecoDAO();
/*     */       
/* 130 */       ClausulaWhere condicao = new ClausulaWhere();
/*     */       
/* 132 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "SEQ_EMPRESA", GeneroCondicaoWhere.igual, String.valueOf(pSeqEmpresa), TipoCondicaoWhere.Numero);
/*     */       
/* 134 */       retorno = dao.listarGruposComParceiro(condicao);
/* 135 */     } 
/* 139 */     return retorno;
/*     */   }
/*     */   
/* 142 */   public List<MaterialPreco> listarNomeGrupoServico(String pSeqEmpresa,String pReferencia , String pSeqParceiro) { List<MaterialPreco> retorno = new ArrayList();
/*     */     
/* 144 */     MaterialPrecoDAO dao = new MaterialPrecoDAO();
/*     */     
/* 146 */     ClausulaWhere condicao = new ClausulaWhere();
/*     */     
/* 148 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "material.SEQ_EMPRESA", GeneroCondicaoWhere.igual, String.valueOf(pSeqEmpresa), TipoCondicaoWhere.Numero);
/* 149  */    condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "material.REFERENCIA", GeneroCondicaoWhere.igual, String.valueOf(pReferencia), TipoCondicaoWhere.Texto);
/* 150 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "material_preco.seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(pSeqEmpresa), TipoCondicaoWhere.Numero);
/* 151 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "material_preco.situacao", GeneroCondicaoWhere.igual, "ATIVO", TipoCondicaoWhere.Texto);
/* 152 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "parceiro.seq_parceiro", GeneroCondicaoWhere.igual, pSeqParceiro, TipoCondicaoWhere.Texto);
/*     */     
/* 154 */     retorno = dao.listarServicoNome(condicao);
/*     */     
/* 156 */     return retorno;
/*     */   }
/*     */   
/* 159 */   public List<MaterialPreco> listarTodosServicos(String pSeqEmpresa, String pSeqParceiro) { List<MaterialPreco> retorno = new ArrayList();
/*     */     
/* 161 */     MaterialPrecoDAO dao = new MaterialPrecoDAO();
/*     */     
/* 163 */     ClausulaWhere condicao = new ClausulaWhere();
/*     */     
/* 165 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "material.SEQ_EMPRESA", GeneroCondicaoWhere.igual, String.valueOf(pSeqEmpresa), TipoCondicaoWhere.Numero);
/* 166 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "material_preco.seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(pSeqEmpresa), TipoCondicaoWhere.Numero);
/* 167 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "material_preco.situacao", GeneroCondicaoWhere.igual, "ATIVO", TipoCondicaoWhere.Texto);
/* 168 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "parceiro.seq_parceiro", GeneroCondicaoWhere.igual, pSeqParceiro, TipoCondicaoWhere.Texto);
/*     */     
/* 170 */     retorno = dao.listarServicoNome(condicao);
/*     */     
/* 172 */     return retorno;
/*     */   }
/*     */ }
