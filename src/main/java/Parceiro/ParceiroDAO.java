/*     */ package Parceiro;
/*     */ 
/*     */ import ClausulaSQL.ClausulaWhere;
/*     */ import Util.Conexao;
/*     */ import Util.Sequence;
/*     */ import java.io.PrintStream;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;

/*     */ public class ParceiroDAO
/*     */ {
/*     */   public Parceiro inserir(Parceiro parceiro)
/*     */   {
/*     */     try
/*     */     {
/*  29 */       String seq = Sequence.buscarNumeroSequence("SEQ_PARCEIRO");
/*  30 */       parceiro.setSeqParceiro(seq);
/*  31 */       Conexao conexao = new Conexao();
/*  32 */       Connection conn = Conexao.getConnection();
/*  33 */       String sql = "insert into PARCEIRO (SEQ_PARCEIRO,NOME,TIPO,DOCUMENTO,FANTASIA,LOGRADOURO,NUMERO,COMPLEMENTO,BAIRRO,CIDADE,UF,CEP,TELEFONE_1,TELEFONE_2,TELEFONE_3,TELEFONE_4,EMAIL,PESSOA_CONTATO,SITUACAO,SEQ_EMPRESA,SEQ_TIPO_PARCEIRO,DATA_CADASTRO, codigo, data_nascimento, atividade_principal, chave_Origem, tag1, tag2, tag3, tag4, unidade_negocio, seq_parceiro_inclusao, ie, im,longitude, latitude,seq_usuario, seq_tabela_preco, pais, site, grupo, perfil, cargo ) values  (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*  38 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*  40 */       ps.setString(1, parceiro.getSeqParceiro());
/*  41 */       ps.setString(2, parceiro.getNome());
/*  42 */       ps.setString(3, parceiro.getTipo());
/*  43 */       ps.setString(4, parceiro.getDocumento());
/*  44 */       ps.setString(5, parceiro.getFantasia());
/*  45 */       ps.setString(6, parceiro.getLogradouro());
/*  46 */       ps.setString(7, parceiro.getNumero());
/*  47 */       ps.setString(8, parceiro.getComplemento());
/*  48 */       ps.setString(9, parceiro.getBairro());
/*  49 */       ps.setString(10, parceiro.getCidade());
/*  50 */       ps.setString(11, parceiro.getUf());
/*  51 */       ps.setString(12, parceiro.getCep());
/*  52 */       ps.setString(13, parceiro.getTelefone1());
/*  53 */       ps.setString(14, parceiro.getTelefone2());
/*  54 */       ps.setString(15, parceiro.getTelefone3());
/*  55 */       ps.setString(16, parceiro.getTelefone4());
/*  56 */       ps.setString(17, parceiro.getEmail());
/*  57 */       ps.setString(18, parceiro.getPessoaContato());
/*  58 */       ps.setString(19, parceiro.getSituacao());
/*  59 */       ps.setString(20, parceiro.getSeqEmpresa());
/*  60 */       ps.setObject(21, parceiro.getSeqTipoParceiro(), 4);
/*  61 */       ps.setDate(22, new java.sql.Date(parceiro.getDataCadastro().getTime()));
/*  62 */       ps.setString(23, parceiro.getCodigo());
/*     */       try {
/*  64 */         ps.setDate(24, new java.sql.Date(parceiro.getDataNascimento().getTime()));
/*     */       } catch (NullPointerException e) {
/*  66 */         ps.setDate(24, null);
/*     */       }
/*  68 */       ps.setString(25, parceiro.getAtividadePrincipal());
/*  69 */       ps.setString(26, parceiro.getChaveOrigem());
/*  70 */       ps.setString(27, parceiro.getTag1());
/*  71 */       ps.setString(28, parceiro.getTag2());
/*  72 */       ps.setString(29, parceiro.getTag3());
/*  73 */       ps.setString(30, parceiro.getTag4());
/*  74 */       ps.setString(31, parceiro.getUnidadeNegocio());
/*  75 */       ps.setObject(32, parceiro.getSeqParceiroInclusao(), 1);
/*  76 */       ps.setString(33, parceiro.getIe());
/*  77 */       ps.setString(34, parceiro.getIm());
/*  78 */       ps.setString(35, parceiro.getStringitude());
/*  79 */       ps.setString(36, parceiro.getLatitude());
/*  80 */       ps.setObject(37, parceiro.getSeqUsuario(), 1);
/*  81 */       ps.setObject(38, parceiro.getSeqTabelaPreco(), 1);
/*  79 */       ps.setString(39, parceiro.getPais());
/*     */       ps.setString(40, parceiro.getSite());
                ps.setString(41, parceiro.getGrupo());
                ps.setString(42, parceiro.getPerfil());
                ps.setString(43, parceiro.getCargo());
/*  83 */       ps.execute();
/*  84 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/*  87 */       Logger.getLogger(ParceiroDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  88 */       System.out.println(ex.getMessage());
/*     */     }
/*  90 */     return parceiro;
/*     */   }
/*     */   
/*     */   public Parceiro alterar(Parceiro parceiro) {
/*     */     try {
/*  95 */       Conexao conexao = new Conexao();
/*  96 */       Connection conn = Conexao.getConnection();
/*  97 */       String sql = "update PARCEIRO set NOME = ?,TIPO = ?,DOCUMENTO = ?,FANTASIA = ?,LOGRADOURO = ?,NUMERO = ?,COMPLEMENTO = ?,BAIRRO = ?,CIDADE = ?,UF = ?,CEP = ?,TELEFONE_1 = ?,TELEFONE_2 = ?,TELEFONE_3 = ?,TELEFONE_4 = ?,EMAIL = ?,PESSOA_CONTATO = ?,SITUACAO = ?,SEQ_EMPRESA = ?,SEQ_TIPO_PARCEIRO = ?,DATA_CADASTRO = ?, codigo = ?,data_nascimento = ?,atividade_principal = ?,tag1 = ?,tag2 = ?,tag3 = ?,tag4 = ?,unidade_negocio = ?, ie = ?, im = ?, longitude = ?, latitude = ?, seq_usuario = ?, seq_tabela_preco = ?, pais = ?, site = ?, grupo = ?, perfil = ?, cargo = ?   where SEQ_PARCEIRO = ?";

/* 105 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/* 107 */       ps.setString(1, parceiro.getNome());
/* 108 */       ps.setString(2, parceiro.getTipo());
/* 109 */       ps.setString(3, parceiro.getDocumento());
/* 110 */       ps.setString(4, parceiro.getFantasia());
/* 111 */       ps.setString(5, parceiro.getLogradouro());
/* 112 */       ps.setString(6, parceiro.getNumero());
/* 113 */       ps.setString(7, parceiro.getComplemento());
/* 114 */       ps.setString(8, parceiro.getBairro());
/* 115 */       ps.setString(9, parceiro.getCidade());
/* 116 */       ps.setString(10, parceiro.getUf());
/* 117 */       ps.setString(11, parceiro.getCep());
/* 118 */       ps.setString(12, parceiro.getTelefone1());
/* 119 */       ps.setString(13, parceiro.getTelefone2());
/* 120 */       ps.setString(14, parceiro.getTelefone3());
/* 121 */       ps.setString(15, parceiro.getTelefone4());
/* 122 */       ps.setString(16, parceiro.getEmail());
/* 123 */       ps.setString(17, parceiro.getPessoaContato());
/* 124 */       ps.setString(18, parceiro.getSituacao());
/* 125 */       ps.setString(19, parceiro.getSeqEmpresa());
/* 126 */       ps.setString(20, parceiro.getSeqTipoParceiro());
/* 127 */       ps.setDate(21, new java.sql.Date(parceiro.getDataCadastro().getTime()));
/*     */       
/* 129 */       ps.setString(22, parceiro.getCodigo());
/*     */       try
/*     */       {
/* 132 */         ps.setDate(23, new java.sql.Date(parceiro.getDataNascimento().getTime()));
/*     */       } catch (NullPointerException e) {
/* 134 */         ps.setDate(23, null);
/*     */       }
/*     */       
/* 137 */       ps.setString(24, parceiro.getAtividadePrincipal());
/*     */       
/* 139 */       ps.setString(25, parceiro.getTag1());
/* 140 */       ps.setString(26, parceiro.getTag2());
/* 141 */       ps.setString(27, parceiro.getTag3());
/* 142 */       ps.setString(28, parceiro.getTag4());
/* 143 */       ps.setString(29, parceiro.getUnidadeNegocio());
/* 144 */       ps.setString(30, parceiro.getIe());
/* 145 */       ps.setString(31, parceiro.getIm());
/* 146 */       ps.setString(32, parceiro.getStringitude());
/* 147 */       ps.setString(33, parceiro.getLatitude());
/* 148 */       ps.setObject(34, parceiro.getSeqUsuario(), 1);
/* 149 */       ps.setObject(35, parceiro.getSeqTabelaPreco(), 1);
/* 150 */       ps.setString(36, parceiro.getPais());
                ps.setString(37, parceiro.getSite());
                ps.setString(38, parceiro.getGrupo());
                ps.setString(39, parceiro.getPerfil());
                ps.setString(40, parceiro.getCargo());
/* 150 */       ps.setString(41, parceiro.getSeqParceiro());
/*     */       
/* 152 */       ps.execute();
/* 153 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/* 156 */       Logger.getLogger(ParceiroDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 157 */       System.out.println(ex.getMessage());
/*     */     }
/*     */     
/* 160 */     return parceiro;
/*     */   }
/*     */   
/*     */   public List<Parceiro> listar(ClausulaWhere sClausula)
/*     */   {
/* 165 */     Conexao conexao = new Conexao();
/* 166 */     Connection conn = Conexao.getConnection();
/* 167 */     String sql = "select \nparceiro.*,\ntipo_parceiro.nome tipo_parceiro \nfrom\nparceiro\ninner join tipo_parceiro on tipo_parceiro.seq_tipo_parceiro = parceiro.seq_tipo_parceiro and tipo_parceiro.seq_empresa = parceiro.seq_empresa \n" + sClausula.montarsClausula() + " order by parceiro.nome asc";

/* 178 */     List<Parceiro> listaParceiro = new ArrayList();
/*     */     try
/*     */     {
/* 181 */       PreparedStatement ps = conn.prepareStatement(sql);
/* 182 */       ResultSet rs = ps.executeQuery();
/* 183 */       while (rs.next()) {
/* 184 */         Parceiro parceiro = new Parceiro();
/* 185 */         parceiro.setSeqParceiro(rs.getString("SEQ_PARCEIRO"));
/* 186 */         parceiro.setNome(rs.getString("NOME"));
/* 187 */         parceiro.setTipo(rs.getString("TIPO"));
/* 188 */         parceiro.setDocumento(rs.getString("DOCUMENTO"));
/* 189 */         parceiro.setFantasia(rs.getString("FANTASIA"));
/* 190 */         parceiro.setLogradouro(rs.getString("LOGRADOURO"));
/* 191 */         parceiro.setNumero(rs.getString("NUMERO"));
/* 192 */         parceiro.setComplemento(rs.getString("COMPLEMENTO"));
/* 193 */         parceiro.setBairro(rs.getString("BAIRRO"));
/* 194 */         parceiro.setCidade(rs.getString("CIDADE"));
/* 195 */         parceiro.setUf(rs.getString("UF"));
/* 196 */         parceiro.setCep(rs.getString("CEP"));
/* 197 */         parceiro.setTelefone1(rs.getString("TELEFONE_1"));
/* 198 */         parceiro.setTelefone2(rs.getString("TELEFONE_2"));
/* 199 */         parceiro.setTelefone3(rs.getString("TELEFONE_3"));
/* 200 */         parceiro.setTelefone4(rs.getString("TELEFONE_4"));
/* 201 */         parceiro.setEmail(rs.getString("EMAIL"));
/* 202 */         parceiro.setPessoaContato(rs.getString("PESSOA_CONTATO"));
/* 203 */         parceiro.setSituacao(rs.getString("SITUACAO"));
/* 204 */         parceiro.setSeqEmpresa(rs.getString("SEQ_EMPRESA"));
/* 205 */         parceiro.setSeqTipoParceiro(rs.getString("SEQ_TIPO_PARCEIRO"));
/* 206 */         parceiro.setDataCadastro(rs.getDate("DATA_CADASTRO"));
/* 207 */         parceiro.setCodigo(rs.getString("CODIGO"));
/* 208 */         parceiro.setTipoParceiro(rs.getString("tipo_parceiro"));
/* 209 */         parceiro.setDataNascimento(rs.getDate("DATA_NASCIMENTO"));
/* 210 */         parceiro.setAtividadePrincipal(rs.getString("atividade_principal"));
/* 211 */         parceiro.setChaveOrigem(rs.getString("chave_origem"));
/* 212 */         parceiro.setIe(rs.getString("ie"));
/* 213 */         parceiro.setIm(rs.getString("im"));
/* 214 */         parceiro.setStringitude(rs.getString("longitude"));
/* 215 */         parceiro.setLatitude(rs.getString("latitude"));
/* 216 */         parceiro.setTag1(rs.getString("tag1"));
/* 217 */         parceiro.setTag2(rs.getString("tag2"));
/* 218 */         parceiro.setTag3(rs.getString("tag3"));
/* 219 */         parceiro.setTag4(rs.getString("tag4"));
/* 220 */         parceiro.setUnidadeNegocio(rs.getString("unidade_negocio"));
/* 221 */         parceiro.setSeqParceiroInclusao(rs.getString("seq_parceiro_inclusao"));
/* 222 */         parceiro.setSeqUsuario(rs.getString("seq_usuario"));
/* 223 */         parceiro.setSeqTabelaPreco(rs.getString("seq_tabela_preco"));
/* 223 */         parceiro.setPais(rs.getString("pais"));
                  parceiro.setSite(rs.getString("site"));
                  parceiro.setGrupo(rs.getString("grupo"));
                  parceiro.setPerfil(rs.getString("perfil"));
                  parceiro.setCargo(rs.getString("cargo"));
/* 224 */         listaParceiro.add(parceiro);
/*     */       }
/*     */       
/* 227 */       ps.execute();
/* 228 */       ps.close();
/*     */       
/* 230 */       return listaParceiro;
/*     */     } catch (SQLException ex) {
/* 232 */       Logger.getLogger(ParceiroDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 233 */       System.out.println(ex.getMessage()); }
/* 234 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public List<Parceiro> listarComVinculoComercial(ClausulaWhere sClausula)
/*     */   {
/* 240 */     Conexao conexao = new Conexao();
/* 241 */     Connection conn = Conexao.getConnection();
/* 242 */     String sql = "select \nparceiro.*,\ntipo_parceiro.nome tipo_parceiro \nfrom\nparceiro\ninner join tipo_parceiro on tipo_parceiro.seq_tipo_parceiro = parceiro.seq_tipo_parceiro and tipo_parceiro.seq_empresa = parceiro.seq_empresa  and tipo_parceiro.PORTAL_COMERCIAL = 'Sim'\ninner join parceiro_vinculo on parceiro_vinculo.seq_parceiro = parceiro.seq_parceiro\ninner join empresa on empresa.seq_empresa = parceiro.seq_empresa and parceiro_vinculo.SEQ_TIPO_VINCULO = empresa.SEQ_TIPO_VINCULO_VENDEDOR \ninner join usuario on usuario.seq_parceiro = parceiro_vinculo.seq_parceiro_vinculado" + sClausula.montarsClausula();

/* 255 */     List<Parceiro> listaParceiro = new ArrayList();
/*     */     try
/*     */     {
/* 258 */       PreparedStatement ps = conn.prepareStatement(sql);
/* 259 */       ResultSet rs = ps.executeQuery();
/* 260 */       while (rs.next()) {
/* 261 */         Parceiro parceiro = new Parceiro();
/* 262 */         parceiro.setSeqParceiro(rs.getString("SEQ_PARCEIRO"));
/* 263 */         parceiro.setNome(rs.getString("NOME"));
/* 264 */         parceiro.setTipo(rs.getString("TIPO"));
/* 265 */         parceiro.setDocumento(rs.getString("DOCUMENTO"));
/* 266 */         parceiro.setFantasia(rs.getString("FANTASIA"));
/* 267 */         parceiro.setLogradouro(rs.getString("LOGRADOURO"));
/* 268 */         parceiro.setNumero(rs.getString("NUMERO"));
/* 269 */         parceiro.setComplemento(rs.getString("COMPLEMENTO"));
/* 270 */         parceiro.setBairro(rs.getString("BAIRRO"));
/* 271 */         parceiro.setCidade(rs.getString("CIDADE"));
/* 272 */         parceiro.setUf(rs.getString("UF"));
/* 273 */         parceiro.setCep(rs.getString("CEP"));
/* 274 */         parceiro.setTelefone1(rs.getString("TELEFONE_1"));
/* 275 */         parceiro.setTelefone2(rs.getString("TELEFONE_2"));
/* 276 */         parceiro.setTelefone3(rs.getString("TELEFONE_3"));
/* 277 */         parceiro.setTelefone4(rs.getString("TELEFONE_4"));
/* 278 */         parceiro.setEmail(rs.getString("EMAIL"));
/* 279 */         parceiro.setPessoaContato(rs.getString("PESSOA_CONTATO"));
/* 280 */         parceiro.setSituacao(rs.getString("SITUACAO"));
/* 281 */         parceiro.setSeqEmpresa(rs.getString("SEQ_EMPRESA"));
/* 282 */         parceiro.setSeqTipoParceiro(rs.getString("SEQ_TIPO_PARCEIRO"));
/* 283 */         parceiro.setDataCadastro(rs.getDate("DATA_CADASTRO"));
/* 284 */         parceiro.setCodigo(rs.getString("CODIGO"));
/* 285 */         parceiro.setTipoParceiro(rs.getString("tipo_parceiro"));
/* 286 */         parceiro.setDataNascimento(rs.getDate("DATA_NASCIMENTO"));
/* 287 */         parceiro.setAtividadePrincipal(rs.getString("atividade_principal"));
/* 288 */         parceiro.setChaveOrigem(rs.getString("chave_origem"));
/* 289 */         parceiro.setIe(rs.getString("ie"));
/* 290 */         parceiro.setIm(rs.getString("im"));
/* 291 */         parceiro.setStringitude(rs.getString("longitude"));
/* 292 */         parceiro.setLatitude(rs.getString("latitude"));
/* 293 */         parceiro.setSeqUsuario(rs.getString("seq_usuario"));
/* 294 */         parceiro.setTag1(rs.getString("tag1"));
/* 295 */         parceiro.setTag2(rs.getString("tag2"));
/* 296 */         parceiro.setTag3(rs.getString("tag3"));
/* 297 */         parceiro.setTag4(rs.getString("tag4"));
/* 298 */         parceiro.setUnidadeNegocio(rs.getString("unidade_negocio"));
/* 299 */         parceiro.setSeqParceiroInclusao(rs.getString("seq_parceiro_inclusao"));
                  parceiro.setGrupo(rs.getString("grupo"));
                  parceiro.setPerfil(rs.getString("perfil"));
/*     */         
/* 301 */         listaParceiro.add(parceiro);
/*     */       }
/*     */       
/* 304 */       ps.execute();
/* 305 */       ps.close();
/*     */       
/* 307 */       return listaParceiro;
/*     */     } catch (SQLException ex) {
/* 309 */       Logger.getLogger(ParceiroDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 310 */       System.out.println(ex.getMessage()); }
/* 311 */     return null;
/*     */   }

            public List<Parceiro> listartipo(ClausulaWhere sClausula)
/*     */   {
/* 165 */     Conexao conexao = new Conexao();
/* 166 */     Connection conn = Conexao.getConnection();
/* 167 */     String sql = "select \nparceiro.*,\ntipo_parceiro.nome tipo_parceiro \nfrom\nparceiro\ninner join tipo_parceiro on tipo_parceiro.seq_tipo_parceiro = parceiro.seq_tipo_parceiro and tipo_parceiro.seq_empresa = parceiro.seq_empresa  and tipo_parceiro.seq_tipo_parceiro in (0,641)\n" + sClausula.montarsClausula() + " order by parceiro.nome asc";

/* 178 */     List<Parceiro> listaParceiro = new ArrayList();
/*     */     try
/*     */     {
/* 181 */       PreparedStatement ps = conn.prepareStatement(sql);
/* 182 */       ResultSet rs = ps.executeQuery();
/* 183 */       while (rs.next()) {
/* 184 */         Parceiro parceiro = new Parceiro();
/* 185 */         parceiro.setSeqParceiro(rs.getString("SEQ_PARCEIRO"));
/* 186 */         parceiro.setNome(rs.getString("NOME"));
/* 187 */         parceiro.setTipo(rs.getString("TIPO"));
/* 188 */         parceiro.setDocumento(rs.getString("DOCUMENTO"));
/* 189 */         parceiro.setFantasia(rs.getString("FANTASIA"));
/* 190 */         parceiro.setLogradouro(rs.getString("LOGRADOURO"));
/* 191 */         parceiro.setNumero(rs.getString("NUMERO"));
/* 192 */         parceiro.setComplemento(rs.getString("COMPLEMENTO"));
/* 193 */         parceiro.setBairro(rs.getString("BAIRRO"));
/* 194 */         parceiro.setCidade(rs.getString("CIDADE"));
/* 195 */         parceiro.setUf(rs.getString("UF"));
/* 196 */         parceiro.setCep(rs.getString("CEP"));
/* 197 */         parceiro.setTelefone1(rs.getString("TELEFONE_1"));
/* 198 */         parceiro.setTelefone2(rs.getString("TELEFONE_2"));
/* 199 */         parceiro.setTelefone3(rs.getString("TELEFONE_3"));
/* 200 */         parceiro.setTelefone4(rs.getString("TELEFONE_4"));
/* 201 */         parceiro.setEmail(rs.getString("EMAIL"));
/* 202 */         parceiro.setPessoaContato(rs.getString("PESSOA_CONTATO"));
/* 203 */         parceiro.setSituacao(rs.getString("SITUACAO"));
/* 204 */         parceiro.setSeqEmpresa(rs.getString("SEQ_EMPRESA"));
/* 205 */         parceiro.setSeqTipoParceiro(rs.getString("SEQ_TIPO_PARCEIRO"));
/* 206 */         parceiro.setDataCadastro(rs.getDate("DATA_CADASTRO"));
/* 207 */         parceiro.setCodigo(rs.getString("CODIGO"));
/* 208 */         parceiro.setTipoParceiro(rs.getString("tipo_parceiro"));
/* 209 */         parceiro.setDataNascimento(rs.getDate("DATA_NASCIMENTO"));
/* 210 */         parceiro.setAtividadePrincipal(rs.getString("atividade_principal"));
/* 211 */         parceiro.setChaveOrigem(rs.getString("chave_origem"));
/* 212 */         parceiro.setIe(rs.getString("ie"));
/* 213 */         parceiro.setIm(rs.getString("im"));
/* 214 */         parceiro.setStringitude(rs.getString("longitude"));
/* 215 */         parceiro.setLatitude(rs.getString("latitude"));
/* 216 */         parceiro.setTag1(rs.getString("tag1"));
/* 217 */         parceiro.setTag2(rs.getString("tag2"));
/* 218 */         parceiro.setTag3(rs.getString("tag3"));
/* 219 */         parceiro.setTag4(rs.getString("tag4"));
/* 220 */         parceiro.setUnidadeNegocio(rs.getString("unidade_negocio"));
/* 221 */         parceiro.setSeqParceiroInclusao(rs.getString("seq_parceiro_inclusao"));
/* 222 */         parceiro.setSeqUsuario(rs.getString("seq_usuario"));
/* 223 */         parceiro.setSeqTabelaPreco(rs.getString("seq_tabela_preco"));
/* 223 */         parceiro.setPais(rs.getString("pais"));
                  parceiro.setGrupo(rs.getString("grupo"));
                  parceiro.setPerfil(rs.getString("perfil"));
/* 224 */         listaParceiro.add(parceiro);
/*     */       }
/*     */       
/* 227 */       ps.execute();
/* 228 */       ps.close();
/*     */       
/* 230 */       return listaParceiro;
/*     */     } catch (SQLException ex) {
/* 232 */       Logger.getLogger(ParceiroDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 233 */       System.out.println(ex.getMessage()); }
/* 234 */     return null;
/*     */   }

/*     */   
/*     */   public boolean deletar(String pSeqEmpresa, String pCodigo)
/*     */   {
/*     */     try {
/* 317 */       Conexao conexao = new Conexao();
/* 318 */       Connection conn = Conexao.getConnection();
/* 319 */       String sql = "DELETE FROM PARCEIRO WHERE seq_empresa =  ? and codigo = ? ";
/*     */       
/* 321 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/* 323 */       ps.setString(1, pSeqEmpresa);
/* 324 */       ps.setString(2, pCodigo);
/*     */       
/* 326 */       ps.execute();
/* 327 */       ps.close();
/*     */       
/* 329 */       return true;
/*     */     } catch (SQLException ex) {
/* 331 */       System.out.println(ex.getMessage()); }
/* 332 */     return false;
/*     */   }
/*     */ }

