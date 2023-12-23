package Controller;

import NvAvisos.NvAvisos;
import java.util.List;
import NvAvisos.NvAvisosService;
import NvCertificadoDetalhe.NvCertificadoDetalhe;
import NvCertificadoDetalhe.NvCertificadoDetalheService;
import NvCertificado.NvCertificado;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import Util.Util;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 
import java.util.GregorianCalendar;

@ManagedBean(name = "nvAvisosController")
@ViewScoped
public class NvAvisosController
{
   
    @ManagedProperty("#{loginController}")
    protected LoginController loginController;
    private NvAvisosService nvAvisosService;
    private List<NvAvisos> listaNvAvisos;
    private List<NvAvisos> listaNvAvisos60;
    private List<NvAvisos> listaNvAvisos30;
    private List<NvAvisos> listaNvAvisosJanelas;
    private List<NvAvisos> listaNvAvisosJanelas30;
    private List<NvAvisos> listaNvAvisosJanelas60;
    NvAvisos nvAvisos = new NvAvisos();
    NvCertificadoDetalhe nvCertificadoDetalhe = new NvCertificadoDetalhe();
    NvCertificado nvCertificado = new NvCertificado();
    NvCertificadoDetalheService nvCertificadoDetalheService = new NvCertificadoDetalheService();
    Util util = new Util();
    LocalDate dtok = LocalDate.now().plusDays(30);
    public NvAvisosController() {
    this.nvAvisosService = new NvAvisosService();
    }
    
    public void iniciar() {
        if (this.loginController.usuario.getAcOrgListArquivo()== null || this.loginController.usuario.getAcOrgListArquivo().equals("-1")) {
            this.loginController.mudarPagina("/organizacional/acessonegado.jsf");
            return;
        }
        this.listar();
    }
    
       public void iniciar60() {
        if (this.loginController.usuario.getAcOrgListArquivo()== null || this.loginController.usuario.getAcOrgListArquivo().equals("-1")) {
            this.loginController.mudarPagina("/organizacional/acessonegado.jsf");
            return;
        }
        this.listar60();
    }   
       
       public void iniciar30() {
        if (this.loginController.usuario.getAcOrgListArquivo()== null || this.loginController.usuario.getAcOrgListArquivo().equals("-1")) {
            this.loginController.mudarPagina("/organizacional/acessonegado.jsf");
            return;
        }
        this.listar30();
    }
       
    public void iniciarJanelas() {
        if (this.loginController.usuario.getAcOrgListArquivo() == null || this.loginController.usuario.getAcOrgListArquivo().equals("-1")) {
            this.loginController.mudarPagina("/organizacional/acessonegado.jsf");
            return;
        }
        this.listarJanelas();

    }
       
    public void iniciarJanelas30() {
        if (this.loginController.usuario.getAcOrgListArquivo() == null || this.loginController.usuario.getAcOrgListArquivo().equals("-1")) {
            this.loginController.mudarPagina("/organizacional/acessonegado.jsf");
            return;
        }
        this.listarJanelas30();
    }  
    
    public void iniciarJanelas60() {
        if (this.loginController.usuario.getAcOrgListArquivo() == null || this.loginController.usuario.getAcOrgListArquivo().equals("-1")) {
            this.loginController.mudarPagina("/organizacional/acessonegado.jsf");
            return;
        }
        this.listarJanelas60();
    }
    
    public void listar() {
        this.setAvisos(this.nvAvisosService.listar(this.loginController.getEmpresa().getSeqEmpresa()));
    }
    
      public void listar60() {
        this.setAvisos60(this.nvAvisosService.listar60(this.loginController.getEmpresa().getSeqEmpresa()));
    }
    
        public void listar30() {
        this.setAvisos30(this.nvAvisosService.listar30(this.loginController.getEmpresa().getSeqEmpresa()));
    }
        
    public void listarJanelas() {
        this.setAvisosJanelas(this.nvAvisosService.listarJanelas(this.loginController.getEmpresa().getSeqEmpresa()));
    }    
     
    public void listarJanelas30() {
        this.setAvisosJanelas30(this.nvAvisosService.listarJanelas30(this.loginController.getEmpresa().getSeqEmpresa()));
    }
    
    
    public void listarJanelas60() {
        this.setAvisosJanelas60(this.nvAvisosService.listarJanelas60(this.loginController.getEmpresa().getSeqEmpresa()));
    }

    public void listarEnvio() {
        this.setAvisosJanelas(this.nvAvisosService.listarPEnvio(this.nvAvisos.getSeqCertificado()));
    }

    public void enviar(NvAvisos pCer) {
        this.nvAvisos.setSeqNvCertificadoDetalhe(pCer.getSeqNvCertificadoDetalhe());
        this.nvAvisos.setAviso("SIM");
        this.nvAvisos = this.nvAvisosService.salvar(this.nvAvisos);
        this.util.enviarAviso(pCer);        
    } 

     public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public LoginController getLoginController() {
        return this.loginController;
    }
    
    public List<NvAvisos> getListaAvisos() {
        return this.listaNvAvisos;
    }
    
    public void setAvisos(List<NvAvisos> listaAvisos) {
        this.listaNvAvisos = listaAvisos;
    }     
    
      public List<NvAvisos> getListaAvisos60() {
        return this.listaNvAvisos60;
    }
    
    public void setAvisos60(List<NvAvisos> listaAvisos60) {
        this.listaNvAvisos60 = listaAvisos60;
    } 
    
      public List<NvAvisos> getListaAvisos30() {
        return this.listaNvAvisos30;
    }
    
    public void setAvisos30(List<NvAvisos> listaAvisos30) {
        this.listaNvAvisos30 = listaAvisos30;
    } 
    
    public List<NvAvisos> getListaAvisosJanelas() {
        return this.listaNvAvisosJanelas;
    }

    public void setAvisosJanelas(List<NvAvisos> listaAvisosJanelas) {
        this.listaNvAvisosJanelas = listaAvisosJanelas;
    } 
    
    public List<NvAvisos> getListaAvisosJanelas30() {
        return this.listaNvAvisosJanelas30;
    }

    public void setAvisosJanelas30(List<NvAvisos> listaAvisosJanelas30) {
        this.listaNvAvisosJanelas30 = listaAvisosJanelas30;
    }
    
        public List<NvAvisos> getListaAvisosJanelas60() {
        return this.listaNvAvisosJanelas60;
    }

    public void setAvisosJanelas60(List<NvAvisos> listaAvisosJanelas60) {
        this.listaNvAvisosJanelas60 = listaAvisosJanelas60;
    }
    
    public void buttonAction() {
        addMessage("Welcome to PrimeFaces!!");
    }
 
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    public NvAvisos getNvAvisos() {
        return this.nvAvisos;
    }
    public void setNvAvisos(NvAvisos nvAvisos) {
		this.nvAvisos = nvAvisos;
	}    

    
    
}





