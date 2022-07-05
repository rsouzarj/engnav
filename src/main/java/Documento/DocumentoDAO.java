/*      */ package Documento;
/*      */ 
/*      */ import ClausulaSQL.ClausulaWhere;
/*      */ import DocumentoItemEmbarcacao.DocumentoItemEmbarcacao;
/*      */ import DocumentoItemEmbarcacao.DocumentoItemEmbarcacaoService;
/*      */ import DocumentoItemEquipamento.DocumentoItemEquipamento;
/*      */ import DocumentoItemEquipamento.DocumentoItemEquipamentoService;
/*      */ import DocumentoItemMaterial.DocumentoItemMaterial;
/*      */ import DocumentoItemMaterial.DocumentoItemMaterialService;
/*      */ import DocumentoItemServico.DocumentoItemServico;
/*      */ import DocumentoItemServico.DocumentoItemServicoService;
/*      */ import DocumentoOcorrencia.DocumentoOcorrencia;
/*      */ import DocumentoOcorrencia.DocumentoOcorrenciaService;
/*      */ import Util.Conexao;
/*      */ import Util.Sequence;
/*      */ import java.io.PrintStream;
/*      */ import java.sql.Connection;
/*      */ import java.sql.PreparedStatement;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.SQLException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
/*      */ import java.util.logging.Level;
/*      */ import java.util.logging.Logger;


