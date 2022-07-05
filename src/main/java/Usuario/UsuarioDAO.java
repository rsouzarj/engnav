/*     */ package Usuario;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UsuarioDAO
/*     */ {
/*     */   public Usuario inserir(Usuario usuario)
/*     */   {
/*     */     try
/*     */     {
/*  27 */       String seq = Sequence.buscarNumeroSequence("SEQ_USUARIO");
/*  28 */       usuario.setSeqUsuario(seq);
/*  29 */       Conexao conexao = new Conexao();
/*  30 */       Connection conn = Conexao.getConnection();
/*  31 */       String sql = "insert into USUARIO (SEQ_USUARIO,DATA_CADASTRO,AC_PARCEIRO,AC_DOCUMENTO,AC_OPERACIONAL,AC_CADASTRO,AC_ORGANIZACIONAL,AC_REL_PARCEIRO,AC_REL_DOCUMENTO,AC_PAR_NOVO,AC_PAR_TODOS,SEQ_EMPRESA,SEQ_UNIDADE_NEGOCIO,SEQ_DEPARTAMENTO,NIVEL,SEQ_PARCEIRO,ACESSO_ERP,CHAVE_ORIGEM,AC_DOC_NOVO,ACESSO_COMERCIAL,AC_RELATORIO,AC_OP_EMBARCACAO,AC_OP_EQUIPAMENTO,AC_OP_VISTORIA,AC_OP_LICENCA,AC_OP_CERTIFICADO,AC_OP_CADASTRO,AC_OP_TVINCULO,AC_OP_TVISTORIA,AC_OP_TCERTIFICADO,AC_OP_TLICENCA,AC_OP_STATUS_VISTORIA,AC_OP_CALCULO_DATA,AC_CAD_PARCEIRO,AC_CAD_TPARCEIRO,AC_CAD_TVINCULO,AC_CAD_TCARACTERISTICA,AC_CAD_TENDERECO,AC_CAD_DOCUMENTO,AC_CAD_TDOCUMENTO,AC_FINANCEIRO,AC_FIN_TMOVIMENTO,AC_FIN_CONTAS,AC_FIN_FPAGAMENTO,AC_FIN_CPAGAMENTO,AC_COMERCIAL,AC_COM_SERVICO,AC_COM_TPRECO,AC_COM_ESCOPO,AC_COM_PSERVICO,AC_ORG_USUARIO,AC_ORG_UNEGOCIO,AC_ORG_DEPARTAMENTO,AC_ORG_FUNCAO,AC_ORG_COLABORADOR,AC_ORG_PARAMETRO,USUARIO,SENHA,EMAIL,COR,SITUACAO,CALCULAR_COMISSAO,APP_SESSAO,AC_FIN_TUNIDADE,AC_FIN_ALIQUOTA,AC_FIN_PROJECAO,ac_fin_cadastro,ac_fin_informacao,ac_fin_faturamento,ac_fin_boletim,ac_fin_C_Pagar,AC_FIN_F_FATURAMENTO,ac_fin_centro_custo,ac_fin_doc_fiscal,ac_fin_contas_receber,ac_fin_solicitacao,ac_fin_ordem_p,ac_fin_categoria,ac_administrativo,ac_fin_conta_corrente,ac_fin_nota_debito,ac_fin_prestacao_contas,ac_fin_rlbm,ac_rel_pesq_satisfacao,ac_fin_equipamento,AC_OP_APROVA_VISTORIA,AC_ORG_GER_ARQUIVO,AC_OP_LISTA_ARQUIVOS,AC_FIN_APROVA_PO) values  (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  38 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*  40 */       ps.setString(1, usuario.getSeqUsuario());
/*     */       try {
/*  42 */         ps.setDate(2, new java.sql.Date(usuario.getDataCadastro().getTime()));
/*     */       } catch (NullPointerException e) {
/*  44 */         ps.setDate(2, null);
/*     */       }
/*  46 */       ps.setString(3, usuario.getAcParceiro());
/*  47 */       ps.setString(4, usuario.getAcDocumento());
/*  48 */       ps.setString(5, usuario.getAcOperacional());
/*  49 */       ps.setString(6, usuario.getAcCadastro());
/*  50 */       ps.setString(7, usuario.getAcOrganizacional());
/*  51 */       ps.setString(8, usuario.getAcRelParceiro());
/*  52 */       ps.setString(9, usuario.getAcRelDocumento());
/*  53 */       ps.setString(10, usuario.getAcParNovo());
/*  54 */       ps.setString(11, usuario.getAcParTodos());
/*  55 */       ps.setString(12, usuario.getSeqEmpresa());
/*  56 */       ps.setString(13, usuario.getSeqUnidadeNegocio());
/*  57 */       ps.setString(14, usuario.getSeqDepartamento());
/*  58 */       ps.setString(15, usuario.getNivel());
/*  59 */       ps.setString(16, usuario.getSeqParceiro());
/*  60 */       ps.setString(17, usuario.getAcessoErp());
/*  61 */       ps.setString(18, usuario.getChaveOrigem());
/*  62 */       ps.setString(19, usuario.getAcDocNovo());
/*  63 */       ps.setString(20, usuario.getAcessoComercial());
/*  64 */       ps.setString(21, usuario.getAcRelatorio());
/*  65 */       ps.setString(22, usuario.getAcOpEmbarcacao());
/*  66 */       ps.setString(23, usuario.getAcOpEquipamento());
/*  67 */       ps.setString(24, usuario.getAcOpVistoria());
/*  68 */       ps.setString(25, usuario.getAcOpLicenca());
/*  69 */       ps.setString(26, usuario.getAcOpCertificado());
/*  70 */       ps.setString(27, usuario.getAcOpCadastro());
/*  71 */       ps.setString(28, usuario.getAcOpTvinculo());
/*  72 */       ps.setString(29, usuario.getAcOpTvistoria());
/*  73 */       ps.setString(30, usuario.getAcOpTcertificado());
/*  74 */       ps.setString(31, usuario.getAcOpTlicenca());
/*  75 */       ps.setString(32, usuario.getAcOpStatusVistoria());
/*  76 */       ps.setString(33, usuario.getAcOpCalculoData());
/*  77 */       ps.setString(34, usuario.getAcCadParceiro());
/*  78 */       ps.setString(35, usuario.getAcCadTparceiro());
/*  79 */       ps.setString(36, usuario.getAcCadTvinculo());
/*  80 */       ps.setString(37, usuario.getAcCadTcaracteristica());
/*  81 */       ps.setString(38, usuario.getAcCadTendereco());
/*  82 */       ps.setString(39, usuario.getAcCadDocumento());
/*  83 */       ps.setString(40, usuario.getAcCadTdocumento());
/*  84 */       ps.setString(41, usuario.getAcFinanceiro());
/*  85 */       ps.setString(42, usuario.getAcFinTLancamento());
/*  86 */       ps.setString(43, usuario.getAcFinContas());
/*  87 */       ps.setString(44, usuario.getAcFinFpagamento());
/*  88 */       ps.setString(45, usuario.getAcFinCpagamento());
/*  89 */       ps.setString(46, usuario.getAcComercial());
/*  90 */       ps.setString(47, usuario.getAcComServico());
/*  91 */       ps.setString(48, usuario.getAcComTpreco());
/*  92 */       ps.setString(49, usuario.getAcComEscopo());
/*  93 */       ps.setString(50, usuario.getAcComPservico());
/*  94 */       ps.setString(51, usuario.getAcOrgUsuario());
/*  95 */       ps.setString(52, usuario.getAcOrgUnegocio());
/*  96 */       ps.setString(53, usuario.getAcOrgDepartamento());
/*  97 */       ps.setString(54, usuario.getAcOrgFuncao());
/*  98 */       ps.setString(55, usuario.getAcOrgColaborador());
/*  99 */       ps.setString(56, usuario.getAcOrgParametro());
/* 100 */       ps.setString(57, usuario.getUsuario().trim());
/* 101 */       ps.setString(58, usuario.getSenha());
/* 102 */       ps.setString(59, usuario.getEmail());
/* 103 */       ps.setString(60, usuario.getCor());
/* 104 */       ps.setString(61, usuario.getSituacao());
/* 105 */       ps.setString(62, usuario.getCalcularComissao());
/* 106 */       ps.setString(63, usuario.getAppSessao());
/* 107 */       ps.setString(64, usuario.getAcFinTunidade());
/* 108 */       ps.setString(65, usuario.getAcFinAliquotaRetencaoFederal());
/* 109 */       ps.setString(66, usuario.getAcFinProjecaoTributaria());
/* 110 */       ps.setString(67, usuario.getAcFinCadastro());
/* 111 */       ps.setString(68, usuario.getAcFinInformacaoImportante());
/* 112 */       ps.setString(69, usuario.getAcFinFaturamento());
/* 113 */       ps.setString(70, usuario.getAcFinBoletim());
/* 114 */       ps.setString(71, usuario.getAcFinContasPagar());
/* 115 */       ps.setString(72, usuario.getAcFinFFaturamento());
/* 116 */       ps.setString(73, usuario.getAcFinCentroCusto());
/* 117 */       ps.setString(74, usuario.getAcFinDocFiscal());
/* 118 */       ps.setString(75, usuario.getAcFinContasReceber());
/* 119 */       ps.setString(76, usuario.getAcFinSolicitacao());
/* 120 */       ps.setString(77, usuario.getAcFinOrdemPagamento());
/* 121 */       ps.setString(78, usuario.getAcFinCategoria());
/* 122 */       ps.setString(79, usuario.getAcAdministrativo());
/* 123 */       ps.setString(80, usuario.getAcFinContaCorrente());
/* 124 */       ps.setString(81, usuario.getAcFinNotaDebito());
/* 125 */       ps.setString(82, usuario.getAcFinPrestacaoContas());
/* 126 */       ps.setString(83, usuario.getAcFinRLBM());
/* 127 */       ps.setString(84, usuario.getAcRelPesqSatisfacao());
/* 128 */       ps.setString(85, usuario.getAcFinEquipamento());
/*     */       ps.setString(86, usuario.getAcAprovaVistoria());
/*     */       ps.setString(87, usuario.getAcOrgGerArquivo());
                ps.setString(88, usuario.getAcOrgListArquivo());
                ps.setString(89, usuario.getAcAprovaPO());
/* 130 */       ps.execute();
/* 131 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/* 134 */       Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 135 */       System.out.println(ex.getMessage());
/*     */     }
/* 137 */     return usuario;
/*     */   }
/*     */   
/*     */   public Usuario alterar(Usuario usuario) {
/*     */     try {
/* 142 */       Conexao conexao = new Conexao();
/* 143 */       Connection conn = Conexao.getConnection();
/* 144 */       String sql = "update USUARIO set DATA_CADASTRO = ?,AC_PARCEIRO = ?,AC_DOCUMENTO = ?,AC_OPERACIONAL = ?,AC_CADASTRO = ?,AC_ORGANIZACIONAL = ?,AC_REL_PARCEIRO = ?,AC_REL_DOCUMENTO = ?,AC_PAR_NOVO = ?,AC_PAR_TODOS = ?,SEQ_EMPRESA = ?,SEQ_UNIDADE_NEGOCIO = ?,SEQ_DEPARTAMENTO = ?,NIVEL = ?,SEQ_PARCEIRO = ?,ACESSO_ERP = ?,CHAVE_ORIGEM = ?,AC_DOC_NOVO = ?,ACESSO_COMERCIAL = ?,AC_RELATORIO = ?,AC_OP_EMBARCACAO = ?,AC_OP_EQUIPAMENTO = ?,AC_OP_VISTORIA = ?,AC_OP_LICENCA = ?,AC_OP_CERTIFICADO = ?,AC_OP_CADASTRO = ?,AC_OP_TVINCULO = ?,AC_OP_TVISTORIA = ?,AC_OP_TCERTIFICADO = ?,AC_OP_TLICENCA = ?,AC_OP_STATUS_VISTORIA = ?,AC_OP_CALCULO_DATA = ?,AC_CAD_PARCEIRO = ?,AC_CAD_TPARCEIRO = ?,AC_CAD_TVINCULO = ?,AC_CAD_TCARACTERISTICA = ?,AC_CAD_TENDERECO = ?,AC_CAD_DOCUMENTO = ?,AC_CAD_TDOCUMENTO = ?,AC_FINANCEIRO = ?,AC_FIN_TMOVIMENTO = ?,AC_FIN_CONTAS = ?,AC_FIN_FPAGAMENTO = ?,AC_FIN_CPAGAMENTO = ?,AC_COMERCIAL = ?,AC_COM_SERVICO = ?,AC_COM_TPRECO = ?,AC_COM_ESCOPO = ?,AC_COM_PSERVICO = ?,AC_ORG_USUARIO = ?,AC_ORG_UNEGOCIO = ?,AC_ORG_DEPARTAMENTO = ?,AC_ORG_FUNCAO = ?,AC_ORG_COLABORADOR = ?,AC_ORG_PARAMETRO = ?,USUARIO = ?,SENHA = ?,EMAIL = ?,COR = ?,SITUACAO = ?,CALCULAR_COMISSAO = ?,APP_SESSAO = ?,AC_FIN_TUNIDADE = ?,AC_FIN_ALIQUOTA = ?,AC_FIN_PROJECAO = ?,ac_fin_cadastro = ?,ac_fin_informacao = ?,ac_fin_faturamento = ?,ac_fin_boletim = ?,AC_FIN_F_FATURAMENTO = ? ,ac_fin_C_Pagar = ?,ac_fin_centro_custo = ?,ac_fin_doc_fiscal = ?,ac_fin_contas_receber = ?, ac_fin_solicitacao = ?, ac_fin_ordem_p = ?,ac_fin_categoria = ?,ac_administrativo = ?,ac_fin_conta_corrente = ?, ac_fin_nota_debito = ?, ac_fin_prestacao_contas = ?, ac_fin_rlbm = ?, ac_rel_pesq_satisfacao = ?, ac_fin_equipamento = ?, AC_OP_APROVA_VISTORIA = ?, AC_ORG_GER_ARQUIVO = ?, AC_OP_LISTA_ARQUIVOS = ?, AC_FIN_APROVA_PO = ? where SEQ_USUARIO = ?";
/*     */        
/*     */ 
/*     */ 
/* 149 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       try
/*     */       {
/* 152 */         ps.setDate(1, new java.sql.Date(usuario.getDataCadastro().getTime()));
/*     */       } catch (NullPointerException e) {
/* 154 */         ps.setDate(1, null);
/*     */       }
/* 156 */       ps.setString(2, usuario.getAcParceiro());
/* 157 */       ps.setString(3, usuario.getAcDocumento());
/* 158 */       ps.setString(4, usuario.getAcOperacional());
/* 159 */       ps.setString(5, usuario.getAcCadastro());
/* 160 */       ps.setString(6, usuario.getAcOrganizacional());
/* 161 */       ps.setString(7, usuario.getAcRelParceiro());
/* 162 */       ps.setString(8, usuario.getAcRelDocumento());
/* 163 */       ps.setString(9, usuario.getAcParNovo());
/* 164 */       ps.setString(10, usuario.getAcParTodos());
/* 165 */       ps.setString(11, usuario.getSeqEmpresa());
/* 166 */       ps.setString(12, usuario.getSeqUnidadeNegocio());
/* 167 */       ps.setString(13, usuario.getSeqDepartamento());
/* 168 */       ps.setString(14, usuario.getNivel());
/* 169 */       ps.setString(15, usuario.getSeqParceiro());
/* 170 */       ps.setString(16, usuario.getAcessoErp());
/* 171 */       ps.setString(17, usuario.getChaveOrigem());
/* 172 */       ps.setString(18, usuario.getAcDocNovo());
/* 173 */       ps.setString(19, usuario.getAcessoComercial());
/* 174 */       ps.setString(20, usuario.getAcRelatorio());
/* 175 */       ps.setString(21, usuario.getAcOpEmbarcacao());
/* 176 */       ps.setString(22, usuario.getAcOpEquipamento());
/* 177 */       ps.setString(23, usuario.getAcOpVistoria());
/* 178 */       ps.setString(24, usuario.getAcOpLicenca());
/* 179 */       ps.setString(25, usuario.getAcOpCertificado());
/* 180 */       ps.setString(26, usuario.getAcOpCadastro());
/* 181 */       ps.setString(27, usuario.getAcOpTvinculo());
/* 182 */       ps.setString(28, usuario.getAcOpTvistoria());
/* 183 */       ps.setString(29, usuario.getAcOpTcertificado());
/* 184 */       ps.setString(30, usuario.getAcOpTlicenca());
/* 185 */       ps.setString(31, usuario.getAcOpStatusVistoria());
/* 186 */       ps.setString(32, usuario.getAcOpCalculoData());
/* 187 */       ps.setString(33, usuario.getAcCadParceiro());
/* 188 */       ps.setString(34, usuario.getAcCadTparceiro());
/* 189 */       ps.setString(35, usuario.getAcCadTvinculo());
/* 190 */       ps.setString(36, usuario.getAcCadTcaracteristica());
/* 191 */       ps.setString(37, usuario.getAcCadTendereco());
/* 192 */       ps.setString(38, usuario.getAcCadDocumento());
/* 193 */       ps.setString(39, usuario.getAcCadTdocumento());
/* 194 */       ps.setString(40, usuario.getAcFinanceiro());
/* 195 */       ps.setString(41, usuario.getAcFinTLancamento());
/* 196 */       ps.setString(42, usuario.getAcFinContas());
/* 197 */       ps.setString(43, usuario.getAcFinFpagamento());
/* 198 */       ps.setString(44, usuario.getAcFinCpagamento());
/* 199 */       ps.setString(45, usuario.getAcComercial());
/* 200 */       ps.setString(46, usuario.getAcComServico());
/* 201 */       ps.setString(47, usuario.getAcComTpreco());
/* 202 */       ps.setString(48, usuario.getAcComEscopo());
/* 203 */       ps.setString(49, usuario.getAcComPservico());
/* 204 */       ps.setString(50, usuario.getAcOrgUsuario());
/* 205 */       ps.setString(51, usuario.getAcOrgUnegocio());
/* 206 */       ps.setString(52, usuario.getAcOrgDepartamento());
/* 207 */       ps.setString(53, usuario.getAcOrgFuncao());
/* 208 */       ps.setString(54, usuario.getAcOrgColaborador());
/* 209 */       ps.setString(55, usuario.getAcOrgParametro());
/* 210 */       ps.setString(56, usuario.getUsuario());
/* 211 */       ps.setString(57, usuario.getSenha());
/* 212 */       ps.setString(58, usuario.getEmail());
/* 213 */       ps.setString(59, usuario.getCor());
/* 214 */       ps.setString(60, usuario.getSituacao());
/* 215 */       ps.setString(61, usuario.getCalcularComissao());
/* 216 */       ps.setString(62, usuario.getAppSessao());
/* 217 */       ps.setString(63, usuario.getAcFinTunidade());
/* 218 */       ps.setString(64, usuario.getAcFinAliquotaRetencaoFederal());
/* 219 */       ps.setString(65, usuario.getAcFinProjecaoTributaria());
/* 220 */       ps.setString(66, usuario.getAcFinCadastro());
/* 221 */       ps.setString(67, usuario.getAcFinInformacaoImportante());
/* 222 */       ps.setString(68, usuario.getAcFinFaturamento());
/* 223 */       ps.setString(69, usuario.getAcFinBoletim());
/* 224 */       ps.setString(70, usuario.getAcFinFFaturamento());
/* 225 */       ps.setString(71, usuario.getAcFinContasPagar());
/* 226 */       ps.setString(72, usuario.getAcFinCentroCusto());
/* 227 */       ps.setString(73, usuario.getAcFinDocFiscal());
/* 228 */       ps.setString(74, usuario.getAcFinContasReceber());
/* 229 */       ps.setString(75, usuario.getAcFinSolicitacao());
/* 230 */       ps.setString(76, usuario.getAcFinOrdemPagamento());
/* 231 */       ps.setString(77, usuario.getAcFinCategoria());
/* 232 */       ps.setString(78, usuario.getAcAdministrativo());
/* 233 */       ps.setString(79, usuario.getAcFinContaCorrente());
/* 234 */       ps.setString(80, usuario.getAcFinNotaDebito());
/* 235 */       ps.setString(81, usuario.getAcFinPrestacaoContas());
/* 236 */       ps.setString(82, usuario.getAcFinRLBM());
/* 237 */       ps.setString(83, usuario.getAcRelPesqSatisfacao());
/* 238 */       ps.setString(84, usuario.getAcFinEquipamento());
                ps.setString(85, usuario.getAcAprovaVistoria());
/* 239 */       ps.setString(86, usuario.getAcOrgGerArquivo());
/* 240 */       ps.setString(87, usuario.getAcOrgListArquivo());
                ps.setString(88, usuario.getAcAprovaPO());
                ps.setString(89, usuario.getSeqUsuario());

                ps.execute();
/* 241 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/* 244 */       Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 245 */       System.out.println(ex.getMessage());
/*     */     }
/*     */     
/* 248 */     return usuario;
/*     */   }
/*     */   
/*     */   public List<Usuario> listar(ClausulaWhere sClausula) {
/*     */     try {
/* 253 */       Conexao conexao = new Conexao();
/* 254 */       Connection conn = Conexao.getConnection();
/* 255 */       String sql = "SELECT * FROM USUARIO inner join empresa on empresa.seq_empresa = usuario.seq_empresa" + sClausula.montarsClausula();
/* 256 */       System.out.println(sql);
/*     */       
/* 258 */       List<Usuario> listaUsuario = new ArrayList();
/* 259 */       PreparedStatement ps = conn.prepareStatement(sql);
/* 260 */       ResultSet rs = ps.executeQuery();
/*     */       
/* 262 */       while (rs.next()) {
/* 263 */         Usuario usuario = new Usuario();
/* 264 */         usuario.setSeqUsuario(rs.getString("SEQ_USUARIO"));
/* 265 */         usuario.setDataCadastro(rs.getDate("DATA_CADASTRO"));
/* 266 */         usuario.setAcParceiro(rs.getString("AC_PARCEIRO"));
/* 267 */         usuario.setAcDocumento(rs.getString("AC_DOCUMENTO"));
/* 268 */         usuario.setAcOperacional(rs.getString("AC_OPERACIONAL"));
/* 269 */         usuario.setAcCadastro(rs.getString("AC_CADASTRO"));
/* 270 */         usuario.setAcOrganizacional(rs.getString("AC_ORGANIZACIONAL"));
/* 271 */         usuario.setAcRelParceiro(rs.getString("AC_REL_PARCEIRO"));
/* 272 */         usuario.setAcRelDocumento(rs.getString("AC_REL_DOCUMENTO"));
/* 273 */         usuario.setAcParNovo(rs.getString("AC_PAR_NOVO"));
/* 274 */         usuario.setAcParTodos(rs.getString("AC_PAR_TODOS"));
/* 275 */         usuario.setSeqEmpresa(rs.getString("SEQ_EMPRESA"));
/* 276 */         usuario.setSeqUnidadeNegocio(rs.getString("SEQ_UNIDADE_NEGOCIO"));
/* 277 */         usuario.setSeqDepartamento(rs.getString("SEQ_DEPARTAMENTO"));
/* 278 */         usuario.setNivel(rs.getString("NIVEL"));
/* 279 */         usuario.setSeqParceiro(rs.getString("SEQ_PARCEIRO"));
/* 280 */         usuario.setAcessoErp(rs.getString("ACESSO_ERP"));
/* 281 */         usuario.setChaveOrigem(rs.getString("CHAVE_ORIGEM"));
/* 282 */         usuario.setAcDocNovo(rs.getString("AC_DOC_NOVO"));
/* 283 */         usuario.setAcessoComercial(rs.getString("ACESSO_COMERCIAL"));
/* 284 */         usuario.setAcRelatorio(rs.getString("AC_RELATORIO"));
/* 285 */         usuario.setAcOpEmbarcacao(rs.getString("AC_OP_EMBARCACAO"));
/* 286 */         usuario.setAcOpEquipamento(rs.getString("AC_OP_EQUIPAMENTO"));
/* 287 */         usuario.setAcOpVistoria(rs.getString("AC_OP_VISTORIA"));
/* 288 */         usuario.setAcOpLicenca(rs.getString("AC_OP_LICENCA"));
/* 289 */         usuario.setAcOpCertificado(rs.getString("AC_OP_CERTIFICADO"));
/* 290 */         usuario.setAcOpCadastro(rs.getString("AC_OP_CADASTRO"));
/* 291 */         usuario.setAcOpTvinculo(rs.getString("AC_OP_TVINCULO"));
/* 292 */         usuario.setAcOpTvistoria(rs.getString("AC_OP_TVISTORIA"));
/* 293 */         usuario.setAcOpTcertificado(rs.getString("AC_OP_TCERTIFICADO"));
/* 294 */         usuario.setAcOpTlicenca(rs.getString("AC_OP_TLICENCA"));
/* 295 */         usuario.setAcOpStatusVistoria(rs.getString("AC_OP_STATUS_VISTORIA"));
/* 296 */         usuario.setAcOpCalculoData(rs.getString("AC_OP_CALCULO_DATA"));
/* 297 */         usuario.setAcCadParceiro(rs.getString("AC_CAD_PARCEIRO"));
/* 298 */         usuario.setAcCadTparceiro(rs.getString("AC_CAD_TPARCEIRO"));
/* 299 */         usuario.setAcCadTvinculo(rs.getString("AC_CAD_TVINCULO"));
/* 300 */         usuario.setAcCadTcaracteristica(rs.getString("AC_CAD_TCARACTERISTICA"));
/* 301 */         usuario.setAcCadTendereco(rs.getString("AC_CAD_TENDERECO"));
/* 302 */         usuario.setAcCadDocumento(rs.getString("AC_CAD_DOCUMENTO"));
/* 303 */         usuario.setAcCadTdocumento(rs.getString("AC_CAD_TDOCUMENTO"));
/* 304 */         usuario.setAcFinanceiro(rs.getString("AC_FINANCEIRO"));
/* 305 */         usuario.setAcFinCadastro(rs.getString("AC_FIN_cadastro"));
/* 306 */         usuario.setAcFinTLancamento(rs.getString("AC_FIN_TMOVIMENTO"));
/* 307 */         usuario.setAcFinInformacaoImportante(rs.getString("AC_FIN_INFORMACAO"));
/* 308 */         usuario.setAcFinTunidade(rs.getString("AC_FIN_TUNIDADE"));
/* 309 */         usuario.setAcFinContas(rs.getString("AC_FIN_CONTAS"));
/* 310 */         usuario.setAcFinFpagamento(rs.getString("AC_FIN_FPAGAMENTO"));
/* 311 */         usuario.setAcFinCpagamento(rs.getString("AC_FIN_CPAGAMENTO"));
/* 312 */         usuario.setAcFinFaturamento(rs.getString("ac_fin_faturamento"));
/* 313 */         usuario.setAcFinBoletim(rs.getString("ac_fin_boletim"));
/* 314 */         usuario.setAcComercial(rs.getString("AC_COMERCIAL"));
/* 315 */         usuario.setAcComServico(rs.getString("AC_COM_SERVICO"));
/* 316 */         usuario.setAcComTpreco(rs.getString("AC_COM_TPRECO"));
/* 317 */         usuario.setAcComEscopo(rs.getString("AC_COM_ESCOPO"));
/* 318 */         usuario.setAcComPservico(rs.getString("AC_COM_PSERVICO"));
/* 319 */         usuario.setAcOrgUsuario(rs.getString("AC_ORG_USUARIO"));
/* 320 */         usuario.setAcOrgUnegocio(rs.getString("AC_ORG_UNEGOCIO"));
/* 321 */         usuario.setAcOrgDepartamento(rs.getString("AC_ORG_DEPARTAMENTO"));
/* 322 */         usuario.setAcOrgFuncao(rs.getString("AC_ORG_FUNCAO"));
/* 323 */         usuario.setAcOrgColaborador(rs.getString("AC_ORG_COLABORADOR"));
/* 324 */         usuario.setAcOrgParametro(rs.getString("AC_ORG_PARAMETRO"));
/* 325 */         usuario.setUsuario(rs.getString("USUARIO"));
/* 326 */         usuario.setSenha(rs.getString("SENHA"));
/* 327 */         usuario.setEmail(rs.getString("EMAIL"));
/* 328 */         usuario.setCor(rs.getString("COR"));
/* 329 */         usuario.setSituacao(rs.getString("SITUACAO"));
/* 330 */         usuario.setCalcularComissao(rs.getString("CALCULAR_COMISSAO"));
/* 331 */         usuario.setAppSessao(rs.getString("APP_SESSAO"));
/* 332 */         usuario.setAcFinAliquotaRetencaoFederal(rs.getString("AC_FIN_ALIQUOTA"));
/* 333 */         usuario.setAcFinProjecaoTributaria(rs.getString("AC_FIN_PROJECAO"));
/* 334 */         usuario.setAcFinFFaturamento(rs.getString("AC_FIN_F_FATURAMENTO"));
/* 335 */         usuario.setAcFinContasPagar(rs.getString("ac_fin_C_Pagar"));
/* 336 */         usuario.setAcFinCentroCusto(rs.getString("ac_fin_centro_custo"));
/* 337 */         usuario.setAcFinDocFiscal(rs.getString("ac_fin_doc_fiscal"));
/* 338 */         usuario.setAcFinContasReceber(rs.getString("ac_fin_contas_receber"));
/* 339 */         usuario.setAcFinSolicitacao(rs.getString("ac_fin_solicitacao"));
/* 340 */         usuario.setAcFinOrdemPagamento(rs.getString("ac_fin_ordem_p"));
/* 341 */         usuario.setAcFinCategoria(rs.getString("ac_fin_categoria"));
/* 342 */         usuario.setAcAdministrativo(rs.getString("ac_administrativo"));
/* 343 */         usuario.setAcFinContaCorrente(rs.getString("ac_fin_conta_corrente"));
/* 344 */         usuario.setAcFinNotaDebito(rs.getString("ac_fin_nota_debito"));
/* 345 */         usuario.setAcFinPrestacaoContas(rs.getString("ac_fin_prestacao_contas"));
/* 346 */         usuario.setAcFinRLBM(rs.getString("ac_fin_rlbm"));
/* 347 */         usuario.setAcRelPesqSatisfacao(rs.getString("ac_rel_pesq_satisfacao"));
/* 348 */         usuario.setAcFinEquipamento(rs.getString("ac_fin_equipamento"));
                  usuario.setAcAprovaVistoria(rs.getString("AC_OP_APROVA_VISTORIA"));
                  usuario.setAcOrgGerArquivo(rs.getString("AC_ORG_GER_ARQUIVO"));
/* 349 */         usuario.setAcOrgListArquivo(rs.getString("AC_OP_LISTA_ARQUIVOS"));
                  usuario.setAcAprovaPO(rs.getString("AC_FIN_APROVA_PO"));
                  listaUsuario.add(usuario);
/*     */       }
/*     */       
/* 352 */       ps.execute();
/* 353 */       ps.close();
/*     */       
/* 355 */       return listaUsuario;
/*     */     } catch (SQLException ex) {
/* 357 */       Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 358 */       System.out.println(ex.getMessage()); }
/* 359 */     return null;
/*     */   }
/*     */   
/*     */   public boolean deletar(Usuario usuario)
/*     */   {
/*     */     try {
/* 365 */       Conexao conexao = new Conexao();
/* 366 */       Connection conn = Conexao.getConnection();
/* 367 */       String sql = "DELETE FROM USUARIO WHERE SEQ_USUARIO =  ? ";
/*     */       
/* 369 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/* 371 */       ps.setString(1, usuario.getSeqUsuario());
/*     */       
/* 373 */       ps.execute();
/* 374 */       ps.close();
/*     */       
/* 376 */       return true;
/*     */     } catch (SQLException ex) {
/* 378 */       System.out.println(ex.getMessage()); }
/* 379 */     return false;
/*     */   }
/*     */ }
