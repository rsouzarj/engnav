package Controller;

import Departamento.Departamento;
import Departamento.DepartamentoService;
import Empresa.Empresa;
import Parceiro.Parceiro;
import Parceiro.ParceiroService;
import TipoDocumento.TipoDocumento;
import TipoDocumento.TipoDocumentoService;
import UnidadeNegocio.UnidadeNegocio;
import UnidadeNegocio.UnidadeNegocioService;
import Usuario.Usuario;
import Usuario.UsuarioService;
import UsuarioTipoDocumento.UsuarioTipoDocumento;
import UsuarioTipoDocumento.UsuarioTipoDocumentoService;
import Util.Situacao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;









@ManagedBean(name="usuarioController")
@ViewScoped
public class UsuarioController
{
  @ManagedProperty("#{loginController}")
  protected LoginController loginController;
  UsuarioService usuarioService = new UsuarioService();
  Usuario usuario = new Usuario();
  List<Usuario> listaUsuario = new ArrayList();
  

  
  List<TipoDocumento> listaTipoDocumento = new ArrayList();
  TipoDocumentoService tipoDocumentoService = new TipoDocumentoService();
  
  UsuarioTipoDocumentoService usuarioTipoDocumentoService = new UsuarioTipoDocumentoService();
  List<UsuarioTipoDocumento> listaUsuarioTipoDocumento = new ArrayList();
  UsuarioTipoDocumento usuarioTipoDocumento = new UsuarioTipoDocumento();
  
  String pesquisa;
  Integer tela = Integer.valueOf(0);
  
  String SenhaAntiga;
  String senhaNova;
  String senhaConfirm;
  UnidadeNegocioService unidadeNegocioService = new UnidadeNegocioService();
  List<UnidadeNegocio> listaUnidadeNegocio = new ArrayList();
  
  DepartamentoService departamentoService = new DepartamentoService();
  List<Departamento> listaDepartamento = new ArrayList();
  
  ParceiroService parceiroService = new ParceiroService();
  List<Parceiro> listaParceiro = new ArrayList();
  
  public void iniciar()
  {
    if ((this.loginController.usuario.getAcOrgUsuario() == null) || (this.loginController.usuario.getAcOrgUsuario().equals("-1"))) {
      this.loginController.mudarPagina("/organizacional/acessonegado.jsf");
      return;
    }
    
    this.listaUnidadeNegocio = this.unidadeNegocioService.listar(this.loginController.getEmpresa().getSeqEmpresa(), "", Situacao.ATIVO);
    this.listaDepartamento = this.departamentoService.listar(this.loginController.getEmpresa().getSeqEmpresa(), "", Situacao.ATIVO);
    this.listaTipoDocumento = this.tipoDocumentoService.listarTodos(this.loginController.getEmpresa().getSeqEmpresa(), "");
  }
  
  public void alterarSenha() {
    if ((this.loginController.getUsuario().getSenha().equals(this.SenhaAntiga)) && (this.senhaNova.equals(this.senhaConfirm))) {
      this.loginController.getUsuario().setSenha(this.senhaNova);
      this.usuarioService.salvarUsuario(this.loginController.getUsuario());
      this.loginController.sair();
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Senha alterada com sucesso!", ""));
    } else {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Senha antiga ou confirmação de senha invalida!", ""));
    }
  }
  
  public void redefinirSenha() {
    this.usuario.setSenha("123");
    this.usuarioService.salvarUsuario(this.usuario);
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Senha redefinida com sucesso!", ""));
  }
  
  public void salvarUsuario(int pTela) {
    this.usuario.setSeqEmpresa(this.loginController.getEmpresa().getSeqEmpresa());
    this.usuario.setCor("-black");
    this.usuario = this.usuarioService.salvarUsuario(this.usuario);
    
    if (this.usuario.getSeqUsuario().equals(this.loginController.getUsuario().getSeqUsuario())) {
      this.loginController.setUsuario(this.usuario);
    }
    
    listarUsuario();
    
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro armazenado com sucesso.", ""));
    this.tela = Integer.valueOf(pTela);
  }
  
  public void novaUsuario() {
    this.usuario = new Usuario();
    this.listaUsuarioTipoDocumento.clear();
    this.tela = Integer.valueOf(1);
  }
  
  public void listarUsuario() {
    this.listaUsuario = this.usuarioService.listarUsuarioPorNome(this.loginController.getEmpresa().getSeqEmpresa(), this.pesquisa);
  }
  
