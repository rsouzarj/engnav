package Controller;

import UnidadeNegocio.UnidadeNegocio;
import UnidadeNegocio.UnidadeNegocioService;
import Anotacao.Anotacao;
import Anotacao.AnotacaoService;
import ClausulaSQL.ClausulaWhere;
import ClausulaSQL.GeneroCondicaoWhere;
import ClausulaSQL.OperacaoCondicaoWhere;
import ClausulaSQL.TipoCondicaoWhere;
import Documento.Documento;
import Documento.DocumentoService;
import DocumentoItemMaterial.DocumentoItemMaterial;
import DocumentoItemMaterial.DocumentoItemMaterialService;
import Empresa.Empresa;
import Parceiro.Parceiro;
import Parceiro.ParceiroService;
import ParceiroCaracteristica.ParceiroCaracteristica;
import ParceiroCaracteristica.ParceiroCaracteristicaService;
import ParceiroContato.ParceiroContato;
import ParceiroContato.ParceiroContatoService;
import ParceiroEndereco.ParceiroEndereco;
import ParceiroEndereco.ParceiroEnderecoService;
import ParceiroVinculo.ParceiroVinculo;
import ParceiroVinculo.ParceiroVinculoService;
import TabelaPreco.TabelaPreco;
import TabelaPreco.TabelaPrecoService;
import TipoAnotacao.TipoAnotacao;
import TipoAnotacao.TipoAnotacaoService;
import TipoCaracteristica.TipoCaracteristica;
import TipoCaracteristica.TipoCaracteristicaService;
import TipoDocumento.TipoDocumento;
import TipoDocumento.TipoDocumentoService;
import TipoEndereco.TipoEndereco;
import TipoEndereco.TipoEnderecoService;
import TipoParceiro.TipoParceiro;
import TipoParceiro.TipoParceiroService;
import TipoVinculo.TipoVinculo;
import TipoVinculo.TipoVinculoService;
import GrupoEmpresarial.GrupoEmpresarial;
import GrupoEmpresarial.GrupoEmpresarialService;        
import Uf.Uf;
import Uf.UfService;
import Upload.Upload;
import Upload.UploadService;
import Usuario.Usuario;
import Util.Cep;
import Util.Conexao;
import Util.Retorno;
import Util.Situacao;
import Util.Util;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.Connection;
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


@ManagedBean(name="parceiroController")
@ViewScoped
public class ParceiroController
{
  @ManagedProperty("#{loginController}")
  protected LoginController loginController;
  ParceiroService parceiroService = new ParceiroService();
  Parceiro parceiro = new Parceiro();
  List<Parceiro> listaParceiro = new ArrayList();
  String pesquisa;
  Long seqParceiroSelecionado;
  Integer tela = Integer.valueOf(0);
  
  List<Uf> listaUf = new ArrayList();
  boolean bCaractreistica = false;
  String id = "";
  String tituloPagina;
  List<TipoParceiro> listaTipoParceiro = new ArrayList();
  
  ParceiroVinculoService ParceiroVinculoService = new ParceiroVinculoService();
  List<ParceiroVinculo> listaParceiroVinculo = new ArrayList();
  ParceiroVinculo parceiroVinculo = new ParceiroVinculo();
  List<TipoVinculo> listaTipoVinculo = new ArrayList();
  
  TipoAnotacaoService tipoAnotacaoService = new TipoAnotacaoService();
  List<TipoAnotacao> listaTipoAnotacao = new ArrayList();
  
  AnotacaoService anotacaoService = new AnotacaoService();
  List<Anotacao> listaAnotacao = new ArrayList();
  Anotacao anotacao = new Anotacao();
  
  DocumentoService processoService = new DocumentoService();
  Documento processo = new Documento();
  List<Documento> listaDocumento = new ArrayList();
  
  DocumentoItemMaterialService processoItemMaterialService = new DocumentoItemMaterialService();
  DocumentoItemMaterial processoItemMaterial = new DocumentoItemMaterial();
  List<DocumentoItemMaterial> listaDocumentoItemMaterial = new ArrayList();
  
  TipoDocumentoService tipoDocumentoService = new TipoDocumentoService();
  TipoDocumento tipoDocumento = new TipoDocumento();
  List<TipoDocumento> listaTipoDocumento = new ArrayList();
  
  TabelaPrecoService tabelaPrecoService = new TabelaPrecoService();
  List<TabelaPreco> listaTabelaPreco = new ArrayList();
  
  List<Usuario> listaUsuario = new ArrayList();
  
  TipoCaracteristicaService tipoCaracteristicaService = new TipoCaracteristicaService();
  TipoCaracteristica tipoCaracteristica = new TipoCaracteristica();
  List<TipoCaracteristica> listaTipoCaracteristica = new ArrayList();
  
  GrupoEmpresarialService grupoEmpresarialService = new GrupoEmpresarialService();
  GrupoEmpresarial grupoEmpresarial = new GrupoEmpresarial();
  List<GrupoEmpresarial> listaGrupoEmpresarial = new ArrayList();
  
