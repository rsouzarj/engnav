package Controller;

import ClausulaSQL.ClausulaWhere;
import ClausulaSQL.GeneroCondicaoWhere;
import ClausulaSQL.OperacaoCondicaoWhere;
import ClausulaSQL.TipoCondicaoWhere;
import Empresa.Empresa;
import Equipamento.Equipamento;
import Equipamento.EquipamentoService;
import EquipamentoParceiro.EquipamentoParceiro;
import EquipamentoParceiro.EquipamentoParceiroService;
import Parceiro.Parceiro;
import Parceiro.ParceiroService;
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

@ManagedBean(name="equipamentoController")
@ViewScoped
public class EquipamentoController
{
  @ManagedProperty("#{loginController}")
  protected LoginController loginController;
  EquipamentoService equipamentoService = new EquipamentoService();
  Equipamento equipamento = new Equipamento();
  List<Equipamento> listaEquipamento = new ArrayList();
  List<Equipamento> listaEquipamentoIni = new ArrayList();
  
  EquipamentoParceiroService equipamentoParceiroService = new EquipamentoParceiroService();
  EquipamentoParceiro equipamentoParceiro = new EquipamentoParceiro();
  List<EquipamentoParceiro> listaEquipamentoParceiro = new ArrayList();
  
  
  ParceiroService parceiroService = new ParceiroService();
  Parceiro parceiro = new Parceiro();
  Long seqEquipamentoSelecionado;
  Long seqParceiroSelecionado;
  
  List<Parceiro> listaParceiro = new ArrayList();
  String pesquisa = "";
  Integer tela = Integer.valueOf(0);
  
  public void iniciar()
  {
    if ((this.loginController.usuario.getAcOpEquipamento() == null) || (this.loginController.usuario.getAcOpEquipamento().equals("-1"))) {
      this.loginController.mudarPagina("/organizacional/acessonegado.jsf");
      return;
    }
    
  
    this.listaParceiro = parceiroService.listarParceiro(this.loginController.getUsuario().getSeqUsuario(), "");
    this.listaEquipamentoIni = this.equipamentoService.listar(this.loginController.getEmpresa().getSeqEmpresa(), "", Situacao.ATIVO);
    this.listaEquipamentoParceiro = this.equipamentoParceiroService.listarParceiro(this.equipamento.getSeqEquipamento());
  }
  
  public void salvar(int pTela) {
    this.equipamento.setSeqEmpresa(this.loginController.getEmpresa().getSeqEmpresa());
    this.equipamento.setSituacao("ATIVO");
    this.equipamento = this.equipamentoService.salvar(this.equipamento);
    listar();
    
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro armazenado com sucesso.", ""));
    this.tela = Integer.valueOf(pTela);
  }
  
    public void salvarFin(int pTela) {
    this.equipamento.setSeqEmpresa(this.loginController.getEmpresa().getSeqEmpresa());
    this.equipamento.setSituacao("ATIVO");
    this.equipamento = this.equipamentoService.salvar(this.equipamento);
    listarFin();
    
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro armazenado com sucesso.", ""));
    this.tela = Integer.valueOf(pTela);
  }
  
  public void novo() {
    this.equipamento = new Equipamento();
   
    this.tela = Integer.valueOf(1);
  }
  
  public void listar() {
    this.listaEquipamento = this.equipamentoService.listar(this.loginController.getEmpresa().getSeqEmpresa(), this.pesquisa, Situacao.TODOS);
  }
  
    public void listarFin() {
    this.listaEquipamento = this.equipamentoService.listarFin(this.loginController.getEmpresa().getSeqEmpresa(), this.pesquisa, Situacao.TODOS,"Financeiro");
  }
    
	public void filtrar() {
           		ClausulaWhere condicao = new ClausulaWhere();
		condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "equipamento.seq_empresa", GeneroCondicaoWhere.igual,
				String.valueOf(this.loginController.getEmpresa().getSeqEmpresa()), TipoCondicaoWhere.Numero);

