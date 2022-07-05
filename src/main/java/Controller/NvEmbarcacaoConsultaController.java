package Controller;

import ClausulaSQL.ClausulaWhere;
import ClausulaSQL.GeneroCondicaoWhere;
import ClausulaSQL.OperacaoCondicaoWhere;
import ClausulaSQL.TipoCondicaoWhere;
import ClausulaSQL.GeneroCondicaoWhere;
import ClausulaSQL.OperacaoCondicaoWhere;
import ClausulaSQL.TipoCondicaoWhere;
import Empresa.Empresa;
import NvCertificadoCalculo.NvCertificadoCalculo;
import NvCertificadoCalculo.NvCertificadoCalculoService;
import NvCertificadoDetalhe.NvCertificadoDetalhe;
import NvCertificadoDetalhe.NvCertificadoDetalheService;
import NvCertificado.NvCertificado;
import NvCertificado.NvCertificadoService;
import NvVistoria.NvVistoria;
import NvVistoria.NvVistoriaService;
import Upload.Upload;
import Upload.UploadService;
import NvLicenca.NvLicenca;
import NvLicenca.NvLicencaService;
import NvEmbarcacao.NvEmbarcacao;
import NvEmbarcacao.NvEmbarcacaoService;
import NvEmbarcacaoDetalhe.NvEmbarcacaoDetalhe;
import NvEmbarcacaoDetalhe.NvEmbarcacaoDetalheService;
import NvEmbarcacaoParceiro.NvEmbarcacaoParceiro;
import NvEmbarcacaoParceiro.NvEmbarcacaoParceiroService;
import NvEmbarcacaoUsuario.NvEmbarcacaoUsuario;
import NvEmbarcacaoUsuario.NvEmbarcacaoUsuarioService;
import NvTipoEmbarcacao.NvTipoEmbarcacao;
import NvTipoEmbarcacao.NvTipoEmbarcacaoService;
import NvTipoVincParcEmbarca.NvTipoVincParcEmbarca;
import NvTipoVincParcEmbarca.NvTipoVincParcEmbarcaService;
import Parceiro.Parceiro;
import Parceiro.ParceiroService;
import ParceiroVinculo.ParceiroVinculo;
import ParceiroVinculo.ParceiroVinculoService;
import ParceiroVinculoEmbarcacao.ParceiroVinculoEmbarcacao;
import ParceiroVinculoEmbarcacao.ParceiroVinculoEmbarcacaoService;
import TipoParceiro.TipoParceiro;
import TipoParceiro.TipoParceiroService;
import TipoVinculo.TipoVinculo;
import TipoVinculo.TipoVinculoService;
import Usuario.Usuario;
import Usuario.UsuarioService;
import Util.Conexao;
import Util.Situacao;
import Util.Util;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
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

@ManagedBean(name = "nvEmbarcacaoConsultaController")
@ViewScoped
public class NvEmbarcacaoConsultaController {
	@ManagedProperty("#{loginController}")
	protected LoginController loginController;
	Parceiro parceiro = new Parceiro();
	List<Parceiro> listaParceiro = new ArrayList();

	NvEmbarcacaoService nvEmbarcacaoService = new NvEmbarcacaoService();
	NvEmbarcacao nvEmbarcacao = new NvEmbarcacao();
	List<NvEmbarcacao> listaNvEmbarcacao = new ArrayList();

	NvTipoEmbarcacaoService nvTipoEmbarcacaoService = new NvTipoEmbarcacaoService();
	List<NvTipoEmbarcacao> listaNvTipoEmbarcacao = new ArrayList();
	String pesquisa = "";
	Integer tela = Integer.valueOf(0);

	NvEmbarcacaoParceiroService nvEmbarcacaoParceiroService = new NvEmbarcacaoParceiroService();
	NvEmbarcacaoParceiro nvEmbarcacaoParceiro = new NvEmbarcacaoParceiro();
	List<NvEmbarcacaoParceiro> listaNvEmbarcacaoParceiro = new ArrayList();
        
	NvEmbarcacaoUsuarioService nvEmbarcacaoUsuarioService = new NvEmbarcacaoUsuarioService();
	NvEmbarcacaoUsuario nvEmbarcacaoUsuario = new NvEmbarcacaoUsuario();
	List<NvEmbarcacaoUsuario> listaNvEmbarcacaoUsuario = new ArrayList();        

