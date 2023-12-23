/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;

@ManagedBean(name="GraficoAreaMB")
public class GraficoArea implements Serializable {

    private BarChartModel areaModel;

    @PostConstruct
    public void init() {
        createAreaModel();
    }

    public BarChartModel getAreaModel() {
        return areaModel;
    }

    private void createAreaModel() {
        areaModel = new BarChartModel();

        BarChartSeries reservatorio1 = new BarChartSeries();
       
        reservatorio1.setLabel("Filial RJ");
        reservatorio1.set("2018", 400);
        reservatorio1.set("2019", 580);
        reservatorio1.set("2020", 180);
        reservatorio1.set("2021", 220);
        reservatorio1.set("2022", 360);        
        
        BarChartSeries reservatorio2 = new BarChartSeries();
        
        reservatorio2.setLabel("Filial SP");
        reservatorio2.set("2018", 340);
        reservatorio2.set("2019", 390);
        reservatorio2.set("2020", 90);
        reservatorio2.set("2021", 110);
        reservatorio2.set("2022", 300);

        BarChartSeries reservatorio3 = new BarChartSeries();
       
        reservatorio3.setLabel("Filial MG");
        reservatorio3.set("2018", 52);
        reservatorio3.set("2019", 65);
        reservatorio3.set("2020", 13);
        reservatorio3.set("2021", 18);
        reservatorio3.set("2022", 38);

        areaModel.addSeries(reservatorio1);
        areaModel.addSeries(reservatorio2);
        areaModel.addSeries(reservatorio3);

        areaModel.setTitle("Negócios");
        areaModel.setLegendPosition("ne");
        
        areaModel.setShowPointLabels(true);

        Axis xAxis = new CategoryAxis("");
        areaModel.getAxes().put(AxisType.X, xAxis);
        Axis yAxis = areaModel.getAxis(AxisType.Y);
        yAxis.setLabel("");
        yAxis.setMin(0);
        yAxis.setMax(1200);
    }

}