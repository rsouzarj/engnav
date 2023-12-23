/*    */ package ClausulaSQL;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CondicaoWhere
/*    */ {
/*    */   String campo;
/*    */   
/*    */ 
/*    */   String valor;
/*    */   
/*    */ 
/*    */   GeneroCondicaoWhere genero;
/*    */   
/*    */   TipoCondicaoWhere tipo;
/*    */   
/*    */   OperacaoCondicaoWhere operacao;
/*    */   
/*    */ 
/*    */   public void AdicionarCondicao(String campo, GeneroCondicaoWhere genero, String valor, TipoCondicaoWhere tipo, OperacaoCondicaoWhere operacao)
/*    */   {
/* 22 */     this.campo = campo;
/* 23 */     this.valor = valor;
/* 24 */     this.genero = genero;
/* 25 */     this.tipo = tipo;
/* 26 */     this.operacao = operacao;
/*    */   }
/*    */   
/*    */   public String getCampo() {
/* 30 */     return this.campo;
/*    */   }
/*    */   
/*    */   public void setCampo(String campo) {
/* 34 */     this.campo = campo;
/*    */   }
/*    */   
/*    */   public GeneroCondicaoWhere getGenero() {
/* 38 */     return this.genero;
/*    */   }
/*    */   
/*    */   public void setGenero(GeneroCondicaoWhere genero) {
/* 42 */     this.genero = genero;
/*    */   }
/*    */   
/*    */   public TipoCondicaoWhere getTipo() {
/* 46 */     return this.tipo;
/*    */   }
/*    */   
/*    */   public void setTipo(TipoCondicaoWhere tipo) {
/* 50 */     this.tipo = tipo;
/*    */   }
/*    */   
/*    */   public String getValor() {
/* 54 */     return this.valor;
/*    */   }
/*    */   
/*    */   public void setValor(String valor) {
/* 58 */     this.valor = valor;
/*    */   }
/*    */   
/*    */   public OperacaoCondicaoWhere getOperacao() {
/* 62 */     return this.operacao;
/*    */   }
/*    */   
/*    */   public void setOperacao(OperacaoCondicaoWhere operacao) {
/* 66 */     this.operacao = operacao;
/*    */   }
/*    */ }

