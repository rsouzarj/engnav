package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import Comissao.Comissao;
import Comissao.ComissaoService;
import Empresa.Empresa;
import Empresa.EmpresaService;
import Parceiro.Parceiro;
import Parceiro.ParceiroService;
import TipoDocumento.TipoDocumento;
import TipoDocumento.TipoDocumentoService;
import TipoVinculo.TipoVinculo;
import TipoVinculo.TipoVinculoService;
import Usuario.Usuario;
import Usuario.UsuarioService;
import Util.Modulo;
import Util.PastaDisco;
import Util.Situacao;
import Util.Util;
import Agenda.Agenda;
import Agenda.AgendaService;

@SessionScoped
@ManagedBean(name = "loginController")
public class LoginController implements HttpSessionListener {
	Util util = new Util();

	public Usuario usuario = new Usuario();
	UsuarioService usuarioService = new UsuarioService();

	Empresa empresa = new Empresa();
	EmpresaService empresaService = new EmpresaService();

	List<TipoDocumento> listaTipoDocumento = new ArrayList();
	TipoDocumento tipoDocumento = new TipoDocumento();
        TipoDocumentoService tipoDocumentoService = new TipoDocumentoService();
	HashMap<String, String> listaTipoDocumentoContador = new HashMap();

	List<TipoVinculo> listaTipoVinculo = new ArrayList();
	TipoVinculoService tipoVinculoService = new TipoVinculoService();

	ParceiroService parceiroService = new ParceiroService();
	Parceiro parceiroV = new Parceiro();
	List<Parceiro> listaParceiroVinculado = new ArrayList();

	ComissaoService comissaoService = new ComissaoService();
	List<Comissao> listaComissao = new ArrayList();
        
      	AgendaService agendaService = new AgendaService();
	Agenda agenda = new Agenda();
	List<Agenda> listaAgenda = new ArrayList();  
        
             

	private HttpSession session;
	String vLogin = null;
	String vSenha = null;
	String vEmail = null;
	String color = "";
	String chave = "";
	String vModulo = null;
	Modulo modulo = null;
	String home;
	private StreamedContent logo;


	public void iniciarParametro() {
		if ((this.usuario.getAcOrgParametro() == null) || (this.usuario.getAcOrgParametro().equals("-1"))) {
			mudarPagina("/organizacional/acessonegado.jsf");
			return;
		}
	}

	public void iniciarADM() {
		this.listaTipoDocumento = this.tipoDocumentoService.montarMenu(this.empresa.getSeqEmpresa(),
				this.usuario.getSeqUsuario());

		this.listaTipoDocumentoContador = this.tipoDocumentoService.listarContador(this.empresa.getSeqEmpresa(),
				this.usuario.getSeqUsuario());
                
		this.listaTipoDocumentoContador = this.tipoDocumentoService.listarContador(this.empresa.getSeqEmpresa(),
				this.usuario.getSeqUsuario());                
	}

	public void iniciarComercial() {
		this.listaComissao = this.comissaoService.listarLiberadoPorUsuario(this.usuario.getSeqUsuario());
	}

	public void carregarTabAuxParametro() {
		this.listaTipoVinculo = this.tipoVinculoService.listar(this.usuario.getSeqEmpresa(), "", Situacao.ATIVO);
	}

