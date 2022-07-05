package Controller;

import Empresa.Empresa;
import RelVisita.RelVisita;
import RelVisita.RelVisitaService;
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
import Parceiro.Parceiro;
import Parceiro.ParceiroService;


@ManagedBean(name="relVisitaController")
@ViewScoped
public class RelatorioVisitaController
{
  @ManagedProperty("#{loginController}")
  protected LoginController loginController;
  RelVisitaService relVisitaService = new RelVisitaService();
  RelVisita relVisita = new RelVisita();
  List<RelVisita> listaRelVisita = new ArrayList();
  String pesquisa = "";
  String nomeParceiro;
  ParceiroService parceiroService = new ParceiroService();
  Parceiro parceiro = new Parceiro();
  List<Parceiro> listaParceiro = new ArrayList();
  Long seqParceiroSelecionado;
  Integer tela = Integer.valueOf(0);
  
  
  public void iniciar()
  {
    if ((this.loginController.usuario.getAcOpStatusVistoria() == null) || (this.loginController.usuario.getAcOpStatusVistoria().equals("-1"))) {
      this.loginController.mudarPagina("/organizacional/acessonegado.jsf");
      return;
    }
  this.listaParceiro = parceiroService.listarParceiro(this.loginController.getUsuario().getSeqUsuario(), "");  
  }
  

  public void salvar(int pTela)
  {
    this.relVisita.setSeqEmpresa(this.loginController.getEmpresa().getSeqEmpresa());
    this.relVisita.setSeqUsuario(this.loginController.getUsuario().getSeqUsuario());
    this.relVisita.setCliente(this.nomeParceiro);
    this.relVisita = this.relVisitaService.salvar(this.relVisita);
    listar();
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro armazenado com sucesso.", ""));
    this.tela = Integer.valueOf(pTela);
  }
  
  public void novo() {
    this.relVisita = new RelVisita();
    this.tela = Integer.valueOf(1);
  }
  
  public void listar() {
    this.listaRelVisita = this.relVisitaService.listar(this.loginController.getEmpresa().getSeqEmpresa(), this.pesquisa);
  }
  
  public void deletar() {
    if (this.relVisitaService.deletar(this.relVisita)) {
      listar();
      this.relVisita = new RelVisita();
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
  
  public void selecionar(RelVisita pRelVisita) {
    this.listaRelVisita = this.relVisitaService.listar(this.loginController.getEmpresa().getSeqEmpresa(), "");
      this.relVisita = pRelVisita;
    
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
  
  public RelVisitaService getRelVisitaService() {
    return this.relVisitaService;
  }
  
  public void setRelVisitaService(RelVisitaService relVisitaService) {
    this.relVisitaService = relVisitaService;
  }
  
  public RelVisita getRelVisita() {
    return this.relVisita;
  }
  
  public void setRelVisita(RelVisita relVisita) {
    this.relVisita = relVisita;
  }
  
  public List<RelVisita> getListaRelVisita() {
    return this.listaRelVisita;
  }
  
  public void setListaRelVisita(List<RelVisita> listaRelVisita) {
    this.listaRelVisita = listaRelVisita;
  }
  
  public String getPesquisa() {
    return this.pesquisa;
  }
  
  public void setPesquisa(String pesquisa) {
    this.pesquisa = pesquisa;
  }
  
  public String getNomeParceiro() {
    return this.nomeParceiro;
  }
  
  public void setNomeParceiro(String nomeParceiro) {
    this.nomeParceiro = nomeParceiro;
  }
  
  public Integer getTela() {
    return this.tela;
  }
  
  public void setTela(Integer tela) {
    this.tela = tela;
  }
  
  public List<Parceiro> getListaParceiro() {
    return this.listaParceiro;
  }
  
  public void setListaParceiro(List<Parceiro> listaParceiro) {
    this.listaParceiro = listaParceiro;
  }
  
      public Long getSeqParceiroSelecionado() {
        return this.seqParceiroSelecionado;
    }

    public void setSeqParceiroSelecionado(Long seqParceiroSelecionado) {
        this.seqParceiroSelecionado = seqParceiroSelecionado;
    }
}
