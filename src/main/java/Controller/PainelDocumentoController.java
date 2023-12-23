package Controller;

import ClausulaSQL.ClausulaWhere;
import ClausulaSQL.GeneroCondicaoWhere;
import ClausulaSQL.OperacaoCondicaoWhere;
import ClausulaSQL.TipoCondicaoWhere;
import Documento.Documento;
import Documento.DocumentoService;
import DocumentoEtapa.DocumentoEtapa;
import DocumentoEtapa.DocumentoEtapaService;
import DocumentoItemMaterial.DocumentoItemMaterial;
import DocumentoItemMaterial.DocumentoItemMaterialService;
import Empresa.Empresa;
import Equipamento.Equipamento;
import Equipamento.EquipamentoService;
import Material.Material;
import Material.MaterialService;
import MaterialPreco.MaterialPreco;
import MaterialPreco.MaterialPrecoService;
import NvEmbarcacao.NvEmbarcacao;
import NvEmbarcacao.NvEmbarcacaoService;
import Parceiro.Parceiro;
import Parceiro.ParceiroService;
import TipoDocumento.TipoDocumento;
import TipoDocumento.TipoDocumentoService;
import UnidadeNegocio.UnidadeNegocio;
import UnidadeNegocio.UnidadeNegocioService;
import Usuario.Usuario;
import Util.Situacao;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="painelDocumentocontroller")
@ViewScoped
public class PainelDocumentoController
{
  @ManagedProperty("#{loginController}")
  protected LoginController loginController;
  DocumentoService documentoService = new DocumentoService();
  List<Documento> listaDocumento = new ArrayList();
 
  
  TipoDocumentoService tipoDocumentoService = new TipoDocumentoService();
  List<TipoDocumento> listaTipoDocumento = new ArrayList();
  
  DocumentoEtapaService documentoEtapaService = new DocumentoEtapaService();
  List<DocumentoEtapa> listaDocumentoEtapa = new ArrayList();
  
  DocumentoItemMaterialService documentoItemMaterialService = new DocumentoItemMaterialService();
  List<DocumentoItemMaterial> listaDocumentoItemMaterial = new ArrayList();
  DocumentoItemMaterial documentoItemMaterial = new DocumentoItemMaterial();
  
  MaterialService materialService = new MaterialService();
  List<Material> listaMaterial = new ArrayList();
  Material material = new Material();    
  
  ParceiroService parceiroService = new ParceiroService();
  List<Parceiro> listaParceiro = new ArrayList();
  
  NvEmbarcacaoService embarcacaoService = new NvEmbarcacaoService();
  List<NvEmbarcacao> listaNvEmbarcacao = new ArrayList();
  
  EquipamentoService equipamentoService = new EquipamentoService();
  List<Equipamento> listaEquipamento = new ArrayList();
  
  UnidadeNegocioService unidadeNegocioService = new UnidadeNegocioService();
  List<UnidadeNegocio> listaUnidadeNegocio = new ArrayList();
  
  Long seqTipoDocumentoSelecionado;
  
  Long seqDocumentoEtapaSelecionado;
  
  Long seqParceiroSelecionado;
  
  Long seqEmbarcacaoSelecionada;
  
  Long seqServicoSelecionado;
  
  Long seqEquipamentoAcessorioSelecionada;
  Long seqUnidadeNegocioSelecionado;
  String codigoSelecionado;
  boolean porPrevisaoBaixa;
  String dataPrevBaixaInicial;
  String dataPrevBaixaFinal;
  boolean porDataDocumento;
  String dataDocumentoInicial;
  String dataDocumentoFinal;
  boolean porDataCadastro;
  String dataCadastroInicial;
  String dataCadastroFinal;
  String ordemCobranca;
  boolean bParceiro = true;
  boolean bDataCadastro = true;
  boolean bDataPrevisao = true;
  
  public PainelDocumentoController() {
    System.out.println("Criando Controller Painel");
  }
  

  public void iniciar()
  {
    if ((this.loginController.usuario.getAcRelDocumento() == null) || (this.loginController.usuario.getAcRelDocumento().equals("-1"))) {
      this.loginController.mudarPagina("/organizacional/acessonegado.jsf");
      return;
    }
    this.listaTipoDocumento = this.tipoDocumentoService.listar(this.loginController.getEmpresa().getSeqEmpresa(), "", Situacao.TODOS, this.loginController.getUsuario().getSeqUsuario());
    this.listaParceiro = this.parceiroService.listarParceiro(this.loginController.getUsuario().getSeqUsuario(), "");
    this.listaNvEmbarcacao = this.embarcacaoService.listar(this.loginController.getEmpresa().getSeqEmpresa(), "", Situacao.ATIVO);
    this.listaEquipamento = this.equipamentoService.listar(this.loginController.getEmpresa().getSeqEmpresa(), "", Situacao.ATIVO);
    this.listaUnidadeNegocio = this.unidadeNegocioService.listar(this.loginController.getEmpresa().getSeqEmpresa(), "", Situacao.ATIVO);
    this.listaMaterial = this.materialService.listar(this.loginController.getUsuario().getSeqEmpresa(),"",Situacao.ATIVO);
   
  }
  
