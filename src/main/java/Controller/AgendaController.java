package Controller;

import Agenda.Agenda;
import Agenda.AgendaService;
import Empresa.Empresa;
import Parceiro.Parceiro;
import Parceiro.ParceiroService;
import TipoAgenda.TipoAgenda;
import TipoAgenda.TipoAgendaService;
import Usuario.Usuario;
import Util.EventoAdicionais;
import Util.Situacao;
import java.awt.event.ActionEvent;
import java.io.PrintStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;


@ManagedBean(name="agendaController")
@ViewScoped
public class AgendaController
{
  @ManagedProperty("#{loginController}")
  protected LoginController loginController;
  private ScheduleModel eventModel;
  private EventoAdicionais event = new EventoAdicionais();
  Agenda agenda = new Agenda();
  List<Agenda> listaAgenda = new ArrayList();
  List<Agenda> listaAgendaT = new ArrayList();
  AgendaService agendaService = new AgendaService();
  ParceiroService parceiroService = new ParceiroService();
  List<Parceiro> listaParceiro = new ArrayList();
  List<TipoAgenda> listaTipoAgenda = new ArrayList();
  
  @PostConstruct
  public void init() {
    this.listaAgenda = this.agendaService.listarPorUsuario(this.loginController.getUsuario().getSeqUsuario());
    
 
    /*this.listaAgendaT = this.agendaService.listarTodosUsuarios(this.loginController.getUsuario().getSeqUsuario());*/

    TipoAgendaService tipoAgendaService = new TipoAgendaService();
    this.listaTipoAgenda = tipoAgendaService.listar(this.loginController.getEmpresa().getSeqEmpresa(), "", Situacao.ATIVO);
    
    ParceiroService parceiroService = new ParceiroService();
    this.listaParceiro = this.parceiroService.listarParceiro(this.loginController.getUsuario().getSeqUsuario(), "");
		
    
    
    
    EventoAdicionais evento = new EventoAdicionais();
    
    evento.setSeqParceiro("");
    this.eventModel = new DefaultScheduleModel();
    for (Agenda vAgenda : this.listaAgenda) {
      evento = new EventoAdicionais();
      evento.setEditable(true);
      evento.setSeqParceiro(vAgenda.getSeqParceiro());
      evento.setSeqTipoAgenda(vAgenda.getSeqTipoAgenda());
      evento.setData(vAgenda.getSeqAgenda());
      evento.setStartDate(vAgenda.getDtInicio());
      evento.setEndDate(vAgenda.getDtFim());
      evento.setTitle(vAgenda.getAssunto());
      evento.setDescription(vAgenda.getDescricao());
      evento.setCor(vAgenda.getCor());
      evento.setNomeParceiro(vAgenda.getNomeParceiro());
      evento.setNomeTipoAgenda(vAgenda.getSeqTipoAgendaNome());
      evento.setResponsavel(vAgenda.getNomeUsuario());
      
      if (vAgenda.getCor().equals("Normal")) {
        evento.setStyleClass("normal");
      } else if (vAgenda.getCor().equals("Moderado")) {
        evento.setStyleClass("moderado");
      } else if (vAgenda.getCor().equals("Urgente")) {
        evento.setStyleClass("urgente");
      }
      
      this.eventModel.addEvent(evento);
    }
  }
  
  public void novo(SelectEvent selectEvent) {
    Date data = (Date)selectEvent.getObject();
    Timestamp inicio = new Timestamp(data.getTime());
    
    this.event = new EventoAdicionais("", inicio, inicio, this.loginController.getUsuario().getSeqUsuario());
  }
  
  public void selecionar(SelectEvent selectEvent)
  {
    this.event = ((EventoAdicionais)selectEvent.getObject());
  }
  
  public void salvar() {
    this.agenda = new Agenda();
    System.out.println("event.getData(): " + this.event.getData());
    if (this.event.getData() != null) {
      this.agenda.setSeqAgenda((String)this.event.getData());
    }
    
    this.agenda.setSeqUsuario(this.loginController.getUsuario().getSeqUsuario());
    this.agenda.setSeqParceiro(this.event.getSeqParceiro());
    this.agenda.setAssunto(this.event.getTitle());
    this.agenda.setDescricao(this.event.getDescription());
    this.agenda.setDataCadastro(new Date());
    this.agenda.setDtInicio(this.event.getStartDate());
    this.agenda.setDtFim(this.event.getEndDate());
    this.agenda.setSeqTipoAgenda(this.event.getSeqTipoAgenda());
    this.agenda.setSeqParceiro(this.event.getSeqParceiro());
    this.agenda.setCor(this.event.getCor());
    this.agendaService.salvar(this.agenda);
    
    init();
    this.event = new EventoAdicionais();
  }
  
  
   public void listartodos() {
        this.setListaAgenda(this.agendaService.listarTodosUsuarios(this.loginController.getEmpresa().getSeqEmpresa()));
    }
  
  
  public void deletar() {
    this.agenda.setSeqAgenda((String)this.event.getData());
    this.agendaService.deletar(this.agenda);
    
    init();
    this.event = new EventoAdicionais();
  }
  
  public EventoAdicionais getEvent() {
    return this.event;
  }
  
  public void setEvent(EventoAdicionais event) {
    this.event = event;
  }
  
  public void onEventMove(ScheduleEntryMoveEvent event) {
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
    
    addMessage(message);
  }
  
  public void onEventResize(ScheduleEntryResizeEvent event) {
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
    
    addMessage(message);
  }
  
  private void addMessage(FacesMessage message) {
    FacesContext.getCurrentInstance().addMessage(null, message);
  }
  
  public LoginController getLoginController() {
    return this.loginController;
  }
  
  public void setLoginController(LoginController loginController) {
    this.loginController = loginController;
  }
  
  public Agenda getAgenda() {
    return this.agenda;
  }
  
  public void setAgenda(Agenda agenda) {
    this.agenda = agenda;
  }
  
  public AgendaService getAgendaService() {
    return this.agendaService;
  }
  
  public void setAgendaService(AgendaService agendaService) {
    this.agendaService = agendaService;
  }
  
  public List<Agenda> getListaAgenda() {
    return this.listaAgenda;
  }
  
  public void setListaAgenda(List<Agenda> listaAgenda) {
    this.listaAgenda = listaAgenda;
  }
  
  public ScheduleModel getEventModel() {
    return this.eventModel;
  }
  
  public void setEventModel(ScheduleModel eventModel) {
    this.eventModel = eventModel;
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
  
  public List<TipoAgenda> getListaTipoAgenda() {
    return this.listaTipoAgenda;
  }
  
  public void setListaTipoAgenda(List<TipoAgenda> listaTipoAgenda) {
    this.listaTipoAgenda = listaTipoAgenda;
  }
  
  public List<Agenda> getListaAgendaT() {
    return this.listaAgendaT;
  }
  
  public void setListaAgendaT(List<Agenda> listaAgendaT) {
    this.listaAgendaT = listaAgendaT;
  }  
}