  public void deletarUsuario() {
    if (this.usuarioService.deletarUsuario(this.usuario)) {
      listarUsuario();
      this.usuario = new Usuario();
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro eliminado com sucesso.", ""));
      this.tela = Integer.valueOf(0);
      listarUsuario();
    } else {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao excluir registro.", ""));
    }
  }
  
  public void selecionar(Usuario pUsuario) {
    this.usuario = pUsuario;
    listarTipoDocumento();
    this.tela = Integer.valueOf(1);
  }
  
  public void listarTipoDocumento()
  {
    this.listaUsuarioTipoDocumento = this.usuarioTipoDocumentoService.listar(this.usuario.getSeqUsuario());
  }
  
  public void salvarTipoDocumento() {
    if (this.usuario.getSeqUsuario() == null) {
      salvarUsuario(1);
    }
    this.usuarioTipoDocumento.setSeqUsuario(this.usuario.getSeqUsuario());
    this.usuarioTipoDocumentoService.salvar(this.usuarioTipoDocumento);
    this.usuarioTipoDocumento = new UsuarioTipoDocumento();
    listarTipoDocumento();
  }
  
  public void deletarTipoDocumento(UsuarioTipoDocumento tp) {
    this.usuarioTipoDocumentoService.deletar(tp);
    listarTipoDocumento();
  }
  
  public void fecharTela() throws IOException {
    this.loginController.mudarPagina("/principal/principal.jsf");
  }
  
  public void mudarTela(Integer pTela) {
    this.tela = pTela;
  }
  
  public Integer getTela()
  {
    return this.tela;
  }
  
  public String getSenhaNova() {
    return this.senhaNova;
  }
  
  public void setSenhaNova(String senhaNova) {
    this.senhaNova = senhaNova;
  }
  
  public String getSenhaAntiga() {
    return this.SenhaAntiga;
  }
  
  public void setSenhaAntiga(String SenhaAntiga) {
    this.SenhaAntiga = SenhaAntiga;
  }
  
  public String getSenhaConfirm() {
    return this.senhaConfirm;
  }
  
  public void setSenhaConfirm(String senhaConfirm) {
    this.senhaConfirm = senhaConfirm;
  }
  
  public void setTela(Integer tela) {
    this.tela = tela;
  }
  
  public LoginController getLoginController() {
    return this.loginController;
  }
  
  public void setLoginController(LoginController loginController) {
    this.loginController = loginController;
  }
  
  public List<Usuario> getListaUsuario() {
    return this.listaUsuario;
  }
  
  public void setListaUsuario(List<Usuario> listaUsuario) {
    this.listaUsuario = listaUsuario;
  }
  
  public String getPesquisa() {
    return this.pesquisa;
  }
  
  public void setPesquisa(String pesquisa) {
    this.pesquisa = pesquisa;
  }
  
  public UsuarioService getUsuarioService() {
    return this.usuarioService;
  }
  
  public void setUsuarioService(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }
  
  public Usuario getUsuario() {
    return this.usuario;
  }
  
  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
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
  
  public DepartamentoService getDepartamentoService() {
    return this.departamentoService;
  }
  
  public void setDepartamentoService(DepartamentoService departamentoService) {
    this.departamentoService = departamentoService;
  }
  
  public List<Departamento> getListaDepartamento() {
    return this.listaDepartamento;
  }
  
  public void setListaDepartamento(List<Departamento> listaDepartamento) {
    this.listaDepartamento = listaDepartamento;
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
  
  public UsuarioTipoDocumentoService getUsuarioTipoDocumentoService() {
    return this.usuarioTipoDocumentoService;
  }
  
  public void setUsuarioTipoDocumentoService(UsuarioTipoDocumentoService usuarioTipoDocumentoService) {
    this.usuarioTipoDocumentoService = usuarioTipoDocumentoService;
  }
  
  public List<UsuarioTipoDocumento> getListaUsuarioTipoDocumento() {
    return this.listaUsuarioTipoDocumento;
  }
  
  public void setListaUsuarioTipoDocumento(List<UsuarioTipoDocumento> listaUsuarioTipoDocumento) {
    this.listaUsuarioTipoDocumento = listaUsuarioTipoDocumento;
  }
  
  public UsuarioTipoDocumento getUsuarioTipoDocumento() {
    return this.usuarioTipoDocumento;
  }
  
  public void setUsuarioTipoDocumento(UsuarioTipoDocumento usuarioTipoDocumento) {
    this.usuarioTipoDocumento = usuarioTipoDocumento;
  }
}
