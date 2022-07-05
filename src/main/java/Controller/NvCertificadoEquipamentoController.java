package Controller;

import ClausulaSQL.ClausulaWhere;
import ClausulaSQL.GeneroCondicaoWhere;
import ClausulaSQL.OperacaoCondicaoWhere;
import ClausulaSQL.TipoCondicaoWhere;
import Colaborador.Colaborador;
import Colaborador.ColaboradorService;
import Empresa.Empresa;
import Equipamento.Equipamento;
import Equipamento.EquipamentoService;
import EquipamentoParceiro.EquipamentoParceiro;
import EquipamentoParceiro.EquipamentoParceiroService;
import NvCertificado.NvCertificado;
import NvCertificadoEquipamento.NvCertificadoEquipamento;
import NvCertificadoEquipamento.NvCertificadoEquipamentoService;
import Parceiro.Parceiro;
import Parceiro.ParceiroService;
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


@ManagedBean(name="nvCertificadoEquipamentoController")
@ViewScoped
public class NvCertificadoEquipamentoController
{
  @ManagedProperty("#{loginController}")
  protected LoginController loginController;
  NvCertificadoEquipamentoService nvCertificadoEquipamentoService = new NvCertificadoEquipamentoService();
  NvCertificadoEquipamento nvCertificadoEquipamento = new NvCertificadoEquipamento();
  List<NvCertificadoEquipamento> listaNvCertificadoEquipamento = new ArrayList();
  
  EquipamentoService equipamentoService = new EquipamentoService();
  List<Equipamento> listaEquipamento = new ArrayList();
  Equipamento equipamento = new Equipamento(); 
  
  ParceiroService parceiroService = new ParceiroService();
  List<Parceiro> listaParceiro = new ArrayList();
  Parceiro parceiro = new Parceiro();
  
  EquipamentoParceiro equipamentoParceiro = new EquipamentoParceiro();
  List<EquipamentoParceiro> listaEquipamentoParceiro = new ArrayList();
 
  EquipamentoParceiroService equipamentoParceiroService = new EquipamentoParceiroService();
  List<EquipamentoParceiro> listaEquipamentoParceiroService = new ArrayList();
  
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
   
  ColaboradorService colaboradorService = new ColaboradorService();
  List<Colaborador> listaColaborador = new ArrayList();
  
 
   Long seqParceiroSelecionado;  
   Long seqEquipamentoSelecionado;
  
  public void iniciar()
  {
    if ((this.loginController.usuario.getAcOpCertificado() == null) || (this.loginController.usuario.getAcOpCertificado().equals("-1"))) {
      this.loginController.mudarPagina("/organizacional/acessonegado.jsf");
       }
   this.listaParceiro = this.parceiroService.listarParceiro(this.loginController.getUsuario().getSeqUsuario(),"");
   this.listaColaborador = this.colaboradorService.listar(this.loginController.getEmpresa().getSeqEmpresa(), "", Situacao.ATIVO);
   this.listaEquipamento = this.equipamentoService.listarPorParceiro(this.loginController.getEmpresa().getSeqEmpresa(), this.nvCertificadoEquipamento.getSeqParceiro(), Situacao.ATIVO);
   this.listaEquipamento = this.equipamentoService.listar(this.loginController.getEmpresa().getSeqEmpresa(), "", Situacao.ATIVO);
  }
  
