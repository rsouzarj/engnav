/*    */ package Equipamento;
/*    */ 
/*    */ import ClausulaSQL.ClausulaWhere;
/*    */ import ClausulaSQL.GeneroCondicaoWhere;
/*    */ import ClausulaSQL.OperacaoCondicaoWhere;
/*    */ import ClausulaSQL.TipoCondicaoWhere;
/*    */ import Util.Situacao;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Date;
/*    */ import java.util.List;

/*    */ public class EquipamentoService
/*    */ {
/*    */   public Equipamento salvar(Equipamento equipamento)
/*    */   {
/* 23 */     EquipamentoDAO dao = new EquipamentoDAO();
/* 24 */     if (equipamento.getSeqEquipamento() == null) {
/* 25 */       equipamento.setDataCadastro(new Date());
/* 26 */       return dao.inserir(equipamento);
/*    */     }
/* 28 */     return dao.alterar(equipamento);
/*    */   }
/*    */   
/*    */   public List<Equipamento> listar(String pSeqEmpresa, String pString, Situacao pSituacao)
/*    */   {
/* 33 */     EquipamentoDAO dao = new EquipamentoDAO();
/* 34 */     List<Equipamento> listaEquipamento = new ArrayList();
/* 35 */     ClausulaWhere condicao = new ClausulaWhere();
/*    */     
/* 37 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "equipamento.nome", GeneroCondicaoWhere.contem, pString, TipoCondicaoWhere.Texto);
/* 38 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "equipamento.seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(pSeqEmpresa), TipoCondicaoWhere.Numero);
/*    */     
/* 40 */     if (pSituacao == Situacao.ATIVO) {
/* 41 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "equipamento.situacao", GeneroCondicaoWhere.igual, "ATIVO", TipoCondicaoWhere.Texto);
/* 42 */     } else if (pSituacao == Situacao.INATIVO) {
/* 43 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "equipamento.situacao", GeneroCondicaoWhere.igual, "INATIVO", TipoCondicaoWhere.Texto);
/*    */     }
/*    */     
/* 46 */     listaEquipamento = dao.listar(condicao);
/* 47 */     return listaEquipamento;
/*    */   }

/*    */   public List<Equipamento> listarFin(String pSeqEmpresa, String pString, Situacao pSituacao,String pTipo)
/*    */   {
/* 33 */     EquipamentoDAO dao = new EquipamentoDAO();
/* 34 */     List<Equipamento> listaEquipamento = new ArrayList();
/* 35 */     ClausulaWhere condicao = new ClausulaWhere();
/*    */     
/* 37 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "equipamento.nome", GeneroCondicaoWhere.contem, pString, TipoCondicaoWhere.Texto);
             condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "equipamento.tipo_equipamento", GeneroCondicaoWhere.igual, "Financeiro", TipoCondicaoWhere.Texto);
/* 38 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "equipamento.seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(pSeqEmpresa), TipoCondicaoWhere.Numero);
/*    */     
/* 40 */     if (pSituacao == Situacao.ATIVO) {
/* 41 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "situacao", GeneroCondicaoWhere.igual, "ATIVO", TipoCondicaoWhere.Texto);
/* 42 */     } else if (pSituacao == Situacao.INATIVO) {
/* 43 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "situacao", GeneroCondicaoWhere.igual, "INATIVO", TipoCondicaoWhere.Texto);
/*    */     }
/*    */     
/* 46 */     listaEquipamento = dao.listar(condicao);
/* 47 */     return listaEquipamento;
/*    */   }

/*    */   public List<Equipamento> listarequipamento(String pSeqEmpresa, String pSeqEquipamento, Situacao pSituacao)
/*    */   {
/* 33 */     EquipamentoDAO dao = new EquipamentoDAO();
/* 34 */     List<Equipamento> listaEquipamento = new ArrayList();
/* 35 */     ClausulaWhere condicao = new ClausulaWhere();
/*    */     
/* 37 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "equipamento.nome", GeneroCondicaoWhere.igual, String.valueOf(pSeqEquipamento), TipoCondicaoWhere.Numero);
/* 38 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "equipamento.seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(pSeqEmpresa), TipoCondicaoWhere.Numero);
/*    */     
/* 40 */     if (pSituacao == Situacao.ATIVO) {
/* 41 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "situacao", GeneroCondicaoWhere.igual, "ATIVO", TipoCondicaoWhere.Texto);
/* 42 */     } else if (pSituacao == Situacao.INATIVO) {
/* 43 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "situacao", GeneroCondicaoWhere.igual, "INATIVO", TipoCondicaoWhere.Texto);
/*    */     }
/*    */     
/* 46 */     listaEquipamento = dao.listar(condicao);
/* 47 */     return listaEquipamento;
/*    */   }

/*    */   
/*    */   public List<Equipamento> listarPorParceiro(String pSeqEmpresa, String pSeqParceiro, Situacao pSituacao) {
/* 51 */     EquipamentoDAO dao = new EquipamentoDAO();
/* 52 */     List<Equipamento> listaEquipamento = new ArrayList();
/* 53 */     ClausulaWhere condicao = new ClausulaWhere();
/*    */     
/* 55 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "equipamento_parceiro.seq_parceiro", GeneroCondicaoWhere.igual, pSeqParceiro, TipoCondicaoWhere.Numero);
/* 56 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "equipamento.seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(pSeqEmpresa), TipoCondicaoWhere.Numero);
/*    */     
/* 58 */     if (pSituacao == Situacao.ATIVO) {
/* 59 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "equipamento.situacao", GeneroCondicaoWhere.igual, "ATIVO", TipoCondicaoWhere.Texto);
/* 60 */     } else if (pSituacao == Situacao.INATIVO) {
/* 61 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "equipamento.situacao", GeneroCondicaoWhere.igual, "INATIVO", TipoCondicaoWhere.Texto);
/*    */     }
/*    */     
/* 64 */     listaEquipamento = dao.listarParceiro(condicao);
/* 65 */     return listaEquipamento;
/*    */   }

/*    */   public List<Equipamento> listarEQPParceiro(ClausulaWhere sClausula) {
/* 51 */     EquipamentoDAO dao = new EquipamentoDAO();
/* 52 */      return dao.listar(sClausula);
           }
/*    */   
/*    */   public List<Equipamento> listarPorDono(String pSeqEmpresa, String pSeqDono, Situacao pSituacao) {
/* 69 */     EquipamentoDAO dao = new EquipamentoDAO();
/* 70 */     List<Equipamento> listaEquipamento = new ArrayList();
/* 71 */     ClausulaWhere condicao = new ClausulaWhere();
/*    */     
/* 73 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "equipamento.seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(pSeqEmpresa), TipoCondicaoWhere.Numero);
/* 74 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "documento_item_equipamento.seq_documento", GeneroCondicaoWhere.igual, String.valueOf(pSeqDono), TipoCondicaoWhere.Numero);
/*    */     
/* 76 */     if (pSituacao == Situacao.ATIVO) {
/* 77 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "equipamento.situacao", GeneroCondicaoWhere.igual, "ATIVO", TipoCondicaoWhere.Texto);
/* 78 */     } else if (pSituacao == Situacao.INATIVO) {
/* 79 */       condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "equipamento.situacao", GeneroCondicaoWhere.igual, "INATIVO", TipoCondicaoWhere.Texto);
/*    */     }
/*    */     
/* 82 */     listaEquipamento = dao.listarPorDono(condicao);
/* 83 */     return listaEquipamento;
/*    */   }
/*    */   
/*    */   public boolean deletar(Equipamento equipamento) {
/* 87 */     EquipamentoDAO dao = new EquipamentoDAO();
/* 88 */     return dao.deletar(equipamento);
/*    */   }
/*    */ 
           

           public Equipamento buscar(String pSeqEquipamento) {
/* 53 */     EquipamentoDAO dao = new EquipamentoDAO();
/* 54 */     List<Equipamento> listaEquipamento = new ArrayList();
/* 55 */     ClausulaWhere condicao = new ClausulaWhere();
/*    */     
/* 57 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "equipamento.seq_equipamento", GeneroCondicaoWhere.igual, String.valueOf(pSeqEquipamento), TipoCondicaoWhere.Numero);
/*    */     
/* 59 */     listaEquipamento = dao.listar(condicao);
/* 60 */     return (Equipamento)listaEquipamento.get(0);
/*    */   }
           
/*    */   public List<Equipamento> listar3(ClausulaWhere sClausula) {
/* 67 */     EquipamentoDAO dao = new EquipamentoDAO();
/* 68 */     return dao.listarParceiro(sClausula);
/*    */   }           
           
           
}

