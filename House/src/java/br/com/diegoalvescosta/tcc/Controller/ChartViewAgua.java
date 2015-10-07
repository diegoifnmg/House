package br.com.diegoalvescosta.tcc.Controller;


import javax.annotation.PostConstruct;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.LinearAxis;
 
@ManagedBean
public class ChartViewAgua implements Serializable {
 
    private LineChartModel multiAxisModel;
 
    @PostConstruct
    public void init() {
        createMultiAxisModel();
    }
 
    public LineChartModel getMultiAxisModel() {
        return multiAxisModel;
    }
     
    private void createMultiAxisModel() {
        multiAxisModel = new LineChartModel();
 
        BarChartSeries boys = new BarChartSeries();
        boys.setLabel("Boys");
 
        boys.set("Jan", 120);
        boys.set("Fev", 100);
        boys.set("Mar", 44);
        boys.set("Abr", 150);
        boys.set("Mai", 30);
 
        LineChartSeries girls = new LineChartSeries();
        girls.setLabel("Girls");
        girls.setXaxis(AxisType.X2);
        girls.setYaxis(AxisType.Y2);
         
        girls.set("A", 120);
        girls.set("B", 100);
        girls.set("C", 44);
        girls.set("D", 150);
        girls.set("E", 30);
 
        multiAxisModel.addSeries(boys);
        multiAxisModel.addSeries(girls);
         
        multiAxisModel.setTitle("Gráfico Consumo de Água");
        multiAxisModel.setMouseoverHighlight(false);
         
        multiAxisModel.getAxes().put(AxisType.X, new CategoryAxis("Mês"));
        multiAxisModel.getAxes().put(AxisType.X2, new CategoryAxis("Período"));
         
        Axis yAxis = multiAxisModel.getAxis(AxisType.Y);
        yAxis.setLabel("Valor");
        yAxis.setMin(0);
        yAxis.setMax(200);
                 
        Axis y2Axis = new LinearAxis("R$");
        y2Axis.setMin(0);
        y2Axis.setMax(200);
         
        multiAxisModel.getAxes().put(AxisType.Y2, y2Axis);
    }
 
}