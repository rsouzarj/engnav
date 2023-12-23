package NvAvisos;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class NvAvisos
{
    private String identificacao; /*ok*/
    private String seqCertificado; /*ok*/
    private String seqNvCertificadoDetalhe;
    private String nome; /*ok*/
    private String embarcacao; /*ok*/
    private String cliente; /*ok*/
    private String arealiza; /*ok*/
    private String local; /*ok*/
    private String filial;/*ok*/
    private Date dataCadastro; /*ok*/
    private Date dataEmissao;
    private Date dataValidade;
    private Date dataInicial;
    private Date dataFinal;
    private String seqEmpresa;
    private String email;
    private String aviso;
    private String vdate;
    private String alerta;

    public String getAlerta() {
        return alerta;
    }

    public void setAlerta(String alerta) {
        this.alerta = alerta;
    }

    public String getVdate() {
        return vdate;
    }

    public void setVdate(String vdate) {
        this.vdate = vdate;
    }

    public String getAviso() {
        return aviso;
    }

    public void setAviso(String aviso) {
        this.aviso = aviso;
    }
    
     /*private Date dataEmissao;
    private Date dataValidade;
    private Date dataInicial;
    private Date dataFinal;*/
    
    public Date getDataCadastro() {
        return this.dataCadastro;
    }
    
    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
        
    public Date getDataEmissao() {
        return this.dataEmissao;
    }
    
    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }
        
    public Date getDataValidade() {
        return this.dataValidade;
    }
    
    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }
    
    public Date getDataInicial() {
        return this.dataInicial;
    }
    
    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }
    
      public Date getDataFinal() {
        return this.dataFinal;
    }
    
    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }
    
    public String getTipoCertificado() {
        return this.nome;
    }
    
    public void setTipoCertificado(String tipoCertificado) {
        this.nome = tipoCertificado;
    }
    
    public String getIdentificacao() {
        return this.identificacao;
    }
    
    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }
    
    public String getSeqCertificado() {
        return this.seqCertificado;
    }
    
    public void setSeqCertificado(String seqCertificado) {
        this.seqCertificado = seqCertificado;
    }
    
    public String getEmbarcacao() {
        return this.embarcacao;
    }
    
    public void setEmbarcacao(String embarcacao) {
        this.embarcacao = embarcacao;
    }
    
    public String getCliente() {
        return this.cliente;
    }
    
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    
    public String getArealiza() {
        return this.arealiza;
    }
    
    public void setArealiza(String arealiza) {
        this.arealiza = arealiza;
    }
    
      public String getLocal() {
        return this.local;
    }
    
    public void setLocal(String local) {
        this.local = local;
    }
    
         public String getFilial() {
        return this.filial;
    }
    
    public void setFilial(String filial) {    
        this.filial = filial;
    }
    
  public String getSeqEmpresa() {
        return this.seqEmpresa;
    }
    
    public void setSeqEmpresa(String seqEmpresa) {
        this.seqEmpresa = seqEmpresa;
    }
  public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSeqNvCertificadoDetalhe() {
        return seqNvCertificadoDetalhe;
    }

    public void setSeqNvCertificadoDetalhe(String seqNvCertificadoDetalhe) {
        this.seqNvCertificadoDetalhe = seqNvCertificadoDetalhe;
    }    

}