	NvTipoVincParcEmbarcaService nvTipoVincParcEmbarcaService = new NvTipoVincParcEmbarcaService();
	List<NvTipoVincParcEmbarca> listaNvTipoVincParcEmbarca = new ArrayList();

	NvCertificadoCalculoService nvCertificadoCalculoService = new NvCertificadoCalculoService();
	List<NvCertificadoCalculo> listaNvCertificadoCalculo = new ArrayList();

	ParceiroVinculoService ParceiroVinculoService = new ParceiroVinculoService();
	List<ParceiroVinculo> listaParceiroVinculo = new ArrayList();
	ParceiroVinculo parceiroVinculo = new ParceiroVinculo();

	ParceiroVinculoEmbarcacaoService parceiroVinculoEmbarcacaoService = new ParceiroVinculoEmbarcacaoService();
	List<ParceiroVinculoEmbarcacao> listaParceiroVinculoEmbarcacao = new ArrayList();
	ParceiroVinculoEmbarcacao parceiroVinculoEmbarcacao = new ParceiroVinculoEmbarcacao();

	List<TipoVinculo> listaTipoVinculo = new ArrayList();
	List<TipoParceiro> listaTipoParceiro = new ArrayList();

	NvEmbarcacaoDetalheService nvEmbarcacaoDetalheService = new NvEmbarcacaoDetalheService();
	List<NvEmbarcacaoDetalhe> listaNvEmbarcacaoDetalhe = new ArrayList();
	NvEmbarcacaoDetalhe nvEmbarcacaoDetalhe = new NvEmbarcacaoDetalhe();

	List<Parceiro> listaParceiroVinculado = new ArrayList();
	String seqParceiroVinculado;
	ParceiroService parceiroService = new ParceiroService();
        
	List<NvEmbarcacaoUsuario> listaUsuarioVinculado = new ArrayList();
	String seqUsuarioVinculado;
	NvEmbarcacaoUsuarioService UsuarioVService = new NvEmbarcacaoUsuarioService();        
        
	List<Usuario> listaUsuario = new ArrayList();
	String seqUsuario;
	UsuarioService usuarioService = new UsuarioService();
        Usuario usuario = new Usuario();
        
        NvCertificado nvCertificado = new NvCertificado();
        NvCertificadoService nvCertificadoService = new NvCertificadoService();
        List<NvCertificado> listaNvCertificado = new ArrayList();
                
        NvCertificadoDetalheService nvCertificadoDetalheService = new NvCertificadoDetalheService();
        List<NvCertificadoDetalhe> listaNvCertificadoDetalhe = new ArrayList();
        NvCertificadoDetalhe nvCertificadoDetalhe = new NvCertificadoDetalhe();        
        
        NvVistoriaService nvVistoriaService = new NvVistoriaService();
        NvVistoria nvVistoria = new NvVistoria();
	List<NvVistoria> listaNvVistoria = new ArrayList();

        NvLicencaService nvLicencaService = new NvLicencaService();
        NvLicenca nvLicenca = new NvLicenca();
	List<NvLicenca> listaNvLicenca = new ArrayList();

	Upload upload = new Upload();
	UploadService uploadService = new UploadService();
	UploadController uploadController = new UploadController();
	List<Upload> listaUpload = new ArrayList();  
        
	UploadedFile file;

	StreamedContent fileDownload;        
               
        boolean porCamposTela;
               
        public boolean isPorCamposTela() {
		return this.porCamposTela;
	}
        
        public void setporCamposTela(boolean porCamposTela) {
		this.porCamposTela = porCamposTela;

        }
        
        Util util = new Util();
             
	public void iniciar() {
		if ((this.loginController.usuario.getAcOpEmbarcacao() == null)
				|| (this.loginController.usuario.getAcOpEmbarcacao().equals("-1"))) {
			this.loginController.mudarPagina("/organizacional/acessonegado.jsf");
			return;
		}
		/*this.listaNvTipoEmbarcacao = this.nvTipoEmbarcacaoService.listar("", Situacao.ATIVO);*/
		TipoVinculoService tipoVinculoService = new TipoVinculoService();
		this.listaTipoVinculo = tipoVinculoService.listar(this.loginController.getEmpresa().getSeqEmpresa(), "",
				Situacao.ATIVO);
		this.listaNvCertificadoCalculo = this.nvCertificadoCalculoService
				.listar(this.loginController.getEmpresa().getSeqEmpresa(), "", Situacao.TODOS);
		TipoParceiroService tipoParceiroService = new TipoParceiroService();
		this.listaTipoParceiro = tipoParceiroService.listar(this.loginController.getEmpresa().getSeqEmpresa(),
				Situacao.ATIVO);
		this.listaParceiroVinculado = this.parceiroService.listarParceiro(this.loginController.usuario.getSeqUsuario(),"");
                this.listaUsuario = this.usuarioService.listarTodosOsUsuarios(this.loginController.getEmpresa().getSeqEmpresa(),
				Situacao.ATIVO);        
                              
	}

