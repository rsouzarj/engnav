/*     */ package CRUD;
/*     */ 
/*     */ import Util.Conexao;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.sql.Connection;
/*     */ import java.sql.Date;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;

/*     */ public class CriarCRUD
/*     */ {
/*     */   public static void main(String[] args)
/*     */     throws IOException
/*     */   {
/*  32 */     String tabela = "financeiro_ocorrencia".toUpperCase();
/*     */   }

/*     */   public void criarDAO(String pTabela)
/*     */     throws IOException
/*     */   {
/*  44 */     CriarCRUD crud = new CriarCRUD();
/*     */     
/*  46 */     List<Campo> listaCampo = crud.listarCampos(pTabela);
/*  47 */     Campo campo = (Campo)listaCampo.get(0);
/*  48 */     List<String> codigo = new ArrayList();
/*     */     
/*     */ 
/*  51 */     String CamposInsert = "";
/*  52 */     String ConteudoInsert = "";
/*  53 */     for (int i = 0; i < listaCampo.size(); i++) {
/*  54 */       CamposInsert = CamposInsert + ((Campo)listaCampo.get(i)).getNome();
/*  55 */       ConteudoInsert = ConteudoInsert + "?";
/*  56 */       if (listaCampo.size() != i + 1) {
/*  57 */         CamposInsert = CamposInsert + ",";
/*  58 */         ConteudoInsert = ConteudoInsert + ",";
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  63 */     String CamposUpdate = "";
/*  64 */     for (int i = 1; i < listaCampo.size(); i++) {
/*  65 */       CamposUpdate = CamposUpdate + ((Campo)listaCampo.get(i)).getNome() + " = ?";
/*  66 */       if (listaCampo.size() != i + 1) {
/*  67 */         CamposUpdate = CamposUpdate + ",";
/*     */       }
/*     */     }
/*     */     
/*  71 */     codigo.add("public " + campo.getClasse() + " inserir(" + campo.getClasse() + " " + campo.getInstancia() + " ) {");
/*  72 */     codigo.add("try {");
/*     */     
/*  74 */     codigo.add("String seq = Sequence.buscarNumeroSequence(\"SEQ_" + pTabela + "\");");
/*  75 */     codigo.add(campo.getInstancia() + ".set" + campo.atributoGetSet + "(seq);");
/*  76 */     codigo.add("Conexao conexao = new Conexao();");
/*  77 */     codigo.add("Connection conn = conexao.getConnection();");
/*  78 */     codigo.add("String sql = \"insert into " + pTabela + "\" +");
/*     */     
/*  80 */     codigo.add("\" (" + CamposInsert + ")" + "\"");
/*  81 */     codigo.add("+ \" values \"+");
/*  82 */     codigo.add("\" (" + ConteudoInsert + ")" + "\"" + ";");
/*  83 */     codigo.add("PreparedStatement ps = conn.prepareStatement(sql);");
/*  84 */     codigo.add("");
/*     */     
/*  86 */     for (int i = 0; i < listaCampo.size(); i++)
/*     */     {
/*  88 */       if (((Campo)listaCampo.get(i)).getTipo().equals("Date")) {
/*  89 */         codigo.add("try{");
/*  90 */         codigo.add("ps.set" + ((Campo)listaCampo.get(i)).getTipo() + "(" + String.valueOf(i + 1) + ",new java.sql.Date(" + ((Campo)listaCampo.get(i)).getInstancia() + ".get" + ((Campo)listaCampo.get(i)).getAtributoGetSet() + "().getTime()));");
/*  91 */         codigo.add("} catch (NullPointerException e) {");
/*  92 */         codigo.add("ps.setDate(" + String.valueOf(i + 1) + ", null);");
/*  93 */         codigo.add("}");
/*     */       } else {
/*  95 */         codigo.add("ps.set" + ((Campo)listaCampo.get(i)).getTipo() + "(" + String.valueOf(i + 1) + "," + ((Campo)listaCampo.get(i)).getInstancia() + ".get" + ((Campo)listaCampo.get(i)).getAtributoGetSet() + "());");
/*     */       }
/*     */     }
/*     */     
/*  99 */     codigo.add("");
/*     */     
/* 101 */     codigo.add("ps.execute();");
/*     */     
/* 103 */     codigo.add("ps.close();");
/*     */     
/* 105 */     codigo.add("");
/*     */     
/* 107 */     codigo.add("} catch (SQLException ex) {");
/*     */     
/* 109 */     codigo.add("Logger.getLogger(" + campo.classe + "DAO.class.getName()).log(Level.SEVERE, null, ex);");
/*     */     
/* 111 */     codigo.add("System.out.println(ex.getMessage());");
/*     */     
/* 113 */     codigo.add("}");
/*     */     
/* 115 */     codigo.add("return " + campo.getInstancia() + ";");
/*     */     
/* 117 */     codigo.add("}");
/*     */     
/* 119 */     codigo.add("");
/*     */     
/*     */ 
/*     */ 
/* 123 */     codigo.add("public " + campo.getClasse() + " alterar(" + campo.getClasse() + " " + campo.getInstancia() + ") {");
/*     */     
/* 125 */     codigo.add("        try {");
/*     */     
/* 127 */     codigo.add("        Conexao conexao = new Conexao();");
/*     */     
/* 129 */     codigo.add("        Connection conn = conexao.getConnection();");
/*     */     
/* 131 */     codigo.add("        String sql = \"update " + pTabela + " set " + CamposUpdate + " where " + campo.nome + " = ?" + "\";");
/*     */     
/* 133 */     codigo.add("        ");
/*     */     
/* 135 */     codigo.add("        PreparedStatement ps = conn.prepareStatement(sql);");
/*     */     
/* 137 */     codigo.add("        ");
/*     */     
/*     */ 
/* 140 */     for (int i = 1; 
/* 141 */         i < listaCampo.size(); 
/* 142 */         i++)
/*     */     {
/* 144 */       if (((Campo)listaCampo.get(i)).getTipo().equals("Date")) {
/* 145 */         codigo.add("try{");
/* 146 */         codigo.add("ps.set" + ((Campo)listaCampo.get(i)).getTipo() + "(" + String.valueOf(i) + ",new java.sql.Date(" + ((Campo)listaCampo.get(i)).getInstancia() + ".get" + ((Campo)listaCampo.get(i)).getAtributoGetSet() + "().getTime()));");
/* 147 */         codigo.add("} catch (NullPointerException e) {");
/* 148 */         codigo.add("ps.setDate(" + String.valueOf(i) + ", null);");
/* 149 */         codigo.add("}");
/*     */       }
/*     */       else {
/* 152 */         codigo.add("ps.set" + ((Campo)listaCampo.get(i)).getTipo() + "(" + String.valueOf(i) + "," + ((Campo)listaCampo.get(i)).getInstancia() + ".get" + ((Campo)listaCampo.get(i)).getAtributoGetSet() + "());");
/*     */       }
/*     */     }
/*     */     
/* 156 */     codigo.add("ps.set" + campo.getTipo() + "(" + String.valueOf(listaCampo.size()) + "," + campo.getInstancia() + ".get" + campo.getAtributoGetSet() + "());");
/*     */     
/*     */ 
/* 159 */     codigo.add("        ps.execute();");
/*     */     
/* 161 */     codigo.add("        ps.close();");
/*     */     
/*     */ 
/* 164 */     codigo.add("        ");
/*     */     
/* 166 */     codigo.add("        } catch (SQLException ex) {");
/*     */     
/* 168 */     codigo.add("Logger.getLogger(" + campo.classe + "DAO.class.getName()).log(Level.SEVERE, null, ex);");
/*     */     
/* 170 */     codigo.add("        System.out.println(ex.getMessage());");
/*     */     
/* 172 */     codigo.add("        }");
/*     */     
/* 174 */     codigo.add("        ");
/*     */     
/* 176 */     codigo.add("return " + campo.getInstancia() + ";");
/*     */     
/* 178 */     codigo.add("            }        ");
/*     */     
/*     */ 
/*     */ 
/* 182 */     codigo.add("public List<" + campo.getClasse() + "> listar (ClausulaWhere sClausula) {");
/*     */     
/* 184 */     codigo.add("try {");
/*     */     
/* 186 */     codigo.add("Conexao conexao = new Conexao();");
/*     */     
/* 188 */     codigo.add("Connection conn = conexao.getConnection();");
/*     */     
/* 190 */     codigo.add("String sql = \"SELECT * FROM " + pTabela + "\" + sClausula.montarsClausula();");
/*     */     
/* 192 */     codigo.add("System.out.println(sql);");
/*     */     
/* 194 */     codigo.add("");
/*     */     
/* 196 */     codigo.add("List<" + campo.getClasse() + "> lista" + campo.getClasse() + " = new ArrayList<" + campo.getClasse() + ">();");
/*     */     
/* 198 */     codigo.add("PreparedStatement ps = conn.prepareStatement(sql);");
/*     */     
/* 200 */     codigo.add("ResultSet rs = ps.executeQuery();");
/*     */     
/* 202 */     codigo.add("");
/*     */     
/* 204 */     codigo.add("while (rs.next()) {");
/*     */     
/* 206 */     codigo.add(campo.getClasse() + " " + campo.getInstancia() + " = new " + campo.getClasse() + "();");
/*     */     
/* 208 */     for (int i = 0; 
/* 209 */         i < listaCampo.size(); 
/* 210 */         i++) {
/* 211 */       codigo.add(campo.getInstancia() + ".set" + ((Campo)listaCampo.get(i)).getAtributoGetSet() + "(rs.get" + ((Campo)listaCampo.get(i)).getTipo() + "(" + "\"" + ((Campo)listaCampo.get(i)).getNome() + "\"" + "));");
/*     */     }
/*     */     
/* 214 */     codigo.add("lista" + campo.getClasse() + ".add(" + campo.getInstancia() + ");");
/*     */     
/*     */ 
/* 217 */     codigo.add("}");
/*     */     
/* 219 */     codigo.add("");
/*     */     
/* 221 */     codigo.add("            ps.execute();");
/*     */     
/* 223 */     codigo.add("            ps.close();");
/*     */     
/* 225 */     codigo.add("");
/*     */     
/* 227 */     codigo.add("            return lista" + campo.getClasse() + ";");
/*     */     
/* 229 */     codigo.add("} catch (SQLException ex) {");
/*     */     
/* 231 */     codigo.add("Logger.getLogger(" + campo.classe + "DAO.class.getName()).log(Level.SEVERE, null, ex);");
/*     */     
/* 233 */     codigo.add("System.out.println(ex.getMessage());                     // Print da mensagem de tratamento de erro.");
/*     */     
/* 235 */     codigo.add("return null;");
/*     */     
/* 237 */     codigo.add("}");
/*     */     
/* 239 */     codigo.add("}");
/*     */     
/*     */ 
/*     */ 
/* 243 */     codigo.add("public boolean deletar(" + campo.getClasse() + " " + campo.getInstancia() + ") {");
/*     */     
/* 245 */     codigo.add("try {");
/*     */     
/* 247 */     codigo.add("Conexao conexao = new Conexao();");
/*     */     
/* 249 */     codigo.add("Connection conn = conexao.getConnection();");
/*     */     
/* 251 */     codigo.add("String sql = \"DELETE FROM " + pTabela + " WHERE " + campo.getNome() + " =  ? " + "\";");
/*     */     
/* 253 */     codigo.add("");
/*     */     
/* 255 */     codigo.add("PreparedStatement ps = conn.prepareStatement(sql);");
/*     */     
/* 257 */     codigo.add("");
/*     */     
/* 259 */     codigo.add("ps.setString(1," + campo.getInstancia() + ".get" + campo.getAtributoGetSet() + "());");
/*     */     
/* 261 */     codigo.add("");
/*     */     
/* 263 */     codigo.add("            ps.execute();");
/*     */     
/* 265 */     codigo.add("            ps.close();");
/*     */     
/* 267 */     codigo.add("");
/*     */     
/* 269 */     codigo.add("            return true;");
/*     */     
/* 271 */     codigo.add("} catch (SQLException ex) {");
/*     */     
/* 273 */     codigo.add("System.out.println(ex.getMessage());");
/*     */     
/* 275 */     codigo.add("return false;");
/*     */     
/* 277 */     codigo.add("}");
/*     */     
/* 279 */     codigo.add("}");
/*     */     
/*     */ 
/* 282 */     String caminho = System.getProperty("user.dir") + "\\src\\" + campo.classe;
/*     */     
/* 284 */     System.out.println(caminho);
/*     */     
/* 286 */     System.out.println("******** DAO*****************");
/*     */     
/*     */ 
/* 289 */     File diretorio = new File(caminho);
/*     */     
/* 291 */     diretorio.mkdir();
/*     */     
/* 293 */     FileWriter fw = new FileWriter(caminho + "\\" + campo.classe + "DAO.java");
/* 294 */     BufferedWriter bw = new BufferedWriter(fw);
/*     */     
/* 296 */     bw.newLine();
/*     */     
/* 298 */     bw.write("/***********************************************************************************/");
/*     */     
/* 300 */     bw.newLine();
/*     */     
/* 302 */     bw.write("/*CRIADO DE FORMA AUTOMATICA*/");
/*     */     
/* 304 */     bw.newLine();
/*     */     
/* 306 */     bw.write("/*Data:  " + String.valueOf(new Date(System.currentTimeMillis())) + " */");
/*     */     
/* 308 */     bw.newLine();
/*     */     
/* 310 */     bw.write("/***********************************************************************************/");
/*     */     
/* 312 */     bw.newLine();
/*     */     
/* 314 */     bw.write("package " + campo.getClasse() + ";");
/*     */     
/*     */ 
/* 317 */     bw.newLine();
/*     */     
/* 319 */     bw.write("public class " + campo.classe + "DAO {");
/*     */     
/* 321 */     bw.newLine();
/*     */     
/* 323 */     bw.newLine();
/*     */     
/* 325 */     System.out.println("");
/*     */     
/* 327 */     for (int i = 0; 
/* 328 */         i < codigo.size(); 
/* 329 */         i++) {
/* 330 */       bw.write((String)codigo.get(i));
/* 331 */       bw.newLine();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 336 */     bw.write("}");
/*     */     
/*     */ 
/* 339 */     bw.close();
/*     */     
/* 341 */     fw.close();
/*     */     
/* 343 */     System.out.println("");
/*     */     
/* 345 */     System.out.println("*******************************");
/*     */   }
/*     */   
/*     */ 
/*     */   public void criarBean(String pTabela)
/*     */     throws IOException
/*     */   {
/* 352 */     CriarCRUD crud = new CriarCRUD();
/* 353 */     List<Campo> listaCampo = new ArrayList();
/* 354 */     listaCampo = crud.listarCampos(pTabela);
/* 355 */     Campo campo = (Campo)listaCampo.get(0);
/* 356 */     List<String> codigo = new ArrayList();
/*     */     
/* 358 */     for (int i = 0; i < listaCampo.size(); i++) {
/* 359 */       codigo.add("private " + ((Campo)listaCampo.get(i)).getTipo() + " " + ((Campo)listaCampo.get(i)).getAtributo() + ";");
/*     */     }
/* 361 */     codigo.add("");
/* 362 */     codigo.add("// Criar GETs e SETs");
/*     */     
/* 364 */     System.out.println("******** BEAN *****************");
/* 365 */     System.out.println("");
/*     */     
/* 367 */     String caminho = System.getProperty("user.dir") + "\\src\\" + campo.classe;
/* 368 */     System.out.println(caminho);
/*     */     
/* 370 */     File diretorio = new File(caminho);
/* 371 */     diretorio.mkdir();
/*     */     
/* 373 */     FileWriter fw = new FileWriter(caminho + "\\" + campo.classe + ".java");
/* 374 */     BufferedWriter bw = new BufferedWriter(fw);
/* 375 */     bw.newLine();
/* 376 */     bw.write("/***********************************************************************************/");
/* 377 */     bw.newLine();
/* 378 */     bw.write("/*CRIADO DE FORMA AUTOMATICA*/");
/* 379 */     bw.newLine();
/* 380 */     bw.write("/*Data:  " + String.valueOf(new Date(System.currentTimeMillis())) + " */");
/* 381 */     bw.newLine();
/* 382 */     bw.write("/***********************************************************************************/");
/* 383 */     bw.newLine();
/* 384 */     bw.write("package " + campo.getClasse() + ";");
/*     */     
/* 386 */     bw.newLine();
/* 387 */     bw.write("public class " + campo.classe + " {");
/* 388 */     bw.newLine();
/* 389 */     bw.newLine();
/* 390 */     for (int i = 0; i < codigo.size(); i++) {
/* 391 */       bw.write((String)codigo.get(i));
/* 392 */       bw.newLine();
/*     */     }
/*     */     
/* 395 */     bw.write("}");
/*     */     
/* 397 */     bw.close();
/* 398 */     fw.close();
/* 399 */     System.out.println("");
/* 400 */     System.out.println("*******************************");
/*     */   }
/*     */   
/*     */   public void criarService(String pTabela) throws IOException
/*     */   {
/* 405 */     CriarCRUD crud = new CriarCRUD();
/*     */     
/* 407 */     List<Campo> listaCampo = crud.listarCampos(pTabela);
/* 408 */     Campo campo = (Campo)listaCampo.get(0);
/* 409 */     List<String> codigo = new ArrayList();
/*     */     
/* 411 */     codigo.add("public " + campo.classe + " salvar(" + campo.classe + " " + campo.instancia + "){ ");
/* 412 */     codigo.add(campo.getClasse() + "DAO dao = new " + campo.getClasse() + "DAO();");
/* 413 */     codigo.add("if (" + campo.instancia + ".get" + campo.getAtributoGetSet() + "() == null) { ");
/* 414 */     codigo.add(campo.getInstancia() + ".setDataCadastro(new Date());");
/* 415 */     codigo.add("return dao.inserir(" + campo.getInstancia() + " ); ");
/* 416 */     codigo.add("} else { ");
/* 417 */     codigo.add("return dao.alterar(" + campo.getInstancia() + "); ");
/* 418 */     codigo.add("} ");
/* 419 */     codigo.add("} ");
/* 420 */     codigo.add(" ");
/* 421 */     codigo.add("   ");
/* 422 */     codigo.add("public List<" + campo.getClasse() + "> listar(String pSeqEmpresa, String pString, Situacao pSituacao) { ");
/* 423 */     codigo.add(campo.getClasse() + "DAO dao = new " + campo.getClasse() + "DAO();");
/* 424 */     codigo.add("List<" + campo.getClasse() + " > lista" + campo.getClasse() + " = new ArrayList<" + campo.getClasse() + ">(); ");
/* 425 */     codigo.add("ClausulaWhere condicao = new ClausulaWhere(); ");
/* 426 */     codigo.add("");
/* 427 */     codigo.add("condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, \"nome\"  , GeneroCondicaoWhere.contem, pString, TipoCondicaoWhere.Texto); ");
/* 428 */     codigo.add("condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, \"seq_empresa\"  , GeneroCondicaoWhere.igual, String.valueOf(pSeqEmpresa), TipoCondicaoWhere.Numero); ");
/* 429 */     codigo.add("");
/* 430 */     codigo.add("");
/* 431 */     codigo.add("if (pSituacao == Situacao.ATIVO){");
/* 432 */     codigo.add("condicao.AdicionarCondicao(OperacaoCondicaoWhere.and,\"situacao\", GeneroCondicaoWhere.igual, \"ATIVO\", TipoCondicaoWhere.Texto);");
/* 433 */     codigo.add("} else if (pSituacao == Situacao.INATIVO){");
/* 434 */     codigo.add("condicao.AdicionarCondicao(OperacaoCondicaoWhere.and,\"situacao\", GeneroCondicaoWhere.igual, \"INATIVO\", TipoCondicaoWhere.Texto);");
/* 435 */     codigo.add("}");
/* 436 */     codigo.add("");
/* 437 */     codigo.add("lista" + campo.getClasse() + " = dao.listar(condicao); ");
/* 438 */     codigo.add("return lista" + campo.getClasse() + "; ");
/* 439 */     codigo.add("} ");
/*     */     
/* 441 */     codigo.add("public boolean deletar(" + campo.getClasse() + " " + campo.getInstancia() + ") {");
/* 442 */     codigo.add(campo.getClasse() + "DAO dao = new " + campo.getClasse() + "DAO();");
/* 443 */     codigo.add("return dao.deletar(" + campo.getInstancia() + ");");
/* 444 */     codigo.add("}");
/*     */     
/* 446 */     System.out.println("******** SERVICE *****************");
/* 447 */     System.out.println("");
/*     */     
/* 449 */     String caminho = System.getProperty("user.dir") + "\\src\\" + campo.classe;
/* 450 */     System.out.println(caminho);
/*     */     
/* 452 */     File diretorio = new File(caminho);
/* 453 */     diretorio.mkdir();
/*     */     
/* 455 */     FileWriter fw = new FileWriter(caminho + "\\" + campo.classe + "Service.java");
/* 456 */     BufferedWriter bw = new BufferedWriter(fw);
/* 457 */     bw.newLine();
/* 458 */     bw.write("/***********************************************************************************/");
/* 459 */     bw.newLine();
/* 460 */     bw.write("/*CRIADO DE FORMA AUTOMATICA*/");
/* 461 */     bw.newLine();
/* 462 */     bw.write("/*Data:  " + String.valueOf(new Date(System.currentTimeMillis())) + " */");
/* 463 */     bw.newLine();
/* 464 */     bw.write("/***********************************************************************************/");
/* 465 */     bw.newLine();
/* 466 */     bw.write("package " + campo.getClasse() + ";");
/*     */     
/* 468 */     bw.newLine();
/* 469 */     bw.write("public class " + campo.classe + "Service {");
/* 470 */     bw.newLine();
/* 471 */     bw.newLine();
/* 472 */     for (int i = 0; i < codigo.size(); i++) {
/* 473 */       bw.write((String)codigo.get(i));
/* 474 */       bw.newLine();
/*     */     }
/*     */     
/* 477 */     bw.write("}");
/*     */     
/* 479 */     bw.close();
/* 480 */     fw.close();
/* 481 */     System.out.println("");
/* 482 */     System.out.println("*******************************");
/*     */   }
/*     */   
/*     */   public void criarController(String pTabela) throws IOException
/*     */   {
/* 487 */     CriarCRUD crud = new CriarCRUD();
/*     */     
/* 489 */     List<Campo> listaCampo = crud.listarCampos(pTabela);
/* 490 */     Campo campo = (Campo)listaCampo.get(0);
/* 491 */     List<String> codigo = new ArrayList();
/*     */     
/* 493 */     codigo.add("@ManagedProperty(value = \"#{loginController}\") ");
/* 494 */     codigo.add("protected LoginController loginController; ");
/* 495 */     codigo.add(campo.getClasse() + "Service " + campo.getInstancia() + "Service = new " + campo.getClasse() + "Service(); ");
/* 496 */     codigo.add(campo.getClasse() + " " + campo.getInstancia() + " = new " + campo.getClasse() + "();");
/* 497 */     codigo.add("List<" + campo.getClasse() + " > lista" + campo.getClasse() + " = new ArrayList<" + campo.getClasse() + ">(); ");
/* 498 */     codigo.add("String pesquisa =  \"\";");
/* 499 */     codigo.add("Integer tela = 0; ");
/* 500 */     codigo.add(" ");
/* 501 */     codigo.add("    public void iniciar(){");
/* 502 */     codigo.add("    }");
/* 503 */     codigo.add(" ");
/* 504 */     codigo.add("public void salvar(int pTela) { ");
/* 505 */     codigo.add(campo.instancia + ".setSeqEmpresa(loginController.getEmpresa().getSeqEmpresa());");
/* 506 */     codigo.add(campo.instancia + " = " + campo.instancia + "Service.salvar(" + campo.getInstancia() + ");");
/*     */     
/* 508 */     codigo.add(" ");
/* 509 */     codigo.add(" ");
/* 510 */     codigo.add("FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, \"Registro armazenado com sucesso.\",\"\")); ");
/* 511 */     codigo.add("tela = pTela; ");
/* 512 */     codigo.add("} ");
/* 513 */     codigo.add(" ");
/* 514 */     codigo.add("public void novo() { ");
/* 515 */     codigo.add(campo.getInstancia() + " = new " + campo.getClasse() + "(); ");
/* 516 */     codigo.add("tela = 1; ");
/* 517 */     codigo.add("} ");
/* 518 */     codigo.add(" ");
/* 519 */     codigo.add("public void listar() { ");
/* 520 */     codigo.add("lista" + campo.getClasse() + " = " + campo.getInstancia() + "Service.listar(loginController.getEmpresa().getSeqEmpresa(), pesquisa,Situacao.TODOS); ");
/*     */     
/* 522 */     codigo.add("} ");
/* 523 */     codigo.add(" ");
/* 524 */     codigo.add("public void deletar() { ");
/* 525 */     codigo.add("if (" + campo.getInstancia() + "Service.deletar(" + campo.getInstancia() + " )) { ");
/* 526 */     codigo.add("listar(); ");
/* 527 */     codigo.add(campo.getInstancia() + " = new " + campo.getClasse() + "(); ");
/* 528 */     codigo.add("FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, \"Registro eliminado com sucesso.\",\"\")); ");
/* 529 */     codigo.add("tela = 0; ");
/* 530 */     codigo.add("listar(); ");
/* 531 */     codigo.add("} else { ");
/*     */     
/* 533 */     codigo.add("FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, \"Falha ao excluir registro.\",\"\")); ");
/*     */     
/* 535 */     codigo.add("} ");
/* 536 */     codigo.add("} ");
/* 537 */     codigo.add(" ");
/* 538 */     codigo.add("public void fecharTela() throws IOException { ");
/* 539 */     codigo.add("loginController.mudarPagina(\"/principal/ principal.jsf\"); ");
/* 540 */     codigo.add("} ");
/* 541 */     codigo.add(" ");
/* 542 */     codigo.add("public void selecionar(" + campo.classe + " p" + campo.getClasse() + ") { ");
/* 543 */     codigo.add(campo.getInstancia() + " = p" + campo.getClasse() + "; ");
/* 544 */     codigo.add("tela = 1; ");
/* 545 */     codigo.add("} ");
/* 546 */     codigo.add(" ");
/* 547 */     codigo.add("public void mudarTela(Integer pTela) { ");
/* 548 */     codigo.add("tela = pTela; ");
/* 549 */     codigo.add("}         ");
/*     */     
/* 551 */     System.out.println("******** CONTROLLER *****************");
/* 552 */     System.out.println("");
/*     */     
/* 554 */     String caminho = System.getProperty("user.dir") + "\\src\\" + campo.classe;
/* 555 */     System.out.println(caminho);
/*     */     
/* 557 */     File diretorio = new File(caminho);
/* 558 */     diretorio.mkdir();
/*     */     
/* 560 */     FileWriter fw = new FileWriter(caminho + "\\" + campo.classe + "Controller.java");
/* 561 */     BufferedWriter bw = new BufferedWriter(fw);
/* 562 */     bw.newLine();
/* 563 */     bw.write("/***********************************************************************************/");
/* 564 */     bw.newLine();
/* 565 */     bw.write("/*CRIADO DE FORMA AUTOMATICA*/");
/* 566 */     bw.newLine();
/* 567 */     bw.write("/*Data:  " + String.valueOf(new Date(System.currentTimeMillis())) + " */");
/* 568 */     bw.newLine();
/* 569 */     bw.write("/***********************************************************************************/");
/* 570 */     bw.newLine();
/* 571 */     bw.write("package " + campo.getClasse() + ";");
/* 572 */     bw.newLine();
/* 573 */     bw.write("@ManagedBean(name = \"" + campo.instancia + "Controller" + "\"" + ") ");
/* 574 */     bw.write("@ViewScoped ");
/* 575 */     bw.write("public class " + campo.classe + "Controller { ");
/* 576 */     bw.newLine();
/* 577 */     bw.newLine();
/* 578 */     for (int i = 0; i < codigo.size(); i++) {
/* 579 */       bw.write((String)codigo.get(i));
/* 580 */       bw.newLine();
/*     */     }
/*     */     
/* 583 */     bw.newLine();
/* 584 */     bw.newLine();
/* 585 */     bw.write("/* GET´s SET´s*/");
/* 586 */     bw.newLine();
/* 587 */     bw.newLine();
/* 588 */     bw.write("}");
/*     */     
/* 590 */     bw.close();
/* 591 */     fw.close();
/* 592 */     System.out.println("");
/* 593 */     System.out.println("*******************************");
/*     */   }
/*     */   
/*     */   public List<Campo> listarCampos(String pTabela)
/*     */   {
/*     */     try {
/* 599 */       Conexao conexao = new Conexao();
/* 600 */       Connection conn = Conexao.getConnection();
/* 601 */       String sql = "SELECT decode(COLUMN_NAME,'SEQ_" + pTabela + "','0','1') ordem, " + "COLUMN_NAME nome," + "lower(substr(replace(initCap(COLUMN_NAME),'_',''),1,1))||substr(replace(initCap(COLUMN_NAME),'_',''),2,length(replace(initCap(COLUMN_NAME),'_',''))) atributo," + "replace(initCap(COLUMN_NAME),'_','') atributoGetSet," + "lower(substr(replace(initCap(TABLE_NAME),'_',''),1,1))||substr(replace(initCap(TABLE_NAME),'_',''),2,length(replace(initCap(TABLE_NAME),'_',''))) instancia," + "Replace(initCap(TABLE_NAME),'_','') classe," + "       DATA_TYPE tipo, \n" + "       DATA_LENGTH tamanho, \n" + "       DATA_PRECISION precisao \n" + "  FROM user_TAB_COLUMNS \n" + "WHERE UPPER(TABLE_NAME)= '" + pTabela + "'" + "order by 1";

/* 614 */       System.out.println(sql);
/*     */       
/* 616 */       PreparedStatement ps = conn.prepareStatement(sql);
/* 617 */       ResultSet rs = ps.executeQuery();
/* 618 */       List<Campo> listaCampo = new ArrayList();
/*     */       
/* 620 */       while (rs.next()) {
/* 621 */         Campo campo = new Campo();
/* 622 */         campo.setNome(rs.getString("nome"));
/* 623 */         campo.setAtributo(rs.getString("atributo"));
/* 624 */         campo.setInstancia(rs.getString("instancia"));
/* 625 */         campo.setClasse(rs.getString("CLASSE"));
/* 626 */         campo.setAtributoGetSet(rs.getString("atributoGetSet"));
/*     */         
/* 628 */         if ((rs.getString("TIPO").equals("VARCHAR2")) || (rs.getString("TIPO").equals("NCLOB"))) {
/* 629 */           campo.setTipo("String");
/* 630 */         } else if (rs.getString("TIPO").equals("DATE")) {
/* 631 */           campo.setTipo("Date");
/* 632 */         } else if (rs.getString("TIPO").equals("NUMBER")) {
/* 633 */           if (rs.getString("PRECISAO") == null) {
/* 634 */             campo.setTipo("String");
/*     */           } else {
/* 636 */             campo.setTipo("BigDecimal");
/*     */           }
/*     */         }
/*     */         
/* 640 */         listaCampo.add(campo);
/*     */       }
/* 642 */       return listaCampo;
/*     */     }
/*     */     catch (SQLException ex) {
/* 645 */       Logger.getLogger(CriarCRUD.class.getName()).log(Level.SEVERE, null, ex);
/*     */       
/* 647 */       System.out.println(ex.getMessage());
/*     */     }
/* 649 */     return null;
/*     */   }
/*     */ }
