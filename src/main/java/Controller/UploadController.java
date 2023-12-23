package Controller;

import Empresa.Empresa;
import Upload.Upload;
import Upload.UploadService;
import UpPrestacao.UpPrestacao;
import UpPrestacao.UpPrestacaoService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;







@ManagedBean(name="uploadController")
@ViewScoped
public class UploadController
{
  @ManagedProperty("#{loginController}")
  protected LoginController loginController;
  UploadService uploadService = new UploadService();
  Upload upload = new Upload();
  List<Upload> listaUpload = new ArrayList();
  UpPrestacaoService upPrestacaoService = new UpPrestacaoService();
  UpPrestacao upPrestacao = new UpPrestacao();
  List<UpPrestacao> listaUpPrestacao = new ArrayList();
  String pesquisa = "";
  Integer tela = Integer.valueOf(0);
  private UploadedFile file;
  private StreamedContent fileDownload;
  
  public void iniciar() {}
  
  public void salvar(int pTela)
  {
    this.upload.setSeqEmpresa(this.loginController.getEmpresa().getSeqEmpresa());
    this.upload = this.uploadService.salvar(this.upload);
    
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro armazenado com sucesso.", ""));
    this.tela = Integer.valueOf(pTela);
  }
  
  public void novo() {
    this.upload = new Upload();
    this.tela = Integer.valueOf(1);
  }
  

  public void listar() {}
  
  public void deletar(Upload pUpload)
  {
    File arquivo = new File(pUpload.getUrl());
    if (arquivo.getName().equals(pUpload.getNomeArquivo())) {
      arquivo.delete();
    }
    
    if (this.uploadService.deletar(pUpload)) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Anexo eliminado com sucesso.", ""));
      listar();
    } else {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao excluir anexo.", ""));
    }
  }
  
  public void fecharTela() throws IOException {
    this.loginController.mudarPagina("/principal/ principal.jsf");
  }
  
  public void selecionar(Upload pUpload) {
    this.upload = pUpload;
    this.tela = Integer.valueOf(1);
  }
  
  public void mudarTela(Integer pTela) {
    this.tela = pTela;
  }
  

  public StreamedContent download(Upload pUpload)
  {
    try
    {
      FileInputStream stream = new FileInputStream(pUpload.getUrl());
      this.fileDownload = new DefaultStreamedContent(stream, "image/jpg", pUpload.getNomeArquivo());
    }
    catch (FileNotFoundException ex) {
      Logger.getLogger(UploadController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return this.fileDownload;
  }
  
  public StreamedContent visualizar(Upload pUpload)
  {
    try
    {
      FileInputStream stream = new FileInputStream(pUpload.getUrl());
      this.fileDownload = new DefaultStreamedContent(stream, "image/jpg", pUpload.getNomeArquivo());
    }
    catch (FileNotFoundException ex) {
      Logger.getLogger(UploadController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return this.fileDownload;
  }
  
  public void upload(UploadedFile pFile, Upload pUpload) {
    this.file = pFile;
    if (this.file != null) {
      try
      {
        File dir = new File("../applications/erp/upload/"+ pUpload.getOrigem());
        //File dir = new File("C:\\Upload\\"+ pUpload.getOrigem());
        

        if (!dir.exists()) {
          dir.mkdir();
        }
        
        File l = new File(dir, this.file.getFileName());
        
        FileOutputStream out = new FileOutputStream(l);
        
        InputStream in = this.file.getInputstream();
        
        byte[] buffer = new byte[' '];
        int nLidos;
        while ((nLidos = in.read(buffer)) >= 0) {
          out.write(buffer, 0, nLidos);
        }
        
        out.flush();
        out.close();
        
        pUpload.setNomeArquivo(this.file.getFileName());
        pUpload.setUrl(dir.getPath() + "\\" + this.file.getFileName());
        
        this.uploadService.salvar(pUpload);
        
        FacesMessage message = new FacesMessage("Sucesso", this.file.getFileName() + " arquivo anexado.");
        FacesContext.getCurrentInstance().addMessage(null, message);
      }
      catch (Exception e) {
        e.printStackTrace();
        FacesMessage message = new FacesMessage("Houve um problema, seu arquivo não pode ser anexado.");
        FacesContext.getCurrentInstance().addMessage(null, message);
      }
      
    } else {
      System.out.println("Arquivo null");
    }
  }
 
  /*Add por Roberto Souza*/
  
  public void upPrestacao(UploadedFile pFile, UpPrestacao pUpPrestacao) {
    this.file = pFile;
    if (this.file != null) {
      try
      {
        File dir = new File("C:\\Upload\\" + pUpPrestacao.getSeqUsuario());
        

        if (!dir.exists()) {
          dir.mkdir();
        }
        
        File l = new File(dir, this.file.getFileName());
        
        FileOutputStream out = new FileOutputStream(l);
        
        InputStream in = this.file.getInputstream();
        
        byte[] buffer = new byte[' '];
        int nLidos;
        while ((nLidos = in.read(buffer)) >= 0) {
          out.write(buffer, 0, nLidos);
        }
        
        out.flush();
        out.close();
        
        pUpPrestacao.setNomeArquivo(this.file.getFileName());
        pUpPrestacao.setUrl(dir.getPath() + "\\" + this.file.getFileName());
        
        this.upPrestacaoService.salvar(pUpPrestacao);
        
        FacesMessage message = new FacesMessage("Sucesso", this.file.getFileName() + " arquivo anexado.");
        FacesContext.getCurrentInstance().addMessage(null, message);
      }
      catch (Exception e) {
        e.printStackTrace();
        FacesMessage message = new FacesMessage("Houve um problema, seu arquivo não pode ser anexado.");
        FacesContext.getCurrentInstance().addMessage(null, message);
      }
      
    } else {
      System.out.println("Arquivo null");
    }
  }
  
  
  
  
  public void handleFileUpload(FileUploadEvent event) {
    FacesMessage message = new FacesMessage("Sucesso", event.getFile().getFileName() + " arquivo anexado.");
    FacesContext.getCurrentInstance().addMessage(null, message);
  }
  
  public UploadedFile getFile() {
    return this.file;
  }
  
  public void setFile(UploadedFile file) {
    this.file = file;
  }
  
  public StreamedContent getFile1() {
    return this.fileDownload;
  }
  

  public LoginController getLoginController()
  {
    return this.loginController;
  }
  
  public void setLoginController(LoginController loginController) {
    this.loginController = loginController;
  }
  
  public UploadService getUploadService() {
    return this.uploadService;
  }
  
  public void setUploadService(UploadService uploadService) {
    this.uploadService = uploadService;
  }
  
  public Upload getUpload() {
    return this.upload;
  }
  
  public void setUpload(Upload upload) {
    this.upload = upload;
  }
  
  public List<Upload> getListaUpload() {
    return this.listaUpload;
  }
  
  public void setListaUpload(List<Upload> listaUpload) {
    this.listaUpload = listaUpload;
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

