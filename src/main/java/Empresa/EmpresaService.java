/*    */ package Empresa;
/*    */ 
/*    */ import ClausulaSQL.ClausulaWhere;
/*    */ import ClausulaSQL.GeneroCondicaoWhere;
/*    */ import ClausulaSQL.OperacaoCondicaoWhere;
/*    */ import ClausulaSQL.TipoCondicaoWhere;
/*    */ import Util.Situacao;
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
/*    */ 
/*    */ public class EmpresaService
/*    */ {
/*    */   public Empresa salvar(Empresa pEmpresa)
/*    */   {
/* 24 */     EmpresaDAO dao = new EmpresaDAO();
/* 25 */     if (pEmpresa.getSeqEmpresa() == null) {
/* 26 */       pEmpresa.setDataCadastro(new Date());
/* 27 */       return dao.inserir(pEmpresa);
/*    */     }
/* 29 */     return dao.alterar(pEmpresa);
/*    */   }
/*    */   
/*    */   public List<Empresa> listar(String pSeqEmpresa, String pString, Situacao pSituacao)
/*    */   {
/* 34 */     EmpresaDAO dao = new EmpresaDAO();
/* 35 */     List<Empresa> listaEmpresa = new ArrayList();
/* 36 */     ClausulaWhere condicao = new ClausulaWhere();
/*    */     
/* 38 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "nome", GeneroCondicaoWhere.contem, pString, TipoCondicaoWhere.Texto);
/* 39 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(pSeqEmpresa), TipoCondicaoWhere.Numero);
/*    */     
/* 41 */     if (pSituacao == Situacao.ATIVO) {
/* 42 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "situacao", GeneroCondicaoWhere.igual, "ATIVO", TipoCondicaoWhere.Texto);
/* 43 */     } else if (pSituacao == Situacao.INATIVO) {
/* 44 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "situacao", GeneroCondicaoWhere.igual, "INATIVO", TipoCondicaoWhere.Texto);
/*    */     }
/*    */     
/* 47 */     listaEmpresa = dao.listar(condicao);
/* 48 */     return listaEmpresa;
/*    */   }
/*    */   
/*    */   public Empresa buscarEmpresaPorChave(String pChave) {
/* 52 */     EmpresaDAO dao = new EmpresaDAO();
/* 53 */     List<Empresa> listaEmpresa = new ArrayList();
/* 54 */     ClausulaWhere condicao = new ClausulaWhere();
/*    */     
/* 56 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "chave", GeneroCondicaoWhere.igual, "RJ", TipoCondicaoWhere.Texto);
/*    */     
/* 58 */     listaEmpresa = dao.listar(condicao);
/* 59 */     return (Empresa)listaEmpresa.get(0);
/*    */   }
/*    */   
/*    */   public Empresa buscarEmpresaPorID(String pSeqEmpresa) {
/* 63 */     EmpresaDAO dao = new EmpresaDAO();
/* 64 */     List<Empresa> listaEmpresa = new ArrayList();
/* 65 */     ClausulaWhere condicao = new ClausulaWhere();
/*    */     
/* 67 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(pSeqEmpresa), TipoCondicaoWhere.Numero);
/*    */     
/* 69 */     listaEmpresa = dao.listar(condicao);
/* 70 */     return (Empresa)listaEmpresa.get(0);
/*    */   }
/*    */   
/*    */   public boolean deletar(Empresa empresa) {
/* 74 */     EmpresaDAO dao = new EmpresaDAO();
/* 75 */     return dao.deletar(empresa);
/*    */   }
/*    */ }


/* Location:              /Users/diogo.lima/Documents/PEDIDO.jar!/Empresa/EmpresaService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */