package Controller;

import ClausulaSQL.ClausulaWhere;
import ClausulaSQL.GeneroCondicaoWhere;
import ClausulaSQL.OperacaoCondicaoWhere;
import ClausulaSQL.TipoCondicaoWhere;
import Colaborador.Colaborador;
import Colaborador.ColaboradorService;
import Empresa.Empresa;
import NvCertificado.NvCertificado;
import NvCertificado.NvCertificadoService;
import NvEmbarcacao.NvEmbarcacao;
import NvEmbarcacao.NvEmbarcacaoService;
import NvTipoCertificado.NvTipoCertificado;
import NvTipoCertificado.NvTipoCertificadoService;
import NvTipoVistoria.NvTipoVistoria;
import NvTipoVistoria.NvTipoVistoriaService;
import NvTpVistoriaTpCertificado.NvTpVistoriaTpCertificado;
import NvTpVistoriaTpCertificado.NvTpVistoriaTpCertificadoService;
import NvVistoria.NvVistoria;
import NvVistoria.NvVistoriaService;
import NvVistoriaStatus.NvVistoriaStatus;
import NvVistoriaStatus.NvVistoriaStatusService;
import UnidadeNegocio.UnidadeNegocio;
import UnidadeNegocio.UnidadeNegocioService;
import Uf.Uf;
import Uf.UfService;
import Upload.Upload;
import Upload.UploadService;
import Usuario.Usuario;
import Util.Situacao;
import Util.Util;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "nvVistoriaController")
@ViewScoped
public class NvVistoriaController {
	@ManagedProperty("#{loginController}")
	protected LoginController loginController;
	Util util = new Util();
	NvVistoriaService nvVistoriaService = new NvVistoriaService();
	NvVistoria nvVistoria = new NvVistoria();
	List<NvVistoria> listaNvVistoria = new ArrayList();

	NvTipoVistoriaService nvTipoVistoriaService = new NvTipoVistoriaService();
	List<NvTipoVistoria> listaNvTipoVistoria = new ArrayList();

	NvEmbarcacaoService nvEmbarcacaoService = new NvEmbarcacaoService();
	List<NvEmbarcacao> listaNvEmbarcacao = new ArrayList();

	UfService ufService = new UfService();
	List<Uf> listaUf = new ArrayList();

	Upload upload = new Upload();
	UploadService uploadService = new UploadService();
	UploadController uploadController = new UploadController();
	List<Upload> listaUpload = new ArrayList();

	UploadedFile file;

	StreamedContent fileDownload;
	ColaboradorService colaboradorService = new ColaboradorService();
	List<Colaborador> listaColaborador = new ArrayList();

	NvCertificadoService nvCertificadoService = new NvCertificadoService();
	NvCertificado nvCertificado = new NvCertificado();
	List<NvCertificado> listaNvCertificado = new ArrayList();

	NvTipoCertificadoService nvTipoCertificadoService = new NvTipoCertificadoService();
	List<NvTipoCertificado> listaNvTipoCertificado = new ArrayList();
	NvTipoCertificado nvTipoCertificado = new NvTipoCertificado();

	NvEmbarcacao nvEmbarcacao = new NvEmbarcacao();

	String pesquisa = "";
	Integer tela = Integer.valueOf(0);

	NvTpVistoriaTpCertificadoService nvTpVistoriaTpCertificadoService = new NvTpVistoriaTpCertificadoService();
	List<NvTpVistoriaTpCertificado> listaNvTpVistoriaTpCertificado = new ArrayList();
	NvTpVistoriaTpCertificado nvTpVistoriaTpCertificado = new NvTpVistoriaTpCertificado();

	String seqVistoriaSelecionadaEmissaoCertificado;
	NvVistoriaStatusService NvVistoriaStatusService = new NvVistoriaStatusService();
	List<NvVistoriaStatus> listaNvVistoriaStatus = new ArrayList();
        
        UnidadeNegocio unidadeNegocio = new UnidadeNegocio();
        UnidadeNegocioService unidadeNegocioService = new UnidadeNegocioService();
        List<UnidadeNegocio> listaUnidadeNegocio = new ArrayList();   
        

	private String[] tipoVistoriaSelecionada;

	Long seqTipoVistoriaSelecionado;

	Long seqEmbarcacaoSelecionado;

	Long seqColaboradorSelecionado;
	Long seqStatusSelecionado;
	String UFSelecionada;
	boolean porData;
	String dataInicial;
	String dataFinal;
	boolean porPrazo;
	String prazoInicial;
	String prazoFinal;
        String seqUnidadeNegocioSelecionada;

	public void iniciar() {
		if ((this.loginController.usuario.getAcOpVistoria() == null)
				|| (this.loginController.usuario.getAcOpVistoria().equals("-1"))) {
			this.loginController.mudarPagina("/organizacional/acessonegado.jsf");
			return;
		}

		this.listaNvTipoVistoria = this.nvTipoVistoriaService.listar(this.loginController.getEmpresa().getSeqEmpresa(),
				"", Situacao.ATIVO);
               this.listaUnidadeNegocio = this.unidadeNegocioService.listar(this.loginController.getEmpresa().getSeqEmpresa(),
                                "", Situacao.ATIVO);
		this.listaNvEmbarcacao = this.nvEmbarcacaoService.listar(this.loginController.getEmpresa().getSeqEmpresa(), "",
				Situacao.ATIVO);
		this.listaColaborador = this.colaboradorService.listarv(this.loginController.getEmpresa().getSeqEmpresa(), "",
				Situacao.ATIVO);
		this.listaUf = this.ufService.listar();
		this.listaNvVistoriaStatus = this.NvVistoriaStatusService
				.listar(this.loginController.getEmpresa().getSeqEmpresa(), "", Situacao.ATIVO);
	}

	public void salvar(int pTela) {
		String NvVistoriaUltimo = "";
		if (this.nvVistoria.getSeqNvVistoria() != null) {
			this.nvVistoria = this.nvVistoriaService.salvar(this.nvVistoria);
		} else if (this.tipoVistoriaSelecionada.length == 1) {
			for (String id : this.tipoVistoriaSelecionada) {
				this.nvVistoria.setSeqNvTipoVistoria(id);
				this.nvVistoria.setSeqEmpresa(this.loginController.getEmpresa().getSeqEmpresa());
				/*this.nvVistoria.setSeqUnidadeNegocio(this.unidadeNegocio.getSeqUnidadeNegocio());*/
                                this.nvVistoria = this.nvVistoriaService.salvar(this.nvVistoria);
                               	this.nvVistoria = this.nvVistoriaService.buscar(this.nvVistoria.getSeqNvVistoria());
                                
			}
			selecionar(this.nvVistoria);
		} else {
			for (String id : this.tipoVistoriaSelecionada) {
				this.nvVistoria.setSeqNvTipoVistoria(id);
				this.nvVistoria.setSeqEmpresa(this.loginController.getEmpresa().getSeqEmpresa());
				System.out.println("Id: " + id);
				this.nvVistoria = this.nvVistoriaService.salvar(this.nvVistoria);

				System.out.println("v1" + this.nvVistoria.getSeqNvVistoria());
				NvVistoriaUltimo = this.nvVistoria.getSeqNvVistoria();
				this.nvVistoria.setSeqNvVistoria(null);
			}
			this.nvVistoria = this.nvVistoriaService.buscar(NvVistoriaUltimo);
			selecionar(this.nvVistoria);
                        
		}

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Vistoria armazenada com sucesso.", ""));
		this.tela = Integer.valueOf(pTela);
	}

	public void novo() {
		iniciar();
		this.tipoVistoriaSelecionada = new String[this.listaNvTipoVistoria.size()];
		this.nvVistoria = new NvVistoria();
		this.tela = Integer.valueOf(1);
	}

	public void listar() {
		this.listaNvVistoria = this.nvVistoriaService.listar(this.loginController.getEmpresa().getSeqEmpresa(),
				this.pesquisa, Situacao.TODOS);
	}