  public void filtrar() {
    ClausulaWhere condicao = new ClausulaWhere();
    
    condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "documento.seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(this.loginController.getEmpresa().getSeqEmpresa()), TipoCondicaoWhere.Numero);
    
    if (isPorPrevisaoBaixa() == true) {
      condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "documento.data_previsao_conclusao", GeneroCondicaoWhere.MaiorIgual, this.dataPrevBaixaInicial, TipoCondicaoWhere.Data);
      condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "documento.data_previsao_conclusao", GeneroCondicaoWhere.MenorIgual, this.dataPrevBaixaFinal, TipoCondicaoWhere.Data);
    }
    
    if (isPorDataDocumento() == true) {
      condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "documento.data", GeneroCondicaoWhere.MaiorIgual, this.dataDocumentoInicial, TipoCondicaoWhere.Data);
      condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "documento.data", GeneroCondicaoWhere.MenorIgual, this.dataDocumentoFinal, TipoCondicaoWhere.Data);
    }
    
    if (isPorDataCadastro() == true) {
      condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "documento.data_cadastro", GeneroCondicaoWhere.MaiorIgual, this.dataCadastroInicial, TipoCondicaoWhere.Data);
      condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "documento.data_cadastro", GeneroCondicaoWhere.MenorIgual, this.dataCadastroFinal, TipoCondicaoWhere.Data);
    }
    
    if (this.seqTipoDocumentoSelecionado != null) {
      condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "documento.seq_tipo_documento", GeneroCondicaoWhere.igual, String.valueOf(this.seqTipoDocumentoSelecionado), TipoCondicaoWhere.Numero);
    }
    
    if (this.seqDocumentoEtapaSelecionado != null) {
      condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "documento.seq_documento_etapa", GeneroCondicaoWhere.igual, String.valueOf(this.seqDocumentoEtapaSelecionado), TipoCondicaoWhere.Numero);
    }
    
    if (this.seqParceiroSelecionado != null) {
      condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "documento.seq_parceiro", GeneroCondicaoWhere.igual, String.valueOf(this.seqParceiroSelecionado), TipoCondicaoWhere.Numero);
    }
    if ((this.codigoSelecionado != null) && (!this.codigoSelecionado.isEmpty()))
    {

      condicao.AdicionarCondicaoManual(" and (documento.codigo = '" + this.codigoSelecionado + "' or documento.codigo = ' " + this.codigoSelecionado + "')");
    }
    
    if (this.seqEmbarcacaoSelecionada != null) {
      condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "nv_embarcacao.seq_nv_embarcacao", GeneroCondicaoWhere.igual, String.valueOf(this.seqEmbarcacaoSelecionada), TipoCondicaoWhere.Numero);
    }
    if (this.seqEquipamentoAcessorioSelecionada != null) {
      condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "equipamento.seq_equipamento", GeneroCondicaoWhere.igual, String.valueOf(this.seqEquipamentoAcessorioSelecionada), TipoCondicaoWhere.Numero);
    }
    if (this.seqServicoSelecionado != null) {
      condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "material.seq_material", GeneroCondicaoWhere.igual, String.valueOf(this.seqServicoSelecionado), TipoCondicaoWhere.Numero);
    }    
    if (this.seqUnidadeNegocioSelecionado != null) {
      condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "usuario.seq_unidade_negocio", GeneroCondicaoWhere.igual, String.valueOf(this.seqUnidadeNegocioSelecionado), TipoCondicaoWhere.Numero);
    }
    
    this.listaDocumento = this.documentoService.listarDocumentoFiltro(condicao);
  }
  
  public void carregarEtapa() {
    if (this.seqTipoDocumentoSelecionado == null) {
      this.listaDocumentoEtapa.clear();
    } else {
      this.listaDocumentoEtapa = this.documentoEtapaService.listarEtapa(this.loginController.getEmpresa().getSeqEmpresa(), this.seqTipoDocumentoSelecionado.toString(), Situacao.TODOS);
    }
  }
  
  public void selecionarDocumento(Documento pDocumento) {
    this.loginController.mudarPagina("/documento/documento.jsf?idDocumento=" + pDocumento.getSeqDocumento() + "&idTipoDocumento=" + pDocumento.getSeqTipoDocumento());
  }
  
  public LoginController getLoginController()
  {
    return this.loginController;
  }
  
  public void setLoginController(LoginController loginController) {
    this.loginController = loginController;
  }
  
  public DocumentoService getDocumentoService() {
    return this.documentoService;
  }
  
  public void setDocumentoService(DocumentoService documentoService) {
    this.documentoService = documentoService;
  }
  
  public List<Documento> getListaDocumento() {
    return this.listaDocumento;
  }
  
  public void setListaDocumento(List<Documento> listaDocumento) {
    this.listaDocumento = listaDocumento;
  }
  
  public TipoDocumentoService getTipoDocumentoService() {
    return this.tipoDocumentoService;
  }
  
  public void setTipoDocumentoService(TipoDocumentoService tipoDocumentoService) {
    this.tipoDocumentoService = tipoDocumentoService;
  }
  
  public List<TipoDocumento> getListaTipoDocumento() {
    return this.listaTipoDocumento;
  }
  
  public void setListaTipoDocumento(List<TipoDocumento> listaTipoDocumento) {
    this.listaTipoDocumento = listaTipoDocumento;
  }
  
  public DocumentoEtapaService getDocumentoEtapaService() {
    return this.documentoEtapaService;
  }
  
  public void setDocumentoEtapaService(DocumentoEtapaService documentoEtapaService) {
    this.documentoEtapaService = documentoEtapaService;
  }
  
  public List<DocumentoEtapa> getListaDocumentoEtapa() {
    return this.listaDocumentoEtapa;
  }
  
  public void setListaDocumentoEtapa(List<DocumentoEtapa> listaDocumentoEtapa) {
    this.listaDocumentoEtapa = listaDocumentoEtapa;
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
  
  public Long getSeqTipoDocumentoSelecionado() {
    return this.seqTipoDocumentoSelecionado;
  }
  
  public void setSeqTipoDocumentoSelecionado(Long seqTipoDocumentoSelecionado) {
    this.seqTipoDocumentoSelecionado = seqTipoDocumentoSelecionado;
  }
  
  public Long getSeqDocumentoEtapaSelecionado() {
    return this.seqDocumentoEtapaSelecionado;
  }
  
  public void setSeqDocumentoEtapaSelecionado(Long seqDocumentoEtapaSelecionado) {
    this.seqDocumentoEtapaSelecionado = seqDocumentoEtapaSelecionado;
  }
  
  public Long getSeqParceiroSelecionado() {
    return this.seqParceiroSelecionado;
  }
  
  public void setSeqParceiroSelecionado(Long seqParceiroSelecionado) {
    this.seqParceiroSelecionado = seqParceiroSelecionado;
  }
  
  public boolean isPorPrevisaoBaixa() {
    return this.porPrevisaoBaixa;
  }
  
  public void setPorPrevisaoBaixa(boolean porPrevisaoBaixa) {
    this.porPrevisaoBaixa = porPrevisaoBaixa;
  }
  
  public String getDataPrevBaixaInicial() {
    return this.dataPrevBaixaInicial;
  }
  
  public void setDataPrevBaixaInicial(String dataPrevBaixaInicial) {
    this.dataPrevBaixaInicial = dataPrevBaixaInicial;
  }
  
  public String getDataPrevBaixaFinal() {
    return this.dataPrevBaixaFinal;
  }
  
  public void setDataPrevBaixaFinal(String dataPrevBaixaFinal) {
    this.dataPrevBaixaFinal = dataPrevBaixaFinal;
  }
  
  public boolean isPorDataDocumento() {
    return this.porDataDocumento;
  }
  
  public void setPorDataDocumento(boolean porDataDocumento) {
    this.porDataDocumento = porDataDocumento;
  }
  
  public String getDataDocumentoInicial() {
    return this.dataDocumentoInicial;
  }
  
  public void setDataDocumentoInicial(String dataDocumentoInicial) {
    this.dataDocumentoInicial = dataDocumentoInicial;
  }
  
  public String getDataDocumentoFinal() {
    return this.dataDocumentoFinal;
  }
  
  public void setDataDocumentoFinal(String dataDocumentoFinal) {
    this.dataDocumentoFinal = dataDocumentoFinal;
  }
  
  public boolean isPorDataCadastro() {
    return this.porDataCadastro;
  }
  
  public void setPorDataCadastro(boolean porDataCadastro) {
    this.porDataCadastro = porDataCadastro;
  }
  
  public String getDataCadastroInicial() {
    return this.dataCadastroInicial;
  }
  
  public void setDataCadastroInicial(String dataCadastroInicial) {
    this.dataCadastroInicial = dataCadastroInicial;
  }
  
  public String getDataCadastroFinal() {
    return this.dataCadastroFinal;
  }
  
  public void setDataCadastroFinal(String dataCadastroFinal) {
    this.dataCadastroFinal = dataCadastroFinal;
  }
  
  public boolean isbParceiro() {
    return this.bParceiro;
  }
  
  public void setbParceiro(boolean bParceiro) {
    this.bParceiro = bParceiro;
  }
  
  public boolean isbDataCadastro() {
    return this.bDataCadastro;
  }
  
  public void setbDataCadastro(boolean bDataCadastro) {
    this.bDataCadastro = bDataCadastro;
  }
  
  public boolean isbDataPrevisao() {
    return this.bDataPrevisao;
  }
  
  public void setbDataPrevisao(boolean bDataPrevisao) {
    this.bDataPrevisao = bDataPrevisao;
  }
  
  public Long getSeqEmbarcacaoSelecionada() {
    return this.seqEmbarcacaoSelecionada;
  }
  
  public Long getSeqServicoSelecionado() {
    return this.seqServicoSelecionado;
  }  
  
  public String getCodigoSelecionado() {
    return this.codigoSelecionado;
  }
  
  public void setCodigoSelecionado(String codigoSelecionado) {
    this.codigoSelecionado = codigoSelecionado;
  }
  
  public void setSeqEmbarcacaoSelecionada(Long seqEmbarcacaoSelecionada) {
    this.seqEmbarcacaoSelecionada = seqEmbarcacaoSelecionada;
  }
  
  public void setSeqServicoSelecionado(Long seqServicoSelecionado) {
    this.seqServicoSelecionado = seqServicoSelecionado;
  }  
  
  public Long getSeqEquipamentoAcessorioSelecionada() {
    return this.seqEquipamentoAcessorioSelecionada;
  }
  
  public void setSeqEquipamentoAcessorioSelecionada(Long seqEquipamentoAcessorioSelecionada) {
    this.seqEquipamentoAcessorioSelecionada = seqEquipamentoAcessorioSelecionada;
  }
  
  public NvEmbarcacaoService getEmbarcacaoService() {
    return this.embarcacaoService;
  }
  
  public void setEmbarcacaoService(NvEmbarcacaoService embarcacaoService) {
    this.embarcacaoService = embarcacaoService;
  }
  
  public List<NvEmbarcacao> getListaNvEmbarcacao() {
    return this.listaNvEmbarcacao;
  }
  
  public void setListaNvEmbarcacao(List<NvEmbarcacao> listaNvEmbarcacao) {
    this.listaNvEmbarcacao = listaNvEmbarcacao;
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
  
  public Long getSeqUnidadeNegocioSelecionado() {
    return this.seqUnidadeNegocioSelecionado;
  }
  
  public void setSeqUnidadeNegocioSelecionado(Long seqUnidadeNegocioSelecionado) {
    this.seqUnidadeNegocioSelecionado = seqUnidadeNegocioSelecionado;
  }
  
    public String getOrdemCobranca() {
    return this.ordemCobranca;
  }
  
    public void setOrdemCobranca(String ordemCobranca) {
        this.ordemCobranca = ordemCobranca;
    }

    public DocumentoItemMaterialService getDocumentoItemMaterialService() {
        return this.documentoItemMaterialService;
    }

    public void setDocumentoItemMaterialService(DocumentoItemMaterialService documentoItemMaterialService) {
        this.documentoItemMaterialService = documentoItemMaterialService;
    }

    public List<DocumentoItemMaterial> getListaDocumentoItemMaterial() {
        return this.listaDocumentoItemMaterial;
    }

    public void setListaDocumentoItemMaterial(List<DocumentoItemMaterial> listaDocumentoItemMaterial) {
        this.listaDocumentoItemMaterial = listaDocumentoItemMaterial;
    }

    public DocumentoItemMaterial getDocumentoItemMaterial() {
        return this.documentoItemMaterial;
    }

    public void setDocumentoItemMaterial(DocumentoItemMaterial documentoItemMaterial) {
        this.documentoItemMaterial = documentoItemMaterial;
    }
    
	public List<Material> getListaMaterial() {
		return this.listaMaterial;
	}

	public void setListaMaterial(List<Material> listaMaterial) {
		this.listaMaterial = listaMaterial;
	}       

}


