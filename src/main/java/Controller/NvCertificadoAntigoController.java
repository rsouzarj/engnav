package Controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import ClausulaSQL.ClausulaWhere;
import ClausulaSQL.GeneroCondicaoWhere;
import ClausulaSQL.OperacaoCondicaoWhere;
import ClausulaSQL.TipoCondicaoWhere;
import Colaborador.Colaborador;
import Colaborador.ColaboradorService;
import NvCertificado.NvCertificado;
import NvCertificado.NvCertificadoService;
import NvCertificadoCalculo.NvCertificadoCalculo;
import NvCertificadoCalculo.NvCertificadoCalculoService;
import NvCertificadoCalculoD.NvCertificadoCalculoD;
import NvCertificadoCalculoD.NvCertificadoCalculoDService;
import NvCertificadoDetalhe.NvCertificadoDetalhe;
import NvCertificadoDetalhe.NvCertificadoDetalheService;
import NvEmbarcacao.NvEmbarcacao;
import NvEmbarcacao.NvEmbarcacaoService;
import NvEmbarcacaoDetalhe.NvEmbarcacaoDetalhe;
import NvEmbarcacaoDetalhe.NvEmbarcacaoDetalheService;
import NvTipoCertificado.NvTipoCertificado;
import NvTipoCertificado.NvTipoCertificadoService;
import NvVistoria.NvVistoria;
import NvVistoria.NvVistoriaService;
import Util.Conexao;
import Util.Situacao;
import Util.Util;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

@ManagedBean(name = "nvCertificadoAntigoController")
@ViewScoped
public class NvCertificadoAntigoController {

	@ManagedProperty("#{loginController}")
	protected LoginController loginController;

	NvCertificadoService nvCertificadoService = new NvCertificadoService();

	NvCertificado nvCertificado = new NvCertificado();

	List<NvCertificado> listaNvCertificado = new ArrayList();

	String pesquisa = "";

	Integer tela = Integer.valueOf(0);

	Util util = new Util();

	NvTipoCertificadoService nvTipoCertificadoService = new NvTipoCertificadoService();

	List<NvTipoCertificado> listaNvTipoCertificado = new ArrayList();

	NvTipoCertificado nvTipoCertificado = new NvTipoCertificado();

	NvEmbarcacao nvEmbarcacao = new NvEmbarcacao();

	NvEmbarcacaoService nvEmbarcacaoService = new NvEmbarcacaoService();

	List<NvEmbarcacao> listaNvEmbarcacao = new ArrayList();

	NvVistoriaService nvVistoriaService = new NvVistoriaService();

	List<NvVistoria> listaNvVistoria = new ArrayList();

	NvVistoria nvVistoria = new NvVistoria();

	NvCertificadoCalculoService nvCertificadoCalculoService = new NvCertificadoCalculoService();

	NvCertificadoCalculo nvCertificadoCalculo = new NvCertificadoCalculo();

	NvCertificadoCalculoDService nvCertificadoCalculoDService = new NvCertificadoCalculoDService();

	List<NvCertificadoCalculoD> listaNvCertificadoCalculoD = new ArrayList();

	NvCertificadoCalculoD nvCertificadoCalculoD = new NvCertificadoCalculoD();

	NvCertificadoDetalheService nvCertificadoDetalheService = new NvCertificadoDetalheService();

	List<NvCertificadoDetalhe> listaNvCertificadoDetalhe = new ArrayList();

	NvCertificadoDetalhe nvCertificadoDetalhe = new NvCertificadoDetalhe();

	ColaboradorService colaboradorService = new ColaboradorService();

	List<Colaborador> listaColaborador = new ArrayList();

	String seqTipoCertificadoSelecionado;

	String seqEmbarcacaoSelecionado;

	String seqColaboradorSelecionado;

	String statusConvalidacaoSelecionado = "Todos";
	
	boolean porDataValidade = false;

	String dataValidadeInicial;

	String dataValidadeFinal;

	boolean porDataEmissao = false;

	String dataEmissaoInicial;

	String dataEmissaoFinal;

	boolean porDataCadastro = false;

	String dataCadastroInicial;

	String dataCadastroFinal;

	boolean porDataConvalidacao = false;

	String dataConvalidacaoInicial;

	String dataConvalidacaoFinal;

	boolean porDataConvalidacaoPrazo = false;

	String dataConvalidacaoPrazoInicial;

	String dataConvalidacaoPrazoFinal;

	String seqVistoriaEmissao = "";

	String seqTipoCertificadoEmissao = "";

	int limitePrazo = 0;

	public void iniciar() {
		if ((this.loginController.usuario.getAcOpCertificado() == null)
				|| (this.loginController.usuario.getAcOpCertificado().equals("-1"))) {
			this.loginController.mudarPagina("/organizacional/acessonegado.jsf");
			return;
		}

		this.listaNvEmbarcacao = this.nvEmbarcacaoService.listar(
				this.loginController.getEmpresa().getSeqEmpresa(), "", Situacao.ATIVO);
		this.listaNvTipoCertificado = this.nvTipoCertificadoService.listar(
				this.loginController.getEmpresa().getSeqEmpresa(), "", Situacao.ATIVO);
		this.listaColaborador = this.colaboradorService.listar(
				this.loginController.getEmpresa().getSeqEmpresa(), "", Situacao.ATIVO);

		if (!this.seqVistoriaEmissao.equals("")) {
			this.tela = Integer.valueOf(1);
			
		}
	}

        
        
