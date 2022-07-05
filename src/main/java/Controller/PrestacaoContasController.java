package Controller;

import ClausulaSQL.ClausulaWhere;
import ClausulaSQL.GeneroCondicaoWhere;
import ClausulaSQL.OperacaoCondicaoWhere;
import ClausulaSQL.TipoCondicaoWhere;
import Colaborador.Colaborador;
import Colaborador.ColaboradorService;
import Conta.Conta;
import Conta.ContaService;
import Documento.Documento;
import Documento.DocumentoService;
import DocumentoFiscal.DocumentoFiscal;
import DocumentoFiscal.DocumentoFiscalService;
import Empresa.Empresa;
import Equipamento.Equipamento;
import Equipamento.EquipamentoService;
import Financeiro.Financeiro;
import Financeiro.FinanceiroService;
import FinanceiroCategoria.FinanceiroCategoria;
import FinanceiroCategoria.FinanceiroCategoriaService;
import FinanceiroItemPc.FinanceiroItemPc;
import FinanceiroItemPc.FinanceiroItemPcService;
import Material.Material;
import Material.MaterialService;
import NvEmbarcacaoParceiro.NvEmbarcacaoParceiro;
import NvEmbarcacaoParceiro.NvEmbarcacaoParceiroService;
import Parceiro.Parceiro;
import Parceiro.ParceiroService;
import TipoMovimentoFinanceiro.TipoMovimentoFinanceiro;
import TipoMovimentoFinanceiro.TipoMovimentoFinanceiroService;
import Upload.Upload;
import Upload.UploadService;
import Usuario.Usuario;
import Util.Conexao;
import Util.Situacao;
import Util.Util;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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


@ManagedBean(name="prestacaoContasController")
@ViewScoped
public class PrestacaoContasController
{
  @ManagedProperty("#{loginController}")
  protected LoginController loginController;
  List<Financeiro> listaFinanceiro = new ArrayList();
  FinanceiroService financeiroService = new FinanceiroService();
  Financeiro financeiro = new Financeiro();
  
  Documento documento = new Documento();
  List<Documento> listaDocumento = new ArrayList();
  DocumentoService documentoService = new DocumentoService();
  
  List<DocumentoFiscal> listaDocumentoFiscal = new ArrayList();
  DocumentoFiscalService documentoFiscalService = new DocumentoFiscalService();
  
  List<TipoMovimentoFinanceiro> listaTipoLancamento = new ArrayList();
  TipoMovimentoFinanceiroService tipoMovimentoFinanceiroService = new TipoMovimentoFinanceiroService();
  
  List<FinanceiroCategoria> listaFinanceiroCategoria = new ArrayList();
  FinanceiroCategoriaService financeiroCategoriaService = new FinanceiroCategoriaService();
  
  List<Conta> listaConta = new ArrayList();
  ContaService contaService = new ContaService();
  
  List<Parceiro> listaParceiro = new ArrayList();
  ParceiroService parceiroService = new ParceiroService();
  
  List<Colaborador> listaColaborador = new ArrayList();
  ColaboradorService colaboradorService = new ColaboradorService();
  
  List<Material> listaMaterial = new ArrayList();
  MaterialService materialService = new MaterialService();
  
  
Upload upload = new Upload();
UploadService uploadService = new UploadService();
UploadController uploadController = new UploadController();
List<Upload> listaUpload = new ArrayList();
UploadedFile file;
StreamedContent fileDownload;
StreamedContent arquivoVisualizar;
	       
       
  
  FinanceiroItemPc financeiroItemPc = new FinanceiroItemPc();
  List<FinanceiroItemPc> listaFinanceiroItemPc = new ArrayList();
  List<FinanceiroItemPc> listaFinanceiroItemPcDeletado = new ArrayList();
  FinanceiroItemPcService financeiroItemPcService = new FinanceiroItemPcService();
  
  List<Equipamento> listaEquipamento = new ArrayList();
  EquipamentoService equipamentoService = new EquipamentoService();
  
  List<NvEmbarcacaoParceiro> listaNvEmbarcacaoParceiro = new ArrayList();
  NvEmbarcacaoParceiroService nvEmbarcacaoParceiroService = new NvEmbarcacaoParceiroService();
  
