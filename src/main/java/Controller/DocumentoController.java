package Controller;

import Aliquota.Aliquota;
import Aliquota.AliquotaService;
import ClausulaSQL.ClausulaWhere;
import Colaborador.Colaborador;
import Colaborador.ColaboradorService;
import CondicaoPagamento.CondicaoPagamento;
import CondicaoPagamento.CondicaoPagamentoService;
import Conta.Conta;
import Conta.ContaService;
import Documento.Documento;
import Documento.DocumentoService;
import DocumentoComentario.DocumentoComentario;
import DocumentoComentario.DocumentoComentarioService;
import DocumentoEscopo.DocumentoEscopo;
import DocumentoEscopo.DocumentoEscopoService;
import DocumentoEtapa.DocumentoEtapa;
import DocumentoEtapa.DocumentoEtapaService;
import DocumentoItemEmbarcacao.DocumentoItemEmbarcacao;
import DocumentoItemEquipamento.DocumentoItemEquipamento;
import DocumentoItemFinanceiro.DocumentoItemFinanceiro;
import DocumentoItemFinanceiro.DocumentoItemFinanceiroService;
import DocumentoItemMaterial.DocumentoItemMaterial;
import DocumentoItemMaterial.DocumentoItemMaterialService;
import DocumentoItemServico.DocumentoItemServico;
import DocumentoItemServico.DocumentoItemServicoService;
import DocumentoOcorrencia.DocumentoOcorrencia;
import DocumentoOcorrencia.DocumentoOcorrenciaService;
import Empresa.Empresa;
import Equipamento.Equipamento;
import Equipamento.EquipamentoService;
import FormaPagamento.FormaPagamento;
import FormaPagamento.FormaPagamentoService;
import Lugar.Lugar;
import Lugar.LugarService;
import MaterialPreco.MaterialPreco;
import MaterialPreco.MaterialPrecoService;
import Material.Material;
import Material.MaterialService;
import NvEmbarcacao.NvEmbarcacao;
import NvEmbarcacao.NvEmbarcacaoService;
import NvEmbarcacaoParceiro.NvEmbarcacaoParceiro;
import NvEmbarcacaoParceiro.NvEmbarcacaoParceiroService;
import Parceiro.Parceiro;
import Parceiro.ParceiroService;
import ParceiroContato.ParceiroContato;
import ParceiroContato.ParceiroContatoService;
import PlanoItem.PlanoItem;
import PlanoItem.PlanoItemService;
import ProjecaoTributaria.ProjecaoTributaria;
import ProjecaoTributaria.ProjecaoTributariaService;
import ServicoEscopo.ServicoEscopo;
import ServicoEscopo.ServicoEscopoService;
import TipoDocumento.TipoDocumento;
import TipoDocumento.TipoDocumentoService;
import TipoDocumentoGerar.TipoDocumentoGerar;
import TipoDocumentoGerar.TipoDocumentoGerarService;
import TipoMovimentoFinanceiro.TipoMovimentoFinanceiro;
import TipoMovimentoFinanceiro.TipoMovimentoFinanceiroService;
import TipoUnidade.TipoUnidade;
import TipoUnidade.TipoUnidadeService;
import UnidadeNegocio.UnidadeNegocio;
import UnidadeNegocio.UnidadeNegocioService;
import Upload.Upload;
import Upload.UploadService;
import Usuario.Usuario;
import UsuarioTipoDocumento.UsuarioTipoDocumentoService;
import Util.Conexao;
import Util.Situacao;
import Util.Util;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "documentoController")
@ViewScoped
public class DocumentoController {
	@ManagedProperty("#{loginController}")
	protected LoginController loginController;
	boolean bNovoDocumento = false;

	boolean bPontoServico = false;
	boolean bConta = false;
	boolean bFormaPagamento = false;
	boolean bCondicaoPagamento = false;
	boolean bTipoMovimentoFin = false;

	boolean bFinanceiro = false;
	boolean bMaterial = false;
	boolean bEscopo = false;
	boolean bEmbarcacao = false;
	boolean bEquipamento = false;
	boolean bColaborador = false;
	boolean bAssinaturaColaborador = false;

	String pGrupoServico = "";
	String msgCancelamentoAnterior = "";
	BigDecimal precoMinimo = BigDecimal.ZERO;
	BigDecimal precoMaximo = BigDecimal.ZERO;

	BigDecimal valorDescDocumento = BigDecimal.ZERO;
	BigDecimal valorRestanteDocumento = BigDecimal.ZERO;
	BigDecimal qtdeItemDocumento = BigDecimal.ZERO;

	DocumentoService documentoService = new DocumentoService();
	Documento documento = new Documento();
	Documento documentoDono = new Documento();
	List<Documento> listaDocumentoFilho = new ArrayList();

	List<Documento> listaItensBotaoGerar = new ArrayList();
	List<TipoDocumentoGerar> tp = new ArrayList();

	Upload upload = new Upload();
	UploadController uploadController = new UploadController();
	UploadService uploadService = new UploadService();
	List<Upload> listaUpload = new ArrayList();

	UploadedFile arquivo;

	StreamedContent fileDownload;
	StreamedContent arquivoVisualizar;
	Integer tela = Integer.valueOf(0);

	String idDocumento;
	String idTipoDocumento;
	String url;
	String naturezaLancamento;
        String caminho;
	ProjecaoTributariaService projecaoTributariaService = new ProjecaoTributariaService();
	List<ProjecaoTributaria> listaProjecaoTributaria = new ArrayList();

	AliquotaService aliquotaRetencaoFederalService = new AliquotaService();
	List<Aliquota> listaAliquotaRetencaoFederal = new ArrayList();

	PlanoItemService planoItemService = new PlanoItemService();
	List<PlanoItem> listaPlanoItem = new ArrayList();

	ParceiroService parceiroService = new ParceiroService();
	List<Parceiro> listaParceiro = new ArrayList();
	Parceiro parceiro = new Parceiro();

	UnidadeNegocio unidadeNegocio = new UnidadeNegocio();
	UnidadeNegocioService unidadeNegocioService = new UnidadeNegocioService();
	List<UnidadeNegocio> listaUnidadeNegocio = new ArrayList();

	TipoDocumentoService tipoDocumentoService = new TipoDocumentoService();
	List<TipoDocumento> listaTipoDocumento = new ArrayList();
	TipoDocumento tipoDocumento = new TipoDocumento();

	TipoDocumentoGerarService tipoDocumentoGerarService = new TipoDocumentoGerarService();
	TipoDocumentoGerar tipoDocumentoGerar = new TipoDocumentoGerar();
	List<TipoDocumentoGerar> listaTipoDocumentoGerar = new ArrayList();

	DocumentoItemMaterialService documentoItemMaterialService = new DocumentoItemMaterialService();
	List<DocumentoItemMaterial> listaDocumentoItemMaterial = new ArrayList();
	DocumentoItemMaterial documentoItemMaterial = new DocumentoItemMaterial();

	DocumentoItemServicoService documentoItemServicoService = new DocumentoItemServicoService();
	List<DocumentoItemServico> listaDocumentoItemServico = new ArrayList();
	List<DocumentoItemServico> listaDocumentoItemServicoRemover = new ArrayList();
	DocumentoItemServico documentoItemServico = new DocumentoItemServico();

	DocumentoEtapaService documentoEtapaService = new DocumentoEtapaService();
	List<DocumentoEtapa> listaDocumentoEtapa = new ArrayList();
	DocumentoEtapa documentoEtapa = new DocumentoEtapa();

	DocumentoOcorrenciaService documentoOcorrenciaService = new DocumentoOcorrenciaService();
	List<DocumentoOcorrencia> listaDocumentoOcorrencia = new ArrayList();
	DocumentoOcorrencia documentoOcorrencia = new DocumentoOcorrencia();

	DocumentoComentario documentoComentario = new DocumentoComentario();
	DocumentoComentarioService documentoComentarioService = new DocumentoComentarioService();
	List<DocumentoComentario> listaDocumentoComentario = new ArrayList();

	MaterialPrecoService materialPrecoService = new MaterialPrecoService();
	List<MaterialPreco> listaMaterialReferencia = new ArrayList();
	List<MaterialPreco> listaMaterialPreco = new ArrayList();
	MaterialPreco materialPreco = new MaterialPreco();
        
	MaterialService materialService = new MaterialService();
	List<Material> listaMaterial = new ArrayList();
	Material material = new Material();        

	CondicaoPagamentoService condicaoPagamentoService = new CondicaoPagamentoService();
	List<CondicaoPagamento> listaCondicaoPagamento = new ArrayList();

	EquipamentoService equipamentoService = new EquipamentoService();
	List<Equipamento> listaEquipamento = new ArrayList();
	Equipamento equipamento = new Equipamento();
	private ArrayList<String> equipamentoSelecionado = new ArrayList();

	NvEmbarcacaoParceiroService nvEmbarcacaoParceiroService = new NvEmbarcacaoParceiroService();
	List<NvEmbarcacaoParceiro> listaNvEmbarcacaoParceiro = new ArrayList();
	private ArrayList<String> embarcacaoSelecionado = new ArrayList();

	LugarService lugarService = new LugarService();
	List<Lugar> listaLugar = new ArrayList();

	ServicoEscopoService servicoEscopoService = new ServicoEscopoService();
	List<ServicoEscopo> listaServicoEscopo = new ArrayList();

	ColaboradorService colaboradorService = new ColaboradorService();
	List<Colaborador> listaColaborador = new ArrayList();
	List<Colaborador> listaAssColaborador = new ArrayList();

	DocumentoItemFinanceiroService documentoItemFinanceiroService = new DocumentoItemFinanceiroService();
	DocumentoItemFinanceiro documentoItemFinanceiro = new DocumentoItemFinanceiro();

	FormaPagamentoService formaPagamentoService = new FormaPagamentoService();
	List<FormaPagamento> listaFormaPagamento = new ArrayList();

	ContaService contaService = new ContaService();
	List<Conta> listaConta = new ArrayList();

	TipoUnidadeService tipoUnidadeService = new TipoUnidadeService();
	List<TipoUnidade> listaTipoUnidade = new ArrayList();

	TipoMovimentoFinanceiroService tipoMovimentoFinanceiroService = new TipoMovimentoFinanceiroService();
	List<TipoMovimentoFinanceiro> listaTipoMovimentoFinanceiro = new ArrayList();

	List<ParceiroContato> listaParceiroContato = new ArrayList();
	ParceiroContatoService parceiroContatoService = new ParceiroContatoService();

	UsuarioTipoDocumentoService usuarioTipoDocumentoService = new UsuarioTipoDocumentoService();
	boolean usuarioTipoDocumento = false;

	DocumentoEscopoService documentoEscopoService = new DocumentoEscopoService();
	DocumentoEscopo documentoEscopo = new DocumentoEscopo();
	List<DocumentoEscopo> listaDocumentoEscopo = new ArrayList();

	NvEmbarcacaoService nvEmbarcacaoService = new NvEmbarcacaoService();
	NvEmbarcacao nvEmbarcacao = new NvEmbarcacao();

	public void carregarTelaNovoDocumento() {
		if ((this.loginController.usuario.getAcDocNovo() == null)
				|| (this.loginController.usuario.getAcDocNovo().equals("-1"))) {
			this.loginController.mudarPagina("/organizacional/acessonegado.jsf");
			return;
		}
                 if (this.documento.getValidade()!= null) {
                    this.documento.setValidade(this.documento.getValidade());
                } else {
                    this.documento.setValidade("Proposta válida por 60 (sessenta) dias a partir da data de emissão.");
                }                
                
                /*this.documento.setCondicoesGerais("As despesas de hospedagem, transporte, alimentação e outras para atendimento fora do município do Rio de Janeiro e Niterói, são de \n" +
"responsabilidade do cliente e devem ser providenciadas preferencialmente pelo mesmo, quando não, serão aplicadas as seguintes condições:\n" +
" \n" +
"a)    R$ 1,50 (Um real e cinquenta centavos) /Km rodado considerando-se a partida da sede ou escritório regional da empresa e os municípios visitados, e retorno a sede ou escritório regional da empresa;\n" +
"b)    Reembolso de pedágios e estacionamentos, mediante a apresentação do recibo original, acrescidos de 15% a título de \n" +
"administração, acrescidas de impostos;\n" +
"c)    Valor de alimentação R$ 100,00 (cem reais) dia/homem;\n" +
"d)    Diária de hospedagem R$ 200,00 (duzentos reais) dia/homem;\n" +
"e)    Reembolso de transporte aéreo, quando aplicado, mediante aprovação prévia do contratante e apresentação de \n" +
"comprovante;");*/                
		this.listaTipoDocumento = this.tipoDocumentoService.listar(this.loginController.getEmpresa().getSeqEmpresa(),
				"", Situacao.ATIVO, this.loginController.getUsuario().getSeqUsuario());

	}

	public void carregarTelaDocumento() {
		this.listaTipoDocumento = this.tipoDocumentoService.listar(this.loginController.getEmpresa().getSeqEmpresa(),
				"", Situacao.ATIVO, this.loginController.getUsuario().getSeqUsuario());

		if (!this.idTipoDocumento.equals("0")) {
			boolean permissao = false;
			for (TipoDocumento tp : this.listaTipoDocumento) {
				if (tp.getSeqTipoDocumento().equals(this.idTipoDocumento)) {
					permissao = true;
				}
			}
			if (!permissao) {
				this.loginController.mudarPagina("/documento/sempermissao.jsf");
			}
		}

		if (!this.idDocumento.equals("0")) {
			this.listaDocumentoComentario = this.documentoComentarioService
					.listar(this.loginController.getUsuario().getSeqEmpresa(), this.idDocumento);
			this.listaParceiroContato = this.parceiroContatoService.listarPorParceiro(this.documento.getSeqParceiro(),
					Situacao.TODOS);
			this.listaUpload = this.uploadService.listar(this.loginController.empresa.getSeqEmpresa(),
					this.idDocumento);
			this.listaDocumentoFilho = this.documentoService.listarDocumentosFilhos(this.idDocumento);
		}

		this.documento = this.documentoService.buscarPorID(this.loginController.getUsuario().getSeqEmpresa(),
				this.idDocumento);
		this.tipoDocumento = this.tipoDocumentoService.buscarPorId(this.idTipoDocumento);
		this.listaDocumentoEtapa = this.documentoEtapaService.listarPorTipoDocumento(this.idTipoDocumento,
				Situacao.ATIVO);
		this.usuarioTipoDocumento = this.usuarioTipoDocumentoService
				.listarDocumento(this.loginController.getUsuario().getSeqUsuario(), "401");
		this.listaTipoDocumentoGerar = this.tipoDocumentoGerarService.listar(this.idTipoDocumento, this.idDocumento);
                this.listaMaterial = this.materialService.listar(this.loginController.getUsuario().getSeqEmpresa(),"",Situacao.ATIVO);

		for (int i = 0; i < this.listaTipoDocumentoGerar.size(); i++) {
			if ((((TipoDocumentoGerar) this.listaTipoDocumentoGerar.get(i)).getSeqTipoDocumentoPai().equals("301"))
					&& (!this.documento.getDocDonoSeqTipo().equals("282")))
				this.listaTipoDocumentoGerar.remove(i);
		}
		int i;
		if (!this.usuarioTipoDocumento) {
			System.out.println("falso<+========");
			for (i = 0; i < this.listaTipoDocumentoGerar.size(); i++) {
				if (((TipoDocumentoGerar) this.listaTipoDocumentoGerar.get(i)).getSeqTipoDocumentoFilho()
						.equals("401")) {
					this.listaTipoDocumentoGerar.remove(i);
				}
			}
		}

		this.listaTipoUnidade = this.tipoUnidadeService.listar(this.loginController.getUsuario().getSeqEmpresa(), "",
				Situacao.ATIVO);
		this.listaUnidadeNegocio = this.unidadeNegocioService.listar(this.loginController.usuario.getSeqEmpresa(), "",
				Situacao.ATIVO);

		this.bPontoServico = this.tipoDocumento.getOpcPontoServico().equals("Sim");
		this.bEmbarcacao = this.tipoDocumento.getOpcEmbarcacao().equals("Sim");
		this.bEquipamento = this.tipoDocumento.getOpcEquipamento().equals("Sim");
		this.bEscopo = this.tipoDocumento.getOpcEscopo().equals("Sim");
		this.bFinanceiro = this.tipoDocumento.getOpcGeraBoleto().equals("Sim");
		this.bMaterial = this.tipoDocumento.getOpcTabelaPreco().equals("Sim");
		this.bConta = this.tipoDocumento.getOpcConta().equals("Sim");
		this.bFormaPagamento = this.tipoDocumento.getOpcFormaPagamento().equals("Sim");
		this.bCondicaoPagamento = this.tipoDocumento.getOpcCondicaoPagamento().equals("Sim");
		this.bTipoMovimentoFin = this.tipoDocumento.getOpcTipoMovimentoFin().equals("Sim");
		this.bColaborador = this.tipoDocumento.getOpcColaborador().equals("Sim");
		this.bAssinaturaColaborador = this.tipoDocumento.getOpcAssColaborador().equals("Sim");

		this.listaPlanoItem = this.planoItemService.listar(this.loginController.empresa.getSeqEmpresa(), "",
				Situacao.ATIVO);
		this.bNovoDocumento = false;
		if (this.documento == null) {
			this.bNovoDocumento = true;
			this.documento = new Documento();
			this.documento.setData(new Date());
		}

		System.out.println("===> " + this.bNovoDocumento);

		if (this.idTipoDocumento.equals("401")) {
			this.listaParceiro = this.parceiroService
                                .listarParceiroTipoDoc(this.loginController.getEmpresa().getSeqEmpresa());
			this.listaTipoMovimentoFinanceiro = this.tipoMovimentoFinanceiroService.listarPorOperacao(
					this.loginController.getEmpresa().getSeqEmpresa(),Situacao.ATIVO, "Crédito");
			
                        this.listaProjecaoTributaria = this.projecaoTributariaService
					.listar(this.loginController.getUsuario().getSeqEmpresa(), "", Situacao.ATIVO);
			this.listaAliquotaRetencaoFederal = this.aliquotaRetencaoFederalService
					.listar(this.loginController.getUsuario().getSeqEmpresa(), "RETENÇÕES FEDERAIS", Situacao.ATIVO);

			for (DocumentoEtapa documentoEtapa : this.listaDocumentoEtapa) {
				if ((documentoEtapa.getSeqDocumentoEtapa().equals("442"))
						|| (documentoEtapa.getSeqDocumentoEtapa().equals("443"))) {
					this.listaDocumentoEtapa.remove(documentoEtapa);
				}

			}
		} else {
			this.listaParceiro = this.parceiroService.listarParceiroTipoDoc(this.loginController.getUsuario().getSeqEmpresa()
					);
		}

		if (this.documento.getSeqParceiro() != null) {
			listarDetalheParceiro();
		}

		if (this.bEquipamento) {
			listarEquipamento();
			for (DocumentoItemEquipamento eqp : this.documento.getListaDocumentoItemEquipamento()) {
				this.equipamentoSelecionado.add(eqp.getSeqEquipamento());
			}
		}

		if (this.bEmbarcacao) {
			listarEmbarcacao();
			for (DocumentoItemEmbarcacao emb : this.documento.getListaDocumentoItemEmbarcacao()) {
				this.embarcacaoSelecionado.add(emb.getSeqNvEmbarcacao());
			}
		}

		if ((this.bMaterial) && (!this.bNovoDocumento)) {
			this.listaMaterialPreco = this.materialPrecoService.listarPorParceiro(
					this.loginController.getUsuario().getSeqEmpresa(), this.documento.getSeqParceiro());
		}

		if (this.bPontoServico) {
			this.listaLugar = this.lugarService.listar(this.loginController.getEmpresa().getSeqEmpresa(), "",
					Situacao.ATIVO);
		}
		Documento dono;
		if (this.bEscopo) {
			dono = new Documento();
			if ((this.idTipoDocumento.equals("301")) || (this.idTipoDocumento.equals("381"))) {
				dono = (Documento) this.documentoService.buscarTipoDocumentoDono(this.idDocumento).get(0);
				this.listaDocumentoEscopo = this.documentoEscopoService.listar(dono.getDocDonoSeqTipo(), "");
			} else if ((this.idTipoDocumento.equals("401")) && (!this.idDocumento.equals("0"))) {
				dono = (Documento) this.documentoService.buscarTipoDocumentoDono(this.idDocumento).get(0);
				this.listaDocumentoEscopo = this.documentoEscopoService.listar(dono.getDocDonoSeqTipo(), "");
			} else {
				this.listaDocumentoEscopo = this.documentoEscopoService.listar(this.idTipoDocumento, "");
			}
		}

		if (this.bColaborador) {
			this.listaColaborador = this.colaboradorService.listar(this.loginController.getEmpresa().getSeqEmpresa(),
					"", Situacao.ATIVO);
		}

		if (this.bAssinaturaColaborador) {
			this.listaAssColaborador = this.colaboradorService.listar(this.loginController.getEmpresa().getSeqEmpresa(),
					"", Situacao.ATIVO);
		}

		if (this.bFormaPagamento) {
			this.listaFormaPagamento = this.formaPagamentoService
					.listar(this.loginController.getEmpresa().getSeqEmpresa(), "", Situacao.ATIVO);
		}

		if (this.bConta) {
			this.listaConta = this.contaService.listar(this.loginController.getEmpresa().getSeqEmpresa(), "",
					Situacao.ATIVO);
		}

		if (this.bCondicaoPagamento) {
			this.listaCondicaoPagamento = this.condicaoPagamentoService
					.listar(this.loginController.getEmpresa().getSeqEmpresa(), "", Situacao.ATIVO);
		}

		if (this.bFinanceiro) {
			this.documentoItemFinanceiro = this.documentoItemFinanceiroService.buscar(this.idDocumento);
		}

		if (this.bConta) {
			this.listaConta = this.contaService.listar(this.loginController.getEmpresa().getSeqEmpresa(), "",
					Situacao.ATIVO);
			if (this.documento.getSeqConta() != null) {
				for (Conta pConta : this.listaConta) {
					if (pConta.getSeqConta().equals(this.documento.getSeqConta())) {
						this.documento.setTarifaBancaria(pConta.getVlTarifa());
					}
				}
			}
		}

		if (this.bFormaPagamento) {
			this.listaFormaPagamento = this.formaPagamentoService
					.listar(this.loginController.getEmpresa().getSeqEmpresa(), "", Situacao.ATIVO);
		}

		if (this.bCondicaoPagamento) {
			this.listaCondicaoPagamento = this.condicaoPagamentoService
					.listar(this.loginController.getEmpresa().getSeqEmpresa(), "", Situacao.ATIVO);
		}

		if (this.documento.getVlTotal() == null) {
			this.documento.setVlTotal(BigDecimal.ZERO);
			this.valorRestanteDocumento = this.documento.getVlTotal();
		} else if (this.documento.getVlEntrada() != null) {
			this.valorRestanteDocumento = this.documento.getVlTotal().subtract(this.documento.getVlEntrada());
		} else {
			this.valorRestanteDocumento = this.documento.getVlTotal();
		}

		if (!this.documento.getListaDocumentoItemMaterial().isEmpty()) {
			this.documento.setVlTotal(BigDecimal.ZERO);
			for (int j = 0; j < this.documento.getListaDocumentoItemMaterial().size(); j++) {
				this.qtdeItemDocumento = this.qtdeItemDocumento
						.add(((DocumentoItemMaterial) this.documento.getListaDocumentoItemMaterial().get(j)).getQtde());
				if (this.idTipoDocumento.equals("401")) {
					if (((DocumentoItemMaterial) this.documento.getListaDocumentoItemMaterial().get(j))
							.getVlAcumulado() != null) {
						this.documento.setVlTotal(
								((DocumentoItemMaterial) this.documento.getListaDocumentoItemMaterial().get(j))
										.getVlAcumulado());
					}
				} else {
					this.documento.setVlTotal(this.documento.getVlTotal()
							.add(((DocumentoItemMaterial) this.documento.getListaDocumentoItemMaterial().get(j))
									.getPrecoTotal()));
				}
				if (((DocumentoItemMaterial) this.documento.getListaDocumentoItemMaterial().get(j))
						.getVlDesconto() != null) {
					this.valorDescDocumento = this.valorDescDocumento
							.add(((DocumentoItemMaterial) this.documento.getListaDocumentoItemMaterial().get(j))
									.getVlDesconto());
				}
			}
		} else {
			this.valorDescDocumento = BigDecimal.ZERO;
			this.qtdeItemDocumento = BigDecimal.ZERO;
			this.valorRestanteDocumento = BigDecimal.ZERO;
			this.documento.setVlTotal(BigDecimal.ZERO);
		}

		if (this.documento.getSeqUnidadeNegocio() != null) {
			this.unidadeNegocio = ((UnidadeNegocio) this.unidadeNegocioService
					.listarPorSeq(this.loginController.getUsuario().getSeqEmpresa(),
							this.documento.getSeqUnidadeNegocio(), Situacao.ATIVO)
					.get(0));
		}

		if (this.documento.getComentarioEtapa() != null) {
			this.msgCancelamentoAnterior = this.documento.getComentarioEtapa();
		} else {
			this.msgCancelamentoAnterior = "";
		}
                
                /*if (this.documento.getCondicoesGerais()!= null) {
                    this.documento.setCondicoesGerais(this.documento.getCondicoesGerais());
                } else {
                    this.documento.setCondicoesGerais("As despesas de hospedagem, transporte, alimentação e outras para atendimento fora do município do Rio de Janeiro e Niterói, são de responsabilidade do cliente e devem ser providenciadas preferencialmente pelo mesmo, quando não, serão aplicadas as seguintes condições: <br/>" +
"a)    R$ 1,50 (Um real e cinquenta centavos) /Km rodado considerando-se a partida da sede ou escritório regional da empresa e os municípios visitados, e retorno a sede ou escritório regional da empresa;" +
"b)    Reembolso de pedágios e estacionamentos, mediante a apresentação do recibo original, acrescidos de 15% a título de administração, acrescidas de impostos; <br/> " +
"c)    Valor de alimentação R$ 100,00 (cem reais) dia/homem; <br/> " +
"d)    Diária de hospedagem R$ 200,00 (duzentos reais) dia/homem; <br/> " +
"e)    Reembolso de transporte aéreo, quando aplicado, mediante aprovação prévia do contratante e apresentação de comprovante;");
                }*/
                if (this.documento.getValidade()!= null) {
                    this.documento.setValidade(this.documento.getValidade());
                } else {
                    this.documento.setValidade("Proposta válida por 60 (sessenta) dias a partir da data de emissão.");
                }                
	}

	public void carregarModelo2() {
	}

