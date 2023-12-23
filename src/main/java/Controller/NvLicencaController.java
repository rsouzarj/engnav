package Controller;

import ClausulaSQL.ClausulaWhere;
import ClausulaSQL.GeneroCondicaoWhere;
import ClausulaSQL.OperacaoCondicaoWhere;
import ClausulaSQL.TipoCondicaoWhere;
import Colaborador.Colaborador;
import Colaborador.ColaboradorService;
import NvEmbarcacao.NvEmbarcacao;
import NvEmbarcacao.NvEmbarcacaoService;
import NvLicenca.NvLicenca;
import NvLicenca.NvLicencaService;
import NvTipoLicenca.NvTipoLicenca;
import NvTipoLicenca.NvTipoLicencaService;
import UnidadeNegocio.UnidadeNegocio;
import UnidadeNegocio.UnidadeNegocioService;
import Util.Conexao;
import Util.Situacao;
import Util.Util;
import _Teste.DataPorExtenso;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
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
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

@ManagedBean(
        name = "nvLicencaController"
)
@ViewScoped
public class NvLicencaController {

    @ManagedProperty("#{loginController}")
    protected LoginController loginController;
    NvLicencaService nvLicencaService = new NvLicencaService();
    NvLicenca nvLicenca = new NvLicenca();
    List<NvLicenca> listaNvLicenca = new ArrayList();
    String pesquisa = "";
    Integer tela = 0;
    Util util = new Util();
    NvTipoLicencaService nvTipoLicencaService = new NvTipoLicencaService();
    List<NvTipoLicenca> listaNvTipoLicenca = new ArrayList();
    NvEmbarcacaoService nvEmbarcacaoService = new NvEmbarcacaoService();
    List<NvEmbarcacao> listaNvEmbarcacao = new ArrayList();
    NvEmbarcacao nvEmbarcacao = new NvEmbarcacao();
    ColaboradorService colaboradorService = new ColaboradorService();
    List<Colaborador> listaColaborador = new ArrayList();
    Long seqTipoLicencaSelecionada;
    Long seqEmbarcacaoSelecionada;
    Long seqUnidadeNegocioSelecionada;
    UnidadeNegocio unidadeNegocio = new UnidadeNegocio();
    UnidadeNegocioService unidadeNegocioService = new UnidadeNegocioService();
    List<UnidadeNegocio> listaUnidadeNegocio = new ArrayList();

    public void iniciar() {
        if (this.loginController.usuario.getAcOpLicenca() != null && !this.loginController.usuario.getAcOpLicenca().equals("-1")) {
            this.listaNvTipoLicenca = this.nvTipoLicencaService.listar(this.loginController.getEmpresa().getSeqEmpresa(), "", Situacao.ATIVO);
            this.listaNvEmbarcacao = this.nvEmbarcacaoService.listar(this.loginController.getEmpresa().getSeqEmpresa(), "", Situacao.ATIVO);
            this.listaColaborador = this.colaboradorService.listar(this.loginController.getEmpresa().getSeqEmpresa(), "", Situacao.ATIVO);
            this.listaUnidadeNegocio = this.unidadeNegocioService.listar(this.loginController.getEmpresa().getSeqEmpresa(), "", Situacao.ATIVO);
        } else {
            this.loginController.mudarPagina("/organizacional/acessonegado.jsf");
        }
    }

    public void salvar(int pTela) {
        this.nvLicenca.setSeqEmpresa(this.loginController.getEmpresa().getSeqEmpresa());

        this.nvLicenca.setObservacao(this.nvLicenca.getObservacao().replace("font-size", ""));
        this.nvLicenca = this.nvLicencaService.salvar(this.nvLicenca);
        this.nvLicenca.setSeqUnidadeNegocio(this.unidadeNegocio.getSeqUnidadeNegocio());
        this.listar();
        FacesContext.getCurrentInstance().addMessage((String) null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro armazenado com sucesso.", ""));
        this.tela = pTela;
    }

    public void novo() {
        this.nvLicenca = new NvLicenca();
        this.tela = 1;
    }

    public void listar() {
        this.listaNvLicenca = this.nvLicencaService.listar(this.loginController.getEmpresa().getSeqEmpresa(), this.pesquisa, Situacao.TODOS);
    }

    public void filtrar() {
        boolean executar = true;
        ClausulaWhere condicao = new ClausulaWhere();
        condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "NV_licenca.seq_empresa", GeneroCondicaoWhere.igual, String.valueOf(this.loginController.getEmpresa().getSeqEmpresa()), TipoCondicaoWhere.Numero);
        if (this.seqTipoLicencaSelecionada != null) {
            condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "NV_licenca.seq_nv_tipo_licenca", GeneroCondicaoWhere.igual, String.valueOf(this.seqTipoLicencaSelecionada), TipoCondicaoWhere.Numero);
        }