  public void salvar(int pTela) {
    this.nvCertificadoEquipamento.setSeqEmpresa(this.loginController.getEmpresa().getSeqEmpresa());
    this.nvCertificadoEquipamento = this.nvCertificadoEquipamentoService.salvar(this.nvCertificadoEquipamento);
    listar();
    listarEquipamento();
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
    FacesMessage.SEVERITY_INFO, "Registro armazenado com sucesso.", ""));
    this.tela = Integer.valueOf(1);
  }
    
 public void novo() {
    this.nvCertificadoEquipamento = new NvCertificadoEquipamento();
    this.tela = 1;
}           
  
  public void listar() {
    this.listaNvCertificadoEquipamento = this.nvCertificadoEquipamentoService.listar(this.loginController.getEmpresa().getSeqEmpresa(), this.pesquisa, Situacao.TODOS);
    listarEquipamento();
}
 
 /* public void listarMax() {
    this.listaNvCertificadoEquipamento = this.nvCertificadoEquipamentoService.listarMax();
    listarEquipamento();
}*/  
  
  public void listarEquipamento() {
    this.listaEquipamento = this.equipamentoService.listarPorParceiro(this.loginController.getEmpresa().getSeqEmpresa(), this.nvCertificadoEquipamento.getSeqParceiro(), Situacao.ATIVO);
}
	
  
  public void filtrar() {
    boolean executar = true;    
    ClausulaWhere condicao = new ClausulaWhere();
    condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "NV_CERTIFICADO_EQUIPAMENTO.seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(this.loginController.getEmpresa().getSeqEmpresa()), TipoCondicaoWhere.Numero);
    
    if (this.seqEquipamentoSelecionado != null) {
      condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "NV_CERTIFICADO_EQUIPAMENTO.seq_nv_equipamento", GeneroCondicaoWhere.igual, String.valueOf(this.seqEquipamentoSelecionado), TipoCondicaoWhere.Numero);
    }
    
      if (this.seqParceiroSelecionado != null) {
      condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "NV_CERTIFICADO_EQUIPAMENTO.seq_parceiro", GeneroCondicaoWhere.igual, String.valueOf(this.seqParceiroSelecionado), TipoCondicaoWhere.Numero);
    }
    
    if (executar == true) {
      this.listaNvCertificadoEquipamento = this.nvCertificadoEquipamentoService.listar(condicao);
    }
  }
  
  public void deletar()
  {
    if (this.nvCertificadoEquipamentoService.deletar(this.nvCertificadoEquipamento)) {
      listar();
      this.nvCertificadoEquipamento = new NvCertificadoEquipamento();
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
  
  public void selecionar (NvCertificadoEquipamento pNvCertificadoEquipamento)
          {
      this.nvCertificadoEquipamento = pNvCertificadoEquipamento;
      this.listaUpload = this.uploadService.listar(this.loginController.empresa.getSeqEmpresa(),
				this.nvCertificadoEquipamento.getSeqCertificado());
      this.tela = 1;
   }    
  
  public void mudarTela(Integer pTela) {
    this.tela = pTela;
  }
  
  public void imprimir() throws IOException, JRException {

        String uuidCertificado = getUUIDCertificado(this.nvCertificadoEquipamento);

        HashMap parametro = new HashMap();
        Conexao conexao = new Conexao();
        Connection conn = Conexao.getConnection();

        String caminho = "/relatorio/CERTIFICADO EQUIPAMENTO/CERTIFICADO.jasper";
        parametro.put("pSeqCertificado", Integer.valueOf(this.nvCertificadoEquipamento.getSeqCertificado()));
        parametro.put("pReportURL", "http://191.252.59.211//erp/operacional/certificado_equipamento/" + this.nvCertificadoEquipamento.getSeqEmpresa() + "/" + uuidCertificado);

        FacesContext facesContext = FacesContext.getCurrentInstance();

        facesContext.responseComplete();
        ServletContext scontext = (ServletContext) facesContext.getExternalContext()
                .getContext();
        System.out.println(caminho);
        JasperPrint jasperPrint = JasperFillManager
                .fillReport(scontext.getRealPath(caminho), parametro, conn);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        JRPdfExporter exporter = new JRPdfExporter();

        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);

        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

        exporter.exportReport();
        byte[] bytes = baos.toByteArray();

        if ((bytes != null) && (bytes.length > 0)) {
            HttpServletResponse responseeqp = (HttpServletResponse) facesContext
                    .getExternalContext().getResponse();
            responseeqp.setContentType("application/pdf");
            responseeqp.setHeader("Content-disposition",
                    "inline; filename=\"Certificado.pdf\"");
            responseeqp.setContentLength(bytes.length);
            ServletOutputStream outputStream = responseeqp.getOutputStream();
            outputStream.write(bytes, 0, bytes.length);
            outputStream.flush();
            outputStream.close();
        }
    }

    private String getUUIDCertificado(NvCertificadoEquipamento certificadoEquipamento) {
        String uuid = "";
        try (Connection conn = Conexao.getConnection()) {
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery(
                        "select uuid from NV_CERTIFICADO_EQUIPAMENTO where SEQ_NV_CERTIFICADO = "
                        + certificadoEquipamento.getSeqCertificado())) {
                    while (rs.next()) {
                        uuid = rs.getString("uuid");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return uuid;
    }
    
	public void upload() {
		String id = this.nvCertificadoEquipamento.getIdentificacao().replace("/", "-");
		this.upload.setSeqCertificado(this.nvCertificadoEquipamento.getSeqCertificado());
                this.upload.setOrigem("EQUIPAMENTO" + id + "-" + this.nvCertificadoEquipamento.getSeqCertificado());
		this.upload.setSeqEmpresa(this.loginController.empresa.getSeqEmpresa());
		this.upload.setSeqUsuario(this.loginController.usuario.getSeqUsuario());
		this.uploadController.upload(this.file, this.upload);
		this.listaUpload = this.uploadService.listar(this.loginController.empresa.getSeqEmpresa(),
				this.nvCertificadoEquipamento.getSeqCertificado());
		this.upload = new Upload();
	}

	public void download(Upload pUpload) {
		if (pUpload.getNomeArquivo().contains("pdf")) {
			visualizar(pUpload);
		} else {
			this.fileDownload = this.uploadController.download(pUpload);
		}
	}

	public void visualizar(Upload pUpload) {
		try {
			FileInputStream s = new FileInputStream(pUpload.getUrl());

			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			byte[] buf = new byte['Ѐ'];
			try {
				int readNum;
				while ((readNum = s.read(buf)) != -1) {
					bos.write(buf, 0, readNum);
					System.out.println("read " + readNum + " bytes,");
				}
			} catch (IOException ex) {
				Logger.getLogger(DocumentoController.class.getName()).log(Level.SEVERE, null, ex);
			}

			byte[] bytes = bos.toByteArray();

			HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
					.getResponse();

			response.setHeader("Content-Disposition", "inline; filename=" + pUpload.getNomeArquivo());
			OutputStream output = response.getOutputStream();
			output.write(bytes);
			response.flushBuffer();
			FacesContext.getCurrentInstance().responseComplete();
		} catch (IOException ex) {
			Logger.getLogger(DocumentoController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void removerAnexo(Upload pUpload) {
		this.uploadController.deletar(pUpload);
		this.listaUpload = this.uploadService.listar(this.loginController.empresa.getSeqEmpresa(),
				this.nvCertificadoEquipamento.getSeqCertificado());
	}    
   
 
  public LoginController getLoginController()
  {
    return this.loginController;
  }
  
  public void setLoginController(LoginController loginController) {
    this.loginController = loginController;
  }
  
  public NvCertificadoEquipamentoService getNvCertificadoEquipamentoService() {
    return this.nvCertificadoEquipamentoService;
  }
  
  public void setNvCertificadoEquipamentoService(NvCertificadoEquipamentoService nvCertificadoEquipamentoService) {
    this.nvCertificadoEquipamentoService = nvCertificadoEquipamentoService;
  }
  
  public NvCertificadoEquipamento getNvCertificadoEquipamento() {
    return this.nvCertificadoEquipamento;
  }
  
  public void setNvCertificadoEquipamento(NvCertificadoEquipamento nvCertificadoEquipamento) {
    this.nvCertificadoEquipamento = nvCertificadoEquipamento;
  }
  
  public List<NvCertificadoEquipamento> getListaNvCertificadoEquipamento() {
    return this.listaNvCertificadoEquipamento;
  }
  
    public void setListaNvCertificadoEquipamento(List<NvCertificadoEquipamento> listaNvCertificadoEquipamento) {
        this.listaNvCertificadoEquipamento = listaNvCertificadoEquipamento;
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

    public EquipamentoService getEquipamentoService() {
        return this.equipamentoService;
    }

    public void setEquipamentoService(EquipamentoService equipamentoService) {
        this.equipamentoService = equipamentoService;
    }

    public List<Equipamento> getListaEquipamento() {
        return this.listaEquipamento;
    }

    public void setListaEquipamento(List<Equipamento> listaEquipamento) {
        this.listaEquipamento = listaEquipamento;
    }

    public boolean isbEquipamento() {
        return this.bEquipamento;
    }

    public void setbEquipamento(boolean bEquipamento) {
        this.bEquipamento = bEquipamento;
    }

    public ColaboradorService getColaboradorService() {
        return this.colaboradorService;
    }

    public void setColaboradorService(ColaboradorService colaboradorService) {
        this.colaboradorService = colaboradorService;
    }

    public List<Colaborador> getListaColaborador() {
        return this.listaColaborador;
    }

    public void setListaColaborador(List<Colaborador> listaColaborador) {
        this.listaColaborador = listaColaborador;
    }

    public Util getUtil() {
        return this.util;
    }

    public void setUtil(Util util) {
        this.util = util;
    }

    public List<EquipamentoParceiro> getEquipamentoParceiro() {
        return this.listaEquipamentoParceiro;
    }

    public void setEquipamentoParceiro(List<EquipamentoParceiro> listaequipamentoParceiro) {
        this.listaEquipamentoParceiro = listaequipamentoParceiro;
    }
    
    public Long getSeqParceiroSelecionado() {
        return this.seqParceiroSelecionado;
    }
    public void setSeqParceiroSelecionado(Long seqParceiroSelecionado) {
        this.seqParceiroSelecionado = seqParceiroSelecionado;
    }
    
    public Long getSeqEquipamentoSelecionado() {
        return this.seqEquipamentoSelecionado;
    }

    public void setSeqEquipamentoSelecionado(Long seqEquipamentoSelecionado) {
        this.seqEquipamentoSelecionado = seqEquipamentoSelecionado;
    }
    
	public Upload getUpload() {
		return this.upload;
	}

	public void setUpload(Upload upload) {
		this.upload = upload;
	}

	public UploadService getUploadService() {
		return this.uploadService;
	}

	public void setUploadService(UploadService uploadService) {
		this.uploadService = uploadService;
	}

	public List<Upload> getListaUpload() {
		return this.listaUpload;
	}

	public void setListaUpload(List<Upload> listaUpload) {
		this.listaUpload = listaUpload;
	}

	public UploadedFile getFile() {
		return this.file;
	}

	public UploadController getUploadController() {
		return this.uploadController;
	}

	public void setUploadController(UploadController uploadController) {
		this.uploadController = uploadController;
	}

	public StreamedContent getFileDownload() {
		return this.fileDownload;
	}

	public void setFileDownload(StreamedContent fileDownload) {
		this.fileDownload = fileDownload;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}    

}
