package Controller;

import CentralRelacionamento.CentralRelacionamento;
import CentralRelacionamento.CentralRelacionamentoService;
import Usuario.Usuario;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;







@ManagedBean(name="centralRelacionamentoController")
@ViewScoped
public class CentralRelacionamentoController
{
  @ManagedProperty("#{loginController}")
  protected LoginController loginController;
  CentralRelacionamentoService centralRelacionamentoService = new CentralRelacionamentoService();
  List<CentralRelacionamento> minhaCarteira = new ArrayList();
  List<CentralRelacionamento> minhaRede = new ArrayList();
  String pesquisa;
  Integer tela = Integer.valueOf(0);
  
  public void iniciar() {
    this.minhaCarteira = this.centralRelacionamentoService.listarCarteiraPorUsuario(this.loginController.getUsuario().getSeqUsuario());
    this.minhaRede = this.centralRelacionamentoService.listarCarteiraPorNivel(this.loginController.getUsuario().getNivel());
  }
  
  public void selecionar(CentralRelacionamento pCentralRelacionamento) {
    System.out.println("Selecionado: " + pCentralRelacionamento);
  }
  
  public LoginController getLoginController()
  {
    return this.loginController;
  }
  
  public void setLoginController(LoginController loginController) {
    this.loginController = loginController;
  }
  
  public CentralRelacionamentoService getCentralRelacionamentoService() {
    return this.centralRelacionamentoService;
  }
  
  public void setCentralRelacionamentoService(CentralRelacionamentoService centralRelacionamentoService) {
    this.centralRelacionamentoService = centralRelacionamentoService;
  }
  
  public List<CentralRelacionamento> getMinhaCarteira() {
    return this.minhaCarteira;
  }
  
  public void setMinhaCarteira(List<CentralRelacionamento> minhaCarteira) {
    this.minhaCarteira = minhaCarteira;
  }
  
  public List<CentralRelacionamento> getMinhaRede() {
    return this.minhaRede;
  }
  
  public void setMinhaRede(List<CentralRelacionamento> minhaRede) {
    this.minhaRede = minhaRede;
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
