/**   
 *  Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 * @Title: BarChart3DDemo2.java 
 * @Package com.jfree.chart 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author HeXin  
 * @date 2013-8-15 上午10:04:18 
 * @version V1.0   
 */
package com.jfree.chart;

import java.awt.Color;  
import java.awt.Font;  
import java.io.FileOutputStream;  
import java.io.IOException;  
  
import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartUtilities;  
import org.jfree.chart.JFreeChart;  
import org.jfree.chart.axis.CategoryAxis;  
import org.jfree.chart.axis.NumberAxis;  
import org.jfree.chart.plot.CategoryPlot;  
import org.jfree.chart.plot.PlotOrientation;  
import org.jfree.chart.title.LegendTitle;  
import org.jfree.chart.title.TextTitle;  
import org.jfree.data.category.CategoryDataset;  
import org.jfree.data.category.DefaultCategoryDataset;   

/**
 * 
 * A simple demonstration application showing how to create a horizontal 3D bar
 * chart using data
 * 
 * from a {@link CategoryDataset}.
 * 
 * 
 */

public class BarChart3DDemo4
{

    public static void main(String[] args) throws IOException {  
        //生成3D折线图（柱状图只改方法名createLineChart3D就可以了）  
        JFreeChart chart = ChartFactory.createLineChart3D (  
                "图书销售统计表",     //图表标题  
                "图书",             //目录轴的显示标签  
                "销量",             //数值轴的显示标签  
                getDateSet(),      //数据  
                //PlotOrientation.HORIZONTAL, //图表方向水平  
                PlotOrientation.VERTICAL,    //图表方向垂直  
                true,             //是否显示图例  
                false,            //是否显示工具提示  
                false             //是否生成URL  
        );  
        //设置标题及标题字体  
        chart.setTitle(new TextTitle("图书销售统计图",new Font("黑体",Font.ITALIC,22)));  
        //建一个图例  
        LegendTitle legendTitle = chart.getLegend(0);  
        //设置图例字体  
        legendTitle.setItemFont(new Font("宋体",Font.BOLD,14));  
        //获取折线图plot对象  
        CategoryPlot plot = (CategoryPlot) chart.getPlot();  
        //设置折线的颜色  
        plot.getRenderer().setSeriesPaint(0, Color.BLUE);  
        plot.getRenderer().setSeriesPaint(1, Color.GREEN);  
        plot.getRenderer().setSeriesPaint(2, Color.ORANGE);  
        //取得横轴  
        CategoryAxis categoryAxis = plot.getDomainAxis();  
        //设置横轴的字体  
        categoryAxis.setLabelFont(new Font("宋体",Font.BOLD,22));  
        //设置分类标签以45度倾斜  
        //categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);  
        //设置分类标签字体  
        categoryAxis.setTickLabelFont(new Font("宋体",Font.BOLD,22));  
        //取得纵轴  
        NumberAxis numberAxis = (NumberAxis) plot.getRangeAxis();  
        //设置纵轴的字体  
        numberAxis.setLabelFont(new Font("宋体",Font.BOLD,22));  
        //设置背景透明度（0~1）  
        plot.setBackgroundAlpha(0.9f);  
        //设置前景色透明度（0~1）  
        plot.setForegroundAlpha(0.5f);  
        //输出文件  
        FileOutputStream fos = new FileOutputStream("d:\\book.jpg");  
        //用ChartUtilities工具输出  
        ChartUtilities.writeChartAsJPEG(fos, chart, 800, 600);  
        fos.close();  
    }  
      
    private static CategoryDataset getDateSet() {  
        //提供生成折线图的数据  
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
        //dataset.addValue(47,"","JAVA教程");  
        //dataset.addValue(23,"","c++教程");  
        //dataset.addValue(20,"","C#教程");  
        //dataset.addValue(10,"","VC++教程");  
          
        //生成复杂带图例的柱状图  
        dataset.addValue(47,"北京","JAVA教程");  
        dataset.addValue(23,"北京","c++教程");  
        dataset.addValue(20,"北京","C#教程");  
        dataset.addValue(10,"北京","VC++教程");  
        dataset.addValue(40,"天津","JAVA教程");  
        dataset.addValue(20,"天津","c++教程");  
        dataset.addValue(35,"天津","C#教程");  
        dataset.addValue(5,"天津","VC++教程");  
        dataset.addValue(30,"上海","JAVA教程");  
        dataset.addValue(28,"上海","c++教程");  
        dataset.addValue(22,"上海","C#教程");  
        dataset.addValue(20,"上海","VC++教程");  
        return dataset;  
    }  

}