  boolean porDataLancamento;
  
  String dataLancamentoInicial;
  
  String dataLancamentoFinal;
  
  boolean porDataVencimento;
  
  String dataVencimentoInicial;
  String dataVencimentoFinal;
  boolean porDataPeriodoInicial;
  String dataPeriodoInicialInicial;
  String dataPeriodoInicialFinal;
  boolean porDataPeriodoFinal;
  String dataPeriodoFinalInicial;
  String dataPeriodoFinalFinal;
  String seqParceiroSelecionado;
  String seqColaboradorSelecionado;
  BigDecimal valorTotalPC = BigDecimal.ZERO;
  BigDecimal saldoPC = BigDecimal.ZERO;
  BigDecimal ItnTotal = BigDecimal.ZERO;
  
  boolean id;
  Integer tela = Integer.valueOf(0);

    public PrestacaoContasController() {
        this.listaUpload = new ArrayList();
    }
  



  public void salvar(int pTela)
  {
    this.financeiro.setSeqEmpresa(this.loginController.usuario.getSeqEmpresa());
    
    if (this.financeiro.getOcorrencias() == null) {
      this.financeiro.setOcorrencias(Integer.valueOf(0));
    }
    if (this.financeiro.getIntervaloNumero() == null) {
      this.financeiro.setIntervaloNumero(Integer.valueOf(0));
    }
    if (this.financeiro.getParcelaInicio() == null) {
      this.financeiro.setParcelaInicio(Integer.valueOf(0));
    }
    if (this.financeiro.getParcelaFim() == null) {
      this.financeiro.setParcelaFim(Integer.valueOf(0));
    }
    
    this.financeiro.setValor(this.saldoPC);
    
    
    this.financeiro.setOrigemLCM("6");
    this.financeiro = this.financeiroService.salvar(this.financeiro);
    
    for (FinanceiroItemPc pFinanceiroItemPc : this.listaFinanceiroItemPc) {
      System.out.println(this.financeiro.getSeqFinanceiro());
      pFinanceiroItemPc.setSeqEmpresa(this.loginController.empresa.getSeqEmpresa());
      pFinanceiroItemPc.setSeqFinanceiro(this.financeiro.getSeqFinanceiro());
      this.financeiroItemPcService.salvar(pFinanceiroItemPc);
    }
    
    if (!this.listaFinanceiroItemPcDeletado.isEmpty()) {
      for (FinanceiroItemPc deletado : this.listaFinanceiroItemPcDeletado) {
        this.financeiroItemPcService.deletar(deletado);
      }
    }
    
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro armazenado com sucesso.", ""));
  }
  
  public void novo()
  {
    this.id = false;
    this.financeiro = new Financeiro();
    
    this.documento = new Documento();
    
    this.financeiro.setDataLancamento(new Date());
    this.financeiro.setSeqUsuario(this.loginController.usuario.getSeqUsuario());
    this.financeiro.setResponsavel(this.loginController.usuario.getUsuario());
    
    this.tela = Integer.valueOf(1);
  }
  
  public void cancelar() {
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    String dataCancelamento = formato.format(new Date());
    
    String msg = "'".concat(this.financeiro.getMotivoCancelamento()).concat("'. Por:").concat(this.loginController.getUsuario().getUsuario()).concat(". Data de Cancelamento:").concat(dataCancelamento);
    this.financeiro.setMotivoCancelamento(msg);
    this.financeiro.setEtapa("CANCELADO");
    this.financeiro = this.financeiroService.salvar(this.financeiro);
  }
  
  public void mudarTela(Integer pTela)
  {
    this.financeiro = new Financeiro();
    this.tela = pTela;
  }
  

  public void selecionar(Financeiro pFinanceiro)
  {
    this.id = true;
    this.financeiro = ((Financeiro)this.financeiroService.listar(this.loginController.getUsuario().getSeqEmpresa(), pFinanceiro.getSeqFinanceiro()).get(0));
    
    this.listaFinanceiroItemPc = this.financeiroItemPcService.listar(this.loginController.empresa.getSeqEmpresa(), this.financeiro.getSeqFinanceiro());
    System.out.println(this.listaFinanceiroItemPc.size() + "-----");
    if (this.financeiro.getSeqParceiro() != null) {
      listarEquipEmbarc();
      this.listaUpload = this.uploadService.listar(this.loginController.empresa.getSeqEmpresa(),
				this.financeiro.getSeqFinanceiro());
    }
    this.tela = Integer.valueOf(1);
  }
  
