/*     */ package Util;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Stack;

/*     */ 
/*     */ public class CurrencyWriter
/*     */ {
/*  20 */   private static final BigInteger THOUSAND = new BigInteger("1000");
/*  21 */   private static final BigInteger HUNDRED = new BigInteger("100");
/*     */   
/*     */ 
/*     */ 
/*     */   private static final String CENTO = "cento";
/*     */   
/*     */ 
/*     */ 
/*     */   private static final String CEM = "cem";
/*     */   
/*     */ 
/*     */ 
/*  33 */   private final Map<Integer, String> grandezasPlural = new HashMap();
/*  34 */   private final Map<Integer, String> grandezasSingular = new HashMap();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  39 */   private final Map<Integer, String> nomes = new HashMap();
/*     */   
/*     */ 
/*     */   private static final String MOEDA_SINGULAR = "real";
/*     */   
/*     */   private static final String MOEDA_PLURAL = "reais";
/*     */   
/*     */   private static final String FRACAO_SINGULAR = "centavo";
/*     */   
/*     */   private static final String FRACAO_PLURAL = "centavos";
/*     */   
/*     */   private static final String PARTICULA_ADITIVA = "e";
/*     */   
/*     */   private static final String PARTICULA_DESCRITIVA = "de";
/*     */   
/*  54 */   private static final BigDecimal MAX_SUPPORTED_VALUE = new BigDecimal("999999999999999999999999999.99");
/*     */   
/*  56 */   private static CurrencyWriter instance = null;
/*     */   
/*     */   public static CurrencyWriter getInstance() {
/*  59 */     if (instance == null) {
/*  60 */       instance = new CurrencyWriter();
/*     */     }
/*     */     
/*  63 */     return instance;
/*     */   }
/*     */   
/*     */   private CurrencyWriter() {
/*  67 */     preencherGrandezasPlural();
/*  68 */     preencherGrandezasSingular();
/*  69 */     preencherNomes();
/*     */   }
/*     */   
/*     */   public String write(BigDecimal amount) {
/*  73 */     if (null == amount) {
/*  74 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  81 */     BigDecimal value = amount.setScale(2, 6);
/*     */     
/*  83 */     if (value.compareTo(BigDecimal.ZERO) <= 0) {
/*  84 */       return "";
/*     */     }
/*     */     
/*  87 */     if (MAX_SUPPORTED_VALUE.compareTo(value) < 0) {
/*  88 */       throw new IllegalArgumentException("Valor acima do limite suportado.");
/*     */     }
/*     */     
/*  91 */     Stack<Integer> decomposed = decompose(value);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  98 */     int expoente = 3 * (decomposed.size() - 2);
/*     */     
/* 100 */     StringBuffer sb = new StringBuffer();
/* 101 */     int lastNonZeroExponent = -1;
/*     */     
/* 103 */     while (!decomposed.empty()) {
/* 104 */       int valor = ((Integer)decomposed.pop()).intValue();
/*     */       
/* 106 */       if (valor > 0) {
/* 107 */         sb.append(" ").append("e").append(" ");
/* 108 */         sb.append(comporNomeGrupos(valor));
/* 109 */         String nomeGrandeza = obterNomeGrandeza(expoente, valor);
/* 110 */         if (nomeGrandeza.length() > 0) {
/* 111 */           sb.append(" ");
/*     */         }
/* 113 */         sb.append(nomeGrandeza);
/*     */         
/* 115 */         lastNonZeroExponent = expoente;
/*     */       }
/*     */       
/* 118 */       switch (expoente) {
/*     */       case 0: 
/* 120 */         BigInteger parteInteira = value.toBigInteger();
/*     */         
/* 122 */         if (BigInteger.ONE.equals(parteInteira)) {
/* 123 */           sb.append(" ").append("real");
/* 124 */         } else if (parteInteira.compareTo(BigInteger.ZERO) > 0) {
/* 125 */           if (lastNonZeroExponent >= 6) {
/* 126 */             sb.append(" ").append("de");
/*     */           }
/* 128 */           sb.append(" ").append("reais");
/*     */         }
/*     */         
/*     */         break;
/*     */       case -3: 
/* 133 */         if (1 == valor) {
/* 134 */           sb.append(" ").append("centavo");
/* 135 */         } else if (valor > 1) {
/* 136 */           sb.append(" ").append("centavos");
/*     */         }
/*     */         break;
/*     */       }
/*     */       
/* 141 */       expoente -= 3;
/*     */     }
/*     */     
/* 144 */     return sb.substring(3);
/*     */   }
/*     */   
/*     */   private StringBuffer comporNomeGrupos(int valor) {
/* 148 */     StringBuffer nome = new StringBuffer();
/*     */     
/* 150 */     int centenas = valor - valor % 100;
/* 151 */     int unidades = valor % 10;
/* 152 */     int dezenas = valor - centenas - unidades;
/* 153 */     int duasCasas = dezenas + unidades;
/*     */     
/* 155 */     if (centenas > 0) {
/* 156 */       nome.append(" ").append("e").append(" ");
/*     */       
/* 158 */       if (100 == centenas) {
/* 159 */         if (duasCasas > 0) {
/* 160 */           nome.append("cento");
/*     */         } else {
/* 162 */           nome.append("cem");
/*     */         }
/*     */       } else {
/* 165 */         nome.append((String)this.nomes.get(Integer.valueOf(centenas)));
/*     */       }
/*     */     }
/*     */     
/* 169 */     if (duasCasas > 0) {
/* 170 */       nome.append(" ").append("e").append(" ");
/* 171 */       if (duasCasas < 20) {
/* 172 */         nome.append((String)this.nomes.get(Integer.valueOf(duasCasas)));
/*     */       } else {
/* 174 */         if (dezenas > 0) {
/* 175 */           nome.append((String)this.nomes.get(Integer.valueOf(dezenas)));
/*     */         }
/*     */         
/* 178 */         if (unidades > 0) {
/* 179 */           nome.append(" ").append("e").append(" ");
/* 180 */           nome.append((String)this.nomes.get(Integer.valueOf(unidades)));
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 185 */     return nome.delete(0, 3);
/*     */   }
/*     */   
/*     */   private String obterNomeGrandeza(int exponent, int value) {
/* 189 */     if (exponent < 3) {
/* 190 */       return "";
/*     */     }
/*     */     
/* 193 */     if (1 == value) {
/* 194 */       return (String)this.grandezasSingular.get(Integer.valueOf(exponent));
/*     */     }
/* 196 */     return (String)this.grandezasPlural.get(Integer.valueOf(exponent));
/*     */   }
/*     */   
/*     */   private Stack<Integer> decompose(BigDecimal value)
/*     */   {
/* 201 */     BigInteger intermediate = value.multiply(new BigDecimal(100)).toBigInteger();
/* 202 */     Stack<Integer> decomposed = new Stack();
/*     */     
/* 204 */     BigInteger[] result = intermediate.divideAndRemainder(HUNDRED);
/* 205 */     intermediate = result[0];
/* 206 */     decomposed.add(Integer.valueOf(result[1].intValue()));
/*     */     
/* 208 */     while (intermediate.compareTo(BigInteger.ZERO) > 0) {
/* 209 */       result = intermediate.divideAndRemainder(THOUSAND);
/* 210 */       intermediate = result[0];
/* 211 */       decomposed.add(Integer.valueOf(result[1].intValue()));
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 218 */     if (decomposed.size() == 1) {
/* 219 */       decomposed.add(Integer.valueOf(0));
/*     */     }
/*     */     
/* 222 */     return decomposed;
/*     */   }
/*     */   
/*     */   private void preencherGrandezasPlural() {
/* 226 */     this.grandezasPlural.put(Integer.valueOf(3), "mil");
/* 227 */     this.grandezasPlural.put(Integer.valueOf(6), "milhões");
/* 228 */     this.grandezasPlural.put(Integer.valueOf(9), "bilhões");
/* 229 */     this.grandezasPlural.put(Integer.valueOf(12), "trilhões");
/* 230 */     this.grandezasPlural.put(Integer.valueOf(15), "quatrilhões");
/* 231 */     this.grandezasPlural.put(Integer.valueOf(18), "quintilhões");
/* 232 */     this.grandezasPlural.put(Integer.valueOf(21), "sextilhões");
/* 233 */     this.grandezasPlural.put(Integer.valueOf(24), "setilhões");
/*     */   }
/*     */   
/*     */   private void preencherGrandezasSingular() {
/* 237 */     this.grandezasSingular.put(Integer.valueOf(3), "mil");
/* 238 */     this.grandezasSingular.put(Integer.valueOf(6), "milhão");
/* 239 */     this.grandezasSingular.put(Integer.valueOf(9), "bilhão");
/* 240 */     this.grandezasSingular.put(Integer.valueOf(12), "trilhão");
/* 241 */     this.grandezasSingular.put(Integer.valueOf(15), "quatrilhão");
/* 242 */     this.grandezasSingular.put(Integer.valueOf(18), "quintilhão");
/* 243 */     this.grandezasSingular.put(Integer.valueOf(21), "sextilhão");
/* 244 */     this.grandezasSingular.put(Integer.valueOf(24), "setilhão");
/*     */   }
/*     */   
/*     */   private void preencherNomes() {
/* 248 */     this.nomes.put(Integer.valueOf(1), "um");
/* 249 */     this.nomes.put(Integer.valueOf(2), "dois");
/* 250 */     this.nomes.put(Integer.valueOf(3), "três");
/* 251 */     this.nomes.put(Integer.valueOf(4), "quatro");
/* 252 */     this.nomes.put(Integer.valueOf(5), "cinco");
/* 253 */     this.nomes.put(Integer.valueOf(6), "seis");
/* 254 */     this.nomes.put(Integer.valueOf(7), "sete");
/* 255 */     this.nomes.put(Integer.valueOf(8), "oito");
/* 256 */     this.nomes.put(Integer.valueOf(9), "nove");
/* 257 */     this.nomes.put(Integer.valueOf(10), "dez");
/* 258 */     this.nomes.put(Integer.valueOf(11), "onze");
/* 259 */     this.nomes.put(Integer.valueOf(12), "doze");
/* 260 */     this.nomes.put(Integer.valueOf(13), "treze");
/* 261 */     this.nomes.put(Integer.valueOf(14), "quatorze");
/* 262 */     this.nomes.put(Integer.valueOf(15), "quinze");
/* 263 */     this.nomes.put(Integer.valueOf(16), "dezesseis");
/* 264 */     this.nomes.put(Integer.valueOf(17), "dezessete");
/* 265 */     this.nomes.put(Integer.valueOf(18), "dezoito");
/* 266 */     this.nomes.put(Integer.valueOf(19), "dezenove");
/* 267 */     this.nomes.put(Integer.valueOf(20), "vinte");
/* 268 */     this.nomes.put(Integer.valueOf(30), "trinta");
/* 269 */     this.nomes.put(Integer.valueOf(40), "quarenta");
/* 270 */     this.nomes.put(Integer.valueOf(50), "cinquenta");
/* 271 */     this.nomes.put(Integer.valueOf(60), "sessenta");
/* 272 */     this.nomes.put(Integer.valueOf(70), "setenta");
/* 273 */     this.nomes.put(Integer.valueOf(80), "oitenta");
/* 274 */     this.nomes.put(Integer.valueOf(90), "noventa");
/* 275 */     this.nomes.put(Integer.valueOf(200), "duzentos");
/* 276 */     this.nomes.put(Integer.valueOf(300), "trezentos");
/* 277 */     this.nomes.put(Integer.valueOf(400), "quatrocentos");
/* 278 */     this.nomes.put(Integer.valueOf(500), "quinhentos");
/* 279 */     this.nomes.put(Integer.valueOf(600), "seiscentos");
/* 280 */     this.nomes.put(Integer.valueOf(700), "setecentos");
/* 281 */     this.nomes.put(Integer.valueOf(800), "oitocentos");
/* 282 */     this.nomes.put(Integer.valueOf(900), "novecentos");
/*     */   }
/*     */ }