	public void deletar() {
		List<NvCertificado> listaCertificadoTMP = this.nvCertificadoService
				.listarPorVistoria(this.nvVistoria.getSeqNvVistoria());
		List<String> certificados = new ArrayList();

		this.listaUpload = this.uploadService.listar(this.loginController.empresa.getSeqEmpresa(),
				this.nvVistoria.getSeqNvVistoria());

		if (listaCertificadoTMP.isEmpty()) {
			if (this.nvVistoriaService.deletar(this.nvVistoria)) {
				for (Upload u : this.listaUpload) {
					this.uploadController.deletar(u);
				}
				listar();
				this.nvVistoria = new NvVistoria();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Vistoria eliminada com sucesso.", ""));
				this.tela = Integer.valueOf(0);
				listar();
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao excluir registro.", ""));
			}
		} else {
			for (NvCertificado listaCertificadoTMP1 : listaCertificadoTMP) {
				certificados.add(listaCertificadoTMP1.getIdentificacao());
			}
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Falha ao deletar vistoria. Você precisa deletar os certificados: "
									+ certificados.toString() + " antes.",
							""));
		}
	}

        
        
        public void aprovar () {
    this.nvVistoria.setAprovador(this.loginController.getUsuario().getSeqUsuario());
    
    this.nvVistoria = this.nvVistoriaService.aprovar(this.nvVistoria);
     
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Vistoria Aprovada.", ""));
    /*this.tela = pTela;*/ return;
  }      
        
        
        
        
        
	public void emitirCertificado() {
		if (this.seqVistoriaSelecionadaEmissaoCertificado == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Favor selecionar tipo de certificado. ", ""));
			return;
		}

		ClausulaWhere condicao = new ClausulaWhere();
		List<Upload> lista = new ArrayList();

		int countLaudo = 0;
		int countRap = 0;

		condicao.AdicionarCondicaoManual(" where upload.seq_nv_vistoria = " + this.nvVistoria.getSeqNvVistoria()
				+ " and (upload.tipo_arquivo='LAUDO' OR UPLOAD.TIPO_ARQUIVO = 'RAP') ");
		lista = this.uploadService.listarFiltro(condicao);

		for (Upload l : lista) {
			if (l.getTipoArquivo().equals("LAUDO")) {
				countLaudo++;
			}
			if (l.getTipoArquivo().equals("RAP")) {
				countRap++;
			}
		}

		if ((countLaudo > 0) && (countRap > 0)) {
			System.out.println("EMITIR CERTIFICADO");

			salvar(1);
			if (this.nvVistoria.getSeqNvVistoriaStatus().equals("2")) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Não é possível emitir certificado com o status atual da vistoria!", ""));

				return;
			}
			if ((this.nvVistoria.getSeqNvVistoriaStatus().equals("3"))
					|| (this.nvVistoria.getSeqNvVistoriaStatus().equals("0"))
					|| (this.nvVistoria.getSeqNvVistoriaStatus().equals("4"))
					|| (this.nvVistoria.getSeqNvVistoriaStatus().equals("21"))) {
				this.nvCertificado.setStatus("Definitivo");
			} else if ((this.nvVistoria.getSeqNvVistoriaStatus().equals("1"))
					|| (this.nvVistoria.getSeqNvVistoriaStatus().equals("42"))) {
				this.nvCertificado.setStatus("Condicional");
			} else if (this.nvVistoria.getSeqNvVistoriaStatus().equals("41")) {
				this.nvCertificado.setStatus("Provisório");
			}
                        
                          else if (this.nvVistoria.getSeqNvVistoriaStatus().equals("61")) {
				this.nvCertificado.setStatus("Aprovado");
			}  

			
                        this.nvCertificado.setTipoCertificado(this.nvTipoCertificado.getNome());
			this.nvCertificado.setSeqNvCertificado(this.nvVistoria.getSeqNvTipoVistoria());
			System.out.println("STATUS: " + this.nvCertificado.getStatus());

			this.loginController
					.mudarPagina("/naval/certificado.jsf?vistoria=" + String.valueOf(this.nvVistoria.getSeqNvVistoria())
							+ "&tipo_certificado=" + this.seqVistoriaSelecionadaEmissaoCertificado);
		} else {
			if (countLaudo < 1) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Favor anexar o Laudo ", ""));
			}
			if (countRap < 1) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Favor anexar o RAP", ""));
			}
		}
	}

	public void fecharTela() throws IOException {
		this.loginController.mudarPagina("/principal/principal.jsf");
	}

	public void selecionar(NvVistoria pNvVistoria) {
		this.nvVistoria = pNvVistoria;
		System.out.println("seq:" + this.nvVistoria.getSeqNvVistoria());
		System.out.println("tipo:" + this.nvVistoria.getTipoVistoria());
		this.listaNvTpVistoriaTpCertificado = this.nvTpVistoriaTpCertificadoService
				.listarPorTipoVistoria(this.nvVistoria.getSeqNvTipoVistoria());
		this.listaUpload = this.uploadService.listar(this.loginController.empresa.getSeqEmpresa(),
				this.nvVistoria.getSeqNvVistoria());
		this.tela = Integer.valueOf(1);
	}

	public void mudarTela(Integer pTela) {
		this.tela = pTela;
	}

	public void filtrar() {
		ClausulaWhere condicao = new ClausulaWhere();
		condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "nv_vistoria.seq_empresa", GeneroCondicaoWhere.igual,
				String.valueOf(this.loginController.getEmpresa().getSeqEmpresa()), TipoCondicaoWhere.Numero);

		if (this.seqTipoVistoriaSelecionado != null) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "nv_vistoria.seq_nv_tipo_vistoria",
					GeneroCondicaoWhere.igual, String.valueOf(this.seqTipoVistoriaSelecionado),
					TipoCondicaoWhere.Numero);
		}

		if (this.seqStatusSelecionado != null) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "nv_vistoria.SEQ_NV_VISTORIA_STATUS",
					GeneroCondicaoWhere.igual, String.valueOf(this.seqStatusSelecionado), TipoCondicaoWhere.Numero);
		}

		if (this.seqEmbarcacaoSelecionado != null) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "nv_vistoria.seq_nv_embarcacao",
					GeneroCondicaoWhere.igual, String.valueOf(this.seqEmbarcacaoSelecionado), TipoCondicaoWhere.Numero);
		}
                
                if (this.seqUnidadeNegocioSelecionada != null) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "nv_vistoria.seq_unidade_negocio",
					GeneroCondicaoWhere.igual, String.valueOf(this.seqUnidadeNegocioSelecionada), TipoCondicaoWhere.Numero);
		}

		if (this.seqColaboradorSelecionado != null) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "nv_vistoria.seq_colaborador",
					GeneroCondicaoWhere.igual, String.valueOf(this.seqColaboradorSelecionado),
					TipoCondicaoWhere.Numero);
		}

		if (this.UFSelecionada != null) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "nv_vistoria.estado_vistoria",
					GeneroCondicaoWhere.igual, this.UFSelecionada, TipoCondicaoWhere.Texto);
		}

		if (isPorData() == true) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "nv_vistoria.DATA_VISTORIA",
					GeneroCondicaoWhere.MaiorIgual, String.valueOf(this.dataInicial), TipoCondicaoWhere.Data);
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "nv_vistoria.DATA_VISTORIA",
					GeneroCondicaoWhere.MenorIgual, String.valueOf(this.dataFinal), TipoCondicaoWhere.Data);
		}

		if (isPorPrazo() == true) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "nv_vistoria.PRAZO", GeneroCondicaoWhere.MaiorIgual,
					String.valueOf(this.prazoInicial), TipoCondicaoWhere.Data);
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "nv_vistoria.PRAZO", GeneroCondicaoWhere.MenorIgual,
					String.valueOf(this.prazoFinal), TipoCondicaoWhere.Data);
		}

		this.listaNvVistoria = this.nvVistoriaService.listar(condicao);
	}

	public void upload() {
		String id = this.nvVistoria.getIdentificacao().replace("/", "-");
		this.upload.setSeqNvVistoria(this.nvVistoria.getSeqNvVistoria());
                this.upload.setSeqNvEmbarcacao(this.nvVistoria.getSeqNvEmbarcacao());
		this.upload.setOrigem("VISTORIA" + id + "-" + this.nvVistoria.getSeqNvVistoria());
		this.upload.setSeqEmpresa(this.loginController.empresa.getSeqEmpresa());
		this.upload.setSeqUsuario(this.loginController.usuario.getSeqUsuario());
		this.uploadController.upload(this.file, this.upload);
		this.listaUpload = this.uploadService.listar(this.loginController.empresa.getSeqEmpresa(),
				this.nvVistoria.getSeqNvVistoria());
		this.upload = new Upload();
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
				this.nvVistoria.getSeqNvVistoria());
	}

	public LoginController getLoginController() {
		return this.loginController;
	}

	public void setLoginController(LoginController loginController) {
		this.loginController = loginController;
	}

	public NvVistoriaService getNvVistoriaService() {
		return this.nvVistoriaService;
	}

	public void setNvVistoriaService(NvVistoriaService nvVistoriaService) {
		this.nvVistoriaService = nvVistoriaService;
	}

	public NvVistoria getNvVistoria() {
		return this.nvVistoria;
	}

	public void setNvVistoria(NvVistoria nvVistoria) {
		this.nvVistoria = nvVistoria;
	}

	public List<NvVistoria> getListaNvVistoria() {
		return this.listaNvVistoria;
	}

	public void setListaNvVistoria(List<NvVistoria> listaNvVistoria) {
		this.listaNvVistoria = listaNvVistoria;
	}

	public NvTipoVistoriaService getNvTipoVistoriaService() {
		return this.nvTipoVistoriaService;
	}

	public void setNvTipoVistoriaService(NvTipoVistoriaService nvTipoVistoriaService) {
		this.nvTipoVistoriaService = nvTipoVistoriaService;
	}

	public List<NvTipoVistoria> getListaNvTipoVistoria() {
		return this.listaNvTipoVistoria;
	}

	public void setListaNvTipoVistoria(List<NvTipoVistoria> listaNvTipoVistoria) {
		this.listaNvTipoVistoria = listaNvTipoVistoria;
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

	public UfService getUfService() {
		return this.ufService;
	}

	public void setUfService(UfService ufService) {
		this.ufService = ufService;
	}

	public List<Uf> getListaUf() {
		return this.listaUf;
	}

	public void setListaUf(List<Uf> listaUf) {
		this.listaUf = listaUf;
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

	public String[] getTipoVistoriaSelecionada() {
		return this.tipoVistoriaSelecionada;
	}

	public void setTipoVistoriaSelecionada(String[] tipoVistoriaSelecionada) {
		this.tipoVistoriaSelecionada = tipoVistoriaSelecionada;
	}

	public Long getSeqTipoVistoriaSelecionado() {
		return this.seqTipoVistoriaSelecionado;
	}

	public void setSeqTipoVistoriaSelecionado(Long seqTipoVistoriaSelecionado) {
		this.seqTipoVistoriaSelecionado = seqTipoVistoriaSelecionado;
	}

	public Long getSeqEmbarcacaoSelecionado() {
		return this.seqEmbarcacaoSelecionado;
	}

	public void setSeqEmbarcacaoSelecionado(Long seqEmbarcacaoSelecionado) {
		this.seqEmbarcacaoSelecionado = seqEmbarcacaoSelecionado;
	}

	public Long getSeqColaboradorSelecionado() {
		return this.seqColaboradorSelecionado;
	}

	public void setSeqColaboradorSelecionado(Long seqColaboradorSelecionado) {
		this.seqColaboradorSelecionado = seqColaboradorSelecionado;
	}

	public boolean isPorData() {
		return this.porData;
	}

	public void setPorData(boolean porData) {
		this.porData = porData;
	}

	public String getDataInicial() {
		return this.dataInicial;
	}

	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}

	public String getDataFinal() {
		return this.dataFinal;
	}

	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}

	public String getUFSelecionada() {
		return this.UFSelecionada;
	}

	public void setUFSelecionada(String UFSelecionada) {
		this.UFSelecionada = UFSelecionada;
	}

	public NvTpVistoriaTpCertificadoService getNvTpVistoriaTpCertificadoService() {
		return this.nvTpVistoriaTpCertificadoService;
	}

	public void setNvTpVistoriaTpCertificadoService(NvTpVistoriaTpCertificadoService nvTpVistoriaTpCertificadoService) {
		this.nvTpVistoriaTpCertificadoService = nvTpVistoriaTpCertificadoService;
	}

	public List<NvTpVistoriaTpCertificado> getListaNvTpVistoriaTpCertificado() {
		return this.listaNvTpVistoriaTpCertificado;
	}

	public void setListaNvTpVistoriaTpCertificado(List<NvTpVistoriaTpCertificado> listaNvTpVistoriaTpCertificado) {
		this.listaNvTpVistoriaTpCertificado = listaNvTpVistoriaTpCertificado;
	}

	public NvTpVistoriaTpCertificado getNvTpVistoriaTpCertificado() {
		return this.nvTpVistoriaTpCertificado;
	}

	public void setNvTpVistoriaTpCertificado(NvTpVistoriaTpCertificado nvTpVistoriaTpCertificado) {
		this.nvTpVistoriaTpCertificado = nvTpVistoriaTpCertificado;
	}

	public String getSeqVistoriaSelecionadaEmissaoCertificado() {
		return this.seqVistoriaSelecionadaEmissaoCertificado;
	}

	public void setSeqVistoriaSelecionadaEmissaoCertificado(String seqVistoriaSelecionadaEmissaoCertificado) {
		this.seqVistoriaSelecionadaEmissaoCertificado = seqVistoriaSelecionadaEmissaoCertificado;
	}

	public NvVistoriaStatusService getNvVistoriaStatusService() {
		return this.NvVistoriaStatusService;
	}

	public void setNvVistoriaStatusService(NvVistoriaStatusService NvVistoriaStatusService) {
		this.NvVistoriaStatusService = NvVistoriaStatusService;
	}

	public List<NvVistoriaStatus> getListaNvVistoriaStatus() {
		return this.listaNvVistoriaStatus;
	}

	public void setListaNvVistoriaStatus(List<NvVistoriaStatus> listaNvVistoriaStatus) {
		this.listaNvVistoriaStatus = listaNvVistoriaStatus;
	}

	public Long getSeqStatusSelecionado() {
		return this.seqStatusSelecionado;
	}

	public void setSeqStatusSelecionado(Long seqStatusSelecionado) {
		this.seqStatusSelecionado = seqStatusSelecionado;
	}

	public boolean isPorPrazo() {
		return this.porPrazo;
	}

	public void setPorPrazo(boolean porPrazo) {
		this.porPrazo = porPrazo;
	}

	public String getPrazoInicial() {
		return this.prazoInicial;
	}

	public void setPrazoInicial(String prazoInicial) {
		this.prazoInicial = prazoInicial;
	}

	public String getPrazoFinal() {
		return this.prazoFinal;
	}

	public void setPrazoFinal(String prazoFinal) {
		this.prazoFinal = prazoFinal;
	}

	public Util getUtil() {
		return this.util;
	}

	public void setUtil(Util util) {
		this.util = util;
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

	public NvTipoCertificadoService getNvTipoCertificadoService() {
		return this.nvTipoCertificadoService;
	}

	public void setNvTipoCertificadoService(NvTipoCertificadoService nvTipoCertificadoService) {
		this.nvTipoCertificadoService = nvTipoCertificadoService;
	}

	public List<NvTipoCertificado> getListaNvTipoCertificado() {
		return this.listaNvTipoCertificado;
	}

	public void setListaNvTipoCertificado(List<NvTipoCertificado> listaNvTipoCertificado) {
		this.listaNvTipoCertificado = listaNvTipoCertificado;
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

	public UploadedFile getFile() {
		return this.file;
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
        
        public List<UnidadeNegocio> getListaUnidadeNegocio() {
	return this.listaUnidadeNegocio;
	}

	public void setListaUnidadeNegocio(List<UnidadeNegocio> listaUnidadeNegocio) {
		this.listaUnidadeNegocio = listaUnidadeNegocio;
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
        
        public String getSeqUnidadeNegocioSelecionada() {
        return this.seqUnidadeNegocioSelecionada;
        }

        public void setSeqUnidadeNegocioSelecionada(String seqUnidadeNegocioSelecionada) {
        this.seqUnidadeNegocioSelecionada = seqUnidadeNegocioSelecionada;
        }
}

