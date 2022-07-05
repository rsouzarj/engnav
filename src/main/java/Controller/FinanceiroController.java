package Controller;

import Aliquota.Aliquota;
import Aliquota.AliquotaService;
import CentroCusto.CentroCusto;
import CentroCusto.CentroCustoService;
import ClausulaSQL.ClausulaWhere;
import ClausulaSQL.GeneroCondicaoWhere;
import ClausulaSQL.OperacaoCondicaoWhere;
import ClausulaSQL.TipoCondicaoWhere;
import Colaborador.Colaborador;
import Colaborador.ColaboradorService;
import CondicaoPagamento.CondicaoPagamento;
import CondicaoPagamento.CondicaoPagamentoService;
import Conta.Conta;
import Conta.ContaService;
import Documento.Documento;
import Documento.DocumentoService;
import DocumentoFiscal.DocumentoFiscal;
import DocumentoFiscal.DocumentoFiscalService;
import Empresa.Empresa;
import Equipamento.Equipamento;
import Equipamento.EquipamentoService;
import Financeiro.Financeiro;
import Financeiro.FinanceiroService;
import FinanceiroCategoria.FinanceiroCategoria;
import FinanceiroCategoria.FinanceiroCategoriaService;
import FinanceiroItem.FinanceiroItem;
import FinanceiroItem.FinanceiroItemService;
import FinanceiroItemPc.FinanceiroItemPc;
import FinanceiroItemPc.FinanceiroItemPcService;
import FinanceiroOcorrencia.FinanceiroOcorrencia;
import FinanceiroOcorrencia.FinanceiroOcorrenciaService;
import FormaPagamento.FormaPagamento;
import FormaPagamento.FormaPagamentoService;
import Material.Material;
import Material.MaterialService;
import MaterialPreco.MaterialPreco;
import MaterialPreco.MaterialPrecoService;
import NvEmbarcacaoParceiro.NvEmbarcacaoParceiro;
import NvEmbarcacaoParceiro.NvEmbarcacaoParceiroService;
import Parceiro.Parceiro;
import Parceiro.ParceiroService;
import ParceiroContato.ParceiroContato;
import ParceiroContato.ParceiroContatoService;
import TipoDocumento.TipoDocumento;
import TipoDocumento.TipoDocumentoService;
import TipoMovimentoFinanceiro.TipoMovimentoFinanceiro;
import TipoMovimentoFinanceiro.TipoMovimentoFinanceiroService;
import TipoParceiro.TipoParceiro;
import TipoParceiro.TipoParceiroService;
import TipoUnidade.TipoUnidade;
import TipoUnidade.TipoUnidadeService;
import UnidadeNegocio.UnidadeNegocio;
import UnidadeNegocio.UnidadeNegocioService;
import Upload.Upload;
import Upload.UploadService;
import Usuario.Usuario;
import Util.Conexao;
import Util.CurrencyWriter;
import Util.Situacao;
import Util.Util;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "financeiroController")
@ViewScoped
public class FinanceiroController {
	@ManagedProperty("#{loginController}")
	protected LoginController loginController;
	FinanceiroService financeiroService = new FinanceiroService();
	Financeiro financeiro = new Financeiro();
	Financeiro financeiroQuitacao = new Financeiro();
	List<Financeiro> listaFinanceiro = new ArrayList();
	List<Financeiro> listaFinanceiroOrdemPagamento = new ArrayList();

	TipoMovimentoFinanceiro tipoMovimentoFinanceiro = new TipoMovimentoFinanceiro();
	List<TipoMovimentoFinanceiro> listaTipoLancamento = new ArrayList();
	TipoMovimentoFinanceiroService tipoMovimentoFinanceiroService = new TipoMovimentoFinanceiroService();

	FinanceiroItemPc financeiroItemPc = new FinanceiroItemPc();
	List<FinanceiroItemPc> listaFinanceiroItemPc = new ArrayList();
	List<FinanceiroItemPc> listaFinanceiroItemPcDeletado = new ArrayList();
	FinanceiroItemPcService financeiroItemPcService = new FinanceiroItemPcService();

	List<TipoDocumento> listaTipoDocumento = new ArrayList();
	TipoDocumentoService tipoDocumentoService = new TipoDocumentoService();

	List<Material> listaMaterial = new ArrayList();
	MaterialService materialService = new MaterialService();

	Documento documento = new Documento();
	List<Documento> listaTodosDocumentos = new ArrayList();
	List<Documento> listaDocumento = new ArrayList();
	DocumentoService documentoService = new DocumentoService();

	Parceiro parceiro = new Parceiro();
	List<Parceiro> listaParceiro = new ArrayList();
	ParceiroService parceiroService = new ParceiroService();

	TipoParceiroService tipoParceiroService = new TipoParceiroService();
	List<TipoParceiro> listaTipoParceiro = new ArrayList();

	Colaborador colaborador = new Colaborador();
	List<Colaborador> listaColaborador = new ArrayList();
	ColaboradorService colaboradorService = new ColaboradorService();

	UnidadeNegocio unidadeNegocio = new UnidadeNegocio();
	UnidadeNegocioService unidadeNegocioService = new UnidadeNegocioService();
	List<UnidadeNegocio> listaUnidadeNegocio = new ArrayList();

	CentroCustoService centroCustoService = new CentroCustoService();
	List<CentroCusto> listaCentroCusto = new ArrayList();

	FormaPagamentoService formaPagamentoService = new FormaPagamentoService();
	List<FormaPagamento> listaFormaPagamento = new ArrayList();

	DocumentoFiscalService documentoFiscalService = new DocumentoFiscalService();
	List<DocumentoFiscal> listaDocumentoFiscal = new ArrayList();

	FinanceiroItemService financeiroItemService = new FinanceiroItemService();
	List<FinanceiroItem> listaFinanceiroItem = new ArrayList();
	FinanceiroItem financeiroItem = new FinanceiroItem();

	MaterialPrecoService materialPrecoService = new MaterialPrecoService();
	List<MaterialPreco> listaMaterialReferencia = new ArrayList();

	List<MaterialPreco> listaMaterialPreco = new ArrayList();
	MaterialPreco materialPreco = new MaterialPreco();

	List<ParceiroContato> listaParceiroContato = new ArrayList();
	ParceiroContatoService parceiroContatoService = new ParceiroContatoService();

	List<FinanceiroCategoria> listaFinanceiroCategoria = new ArrayList();
	FinanceiroCategoriaService financeiroCategoriaService = new FinanceiroCategoriaService();

	List<CondicaoPagamento> listaCondicaoPagamento = new ArrayList();
	CondicaoPagamentoService condicaoPagamentoService = new CondicaoPagamentoService();

	List<Equipamento> listaEquipamento = new ArrayList();
	EquipamentoService equipamentoService = new EquipamentoService();

	List<NvEmbarcacaoParceiro> listaNvEmbarcacaoParceiro = new ArrayList();
	NvEmbarcacaoParceiroService nvEmbarcacaoParceiroService = new NvEmbarcacaoParceiroService();

	List<Conta> listaConta = new ArrayList();
	ContaService contaService = new ContaService();

	AliquotaService aliquotaService = new AliquotaService();
	List<Aliquota> listaISSQN = new ArrayList();
	List<Aliquota> listaIRRF = new ArrayList();
	List<Aliquota> listaCSLL = new ArrayList();
	List<Aliquota> listaPIS = new ArrayList();
	List<Aliquota> listaCOFINS = new ArrayList();

	TipoUnidadeService tipoUnidadeService = new TipoUnidadeService();
	List<TipoUnidade> listaTipoUnidade = new ArrayList();

	FinanceiroOcorrenciaService financeiroOcorrenciaService = new FinanceiroOcorrenciaService();
	List<FinanceiroOcorrencia> listaFinanceiroOcorrencia = new ArrayList();
	FinanceiroOcorrencia financeiroOcorrencia = new FinanceiroOcorrencia();
        
        
        Upload upload = new Upload();
        UploadService uploadService = new UploadService();
        UploadController uploadController = new UploadController();
        List<Upload> listaUpload = new ArrayList();
        UploadedFile file;
        StreamedContent fileDownload;
	StreamedContent arquivoVisualizar;
        
        
        

	String pGrupoServico = "";
	String pesquisa = "";
	String tipo = "";
	String seqProposta = "";
	String codigoOrdemServico = "";
	String seqCentroCustoSelecionado = "";
	String seqDocumentoFiscalSelecionado = "";
	String seqParceiroSelecionado = "";
	String seqTipoParceiroSelecionado = "";
	String seqContaSelecionado = "";
	String operacaoSelecionada = "";
	String statusSelecionado = "";
	String seqCategoriaSelecionado = "";
	String seqNaturezaSelecionado = "";
	String seqCodigoFiscalSelecionado = "";
	String seqColaboradorSelecionado = "";
	String seqUnidadeNegocioSelecionado = "";

	boolean id;
	Integer tela = Integer.valueOf(0);
	Integer idEditarQuitacao = Integer.valueOf(0);
	Documento docProposta = new Documento();
	String msgCancelamentoAnterior = "";
	String tipoDocumentoSelecionado = "";
	String idFinanceiro = "";

	BigDecimal totalValorBruto;
	BigDecimal totalValorLiquido;
	BigDecimal totalCredito;
	BigDecimal totalDebito;
	BigDecimal saldoFinal;
	BigDecimal saldoInicial;
	BigDecimal saldoRestante = new BigDecimal(0);

	boolean vincularDocumento;

	boolean vincularParceiro;

	boolean porDataLancamento;

	String dataLancamentoInicial;

	String dataLancamentoFinal;

	boolean porDataEmissao;

	String dataEmissaoInicial;

	String dataEmissaoFinal;

	boolean porDataVencimento;

	String dataVencimentoInicial;
	String dataVencimentoFinal;
	boolean porDataPagamento;
	String dataPagamentoInicial;
	String dataPagamentoFinal;
	boolean porDataPeriodoInicial;
	String dataPeriodoInicialInicial;
	String dataPeriodoInicialFinal;
	boolean porDataPeriodoFinal;
	String dataPeriodoFinalInicial;
	String dataPeriodoFinalFinal;
	BigDecimal percentual;
	BigDecimal valorISSQN;
	BigDecimal valorCSLL;
	BigDecimal valorIRRF;
	BigDecimal valorPIS;
	BigDecimal valorCOFINS;
	BigDecimal valorOrdemPagamento;
	Integer idAccordionPanel = Integer.valueOf(-1);

	int origem;

	public void iniciar() {
		if ((this.loginController.usuario.getAcFinFFaturamento() == null)
				|| (this.loginController.usuario.getAcFinFFaturamento().equals("-1"))) {
			this.loginController.mudarPagina("/organizacional/acessonegado.jsf");
			return;
		}

		this.listaISSQN = this.aliquotaService.listarPorTipo(this.loginController.usuario.getSeqEmpresa(),
				"ISSQN RETIDO", Situacao.ATIVO);
		this.listaIRRF = this.aliquotaService.listarPorTipo(this.loginController.usuario.getSeqEmpresa(), "IRRF",
				Situacao.ATIVO);
		this.listaCSLL = this.aliquotaService.listarPorTipo(this.loginController.usuario.getSeqEmpresa(), "CSLL",
				Situacao.ATIVO);
		this.listaPIS = this.aliquotaService.listarPorTipo(this.loginController.usuario.getSeqEmpresa(), "PIS",
				Situacao.ATIVO);
		this.listaCOFINS = this.aliquotaService.listarPorTipo(this.loginController.usuario.getSeqEmpresa(), "CONFINS",
				Situacao.ATIVO);
		this.listaUnidadeNegocio = this.unidadeNegocioService.listar(this.loginController.empresa.getSeqEmpresa(), "",
				Situacao.ATIVO);

                this.listaDocumentoFiscal = this.documentoFiscalService.listar(this.loginController.usuario.getSeqEmpresa(), "",
				Situacao.ATIVO);
		this.listaParceiro = this.parceiroService.listarParceiro(this.loginController.usuario.getSeqUsuario(), "");
		
                this.listaTipoLancamento = this.tipoMovimentoFinanceiroService.listarPorOperacao(this.loginController.empresa.getSeqEmpresa(), Situacao.ATIVO, "Crédito");

		this.listaFinanceiroCategoria = this.financeiroCategoriaService
				.listar(this.loginController.empresa.getSeqEmpresa(), "", Situacao.ATIVO);
		this.listaCentroCusto = this.centroCustoService.listar(this.loginController.usuario.getSeqEmpresa(), "",
				Situacao.ATIVO);
		this.listaConta = this.contaService.listar(this.loginController.usuario.getSeqEmpresa(), "", Situacao.ATIVO);

		this.listaTodosDocumentos = this.documentoService.listarBMFaturamento("401");
		for (Documento listaTodosDocumento : this.listaTodosDocumentos) {
			if ((listaTodosDocumento.getIdRevisao().longValue() == 0L)
					&& (listaTodosDocumento.getIdComplementar().longValue() > 0L)) {
				listaTodosDocumento
						.setEstadoBM(" - Complementar nÂº".concat(listaTodosDocumento.getIdComplementar().toString()));
				this.listaDocumento.add(listaTodosDocumento);
			}
			if ((listaTodosDocumento.getIdRevisao().longValue() > 0L)
					&& (listaTodosDocumento.getIdComplementar().longValue() == 0L)) {
				listaTodosDocumento.setEstadoBM(" - Revisado nÂº".concat(listaTodosDocumento.getIdRevisao().toString()));
				this.listaDocumento.add(listaTodosDocumento);
			}
			if ((listaTodosDocumento.getSequencia() == 0) && (listaTodosDocumento.getIdComplementar().longValue() == 0L)
					&& (listaTodosDocumento.getIdRevisao().longValue() == 0L)) {
				this.listaDocumento.add(listaTodosDocumento);
			}
		}

		listarDocumento();
	}

	public void editar() {
		this.id = (!this.id);
	}

	public void salvar(int pTela, int origem) {
		this.financeiro.setSeqEmpresa(this.loginController.usuario.getSeqEmpresa());

		if (this.financeiro.getOcorrencias() == null) {
			this.financeiro.setOcorrencias(Integer.valueOf(0));
		}
		if (this.financeiro.getIntervaloNumero() == null) {
			this.financeiro.setIntervaloNumero(Integer.valueOf(0));
		}
		if (this.financeiro.getParcelaInicio() == null) {
			this.financeiro.setParcelaInicio(Integer.valueOf(0));
		}
		if (this.financeiro.getParcelaFim() == null)
			this.financeiro.setParcelaFim(Integer.valueOf(0));
		String seqFinanceiro;
		switch (origem) {
		case 0:
			if (this.financeiro.getSeqFinanceiro() == null) {
				this.financeiro.setOrigemLCM("0");
				this.financeiro = this.financeiroService.salvar(this.financeiro);
				String numeroFaturamento = this.financeiro.getSeqFinanceiro();
				BigDecimal valorLiquidoFaturamento = this.financeiro.getValorPagamento();

				this.financeiro.setSeqFinanceiro(null);
				this.financeiro.setOrigemLCM("1");
				this.financeiro.setSeqTipoMovimento("61");
				this.financeiro.setSeqTipoDocumento("401");

				this.financeiro.setValor(valorLiquidoFaturamento);
				this.financeiro.setOutrasInformacoes(
						"LanÃ§amento gerado Ã  partir do processo de Faturamento, N.Âº: " + numeroFaturamento + ".");
				this.financeiro = this.financeiroService.salvar(this.financeiro);

				this.documento.setSeqDocumentoEtapa("542");
				if (this.documento != null) {
					this.documento = this.documentoService.salvar(this.documento);
				}
			} else {
				this.financeiro = this.financeiroService.salvar(this.financeiro);
			}

			break;
		case 1:
			if (this.financeiro.getSeqFinanceiro() == null) {
				this.financeiro.setEtapa("A VENCER");
			}
			this.financeiro.setOrigemLCM("1");
			this.financeiro.setOperacao("CRÉDITO");
			this.financeiro = this.financeiroService.salvar(this.financeiro);

			if (this.financeiro.getDataPagamento() != null) {
				seqFinanceiro = this.financeiro.getSeqFinanceiro();

				if (this.financeiro.getTipoQuitacao().equals("PARCIAL")) {
					Financeiro pFinanceiro = this.financeiro;
					pFinanceiro.setSeqFinanceiro(null);
					pFinanceiro.setOutrasInformacoes(this.financeiro.getOutrasInformacoes()
							.concat("Gerado para complementar o valor de R$" + this.financeiro.getValorRecebido()
									+ " do Contas a Receber nÂº " + seqFinanceiro + "."));
					pFinanceiro.setValor(this.saldoRestante);
					pFinanceiro.setValorRecebido(null);
					pFinanceiro.setValorJuros(null);
					pFinanceiro.setValorMulta(null);
					pFinanceiro.setValorDesconto(null);
					pFinanceiro.setOutrosAcrescimos(null);
					pFinanceiro.setDataPagamento(null);
					pFinanceiro.setResponsavelQuitacao(null);
					pFinanceiro.setTipoQuitacao(null);
					pFinanceiro.setValorParcial(null);
					pFinanceiro.setDataEmissao(new Date());

					this.financeiroService.salvar(pFinanceiro);
				} else {
					this.financeiro = this.financeiroService.salvar(this.financeiro);
				}
			} else {
				this.financeiro = this.financeiroService.salvar(this.financeiro);
			}

			this.tela = Integer.valueOf(pTela);
			break;
		case 2:
			if (this.financeiro.getSeqFinanceiro() == null) {
				this.financeiro.setEtapa("A VENCER");
			}
			this.financeiro.setOrigemLCM("2");
			this.financeiro.setOperacao("DÉBITO");

			this.financeiro = this.financeiroService.salvar(this.financeiro);

			if (this.financeiro.isChkRepeticao()) {
				this.financeiro.setChkRepeticao(false);
				this.financeiro = this.financeiroService.salvar(this.financeiro);
				repeticao(this.financeiro.getIndefinidamente(), this.financeiro.getIntervaloTempo(),
						this.financeiro.getIntervaloNumero().intValue());
			}

			if (this.financeiro.getDataPagamento() != null) {
				seqFinanceiro = this.financeiro.getSeqFinanceiro();

				if (this.financeiro.getValor().compareTo(this.financeiro.getValorPagamento()) == 1) {
					Financeiro pFinanceiro = this.financeiro;
					pFinanceiro.setSeqFinanceiro(null);
					pFinanceiro.setValor(this.financeiro.getValor().subtract(this.financeiro.getValorPagamento()));
					pFinanceiro.setValorPagamento(null);
					pFinanceiro.setValorJuros(null);
					pFinanceiro.setValorMulta(null);
					pFinanceiro.setValorDesconto(null);
					pFinanceiro.setOutrosAcrescimos(null);
					pFinanceiro.setDataPagamento(null);
					pFinanceiro.setResponsavelQuitacao(null);
					pFinanceiro.setDataEmissao(new Date());
					pFinanceiro.setOutrasInformacoes(
							"Gerado para complementar o valor do Contas a Pagar nÂº " + seqFinanceiro + ".");

					this.financeiroService.salvar(pFinanceiro);
				} else {
					this.financeiro = this.financeiroService.salvar(this.financeiro);
				}
			}

			this.tela = Integer.valueOf(pTela);
			break;
		case 3:
			this.financeiro.setOrigemLCM("3");
			this.financeiro = this.financeiroService.salvar(this.financeiro);

			break;
		case 4:
			this.financeiro.setOrigemLCM("4");
			this.financeiro = this.financeiroService.salvar(this.financeiro);
			break;

		case 5:
			this.financeiro.setOrigemLCM("5");
			this.financeiro.setOperacao("CRÉDITO");
			this.financeiro.setEtapa("QUITADO");
			System.out.println("contacorrente");
			this.financeiro = this.financeiroService.salvar(this.financeiro);

			this.tela = Integer.valueOf(pTela);
			break;

		case 6:
			this.financeiro.setOrigemLCM("6");
			this.financeiro = this.financeiroService.salvar(this.financeiro);

			for (FinanceiroItemPc pFinanceiroItemPc : this.listaFinanceiroItemPc) {
				System.out.println(this.financeiro.getSeqFinanceiro());
				pFinanceiroItemPc.setSeqEmpresa(this.loginController.empresa.getSeqEmpresa());
				pFinanceiroItemPc.setSeqFinanceiro(this.financeiro.getSeqFinanceiro());
				this.financeiroItemPcService.salvar(pFinanceiroItemPc);
			}

			if (!this.listaFinanceiroItemPcDeletado.isEmpty())
				for (FinanceiroItemPc deletado : this.listaFinanceiroItemPcDeletado)
					this.financeiroItemPcService.deletar(deletado);
			break;

		case 7:
			this.financeiro.setOrigemLCM("7");
			this.financeiro = this.financeiroService.salvar(this.financeiro);
		}

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro armazenado com sucesso.", ""));
	}

	public void repeticao(String pIndefinidamente, String pIntevaloTempo, int pIntervaloNumero) {
		Financeiro financeiroRepeticao = this.financeiro;
		System.out.println("ENTROU");

		int intervalo = 0;

		financeiroRepeticao.setSeqFinanceiro(null);
		financeiroRepeticao.setIntervaloTempo(null);
		financeiroRepeticao.setIntervaloNumero(Integer.valueOf(0));
		financeiroRepeticao.setIndefinidamente(null);
		financeiroRepeticao.setChkRepeticao(false);

		Date dataVencimentoRepeticao = this.financeiro.getDataVencimento();

		if (pIntervaloNumero > 0) {
			System.out.println("IntervaloNumero :" + pIntervaloNumero);
			intervalo = pIntervaloNumero;
			System.out.println("IntervaloNumero :" + pIntervaloNumero);
		}

		if (pIndefinidamente.equals("NAO")) {
			switch (pIntevaloTempo) {
			case "DIA":
				for (int i = 0; i < financeiroRepeticao.getParcelaFim().intValue(); i++) {
					financeiroRepeticao.setSeqFinanceiro(null);
					if (financeiroRepeticao.getOcorrencias().intValue() <= financeiroRepeticao.getParcelaFim()
							.intValue()) {
						financeiroRepeticao
								.setOcorrencias(Integer.valueOf(financeiroRepeticao.getOcorrencias().intValue() + 1));
					}
					dataVencimentoRepeticao.setDate(this.financeiro.getDataVencimento().getDate() + intervalo);
					financeiroRepeticao.setDataVencimento(dataVencimentoRepeticao);
					financeiroRepeticao.setOrigemLCM("2");
					financeiroRepeticao = this.financeiroService.salvar(financeiroRepeticao);
				}
				break;
			case "SEMANA":
				for (int i = 0; i < financeiroRepeticao.getParcelaFim().intValue(); i++) {
					financeiroRepeticao.setSeqFinanceiro(null);
					if (financeiroRepeticao.getOcorrencias().intValue() <= financeiroRepeticao.getParcelaFim()
							.intValue()) {
						financeiroRepeticao
								.setOcorrencias(Integer.valueOf(financeiroRepeticao.getOcorrencias().intValue() + 1));
					}
					dataVencimentoRepeticao.setDate(this.financeiro.getDataVencimento().getDate() + intervalo * 7);
					financeiroRepeticao.setDataVencimento(dataVencimentoRepeticao);
					financeiroRepeticao.setOrigemLCM("2");
					financeiroRepeticao = this.financeiroService.salvar(financeiroRepeticao);
				}
				break;
			case "MES":
				for (int i = 0; i < financeiroRepeticao.getParcelaFim().intValue(); i++) {
					financeiroRepeticao.setSeqFinanceiro(null);
					if (financeiroRepeticao.getOcorrencias().intValue() <= financeiroRepeticao.getParcelaFim()
							.intValue()) {
						financeiroRepeticao
								.setOcorrencias(Integer.valueOf(financeiroRepeticao.getOcorrencias().intValue() + 1));
					}
					dataVencimentoRepeticao.setMonth(this.financeiro.getDataVencimento().getMonth() + intervalo);
					financeiroRepeticao.setDataVencimento(dataVencimentoRepeticao);
					financeiroRepeticao.setOrigemLCM("2");
					financeiroRepeticao = this.financeiroService.salvar(financeiroRepeticao);
				}
				break;
			case "ANO":
				for (int i = 0; i < financeiroRepeticao.getParcelaFim().intValue(); i++) {
					financeiroRepeticao.setSeqFinanceiro(null);
					if (financeiroRepeticao.getOcorrencias().intValue() <= financeiroRepeticao.getParcelaFim()
							.intValue()) {
						financeiroRepeticao
								.setOcorrencias(Integer.valueOf(financeiroRepeticao.getOcorrencias().intValue() + 1));
					}
					dataVencimentoRepeticao.setYear(this.financeiro.getDataVencimento().getYear() + intervalo);
					financeiroRepeticao.setDataVencimento(dataVencimentoRepeticao);
					financeiroRepeticao.setOrigemLCM("2");
					financeiroRepeticao = this.financeiroService.salvar(financeiroRepeticao);
				}
			}

		} else {
			switch (pIntevaloTempo) {
			case "DIA":
				dataVencimentoRepeticao.setDate(this.financeiro.getDataVencimento().getDate() + intervalo);
				financeiroRepeticao.setDataVencimento(dataVencimentoRepeticao);
				financeiroRepeticao.setOrigemLCM("2");
				this.financeiroService.salvar(financeiroRepeticao);
				break;
			case "SEMANA":
				dataVencimentoRepeticao.setDate(this.financeiro.getDataVencimento().getDate() + intervalo * 7);
				financeiroRepeticao.setDataVencimento(dataVencimentoRepeticao);
				financeiroRepeticao.setOrigemLCM("2");
				this.financeiroService.salvar(financeiroRepeticao);
				break;
			case "MES":
				dataVencimentoRepeticao.setMonth(this.financeiro.getDataVencimento().getMonth() + intervalo);
				financeiroRepeticao.setDataVencimento(dataVencimentoRepeticao);
				financeiroRepeticao.setOrigemLCM("2");
				this.financeiroService.salvar(financeiroRepeticao);
				break;
			case "ANO":
				dataVencimentoRepeticao.setYear(this.financeiro.getDataVencimento().getYear() + intervalo);
				financeiroRepeticao.setDataVencimento(dataVencimentoRepeticao);
				financeiroRepeticao.setOrigemLCM("2");
				this.financeiroService.salvar(financeiroRepeticao);
			}

		}
	}

	public void novo() {
		this.id = false;
		this.financeiro = new Financeiro();

		this.documento = new Documento();

		this.docProposta = new Documento();
		this.codigoOrdemServico = "";

		this.vincularDocumento = false;
		this.vincularParceiro = false;

		this.financeiro.setDataLancamento(new Date());
		this.financeiro.setSeqUsuario(this.loginController.usuario.getSeqUsuario());
		this.financeiro.setResponsavel(this.loginController.usuario.getUsuario());

		limpar();

		this.tela = Integer.valueOf(1);
	}

	public void listar() {
		this.totalValorBruto = BigDecimal.ZERO;
		this.totalValorLiquido = BigDecimal.ZERO;

		ClausulaWhere condicao = new ClausulaWhere();

		condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "financeiro.seq_empresa", GeneroCondicaoWhere.igual,
				String.valueOf(this.loginController.getEmpresa().getSeqEmpresa()), TipoCondicaoWhere.Numero);
		condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.ORIGEM_LCM", GeneroCondicaoWhere.igual,
				String.valueOf("0"), TipoCondicaoWhere.Texto);

		if (isPorDataLancamento() == true) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.data_lancamento",
					GeneroCondicaoWhere.MaiorIgual, this.dataLancamentoInicial, TipoCondicaoWhere.Data);
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.data_lancamento",
					GeneroCondicaoWhere.MenorIgual, this.dataLancamentoFinal, TipoCondicaoWhere.Data);
		}

		if (isPorDataEmissao() == true) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.data_emissao",
					GeneroCondicaoWhere.MaiorIgual, this.dataEmissaoInicial, TipoCondicaoWhere.Data);
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.data_emissao",
					GeneroCondicaoWhere.MenorIgual, this.dataEmissaoFinal, TipoCondicaoWhere.Data);
		}

		if (isPorDataVencimento() == true) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.data_vencimento",
					GeneroCondicaoWhere.MaiorIgual, this.dataVencimentoInicial, TipoCondicaoWhere.Data);
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.data_vencimento",
					GeneroCondicaoWhere.MenorIgual, this.dataVencimentoFinal, TipoCondicaoWhere.Data);
		}

		if (this.seqParceiroSelecionado != null) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "p.seq_parceiro", GeneroCondicaoWhere.igual,
					String.valueOf(this.seqParceiroSelecionado), TipoCondicaoWhere.Numero);
		}

		if (this.seqDocumentoFiscalSelecionado != null) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.seq_documento_fiscal",
					GeneroCondicaoWhere.igual, String.valueOf(this.seqDocumentoFiscalSelecionado),
					TipoCondicaoWhere.Numero);
		}
		if (this.seqNaturezaSelecionado != null) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.seq_tipo_movimento",
					GeneroCondicaoWhere.igual, String.valueOf(this.seqNaturezaSelecionado), TipoCondicaoWhere.Numero);
		}
		if (this.seqCategoriaSelecionado != null) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro_categoria.seq_financeiro_categoria",
					GeneroCondicaoWhere.igual, String.valueOf(this.seqCategoriaSelecionado), TipoCondicaoWhere.Numero);
		}
		if (this.seqCodigoFiscalSelecionado != null) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.seq_aliquota_codigo_fiscal",
					GeneroCondicaoWhere.igual, String.valueOf(this.seqCodigoFiscalSelecionado),
					TipoCondicaoWhere.Numero);
		}
		if (this.seqUnidadeNegocioSelecionado != null) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.seq_unidade_negocio",
					GeneroCondicaoWhere.igual, String.valueOf(this.seqUnidadeNegocioSelecionado),
					TipoCondicaoWhere.Numero);
		}

		this.listaFinanceiro = this.financeiroService.listarFiltro(condicao);

		for (Financeiro pFinanceiro : this.listaFinanceiro) {
			if (pFinanceiro.getValor() != null) {
				this.totalValorBruto = this.totalValorBruto.add(pFinanceiro.getValor()).setScale(2,
						RoundingMode.HALF_EVEN);
			}
			if (pFinanceiro.getValorPagamento() != null) {
				this.totalValorLiquido = this.totalValorLiquido.add(pFinanceiro.getValorPagamento()).setScale(2,
						RoundingMode.HALF_EVEN);
			} else {
				pFinanceiro.setValorPagamento(BigDecimal.ZERO);
			}
		}
	}

	public void cancelar() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String dataCancelamento = formato.format(new Date());

		String msg = "'".concat(this.financeiro.getMotivoCancelamento()).concat("'. Por:")
				.concat(this.loginController.getUsuario().getUsuario()).concat(". Data de Cancelamento:")
				.concat(dataCancelamento);
		this.financeiro.setMotivoCancelamento(msg);
		this.financeiro.setEtapa("CANCELADO");
		this.financeiro = this.financeiroService.salvar(this.financeiro);
	}

	public void deletar() {
		if (this.financeiroService.deletar(this.financeiro)) {
			listar();
			this.financeiro = new Financeiro();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro eliminado com sucesso.", ""));
			this.tela = Integer.valueOf(0);
			listar();
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao excluir registro.", ""));
		}
	}

	public void fecharTela() throws IOException {
		this.loginController.mudarPagina("/principal/ principal.jsf");
	}
        
        
        public void selecionarDocumento(Financeiro pFinanceiro) {
                this.loginController.mudarPagina("/Financeiro/faturamento.jsf?idDocumento=" + pFinanceiro.getSeqDocumento()+ "&idTipoDocumento=");
        }
        
        
	public void selecionar(Financeiro pFinanceiro) {
		this.idAccordionPanel = Integer.valueOf(-1);
		this.id = true;
		this.idEditarQuitacao = Integer.valueOf(0);
		this.saldoRestante = new BigDecimal(0);
		this.financeiro = this.financeiroService.listarSeq(this.loginController.getUsuario().getSeqEmpresa(),
				pFinanceiro.getSeqFinanceiro());
                this.listaUpload = this.uploadService.listar(this.loginController.empresa.getSeqEmpresa(),this.financeiro.getSeqFinanceiro());
                
		if (this.financeiro.getOrigemLCM().equals("6")) {
			this.listaFinanceiroItemPc = this.financeiroItemPcService
					.listar(this.loginController.empresa.getSeqEmpresa(), this.financeiro.getSeqFinanceiro());
			System.out.println(this.listaFinanceiroItemPc.size() + "-----");
                     
			if (this.financeiro.getSeqParceiro() != null) {
				listarEquipEmbarc();
			}
			this.tela = Integer.valueOf(1);
			return;
		}

		if (this.financeiro.getOrigemLCM().equals("7")) {
			this.tela = Integer.valueOf(1);
			return;
		}

		if (this.financeiro.getMotivoCancelamento() != null) {
			this.msgCancelamentoAnterior = this.financeiro.getMotivoCancelamento();
		}

		this.financeiroQuitacao.setDataPagamento(this.financeiro.getDataPagamento());
		this.financeiroQuitacao.setSeqConta(this.financeiro.getSeqConta());
		this.financeiroQuitacao.setValorDesconto(this.financeiro.getValorDesconto());
		this.financeiroQuitacao.setValorMulta(this.financeiro.getValorMulta());
		this.financeiroQuitacao.setValorJuros(this.financeiro.getValorJuros());
		this.financeiroQuitacao.setValorPagamento(this.financeiro.getValorPagamento());
		this.financeiroQuitacao.setValorRecebido(this.financeiro.getValorRecebido());
		this.financeiroQuitacao.setOutrosAcrescimos(this.financeiro.getOutrosAcrescimos());
		this.financeiroQuitacao.setResponsavelQuitacao(this.financeiro.getResponsavelQuitacao());
		this.financeiroQuitacao.setTipoQuitacao(this.financeiro.getTipoQuitacao());
		this.financeiroQuitacao.setValorParcial(this.financeiro.getValorParcial());

		if ((this.financeiro.getTipoQuitacao() != null) && (this.financeiro.getTipoQuitacao().equals("PARCIAL"))) {
			this.saldoRestante = this.financeiro.getValorRecebido().subtract(this.financeiro.getValorParcial());
		}

		if (this.financeiro.getSeqDocumento() != null) {
			selecionarDocumento();
			listarDocumento();
			limpar();
			buscarNumeroProposta();
			this.vincularDocumento = true;
		}
		if (this.financeiro.getSeqParceiro() != null) {
			this.vincularParceiro = true;
			listarDetalheParceiro();
		}

		if (this.financeiro.getSeqUnidadeNegocio() != null) {
			buscarContratada();
		}

		if (this.financeiro.getOrigemLCM().equals("0")) {
			calcularAliquota("ISSQN");
			calcularAliquota("CSLL");
			calcularAliquota("IRRF");
			calcularAliquota("PIS");
			calcularAliquota("COFINS");
		}
               
		this.tela = Integer.valueOf(1);
	}
        
        
        
        
          public void selecionarPO(Financeiro pFinanceiro)
  {
    this.id = true;
    this.financeiro = ((Financeiro)this.financeiroService.listar(this.loginController.getUsuario().getSeqEmpresa(), pFinanceiro.getSeqFinanceiro()).get(0));
    
    this.listaFinanceiroItemPc = this.financeiroItemPcService.listar(this.loginController.empresa.getSeqEmpresa(), this.financeiro.getSeqFinanceiro());
    System.out.println(this.listaFinanceiroItemPc.size() + "-----");
    if (this.financeiro.getSeqParceiro() != null) {
      listarEquipEmbarc();
      
    }
    this.tela = Integer.valueOf(1);
  }
        
        

	public void mudarTela(Integer pTela) {
		this.financeiro = new Financeiro();
		this.tela = pTela;
	}

	public void listarDocumento() {
		if (this.tipoDocumentoSelecionado != null) {
			if (this.tipoDocumentoSelecionado.equals("401")) {
				this.financeiro.setSeqTipoDocumento("401");
				this.listaTodosDocumentos = this.documentoService.listarPorTipo(this.financeiro.getSeqTipoDocumento());
				for (Documento listaTodosDocumento : this.listaTodosDocumentos) {
					if ((listaTodosDocumento.getIdRevisao().longValue() == 0L)
							&& (listaTodosDocumento.getIdComplementar().longValue() > 0L)) {
						listaTodosDocumento.setEstadoBM(
								" - Complementar nÂº".concat(listaTodosDocumento.getIdComplementar().toString()));
						this.listaDocumento.add(listaTodosDocumento);
					}
					if ((listaTodosDocumento.getIdRevisao().longValue() > 0L)
							&& (listaTodosDocumento.getIdComplementar().longValue() == 0L)) {
						listaTodosDocumento
								.setEstadoBM(" - Revisado nÂº".concat(listaTodosDocumento.getIdRevisao().toString()));
						this.listaDocumento.add(listaTodosDocumento);
					}
					if ((listaTodosDocumento.getSequencia() == 0)
							&& (listaTodosDocumento.getIdComplementar().longValue() == 0L)
							&& (listaTodosDocumento.getIdRevisao().longValue() == 0L)) {
						this.listaDocumento.add(listaTodosDocumento);
					}
				}
			} else if (this.tipoDocumentoSelecionado.equals("LF")) {
				ClausulaWhere condicao = new ClausulaWhere();

				condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "financeiro.seq_empresa",
						GeneroCondicaoWhere.igual, String.valueOf(this.loginController.getEmpresa().getSeqEmpresa()),
						TipoCondicaoWhere.Numero);
				condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.ORIGEM_LCM",
						GeneroCondicaoWhere.igual, String.valueOf("0"), TipoCondicaoWhere.Texto);

				this.listaFinanceiro = this.financeiroService.listarFiltro(condicao);
			}
		}
	}

	public void limpar() {
		this.percentual = new BigDecimal(0);
		this.valorISSQN = new BigDecimal(0);
		this.valorCSLL = new BigDecimal(0);
		this.valorIRRF = new BigDecimal(0);
		this.valorPIS = new BigDecimal(0);
		this.valorCOFINS = new BigDecimal(0);
	}

	public void selecionarDocumento() {
		if (this.financeiro.getSeqDocumento() == null) {
			this.docProposta = new Documento();
			this.codigoOrdemServico = "";
			this.financeiro.setValor(BigDecimal.ZERO);
			this.documento = new Documento();
		} else {
			this.documento = this.documentoService.buscarPorID(this.loginController.getEmpresa().getSeqEmpresa(),
					this.financeiro.getSeqDocumento());

			if (this.documento == null) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Falha ao encontrar Boletim de Medição. Boletim de Medição inexistente.", ""));
				return;
			}

			this.financeiro.setMoedaOrigem(this.documento.getMoedaDestino());
			this.financeiro.setSeqTipoMovimento(this.documento.getSeqTipoMovimentoFinanceiro());
			this.financeiro.setSeqParceiro(this.documento.getSeqParceiro());
			this.financeiro.setValor(this.documento.getVlBruto());
			this.financeiro.setSeqUnidadeNegocio(this.documento.getSeqUnidadeNegocio());

			if (this.financeiro.getSeqUnidadeNegocio() != null) {
				buscarContratada();
			}

			if (this.documento.getSeqTipoDocumento().equals("401")) {
				this.financeiro.setValor(this.documento.getVlBruto());
			} else {
				this.financeiro.setValor(this.documento.getVlTotal());
			}

			if (this.documento.getNatureza() == null) {
				this.documento.setNatureza("-");
			}
			if (this.documento.getCategoria() == null) {
				this.documento.setCategoria("-");
			}
			if (this.documento.getMoeda() == null) {
				this.documento.setMoeda("-");
			}
			buscarNumeroProposta();

			if (this.financeiro.getValor() == null) {
				this.financeiro.setValorPagamento(null);
			} else {
				calcularAliquota("ISSQN");
				calcularAliquota("CSLL");
				calcularAliquota("IRRF");
				calcularAliquota("PIS");
				calcularAliquota("COFINS");
				calcularValorLiquido();
			}
		}

		if (this.tipoDocumentoSelecionado.equals("LF")) {
			Financeiro pFaturamento = (Financeiro) this.financeiroService
					.listar(this.loginController.empresa.getSeqEmpresa(), this.financeiro.getSeqFinanceiroFaturamento())
					.get(0);
			this.financeiro.setSeqTipoMovimento(pFaturamento.getSeqTipoMovimento());
			this.financeiro.setSeqParceiro(pFaturamento.getSeqParceiro());
			this.financeiro.setValor(pFaturamento.getValor());
		}
	}

	public void buscarNumeroProposta() {
		this.docProposta = new Documento();
		this.codigoOrdemServico = "";

		if (this.documento == null) {
			return;
		}
		if (this.documento.getSeqDocumentoDono() != null) {
			this.seqProposta = this.documentoService
					.buscarPorID(this.loginController.usuario.getSeqEmpresa(), this.documento.getSeqDocumentoDono())
					.getSeqDocumentoDono();
			this.docProposta = this.documentoService.buscarPorID(this.loginController.usuario.getSeqEmpresa(),
					this.seqProposta);

			this.codigoOrdemServico = ((Documento) this.documentoService.listarPorTipoSeq("301", this.seqProposta)
					.get(0)).getCodigo();
		}

		if (this.documento.getGrupoServico() == null) {
			this.documento.setGrupoServico("-");
		}
	}

	public void buscarContratada() {
		for (UnidadeNegocio un : this.listaUnidadeNegocio) {
			if (un.getSeqUnidadeNegocio().equals(this.financeiro.getSeqUnidadeNegocio())) {
				if (un.getCnpj() == null) {
					this.unidadeNegocio = un;
					this.unidadeNegocio.setCnpj("S/Nº.");
				} else {
					this.unidadeNegocio = un;
				}
			} else if (this.financeiro.getSeqUnidadeNegocio() == null) {
				this.unidadeNegocio = new UnidadeNegocio();
			}
		}
	}

	public void selecionarRetencao() {
		if (this.financeiro.getRetencaoISSQN().equals(new BigDecimal(1))) {
			this.financeiro.setSeqAliquotaISSQN("1");
			this.valorISSQN = new BigDecimal(0);
			calcularValorLiquido();
		}
	}

	public void limiteData() {
		if (this.financeiro.getDataVencimento() != null) {
			System.out.println(this.financeiro.getDataVencimento());
			if (this.financeiro.getDataEmissao().after(this.financeiro.getDataVencimento())) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Data de EmissÃ£o nÃ£o pode ser superior a Data de Vencimento.", ""));
				return;
			}
		}
		if ((this.financeiro.getDataPeriodoFinalRLBM() != null)
				&& (this.financeiro.getDataPeriodoInicialRLBM().after(this.financeiro.getDataPeriodoFinalRLBM()))) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Data Inicial nÃ£o pode ser superior a Data Final.", ""));
			return;
		}
	}

	public void buscarPercentual(String seqAliquota) {
		Aliquota aliquota = (Aliquota) this.aliquotaService
				.listarPorSeq(this.loginController.usuario.getSeqEmpresa(), seqAliquota, Situacao.ATIVO).get(0);
		if (aliquota.getTipoAliquota().equals("ISSQN RETIDO")) {
			this.financeiro.setCodigoFiscalMunicipal(aliquota.getDescricao());
			this.financeiro.setSeqAliquotaCodigoFiscal(aliquota.getSeqAliquota());
		}
		this.percentual = aliquota.getPercentual();
	}

	public void selecionarCodigoFiscal() {
		buscarPercentual(this.financeiro.getSeqAliquotaCodigoFiscal());
	}

	public void setAliquotaISSQN(Aliquota pAliquota) {
		this.financeiro.setSeqAliquotaISSQN(pAliquota.getSeqAliquota());
	}

	public void calcularValorLiquido() {
		if ((this.valorISSQN != null) && (this.valorIRRF != null) && (this.valorPIS != null) && (this.valorCSLL != null)
				&& (this.valorCOFINS != null)) {
			this.financeiro.setValorPagamento(this.financeiro.getValor()
					.subtract(this.valorIRRF
							.add(this.valorPIS.add(this.valorCSLL.add(this.valorCOFINS.add(this.valorISSQN)))))
					.setScale(2, RoundingMode.HALF_EVEN));
		}

		/*if ((this.financeiro.getMoedaDestino() != null) && (this.financeiro.getMoedaOrigem() != null)) {
			if (this.financeiro.getMoedaDestino().equals(this.financeiro.getMoedaOrigem())) {
				this.financeiro.setValorPagamento(this.financeiro.getValor()
						.subtract(this.valorIRRF
								.add(this.valorPIS.add(this.valorCSLL.add(this.valorCOFINS.add(this.valorISSQN)))))
						.setScale(2, RoundingMode.HALF_EVEN));
			} else if ((this.financeiro.isChkTaxaCambio()) && (this.financeiro.getValorPagamento() != null)
					&& (this.financeiro.getTaxaCambio() != null)) {
				this.financeiro.setValorPagamento(
						this.financeiro.getValorPagamento().multiply(this.financeiro.getTaxaCambio()));
			}
		}*/
	}

        public void calcularSaldoReceber() {
					if (this.financeiro.getTipoQuitacao() == "TOTAL") {
				this.financeiro.setValorRecebido(this.financeiro.getValor());
			}
        }
        
	public void calcularAliquota(String tipo) {
		switch (tipo) {
		case "ISSQN":
			if ((this.financeiro.getSeqAliquotaISSQN() != null) && (this.financeiro.getValor() != null)) {
				buscarPercentual(this.financeiro.getSeqAliquotaISSQN());
				this.valorISSQN = this.financeiro.getValor().multiply(this.percentual.divide(new BigDecimal(100)))
						.setScale(2, RoundingMode.HALF_EVEN);
				calcularValorLiquido();
			}
			break;
		case "IRRF":
			if ((this.financeiro.getSeqAliquotaIRRF() != null) && (this.financeiro.getValor() != null)) {
				buscarPercentual(this.financeiro.getSeqAliquotaIRRF());
				this.valorIRRF = this.financeiro.getValor().multiply(this.percentual.divide(new BigDecimal(100)))
						.setScale(2, RoundingMode.HALF_EVEN);
				calcularValorLiquido();
			}
			break;
		case "CSLL":
			if ((this.financeiro.getSeqAliquotaCSLL() != null) && (this.financeiro.getValor() != null)) {
				buscarPercentual(this.financeiro.getSeqAliquotaCSLL());
				this.valorCSLL = this.financeiro.getValor().multiply(this.percentual.divide(new BigDecimal(100)))
						.setScale(2, RoundingMode.HALF_EVEN);
				calcularValorLiquido();
			}
			break;
		case "PIS":
			if ((this.financeiro.getSeqAliquotaPIS() != null) && (this.financeiro.getValor() != null)) {
				buscarPercentual(this.financeiro.getSeqAliquotaPIS());
				this.valorPIS = this.financeiro.getValor().multiply(this.percentual.divide(new BigDecimal(100)))
						.setScale(2, RoundingMode.HALF_EVEN);
				calcularValorLiquido();
			}
			break;
		case "COFINS":
			if ((this.financeiro.getSeqAliquotaCOFINS() != null) && (this.financeiro.getValor() != null)) {
				buscarPercentual(this.financeiro.getSeqAliquotaCOFINS());
				this.valorCOFINS = this.financeiro.getValor().multiply(this.percentual.divide(new BigDecimal(100)))
						.setScale(2, RoundingMode.HALF_EVEN);
				calcularValorLiquido();
			}
			break;
		case "ATUALIZAR":
			if (this.financeiro.getSeqAliquotaISSQN() != null) {
				buscarPercentual(this.financeiro.getSeqAliquotaISSQN());
				this.valorISSQN = this.financeiro.getValor().multiply(this.percentual.divide(new BigDecimal(100)))
						.setScale(2, RoundingMode.HALF_EVEN);
			}
			if (this.financeiro.getSeqAliquotaIRRF() != null) {
				buscarPercentual(this.financeiro.getSeqAliquotaIRRF());
				this.valorIRRF = this.financeiro.getValor().multiply(this.percentual.divide(new BigDecimal(100)))
						.setScale(2, RoundingMode.HALF_EVEN);
			}
			if (this.financeiro.getSeqAliquotaCSLL() != null) {
				buscarPercentual(this.financeiro.getSeqAliquotaCSLL());
				this.valorCSLL = this.financeiro.getValor().multiply(this.percentual.divide(new BigDecimal(100)))
						.setScale(2, RoundingMode.HALF_EVEN);
			}
			if (this.financeiro.getSeqAliquotaPIS() != null) {
				buscarPercentual(this.financeiro.getSeqAliquotaPIS());
				this.valorPIS = this.financeiro.getValor().multiply(this.percentual.divide(new BigDecimal(100)))
						.setScale(2, RoundingMode.HALF_EVEN);
			}
			if (this.financeiro.getSeqAliquotaCOFINS() != null) {
				buscarPercentual(this.financeiro.getSeqAliquotaCOFINS());
				this.valorCOFINS = this.financeiro.getValor().multiply(this.percentual.divide(new BigDecimal(100)))
						.setScale(2, RoundingMode.HALF_EVEN);
			}
			calcularValorLiquido();
		}
	}

	public void vincular() {
		System.out.println("vincular" + this.vincularDocumento);
	}

	public void iniciarContasReceber() {
		if ((this.loginController.usuario.getAcFinContasReceber() == null)
				|| (this.loginController.usuario.getAcFinContasReceber().equals("-1"))) {
			this.loginController.mudarPagina("/organizacional/acessonegado.jsf");
			return;
		}

		ClausulaWhere condicaoParceiro = new ClausulaWhere();

		condicaoParceiro.AdicionarCondicaoManual(
				"where parceiro.seq_empresa = 61 and (PARCEIRO.seq_tipo_parceiro = 641 or PARCEIRO.seq_tipo_parceiro=702 or PARCEIRO.seq_tipo_parceiro=0)");

		this.listaDocumentoFiscal = this.documentoFiscalService.listar(this.loginController.empresa.getSeqEmpresa(), "",
				Situacao.ATIVO);
		this.listaCentroCusto = this.centroCustoService.listar(this.loginController.empresa.getSeqEmpresa(), "",
				Situacao.ATIVO);
		this.listaFormaPagamento = this.formaPagamentoService.listar(this.loginController.empresa.getSeqEmpresa(), "",
				Situacao.ATIVO);
		this.listaUnidadeNegocio = this.unidadeNegocioService.listar(this.loginController.empresa.getSeqEmpresa(), "",
				Situacao.ATIVO);
		this.listaConta = this.contaService.listar(this.loginController.usuario.getSeqEmpresa(), "", Situacao.ATIVO);

		this.listaTipoLancamento = this.tipoMovimentoFinanceiroService
				.listarPorOperacao(this.loginController.empresa.getSeqEmpresa(), Situacao.ATIVO, "Crédito");
		this.listaFinanceiroCategoria = this.financeiroCategoriaService
				.listar(this.loginController.empresa.getSeqEmpresa(), "", Situacao.ATIVO);
		this.listaTipoDocumento = this.tipoDocumentoService.listarTodos(this.loginController.empresa.getSeqEmpresa(),
				"");

		this.listaParceiro = this.parceiroService.listarParceiroFiltro(condicaoParceiro);
		this.unidadeNegocio = new UnidadeNegocio();
	}

	public void listarContasReceber() throws ParseException {
		this.totalValorBruto = new BigDecimal(0);
		this.totalValorLiquido = new BigDecimal(0);

		ClausulaWhere condicao = new ClausulaWhere();

		condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "financeiro.seq_empresa", GeneroCondicaoWhere.igual,
				String.valueOf(this.loginController.getEmpresa().getSeqEmpresa()), TipoCondicaoWhere.Numero);
		condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.ORIGEM_LCM", GeneroCondicaoWhere.igual,
				String.valueOf("1"), TipoCondicaoWhere.Texto);

		if (isPorDataLancamento() == true) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.data_lancamento",
					GeneroCondicaoWhere.MaiorIgual, this.dataLancamentoInicial, TipoCondicaoWhere.Data);
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.data_lancamento",
					GeneroCondicaoWhere.MenorIgual, this.dataLancamentoFinal, TipoCondicaoWhere.Data);
		}

		if (isPorDataEmissao() == true) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.data_emissao",
					GeneroCondicaoWhere.MaiorIgual, this.dataEmissaoInicial, TipoCondicaoWhere.Data);
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.data_emissao",
					GeneroCondicaoWhere.MenorIgual, this.dataEmissaoFinal, TipoCondicaoWhere.Data);
		}

		if (isPorDataVencimento() == true) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.data_vencimeNto",
					GeneroCondicaoWhere.MaiorIgual, this.dataVencimentoInicial, TipoCondicaoWhere.Data);
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.data_vencimento",
					GeneroCondicaoWhere.MenorIgual, this.dataVencimentoFinal, TipoCondicaoWhere.Data);
		}
		if (isPorDataPagamento() == true) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.data_pagamento",
					GeneroCondicaoWhere.MaiorIgual, this.dataPagamentoInicial, TipoCondicaoWhere.Data);
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.data_pagamento",
					GeneroCondicaoWhere.MenorIgual, this.dataPagamentoFinal, TipoCondicaoWhere.Data);
		}
		if (this.seqParceiroSelecionado != null) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.seq_parceiro", GeneroCondicaoWhere.igual,
					String.valueOf(this.seqParceiroSelecionado), TipoCondicaoWhere.Numero);
		}

		if (this.seqDocumentoFiscalSelecionado != null) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.seq_documento_fiscal",
					GeneroCondicaoWhere.igual, String.valueOf(this.seqDocumentoFiscalSelecionado),
					TipoCondicaoWhere.Numero);
		}
		if (this.statusSelecionado != null) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.status", GeneroCondicaoWhere.igual,
					this.statusSelecionado, TipoCondicaoWhere.Texto);
		}

		if (this.seqNaturezaSelecionado != null) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.seq_tipo_movimento",
					GeneroCondicaoWhere.igual, String.valueOf(this.seqNaturezaSelecionado), TipoCondicaoWhere.Numero);
		}
		if (this.seqCategoriaSelecionado != null) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro_categoria.seq_financeiro_categoria",
					GeneroCondicaoWhere.igual, String.valueOf(this.seqCategoriaSelecionado), TipoCondicaoWhere.Numero);
		}
		if (this.seqContaSelecionado != null) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.seq_conta", GeneroCondicaoWhere.igual,
					String.valueOf(this.seqContaSelecionado), TipoCondicaoWhere.Numero);
		}

		this.listaFinanceiro = this.financeiroService.listarFiltro(condicao);
		verificarVencimento();

		for (Financeiro pFinanceiro : this.listaFinanceiro) {
			if (pFinanceiro.getValor() != null) {
				this.totalValorBruto = this.totalValorBruto.add(pFinanceiro.getValor()).setScale(2,
						RoundingMode.HALF_EVEN);
			}
			if (pFinanceiro.getValorRecebido() != null) {
				this.totalValorLiquido = this.totalValorLiquido.add(pFinanceiro.getValorRecebido()).setScale(2,
						RoundingMode.HALF_EVEN);
			} else {
				pFinanceiro.setValorRecebido(BigDecimal.ZERO);
			}
		}
	}

	public void verificarVencimento() {
		Date dataAtual = new Date();
		for (Financeiro lista : this.listaFinanceiro) {

			if (lista.getDataVencimento() != null) {
				if (lista.getEtapa() == null) {
					lista.setEtapa("A VENCER");
					this.financeiroService.salvar(lista);
				}
				if ((!lista.getEtapa().equals("CANCELADO")) && (!lista.getEtapa().equals("STANDY-BY"))) {
					if (lista.getDataPagamento() != null) {

						if (lista.getTipoQuitacao() != null) {
							if ((lista.getTipoQuitacao().equals("PARCIAL"))
									&& (!lista.getEtapa().equals("PARCIALMENTE QUITADO"))) {
								lista.setEtapa("PARCIALMENTE QUITADO");
								this.financeiroService.salvar(lista);
							} else if ((lista.getTipoQuitacao().equals("TOTAL"))
									&& (!lista.getEtapa().equals("QUITADO"))) {
								lista.setEtapa("QUITADO");
								this.financeiroService.salvar(lista);
							}
						} else if ((lista.getValor().equals(lista.getValorPagamento()))
								|| (lista.getValor().equals(lista.getValorRecebido()))) {
							lista.setEtapa("QUITADO");
							this.financeiroService.salvar(lista);
						} else {
							lista.setEtapa("PARCIALMENTE QUITADO");
							this.financeiroService.salvar(lista);
						}
					} else if (((dataAtual.getYear() > lista.getDataVencimento().getYear())
							|| ((dataAtual.getDate() > lista.getDataVencimento().getDate())
									&& (dataAtual.getMonth() >= lista.getDataVencimento().getMonth()))
							|| ((dataAtual.getMonth() > lista.getDataVencimento().getMonth())
									&& (dataAtual.getYear() == lista.getDataVencimento().getYear())))
							&& (!lista.getEtapa().equals("VENCIDO"))) {
						lista.setEtapa("VENCIDO");
						this.financeiroService.salvar(lista);
					}
				}
			}
		}
	}

	public void editarQuitacao() {
		this.idEditarQuitacao = Integer.valueOf(1);
	}

	public void salvarQuitacao(int index) {
		this.idEditarQuitacao = Integer.valueOf(0);

		this.financeiro.setSeqConta(this.financeiroQuitacao.getSeqConta());
		this.financeiro.setValorDesconto(this.financeiroQuitacao.getValorDesconto());
		this.financeiro.setValorMulta(this.financeiroQuitacao.getValorMulta());
		this.financeiro.setValorJuros(this.financeiroQuitacao.getValorJuros());
		this.financeiro.setOutrosAcrescimos(this.financeiroQuitacao.getOutrosAcrescimos());
		this.financeiro.setResponsavelQuitacao(this.loginController.getUsuario().getUsuario());

		switch (index) {
		case 0:
			this.financeiro.setValorPagamento(this.financeiroQuitacao.getValorPagamento());
			break;
		case 1:
			this.financeiro.setValorParcial(this.financeiroQuitacao.getValorParcial());
			this.financeiro.setTipoQuitacao(this.financeiroQuitacao.getTipoQuitacao());
			this.financeiro.setDataPagamento(this.financeiroQuitacao.getDataPagamento());

			this.financeiro.setValorRecebido(this.financeiroQuitacao.getValorRecebido());
		}
	}

	public void cancelarQuitacao() {
		this.idEditarQuitacao = Integer.valueOf(0);
		this.financeiroQuitacao.setValorDesconto(this.financeiro.getValorDesconto());
		this.financeiroQuitacao.setValorJuros(this.financeiro.getValorJuros());
		this.financeiroQuitacao.setValorMulta(this.financeiro.getValorMulta());
		this.financeiroQuitacao.setOutrosAcrescimos(this.financeiro.getOutrosAcrescimos());
		this.financeiroQuitacao.setValorRecebido(this.financeiro.getValorRecebido());
		this.financeiroQuitacao.setValorPagamento(this.financeiro.getValorPagamento());
		this.financeiroQuitacao.setSeqConta(this.financeiro.getSeqConta());
	}

	public void iniciarContasPagar() {
		if ((this.loginController.usuario.getAcFinContasPagar() == null)
				|| (this.loginController.usuario.getAcFinContasPagar().equals("-1"))) {
			this.loginController.mudarPagina("/organizacional/acessonegado.jsf");
			return;
		}

		ClausulaWhere condicao = new ClausulaWhere();
		ClausulaWhere condicaoParceiro = new ClausulaWhere();

		condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "financeiro.origem_LCM", GeneroCondicaoWhere.igual,
				String.valueOf(4), TipoCondicaoWhere.Texto);

		condicaoParceiro
				.AdicionarCondicaoManual("where parceiro.seq_empresa = 61 and parceiro.seq_tipo_parceiro <> 641");

		this.listaConta = this.contaService.listar(this.loginController.usuario.getSeqEmpresa(), "", Situacao.ATIVO);
		this.listaDocumentoFiscal = this.documentoFiscalService.listar(this.loginController.empresa.getSeqEmpresa(), "",
				Situacao.ATIVO);
		this.listaCentroCusto = this.centroCustoService.listar(this.loginController.empresa.getSeqEmpresa(), "",
				Situacao.ATIVO);
		this.listaFormaPagamento = this.formaPagamentoService.listar(this.loginController.empresa.getSeqEmpresa(), "",
				Situacao.ATIVO);
		this.listaUnidadeNegocio = this.unidadeNegocioService.listar(this.loginController.empresa.getSeqEmpresa(), "",
				Situacao.ATIVO);

		this.listaTipoLancamento = this.tipoMovimentoFinanceiroService
				.listarPorOperacao(this.loginController.empresa.getSeqEmpresa(), Situacao.ATIVO, "Débito");
		this.listaFinanceiroCategoria = this.financeiroCategoriaService
				.listar(this.loginController.empresa.getSeqEmpresa(), "", Situacao.ATIVO);
		this.listaTipoDocumento = this.tipoDocumentoService.listarTodos(this.loginController.empresa.getSeqEmpresa(),
				"");

		this.unidadeNegocio = new UnidadeNegocio();

		this.listaParceiro = this.parceiroService.listarParceiroFiltro(condicaoParceiro);
		this.listaFinanceiroOrdemPagamento = this.financeiroService.listarFiltro(condicao);

		if (!this.idFinanceiro.isEmpty()) {
			System.out.println(this.idFinanceiro);
			this.financeiro = ((Financeiro) this.financeiroService
					.listar(this.loginController.getUsuario().getSeqEmpresa(), this.idFinanceiro).get(0));
			selecionar(this.financeiro);
		}
	}

	public void listarContasPagar() {
		this.totalValorBruto = new BigDecimal(0);
		this.totalValorLiquido = new BigDecimal(0);

		ClausulaWhere condicao = new ClausulaWhere();

		condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "financeiro.seq_empresa", GeneroCondicaoWhere.igual,
				String.valueOf(this.loginController.getEmpresa().getSeqEmpresa()), TipoCondicaoWhere.Numero);
		condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.ORIGEM_LCM", GeneroCondicaoWhere.igual,
				String.valueOf("2"), TipoCondicaoWhere.Texto);

		if (isPorDataLancamento() == true) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.data_lancamento",
					GeneroCondicaoWhere.MaiorIgual, this.dataLancamentoInicial, TipoCondicaoWhere.Data);
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.data_lancamento",
					GeneroCondicaoWhere.MenorIgual, this.dataLancamentoFinal, TipoCondicaoWhere.Data);
		}

		if (isPorDataEmissao() == true) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.data_emissao",
					GeneroCondicaoWhere.MaiorIgual, this.dataEmissaoInicial, TipoCondicaoWhere.Data);
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.data_emissao",
					GeneroCondicaoWhere.MenorIgual, this.dataEmissaoFinal, TipoCondicaoWhere.Data);
		}

		if (isPorDataVencimento() == true) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.data_vencimeNto",
					GeneroCondicaoWhere.MaiorIgual, this.dataVencimentoInicial, TipoCondicaoWhere.Data);
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.data_vencimento",
					GeneroCondicaoWhere.MenorIgual, this.dataVencimentoFinal, TipoCondicaoWhere.Data);
		}
		if (isPorDataPagamento() == true) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.data_pagamento",
					GeneroCondicaoWhere.MaiorIgual, this.dataPagamentoInicial, TipoCondicaoWhere.Data);
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.data_pagamento",
					GeneroCondicaoWhere.MenorIgual, this.dataPagamentoFinal, TipoCondicaoWhere.Data);
		}
		if (this.seqParceiroSelecionado != null) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.seq_parceiro", GeneroCondicaoWhere.igual,
					String.valueOf(this.seqParceiroSelecionado), TipoCondicaoWhere.Numero);
		}
		if (this.seqContaSelecionado != null) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.seq_conta", GeneroCondicaoWhere.igual,
					String.valueOf(this.seqContaSelecionado), TipoCondicaoWhere.Numero);
		}
		if (this.statusSelecionado != null) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.status", GeneroCondicaoWhere.igual,
					this.statusSelecionado, TipoCondicaoWhere.Texto);
		}
		if (this.seqCentroCustoSelecionado != null) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.seq_centro_custo",
					GeneroCondicaoWhere.igual, String.valueOf(this.seqCentroCustoSelecionado),
					TipoCondicaoWhere.Numero);
		}

		if (this.seqNaturezaSelecionado != null) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.seq_tipo_movimento",
					GeneroCondicaoWhere.igual, String.valueOf(this.seqNaturezaSelecionado), TipoCondicaoWhere.Numero);
		}
		if (this.seqCategoriaSelecionado != null) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro_categoria.seq_financeiro_categoria",
					GeneroCondicaoWhere.igual, String.valueOf(this.seqCategoriaSelecionado), TipoCondicaoWhere.Numero);
		}

		this.listaFinanceiro = this.financeiroService.listarFiltro(condicao);
		verificarVencimento();

		for (Financeiro pFinanceiro : this.listaFinanceiro) {
			if (pFinanceiro.getValor() != null) {
				this.totalValorBruto = this.totalValorBruto.add(pFinanceiro.getValor()).setScale(2,
						RoundingMode.HALF_EVEN);
			}
			if (pFinanceiro.getValorPagamento() != null) {
				this.totalValorLiquido = this.totalValorLiquido.add(pFinanceiro.getValorPagamento()).setScale(2,
						RoundingMode.HALF_EVEN);
			} else {
				pFinanceiro.setValorPagamento(BigDecimal.ZERO);
			}
		}
	}

	public void postProcessXLS(Object document) {
		HSSFWorkbook wb = (HSSFWorkbook) document;
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFRow header = sheet.getRow(0);

		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setFillForegroundColor((short) 22);
		cellStyle.setFillPattern((short) 1);

		for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
			HSSFCell cell = header.getCell(i);

			cell.setCellStyle(cellStyle);
		}
	}

	public void selecionarOrdemPagamento() {
		Financeiro pFinanceiro = new Financeiro();
		pFinanceiro = (Financeiro) this.financeiroService
				.listar(this.loginController.empresa.getSeqEmpresa(), this.financeiro.getSeqFinanceiroOrdemPagamento())
				.get(0);
		this.financeiro.setValor(pFinanceiro.getValor());
		this.financeiro.setSeqParceiro(pFinanceiro.getSeqParceiro());
		this.financeiro.setSeqCentroCusto(pFinanceiro.getSeqCentroCusto());
		this.financeiro.setSeqTipoMovimento(pFinanceiro.getSeqTipoMovimento());
	}

	public void atualizarTarifaBancaria() {
		if (this.financeiro.getSeqConta() != null) {
			BigDecimal tarifa = this.contaService.buscar(this.financeiro.getSeqConta()).getVlTarifa();
			if (tarifa != null) {
				this.financeiro.setTarifaBancaria(tarifa);
			} else {
				this.financeiro.setTarifaBancaria(BigDecimal.ZERO);
			}
		} else {
			this.financeiro.setTarifaBancaria(null);
		}
	}

	public void enviarParaProximaEtapa(String status) {
		this.financeiro.setEtapa(status);
	}

	public void calcularValorFinal(int index) {
		switch (index) {
		case 0:
			if ((this.financeiroQuitacao.getValorDesconto() != null)
					&& (this.financeiroQuitacao.getValorJuros() != null)
					&& (this.financeiroQuitacao.getValorMulta() != null)
					&& (this.financeiroQuitacao.getOutrosAcrescimos() != null)) {
				this.financeiroQuitacao.setValorPagamento(this.financeiro.getValor()
						.subtract(this.financeiroQuitacao.getValorDesconto())
						.add(this.financeiroQuitacao.getValorJuros()).add(this.financeiroQuitacao.getValorMulta())
						.add(this.financeiroQuitacao.getOutrosAcrescimos()));
			}
			break;
		case 1:
			if ((this.financeiroQuitacao.getValorDesconto() != null)
					&& (this.financeiroQuitacao.getValorJuros() != null)
					&& (this.financeiroQuitacao.getValorMulta() != null)
					&& (this.financeiroQuitacao.getOutrosAcrescimos() != null)) {
				this.financeiroQuitacao.setValorRecebido(this.financeiro.getValor()
						.subtract(this.financeiroQuitacao.getValorDesconto())
						.add(this.financeiroQuitacao.getValorJuros()).add(this.financeiroQuitacao.getValorMulta())
						.add(this.financeiroQuitacao.getOutrosAcrescimos()));
			}
			if ((this.financeiroQuitacao.getTipoQuitacao().equals("PARCIAL"))
					&& (this.financeiroQuitacao.getValorParcial() != null)) {
				this.saldoRestante = this.financeiroQuitacao.getValorRecebido()
						.subtract(this.financeiroQuitacao.getValorParcial());
			}

			break;
		}

	}

	public void iniciarOrdemPagamento() {
		if ((this.loginController.usuario.getAcFinOrdemPagamento() == null)
				|| (this.loginController.usuario.getAcFinOrdemPagamento().equals("-1"))) {
			this.loginController.mudarPagina("/organizacional/acessonegado.jsf");
			return;
		}

		ClausulaWhere condicaoParceiro = new ClausulaWhere();
condicaoParceiro
				.AdicionarCondicaoManual("where parceiro.seq_empresa = 61 and parceiro.seq_tipo_parceiro <> 641");
                
                /*ClausulaWhere condicao = new ClausulaWhere();
		condicao.AdicionarCondicaoManual(
				"where PARCEIRO.SEQ_tipo_parceiro =  701 or PARCEIRO.SEQ_tipo_parceiro = 702 or PARCEIRO.SEQ_tipo_parceiro = 721 or PARCEIRO.SEQ_tipo_parceiro = 0 AND  parceiro.seq_empresa = 61");
                
                                
		this.listaParceiro = this.parceiroService.listarParceiroFiltro(condicao);*/
                
                
                 ClausulaWhere condicao = new ClausulaWhere();
    
                condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "documento.seq_tipo_documento", GeneroCondicaoWhere.igual, String.valueOf("301"), TipoCondicaoWhere.Numero);
                /*condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "documento.seq_documento_etapa", GeneroCondicaoWhere.igual, String.valueOf("282"), TipoCondicaoWhere.Numero);*/
                                
                this.listaDocumento = this.documentoService.listarDocumentoFiltro(condicao);
                
                
                this.listaParceiro = this.parceiroService.listarParceiroFiltro(condicaoParceiro);


		this.listaTipoUnidade = this.tipoUnidadeService.listar(this.loginController.getUsuario().getSeqEmpresa(), "",
				Situacao.ATIVO);
		this.listaCentroCusto = this.centroCustoService.listar(this.loginController.usuario.getSeqEmpresa(), "",
				Situacao.ATIVO);
		this.listaCondicaoPagamento = this.condicaoPagamentoService
				.listar(this.loginController.getEmpresa().getSeqEmpresa(), "", Situacao.ATIVO);

		this.listaTipoLancamento = this.tipoMovimentoFinanceiroService
				.listarPorOperacao(this.loginController.empresa.getSeqEmpresa(), Situacao.ATIVO, "Débito");
		this.listaFinanceiroCategoria = this.financeiroCategoriaService
				.listar(this.loginController.empresa.getSeqEmpresa(), "", Situacao.ATIVO);
	}

	public void listarOrdemPagamento() {
		ClausulaWhere condicao = new ClausulaWhere();

		condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "financeiro.seq_empresa", GeneroCondicaoWhere.igual,
				String.valueOf(this.loginController.getEmpresa().getSeqEmpresa()), TipoCondicaoWhere.Numero);
		condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.ORIGEM_LCM", GeneroCondicaoWhere.igual,
				String.valueOf("4"), TipoCondicaoWhere.Texto);

		if (isPorDataEmissao() == true) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.data_emissao",
					GeneroCondicaoWhere.MaiorIgual, this.dataEmissaoInicial, TipoCondicaoWhere.Data);
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.data_emissao",
					GeneroCondicaoWhere.MenorIgual, this.dataEmissaoFinal, TipoCondicaoWhere.Data);
		}
		if (this.seqParceiroSelecionado != null) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.seq_parceiro", GeneroCondicaoWhere.igual,
					String.valueOf(this.seqParceiroSelecionado), TipoCondicaoWhere.Numero);
		}
		if (this.seqCentroCustoSelecionado != null) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.seq_centro_custo",
					GeneroCondicaoWhere.igual, String.valueOf(this.seqCentroCustoSelecionado),
					TipoCondicaoWhere.Numero);
		}

		if (this.seqNaturezaSelecionado != null) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.seq_tipo_movimento",
					GeneroCondicaoWhere.igual, String.valueOf(this.seqNaturezaSelecionado), TipoCondicaoWhere.Numero);
		}
		if (this.seqCategoriaSelecionado != null) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro_categoria.seq_financeiro_categoria",
					GeneroCondicaoWhere.igual, String.valueOf(this.seqCategoriaSelecionado), TipoCondicaoWhere.Numero);
		}

		this.listaFinanceiro = this.financeiroService.listarFiltro(condicao);
	}

	public void buscarServicoNome() {
		System.out.println("===>" + this.pGrupoServico);
		System.out.println("->" + this.loginController.getUsuario().getSeqEmpresa());
		System.out.println(this.financeiro.getSeqParceiro());
		if (this.pGrupoServico.equals("todos")) {
			this.listaMaterialPreco = this.materialPrecoService.listarTodosServicos(
					this.loginController.getUsuario().getSeqEmpresa(), this.financeiro.getSeqParceiro());
		} else {
			System.out.println("SEQ PARCEIRO" + this.financeiro.getSeqParceiro());
			this.listaMaterialPreco = this.materialPrecoService.listarNomeGrupoServico(
					this.loginController.getUsuario().getSeqEmpresa(), this.financeiro.getSeqParceiro(),
					this.pGrupoServico);
		}
	}

	public void calcularTotalItem() {
		this.financeiroItem
				.setValorTotal(this.financeiroItem.getQuantidade().multiply(this.financeiroItem.getValorUnitario()));
	}

	public void salvarFinanceiroItem() {
		if (this.financeiro.getValor() == null) {
			this.financeiro.setValor(new BigDecimal(0));
		}

		this.financeiro.setValor(this.financeiro.getValor().add(this.financeiroItem.getValorTotal()));

		this.financeiro.getListaFinanceiroItem().add(this.financeiroItem);

		this.financeiroItem = new FinanceiroItem();
		if ((this.financeiro.getMoedaDestino() != null) && (this.financeiro.getMoedaOrigem() != null)) {
			aplicarTaxa();
		}
	}

	public void selecionarFinanceiroItem(FinanceiroItem pFinanceiroItem) {
		this.financeiroItem = pFinanceiroItem;
		this.financeiro.getListaFinanceiroItem().remove(pFinanceiroItem);

		if (this.financeiro.getValor() != BigDecimal.ZERO) {
			this.financeiro.setValor(this.financeiro.getValor().subtract(pFinanceiroItem.getValorTotal()));
		}
	}

	public void removerFinanceiroItem(FinanceiroItem pFinanceiroItem) {
		this.financeiro.getListaFinanceiroItem().remove(pFinanceiroItem);

		if (this.financeiro.getValor() != BigDecimal.ZERO) {
			this.financeiro.setValor(this.financeiro.getValor().subtract(pFinanceiroItem.getValorTotal()));
		}
	}

	public void listarDetalheParceiro() {
		if (!this.materialPrecoService
				.listarPorParceiro(this.loginController.getUsuario().getSeqEmpresa(), this.financeiro.getSeqParceiro())
				.isEmpty()) {
			this.listaMaterialReferencia = this.materialPrecoService.listarGrupoPorParceiro(
					this.loginController.getUsuario().getSeqEmpresa(), this.financeiro.getSeqParceiro());
		} else {
			this.listaMaterialReferencia.clear();
		}
		this.listaParceiroContato = this.parceiroContatoService.listarPorParceiro(this.financeiro.getSeqParceiro(),
				Situacao.TODOS);
	}

	public void imprimir() throws IOException, JRException {
		Conexao conexao = new Conexao();
		Connection conn = Conexao.getConnection();
		Util util = new Util();
		/*Financeiro pFinanceiro = new Financeiro();*/

		HashMap parametro = new HashMap();
		parametro.put("seqFinanceiro", Integer.valueOf(this.financeiro.getSeqFinanceiro()));

		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.responseComplete();
		ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();

		JasperPrint jasperPrint = new JasperPrint();

		jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/relatorio/aws/PO/PO.jasper"), parametro,
				conn);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
		exporter.exportReport();
		byte[] bytes = baos.toByteArray();

		if ((bytes != null) && (bytes.length > 0)) {
			HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
			response.setContentType("application/pdf");
			response.setHeader("Content-disposition", "inline; filename=\"Certificado.pdf\"");
			response.setContentLength(bytes.length);
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(bytes, 0, bytes.length);
			outputStream.flush();
			outputStream.close();
		}
	}

	public void aplicarTaxa() {
		if (!this.financeiro.getMoedaDestino().equals(this.financeiro.getMoedaOrigem())) {
			if (this.financeiro.getTaxaCambio() == null) {
				this.financeiro.setValorConvertido(null);
			} else {
				this.financeiro
						.setValorConvertido(this.financeiro.getValor().multiply(this.financeiro.getTaxaCambio()));
			}
		} else {
			this.financeiro.setValorConvertido(null);
			this.financeiro.setTaxaCambio(null);
		}
	}

	public void gerarContasPagar() {
		Financeiro pFinanceiro = new Financeiro();
		pFinanceiro.setSeqFinanceiro(null);
		pFinanceiro.setSeqParceiro(this.financeiro.getSeqParceiro());
		pFinanceiro.setSeqUsuario(this.financeiro.getSeqUsuario());
		pFinanceiro.setSeqEmpresa(this.loginController.empresa.getSeqEmpresa());
		pFinanceiro.setSeqTipoMovimento(this.financeiro.getSeqTipoMovimento());
		pFinanceiro.setSeqCentroCusto(this.financeiro.getSeqCentroCusto());
		pFinanceiro.setValor(this.financeiro.getValor());
		pFinanceiro.setValorConvertido(this.financeiro.getValorConvertido());
		pFinanceiro.setDataEmissao(new Date());
		pFinanceiro.setOrigemLCM("2");
		pFinanceiro.setOperacao("DÉBITO");
		pFinanceiro.setEtapa("A VENCER");
		pFinanceiro.setOutrasInformacoes(
				"Gerado a partir da Ordem de Pagamento nº" + this.financeiro.getSeqFinanceiro() + ".");
		pFinanceiro.setOcorrencias(Integer.valueOf(0));
		pFinanceiro.setIntervaloNumero(Integer.valueOf(0));
		pFinanceiro.setParcelaFim(Integer.valueOf(0));
		pFinanceiro.setParcelaInicio(Integer.valueOf(0));

		pFinanceiro = this.financeiroService.salvar(pFinanceiro);

		FacesContext ctx = FacesContext.getCurrentInstance();
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(ctx.getExternalContext().getRequestContextPath() + "/financeiro/contas_pagar.jsf?id="
							+ pFinanceiro.getSeqFinanceiro());
		} catch (IOException ex) {
			Logger.getLogger(FinanceiroController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
        

	public void listarContaCorrente() {
		this.totalCredito = new BigDecimal(0);
		this.totalDebito = new BigDecimal(0);
		this.saldoInicial = new BigDecimal(0);
		this.saldoFinal = new BigDecimal(0);

		ClausulaWhere condicao = new ClausulaWhere();

		condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "financeiro.seq_empresa", GeneroCondicaoWhere.igual,
				String.valueOf(this.loginController.getEmpresa().getSeqEmpresa()), TipoCondicaoWhere.Numero);

		condicao.AdicionarCondicaoManual(
				" and (financeiro.ORIGEM_LCM = '1' or financeiro.ORIGEM_LCM = '2' or financeiro.ORIGEM_LCM = '5') ");
		condicao.AdicionarCondicaoManual(
				" and (financeiro.status = 'QUITADO' or financeiro.status = 'PARCIALMENTE QUITADO') ");

		if (isPorDataPagamento() == true) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.data_pagamento",
					GeneroCondicaoWhere.MaiorIgual, this.dataPagamentoInicial, TipoCondicaoWhere.Data);
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.data_pagamento",
					GeneroCondicaoWhere.MenorIgual, this.dataPagamentoFinal, TipoCondicaoWhere.Data);
		}

		if (this.seqContaSelecionado != null) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.seq_conta", GeneroCondicaoWhere.igual,
					String.valueOf(this.seqContaSelecionado), TipoCondicaoWhere.Numero);
		}
		if (this.seqDocumentoFiscalSelecionado != null) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.seq_documento_fiscal",
					GeneroCondicaoWhere.igual, String.valueOf(this.seqDocumentoFiscalSelecionado),
					TipoCondicaoWhere.Numero);
		}
		if (this.operacaoSelecionada != null) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.operacao", GeneroCondicaoWhere.igual,
					String.valueOf(this.operacaoSelecionada), TipoCondicaoWhere.Texto);
		}
		if (this.seqNaturezaSelecionado != null) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.seq_tipo_movimento",
					GeneroCondicaoWhere.igual, String.valueOf(this.seqNaturezaSelecionado), TipoCondicaoWhere.Numero);
		}

		this.listaFinanceiro = this.financeiroService.listarFiltro(condicao);

		for (Financeiro contaCorrente : this.listaFinanceiro) {
			if ((this.seqContaSelecionado != null) && (contaCorrente.getContaSaldo() != null)) {
				this.saldoInicial = contaCorrente.getContaSaldo();
			}
			if (contaCorrente.getOrigemLCM().equals("1")) {
				this.totalCredito = this.totalCredito.add(contaCorrente.getValorRecebido());
			} else if (contaCorrente.getOrigemLCM().equals("2")) {
				this.totalDebito = this.totalDebito.add(contaCorrente.getValorPagamento());
			}
		}
		this.saldoFinal = this.saldoInicial.add(this.totalCredito).subtract(this.totalDebito);
	}

	public void iniciarContaCorrente() {
		if ((this.loginController.usuario.getAcFinContaCorrente() == null)
				|| (this.loginController.usuario.getAcFinContaCorrente().equals("-1"))) {
			this.loginController.mudarPagina("/organizacional/acessonegado.jsf");
			return;
		}
		this.listaDocumentoFiscal = this.documentoFiscalService.listar(this.loginController.empresa.getSeqEmpresa(), "",
				Situacao.ATIVO);

		this.listaConta = this.contaService.listar(this.loginController.usuario.getSeqEmpresa(), "", Situacao.ATIVO);

		this.listaTipoLancamento = this.tipoMovimentoFinanceiroService
				.listar(this.loginController.empresa.getSeqEmpresa(), "", Situacao.ATIVO);

		this.listaFinanceiroCategoria = this.financeiroCategoriaService
				.listar(this.loginController.empresa.getSeqEmpresa(), "", Situacao.ATIVO);
	}

	public void listarPrestacaoContas() {
		ClausulaWhere condicao = new ClausulaWhere();

		condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "financeiro.seq_empresa", GeneroCondicaoWhere.igual,
				String.valueOf(this.loginController.getEmpresa().getSeqEmpresa()), TipoCondicaoWhere.Numero);
		condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.ORIGEM_LCM", GeneroCondicaoWhere.igual,
				String.valueOf("6"), TipoCondicaoWhere.Texto);

		if (isPorDataLancamento() == true) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.data_cadastro",
					GeneroCondicaoWhere.MaiorIgual, this.dataLancamentoInicial, TipoCondicaoWhere.Data);
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.data_cadastro",
					GeneroCondicaoWhere.MenorIgual, this.dataLancamentoFinal, TipoCondicaoWhere.Data);
		}
		if (isPorDataPeriodoInicial() == true) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.data_periodo_inicial",
					GeneroCondicaoWhere.MaiorIgual, this.dataPeriodoInicialInicial, TipoCondicaoWhere.Data);
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.data_periodo_inicial",
					GeneroCondicaoWhere.MenorIgual, this.dataPeriodoInicialFinal, TipoCondicaoWhere.Data);
		}

		if (isPorDataPeriodoFinal() == true) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.data_periodo_final",
					GeneroCondicaoWhere.MaiorIgual, this.dataPeriodoFinalInicial, TipoCondicaoWhere.Data);
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.data_periodo_final",
					GeneroCondicaoWhere.MenorIgual, this.dataPeriodoFinalFinal, TipoCondicaoWhere.Data);
		}

		if (this.seqParceiroSelecionado != null) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.seq_parceiro", GeneroCondicaoWhere.igual,
					String.valueOf(this.seqParceiroSelecionado), TipoCondicaoWhere.Texto);
		}
		if (this.seqColaboradorSelecionado != null) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.seq_colaborador",
					GeneroCondicaoWhere.igual, String.valueOf(this.seqColaboradorSelecionado),
					TipoCondicaoWhere.Numero);
		}

		this.listaFinanceiro = this.financeiroService.listarFiltro(condicao);
	}

	public void iniciarPrestacaoContas() {
		ClausulaWhere condicao = new ClausulaWhere();

		condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "documento.seq_tipo_documento",
				GeneroCondicaoWhere.igual, String.valueOf("301"), TipoCondicaoWhere.Numero);
		/*condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "documento.seq_documento_etapa",
				GeneroCondicaoWhere.igual, String.valueOf("282"), TipoCondicaoWhere.Numero); ALTERADO POR ROBERTO  */ 

		this.listaDocumento = this.documentoService.listarDocumentoFiltro(condicao);

		this.listaDocumentoFiscal = this.documentoFiscalService.listar(this.loginController.empresa.getSeqEmpresa(), "",
				Situacao.ATIVO);
		this.listaConta = this.contaService.listar(this.loginController.usuario.getSeqEmpresa(), "", Situacao.ATIVO);
		this.listaTipoLancamento = this.tipoMovimentoFinanceiroService
				.listar(this.loginController.empresa.getSeqEmpresa(), "", Situacao.ATIVO);
		this.listaFinanceiroCategoria = this.financeiroCategoriaService
				.listar(this.loginController.empresa.getSeqEmpresa(), "", Situacao.ATIVO);
		this.listaParceiro = this.parceiroService.listarParceiro(this.loginController.usuario.getSeqUsuario(), "");
		this.listaColaborador = this.colaboradorService.listar(this.loginController.empresa.getSeqEmpresa(), "",
				Situacao.ATIVO);
	}

	public void listarEquipEmbarc() {
		this.listaEquipamento = this.equipamentoService.listarPorParceiro(
				this.loginController.getEmpresa().getSeqEmpresa(), this.financeiro.getSeqParceiro(), Situacao.ATIVO);
		this.listaNvEmbarcacaoParceiro = this.nvEmbarcacaoParceiroService
				.listarPorParceiro(this.financeiro.getSeqParceiro());
	}

	public void salvarFinanceiroItemPC() {
		this.listaFinanceiroItemPc.add(this.financeiroItemPc);
		this.financeiroItemPc = new FinanceiroItemPc();
	}

	public void selecionarDocumentoFiscal() {
		for (DocumentoFiscal df : this.listaDocumentoFiscal) {
			if (df.getSeqDocumentoFiscal().equals(this.financeiroItemPc.getSeqDocumentoFiscal())) {
				this.financeiroItemPc.setNomeDocumentoFiscal(df.getNome());
			}
		}
	}

	public void selecionarFinanceiroItemPC(FinanceiroItemPc pFinanceiroItemPc) {
		this.financeiroItemPc = pFinanceiroItemPc;
		this.listaFinanceiroItemPc.remove(pFinanceiroItemPc);
	}

	public void removerFinanceiroItemPC(FinanceiroItemPc pFinanceiroItemPc) {
		this.listaFinanceiroItemPc.remove(pFinanceiroItemPc);
		if (pFinanceiroItemPc.getSeqFinanceiroItemPc() != null) {
			this.listaFinanceiroItemPcDeletado.add(pFinanceiroItemPc);
		}
	}

	public void imprimirPrestacaoContas() throws IOException, JRException {
		Conexao conexao = new Conexao();
		Connection conn = Conexao.getConnection();
		Util util = new Util();
		Financeiro pFinanceiro = new Financeiro();

		HashMap parametro = new HashMap();
		parametro.put("seqFinanceiro", Integer.valueOf(this.financeiro.getSeqFinanceiro()));

		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.responseComplete();
		ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();

		JasperPrint jasperPrint = new JasperPrint();

		jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/relatorio/aws/PC/PC.jasper"), parametro,
				conn);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
		exporter.exportReport();
		byte[] bytes = baos.toByteArray();

		if ((bytes != null) && (bytes.length > 0)) {
			HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
			response.setContentType("application/pdf");
			response.setHeader("Content-disposition", "inline; filename=\"PrestacaoContas.pdf\"");
			response.setContentLength(bytes.length);
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(bytes, 0, bytes.length);
			outputStream.flush();
			outputStream.close();
		}
	}

	public void listarNotaDebito() {
		ClausulaWhere condicao = new ClausulaWhere();

		condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "financeiro.seq_empresa", GeneroCondicaoWhere.igual,
				String.valueOf(this.loginController.getEmpresa().getSeqEmpresa()), TipoCondicaoWhere.Numero);
		condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.ORIGEM_LCM", GeneroCondicaoWhere.igual,
				String.valueOf("7"), TipoCondicaoWhere.Texto);

		if (isPorDataLancamento() == true) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.data_cadastro",
					GeneroCondicaoWhere.MaiorIgual, this.dataLancamentoInicial, TipoCondicaoWhere.Data);
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.data_cadastro",
					GeneroCondicaoWhere.MenorIgual, this.dataLancamentoFinal, TipoCondicaoWhere.Data);
		}
		if (isPorDataVencimento() == true) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.data_vencimento",
					GeneroCondicaoWhere.MaiorIgual, this.dataVencimentoInicial, TipoCondicaoWhere.Data);
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.data_vencimento",
					GeneroCondicaoWhere.MenorIgual, this.dataVencimentoFinal, TipoCondicaoWhere.Data);
		}

		if (this.seqParceiroSelecionado != null) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.seq_parceiro", GeneroCondicaoWhere.igual,
					String.valueOf(this.seqParceiroSelecionado), TipoCondicaoWhere.Texto);
		}
		if (this.seqColaboradorSelecionado != null) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.seq_colaborador",
					GeneroCondicaoWhere.igual, String.valueOf(this.seqColaboradorSelecionado),
					TipoCondicaoWhere.Numero);
		}

		this.listaFinanceiro = this.financeiroService.listarFiltro(condicao);
	}

	public void iniciarNotaDebito() {
		ClausulaWhere condicao = new ClausulaWhere();

		condicao.AdicionarCondicaoManual(
				" where (documento.seq_documento_etapa = 508 or documento.seq_documento_etapa = 505 or documento.seq_documento_etapa = 502 or documento.seq_documento_etapa = 441 or documento.seq_documento_etapa =268 or documento.seq_documento_etapa =265 or documento.seq_documento_etapa =262 or documento.seq_documento_etapa = 241 ) and documento.seq_empresa = 61");

		this.listaDocumento = this.documentoService.listarDocumentoFiltro(condicao);

		this.listaConta = this.contaService.listar(this.loginController.usuario.getSeqEmpresa(), "", Situacao.ATIVO);

		this.listaParceiro = this.parceiroService.listarParceiro(this.loginController.usuario.getSeqUsuario(), "");
		this.listaColaborador = this.colaboradorService.listar(this.loginController.empresa.getSeqEmpresa(), "",
				Situacao.ATIVO);
		this.listaMaterial = this.materialService.listarPorReferencia(this.loginController.empresa.getSeqEmpresa(),
				"Reembolsos de Despesas", Situacao.ATIVO);
	}

	public void imprimirNotaDebito() throws IOException, JRException {
		Conexao conexao = new Conexao();
		Connection conn = Conexao.getConnection();
		Util util = new Util();
		Financeiro pFinanceiro = new Financeiro();

		CurrencyWriter cw = CurrencyWriter.getInstance();
		String extenso = cw.write(this.financeiro.getValor());
		System.out.println(this.financeiro.getValor());
		System.out.println(extenso);

		HashMap parametro = new HashMap();
		parametro.put("SeqFinanceiro", Integer.valueOf(this.financeiro.getSeqFinanceiro()));
		parametro.put("valorPorExtenso", cw.write(this.financeiro.getValor()));

		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.responseComplete();
		ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();

		JasperPrint jasperPrint = new JasperPrint();
		jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/relatorio/aws/ND/ND.jasper"), parametro,
				conn);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
		exporter.exportReport();
		byte[] bytes = baos.toByteArray();

		if ((bytes != null) && (bytes.length > 0)) {
			HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
			response.setContentType("application/pdf");
			response.setHeader("Content-disposition", "inline; filename=\"NotaDebito.pdf\"");
			response.setContentLength(bytes.length);
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(bytes, 0, bytes.length);
			outputStream.flush();
			outputStream.close();
		}
	}

	public void buscarDadosProposta() {
		this.documento = this.documentoService.buscarPorID(this.loginController.empresa.getSeqEmpresa(),
				this.financeiro.getSeqDocumento());

		this.financeiro.setSeqParceiro(this.documento.getSeqParceiro());
		this.financeiro.setSeqConta(this.documento.getSeqConta());
		this.financeiro.setSeqColaborador(this.documento.getSeqAssColaborador());
	}
        
    public void aprovar() {
        this.financeiro.setAprovador(this.loginController.getUsuario().getUsuario());

        this.financeiro = this.financeiroService.aprovar(this.financeiro);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PO Aprovada.", ""));
        return;
    }
        
        
        public void upload() {
		String id1 = this.financeiro.getOrigemLCM().replace("/", "-");
                this.upload.setSeqFinanceiro(this.financeiro.getSeqFinanceiro());
		this.upload.setOrigem("FIN" + id1 + "-" + this.financeiro.getSeqFinanceiro());
		this.upload.setSeqEmpresa(this.loginController.empresa.getSeqEmpresa());
		this.upload.setSeqUsuario(this.loginController.usuario.getSeqUsuario());
		this.uploadController.upload(this.file, this.upload);
                this.listaUpload = this.uploadService.listar(this.loginController.empresa.getSeqEmpresa(),this.financeiro.getSeqFinanceiro());
		this.upload = new Upload();
                /*this.idFinanceiro = this.financeiro.getSeqFinanceiro();*/
                
	}
 
 	public void download(Upload pUpload) {
		if (pUpload.getNomeArquivo().contains("pdf")) {
			visualizar(pUpload);
		} else {
			this.fileDownload = this.uploadController.download(pUpload);
		}
	}

	public void visualizar(Upload pUpload) {
		try {
			FileInputStream s = new FileInputStream(pUpload.getUrl());

			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			byte[] buf = new byte['Ѐ'];
			try {
				int readNum;
				while ((readNum = s.read(buf)) != -1) {
					bos.write(buf, 0, readNum);
					System.out.println("read " + readNum + " bytes,");
				}
			} catch (IOException ex) {
				Logger.getLogger(DocumentoController.class.getName()).log(Level.SEVERE, null, ex);
			}

			byte[] bytes = bos.toByteArray();

			HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
					.getResponse();

			response.setHeader("Content-Disposition", "inline; filename=" + pUpload.getNomeArquivo());
			OutputStream output = response.getOutputStream();
			output.write(bytes);
			response.flushBuffer();
			FacesContext.getCurrentInstance().responseComplete();
		} catch (IOException ex) {
			Logger.getLogger(DocumentoController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void removerAnexo(Upload pUpload) {
		this.uploadController.deletar(pUpload);
		this.listaUpload = this.uploadService.listar(this.loginController.empresa.getSeqEmpresa(),this.financeiro.getSeqFinanceiro());
	} 
    

	public LoginController getLoginController() {
		return this.loginController;
	}

	public void setLoginController(LoginController loginController) {
		this.loginController = loginController;
	}

	public FinanceiroService getFinanceiroService() {
		return this.financeiroService;
	}

	public void setFinanceiroService(FinanceiroService financeiroService) {
		this.financeiroService = financeiroService;
	}

	public Financeiro getFinanceiro() {
		return this.financeiro;
	}

	public void setFinanceiro(Financeiro financeiro) {
		this.financeiro = financeiro;
	}

	public List<Financeiro> getListaFinanceiro() {
		return this.listaFinanceiro;
	}

	public void setListaFinanceiro(List<Financeiro> listaFinanceiro) {
		this.listaFinanceiro = listaFinanceiro;
	}

	public String getPesquisa() {
		return this.pesquisa;
	}

	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}

	public Integer getTela() {
		return this.tela;
	}

	public void setTela(Integer tela) {
		this.tela = tela;
	}

	public TipoMovimentoFinanceiro getTipoMovimentoFinanceiro() {
		return this.tipoMovimentoFinanceiro;
	}

	public void setTipoMovimentoFinanceiro(TipoMovimentoFinanceiro tipoMovimentoFinanceiro) {
		this.tipoMovimentoFinanceiro = tipoMovimentoFinanceiro;
	}

	public TipoMovimentoFinanceiroService getTipoMovimentoFinanceiroService() {
		return this.tipoMovimentoFinanceiroService;
	}

	public List<TipoMovimentoFinanceiro> getListaTipoLancamento() {
		return this.listaTipoLancamento;
	}

	public void setListaTipoLancamento(List<TipoMovimentoFinanceiro> listaTipoLancamento) {
		this.listaTipoLancamento = listaTipoLancamento;
	}

	public int getOrigem() {
		return this.origem;
	}

	public void setOrigem(int origem) {
		this.origem = origem;
	}

	public void setTipoMovimentoFinanceiroService(TipoMovimentoFinanceiroService tipoMovimentoFinanceiroService) {
		this.tipoMovimentoFinanceiroService = tipoMovimentoFinanceiroService;
	}

	public List<TipoDocumento> getListaTipoDocumento() {
		return this.listaTipoDocumento;
	}

	public void setListaTipoDocumento(List<TipoDocumento> listaTipoDocumento) {
		this.listaTipoDocumento = listaTipoDocumento;
	}

	public TipoDocumentoService getTipoDocumentoService() {
		return this.tipoDocumentoService;
	}

	public void setTipoDocumentoService(TipoDocumentoService tipoDocumentoService) {
		this.tipoDocumentoService = tipoDocumentoService;
	}

	public Documento getDocumento() {
		return this.documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public List<Documento> getListaDocumento() {
		return this.listaDocumento;
	}

	public void setListaDocumento(List<Documento> listaDocumento) {
		this.listaDocumento = listaDocumento;
	}

	public DocumentoService getDocumentoService() {
		return this.documentoService;
	}

	public void setDocumentoService(DocumentoService documentoService) {
		this.documentoService = documentoService;
	}

	public Parceiro getParceiro() {
		return this.parceiro;
	}

	public void setParceiro(Parceiro parceiro) {
		this.parceiro = parceiro;
	}

	public List<Parceiro> getListaParceiro() {
		return this.listaParceiro;
	}

	public void setListaParceiro(List<Parceiro> listaParceiro) {
		this.listaParceiro = listaParceiro;
	}

	public ParceiroService getParceiroService() {
		return this.parceiroService;
	}

	public void setParceiroService(ParceiroService parceiroService) {
		this.parceiroService = parceiroService;
	}

	public Colaborador getColaborador() {
		return this.colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public List<Colaborador> getListaColaborador() {
		return this.listaColaborador;
	}

	public void setListaColaborador(List<Colaborador> listaColaborador) {
		this.listaColaborador = listaColaborador;
	}

	public ColaboradorService getColaboradorService() {
		return this.colaboradorService;
	}

	public void setColaboradorService(ColaboradorService colaboradorService) {
		this.colaboradorService = colaboradorService;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public UnidadeNegocio getUnidadeNegocio() {
		return this.unidadeNegocio;
	}

	public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public UnidadeNegocioService getUnidadeNegocioService() {
		return this.unidadeNegocioService;
	}

	public void setUnidadeNegocioService(UnidadeNegocioService unidadeNegocioService) {
		this.unidadeNegocioService = unidadeNegocioService;
	}

	public List<UnidadeNegocio> getListaUnidadeNegocio() {
		return this.listaUnidadeNegocio;
	}

	public void setListaUnidadeNegocio(List<UnidadeNegocio> listaUnidadeNegocio) {
		this.listaUnidadeNegocio = listaUnidadeNegocio;
	}

	public String getCodigoOrdemServico() {
		return this.codigoOrdemServico;
	}

	public void setCodigoOrdemServico(String codigoOrdemServico) {
		this.codigoOrdemServico = codigoOrdemServico;
	}

	public List<Aliquota> getListaISSQN() {
		return this.listaISSQN;
	}

	public void setListaISSQN(List<Aliquota> listaISSQN) {
		this.listaISSQN = listaISSQN;
	}

	public List<Aliquota> getListaIRRF() {
		return this.listaIRRF;
	}

	public void setListaIRRF(List<Aliquota> listaIRRF) {
		this.listaIRRF = listaIRRF;
	}

	public List<Aliquota> getListaCSLL() {
		return this.listaCSLL;
	}

	public void setListaCSLL(List<Aliquota> listaCSLL) {
		this.listaCSLL = listaCSLL;
	}

	public List<Aliquota> getListaPIS() {
		return this.listaPIS;
	}

	public void setListaPIS(List<Aliquota> listaPIS) {
		this.listaPIS = listaPIS;
	}

	public List<Aliquota> getListaCOFINS() {
		return this.listaCOFINS;
	}

	public void setListaCOFINS(List<Aliquota> listaCOFINS) {
		this.listaCOFINS = listaCOFINS;
	}

	public AliquotaService getAliquotaService() {
		return this.aliquotaService;
	}

	public void setAliquotaService(AliquotaService aliquotaService) {
		this.aliquotaService = aliquotaService;
	}

	public BigDecimal getPercentual() {
		return this.percentual;
	}

	public void setPercentual(BigDecimal percentual) {
		this.percentual = percentual;
	}

	public BigDecimal getValorISSQN() {
		return this.valorISSQN;
	}

	public void setValorISSQN(BigDecimal valorISSQN) {
		this.valorISSQN = valorISSQN;
	}

	public BigDecimal getValorCSLL() {
		return this.valorCSLL;
	}

	public void setValorCSLL(BigDecimal valorCSLL) {
		this.valorCSLL = valorCSLL;
	}

	public BigDecimal getValorIRRF() {
		return this.valorIRRF;
	}

	public void setValorIRRF(BigDecimal valorIRRF) {
		this.valorIRRF = valorIRRF;
	}

	public BigDecimal getValorPIS() {
		return this.valorPIS;
	}

	public void setValorPIS(BigDecimal valorPIS) {
		this.valorPIS = valorPIS;
	}

	public BigDecimal getValorCOFINS() {
		return this.valorCOFINS;
	}

	public void setValorCOFINS(BigDecimal valorCOFINS) {
		this.valorCOFINS = valorCOFINS;
	}

	public List<Documento> getListaTodosDocumentos() {
		return this.listaTodosDocumentos;
	}

	public void setListaTodosDocumentos(List<Documento> listaTodosDocumentos) {
		this.listaTodosDocumentos = listaTodosDocumentos;
	}

	public boolean getId() {
		return this.id;
	}

	public void setId(boolean id) {
		this.id = id;
	}

	public boolean isVincularDocumento() {
		return this.vincularDocumento;
	}

	public void setVincularDocumento(boolean vincularDocumento) {
		this.vincularDocumento = vincularDocumento;
	}

	public CentroCustoService getCentroCustoService() {
		return this.centroCustoService;
	}

	public void setCentroCustoService(CentroCustoService centroCustoService) {
		this.centroCustoService = centroCustoService;
	}

	public List<CentroCusto> getListaCentroCusto() {
		return this.listaCentroCusto;
	}

	public void setListaCentroCusto(List<CentroCusto> listaCentroCusto) {
		this.listaCentroCusto = listaCentroCusto;
	}

	public DocumentoFiscalService getDocumentoFiscalService() {
		return this.documentoFiscalService;
	}

	public void setDocumentoFiscalService(DocumentoFiscalService documentoFiscalService) {
		this.documentoFiscalService = documentoFiscalService;
	}

	public List<DocumentoFiscal> getListaDocumentoFiscal() {
		return this.listaDocumentoFiscal;
	}

	public void setListaDocumentoFiscal(List<DocumentoFiscal> listaDocumentoFiscal) {
		this.listaDocumentoFiscal = listaDocumentoFiscal;
	}

	public boolean isVincularParceiro() {
		return this.vincularParceiro;
	}

	public void setVincularParceiro(boolean vincularParceiro) {
		this.vincularParceiro = vincularParceiro;
	}

	public String getSeqProposta() {
		return this.seqProposta;
	}

	public void setSeqProposta(String seqProposta) {
		this.seqProposta = seqProposta;
	}

	public Documento getDocProposta() {
		return this.docProposta;
	}

	public void setDocProposta(Documento docProposta) {
		this.docProposta = docProposta;
	}

	public String getSeqCentroCustoSelecionado() {
		return this.seqCentroCustoSelecionado;
	}

	public void setSeqCentroCustoSelecionado(String seqCentroCustoSelecionado) {
		this.seqCentroCustoSelecionado = seqCentroCustoSelecionado;
	}

	public String getSeqDocumentoFiscalSelecionado() {
		return this.seqDocumentoFiscalSelecionado;
	}

	public void setSeqDocumentoFiscalSelecionado(String seqDocumentoFiscalSelecionado) {
		this.seqDocumentoFiscalSelecionado = seqDocumentoFiscalSelecionado;
	}

	public boolean isPorDataLancamento() {
		return this.porDataLancamento;
	}

	public void setPorDataLancamento(boolean porDataLancamento) {
		this.porDataLancamento = porDataLancamento;
	}

	public boolean isPorDataEmissao() {
		return this.porDataEmissao;
	}

	public void setPorDataEmissao(boolean porDataEmissao) {
		this.porDataEmissao = porDataEmissao;
	}

	public boolean isPorDataVencimento() {
		return this.porDataVencimento;
	}

	public void setPorDataVencimento(boolean porDataVencimento) {
		this.porDataVencimento = porDataVencimento;
	}

	public String getDataLancamentoInicial() {
		return this.dataLancamentoInicial;
	}

	public void setDataLancamentoInicial(String dataLancamentoInicial) {
		this.dataLancamentoInicial = dataLancamentoInicial;
	}

	public String getDataLancamentoFinal() {
		return this.dataLancamentoFinal;
	}

	public void setDataLancamentoFinal(String dataLancamentoFinal) {
		this.dataLancamentoFinal = dataLancamentoFinal;
	}

	public String getDataEmissaoInicial() {
		return this.dataEmissaoInicial;
	}

	public void setDataEmissaoInicial(String dataEmissaoInicial) {
		this.dataEmissaoInicial = dataEmissaoInicial;
	}

	public String getDataEmissaoFinal() {
		return this.dataEmissaoFinal;
	}

	public void setDataEmissaoFinal(String dataEmissaoFinal) {
		this.dataEmissaoFinal = dataEmissaoFinal;
	}

	public String getDataVencimentoInicial() {
		return this.dataVencimentoInicial;
	}

	public void setDataVencimentoInicial(String dataVencimentoInicial) {
		this.dataVencimentoInicial = dataVencimentoInicial;
	}

	public String getDataVencimentoFinal() {
		return this.dataVencimentoFinal;
	}

	public void setDataVencimentoFinal(String dataVencimentoFinal) {
		this.dataVencimentoFinal = dataVencimentoFinal;
	}

	public FormaPagamentoService getFormaPagamentoService() {
		return this.formaPagamentoService;
	}

	public void setFormaPagamentoService(FormaPagamentoService formaPagamentoService) {
		this.formaPagamentoService = formaPagamentoService;
	}

	public List<FormaPagamento> getListaFormaPagamento() {
		return this.listaFormaPagamento;
	}

	public void setListaFormaPagamento(List<FormaPagamento> listaFormaPagamento) {
		this.listaFormaPagamento = listaFormaPagamento;
	}

	public boolean isPorDataPagamento() {
		return this.porDataPagamento;
	}

	public void setPorDataPagamento(boolean porDataPagamento) {
		this.porDataPagamento = porDataPagamento;
	}

	public String getDataPagamentoInicial() {
		return this.dataPagamentoInicial;
	}

	public void setDataPagamentoInicial(String dataPagamentoInicial) {
		this.dataPagamentoInicial = dataPagamentoInicial;
	}

	public String getDataPagamentoFinal() {
		return this.dataPagamentoFinal;
	}

	public void setDataPagamentoFinal(String dataPagamentoFinal) {
		this.dataPagamentoFinal = dataPagamentoFinal;
	}

	public String getSeqParceiroSelecionado() {
		return this.seqParceiroSelecionado;
	}

	public void setSeqParceiroSelecionado(String seqParceiroSelecionado) {
		this.seqParceiroSelecionado = seqParceiroSelecionado;
	}

	public TipoParceiroService getTipoParceiroService() {
		return this.tipoParceiroService;
	}

	public void setTipoParceiroService(TipoParceiroService tipoParceiroService) {
		this.tipoParceiroService = tipoParceiroService;
	}

	public List<TipoParceiro> getListaTipoParceiro() {
		return this.listaTipoParceiro;
	}

	public void setListaTipoParceiro(List<TipoParceiro> listaTipoParceiro) {
		this.listaTipoParceiro = listaTipoParceiro;
	}

	public String getSeqTipoParceiroSelecionado() {
		return this.seqTipoParceiroSelecionado;
	}

	public void setSeqTipoParceiroSelecionado(String seqTipoParceiroSelecionado) {
		this.seqTipoParceiroSelecionado = seqTipoParceiroSelecionado;
	}

	public String getStatusSelecionado() {
		return this.statusSelecionado;
	}

	public void setStatusSelecionado(String statusSelecionado) {
		this.statusSelecionado = statusSelecionado;
	}

	public String getpGrupoServico() {
		return this.pGrupoServico;
	}

	public void setpGrupoServico(String pGrupoServico) {
		this.pGrupoServico = pGrupoServico;
	}

	public MaterialPrecoService getMaterialPrecoService() {
		return this.materialPrecoService;
	}

	public void setMaterialPrecoService(MaterialPrecoService materialPrecoService) {
		this.materialPrecoService = materialPrecoService;
	}

	public List<MaterialPreco> getListaMaterialReferencia() {
		return this.listaMaterialReferencia;
	}

	public void setListaMaterialReferencia(List<MaterialPreco> listaMaterialReferencia) {
		this.listaMaterialReferencia = listaMaterialReferencia;
	}

	public List<MaterialPreco> getListaMaterialPreco() {
		return this.listaMaterialPreco;
	}

	public void setListaMaterialPreco(List<MaterialPreco> listaMaterialPreco) {
		this.listaMaterialPreco = listaMaterialPreco;
	}

	public MaterialPreco getMaterialPreco() {
		return this.materialPreco;
	}

	public void setMaterialPreco(MaterialPreco materialPreco) {
		this.materialPreco = materialPreco;
	}

	public TipoUnidadeService getTipoUnidadeService() {
		return this.tipoUnidadeService;
	}

	public void setTipoUnidadeService(TipoUnidadeService tipoUnidadeService) {
		this.tipoUnidadeService = tipoUnidadeService;
	}

	public List<TipoUnidade> getListaTipoUnidade() {
		return this.listaTipoUnidade;
	}

	public void setListaTipoUnidade(List<TipoUnidade> listaTipoUnidade) {
		this.listaTipoUnidade = listaTipoUnidade;
	}

	public List<ParceiroContato> getListaParceiroContato() {
		return this.listaParceiroContato;
	}

	public void setListaParceiroContato(List<ParceiroContato> listaParceiroContato) {
		this.listaParceiroContato = listaParceiroContato;
	}

	public ParceiroContatoService getParceiroContatoService() {
		return this.parceiroContatoService;
	}

	public void setParceiroContatoService(ParceiroContatoService parceiroContatoService) {
		this.parceiroContatoService = parceiroContatoService;
	}

	public List<CondicaoPagamento> getListaCondicaoPagamento() {
		return this.listaCondicaoPagamento;
	}

	public void setListaCondicaoPagamento(List<CondicaoPagamento> listaCondicaoPagamento) {
		this.listaCondicaoPagamento = listaCondicaoPagamento;
	}

	public CondicaoPagamentoService getCondicaoPagamentoService() {
		return this.condicaoPagamentoService;
	}

	public void setCondicaoPagamentoService(CondicaoPagamentoService condicaoPagamentoService) {
		this.condicaoPagamentoService = condicaoPagamentoService;
	}

	public List<Conta> getListaConta() {
		return this.listaConta;
	}

	public void setListaConta(List<Conta> listaConta) {
		this.listaConta = listaConta;
	}

	public ContaService getContaService() {
		return this.contaService;
	}

	public void setContaService(ContaService contaService) {
		this.contaService = contaService;
	}

	public String getSeqContaSelecionado() {
		return this.seqContaSelecionado;
	}

	public void setSeqContaSelecionado(String seqConta) {
		this.seqContaSelecionado = seqConta;
	}

	public Integer getIdAccordionPanel() {
		return this.idAccordionPanel;
	}

	public void setIdAccordionPanel(Integer idAccordionPanel) {
		this.idAccordionPanel = idAccordionPanel;
	}

	public FinanceiroItemService getFinanceiroItemService() {
		return this.financeiroItemService;
	}

	public void setFinanceiroItemService(FinanceiroItemService financeiroItemService) {
		this.financeiroItemService = financeiroItemService;
	}

	public List<FinanceiroItem> getListaFinanceiroItem() {
		return this.listaFinanceiroItem;
	}

	public void setListaFinanceiroItem(List<FinanceiroItem> listaFinanceiroItem) {
		this.listaFinanceiroItem = listaFinanceiroItem;
	}

	public FinanceiroItem getFinanceiroItem() {
		return this.financeiroItem;
	}

	public void setFinanceiroItem(FinanceiroItem financeiroItem) {
		this.financeiroItem = financeiroItem;
	}

	public List<FinanceiroCategoria> getListaFinanceiroCategoria() {
		return this.listaFinanceiroCategoria;
	}

	public void setListaFinanceiroCategoria(List<FinanceiroCategoria> listaFinanceiroCategoria) {
		this.listaFinanceiroCategoria = listaFinanceiroCategoria;
	}

	public FinanceiroCategoriaService getFinanceiroCategoriaService() {
		return this.financeiroCategoriaService;
	}

	public void setFinanceiroCategoriaService(FinanceiroCategoriaService financeiroCategoriaService) {
		this.financeiroCategoriaService = financeiroCategoriaService;
	}

	public String getSeqCategoriaSelecionado() {
		return this.seqCategoriaSelecionado;
	}

	public void setSeqCategoriaSelecionado(String seqCategoriaSelecionado) {
		this.seqCategoriaSelecionado = seqCategoriaSelecionado;
	}

	public String getSeqNaturezaSelecionado() {
		return this.seqNaturezaSelecionado;
	}

	public void setSeqNaturezaSelecionado(String seqNaturezaSelecionado) {
		this.seqNaturezaSelecionado = seqNaturezaSelecionado;
	}

	public String getSeqCodigoFiscalSelecionado() {
		return this.seqCodigoFiscalSelecionado;
	}

	public void setSeqCodigoFiscalSelecionado(String seqCodigoFiscalSelecionado) {
		this.seqCodigoFiscalSelecionado = seqCodigoFiscalSelecionado;
	}

	public Financeiro getFinanceiroQuitacao() {
		return this.financeiroQuitacao;
	}

	public void setFinanceiroQuitacao(Financeiro financeiroQuitacao) {
		this.financeiroQuitacao = financeiroQuitacao;
	}

	public Integer getIdEditarQuitacao() {
		return this.idEditarQuitacao;
	}

	public void setIdEditarQuitacao(Integer idEditarQuitacao) {
		this.idEditarQuitacao = idEditarQuitacao;
	}

	public String getMsgCancelamentoAnterior() {
		return this.msgCancelamentoAnterior;
	}

	public void setMsgCancelamentoAnterior(String msgCancelamentoAnterior) {
		this.msgCancelamentoAnterior = msgCancelamentoAnterior;
	}

	public List<Financeiro> getListaFinanceiroOrdemPagamento() {
		return this.listaFinanceiroOrdemPagamento;
	}

	public void setListaFinanceiroOrdemPagamento(List<Financeiro> listaFinanceiroOrdemPagamento) {
		this.listaFinanceiroOrdemPagamento = listaFinanceiroOrdemPagamento;
	}

	public BigDecimal getValorOrdemPagamento() {
		return this.valorOrdemPagamento;
	}

	public void setValorOrdemPagamento(BigDecimal valorOrdemPagamento) {
		this.valorOrdemPagamento = valorOrdemPagamento;
	}

	public String getTipoDocumentoSelecionado() {
		return this.tipoDocumentoSelecionado;
	}

	public void setTipoDocumentoSelecionado(String tipoDocumentoSelecionado) {
		this.tipoDocumentoSelecionado = tipoDocumentoSelecionado;
	}

	public BigDecimal getTotalValorBruto() {
		return this.totalValorBruto;
	}

	public void setTotalValorBruto(BigDecimal totalValorBruto) {
		this.totalValorBruto = totalValorBruto;
	}

	public BigDecimal getTotalValorLiquido() {
		return this.totalValorLiquido;
	}

	public void setTotalValorLiquido(BigDecimal totalValorLiquido) {
		this.totalValorLiquido = totalValorLiquido;
	}

	public String getSeqUnidadeNegocioSelecionado() {
		return this.seqUnidadeNegocioSelecionado;
	}

	public void setSeqUnidadeNegocioSelecionado(String seqUnidadeNegocioSelecionado) {
		this.seqUnidadeNegocioSelecionado = seqUnidadeNegocioSelecionado;
	}

	public String getOperacaoSelecionada() {
		return this.operacaoSelecionada;
	}

	public void setOperacaoSelecionada(String operacaoSelecionada) {
		this.operacaoSelecionada = operacaoSelecionada;
	}

	public BigDecimal getTotalCredito() {
		return this.totalCredito;
	}

	public void setTotalCredito(BigDecimal totalCredito) {
		this.totalCredito = totalCredito;
	}

	public BigDecimal getTotalDebito() {
		return this.totalDebito;
	}

	public void setTotalDebito(BigDecimal totalDebito) {
		this.totalDebito = totalDebito;
	}

	public BigDecimal getSaldoFinal() {
		return this.saldoFinal;
	}

	public void setSaldoFinal(BigDecimal saldoFinal) {
		this.saldoFinal = saldoFinal;
	}

	public BigDecimal getSaldoInicial() {
		return this.saldoInicial;
	}

	public void setSaldoInicial(BigDecimal saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public String getIdFinanceiro() {
		return this.idFinanceiro;
	}

	public void setIdFinanceiro(String idFinanceiro) {
		this.idFinanceiro = idFinanceiro;
	}

	public FinanceiroItemPc getFinanceiroItemPc() {
		return this.financeiroItemPc;
	}

	public void setFinanceiroItemPc(FinanceiroItemPc financeiroItemPc) {
		this.financeiroItemPc = financeiroItemPc;
	}

	public List<FinanceiroItemPc> getListaFinanceiroItemPc() {
		return this.listaFinanceiroItemPc;
	}

	public void setListaFinanceiroItemPc(List<FinanceiroItemPc> listaFinanceiroItemPc) {
		this.listaFinanceiroItemPc = listaFinanceiroItemPc;
	}

	public FinanceiroItemPcService getFinanceiroItemPcService() {
		return this.financeiroItemPcService;
	}

	public void setFinanceiroItemPcService(FinanceiroItemPcService financeiroItemPcService) {
		this.financeiroItemPcService = financeiroItemPcService;
	}

	public List<Equipamento> getListaEquipamento() {
		return this.listaEquipamento;
	}

	public void setListaEquipamento(List<Equipamento> listaEquipamento) {
		this.listaEquipamento = listaEquipamento;
	}

	public EquipamentoService getEquipamentoService() {
		return this.equipamentoService;
	}

	public void setEquipamentoService(EquipamentoService equipamentoService) {
		this.equipamentoService = equipamentoService;
	}

	public List<NvEmbarcacaoParceiro> getListaNvEmbarcacaoParceiro() {
		return this.listaNvEmbarcacaoParceiro;
	}

	public void setListaNvEmbarcacaoParceiro(List<NvEmbarcacaoParceiro> listaNvEmbarcacaoParceiro) {
		this.listaNvEmbarcacaoParceiro = listaNvEmbarcacaoParceiro;
	}

	public NvEmbarcacaoParceiroService getNvEmbarcacaoParceiroService() {
		return this.nvEmbarcacaoParceiroService;
	}

	public void setNvEmbarcacaoParceiroService(NvEmbarcacaoParceiroService nvEmbarcacaoParceiroService) {
		this.nvEmbarcacaoParceiroService = nvEmbarcacaoParceiroService;
	}

	public List<FinanceiroItemPc> getListaFinanceiroItemPcDeletado() {
		return this.listaFinanceiroItemPcDeletado;
	}

	public void setListaFinanceiroItemPcDeletado(List<FinanceiroItemPc> listaFinanceiroItemPcDeletado) {
		this.listaFinanceiroItemPcDeletado = listaFinanceiroItemPcDeletado;
	}

	public List<Material> getListaMaterial() {
		return this.listaMaterial;
	}

	public void setListaMaterial(List<Material> listaMaterial) {
		this.listaMaterial = listaMaterial;
	}

	public MaterialService getMaterialService() {
		return this.materialService;
	}

	public void setMaterialService(MaterialService materialService) {
		this.materialService = materialService;
	}

	public String getSeqColaboradorSelecionado() {
		return this.seqColaboradorSelecionado;
	}

	public void setSeqColaboradorSelecionado(String seqColaboradorSelecionado) {
		this.seqColaboradorSelecionado = seqColaboradorSelecionado;
	}

	public boolean isPorDataPeriodoInicial() {
		return this.porDataPeriodoInicial;
	}

	public void setPorDataPeriodoInicial(boolean porDataPeriodoInicial) {
		this.porDataPeriodoInicial = porDataPeriodoInicial;
	}

	public String getDataPeriodoInicialInicial() {
		return this.dataPeriodoInicialInicial;
	}

	public void setDataPeriodoInicialInicial(String dataPeriodoInicialInicial) {
		this.dataPeriodoInicialInicial = dataPeriodoInicialInicial;
	}

	public String getDataPeriodoInicialFinal() {
		return this.dataPeriodoInicialFinal;
	}

	public void setDataPeriodoInicialFinal(String dataPeriodoInicialFinal) {
		this.dataPeriodoInicialFinal = dataPeriodoInicialFinal;
	}

	public boolean isPorDataPeriodoFinal() {
		return this.porDataPeriodoFinal;
	}

	public void setPorDataPeriodoFinal(boolean porDataPeriodoFinal) {
		this.porDataPeriodoFinal = porDataPeriodoFinal;
	}

	public String getDataPeriodoFinalInicial() {
		return this.dataPeriodoFinalInicial;
	}

	public void setDataPeriodoFinalInicial(String dataPeriodoFinalInicial) {
		this.dataPeriodoFinalInicial = dataPeriodoFinalInicial;
	}

	public String getDataPeriodoFinalFinal() {
		return this.dataPeriodoFinalFinal;
	}

	public void setDataPeriodoFinalFinal(String dataPeriodoFinalFinal) {
		this.dataPeriodoFinalFinal = dataPeriodoFinalFinal;
	}

	public FinanceiroOcorrenciaService getFinanceiroOcorrenciaService() {
		return this.financeiroOcorrenciaService;
	}

	public void setFinanceiroOcorrenciaService(FinanceiroOcorrenciaService financeiroOcorrenciaService) {
		this.financeiroOcorrenciaService = financeiroOcorrenciaService;
	}

	public List<FinanceiroOcorrencia> getListaFinanceiroOcorrencia() {
		return this.listaFinanceiroOcorrencia;
	}

	public void setListaFinanceiroOcorrencia(List<FinanceiroOcorrencia> listaFinanceiroOcorrencia) {
		this.listaFinanceiroOcorrencia = listaFinanceiroOcorrencia;
	}

	public FinanceiroOcorrencia getFinanceiroOcorrencia() {
		return this.financeiroOcorrencia;
	}

	public void setFinanceiroOcorrencia(FinanceiroOcorrencia financeiroOcorrencia) {
		this.financeiroOcorrencia = financeiroOcorrencia;
	}

	public BigDecimal getSaldoRestante() {
		return this.saldoRestante;
	}

	public void setSaldoRestante(BigDecimal saldoRestante) {
		this.saldoRestante = saldoRestante;
	}
        
        
       	public UploadedFile getFile() {
		return this.file;
	}

    
	public Upload getUpload() {
		return this.upload;
	}

	public void setUpload(Upload upload) {
		this.upload = upload;
	}

	public UploadService getUploadService() {
		return this.uploadService;
	}

	public void setUploadService(UploadService uploadService) {
		this.uploadService = uploadService;
	}

	public List<Upload> getListaUpload() {
		return this.listaUpload;
	}

	public void setListaUpload(List<Upload> listaUpload) {
		this.listaUpload = listaUpload;
	}



	public UploadController getUploadController() {
		return this.uploadController;
	}

	public void setUploadController(UploadController uploadController) {
		this.uploadController = uploadController;
	}

	public StreamedContent getFileDownload() {
		return this.fileDownload;
	}

	public void setFileDownload(StreamedContent fileDownload) {
		this.fileDownload = fileDownload;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}    
 
        
        
        
        
        
        
}

/*
 * Location: C:\Users\diogo\Documents\workspace\others\prod
 * erp\deploy\erp3.war!\WEB-INF\classes\Controller\FinanceiroController.class
 * Java compiler version: 7 (51.0) JD-Core Version: 0.7.1
 */