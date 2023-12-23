package Controller;

import Comissao.Comissao;
import Comissao.ComissaoService;
import Empresa.Empresa;
import Usuario.Usuario;
import Usuario.UsuarioService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;








@ManagedBean(name="comissaoController")
@ViewScoped
public class ComissaoController
{
  @ManagedProperty("#{loginController}")
  protected LoginController loginController;
  ComissaoService comissaoService = new ComissaoService();
  Comissao comissao = new Comissao();
  List<Comissao> listaComissao = new ArrayList();
  String pesquisa = "";
  Integer tela = Integer.valueOf(0);
  
  Usuario usuario = new Usuario();
  List<Usuario> listaUsuario = new ArrayList();
  UsuarioService usuarioService = new UsuarioService();
  
  public void iniciar() {
    this.listaUsuario = this.usuarioService.listarUsuarioQueRecebeComissao(this.loginController.getEmpresa().getSeqEmpresa());
  }
  
  public void onRowEdit(RowEditEvent event)
  {
    this.comissaoService.salvar((Comissao)event.getObject());
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro armazenado com sucesso.", ""));
  }
  
  public void salvar(int pTela) {
    this.comissao.setSeqEmpresa(this.loginController.getEmpresa().getSeqEmpresa());
    this.comissao = this.comissaoService.salvar(this.comissao);
    String id = this.comissao.getSeqUsuario();
    this.comissao = new Comissao();
    this.comissao.setSeqUsuario(id);
    listar();
    
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro armazenado com sucesso.", ""));
    this.tela = Integer.valueOf(pTela);
  }
  
  public void novo() {
    this.comissao = new Comissao();
    this.tela = Integer.valueOf(1);
  }
  
  public void listar() {
    this.listaComissao = this.comissaoService.listarPorUsuario(this.comissao.getSeqUsuario());
  }
  
  public void deletar(Comissao pComissao) {
    if (this.comissaoService.deletar(pComissao)) {
      listar();
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro eliminado com sucesso.", ""));
      this.tela = Integer.valueOf(0);
      listar();
    } else {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao excluir registro.", ""));
    }
  }
  
  public void fecharTela() throws IOException {
    this.loginController.mudarPagina("/principal/ principal.jsf");
  }
  
  public void selecionar(Comissao pComissao) {
    this.comissao = pComissao;
    this.tela = Integer.valueOf(1);
  }
  
  public void mudarTela(Integer pTela) {
    this.tela = pTela;
  }
  

  public LoginController getLoginController()
  {
    return this.loginController;
  }
  
  public void setLoginController(LoginController loginController) {
    this.loginController = loginController;
  }
  
  public ComissaoService getComissaoService() {
    return this.comissaoService;
  }
  
  public void setComissaoService(ComissaoService comissaoService) {
    this.comissaoService = comissaoService;
  }
  
  public Comissao getComissao() {
    return this.comissao;
  }
  
  public void setComissao(Comissao comissao) {
    this.comissao = comissao;
  }
  
  public List<Comissao> getListaComissao() {
    return this.listaComissao;
  }
  
  public void setListaComissao(List<Comissao> listaComissao) {
    this.listaComissao = listaComissao;
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
  
  public Usuario getUsuario() {
    return this.usuario;
  }
  
  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }
  
  public List<Usuario> getListaUsuario() {
    return this.listaUsuario;
  }
  
  public void setListaUsuario(List<Usuario> listaUsuario) {
    this.listaUsuario = listaUsuario;
  }
  
  public UsuarioService getUsuarioService() {
    return this.usuarioService;
  }
  
  public void setUsuarioService(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }
}


/* Location:              C:\Users\diogo\Documents\workspace\others\prod erp\deploy\erp3.war!\WEB-INF\classes\Controller\ComissaoController.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */