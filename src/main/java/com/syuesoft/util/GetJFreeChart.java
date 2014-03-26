package com.syuesoft.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.RenderingHints;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardCategorySeriesLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

import com.opensymphony.xwork2.ActionSupport;


public class GetJFreeChart extends ActionSupport {
	private JFreeChart chart;
	
	public JFreeChart getChart() {
		return chart;
	}
	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}

	private static final long serialVersionUID = -4767211955516665519L;


	
	public String execute()throws Exception{
		chart = ChartFactory.createBarChart("�ͻ������ͳ��ͼ", "ͳ���·�","�ط���",  createCategoryDataset(), 
                PlotOrientation.VERTICAL,  true,false,  false);
		chart.setBackgroundPaint(new GradientPaint(0.0F, 0.0F, Color.LIGHT_GRAY,  
                10000F, 0.0F, Color.YELLOW));  
		
		chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,  
                RenderingHints.VALUE_TEXT_ANTIALIAS_OFF); 
		
		Font font = new Font("����", Font.TRUETYPE_FONT, 18);
		
		TextTitle title = chart.getTitle();
		title.setFont(new Font("SimSun", Font.BOLD, 24));
		title.setMargin(5.0, 0, 5.0, 0);
		
		LegendTitle legend = (LegendTitle) chart.getLegend();
		legend.setItemFont(font); 
		
		Plot plot = chart.getPlot(); 
		plot.setNoDataMessage("û�������ʾ");
		plot.setNoDataMessageFont(font);
		plot.setNoDataMessagePaint(Color.RED);
		plot.setBackgroundAlpha(0.8F);
		
		CategoryPlot categoryplot = (CategoryPlot) plot;
		categoryplot.setForegroundAlpha(0.7F);

		categoryplot.setDomainGridlinePaint(Color.RED);
		categoryplot.setDomainGridlinesVisible(true);
		categoryplot.setRangeGridlinesVisible(true);
		categoryplot.setRangeGridlinePaint(Color.RED);
		
		categoryplot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
		
		ValueAxis valueAxis = categoryplot.getRangeAxis();
		valueAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		valueAxis.setLabelFont(font);
		valueAxis.setLabelPaint(Color.RED);
		valueAxis.setTickLabelFont(font);
		valueAxis.setTickLabelPaint(Color.RED);
		
		BarRenderer barrenderer = (BarRenderer) categoryplot.getRenderer();
		barrenderer.setDrawBarOutline(false);// ���������߲��ɼ�
		
		barrenderer
		.setLegendItemToolTipGenerator(new StandardCategorySeriesLabelGenerator(
				"Tooltip: {0}"));
		
		barrenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());  
		barrenderer.setBaseItemLabelFont(font);  
		barrenderer.setBaseItemLabelsVisible(true);  
		barrenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));   
		barrenderer.setItemMargin(0.0);
		
		CategoryAxis categoryaxis = categoryplot.getDomainAxis();
		categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		categoryaxis.setLabelFont(font);
		categoryaxis.setLabelPaint(Color.RED);
		categoryaxis.setTickLabelFont(font);
		categoryaxis.setTickLabelPaint(Color.RED);
		
		return SUCCESS;
	}
	
	private CategoryDataset createCategoryDataset() {
		DefaultCategoryDataset categorydataset = new DefaultCategoryDataset();  
        categorydataset.addValue(1, "����", "һ��");  
        categorydataset.addValue(2, "������", "һ��");  
        categorydataset.addValue(1, "һ��", "һ��");  
        categorydataset.addValue(5, "������", "һ��");  
        categorydataset.addValue(7, "�ܲ���", "һ��");  
        categorydataset.addValue(3, "����", "����");  
        categorydataset.addValue(1, "������", "����");  
        categorydataset.addValue(4, "һ��", "����");  
        categorydataset.addValue(2, "������", "����");  
        categorydataset.addValue(4, "�ܲ���", "����");
        categorydataset.addValue(3, "����", "����");  
        categorydataset.addValue(1, "������", "����");  
        categorydataset.addValue(4, "һ��", "����");  
        categorydataset.addValue(2, "������", "����");  
        categorydataset.addValue(4, "�ܲ���", "����");
        
        categorydataset.addValue(9, "����", "����");  
        categorydataset.addValue(2, "������", "����");  
        categorydataset.addValue(1, "һ��", "����");  
        categorydataset.addValue(0, "������", "����");  
        categorydataset.addValue(7, "�ܲ���", "����"); 
        return categorydataset;  
	}

}
