package Util;

import java.util.Date;
import org.primefaces.model.DefaultScheduleEvent;



public class EventoAdicionais
  extends DefaultScheduleEvent
{
  String seqParceiro;
  String cor;
  String seqTipoAgenda;
  String nomeParceiro;
  String nomeTipoAgenda;
  String responsavel;
  
  public EventoAdicionais() {}
  
  public EventoAdicionais(String title, Date start, Date end, String pSeqParceiro)
  {
    super(title, start, end);
    this.seqParceiro = pSeqParceiro;
  }
  
  public String getSeqParceiro() {
    return this.seqParceiro;
  }
  
  public void setSeqParceiro(String seqParceiro) {
    this.seqParceiro = seqParceiro;
  }
  
  public String getCor() {
    return this.cor;
  }
  
  public void setCor(String cor) {
    this.cor = cor;
  }
  
  public String getSeqTipoAgenda() {
    return this.seqTipoAgenda;
  }
  
  public void setSeqTipoAgenda(String seqTipoAgenda) {
    this.seqTipoAgenda = seqTipoAgenda;
  }
  
   public String getNomeParceiro() {
    return this.nomeParceiro;
  }
  
  public void setNomeParceiro(String nomeParceiro) {
    this.nomeParceiro = nomeParceiro;
  }
  
     public String getNomeTipoAgenda() {
    return this.nomeTipoAgenda;
  }
  
  public void setNomeTipoAgenda(String nomeTipoAgenda) {
    this.nomeTipoAgenda = nomeTipoAgenda;
  }

    public String getResponsavel() {
        return this.responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    } 
  
}