        if (this.seqEmbarcacaoSelecionada != null) {
            condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "NV_licenca.seq_nv_embarcacao", GeneroCondicaoWhere.igual, String.valueOf(this.seqEmbarcacaoSelecionada), TipoCondicaoWhere.Numero);
        }

        if (this.seqUnidadeNegocioSelecionada != null) {
            condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "NV_licenca.seq_unidade_negocio", GeneroCondicaoWhere.igual, String.valueOf(this.seqUnidadeNegocioSelecionada), TipoCondicaoWhere.Numero);
        }

        if (executar) {
            this.listaNvLicenca = this.nvLicencaService.listar(condicao);
        }
    }

    public void deletar() {
        if (this.nvLicencaService.deletar(this.nvLicenca)) {
            this.listar();
            this.nvLicenca = new NvLicenca();
            FacesContext.getCurrentInstance().addMessage((String) null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro eliminado com sucesso.", ""));
            this.tela = 0;
            this.listar();
        } else {
            FacesContext.getCurrentInstance().addMessage((String) null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao excluir registro.", ""));
        }

    }

    public void buscarContratada() {
        for (UnidadeNegocio un : this.listaUnidadeNegocio) {
            if (un.getSeqUnidadeNegocio().equals(this.unidadeNegocio.getSeqUnidadeNegocio())) {
                if (un.getCnpj() == null) {
                    this.unidadeNegocio = un;
                    this.unidadeNegocio.setCnpj("S/Nº.");
                } else {
                    this.unidadeNegocio = un;
                }
            } else if (this.unidadeNegocio.getSeqUnidadeNegocio() == null) {
                this.unidadeNegocio = new UnidadeNegocio();
            }
        }
    }

    public void fecharTela() throws IOException {
        this.loginController.mudarPagina("/principal/ principal.jsf");
    }

    public void selecionar(NvLicenca pNvLicenca) {
        this.nvLicenca = pNvLicenca;
        this.tela = 1;
    }

    public void mudarTela(Integer pTela) {
        this.tela = pTela;
    }

    public void imprimir() {
        this.nvEmbarcacao = this.nvEmbarcacaoService.buscar(this.nvLicenca.getSeqNvEmbarcacao());
        new DataPorExtenso();
        String diaPorExtenso = DataPorExtenso.DataPorExtenso("Emitido em: " + this.nvLicenca.getLocalEmissao() + ", ", this.nvLicenca.getDataEmissao());
        String validade = "__/__/____";
        if (this.nvLicenca.getValidadeLicencaTemporaria() != null) {
            validade = this.util.DataToString(this.nvLicenca.getValidadeLicencaTemporaria());
        }

        try {
            new Conexao();
            Connection conn = Conexao.getConnection();
            HashMap parametro = new HashMap();
            parametro.put("pSeqLicenca", Long.valueOf(this.nvLicenca.getSeqNvLicenca()));
            parametro.put("pEmissao", diaPorExtenso);
            parametro.put("pValidade", validade);
            if (this.nvEmbarcacao.getAreaNavegacao().equals("Mar Aberto / Navegação Interior")) {
                if (this.nvEmbarcacao.getAreaNavegacaoTipo().contains("01 e 02")) {
                    parametro.put("pAreaNavegacaoTipoInterior", "Área 01 e Área 02");
                } else if (this.nvEmbarcacao.getAreaNavegacaoTipo().contains("01")) {
                    parametro.put("pAreaNavegacaoTipoInterior", "Área 01");
                } else if (this.nvEmbarcacao.getAreaNavegacaoTipo().contains("02")) {
                    parametro.put("pAreaNavegacaoTipoInterior", "Área 02");
                }

                if (this.nvEmbarcacao.getAreaNavegacaoTipo().contains("Apoio Marítimo")) {
                    parametro.put("pAreaNavegacaoTipoMar", "Apoio Marítimo");
                } else if (this.nvEmbarcacao.getAreaNavegacaoTipo().contains("Cabotagem")) {
                    parametro.put("pAreaNavegacaoTipoMar", "Cabotagem");
                } else if (this.nvEmbarcacao.getAreaNavegacaoTipo().contains("Longo Curso")) {
                    parametro.put("pAreaNavegacaoTipoMar", "Longo Curso");
                }
            } else {
                parametro.put("pAreaNavegacaoTipoMar", this.nvEmbarcacao.getAreaNavegacaoTipo());
                parametro.put("pAreaNavegacaoTipoInterior", this.nvEmbarcacao.getAreaNavegacaoTipo());
            }

            String caminho = null;
            if (this.nvLicenca.getSeqNvTipoLicenca().equals("16")) {
                caminho = "/relatorio/LCC/Licenca_Temporaria.jasper";
                parametro.put("IDLicencaProvisoria", this.nvLicenca.getIdentificacao());
            } else {
                caminho = "/relatorio/LCC/LICENCA.jasper";
                if (this.nvLicenca.getSeqNvTipoLicenca().equals("18")) {
                    parametro.put("XConstrucao", "X");
                    parametro.put("IDConstrucao", this.nvLicenca.getIdentificacao());
                } else if (this.nvLicenca.getSeqNvTipoLicenca().equals("19")) {
                    parametro.put("XAlteracao", "X");
                    parametro.put("IDAlteracao", this.nvLicenca.getIdentificacao());
                } else if (this.nvLicenca.getSeqNvTipoLicenca().equals("21")) {
                    System.out.println("21");
                    parametro.put("XReclassificacao", "X");
                    parametro.put("IDReclassificacao", this.nvLicenca.getIdentificacao());
                } else if (this.nvLicenca.getSeqNvTipoLicenca().equals("20")) {
                    parametro.put("XLec", "X");
                    parametro.put("IDLec", this.nvLicenca.getIdentificacao());
                }
            }

            if (this.nvEmbarcacao.getAreaNavegacao().equals("Mar Aberto")) {
                parametro.put("idNormam", "201");
            } else if (this.nvEmbarcacao.getAreaNavegacao().equals("Navegação Interior")) {
                parametro.put("idNormam", "202");
            } else if (this.nvEmbarcacao.getAreaNavegacao().equals("Mar Aberto / Navegação Interior")) {
                parametro.put("idNormam", "201");
            } else {
                parametro.put("idNormam", "");
            }

            if (caminho == null) {
                FacesContext.getCurrentInstance().addMessage((String) null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Modelo de licença não foi encontrado!", ""));
                return;
            }

            System.out.println("SEQ NV LICENÇA " + this.nvLicenca.getSeqNvLicenca());
            System.out.println("SEQ NV TIPO LICENCA " + this.nvLicenca.getSeqNvTipoLicenca());
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();
            ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
            JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath(caminho), parametro, conn);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            JRPdfExporter exporter = null;
            exporter = new JRPdfExporter();
            savePDF(jasperPrint, "C:/UPLOAD/LIC.PDF");
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            //exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,"C:/UPLOAD/LIC.PDF");
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
            exporter.exportReport();
            byte[] bytes = baos.toByteArray();
            if (bytes != null && bytes.length > 0) {
                HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
                response.setContentType("application/pdf");
                response.setHeader("Content-disposition", "inline; filename=\"licenca.pdf\"");
                response.setContentLength(bytes.length);
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(bytes, 0, bytes.length);
                outputStream.flush();
                outputStream.close();
            }
        } catch (JRException var16) {
            Logger.getLogger(NvLicencaController.class.getName()).log(Level.SEVERE, (String) null, var16);
        } catch (IOException var17) {
            Logger.getLogger(NvLicencaController.class.getName()).log(Level.SEVERE, (String) null, var17);
        }

    }

    private void savePDF(JasperPrint jasperPrint, String filePath) throws JRException, IOException {
        JRExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);

        // Aponto onde o arquivo deve ser salvo
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, filePath);

        // Exporta o arquivo
        exporter.exportReport();

        //Caso precise criar cópias em outras pastas do arquivo
        // Files.copy(Paths.get(filePath), Paths.get("C:/ANOTHER/PATH/LIC.PDF"), StandardCopyOption.REPLACE_EXISTING);
    }

    public LoginController getLoginController() {
        return this.loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public NvLicencaService getNvLicencaService() {
        return this.nvLicencaService;
    }

    public void setNvLicencaService(NvLicencaService nvLicencaService) {
        this.nvLicencaService = nvLicencaService;
    }

    public NvLicenca getNvLicenca() {
        return this.nvLicenca;
    }

    public void setNvLicenca(NvLicenca nvLicenca) {
        this.nvLicenca = nvLicenca;
    }

    public List<NvLicenca> getListaNvLicenca() {
        return this.listaNvLicenca;
    }

    public void setListaNvLicenca(List<NvLicenca> listaNvLicenca) {
        this.listaNvLicenca = listaNvLicenca;
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

    public NvTipoLicencaService getNvTipoLicencaService() {
        return this.nvTipoLicencaService;
    }

    public void setNvTipoLicencaService(NvTipoLicencaService nvTipoLicencaService) {
        this.nvTipoLicencaService = nvTipoLicencaService;
    }

    public List<NvTipoLicenca> getListaNvTipoLicenca() {
        return this.listaNvTipoLicenca;
    }

    public void setListaNvTipoLicenca(List<NvTipoLicenca> listaNvTipoLicenca) {
        this.listaNvTipoLicenca = listaNvTipoLicenca;
    }

    public NvEmbarcacaoService getNvEmbarcacaoService() {
        return this.nvEmbarcacaoService;
    }

    public void setNvEmbarcacaoService(NvEmbarcacaoService nvEmbarcacaoService) {
        this.nvEmbarcacaoService = nvEmbarcacaoService;
    }

    public List<NvEmbarcacao> getListaNvEmbarcacao() {
        return this.listaNvEmbarcacao;
    }

    public void setListaNvEmbarcacao(List<NvEmbarcacao> listaNvEmbarcacao) {
        this.listaNvEmbarcacao = listaNvEmbarcacao;
    }

    public Long getSeqTipoLicencaSelecionada() {
        return this.seqTipoLicencaSelecionada;
    }

    public void setSeqTipoLicencaSelecionada(Long seqTipoLicencaSelecionada) {
        this.seqTipoLicencaSelecionada = seqTipoLicencaSelecionada;
    }

    public Long getSeqEmbarcacaoSelecionada() {
        return this.seqEmbarcacaoSelecionada;
    }

    public void setSeqEmbarcacaoSelecionada(Long seqEmbarcacaoSelecionada) {
        this.seqEmbarcacaoSelecionada = seqEmbarcacaoSelecionada;
    }

    public Long getSeqUnidadeNegocioSelecionada() {
        return this.seqUnidadeNegocioSelecionada;
    }

    public void setSeqUnidadeNegocioSelecionada(Long seqUnidadeNegocioSelecionada) {
        this.seqUnidadeNegocioSelecionada = seqUnidadeNegocioSelecionada;
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

    public List<UnidadeNegocio> getListaUnidadeNegocio() {
        return this.listaUnidadeNegocio;
    }

    public void setListaUnidadeNegocio(List<UnidadeNegocio> listaUnidadeNegocio) {
        this.listaUnidadeNegocio = listaUnidadeNegocio;
    }

    public UnidadeNegocioService getUnidadeNegocioService() {
        return this.unidadeNegocioService;
    }

    public void setUnidadeNegocioService(UnidadeNegocioService unidadeNegocioService) {
        this.unidadeNegocioService = unidadeNegocioService;
    }

    public UnidadeNegocio getUnidadeNegocio() {
        return this.unidadeNegocio;
    }

    public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
        this.unidadeNegocio = unidadeNegocio;
    }

}