  ParceiroCaracteristicaService parceiroCaracteristicaService = new ParceiroCaracteristicaService();
  ParceiroCaracteristica parceiroCaracteristica = new ParceiroCaracteristica();
  List<ParceiroCaracteristica> listaParceiroCaracteristica = new ArrayList();
  List<String> caracteristicasSelecionada = new ArrayList();
  
  ParceiroEnderecoService parceiroEnderecoService = new ParceiroEnderecoService();
  ParceiroEndereco parceiroEndereco = new ParceiroEndereco();
  List<ParceiroEndereco> listaParceiroEndereco = new ArrayList();
  
  TipoEnderecoService tipoEnderecoService = new TipoEnderecoService();
  TipoEndereco tipoEndereco = new TipoEndereco();
  List<TipoEndereco> listaTipoEndereco = new ArrayList();
  
  ParceiroContatoService parceiroContatoService = new ParceiroContatoService();
  ParceiroContato parceiroContato = new ParceiroContato();
  List<ParceiroContato> listaParceiroContato = new ArrayList();
  
  List<Parceiro> listaParceiroVinculado = new ArrayList();
  
  String seqParceiroVinculado;
  TipoParceiroService tipoParceiroService = new TipoParceiroService();
  
  UnidadeNegocio unidadeNegocio = new UnidadeNegocio();
  UnidadeNegocioService unidadeNegocioService = new UnidadeNegocioService();
  List<UnidadeNegocio> listaUnidadeNegocio = new ArrayList();
  String seqUnidadeNegocioSelecionado = "";  
  
  Upload upload = new Upload();
  UploadService uploadService = new UploadService();
  UploadController uploadController = new UploadController();
  List<Upload> listaUpload = new ArrayList();  
        
  UploadedFile file;
  StreamedContent fileDownload;    
  

  public void iniciar()
  {
    this.listaTipoParceiro = this.tipoParceiroService.listar(this.loginController.getEmpresa().getSeqEmpresa(), Situacao.ATIVO);
    this.listaGrupoEmpresarial = this.grupoEmpresarialService.listarP(this.loginController.getEmpresa().getSeqEmpresa(), Situacao.ATIVO);
    this.listaParceiro = this.parceiroService.listarTudo(this.loginController.getUsuario().getSeqUsuario());
    if (this.id.equals(""))
    {
      if ((this.loginController.usuario.getAcParTodos() == null) || (this.loginController.usuario.getAcParTodos().equals("-1"))) {
        this.loginController.mudarPagina("/organizacional/acessonegado.jsf");
        return;
      }
      this.tela = Integer.valueOf(0);
    } else if (this.id.equals("-1"))
    {
      if ((this.loginController.usuario.getAcParNovo() == null) || (this.loginController.usuario.getAcParNovo().equals("-1"))) {
        this.loginController.mudarPagina("/organizacional/acessonegado.jsf");
        return;
      }
      this.parceiro = new Parceiro();
      this.parceiro.setTipo("Pessoa Jurídica");
      this.tela = Integer.valueOf(1);
    } else {
      this.parceiro = this.parceiroService.buscarPorSeqParceiro(this.id);
      TipoVinculoService tipoVinculoService = new TipoVinculoService();
      this.tela = Integer.valueOf(1);
    }
    



    TipoVinculoService tipoVinculoService = new TipoVinculoService();
    this.listaTipoVinculo = tipoVinculoService.listar(this.loginController.getEmpresa().getSeqEmpresa(), "", Situacao.ATIVO);
    this.listaParceiroVinculado = this.parceiroService.listarParceiro(this.loginController.usuario.getSeqUsuario(), "");
    
    this.listaTipoAnotacao = this.tipoAnotacaoService.listar(this.loginController.getEmpresa().getSeqEmpresa(), "", Situacao.ATIVO);
    
    UfService ufService = new UfService();
    this.listaUf = ufService.listar();
    
    TipoParceiroService tipoParceiroService = new TipoParceiroService();
    
    this.listaTipoParceiro = tipoParceiroService.listar(this.loginController.getEmpresa().getSeqEmpresa(), Situacao.ATIVO);
    this.listaTipoCaracteristica = this.tipoCaracteristicaService.listar(this.loginController.getEmpresa().getSeqEmpresa(), "", Situacao.ATIVO);
    this.listaTipoEndereco = this.tipoEnderecoService.listar(this.loginController.getEmpresa().getSeqEmpresa(), "", Situacao.ATIVO);
    this.listaUnidadeNegocio = this.unidadeNegocioService.listar(this.loginController.empresa.getSeqEmpresa(), "", Situacao.ATIVO);    
    this.listaTabelaPreco = this.tabelaPrecoService.listar(this.loginController.getUsuario().getSeqEmpresa(), "", Situacao.ATIVO);
    System.out.println("ID: " + this.id);
  }


