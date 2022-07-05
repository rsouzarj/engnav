/*     */ package MaterialPreco;
/*     */ 
/*     */ import ClausulaSQL.ClausulaWhere;
/*     */ import Util.Conexao;
/*     */ import java.io.PrintStream;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MaterialPrecoDAO
/*     */ {
/*     */   public void popuparPrecos(String pSeqEmpresa)
/*     */   {
/*     */     try
/*     */     {
/*  28 */       Conexao conexao = new Conexao();
/*  29 */       Connection conn = Conexao.getConnection();
/*  30 */       String sql = "insert into material_preco(seq_material_preco, seq_material, seq_tabela_preco, data, situacao, preco, preco_min, preco_max, seq_empresa)\nselect seq_material_preco.nextval, material.seq_material, tabela_preco.seq_tabela_preco, sysdate,'ATIVO', 0,0,0, material.seq_empresa \nfrom \nmaterial \nleft join tabela_preco  on 1=1\nleft join material_preco on material_preco.seq_material = material.seq_material \nand material_preco.seq_tabela_preco = tabela_preco.seq_tabela_preco \nwhere  material_preco.SEQ_MATERIAL_PRECO is null and material.seq_empresa = ? and  tabela_preco.seq_empresa = ?";
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  39 */       PreparedStatement ps = conn.prepareStatement(sql);
/*  40 */       ps.setString(1, pSeqEmpresa);
/*  41 */       ps.setString(2, pSeqEmpresa);
/*     */       
/*  43 */       ps.execute();
/*  44 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/*  47 */       System.out.println(ex.getMessage());
/*     */     }
/*     */   }
/*     */   
/*     */   public List<MaterialPreco> alterar(List<MaterialPreco> listaMaterialPreco) {
/*     */     try {
/*  53 */       Conexao conexao = new Conexao();
/*  54 */       Connection conn = Conexao.getConnection();
/*  55 */       String sql = "update MATERIAL_PRECO set PRECO = ?,PRECO_MIN = ?,PRECO_MAX = ?,DATA = ?, SITUACAO = ? where SEQ_MATERIAL_PRECO = ?";
/*     */       
/*  57 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*  59 */       for (MaterialPreco materialPreco : listaMaterialPreco) {
/*  60 */         ps.setBigDecimal(1, materialPreco.getPreco());
/*  61 */         ps.setBigDecimal(2, materialPreco.getPrecoMin());
/*  62 */         ps.setBigDecimal(3, materialPreco.getPrecoMax());
/*  63 */         ps.setDate(4, new java.sql.Date(new java.util.Date().getTime()));
/*  64 */         ps.setString(5, materialPreco.getSituacao());
/*  65 */         ps.setString(6, materialPreco.getSeqMaterialPreco());
/*  66 */         ps.execute();
/*     */       }
/*     */       
/*  69 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/*  72 */       Logger.getLogger(MaterialPrecoDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  73 */       System.out.println(ex.getMessage());
/*     */     }
/*     */     
/*  76 */     return listaMaterialPreco;
/*     */   }
/*     */   
/*     */   public List<MaterialPreco> listar(ClausulaWhere sClausula) {
/*     */     try {
/*  81 */       Conexao conexao = new Conexao();
/*  82 */       Connection conn = Conexao.getConnection();
/*  83 */       String sql = "select \nmaterial_preco.*,\nmaterial.codigo,\nmaterial.nome material,\nmaterial.referencia referencia,\ntabela_preco.nome tabela_preco\n\nfrom\nmaterial_preco\ninner join material on material_preco.seq_material = material.seq_material\ninner join tabela_preco on tabela_preco.seq_tabela_preco = material_preco.seq_tabela_preco " + sClausula.montarsClausula();
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  96 */       List<MaterialPreco> listaMaterialPreco = new ArrayList();
/*  97 */       PreparedStatement ps = conn.prepareStatement(sql);
/*  98 */       ResultSet rs = ps.executeQuery();
/*     */       
/* 100 */       while (rs.next()) {
/* 101 */         MaterialPreco materialPreco = new MaterialPreco();
/* 102 */         materialPreco.setSeqMaterialPreco(rs.getString("SEQ_MATERIAL_PRECO"));
/* 103 */         materialPreco.setSeqMaterial(rs.getString("SEQ_MATERIAL"));
/* 104 */         materialPreco.setSeqTabelaPreco(rs.getString("SEQ_TABELA_PRECO"));
/* 105 */         materialPreco.setPreco(rs.getBigDecimal("PRECO"));
/* 106 */         materialPreco.setPrecoMin(rs.getBigDecimal("PRECO_MIN"));
/* 107 */         materialPreco.setPrecoMax(rs.getBigDecimal("PRECO_MAX"));
/* 108 */         materialPreco.setData(rs.getDate("DATA"));
/* 109 */         materialPreco.setSituacao(rs.getString("SITUACAO"));
/*     */         
/* 111 */         materialPreco.setCodigo(rs.getString("codigo"));
/* 112 */         materialPreco.setMaterial(rs.getString("material"));
/* 113 */         materialPreco.setReferencia(rs.getString("referencia"));
/* 114 */         materialPreco.setTabelaPrecoNome(rs.getString("tabela_preco"));
/*     */         
/* 116 */         listaMaterialPreco.add(materialPreco);
/*     */       }
/*     */       
/* 119 */       ps.execute();
/* 120 */       ps.close();
/*     */       
/* 122 */       return listaMaterialPreco;
/*     */     } catch (SQLException ex) {
/* 124 */       Logger.getLogger(MaterialPrecoDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 125 */       System.out.println(ex.getMessage()); }
/* 126 */     return null;
/*     */   }
/*     */   
/*     */   public List<MaterialPreco> listarComParceiro(ClausulaWhere sClausula)
/*     */   {
/*     */     try {
/* 132 */       Conexao conexao = new Conexao();
/* 133 */       Connection conn = Conexao.getConnection();
/* 134 */       String sql = "select \nmaterial_preco.*,\nmaterial.codigo,\nmaterial.nome material,\nmaterial.referencia referencia,\ntabela_preco.nome tabela_preco\n\nfrom\nmaterial_preco\ninner join material on material_preco.seq_material = material.seq_material\ninner join tabela_preco on tabela_preco.seq_tabela_preco = material_preco.seq_tabela_preco\ninner join parceiro on parceiro.seq_tabela_preco = material_preco.seq_tabela_preco " + sClausula.montarsClausula() + " order by material.nome";
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 151 */       List<MaterialPreco> listaMaterialPreco = new ArrayList();
/* 152 */       PreparedStatement ps = conn.prepareStatement(sql);
/* 153 */       ResultSet rs = ps.executeQuery();
/*     */       
/* 155 */       while (rs.next()) {
/* 156 */         MaterialPreco materialPreco = new MaterialPreco();
/* 157 */         materialPreco.setSeqMaterialPreco(rs.getString("SEQ_MATERIAL_PRECO"));
/* 158 */         materialPreco.setSeqMaterial(rs.getString("SEQ_MATERIAL"));
/* 159 */         materialPreco.setSeqTabelaPreco(rs.getString("SEQ_TABELA_PRECO"));
/* 160 */         materialPreco.setPreco(rs.getBigDecimal("PRECO"));
/* 161 */         materialPreco.setPrecoMin(rs.getBigDecimal("PRECO_MIN"));
/* 162 */         materialPreco.setPrecoMax(rs.getBigDecimal("PRECO_MAX"));
/* 163 */         materialPreco.setData(rs.getDate("DATA"));
/* 164 */         materialPreco.setSituacao(rs.getString("SITUACAO"));
/*     */         
/* 166 */         materialPreco.setCodigo(rs.getString("codigo"));
/* 167 */         materialPreco.setMaterial(rs.getString("material"));
/* 168 */         materialPreco.setReferencia(rs.getString("referencia"));
/* 169 */         materialPreco.setTabelaPrecoNome(rs.getString("tabela_preco"));
/*     */         
/* 171 */         listaMaterialPreco.add(materialPreco);
/*     */       }
/*     */       
/* 174 */       ps.execute();
/* 175 */       ps.close();
/*     */       
/* 177 */       return listaMaterialPreco;
/*     */     } catch (SQLException ex) {
/* 179 */       Logger.getLogger(MaterialPrecoDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 180 */       System.out.println(ex.getMessage()); }
/* 181 */     return null;
/*     */   }
/*     */   
/*     */   public List<MaterialPreco> listarGruposComParceiro(ClausulaWhere sClausula)
/*     */   {
/*     */     try {
/* 187 */       Conexao conexao = new Conexao();
/* 188 */       Connection conn = Conexao.getConnection();
/* 189 */       String sql = "select distinct\nmaterial.referencia referencia\n\nfrom\nmaterial\n" + sClausula.montarsClausula() + " order by material.referencia";
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 199 */       List<MaterialPreco> listaMaterialReferencia = new ArrayList();
/* 200 */       PreparedStatement ps = conn.prepareStatement(sql);
/* 201 */       ResultSet rs = ps.executeQuery();
/*     */       
/* 203 */       while (rs.next()) {
/* 204 */         MaterialPreco materialPreco = new MaterialPreco();
/* 205 */         materialPreco.setReferencia(rs.getString("referencia"));
/*     */         
/* 207 */         listaMaterialReferencia.add(materialPreco);
/*     */       }
/*     */       
/* 210 */       ps.execute();
/* 211 */       ps.close();
/*     */       
/* 213 */       return listaMaterialReferencia;
/*     */     } catch (SQLException ex) {
/* 215 */       Logger.getLogger(MaterialPrecoDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 216 */       System.out.println(ex.getMessage()); }
/* 217 */     return null;
/*     */   }
/*     */   
/*     */   public List<MaterialPreco> listarServicoNome(ClausulaWhere sClausula)
/*     */   {
/*     */     try {
/* 223 */       Conexao conexao = new Conexao();
/* 224 */       Connection conn = Conexao.getConnection();
/*     */       
/*     */ 
/* 227 */       String sql = "select \nmaterial_preco.*,\nmaterial.codigo,\nmaterial.nome material,\nmaterial.referencia referencia,\ntabela_preco.nome tabela_preco\n\nfrom\nmaterial_preco\ninner join material on material_preco.seq_material = material.seq_material\ninner join tabela_preco on tabela_preco.seq_tabela_preco = material_preco.seq_tabela_preco\ninner join parceiro on parceiro.seq_tabela_preco = material_preco.seq_tabela_preco " + sClausula.montarsClausula() + " order by material.nome";
/*     */       

/* 244 */       List<MaterialPreco> listaMaterialPreco = new ArrayList();
/* 245 */       PreparedStatement ps = conn.prepareStatement(sql);
/* 246 */       ResultSet rs = ps.executeQuery();
/*     */       
/* 248 */       while (rs.next()) {
/* 249 */         MaterialPreco materialPreco = new MaterialPreco();
/* 250 */         materialPreco.setSeqMaterialPreco(rs.getString("SEQ_MATERIAL_PRECO"));
/* 251 */         materialPreco.setSeqMaterial(rs.getString("SEQ_MATERIAL"));
/* 252 */         materialPreco.setSeqTabelaPreco(rs.getString("SEQ_TABELA_PRECO"));
/* 253 */         materialPreco.setPreco(rs.getBigDecimal("PRECO"));
/* 254 */         materialPreco.setPrecoMin(rs.getBigDecimal("PRECO_MIN"));
/* 255 */         materialPreco.setPrecoMax(rs.getBigDecimal("PRECO_MAX"));
/* 256 */         materialPreco.setData(rs.getDate("DATA"));
/* 257 */         materialPreco.setSituacao(rs.getString("SITUACAO"));
/*     */         
/* 259 */         materialPreco.setCodigo(rs.getString("codigo"));
/* 260 */         materialPreco.setMaterial(rs.getString("material"));
/* 261 */         materialPreco.setReferencia(rs.getString("referencia"));
/* 262 */         materialPreco.setTabelaPrecoNome(rs.getString("tabela_preco"));
/*     */         
/* 264 */         listaMaterialPreco.add(materialPreco);
/*     */       }
/*     */       
/* 267 */       ps.execute();
/* 268 */       ps.close();
/*     */       
/* 270 */       return listaMaterialPreco;
/*     */     } catch (SQLException ex) {
/* 272 */       Logger.getLogger(MaterialPrecoDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 273 */       System.out.println(ex.getMessage()); }
/* 274 */     return null;
/*     */   }
/*     */ }

