package Controller;


import ClausulaSQL.ClausulaWhere;
import ClausulaSQL.GeneroCondicaoWhere;
import ClausulaSQL.OperacaoCondicaoWhere;
import ClausulaSQL.TipoCondicaoWhere;
import ClausulaSQL.GeneroCondicaoWhere;
import ClausulaSQL.OperacaoCondicaoWhere;
import ClausulaSQL.TipoCondicaoWhere;
import Empresa.Empresa;
import NvEmbarcacao.NvEmbarcacao;
import NvEmbarcacao.NvEmbarcacaoService;
import NvEmbarcacaoUsuario.NvEmbarcacaoUsuario;
import NvEmbarcacaoUsuario.NvEmbarcacaoUsuarioService;
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


@ManagedBean(name = "nvEmbarcacaoUsuarioController")
@ViewScoped
public class NvEmbarcacaoUsuarioController {
	@ManagedProperty("#{loginController}")
	protected LoginController loginController;
	
	NvEmbarcacaoService nvEmbarcacaoService = new NvEmbarcacaoService();
	NvEmbarcacao nvEmbarcacao = new NvEmbarcacao();
	List<NvEmbarcacao> listaNvEmbarcacao = new ArrayList();

	
	String pesquisa = "";
	Integer tela = Integer.valueOf(0);

   
	NvEmbarcacaoUsuarioService nvEmbarcacaoUsuarioService = new NvEmbarcacaoUsuarioService();
	NvEmbarcacaoUsuario nvEmbarcacaoUsuario = new NvEmbarcacaoUsuario();
	List<NvEmbarcacaoUsuario> listaNvEmbarcacaoUsuario = new ArrayList();        

  
	List<NvEmbarcacaoUsuario> listaUsuarioVinculado = new ArrayList();
	String seqUsuarioVinculado;
	NvEmbarcacaoUsuarioService UsuarioVService = new NvEmbarcacaoUsuarioService();        
        
	List<Usuario> listaUsuario = new ArrayList();
	String seqUsuario;
	UsuarioService usuarioService = new UsuarioService();
        Usuario usuario = new Usuario();
        
        boolean porCamposTela;
                
        
        
        Util util = new Util();
                
       
	public void iniciar() {
		if ((this.loginController.usuario.getAcOpEmbarcacao() == null)
				|| (this.loginController.usuario.getAcOpEmbarcacao().equals("-1"))) {
			this.loginController.mudarPagina("/organizacional/acessonegado.jsf");
			return;
		}
                this.listaUsuario = this.usuarioService.listarTodosOsUsuarios(this.loginController.getEmpresa().getSeqEmpresa(),
				Situacao.ATIVO);
                
               
                              
	}

	public void salvar(int pTela) {
		if (this.nvEmbarcacao.getSeqNvEmbarcacao().equals("-1")) {
			this.nvEmbarcacao.setSeqEmpresa(this.loginController.getEmpresa().getSeqEmpresa());
			this.nvEmbarcacao.setSeqNvEmbarcacao(null);
			this.nvEmbarcacao = this.nvEmbarcacaoService.salvar(this.nvEmbarcacao);
		} else {
			this.nvEmbarcacao = this.nvEmbarcacaoService.salvar(this.nvEmbarcacao);
		}
		System.out.println("Salvou");

		listar();
		FacesContext.getCurrentInstance().addMessage(null,
		new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro armazenado com sucesso.", ""));
		this.tela = Integer.valueOf(pTela);
	}

	public void novo() {
		this.nvEmbarcacao = new NvEmbarcacao();
		this.nvEmbarcacao.setSeqNvEmbarcacao("-1");
		this.tela = Integer.valueOf(1);
	}

	public void listar() {
		this.listaNvEmbarcacao = this.nvEmbarcacaoService.listar(this.loginController.getEmpresa().getSeqEmpresa(),this.pesquisa, Situacao.TODOS);
		listarUsuarioVinculado();
                
                
                                
	}

	public void selecionar(NvEmbarcacao pNvEmbarcacao) {
		this.nvEmbarcacao = pNvEmbarcacao;
		this.listaNvEmbarcacaoUsuario = this.nvEmbarcacaoUsuarioService.listarPorEmbarcacao(this.nvEmbarcacao.getSeqNvEmbarcacao());
	              
                this.tela = Integer.valueOf(1);
	}        

        
       
	public void deletar() {
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

        public void adicionarVinculoUsuario() {
		this.nvEmbarcacaoUsuario.setSeqEmbarcacao(this.nvEmbarcacao.getSeqNvEmbarcacao());
		this.nvEmbarcacaoUsuarioService.salvar(this.nvEmbarcacaoUsuario);

		this.listaNvEmbarcacaoUsuario = this.nvEmbarcacaoUsuarioService
				.listarPorEmbarcacao(this.nvEmbarcacao.getSeqNvEmbarcacao());
		this.nvEmbarcacaoUsuario = new NvEmbarcacaoUsuario();
	}

	public void removerVinculo(NvEmbarcacaoUsuario pUsuarioVinculo) {
		this.nvEmbarcacaoUsuarioService.deletar(pUsuarioVinculo);
		this.listaNvEmbarcacaoUsuario = this.nvEmbarcacaoUsuarioService
				.listarPorEmbarcacao(this.nvEmbarcacao.getSeqNvEmbarcacao());
	}        
    
        
        public void listarUsuarioVinculado() {
		this.listaNvEmbarcacaoUsuario = this.nvEmbarcacaoUsuarioService.listarPorEmbarcacao(this.nvEmbarcacao.getSeqNvEmbarcacao());
	}        

	public void fecharTela() throws IOException {
		this.loginController.mudarPagina("/principal/ principal.jsf");
	}
       


       
	public void mudarTela(Integer pTela) {
		this.tela = pTela;
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
