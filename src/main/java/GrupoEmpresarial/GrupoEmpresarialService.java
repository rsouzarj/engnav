/*    */ package GrupoEmpresarial;
 
/*    */ import ClausulaSQL.ClausulaWhere;
/*    */ import ClausulaSQL.GeneroCondicaoWhere;
/*    */ import ClausulaSQL.OperacaoCondicaoWhere;
/*    */ import ClausulaSQL.TipoCondicaoWhere;
/*    */ import Util.Situacao;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Date;
/*    */ import java.util.List;

/*    */ public class GrupoEmpresarialService
/*    */ {
/*    */   public GrupoEmpresarial salvar(GrupoEmpresarial grupoEmpresarial)
/*    */   {
/* 23 */     GrupoEmpresarialDAO dao = new GrupoEmpresarialDAO();
/* 24 */     if (grupoEmpresarial.getSeqGrupo() == null) {
/* 25 */       grupoEmpresarial.setDataCadastro(new Date());
/* 26 */       return dao.inserir(grupoEmpresarial);
/*    */     }
/* 28 */     return dao.alterar(grupoEmpresarial);
/*    */   }
/*    */   
/*    */   public List<GrupoEmpresarial> listar(String pSeqEmpresa, String pString, Situacao pSituacao)
/*    */   {
/* 36 */     GrupoEmpresarialDAO dao = new GrupoEmpresarialDAO();
/* 37 */     List<GrupoEmpresarial> listaGrupoEmpresarial = new ArrayList();
/* 38 */     ClausulaWhere condicao = new ClausulaWhere();
/* 39 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(pSeqEmpresa), TipoCondicaoWhere.Numero);
/* 40 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "nome", GeneroCondicaoWhere.contem, pString, TipoCondicaoWhere.Texto);
/*    */     
/* 42 */     if (pSituacao == Situacao.ATIVO) {
/* 43 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "situacao", GeneroCondicaoWhere.igual, "ATIVO", TipoCondicaoWhere.Texto);
/* 44 */     } else if (pSituacao == Situacao.INATIVO) {
/* 45 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "situacao", GeneroCondicaoWhere.igual, "INATIVO", TipoCondicaoWhere.Texto);
/*    */     }
/*    */     
/* 48 */     listaGrupoEmpresarial = dao.listar(condicao);
/* 49 */     return listaGrupoEmpresarial;
/*    */   }

/*    */   public List<GrupoEmpresarial> listarP(String pSeqEmpresa, Situacao pSituacao)
/*    */   {
/* 36 */     GrupoEmpresarialDAO dao = new GrupoEmpresarialDAO();
/* 37 */     List<GrupoEmpresarial> listaGrupoEmpresarial = new ArrayList();
/* 38 */     ClausulaWhere condicao = new ClausulaWhere();
/* 39 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(pSeqEmpresa), TipoCondicaoWhere.Numero);
/* 42 */     if (pSituacao == Situacao.ATIVO) {
/* 43 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "situacao", GeneroCondicaoWhere.igual, "ATIVO", TipoCondicaoWhere.Texto);
/* 44 */     } else if (pSituacao == Situacao.INATIVO) {
/* 45 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "situacao", GeneroCondicaoWhere.igual, "INATIVO", TipoCondicaoWhere.Texto);
/*    */     }
/*    */     
/* 48 */     listaGrupoEmpresarial = dao.listar(condicao);
/* 49 */     return listaGrupoEmpresarial;
/*    */   }
/*    */   
/*    */   public boolean deletar(GrupoEmpresarial grupoEmpresarial) {
/* 53 */     GrupoEmpresarialDAO dao = new GrupoEmpresarialDAO();
/* 54 */     return dao.deletar(grupoEmpresarial);
/*    */   }
/*    */ }
