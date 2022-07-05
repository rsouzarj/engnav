 package RelVisita;

 import java.util.Date;

 public class RelVisita
 {
   private String responsavel;
   private String seqRel;
   private String seqUsuario;
   private String seqParceiro;
   private String assunto;
   private String descricao;
   private Date dtInicio;
   private Date dtFim;
   private Date dataCadastro;
   private String cor;
   private String nomeParceiro;
   private String nomeUsuario;
   private String cliente;
   private String seqEmpresa;   
   
   public String getSeqRel()
   {
     return this.seqRel;
   }
   
   public void setResponsavel(String responsavel) {
    this.responsavel = responsavel;
   }

    public String getResponsavel()
   {
     return this.responsavel;
   }
   

   public void setSeqRel(String seqRel) {
     this.seqRel = seqRel;
   }
   
   public String getSeqUsuario() {
    return this.seqUsuario;
   }
   
   public void setSeqUsuario(String seqUsuario) {
     this.seqUsuario = seqUsuario;
   }
   
   public String getSeqParceiro() {
     return this.seqParceiro;
   }
   
   public void setSeqParceiro(String seqParceiro) {
     this.seqParceiro = seqParceiro;
   }
   
   public String getAssunto() {
     return this.assunto;
   }
   
   public void setAssunto(String assunto) {
     this.assunto = assunto;
   }
   
   public String getDescricao() {
     return this.descricao;
   }
   
   public void setDescricao(String descricao) {
    this.descricao = descricao;
   }
   
   public Date getDtInicio() {
    return this.dtInicio;
   }
   
   public void setDtInicio(Date dtInicio) {
     this.dtInicio = dtInicio;
   }
   
   public Date getDtFim() {
     return this.dtFim;
   }
   
   public void setDtFim(Date dtFim) {
     this.dtFim = dtFim;
   }
   
   public Date getDataCadastro() {
    return this.dataCadastro;
   }
   
   public void setDataCadastro(Date dataCadastro) {
     this.dataCadastro = dataCadastro;
   }
   
   public String getCor() {
     return this.cor;
   }
   
   public void setCor(String cor) {
    this.cor = cor;
   }
   
   public String getNomeUsuario() {
     return this.nomeUsuario;
   }
   
   public void setNomeUsuario(String nomeUsuario) {
     this.nomeUsuario = nomeUsuario;
   }

    public String getNomeParceiro() {
     return this.nomeParceiro;
   }
            
   public void setNomeParceiro(String nomeParceiro) {
     this.nomeParceiro = nomeParceiro;
   }

   public String getCliente() {
     return this.cliente;
   }
   
   public void setCliente(String cliente) {
     this.cliente = cliente;
   }
   
   public String getSeqEmpresa() {
     return this.seqEmpresa;
   }
   
   public void setSeqEmpresa(String seqEmpresa) {
     this.seqEmpresa = seqEmpresa;
   }   
 }