/*      */ public class DocumentoDAO
/*      */ {
/*      */   public Documento inserir(Documento documento)
/*      */   {
/*      */     try
/*      */     {
/*   37 */       String seq = Sequence.buscarNumeroSequence("SEQ_DOCUMENTO");
/*   38 */       documento.setSeqDocumento(seq);
/*   39 */       Conexao conexao = new Conexao();
/*   40 */       Connection conn = Conexao.getConnection();
/*   41 */       String sql = "insert into DOCUMENTO (SEQ_DOCUMENTO,DESCRICAO,DATA_PREVISAO_CONCLUSAO,SEQ_FORMA_PAGAMENTO,SEQ_CONTA,SEQ_CONDICAO_PAGAMENTO,SEQ_TIPO_MOVIMENTO_FINANCEIRO,SEQ_DOCUMENTO_DONO,SEQ_ASS_COLABORADOR,N_CONTRATO,REEMBOLSO_TRANSPORTE,REEMBOLSO_ALIMENTACAO,REEMBOLSO_HOSPEDAGEM,REEMBOLSO_PLOTAGENS,REEMBOLSO_OUTROS,CODIGO,SEQ_EMPRESA,SEQ_PARCEIRO,SEQ_TIPO_DOCUMENTO,SEQ_DOCUMENTO_ETAPA,DATA_CADASTRO,SEQ_USUARIO_CADASTRO,DATA,SEQ_LUGAR,local_servico,vl_entrada,qtde_parcela,DATA_PAGAMENTO_PARCELADO,vl_total_documento,TRIBUTOS_IMPOSTOS,seq_parceiro_contato,seq_parceiro_conta_pagar,origem_parceiro,seq_unidade_negocio,id_revisao,comentario_etapa,retencao_issqn,seq_projecao_tributaria,aliquota_issqn_retido,seq_aliquota_federal,vl_acumulado,vl_tributo_n_incluso,vl_bruto,vl_issqn_retido,vl_retencao_federal,vl_liquido,sequencia, moeda,id_complementar,observacao,taxa_cambio,MOEDA_DESTINO,ESCOPO,VL_TOTALCAMBIO,MOSTRA_TOTAL,CAMINHO,objetivo, validade, desc_remuneracao, cond_gerais, condpag, assunto) values  (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
/*      */       
/*      */ 
/*      */ 
/*   45 */       PreparedStatement ps = conn.prepareStatement(sql);
/*      */       
/*   47 */       ps.setString(1, documento.getSeqDocumento());
/*   48 */       ps.setString(2, documento.getDescricao());
/*      */       try {
/*   50 */         ps.setDate(3, new java.sql.Date(documento.getDataPrevisaoConclusao().getTime()));
/*      */       } catch (NullPointerException e) {
/*   52 */         ps.setDate(3, null);
/*      */       }
/*   54 */       ps.setString(4, documento.getSeqFormaPagamento());
/*   55 */       ps.setString(5, documento.getSeqConta());
/*   56 */       ps.setString(6, documento.getSeqCondicaoPagamento());
/*   57 */       ps.setString(7, documento.getSeqTipoMovimentoFinanceiro());
/*   58 */       ps.setString(8, documento.getSeqDocumentoDono());
/*   59 */       ps.setString(9, documento.getSeqAssColaborador());
/*   60 */       ps.setString(10, documento.getnContrato());
/*   61 */       ps.setString(11, documento.getReembolsoTransporte());
/*   62 */       ps.setString(12, documento.getReembolsoAlimentacao());
/*   63 */       ps.setString(13, documento.getReembolsoHospedagem());
/*   64 */       ps.setString(14, documento.getReembolsoPlotagens());
/*   65 */       ps.setString(15, documento.getReembolsoOutros());
/*   66 */       ps.setString(16, documento.getCodigo());
/*   67 */       ps.setString(17, documento.getSeqEmpresa());
/*   68 */       ps.setString(18, documento.getSeqParceiro());
/*   69 */       ps.setString(19, documento.getSeqTipoDocumento());
/*   70 */       ps.setString(20, documento.getSeqDocumentoEtapa());
/*      */       try {
/*   72 */         ps.setDate(21, new java.sql.Date(documento.getDataCadastro().getTime()));
/*      */       } catch (NullPointerException e) {
/*   74 */         ps.setDate(21, null);
/*      */       }
/*   76 */       ps.setString(22, documento.getSeqUsuarioCadastro());
/*      */       try {
/*   78 */         ps.setDate(23, new java.sql.Date(documento.getData().getTime()));
/*      */       } catch (NullPointerException e) {
/*   80 */         ps.setDate(23, null);
/*      */       }
/*   82 */       ps.setString(24, documento.getSeqLugar());
/*   83 */       ps.setString(25, documento.getLocalServico());
/*   84 */       ps.setBigDecimal(26, documento.getVlEntrada());
/*   85 */       ps.setBigDecimal(27, documento.getQtdeParcela());
/*   86 */       ps.setString(28, documento.getDatasParcelado());
/*   87 */       ps.setBigDecimal(29, documento.getVlTotal());
/*   88 */       ps.setString(30, documento.getTributosImpostos());
/*   89 */       ps.setString(31, documento.getSeqParceiroContato());
/*   90 */       ps.setString(32, documento.getSeqParceiroContaPagar());
/*   91 */       ps.setString(33, documento.getOrigemParceiro());
/*   92 */       ps.setString(34, documento.getSeqUnidadeNegocio());
/*   93 */       ps.setLong(35, documento.getIdRevisao().longValue());
/*   94 */       ps.setString(36, documento.getComentarioEtapa());
/*      */       
/*   96 */       ps.setBigDecimal(37, documento.getRetencaoISSQN());
/*   97 */       ps.setBigDecimal(38, documento.getSeqProjecaoTributaria());
/*   98 */       ps.setBigDecimal(39, documento.getAliquotaISSQNretido());
/*   99 */       ps.setBigDecimal(40, documento.getSeqAliquotaFederal());
/*  100 */       ps.setBigDecimal(41, documento.getVlExecutadoAcumulado());
/*  101 */       ps.setBigDecimal(42, documento.getVlTributosNaoIncluso());
/*  102 */       ps.setBigDecimal(43, documento.getVlBruto());
/*  103 */       ps.setBigDecimal(44, documento.getVlISSQNRetido());
/*  104 */       ps.setBigDecimal(45, documento.getVlRetencaoFederal());
/*  105 */       ps.setBigDecimal(46, documento.getVlLiquidoAReceber());
/*  106 */       ps.setInt(47, documento.getSequencia());
/*  107 */       ps.setString(48, documento.getMoeda());
/*  108 */       ps.setLong(49, documento.getIdComplementar().longValue());
/*  109 */       ps.setString(50, documento.getObservacao());
/*  110 */       ps.setBigDecimal(51, documento.getTaxaCambio());
/*  111 */       ps.setString(52, documento.getMoedaDestino());
/*      */       ps.setString(53, documento.getEscopo());
                 ps.setBigDecimal(54, documento.getVltotalcambio());
/*      */       ps.setString(55, documento.getMostraTotal());                 
/*      */       ps.setString(56, documento.getCaminho());
/*      */       ps.setString(57, documento.getObjetivo());
/*      */       ps.setString(58, documento.getValidade());
/*      */       ps.setString(59, documento.getDescRemuneracao());
/*      */       ps.setString(60, documento.getCondicoesGerais());
/*      */       ps.setString(61, documento.getCondpag());
/*      */       ps.setString(62, documento.getAssunto());
/*  113 */       ps.execute();
/*  114 */       ps.close();
/*      */       
/*      */ 
/*  117 */       DocumentoItemMaterialService documentoItemMaterialService = new DocumentoItemMaterialService();
/*  118 */       for (DocumentoItemMaterial item : documento.getListaDocumentoItemMaterial()) {
/*  119 */         item.setSeqDocumentoItemMaterial(null);
/*  120 */         item.setSeqEmpresa(documento.getSeqEmpresa());
/*  121 */         item.setSeqDocumento(documento.getSeqDocumento());
/*  122 */         item.setSituacao("ATIVO");
/*  123 */         documentoItemMaterialService.salvar(item);
/*      */       }
/*      */       
/*      */ 
/*  127 */       DocumentoItemServicoService documentoItemServicoService = new DocumentoItemServicoService();
/*  128 */       for (DocumentoItemServico item : documento.getListaDocumentoItemServico()) {
/*  129 */         item.setSeqDocumentoItemServico(null);
/*  130 */         item.setSeqEmpresa(documento.getSeqEmpresa());
/*  131 */         item.setSeqDocumento(documento.getSeqDocumento());
/*  132 */         item.setSituacao("ATIVO");
/*  133 */         documentoItemServicoService.salvar(item);
/*      */       }
/*      */       
/*      */ 
/*  137 */       DocumentoItemEmbarcacaoService documentoItemEmbarcacaoService = new DocumentoItemEmbarcacaoService();
/*  138 */       for (DocumentoItemEmbarcacao item : documento.getListaDocumentoItemEmbarcacao()) {
/*  139 */         item.setSeqDocumentoItemEmbarcacao(null);
/*  140 */         item.setSeqDocumento(documento.getSeqDocumento());
/*  141 */         documentoItemEmbarcacaoService.salvar(item);
/*      */       }
/*      */       
/*      */ 
/*  145 */       DocumentoItemEquipamentoService documentoItemEquipamentoService = new DocumentoItemEquipamentoService();
/*  146 */       for (DocumentoItemEquipamento item : documento.getListaDocumentoItemEquipamento()) {
/*  147 */         item.setSeqDocumentoItemEquipamento(null);
/*  148 */         item.setSeqDocumento(documento.getSeqDocumento());
/*  149 */         documentoItemEquipamentoService.salvar(item);
/*      */       }
/*      */       
/*      */ 
/*  153 */       DocumentoOcorrenciaService documentoOcorrenciaService = new DocumentoOcorrenciaService();
/*  154 */       DocumentoOcorrencia documentoOcorrencia = new DocumentoOcorrencia();
/*  155 */       documentoOcorrencia.setSeqDocumento(documento.getSeqDocumento());
/*  156 */       documentoOcorrencia.setTipo("Automática");
/*  157 */       documentoOcorrencia.setDescricao("Ocorrência de cadastro.");
/*  158 */       documentoOcorrencia.setSeqUsuario(documento.getSeqUsuarioCadastro());
/*  159 */       documentoOcorrenciaService.salvar(documentoOcorrencia);
/*      */     }
/*      */     catch (SQLException ex) {
/*  162 */       Logger.getLogger(DocumentoDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  163 */       System.out.println(ex.getMessage());
/*      */     }
/*  165 */     return documento;
/*      */   }
/*      */   
/*      */   public Documento alterar(Documento documento) {
/*      */     try {
/*  170 */       Conexao conexao = new Conexao();
/*  171 */       Connection conn = Conexao.getConnection();
/*  172 */       String sql = "update DOCUMENTO set DESCRICAO = ?,DATA_PREVISAO_CONCLUSAO = ?,SEQ_FORMA_PAGAMENTO = ?,SEQ_CONTA = ?,SEQ_CONDICAO_PAGAMENTO = ?,SEQ_TIPO_MOVIMENTO_FINANCEIRO = ?,SEQ_DOCUMENTO_DONO = ?,SEQ_ASS_COLABORADOR = ?,N_CONTRATO = ?,REEMBOLSO_TRANSPORTE = ?,REEMBOLSO_ALIMENTACAO = ?,REEMBOLSO_HOSPEDAGEM = ?,REEMBOLSO_PLOTAGENS = ?,REEMBOLSO_OUTROS = ?,CODIGO = ?,SEQ_EMPRESA = ?,SEQ_PARCEIRO = ?,SEQ_TIPO_DOCUMENTO = ?,SEQ_DOCUMENTO_ETAPA = ?,DATA_CADASTRO = ?,SEQ_USUARIO_CADASTRO = ?,DATA = ?,SEQ_LUGAR = ?, local_servico = ?, vl_entrada = ?, qtde_parcela = ?, DATA_PAGAMENTO_PARCELADO = ?, vl_total_documento = ?, TRIBUTOS_IMPOSTOS = ?, seq_parceiro_contato = ?, seq_parceiro_conta_pagar = ?, origem_parceiro = ?,seq_unidade_negocio = ?, id_revisao = ?, comentario_etapa = ?,retencao_issqn = ?,seq_projecao_tributaria = ?,aliquota_issqn_retido = ?,seq_aliquota_federal = ?,vl_acumulado = ?,vl_tributo_n_incluso = ?,vl_bruto = ?,vl_issqn_retido = ?,vl_retencao_federal = ?,vl_liquido = ?, sequencia = ?, MOEDA = ?, id_complementar = ?, observacao = ?, taxa_cambio = ?, MOEDA_DESTINO = ?, ESCOPO = ?, VL_TOTALCAMBIO = ?, MOSTRA_TOTAL = ?, CAMINHO = ?, OBJETIVO = ?, VALIDADE = ?, DESC_REMUNERACAO = ?, COND_GERAIS = ?, CONDPAG = ?, ASSUNTO = ? where SEQ_DOCUMENTO = ?";
/*      */       
/*  174 */       PreparedStatement ps = conn.prepareStatement(sql);
/*      */       
/*  176 */       ps.setString(1, documento.getDescricao());
/*      */       try {
/*  178 */         ps.setDate(2, new java.sql.Date(documento.getDataPrevisaoConclusao().getTime()));
/*      */       } catch (NullPointerException e) {
/*  180 */         ps.setDate(2, null);
/*      */       }
/*  182 */       ps.setString(3, documento.getSeqFormaPagamento());
/*  183 */       ps.setString(4, documento.getSeqConta());
/*  184 */       ps.setString(5, documento.getSeqCondicaoPagamento());
/*  185 */       ps.setString(6, documento.getSeqTipoMovimentoFinanceiro());
/*  186 */       ps.setString(7, documento.getSeqDocumentoDono());
/*  187 */       ps.setString(8, documento.getSeqAssColaborador());
/*  188 */       ps.setString(9, documento.getnContrato());
/*  189 */       ps.setString(10, documento.getReembolsoTransporte());
/*  190 */       ps.setString(11, documento.getReembolsoAlimentacao());
/*  191 */       ps.setString(12, documento.getReembolsoHospedagem());
/*  192 */       ps.setString(13, documento.getReembolsoPlotagens());
/*  193 */       ps.setString(14, documento.getReembolsoOutros());
/*  194 */       ps.setString(15, documento.getCodigo());
/*  195 */       ps.setString(16, documento.getSeqEmpresa());
/*  196 */       ps.setString(17, documento.getSeqParceiro());
/*  197 */       ps.setString(18, documento.getSeqTipoDocumento());
/*  198 */       ps.setString(19, documento.getSeqDocumentoEtapa());
/*      */       try {
/*  200 */         ps.setDate(20, new java.sql.Date(documento.getDataCadastro().getTime()));
/*      */       } catch (NullPointerException e) {
/*  202 */         ps.setDate(20, null);
/*      */       }
/*  204 */       ps.setString(21, documento.getSeqUsuarioCadastro());
/*      */       try {
/*  206 */         ps.setDate(22, new java.sql.Date(documento.getData().getTime()));
/*      */       } catch (NullPointerException e) {
/*  208 */         ps.setDate(22, null);
/*      */       }
/*  210 */       ps.setString(23, documento.getSeqLugar());
/*  211 */       ps.setString(24, documento.getLocalServico());
/*  212 */       ps.setBigDecimal(25, documento.getVlEntrada());
/*  213 */       ps.setBigDecimal(26, documento.getQtdeParcela());
/*  214 */       ps.setString(27, documento.getDatasParcelado());
/*  215 */       ps.setBigDecimal(28, documento.getVlTotal());
/*  216 */       ps.setString(29, documento.getTributosImpostos());
/*  217 */       ps.setString(30, documento.getSeqParceiroContato());
/*  218 */       ps.setString(31, documento.getSeqParceiroContaPagar());
/*  219 */       ps.setString(32, documento.getOrigemParceiro());
/*  220 */       ps.setString(33, documento.getSeqUnidadeNegocio());
/*  221 */       ps.setLong(34, documento.getIdRevisao().longValue());
/*  222 */       ps.setString(35, documento.getComentarioEtapa());
/*      */       
/*  224 */       ps.setBigDecimal(36, documento.getRetencaoISSQN());
/*  225 */       ps.setBigDecimal(37, documento.getSeqProjecaoTributaria());
/*  226 */       ps.setBigDecimal(38, documento.getAliquotaISSQNretido());
/*  227 */       ps.setBigDecimal(39, documento.getSeqAliquotaFederal());
/*  228 */       ps.setBigDecimal(40, documento.getVlExecutadoAcumulado());
/*  229 */       ps.setBigDecimal(41, documento.getVlTributosNaoIncluso());
/*  230 */       ps.setBigDecimal(42, documento.getVlBruto());
/*  231 */       ps.setBigDecimal(43, documento.getVlISSQNRetido());
/*  232 */       ps.setBigDecimal(44, documento.getVlRetencaoFederal());
/*  233 */       ps.setBigDecimal(45, documento.getVlLiquidoAReceber());
/*  234 */       ps.setInt(46, documento.getSequencia());
/*  235 */       ps.setString(47, documento.getMoeda());
/*  236 */       ps.setLong(48, documento.getIdComplementar().longValue());
/*  237 */       ps.setString(49, documento.getObservacao());
/*  238 */       ps.setBigDecimal(50, documento.getTaxaCambio());
/*  239 */       ps.setString(51, documento.getMoedaDestino());
/*  240 */       ps.setString(52, documento.getEscopo());
                 ps.setBigDecimal(53, documento.getVltotalcambio());
/*      */       ps.setString(54, documento.getMostraTotal());
/*      */       ps.setString(55, documento.getCaminho());
/*      */       ps.setString(56, documento.getObjetivo());
/*      */       ps.setString(57, documento.getValidade());
/*      */       ps.setString(58, documento.getDescRemuneracao());
/*      */       ps.setString(59, documento.getCondicoesGerais());
/*      */       ps.setString(60, documento.getCondpag());
/*      */       ps.setString(61, documento.getAssunto());
                 ps.setString(62, documento.getSeqDocumento());
/*  241 */       ps.execute();
/*  242 */       ps.close();
/*      */       
/*      */ 
/*  245 */       DocumentoItemMaterialService documentoItemMaterialService = new DocumentoItemMaterialService();
/*  246 */       for (DocumentoItemMaterial item : documento.getListaDocumentoItemMaterial()) {
/*  247 */         item.setSeqEmpresa(documento.getSeqEmpresa());
/*  248 */         item.setSeqDocumento(documento.getSeqDocumento());
/*  249 */         item.setSituacao("ATIVO");
/*  250 */         documentoItemMaterialService.salvar(item);
/*      */       }
/*      */       
/*  253 */       List<DocumentoItemMaterial> listaMaterialOriginal = documentoItemMaterialService.listarPorDocumento(documento.getSeqDocumento());
/*  254 */       for (DocumentoItemMaterial itemOriginal : listaMaterialOriginal) {
/*  255 */         boolean encontrou = false;
/*  256 */         for (DocumentoItemMaterial item : documento.getListaDocumentoItemMaterial()) {
/*  257 */           if (itemOriginal.getSeqDocumentoItemMaterial().equals(item.getSeqDocumentoItemMaterial())) {
/*  258 */             encontrou = true;
/*      */           }
/*      */         }
/*  261 */         if (!encontrou) {
/*  262 */           documentoItemMaterialService.deletar(itemOriginal);
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*  267 */       DocumentoItemServicoService documentoItemServicoService = new DocumentoItemServicoService();
/*  268 */       for (DocumentoItemServico item : documento.getListaDocumentoItemServico()) {
/*  269 */         item.setSeqEmpresa(documento.getSeqEmpresa());
/*  270 */         item.setSeqDocumento(documento.getSeqDocumento());
/*  271 */         item.setSituacao("ATIVO");
/*  272 */         documentoItemServicoService.salvar(item);
/*      */       }
/*      */       
/*  275 */       List<DocumentoItemServico> listaServicoOriginal = documentoItemServicoService.listarPorDocumento(documento.getSeqDocumento());
/*  276 */       for (DocumentoItemServico itemOriginal : listaServicoOriginal) {
/*  277 */         boolean encontrou = false;
/*  278 */         for (DocumentoItemServico item : documento.getListaDocumentoItemServico()) {
/*  279 */           if (itemOriginal.getSeqServicoEscopo().equals(item.getSeqServicoEscopo())) {
/*  280 */             encontrou = true;
/*      */           }
/*      */         }
/*  283 */         if (!encontrou) {
/*  284 */           documentoItemServicoService.deletar(itemOriginal);
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*  289 */       DocumentoItemEmbarcacaoService documentoItemEmbarcacaoService = new DocumentoItemEmbarcacaoService();
/*  290 */       for (DocumentoItemEmbarcacao item : documento.getListaDocumentoItemEmbarcacao()) {
/*  291 */         item.setSeqDocumento(documento.getSeqDocumento());
/*  292 */         documentoItemEmbarcacaoService.salvar(item);
/*      */       }
/*      */       
/*  295 */       List<DocumentoItemEmbarcacao> listaEmbarcacaoOriginal = documentoItemEmbarcacaoService.listarPorDocumento(documento.getSeqDocumento());
/*  296 */       for (DocumentoItemEmbarcacao itemOriginal : listaEmbarcacaoOriginal) {
/*  297 */         boolean encontrou = false;
/*  298 */         for (DocumentoItemEmbarcacao item : documento.getListaDocumentoItemEmbarcacao()) {
/*  299 */           if (itemOriginal.getSeqDocumentoItemEmbarcacao().equals(item.getSeqDocumentoItemEmbarcacao())) {
/*  300 */             encontrou = true;
/*      */           }
/*      */         }
/*  303 */         if (!encontrou) {
/*  304 */           documentoItemEmbarcacaoService.deletar(itemOriginal);
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*  309 */       DocumentoItemEquipamentoService documentoItemEquipamentoService = new DocumentoItemEquipamentoService();
/*  310 */       for (DocumentoItemEquipamento item : documento.getListaDocumentoItemEquipamento()) {
/*  311 */         item.setSeqDocumento(documento.getSeqDocumento());
/*  312 */         documentoItemEquipamentoService.salvar(item);
/*      */       }
/*      */       
/*  315 */       List<DocumentoItemEquipamento> listaEquipamentoOriginal = documentoItemEquipamentoService.listarPorDocumento(documento.getSeqDocumento());
/*  316 */       for (DocumentoItemEquipamento itemOriginal : listaEquipamentoOriginal) {
/*  317 */         boolean encontrou = false;
/*  318 */         for (DocumentoItemEquipamento item : documento.getListaDocumentoItemEquipamento()) {
/*  319 */           if (itemOriginal.getSeqDocumentoItemEquipamento().equals(item.getSeqDocumentoItemEquipamento())) {
/*  320 */             encontrou = true;
/*      */           }
/*      */         }
/*  323 */         if (!encontrou) {
/*  324 */           documentoItemEquipamentoService.deletar(itemOriginal);
/*      */         }
/*      */       }
/*      */     } catch (SQLException ex) {
/*      */       DocumentoItemEquipamentoService documentoItemEquipamentoService;
/*  329 */       Logger.getLogger(DocumentoDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  330 */       System.out.println(ex.getMessage());
/*      */     }
/*      */     
/*  333 */     return documento;
/*      */   }
/*      */   
/*      */   public List<Documento> listar(ClausulaWhere sClausula) {
/*      */     try {
/*  338 */       Conexao conexao = new Conexao();
/*  339 */       Connection conn = Conexao.getConnection();
/*  340 */       String sql = "select \ndocumento.* ,\nparceiro.nome parceiro,\nparceiro.tipo tipo_pessoa,parceiro.logradouro || ', ' || parceiro.numero||' ' || parceiro.complemento||' - '||parceiro.bairro||' - '||parceiro.cidade||'-'||parceiro.uf||' | CEP:'||parceiro.cep endereco,parceiro.documento,parceiro.telefone_1||' | '||parceiro.telefone_2||' | '|| parceiro.telefone_3 telefone,parceiro.email,tipo_parceiro.nome tipo_parceiro,\ntipo_documento.nome tipo_documento,\ntipo_documento.template template,\ndocumento_etapa.nome documento_etapa,\nusuario.usuario usuario_cadastro,\nusuario.seq_unidade_negocio unidadeNegocioUsuario,\ndoc_dono.codigo DocDonoCodigo,\ntp_dono.nome DocDonoTipo,\ntp_dono.seq_tipo_documento DocDonoSeqTipo,\nconta.*,\nunidade_negocio.nome filial,\nnv_embarcacao.nome embarcacaoNome,\nequipamento.nome equipamentoNome,\ntipo_movimento_financeiro.nome natureza,\nfinanceiro_categoria.nome categoria\nfrom \ndocumento\ninner join parceiro on parceiro.seq_parceiro = documento.seq_parceiro\ninner join tipo_parceiro on tipo_parceiro.seq_tipo_parceiro = parceiro.seq_tipo_parceiro\ninner join tipo_documento on tipo_documento.seq_tipo_documento = documento.seq_tipo_documento\ninner join documento_etapa on documento_etapa.seq_documento_etapa = documento.seq_documento_etapa\nleft join usuario on usuario.seq_usuario = documento.seq_usuario_cadastro\nleft join unidade_negocio on usuario.SEQ_UNIDADE_NEGOCIO = unidade_negocio.SEQ_UNIDADE_NEGOCIO \nleft join documento doc_dono on doc_dono.seq_documento = documento.seq_documento_dono\nleft join tipo_documento tp_dono on tp_dono.seq_tipo_documento = doc_dono.seq_tipo_documento\nleft join conta on conta.seq_conta = documento.seq_conta\nleft join tipo_movimento_financeiro on tipo_movimento_financeiro.seq_tipo_movimento_financeiro = documento.seq_tipo_movimento_financeiro\nleft join financeiro_categoria on financeiro_categoria.seq_financeiro_categoria = tipo_movimento_financeiro.seq_tipo_movimento_financeiro\nleft join documento_item_equipamento on documento_item_equipamento.seq_documento = documento.SEQ_DOCUMENTO\nleft join documento_item_embarcacao on documento_item_embarcacao.seq_documento = documento.SEQ_DOCUMENTO\nleft join equipamento on equipamento.seq_equipamento = documento_item_equipamento.seq_equipamento and documento_item_equipamento.seq_documento = documento.seq_documento\nleft join nv_embarcacao on nv_embarcacao.seq_nv_embarcacao = documento_item_embarcacao.SEQ_NV_EMBARCACAO and documento_item_embarcacao.seq_documento = documento.seq_documento\n   \n" + sClausula.montarsClausula() + " order by documento.codigo asc";

/*  386 */       System.out.println(sql);
/*      */       
/*  388 */       List<Documento> listaDocumento = new ArrayList();
/*  389 */       PreparedStatement ps = conn.prepareStatement(sql);
/*  390 */       ResultSet rs = ps.executeQuery();
/*      */       
/*  392 */       while (rs.next()) {
/*  393 */         Documento documento = new Documento();
/*  394 */         documento.setSeqDocumento(rs.getString("SEQ_DOCUMENTO"));
/*  395 */         documento.setDescricao(rs.getString("DESCRICAO"));
/*  396 */         documento.setDataPrevisaoConclusao(rs.getDate("DATA_PREVISAO_CONCLUSAO"));
/*  397 */         documento.setSeqFormaPagamento(rs.getString("SEQ_FORMA_PAGAMENTO"));
/*  398 */         documento.setSeqConta(rs.getString("SEQ_CONTA"));
/*  399 */         documento.setSeqCondicaoPagamento(rs.getString("SEQ_CONDICAO_PAGAMENTO"));
/*  400 */         documento.setSeqTipoMovimentoFinanceiro(rs.getString("SEQ_TIPO_MOVIMENTO_FINANCEIRO"));
/*  401 */         documento.setSeqDocumentoDono(rs.getString("SEQ_DOCUMENTO_DONO"));
/*      */         
/*  403 */         documento.setSeqAssColaborador(rs.getString("SEQ_ASS_COLABORADOR"));
/*  404 */         documento.setnContrato(rs.getString("N_CONTRATO"));
/*  405 */         documento.setReembolsoTransporte(rs.getString("REEMBOLSO_TRANSPORTE"));
/*  406 */         documento.setReembolsoAlimentacao(rs.getString("REEMBOLSO_ALIMENTACAO"));
/*  407 */         documento.setReembolsoHospedagem(rs.getString("REEMBOLSO_HOSPEDAGEM"));
/*  408 */         documento.setReembolsoPlotagens(rs.getString("REEMBOLSO_PLOTAGENS"));
/*  409 */         documento.setReembolsoOutros(rs.getString("REEMBOLSO_OUTROS"));
/*      */         
/*  411 */         documento.setCodigo(rs.getString("CODIGO"));
/*  412 */         documento.setSeqEmpresa(rs.getString("SEQ_EMPRESA"));
/*  413 */         documento.setSeqParceiro(rs.getString("SEQ_PARCEIRO"));
/*  414 */         documento.setSeqTipoDocumento(rs.getString("SEQ_TIPO_DOCUMENTO"));
/*  415 */         documento.setSeqDocumentoEtapa(rs.getString("SEQ_DOCUMENTO_ETAPA"));
/*  416 */         documento.setDataCadastro(rs.getDate("DATA_CADASTRO"));
/*  417 */         documento.setSeqUsuarioCadastro(rs.getString("SEQ_USUARIO_CADASTRO"));
/*  418 */         documento.setData(rs.getDate("DATA"));
/*  419 */         documento.setSeqLugar(rs.getString("SEQ_LUGAR"));
/*      */         
/*  421 */         documento.setDocDonoSeqTipo(rs.getString("DocDonoSeqTipo"));
/*  422 */         documento.setDocDonoTipo(rs.getString("DocDonoTipo"));
/*  423 */         documento.setDocDonoCodigo(rs.getString("DocDonoCodigo"));
/*      */         
/*  425 */         documento.setParceiro(rs.getString("PARCEIRO"));
/*  426 */         documento.setParceiroTipoPessoa(rs.getString("TIPO_PESSOA"));
/*  427 */         documento.setParceiroEndereco(rs.getString("ENDERECO"));
/*  428 */         documento.setParceiroDocumento(rs.getString("DOCUMENTO"));
/*  429 */         documento.setParceiroTelefone(rs.getString("TELEFONE"));
/*  430 */         documento.setParceiroEmail(rs.getString("EMAIL"));
/*  431 */         documento.setSeqCondicaoPagamento(rs.getString("SEQ_CONDICAO_PAGAMENTO"));
/*  432 */         documento.setSeqFormaPagamento(rs.getString("SEQ_FORMA_PAGAMENTO"));
/*      */         
/*  434 */         documento.setTipoParceiro(rs.getString("TIPO_PARCEIRO"));
/*  435 */         documento.setTipoDocumento(rs.getString("TIPO_DOCUMENTO"));
/*  436 */         documento.setTemplate(rs.getString("TEMPLATE"));
/*  437 */         documento.setEtapa(rs.getString("DOCUMENTO_ETAPA"));
/*  438 */         documento.setUsuarioCadastro(rs.getString("USUARIO_CADASTRO"));
/*  439 */         documento.setDataPrevisaoConclusao(rs.getDate("data_previsao_conclusao"));
/*      */         
/*  441 */         documento.setLocalServico(rs.getString("local_servico"));
/*  442 */         documento.setVlEntrada(rs.getBigDecimal("vl_entrada"));
/*  443 */         documento.setQtdeParcela(rs.getBigDecimal("qtde_parcela"));
/*  444 */         documento.setDatasParcelado(rs.getString("DATA_PAGAMENTO_PARCELADO"));
/*  445 */         documento.setTributosImpostos(rs.getString("TRIBUTOS_IMPOSTOS"));
/*  446 */         documento.setVlTotal(rs.getBigDecimal("vl_total_documento"));
/*  447 */         documento.setSeqParceiroContato(rs.getString("seq_parceiro_contato"));
/*  448 */         documento.setSeqParceiroContaPagar(rs.getString("seq_parceiro_conta_pagar"));
/*  449 */         documento.setOrigemParceiro(rs.getString("origem_parceiro"));
/*  450 */         documento.setSeqUnidadeNegocio(rs.getString("seq_unidade_negocio"));
/*  451 */         documento.setIdRevisao(Long.valueOf(rs.getLong("id_revisao")));
/*  452 */         documento.setIdComplementar(Long.valueOf(rs.getLong("id_complementar")));
/*  453 */         documento.setComentarioEtapa(rs.getString("comentario_etapa"));
/*      */         
/*  455 */         documento.setRetencaoISSQN(rs.getBigDecimal("retencao_issqn"));
/*  456 */         documento.setSeqProjecaoTributaria(rs.getBigDecimal("seq_projecao_tributaria"));
/*  457 */         documento.setAliquotaISSQNretido(rs.getBigDecimal("aliquota_issqn_retido"));
/*  458 */         documento.setSeqAliquotaFederal(rs.getBigDecimal("seq_aliquota_federal"));
/*  459 */         documento.setVlExecutadoAcumulado(rs.getBigDecimal("vl_acumulado"));
/*  460 */         documento.setVlTributosNaoIncluso(rs.getBigDecimal("vl_tributo_n_incluso"));
/*  461 */         documento.setVlBruto(rs.getBigDecimal("vl_bruto"));
/*  462 */         documento.setVlISSQNRetido(rs.getBigDecimal("vl_issqn_retido"));
/*  463 */         documento.setVlRetencaoFederal(rs.getBigDecimal("vl_retencao_federal"));
/*  464 */         documento.setVlLiquidoAReceber(rs.getBigDecimal("vl_liquido"));
/*  465 */         documento.setTarifaBancaria(rs.getBigDecimal("vl_tarifa"));
/*  466 */         documento.setSequencia(rs.getInt("sequencia"));
/*  467 */         documento.setMoeda(rs.getString("MOEDA"));
/*  468 */         documento.setFilial(rs.getString("FILIAL"));
/*  469 */         documento.setObservacao(rs.getString("observacao"));
/*  470 */         documento.setTaxaCambio(rs.getBigDecimal("taxa_cambio"));
/*      */         
/*  472 */         documento.setNvEmbarcacaoNome(rs.getString("embarcacaoNome"));
/*  473 */         documento.setEquipamentoNome(rs.getString("equipamentoNome"));
/*      */         
/*  475 */         documento.setNatureza(rs.getString("natureza"));
/*  476 */         documento.setCategoria(rs.getString("categoria"));
/*  477 */         documento.setMoedaDestino(rs.getString("MOEDA_DESTINO"));
/*  478 */         documento.setUnidadeNegocioUsuario(rs.getString("unidadeNegocioUsuario"));
/*      */         documento.setEscopo(rs.getString("escopo"));
/*      */         documento.setMostraTotal(rs.getString("MOSTRA_TOTAL"));        
/*      */         documento.setCaminho(rs.getString("CAMINHO"));
/*      */         documento.setObjetivo(rs.getString("OBJETIVO"));                   
/*      */         documento.setValidade(rs.getString("VALIDADE"));
/*      */         documento.setDescRemuneracao(rs.getString("DESC_REMUNERACAO"));
/*      */         documento.setCondicoesGerais(rs.getString("COND_GERAIS"));
/*      */         documento.setCondpag(rs.getString("CONDPAG"));
/*      */         documento.setAssunto(rs.getString("ASSUNTO"));
/*  480 */         listaDocumento.add(documento);
/*      */       }
/*      */       
/*  483 */       ps.execute();
/*  484 */       ps.close();
/*      */       
/*  486 */       return listaDocumento;
/*      */     } catch (SQLException ex) {
/*  488 */       Logger.getLogger(DocumentoDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  489 */       System.out.println(ex.getMessage()); }
/*  490 */     return null;
/*      */   }
/*      */   
/*      */   public List<Documento> listarBMFaturamento(ClausulaWhere sClausula)
/*      */   {
/*      */     try {
/*  496 */       Conexao conexao = new Conexao();
/*  497 */       Connection conn = Conexao.getConnection();
/*  498 */       String sql = "select \ndocumento.* ,\nparceiro.nome parceiro,\nparceiro.tipo tipo_pessoa,parceiro.logradouro || ', ' || parceiro.numero||' ' || parceiro.complemento||' - '||parceiro.bairro||' - '||parceiro.cidade||'-'||parceiro.uf||' | CEP:'||parceiro.cep endereco,parceiro.documento,parceiro.telefone_1||' | '||parceiro.telefone_2||' | '|| parceiro.telefone_3 telefone,parceiro.email,tipo_parceiro.nome tipo_parceiro,\ntipo_documento.nome tipo_documento,\ntipo_documento.template template,\ndocumento_etapa.nome documento_etapa,\nusuario.usuario usuario_cadastro,\ndoc_dono.codigo DocDonoCodigo,\ntp_dono.nome DocDonoTipo,\ntp_dono.seq_tipo_documento DocDonoSeqTipo,\nconta.*,\nunidade_negocio.nome filial,\nnv_embarcacao.nome embarcacaoNome,\nequipamento.nome equipamentoNome,\ntipo_movimento_financeiro.nome natureza,\nfinanceiro_categoria.nome categoria\nfrom \ndocumento\ninner join parceiro on parceiro.seq_parceiro = documento.seq_parceiro\ninner join tipo_parceiro on tipo_parceiro.seq_tipo_parceiro = parceiro.seq_tipo_parceiro\ninner join tipo_documento on tipo_documento.seq_tipo_documento = documento.seq_tipo_documento\ninner join documento_etapa on documento_etapa.seq_documento_etapa = documento.seq_documento_etapa\nleft join usuario on usuario.seq_usuario = documento.seq_usuario_cadastro\nleft join unidade_negocio on usuario.SEQ_UNIDADE_NEGOCIO = unidade_negocio.SEQ_UNIDADE_NEGOCIO \nleft join documento doc_dono on doc_dono.seq_documento = documento.seq_documento_dono\nleft join tipo_documento tp_dono on tp_dono.seq_tipo_documento = doc_dono.seq_tipo_documento\nleft join conta on conta.seq_conta = documento.seq_conta\nleft join tipo_movimento_financeiro on tipo_movimento_financeiro.seq_tipo_movimento_financeiro = documento.seq_tipo_movimento_financeiro\nleft join financeiro_categoria on financeiro_categoria.seq_financeiro_categoria = tipo_movimento_financeiro.seq_tipo_movimento_financeiro\nleft join documento_item_equipamento on documento_item_equipamento.seq_documento = documento.SEQ_DOCUMENTO\nleft join documento_item_embarcacao on documento_item_embarcacao.seq_documento = documento.SEQ_DOCUMENTO\nleft join equipamento on equipamento.seq_equipamento = documento_item_equipamento.seq_equipamento and documento_item_equipamento.seq_documento = documento.seq_documento\nleft join nv_embarcacao on nv_embarcacao.seq_nv_embarcacao = documento_item_embarcacao.SEQ_NV_EMBARCACAO and documento_item_embarcacao.seq_documento = documento.seq_documento\n   \n" + sClausula.montarsClausula() + " order by documento.codigo asc";
/*      */       
/*  544 */       List<Documento> listaDocumento = new ArrayList();
/*  545 */       PreparedStatement ps = conn.prepareStatement(sql);
/*  546 */       ResultSet rs = ps.executeQuery();
/*      */       
/*  548 */       while (rs.next()) {
/*  549 */         Documento documento = new Documento();
/*  550 */         documento.setSeqDocumento(rs.getString("SEQ_DOCUMENTO"));
/*  551 */         documento.setDescricao(rs.getString("DESCRICAO"));
/*  552 */         documento.setDataPrevisaoConclusao(rs.getDate("DATA_PREVISAO_CONCLUSAO"));
/*  553 */         documento.setSeqFormaPagamento(rs.getString("SEQ_FORMA_PAGAMENTO"));
/*  554 */         documento.setSeqConta(rs.getString("SEQ_CONTA"));
/*  555 */         documento.setSeqCondicaoPagamento(rs.getString("SEQ_CONDICAO_PAGAMENTO"));
/*  556 */         documento.setSeqTipoMovimentoFinanceiro(rs.getString("SEQ_TIPO_MOVIMENTO_FINANCEIRO"));
/*  557 */         documento.setSeqDocumentoDono(rs.getString("SEQ_DOCUMENTO_DONO"));
/*      */         
/*  559 */         documento.setSeqAssColaborador(rs.getString("SEQ_ASS_COLABORADOR"));
/*  560 */         documento.setnContrato(rs.getString("N_CONTRATO"));
/*  561 */         documento.setReembolsoTransporte(rs.getString("REEMBOLSO_TRANSPORTE"));
/*  562 */         documento.setReembolsoAlimentacao(rs.getString("REEMBOLSO_ALIMENTACAO"));
/*  563 */         documento.setReembolsoHospedagem(rs.getString("REEMBOLSO_HOSPEDAGEM"));
/*  564 */         documento.setReembolsoPlotagens(rs.getString("REEMBOLSO_PLOTAGENS"));
/*  565 */         documento.setReembolsoOutros(rs.getString("REEMBOLSO_OUTROS"));
/*      */         
/*  567 */         documento.setCodigo(rs.getString("CODIGO"));
/*  568 */         documento.setSeqEmpresa(rs.getString("SEQ_EMPRESA"));
/*  569 */         documento.setSeqParceiro(rs.getString("SEQ_PARCEIRO"));
/*  570 */         documento.setSeqTipoDocumento(rs.getString("SEQ_TIPO_DOCUMENTO"));
/*  571 */         documento.setSeqDocumentoEtapa(rs.getString("SEQ_DOCUMENTO_ETAPA"));
/*  572 */         documento.setDataCadastro(rs.getDate("DATA_CADASTRO"));
/*  573 */         documento.setSeqUsuarioCadastro(rs.getString("SEQ_USUARIO_CADASTRO"));
/*  574 */         documento.setData(rs.getDate("DATA"));
/*  575 */         documento.setSeqLugar(rs.getString("SEQ_LUGAR"));
/*      */         
/*  577 */         documento.setDocDonoSeqTipo(rs.getString("DocDonoSeqTipo"));
/*  578 */         documento.setDocDonoTipo(rs.getString("DocDonoTipo"));
/*  579 */         documento.setDocDonoCodigo(rs.getString("DocDonoCodigo"));
/*      */         
/*  581 */         documento.setParceiro(rs.getString("PARCEIRO"));
/*  582 */         documento.setParceiroTipoPessoa(rs.getString("TIPO_PESSOA"));
/*  583 */         documento.setParceiroEndereco(rs.getString("ENDERECO"));
/*  584 */         documento.setParceiroDocumento(rs.getString("DOCUMENTO"));
/*  585 */         documento.setParceiroTelefone(rs.getString("TELEFONE"));
/*  586 */         documento.setParceiroEmail(rs.getString("EMAIL"));
/*  587 */         documento.setSeqCondicaoPagamento(rs.getString("SEQ_CONDICAO_PAGAMENTO"));
/*  588 */         documento.setSeqFormaPagamento(rs.getString("SEQ_FORMA_PAGAMENTO"));
/*      */         
/*  590 */         documento.setTipoParceiro(rs.getString("TIPO_PARCEIRO"));
/*  591 */         documento.setTipoDocumento(rs.getString("TIPO_DOCUMENTO"));
/*  592 */         documento.setTemplate(rs.getString("TEMPLATE"));
/*  593 */         documento.setEtapa(rs.getString("DOCUMENTO_ETAPA"));
/*  594 */         documento.setUsuarioCadastro(rs.getString("USUARIO_CADASTRO"));
/*  595 */         documento.setDataPrevisaoConclusao(rs.getDate("data_previsao_conclusao"));
/*      */         
/*  597 */         documento.setLocalServico(rs.getString("local_servico"));
/*  598 */         documento.setVlEntrada(rs.getBigDecimal("vl_entrada"));
/*  599 */         documento.setQtdeParcela(rs.getBigDecimal("qtde_parcela"));
/*  600 */         documento.setDatasParcelado(rs.getString("DATA_PAGAMENTO_PARCELADO"));
/*  601 */         documento.setTributosImpostos(rs.getString("TRIBUTOS_IMPOSTOS"));
/*  602 */         documento.setVlTotal(rs.getBigDecimal("vl_total_documento"));
/*  603 */         documento.setSeqParceiroContato(rs.getString("seq_parceiro_contato"));
/*  604 */         documento.setSeqParceiroContaPagar(rs.getString("seq_parceiro_conta_pagar"));
/*  605 */         documento.setOrigemParceiro(rs.getString("origem_parceiro"));
/*  606 */         documento.setSeqUnidadeNegocio(rs.getString("seq_unidade_negocio"));
/*  607 */         documento.setIdRevisao(Long.valueOf(rs.getLong("id_revisao")));
/*  608 */         documento.setIdComplementar(Long.valueOf(rs.getLong("id_complementar")));
/*  609 */         documento.setComentarioEtapa(rs.getString("comentario_etapa"));
/*      */         
/*  611 */         documento.setRetencaoISSQN(rs.getBigDecimal("retencao_issqn"));
/*  612 */         documento.setSeqProjecaoTributaria(rs.getBigDecimal("seq_projecao_tributaria"));
/*  613 */         documento.setAliquotaISSQNretido(rs.getBigDecimal("aliquota_issqn_retido"));
/*  614 */         documento.setSeqAliquotaFederal(rs.getBigDecimal("seq_aliquota_federal"));
/*  615 */         documento.setVlExecutadoAcumulado(rs.getBigDecimal("vl_acumulado"));
/*  616 */         documento.setVlTributosNaoIncluso(rs.getBigDecimal("vl_tributo_n_incluso"));
/*  617 */         documento.setVlBruto(rs.getBigDecimal("vl_bruto"));
/*  618 */         documento.setVlISSQNRetido(rs.getBigDecimal("vl_issqn_retido"));
/*  619 */         documento.setVlRetencaoFederal(rs.getBigDecimal("vl_retencao_federal"));
/*  620 */         documento.setVlLiquidoAReceber(rs.getBigDecimal("vl_liquido"));
/*  621 */         documento.setTarifaBancaria(rs.getBigDecimal("vl_tarifa"));
/*  622 */         documento.setSequencia(rs.getInt("sequencia"));
/*  623 */         documento.setMoeda(rs.getString("MOEDA"));
/*  624 */         documento.setFilial(rs.getString("FILIAL"));
/*  625 */         documento.setObservacao(rs.getString("observacao"));
/*  626 */         documento.setTaxaCambio(rs.getBigDecimal("taxa_cambio"));
/*      */         
/*  628 */         documento.setNvEmbarcacaoNome(rs.getString("embarcacaoNome"));
/*  629 */         documento.setEquipamentoNome(rs.getString("equipamentoNome"));
/*      */         
/*  631 */         documento.setNatureza(rs.getString("natureza"));
/*  632 */         documento.setCategoria(rs.getString("categoria"));
/*  633 */         documento.setMoedaDestino(rs.getString("MOEDA_DESTINO"));
/*      */         documento.setObjetivo(rs.getString("OBJETIVO"));                   
/*      */         documento.setValidade(rs.getString("VALIDADE"));
/*      */         documento.setDescRemuneracao(rs.getString("DESC_REMUNERACAO"));
/*      */         documento.setCondicoesGerais(rs.getString("COND_GERAIS"));
/*      */         documento.setCondpag(rs.getString("CONDPAG"));
/*      */         documento.setAssunto(rs.getString("ASSUNTO"));         
/*  635 */         listaDocumento.add(documento);
/*      */       }
/*      */       
/*  638 */       ps.execute();
/*  639 */       ps.close();
/*      */       
/*  641 */       return listaDocumento;
/*      */     } catch (SQLException ex) {
/*  643 */       Logger.getLogger(DocumentoDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  644 */       System.out.println(ex.getMessage()); }
/*  645 */     return null;
/*      */   }
/*      */   
/*      */   public List<Documento> listarOS(ClausulaWhere sClausula)
/*      */   {
/*      */     try {
/*  651 */       Conexao conexao = new Conexao();
/*  652 */       Connection conn = Conexao.getConnection();
/*  653 */       String sql = "select \ndocumento.* ,\nparceiro.nome parceiro,\nparceiro.tipo tipo_pessoa,parceiro.logradouro || ', ' || parceiro.numero||' ' || parceiro.complemento||' - '||parceiro.bairro||' - '||parceiro.cidade||'-'||parceiro.uf||' | CEP:'||parceiro.cep endereco,parceiro.documento,parceiro.telefone_1||' | '||parceiro.telefone_2||' | '|| parceiro.telefone_3 telefone,parceiro.email,tipo_parceiro.nome tipo_parceiro,\ntipo_documento.nome tipo_documento,\ntipo_documento.template template,\ndocumento_etapa.nome documento_etapa,\nusuario.usuario usuario_cadastro,\ndoc_dono.codigo DocDonoCodigo,\ndoc_dono.seq_documento DocDonoSeq,\ntp_dono.nome DocDonoTipo,\ntp_dono.seq_tipo_documento DocDonoSeqTipo,\nconta.*,\nunidade_negocio.nome filial, \nnv_embarcacao.nome nvEmbarcacaoNome,\noc.seq_documento seqOrdemCobranca, \nocRegEstat.seq_documento seqOCRegEstat, \nequipamento.nome nomeEquipamento \nfrom \ndocumento\ninner join parceiro on parceiro.seq_parceiro = documento.seq_parceiro\ninner join tipo_parceiro on tipo_parceiro.seq_tipo_parceiro = parceiro.seq_tipo_parceiro\ninner join tipo_documento on tipo_documento.seq_tipo_documento = documento.seq_tipo_documento\ninner join documento_etapa on documento_etapa.seq_documento_etapa = documento.seq_documento_etapa\nleft join usuario on usuario.seq_usuario = documento.seq_usuario_cadastro\nleft join unidade_negocio on usuario.SEQ_UNIDADE_NEGOCIO = unidade_negocio.SEQ_UNIDADE_NEGOCIO \nleft join documento doc_dono on doc_dono.seq_documento = documento.seq_documento_dono\nleft join tipo_documento tp_dono on tp_dono.seq_tipo_documento = doc_dono.seq_tipo_documento\nleft join conta on conta.seq_conta = documento.seq_conta\nleft join documento_item_embarcacao on documento_item_embarcacao.SEQ_documento = documento.SEQ_documento\nleft join nv_EMBARCACAO on nv_EMBARCACAO.SEQ_NV_EMBARCACAO = documento_item_embarcacao.SEQ_NV_EMBARCACAO\nleft join documento oc on oc.seq_documento_dono = doc_dono.seq_documento and oc.seq_tipo_documento = 381\nleft join documento ocRegEstat on ocRegEstat.seq_documento_dono = documento.seq_documento and ocRegEstat.seq_tipo_documento = 381\nleft join documento_item_equipamento on  documento_item_equipamento.seq_documento = documento.seq_documento \nleft join equipamento on equipamento.seq_equipamento = documento_item_equipamento.SEQ_EQUIPAMENTO \n" + sClausula.montarsClausula() + " order by documento.codigo asc";

/*  696 */       System.out.println(sql);
/*      */       
/*  698 */       List<Documento> listaDocumento = new ArrayList();
/*  699 */       List<Documento> listaDocumentoOrdenada = new ArrayList();
/*  700 */       PreparedStatement ps = conn.prepareStatement(sql);
/*  701 */       ResultSet rs = ps.executeQuery();
/*      */       
/*  703 */       while (rs.next()) {
/*  704 */         Documento documento = new Documento();
/*  705 */         documento.setSeqDocumento(rs.getString("SEQ_DOCUMENTO"));
/*  706 */         documento.setDescricao(rs.getString("DESCRICAO"));
/*  707 */         documento.setDataPrevisaoConclusao(rs.getDate("DATA_PREVISAO_CONCLUSAO"));
/*  708 */         documento.setSeqFormaPagamento(rs.getString("SEQ_FORMA_PAGAMENTO"));
/*  709 */         documento.setSeqConta(rs.getString("SEQ_CONTA"));
/*  710 */         documento.setSeqCondicaoPagamento(rs.getString("SEQ_CONDICAO_PAGAMENTO"));
/*  711 */         documento.setSeqTipoMovimentoFinanceiro(rs.getString("SEQ_TIPO_MOVIMENTO_FINANCEIRO"));
/*  712 */         documento.setSeqDocumentoDono(rs.getString("SEQ_DOCUMENTO_DONO"));
/*      */         
/*  714 */         documento.setSeqAssColaborador(rs.getString("SEQ_ASS_COLABORADOR"));
/*  715 */         documento.setnContrato(rs.getString("N_CONTRATO"));
/*  716 */         documento.setReembolsoTransporte(rs.getString("REEMBOLSO_TRANSPORTE"));
/*  717 */         documento.setReembolsoAlimentacao(rs.getString("REEMBOLSO_ALIMENTACAO"));
/*  718 */         documento.setReembolsoHospedagem(rs.getString("REEMBOLSO_HOSPEDAGEM"));
/*  719 */         documento.setReembolsoPlotagens(rs.getString("REEMBOLSO_PLOTAGENS"));
/*  720 */         documento.setReembolsoOutros(rs.getString("REEMBOLSO_OUTROS"));
/*      */         
/*  722 */         documento.setCodigo(rs.getString("CODIGO"));
/*  723 */         documento.setSeqEmpresa(rs.getString("SEQ_EMPRESA"));
/*  724 */         documento.setSeqParceiro(rs.getString("SEQ_PARCEIRO"));
/*  725 */         documento.setSeqTipoDocumento(rs.getString("SEQ_TIPO_DOCUMENTO"));
/*  726 */         documento.setSeqDocumentoEtapa(rs.getString("SEQ_DOCUMENTO_ETAPA"));
/*  727 */         documento.setDataCadastro(rs.getDate("DATA_CADASTRO"));
/*  728 */         documento.setSeqUsuarioCadastro(rs.getString("SEQ_USUARIO_CADASTRO"));
/*  729 */         documento.setData(rs.getDate("DATA"));
/*  730 */         documento.setSeqLugar(rs.getString("SEQ_LUGAR"));
/*      */         
/*  732 */         documento.setDocDonoSeqTipo(rs.getString("DocDonoSeqTipo"));
/*  733 */         documento.setDocDonoTipo(rs.getString("DocDonoTipo"));
/*  734 */         documento.setDocDonoCodigo(rs.getString("DocDonoCodigo"));
/*      */         
/*  736 */         documento.setParceiro(rs.getString("PARCEIRO"));
/*  737 */         documento.setParceiroTipoPessoa(rs.getString("TIPO_PESSOA"));
/*  738 */         documento.setParceiroEndereco(rs.getString("ENDERECO"));
/*  739 */         documento.setParceiroDocumento(rs.getString("DOCUMENTO"));
/*  740 */         documento.setParceiroTelefone(rs.getString("TELEFONE"));
/*  741 */         documento.setParceiroEmail(rs.getString("EMAIL"));
/*  742 */         documento.setSeqCondicaoPagamento(rs.getString("SEQ_CONDICAO_PAGAMENTO"));
/*  743 */         documento.setSeqFormaPagamento(rs.getString("SEQ_FORMA_PAGAMENTO"));
/*      */         
/*  745 */         documento.setTipoParceiro(rs.getString("TIPO_PARCEIRO"));
/*  746 */         documento.setTipoDocumento(rs.getString("TIPO_DOCUMENTO"));
/*  747 */         documento.setTemplate(rs.getString("TEMPLATE"));
/*  748 */         documento.setEtapa(rs.getString("DOCUMENTO_ETAPA"));
/*  749 */         documento.setUsuarioCadastro(rs.getString("USUARIO_CADASTRO"));
/*  750 */         documento.setDataPrevisaoConclusao(rs.getDate("data_previsao_conclusao"));
/*      */         
/*  752 */         documento.setLocalServico(rs.getString("local_servico"));
/*  753 */         documento.setVlEntrada(rs.getBigDecimal("vl_entrada"));
/*  754 */         documento.setQtdeParcela(rs.getBigDecimal("qtde_parcela"));
/*  755 */         documento.setDatasParcelado(rs.getString("DATA_PAGAMENTO_PARCELADO"));
/*  756 */         documento.setTributosImpostos(rs.getString("TRIBUTOS_IMPOSTOS"));
/*  757 */         documento.setVlTotal(rs.getBigDecimal("vl_total_documento"));
/*  758 */         documento.setSeqParceiroContato(rs.getString("seq_parceiro_contato"));
/*  759 */         documento.setSeqParceiroContaPagar(rs.getString("seq_parceiro_conta_pagar"));
/*  760 */         documento.setOrigemParceiro(rs.getString("origem_parceiro"));
/*  761 */         documento.setSeqUnidadeNegocio(rs.getString("seq_unidade_negocio"));
/*  762 */         documento.setIdRevisao(Long.valueOf(rs.getLong("id_revisao")));
/*  763 */         documento.setIdComplementar(Long.valueOf(rs.getLong("id_complementar")));
/*  764 */         documento.setComentarioEtapa(rs.getString("comentario_etapa"));
/*      */         
/*  766 */         documento.setRetencaoISSQN(rs.getBigDecimal("retencao_issqn"));
/*  767 */         documento.setSeqProjecaoTributaria(rs.getBigDecimal("seq_projecao_tributaria"));
/*  768 */         documento.setAliquotaISSQNretido(rs.getBigDecimal("aliquota_issqn_retido"));
/*  769 */         documento.setSeqAliquotaFederal(rs.getBigDecimal("seq_aliquota_federal"));
/*  770 */         documento.setVlExecutadoAcumulado(rs.getBigDecimal("vl_acumulado"));
/*  771 */         documento.setVlTributosNaoIncluso(rs.getBigDecimal("vl_tributo_n_incluso"));
/*  772 */         documento.setVlBruto(rs.getBigDecimal("vl_bruto"));
/*  773 */         documento.setVlISSQNRetido(rs.getBigDecimal("vl_issqn_retido"));
/*  774 */         documento.setVlRetencaoFederal(rs.getBigDecimal("vl_retencao_federal"));
/*  775 */         documento.setVlLiquidoAReceber(rs.getBigDecimal("vl_liquido"));
/*  776 */         documento.setTarifaBancaria(rs.getBigDecimal("vl_tarifa"));
/*  777 */         documento.setSequencia(rs.getInt("sequencia"));
/*  778 */         documento.setMoeda(rs.getString("MOEDA"));
/*  779 */         documento.setFilial(rs.getString("FILIAL"));
/*  780 */         documento.setNvEmbarcacaoNome(rs.getString("nvEmbarcacaoNome"));
/*  781 */         documento.setEquipamentoNome(rs.getString("nomeEquipamento"));
/*  782 */         documento.setObservacao(rs.getString("observacao"));
/*  783 */         documento.setTaxaCambio(rs.getBigDecimal("taxa_cambio"));
/*  784 */         documento.setMoedaDestino(rs.getString("MOEDA_DESTINO"));
/*      */         
/*  786 */         if (documento.getDocDonoSeqTipo() != null) {
/*  787 */           if ((documento.getDocDonoSeqTipo().equals("282")) && (rs.getString("seqOCRegEstat") != null))
/*      */           {
/*  789 */             documento.setOrdemCobranca(rs.getString("seqOCRegEstat"));
/*      */           } else {
/*  791 */             documento.setOrdemCobranca(rs.getString("seqOrdemCobranca"));
/*      */           }
/*      */         } else {
/*  794 */           documento.setOrdemCobranca(null);
/*      */         }
/*      */         
/*  797 */         if (documento.getOrdemCobranca() != null) {
/*  798 */           documento.setOrdemCobranca("Sim");
/*      */         } else {
/*  800 */           documento.setOrdemCobranca("Não");
/*      */         }
/*      */         
/*  803 */         listaDocumento.add(documento);
/*      */       }
/*      */       
/*  806 */       ps.execute();
/*  807 */       ps.close();
/*      */       
/*  809 */       return listaDocumento;
/*      */     } catch (SQLException ex) {
/*  811 */       Logger.getLogger(DocumentoDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  812 */       System.out.println(ex.getMessage()); }
/*  813 */     return null;
/*      */   }
/*      */   
/*      */   public List<Documento> listarOC(ClausulaWhere sClausula)
/*      */   {
/*      */     try {
/*  819 */       Conexao conexao = new Conexao();
/*  820 */       Connection conn = Conexao.getConnection();
/*  821 */       String sql = "select \ndocumento.* ,\nparceiro.nome parceiro,\nparceiro.tipo tipo_pessoa,parceiro.logradouro || ', ' || parceiro.numero||' ' || parceiro.complemento||' - '||parceiro.bairro||' - '||parceiro.cidade||'-'||parceiro.uf||' | CEP:'||parceiro.cep endereco,parceiro.documento,parceiro.telefone_1||' | '||parceiro.telefone_2||' | '|| parceiro.telefone_3 telefone,parceiro.email,tipo_parceiro.nome tipo_parceiro,\ntipo_documento.nome tipo_documento,\ntipo_documento.template template,\ndocumento_etapa.nome documento_etapa,\nusuario.usuario usuario_cadastro,\ndoc_dono.codigo DocDonoCodigo,\ntp_dono.nome DocDonoTipo,\ntp_dono.seq_tipo_documento DocDonoSeqTipo,\nconta.*,\nunidade_negocio.nome filial, \nboletimMedicao.seq_documento seqBoletimMedicao\nfrom \ndocumento\ninner join parceiro on parceiro.seq_parceiro = documento.seq_parceiro\ninner join tipo_parceiro on tipo_parceiro.seq_tipo_parceiro = parceiro.seq_tipo_parceiro\ninner join tipo_documento on tipo_documento.seq_tipo_documento = documento.seq_tipo_documento\ninner join documento_etapa on documento_etapa.seq_documento_etapa = documento.seq_documento_etapa\nleft join usuario on usuario.seq_usuario = documento.seq_usuario_cadastro\nleft join unidade_negocio on usuario.SEQ_UNIDADE_NEGOCIO = unidade_negocio.SEQ_UNIDADE_NEGOCIO \nleft join documento doc_dono on doc_dono.seq_documento = documento.seq_documento_dono\nleft join tipo_documento tp_dono on tp_dono.seq_tipo_documento = doc_dono.seq_tipo_documento\nleft join conta on conta.seq_conta = documento.seq_conta\nleft join documento boletimMedicao on boletimMedicao.seq_documento_dono = documento.seq_documento and boletimMedicao.seq_tipo_documento = 401\n" + sClausula.montarsClausula() + " order by documento.codigo asc";

/*  856 */       List<Documento> listaDocumento = new ArrayList();
/*  857 */       PreparedStatement ps = conn.prepareStatement(sql);
/*  858 */       ResultSet rs = ps.executeQuery();
/*      */       
/*  860 */       while (rs.next()) {
/*  861 */         Documento documento = new Documento();
/*  862 */         documento.setSeqDocumento(rs.getString("SEQ_DOCUMENTO"));
/*  863 */         documento.setDescricao(rs.getString("DESCRICAO"));
/*  864 */         documento.setDataPrevisaoConclusao(rs.getDate("DATA_PREVISAO_CONCLUSAO"));
/*  865 */         documento.setSeqFormaPagamento(rs.getString("SEQ_FORMA_PAGAMENTO"));
/*  866 */         documento.setSeqConta(rs.getString("SEQ_CONTA"));
/*  867 */         documento.setSeqCondicaoPagamento(rs.getString("SEQ_CONDICAO_PAGAMENTO"));
/*  868 */         documento.setSeqTipoMovimentoFinanceiro(rs.getString("SEQ_TIPO_MOVIMENTO_FINANCEIRO"));
/*  869 */         documento.setSeqDocumentoDono(rs.getString("SEQ_DOCUMENTO_DONO"));
/*      */         
/*  871 */         documento.setSeqAssColaborador(rs.getString("SEQ_ASS_COLABORADOR"));
/*  872 */         documento.setnContrato(rs.getString("N_CONTRATO"));
/*  873 */         documento.setReembolsoTransporte(rs.getString("REEMBOLSO_TRANSPORTE"));
/*  874 */         documento.setReembolsoAlimentacao(rs.getString("REEMBOLSO_ALIMENTACAO"));
/*  875 */         documento.setReembolsoHospedagem(rs.getString("REEMBOLSO_HOSPEDAGEM"));
/*  876 */         documento.setReembolsoPlotagens(rs.getString("REEMBOLSO_PLOTAGENS"));
/*  877 */         documento.setReembolsoOutros(rs.getString("REEMBOLSO_OUTROS"));
/*      */         
/*  879 */         documento.setCodigo(rs.getString("CODIGO"));
/*  880 */         documento.setSeqEmpresa(rs.getString("SEQ_EMPRESA"));
/*  881 */         documento.setSeqParceiro(rs.getString("SEQ_PARCEIRO"));
/*  882 */         documento.setSeqTipoDocumento(rs.getString("SEQ_TIPO_DOCUMENTO"));
/*  883 */         documento.setSeqDocumentoEtapa(rs.getString("SEQ_DOCUMENTO_ETAPA"));
/*  884 */         documento.setDataCadastro(rs.getDate("DATA_CADASTRO"));
/*  885 */         documento.setSeqUsuarioCadastro(rs.getString("SEQ_USUARIO_CADASTRO"));
/*  886 */         documento.setData(rs.getDate("DATA"));
/*  887 */         documento.setSeqLugar(rs.getString("SEQ_LUGAR"));
/*      */         
/*  889 */         documento.setDocDonoSeqTipo(rs.getString("DocDonoSeqTipo"));
/*  890 */         documento.setDocDonoTipo(rs.getString("DocDonoTipo"));
/*  891 */         documento.setDocDonoCodigo(rs.getString("DocDonoCodigo"));
/*      */         
/*  893 */         documento.setParceiro(rs.getString("PARCEIRO"));
/*  894 */         documento.setParceiroTipoPessoa(rs.getString("TIPO_PESSOA"));
/*  895 */         documento.setParceiroEndereco(rs.getString("ENDERECO"));
/*  896 */         documento.setParceiroDocumento(rs.getString("DOCUMENTO"));
/*  897 */         documento.setParceiroTelefone(rs.getString("TELEFONE"));
/*  898 */         documento.setParceiroEmail(rs.getString("EMAIL"));
/*  899 */         documento.setSeqCondicaoPagamento(rs.getString("SEQ_CONDICAO_PAGAMENTO"));
/*  900 */         documento.setSeqFormaPagamento(rs.getString("SEQ_FORMA_PAGAMENTO"));
/*      */         
/*  902 */         documento.setTipoParceiro(rs.getString("TIPO_PARCEIRO"));
/*  903 */         documento.setTipoDocumento(rs.getString("TIPO_DOCUMENTO"));
/*  904 */         documento.setTemplate(rs.getString("TEMPLATE"));
/*  905 */         documento.setEtapa(rs.getString("DOCUMENTO_ETAPA"));
/*  906 */         documento.setUsuarioCadastro(rs.getString("USUARIO_CADASTRO"));
/*  907 */         documento.setDataPrevisaoConclusao(rs.getDate("data_previsao_conclusao"));
/*      */         
/*  909 */         documento.setLocalServico(rs.getString("local_servico"));
/*  910 */         documento.setVlEntrada(rs.getBigDecimal("vl_entrada"));
/*  911 */         documento.setQtdeParcela(rs.getBigDecimal("qtde_parcela"));
/*  912 */         documento.setDatasParcelado(rs.getString("DATA_PAGAMENTO_PARCELADO"));
/*  913 */         documento.setTributosImpostos(rs.getString("TRIBUTOS_IMPOSTOS"));
/*  914 */         documento.setVlTotal(rs.getBigDecimal("vl_total_documento"));
/*  915 */         documento.setSeqParceiroContato(rs.getString("seq_parceiro_contato"));
/*  916 */         documento.setSeqParceiroContaPagar(rs.getString("seq_parceiro_conta_pagar"));
/*  917 */         documento.setOrigemParceiro(rs.getString("origem_parceiro"));
/*  918 */         documento.setSeqUnidadeNegocio(rs.getString("seq_unidade_negocio"));
/*  919 */         documento.setIdRevisao(Long.valueOf(rs.getLong("id_revisao")));
/*  920 */         documento.setIdComplementar(Long.valueOf(rs.getLong("id_complementar")));
/*  921 */         documento.setComentarioEtapa(rs.getString("comentario_etapa"));
/*      */         
/*  923 */         documento.setRetencaoISSQN(rs.getBigDecimal("retencao_issqn"));
/*  924 */         documento.setSeqProjecaoTributaria(rs.getBigDecimal("seq_projecao_tributaria"));
/*  925 */         documento.setAliquotaISSQNretido(rs.getBigDecimal("aliquota_issqn_retido"));
/*  926 */         documento.setSeqAliquotaFederal(rs.getBigDecimal("seq_aliquota_federal"));
/*  927 */         documento.setVlExecutadoAcumulado(rs.getBigDecimal("vl_acumulado"));
/*  928 */         documento.setVlTributosNaoIncluso(rs.getBigDecimal("vl_tributo_n_incluso"));
/*  929 */         documento.setVlBruto(rs.getBigDecimal("vl_bruto"));
/*  930 */         documento.setVlISSQNRetido(rs.getBigDecimal("vl_issqn_retido"));
/*  931 */         documento.setVlRetencaoFederal(rs.getBigDecimal("vl_retencao_federal"));
/*  932 */         documento.setVlLiquidoAReceber(rs.getBigDecimal("vl_liquido"));
/*  933 */         documento.setTarifaBancaria(rs.getBigDecimal("vl_tarifa"));
/*  934 */         documento.setSequencia(rs.getInt("sequencia"));
/*  935 */         documento.setMoeda(rs.getString("MOEDA"));
/*  936 */         documento.setFilial(rs.getString("FILIAL"));
/*  937 */         documento.setBoletimMedicao(rs.getString("seqBoletimMedicao"));
/*  938 */         documento.setObservacao(rs.getString("observacao"));
/*  939 */         documento.setTaxaCambio(rs.getBigDecimal("taxa_cambio"));
/*  940 */         documento.setMoedaDestino(rs.getString("MOEDA_DESTINO"));
/*  932 */         documento.setVltotalcambio(rs.getBigDecimal("VL_TOTALCAMBIO"));
/*      */         documento.setObjetivo(rs.getString("OBJETIVO"));                   
/*      */         documento.setValidade(rs.getString("VALIDADE"));
/*      */         documento.setDescRemuneracao(rs.getString("DESC_REMUNERACAO"));
/*      */         documento.setCondicoesGerais(rs.getString("COND_GERAIS"));
/*      */         documento.setCondpag(rs.getString("CONDPAG"));
/*      */         
/*  942 */         if (documento.getBoletimMedicao() == null) {
/*  943 */           documento.setBoletimMedicao("Não");
/*      */         } else {
/*  945 */           documento.setBoletimMedicao("Sim");
/*      */         }
/*      */         
/*  948 */         listaDocumento.add(documento);
/*      */       }
/*      */       
/*  951 */       ps.execute();
/*  952 */       ps.close();
/*      */       
/*  954 */       return listaDocumento;
/*      */     } catch (SQLException ex) {
/*  956 */       Logger.getLogger(DocumentoDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  957 */       System.out.println(ex.getMessage()); }
/*  958 */     return null;
/*      */   }
/*      */   
/*      */   public boolean atualizar(String seqDocDono, String seqDocDonoNovo)
/*      */   {
/*  963 */     Conexao conexao = new Conexao();
/*  964 */     Connection conn = Conexao.getConnection();
/*  965 */     String sql = "update documento set seq_documento_dono = ? where seq_documento_dono = ? and (seq_tipo_documento = ?  or seq_tipo_documento = ?)";
/*      */     
/*      */ 
/*      */     try
/*      */     {
/*  970 */       PreparedStatement ps = conn.prepareStatement(sql);
/*      */       
/*      */ 
/*  973 */       ps.setString(1, seqDocDonoNovo);
/*  974 */       ps.setString(2, seqDocDono);
/*  975 */       ps.setString(3, "301");
/*  976 */       ps.setString(4, "381");
/*      */       
/*  978 */       ps.execute();
/*  979 */       ps.close();
/*      */     }
/*      */     catch (SQLException ex) {
/*  982 */       Logger.getLogger(DocumentoDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  983 */       return false;
/*      */     }
/*  985 */     return true;
/*      */   }
/*      */   
/*      */   public boolean deletar(Documento documento) {
/*      */     try {
/*  990 */       Conexao conexao = new Conexao();
/*  991 */       Connection conn = Conexao.getConnection();
/*  992 */       String sql = "DELETE FROM DOCUMENTO WHERE SEQ_DOCUMENTO =  ? ";
/*      */       
/*  994 */       PreparedStatement ps = conn.prepareStatement(sql);
/*      */       
/*  996 */       ps.setString(1, documento.getSeqDocumento());
/*      */       
/*  998 */       ps.execute();
/*  999 */       ps.close();
/*      */       
/* 1001 */       return true;
/*      */     } catch (SQLException ex) {
/* 1003 */       System.out.println(ex.getMessage()); }
/* 1004 */     return false;
/*      */   }
/*      */ }

