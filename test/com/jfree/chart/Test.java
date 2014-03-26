package com.jfree.chart;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;

public class Test  extends ApplicationFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

        final Test demo = new Test("3D Bar Chart Demo sdsdd");

        demo.pack();

        RefineryUtilities.centerFrameOnScreen(demo);

        demo.setVisible(true);
	}
	public Test(String title){
		super(title);
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(23.0, "Series 1", "London");
        dataset.addValue(14.0, "Series 1", "New York");
        dataset.addValue(14.0, "Series 1", "Istanbul");
        dataset.addValue(14.0, "Series 1", "Cairo");
        dataset.addValue(13.0, "Series 2", "London");
        dataset.addValue(19.0, "Series 2", "New York");
        dataset.addValue(19.0, "Series 2", "Istanbul");
        dataset.addValue(19.0, "Series 2", "Cairo");
        dataset.addValue(7.0, "Series 3", "London");
        dataset.addValue(9.0, "Series 3", "New York");
        dataset.addValue(9.0, "Series 3", "Istanbul");
        dataset.addValue(9.0, "Series 3", "Cairo");

		 final JFreeChart chart = createChart(dataset);
	        final ChartPanel chartPanel = new ChartPanel(chart);

	        chartPanel.setPreferredSize(new java.awt.Dimension(800, 500));

	        setContentPane(chartPanel);
	}
    private JFreeChart createChart(final CategoryDataset dataset)
    {

        final JFreeChart chart = ChartFactory.createBarChart3D(

        "3D Bar Chart Demo z", // 柱状图标题

                "Category", // 分类栏标题

                "Value", // 数据栏标题

                dataset, // data

                PlotOrientation.VERTICAL, // orientation

                true, // include legend

                true, // tooltips

                false // urls

                );
        //获取柱状图plot对象
        final CategoryPlot plot = chart.getCategoryPlot();
        //柱状图透明度
        plot.setForegroundAlpha(0.8f);
        // 坐标轴到数据区的间距
        plot.setAxisOffset(new RectangleInsets(5d,5d,5d,5d));
        //数据区的方向
        plot.setOrientation(PlotOrientation.VERTICAL);
        //分类轴网格是否可见
        plot.setDomainGridlinesVisible(true);
        //分类轴网格线条颜色
        plot.setDomainGridlinePaint(Color.red);
        //数据轴网格是否可见
        plot.setRangeGridlinesVisible(true);
        //数据轴网格线条颜色
        plot.setRangeGridlinePaint(Color.red);
      //柱状图的renderer对象
        BarRenderer barrenderer = (BarRenderer) plot.getRenderer();
     // 外廓线是否可见
		barrenderer.setDrawBarOutline(false);
		//每个BAR之间的间隔
		barrenderer.setItemMargin(0.1d);
		//柱状颜色
//		barrenderer.setSeriesPaint(0 , Color.ORANGE);
//		barrenderer.setSeriesPaint(1 , Color.CYAN);
//		barrenderer.setSeriesPaint(2 , Color.PINK);
		  
		barrenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		//是否在柱状图上显示数据
		barrenderer.setBaseItemLabelsVisible(true);
		//柱状图上显示数据字体
		barrenderer.setBaseItemLabelFont(new Font("黑体", Font.BOLD, 20)); 
		//柱状图上显示数据方位与锚点
		barrenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));
        
        //柱状图axis对象
//        final CategoryAxis axis = plot.getDomainAxis();
//
//        final CategoryLabelPositions p = axis.getCategoryLabelPositions();
//
//        final CategoryLabelPosition left = new CategoryLabelPosition(
//
//        RectangleAnchor.LEFT, TextBlockAnchor.BOTTOM_CENTER,
//
//        TextAnchor.CENTER_LEFT, 20,
//
//        CategoryLabelWidthType.RANGE, 12f
//
//        );
//
//        axis.setCategoryLabelPositions(CategoryLabelPositions
//                .replaceLeftPosition(p, left));

        return chart;

    }

}
