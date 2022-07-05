/*     */ package NvCertificadoEquipamento;
/*     */ 


          import EquipamentoParceiro.EquipamentoParceiro;
/*     */ import java.util.Date;
          import java.util.List;
/*     */ 

          public class NvCertificadoEquipamento
/*     */ {
/*     */   private String seqCertificado;
/*     */   private String identificacao;
/*     */   private String seqEmpresa;
/*     */   private Date   dataCadastro;
/*     */   private String situacao;
            private String tipoequipamento;   
/*     */   private String seqEquipamento;
/*     */   private String seqColaborador;
/*     */   private Date   dataEmissao;
/*     */   private String nomeVistoriador;
/*     */   private String equipamentoNome;
            private String seqTipoCertificado;  
/*     */   private String observacao;
/*     */   private Date   validade;
/*     */   private String localEmissao;
/*     */   private String status;
/*     */   private String assinaturaDigital;
            private String normas;
            private String realtorioEnsaio;
            private String relatorioConformidade;
            private String conformidadeTecnica;
            private Date   valtCarga;
            private Date   emstCarga;
            private String filial;
            private String seqParceiro;
            private String nomeParceiro;
            private String ultimoEmitido;
            List<EquipamentoParceiro> listaEquipamentoParceiro;
           
            
            
  
/*     */   public String getSeqCertificado()
/*     */   {
/*  35 */     return this.seqCertificado;
/*     */   }
/*     */   
/*     */   public void setSeqCertificado(String seqCertificado) {
/*  39 */     this.seqCertificado = seqCertificado;
/*     */   }
/*     */   
/*     */   public String getIdentificacao() {
/*  43 */     return this.identificacao;
/*     */   }
/*     */   
/*     */   public void setIdentificacao(String identificacao) {
/*  47 */     this.identificacao = identificacao;
/*     */   }
/*     */   
/*     */   public String getSeqEmpresa() {
/*  51 */     return this.seqEmpresa;
/*     */   }
/*     */   
/*     */   public void setSeqEmpresa(String seqEmpresa) {
/*  55 */     this.seqEmpresa = seqEmpresa;
/*     */   }
/*     */   
/*     */   public Date getDataCadastro() {
/*  59 */     return this.dataCadastro;
/*     */   }
/*     */   
/*     */   public void setDataCadastro(Date dataCadastro) {
/*  63 */     this.dataCadastro = dataCadastro;
/*     */   }
/*     */   
/*     */   public String getSituacao() {
/*  67 */     return this.situacao;
/*     */   }
/*     */   
/*     */   public void setSituacao(String situacao) {
/*  71 */     this.situacao = situacao;
/*     */   }
/*     */   
            public String gettipoEquipamento() {
               return this.tipoequipamento;
        }    
            public void settipoEquipamento(String tipoequipamento) {
              this.tipoequipamento = tipoequipamento;
        }
/*     */   
/*     */   public String getSeqEquipamento() {
/*  83 */     return this.seqEquipamento;
/*     */   }
/*     */   
/*     */   public void setSeqEquipamento(String seqEquipamento) {
/*  87 */     this.seqEquipamento = seqEquipamento;
/*     */   }
/*     */   
/*     */   public Date getDataEmissao() {
/*  91 */     return this.dataEmissao;
/*     */   }
/*     */   
/*     */   public void setDataEmissao(Date dataEmissao) {
/*  95 */     this.dataEmissao = dataEmissao;
/*     */   }
/*     */   
            public Date getValidade() {
/*  91 */     return this.validade;
/*     */   }
/*     */   
/*     */   public void setValidade(Date validade) {
/*  95 */     this.validade = validade;

}
/*     */   public String getNomeVistoriador() {
/*  99 */     return this.nomeVistoriador;
/*     */   }
/*     */   
/*     */   public void setNomeVistoriador(String nomeVistoriador) {
/* 103 */     this.nomeVistoriador = nomeVistoriador;
/*     */   }
/*     */   
/*     */   public String getEquipamentoNome() {
/* 107 */     return this.equipamentoNome;
/*     */   }
/*     */   
/*     */   public void setEquipamentoNome(String equipamentoNome) {
/* 111 */     this.equipamentoNome = equipamentoNome;
/*     */   }
/*     */   
            public String getSeqTipoCertificado() {
            return this.seqTipoCertificado;
            }
          
            public void setSeqTipoCertificado(String seqTipoCertificado) {
            this.seqTipoCertificado = seqTipoCertificado;
            }
/*    /   
/*     */   public String getSeqColaborador() {
/* 123 */     return this.seqColaborador;
/*     */   }
/*     */   
/*     */   public void setSeqColaborador(String seqColaborador) {
/* 127 */     this.seqColaborador = seqColaborador;
/*     */   }
/*     */   
/*     */   public String getObservacao() {
/* 131 */     return this.observacao;
/*     */   }
/*     */   
/*     */   public void setObservacao(String observacao) {
/* 135 */     this.observacao = observacao;
/*     */   }
/*     */
/*     */   public Date getEmstCarga() {
/*  67 */     return this.emstCarga;
/*     */   }

/*     */   public void setEmstCarga(Date emstCarga) {
/*  63 */     this.emstCarga = emstCarga;
/*     */   }
/*     */   public Date getValtCarga() {
/*  67 */     return this.valtCarga;
/*     */   }
/*     */   public void setValtCarga(Date valtCarga) {
/*  63 */     this.valtCarga = valtCarga;
/*     */   }
/*     */   
/*     */   
/*     */   public String getLocalEmissao() {
/* 147 */     return this.localEmissao;
/*     */   }
/*     */   
/*     */   public void setLocalEmissao(String localEmissao) {
/* 151 */     this.localEmissao = localEmissao;
/*     */   }
/*     */   
/*     */   public String getStatus() {
/* 155 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(String status) {
/* 159 */     this.status = status;
/*     */   }
/*     */   
/*     */   public String getAssinaturaDigital() {
/* 163 */     return this.assinaturaDigital;
/*     */   }
/*     */   
/*     */   public void setAssinaturaDigital(String assinaturaDigital) {
/* 167 */     this.assinaturaDigital = assinaturaDigital;
/*     */   }

            public String getNormas() {
/* 163 */     return this.normas;
/*     */   }
/*     */   
/*     */   public void setNormas(String normas) {
/* 167 */     this.normas = normas;
/*     */   }

            public String getRealtorioEnsaio() {
/* 163 */     return this.realtorioEnsaio;
/*     */   }
/*     */   
/*     */   public void setRealtorioEnsaio(String realtorioEnsaio) {
/* 167 */     this.realtorioEnsaio = realtorioEnsaio;
/*     */   }

            public String getRelatorioConformidade() {
/* 163 */     return this.relatorioConformidade;
/*     */   }
/*     */   
/*     */   public void setRelatorioConformidade(String relatorioConformidade) {
/* 167 */     this.relatorioConformidade = relatorioConformidade;
/*     */   }
            
            public String getConformidadeTecnica() {
/* 163 */     return this.conformidadeTecnica;
/*     */   }
/*     */   
/*     */   public void setConformidadeTecnica(String conformidadeTecnica) {
/* 167 */     this.conformidadeTecnica = conformidadeTecnica;
/*     */   }
/*     */   public String getFilial()
/*     */   {
/*  35 */     return this.filial;
/*     */   }
/*     */   
/*     */   public void setFilial(String filial) {
/*  39 */     this.filial = filial;
/*     */   }
            
            public String getSeqParceiro()
/*     */   {
/*  35 */     return this.seqParceiro;
/*     */   }
/*     */   
/*     */   public void setSeqParceiro(String seqParceiro) {
/*  39 */     this.seqParceiro = seqParceiro;
/*     */   }

    public List<EquipamentoParceiro> getListaEquipamentoParceiro() {
        return this.listaEquipamentoParceiro;
    }

    public void setListaEquipamentoParceiro(List<EquipamentoParceiro> listaequipamentoParceiro) {
        this.listaEquipamentoParceiro = listaequipamentoParceiro;
    }
    
    public String getNomeParceiro() {
        return this.nomeParceiro;
             }

    public void setNomeParceiro(String nomeParceiro) {
        this.nomeParceiro = nomeParceiro;
              }
    
    public String getUltimoEmitido() {
        return this.ultimoEmitido;
             }

    public void setUltimoEmitido(String ultimoEmitido) {
        this.ultimoEmitido = ultimoEmitido;
              }    
    
}