	public void mudarPagina(String pPagina) {
		try {
			FacesContext ctx = FacesContext.getCurrentInstance();
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(ctx.getExternalContext().getRequestContextPath() + pPagina);
		} catch (IOException ex) {
			Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void selecionarTipoDocumento() {
		for (TipoDocumento tp : this.listaTipoDocumento) {
			if (tp.getSeqTipoDocumento().equals(this.documento.getSeqTipoDocumento())) {
				this.tipoDocumento = tp;
			}
		}
	}

	public void imprimirBoleto() {
		this.loginController
				.mudarPagina("/documento/boleto.jsf?id=" + String.valueOf(this.documento.getSeqDocumento()));
	}

	public void abrirDocumentoDono() {
		Documento doc = this.documentoService.buscarPorID(this.loginController.getUsuario().getSeqEmpresa(),
				this.documento.getSeqDocumentoDono());
		System.out.println("Doc: " + doc.getSeqDocumento() + " - " + doc.getSeqTipoDocumento());
		try {
			FacesContext ctx = FacesContext.getCurrentInstance();
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(ctx.getExternalContext().getRequestContextPath() + "/documento/documento.jsf?idDocumento="
							+ doc.getSeqDocumento() + "&idTipoDocumento=" + doc.getSeqTipoDocumento());
		} catch (IOException ex) {
			Logger.getLogger(PainelDocumentoEtapaController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void abrirDocumentoFilho(String pDocumento) {
		Documento doc = this.documentoService.buscarPorID(this.loginController.getUsuario().getSeqEmpresa(),
				pDocumento);
		System.out.println("Doc: " + doc.getSeqDocumento() + " - " + doc.getSeqTipoDocumento());
		try {
			FacesContext ctx = FacesContext.getCurrentInstance();
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(ctx.getExternalContext().getRequestContextPath() + "/documento/documento.jsf?idDocumento="
							+ doc.getSeqDocumento() + "&idTipoDocumento=" + doc.getSeqTipoDocumento());
		} catch (IOException ex) {
			Logger.getLogger(PainelDocumentoEtapaController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void selecionarDocumentoItemMaterial(DocumentoItemMaterial pDocumentoItemMaterial) {
		this.documentoItemMaterial = pDocumentoItemMaterial;
		this.documento.getListaDocumentoItemMaterial().remove(pDocumentoItemMaterial);
		this.pGrupoServico = pDocumentoItemMaterial.getMaterialReferencia();

		if (this.documento.getVlTotal() != BigDecimal.ZERO) {
			if (this.idTipoDocumento.equals("401")) {
				this.documento
						.setVlTotal(this.documento.getVlTotal().subtract(pDocumentoItemMaterial.getVlAcumulado()));
				this.valorRestanteDocumento = this.valorRestanteDocumento
						.subtract(pDocumentoItemMaterial.getPrecoTotal());
				this.documento.setVlTotal(this.documento.getVlTotal().subtract(pDocumentoItemMaterial.getVlPeriodo()));

				calcularAcumulado();
			} else {
				this.documento.setVlTotal(this.documento.getVlTotal().subtract(pDocumentoItemMaterial.getPrecoTotal()));
				this.valorRestanteDocumento = this.valorRestanteDocumento
						.subtract(pDocumentoItemMaterial.getPrecoTotal());
			}
		}

		this.qtdeItemDocumento = this.qtdeItemDocumento.subtract(pDocumentoItemMaterial.getQtde());
	}

	public void salvarDocumentoItemMaterial() {
		for (MaterialPreco m : this.listaMaterialPreco) {
			if (this.documentoItemMaterial.getSeqMaterial().equals(m.getSeqMaterial())) {
				this.documentoItemMaterial.setMaterialNome(m.getMaterial());
				this.documentoItemMaterial.setMaterialCodigo(m.getCodigo());
				this.documentoItemMaterial.setMaterialReferencia(m.getReferencia());
			}
		}

		this.documento.setGrupoServico(this.pGrupoServico);

		if ((this.idTipoDocumento != null) && (this.idTipoDocumento.equals("401"))) {
			if (this.documento.getIdComplementar() > 0L) {
				if (this.documentoItemMaterial.getQtdePeriodo().add(this.documentoItemMaterial.getQtdeAnterior())
						.compareTo(this.documentoItemMaterial.getQtde()) == 1) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Quantidade Acumulada não pode ser superior a Quantidade Prevista", ""));
					return;
				}
				calcularItemMaterialBoletim();
			} else {
				if (this.documentoItemMaterial.getQtdePeriodo().compareTo(this.documentoItemMaterial.getQtde()) == 1) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Quantidade Executada no Período não pode ser superior a Quantidade Prevista", ""));
					return;
				}
				calcularItemMaterialBoletim();
			}
		} else {
			if (this.documento.getVlTotal() == null) {
				this.documento.setVlTotal(new BigDecimal(0));
			}

			this.documento.setVlTotal(this.documento.getVlTotal().add(this.documentoItemMaterial.getPrecoTotal()));

			if (this.documento.getVlEntrada() != null) {
				this.valorRestanteDocumento = this.documento.getVlTotal().subtract(this.documento.getVlEntrada());
			} else {
				this.valorRestanteDocumento = this.documento.getVlTotal();
			}
		}

		if (this.documentoItemMaterial.getVlDesconto() == null) {
			this.documentoItemMaterial.setVlDesconto(BigDecimal.ZERO);
		} else {
			this.valorDescDocumento = this.valorDescDocumento.add(this.documentoItemMaterial.getVlDesconto());
		}

		this.qtdeItemDocumento = this.qtdeItemDocumento.add(this.documentoItemMaterial.getQtde());

		this.documento.getListaDocumentoItemMaterial().add(this.documentoItemMaterial);

		this.documentoItemMaterial = new DocumentoItemMaterial();
		this.pGrupoServico = "";
		this.precoMaximo = BigDecimal.ZERO;
		this.precoMinimo = BigDecimal.ZERO;

		if (this.idTipoDocumento.equals("401")) {
			calcularAcumulado();
		}
	}

	public void removerDocumentoItemMaterial(DocumentoItemMaterial pDocumentoItemMaterial) {
		this.documento.getListaDocumentoItemMaterial().remove(pDocumentoItemMaterial);

		if ((this.idTipoDocumento != null) && (this.idTipoDocumento.equals("401"))) {
			calcularAcumulado();
			this.documento.setVlTotal(this.documento.getVlTotal().subtract(pDocumentoItemMaterial.getVlAcumulado()));
		} else {
			this.documento.setVlTotal(this.documento.getVlTotal().subtract(pDocumentoItemMaterial.getPrecoTotal()));
		}

		if (this.documento.getVlEntrada() != null) {
			this.valorRestanteDocumento = this.documento.getVlTotal().subtract(this.documento.getVlEntrada());
		} else {
			this.valorRestanteDocumento = this.documento.getVlTotal();
		}

		if (pDocumentoItemMaterial.getVlDesconto() != null) {
			this.valorDescDocumento = this.valorDescDocumento.subtract(pDocumentoItemMaterial.getVlDesconto());
		}

		this.qtdeItemDocumento = this.qtdeItemDocumento.subtract(pDocumentoItemMaterial.getQtde());
	}

	public void calcularItemMaterialBoletim() {
		if (this.documentoItemMaterial.getQtdeAnterior() == null) {
			this.documentoItemMaterial.setQtdeAnterior(BigDecimal.ZERO);
		}

		if ((this.documentoItemMaterial.getVlDesconto() != null)
				&& (this.documentoItemMaterial.getVlDesconto().compareTo(BigDecimal.ZERO) > 0)) {
			this.documentoItemMaterial.setVlPeriodo(
					this.documentoItemMaterial.getQtdePeriodo().multiply(this.documentoItemMaterial.getVlUnitario())
							.subtract(this.documentoItemMaterial.getVlDesconto()));
		} else {
			this.documentoItemMaterial.setVlPeriodo(
					this.documentoItemMaterial.getVlUnitario().multiply(this.documentoItemMaterial.getQtdePeriodo()));
		}

		if (this.documento.getListaDocumentoItemMaterial().size() > 0) {
			this.documento.setVlTotal(BigDecimal.ZERO);

			for (int i = 0; i < this.documento.getListaDocumentoItemMaterial().size(); i++) {
				if (((DocumentoItemMaterial) this.documento.getListaDocumentoItemMaterial().get(i))
						.getVlPeriodo() == null) {
					((DocumentoItemMaterial) this.documento.getListaDocumentoItemMaterial().get(i))
							.setVlPeriodo(BigDecimal.ZERO);
				}
				if (((DocumentoItemMaterial) this.documento.getListaDocumentoItemMaterial().get(i))
						.getVlAcumulado() == null) {
					((DocumentoItemMaterial) this.documento.getListaDocumentoItemMaterial().get(i))
							.setVlAcumulado(BigDecimal.ZERO);
				}

				this.documentoItemMaterial.setVlAcumulado(this.documentoItemMaterial.getVlPeriodo()
						.add(((DocumentoItemMaterial) this.documento.getListaDocumentoItemMaterial().get(i))
								.getVlAcumulado()));
				this.documento.setVlTotal(this.documento.getVlTotal()
						.add(((DocumentoItemMaterial) this.documento.getListaDocumentoItemMaterial().get(i))
								.getVlPeriodo()));
			}
		} else {
			this.documentoItemMaterial.setVlAcumulado(this.documentoItemMaterial.getVlPeriodo());
		}

		this.documentoItemMaterial.setQtdeAcumulado(
				this.documentoItemMaterial.getQtdeAnterior().add(this.documentoItemMaterial.getQtdePeriodo()));
		this.documento.setVlTotal(this.documento.getVlTotal().add(this.documentoItemMaterial.getVlPeriodo()));
	}

	public void calcularAcumulado() {
		BigDecimal acumulado = new BigDecimal(BigInteger.ZERO);

		for (DocumentoItemMaterial lista : this.documento.getListaDocumentoItemMaterial()) {
			if (lista.getVlPeriodo() == null) {
				lista.setVlPeriodo(BigDecimal.ZERO);
			}
			lista.setVlAcumulado(BigDecimal.ZERO);
			acumulado = acumulado.add(lista.getVlPeriodo());
			lista.setVlAcumulado(acumulado);
			this.documento.setVlTotal(acumulado.setScale(2, 0));
		}
	}

	public void buscarPreco() {
		for (MaterialPreco preco : this.listaMaterialPreco) {
			if (preco.getSeqMaterial().equals(this.documentoItemMaterial.getSeqMaterial())) {
				this.materialPreco = preco;
			}
		}

		this.precoMinimo = this.materialPreco.getPrecoMin();
		this.precoMaximo = this.materialPreco.getPrecoMax();

		this.documentoItemMaterial.setVlUnitario(this.materialPreco.getPreco());
		this.documentoItemMaterial.setVlDesconto(BigDecimal.ZERO);
		this.documentoItemMaterial.setPrecoTabela(this.materialPreco.getPreco());
	}

	public void buscarServicoNome() {

			this.listaMaterialPreco = this.materialPrecoService.listarNomeGrupoServico(
					this.loginController.getUsuario().getSeqEmpresa(),"", this.documento.getSeqParceiro());
			
		
	}

	public void calcularTotalItem() {
		if (this.documentoItemMaterial.getVlDesconto() == null) {
			this.documentoItemMaterial.setPrecoTotal(
					this.documentoItemMaterial.getQtde().multiply(this.documentoItemMaterial.getVlUnitario()));
		} else {
			this.documentoItemMaterial.setPrecoTotal(
					this.documentoItemMaterial.getQtde().multiply(this.documentoItemMaterial.getVlUnitario())
							.subtract(this.documentoItemMaterial.getVlDesconto()));
		}
	}

	public void salvarDocumentoItemServico() {
		for (DocumentoEscopo escopo : this.listaDocumentoEscopo) {
			if (escopo.getSeqServicoEscopo().equals(this.documentoItemServico.getSeqServicoEscopo())) {
				this.documentoItemServico.setServicoEscopoNome(escopo.getNomeServicoEscopo());
			}
		}

		for (Colaborador colaborador : this.listaColaborador) {
			if (colaborador.getSeqColaborador().equals(this.documentoItemServico.getSeqColaborador())) {
				this.documentoItemServico.setColaboradorNome(colaborador.getNome());
			}
		}

		this.documento.getListaDocumentoItemServico().add(this.documentoItemServico);

		this.documentoItemServico = new DocumentoItemServico();
	}

	public void selecionarDocumentoItemServico(DocumentoItemServico pDocumentoItemServico) {
		this.documentoItemServico = pDocumentoItemServico;
		this.documento.getListaDocumentoItemServico().remove(pDocumentoItemServico);
	}

	public void removerDocumentoItemServico(DocumentoItemServico pDocumentoItemServico) {
		this.documento.getListaDocumentoItemServico().remove(pDocumentoItemServico);
	}

	public void salvar(int pTela) {
		this.documento.setSeqTipoDocumento(this.idTipoDocumento);
		this.documento.setSeqEmpresa(this.loginController.getEmpresa().getSeqEmpresa());

		this.documento.setSeqUsuarioCadastro(this.loginController.getUsuario().getSeqUsuario());
		this.documento.setSeqUnidadeNegocio(this.unidadeNegocio.getSeqUnidadeNegocio());

		if (this.documento.getIdRevisao() == null) {
			this.documento.setIdRevisao(Long.valueOf(0L));
		}
		if (this.documento.getIdComplementar() == null) {
			this.documento.setIdComplementar(Long.valueOf(0L));
		}
		if (this.documento.getQtdeParcela() == null) {
			this.documento.setQtdeParcela(new BigDecimal(0));
		}
		this.documento.getListaDocumentoItemEquipamento().clear();
		if (this.bEquipamento) {
			for (String eqp : this.equipamentoSelecionado) {
				DocumentoItemEquipamento deq = new DocumentoItemEquipamento();
				deq.setSeqEquipamento(eqp);
				this.documento.getListaDocumentoItemEquipamento().add(deq);
			}
		}

		this.documento.getListaDocumentoItemEmbarcacao().clear();
		if (this.bEmbarcacao) {
			for (String emb : this.embarcacaoSelecionado) {
				DocumentoItemEmbarcacao e = new DocumentoItemEmbarcacao();
				e.setSeqNvEmbarcacao(emb);
				this.documento.getListaDocumentoItemEmbarcacao().add(e);
			}
		}

		
                if (this.documento.getTaxaCambio() != null) {
				this.documento.setVltotalcambio(documento.getVlTotal().multiply(this.documento.getTaxaCambio()));
						
                
                }
                if (this.documento.getObservacao() != null) {
			this.documento.setObservacao(this.documento.getObservacao().replace("font-size: 13.3333px;", ""));
			this.documento.setObservacao(this.documento.getObservacao().replace("font-size: 10pt;", ""));
		}

		if (this.documento.getDescricao() != null) {
			this.documento.setDescricao(this.documento.getDescricao().replace("font-size: 13.3333px;", ""));
			this.documento.setDescricao(this.documento.getDescricao().replace("font-size: 10pt;", ""));
		}

		if (this.documento.getEscopo() != null) {
			this.documento.setEscopo(this.documento.getEscopo().replace("font-size: 13.3333px;", ""));
			this.documento.setEscopo(this.documento.getEscopo().replace("font-size: 10pt;", ""));
		}

                if (this.documento.getObjetivo() != null) {
			this.documento.setObjetivo(this.documento.getObjetivo().replace("font-size: 13.3333px;", ""));
			this.documento.setObjetivo(this.documento.getObjetivo().replace("font-size: 10pt;", ""));
		}
                
                if (this.documento.getCondicoesGerais() != null) {
			this.documento.setCondicoesGerais(this.documento.getCondicoesGerais().replace("font-size: 13.3333px;", ""));
			this.documento.setCondicoesGerais(this.documento.getCondicoesGerais().replace("font-size: 10pt;", ""));
		}

                if (this.documento.getDescRemuneracao() != null) {
			this.documento.setDescRemuneracao(this.documento.getDescRemuneracao().replace("font-size: 13.3333px;", ""));
			this.documento.setDescRemuneracao(this.documento.getDescRemuneracao().replace("font-size: 10pt;", ""));
		}

                if (this.documento.getValidade() != null) {
			this.documento.setValidade(this.documento.getValidade().replace("font-size: 13.3333px;", ""));
			this.documento.setValidade(this.documento.getValidade().replace("font-size: 10pt;", ""));
		}

                if (this.documento.getCondpag() != null) {
			this.documento.setCondpag(this.documento.getCondpag().replace("font-size: 13.3333px;", ""));
			this.documento.setCondpag(this.documento.getCondpag().replace("font-size: 10pt;", ""));
		}                
                              
		this.documento = this.documentoService.salvar(this.documento);

		for (DocumentoComentario pdocumentoComentario : this.listaDocumentoComentario) {
			pdocumentoComentario.setSeqDocumento(this.documento.getSeqDocumento());
			this.documentoComentarioService.salvar(pdocumentoComentario);
		}

		if (pTela == 0) {
			if (this.documento == null) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, " Não foi possível salvar.", ""));
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						this.documento.getTipoDocumento() + " armazenado com sucesso.", ""));
				if (this.idDocumento.equals("0")) {
					this.loginController.mudarPagina(
							"/documento/documento.jsf?idDocumento=" + String.valueOf(this.documento.getSeqDocumento())
									+ "&idTipoDocumento=" + String.valueOf(this.documento.getSeqTipoDocumento()));
				}
				this.tela = Integer.valueOf(0);
			}
		} else if (pTela == 1) {
			this.loginController.mudarPagina("/documento/documento.jsf?idDocumento=0&idTipoDocumento="
					+ String.valueOf(this.documento.getSeqTipoDocumento()));
		}
	}

	public void imprimir() throws IOException, JRException {
		
            String caminho = this.tipoDocumento.getCaminhorel();
                try{
			Conexao conexao = new Conexao();
			Connection conn = Conexao.getConnection();
			Util util = new Util();
			Documento pDocumento = new Documento();
			String pTipoDocumento = "";
	
			System.out.println(this.documento.getObservacao());
			HashMap parametro = new HashMap();
			parametro.put("pSeqDocumento", Integer.valueOf(this.documento.getSeqDocumento()));
	
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.responseComplete();
			ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
	
			JasperPrint jasperPrint = new JasperPrint();
			System.out.println("===> " + this.documento.getTemplate());
			if (this.documento.getTemplate().equals("M1")) {
				pTipoDocumento = "Proposta";
                                jasperPrint = JasperFillManager.fillReport(scontext.getRealPath(this.tipoDocumento.getCaminhorel()), parametro,conn);
				/*jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/relatorio/aws/P1/P1.jasper"), parametro,conn);*/
			} else if (this.documento.getTemplate().equals("M2")) {
				pTipoDocumento = "SalvageMaster";
				jasperPrint = JasperFillManager.fillReport(scontext.getRealPath(this.tipoDocumento.getCaminhorel()), parametro,conn);
                                /*jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/relatorio/aws/P2/P2.jasper"), parametro,conn);*/
			} else if (this.documento.getTemplate().equals("M3")) {
				pTipoDocumento = "Engenharia";
				jasperPrint = JasperFillManager.fillReport(scontext.getRealPath(this.tipoDocumento.getCaminhorel()), parametro,conn);
                                /*jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/relatorio/aws/P3/P3_EV.jasper"), parametro,conn);*/
			} else if (this.documento.getTemplate().equals("M4")) {
				pTipoDocumento = "RegularizacaoEstatutaria";
                                jasperPrint = JasperFillManager.fillReport(scontext.getRealPath(this.tipoDocumento.getCaminhorel()), parametro,conn);
				/*jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/relatorio/aws/P4/P4.jasper"), parametro,conn);*/
			} else if (this.documento.getTemplate().equals("M5")) {
				pTipoDocumento = "OrdemServico";
				jasperPrint = JasperFillManager.fillReport(scontext.getRealPath(this.tipoDocumento.getCaminhorel()), parametro,conn);
                                /*jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/relatorio/aws/P5/P5.jasper"), parametro,conn);*/
			} else if (this.documento.getTemplate().equals("M6")) {
				pTipoDocumento = "OrdemCobranca";
				if (this.documento.getDocDonoSeqTipo().equals("301")) {
					parametro.put("pSeqOrdemServico", Integer.valueOf(this.documento.getSeqDocumentoDono()));
					pDocumento = this.documentoService.buscarPorID(this.loginController.getUsuario().getSeqEmpresa(),
							this.documento.getSeqDocumentoDono());
					parametro.put("pSeqProposta", Integer.valueOf(pDocumento.getSeqDocumentoDono()));
				} else if (this.documento.getDocDonoSeqTipo().equals(this.documento.getSeqTipoDocumento())) {
					pDocumento = this.documentoService.buscarPorID(this.loginController.getUsuario().getSeqEmpresa(),
							this.documento.getSeqDocumentoDono());
	
					while (pDocumento.getSeqTipoDocumento().equals(this.documento.getSeqTipoDocumento())) {
						pDocumento = this.documentoService.buscarPorID(this.loginController.getUsuario().getSeqEmpresa(),
								pDocumento.getSeqDocumentoDono());
					}
	
					parametro.put("pSeqProposta", Integer.valueOf(pDocumento.getSeqDocumento()));
					pDocumento = (Documento) this.documentoService.listarPorTipoSeq("301", pDocumento.getSeqDocumento())
							.get(0);
					parametro.put("pSeqOrdemServico", Integer.valueOf(pDocumento.getSeqDocumento()));
				} else {
					parametro.put("pSeqProposta", Integer.valueOf(this.documento.getSeqDocumentoDono()));
					pDocumento = (Documento) this.documentoService
							.listarPorTipoSeq("301", this.documento.getSeqDocumentoDono()).get(0);
					parametro.put("pSeqOrdemServico", Integer.valueOf(pDocumento.getSeqDocumento()));
				}
				jasperPrint = JasperFillManager.fillReport(scontext.getRealPath(this.tipoDocumento.getCaminhorel()), parametro,conn);
                                /*jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/relatorio/aws/P6/P6.jasper"), parametro,conn);*/
			} else if (this.documento.getTemplate().equals("M7")) {
				pTipoDocumento = "VistoriaSeguro";
                                jasperPrint = JasperFillManager.fillReport(scontext.getRealPath(this.tipoDocumento.getCaminhorel()), parametro,conn);
				/*jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/relatorio/aws/P7/P7.jasper"), parametro,conn);*/
			} else if (this.documento.getTemplate().equals("M8")) {
				pTipoDocumento = "Auditoria";
				jasperPrint = JasperFillManager.fillReport(scontext.getRealPath(this.tipoDocumento.getCaminhorel()), parametro,conn);
                                /*jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/relatorio/aws/P8/P8.jasper"), parametro,conn);*/
			} else if (this.documento.getTemplate().equals("M9")) {
				pTipoDocumento = "ConditionSurvey";
				jasperPrint = JasperFillManager.fillReport(scontext.getRealPath(this.tipoDocumento.getCaminhorel()), parametro,conn);
                                /*jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/relatorio/aws/P9/P9.jasper"), parametro,
						conn);*/
                        }  else if (this.documento.getTemplate().equals("M10")) {
				/*pTipoDocumento = "ServicoJarle";*/
				jasperPrint = JasperFillManager.fillReport(scontext.getRealPath(this.tipoDocumento.getCaminhorel()), parametro,conn);
                                /*jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/relatorio/aws/P10/P10.jasper"), parametro, conn);*/
			} else if (this.documento.getTemplate().equals("BM")) {
				pTipoDocumento = "BoletimMedicao";
				parametro.put("pSeqUnidadeNegocio", Integer.valueOf(this.unidadeNegocio.getSeqUnidadeNegocio()));
			        jasperPrint = JasperFillManager.fillReport(scontext.getRealPath(this.tipoDocumento.getCaminhorel()), parametro,conn);
                                /*JasperFillManager.fillReport(scontext.getRealPath("/relatorio/aws/BoletimMedicao/BoletimMedicao.jasper"), parametro, conn);*/
			}
	
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
			exporter.exportReport();
			byte[] bytes = baos.toByteArray();
	
			if ((bytes != null) && (bytes.length > 0)) {
				HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
				response.setContentType("application/pdf");
				response.setHeader("Content-disposition",
						"inline; filename=\"" + pTipoDocumento + "_" + this.documento.getCodigo() + ".pdf\"");
				response.setContentLength(bytes.length);
				ServletOutputStream outputStream = response.getOutputStream();
				outputStream.write(bytes, 0, bytes.length);
				outputStream.flush();
				outputStream.close();
			}
		}catch (Exception ex){
			Logger.getLogger(DocumentoController.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}
        
	public void imprimirIngles() throws IOException, JRException {
		
            String caminho = this.tipoDocumento.getCaminhorel();
                try{
			Conexao conexao = new Conexao();
			Connection conn = Conexao.getConnection();
			Util util = new Util();
			Documento pDocumento = new Documento();
			String pTipoDocumento = "";
	
			System.out.println(this.documento.getObservacao());
			HashMap parametro = new HashMap();
			parametro.put("pSeqDocumento", Integer.valueOf(this.documento.getSeqDocumento()));
	
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.responseComplete();
			ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
	
			JasperPrint jasperPrint = new JasperPrint();
			System.out.println("===> " + this.documento.getTemplate());
			if (this.documento.getTemplate().equals("M1")) {
				pTipoDocumento = "EquipamentoAcessorios";
                                jasperPrint = JasperFillManager.fillReport(scontext.getRealPath(this.tipoDocumento.getCaminhorelI()), parametro,conn);
				/*jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/relatorio/aws/P1/P1.jasper"), parametro,conn);*/
			} else if (this.documento.getTemplate().equals("M2")) {
				pTipoDocumento = "SalvageMaster";
				jasperPrint = JasperFillManager.fillReport(scontext.getRealPath(this.tipoDocumento.getCaminhorelI()), parametro,conn);
                                /*jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/relatorio/aws/P2/P2.jasper"), parametro,conn);*/
			} else if (this.documento.getTemplate().equals("M3")) {
				pTipoDocumento = "Engenharia";
				jasperPrint = JasperFillManager.fillReport(scontext.getRealPath(this.tipoDocumento.getCaminhorelI()), parametro,conn);
                                /*jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/relatorio/aws/P3/P3_EV.jasper"), parametro,conn);*/
			} else if (this.documento.getTemplate().equals("M4")) {
				pTipoDocumento = "RegularizacaoEstatutaria";
                                jasperPrint = JasperFillManager.fillReport(scontext.getRealPath(this.tipoDocumento.getCaminhorelI()), parametro,conn);
				/*jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/relatorio/aws/P4/P4.jasper"), parametro,conn);*/
			} else if (this.documento.getTemplate().equals("M5")) {
				pTipoDocumento = "OrdemServico";
				jasperPrint = JasperFillManager.fillReport(scontext.getRealPath(this.tipoDocumento.getCaminhorelI()), parametro,conn);
                                /*jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/relatorio/aws/P5/P5.jasper"), parametro,conn);*/
			} else if (this.documento.getTemplate().equals("M6")) {
				pTipoDocumento = "OrdemCobranca";
				if (this.documento.getDocDonoSeqTipo().equals("301")) {
					parametro.put("pSeqOrdemServico", Integer.valueOf(this.documento.getSeqDocumentoDono()));
					pDocumento = this.documentoService.buscarPorID(this.loginController.getUsuario().getSeqEmpresa(),
							this.documento.getSeqDocumentoDono());
					parametro.put("pSeqProposta", Integer.valueOf(pDocumento.getSeqDocumentoDono()));
				} else if (this.documento.getDocDonoSeqTipo().equals(this.documento.getSeqTipoDocumento())) {
					pDocumento = this.documentoService.buscarPorID(this.loginController.getUsuario().getSeqEmpresa(),
							this.documento.getSeqDocumentoDono());
	
					while (pDocumento.getSeqTipoDocumento().equals(this.documento.getSeqTipoDocumento())) {
						pDocumento = this.documentoService.buscarPorID(this.loginController.getUsuario().getSeqEmpresa(),
								pDocumento.getSeqDocumentoDono());
					}
	
					parametro.put("pSeqProposta", Integer.valueOf(pDocumento.getSeqDocumento()));
					pDocumento = (Documento) this.documentoService.listarPorTipoSeq("301", pDocumento.getSeqDocumento())
							.get(0);
					parametro.put("pSeqOrdemServico", Integer.valueOf(pDocumento.getSeqDocumento()));
				} else {
					parametro.put("pSeqProposta", Integer.valueOf(this.documento.getSeqDocumentoDono()));
					pDocumento = (Documento) this.documentoService
							.listarPorTipoSeq("301", this.documento.getSeqDocumentoDono()).get(0);
					parametro.put("pSeqOrdemServico", Integer.valueOf(pDocumento.getSeqDocumento()));
				}
				jasperPrint = JasperFillManager.fillReport(scontext.getRealPath(this.tipoDocumento.getCaminhorelI()), parametro,conn);
                                /*jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/relatorio/aws/P6/P6.jasper"), parametro,conn);*/
			} else if (this.documento.getTemplate().equals("M7")) {
				pTipoDocumento = "VistoriaSeguro";
                                jasperPrint = JasperFillManager.fillReport(scontext.getRealPath(this.tipoDocumento.getCaminhorelI()), parametro,conn);
				/*jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/relatorio/aws/P7/P7.jasper"), parametro,conn);*/
			} else if (this.documento.getTemplate().equals("M8")) {
				pTipoDocumento = "Auditoria";
				jasperPrint = JasperFillManager.fillReport(scontext.getRealPath(this.tipoDocumento.getCaminhorelI()), parametro,conn);
                                /*jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/relatorio/aws/P8/P8.jasper"), parametro,conn);*/
			} else if (this.documento.getTemplate().equals("M9")) {
				pTipoDocumento = "ConditionSurvey";
				jasperPrint = JasperFillManager.fillReport(scontext.getRealPath(this.tipoDocumento.getCaminhorelI()), parametro,conn);
                                /*jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/relatorio/aws/P9/P9.jasper"), parametro,
						conn);*/
                        }  else if (this.documento.getTemplate().equals("M10")) {
				pTipoDocumento = "ServicoJarle";
				jasperPrint = JasperFillManager.fillReport(scontext.getRealPath(this.tipoDocumento.getCaminhorelI()), parametro,conn);
                                /*jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/relatorio/aws/P10/P10.jasper"), parametro, conn);*/
			} else if (this.documento.getTemplate().equals("BM")) {
				pTipoDocumento = "BoletimMedicao";
				parametro.put("pSeqUnidadeNegocio", Integer.valueOf(this.unidadeNegocio.getSeqUnidadeNegocio()));
			        jasperPrint = JasperFillManager.fillReport(scontext.getRealPath(this.tipoDocumento.getCaminhorelI()), parametro,conn);
                                /*JasperFillManager.fillReport(scontext.getRealPath("/relatorio/aws/BoletimMedicao/BoletimMedicao.jasper"), parametro, conn);*/
			}
	
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
			exporter.exportReport();
			byte[] bytes = baos.toByteArray();
	
			if ((bytes != null) && (bytes.length > 0)) {
				HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
				response.setContentType("application/pdf");
				response.setHeader("Content-disposition",
						"inline; filename=\"" + pTipoDocumento + "_" + this.documento.getCodigo() + ".pdf\"");
				response.setContentLength(bytes.length);
				ServletOutputStream outputStream = response.getOutputStream();
				outputStream.write(bytes, 0, bytes.length);
				outputStream.flush();
				outputStream.close();
			}
		}catch (Exception ex){
			Logger.getLogger(DocumentoController.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}        

	public void novo() {
		this.loginController.mudarPagina("/documento/documento.jsf?id=0");
		this.tela = Integer.valueOf(1);
	}

	public void deletar() {
		if (this.documentoService.deletar(this.documento)) {
			this.documento = new Documento();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro eliminado com sucesso.", ""));
			this.unidadeNegocio = new UnidadeNegocio();
			this.tela = Integer.valueOf(0);
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao excluir registro.", ""));
		}
	}

	public void fecharTela() throws IOException {
		this.loginController.mudarPagina("/principal/ principal.jsf");
	}

	public void selecionar(Documento pDocumento) {
		this.documento = pDocumento;
		this.tela = Integer.valueOf(1);
	}

	public void mudarTela(Integer pTela) {
		this.tela = pTela;
	}

	public void salvarComentario() {
		String msg = "";
		if (!this.msgCancelamentoAnterior.equals("")) {
			msg = "Motivo de Cancelamento alterado.<br/> De: " + this.msgCancelamentoAnterior + "<br/> Para: "
					+ this.documento.getComentarioEtapa() + ".";

			this.documentoOcorrencia = new DocumentoOcorrencia();
			this.documentoOcorrencia.setSeqDocumento(this.documento.getSeqDocumento());
			this.documentoOcorrencia.setTipo("Automática");
			this.documentoOcorrencia.setDescricao(msg);
			this.documentoOcorrencia.setSeqUsuario(this.loginController.getUsuario().getSeqUsuario());
			this.documentoOcorrenciaService.salvar(this.documentoOcorrencia);
		} else if (this.msgCancelamentoAnterior.equals("")) {
			switch (this.idTipoDocumento) {
			case "301":
				msg = "Ocorrência de Mudança de Etapa <br/> Etapa Anterior: " + this.documento.getEtapa()
						+ "<br> Etapa Posterior: Cancelada.<br/> Motivo do Cancelamento: "
						+ this.documento.getComentarioEtapa();
				this.documento.setSeqDocumentoEtapa("521");
				break;
			case "401":
				msg = "Ocorrência de Mudança de Etapa <br/> Etapa Anterior: " + this.documento.getEtapa()
						+ "<br> Etapa Posterior: Rejeitado.<br/> Motivo do Cancelamento: "
						+ this.documento.getComentarioEtapa();
				this.documento.setSeqDocumentoEtapa("422");
				break;
			case "422":
				msg = "Ocorrência de Mudança de Etapa <br/> Etapa Anterior: " + this.documento.getEtapa()
						+ "<br> Etapa Posterior: Rejeitado.<br/> Motivo do Cancelamento: "
						+ this.documento.getComentarioEtapa();
				this.documento.setSeqDocumentoEtapa("506");
				break;
			case "421":
				msg = "Ocorrência de Mudança de Etapa <br/> Etapa Anterior: " + this.documento.getEtapa()
						+ "<br> Etapa Posterior: Rejeitado.<br/> Motivo do Cancelamento: "
						+ this.documento.getComentarioEtapa();
				this.documento.setSeqDocumentoEtapa("503");
				break;
			case "381":
				msg = "Ocorrência de Mudança de Etapa <br/> Etapa Anterior: " + this.documento.getEtapa()
						+ "<br> Etapa Posterior: Rejeitado.<br/> Motivo do Cancelamento: "
						+ this.documento.getComentarioEtapa();
				this.documento.setSeqDocumentoEtapa("401");
				break;
			case "283":
				msg = "Ocorrência de Mudança de Etapa <br/> Etapa Anterior: " + this.documento.getEtapa()
						+ "<br> Etapa Posterior: Rejeitado.<br/> Motivo do Cancelamento: "
						+ this.documento.getComentarioEtapa();
				this.documento.setSeqDocumentoEtapa("269");
				break;
			case "282":
				msg = "Ocorrência de Mudança de Etapa <br/> Etapa Anterior: " + this.documento.getEtapa()
						+ "<br> Etapa Posterior: Rejeitado.<br/> Motivo do Cancelamento: "
						+ this.documento.getComentarioEtapa();
				this.documento.setSeqDocumentoEtapa("266");
				break;
			case "281":
				msg = "Ocorrência de Mudança de Etapa <br/> Etapa Anterior: " + this.documento.getEtapa()
						+ "<br> Etapa Posterior: Rejeitado.<br/> Motivo do Cancelamento: "
						+ this.documento.getComentarioEtapa();
				this.documento.setSeqDocumentoEtapa("263");
				break;
			case "221":
				msg = "Ocorrência de Mudança de Etapa <br/> Etapa Anterior: " + this.documento.getEtapa()
						+ "<br> Etapa Posterior: Rejeitado.<br/> Motivo do Cancelamento: "
						+ this.documento.getComentarioEtapa();
				this.documento.setSeqDocumentoEtapa("301");
				break;
			case "423":
				msg = "Ocorrência de Mudança de Etapa <br/> Etapa Anterior: " + this.documento.getEtapa()
						+ "<br> Etapa Posterior: Rejeitado.<br/> Motivo do Cancelamento: "
						+ this.documento.getComentarioEtapa();
				this.documento.setSeqDocumentoEtapa("509");
			}

			this.documentoOcorrencia = new DocumentoOcorrencia();
			this.documentoOcorrencia.setSeqDocumento(this.documento.getSeqDocumento());
			this.documentoOcorrencia.setTipo("Automática");
			this.documentoOcorrencia.setDescricao(msg);
			this.documentoOcorrencia.setSeqUsuario(this.loginController.getUsuario().getSeqUsuario());
			this.documentoOcorrenciaService.salvar(this.documentoOcorrencia);
		}
		this.documento = this.documentoService.salvar(this.documento);

		this.loginController
				.mudarPagina("/documento/documento.jsf?idDocumento=" + String.valueOf(this.documento.getSeqDocumento())
						+ "&idTipoDocumento=" + String.valueOf(this.documento.getSeqTipoDocumento()));
	}

	public void enviarParaProximaEtapa(DocumentoEtapa pDocumentoEtapa) {
		String etapaInicial = this.documento.getEtapa();
		String etapaFinal = pDocumentoEtapa.getNome();

		this.documento.setSeqDocumentoEtapa(pDocumentoEtapa.getSeqDocumentoEtapa());

		this.documento = this.documentoService.salvar(this.documento);
		String msg = "Ocorrência de Mudança de Etapa <br/> Etapa Anterior: " + etapaInicial + "<br> Etapa Posterior: "
				+ etapaFinal;

		this.documentoOcorrencia = new DocumentoOcorrencia();
		this.documentoOcorrencia.setSeqDocumento(this.documento.getSeqDocumento());
		this.documentoOcorrencia.setTipo("Automática");
		this.documentoOcorrencia.setDescricao(msg);
		this.documentoOcorrencia.setSeqUsuario(this.loginController.getUsuario().getSeqUsuario());
		this.documentoOcorrenciaService.salvar(this.documentoOcorrencia);

		this.loginController
				.mudarPagina("/documento/documento.jsf?idDocumento=" + String.valueOf(this.documento.getSeqDocumento())
						+ "&idTipoDocumento=" + String.valueOf(this.documento.getSeqTipoDocumento()));

		if ((pDocumentoEtapa.getNome().equals("Finalizada")) && (this.documento.getSeqTipoDocumento().equals("301"))) {
			enviarPesquisaSatisfacao();
		}
	}

	public void enviarPesquisaSatisfacao() {
		ClausulaWhere condicao = new ClausulaWhere();
		MimeBodyPart textPart = new MimeBodyPart();
		Multipart mps = new MimeMultipart();

		condicao.AdicionarCondicaoManual(" where parceiro.seq_parceiro = " + this.documento.getSeqParceiro()
				+ " and parceiro.seq_empresa = " + this.loginController.empresa.getSeqEmpresa());

		Parceiro pParceiro = (Parceiro) this.parceiroService.listarParceiroFiltro(condicao).get(0);

		condicao = new ClausulaWhere();
		condicao.AdicionarCondicaoManual(" where documento.seq_documento = " + this.documento.getSeqDocumentoDono());

		Documento pDocDono = (Documento) this.documentoService.listarDocumentoFiltro(condicao).get(0);

		String pConteudo = "<b>" + pParceiro.getNome()
				+ "</b>,<br><br><br><br><br> A <b>Ordem de Serviço</b> de Código <b>" + this.documento.getCodigo()
				+ "</b>, da " + pDocDono.getTipoDocumento() + " - " + pDocDono.getCodigo()
				+ ", foi finalizada.<br><br> Para que possamos implementar ações para melhor atendê-lo, solicitamos que participe da Pesquisa de Satisfação <a href='http://191.252.65.76/erp/pesquisa.jsf?se=61&sd="
				+ this.documento.getSeqDocumento() + "&spc=" + pParceiro.getSeqParceiro()
				+ "'>Clicando Aqui</a>.<br><br><br><br><br>Agradecemos a preferência,<br><br><br><br>Atenciosamente,<br><br><br><br><b>AWS SERVICE</b><br/><h5><b>**E-mail enviado de forma automática, favor não responder.**</b></h5>";

		try {
			textPart.setContent(pConteudo, "text/html");
		} catch (MessagingException ex) {
			Logger.getLogger(DocumentoController.class.getName()).log(Level.SEVERE, null, ex);
		}
		try {
			mps.addBodyPart(textPart);
		} catch (MessagingException ex) {
			Logger.getLogger(DocumentoController.class.getName()).log(Level.SEVERE, null, ex);
		}
		if (!this.documentoService.enviarEmailParceiro(pParceiro.getEmail().trim(), mps)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Falha ao enviar a Pesquisa de Satisfação ao Cliente. Favor verificar se o e-mail cadastrado é válido.",
							""));
		}
	}

	public void gerarNovoDocumento(TipoDocumentoGerar pTipoDocumentoGerar)
  {
    TipoDocumento tp = new TipoDocumento();
    tp = this.tipoDocumentoService.buscarPorId(pTipoDocumentoGerar.getSeqTipoDocumentoFilho());
    
    Documento documentoNovo = new Documento();
    documentoNovo = this.documentoService.buscarPorID(this.documento.getSeqEmpresa(), this.documento.getSeqDocumento());
    documentoNovo.setSeqDocumento(null);
    documentoNovo.setCodigo(null);
    documentoNovo.setSeqTipoDocumento(tp.getSeqTipoDocumento());
    documentoNovo.setSeqDocumentoDono(this.documento.getSeqDocumento());
    documentoNovo.setSequencia(0);
    documentoNovo.setIdRevisao(Long.valueOf(0L));
    documentoNovo.setIdComplementar(Long.valueOf(0L));
    boolean possuiProximaEtapa = false;
    if (this.documento.getSeqTipoDocumento().equals("381")) {
      for (DocumentoEtapa pEtapa : this.listaDocumentoEtapa) {
        if (pEtapa.getSeqDocumentoEtapa().equals("386")) {
          enviarParaProximaEtapa(pEtapa);
          possuiProximaEtapa = true;
        }
      }
    }
    if (documentoNovo.getSeqTipoDocumento().equals("401")) {
      documentoNovo.setObservacao(this.documento.getObservacao());
    }
    
    if (documentoNovo.getSeqTipoDocumento().equals("301")) {
      documentoNovo.setDescricao(this.documento.getDescricao());
      documentoNovo.setEscopo(this.documento.getEscopo());
      if (!this.embarcacaoSelecionado.isEmpty()) {
        for (NvEmbarcacaoParceiro nvEmbarcacaoParceiro : this.listaNvEmbarcacaoParceiro) {
          for (String embarcacaoParceiro : this.embarcacaoSelecionado) {
            if (nvEmbarcacaoParceiro.getSeqEmbarcacao().equals(embarcacaoParceiro)) {
              this.nvEmbarcacao = this.nvEmbarcacaoService.buscar(nvEmbarcacaoParceiro.getSeqEmbarcacao());
              




              String detalhesEmbarcação = "\n Detalhes da Embarcação " + this.nvEmbarcacao.getNome() + " : Arqueação Bruta (AB): " + this.nvEmbarcacao.getArqueacaoBruta() + ";Área de Navegação: " + this.nvEmbarcacao.getAreaNavegacao() + ";Comprimento Total: " + this.nvEmbarcacao.getComprimentoTotal() + ";Tipo de Embarcação: " + this.nvEmbarcacao.getTipoEmbarcacaoNome() + ". \n";
              
              System.out.println("Detalhes da embarcação:" + detalhesEmbarcação);
              documentoNovo.setDescricao(documentoNovo.getDescricao().concat(detalhesEmbarcação));
            }
          }
        }
      }
    } 
     if (documentoNovo.getSeqTipoDocumento().equals("381")) {
      documentoNovo.setDescricao(this.documento.getDescricao());
     }
      else {
      documentoNovo.setDescricao("");
    }
    documentoNovo = this.documentoService.salvar(documentoNovo);
    

    String msgNovo = tp.getNome() + ", gerado a partir de " + this.documento.getTipoDocumento() + " Código:" + this.documento.getCodigo();
    DocumentoOcorrencia documentoOcorrenciaNovo = new DocumentoOcorrencia();
    
    documentoOcorrenciaNovo.setSeqDocumento(documentoNovo.getSeqDocumento());
    documentoOcorrenciaNovo.setTipo("Automática");
    
    documentoOcorrenciaNovo.setDescricao(msgNovo);
    
    documentoOcorrenciaNovo.setSeqUsuario(this.loginController.getUsuario().getSeqUsuario());
    this.documentoOcorrenciaService.salvar(documentoOcorrenciaNovo);
    

    String msg = tp.getNome() + " gerado com sucesso!";
    this.documentoOcorrencia = new DocumentoOcorrencia();
    
    this.documentoOcorrencia.setSeqDocumento(this.documento.getSeqDocumento());
    this.documentoOcorrencia.setTipo("Automática");
    
    this.documentoOcorrencia.setDescricao(msg);
    
    this.documentoOcorrencia.setSeqUsuario(this.loginController.getUsuario().getSeqUsuario());
    this.documentoOcorrenciaService.salvar(this.documentoOcorrencia);
    
    if(!possuiProximaEtapa) {
	    this.loginController.mudarPagina("/documento/documento.jsf?idDocumento=" + 
	      String.valueOf(documentoNovo.getSeqDocumento()) + "&idTipoDocumento=" + String.valueOf(documentoNovo.getSeqTipoDocumento()));
    }
  }

	public void gerarNovo(int tipo) {
		switch (tipo) {
		case 0:
			System.out.println("==> COMPLEMENTAR");

			Documento documentoNovoC = new Documento();
			documentoNovoC = this.documentoService.buscarPorID(this.documento.getSeqEmpresa(),
					this.documento.getSeqDocumento());
			documentoNovoC.setSeqDocumento(null);
			documentoNovoC.setCodigo(null);
			documentoNovoC.setSeqTipoDocumento(this.documento.getSeqTipoDocumento());
			documentoNovoC.setSeqDocumentoDono(this.documento.getSeqDocumento());
			documentoNovoC.setSequencia(0);
			documentoNovoC.setIdComplementar(Long.valueOf(this.documento.getIdComplementar().longValue() + 1L));
			documentoNovoC.setIdRevisao(Long.valueOf(0L));
			documentoNovoC.setDescricao("");

			if (!documentoNovoC.getListaDocumentoItemMaterial().isEmpty()) {
				for (int c = 0; c < documentoNovoC.getListaDocumentoItemMaterial().size(); c++) {
					((DocumentoItemMaterial) documentoNovoC.getListaDocumentoItemMaterial().get(c)).setQtdeAnterior(
							((DocumentoItemMaterial) this.documento.getListaDocumentoItemMaterial().get(c))
									.getQtdeAcumulado());
					((DocumentoItemMaterial) documentoNovoC.getListaDocumentoItemMaterial().get(c))
							.setQtdePeriodo(BigDecimal.ZERO);
					((DocumentoItemMaterial) documentoNovoC.getListaDocumentoItemMaterial().get(c)).setQtdeAcumulado(
							((DocumentoItemMaterial) this.documento.getListaDocumentoItemMaterial().get(c))
									.getQtdeAnterior().add(((DocumentoItemMaterial) this.documento
											.getListaDocumentoItemMaterial().get(c)).getQtdePeriodo()));

					((DocumentoItemMaterial) documentoNovoC.getListaDocumentoItemMaterial().get(c))
							.setVlPeriodo(BigDecimal.ZERO);
					((DocumentoItemMaterial) documentoNovoC.getListaDocumentoItemMaterial().get(c))
							.setVlAcumulado(BigDecimal.ZERO);
				}
			}
			documentoNovoC = this.documentoService.salvar(documentoNovoC);

			String msgNovo = "Boletim de Medição - Complementar nº " + documentoNovoC.getIdComplementar()
					+ ",<br/> gerado a partir de " + this.documento.getTipoDocumento() + " Código:"
					+ this.documento.getCodigo();
			DocumentoOcorrencia documentoOcorrenciaNovoC = new DocumentoOcorrencia();
			documentoOcorrenciaNovoC.setSeqDocumento(documentoNovoC.getSeqDocumento());
			documentoOcorrenciaNovoC.setTipo("Automática");
			documentoOcorrenciaNovoC.setDescricao(msgNovo);
			documentoOcorrenciaNovoC.setSeqUsuario(this.loginController.getUsuario().getSeqUsuario());
			this.documentoOcorrenciaService.salvar(documentoOcorrenciaNovoC);

			String msg = "Boletim de Medição - Complementar nº " + documentoNovoC.getIdComplementar()
					+ ", gerado com sucesso!";
			this.documentoOcorrencia = new DocumentoOcorrencia();
			this.documentoOcorrencia.setSeqDocumento(this.documento.getSeqDocumento());
			this.documentoOcorrencia.setTipo("Automática");
			this.documentoOcorrencia.setDescricao(msg);
			this.documentoOcorrencia.setSeqUsuario(this.loginController.getUsuario().getSeqUsuario());
			this.documentoOcorrenciaService.salvar(this.documentoOcorrencia);

			mudarPagina("/documento/documento.jsf?idDocumento=" + String.valueOf(documentoNovoC.getSeqDocumento())
					+ "&idTipoDocumento=" + String.valueOf(documentoNovoC.getSeqTipoDocumento()));
			break;
		case 1:
			System.out.println("==> REVISADO");

			this.documento.setSeqDocumentoEtapa("422");
			this.documento = this.documentoService.salvar(this.documento);

			Documento documentoNovoR = new Documento();
			documentoNovoR = this.documentoService.buscarPorID(this.documento.getSeqEmpresa(),
					this.documento.getSeqDocumento());
			documentoNovoR.setSeqDocumento(null);
			documentoNovoR.setCodigo(this.documento.getCodigo());
			documentoNovoR.setSeqTipoDocumento(this.documento.getSeqTipoDocumento());
			documentoNovoR.setSeqDocumentoDono(this.documento.getSeqDocumento());
			documentoNovoR.setIdRevisao(Long.valueOf(this.documento.getIdRevisao().longValue() + 1L));
			documentoNovoR.setIdComplementar(Long.valueOf(0L));
			documentoNovoR.setSequencia(0);

			documentoNovoR = this.documentoService.salvar(documentoNovoR);

			Documento filhoR = new Documento();
			filhoR = this.documentoService.buscarPorID(this.documento.getSeqEmpresa(),
					documentoNovoR.getSeqDocumento());
			this.documento.setComentarioEtapa(
					"Substituído por Boletim de Medição - Revisado nº " + documentoNovoR.getIdRevisao());
			this.documento = this.documentoService.salvar(this.documento);

			String msgNovoR = "Boletim de Medição - Revisado nº " + documentoNovoR.getIdRevisao()
					+ ",<br/> gerado a partir de " + this.documento.getTipoDocumento() + " Código:"
					+ this.documento.getCodigo();
			DocumentoOcorrencia documentoOcorrenciaNovoR = new DocumentoOcorrencia();
			documentoOcorrenciaNovoR.setSeqDocumento(documentoNovoR.getSeqDocumento());
			documentoOcorrenciaNovoR.setTipo("Automática");
			documentoOcorrenciaNovoR.setDescricao(msgNovoR);
			documentoOcorrenciaNovoR.setSeqUsuario(this.loginController.getUsuario().getSeqUsuario());
			this.documentoOcorrenciaService.salvar(documentoOcorrenciaNovoR);

			String msgR = "Boletim de Medição - Revisado nº " + documentoNovoR.getIdRevisao() + ", gerado com sucesso!";
			this.documentoOcorrencia = new DocumentoOcorrencia();
			this.documentoOcorrencia.setSeqDocumento(this.documento.getSeqDocumento());
			this.documentoOcorrencia.setTipo("Automática");
			this.documentoOcorrencia.setDescricao(msgR);
			this.documentoOcorrencia.setSeqUsuario(this.loginController.getUsuario().getSeqUsuario());
			this.documentoOcorrenciaService.salvar(this.documentoOcorrencia);

			atualizarSeqDocDonoFilho(this.documento.getSeqDocumento(), documentoNovoR.getSeqDocumento());

			mudarPagina("/documento/documento.jsf?idDocumento=" + String.valueOf(documentoNovoR.getSeqDocumento())
					+ "&idTipoDocumento=" + String.valueOf(documentoNovoR.getSeqTipoDocumento()));
			break;
		case 2:
			System.out.println("=>Ordem de Cobrança - Regularizacao estatutaria");
			Documento documentoNovoOC = new Documento();
			documentoNovoOC = this.documentoService.buscarPorID(this.documento.getSeqEmpresa(),
					this.documento.getSeqDocumento());
			documentoNovoOC.setSeqDocumento(null);
			documentoNovoOC.setCodigo(null);
			documentoNovoOC.setSeqTipoDocumento("282");
			documentoNovoOC.setSeqDocumentoDono(this.documento.getSeqDocumento());
			documentoNovoOC.setSequencia(0);
			documentoNovoOC.setIdComplementar(Long.valueOf(0L));
			documentoNovoOC.setIdRevisao(Long.valueOf(0L));
			documentoNovoOC.setDescricao("");

			documentoNovoOC = this.documentoService.salvar(documentoNovoOC);

			String msgNovoOC = "Ordem de Cobrança, gerado a partir de " + this.documento.getTipoDocumento() + " Código:"
					+ this.documento.getCodigo();
			DocumentoOcorrencia documentoOcorrenciaNovoOC = new DocumentoOcorrencia();
			documentoOcorrenciaNovoOC.setSeqDocumento(documentoNovoOC.getSeqDocumento());
			documentoOcorrenciaNovoOC.setTipo("Automática");
			documentoOcorrenciaNovoOC.setDescricao(msgNovoOC);
			documentoOcorrenciaNovoOC.setSeqUsuario(this.loginController.getUsuario().getSeqUsuario());
			this.documentoOcorrenciaService.salvar(documentoOcorrenciaNovoOC);

			String msgOC = "Ordem de Cobrança nº " + documentoNovoOC.getCodigo() + ", gerado com sucesso!";
			this.documentoOcorrencia = new DocumentoOcorrencia();
			this.documentoOcorrencia.setSeqDocumento(this.documento.getSeqDocumento());
			this.documentoOcorrencia.setTipo("Automática");
			this.documentoOcorrencia.setDescricao(msgOC);
			this.documentoOcorrencia.setSeqUsuario(this.loginController.getUsuario().getSeqUsuario());
			this.documentoOcorrenciaService.salvar(this.documentoOcorrencia);

			mudarPagina("/documento/documento.jsf?idDocumento=" + String.valueOf(documentoNovoOC.getSeqDocumento())
					+ "&idTipoDocumento=" + String.valueOf(documentoNovoOC.getSeqTipoDocumento()));
			break;
		case 3:
			System.out.println("==> ALTERAÇÃO");

			if (this.documento.getSeqTipoDocumento().equals("221")) {
				this.documento.setSeqDocumentoEtapa("301");
			} else if (this.documento.getSeqTipoDocumento().equals("282")) {
				this.documento.setSeqDocumentoEtapa("266");
			} else if (this.documento.getSeqTipoDocumento().equals("283")) {
				this.documento.setSeqDocumentoEtapa("269");
			} else if (this.documento.getSeqTipoDocumento().equals("281")) {
				this.documento.setSeqDocumentoEtapa("263");
			}

			this.documento = this.documentoService.salvar(this.documento);

			Documento documentoNovoA = new Documento();
			documentoNovoA = this.documentoService.buscarPorID(this.documento.getSeqEmpresa(),
					this.documento.getSeqDocumento());
			documentoNovoA.setSeqDocumento(null);
			documentoNovoA.setCodigo(this.documento.getCodigo());
			documentoNovoA.setSeqTipoDocumento(this.documento.getSeqTipoDocumento());
			documentoNovoA.setSeqDocumentoDono(this.documento.getSeqDocumento());
			documentoNovoA.setSequencia(this.documento.getSequencia() + 1);
			documentoNovoA.setIdRevisao(Long.valueOf(0L));
			documentoNovoA.setIdComplementar(Long.valueOf(0L));

			documentoNovoA = this.documentoService.salvar(documentoNovoA);

			Documento filhoA = new Documento();
			filhoA = this.documentoService.buscarPorID(this.documento.getSeqEmpresa(),
					documentoNovoA.getSeqDocumento());
			this.documento.setComentarioEtapa(
					"Substituído por " + filhoA.getTipoDocumento() + " Alteração nº " + filhoA.getSequencia());
			this.documento = this.documentoService.salvar(this.documento);

			String msgNovoA = filhoA.getTipoDocumento() + " Alteração nº " + filhoA.getSequencia()
					+ ", gerado a partir de " + this.documento.getTipoDocumento() + " Código:"
					+ this.documento.getCodigo();
			DocumentoOcorrencia documentoOcorrenciaNovoA = new DocumentoOcorrencia();
			documentoOcorrenciaNovoA.setSeqDocumento(documentoNovoA.getSeqDocumento());
			documentoOcorrenciaNovoA.setTipo("Automática");
			documentoOcorrenciaNovoA.setDescricao(msgNovoA);
			documentoOcorrenciaNovoA.setSeqUsuario(this.loginController.getUsuario().getSeqUsuario());
			this.documentoOcorrenciaService.salvar(documentoOcorrenciaNovoA);

			String msgA = filhoA.getTipoDocumento() + " Alteração nº " + filhoA.getSequencia()
					+ ", gerado com sucesso!";
			this.documentoOcorrencia = new DocumentoOcorrencia();
			this.documentoOcorrencia.setSeqDocumento(this.documento.getSeqDocumento());
			this.documentoOcorrencia.setTipo("Automática");
			this.documentoOcorrencia.setDescricao(msgA);
			this.documentoOcorrencia.setSeqUsuario(this.loginController.getUsuario().getSeqUsuario());
			this.documentoOcorrenciaService.salvar(this.documentoOcorrencia);

			atualizarSeqDocDonoFilho(this.documento.getSeqDocumento(), filhoA.getSeqDocumento());

			mudarPagina("/documento/documento.jsf?idDocumento=" + String.valueOf(documentoNovoA.getSeqDocumento())
					+ "&idTipoDocumento=" + String.valueOf(documentoNovoA.getSeqTipoDocumento()));
		}

	}

	public void atualizarSeqDocDonoFilho(String seqDocDono, String seqDocDonoNovo) {
		this.documentoService.atualizarSeqDocDonoFilho(seqDocDono, seqDocDonoNovo);
	}

	public void listarDetalheParceiro() {
		if ((this.idTipoDocumento.equals("381")) || (this.idTipoDocumento.equals("401"))) {
			for (Parceiro pParceiro : this.listaParceiro) {
				if ((this.documento.getSeqParceiro().equals(pParceiro.getSeqParceiro())) && (pParceiro.getIm() == null)
						&& (pParceiro.getTipo().equals("Pessoa Jurídica"))) {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_WARN,
									"O Parceiro selecionado não possui Inscrição Municipal no seu cadastro. Favor atualizar o cadastro do Parceiro.",
									""));
				}
			}
		}

		if (!this.materialPrecoService
				.listarPorParceiro(this.loginController.getUsuario().getSeqEmpresa(), this.documento.getSeqParceiro())
				.isEmpty()) {
			this.listaMaterialReferencia = this.materialPrecoService.listarGrupoPorParceiro(
					this.loginController.getUsuario().getSeqEmpresa(), this.documento.getSeqParceiro());
		} else {
			this.listaMaterialReferencia.clear();
		}
		this.listaParceiroContato = this.parceiroContatoService.listarPorParceiro(this.documento.getSeqParceiro(),
				Situacao.TODOS);
		if (this.bEquipamento) {
			listarEquipamento();
		}

		if (this.bEmbarcacao) {
			listarEmbarcacao();
		}
	}

	public void listarEquipamento() {
		this.equipamentoSelecionado = new ArrayList();
		if (this.idTipoDocumento.equals("301")) {
			this.listaEquipamento = this.equipamentoService.listarPorDono(
					this.loginController.getEmpresa().getSeqEmpresa(), this.documento.getSeqDocumentoDono(),
					Situacao.ATIVO);
		} else {
			this.listaEquipamento = this.equipamentoService.listarPorParceiro(
					this.loginController.getEmpresa().getSeqEmpresa(), this.documento.getSeqParceiro(), Situacao.ATIVO);
		}
	}

	public void listarEmbarcacao() {
		this.embarcacaoSelecionado = new ArrayList();
		if (this.idTipoDocumento.equals("301")) {
			this.listaNvEmbarcacaoParceiro = this.nvEmbarcacaoParceiroService
					.listarPorDono(this.documento.getSeqDocumentoDono());
		} else {
			this.listaNvEmbarcacaoParceiro = this.nvEmbarcacaoParceiroService
					.listarPorParceiro(this.documento.getSeqParceiro());
		}
	}

	public void calcularEntrada() {
		if (this.documento.getVlEntrada() == null) {
			this.valorRestanteDocumento = this.documento.getVlTotal();
		} else {
			this.valorRestanteDocumento = this.documento.getVlTotal().subtract(this.documento.getVlEntrada());
		}
	}

	public void buscarContratada() {
		for (UnidadeNegocio un : this.listaUnidadeNegocio) {
			if (un.getSeqUnidadeNegocio().equals(this.unidadeNegocio.getSeqUnidadeNegocio())) {
				if (un.getCnpj() == null) {
					this.unidadeNegocio = un;
					this.unidadeNegocio.setCnpj("S/Nº.");
				} else {
					this.unidadeNegocio = un;
				}
			} else if (this.unidadeNegocio.getSeqUnidadeNegocio() == null) {
				this.unidadeNegocio = new UnidadeNegocio();
			}
		}
	}

	public void selecionaConta(String seqConta) {
		if (seqConta == null) {
			this.documento.setTarifaBancaria(null);
		} else if (seqConta.equals("0")) {
			if ((!this.documento.getSeqFormaPagamento().equals("83"))
					&& (!this.documento.getSeqFormaPagamento().equals("82"))) {
				this.documento.setSeqConta(null);
				this.documento.setTarifaBancaria(null);
			}
		} else {
			for (Conta pConta : this.listaConta) {
				if (pConta.getSeqConta().equals(seqConta)) {
					this.documento.setTarifaBancaria(pConta.getVlTarifa());
				}
			}
		}
	}

	public void limparValores() {
		this.documento.setVlExecutadoAcumulado(BigDecimal.ZERO);
		this.documento.setVlTributosNaoIncluso(BigDecimal.ZERO);
		this.documento.setVlBruto(BigDecimal.ZERO);
		this.documento.setVlRetencaoFederal(BigDecimal.ZERO);
		this.documento.setVlLiquidoAReceber(BigDecimal.ZERO);
		this.documento.setVlISSQNRetido(BigDecimal.ZERO);
	}

	public void calcularBoletim() {
		limparValores();

		BigDecimal bdUM = BigDecimal.ONE;
		BigDecimal bdZero = BigDecimal.ONE;
		BigDecimal pTributoZero;
		if (this.documento.getTributosImpostos().equals("1")) {
			pTributoZero = new BigDecimal(38);
			this.documento.setSeqProjecaoTributaria(pTributoZero);
		}

		for (DocumentoItemMaterial pDocumentoItemMaterial : this.documento.getListaDocumentoItemMaterial()) {
			if (pDocumentoItemMaterial.getVlAcumulado() == null) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Informe a Quantida de Executada no Período", ""));
			} else {
				this.documento.setVlExecutadoAcumulado(this.documento.getVlExecutadoAcumulado()
						.add(pDocumentoItemMaterial.getVlPeriodo()).setScale(2, RoundingMode.HALF_EVEN));
			}
		}

		List<ProjecaoTributaria> plistaProjecaoTributaria = new ArrayList();
		plistaProjecaoTributaria = this.projecaoTributariaService.listarPorSeq(
				this.loginController.getUsuario().getSeqEmpresa(), this.documento.getSeqProjecaoTributaria().toString(),
				Situacao.ATIVO);

		BigDecimal part1 = bdUM.subtract(((ProjecaoTributaria) plistaProjecaoTributaria.get(0)).getPercentual()
				.divide(new BigDecimal(100), 2, RoundingMode.HALF_UP));
		this.documento
				.setVlTributosNaoIncluso(this.documento.getVlExecutadoAcumulado().divide(part1, 2, RoundingMode.HALF_UP)
						.subtract(this.documento.getVlExecutadoAcumulado()).setScale(2, RoundingMode.HALF_EVEN));

		this.documento.setVlBruto(this.documento.getVlExecutadoAcumulado().add(this.documento.getVlTributosNaoIncluso())
				.setScale(2, RoundingMode.HALF_EVEN));

		if ((this.documento.getRetencaoISSQN().equals(bdUM)) || (this.documento.getAliquotaISSQNretido() == null)) {
			this.documento.setAliquotaISSQNretido(bdZero);
			this.documento.setVlISSQNRetido(
					this.documento.getVlBruto().multiply(BigDecimal.ZERO).setScale(2, RoundingMode.HALF_EVEN));
		} else {
			this.documento.setVlISSQNRetido(this.documento.getVlBruto()
					.multiply(this.documento.getAliquotaISSQNretido().divide(new BigDecimal(100)))
					.setScale(2, RoundingMode.HALF_EVEN));
		}

		List<Aliquota> plistaAliquotaRetencaoFederal = new ArrayList();
		plistaAliquotaRetencaoFederal = this.aliquotaRetencaoFederalService.listarPorSeq(
				this.loginController.getUsuario().getSeqEmpresa(), this.documento.getSeqAliquotaFederal().toString(),
				Situacao.ATIVO);

		this.documento.setVlRetencaoFederal(this.documento.getVlBruto()
				.multiply(((Aliquota) plistaAliquotaRetencaoFederal.get(0)).getPercentual().divide(new BigDecimal(100)))
				.setScale(2, RoundingMode.HALF_EVEN));

		this.documento.setVlLiquidoAReceber(this.documento.getVlBruto().subtract(this.documento.getVlRetencaoFederal())
				.subtract(this.documento.getVlISSQNRetido()).setScale(2, RoundingMode.HALF_EVEN));
	}