	public void salvar(int pTela) {
		if (this.nvEmbarcacao.getSeqNvEmbarcacao().equals("-1")) {
			this.nvEmbarcacao.setSeqEmpresa(this.loginController.getEmpresa().getSeqEmpresa());
			this.nvEmbarcacao.setSeqNvEmbarcacao(null);
			this.nvEmbarcacao = this.nvEmbarcacaoService.salvar(this.nvEmbarcacao);
		} else {
                    this.nvEmbarcacao.setSeqUsuario(this.loginController.getUsuario().getSeqUsuario());
                    this.nvEmbarcacao = this.nvEmbarcacaoService.salvar(this.nvEmbarcacao);
		}
		for (NvEmbarcacaoDetalhe detalhe : this.listaNvEmbarcacaoDetalhe) {
			detalhe.setSeqNvEmbarcacao(this.nvEmbarcacao.getSeqNvEmbarcacao());
                        detalhe = this.nvEmbarcacaoDetalheService.salvar(detalhe);
			System.out.println("SEQ DETALHE" + detalhe.getSeqNvEmbarcacaoDetalhe());
		}

		System.out.println("Salvou");

		listar();
		this.listaNvEmbarcacaoDetalhe = this.nvEmbarcacaoDetalheService.listar(this.nvEmbarcacao.getSeqNvEmbarcacao());
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro armazenado com sucesso.", ""));
		this.tela = Integer.valueOf(pTela);
	}

	public void novo() {
		this.nvEmbarcacao = new NvEmbarcacao();
		this.nvEmbarcacao.setSeqNvEmbarcacao("-1");
		popularDetalhe();
		this.tela = Integer.valueOf(1);
	}

	public void listar() {
		this.listaNvEmbarcacao = this.nvEmbarcacaoService.listar(this.loginController.getEmpresa().getSeqEmpresa(),this.pesquisa, Situacao.TODOS);
		listarDetalhe();
                listarUsuarioVinculado();                       
	}
      
	public void listarPCliente() {
		this.listaNvEmbarcacao = this.nvEmbarcacaoService.listarPCliente(this.loginController.getUsuario().getSeqUsuario(),this.pesquisa, Situacao.TODOS);
		listarDetalhe();
                listarUsuarioVinculado();                       
	}        
       
	public void deletar() {
		this.nvEmbarcacaoDetalhe = ((NvEmbarcacaoDetalhe) this.nvEmbarcacaoDetalheService
				.listar(this.nvEmbarcacao.getSeqNvEmbarcacao()).get(0));

		this.nvEmbarcacaoDetalheService.deletar(this.nvEmbarcacaoDetalhe);
		if (this.nvEmbarcacaoService.deletar(this.nvEmbarcacao)) {
			listar();
			this.nvEmbarcacao = new NvEmbarcacao();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro eliminado com sucesso.", ""));
			this.tela = Integer.valueOf(0);
			listar();
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao excluir registro.", ""));
		}
	}

	public void adicionarVinculo() {
		this.nvEmbarcacaoParceiro.setSeqEmbarcacao(this.nvEmbarcacao.getSeqNvEmbarcacao());
		this.nvEmbarcacaoParceiroService.salvar(this.nvEmbarcacaoParceiro);

		this.listaNvEmbarcacaoParceiro = this.nvEmbarcacaoParceiroService
				.listarPorEmbarcacao(this.nvEmbarcacao.getSeqNvEmbarcacao());
		this.nvEmbarcacaoParceiro = new NvEmbarcacaoParceiro();
	}

	public void removerVinculo(NvEmbarcacaoParceiro pParceiroVinculo) {
		this.nvEmbarcacaoParceiroService.deletar(pParceiroVinculo);
		this.listaNvEmbarcacaoParceiro = this.nvEmbarcacaoParceiroService
				.listarPorEmbarcacao(this.nvEmbarcacao.getSeqNvEmbarcacao());
	}
        
