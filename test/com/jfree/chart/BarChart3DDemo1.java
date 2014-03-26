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

import org.jfree.chart.ChartFactory;

import org.jfree.chart.ChartPanel;

import org.jfree.chart.JFreeChart;

import org.jfree.chart.axis.CategoryAxis;

import org.jfree.chart.axis.CategoryLabelPosition;

import org.jfree.chart.axis.CategoryLabelPositions;

import org.jfree.chart.axis.CategoryLabelWidthType;

import org.jfree.chart.plot.CategoryPlot;

import org.jfree.chart.plot.PlotOrientation;

import org.jfree.data.category.CategoryDataset;

import org.jfree.data.category.DefaultCategoryDataset;

import org.jfree.text.TextBlockAnchor;

import org.jfree.ui.ApplicationFrame;

import org.jfree.ui.RectangleAnchor;

import org.jfree.ui.RefineryUtilities;

import org.jfree.ui.TextAnchor;

import org.jfree.util.Log;

import org.jfree.util.PrintStreamLogTarget;

/**
 * 
 * A simple demonstration application showing how to create a horizontal 3D bar
 * chart using data
 * 
 * from a {@link CategoryDataset}.
 * 
 * 
 */

public class BarChart3DDemo1 extends ApplicationFrame
{

    /**
     * 
     * Creates a new demo.
     * 
     * 
     * 
     * @param title
     *            the frame title.
     */

    public BarChart3DDemo1(final String title)
    {

        super(title);

        // create the chart...

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

        // add the chart to a panel...

        final ChartPanel chartPanel = new ChartPanel(chart);

        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));

        setContentPane(chartPanel);

    }

    /**
     * 
     * Creates a chart.
     * 
     * 
     * 
     * @param dataset
     *            the dataset.
     * 
     * 
     * 
     * @return The chart.
     */

    private JFreeChart createChart(final CategoryDataset dataset)
    {

        final JFreeChart chart = ChartFactory.createBarChart3D(

        "3D Bar Chart Demo 2", // chart title

                "Category", // domain axis label

                "Value", // range axis label

                dataset, // data

                PlotOrientation.VERTICAL, // orientation

                true, // include legend

                true, // tooltips

                false // urls

                );

        final CategoryPlot plot = chart.getCategoryPlot();

        plot.setForegroundAlpha(1.0f);

        // left align the category labels...

        final CategoryAxis axis = plot.getDomainAxis();

        final CategoryLabelPositions p = axis.getCategoryLabelPositions();

        final CategoryLabelPosition left = new CategoryLabelPosition(

        RectangleAnchor.LEFT, TextBlockAnchor.CENTER_LEFT,

        TextAnchor.CENTER_LEFT, 0.0,

        CategoryLabelWidthType.RANGE, 0.30f

        );

        axis.setCategoryLabelPositions(CategoryLabelPositions
                .replaceLeftPosition(p, left));

        return chart;

    }

    /**
     * 
     * Starting point for the demonstration application.
     * 
     * 
     * 
     * @param args
     *            ignored.
     */

    public static void main(final String[] args)
    {

        Log.getInstance().addTarget(new PrintStreamLogTarget());

        final BarChart3DDemo1 demo = new BarChart3DDemo1("3D Bar Chart Demo 2");

        demo.pack();

        RefineryUtilities.centerFrameOnScreen(demo);

        demo.setVisible(true);

    }

}