	public void verificarMoeda() {
		if (this.documento.getMoeda().equals(this.documento.getMoedaDestino())) {
			this.documento.setTaxaCambio(new BigDecimal(0));
		}
	}

	public void calcularTaxaCambio(DocumentoItemMaterial item) {
		if (this.documento.getTaxaCambio() == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informe a Taxa de Câmbio", ""));
			return;
		}
		if (item.isChkTaxaCambio()) {
			if (item.getVlDesconto() != null) {
				item.setVlPeriodo(item.getVlUnitario().multiply(this.documento.getTaxaCambio())
						.multiply(item.getQtdePeriodo()).subtract(item.getVlDesconto()));
			} else {
				item.setVlPeriodo(
						item.getVlUnitario().multiply(this.documento.getTaxaCambio()).multiply(item.getQtdePeriodo()));
			}
		} else if (item.getVlDesconto() != null) {
			item.setVlPeriodo(item.getVlUnitario().multiply(item.getQtdePeriodo()).subtract(item.getVlDesconto()));
		} else {
			item.setVlPeriodo(item.getVlUnitario().multiply(item.getQtdePeriodo()));
		}

		calcularAcumulado();
	}

	public void selecionarOrigem() {
		if (this.documento.getOrigemParceiro().equals("1")) {
			this.documento.setRetencaoISSQN(BigDecimal.ONE);
			this.documento.setSeqAliquotaFederal(new BigDecimal(0));
		}
		if (this.documento.getOrigemParceiro().equals("0")) {
			this.documento.setRetencaoISSQN(BigDecimal.ZERO);
			this.documento.setSeqAliquotaFederal(null);
		}
	}