		if (this.seqEquipamentoSelecionado != null) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "equipamento.seq_equipamento",
					GeneroCondicaoWhere.igual, String.valueOf(this.seqEquipamentoSelecionado),
					TipoCondicaoWhere.Numero);
		}

		if (this.seqParceiroSelecionado != null) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "equipamento_parceiro.seq_parceiro",
					GeneroCondicaoWhere.igual, String.valueOf(this.seqParceiroSelecionado),
                                        TipoCondicaoWhere.Numero);
		}
                this.listaEquipamento = this.equipamentoService.listar3(condicao);
                } 
        
    
    
  
  public void deletar() {
    if (this.equipamentoService.deletar(this.equipamento)) {
      listar();
      this.equipamento = new Equipamento();
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro eliminado com sucesso.", ""));
      this.tela = Integer.valueOf(0);
      listar();
    } else {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao excluir registro.", ""));
    }
  }
  
  public void adicionarVinculo() {
    /*if (this.equipamento.getSeqEquipamento() == null) {
      salvar(1);
    }*/
    this.equipamentoParceiro.setSeqEquipamento(this.equipamento.getSeqEquipamento());
    this.equipamentoParceiroService.salvar(this.equipamentoParceiro);
    this.listaEquipamentoParceiro = this.equipamentoParceiroService.listarParceiro(this.equipamento.getSeqEquipamento());
    this.equipamentoParceiro = new EquipamentoParceiro();
  }
  
  public void removerVinculo(EquipamentoParceiro pEquipamentoParceiro) {
    this.equipamentoParceiroService.deletar(pEquipamentoParceiro);
    this.listaEquipamentoParceiro = this.equipamentoParceiroService.listarParceiro(this.equipamento.getSeqEquipamento());
  }
  
  public void fecharTela() throws IOException {
    this.loginController.mudarPagina("/principal/ principal.jsf");
  }
  
  public void selecionar(Equipamento pEquipamento) {
    this.equipamento = pEquipamento;
    this.listaEquipamentoParceiro = this.equipamentoParceiroService.listarParceiro(this.equipamento.getSeqEquipamento());
    listar();
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
  
  public EquipamentoService getEquipamentoService() {
    return this.equipamentoService;
  }
  
  public void setEquipamentoService(EquipamentoService equipamentoService) {
    this.equipamentoService = equipamentoService;
  }
  
  public Equipamento getEquipamento() {
    return this.equipamento;
  }
  
  public void setEquipamento(Equipamento equipamento) {
    this.equipamento = equipamento;
  }
  
  public Parceiro getParceiro() {
    return this.parceiro;
  }
  
  public void setParceiro(Parceiro parceiro) {
    this.parceiro = parceiro;
  }  
  
  public List<Equipamento> getListaEquipamento() {
    return this.listaEquipamento;
  }
  
  public void setListaEquipamento(List<Equipamento> listaEquipamento) {
    this.listaEquipamento = listaEquipamento;
  }
  
    public List<Equipamento> getListaEquipamentoIni() {
    return this.listaEquipamentoIni;
  }
  
  public void setListaEquipamentoIni(List<Equipamento> listaEquipamentoIni) {
    this.listaEquipamentoIni = listaEquipamentoIni;
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
  
  public List<Parceiro> getListaParceiro() {
    return this.listaParceiro;
  }
  
  public void setListaParceiro(List<Parceiro> listaParceiro) {
    this.listaParceiro = listaParceiro;
  }
  
  public EquipamentoParceiroService getEquipamentoParceiroService() {
    return this.equipamentoParceiroService;
  }
  
  public void setEquipamentoParceiroService(EquipamentoParceiroService equipamentoParceiroService) {
    this.equipamentoParceiroService = equipamentoParceiroService;
  }
  
  public EquipamentoParceiro getEquipamentoParceiro() {
    return this.equipamentoParceiro;
  }
  
    public void setEquipamentoParceiro(EquipamentoParceiro equipamentoParceiro) {
        this.equipamentoParceiro = equipamentoParceiro;
    }

    public List<EquipamentoParceiro> getListaEquipamentoParceiro() {
        return this.listaEquipamentoParceiro;
    }

    public void setListaEquipamentoParceiro(List<EquipamentoParceiro> listaEquipamentoParceiro) {
        this.listaEquipamentoParceiro = listaEquipamentoParceiro;
    }

    public Long getSeqEquipamentoSelecionado() {
        return this.seqEquipamentoSelecionado;
    }

    public void setSeqEquipamentoSelecionado(Long seqEquipamentoSelecionado) {
        this.seqEquipamentoSelecionado = seqEquipamentoSelecionado;
    }

    public Long getSeqParceiroSelecionado() {
        return this.seqParceiroSelecionado;
    }

    public void setSeqParceiroSelecionado(Long seqParceiroSelecionado) {
        this.seqParceiroSelecionado = seqParceiroSelecionado;
    }

}
