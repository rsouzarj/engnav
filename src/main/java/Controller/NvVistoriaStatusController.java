package Controller;

import Empresa.Empresa;
import NvVistoriaStatus.NvVistoriaStatus;
import NvVistoriaStatus.NvVistoriaStatusService;
import Usuario.Usuario;
import Util.Situacao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="nvVistoriaStatusController")
@ViewScoped
public class NvVistoriaStatusController
{
  @ManagedProperty("#{loginController}")
  protected LoginController loginController;
  NvVistoriaStatusService nvVistoriaStatusService = new NvVistoriaStatusService();
  NvVistoriaStatus nvVistoriaStatus = new NvVistoriaStatus();
  List<NvVistoriaStatus> listaNvVistoriaStatus = new ArrayList();
  String pesquisa = "";
  Integer tela = Integer.valueOf(0);
  
  public void iniciar()
  {
    if ((this.loginController.usuario.getAcOpStatusVistoria() == null) || (this.loginController.usuario.getAcOpStatusVistoria().equals("-1"))) {
      this.loginController.mudarPagina("/organizacional/acessonegado.jsf");
      return;
    }
  }
  

  public void salvar(int pTela)
  {
    this.nvVistoriaStatus.setSeqEmpresa(this.loginController.getEmpresa().getSeqEmpresa());
    this.nvVistoriaStatus = this.nvVistoriaStatusService.salvar(this.nvVistoriaStatus);
    listar();
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro armazenado com sucesso.", ""));
    this.tela = Integer.valueOf(pTela);
  }
  
  public void novo() {
    this.nvVistoriaStatus = new NvVistoriaStatus();
    this.tela = Integer.valueOf(1);
  }
  
  public void listar() {
    this.listaNvVistoriaStatus = this.nvVistoriaStatusService.listar(this.loginController.getEmpresa().getSeqEmpresa(), this.pesquisa, Situacao.TODOS);
  }
  
  public void deletar() {
    if (this.nvVistoriaStatusService.deletar(this.nvVistoriaStatus)) {
      listar();
      this.nvVistoriaStatus = new NvVistoriaStatus();
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
  
  public void selecionar(NvVistoriaStatus pNvVistoriaStatus) {
    this.nvVistoriaStatus = pNvVistoriaStatus;
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
  
  public NvVistoriaStatusService getNvVistoriaStatusService() {
    return this.nvVistoriaStatusService;
  }
  
  public void setNvVistoriaStatusService(NvVistoriaStatusService nvVistoriaStatusService) {
    this.nvVistoriaStatusService = nvVistoriaStatusService;
  }
  
  public NvVistoriaStatus getNvVistoriaStatus() {
    return this.nvVistoriaStatus;
  }
  
  public void setNvVistoriaStatus(NvVistoriaStatus nvVistoriaStatus) {
    this.nvVistoriaStatus = nvVistoriaStatus;
  }
  
  public List<NvVistoriaStatus> getListaNvVistoriaStatus() {
    return this.listaNvVistoriaStatus;
  }
  
  public void setListaNvVistoriaStatus(List<NvVistoriaStatus> listaNvVistoriaStatus) {
    this.listaNvVistoriaStatus = listaNvVistoriaStatus;
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
