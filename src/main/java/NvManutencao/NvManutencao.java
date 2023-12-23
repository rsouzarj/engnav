/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NvManutencao;

import java.util.Date;

/**
 *
 * @author rober
 */
public class NvManutencao {
    private String seqmanutencao;
    private String seqNvEmbarcacao;
    private String seqNvEquipamento;
    private Date data;
    private Date dataGarantia;
    private String detalhe;
    private String seqEmpresa;

    public String getSeqEmpresa() {
        return seqEmpresa;
    }

    public void setSeqEmpresa(String seqEmpresa) {
        this.seqEmpresa = seqEmpresa;
    }

    public NvManutencao() {
        }

    public String getSeqmanutencao() {
        return seqmanutencao;
    }

    public void setSeqmanutencao(String seqmanutencao) {
        this.seqmanutencao = seqmanutencao;
    }

    public String getSeqNvEmbarcacao() {
        return seqNvEmbarcacao;
    }

    public void setSeqNvEmbarcacao(String seqNvEmbarcacao) {
        this.seqNvEmbarcacao = seqNvEmbarcacao;
    }

    public String getSeqNvEquipamento() {
        return seqNvEquipamento;
    }

    public void setSeqNvEquipamento(String seqNvEquipamento) {
        this.seqNvEquipamento = seqNvEquipamento;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getDataGarantia() {
        return dataGarantia;
    }

    public void setDataGarantia(Date dataGarantia) {
        this.dataGarantia = dataGarantia;
    }

    public String getDetalhe() {
        return detalhe;
    }

    public void setDetalhe(String detalhe) {
        this.detalhe = detalhe;
    }
    
    
    
    
}