  public void listarPrestacaoContas() {
    ClausulaWhere condicao = new ClausulaWhere();
    
    condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "financeiro.seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(this.loginController.getEmpresa().getSeqEmpresa()), TipoCondicaoWhere.Numero);
    condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.ORIGEM_LCM", GeneroCondicaoWhere.igual, String.valueOf("6"), TipoCondicaoWhere.Texto);
    
    if (isPorDataLancamento() == true) {
      condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.data_cadastro", GeneroCondicaoWhere.MaiorIgual, this.dataLancamentoInicial, TipoCondicaoWhere.Data);
      condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.data_cadastro", GeneroCondicaoWhere.MenorIgual, this.dataLancamentoFinal, TipoCondicaoWhere.Data);
    }
    if (isPorDataPeriodoInicial() == true) {
      condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.data_periodo_inicial", GeneroCondicaoWhere.MaiorIgual, this.dataPeriodoInicialInicial, TipoCondicaoWhere.Data);
      condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.data_periodo_inicial", GeneroCondicaoWhere.MenorIgual, this.dataPeriodoInicialFinal, TipoCondicaoWhere.Data);
    }
    
    if (isPorDataPeriodoFinal() == true) {
      condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.data_periodo_final", GeneroCondicaoWhere.MaiorIgual, this.dataPeriodoFinalInicial, TipoCondicaoWhere.Data);
      condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.data_periodo_final", GeneroCondicaoWhere.MenorIgual, this.dataPeriodoFinalFinal, TipoCondicaoWhere.Data);
    }
    
    if (this.seqParceiroSelecionado != null) {
      condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.seq_parceiro", GeneroCondicaoWhere.igual, String.valueOf(this.seqParceiroSelecionado), TipoCondicaoWhere.Texto);
    }
    if (this.seqColaboradorSelecionado != null) {
      condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.seq_colaborador", GeneroCondicaoWhere.igual, String.valueOf(this.seqColaboradorSelecionado), TipoCondicaoWhere.Numero);
    }
    
    this.listaFinanceiro = this.financeiroService.listarFiltro(condicao);
  }
  
  
   public void listarPrestacaoContasC() {
    ClausulaWhere condicao = new ClausulaWhere();
    
    
    condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "financeiro.seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(this.loginController.getEmpresa().getSeqEmpresa()), TipoCondicaoWhere.Numero);
    condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.ORIGEM_LCM", GeneroCondicaoWhere.igual, String.valueOf("6"), TipoCondicaoWhere.Texto);
    condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "financeiro.seq_usuario", GeneroCondicaoWhere.igual, String.valueOf(this.loginController.usuario.getSeqUsuario()), TipoCondicaoWhere.Texto);
  
    
    this.listaFinanceiro = this.financeiroService.listarFiltro(condicao);
  }
  

  public void iniciarPrestacaoContas()
  {
    if ((this.loginController.usuario.getAcFinPrestacaoContas() == null) || (this.loginController.usuario.getAcFinPrestacaoContas().equals("-1"))) {
      this.loginController.mudarPagina("/organizacional/acessonegado.jsf");
      return;
    }
    

    ClausulaWhere condicao = new ClausulaWhere();
    
    condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "documento.seq_tipo_documento", GeneroCondicaoWhere.igual, String.valueOf("301"), TipoCondicaoWhere.Numero);
    /*condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "documento.seq_documento_etapa", GeneroCondicaoWhere.igual, String.valueOf("282"), TipoCondicaoWhere.Numero);*/
    
    this.listaDocumento = this.documentoService.listarDocumentoFiltro(condicao);
    
    this.listaDocumentoFiscal = this.documentoFiscalService.listar(this.loginController.empresa.getSeqEmpresa(), "", Situacao.ATIVO);
    this.listaConta = this.contaService.listar(this.loginController.usuario.getSeqEmpresa(), "", Situacao.ATIVO);
    this.listaTipoLancamento = this.tipoMovimentoFinanceiroService.listar(this.loginController.empresa.getSeqEmpresa(), "", Situacao.ATIVO);
    this.listaFinanceiroCategoria = this.financeiroCategoriaService.listar(this.loginController.empresa.getSeqEmpresa(), "", Situacao.ATIVO);
    this.listaParceiro = this.parceiroService.listarParceiro(this.loginController.usuario.getSeqUsuario(), "");
    this.listaColaborador = this.colaboradorService.listar(this.loginController.empresa.getSeqEmpresa(), "", Situacao.ATIVO);
    /*this.listaUpload = this.uploadService.listar(this.loginController.empresa.getSeqEmpresa(),this.financeiro.getSeqFinanceiro());*/
  }
  
  public void listarEquipEmbarc() {
    this.listaEquipamento = this.equipamentoService.listarPorParceiro(this.loginController.getEmpresa().getSeqEmpresa(), this.financeiro.getSeqParceiro(), Situacao.ATIVO);
    this.listaNvEmbarcacaoParceiro = this.nvEmbarcacaoParceiroService.listarPorParceiro(this.financeiro.getSeqParceiro());
  }
  
    public void salvarFinanceiroItemPC() {
      
        if  (this.financeiro.getValorTotalMovimentacao() == null) {
            this.financeiro.setValorTotalMovimentacao(new BigDecimal(0));
             }
            this.financeiro.setValorTotalMovimentacao(this.financeiro.getValorTotalMovimentacao().add(this.financeiroItemPc.getValor()));
        
          
        if  (this.financeiro.getValorRecebido() !=null) {
            this.saldoPC = this.financeiro.getValorRecebido().subtract(this.financeiro.getValorTotalMovimentacao());            
        } else {
            this.saldoPC = this.financeiro.getValorTotalMovimentacao();
        }   
                 
            
            /*if (this.financeiro.getValorTotalMovimentacao() != null) {
	    this.valorTotalPC = this.financeiro.getValorTotalMovimentacao().add(this.financeiroItemPc.getValor());
	    } 
        
        if (this.financeiro.getValor() != null) {
	    this.financeiro.setValor(this.valorTotalPC.add(this.financeiroItemPc.getValor())); 
	    }      */  
       
        this.listaFinanceiroItemPc.add(this.financeiroItemPc);
        this.financeiroItemPc = new FinanceiroItemPc();
    }

  public void selecionarDocumentoFiscal() {
    for (DocumentoFiscal df : this.listaDocumentoFiscal) {
      if (df.getSeqDocumentoFiscal().equals(this.financeiroItemPc.getSeqDocumentoFiscal())) {
        this.financeiroItemPc.setNomeDocumentoFiscal(df.getNome());
      }
    }
  }
  
  public void selecionarFinanceiroItemPC(FinanceiroItemPc pFinanceiroItemPc) {
    this.financeiroItemPc = pFinanceiroItemPc;
    this.listaFinanceiroItemPc.remove(pFinanceiroItemPc);
  }
  
  public void removerFinanceiroItemPC(FinanceiroItemPc pFinanceiroItemPc) {
    this.listaFinanceiroItemPc.remove(pFinanceiroItemPc);
      
    if  (this.financeiro.getValorTotalMovimentacao() != null) {
      this.financeiro.setValorTotalMovimentacao(this.financeiro.getValorTotalMovimentacao().subtract(pFinanceiroItemPc.getValor()));
    } 
        if  (this.financeiro.getValorRecebido() !=null) {
            this.saldoPC = this.financeiro.getValorRecebido().subtract(this.financeiro.getValorTotalMovimentacao());            
        } else {
            this.saldoPC = this.financeiro.getValorTotalMovimentacao();
        }     
    
    /*else { 
        this.financeiro.setValorTotalMovimentacao(this.financeiro.getValorTotalMovimentacao().subtract(pFinanceiroItemPc.getValor()));
    }*/
    
    if (pFinanceiroItemPc.getSeqFinanceiroItemPc() != null) {
      this.listaFinanceiroItemPcDeletado.add(pFinanceiroItemPc);
    }
  }
    
    public void gerarContasPagar() {
		Financeiro pFinanceiro = new Financeiro();
		pFinanceiro.setSeqFinanceiro(null);
		pFinanceiro.setSeqParceiro(this.financeiro.getSeqParceiro());
		pFinanceiro.setSeqUsuario(this.financeiro.getSeqUsuario());
		pFinanceiro.setSeqEmpresa(this.loginController.empresa.getSeqEmpresa());
		pFinanceiro.setSeqTipoMovimento(this.financeiro.getSeqTipoMovimento());
		pFinanceiro.setSeqCentroCusto(this.financeiro.getSeqCentroCusto());
		pFinanceiro.setValor(this.financeiro.getValor());
		pFinanceiro.setValorConvertido(this.financeiro.getValorConvertido());
		pFinanceiro.setDataEmissao(new Date());
		pFinanceiro.setOrigemLCM("2");
		pFinanceiro.setOperacao("DÉBITO");
		pFinanceiro.setEtapa("A VENCER");
		pFinanceiro.setOutrasInformacoes(
				"Gerado a partir da Prestação de Contas nº" + this.financeiro.getSeqFinanceiro() + ".");
		pFinanceiro.setOcorrencias(Integer.valueOf(0));
		pFinanceiro.setIntervaloNumero(Integer.valueOf(0));
		pFinanceiro.setParcelaFim(Integer.valueOf(0));
		pFinanceiro.setParcelaInicio(Integer.valueOf(0));

		pFinanceiro = this.financeiroService.salvar(pFinanceiro);

		FacesContext ctx = FacesContext.getCurrentInstance();
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(ctx.getExternalContext().getRequestContextPath() + "/financeiro/contas_pagar.jsf?id="
							+ pFinanceiro.getSeqFinanceiro());
		} catch (IOException ex) {
			Logger.getLogger(FinanceiroController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}  
  
  
  public void imprimirPrestacaoContas() throws IOException, JRException {
    Conexao conexao = new Conexao();
    Connection conn = Conexao.getConnection();
    Util util = new Util();
    Financeiro pFinanceiro = new Financeiro();
    
    HashMap parametro = new HashMap();
    parametro.put("seqFinanceiro", Integer.valueOf(this.financeiro.getSeqFinanceiro()));
    

    FacesContext facesContext = FacesContext.getCurrentInstance();
    facesContext.responseComplete();
    ServletContext scontext = (ServletContext)facesContext.getExternalContext().getContext();
    
    JasperPrint jasperPrint = new JasperPrint();
    
    jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/relatorio/aws/PC/PC.jasper"), parametro, conn);
    
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    JRPdfExporter exporter = new JRPdfExporter();
    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
    exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
    exporter.exportReport();
    byte[] bytes = baos.toByteArray();
    
    if ((bytes != null) && (bytes.length > 0)) {
      HttpServletResponse response = (HttpServletResponse)facesContext.getExternalContext().getResponse();
      response.setContentType("application/pdf");
      response.setHeader("Content-disposition", "inline; filename=\"PrestacaoContas.pdf\"");
      response.setContentLength(bytes.length);
      ServletOutputStream outputStream = response.getOutputStream();
      outputStream.write(bytes, 0, bytes.length);
      outputStream.flush();
      outputStream.close();
    }
  }
  /*Add Roberto Souza*/
  public void calcularSaldo() {
		this.saldoPC = this.financeiro.getValorRecebido().subtract(this.financeiro.getValorTotalMovimentacao());
                  
  }  
  
  /*Add Roberto Souza*/
  
      public BigDecimal getItnTotal() {
        if (ItnTotal == null) {
            ItnTotal = this.financeiroItemPc.getValor().add(this.financeiroItemPc.getValor());
        }
        return ItnTotal;
    }
  
  
  
  
  /*add Roberto Souza*/              
        public void upload() {
		String id1 = this.financeiro.getSeqColaborador();
                this.upload.setSeqFinanceiro(this.financeiro.getSeqFinanceiro());
		this.upload.setOrigem("PREST" + "-" + id1 + "-" + this.financeiro.getSeqFinanceiro());
		this.upload.setSeqEmpresa(this.loginController.empresa.getSeqEmpresa());
		this.upload.setSeqUsuario(this.loginController.usuario.getSeqUsuario());
		this.uploadController.upload(this.file, this.upload);
                this.listaUpload = this.uploadService.listar(this.loginController.empresa.getSeqEmpresa(),this.financeiro.getSeqFinanceiro());
		this.upload = new Upload();
                /*this.idFinanceiro = this.financeiro.getSeqFinanceiro();*/
                
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
		this.listaUpload = this.uploadService.listar(this.loginController.empresa.getSeqEmpresa(),this.financeiro.getSeqFinanceiro());
	}       
  

  public LoginController getLoginController()
  {
    return this.loginController;
  }
  
  public void setLoginController(LoginController loginController) {
    this.loginController = loginController;
  }
  
  public List<Financeiro> getListaFinanceiro() {
    return this.listaFinanceiro;
  }
  
  public void setListaFinanceiro(List<Financeiro> listaFinanceiro) {
    this.listaFinanceiro = listaFinanceiro;
  }
  
  public FinanceiroService getFinanceiroService() {
    return this.financeiroService;
  }
  
  public void setFinanceiroService(FinanceiroService financeiroService) {
    this.financeiroService = financeiroService;
  }
  
  public Financeiro getFinanceiro() {
    return this.financeiro;
  }
  
  public void setFinanceiro(Financeiro financeiro) {
    this.financeiro = financeiro;
  }
  
  public Documento getDocumento() {
    return this.documento;
  }
  
  public void setDocumento(Documento documento) {
    this.documento = documento;
  }
  
  public List<Documento> getListaDocumento() {
    return this.listaDocumento;
  }
  
  public void setListaDocumento(List<Documento> listaDocumento) {
    this.listaDocumento = listaDocumento;
  }
  
  public DocumentoService getDocumentoService() {
    return this.documentoService;
  }
  
  public void setDocumentoService(DocumentoService documentoService) {
    this.documentoService = documentoService;
  }
  
  public List<Conta> getListaConta() {
    return this.listaConta;
  }
  
  public void setListaConta(List<Conta> listaConta) {
    this.listaConta = listaConta;
  }
  
  public ContaService getContaService() {
    return this.contaService;
  }
  
  public void setContaService(ContaService contaService) {
    this.contaService = contaService;
  }
  
  public List<Parceiro> getListaParceiro() {
    return this.listaParceiro;
  }
  
  public void setListaParceiro(List<Parceiro> listaParceiro) {
    this.listaParceiro = listaParceiro;
  }
  
  public ParceiroService getParceiroService() {
    return this.parceiroService;
  }
  
  public void setParceiroService(ParceiroService parceiroService) {
    this.parceiroService = parceiroService;
  }
  
  public List<Colaborador> getListaColaborador() {
    return this.listaColaborador;
  }
  
  public void setListaColaborador(List<Colaborador> listaColaborador) {
    this.listaColaborador = listaColaborador;
  }
  
  public ColaboradorService getColaboradorService() {
    return this.colaboradorService;
  }
  
  public void setColaboradorService(ColaboradorService colaboradorService) {
    this.colaboradorService = colaboradorService;
  }
  
  public List<Material> getListaMaterial() {
    return this.listaMaterial;
  }
  
  public void setListaMaterial(List<Material> listaMaterial) {
    this.listaMaterial = listaMaterial;
  }
  
  public MaterialService getMaterialService() {
    return this.materialService;
  }
  
  public void setMaterialService(MaterialService materialService) {
    this.materialService = materialService;
  }
  
  public boolean isPorDataLancamento() {
    return this.porDataLancamento;
  }
  
  public void setPorDataLancamento(boolean porDataLancamento) {
    this.porDataLancamento = porDataLancamento;
  }
  
  public String getDataLancamentoInicial() {
    return this.dataLancamentoInicial;
  }
  
  public void setDataLancamentoInicial(String dataLancamentoInicial) {
    this.dataLancamentoInicial = dataLancamentoInicial;
  }
  
  public String getDataLancamentoFinal() {
    return this.dataLancamentoFinal;
  }
  
  public void setDataLancamentoFinal(String dataLancamentoFinal) {
    this.dataLancamentoFinal = dataLancamentoFinal;
  }
  
  public boolean isPorDataVencimento() {
    return this.porDataVencimento;
  }
  
  public void setPorDataVencimento(boolean porDataVencimento) {
    this.porDataVencimento = porDataVencimento;
  }
  
  public String getDataVencimentoInicial() {
    return this.dataVencimentoInicial;
  }
  
  public void setDataVencimentoInicial(String dataVencimentoInicial) {
    this.dataVencimentoInicial = dataVencimentoInicial;
  }
  
  public String getDataVencimentoFinal() {
    return this.dataVencimentoFinal;
  }
  
  public void setDataVencimentoFinal(String dataVencimentoFinal) {
    this.dataVencimentoFinal = dataVencimentoFinal;
  }
  
  public boolean isPorDataPeriodoInicial() {
    return this.porDataPeriodoInicial;
  }
  
  public void setPorDataPeriodoInicial(boolean porDataPeriodoInicial) {
    this.porDataPeriodoInicial = porDataPeriodoInicial;
  }
  
  public String getDataPeriodoInicialInicial() {
    return this.dataPeriodoInicialInicial;
  }
  
  public void setDataPeriodoInicialInicial(String dataPeriodoInicialInicial) {
    this.dataPeriodoInicialInicial = dataPeriodoInicialInicial;
  }
  
  public String getDataPeriodoInicialFinal() {
    return this.dataPeriodoInicialFinal;
  }
  
  public void setDataPeriodoInicialFinal(String dataPeriodoInicialFinal) {
    this.dataPeriodoInicialFinal = dataPeriodoInicialFinal;
  }
  
  public boolean isPorDataPeriodoFinal() {
    return this.porDataPeriodoFinal;
  }
  
  public void setPorDataPeriodoFinal(boolean porDataPeriodoFinal) {
    this.porDataPeriodoFinal = porDataPeriodoFinal;
  }
  
  public String getDataPeriodoFinalInicial() {
    return this.dataPeriodoFinalInicial;
  }
  
  public void setDataPeriodoFinalInicial(String dataPeriodoFinalInicial) {
    this.dataPeriodoFinalInicial = dataPeriodoFinalInicial;
  }
  
  public String getDataPeriodoFinalFinal() {
    return this.dataPeriodoFinalFinal;
  }
  
  public void setDataPeriodoFinalFinal(String dataPeriodoFinalFinal) {
    this.dataPeriodoFinalFinal = dataPeriodoFinalFinal;
  }
  
  public String getSeqParceiroSelecionado() {
    return this.seqParceiroSelecionado;
  }
  
  public void setSeqParceiroSelecionado(String seqParceiroSelecionado) {
    this.seqParceiroSelecionado = seqParceiroSelecionado;
  }
  
  public String getSeqColaboradorSelecionado() {
    return this.seqColaboradorSelecionado;
  }
  
  public void setSeqColaboradorSelecionado(String seqColaboradorSelecionado) {
    this.seqColaboradorSelecionado = seqColaboradorSelecionado;
  }
  
  public boolean isId() {
    return this.id;
  }
  
  public void setId(boolean id) {
    this.id = id;
  }
  
  public Integer getTela() {
    return this.tela;
  }
  
  public void setTela(Integer tela) {
    this.tela = tela;
  }
  
  public List<DocumentoFiscal> getListaDocumentoFiscal() {
    return this.listaDocumentoFiscal;
  }
  
  public void setListaDocumentoFiscal(List<DocumentoFiscal> listaDocumentoFiscal) {
    this.listaDocumentoFiscal = listaDocumentoFiscal;
  }
  
  public DocumentoFiscalService getDocumentoFiscalService() {
    return this.documentoFiscalService;
  }
  
  public void setDocumentoFiscalService(DocumentoFiscalService documentoFiscalService) {
    this.documentoFiscalService = documentoFiscalService;
  }
  
  public List<TipoMovimentoFinanceiro> getListaTipoLancamento() {
    return this.listaTipoLancamento;
  }
  
  public void setListaTipoLancamento(List<TipoMovimentoFinanceiro> listaTipoLancamento) {
    this.listaTipoLancamento = listaTipoLancamento;
  }
  
  public TipoMovimentoFinanceiroService getTipoMovimentoFinanceiroService() {
    return this.tipoMovimentoFinanceiroService;
  }
  
  public void setTipoMovimentoFinanceiroService(TipoMovimentoFinanceiroService tipoMovimentoFinanceiroService) {
    this.tipoMovimentoFinanceiroService = tipoMovimentoFinanceiroService;
  }
  
  public List<FinanceiroCategoria> getListaFinanceiroCategoria() {
    return this.listaFinanceiroCategoria;
  }
  
  public void setListaFinanceiroCategoria(List<FinanceiroCategoria> listaFinanceiroCategoria) {
    this.listaFinanceiroCategoria = listaFinanceiroCategoria;
  }
  
  public FinanceiroCategoriaService getFinanceiroCategoriaService() {
    return this.financeiroCategoriaService;
  }
  
  public void setFinanceiroCategoriaService(FinanceiroCategoriaService financeiroCategoriaService) {
    this.financeiroCategoriaService = financeiroCategoriaService;
  }
  
  public FinanceiroItemPc getFinanceiroItemPc() {
    return this.financeiroItemPc;
  }
  
  public void setFinanceiroItemPc(FinanceiroItemPc financeiroItemPc) {
    this.financeiroItemPc = financeiroItemPc;
  }
  
  public List<FinanceiroItemPc> getListaFinanceiroItemPc() {
    return this.listaFinanceiroItemPc;
  }
  
  public void setListaFinanceiroItemPc(List<FinanceiroItemPc> listaFinanceiroItemPc) {
    this.listaFinanceiroItemPc = listaFinanceiroItemPc;
  }
  
  public List<FinanceiroItemPc> getListaFinanceiroItemPcDeletado() {
    return this.listaFinanceiroItemPcDeletado;
  }
  
  public void setListaFinanceiroItemPcDeletado(List<FinanceiroItemPc> listaFinanceiroItemPcDeletado) {
    this.listaFinanceiroItemPcDeletado = listaFinanceiroItemPcDeletado;
  }
  
  public FinanceiroItemPcService getFinanceiroItemPcService() {
    return this.financeiroItemPcService;
  }
  
  public void setFinanceiroItemPcService(FinanceiroItemPcService financeiroItemPcService) {
    this.financeiroItemPcService = financeiroItemPcService;
  }
  
  public List<Equipamento> getListaEquipamento() {
    return this.listaEquipamento;
  }
  
  public void setListaEquipamento(List<Equipamento> listaEquipamento) {
    this.listaEquipamento = listaEquipamento;
  }
  
  public EquipamentoService getEquipamentoService() {
    return this.equipamentoService;
  }
  
  public void setEquipamentoService(EquipamentoService equipamentoService) {
    this.equipamentoService = equipamentoService;
  }
  
  public List<NvEmbarcacaoParceiro> getListaNvEmbarcacaoParceiro() {
    return this.listaNvEmbarcacaoParceiro;
  }
  
  public void setListaNvEmbarcacaoParceiro(List<NvEmbarcacaoParceiro> listaNvEmbarcacaoParceiro) {
    this.listaNvEmbarcacaoParceiro = listaNvEmbarcacaoParceiro;
  }
  
  public NvEmbarcacaoParceiroService getNvEmbarcacaoParceiroService() {
    return this.nvEmbarcacaoParceiroService;
  }
  
  public void setNvEmbarcacaoParceiroService(NvEmbarcacaoParceiroService nvEmbarcacaoParceiroService) {
    this.nvEmbarcacaoParceiroService = nvEmbarcacaoParceiroService;
  }
   
	public UploadedFile getFile() {
		return this.file;
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
        
	public BigDecimal getValorTotalPC() {
		return this.valorTotalPC;
	}

	public void setValorTotalPC(BigDecimal valorTotalPC) {
		this.valorTotalPC = valorTotalPC;
	}

	public BigDecimal getSaldoPC() {
		return this.saldoPC;
	}

	public void setSaldoPC(BigDecimal saldoPC) {
		this.saldoPC = saldoPC;
	}        

}
