/*     */ package TipoDocumento;
/*     */ 
/*     */ import ClausulaSQL.ClausulaWhere;
/*     */ import TipoParceiro.TipoParceiroDAO;
/*     */ import Util.Conexao;
/*     */ import Util.Sequence;
/*     */ import java.io.PrintStream;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;

/*     */ public class TipoDocumentoDAO
/*     */ {
/*     */   public TipoDocumento inserir(TipoDocumento tipoDocumento)
/*     */   {
/*     */     try
/*     */     {
/*  29 */       String seq = Sequence.buscarNumeroSequence("SEQ_TIPO_DOCUMENTO");
/*  30 */       tipoDocumento.setSeqTipoDocumento(seq);
/*  31 */       Conexao conexao = new Conexao();
/*  32 */       Connection conn = Conexao.getConnection();
/*  33 */       String sql = "insert into TIPO_DOCUMENTO (SEQ_TIPO_DOCUMENTO,DATA_CADASTRO,SITUACAO,SEQ_EMPRESA,ORDEM,OPC_ESCOPO,OPC_PONTO_SERVICO,OPC_TABELA_PRECO,OPC_GERA_BOLETO,OPC_EQUIPAMENTO,OPC_EMBARCACAO,OPC_CONTA,OPC_FORMA_PAGAMENTO,OPC_CONDICAO_PAGAMENTO,MODELO_TELA,TEMPLATE,OPC_TIPO_MOVIMENTO_FIN,PROXIMO_CODIGO,NOME, opc_colaborador,OPC_ASS_COLABORADOR,REPORTPATH,REPORTPATH_I) values  (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

/*  37 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*  39 */       ps.setString(1, tipoDocumento.getSeqTipoDocumento());
/*     */       try {
/*  41 */         ps.setDate(2, new java.sql.Date(tipoDocumento.getDataCadastro().getTime()));
/*     */       } catch (NullPointerException e) {
/*  43 */         ps.setDate(2, null);
/*     */       }
/*  45 */       ps.setString(3, tipoDocumento.getSituacao());
/*  46 */       ps.setString(4, tipoDocumento.getSeqEmpresa());
/*  47 */       ps.setString(5, tipoDocumento.getOrdem());
/*  48 */       ps.setString(6, tipoDocumento.getOpcEscopo());
/*  49 */       ps.setString(7, tipoDocumento.getOpcPontoServico());
/*  50 */       ps.setString(8, tipoDocumento.getOpcTabelaPreco());
/*  51 */       ps.setString(9, tipoDocumento.getOpcGeraBoleto());
/*  52 */       ps.setString(10, tipoDocumento.getOpcEquipamento());
/*  53 */       ps.setString(11, tipoDocumento.getOpcEmbarcacao());
/*  54 */       ps.setString(12, tipoDocumento.getOpcConta());
/*  55 */       ps.setString(13, tipoDocumento.getOpcFormaPagamento());
/*  56 */       ps.setString(14, tipoDocumento.getOpcCondicaoPagamento());
/*  57 */       ps.setString(15, tipoDocumento.getModeloTela());
/*  58 */       ps.setString(16, tipoDocumento.getTemplate());
/*  59 */       ps.setString(17, tipoDocumento.getOpcTipoMovimentoFin());
/*  60 */       ps.setString(18, tipoDocumento.getProximoCodigo());
/*  61 */       ps.setString(19, tipoDocumento.getNome());
/*  62 */       ps.setString(20, tipoDocumento.getOpcColaborador());
/*  63 */       ps.setString(21, tipoDocumento.getOpcAssColaborador());
                ps.setString(22, tipoDocumento.getCaminhorel());
                ps.setString(23, tipoDocumento.getCaminhorelI());
                /*     */       
/*  65 */       ps.execute();
/*  66 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/*  69 */       Logger.getLogger(TipoDocumentoDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  70 */       System.out.println(ex.getMessage());
/*     */     }
/*  72 */     return tipoDocumento;
/*     */   }
/*     */   
/*     */   public TipoDocumento alterar(TipoDocumento tipoDocumento) {
/*     */     try {
/*  77 */       Conexao conexao = new Conexao();
/*  78 */       Connection conn = Conexao.getConnection();
/*  79 */       String sql = "update TIPO_DOCUMENTO set DATA_CADASTRO = ?,SITUACAO = ?,SEQ_EMPRESA = ?,ORDEM = ?,OPC_ESCOPO = ?,OPC_PONTO_SERVICO = ?,OPC_TABELA_PRECO = ?,OPC_GERA_BOLETO = ?,OPC_EQUIPAMENTO = ?,OPC_EMBARCACAO = ?,OPC_CONTA = ?,OPC_FORMA_PAGAMENTO = ?,OPC_CONDICAO_PAGAMENTO = ?,MODELO_TELA = ?,TEMPLATE = ?,OPC_TIPO_MOVIMENTO_FIN = ?,PROXIMO_CODIGO = ?,NOME = ?, opc_colaborador = ?, OPC_ASS_COLABORADOR = ?, REPORTPATH = ?,  REPORTPATH_I = ? where SEQ_TIPO_DOCUMENTO = ?";
/*     */       
/*  81 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       try
/*     */       {
/*  84 */         ps.setDate(1, new java.sql.Date(tipoDocumento.getDataCadastro().getTime()));
/*     */       } catch (NullPointerException e) {
/*  86 */         ps.setDate(1, null);
/*     */       }
/*  88 */       ps.setString(2, tipoDocumento.getSituacao());
/*  89 */       ps.setString(3, tipoDocumento.getSeqEmpresa());
/*  90 */       ps.setString(4, tipoDocumento.getOrdem());
/*  91 */       ps.setString(5, tipoDocumento.getOpcEscopo());
/*  92 */       ps.setString(6, tipoDocumento.getOpcPontoServico());
/*  93 */       ps.setString(7, tipoDocumento.getOpcTabelaPreco());
/*  94 */       ps.setString(8, tipoDocumento.getOpcGeraBoleto());
/*  95 */       ps.setString(9, tipoDocumento.getOpcEquipamento());
/*  96 */       ps.setString(10, tipoDocumento.getOpcEmbarcacao());
/*  97 */       ps.setString(11, tipoDocumento.getOpcConta());
/*  98 */       ps.setString(12, tipoDocumento.getOpcFormaPagamento());
/*  99 */       ps.setString(13, tipoDocumento.getOpcCondicaoPagamento());
/* 100 */       ps.setString(14, tipoDocumento.getModeloTela());
/* 101 */       ps.setString(15, tipoDocumento.getTemplate());
/* 102 */       ps.setString(16, tipoDocumento.getOpcTipoMovimentoFin());
/* 103 */       ps.setString(17, tipoDocumento.getProximoCodigo());
/* 104 */       ps.setString(18, tipoDocumento.getNome());
/* 105 */       ps.setString(19, tipoDocumento.getOpcColaborador());
/* 106 */       ps.setString(20, tipoDocumento.getOpcAssColaborador());
                ps.setString(21, tipoDocumento.getCaminhorel());
                ps.setString(22, tipoDocumento.getCaminhorelI());                
/* 107 */       ps.setString(23, tipoDocumento.getSeqTipoDocumento());

/* 108 */       ps.execute();
/* 109 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/* 112 */       Logger.getLogger(TipoDocumentoDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 113 */       System.out.println(ex.getMessage());
/*     */     }
/*     */     
/* 116 */     return tipoDocumento;
/*     */   }
/*     */   
/*     */   public List<TipoDocumento> listarTodos(ClausulaWhere sClausula) {
/*     */     try {
/* 121 */       Conexao conexao = new Conexao();
/* 122 */       Connection conn = Conexao.getConnection();
/* 123 */       String sql = "select \nTIPO_DOCUMENTO.*\nfrom  \ntipo_documento " + sClausula.montarsClausula();

/* 129 */       List<TipoDocumento> listaTipoDocumento = new ArrayList();
/* 130 */       PreparedStatement ps = conn.prepareStatement(sql);
/* 131 */       ResultSet rs = ps.executeQuery();
/*     */       
/* 133 */       while (rs.next()) {
/* 134 */         TipoDocumento tipoDocumento = new TipoDocumento();
/* 135 */         tipoDocumento.setSeqTipoDocumento(rs.getString("SEQ_TIPO_DOCUMENTO"));
/* 136 */         tipoDocumento.setDataCadastro(rs.getDate("DATA_CADASTRO"));
/* 137 */         tipoDocumento.setSituacao(rs.getString("SITUACAO"));
/* 138 */         tipoDocumento.setSeqEmpresa(rs.getString("SEQ_EMPRESA"));
/* 139 */         tipoDocumento.setOrdem(rs.getString("ORDEM"));
/* 140 */         tipoDocumento.setOpcEscopo(rs.getString("OPC_ESCOPO"));
/* 141 */         tipoDocumento.setOpcPontoServico(rs.getString("OPC_PONTO_SERVICO"));
/* 142 */         tipoDocumento.setOpcTabelaPreco(rs.getString("OPC_TABELA_PRECO"));
/* 143 */         tipoDocumento.setOpcGeraBoleto(rs.getString("OPC_GERA_BOLETO"));
/* 144 */         tipoDocumento.setOpcEquipamento(rs.getString("OPC_EQUIPAMENTO"));
/* 145 */         tipoDocumento.setOpcEmbarcacao(rs.getString("OPC_EMBARCACAO"));
/* 146 */         tipoDocumento.setOpcConta(rs.getString("OPC_CONTA"));
/* 147 */         tipoDocumento.setOpcFormaPagamento(rs.getString("OPC_FORMA_PAGAMENTO"));
/* 148 */         tipoDocumento.setOpcCondicaoPagamento(rs.getString("OPC_CONDICAO_PAGAMENTO"));
/* 149 */         tipoDocumento.setModeloTela(rs.getString("MODELO_TELA"));
/* 150 */         tipoDocumento.setTemplate(rs.getString("TEMPLATE"));
/* 151 */         tipoDocumento.setOpcTipoMovimentoFin(rs.getString("OPC_TIPO_MOVIMENTO_FIN"));
/* 152 */         tipoDocumento.setProximoCodigo(rs.getString("PROXIMO_CODIGO"));
/* 153 */         tipoDocumento.setNome(rs.getString("NOME"));
/* 154 */         tipoDocumento.setOpcColaborador(rs.getString("opc_colaborador"));
/* 155 */         tipoDocumento.setOpcAssColaborador(rs.getString("OPC_ASS_COLABORADOR"));
                  tipoDocumento.setCaminhorel(rs.getString("REPORTPATH"));
                  tipoDocumento.setCaminhorelI(rs.getString("REPORTPATH_I"));                  
/* 156 */         listaTipoDocumento.add(tipoDocumento);
/*     */       }
/*     */       
/* 159 */       ps.execute();
/* 160 */       ps.close();
/*     */       
/* 162 */       return listaTipoDocumento;
/*     */     } catch (SQLException ex) {
/* 164 */       Logger.getLogger(TipoDocumentoDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 165 */       System.out.println(ex.getMessage()); }
/* 166 */     return null;
/*     */   }
/*     */   
/*     */   public List<TipoDocumento> listar(ClausulaWhere sClausula)
/*     */   {
/*     */     try {
/* 172 */       Conexao conexao = new Conexao();
/* 173 */       Connection conn = Conexao.getConnection();
/* 174 */       String sql = "select \ndistinct TIPO_DOCUMENTO.*\nfrom  \nUSUARIO_TIPO_DOCUMENTO\ninner join tipo_documento on tipo_documento.seq_tipo_documento =  USUARIO_TIPO_DOCUMENTO.seq_tipo_documento " + sClausula.montarsClausula();
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 180 */       System.out.println(sql);
/*     */       
/* 182 */       List<TipoDocumento> listaTipoDocumento = new ArrayList();
/* 183 */       PreparedStatement ps = conn.prepareStatement(sql);
/* 184 */       ResultSet rs = ps.executeQuery();
/*     */       
/* 186 */       while (rs.next()) {
/* 187 */         TipoDocumento tipoDocumento = new TipoDocumento();
/* 188 */         tipoDocumento.setSeqTipoDocumento(rs.getString("SEQ_TIPO_DOCUMENTO"));
/* 189 */         tipoDocumento.setDataCadastro(rs.getDate("DATA_CADASTRO"));
/* 190 */         tipoDocumento.setSituacao(rs.getString("SITUACAO"));
/* 191 */         tipoDocumento.setSeqEmpresa(rs.getString("SEQ_EMPRESA"));
/* 192 */         tipoDocumento.setOrdem(rs.getString("ORDEM"));
/* 193 */         tipoDocumento.setOpcEscopo(rs.getString("OPC_ESCOPO"));
/* 194 */         tipoDocumento.setOpcPontoServico(rs.getString("OPC_PONTO_SERVICO"));
/* 195 */         tipoDocumento.setOpcTabelaPreco(rs.getString("OPC_TABELA_PRECO"));
/* 196 */         tipoDocumento.setOpcGeraBoleto(rs.getString("OPC_GERA_BOLETO"));
/* 197 */         tipoDocumento.setOpcEquipamento(rs.getString("OPC_EQUIPAMENTO"));
/* 198 */         tipoDocumento.setOpcEmbarcacao(rs.getString("OPC_EMBARCACAO"));
/* 199 */         tipoDocumento.setOpcConta(rs.getString("OPC_CONTA"));
/* 200 */         tipoDocumento.setOpcFormaPagamento(rs.getString("OPC_FORMA_PAGAMENTO"));
/* 201 */         tipoDocumento.setOpcCondicaoPagamento(rs.getString("OPC_CONDICAO_PAGAMENTO"));
/* 202 */         tipoDocumento.setModeloTela(rs.getString("MODELO_TELA"));
/* 203 */         tipoDocumento.setTemplate(rs.getString("TEMPLATE"));
/* 204 */         tipoDocumento.setOpcTipoMovimentoFin(rs.getString("OPC_TIPO_MOVIMENTO_FIN"));
/* 205 */         tipoDocumento.setProximoCodigo(rs.getString("PROXIMO_CODIGO"));
/* 206 */         tipoDocumento.setNome(rs.getString("NOME"));
/* 207 */         tipoDocumento.setOpcColaborador(rs.getString("opc_colaborador"));
/* 208 */         tipoDocumento.setOpcAssColaborador(rs.getString("OPC_ASS_COLABORADOR"));
                  tipoDocumento.setCaminhorel(rs.getString("REPORTPATH"));
                  tipoDocumento.setCaminhorelI(rs.getString("REPORTPATH_I"));                  
/* 209 */         listaTipoDocumento.add(tipoDocumento);
/*     */       }
/*     */       
/* 212 */       ps.execute();
/* 213 */       ps.close();
/*     */       
/* 215 */       return listaTipoDocumento;
/*     */     } catch (SQLException ex) {
/* 217 */       Logger.getLogger(TipoDocumentoDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 218 */       System.out.println(ex.getMessage()); }
/* 219 */     return null;
/*     */   }
/*     */   
/*     */   public boolean deletar(TipoDocumento tipoDocumento)
/*     */   {
/*     */     try {
/* 225 */       Conexao conexao = new Conexao();
/* 226 */       Connection conn = Conexao.getConnection();
/* 227 */       String sql = "DELETE FROM TIPO_DOCUMENTO WHERE SEQ_TIPO_DOCUMENTO =  ? ";
/*     */       
/* 229 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/* 231 */       ps.setString(1, tipoDocumento.getSeqTipoDocumento());
/*     */       
/* 233 */       ps.execute();
/* 234 */       ps.close();
/*     */       
/* 236 */       return true;
/*     */     } catch (SQLException ex) {
/* 238 */       System.out.println(ex.getMessage()); }
/* 239 */     return false;
/*     */   }
/*     */   
/*     */   public HashMap<String, String> listarContador(String pSeqEmpresa, String pSeqUsuario)
/*     */   {
/* 244 */     HashMap<String, String> retorno = new HashMap();
/*     */     try {
/* 246 */       Conexao conexao = new Conexao();
/* 247 */       Connection conn = Conexao.getConnection();
/* 248 */       String sql = "select \ntipo_documento.seq_tipo_documento,\ntipo_documento.nome,\ntipo_documento.ordem,\ncount(documento.seq_documento) total\nFrom \n tipo_documento\nleft join documento on documento.seq_tipo_documento = tipo_documento.seq_tipo_documento \nleft join USUARIO_TIPO_DOCUMENTO on USUARIO_TIPO_DOCUMENTO.seq_tipo_documento = tipo_documento.seq_tipo_documento where  tipo_documento.seq_empresa = " + String.valueOf(pSeqEmpresa) + " and USUARIO_TIPO_DOCUMENTO.SEQ_USUARIO = " + String.valueOf(pSeqUsuario) + " and tipo_documento.situacao = 'ATIVO' " + " group by \n" + "tipo_documento.nome,\n" + "tipo_documento.seq_tipo_documento,\n" + "tipo_documento.ordem\n" + "order by 2";

/* 266 */       System.out.println(sql);
/* 267 */       PreparedStatement ps = conn.prepareStatement(sql);
/* 268 */       ResultSet rs = ps.executeQuery();
/*     */       
/* 270 */       while (rs.next()) {
/* 271 */         retorno.put(rs.getString("SEQ_TIPO_DOCUMENTO"), rs.getString("NOME") +"  "+ rs.getString("TOTAL"));
/*     */       }
/*     */       
/* 274 */       ps.execute();
/* 275 */       ps.close();
/*     */       
/* 277 */       return retorno;
/*     */     } catch (SQLException ex) {
/* 279 */       Logger.getLogger(TipoParceiroDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 280 */       System.out.println(ex.getMessage()); }
/* 281 */     return null;
/*     */   }
/*     */ }

