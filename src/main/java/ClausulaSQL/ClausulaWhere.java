/*     */ package ClausulaSQL;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;

/*     */ public class ClausulaWhere
/*     */ {
/*  17 */   List<CondicaoWhere> condicoes = new ArrayList();
/*  18 */   List<String> cs = new ArrayList();
/*     */   
/*     */   public void AdicionarCondicao(CondicaoWhere condicao) {
/*  21 */     this.condicoes.add(condicao);
/*     */   }
/*     */   
/*     */   public String montarsClausula() {
/*  25 */     String cd = " ";
/*  26 */     for (int i = 0; i < this.cs.size(); i++) {
/*  27 */       cd = cd + (String)this.cs.get(i);
/*     */     }
/*  29 */     return cd;
/*     */   }
/*     */   
/*     */   public void AdicionarCondicaoManual(String condicao)
/*     */   {
/*  34 */     this.cs.add(condicao);
/*     */   }
/*     */   
/*     */   public void AdicionarCondicao(OperacaoCondicaoWhere operacao, String campo, GeneroCondicaoWhere genero, String valor, TipoCondicaoWhere tipo) {
/*  38 */     CondicaoWhere c = new CondicaoWhere();
/*  39 */     c.setCampo(campo);
/*  40 */     c.setValor(valor);
/*  41 */     c.setGenero(genero);
/*  42 */     c.setTipo(tipo);
/*  43 */     c.setOperacao(operacao);
/*  44 */     this.condicoes.add(c);
/*     */     
/*  46 */     String cdc = " ";
/*  47 */     if (operacao.equals(OperacaoCondicaoWhere.and)) {
/*  48 */       cdc = cdc + " and ";
/*  49 */     } else if (operacao.equals(OperacaoCondicaoWhere.or)) {
/*  50 */       cdc = cdc + " or ";
/*  51 */     } else if (operacao.equals(OperacaoCondicaoWhere.vazio)) {
/*  52 */       cdc = cdc + " where ";
/*     */     }
/*     */     
/*  55 */     String vValor = " ";
/*  56 */     if (tipo.equals(TipoCondicaoWhere.Texto))
/*     */     {
/*  58 */       cdc = cdc + "upper(" + campo + ")";
/*     */       
/*  60 */       vValor = "'" + valor.toUpperCase() + "'";
/*  61 */     } else if (tipo.equals(TipoCondicaoWhere.Numero)) {
/*  62 */       cdc = cdc + campo;
/*  63 */       vValor = valor;
/*  64 */     } else if (tipo.equals(TipoCondicaoWhere.Data)) {
/*  65 */       cdc = cdc + "trunc(" + campo + ")";
/*  66 */       vValor = "trunc(to_date('" + valor + "','dd/mm/yyyy)'))";
/*     */     }
/*     */     
/*  69 */     if (genero.equals(GeneroCondicaoWhere.igual)) {
/*  70 */       cdc = cdc + " = " + vValor;
/*  71 */     } else if (genero.equals(GeneroCondicaoWhere.maior)) {
/*  72 */       cdc = cdc + " > " + vValor;
/*  73 */     } else if (genero.equals(GeneroCondicaoWhere.menor)) {
/*  74 */       cdc = cdc + " < " + vValor;
/*  75 */     } else if (genero.equals(GeneroCondicaoWhere.contem)) {
/*  76 */       cdc = cdc + " like ('%" + vValor.replaceAll("'", "").replaceAll(" ", "%") + "%')";
/*  77 */     } else if (genero.equals(GeneroCondicaoWhere.ComecaPor)) {
/*  78 */       cdc = cdc + " like ('" + vValor.replaceAll("'", "") + "%')";
/*  79 */     } else if (genero.equals(GeneroCondicaoWhere.TerminaPor)) {
/*  80 */       cdc = cdc + " like ('%" + vValor.replaceAll("'", "") + "')";
/*  81 */     } else if (genero.equals(GeneroCondicaoWhere.diferente)) {
/*  82 */       cdc = cdc + " <> " + vValor;
/*  83 */     } else if (genero.equals(GeneroCondicaoWhere.isNull)) {
/*  84 */       cdc = cdc + " is null ";
/*  85 */     } else if (genero.equals(GeneroCondicaoWhere.isNotNull)) {
/*  86 */       cdc = cdc + " is not null ";
/*  87 */     } else if (genero.equals(GeneroCondicaoWhere.MaiorIgual)) {
/*  88 */       cdc = cdc + " >= " + vValor;
/*  89 */     } else if (genero.equals(GeneroCondicaoWhere.MenorIgual)) {
/*  90 */       cdc = cdc + " <= " + vValor;
/*     */     }
/*  92 */     this.cs.add(cdc);
/*     */   }
/*     */   
/*     */   public List<CondicaoWhere> condicoes() {
/*  96 */     return this.condicoes;
/*     */   }
/*     */   
/*     */   public List<CondicaoWhere> getCondicoes() {
/* 100 */     return this.condicoes;
/*     */   }
/*     */   
/*     */   public void setCondicoes(List<CondicaoWhere> condicoes) {
/* 104 */     this.condicoes = condicoes;
/*     */   }
/*     */   
/*     */   public List<String> getCs() {
/* 108 */     return this.cs;
/*     */   }
/*     */   
/*     */   public void setCs(List<String> cs) {
/* 112 */     this.cs = cs;
/*     */   }
/*     */ }