	public void enviarSenhaEmail() {
		if (this.usuarioService.enviarEmailSenha(this.vEmail) == true) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Em alguns instantes você receberá um email com sua senha.", ""));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email não encontrado no sistema.", ""));
		}
	}

	public void salvarParametro() {
		this.empresa = this.empresaService.salvar(this.empresa);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Informações armazenadas com sucesso!", ""));
	}

	public void upload(FileUploadEvent event) {
		try {
			this.util.copiarArquivoDisco(this.empresa.getSeqEmpresa(), PastaDisco.Logo, "logo.png",
					event.getFile().getInputstream());
		} catch (IOException e) {
			e.printStackTrace();
		}

		carregarlogo();

		mudarPagina("/organizacional/parametro.jsf");
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

	public void carregarlogo() {
		try {
			FileInputStream in = new FileInputStream(
					new File("C:\\temp\\erp\\" + this.empresa.getSeqEmpresa() + "\\Logo\\logo.png"));

			System.out.println("C:\\temp\\erp\\" + this.empresa.getSeqEmpresa() + "\\Logo\\logo.png");

			this.logo = new DefaultStreamedContent(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void logar() throws IOException {
		FacesContext ctx = FacesContext.getCurrentInstance();
		this.vModulo = "ADM";
		if (this.vModulo.equals("FV")) {
			this.modulo = Modulo.Comercial;
			this.home = (ctx.getExternalContext().getRequestContextPath() + "/principal/comercial.jsf");
		} else if (this.vModulo.equals("ADM")) {
			this.modulo = Modulo.Administrativo;
			this.home = (ctx.getExternalContext().getRequestContextPath() + "/principal/principal.jsf");
		}

		this.usuario = this.usuarioService.validarAcesso(this.vLogin, this.vSenha, this.chave, this.modulo);
		System.out.println(this.chave);

		if (this.usuario != null) {
			this.empresa = this.empresaService.buscarEmpresaPorChave(this.chave);
			this.session = ((HttpSession) ctx.getExternalContext().getSession(false));
			this.session.setAttribute("currentUser", this.usuario);
			this.session.setAttribute("context", ctx.getExternalContext().getRequestContextPath());

			this.color = this.usuario.getCor();
			carregarlogo();
			FacesContext.getCurrentInstance().getExternalContext().redirect(this.home);
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario ou senha invalido!.", ""));
		}
	}

	public void sair() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession s = (HttpSession) fc.getExternalContext().getSession(false);
		s.invalidate();
		FacesContext ctx = FacesContext.getCurrentInstance();
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(ctx.getExternalContext().getRequestContextPath() + "/");
		} catch (IOException ex) {
			Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void mudarCor(String color) {
		// if (color.equals("green")) {
		// this.color = null;
		// } else {
		// this.color = ("-" + color);
		// }
		this.usuario.setCor(this.color);
		this.usuarioService.salvarUsuario(this.usuario);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(this.home);
		} catch (IOException ex) {
			Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public List<Parceiro> listarParceiroAutoComplete(String query) {
		this.listaParceiroVinculado = this.parceiroService.listarParceiro(this.usuario.getSeqUsuario(), "");

		List<Parceiro> parceirosFiltrados = new ArrayList();

		for (int i = 0; i < this.listaParceiroVinculado.size(); i++) {
			Parceiro parceiroV = (Parceiro) this.listaParceiroVinculado.get(i);
			if (parceiroV.getNome().toLowerCase().startsWith(query)) {
				parceirosFiltrados.add(parceiroV);
			}
		}

		return parceirosFiltrados;
	}

	public void parceiroSelecionada() {
		System.out.println("Parceiro: " + this.parceiroV.getNome());
	}

	public void buscarParceiroAutoComplete(String pSeqParceiro) {
	}

	public HttpSession getSession() {
		return this.session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public String getvEmail() {
		return this.vEmail;
	}

	public void setvEmail(String vEmail) {
		this.vEmail = vEmail;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioService getUsuarioService() {
		return this.usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public String getvLogin() {
		return this.vLogin;
	}

	public void setvLogin(String vLogin) {
		this.vLogin = vLogin;
	}

	public String getvSenha() {
		return this.vSenha;
	}

	public void setvSenha(String vSenha) {
		this.vSenha = vSenha;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void sessionCreated(HttpSessionEvent se) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public String getChave() {
		return this.chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public EmpresaService getEmpresaService() {
		return this.empresaService;
	}

	public void setEmpresaService(EmpresaService empresaService) {
		this.empresaService = empresaService;
	}

	public List<TipoDocumento> getListaTipoDocumento() {
		return this.listaTipoDocumento;
	}

	public void setListaTipoDocumento(List<TipoDocumento> listaTipoDocumento) {
		this.listaTipoDocumento = listaTipoDocumento;
	}
        
	public TipoDocumento getTipoDocumento() {
		return this.tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}        

	public TipoDocumentoService getTipoDocumentoService() {
		return this.tipoDocumentoService;
	}

	public void setTipoDocumentoService(TipoDocumentoService tipoDocumentoService) {
		this.tipoDocumentoService = tipoDocumentoService;
	}

	public HashMap<String, String> getListaTipoDocumentoContador() {
		return this.listaTipoDocumentoContador;
	}

	public void setListaTipoDocumentoContador(HashMap<String, String> listaTipoDocumentoContador) {
		this.listaTipoDocumentoContador = listaTipoDocumentoContador;
	}

	public List<TipoVinculo> getListaTipoVinculo() {
		return this.listaTipoVinculo;
	}

	public void setListaTipoVinculo(List<TipoVinculo> listaTipoVinculo) {
		this.listaTipoVinculo = listaTipoVinculo;
	}

	public TipoVinculoService getTipoVinculoService() {
		return this.tipoVinculoService;
	}

	public void setTipoVinculoService(TipoVinculoService tipoVinculoService) {
		this.tipoVinculoService = tipoVinculoService;
	}

	public Parceiro getParceiroV() {
		return this.parceiroV;
	}

	public void setParceiroV(Parceiro parceiroV) {
		this.parceiroV = parceiroV;
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

	public String getvModulo() {
		return this.vModulo;
	}

	public void setvModulo(String vModulo) {
		this.vModulo = vModulo;
	}

	public Modulo getModulo() {
		return this.modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	public String getHome() {
		return this.home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public ComissaoService getComissaoService() {
		return this.comissaoService;
	}

	public void setComissaoService(ComissaoService comissaoService) {
		this.comissaoService = comissaoService;
	}

	public List<Comissao> getListaComissao() {
		return this.listaComissao;
	}

	public void setListaComissao(List<Comissao> listaComissao) {
		this.listaComissao = listaComissao;
	}

	public Util getUtil() {
		return this.util;
	}

	public void setUtil(Util util) {
		this.util = util;
	}

	public StreamedContent getLogo() {
		return this.logo;
	}

	public void setLogo(StreamedContent logo) {
		this.logo = logo;
	}
}

