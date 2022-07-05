package Controller;

import Empresa.Empresa;
import GrupoEmpresarial.GrupoEmpresarial;
import GrupoEmpresarial.GrupoEmpresarialService;
import Usuario.Usuario;
import Util.Situacao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="grupoEmpresarialController")
@ViewScoped
public class GrupoEmpresarialController
{
  @ManagedProperty("#{loginController}")
  protected LoginController loginController;
  GrupoEmpresarialService grupoEmpresarialService = new GrupoEmpresarialService();
  GrupoEmpresarial grupoEmpresarial = new GrupoEmpresarial();
  List<GrupoEmpresarial> listaGrupoEmpresarial = new ArrayList();
  String pesquisa;
  Integer tela = Integer.valueOf(0);
  
  public void salvar(int pTela) {
    this.grupoEmpresarial.setDataCadastro(new Date());
    this.grupoEmpresarial.setSeqEmpresa(this.loginController.getEmpresa().getSeqEmpresa());
    this.grupoEmpresarial = this.grupoEmpresarialService.salvar(this.grupoEmpresarial);
    listar();
      
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro armazenado com sucesso.", ""));
    this.tela = Integer.valueOf(pTela);
  }
  
  public void novo() {
    this.grupoEmpresarial = new GrupoEmpresarial();
    this.tela = Integer.valueOf(1);
  }
  
  public void listar() {
    this.listaGrupoEmpresarial = this.grupoEmpresarialService.listar(this.loginController.getEmpresa().getSeqEmpresa(), this.pesquisa, Situacao.TODOS);
  }
  
  public void deletar() {
    if (this.grupoEmpresarialService.deletar(this.grupoEmpresarial)) {
      listar();
      this.grupoEmpresarial = new GrupoEmpresarial();
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
  
  public void iniciar()
  {
    if ((this.loginController.usuario.getAcCadTvinculo() == null) || (this.loginController.usuario.getAcCadTvinculo().equals("-1"))) {
      this.loginController.mudarPagina("/organizacional/acessonegado.jsf");
      return;
    }
  }
  
  public void selecionar(GrupoEmpresarial pGrupoEmpresarial) {
    this.grupoEmpresarial = pGrupoEmpresarial;
    this.tela = Integer.valueOf(1);
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
  
  public GrupoEmpresarialService getGrupoEmpresarialService() {
    return this.grupoEmpresarialService;
  }
  
  public void setGrupoEmpresarialService(GrupoEmpresarialService grupoEmpresarialService) {
    this.grupoEmpresarialService = grupoEmpresarialService;
  }
  
  public GrupoEmpresarial getGrupoEmpresarial() {
    return this.grupoEmpresarial;
  }
  
  public void setGrupoEmpresarial(GrupoEmpresarial grupoEmpresarial) {
    this.grupoEmpresarial = grupoEmpresarial;
  }
  
  public List<GrupoEmpresarial> getListaGrupoEmpresarial() {
    return this.listaGrupoEmpresarial;
  }
  
  public void setListaGrupoEmpresarial(List<GrupoEmpresarial> listaGrupoEmpresarial) {
    this.listaGrupoEmpresarial = listaGrupoEmpresarial;
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
}