	public void adicionarVinculoUsuario() {
		this.nvEmbarcacaoUsuario.setSeqEmbarcacao(this.nvEmbarcacao.getSeqNvEmbarcacao());
		this.nvEmbarcacaoUsuarioService.salvar(this.nvEmbarcacaoUsuario);

		this.listaNvEmbarcacaoUsuario = this.nvEmbarcacaoUsuarioService
				.listarPorEmbarcacao(this.nvEmbarcacao.getSeqNvEmbarcacao());
		this.nvEmbarcacaoUsuario = new NvEmbarcacaoUsuario();
	}

	public void removerVinculoUsuario(NvEmbarcacaoUsuario pUsuarioVinculo) {
		this.nvEmbarcacaoUsuarioService.deletar(pUsuarioVinculo);
		this.listaNvEmbarcacaoUsuario = this.nvEmbarcacaoUsuarioService
				.listarPorEmbarcacao(this.nvEmbarcacao.getSeqNvEmbarcacao());
	}        

	public void selecionarVinculo(NvEmbarcacaoParceiro pNvEmbarcacaoParceiro) {
		this.nvEmbarcacaoParceiro = pNvEmbarcacaoParceiro;
		this.loginController.buscarParceiroAutoComplete(pNvEmbarcacaoParceiro.getSeqParceiro());
	}

	public void novoVinculo() {
		this.nvEmbarcacaoParceiro = new NvEmbarcacaoParceiro();
	}

	public void deletarVinculo() {
		this.nvEmbarcacaoParceiroService.deletar(this.nvEmbarcacaoParceiro);
		listarVinculo();
	}

	public void salvarVinculo() {
		this.nvEmbarcacaoParceiro.setSeqEmbarcacao(this.nvEmbarcacao.getSeqNvEmbarcacao());
		this.nvEmbarcacaoParceiroService.salvar(this.nvEmbarcacaoParceiro);
		listarVinculo();
	}

	public void listarVinculo() {
		this.listaNvEmbarcacaoParceiro = this.nvEmbarcacaoParceiroService
				.listarPorEmbarcacao(this.nvEmbarcacao.getSeqNvEmbarcacao());
	}

	public void salvarDetalhe(int pTela) {
		if (this.nvEmbarcacao.getSeqNvEmbarcacao().equals("-1")) {
			System.out.println(this.nvEmbarcacao.getSeqNvEmbarcacao());
			salvar(1);
		}
		this.nvEmbarcacaoDetalhe.setSeqNvEmbarcacao(this.nvEmbarcacao.getSeqNvEmbarcacao());
		this.nvEmbarcacaoDetalhe = this.nvEmbarcacaoDetalheService.salvar(this.nvEmbarcacaoDetalhe);
	}

	public void novoDetalhe() {
		this.nvEmbarcacaoDetalhe = new NvEmbarcacaoDetalhe();
		this.tela = Integer.valueOf(1);
	}

	public void listarDetalhe() {
		this.listaNvEmbarcacaoDetalhe = this.nvEmbarcacaoDetalheService.listar(this.nvEmbarcacao.getSeqNvEmbarcacao());
	}     
        
        public void listarNvCertificado() {
                this.listaNvCertificado = this.nvEmbarcacaoService.listarC(this.nvEmbarcacao.getSeqNvEmbarcacao());
                       		
	}
        
        
        public void listarVistoria() {
                this.listaNvVistoria = this.nvEmbarcacaoService.listarV(this.nvEmbarcacao.getSeqNvEmbarcacao());
                      		
	} 
        
        public void listarLicenca() {
                this.listaNvLicenca = this.nvEmbarcacaoService.listarL(this.nvEmbarcacao.getSeqNvEmbarcacao());
                       		
	}

        public void listarUpload()  {
                this.listaUpload = this.nvEmbarcacaoService.listarU(this.loginController.empresa.getSeqEmpresa(),
				this.nvEmbarcacao.getSeqNvEmbarcacao());
		
        }      
        public void listarCertificadoDetalhe() {
                this.listaNvCertificadoDetalhe = this.nvEmbarcacaoService.listarD(this.nvEmbarcacao.getSeqNvEmbarcacao());
                       		
	}
	public void listarUsuarioVinculado() {
		this.listaNvEmbarcacaoUsuario = this.nvEmbarcacaoUsuarioService.listarPorEmbarcacao(this.nvEmbarcacao.getSeqNvEmbarcacao());
	}        
        public void popularDetalhe() {
		String[] detalhe1 = { "", "Passageiros sentados", "Passageiros em camarote", "Passageiros em redes",
				"Passageiros em pé", "Porão de carga 01 (carga geral)",
				"Paiol no casco (mantimentos e materiais diversos)", "Almoxarifado no convés principal",
				"Depósito no convés principal", "Depósito no convés superior" };

		String[] detalhe2 = { "Convés Principal", "", "", "", "", "", "", "", "", "" };
		String[] detalhe3 = { "Convés Superior", "", "", "", "", "", "", "", "", "", "" };
		String[] detalhe4 = { "Área de lazer", "", "", "", "", "", "", "", "", "", "" };

		this.listaNvEmbarcacaoDetalhe = new ArrayList();
		NvEmbarcacaoDetalhe lnvEmbarcacaoDetalhe;
		Integer temp;
		for (Integer i = Integer.valueOf(0); i.intValue() < 10; temp = i = Integer.valueOf(i.intValue() + 1)) {
			lnvEmbarcacaoDetalhe = new NvEmbarcacaoDetalhe();
			lnvEmbarcacaoDetalhe.setDetalhe1(detalhe1[i.intValue()]);
			lnvEmbarcacaoDetalhe.setDetalhe2(detalhe2[i.intValue()]);
			lnvEmbarcacaoDetalhe.setDetalhe3(detalhe3[i.intValue()]);
			lnvEmbarcacaoDetalhe.setDetalhe4(detalhe4[i.intValue()]);
			temp = Integer.valueOf(i.intValue() + 1);
			if (temp.intValue() < 10) {
				lnvEmbarcacaoDetalhe.setOrdem("0" + temp.toString());
			} else {
				lnvEmbarcacaoDetalhe.setOrdem(temp.toString());
			}
			System.out.println(lnvEmbarcacaoDetalhe.getOrdem());
			this.listaNvEmbarcacaoDetalhe.add(lnvEmbarcacaoDetalhe);
		}

		System.out.println("LISTA COMPLETA: " + this.listaNvEmbarcacaoDetalhe);
	}

	public void deletarDetalhe() {
		if (this.nvEmbarcacaoDetalheService.deletar(this.nvEmbarcacaoDetalhe)) {
			listarDetalhe();
			this.nvEmbarcacaoDetalhe = new NvEmbarcacaoDetalhe();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro eliminado com sucesso.", ""));
			this.tela = Integer.valueOf(0);
			listarDetalhe();
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao excluir registro.", ""));
		}
	}

	public void selecionarDetalhe(NvEmbarcacaoDetalhe pNvEmbarcacaoDetalhe) {
		this.nvEmbarcacaoDetalhe = pNvEmbarcacaoDetalhe;
	}

	public void fecharTela() throws IOException {
		this.loginController.mudarPagina("/principal/ principal.jsf");
	}

	public void selecionar(NvEmbarcacao pNvEmbarcacao) {
		this.nvEmbarcacao = pNvEmbarcacao;
		this.listaParceiroVinculoEmbarcacao = this.parceiroVinculoEmbarcacaoService.listar(
				this.loginController.usuario.getSeqEmpresa(), this.nvEmbarcacao.getSeqNvEmbarcacao(), Situacao.ATIVO);
		listarDetalhe();
		if (this.listaNvEmbarcacaoDetalhe.size() < 10) {
			popularDetalhe();
                     
                        
		}
		listarVinculo();
                listarUpload ();
                listarNvCertificado();
                
                this.tela = Integer.valueOf(1);
	}
         
        	public void selecionar2(NvEmbarcacao pNvEmbarcacao) {
		this.nvEmbarcacao = pNvEmbarcacao;
		this.listaParceiroVinculoEmbarcacao = this.parceiroVinculoEmbarcacaoService.listar(
				this.loginController.usuario.getSeqEmpresa(), this.nvEmbarcacao.getSeqNvEmbarcacao(), Situacao.ATIVO);
		listarDetalhe();
		if (this.listaNvEmbarcacaoDetalhe.size() < 10) {
			popularDetalhe();
                     
                        
		}
		listarVinculo();
                listarNvCertificado();
                listarVistoria();
                listarLicenca();
                listarUpload ();
                listarCertificadoDetalhe ();
                
                
                this.tela = Integer.valueOf(1);
	
                }
/*impressão do certificado na tela de consulta

public void imprimir(NvCertificado pNvCertificado) throws IOException, JRException {
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
			}
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
			}
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
			}      
                        
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
						this.nvCertificado.getDataCadastro()));
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
	}*/
       
