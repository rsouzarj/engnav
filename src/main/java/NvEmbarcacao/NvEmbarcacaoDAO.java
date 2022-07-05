/*     */ package NvEmbarcacao;
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

/*     */ public class NvEmbarcacaoDAO
/*     */ {
/*     */   public NvEmbarcacao inserir(NvEmbarcacao nvEmbarcacao)
/*     */   {
/*     */     try
/*     */     {
/*  28 */       String seq = Sequence.buscarNumeroSequence("SEQ_NV_EMBARCACAO");
/*  29 */       nvEmbarcacao.setSeqNvEmbarcacao(seq);
/*  30 */       Conexao conexao = new Conexao();
/*  31 */       Connection conn = Conexao.getConnection();
/*  32 */       String sql = "insert into NV_EMBARCACAO (SEQ_NV_EMBARCACAO,SITUACAO,DATA_CADASTRO,NOME,NUMERO_INSCRICAO,DATA_INSCRICAO,LIVRO_INSCRICAO,FOLHA_INSCRICAO,IND_RADIO_INTERNAC,SEQ_NV_TIPO_EMBARCACAO,AREA_NAVEGACAO,TIPO_PROPULSAO,TIPO_ATIVIDADE_MAR,TRIPULANTES,PASSAGEIROS,ANO_CONSTRUCAO,CONSTRUTOR,MATERIAL_CONSTRU_CASCO,MATERIAL_SUPERTRUTURA,POTENCIA_MOTOR,TOTAL_MAQUINA,TIPO_COMBUSTIVEL,CAPAC_ARMAZENAMENTO,ARQUEACAO_BRUTA,ARQUEACAO_LIQUIDA,TONELAGEM_PORTE_BRUTA,COMPRIMENTO_TOTAL,BOCA,PONTAL,CALADO_LEVE,CALADO_CARREGADO,NOME_PROPRIETARIO,CPF_CNPJ,ENDERECO,CIDADE_ESTADO,NOME_PROPRIETARIO2,CPF_CNPJ2,ENDERECO2,CIDADE_ESTADO2,IMO,SEQ_PARCEIRO,INDICATIVO,PORTO_INSCRICAO,TIPO,CENTRO_DISCO_CONVES,CENTRO_DISCO_PROA,LINHA_CONVES,MARCA_LINHA_DAGUA,SEQ_EMPRESA, area_navegacao_tipo,casco_numero,COMPRIMENTO_ENTRE_PERPEND ,seq_nv_certificado_calculo_csn , seq_nv_certificado_calculo_blr , seq_nv_certificado_calculo_arq, correcao_navegacao_agua_doce, CORRECAO_NAV_AGUA_SALGADA, MARCA_LINHA_CARGA_AREA1, MARCA_LINHA_CARGA_AREA2, ESPACOS_FECHADOS_ABAIXO_CONVES, ESPACOS_FECHADOS_ACIMA_CONVES,VOLUME_TOTAL_ESPACOS_FECHADOS, ESPACO_CARGA, DATA_LOCAL_ARQUEACAO_ORIGINAL, DATA_LOCAL_ULTIMA_REARQUEACAO,TOTAL_PASSAG_CAMAR_8_BELICHES, TIPO_MARCA_MOTOR, NUMERO_MOTOR, AUTORIZADO_TRANSP_CARGA_CONVES, borda_livre, destinacao_reboque, potencia_nominal_eletrica, mercadorias_perigosas, apoio_portuario, tipo_atividade_interior, CEP_ESTALEIRO_CONSTRUTOR, CEP_ARMADOR, REQUISITOS_TRANSPORTE_COLETIVO,TIPO_PLANTA_PROPULSORA,COMPRIMENTO_REGRA,BANDEIRA,SEQ_USUARIO, ap_maritimo, cabotagem, longo_curso, ma_area1, ma_area12, ma_area123, ma_area1234, nav_int, nav_area1, nav_area2, ap_portuario, ap_area1, ap_area2) values  (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

/*  61 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*  63 */       ps.setString(1, nvEmbarcacao.getSeqNvEmbarcacao());
/*  64 */       ps.setString(2, nvEmbarcacao.getSituacao());
/*     */       try {
/*  66 */         ps.setDate(3, new java.sql.Date(nvEmbarcacao.getDataCadastro().getTime()));
/*     */       } catch (NullPointerException e) {
/*  68 */         ps.setDate(3, null);
/*     */       }
/*  70 */       ps.setString(4, nvEmbarcacao.getNome());
/*  71 */       ps.setString(5, nvEmbarcacao.getNumeroInscricao());
/*     */       try {
/*  73 */         ps.setDate(6, new java.sql.Date(nvEmbarcacao.getDataInscricao().getTime()));
/*     */       } catch (NullPointerException e) {
/*  75 */         ps.setDate(6, null);
/*     */       }
/*  77 */       ps.setString(7, nvEmbarcacao.getLivroInscricao());
/*  78 */       ps.setString(8, nvEmbarcacao.getFolhaInscricao());
/*  79 */       ps.setString(9, nvEmbarcacao.getIndRadioInternac());
/*  80 */       ps.setString(10, nvEmbarcacao.getSeqNvTipoEmbarcacao());
/*  81 */       ps.setString(11, nvEmbarcacao.getAreaNavegacao());
/*  82 */       ps.setString(12, nvEmbarcacao.getTipoPropulsao());
/*  83 */       ps.setString(13, nvEmbarcacao.getTipoAtividadeMar());
/*  84 */       ps.setString(14, nvEmbarcacao.getTripulantes());
/*  85 */       ps.setString(15, nvEmbarcacao.getPassageiros());
/*  86 */       ps.setString(16, nvEmbarcacao.getAnoConstrucao());
/*  87 */       ps.setString(17, nvEmbarcacao.getConstrutor());
/*  88 */       ps.setString(18, nvEmbarcacao.getMaterialConstruCasco());
/*  89 */       ps.setString(19, nvEmbarcacao.getMaterialSupertrutura());
/*  90 */       ps.setString(20, nvEmbarcacao.getPotenciaMotor());
/*  91 */       ps.setString(21, nvEmbarcacao.getTotalMaquina());
/*  92 */       ps.setString(22, nvEmbarcacao.getTipoCombustivel());
/*  93 */       ps.setString(23, nvEmbarcacao.getCapacArmazenamento());
/*  94 */       ps.setString(24, nvEmbarcacao.getArqueacaoBruta());
/*  95 */       ps.setString(25, nvEmbarcacao.getArqueacaoLiquida());
/*  96 */       ps.setString(26, nvEmbarcacao.getTonelagemPorteBruta());
/*  97 */       ps.setString(27, nvEmbarcacao.getComprimentoTotal());
/*  98 */       ps.setString(28, nvEmbarcacao.getBoca());
/*  99 */       ps.setString(29, nvEmbarcacao.getPontal());
/* 100 */       ps.setString(30, nvEmbarcacao.getCaladoLeve());
/* 101 */       ps.setString(31, nvEmbarcacao.getCaladoCarregado());
/* 102 */       ps.setString(32, nvEmbarcacao.getNomeProprietario());
/* 103 */       ps.setString(33, nvEmbarcacao.getCpfCnpj());
/* 104 */       ps.setString(34, nvEmbarcacao.getEndereco());
/* 105 */       ps.setString(35, nvEmbarcacao.getCidadeEstado());
/* 106 */       ps.setString(36, nvEmbarcacao.getNomeProprietario2());
/* 107 */       ps.setString(37, nvEmbarcacao.getCpfCnpj2());
/* 108 */       ps.setString(38, nvEmbarcacao.getEndereco2());
/* 109 */       ps.setString(39, nvEmbarcacao.getCidadeEstado2());
/* 110 */       ps.setString(40, nvEmbarcacao.getImo());
/* 111 */       ps.setObject(41, nvEmbarcacao.getSeqParceiro(), 1);
/* 112 */       ps.setString(42, nvEmbarcacao.getIndicativo());
/* 113 */       ps.setString(43, nvEmbarcacao.getPortoInscricao());
/* 114 */       ps.setString(44, nvEmbarcacao.getTipo());
/* 115 */       ps.setString(45, nvEmbarcacao.getCentroDiscoConves());
/* 116 */       ps.setString(46, nvEmbarcacao.getCentroDiscoProa());
/* 117 */       ps.setString(47, nvEmbarcacao.getLinhaConves());
/* 118 */       ps.setString(48, nvEmbarcacao.getMarcaLinhaDagua());
/* 119 */       ps.setString(49, nvEmbarcacao.getSeqEmpresa());
/* 120 */       ps.setString(50, nvEmbarcacao.getAreaNavegacaoTipo());
/* 121 */       ps.setString(51, nvEmbarcacao.getNumeroCasco());
/* 122 */       ps.setString(52, nvEmbarcacao.getComprimentoEntrePerpend());
/* 123 */       ps.setObject(53, nvEmbarcacao.getSeqNvCertificadoCalculoCSN(), 1);
/* 124 */       ps.setObject(54, nvEmbarcacao.getSeqNvCertificadoCalculoBLR(), 1);
/* 125 */       ps.setObject(55, nvEmbarcacao.getSeqNvCertificadoCalculoARQ(), 1);
/* 126 */       ps.setString(56, nvEmbarcacao.getCorrecaoNavegacaoAguaDoce());
/* 127 */       ps.setString(57, nvEmbarcacao.getCorrecaoNavegacaoAguaSalgada());
/* 128 */       ps.setString(58, nvEmbarcacao.getMarcaLinhaCargaArea2());
/* 129 */       ps.setString(59, nvEmbarcacao.getMarcaLinhaCargaArea2());
/* 130 */       ps.setString(60, nvEmbarcacao.getEspacosFechadosAbaixoConves());
/* 131 */       ps.setString(61, nvEmbarcacao.getEspacosFechadosAcimaConves());
/* 132 */       ps.setString(62, nvEmbarcacao.getVolumeTotalEspacos());
/* 133 */       ps.setString(63, nvEmbarcacao.getEspacoCarga());
/* 134 */       ps.setString(64, nvEmbarcacao.getDataLocalArqueacaoOriginal());
/* 135 */       ps.setString(65, nvEmbarcacao.getDataLocalUltimaRearqueacao());
/* 136 */       ps.setString(66, nvEmbarcacao.getTotalPassageirosCamarote());
/* 137 */       ps.setString(67, nvEmbarcacao.getTipoMarcaMotor());
/* 138 */       ps.setString(68, nvEmbarcacao.getNumeroMotor());
/* 139 */       ps.setString(69, nvEmbarcacao.getAutorizadoTransportarCargaConves());
/* 140 */       ps.setString(70, nvEmbarcacao.getBordaLivre());
/* 141 */       ps.setString(71, nvEmbarcacao.getDestinacaoReboque());
/* 159 */       ps.setString(72, nvEmbarcacao.getPotenciaNominalEletrica());
/* 160 */       ps.setString(73, nvEmbarcacao.getMercadoriasPerigosas());
/* 161 */       ps.setString(74, nvEmbarcacao.getApoioPortuario());
/* 162 */       ps.setString(75, nvEmbarcacao.getTipoAtividadeInterior());
/* 163 */       ps.setString(76, nvEmbarcacao.getCepEstaleiroConstrutor());
/* 164 */       ps.setString(77, nvEmbarcacao.getCepArmador());
/* 165 */       ps.setString(78, nvEmbarcacao.getRequisitosTransporteColetivo());
/* 166 */       ps.setString(79, nvEmbarcacao.getTipoPlantaPropulsora());
/* 167 */       ps.setString(80, nvEmbarcacao.getComprimentoRegra());
                ps.setString(81, nvEmbarcacao.getBandeira());
                ps.setString(82, nvEmbarcacao.getSeqUsuario());
                ps.setString(83, nvEmbarcacao.getApmaritimo());
                ps.setString(84, nvEmbarcacao.getCabotagem());
                ps.setString(85, nvEmbarcacao.getLongoCurso());
                ps.setString(86, nvEmbarcacao.getMaarea1());
                ps.setString(87, nvEmbarcacao.getMaarea12());
                ps.setString(88, nvEmbarcacao.getMaarea123());
                ps.setString(89, nvEmbarcacao.getMaarea1234());
                ps.setString(90, nvEmbarcacao.getNavint());
                ps.setString(91, nvEmbarcacao.getNavarea1());
/*     */       ps.setString(92, nvEmbarcacao.getNavarea2());
                ps.setString(93, nvEmbarcacao.getApportuario());
                ps.setString(94, nvEmbarcacao.getAparea1());
                ps.setString(95, nvEmbarcacao.getAparea2());
/* 169 */       ps.execute();
/* 170 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/* 173 */       Logger.getLogger(NvEmbarcacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/* 175 */     return nvEmbarcacao;
/*     */   }
/*     */   
/*     */   public NvEmbarcacao alterar(NvEmbarcacao nvEmbarcacao) {
/*     */     try {
/* 180 */       Conexao conexao = new Conexao();
/* 181 */       Connection conn = Conexao.getConnection();
/* 182 */       String sql = "update NV_EMBARCACAO set SITUACAO = ?,DATA_CADASTRO = ?,NOME = ?,NUMERO_INSCRICAO = ?,DATA_INSCRICAO = ?,LIVRO_INSCRICAO = ?,FOLHA_INSCRICAO = ?,IND_RADIO_INTERNAC = ?, SEQ_NV_TIPO_EMBARCACAO = ?,AREA_NAVEGACAO = ?,TIPO_PROPULSAO = ?,TIPO_ATIVIDADE_MAR = ?,TRIPULANTES = ?, PASSAGEIROS = ?,ANO_CONSTRUCAO = ?,CONSTRUTOR = ?,MATERIAL_CONSTRU_CASCO = ?,MATERIAL_SUPERTRUTURA = ?,POTENCIA_MOTOR = ?,TOTAL_MAQUINA = ?,TIPO_COMBUSTIVEL = ?,CAPAC_ARMAZENAMENTO = ?,ARQUEACAO_BRUTA = ?, ARQUEACAO_LIQUIDA = ?,TONELAGEM_PORTE_BRUTA = ?,COMPRIMENTO_TOTAL = ?,BOCA = ?,PONTAL = ?, CALADO_LEVE = ?,CALADO_CARREGADO = ?,NOME_PROPRIETARIO = ?,CPF_CNPJ = ?,ENDERECO = ?,CIDADE_ESTADO = ?, NOME_PROPRIETARIO2 = ?,CPF_CNPJ2 = ?,ENDERECO2 = ?,CIDADE_ESTADO2 = ?,IMO = ?,SEQ_PARCEIRO = ?, INDICATIVO = ?,PORTO_INSCRICAO = ?,TIPO = ?,CENTRO_DISCO_CONVES = ?,CENTRO_DISCO_PROA = ?,LINHA_CONVES = ?,MARCA_LINHA_DAGUA = ?,SEQ_EMPRESA = ?, area_navegacao_tipo = ?, casco_numero = ?,  COMPRIMENTO_ENTRE_PERPEND = ?,seq_nv_certificado_calculo_csn = ? , seq_nv_certificado_calculo_blr = ?,  seq_nv_certificado_calculo_arq = ?, correcao_navegacao_agua_doce = ?,  CORRECAO_NAV_AGUA_SALGADA = ?, MARCA_LINHA_CARGA_AREA1 = ?, MARCA_LINHA_CARGA_AREA2 = ?,  ESPACOS_FECHADOS_ABAIXO_CONVES = ?, ESPACOS_FECHADOS_ACIMA_CONVES = ?, VOLUME_TOTAL_ESPACOS_FECHADOS = ?, ESPACO_CARGA = ?, DATA_LOCAL_ARQUEACAO_ORIGINAL = ?, DATA_LOCAL_ULTIMA_REARQUEACAO = ?, TOTAL_PASSAG_CAMAR_8_BELICHES = ? ,TIPO_MARCA_MOTOR = ?, NUMERO_MOTOR = ?, AUTORIZADO_TRANSP_CARGA_CONVES = ?, borda_livre = ?, destinacao_reboque = ?,POTENCIA_NOMINAL_ELETRICA = ?, MERCADORIAS_PERIGOSAS = ?, apoio_portuario = ?, tipo_atividade_interior = ?, CEP_ESTALEIRO_CONSTRUTOR = ?, CEP_ARMADOR = ?, REQUISITOS_TRANSPORTE_COLETIVO = ?, TIPO_PLANTA_PROPULSORA = ?, COMPRIMENTO_REGRA = ?, BANDEIRA = ?, SEQ_USUARIO = ?,ap_maritimo = ?, cabotagem = ?, longo_curso = ?, ma_area1 = ?, ma_area12 = ?, ma_area123 = ?, ma_area1234 = ?, nav_int = ?, nav_area1 = ?, nav_area2 = ?, ap_portuario = ?, ap_area1 = ?, ap_area2 = ? where SEQ_NV_EMBARCACAO = ?";

/*     */ 
/* 216 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/* 218 */       ps.setString(1, nvEmbarcacao.getSituacao());
/*     */       try {
/* 220 */         ps.setDate(2, new java.sql.Date(nvEmbarcacao.getDataCadastro().getTime()));
/*     */       } catch (NullPointerException e) {
/* 222 */         ps.setDate(2, null);
/*     */       }
/* 224 */       ps.setString(3, nvEmbarcacao.getNome());
/* 225 */       ps.setString(4, nvEmbarcacao.getNumeroInscricao());
/*     */       try {
/* 227 */         ps.setDate(5, new java.sql.Date(nvEmbarcacao.getDataInscricao().getTime()));
/*     */       } catch (NullPointerException e) {
/* 229 */         ps.setDate(5, null);
/*     */       }
/* 231 */       ps.setString(6, nvEmbarcacao.getLivroInscricao());
/* 232 */       ps.setString(7, nvEmbarcacao.getFolhaInscricao());
/* 233 */       ps.setString(8, nvEmbarcacao.getIndRadioInternac());
/* 234 */       ps.setString(9, nvEmbarcacao.getSeqNvTipoEmbarcacao());
/* 235 */       ps.setString(10, nvEmbarcacao.getAreaNavegacao());
/* 236 */       ps.setString(11, nvEmbarcacao.getTipoPropulsao());
/* 237 */       ps.setString(12, nvEmbarcacao.getTipoAtividadeMar());
/* 238 */       ps.setString(13, nvEmbarcacao.getTripulantes());
/* 239 */       ps.setString(14, nvEmbarcacao.getPassageiros());
/* 240 */       ps.setString(15, nvEmbarcacao.getAnoConstrucao());
/* 241 */       ps.setString(16, nvEmbarcacao.getConstrutor());
/* 242 */       ps.setString(17, nvEmbarcacao.getMaterialConstruCasco());
/* 243 */       ps.setString(18, nvEmbarcacao.getMaterialSupertrutura());
/* 244 */       ps.setString(19, nvEmbarcacao.getPotenciaMotor());
/* 245 */       ps.setString(20, nvEmbarcacao.getTotalMaquina());
/* 246 */       ps.setString(21, nvEmbarcacao.getTipoCombustivel());
/* 247 */       ps.setString(22, nvEmbarcacao.getCapacArmazenamento());
/* 248 */       ps.setString(23, nvEmbarcacao.getArqueacaoBruta());
/* 249 */       ps.setString(24, nvEmbarcacao.getArqueacaoLiquida());
/* 250 */       ps.setString(25, nvEmbarcacao.getTonelagemPorteBruta());
/* 251 */       ps.setString(26, nvEmbarcacao.getComprimentoTotal());
/* 252 */       ps.setString(27, nvEmbarcacao.getBoca());
/* 253 */       ps.setString(28, nvEmbarcacao.getPontal());
/* 254 */       ps.setString(29, nvEmbarcacao.getCaladoLeve());
/* 255 */       ps.setString(30, nvEmbarcacao.getCaladoCarregado());
/* 256 */       ps.setString(31, nvEmbarcacao.getNomeProprietario());
/* 257 */       ps.setString(32, nvEmbarcacao.getCpfCnpj());
/* 258 */       ps.setString(33, nvEmbarcacao.getEndereco());
/* 259 */       ps.setString(34, nvEmbarcacao.getCidadeEstado());
/* 260 */       ps.setString(35, nvEmbarcacao.getNomeProprietario2());
/* 261 */       ps.setString(36, nvEmbarcacao.getCpfCnpj2());
/* 262 */       ps.setString(37, nvEmbarcacao.getEndereco2());
/* 263 */       ps.setString(38, nvEmbarcacao.getCidadeEstado2());
/* 264 */       ps.setString(39, nvEmbarcacao.getImo());
/* 265 */       ps.setString(40, nvEmbarcacao.getSeqParceiro());
/* 266 */       ps.setString(41, nvEmbarcacao.getIndicativo());
/* 267 */       ps.setString(42, nvEmbarcacao.getPortoInscricao());
/* 268 */       ps.setString(43, nvEmbarcacao.getTipo());
/* 269 */       ps.setString(44, nvEmbarcacao.getCentroDiscoConves());
/* 270 */       ps.setString(45, nvEmbarcacao.getCentroDiscoProa());
/* 271 */       ps.setString(46, nvEmbarcacao.getLinhaConves());
/* 272 */       ps.setString(47, nvEmbarcacao.getMarcaLinhaDagua());
/* 273 */       ps.setString(48, nvEmbarcacao.getSeqEmpresa());
/* 274 */       ps.setString(49, nvEmbarcacao.getAreaNavegacaoTipo());
/* 275 */       ps.setString(50, nvEmbarcacao.getNumeroCasco());
/* 276 */       ps.setString(51, nvEmbarcacao.getComprimentoEntrePerpend());
/* 277 */       ps.setObject(52, nvEmbarcacao.getSeqNvCertificadoCalculoCSN(), 1);
/* 278 */       ps.setObject(53, nvEmbarcacao.getSeqNvCertificadoCalculoBLR(), 1);
/* 279 */       ps.setObject(54, nvEmbarcacao.getSeqNvCertificadoCalculoARQ(), 1);
/* 280 */       ps.setString(55, nvEmbarcacao.getCorrecaoNavegacaoAguaDoce());
/* 281 */       ps.setString(56, nvEmbarcacao.getCorrecaoNavegacaoAguaSalgada());
/* 282 */       ps.setString(57, nvEmbarcacao.getMarcaLinhaCargaArea1());
/* 283 */       ps.setString(58, nvEmbarcacao.getMarcaLinhaCargaArea2());
/* 284 */       ps.setString(59, nvEmbarcacao.getEspacosFechadosAbaixoConves());
/* 285 */       ps.setString(60, nvEmbarcacao.getEspacosFechadosAcimaConves());
/* 286 */       ps.setString(61, nvEmbarcacao.getVolumeTotalEspacos());
/* 287 */       ps.setString(62, nvEmbarcacao.getEspacoCarga());
/* 288 */       ps.setString(63, nvEmbarcacao.getDataLocalArqueacaoOriginal());
/* 289 */       ps.setString(64, nvEmbarcacao.getDataLocalUltimaRearqueacao());
/* 290 */       ps.setString(65, nvEmbarcacao.getTotalPassageirosCamarote());
/* 291 */       ps.setString(66, nvEmbarcacao.getTipoMarcaMotor());
/* 292 */       ps.setString(67, nvEmbarcacao.getNumeroMotor());
/* 293 */       ps.setString(68, nvEmbarcacao.getAutorizadoTransportarCargaConves());
/* 294 */       ps.setString(69, nvEmbarcacao.getBordaLivre());
/* 295 */       ps.setString(70, nvEmbarcacao.getDestinacaoReboque());
/* 313 */       ps.setString(71, nvEmbarcacao.getPotenciaNominalEletrica());
/* 314 */       ps.setString(72, nvEmbarcacao.getMercadoriasPerigosas());
/* 315 */       ps.setString(73, nvEmbarcacao.getApoioPortuario());
/* 316 */       ps.setString(74, nvEmbarcacao.getTipoAtividadeInterior());
/* 317 */       ps.setString(75, nvEmbarcacao.getCepEstaleiroConstrutor());
/* 318 */       ps.setString(76, nvEmbarcacao.getCepArmador());
/* 319 */       ps.setString(77, nvEmbarcacao.getRequisitosTransporteColetivo());
/* 320 */       ps.setString(78, nvEmbarcacao.getTipoPlantaPropulsora());
/* 321 */       ps.setString(79, nvEmbarcacao.getComprimentoRegra());
                ps.setString(80, nvEmbarcacao.getBandeira());
                ps.setString(81, nvEmbarcacao.getSeqUsuario());
                ps.setString(82, nvEmbarcacao.getApmaritimo());
                ps.setString(83, nvEmbarcacao.getCabotagem());
                ps.setString(84, nvEmbarcacao.getLongoCurso());
                ps.setString(85, nvEmbarcacao.getMaarea1());
                ps.setString(86, nvEmbarcacao.getMaarea12());
                ps.setString(87, nvEmbarcacao.getMaarea123());
                ps.setString(88, nvEmbarcacao.getMaarea1234());
                ps.setString(89, nvEmbarcacao.getNavint());
                ps.setString(90, nvEmbarcacao.getNavarea1());
/*     */       ps.setString(91, nvEmbarcacao.getNavarea2());
                ps.setString(92, nvEmbarcacao.getApportuario());
                ps.setString(93, nvEmbarcacao.getAparea1());
                ps.setString(94, nvEmbarcacao.getAparea2());                
                ps.setString(95, nvEmbarcacao.getSeqNvEmbarcacao());
                
/*     */       
/* 324 */       ps.execute();
/* 325 */       ps.close();
/*     */     } catch (SQLException ex) {
/* 327 */       Logger.getLogger(NvEmbarcacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 328 */       System.out.println(ex.getMessage());
/*     */     }
/*     */     
/* 331 */     return nvEmbarcacao;
/*     */   }
/*     */   
/*     */   public List<NvEmbarcacao> listar(ClausulaWhere sClausula) {
/*     */     try {
/* 336 */       Conexao conexao = new Conexao();
/* 337 */       Connection conn = Conexao.getConnection();
/* 338 */       String sql = "SELECT NV_EMBARCACAO.*, NV_tipo_EMBARCACAO.codigo tipo_embarcacao_codigo, NV_tipo_EMBARCACAO.nome tipo_embarcacao_nome, parceiro.nome parceiro_nome FROM NV_EMBARCACAO left join NV_tipo_EMBARCACAO on NV_tipo_EMBARCACAO.seq_nv_tipo_embarcacao = nv_embarcacao.seq_nv_tipo_embarcacao left join parceiro on parceiro.seq_parceiro = nv_embarcacao.seq_parceiro " + sClausula.montarsClausula() + "ORDER BY NV_EMBARCACAO.NOME";

/* 346 */       List<NvEmbarcacao> listaNvEmbarcacao = new ArrayList();
/* 347 */       PreparedStatement ps = conn.prepareStatement(sql);
/* 348 */       ResultSet rs = ps.executeQuery();
/*     */       
/* 350 */       while (rs.next()) {
/* 351 */         NvEmbarcacao nvEmbarcacao = new NvEmbarcacao();
/* 352 */         nvEmbarcacao.setSeqNvEmbarcacao(rs.getString("SEQ_NV_EMBARCACAO"));
/* 353 */         nvEmbarcacao.setSituacao(rs.getString("SITUACAO"));
/* 354 */         nvEmbarcacao.setDataCadastro(rs.getDate("DATA_CADASTRO"));
/* 355 */         nvEmbarcacao.setNome(rs.getString("NOME"));
/* 356 */         nvEmbarcacao.setNumeroInscricao(rs.getString("NUMERO_INSCRICAO"));
/* 357 */         nvEmbarcacao.setDataInscricao(rs.getDate("DATA_INSCRICAO"));
/* 358 */         nvEmbarcacao.setLivroInscricao(rs.getString("LIVRO_INSCRICAO"));
/* 359 */         nvEmbarcacao.setFolhaInscricao(rs.getString("FOLHA_INSCRICAO"));
/* 360 */         nvEmbarcacao.setIndRadioInternac(rs.getString("IND_RADIO_INTERNAC"));
/* 361 */         nvEmbarcacao.setSeqNvTipoEmbarcacao(rs.getString("SEQ_NV_TIPO_EMBARCACAO"));
/* 362 */         nvEmbarcacao.setAreaNavegacao(rs.getString("AREA_NAVEGACAO"));
/* 363 */         nvEmbarcacao.setTipoPropulsao(rs.getString("TIPO_PROPULSAO"));
/* 364 */         nvEmbarcacao.setTipoAtividadeMar(rs.getString("TIPO_ATIVIDADE_MAR"));
/* 365 */         nvEmbarcacao.setTripulantes(rs.getString("TRIPULANTES"));
/* 366 */         nvEmbarcacao.setPassageiros(rs.getString("PASSAGEIROS"));
/* 367 */         nvEmbarcacao.setAnoConstrucao(rs.getString("ANO_CONSTRUCAO"));
/* 368 */         nvEmbarcacao.setConstrutor(rs.getString("CONSTRUTOR"));
/* 369 */         nvEmbarcacao.setMaterialConstruCasco(rs.getString("MATERIAL_CONSTRU_CASCO"));
/* 370 */         nvEmbarcacao.setMaterialSupertrutura(rs.getString("MATERIAL_SUPERTRUTURA"));
/* 371 */         nvEmbarcacao.setPotenciaMotor(rs.getString("POTENCIA_MOTOR"));
/* 372 */         nvEmbarcacao.setTotalMaquina(rs.getString("TOTAL_MAQUINA"));
/* 373 */         nvEmbarcacao.setTipoCombustivel(rs.getString("TIPO_COMBUSTIVEL"));
/* 374 */         nvEmbarcacao.setCapacArmazenamento(rs.getString("CAPAC_ARMAZENAMENTO"));
/* 375 */         nvEmbarcacao.setArqueacaoBruta(rs.getString("ARQUEACAO_BRUTA"));
/* 376 */         nvEmbarcacao.setArqueacaoLiquida(rs.getString("ARQUEACAO_LIQUIDA"));
/* 377 */         nvEmbarcacao.setTonelagemPorteBruta(rs.getString("TONELAGEM_PORTE_BRUTA"));
/* 378 */         nvEmbarcacao.setComprimentoTotal(rs.getString("COMPRIMENTO_TOTAL"));
/* 379 */         nvEmbarcacao.setBoca(rs.getString("BOCA"));
/* 380 */         nvEmbarcacao.setPontal(rs.getString("PONTAL"));
/* 381 */         nvEmbarcacao.setCaladoLeve(rs.getString("CALADO_LEVE"));
/* 382 */         nvEmbarcacao.setCaladoCarregado(rs.getString("CALADO_CARREGADO"));
/* 383 */         nvEmbarcacao.setNomeProprietario(rs.getString("NOME_PROPRIETARIO"));
/* 384 */         nvEmbarcacao.setCpfCnpj(rs.getString("CPF_CNPJ"));
/* 385 */         nvEmbarcacao.setEndereco(rs.getString("ENDERECO"));
/* 386 */         nvEmbarcacao.setCidadeEstado(rs.getString("CIDADE_ESTADO"));
/* 387 */         nvEmbarcacao.setNomeProprietario2(rs.getString("NOME_PROPRIETARIO2"));
/* 388 */         nvEmbarcacao.setCpfCnpj2(rs.getString("CPF_CNPJ2"));
/* 389 */         nvEmbarcacao.setEndereco2(rs.getString("ENDERECO2"));
/* 390 */         nvEmbarcacao.setCidadeEstado2(rs.getString("CIDADE_ESTADO2"));
/* 391 */         nvEmbarcacao.setImo(rs.getString("IMO"));
/* 392 */         nvEmbarcacao.setSeqParceiro(rs.getString("SEQ_PARCEIRO"));
/* 393 */         nvEmbarcacao.setIndicativo(rs.getString("INDICATIVO"));
/* 394 */         nvEmbarcacao.setPortoInscricao(rs.getString("PORTO_INSCRICAO"));
/* 395 */         nvEmbarcacao.setTipo(rs.getString("TIPO"));
/* 396 */         nvEmbarcacao.setCentroDiscoConves(rs.getString("CENTRO_DISCO_CONVES"));
/* 397 */         nvEmbarcacao.setCentroDiscoProa(rs.getString("CENTRO_DISCO_PROA"));
/* 398 */         nvEmbarcacao.setLinhaConves(rs.getString("LINHA_CONVES"));
/* 399 */         nvEmbarcacao.setMarcaLinhaDagua(rs.getString("MARCA_LINHA_DAGUA"));
/* 400 */         nvEmbarcacao.setSeqEmpresa(rs.getString("SEQ_EMPRESA"));
/* 401 */         nvEmbarcacao.setAreaNavegacaoTipo(rs.getString("area_navegacao_tipo"));
/* 402 */         nvEmbarcacao.setNumeroCasco(rs.getString("casco_numero"));
/* 403 */         nvEmbarcacao.setComprimentoEntrePerpend(rs.getString("COMPRIMENTO_ENTRE_PERPEND"));
/* 404 */         nvEmbarcacao.setSeqNvCertificadoCalculoCSN(rs.getString("seq_nv_certificado_calculo_csn"));
/* 405 */         nvEmbarcacao.setSeqNvCertificadoCalculoBLR(rs.getString("seq_nv_certificado_calculo_BLR"));
/* 406 */         nvEmbarcacao.setSeqNvCertificadoCalculoARQ(rs.getString("seq_nv_certificado_calculo_ARQ"));
/* 407 */         nvEmbarcacao.setTipoEmbarcacaoCodigo(rs.getString("tipo_embarcacao_codigo"));
/* 408 */         nvEmbarcacao.setTipoEmbarcacaoNome(rs.getString("tipo_embarcacao_nome"));
/* 409 */         nvEmbarcacao.setParceiroNome(rs.getString("parceiro_nome"));
/* 410 */         nvEmbarcacao.setCorrecaoNavegacaoAguaDoce(rs.getString("correcao_navegacao_agua_doce"));
/* 411 */         nvEmbarcacao.setCorrecaoNavegacaoAguaSalgada(rs.getString("CORRECAO_NAV_AGUA_SALGADA"));
/* 412 */         nvEmbarcacao.setMarcaLinhaCargaArea1(rs.getString("MARCA_LINHA_CARGA_AREA1"));
/* 413 */         nvEmbarcacao.setMarcaLinhaCargaArea2(rs.getString("MARCA_LINHA_CARGA_AREA2"));
/* 414 */         nvEmbarcacao.setEspacosFechadosAbaixoConves(rs.getString("ESPACOS_FECHADOS_ABAIXO_CONVES"));
/* 415 */         nvEmbarcacao.setEspacosFechadosAcimaConves(rs.getString("ESPACOS_FECHADOS_ACIMA_CONVES"));
/* 416 */         nvEmbarcacao.setVolumeTotalEspacos(rs.getString("VOLUME_TOTAL_ESPACOS_FECHADOS"));
/* 417 */         nvEmbarcacao.setEspacoCarga(rs.getString("ESPACO_CARGA"));
/* 418 */         nvEmbarcacao.setDataLocalArqueacaoOriginal(rs.getString("DATA_LOCAL_ARQUEACAO_ORIGINAL"));
/* 419 */         nvEmbarcacao.setDataLocalUltimaRearqueacao(rs.getString("DATA_LOCAL_ULTIMA_REARQUEACAO"));
/* 420 */         nvEmbarcacao.setTotalPassageirosCamarote(rs.getString("TOTAL_PASSAG_CAMAR_8_BELICHES"));
/* 421 */         nvEmbarcacao.setTipoMarcaMotor(rs.getString("TIPO_MARCA_MOTOR"));
/* 422 */         nvEmbarcacao.setNumeroMotor(rs.getString("NUMERO_MOTOR"));
/* 423 */         nvEmbarcacao.setAutorizadoTransportarCargaConves(rs.getString("AUTORIZADO_TRANSP_CARGA_CONVES"));
/* 424 */         nvEmbarcacao.setBordaLivre(rs.getString("borda_livre"));
/* 425 */         nvEmbarcacao.setDestinacaoReboque(rs.getString("destinacao_reboque"));
                  
/* 443 */         nvEmbarcacao.setPotenciaNominalEletrica(rs.getString("POTENCIA_NOMINAL_ELETRICA"));
/* 444 */         nvEmbarcacao.setMercadoriasPerigosas(rs.getString("MERCADORIAS_PERIGOSAS"));
/* 445 */         nvEmbarcacao.setApoioPortuario(rs.getString("APOIO_PORTUARIO"));
/* 446 */         nvEmbarcacao.setTipoAtividadeInterior(rs.getString("TIPO_ATIVIDADE_INTERIOR"));
/* 447 */         nvEmbarcacao.setCepEstaleiroConstrutor(rs.getString("CEP_ESTALEIRO_CONSTRUTOR"));
/* 448 */         nvEmbarcacao.setCepArmador(rs.getString("CEP_ARMADOR"));
/* 449 */         nvEmbarcacao.setRequisitosTransporteColetivo(rs.getString("REQUISITOS_TRANSPORTE_COLETIVO"));
/* 450 */         nvEmbarcacao.setTipoPlantaPropulsora(rs.getString("TIPO_PLANTA_PROPULSORA"));
/* 451 */         nvEmbarcacao.setComprimentoRegra(rs.getString("COMPRIMENTO_REGRA"));
/*     */         nvEmbarcacao.setBandeira(rs.getString("BANDEIRA"));
                  nvEmbarcacao.setApmaritimo(rs.getString("ap_maritimo"));
                  nvEmbarcacao.setCabotagem(rs.getString("cabotagem"));
                  nvEmbarcacao.setLongoCurso(rs.getString("longo_curso"));
                  nvEmbarcacao.setMaarea1(rs.getString("ma_area1"));
                  nvEmbarcacao.setMaarea12(rs.getString("ma_area12"));
                  nvEmbarcacao.setMaarea123(rs.getString("ma_area123"));
                  nvEmbarcacao.setMaarea1234(rs.getString("ma_area1234"));
                  nvEmbarcacao.setNavint(rs.getString("nav_int"));
                  nvEmbarcacao.setNavarea1(rs.getString("nav_area1"));
                  nvEmbarcacao.setNavarea2(rs.getString("nav_area2"));
                  nvEmbarcacao.setApportuario(rs.getString("ap_portuario"));
                  nvEmbarcacao.setAparea1(rs.getString("ap_area1"));
                  nvEmbarcacao.setAparea2(rs.getString("ap_area2"));
/* 453 */         listaNvEmbarcacao.add(nvEmbarcacao);
/*     */       }
/*     */       
/* 456 */       ps.execute();
/* 457 */       ps.close();
/*     */       
/* 459 */       return listaNvEmbarcacao;
/*     */     } catch (SQLException ex) {
/* 461 */       Logger.getLogger(NvEmbarcacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 462 */       System.out.println(ex.getMessage()); }
/* 463 */     return null;
/*     */   }

/*     */   public List<NvEmbarcacao> listarPCliente(ClausulaWhere sClausula) {
/*     */     try {
/* 336 */       Conexao conexao = new Conexao();
/* 337 */       Connection conn = Conexao.getConnection();
/* 338 */       String sql = "SELECT NV_EMBARCACAO.*, NV_tipo_EMBARCACAO.codigo tipo_embarcacao_codigo, NV_tipo_EMBARCACAO.nome tipo_embarcacao_nome, parceiro.nome parceiro_nome, nv_embarcacao_usuario.seq_usuario seq_usuario FROM NV_EMBARCACAO inner join NV_EMBARCACAO_USUARIO on NV_EMBARCACAO_USUARIO.seq_embarcacao = NV_EMBARCACAO.SEQ_NV_EMBARCACAO left join NV_tipo_EMBARCACAO on NV_tipo_EMBARCACAO.seq_nv_tipo_embarcacao = nv_embarcacao.seq_nv_tipo_embarcacao left join parceiro on parceiro.seq_parceiro = nv_embarcacao.seq_parceiro " + sClausula.montarsClausula() + "ORDER BY NV_EMBARCACAO.NOME";

/* 346 */       List<NvEmbarcacao> listaNvEmbarcacao = new ArrayList();
/* 347 */       PreparedStatement ps = conn.prepareStatement(sql);
/* 348 */       ResultSet rs = ps.executeQuery();
/*     */       
/* 350 */       while (rs.next()) {
/* 351 */         NvEmbarcacao nvEmbarcacao = new NvEmbarcacao();
/* 352 */         nvEmbarcacao.setSeqNvEmbarcacao(rs.getString("SEQ_NV_EMBARCACAO"));
/* 353 */         nvEmbarcacao.setSituacao(rs.getString("SITUACAO"));
/* 354 */         nvEmbarcacao.setDataCadastro(rs.getDate("DATA_CADASTRO"));
/* 355 */         nvEmbarcacao.setNome(rs.getString("NOME"));
/* 356 */         nvEmbarcacao.setNumeroInscricao(rs.getString("NUMERO_INSCRICAO"));
/* 357 */         nvEmbarcacao.setDataInscricao(rs.getDate("DATA_INSCRICAO"));
/* 358 */         nvEmbarcacao.setLivroInscricao(rs.getString("LIVRO_INSCRICAO"));
/* 359 */         nvEmbarcacao.setFolhaInscricao(rs.getString("FOLHA_INSCRICAO"));
/* 360 */         nvEmbarcacao.setIndRadioInternac(rs.getString("IND_RADIO_INTERNAC"));
/* 361 */         nvEmbarcacao.setSeqNvTipoEmbarcacao(rs.getString("SEQ_NV_TIPO_EMBARCACAO"));
/* 362 */         nvEmbarcacao.setAreaNavegacao(rs.getString("AREA_NAVEGACAO"));
/* 363 */         nvEmbarcacao.setTipoPropulsao(rs.getString("TIPO_PROPULSAO"));
/* 364 */         nvEmbarcacao.setTipoAtividadeMar(rs.getString("TIPO_ATIVIDADE_MAR"));
/* 365 */         nvEmbarcacao.setTripulantes(rs.getString("TRIPULANTES"));
/* 366 */         nvEmbarcacao.setPassageiros(rs.getString("PASSAGEIROS"));
/* 367 */         nvEmbarcacao.setAnoConstrucao(rs.getString("ANO_CONSTRUCAO"));
/* 368 */         nvEmbarcacao.setConstrutor(rs.getString("CONSTRUTOR"));
/* 369 */         nvEmbarcacao.setMaterialConstruCasco(rs.getString("MATERIAL_CONSTRU_CASCO"));
/* 370 */         nvEmbarcacao.setMaterialSupertrutura(rs.getString("MATERIAL_SUPERTRUTURA"));
/* 371 */         nvEmbarcacao.setPotenciaMotor(rs.getString("POTENCIA_MOTOR"));
/* 372 */         nvEmbarcacao.setTotalMaquina(rs.getString("TOTAL_MAQUINA"));
/* 373 */         nvEmbarcacao.setTipoCombustivel(rs.getString("TIPO_COMBUSTIVEL"));
/* 374 */         nvEmbarcacao.setCapacArmazenamento(rs.getString("CAPAC_ARMAZENAMENTO"));
/* 375 */         nvEmbarcacao.setArqueacaoBruta(rs.getString("ARQUEACAO_BRUTA"));
/* 376 */         nvEmbarcacao.setArqueacaoLiquida(rs.getString("ARQUEACAO_LIQUIDA"));
/* 377 */         nvEmbarcacao.setTonelagemPorteBruta(rs.getString("TONELAGEM_PORTE_BRUTA"));
/* 378 */         nvEmbarcacao.setComprimentoTotal(rs.getString("COMPRIMENTO_TOTAL"));
/* 379 */         nvEmbarcacao.setBoca(rs.getString("BOCA"));
/* 380 */         nvEmbarcacao.setPontal(rs.getString("PONTAL"));
/* 381 */         nvEmbarcacao.setCaladoLeve(rs.getString("CALADO_LEVE"));
/* 382 */         nvEmbarcacao.setCaladoCarregado(rs.getString("CALADO_CARREGADO"));
/* 383 */         nvEmbarcacao.setNomeProprietario(rs.getString("NOME_PROPRIETARIO"));
/* 384 */         nvEmbarcacao.setCpfCnpj(rs.getString("CPF_CNPJ"));
/* 385 */         nvEmbarcacao.setEndereco(rs.getString("ENDERECO"));
/* 386 */         nvEmbarcacao.setCidadeEstado(rs.getString("CIDADE_ESTADO"));
/* 387 */         nvEmbarcacao.setNomeProprietario2(rs.getString("NOME_PROPRIETARIO2"));
/* 388 */         nvEmbarcacao.setCpfCnpj2(rs.getString("CPF_CNPJ2"));
/* 389 */         nvEmbarcacao.setEndereco2(rs.getString("ENDERECO2"));
/* 390 */         nvEmbarcacao.setCidadeEstado2(rs.getString("CIDADE_ESTADO2"));
/* 391 */         nvEmbarcacao.setImo(rs.getString("IMO"));
/* 392 */         nvEmbarcacao.setSeqParceiro(rs.getString("SEQ_PARCEIRO"));
/* 393 */         nvEmbarcacao.setIndicativo(rs.getString("INDICATIVO"));
/* 394 */         nvEmbarcacao.setPortoInscricao(rs.getString("PORTO_INSCRICAO"));
/* 395 */         nvEmbarcacao.setTipo(rs.getString("TIPO"));
/* 396 */         nvEmbarcacao.setCentroDiscoConves(rs.getString("CENTRO_DISCO_CONVES"));
/* 397 */         nvEmbarcacao.setCentroDiscoProa(rs.getString("CENTRO_DISCO_PROA"));
/* 398 */         nvEmbarcacao.setLinhaConves(rs.getString("LINHA_CONVES"));
/* 399 */         nvEmbarcacao.setMarcaLinhaDagua(rs.getString("MARCA_LINHA_DAGUA"));
/* 400 */         nvEmbarcacao.setSeqEmpresa(rs.getString("SEQ_EMPRESA"));
/* 401 */         nvEmbarcacao.setAreaNavegacaoTipo(rs.getString("area_navegacao_tipo"));
/* 402 */         nvEmbarcacao.setNumeroCasco(rs.getString("casco_numero"));
/* 403 */         nvEmbarcacao.setComprimentoEntrePerpend(rs.getString("COMPRIMENTO_ENTRE_PERPEND"));
/* 404 */         nvEmbarcacao.setSeqNvCertificadoCalculoCSN(rs.getString("seq_nv_certificado_calculo_csn"));
/* 405 */         nvEmbarcacao.setSeqNvCertificadoCalculoBLR(rs.getString("seq_nv_certificado_calculo_BLR"));
/* 406 */         nvEmbarcacao.setSeqNvCertificadoCalculoARQ(rs.getString("seq_nv_certificado_calculo_ARQ"));
/* 407 */         nvEmbarcacao.setTipoEmbarcacaoCodigo(rs.getString("tipo_embarcacao_codigo"));
/* 408 */         nvEmbarcacao.setTipoEmbarcacaoNome(rs.getString("tipo_embarcacao_nome"));
/* 409 */         nvEmbarcacao.setParceiroNome(rs.getString("parceiro_nome"));
/* 410 */         nvEmbarcacao.setCorrecaoNavegacaoAguaDoce(rs.getString("correcao_navegacao_agua_doce"));
/* 411 */         nvEmbarcacao.setCorrecaoNavegacaoAguaSalgada(rs.getString("CORRECAO_NAV_AGUA_SALGADA"));
/* 412 */         nvEmbarcacao.setMarcaLinhaCargaArea1(rs.getString("MARCA_LINHA_CARGA_AREA1"));
/* 413 */         nvEmbarcacao.setMarcaLinhaCargaArea2(rs.getString("MARCA_LINHA_CARGA_AREA2"));
/* 414 */         nvEmbarcacao.setEspacosFechadosAbaixoConves(rs.getString("ESPACOS_FECHADOS_ABAIXO_CONVES"));
/* 415 */         nvEmbarcacao.setEspacosFechadosAcimaConves(rs.getString("ESPACOS_FECHADOS_ACIMA_CONVES"));
/* 416 */         nvEmbarcacao.setVolumeTotalEspacos(rs.getString("VOLUME_TOTAL_ESPACOS_FECHADOS"));
/* 417 */         nvEmbarcacao.setEspacoCarga(rs.getString("ESPACO_CARGA"));
/* 418 */         nvEmbarcacao.setDataLocalArqueacaoOriginal(rs.getString("DATA_LOCAL_ARQUEACAO_ORIGINAL"));
/* 419 */         nvEmbarcacao.setDataLocalUltimaRearqueacao(rs.getString("DATA_LOCAL_ULTIMA_REARQUEACAO"));
/* 420 */         nvEmbarcacao.setTotalPassageirosCamarote(rs.getString("TOTAL_PASSAG_CAMAR_8_BELICHES"));
/* 421 */         nvEmbarcacao.setTipoMarcaMotor(rs.getString("TIPO_MARCA_MOTOR"));
/* 422 */         nvEmbarcacao.setNumeroMotor(rs.getString("NUMERO_MOTOR"));
/* 423 */         nvEmbarcacao.setAutorizadoTransportarCargaConves(rs.getString("AUTORIZADO_TRANSP_CARGA_CONVES"));
/* 424 */         nvEmbarcacao.setBordaLivre(rs.getString("borda_livre"));
/* 425 */         nvEmbarcacao.setDestinacaoReboque(rs.getString("destinacao_reboque"));
                  nvEmbarcacao.setSeqUsuario(rs.getString("seq_usuario"));
/* 443 */         nvEmbarcacao.setPotenciaNominalEletrica(rs.getString("POTENCIA_NOMINAL_ELETRICA"));
/* 444 */         nvEmbarcacao.setMercadoriasPerigosas(rs.getString("MERCADORIAS_PERIGOSAS"));
/* 445 */         nvEmbarcacao.setApoioPortuario(rs.getString("APOIO_PORTUARIO"));
/* 446 */         nvEmbarcacao.setTipoAtividadeInterior(rs.getString("TIPO_ATIVIDADE_INTERIOR"));
/* 447 */         nvEmbarcacao.setCepEstaleiroConstrutor(rs.getString("CEP_ESTALEIRO_CONSTRUTOR"));
/* 448 */         nvEmbarcacao.setCepArmador(rs.getString("CEP_ARMADOR"));
/* 449 */         nvEmbarcacao.setRequisitosTransporteColetivo(rs.getString("REQUISITOS_TRANSPORTE_COLETIVO"));
/* 450 */         nvEmbarcacao.setTipoPlantaPropulsora(rs.getString("TIPO_PLANTA_PROPULSORA"));
/* 451 */         nvEmbarcacao.setComprimentoRegra(rs.getString("COMPRIMENTO_REGRA"));
/*     */         nvEmbarcacao.setBandeira(rs.getString("BANDEIRA"));
                  nvEmbarcacao.setApmaritimo(rs.getString("ap_maritimo"));
                  nvEmbarcacao.setCabotagem(rs.getString("cabotagem"));
                  nvEmbarcacao.setLongoCurso(rs.getString("longo_curso"));
                  nvEmbarcacao.setMaarea1(rs.getString("ma_area1"));
                  nvEmbarcacao.setMaarea12(rs.getString("ma_area12"));
                  nvEmbarcacao.setMaarea123(rs.getString("ma_area123"));
                  nvEmbarcacao.setMaarea1234(rs.getString("ma_area1234"));
                  nvEmbarcacao.setNavint(rs.getString("nav_int"));
                  nvEmbarcacao.setNavarea1(rs.getString("nav_area1"));
                  nvEmbarcacao.setNavarea2(rs.getString("nav_area2"));
                  nvEmbarcacao.setApportuario(rs.getString("ap_portuario"));
                  nvEmbarcacao.setAparea1(rs.getString("ap_area1"));
                  nvEmbarcacao.setAparea2(rs.getString("ap_area2"));
/* 453 */         listaNvEmbarcacao.add(nvEmbarcacao);
/*     */       }
/*     */       
/* 456 */       ps.execute();
/* 457 */       ps.close();
/*     */       
/* 459 */       return listaNvEmbarcacao;
/*     */     } catch (SQLException ex) {
/* 461 */       Logger.getLogger(NvEmbarcacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 462 */       System.out.println(ex.getMessage()); }
/* 463 */     return null;
/*     */   }

/*     */   
/*     */   public boolean deletar(NvEmbarcacao nvEmbarcacao)
/*     */   {
/*     */     try {
/* 469 */       Conexao conexao = new Conexao();
/* 470 */       Connection conn = Conexao.getConnection();
/* 471 */       String sql = "DELETE FROM NV_EMBARCACAO WHERE SEQ_NV_EMBARCACAO =  ? ";
/*     */       
/* 473 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/* 475 */       ps.setString(1, nvEmbarcacao.getSeqNvEmbarcacao());
/*     */       
/* 477 */       ps.execute();
/* 478 */       ps.close();
/*     */       
/* 480 */       return true;
/*     */     } catch (SQLException ex) {
/* 482 */       System.out.println(ex.getMessage()); }
/* 483 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/diogo.lima/Documents/PEDIDO.jar!/NvEmbarcacao/NvEmbarcacaoDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */