/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.PieChartModel;

@ManagedBean(name="GraficoPizzaMB")
public class GraficoPizza implements Serializable {

    private PieChartModel pieModel1;
    private PieChartModel pieModel12;
    private PieChartModel pieModel13;

    @PostConstruct
    public void init() {
        createPieModels();
        createPieModels2();
        createPieModels3();
    }

    public PieChartModel getPieModel1() {
        return pieModel1;
    }
    
    public PieChartModel getPieModel12() {
        return pieModel12;
    }

    public PieChartModel getPieModel13() {
        return pieModel13;
    }    

    private void createPieModels() {
        createPieModel1();
    }
    
    private void createPieModels2() {
        createPieModel12();
    }

    private void createPieModels3() {
        createPieModel13();
    }    

    private void createPieModel1() {
        pieModel1 = new PieChartModel();

        pieModel1.set("Emitidas", 360);
        pieModel1.set("Aprovadas", 281);
        pieModel1.set("Reprovadas", 79);
        pieModel1.set("Canceladas", 50);
        
        pieModel1.setTitle("Propostas Emitidas");
        pieModel1.setLegendPosition("w");
    }
    
    private void createPieModel12() {
        pieModel12 = new PieChartModel();

        pieModel12.set("Vistoria", 150);
        pieModel12.set("Troca de Equipamento", 30);
        pieModel12.set("Cabeamento", 8);
        pieModel12.set("Laudo", 90);
        pieModel12.set("Prototipagem", 3);

        pieModel12.setTitle("Serviços");
        pieModel12.setLegendPosition("w");
    }
    
    private void createPieModel13() {
        pieModel13 = new PieChartModel();

        pieModel13.set("Mary", 93);
        pieModel13.set("Dani", 78);
        pieModel13.set("Yuri", 66);
        pieModel13.set("Carlos", 16);
        pieModel13.set("MArina", 28);

        pieModel13.setTitle("Comercial");
        pieModel13.setLegendPosition("w");
    }


}