	public void mudarTela(Integer pTela) {
		this.tela = pTela;
	}

	public void selecionar(NvCertificado cNvCertificado) {
		this.nvCertificado = cNvCertificado;
		this.nvVistoria = this.nvVistoriaService
				.buscar(this.nvCertificado.getSeqNvVistoria());
		System.out.println(this.nvCertificado.getTipoCertificado());
		
		this.nvCertificado.setPrazo((short) 0);
		listarDetalhe();
		this.tela = Integer.valueOf(1);
	}
        
        public void upload() {
		this.upload.setSeqNvEmbarcacao(this.nvEmbarcacao.getSeqNvEmbarcacao());
		this.upload.setOrigem("CADASTRO MANUAL");
		this.upload.setSeqEmpresa(this.loginController.empresa.getSeqEmpresa());
		this.upload.setSeqUsuario(this.loginController.usuario.getSeqUsuario());
		this.uploadController.upload(this.file, this.upload);
                this.listaUpload = this.uploadService.listar(this.loginController.empresa.getSeqEmpresa(),
				this.nvEmbarcacao.getSeqNvEmbarcacao());
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
				this.nvEmbarcacao.getSeqNvEmbarcacao());
	}
        
        public LoginController getLoginController() {
		return this.loginController;
	}

	public void setLoginController(LoginController loginController) {
		this.loginController = loginController;
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

	public List<NvEmbarcacao> getListaNvEmbarcacao() {
		return this.listaNvEmbarcacao;
	}

	public void setListaNvEmbarcacao(List<NvEmbarcacao> listaNvEmbarcacao) {
		this.listaNvEmbarcacao = listaNvEmbarcacao;
	}

	public NvTipoEmbarcacaoService getNvTipoEmbarcacaoService() {
		return this.nvTipoEmbarcacaoService;
	}

	public void setNvTipoEmbarcacaoService(NvTipoEmbarcacaoService nvTipoEmbarcacaoService) {
		this.nvTipoEmbarcacaoService = nvTipoEmbarcacaoService;
	}

	public List<NvTipoEmbarcacao> getListaNvTipoEmbarcacao() {
		return this.listaNvTipoEmbarcacao;
	}

	public void setListaNvTipoEmbarcacao(List<NvTipoEmbarcacao> listaNvTipoEmbarcacao) {
		this.listaNvTipoEmbarcacao = listaNvTipoEmbarcacao;
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

	public NvEmbarcacaoParceiroService getNvEmbarcacaoParceiroService() {
		return this.nvEmbarcacaoParceiroService;
	}

	public void setNvEmbarcacaoParceiroService(NvEmbarcacaoParceiroService nvEmbarcacaoParceiroService) {
		this.nvEmbarcacaoParceiroService = nvEmbarcacaoParceiroService;
	}

	public NvEmbarcacaoParceiro getNvEmbarcacaoParceiro() {
		return this.nvEmbarcacaoParceiro;
	}

	public void setNvEmbarcacaoParceiro(NvEmbarcacaoParceiro nvEmbarcacaoParceiro) {
		this.nvEmbarcacaoParceiro = nvEmbarcacaoParceiro;
	}

	public List<NvEmbarcacaoParceiro> getListaNvEmbarcacaoParceiro() {
		return this.listaNvEmbarcacaoParceiro;
	}

	public void setListaNvEmbarcacaoParceiro(List<NvEmbarcacaoParceiro> listaNvEmbarcacaoParceiro) {
		this.listaNvEmbarcacaoParceiro = listaNvEmbarcacaoParceiro;
	}

	public NvTipoVincParcEmbarcaService getNvTipoVincParcEmbarcaService() {
		return this.nvTipoVincParcEmbarcaService;
	}

	public void setNvTipoVincParcEmbarcaService(NvTipoVincParcEmbarcaService nvTipoVincParcEmbarcaService) {
		this.nvTipoVincParcEmbarcaService = nvTipoVincParcEmbarcaService;
	}

	public List<NvTipoVincParcEmbarca> getListaNvTipoVincParcEmbarca() {
		return this.listaNvTipoVincParcEmbarca;
	}

	public void setListaNvTipoVincParcEmbarca(List<NvTipoVincParcEmbarca> listaNvTipoVincParcEmbarca) {
		this.listaNvTipoVincParcEmbarca = listaNvTipoVincParcEmbarca;
	}

	public NvCertificadoCalculoService getNvCertificadoCalculoService() {
		return this.nvCertificadoCalculoService;
	}

	public void setNvCertificadoCalculoService(NvCertificadoCalculoService nvCertificadoCalculoService) {
		this.nvCertificadoCalculoService = nvCertificadoCalculoService;
	}

	public List<NvCertificadoCalculo> getListaNvCertificadoCalculo() {
		return this.listaNvCertificadoCalculo;
	}

	public void setListaNvCertificadoCalculo(List<NvCertificadoCalculo> listaNvCertificadoCalculo) {
		this.listaNvCertificadoCalculo = listaNvCertificadoCalculo;
	}

	public List<Parceiro> getListaParceiro() {
		return this.listaParceiro;
	}

	public void setListaParceiro(List<Parceiro> listaParceiro) {
		this.listaParceiro = listaParceiro;
	}

	public NvEmbarcacaoDetalheService getNvEmbarcacaoDetalheService() {
		return this.nvEmbarcacaoDetalheService;
	}

	public void setNvEmbarcacaoDetalheService(NvEmbarcacaoDetalheService nvEmbarcacaoDetalheService) {
		this.nvEmbarcacaoDetalheService = nvEmbarcacaoDetalheService;
	}

	public List<NvEmbarcacaoDetalhe> getListaNvEmbarcacaoDetalhe() {
		return this.listaNvEmbarcacaoDetalhe;
	}

	public void setListaNvEmbarcacaoDetalhe(List<NvEmbarcacaoDetalhe> listaNvEmbarcacaoDetalhe) {
		this.listaNvEmbarcacaoDetalhe = listaNvEmbarcacaoDetalhe;
	}

	public NvEmbarcacaoDetalhe getNvEmbarcacaoDetalhe() {
		return this.nvEmbarcacaoDetalhe;
	}

	public void setNvEmbarcacaoDetalhe(NvEmbarcacaoDetalhe nvEmbarcacaoDetalhe) {
		this.nvEmbarcacaoDetalhe = nvEmbarcacaoDetalhe;
	}

	public List<TipoVinculo> getListaTipoVinculo() {
		return this.listaTipoVinculo;
	}

	public void setListaTipoVinculo(List<TipoVinculo> listaTipoVinculo) {
		this.listaTipoVinculo = listaTipoVinculo;
	}

	public Parceiro getParceiro() {
		return this.parceiro;
	}

	public void setParceiro(Parceiro parceiro) {
		this.parceiro = parceiro;
	}

	public ParceiroVinculoEmbarcacaoService getParceiroVinculoEmbarcacaoService() {
		return this.parceiroVinculoEmbarcacaoService;
	}

	public void setParceiroVinculoEmbarcacaoService(ParceiroVinculoEmbarcacaoService parceiroVinculoEmbarcacaoService) {
		this.parceiroVinculoEmbarcacaoService = parceiroVinculoEmbarcacaoService;
	}

	public List<ParceiroVinculoEmbarcacao> getListaParceiroVinculoEmbarcacao() {
		return this.listaParceiroVinculoEmbarcacao;
	}

	public void setListaParceiroVinculoEmbarcacao(List<ParceiroVinculoEmbarcacao> listaParceiroVinculoEmbarcacao) {
		this.listaParceiroVinculoEmbarcacao = listaParceiroVinculoEmbarcacao;
	}

	public ParceiroVinculoEmbarcacao getParceiroVinculoEmbarcacao() {
		return this.parceiroVinculoEmbarcacao;
	}

	public void setParceiroVinculoEmbarcacao(ParceiroVinculoEmbarcacao parceiroVinculoEmbarcacao) {
		this.parceiroVinculoEmbarcacao = parceiroVinculoEmbarcacao;
	}

	public List<Parceiro> getListaParceiroVinculado() {
		return this.listaParceiroVinculado;
	}

	public void setListaParceiroVinculado(List<Parceiro> listaParceiroVinculado) {
		this.listaParceiroVinculado = listaParceiroVinculado;
	}

	public ParceiroService getParceiroService() {
		return this.parceiroService;
	}

	public void setParceiroService(ParceiroService parceiroService) {
		this.parceiroService = parceiroService;
	}

	public List<TipoParceiro> getListaTipoParceiro() {
		return this.listaTipoParceiro;
	}

	public void setListaTipoParceiro(List<TipoParceiro> listaTipoParceiro) {
		this.listaTipoParceiro = listaTipoParceiro;
	}

	public ParceiroVinculoService getParceiroVinculoService() {
		return this.ParceiroVinculoService;
	}

	public void setParceiroVinculoService(ParceiroVinculoService ParceiroVinculoService) {
		this.ParceiroVinculoService = ParceiroVinculoService;
	}

	public List<ParceiroVinculo> getListaParceiroVinculo() {
		return this.listaParceiroVinculo;
	}

	public void setListaParceiroVinculo(List<ParceiroVinculo> listaParceiroVinculo) {
		this.listaParceiroVinculo = listaParceiroVinculo;
	}

	public ParceiroVinculo getParceiroVinculo() {
		return this.parceiroVinculo;
	}

	public void setParceiroVinculo(ParceiroVinculo parceiroVinculo) {
		this.parceiroVinculo = parceiroVinculo;
	}

	public String getSeqParceiroVinculado() {
		return this.seqParceiroVinculado;
	}

	public void setSeqParceiroVinculado(String seqParceiroVinculado) {
		this.seqParceiroVinculado = seqParceiroVinculado;
	}
        
        public List<NvCertificado> getListaNvCertificado() {
		return this.listaNvCertificado;
	}

	public void setListaNvCertificado(List<NvCertificado> listaNvCertificado) {
		this.listaNvCertificado = listaNvCertificado;
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
        
               public List<NvCertificadoDetalhe> getListaNvCertificadoDetalhe() {
		return this.listaNvCertificadoDetalhe;
	}

	public void setListaNvCertificadoDetalhe(List<NvCertificadoDetalhe> listaNvCertificadoDetalhe) {
		this.listaNvCertificadoDetalhe = listaNvCertificadoDetalhe;
	}
        
        public NvCertificadoDetalheService getNvCertificadoDetalheService() {
		return this.nvCertificadoDetalheService;
	}

	public void setNvCertificadoDetalheService(NvCertificadoDetalheService nvCertificadoDetalheService) {
		this.nvCertificadoDetalheService = nvCertificadoDetalheService;
	}
        
          public NvCertificadoDetalhe getNvCertificadoDetalhe() {
		return this.nvCertificadoDetalhe;
	}

	public void setNvCertificadoDetalhe(NvCertificadoDetalhe nvCertificadoDetalhe) {
		this.nvCertificadoDetalhe = nvCertificadoDetalhe;
	} 
        
        public List<NvVistoria> getListaNvVistoria() {
		return this.listaNvVistoria;
	}

	public void setListaNvVistoria(List<NvVistoria> listaNvVistoria) {
		this.listaNvVistoria = listaNvVistoria;
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
        
        public List<NvLicenca> getListaNvLicenca() {
		return this.listaNvLicenca;
	}

	public void setListaNvLicenca(List<NvLicenca> listaNvLicenca) {
		this.listaNvLicenca = listaNvLicenca;
	}
        
        public NvLicencaService getNvLicencaService() {
		return this.nvLicencaService;
	}

	public void setNvLicencaService(NvLicencaService nvLicencaService) {
		this.nvLicencaService = nvLicencaService;
	}
        
          public NvLicenca getNvLicenca() {
		return this.nvLicenca;
	}

	public void setNvLicenca(NvLicenca nvLicenca) {
		this.nvLicenca = nvLicenca;
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
	public NvEmbarcacaoUsuarioService getNvEmbarcacaoUsuarioService() {
		return this.nvEmbarcacaoUsuarioService;
	}

	public void setNvEmbarcacaoUsuarioService(NvEmbarcacaoUsuarioService nvEmbarcacaoUsuarioService) {
		this.nvEmbarcacaoUsuarioService = nvEmbarcacaoUsuarioService;
	}

	public NvEmbarcacaoUsuario getNvEmbarcacaoUsuario() {
		return this.nvEmbarcacaoUsuario;
	}

	public void setNvEmbarcacaoUsuario(NvEmbarcacaoUsuario nvEmbarcacaoUsuario) {
		this.nvEmbarcacaoUsuario = nvEmbarcacaoUsuario;
	}

	public List<NvEmbarcacaoUsuario> getListaNvEmbarcacaoUsuario() {
		return this.listaNvEmbarcacaoUsuario;
	}

	public void setListaNvEmbarcacaoUsuario(List<NvEmbarcacaoUsuario> listaNvEmbarcacaoUsuario) {
		this.listaNvEmbarcacaoUsuario = listaNvEmbarcacaoUsuario;
	}        
	public List<Usuario> getListaUsuario() {
		return this.listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
        }        
        
        
}
