/*    */ package Material;
/*    */ 
/*    */ import ClausulaSQL.ClausulaWhere;
/*    */ import ClausulaSQL.GeneroCondicaoWhere;
/*    */ import ClausulaSQL.OperacaoCondicaoWhere;
/*    */ import ClausulaSQL.TipoCondicaoWhere;
/*    */ import Util.Situacao;
/*    */ import java.math.BigDecimal;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MaterialService
/*    */ {
/*    */   public Material salvar(Material material)
/*    */   {
/* 24 */     MaterialDAO dao = new MaterialDAO();
/* 25 */     if (material.getSeqMaterial() == null) {
/* 26 */       material.setDataCadastro(new Date());
/* 27 */       material.setQtdeEstoque(BigDecimal.ZERO);
/* 28 */       dao.inserir(material);
/* 29 */       return material;
/*    */     }
/* 31 */     dao.alterar(material);
/* 32 */     return material;
/*    */   }
/*    */   
/*    */   public List<Material> listar(String pSeqEmpresa, String pString, Situacao pSituacao)
/*    */   {
/* 37 */     MaterialDAO dao = new MaterialDAO();
/* 38 */     List<Material> listaMaterial = new ArrayList();
/* 39 */     ClausulaWhere condicao = new ClausulaWhere();
/*    */     
/*    */ 
/*    */ 
/* 43 */     condicao.AdicionarCondicaoManual("where seq_empresa = " + pSeqEmpresa + " and (upper(nome) like('%" + pString + "%') or upper(referencia) like('%" + pString + "%'))");
/*    */     
/* 45 */     if (pSituacao == Situacao.ATIVO) {
/* 46 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "situacao", GeneroCondicaoWhere.igual, "ATIVONP", TipoCondicaoWhere.Texto);
/* 47 */     } else if (pSituacao == Situacao.INATIVO) {
/* 48 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "situacao", GeneroCondicaoWhere.igual, "INATIVO", TipoCondicaoWhere.Texto);
/*    */     }
/*    */     
/*    */ 
/* 52 */     listaMaterial = dao.listar(condicao);
/* 53 */     return listaMaterial;
/*    */   }
/*    */   
/* 56 */   public List<Material> listarPorReferencia(String pSeqEmpresa, String pReferencia, Situacao pSituacao) { MaterialDAO dao = new MaterialDAO();
/* 57 */     List<Material> listaMaterial = new ArrayList();
/* 58 */     ClausulaWhere condicao = new ClausulaWhere();
/*    */     
/* 60 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "referencia", GeneroCondicaoWhere.contem, pReferencia, TipoCondicaoWhere.Texto);
/* 61 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(pSeqEmpresa), TipoCondicaoWhere.Numero);
/*    */     
/* 63 */     if (pSituacao == Situacao.ATIVO) {
/* 64 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "situacao", GeneroCondicaoWhere.igual, "ATIVONP", TipoCondicaoWhere.Texto);
/* 65 */     } else if (pSituacao == Situacao.INATIVO) {
/* 66 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "situacao", GeneroCondicaoWhere.igual, "INATIVO", TipoCondicaoWhere.Texto);
/*    */     }
/*    */     
/*    */ 
/* 70 */     listaMaterial = dao.listar(condicao);
/* 71 */     return listaMaterial;
/*    */   }
/*    */   
/*    */   public boolean deletar(Material material) {
/* 75 */     MaterialDAO dao = new MaterialDAO();
/* 76 */     return dao.deletar(material);
/*    */   }
/*    */ }
