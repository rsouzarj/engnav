package Controller;

import ClausulaSQL.ClausulaWhere;
import ClausulaSQL.GeneroCondicaoWhere;
import ClausulaSQL.OperacaoCondicaoWhere;
import ClausulaSQL.TipoCondicaoWhere;
import Empresa.Empresa;
import NvManutencao.NvManutencao;
import NvManutencao.NvManutencaoDetalhe;
import NvManutencao.NvManutencaoService;
import NvManutencao.NvManutencaoDetalheService;
import Equipamento.Equipamento;
import Equipamento.EquipamentoService;
import Util.Conexao;
import Util.Situacao;
import Util.Util;
import Upload.Upload;
import Upload.UploadService;
import _Teste.DataPorExtenso;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.lang.model.SourceVersion;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name="manutencaoController")
@ViewScoped

public class ManutencaoController
{
  @ManagedProperty("#{loginController}")
  protected LoginController loginController;
  
  NvManutencao nvManutencao = new NvManutencao();
  NvManutencaoService nvManutencaoService = new NvManutencaoService();
  List<NvManutencao> listaNvManutencao = new ArrayList();
  
  NvManutencaoDetalhe nvManutencaoDetalhe = new NvManutencaoDetalhe();
  NvManutencaoDetalheService nvManutencaoDetalheService = new NvManutencaoDetalheService();
  List<NvManutencaoDetalhe> listaNvManutencaoDetalhe = new ArrayList();

  EquipamentoService equipamentoService = new EquipamentoService();
  List<Equipamento> listaEquipamento = new ArrayList();
  Equipamento equipamento = new Equipamento();  
  
  Upload upload = new Upload();
  UploadService uploadService = new UploadService();
  UploadController uploadController = new UploadController();
  List<Upload> listaUpload = new ArrayList();

  UploadedFile file;

  StreamedContent fileDownload;
  
  String pesquisa = "";
  Integer tela = Integer.valueOf(0);
  Util util = new Util();
  boolean bEquipamento = false;
   
  Long seqEquipamentoSelecionado;
  
 public void iniciar()
 { 
    if ((this.loginController.usuario.getAcOpCertificado() == null) || (this.loginController.usuario.getAcOpCertificado().equals("-1"))) {
      this.loginController.mudarPagina("/organizacional/acessonegado.jsf");
       }
    this.listaEquipamento = this.equipamentoService.listarequipamento(this.loginController.getEmpresa().getSeqEmpresa(), this.nvManutencao.getSeqNvEquipamento(), Situacao.ATIVO);
    
 }

    }