  public void buscarDadosReceitaFederal() throws IOException
  {
    if (this.parceiro.getDocumento().length() == 18) {
      Util util = new Util();
      this.parceiro = util.buscarDadosReceitaFederal(this.parceiro);
    }
  }
  
  public void salvar(int pTela) {
    if (this.parceiro.getNome().isEmpty()) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "[NOME] é preenchimento obrigatório", ""));
      return;
    }
    
    TipoParceiro tipoParceiro = new TipoParceiro();
    TipoParceiroService tipoParceiroService = new TipoParceiroService();
    tipoParceiro = tipoParceiroService.buscarPorId(this.parceiro.getSeqTipoParceiro(), this.loginController.getEmpresa().getSeqEmpresa());
    
    boolean vValidacaoDocumento = true;
    String msgValidacao = null;
    if (tipoParceiro.getCpfCnpjObrigatorio().equals("Sim")) {
      if (this.parceiro.getTipo().equals("Pessoa Física")) {
        vValidacaoDocumento = Util.validarCPF(this.parceiro.getDocumento());
        msgValidacao = "[CPF] inválido";
      } else if (this.parceiro.getTipo().equals("Pessoa Jurídica")) {
        vValidacaoDocumento = Util.validarCNPJ(this.parceiro.getDocumento());
        msgValidacao = "[CNPJ] inválido";
      }
    }
    
    if (!vValidacaoDocumento) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msgValidacao, ""));
      return;
    }
    
    this.parceiro.setSeqEmpresa(this.loginController.getEmpresa().getSeqEmpresa());
    
    this.parceiro.setSeqParceiroInclusao(this.loginController.getUsuario().getSeqParceiro());
    for (ParceiroCaracteristica detalhe : this.listaParceiroCaracteristica) {
	detalhe.setSeqParceiro(this.parceiro.getSeqParceiro());
        detalhe = this.parceiroCaracteristicaService.salvar(detalhe);
    }    

    if (this.loginController.getUsuario().getChaveOrigem() != null) {
      this.parceiro.setSeqUsuario(String.valueOf(this.loginController.getUsuario().getChaveOrigem()));
    } else {
      this.parceiro.setSeqUsuario("");
    }
    
    System.out.println("seqUsuario1:  " + this.loginController.getUsuario().getChaveOrigem());
    System.out.println("Parceiro: " + this.parceiro.getNome());
    System.out.println("Seq usuario: " + this.parceiro.getSeqUsuario());
    System.out.println("Seq Tabela Preço: " + this.parceiro.getSeqTabelaPreco());
    

    if ((this.parceiroService.buscarDocumentoParceiro(this.parceiro.getDocumento())) && (this.parceiro.getSeqParceiro() == null)) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "[CNPJ] Já cadastrado.", ""));
      return;
    }
    
    Retorno retorno = this.parceiroService.salvar(this.parceiro);
    this.parceiro = ((Parceiro)retorno.getClasse());
    

    /*this.parceiroCaracteristicaService.deletar(this.parceiro.getSeqParceiro());
      
    for (String s : this.caracteristicasSelecionada) {
      this.parceiroCaracteristica.setSeqParceiro(this.parceiro.getSeqParceiro());
      this.parceiroCaracteristica.setSeqTipoCaracteristica(s);
      this.parceiroCaracteristicaService.salvar(this.parceiroCaracteristica);
    }*/
    listarContato();
    
    this.tela = Integer.valueOf(1);
    
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, retorno.getMsg(), ""));
  }
  
  public void novo()
  {
    this.parceiro = new Parceiro();
    this.tela = Integer.valueOf(1);
    this.listaTipoParceiro = this.tipoParceiroService.listar(this.loginController.getEmpresa().getSeqEmpresa(), Situacao.ATIVO);
    this.parceiro.setTipo("Pessoa Jurídica");
    this.tela = Integer.valueOf(1);
  }
  
  public void listar() {
    this.listaParceiro = this.parceiroService.listarParceiro(this.loginController.getUsuario().getSeqUsuario(), this.pesquisa);
  }
  
  public void filtrar() {
    boolean executar = true;
      ClausulaWhere condicao = new ClausulaWhere();
    condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio,"parceiro.seq_empresa", GeneroCondicaoWhere.igual,String.valueOf(this.loginController.getEmpresa().getSeqEmpresa()), TipoCondicaoWhere.Numero);
    
     if  (this.seqParceiroSelecionado != null) {
			condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "parceiro.seq_parceiro",
					GeneroCondicaoWhere.igual, String.valueOf(this.seqParceiroSelecionado), TipoCondicaoWhere.Numero);
                                                }
    if (executar) {
         this.listaParceiro = this.parceiroService.listar(condicao);                 
         			} }
   
  
  
  public void deletar() {
    Retorno retorno = this.parceiroService.deletar(this.parceiro.getSeqEmpresa(), this.parceiro.getCodigo());
    if (retorno.isTransacaoOK() == true) {
      listar();
      this.parceiro = new Parceiro();
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, retorno.getMsg(), ""));
      this.tela = Integer.valueOf(0);
      listar();
    } else {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, retorno.getMsg(), ""));
    }
  }
  
  public void buscarCep(int pDestino) {
    if (pDestino == 0) {
      Cep cep = Cep.buscarCep(this.parceiro.getCep().replace("-", ""));
      this.parceiro.setLogradouro(cep.getLogradouroFull());
      this.parceiro.setBairro(cep.getBairro());
      this.parceiro.setCidade(cep.getCidade());
      this.parceiro.setUf(cep.getUf());
    } else if (pDestino == 1) {
      Cep cep = Cep.buscarCep(this.parceiroEndereco.getCep().replace("-", ""));
      this.parceiroEndereco.setLogradouro(cep.getLogradouroFull());
      this.parceiroEndereco.setBairro(cep.getBairro());
      this.parceiroEndereco.setCidade(cep.getCidade());
      this.parceiroEndereco.setUf(cep.getUf());
    }
  }
  
  public void adicionarVinculo() {
    this.parceiroVinculo.setDataCadastro(new Date());
    this.parceiroVinculo.setSeqParceiro(this.parceiro.getSeqParceiro());
    System.out.println(this.seqParceiroVinculado);
    this.parceiroVinculo.setSeqParceiroVinculado(this.seqParceiroVinculado);
    
    this.ParceiroVinculoService.salvar(this.parceiroVinculo);
    
    this.listaParceiroVinculo = this.ParceiroVinculoService.listar(this.parceiro.getSeqParceiro());
    this.parceiroVinculo = new ParceiroVinculo();
  }
  
  public void removerVinculo(ParceiroVinculo pParceiroVinculo)
  {
    System.out.println("id: " + this.parceiroVinculo.getSeqParceiroVinculo());
    this.ParceiroVinculoService.deletar(pParceiroVinculo);
    this.listaParceiroVinculo = this.ParceiroVinculoService.listar(this.parceiro.getSeqParceiro());
  }
  
  public void adicionarAnotacao() {
    this.anotacao.setDataCadastro(new Date());
    this.anotacao.setSeqParceiro(this.parceiro.getSeqParceiro());
    this.anotacao.setSeqEmpresa(this.loginController.getEmpresa().getSeqEmpresa());
    this.anotacao.setSeqTipoAnotacao("181");
    this.anotacaoService.salvar(this.anotacao);
    this.listaAnotacao = this.anotacaoService.listarPorParceiro(this.parceiro.getSeqParceiro());
    this.anotacao = new Anotacao();
  }
  
  public void removerAnotacao(Anotacao pAnotacao) {
    this.anotacaoService.deletar(pAnotacao);
    this.listaAnotacao = this.anotacaoService.listarPorParceiro(this.parceiro.getSeqParceiro());
  }
  
  public void selecionarAnotacao(Anotacao pAnotacao) {
    this.anotacao = pAnotacao;
  }
  
  public void novaAnotacao() {
    this.anotacao = new Anotacao();
  }
  
  public void novoDocumento()
  {
    this.processo = new Documento();
  }
  
  public void listarDocumento() {
    this.listaDocumento = this.processoService.listarPorParceiro(this.loginController.getUsuario().getSeqEmpresa(), this.parceiro.getSeqParceiro());
  }
  
          public void listarUpload()  {
                this.listaUpload = this.uploadService.listar(this.loginController.empresa.getSeqEmpresa(),
				this.parceiro.getSeqParceiro());
		
        }
  public void limparCaracteristica() {
    this.parceiro.getListaParceiroCaracteristica().clear();
  }        
  
  public void selecionarDocumento(Documento pDocumento) {
    this.processo = pDocumento;
  }
  
  public void salvarDocumento() {
    this.processo.setSeqParceiro(this.parceiro.getSeqParceiro());
    this.processo.setSeqEmpresa(this.loginController.getEmpresa().getSeqEmpresa());
    this.processo.setSeqUsuarioCadastro(this.loginController.getUsuario().getSeqUsuario());
    this.processoService.salvar(this.processo);
    listarDocumento();
    novoDocumento();
  }
  
  public void removerDocumento(Documento pDocumento) {
    this.processoService.deletar(pDocumento);
    listarDocumento();
  }
  

  public void selecionarEndereco(ParceiroEndereco pParceiroEndereco)
  {
    this.parceiroEndereco = pParceiroEndereco;
  }
  
  public void novoEndereco() {
    this.parceiroEndereco = new ParceiroEndereco();
  }
  
  public void listarEndereco() {
    this.listaParceiroEndereco = this.parceiroEnderecoService.listar(this.parceiro.getSeqParceiro(), Situacao.ATIVO);
  }
  
  
    public void listarCaracteristica() {
    this.listaParceiroCaracteristica = this.parceiroCaracteristicaService.listarPorParceiro(this.parceiro.getSeqParceiro());
  }
  
  public void salvarEndereco() {
    this.parceiroEndereco.setSeqParceiro(this.parceiro.getSeqParceiro());
    this.parceiroEndereco = this.parceiroEnderecoService.salvar(this.parceiroEndereco);
    listarEndereco();
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Endereço armazenado com sucesso!.", ""));
  }
  
  
  public void deletarEndereco()
  {
    this.parceiroEnderecoService.deletar(this.parceiroEndereco);
    listarEndereco();
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Endereço eliminado com sucesso!.", ""));
  }
  
  public void selecionarContato(ParceiroContato pParceiroContato)
  {
    this.parceiroContato = pParceiroContato;
  }
  
  public void novoContato() {
    this.parceiroContato = new ParceiroContato();
  }
  
  public void listarContato() {
    this.listaParceiroContato = this.parceiroContatoService.listarPorParceiro(this.parceiro.getSeqParceiro(), Situacao.TODOS);
  }
  
  public void salvarContato() {
    this.parceiroContato.setSeqParceiro(this.parceiro.getSeqParceiro());
    this.parceiroContato.setSeqEmpresa(this.loginController.getEmpresa().getSeqEmpresa());
    this.parceiroContato = this.parceiroContatoService.salvar(this.parceiroContato);
    listarContato();
    novoContato();
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Contato armazenado com sucesso!.", ""));
  }
  
  public void deletarContato()
  {
    this.parceiroContatoService.deletar(this.parceiroContato);
    listarContato();
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Contato eliminado com sucesso!.", ""));
  }
  
  public void fecharTela() throws IOException
  {
    this.loginController.mudarPagina("/principal/ principal.jsf");
  }
  
  public void selecionarRelatorio(Parceiro pParceiro) {
    this.loginController.mudarPagina("/parceiro/parceiro.jsf");
    mudarTela(Integer.valueOf(1));
    selecionar(pParceiro);
  }
  

  public void selecionar(Parceiro pParceiro)
  {
    this.parceiro = this.parceiroService.buscar(this.loginController.getUsuario().getSeqEmpresa(), pParceiro.getSeqParceiro());
    
    this.listaParceiroVinculo = this.ParceiroVinculoService.listar(this.parceiro.getSeqParceiro());
    this.listaAnotacao = this.anotacaoService.listarPorParceiro(this.parceiro.getSeqParceiro());
    this.listaDocumento = this.processoService.listarPorParceiro(this.loginController.getUsuario().getSeqEmpresa(), this.parceiro.getSeqParceiro());
    this.listaTipoDocumento = this.tipoDocumentoService.listar(this.loginController.getEmpresa().getSeqEmpresa(), "", Situacao.ATIVO, this.loginController.getUsuario().getSeqUsuario());
    this.listaTipoParceiro = this.tipoParceiroService.listar(this.loginController.getEmpresa().getSeqEmpresa(), Situacao.ATIVO);
    
    
    listarEndereco();
    listarContato();
    listarUpload();
    listarCaracteristica();
    
    /*this.parceiro.getListaParceiroCaracteristica().clear();
    for (int i = 0; i < this.listaParceiroCaracteristica.size(); i++) {
      this.caracteristicasSelecionada.add(String.valueOf(((ParceiroCaracteristica)this.listaParceiroCaracteristica.get(i)).getSeqTipoCaracteristica()));
    }*/

    this.tela = 1;
  }
  
 /* public void listarCaracteristicaSelecionada()
  {
    for (String s : this.caracteristicasSelecionada) {
      System.out.println(s);
    }
  }*/
  
  public void mudarTela(Integer pTela)
  {
        
    this.tela = pTela;
  }
  



  public List<Parceiro> listarParceiroAutoComplete(String query)
  {
    this.listaParceiroVinculado = this.parceiroService.listarParceiro(this.loginController.usuario.getSeqUsuario(), "");
    
    List<Parceiro> parceirosFiltrados = new ArrayList();
    
    for (int i = 0; i < this.listaParceiroVinculado.size(); i++) {
      Parceiro parceiroV = (Parceiro)this.listaParceiroVinculado.get(i);
      if (parceiroV.getNome().toLowerCase().startsWith(query)) {
        parceirosFiltrados.add(parceiroV);
      }
    }
    
    return parceirosFiltrados;
  }
  

	public void upload() {
		this.upload.setSeqParceiro(this.parceiro.getSeqParceiro());
                this.upload.setOrigem("PARCEIRO" + "-" + this.parceiro.getNome());
		this.upload.setSeqEmpresa(this.loginController.empresa.getSeqEmpresa());
		this.upload.setSeqUsuario(this.loginController.usuario.getSeqUsuario());
		this.uploadController.upload(this.file, this.upload);
		this.listaUpload = this.uploadService.listar(this.loginController.empresa.getSeqEmpresa(),
				this.parceiro.getSeqParceiro());
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
				this.parceiro.getSeqParceiro());
	}
        
        
        public void imprimir() throws IOException, JRException {

        HashMap parametro = new HashMap();
        Conexao conexao = new Conexao();
        Connection conn = Conexao.getConnection();

        String caminho = "/relatorio/PARCEIRO/parceiro.jasper";
        parametro.put("pSeqParceiro", Integer.valueOf(this.parceiro.getSeqParceiro()));
        
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
                    "inline; filename=\"parceiro.pdf\"");
            responseeqp.setContentLength(bytes.length);
            ServletOutputStream outputStream = responseeqp.getOutputStream();
            outputStream.write(bytes, 0, bytes.length);
            outputStream.flush();
            outputStream.close();
        }
    }        
  
  
  public LoginController getLoginController()
  {
    return this.loginController;
  }
  
  public void setLoginController(LoginController loginController) {
    this.loginController = loginController;
  }
  
  public ParceiroService getParceiroService() {
    return this.parceiroService;
  }
  
  public void setParceiroService(ParceiroService parceiroService) {
    this.parceiroService = parceiroService;
  }
  
  public Parceiro getParceiro() {
    return this.parceiro;
  }
  
  public void setParceiro(Parceiro parceiro) {
    this.parceiro = parceiro;
  }
  
  public List<Parceiro> getListaParceiro() {
    return this.listaParceiro;
  }
  
  public void setListaParceiro(List<Parceiro> listaParceiro) {
    this.listaParceiro = listaParceiro;
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
  
  public String getId() {
    return this.id;
  }
  
  public void setId(String id) {
    this.id = id;
  }
  
  public String getTituloPagina() {
    return this.tituloPagina;
  }
  
  public void setTituloPagina(String tituloPagina) {
    this.tituloPagina = tituloPagina;
  }
  
  public List<TipoParceiro> getListaTipoParceiro() {
    return this.listaTipoParceiro;
  }
  
  public void setListaTipoParceiro(List<TipoParceiro> listaTipoParceiro) {
    this.listaTipoParceiro = listaTipoParceiro;
  }
  
  public List<Uf> getListaUf() {
    return this.listaUf;
  }
  
  public void setListaUf(List<Uf> listaUf) {
    this.listaUf = listaUf;
  }
  
  public List<ParceiroVinculo> getListaParceiroVinculo() {
    return this.listaParceiroVinculo;
  }
  
  public void setListaParceiroVinculo(List<ParceiroVinculo> listaParceiroVinculo) {
    this.listaParceiroVinculo = listaParceiroVinculo;
  }
  
  public ParceiroVinculoService getParceiroVinculoService() {
    return this.ParceiroVinculoService;
  }
  
  public void setParceiroVinculoService(ParceiroVinculoService ParceiroVinculoService) {
    this.ParceiroVinculoService = ParceiroVinculoService;
  }
  
  public ParceiroVinculo getParceiroVinculo() {
    return this.parceiroVinculo;
  }
  
  public void setParceiroVinculo(ParceiroVinculo parceiroVinculo) {
    this.parceiroVinculo = parceiroVinculo;
  }
  
  public List<TipoVinculo> getListaTipoVinculo() {
    return this.listaTipoVinculo;
  }
  
  public void setListaTipoVinculo(List<TipoVinculo> listaTipoVinculo) {
    this.listaTipoVinculo = listaTipoVinculo;
  }
  
  public List<Anotacao> getListaAnotacao() {
    return this.listaAnotacao;
  }
  
  public void setListaAnotacao(List<Anotacao> listaAnotacao) {
    this.listaAnotacao = listaAnotacao;
  }
  
  public AnotacaoService getAnotacaoService() {
    return this.anotacaoService;
  }
  
  public void setAnotacaoService(AnotacaoService anotacaoService) {
    this.anotacaoService = anotacaoService;
  }
  
  public Anotacao getAnotacao() {
    return this.anotacao;
  }
  
  public void setAnotacao(Anotacao anotacao) {
    this.anotacao = anotacao;
  }
  
  public TipoAnotacaoService getTipoAnotacaoService() {
    return this.tipoAnotacaoService;
  }
  
  public void setTipoAnotacaoService(TipoAnotacaoService tipoAnotacaoService) {
    this.tipoAnotacaoService = tipoAnotacaoService;
  }
  
  public List<TipoAnotacao> getListaTipoAnotacao() {
    return this.listaTipoAnotacao;
  }
  
  public void setListaTipoAnotacao(List<TipoAnotacao> listaTipoAnotacao) {
    this.listaTipoAnotacao = listaTipoAnotacao;
  }
  
  public DocumentoService getDocumentoService() {
    return this.processoService;
  }
  
  public void setDocumentoService(DocumentoService processoService) {
    this.processoService = processoService;
  }
  
  public Documento getDocumento() {
    return this.processo;
  }
  
  public void setDocumento(Documento processo) {
    this.processo = processo;
  }
  
  public List<Documento> getListaDocumento() {
    return this.listaDocumento;
  }
  
  public void setListaDocumento(List<Documento> listaDocumento) {
    this.listaDocumento = listaDocumento;
  }
  
  public DocumentoItemMaterialService getDocumentoItemMaterialService() {
    return this.processoItemMaterialService;
  }
  
  public void setDocumentoItemMaterialService(DocumentoItemMaterialService processoItemMaterialService) {
    this.processoItemMaterialService = processoItemMaterialService;
  }
  
  public DocumentoItemMaterial getDocumentoItemMaterial() {
    return this.processoItemMaterial;
  }
  
  public void setDocumentoItemMaterial(DocumentoItemMaterial processoItemMaterial) {
    this.processoItemMaterial = processoItemMaterial;
  }
  
  public List<DocumentoItemMaterial> getListaDocumentoItemMaterial() {
    return this.listaDocumentoItemMaterial;
  }
  
  public void setListaDocumentoItemMaterial(List<DocumentoItemMaterial> listaDocumentoItemMaterial) {
    this.listaDocumentoItemMaterial = listaDocumentoItemMaterial;
  }
  
  public TipoDocumentoService getTipoDocumentoService() {
    return this.tipoDocumentoService;
  }
  
  public void setTipoDocumentoService(TipoDocumentoService tipoDocumentoService) {
    this.tipoDocumentoService = tipoDocumentoService;
  }
  
  public TipoDocumento getTipoDocumento() {
    return this.tipoDocumento;
  }
  
  public void setTipoDocumento(TipoDocumento tipoDocumento) {
    this.tipoDocumento = tipoDocumento;
  }
  
  public List<TipoDocumento> getListaTipoDocumento() {
    return this.listaTipoDocumento;
  }
  
  public void setListaTipoDocumento(List<TipoDocumento> listaTipoDocumento) {
    this.listaTipoDocumento = listaTipoDocumento;
  }
  
  public List<Usuario> getListaUsuario() {
    return this.listaUsuario;
  }
  
  public void setListaUsuario(List<Usuario> listaUsuario) {
    this.listaUsuario = listaUsuario;
  }
  
  public TipoCaracteristicaService getTipoCaracteristicaService() {
    return this.tipoCaracteristicaService;
  }
  
  public void setTipoCaracteristicaService(TipoCaracteristicaService tipoCaracteristicaService) {
    this.tipoCaracteristicaService = tipoCaracteristicaService;
  }
  
  public TipoCaracteristica getTipoCaracteristica() {
    return this.tipoCaracteristica;
  }
  
  public void setTipoCaracteristica(TipoCaracteristica tipoCaracteristica) {
    this.tipoCaracteristica = tipoCaracteristica;
  }
  
  public List<TipoCaracteristica> getListaTipoCaracteristica() {
    return this.listaTipoCaracteristica;
  }
  
  public void setListaTipoCaracteristica(List<TipoCaracteristica> listaTipoCaracteristica) {
    this.listaTipoCaracteristica = listaTipoCaracteristica;
  }
  
  public ParceiroCaracteristicaService getParceiroCaracteristicaService() {
    return this.parceiroCaracteristicaService;
  }
  
  public void setParceiroCaracteristicaService(ParceiroCaracteristicaService parceiroCaracteristicaService) {
    this.parceiroCaracteristicaService = parceiroCaracteristicaService;
  }
  
  public ParceiroCaracteristica getParceiroCaracteristica() {
    return this.parceiroCaracteristica;
  }
  
  public void setParceiroCaracteristica(ParceiroCaracteristica parceiroCaracteristica) {
    this.parceiroCaracteristica = parceiroCaracteristica;
  }
  
  public List<ParceiroCaracteristica> getListaParceiroCaracteristica() {
    return this.listaParceiroCaracteristica;
  }
  
  public void setListaParceiroCaracteristica(List<ParceiroCaracteristica> listaParceiroCaracteristica) {
    this.listaParceiroCaracteristica = listaParceiroCaracteristica;
  }
  
  public List<String> getCaracteristicasSelecionada() {
    return this.caracteristicasSelecionada;
  }
  
  public void setCaracteristicasSelecionada(List<String> caracteristicasSelecionada) {
    this.caracteristicasSelecionada = caracteristicasSelecionada;
  }
  
  public ParceiroEnderecoService getParceiroEnderecoService() {
    return this.parceiroEnderecoService;
  }
  
  public void setParceiroEnderecoService(ParceiroEnderecoService parceiroEnderecoService) {
    this.parceiroEnderecoService = parceiroEnderecoService;
  }
  
  public ParceiroEndereco getParceiroEndereco() {
    return this.parceiroEndereco;
  }
  
  public void setParceiroEndereco(ParceiroEndereco parceiroEndereco) {
    this.parceiroEndereco = parceiroEndereco;
  }
  
  public List<ParceiroEndereco> getListaParceiroEndereco() {
    return this.listaParceiroEndereco;
  }
  
  public void setListaParceiroEndereco(List<ParceiroEndereco> listaParceiroEndereco) {
    this.listaParceiroEndereco = listaParceiroEndereco;
  }
  
  public TipoEnderecoService getTipoEnderecoService() {
    return this.tipoEnderecoService;
  }
  
  public void setTipoEnderecoService(TipoEnderecoService tipoEnderecoService) {
    this.tipoEnderecoService = tipoEnderecoService;
  }
  
  public TipoEndereco getTipoEndereco() {
    return this.tipoEndereco;
  }
  
  public void setTipoEndereco(TipoEndereco tipoEndereco) {
    this.tipoEndereco = tipoEndereco;
  }
  
  public List<TipoEndereco> getListaTipoEndereco() {
    return this.listaTipoEndereco;
  }
  
  public void setListaTipoEndereco(List<TipoEndereco> listaTipoEndereco) {
    this.listaTipoEndereco = listaTipoEndereco;
  }
  
  public ParceiroContatoService getParceiroContatoService() {
    return this.parceiroContatoService;
  }
  
  public void setParceiroContatoService(ParceiroContatoService parceiroContatoService) {
    this.parceiroContatoService = parceiroContatoService;
  }
  
  public ParceiroContato getParceiroContato() {
    return this.parceiroContato;
  }
  
  public void setParceiroContato(ParceiroContato parceiroContato) {
    this.parceiroContato = parceiroContato;
  }
  
  public List<ParceiroContato> getListaParceiroContato() {
    return this.listaParceiroContato;
  }
  
  public void setListaParceiroContato(List<ParceiroContato> listaParceiroContato) {
    this.listaParceiroContato = listaParceiroContato;
  }
  
  public DocumentoService getProcessoService() {
    return this.processoService;
  }
  
  public void setProcessoService(DocumentoService processoService) {
    this.processoService = processoService;
  }
  
  public Documento getProcesso() {
    return this.processo;
  }
  
  public void setProcesso(Documento processo) {
    this.processo = processo;
  }
  
  public DocumentoItemMaterialService getProcessoItemMaterialService() {
    return this.processoItemMaterialService;
  }
  
  public void setProcessoItemMaterialService(DocumentoItemMaterialService processoItemMaterialService) {
    this.processoItemMaterialService = processoItemMaterialService;
  }
  
  public DocumentoItemMaterial getProcessoItemMaterial() {
    return this.processoItemMaterial;
  }
  
  public void setProcessoItemMaterial(DocumentoItemMaterial processoItemMaterial) {
    this.processoItemMaterial = processoItemMaterial;
  }
  
  public TabelaPrecoService getTabelaPrecoService() {
    return this.tabelaPrecoService;
  }
  
  public void setTabelaPrecoService(TabelaPrecoService tabelaPrecoService) {
    this.tabelaPrecoService = tabelaPrecoService;
  }
  
  public List<TabelaPreco> getListaTabelaPreco() {
    return this.listaTabelaPreco;
  }
  
  public void setListaTabelaPreco(List<TabelaPreco> listaTabelaPreco) {
    this.listaTabelaPreco = listaTabelaPreco;
  }
  
  public List<Parceiro> getListaParceiroVinculado() {
    return this.listaParceiroVinculado;
  }
  
  public void setListaParceiroVinculado(List<Parceiro> listaParceiroVinculado) {
    this.listaParceiroVinculado = listaParceiroVinculado;
  }
  
  public String getSeqParceiroVinculado() {
    return this.seqParceiroVinculado;
  }
  
  public void setSeqParceiroVinculado(String seqParceiroVinculado) {
    this.seqParceiroVinculado = seqParceiroVinculado;
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
	public UnidadeNegocio getUnidadeNegocio() {
		return this.unidadeNegocio;
	}

	public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public UnidadeNegocioService getUnidadeNegocioService() {
		return this.unidadeNegocioService;
	}

	public void setUnidadeNegocioService(UnidadeNegocioService unidadeNegocioService) {
		this.unidadeNegocioService = unidadeNegocioService;
	}

	public List<UnidadeNegocio> getListaUnidadeNegocio() {
		return this.listaUnidadeNegocio;
	}

	public void setListaUnidadeNegocio(List<UnidadeNegocio> listaUnidadeNegocio) {
		this.listaUnidadeNegocio = listaUnidadeNegocio;
	}

	public Long getSeqParceiroSelecionado() {
		return this.seqParceiroSelecionado;
	}

	public void setSeqParceiroSelecionado(Long seqParceiroSelecionado) {
		this.seqParceiroSelecionado = seqParceiroSelecionado;
	}

	public List<GrupoEmpresarial> getListaGrupoEmpresarial() {
		return this.listaGrupoEmpresarial;
	}

	public void setListaGrupoEmpresarial(List<GrupoEmpresarial> listaGrupoEmpresarial) {
		this.listaGrupoEmpresarial = listaGrupoEmpresarial;
	}

       
}

