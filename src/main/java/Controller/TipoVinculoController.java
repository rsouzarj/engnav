package Controller;

import Empresa.Empresa;
import TipoVinculo.TipoVinculo;
import TipoVinculo.TipoVinculoService;
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

@ManagedBean(name="tipoVinculoController")
@ViewScoped
public class TipoVinculoController
{
  @ManagedProperty("#{loginController}")
  protected LoginController loginController;
  TipoVinculoService tipoVinculoService = new TipoVinculoService();
  TipoVinculo tipoVinculo = new TipoVinculo();
  List<TipoVinculo> listaTipoVinculo = new ArrayList();
  String pesquisa;
  Integer tela = Integer.valueOf(0);
  
  public void salvar(int pTela) {
    this.tipoVinculo.setDataCadastro(new Date());
    this.tipoVinculo.setSeqEmpresa(this.loginController.getEmpresa().getSeqEmpresa());
    this.tipoVinculo = this.tipoVinculoService.salvar(this.tipoVinculo);
    
    int idx = 0;
    idx = this.listaTipoVinculo.indexOf(this.tipoVinculo);
    if (idx == -1) {
      this.listaTipoVinculo.add(this.tipoVinculo);
    } else if (this.listaTipoVinculo.size() > 0) {
      this.listaTipoVinculo.remove(this.tipoVinculo);
      this.listaTipoVinculo.add(idx, this.tipoVinculo);
    } else {
      this.listaTipoVinculo.add(idx, this.tipoVinculo);
    }
    
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro armazenado com sucesso.", ""));
    this.tela = Integer.valueOf(pTela);
  }
  
  public void novo() {
    this.tipoVinculo = new TipoVinculo();
    this.tela = Integer.valueOf(1);
  }
  
  public void listar() {
    this.listaTipoVinculo = this.tipoVinculoService.listar(this.loginController.getEmpresa().getSeqEmpresa(), this.pesquisa, Situacao.TODOS);
  }
  
  public void deletar() {
    if (this.tipoVinculoService.deletar(this.tipoVinculo)) {
      listar();
      this.tipoVinculo = new TipoVinculo();
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
  
  public void selecionar(TipoVinculo pTipoVinculo) {
    this.tipoVinculo = pTipoVinculo;
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
  
  public TipoVinculoService getTipoVinculoService() {
    return this.tipoVinculoService;
  }
  
  public void setTipoVinculoService(TipoVinculoService tipoVinculoService) {
    this.tipoVinculoService = tipoVinculoService;
  }
  
  public TipoVinculo getTipoVinculo() {
    return this.tipoVinculo;
  }
  
  public void setTipoVinculo(TipoVinculo tipoVinculo) {
    this.tipoVinculo = tipoVinculo;
  }
  
  public List<TipoVinculo> getListaTipoVinculo() {
    return this.listaTipoVinculo;
  }
  
  public void setListaTipoVinculo(List<TipoVinculo> listaTipoVinculo) {
    this.listaTipoVinculo = listaTipoVinculo;
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