	public void verificarOrigem() {
		if (this.documento.getOrigemParceiro().equals("1")) {
			if (this.documento.getSeqAliquotaFederal() != new BigDecimal(0)) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Cliente de Origem Exterior", ""));
			}
			if (this.documento.getRetencaoISSQN() != BigDecimal.ONE) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Cliente de Origem Exterior", ""));
			}
		}
	}

	public void aliquotaIssqnRetido() {
		if (this.documento.getRetencaoISSQN() == BigDecimal.ONE) {
			this.documento.setAliquotaISSQNretido(BigDecimal.ZERO);
		}
	}

	public void buscarTipoMovimentoFinanceiro() {
		this.listaTipoMovimentoFinanceiro = this.tipoMovimentoFinanceiroService
				.listarPorNatureza(this.loginController.getEmpresa().getSeqEmpresa(), this.naturezaLancamento);
	}

	public void adicionarComentario() {
		this.documentoComentario.setSeqEmpresa(this.loginController.getUsuario().getSeqEmpresa());
		this.documentoComentario.setSeqUsuario(this.loginController.getUsuario().getSeqUsuario());
		this.documentoComentario.setUsuario(this.loginController.getUsuario().getUsuario());
		this.documentoComentario.setDataCadastro(new Date());

		this.listaDocumentoComentario.add(this.documentoComentario);

		this.documentoComentario = new DocumentoComentario();
	}

	public void removerComentario(DocumentoComentario pDocumentoComentario) {
		this.listaDocumentoComentario.remove(pDocumentoComentario);
	}

	public void selecionarComentario(DocumentoComentario pDocumentoComentario) {
		this.documentoComentario = pDocumentoComentario;
		this.listaDocumentoComentario.remove(pDocumentoComentario);
	}

	public void upload() {
		String id = this.documento.getCodigo().replace("/", "-");
		id = id.replace(" ", "");
		System.out.println(id);

		this.upload.setSeqDocumento(this.documento.getSeqDocumento());
		this.upload.setOrigem("PROPOSTA" + id + "-" + this.documento.getSeqDocumento());
		this.upload.setSeqEmpresa(this.loginController.empresa.getSeqEmpresa());
		this.upload.setSeqUsuario(this.loginController.usuario.getSeqUsuario());

		for (Upload u : this.listaUpload) {
			if (u.getNomeArquivo().equals(this.arquivo.getFileName())) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Já existe um arquivo com esse nome.", ""));
				return;
			}
		}

		this.uploadController.upload(this.arquivo, this.upload);
		this.listaUpload = this.uploadService.listar(this.loginController.empresa.getSeqEmpresa(),
				this.documento.getSeqDocumento());
		this.upload = new Upload();
		this.idDocumento = this.documento.getSeqDocumento();
		this.idTipoDocumento = this.documento.getSeqTipoDocumento();
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
		this.listaUpload = this.uploadService.listar(this.loginController.empresa.getSeqEmpresa(),
				this.documento.getSeqDocumento());
	}
        
        
        
        public List<String> completeArea(String query) {
        List<String> results = new ArrayList<>();

        if ("A".equals(query)) {
            results.add("As despesas de hospedagem, transporte, alimentação e outras para atendimento fora do município do Rio de Janeiro e Niterói, são de \n" +
"responsabilidade do cliente e devem ser providenciadas preferencialmente pelo mesmo, quando não, serão aplicadas as seguintes condições:\n" +
" \n" +
"a)    R$ 1,50 (Um real e cinquenta centavos) /Km rodado considerando-se a partida da sede ou escritório regional da empresa e os municípios visitados, e retorno a sede ou escritório regional da empresa;\n" +
"b)    Reembolso de pedágios e estacionamentos, mediante a apresentação do recibo original, acrescidos de 15% a título de \n" +
"administração, acrescidas de impostos;\n" +
"c)    Valor de alimentação R$ 100,00 (cem reais) dia/homem;\n" +
"d)    Diária de hospedagem R$ 200,00 (duzentos reais) dia/homem;\n" +
"e)    Reembolso de transporte aéreo, quando aplicado, mediante aprovação prévia do contratante e apresentação de \n" +
"comprovante;");
            results.add("Accommodation, transport, meals and other expenses for service outside the city of Rio de Janeiro and Niterói are the responsibility of the customer and should preferably be provided by the same, if not, the following conditions will apply:\n" +
"\n" +
"a)	BRL 1.50 (one real and fifty cents) / km driven considering the departure from the company's headquarters or regional office and the cities visited, and return to the company's headquarters or regional office;\n" +
"b)	Reimbursement of tolls and parking, upon presentation of the original receipt, plus 15% by way of administration, plus taxes;\n" +
"c)	   Meal fee BRL 100.00 (one hundred reais) day/man;\n" +
"d)	Accommodation of BRL 200.00 (two hundred reais) day/man;\n" +
"e)	Reimbursement for air ticket, when applied, upon prior approval by the contracting party and presentation of proof;");

        }
        else {
            
        }

        return results;
    }   
        
        
       /* public void onSelect(SelectEvent<String> event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "select", event.getObject()));
    }*/
        
        
        
        

	public void iniciarVisualizacao() {
	}

	public LoginController getLoginController() {
		return this.loginController;
	}

	public void setLoginController(LoginController loginController) {
		this.loginController = loginController;
	}

	public DocumentoService getDocumentoService() {
		return this.documentoService;
	}

	public void setDocumentoService(DocumentoService documentoService) {
		this.documentoService = documentoService;
	}

	public Documento getDocumento() {
		return this.documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public Integer getTela() {
		return this.tela;
	}

	public void setTela(Integer tela) {
		this.tela = tela;
	}

	public TipoDocumentoService getTipoDocumentoService() {
		return this.tipoDocumentoService;
	}

	public void setTipoDocumentoService(TipoDocumentoService tipoDocumentoService) {
		this.tipoDocumentoService = tipoDocumentoService;
	}

	public List<TipoDocumento> getListaTipoDocumento() {
		return this.listaTipoDocumento;
	}

	public void setListaTipoDocumento(List<TipoDocumento> listaTipoDocumento) {
		this.listaTipoDocumento = listaTipoDocumento;
	}

	public ParceiroService getParceiroService() {
		return this.parceiroService;
	}

	public void setParceiroService(ParceiroService parceiroService) {
		this.parceiroService = parceiroService;
	}

	public List<Parceiro> getListaParceiro() {
		return this.listaParceiro;
	}

	public void setListaParceiro(List<Parceiro> listaParceiro) {
		this.listaParceiro = listaParceiro;
	}

	public DocumentoOcorrenciaService getDocumentoOcorrenciaService() {
		return this.documentoOcorrenciaService;
	}

	public void setDocumentoOcorrenciaService(DocumentoOcorrenciaService documentoOcorrenciaService) {
		this.documentoOcorrenciaService = documentoOcorrenciaService;
	}

	public List<DocumentoOcorrencia> getListaDocumentoOcorrencia() {
		return this.listaDocumentoOcorrencia;
	}

	public void setListaDocumentoOcorrencia(List<DocumentoOcorrencia> listaDocumentoOcorrencia) {
		this.listaDocumentoOcorrencia = listaDocumentoOcorrencia;
	}

	public DocumentoOcorrencia getDocumentoOcorrencia() {
		return this.documentoOcorrencia;
	}

	public void setDocumentoOcorrencia(DocumentoOcorrencia documentoOcorrencia) {
		this.documentoOcorrencia = documentoOcorrencia;
	}

	public DocumentoEtapaService getDocumentoEtapaService() {
		return this.documentoEtapaService;
	}

	public void setDocumentoEtapaService(DocumentoEtapaService documentoEtapaService) {
		this.documentoEtapaService = documentoEtapaService;
	}

	public List<DocumentoEtapa> getListaDocumentoEtapa() {
		return this.listaDocumentoEtapa;
	}

	public void setListaDocumentoEtapa(List<DocumentoEtapa> listaDocumentoEtapa) {
		this.listaDocumentoEtapa = listaDocumentoEtapa;
	}

	public DocumentoEtapa getDocumentoEtapa() {
		return this.documentoEtapa;
	}

	public void setDocumentoEtapa(DocumentoEtapa documentoEtapa) {
		this.documentoEtapa = documentoEtapa;
	}

	public TipoDocumento getTipoDocumento() {
		return this.tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public DocumentoItemMaterialService getDocumentoItemMaterialService() {
		return this.documentoItemMaterialService;
	}

	public void setDocumentoItemMaterialService(DocumentoItemMaterialService documentoItemMaterialService) {
		this.documentoItemMaterialService = documentoItemMaterialService;
	}

	public List<DocumentoItemMaterial> getListaDocumentoItemMaterial() {
		return this.listaDocumentoItemMaterial;
	}

	public void setListaDocumentoItemMaterial(List<DocumentoItemMaterial> listaDocumentoItemMaterial) {
		this.listaDocumentoItemMaterial = listaDocumentoItemMaterial;
	}

	public DocumentoItemMaterial getDocumentoItemMaterial() {
		return this.documentoItemMaterial;
	}

	public void setDocumentoItemMaterial(DocumentoItemMaterial documentoItemMaterial) {
		this.documentoItemMaterial = documentoItemMaterial;
	}

	public MaterialPrecoService getMaterialPrecoService() {
		return this.materialPrecoService;
	}

	public void setMaterialPrecoService(MaterialPrecoService materialPrecoService) {
		this.materialPrecoService = materialPrecoService;
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

	public String getIdDocumento() {
		return this.idDocumento;
	}

	public void setIdDocumento(String idDocumento) {
		this.idDocumento = idDocumento;
	}

	public String getIdTipoDocumento() {
		return this.idTipoDocumento;
	}

	public void setIdTipoDocumento(String idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}

	public EquipamentoService getEquipamentoService() {
		return this.equipamentoService;
	}

	public void setEquipamentoService(EquipamentoService equipamentoService) {
		this.equipamentoService = equipamentoService;
	}

	public List<Equipamento> getListaEquipamento() {
		return this.listaEquipamento;
	}

	public void setListaEquipamento(List<Equipamento> listaEquipamento) {
		this.listaEquipamento = listaEquipamento;
	}

	public Equipamento getEquipamento() {
		return this.equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public ArrayList<String> getEquipamentoSelecionado() {
		return this.equipamentoSelecionado;
	}

	public void setEquipamentoSelecionado(ArrayList<String> equipamentoSelecionado) {
		this.equipamentoSelecionado = equipamentoSelecionado;
	}

	public NvEmbarcacaoParceiroService getNvEmbarcacaoParceiroService() {
		return this.nvEmbarcacaoParceiroService;
	}

	public void setNvEmbarcacaoParceiroService(NvEmbarcacaoParceiroService nvEmbarcacaoParceiroService) {
		this.nvEmbarcacaoParceiroService = nvEmbarcacaoParceiroService;
	}

	public List<NvEmbarcacaoParceiro> getListaNvEmbarcacaoParceiro() {
		return this.listaNvEmbarcacaoParceiro;
	}

	public void setListaNvEmbarcacaoParceiro(List<NvEmbarcacaoParceiro> listaNvEmbarcacaoParceiro) {
		this.listaNvEmbarcacaoParceiro = listaNvEmbarcacaoParceiro;
	}

	public ArrayList<String> getEmbarcacaoSelecionado() {
		return this.embarcacaoSelecionado;
	}

	public void setEmbarcacaoSelecionado(ArrayList<String> embarcacaoSelecionado) {
		this.embarcacaoSelecionado = embarcacaoSelecionado;
	}

	public LugarService getLugarService() {
		return this.lugarService;
	}

	public void setLugarService(LugarService lugarService) {
		this.lugarService = lugarService;
	}

	public List<Lugar> getListaLugar() {
		return this.listaLugar;
	}

	public void setListaLugar(List<Lugar> listaLugar) {
		this.listaLugar = listaLugar;
	}

	public DocumentoItemServicoService getDocumentoItemServicoService() {
		return this.documentoItemServicoService;
	}

	public void setDocumentoItemServicoService(DocumentoItemServicoService documentoItemServicoService) {
		this.documentoItemServicoService = documentoItemServicoService;
	}

	public List<DocumentoItemServico> getListaDocumentoItemServico() {
		return this.listaDocumentoItemServico;
	}

	public void setListaDocumentoItemServico(List<DocumentoItemServico> listaDocumentoItemServico) {
		this.listaDocumentoItemServico = listaDocumentoItemServico;
	}

	public DocumentoItemServico getDocumentoItemServico() {
		return this.documentoItemServico;
	}

	public void setDocumentoItemServico(DocumentoItemServico documentoItemServico) {
		this.documentoItemServico = documentoItemServico;
	}

	public ServicoEscopoService getServicoEscopoService() {
		return this.servicoEscopoService;
	}

	public void setServicoEscopoService(ServicoEscopoService servicoEscopoService) {
		this.servicoEscopoService = servicoEscopoService;
	}

	public List<ServicoEscopo> getListaServicoEscopo() {
		return this.listaServicoEscopo;
	}

	public void setListaServicoEscopo(List<ServicoEscopo> listaServicoEscopo) {
		this.listaServicoEscopo = listaServicoEscopo;
	}

	public List<DocumentoItemServico> getListaDocumentoItemServicoRemover() {
		return this.listaDocumentoItemServicoRemover;
	}

	public void setListaDocumentoItemServicoRemover(List<DocumentoItemServico> listaDocumentoItemServicoRemover) {
		this.listaDocumentoItemServicoRemover = listaDocumentoItemServicoRemover;
	}

	public DocumentoItemFinanceiroService getDocumentoItemFinanceiroService() {
		return this.documentoItemFinanceiroService;
	}

	public void setDocumentoItemFinanceiroService(DocumentoItemFinanceiroService documentoItemFinanceiroService) {
		this.documentoItemFinanceiroService = documentoItemFinanceiroService;
	}

	public DocumentoItemFinanceiro getDocumentoItemFinanceiro() {
		return this.documentoItemFinanceiro;
	}

	public void setDocumentoItemFinanceiro(DocumentoItemFinanceiro documentoItemFinanceiro) {
		this.documentoItemFinanceiro = documentoItemFinanceiro;
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

	public ContaService getContaService() {
		return this.contaService;
	}

	public void setContaService(ContaService contaService) {
		this.contaService = contaService;
	}

	public List<Conta> getListaConta() {
		return this.listaConta;
	}

	public void setListaConta(List<Conta> listaConta) {
		this.listaConta = listaConta;
	}

	public TipoMovimentoFinanceiroService getTipoMovimentoFinanceiroService() {
		return this.tipoMovimentoFinanceiroService;
	}

	public void setTipoMovimentoFinanceiroService(TipoMovimentoFinanceiroService tipoMovimentoFinanceiroService) {
		this.tipoMovimentoFinanceiroService = tipoMovimentoFinanceiroService;
	}

	public List<TipoMovimentoFinanceiro> getListaTipoMovimentoFinanceiro() {
		return this.listaTipoMovimentoFinanceiro;
	}

	public void setListaTipoMovimentoFinanceiro(List<TipoMovimentoFinanceiro> listaTipoMovimentoFinanceiro) {
		this.listaTipoMovimentoFinanceiro = listaTipoMovimentoFinanceiro;
	}

	public boolean isbPontoServico() {
		return this.bPontoServico;
	}

	public void setbPontoServico(boolean bPontoServico) {
		this.bPontoServico = bPontoServico;
	}

	public boolean isbConta() {
		return this.bConta;
	}

	public void setbConta(boolean bConta) {
		this.bConta = bConta;
	}

	public boolean isbFormaPagamento() {
		return this.bFormaPagamento;
	}

	public void setbFormaPagamento(boolean bFormaPagamento) {
		this.bFormaPagamento = bFormaPagamento;
	}

	public boolean isbCondicaoPagamento() {
		return this.bCondicaoPagamento;
	}

	public void setbCondicaoPagamento(boolean bCondicaoPagamento) {
		this.bCondicaoPagamento = bCondicaoPagamento;
	}

	public boolean isbTipoMovimentoFin() {
		return this.bTipoMovimentoFin;
	}

	public void setbTipoMovimentoFin(boolean bTipoMovimentoFin) {
		this.bTipoMovimentoFin = bTipoMovimentoFin;
	}

	public boolean isbFinanceiro() {
		return this.bFinanceiro;
	}

	public void setbFinanceiro(boolean bFinanceiro) {
		this.bFinanceiro = bFinanceiro;
	}

	public boolean isbMaterial() {
		return this.bMaterial;
	}

	public void setbMaterial(boolean bMaterial) {
		this.bMaterial = bMaterial;
	}

	public boolean isbEscopo() {
		return this.bEscopo;
	}

	public void setbEscopo(boolean bEscopo) {
		this.bEscopo = bEscopo;
	}

	public boolean isbEmbarcacao() {
		return this.bEmbarcacao;
	}

	public void setbEmbarcacao(boolean bEmbarcacao) {
		this.bEmbarcacao = bEmbarcacao;
	}

	public boolean isbEquipamento() {
		return this.bEquipamento;
	}

	public void setbEquipamento(boolean bEquipamento) {
		this.bEquipamento = bEquipamento;
	}

	public CondicaoPagamentoService getCondicaoPagamentoService() {
		return this.condicaoPagamentoService;
	}

	public void setCondicaoPagamentoService(CondicaoPagamentoService condicaoPagamentoService) {
		this.condicaoPagamentoService = condicaoPagamentoService;
	}

	public List<CondicaoPagamento> getListaCondicaoPagamento() {
		return this.listaCondicaoPagamento;
	}

	public void setListaCondicaoPagamento(List<CondicaoPagamento> listaCondicaoPagamento) {
		this.listaCondicaoPagamento = listaCondicaoPagamento;
	}

	public Parceiro getParceiro() {
		return this.parceiro;
	}

	public void setParceiro(Parceiro parceiro) {
		this.parceiro = parceiro;
	}

	public boolean isbNovoDocumento() {
		return this.bNovoDocumento;
	}

	public void setbNovoDocumento(boolean bNovoDocumento) {
		this.bNovoDocumento = bNovoDocumento;
	}

	public List<Documento> getListaDocumentoFilho() {
		return this.listaDocumentoFilho;
	}

	public void setListaDocumentoFilho(List<Documento> listaDocumentoFilho) {
		this.listaDocumentoFilho = listaDocumentoFilho;
	}

	public boolean isbColaborador() {
		return this.bColaborador;
	}

	public void setbColaborador(boolean bColaborador) {
		this.bColaborador = bColaborador;
	}

	public ColaboradorService getColaboradorService() {
		return this.colaboradorService;
	}

	public void setColaboradorService(ColaboradorService colaboradorService) {
		this.colaboradorService = colaboradorService;
	}

	public List<Colaborador> getListaColaborador() {
		return this.listaColaborador;
	}

	public void setListaColaborador(List<Colaborador> listaColaborador) {
		this.listaColaborador = listaColaborador;
	}

	public TipoDocumentoGerar getTipoDocumentoGerar() {
		return this.tipoDocumentoGerar;
	}

	public void setTipoDocumentoGerar(TipoDocumentoGerar tipoDocumentoGerar) {
		this.tipoDocumentoGerar = tipoDocumentoGerar;
	}

	public List<TipoDocumentoGerar> getListaTipoDocumentoGerar() {
		return this.listaTipoDocumentoGerar;
	}

	public void setListaTipoDocumentoGerar(List<TipoDocumentoGerar> listaTipoDocumentoGerar) {
		this.listaTipoDocumentoGerar = listaTipoDocumentoGerar;
	}

	public boolean isbAssinaturaColaborador() {
		return this.bAssinaturaColaborador;
	}

	public void setbAssinaturaColaborador(boolean bAssinaturaColaborador) {
		this.bAssinaturaColaborador = bAssinaturaColaborador;
	}

	public List<Colaborador> getListaAssColaborador() {
		return this.listaAssColaborador;
	}

	public void setListaAssColaborador(List<Colaborador> listaAssColaborador) {
		this.listaAssColaborador = listaAssColaborador;
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

	public BigDecimal getValorDescDocumento() {
		return this.valorDescDocumento;
	}

	public void setValorDescDocumento(BigDecimal valorDescDocumento) {
		this.valorDescDocumento = valorDescDocumento;
	}

	public BigDecimal getValorRestanteDocumento() {
		return this.valorRestanteDocumento;
	}

	public void setValorRestanteDocumento(BigDecimal valorRestanteDocumento) {
		this.valorRestanteDocumento = valorRestanteDocumento;
	}

	public List<Documento> getListaItensBotaoGerar() {
		return this.listaItensBotaoGerar;
	}

	public void setListaItensBotaoGerar(List<Documento> listaItensBotaoGerar) {
		this.listaItensBotaoGerar = listaItensBotaoGerar;
	}

	public List<TipoDocumentoGerar> getTp() {
		return this.tp;
	}

	public void setTp(List<TipoDocumentoGerar> tp) {
		this.tp = tp;
	}

	public BigDecimal getQtdeItemDocumento() {
		return this.qtdeItemDocumento;
	}

	public void setQtdeItemDocumento(BigDecimal qtdeItemDocumento) {
		this.qtdeItemDocumento = qtdeItemDocumento;
	}

	public String getpGrupoServico() {
		return this.pGrupoServico;
	}

	public void setpGrupoServico(String pGrupoServico) {
		this.pGrupoServico = pGrupoServico;
	}

	public List<MaterialPreco> getListaMaterialReferencia() {
		return this.listaMaterialReferencia;
	}

	public void setListaMaterialReferencia(List<MaterialPreco> listaMaterialReferencia) {
		this.listaMaterialReferencia = listaMaterialReferencia;
	}
        
	public List<Material> getListaMaterial() {
		return this.listaMaterial;
	}

	public void setListaMaterial(List<Material> listaMaterial) {
		this.listaMaterial = listaMaterial;
	}        

	public List<ParceiroContato> getListaParceiroContato() {
		return this.listaParceiroContato;
	}

	public void setListaParceiroContato(List<ParceiroContato> listaParceiroContato) {
		this.listaParceiroContato = listaParceiroContato;
	}

	public UnidadeNegocioService getUnidadeNegocioService() {
		return this.unidadeNegocioService;
	}

	public void setUnidadeNegocioService(UnidadeNegocioService unidadeNegocioService) {
		this.unidadeNegocioService = unidadeNegocioService;
	}

	public UnidadeNegocio getUnidadeNegocio() {
		return this.unidadeNegocio;
	}

	public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public List<UnidadeNegocio> getListaUnidadeNegocio() {
		return this.listaUnidadeNegocio;
	}

	public void setListaUnidadeNegocio(List<UnidadeNegocio> listaUnidadeNegocio) {
		this.listaUnidadeNegocio = listaUnidadeNegocio;
	}

	public ProjecaoTributariaService getProjecaoTributariaService() {
		return this.projecaoTributariaService;
	}

	public void setProjecaoTributariaService(ProjecaoTributariaService projecaoTributariaService) {
		this.projecaoTributariaService = projecaoTributariaService;
	}

	public ParceiroContatoService getParceiroContatoService() {
		return this.parceiroContatoService;
	}

	public void setParceiroContatoService(ParceiroContatoService parceiroContatoService) {
		this.parceiroContatoService = parceiroContatoService;
	}

	public List<ProjecaoTributaria> getListaProjecaoTributaria() {
		return this.listaProjecaoTributaria;
	}

	public void setListaProjecaoTributaria(List<ProjecaoTributaria> listaProjecaoTributaria) {
		this.listaProjecaoTributaria = listaProjecaoTributaria;
	}

	public AliquotaService getAliquotaRetencaoFederalService() {
		return this.aliquotaRetencaoFederalService;
	}

	public void setAliquotaRetencaoFederalService(AliquotaService aliquotaRetencaoFederalService) {
		this.aliquotaRetencaoFederalService = aliquotaRetencaoFederalService;
	}

	public List<Aliquota> getListaAliquotaRetencaoFederal() {
		return this.listaAliquotaRetencaoFederal;
	}

	public void setListaAliquotaRetencaoFederal(List<Aliquota> listaAliquotaRetencaoFederal) {
		this.listaAliquotaRetencaoFederal = listaAliquotaRetencaoFederal;
	}

	public BigDecimal getPrecoMinimo() {
		return this.precoMinimo;
	}

	public void setPrecoMinimo(BigDecimal precoMinimo) {
		this.precoMinimo = precoMinimo;
	}

	public BigDecimal getPrecoMaximo() {
		return this.precoMaximo;
	}

	public void setPrecoMaximo(BigDecimal precoMaximo) {
		this.precoMaximo = precoMaximo;
	}

	public String getMsgCancelamentoAnterior() {
		return this.msgCancelamentoAnterior;
	}

	public void setMsgCancelamentoAnterior(String msgCancelamentoAnterior) {
		this.msgCancelamentoAnterior = msgCancelamentoAnterior;
	}

	public UsuarioTipoDocumentoService getUsuarioTipoDocumentoService() {
		return this.usuarioTipoDocumentoService;
	}

	public void setUsuarioTipoDocumentoService(UsuarioTipoDocumentoService usuarioTipoDocumentoService) {
		this.usuarioTipoDocumentoService = usuarioTipoDocumentoService;
	}

	public boolean isUsuarioTipoDocumento() {
		return this.usuarioTipoDocumento;
	}

	public void setUsuarioTipoDocumento(boolean usuarioTipoDocumento) {
		this.usuarioTipoDocumento = usuarioTipoDocumento;
	}

	public TipoDocumentoGerarService getTipoDocumentoGerarService() {
		return this.tipoDocumentoGerarService;
	}

	public void setTipoDocumentoGerarService(TipoDocumentoGerarService tipoDocumentoGerarService) {
		this.tipoDocumentoGerarService = tipoDocumentoGerarService;
	}

	public DocumentoEscopoService getDocumentoEscopoService() {
		return this.documentoEscopoService;
	}

	public void setDocumentoEscopoService(DocumentoEscopoService documentoEscopoService) {
		this.documentoEscopoService = documentoEscopoService;
	}

	public DocumentoEscopo getDocumentoEscopo() {
		return this.documentoEscopo;
	}

	public void setDocumentoEscopo(DocumentoEscopo documentoEscopo) {
		this.documentoEscopo = documentoEscopo;
	}

	public List<DocumentoEscopo> getListaDocumentoEscopo() {
		return this.listaDocumentoEscopo;
	}

	public void setListaDocumentoEscopo(List<DocumentoEscopo> listaDocumentoEscopo) {
		this.listaDocumentoEscopo = listaDocumentoEscopo;
	}

	public DocumentoComentario getDocumentoComentario() {
		return this.documentoComentario;
	}

	public void setDocumentoComentario(DocumentoComentario documentoComentario) {
		this.documentoComentario = documentoComentario;
	}

	public DocumentoComentarioService getDocumentoComentarioService() {
		return this.documentoComentarioService;
	}

	public void setDocumentoComentarioService(DocumentoComentarioService documentoComentarioService) {
		this.documentoComentarioService = documentoComentarioService;
	}

	public List<DocumentoComentario> getListaDocumentoComentario() {
		return this.listaDocumentoComentario;
	}

	public void setListaDocumentoComentario(List<DocumentoComentario> listaDocumentoComentario) {
		this.listaDocumentoComentario = listaDocumentoComentario;
	}

	public Documento getDocumentoDono() {
		return this.documentoDono;
	}

	public void setDocumentoDono(Documento documentoDono) {
		this.documentoDono = documentoDono;
	}

	public NvEmbarcacaoService getNvEmbarcacaoService() {
		return this.nvEmbarcacaoService;
	}

	public void setNvEmbarcacaoService(NvEmbarcacaoService nvEmbarcacaoService) {
		this.nvEmbarcacaoService = nvEmbarcacaoService;
	}

	public NvEmbarcacao getNvEmbarcacao() {
		return this.nvEmbarcacao;
	}

	public void setNvEmbarcacao(NvEmbarcacao nvEmbarcacao) {
		this.nvEmbarcacao = nvEmbarcacao;
	}

	public String getNaturezaLancamento() {
		return this.naturezaLancamento;
	}

	public void setNaturezaLancamento(String naturezaLancamento) {
		this.naturezaLancamento = naturezaLancamento;
	}

	public Upload getUpload() {
		return this.upload;
	}

	public void setUpload(Upload upload) {
		this.upload = upload;
	}

	public UploadController getUploadController() {
		return this.uploadController;
	}

	public void setUploadController(UploadController uploadController) {
		this.uploadController = uploadController;
	}

	public List<Upload> getListaUpload() {
		return this.listaUpload;
	}

	public void setListaUpload(List<Upload> listaUpload) {
		this.listaUpload = listaUpload;
	}

	public UploadService getUploadService() {
		return this.uploadService;
	}

	public void setUploadService(UploadService uploadService) {
		this.uploadService = uploadService;
	}

	public UploadedFile getArquivo() {
		return this.arquivo;
	}

	public void setArquivo(UploadedFile arquivo) {
		this.arquivo = arquivo;
	}

	public StreamedContent getFileDownload() {
		return this.fileDownload;
	}

	public StreamedContent getArquivoVisualizar() {
		return this.arquivoVisualizar;
	}

	public void setArquivoVisualizar(StreamedContent arquivoVisualizar) {
		this.arquivoVisualizar = arquivoVisualizar;
	}

	public void setFileDownload(StreamedContent fileDownload) {
		this.fileDownload = fileDownload;
	}

	public PlanoItemService getPlanoItemService() {
		return this.planoItemService;
	}

	public void setPlanoItemService(PlanoItemService planoItemService) {
		this.planoItemService = planoItemService;
	}

	public List<PlanoItem> getListaPlanoItem() {
		return this.listaPlanoItem;
	}

	public void setListaPlanoItem(List<PlanoItem> listaPlanoItem) {
		this.listaPlanoItem = listaPlanoItem;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
        
        public String getCaminho() {
		return this.caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}
}