	public void filtrar() {
		this.tela = Integer.valueOf(0);
		boolean executar = true;

		ClausulaWhere condicao = new ClausulaWhere();

		if ((isPorDataConvalidacao() == true) || (isPorDataConvalidacaoPrazo() == true)
				|| (!this.statusConvalidacaoSelecionado.equals("Todos"))) {
			condicao.AdicionarCondicaoManual(
					" left join nv_certificado_detalhe on nv_certificado_detalhe.seq_nv_certificado = nv_certificado.seq_nv_certificado ");
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio,
					"NV_CERTIFICADO.seq_empresa", GeneroCondicaoWhere.igual,
					String.valueOf(this.loginController.getEmpresa().getSeqEmpresa()),
					TipoCondicaoWhere.Numero);
		}
		else {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio,
					"NV_CERTIFICADO.seq_empresa", GeneroCondicaoWhere.igual,
					String.valueOf(this.loginController.getEmpresa().getSeqEmpresa()),
					TipoCondicaoWhere.Numero);
		}
		
		if (isPorDataConvalidacao() == true) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and,
					"nv_certificado_detalhe.data_inicial", GeneroCondicaoWhere.MaiorIgual,
					String.valueOf(this.dataConvalidacaoInicial), TipoCondicaoWhere.Data);
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and,
					"nv_certificado_detalhe.data_final", GeneroCondicaoWhere.MenorIgual,
					String.valueOf(this.dataConvalidacaoFinal), TipoCondicaoWhere.Data);
		}

		if (isPorDataConvalidacaoPrazo() == true) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and,
					"nv_certificado_detalhe.prazo", GeneroCondicaoWhere.MaiorIgual,
					String.valueOf(this.dataConvalidacaoPrazoInicial),
					TipoCondicaoWhere.Data);
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and,
					"nv_certificado_detalhe.prazo", GeneroCondicaoWhere.MenorIgual,
					String.valueOf(this.dataConvalidacaoPrazoFinal),
					TipoCondicaoWhere.Data);
		}

		if (!this.statusConvalidacaoSelecionado.equals("Todos")) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and,
					"nv_certificado_detalhe.status", GeneroCondicaoWhere.igual,
					this.statusConvalidacaoSelecionado, TipoCondicaoWhere.Texto);
		}

		if (this.seqTipoCertificadoSelecionado != null) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and,
					"NV_CERTIFICADO.seq_nv_tipo_certificado", GeneroCondicaoWhere.igual,
					String.valueOf(this.seqTipoCertificadoSelecionado),
					TipoCondicaoWhere.Numero);
		}

		if (this.seqEmbarcacaoSelecionado != null) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and,
					"NV_CERTIFICADO.seq_nv_embarcacao", GeneroCondicaoWhere.igual,
					String.valueOf(this.seqEmbarcacaoSelecionado),
					TipoCondicaoWhere.Numero);
		}

		if (isPorDataValidade() == true) {
			if ((this.dataValidadeInicial == null) || (this.dataValidadeFinal == null)) {
				executar = false;
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"[DATA DE VALIDADE] é obrigatório!", ""));
			}
			else {
				condicao.AdicionarCondicao(OperacaoCondicaoWhere.and,
						"NV_CERTIFICADO.data_validade", GeneroCondicaoWhere.MaiorIgual,
						String.valueOf(this.dataValidadeInicial), TipoCondicaoWhere.Data);
				condicao.AdicionarCondicao(OperacaoCondicaoWhere.and,
						"NV_CERTIFICADO.data_validade", GeneroCondicaoWhere.MenorIgual,
						String.valueOf(this.dataValidadeFinal), TipoCondicaoWhere.Data);
			}
		}

		if (isPorDataEmissao() == true) {
			if ((this.dataEmissaoInicial == null) || (this.dataEmissaoFinal == null)) {
				executar = false;
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"[DATA DE EMISSÃO] é obrigatório!", ""));
			}
			else {
				condicao.AdicionarCondicao(OperacaoCondicaoWhere.and,
						"NV_CERTIFICADO.data_emissao", GeneroCondicaoWhere.MaiorIgual,
						String.valueOf(this.dataEmissaoInicial), TipoCondicaoWhere.Data);
				condicao.AdicionarCondicao(OperacaoCondicaoWhere.and,
						"NV_CERTIFICADO.data_emissao", GeneroCondicaoWhere.MenorIgual,
						String.valueOf(this.dataEmissaoFinal), TipoCondicaoWhere.Data);
			}
		}

		if (isPorDataCadastro() == true) {
			if ((this.dataCadastroInicial == null) || (this.dataCadastroFinal == null)) {
				executar = false;
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"[DATA DE CADASTRO] é obrigatório!", ""));
			}
			else {
				condicao.AdicionarCondicao(OperacaoCondicaoWhere.and,
						"NV_CERTIFICADO.data_cadastro", GeneroCondicaoWhere.MaiorIgual,
						String.valueOf(this.dataCadastroInicial), TipoCondicaoWhere.Data);
				condicao.AdicionarCondicao(OperacaoCondicaoWhere.and,
						"NV_CERTIFICADO.data_cadastro", GeneroCondicaoWhere.MenorIgual,
						String.valueOf(this.dataCadastroFinal), TipoCondicaoWhere.Data);
			}
		}

		if (executar == true) {
			this.listaNvCertificado = this.nvCertificadoService.listar(condicao);
		}
	}

	public void salvarDetalhe() {
		if (this.nvCertificado.getSeqNvCertificado().equals("-1")) {
			System.out.println(this.nvCertificado.getSeqNvCertificado());
			salvar(1);
		}
		this.nvCertificadoDetalhe
				.setSeqNvCertificado(this.nvCertificado.getSeqNvCertificado());
		this.nvCertificadoDetalhe = this.nvCertificadoDetalheService
				.salvar(this.nvCertificadoDetalhe);
		listarDetalhe();
		novoDetalhe();
	}

	public void salvar(int pTela) {
		this.nvTipoCertificado = this.nvTipoCertificadoService
				.buscar(this.nvCertificado.getSeqNvTipoCertificado());
		NvVistoria nvVistoriaTMP = this.nvVistoriaService
				.buscar(this.nvCertificado.getSeqNvVistoria());

		System.out.println(
				"SEQ CERTIFICADO ->  " + this.nvCertificado.getSeqNvCertificado());
		System.out
				.println("SEQ COLABORADOR -> " + this.nvCertificado.getSeqColaborador());
		System.out.println(
				"TIPO DE CERTIFICADO -> " + this.nvCertificado.getTipoCertificado());
		System.out
				.println("TIPO DE CERTIFICADO -> " + this.nvTipoCertificado.getCodigo());
		short prazoTmp;
		if (this.nvCertificado.getPrazo() > 0) {
			prazoTmp = (short) (this.nvCertificado.getPrazoTotal()
					+ this.nvCertificado.getPrazo());
			Calendar dataSomaValidade = Calendar.getInstance();

			System.out
					.println("Tipo de certificado: " + this.nvTipoCertificado.getCodigo()
							+ "----" + this.nvTipoCertificado.getNome());

			if (this.nvTipoCertificado.getCodigo().equals("CSN")) {
				switch (this.nvCertificado.getStatus()) {
				case "Provisório":
					System.out.println("Caiu no Provisorio");

					dataSomaValidade.setTime(nvVistoriaTMP.getDataDocagem());
					break;
				case "Condicional":
					System.out.println("Caiu no Condicional");
					dataSomaValidade.setTime(nvVistoriaTMP.getDataVistoria());
					break;
				default:
					System.out.println("Caiu no Definitivo");
					dataSomaValidade.setTime(nvVistoriaTMP.getDataDocagem());
				}

				dataSomaValidade.setTime(nvVistoriaTMP.getDataDocagem());
				dataSomaValidade.add(5, 1);
			}
			else {
				dataSomaValidade.setTime(nvVistoriaTMP.getDataVistoria());
				dataSomaValidade.add(5, 1);
			}

			if (this.nvCertificado.getStatus().equals("Provisório")) {
				if (prazoTmp <= 180) {
					if (this.nvCertificado.getPrazo() < 0) {
						FacesContext.getCurrentInstance().addMessage(null,
								new FacesMessage(FacesMessage.SEVERITY_ERROR,
										"Falha ao salvar registro. Prazo deve ser maior que 0.",
										""));
						return;
					}

					

					this.nvCertificado.setPrazoTotal(prazoTmp);
				}
				else {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							"Falha ao salvar registro. Certificado provisório deve ter no máximo 180 dias de prazo total. O prazo total atual é de "
									+ this.nvCertificado.getPrazoTotal() + " dias",
							""));
				}

			}
			else if (this.nvCertificado.getStatus().equals("Condicional")) {
				if (prazoTmp <= 90) {
					if (this.nvCertificado.getPrazo() < 0) {
						FacesContext.getCurrentInstance().addMessage(null,
								new FacesMessage(FacesMessage.SEVERITY_ERROR,
										"Falha ao salvar registro. Prazo deve ser maior que 0.",
										""));
						return;
					}

					

					this.nvCertificado.setPrazoTotal(prazoTmp);
				}
				else {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							"Falha ao salvar registro. Certificado condicional deve ter no máximo 90 dias de prazo total. O prazo total atual é de "
									+ this.nvCertificado.getPrazoTotal() + " dias",
							""));
					return;
				}
			}
		}

		if (this.nvCertificado.getSeqNvCertificado().equals("-1")) {
			this.nvCertificado
					.setSeqEmpresa(this.loginController.getEmpresa().getSeqEmpresa());
			this.nvCertificado.setDataCadastro(new Date());
			this.nvCertificado.setSeqNvCertificado(null);
			this.nvCertificado = this.nvCertificadoService.salvar(this.nvCertificado);

			for (NvCertificadoDetalhe detalhe : this.listaNvCertificadoDetalhe) {
				detalhe.setSeqNvCertificado(this.nvCertificado.getSeqNvCertificado());
				detalhe = this.nvCertificadoDetalheService.salvar(detalhe);
			}
		}
		else {
			System.out.println("obs" + this.nvCertificado.getObservacao());

			this.nvCertificado.setObservacao(this.nvCertificado.getObservacao()
					.replace("font-size: 13.3333px;", ""));
			this.nvCertificado.setObservacao(
					this.nvCertificado.getObservacao().replace("font-size: 10pt;", ""));

			this.nvCertificado = this.nvCertificadoService.salvar(this.nvCertificado);
			System.out.println("Salvou");
			System.out.println(
					"SEQ COLABORADOR -> " + this.nvCertificado.getSeqColaborador());
		}
		verificarPrazo();
		listar();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
				FacesMessage.SEVERITY_INFO, "Registro armazenado com sucesso.", ""));
		this.tela = Integer.valueOf(pTela);
	}

	public void novo() {
		this.nvCertificado = new NvCertificado();
		listarDetalhe();

		this.tela = Integer.valueOf(1);
	}

	public void listar() {
		this.listaNvCertificado = this.nvCertificadoService.listar(
				this.loginController.getEmpresa().getSeqEmpresa(), this.pesquisa,
				Situacao.TODOS);
	}

	public void deletar() {
		if (this.nvCertificadoService.deletar(this.nvCertificado)) {
			listar();
			this.nvCertificado = new NvCertificado();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_INFO, "Registro eliminado com sucesso.", ""));
			this.tela = Integer.valueOf(0);
			listar();
		}
		else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Falha ao excluir registro.", ""));
		}
	}

	public void fecharTela() throws IOException {
		this.loginController.mudarPagina("/principal/ principal.jsf");
	}

	public void selecionar(NvCertificado pNvCertificado) {
		this.nvCertificado = pNvCertificado;
		this.nvVistoria = this.nvVistoriaService
				.buscar(this.nvCertificado.getSeqNvVistoria());
		System.out.println(this.nvCertificado.getTipoCertificado());
		verificarPrazo();
		this.nvCertificado.setPrazo((short) 0);
		listarDetalhe();
		this.tela = Integer.valueOf(1);
	}

	public void verificarPrazo() {
		switch (this.nvCertificado.getStatus()) {
		case "Provisório":
			if (this.nvCertificado.getPrazoTotal() > 0) {
				this.limitePrazo = (180 - this.nvCertificado.getPrazoTotal());
			}
			else {
				this.limitePrazo = 180;
			}
			break;

		case "Condicional":
			if (this.nvCertificado.getPrazoTotal() > 0) {
				this.limitePrazo = (90 - this.nvCertificado.getPrazoTotal());
			}
			else {
				this.limitePrazo = 90;
			}
			break;
		}
	}

	public void mudarTela(Integer pTela) {
		this.listaNvCertificado = new ArrayList();
		this.nvCertificado = new NvCertificado();
		this.tela = pTela;
	}

	public void imprimir() throws IOException, JRException {
		this.nvVistoria = this.nvVistoriaService
				.buscar(this.nvCertificado.getSeqNvVistoria());
		this.nvEmbarcacao = this.nvEmbarcacaoService
				.buscar(this.nvCertificado.getSeqNvEmbarcacao());

		String uuidCertificado = getUUIDCertificado(this.nvCertificado);

		HashMap parametro = new HashMap();
		Conexao conexao = new Conexao();
		Connection conn = Conexao.getConnection();

		String caminho = "";
		if ((this.nvCertificado.getSeqNvCertificado().equals("-1"))
				|| (this.nvCertificado.getSeqNvCertificado().equals(""))) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Você precisa primeiro salvar o certificado com informações válidas!",
					""));
			return;
		}
		if (this.nvCertificado.getObservacao() == null) {
			this.nvCertificado.setObservacao(" ");
		}
		if (this.nvCertificado.getObservacao().contains("font-size: 13.3333px;")) {
			this.nvCertificado.setObservacao(this.nvCertificado.getObservacao()
					.replace("font-size: 13.3333px;", ""));
			this.nvCertificado.setObservacao(
					this.nvCertificado.getObservacao().replace("font-size: 10pt;", ""));
			this.nvCertificado = this.nvCertificadoService.salvar(this.nvCertificado);
		}

		if (this.nvCertificado.getStatus().equals("Provisório")) {
			parametro.put("pCondicao", "(PROVISÓRIO)");
			if (this.nvCertificado.getSeqNvTipoCertificado().equals("164")) {
				//feito
				caminho = "/relatorio/CERTIFICADO NACIONAL DE BORDA LIVRE PARA NAVEGAÇÃO DE MAR ABERTO/BORDA LIVRE MAR ABERTO PROVISÓRIO.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("28")) {
				//feito
				caminho = "/relatorio/CERTIFICADO NACIONAL DE ARQUEAÇÃO/ARQUEAÇÃO PROVISÓRIO.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("165")) {
				//feito
				caminho = "/relatorio/BLNI/BORDA LIVRE NAVEGAÇÃO INTERIOR PROVISÓRIO.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("103")) {
				caminho = "/relatorio/CSN/MAR ABERTO/CSN MAR ABERTO_2_1.jasper";
				//feito
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("141")) {
				//feito
				caminho = "/relatorio/CSN/NAVEGAÇÃO INTERIOR/07/07 - PROV-COND.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("101")) {
				//feito
				caminho = "/relatorio/CSN/NAVEGAÇÃO INTERIOR/06/06 - PROV-COND.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("163")) {
				//feito
				caminho = "/relatorio/CSN/NAVEGAÇÃO INTERIOR/05/05 - PROV-COND.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("173")) {
				//feito
				caminho = "/relatorio/CSN/NAVEGAÇÃO INTERIOR/04/04 - PROV-COND.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("161")) {
				//feito
				caminho = "/relatorio/CSN/NAVEGAÇÃO INTERIOR/03/03 - PROV-COND.jasper"; //
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("162")) {
				//feito
				caminho = "/relatorio/CSN/NAVEGAÇÃO INTERIOR/02/02 - PROV-COND.jasper";
			}
                        /*else if (this.nvCertificado.getSeqNvTipoCertificado().equals("201")) {
				//feito
				caminho = "/relatorio/CERTIFICADO HELIDEQUE/RH.jasper";
			}*/
			else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Modelo de certificado provisório não encontrado!", ""));
			}

		}
		else if (this.nvCertificado.getStatus().equals("Definitivo")) {
			parametro.put("pCondicao","");
			if (this.nvCertificado.getSeqNvTipoCertificado().equals("28")) {
				//feito
				caminho = "/relatorio/CERTIFICADO NACIONAL DE ARQUEAÇÃO/ARQUEAÇÃO DEFINITIVA.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("165")) {
				//feito
				caminho = "/relatorio/BLNI/BORDA LIVRE NAVEGAÇÃO INTERIOR DEFINITIVO.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("102")) {
				//nao existe
				caminho = "/relatorio/CERTIFICADO DE SEGURANÇA DA NAVEGAÇÃO/Certificado de Segurança da Navegação - NI (Embarcações Não-Propulsadas).jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("164")) {
				//feito
				caminho = "/relatorio/CERTIFICADO NACIONAL DE BORDA LIVRE PARA NAVEGAÇÃO DE MAR ABERTO/BORDA LIVRE MAR ABERTO DEFINITIVO.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("103")) {
				//feito
				caminho = "/relatorio/CSN/MAR ABERTO/CSN MAR ABERTO DEFINITIVO.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("141")) {
				//feito
				caminho = "/relatorio/CSN/NAVEGAÇÃO INTERIOR/07/07 - DEFINITIVO.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("101")) {
				//feito
				caminho = "/relatorio/CSN/NAVEGAÇÃO INTERIOR/06/06 - DEFINITIVO.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("163")) {
				//feito
				caminho = "/relatorio/CSN/NAVEGAÇÃO INTERIOR/05/05 - DEFINITIVO.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("173")) {
				//feito
				caminho = "/relatorio/CSN/NAVEGAÇÃO INTERIOR/04/04 - DEFINITIVO.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("161")) {
				//feito
				caminho = "/relatorio/CSN/NAVEGAÇÃO INTERIOR/03/03 - DEFINITIVO.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("162")) {
				//feito
				caminho =  "/relatorio/CSN/NAVEGAÇÃO INTERIOR/07/07 - DEFINITIVO.jasper";//"/relatorio/CSN/NAVEGAÇÃO INTERIOR/02/02 - DEFINITIVO.jasper";
			}
                        /*else if (this.nvCertificado.getSeqNvTipoCertificado().equals("201")) {
				//feito
				caminho = "/relatorio/CERTIFICADO HELIDEQUE/RH.jasper";
			}*/
			else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Modelo de certificado definitivo não encontrado!", ""));
			}

		}
                
                        else if (this.nvCertificado.getStatus().equals("Aprovado")) {
			parametro.put("pCondicao", "");
			if (this.nvCertificado.getSeqNvTipoCertificado().equals("201")) {
				//feito
				caminho = "/relatorio/CERTIFICADO HELIDEQUE/RH.jasper";
			}
                        else if (this.nvCertificado.getSeqNvTipoCertificado().equals("202")) {
				//feito
				caminho =  "/relatorio/CERTIFICADO HELIDEQUE/RTPH.jasper";
			}
                        else if (this.nvCertificado.getSeqNvTipoCertificado().equals("203")) {
				//feito
				caminho =  "/relatorio/CERTIFICADO HELIDEQUE/RB.jasper";
			}
                        else if (this.nvCertificado.getSeqNvTipoCertificado().equals("204")) {
				//feito
				caminho =  "/relatorio/CERTIFICADO HELIDEQUE/CA.jasper";
			}
                        else if (this.nvCertificado.getSeqNvTipoCertificado().equals("205")) {
				//feito
				caminho =  "/relatorio/CERTIFICADO HELIDEQUE/SC.jasper";
			}
                        else if (this.nvCertificado.getSeqNvTipoCertificado().equals("206")) {
				//feito
				caminho =  "/relatorio/CERTIFICADO HELIDEQUE/MCTAP.jasper";
			}
                        else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Modelo de certificado definitivo não encontrado!", ""));
                        }
                        }
                
		else if (this.nvCertificado.getStatus().equals("Condicional")) {
			parametro.put("pCondicao", "(CONDICIONAL)");

			if (this.nvCertificado.getSeqNvTipoCertificado().equals("28")) {
				//feito
				caminho = "/relatorio/CERTIFICADO NACIONAL DE ARQUEAÇÃO/ARQUEAÇÃO CONDICIONAL.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("165")) {
				//todo
				caminho = "/relatorio/BLNI/BORDA LIVRE NAVEGAÇÃO INTERIOR CONDICIONAL.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("103")) {
				caminho = "/relatorio/CSN/MAR ABERTO/CSN MAR ABERTO_2_1.jasper";
				//feito
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("164")) {
				//feito
				caminho = "/relatorio/CERTIFICADO NACIONAL DE BORDA LIVRE PARA NAVEGAÇÃO DE MAR ABERTO/BORDA LIVRE MAR ABERTO CONDICIONAL.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("141")) {
				//feito
				caminho = "/relatorio/CSN/NAVEGAÇÃO INTERIOR/07/07 - PROV-COND.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("101")) {
				//feito
				caminho = "/relatorio/CSN/NAVEGAÇÃO INTERIOR/06/06 - PROV-COND.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("163")) {
				//feito
				caminho = "/relatorio/CSN/NAVEGAÇÃO INTERIOR/05/05 - PROV-COND.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("173")) {
				//feito
				caminho = "/relatorio/CSN/NAVEGAÇÃO INTERIOR/04/04 - PROV-COND.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("161")) {
				caminho = "/relatorio/CSN/NAVEGAÇÃO INTERIOR/03/03 - PROV-COND.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("162")) {
				//feito
				caminho = "/relatorio/CSN/NAVEGAÇÃO INTERIOR/02/02 - PROV-COND.jasper";
			}
                       /* else if (this.nvCertificado.getSeqNvTipoCertificado().equals("201")) {
				//feito
				caminho = "/relatorio/CERTIFICADO HELIDEQUE/RH.jasper";
			}*/       
                        
                        else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Modelo de certificado condicional não encontrado!", ""));

				return;
			}
		}

		if (this.nvVistoria.getDataVistoria() != null) {
			parametro.put("pDataVistoria",
					this.util.DataToString(this.nvVistoria.getDataVistoria()));
		}
		else {
			parametro.put("pDataVistoria", "__/__/____");
		}

		if (this.nvVistoria.getDataDocagem() != null) {
			parametro.put("pDataDocagem",
					this.util.DataToString(this.nvVistoria.getDataDocagem()));
		}
		else {
			parametro.put("pDataDocagem", "__/__/____");
		}

		if (this.nvEmbarcacao.getDataInscricao() != null) {
			Integer pDataInscricao = Integer
					.valueOf(this.nvEmbarcacao.getDataInscricao().getYear() + 1900);
			parametro.put("pDataInscricao", pDataInscricao.toString());
		}
		else {
			parametro.put("pDataInscricao", "");
		}

		if (this.nvEmbarcacao.getRequisitosTransporteColetivo().equals("Sim")) {
			parametro.put("pSim", "X");
		}
		else if (this.nvEmbarcacao.getRequisitosTransporteColetivo().equals("Não")) {
			parametro.put("pNao", "X");
		}

		parametro.put("pSeqCertificado",
				Long.valueOf(this.nvCertificado.getSeqNvCertificado()));
		parametro.put("pImagem", "../../images/brasao.gif");
		parametro.put("pEmissao",
				this.util.DataPorExtenso(
						"Expedido em: " + this.nvCertificado.getLocalEmissao() + ", ",
						this.nvCertificado.getDataEmissao()));
		Date data = new Date();
		Locale local = new Locale("pt", "BR");
		DateFormat dateFormat = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", local);
		parametro.put("pValidade",
				"Válido até: " + (this.nvCertificado.getDataValidade() != null
						? dateFormat.format(this.nvCertificado.getDataValidade())
						: ""));
		parametro.put("pDataValidade",
				this.util.DataPorExtenso("", this.nvCertificado.getDataValidade()));

		System.out.println(
				"Area Navegação tipo: " + this.nvEmbarcacao.getAreaNavegacaoTipo());

		if (this.nvEmbarcacao.getAreaNavegacaoTipo().contains("Área 01 e 02")) {
			parametro.put("pAreaNavegacao", "ÁREA DE NAVEGAÇÃO: ( X ) 1  ( X ) 2");
			parametro.put("x1", "X");
			parametro.put("x2", "X");
		}
		else if (this.nvEmbarcacao.getAreaNavegacaoTipo().contains("Área 01")) {
			parametro.put("pAreaNavegacao", "ÁREA DE NAVEGAÇÃO: ( X ) 1  (  ) 2");
			parametro.put("pNumeroCirculo", "1");
			parametro.put("x1", "X");
		}
		else if (this.nvEmbarcacao.getAreaNavegacaoTipo().contains("Área 02")) {
			parametro.put("pAreaNavegacao", "ÁREA DE NAVEGAÇÃO: (  ) 1  ( X ) 2");
			parametro.put("pNumeroCirculo", "2");
			parametro.put("x2", "X");
		}
		else {
			parametro.put("pAreaNavegacao", "ÁREA DE NAVEGAÇÃO: (  ) 1  (  ) 2");
			parametro.put("pNumeroCirculo", "");
		}

		switch (this.nvEmbarcacao.getTipo()) {
		case "A":
			parametro.put("pEmbarcacaoTipo",
					"EMBARCAÇÃO DO TIPO: ( X ) A     (  ) B     (  ) C      (  ) D     (  ) E  ");
			break;
		case "B":
			parametro.put("pEmbarcacaoTipo",
					"EMBARCAÇÃO DO TIPO: (  ) A     ( X ) B     (  ) C      (  ) D     (  ) E  ");
			break;
		case "C":
			parametro.put("pEmbarcacaoTipo",
					"EMBARCAÇÃO DO TIPO: (  ) A     (  ) B     ( X ) C      (  ) D     (  ) E  ");
			break;
		case "D":
			parametro.put("pEmbarcacaoTipo",
					"EMBARCAÇÃO DO TIPO: (  ) A     (  ) B     (  ) C      ( X ) D     (  ) E  ");
			break;
		case "E":
			parametro.put("pEmbarcacaoTipo",
					"EMBARCAÇÃO DO TIPO: (  ) A     (  ) B     (  ) C      (  ) D     ( X ) E  ");
			break;
		default:
			parametro.put("pEmbarcacaoTipo",
					"EMBARCAÇÃO DO TIPO: (  ) A     (  ) B     (  ) C      (  ) D     (  ) E  ");
		}

		NvEmbarcacaoDetalheService nvEmbarcacaoDetalheService = new NvEmbarcacaoDetalheService();

		String b = "false";
		for (NvEmbarcacaoDetalhe item : nvEmbarcacaoDetalheService
				.listar(this.nvEmbarcacao.getSeqNvEmbarcacao())) {
			if (!item.getOrdem().equals("01")) {
				if ((item.getDetalhe2() == null) && (item.getDetalhe3() == null)
						&& (item.getDetalhe4() == null)) {
					b = "true";
				}
				else {
					b = "false";
					break;
				}
			}
		}
		parametro.put("pLinhaForm", b);
		parametro.put("pVistoriaInicial",
				this.util.DataToString(this.nvVistoria.getDataVistoria()));
		parametro.put("pReportURL", "http://191.252.59.211//erp/operacional/certificado/"+this.nvCertificado.getSeqEmpresa()+"/"+uuidCertificado);
		FacesContext facesContext = FacesContext.getCurrentInstance();

		facesContext.responseComplete();
		ServletContext scontext = (ServletContext) facesContext.getExternalContext()
				.getContext();
		System.out.println(caminho);
		JasperPrint jasperPrint = JasperFillManager
				.fillReport(scontext.getRealPath(caminho), parametro, conn);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		JRPdfExporter exporter = new JRPdfExporter();

		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);

		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

		exporter.exportReport();
		byte[] bytes = baos.toByteArray();

		if ((bytes != null) && (bytes.length > 0)) {
			HttpServletResponse response = (HttpServletResponse) facesContext
					.getExternalContext().getResponse();
			response.setContentType("application/pdf");
			response.setHeader("Content-disposition",
					"inline; filename=\"Certificado_"
							+ this.nvCertificado.getTipoCertificado() + "_"
							+ this.nvCertificado.getIdentificacao() + "_"
							+ this.nvEmbarcacao.getNome() + ".pdf\"");
			response.setContentLength(bytes.length);
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(bytes, 0, bytes.length);
			outputStream.flush();
			outputStream.close();
		}
	}

	private String getUUIDCertificado(NvCertificado certificado) {
		String uuid = "";
		try (Connection conn = Conexao.getConnection()) {
			try(Statement stmt = conn.createStatement()){
				try(ResultSet rs = stmt.executeQuery(
						"select uuid from NV_CERTIFICADO where SEQ_NV_CERTIFICADO = "
								+ certificado.getSeqNvCertificado())){
				while (rs.next()) {
					uuid = rs.getString("uuid");
				}
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return uuid;
	}

	public void novoDetalhe() {
		this.nvCertificadoDetalhe = new NvCertificadoDetalhe();
	}

	public void deletearDetalhe() {
		this.nvCertificadoDetalheService.deletar(this.nvCertificadoDetalhe);
		listarDetalhe();
	}

	public void listarDetalhe() {
		this.listaNvCertificadoDetalhe = this.nvCertificadoDetalheService.listar(this.nvCertificado.getSeqNvCertificado());
	}

	public void selecionarDetalhe(NvCertificadoDetalhe pNvCertificadoDetalhe) {
		this.nvCertificadoDetalhe = pNvCertificadoDetalhe;
	}

	public LoginController getLoginController() {
		return this.loginController;
	}

	public void setLoginController(LoginController loginController) {
		this.loginController = loginController;
	}

	public NvCertificadoService getNvCertificadoService() {
		return this.nvCertificadoService;
	}

	public void setNvCertificadoService(NvCertificadoService nvCertificadoService) {
		this.nvCertificadoService = nvCertificadoService;
	}

	public NvCertificado getNvCertificado() {
		return this.nvCertificado;
	}

	public void setNvCertificado(NvCertificado nvCertificado) {
		this.nvCertificado = nvCertificado;
	}

	public List<NvCertificado> getListaNvCertificado() {
		return this.listaNvCertificado;
	}

	public void setListaNvCertificado(List<NvCertificado> listaNvCertificado) {
		this.listaNvCertificado = listaNvCertificado;
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

	public NvTipoCertificadoService getNvTipoCertificadoService() {
		return this.nvTipoCertificadoService;
	}

	public void setNvTipoCertificadoService(
			NvTipoCertificadoService nvTipoCertificadoService) {
		this.nvTipoCertificadoService = nvTipoCertificadoService;
	}

	public List<NvTipoCertificado> getListaNvTipoCertificado() {
		return this.listaNvTipoCertificado;
	}

	public void setListaNvTipoCertificado(
			List<NvTipoCertificado> listaNvTipoCertificado) {
		this.listaNvTipoCertificado = listaNvTipoCertificado;
	}

	public NvEmbarcacaoService getNvEmbarcacaoService() {
		return this.nvEmbarcacaoService;
	}

	public void setNvEmbarcacaoService(NvEmbarcacaoService nvEmbarcacaoService) {
		this.nvEmbarcacaoService = nvEmbarcacaoService;
	}

	public List<NvEmbarcacao> getListaNvEmbarcacao() {
		return this.listaNvEmbarcacao;
	}

	public void setListaNvEmbarcacao(List<NvEmbarcacao> listaNvEmbarcacao) {
		this.listaNvEmbarcacao = listaNvEmbarcacao;
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

	public NvCertificadoDetalheService getNvCertificadoDetalheService() {
		return this.nvCertificadoDetalheService;
	}

	public void setNvCertificadoDetalheService(
			NvCertificadoDetalheService nvCertificadoDetalheService) {
		this.nvCertificadoDetalheService = nvCertificadoDetalheService;
	}

	public List<NvCertificadoDetalhe> getListaNvCertificadoDetalhe() {
		return this.listaNvCertificadoDetalhe;
	}

	public void setListaNvCertificadoDetalhe(
			List<NvCertificadoDetalhe> listaNvCertificadoDetalhe) {
		this.listaNvCertificadoDetalhe = listaNvCertificadoDetalhe;
	}

	public NvCertificadoDetalhe getNvCertificadoDetalhe() {
		return this.nvCertificadoDetalhe;
	}

	public void setNvCertificadoDetalhe(NvCertificadoDetalhe nvCertificadoDetalhe) {
		this.nvCertificadoDetalhe = nvCertificadoDetalhe;
	}
	
	public String getSeqTipoCertificadoSelecionado() {
		return this.seqTipoCertificadoSelecionado;
	}

	public void setSeqTipoCertificadoSelecionado(String seqTipoCertificadoSelecionado) {
		this.seqTipoCertificadoSelecionado = seqTipoCertificadoSelecionado;
	}

	public String getSeqEmbarcacaoSelecionado() {
		return this.seqEmbarcacaoSelecionado;
	}

	public void setSeqEmbarcacaoSelecionado(String seqEmbarcacaoSelecionado) {
		this.seqEmbarcacaoSelecionado = seqEmbarcacaoSelecionado;
	}

	public String getSeqColaboradorSelecionado() {
		return this.seqColaboradorSelecionado;
	}

	public void setSeqColaboradorSelecionado(String seqColaboradorSelecionado) {
		this.seqColaboradorSelecionado = seqColaboradorSelecionado;
	}

	public boolean isPorDataValidade() {
		return this.porDataValidade;
	}

	public void setPorDataValidade(boolean porDataValidade) {
		this.porDataValidade = porDataValidade;
	}

	public String getDataValidadeInicial() {
		return this.dataValidadeInicial;
	}

	public void setDataValidadeInicial(String dataValidadeInicial) {
		this.dataValidadeInicial = dataValidadeInicial;
	}

	public String getDataValidadeFinal() {
		return this.dataValidadeFinal;
	}

	public void setDataValidadeFinal(String dataValidadeFinal) {
		this.dataValidadeFinal = dataValidadeFinal;
	}

	public boolean isPorDataEmissao() {
		return this.porDataEmissao;
	}

	public void setPorDataEmissao(boolean porDataEmissao) {
		this.porDataEmissao = porDataEmissao;
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

	public boolean isPorDataCadastro() {
		return this.porDataCadastro;
	}

	public void setPorDataCadastro(boolean porDataCadastro) {
		this.porDataCadastro = porDataCadastro;
	}

	public String getDataCadastroInicial() {
		return this.dataCadastroInicial;
	}

	public void setDataCadastroInicial(String dataCadastroInicial) {
		this.dataCadastroInicial = dataCadastroInicial;
	}

	public String getDataCadastroFinal() {
		return this.dataCadastroFinal;
	}

	public void setDataCadastroFinal(String dataCadastroFinal) {
		this.dataCadastroFinal = dataCadastroFinal;
	}

	public boolean isPorDataConvalidacao() {
		return this.porDataConvalidacao;
	}

	public void setPorDataConvalidacao(boolean porDataConvalidacao) {
		this.porDataConvalidacao = porDataConvalidacao;
	}

	public String getDataConvalidacaoInicial() {
		return this.dataConvalidacaoInicial;
	}

	public void setDataConvalidacaoInicial(String dataConvalidacaoInicial) {
		this.dataConvalidacaoInicial = dataConvalidacaoInicial;
	}

	public String getDataConvalidacaoFinal() {
		return this.dataConvalidacaoFinal;
	}

	public void setDataConvalidacaoFinal(String dataConvalidacaoFinal) {
		this.dataConvalidacaoFinal = dataConvalidacaoFinal;
	}

	public String getSeqVistoriaEmissao() {
		return this.seqVistoriaEmissao;
	}

	public void setSeqVistoriaEmissao(String seqVistoriaEmissao) {
		this.seqVistoriaEmissao = seqVistoriaEmissao;
	}

	public String getSeqTipoCErtificadoEmissao() {
		return this.seqTipoCertificadoEmissao;
	}

	public void setSeqTipoCErtificadoEmissao(String seqTipoCertificadoEmissao) {
		this.seqTipoCertificadoEmissao = seqTipoCertificadoEmissao;
	}

	public NvTipoCertificado getNvTipoCertificado() {
		return this.nvTipoCertificado;
	}

	public void setNvTipoCertificado(NvTipoCertificado nvTipoCertificado) {
		this.nvTipoCertificado = nvTipoCertificado;
	}

	public NvEmbarcacao getNvEmbarcacao() {
		return this.nvEmbarcacao;
	}

	public void setNvEmbarcacao(NvEmbarcacao nvEmbarcacao) {
		this.nvEmbarcacao = nvEmbarcacao;
	}

	public NvVistoriaService getNvVistoriaService() {
		return this.nvVistoriaService;
	}

	public void setNvVistoriaService(NvVistoriaService nvVistoriaService) {
		this.nvVistoriaService = nvVistoriaService;
	}

	public List<NvVistoria> getListaNvVistoria() {
		return this.listaNvVistoria;
	}

	public void setListaNvVistoria(List<NvVistoria> listaNvVistoria) {
		this.listaNvVistoria = listaNvVistoria;
	}

	public NvVistoria getNvVistoria() {
		return this.nvVistoria;
	}

	public void setNvVistoria(NvVistoria nvVistoria) {
		this.nvVistoria = nvVistoria;
	}

	public NvCertificadoCalculoService getNvCertificadoCalculoService() {
		return this.nvCertificadoCalculoService;
	}

	public void setNvCertificadoCalculoService(
			NvCertificadoCalculoService nvCertificadoCalculoService) {
		this.nvCertificadoCalculoService = nvCertificadoCalculoService;
	}

	public NvCertificadoCalculo getNvCertificadoCalculo() {
		return this.nvCertificadoCalculo;
	}

	public void setNvCertificadoCalculo(NvCertificadoCalculo nvCertificadoCalculo) {
		this.nvCertificadoCalculo = nvCertificadoCalculo;
	}

	public NvCertificadoCalculoDService getNvCertificadoCalculoDService() {
		return this.nvCertificadoCalculoDService;
	}

	public void setNvCertificadoCalculoDService(
			NvCertificadoCalculoDService nvCertificadoCalculoDService) {
		this.nvCertificadoCalculoDService = nvCertificadoCalculoDService;
	}

	public List<NvCertificadoCalculoD> getListaNvCertificadoCalculoD() {
		return this.listaNvCertificadoCalculoD;
	}

	public void setListaNvCertificadoCalculoD(
			List<NvCertificadoCalculoD> listaNvCertificadoCalculoD) {
		this.listaNvCertificadoCalculoD = listaNvCertificadoCalculoD;
	}

	public NvCertificadoCalculoD getNvCertificadoCalculoD() {
		return this.nvCertificadoCalculoD;
	}

	public void setNvCertificadoCalculoD(NvCertificadoCalculoD nvCertificadoCalculoD) {
		this.nvCertificadoCalculoD = nvCertificadoCalculoD;
	}

	public boolean isPorDataConvalidacaoPrazo() {
		return this.porDataConvalidacaoPrazo;
	}

	public void setPorDataConvalidacaoPrazo(boolean porDataConvalidacaoPrazo) {
		this.porDataConvalidacaoPrazo = porDataConvalidacaoPrazo;
	}

	public String getDataConvalidacaoPrazoInicial() {
		return this.dataConvalidacaoPrazoInicial;
	}

	public void setDataConvalidacaoPrazoInicial(String dataConvalidacaoPrazoInicial) {
		this.dataConvalidacaoPrazoInicial = dataConvalidacaoPrazoInicial;
	}

	public String getDataConvalidacaoPrazoFinal() {
		return this.dataConvalidacaoPrazoFinal;
	}

	public void setDataConvalidacaoPrazoFinal(String dataConvalidacaoPrazoFinal) {
		this.dataConvalidacaoPrazoFinal = dataConvalidacaoPrazoFinal;
	}

	public String getStatusConvalidacaoSelecionado() {
		return this.statusConvalidacaoSelecionado;
	}

	public void setStatusConvalidacaoSelecionado(String statusConvalidacaoSelecionado) {
		this.statusConvalidacaoSelecionado = statusConvalidacaoSelecionado;
	}

	public Util getUtil() {
		return this.util;
	}

	public void setUtil(Util util) {
		this.util = util;
	}

	public int getLimitePrazo() {
		return this.limitePrazo;
	}

	public void setLimitePrazo(int limitePrazo) {
		this.limitePrazo = limitePrazo;
	}

}
