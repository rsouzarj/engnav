/*    */ package NvEmbarcacaoUsuario;
/*    */ 

/*    */ import ClausulaSQL.ClausulaWhere;
/*    */ import ClausulaSQL.GeneroCondicaoWhere;
/*    */ import ClausulaSQL.OperacaoCondicaoWhere;
/*    */ import ClausulaSQL.TipoCondicaoWhere;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;

/*    */ public class NvEmbarcacaoUsuarioService
/*    */ {
/*    */   public NvEmbarcacaoUsuario salvar(NvEmbarcacaoUsuario nvEmbarcacaoUsuario)
/*    */   {
/* 21 */     NvEmbarcacaoUsuarioDAO dao = new NvEmbarcacaoUsuarioDAO();
/* 22 */     if (nvEmbarcacaoUsuario.getSeqNvEmbarcacaoUsuario() == null) {
/* 23 */       return dao.inserir(nvEmbarcacaoUsuario);
/*    */     }
/* 25 */     return dao.alterar(nvEmbarcacaoUsuario);
/*    */   }
/*    */   
/*    */   public List<NvEmbarcacaoUsuario> listarPorEmbarcacao(String pSeqEmbarcacao)
/*    */   {
/* 30 */     NvEmbarcacaoUsuarioDAO dao = new NvEmbarcacaoUsuarioDAO();
/* 31 */     List<NvEmbarcacaoUsuario> listaNvEmbarcacaoUsuario = new ArrayList();
/* 32 */     ClausulaWhere condicao = new ClausulaWhere();
/*    */     
/* 34 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "NV_EMBARCACAO_USUARIO.seq_embarcacao", GeneroCondicaoWhere.igual, String.valueOf(pSeqEmbarcacao), TipoCondicaoWhere.Numero);
/*    */     
/* 36 */     listaNvEmbarcacaoUsuario = dao.listar(condicao);
/* 37 */     return listaNvEmbarcacaoUsuario;
/*    */   }
/*    */   
/*    */   public List<NvEmbarcacaoUsuario> listarPorUsuario(String pSeqUsuario) {
/* 41 */     NvEmbarcacaoUsuarioDAO dao = new NvEmbarcacaoUsuarioDAO();
/* 42 */     List<NvEmbarcacaoUsuario> listaNvEmbarcacaoUsuario = new ArrayList();
/* 43 */     ClausulaWhere condicao = new ClausulaWhere();
/*    */     
/* 45 */     condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "NV_EMBARCACAO_USUARIO.seq_psuario", GeneroCondicaoWhere.igual, String.valueOf(pSeqUsuario), TipoCondicaoWhere.Numero);
/*    */     
/* 47 */     listaNvEmbarcacaoUsuario = dao.listar(condicao);
/* 48 */     return listaNvEmbarcacaoUsuario;
/*    */   }

/*    */   public boolean deletar(NvEmbarcacaoUsuario nvEmbarcacaoUsuario) {
/* 63 */     NvEmbarcacaoUsuarioDAO dao = new NvEmbarcacaoUsuarioDAO();
/* 64 */     return dao.deletar(nvEmbarcacaoUsuario);
/*    */   }
/*    */ }
