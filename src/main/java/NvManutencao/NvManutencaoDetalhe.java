/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NvManutencao;

import java.math.BigDecimal;

/**
 *
 * @author rober
 */
public class NvManutencaoDetalhe {
    private String seqManutencaoDetalhe;
    private String seqManutencao;
    private String descricao;
    private BigDecimal valor;
    
    private NvManutencao nvManutencao;
    public NvManutencaoDetalhe () {
            }

    public String getSeqManutencaoDetalhe() {
        return seqManutencaoDetalhe;
    }

    public void setSeqManutencaoDetalhe(String seqManutencaoDetalhe) {
        this.seqManutencaoDetalhe = seqManutencaoDetalhe;
    }

    public String getSeqManutencao() {
        return seqManutencao;
    }

    public void setSeqManutencao(String seqManutencao) {
        this.seqManutencao = seqManutencao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public NvManutencao getNvManutencao() {
        return nvManutencao;
    }

    public void setNvManutencao(NvManutencao nvManutencao) {
        this.nvManutencao = nvManutencao;
    }
    
}
