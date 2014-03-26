package com.syuesoft.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.TextAnchor;
/**
 * 报表图生成类
 * @author LWJ
 * */
public class CreateJFreeChart {
	/**
	 * 柱状图
		@param poleMapName="无标题" 图表标题
		@param poleXName="X轴" 分类轴显示标签
		@param poleYName="Y轴" 数据轴显示标签
		@param poleMapList=null 数据集合
		@param flag=false 2D,3D标示，true为3D
		@return JFreeChart 返回JFreeChart对象
	 * */
	public static JFreeChart findPoleMap(String poleMapName,String poleXName,String poleYName,List<PoleMap> poleMapList,Boolean flag){
		return findPoleMap(poleMapName,poleXName,poleYName,poleMapList,null,null,null,null,null,null,null,null,null,null,null,
				null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,flag);
	}
	/**
	 * 柱状图
		@param poleMapName="无标题" 图表标题
		@param poleXName="X轴" 分类轴显示标签
		@param poleYName="Y轴" 数据轴显示标签
		@param poleMapList=null 数据集合
		@param itemMargin=0.1d 每个BAR之间的间隔
		@param flag=false 2D,3D标示，true为3D
		@return JFreeChart 返回JFreeChart对象
	 * */
	public static JFreeChart findPoleMap(String poleMapName,String poleXName,String poleYName,List<PoleMap> poleMapList,Double itemMargin,Boolean flag){
		return findPoleMap(poleMapName,poleXName,poleYName,poleMapList,null,null,null,null,null,null,null,null,itemMargin,null,null,
				null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,flag);
	}
	/**
	 * 柱状图
		@param poleMapName="无标题" 图表标题
		@param poleXName="X轴" 分类轴显示标签
		@param poleYName="Y轴" 数据轴显示标签
		@param poleMapList=null 数据集合
		@param dataOrientation=PlotOrientation.VERTICAL 数据方向
		@param foregroundAlpha=0.8f 柱状图透明度
		@param axisOffset=5d 坐标轴到数据区的间距
		@param poleXGridDisable=false 分类轴网格是否可见
		@param poleXGridColor=Color.red 分类轴网格线条颜色
		@param poleYGridDisable=true 数据轴网格是否可见
		@param poleYGridColor=Color.red 数据轴网格线条颜色
		@param barOutlineDisable=false 外廓线是否可见
		@param itemMargin=0.1d 每个BAR之间的间隔
		@param itemLabel_font_name="宋体" 柱状图上显示数据字体
		@param itemLabel_font_style=Font.PLAIN 柱状图上显示数据样式
		@param itemLabel_font_size=12 柱状图上显示数据大小
		@param itemLabelDisable=false 是否在柱状图上显示数据
		@param itemLabelAnchor=ItemLabelAnchor.OUTSIDE12 柱状图上显示数据的方位
		@param textAnchor=TextAnchor.BASELINE_CENTER 柱状图上显示数据的锚点
		@param poleMapName_font_name="宋体" 图表标题字体
		@param poleMapName_font_style=Font.BOLD 图表标题样式
		@param poleMapName_font_size=20 图表标题大小
		@param poleMapItem_font_name="宋体" 图例字体
		@param poleMapItem_font_style=Font.PLAIN 图例风格
		@param poleMapItem_font_size=14 图例大小
		@param poleX_font_name="宋体" 目录轴的显示标签字体 
		@param poleX_font_style=Font.PLAIN 目录轴的显示标签风格
		@param poleX_font_size=14 目录轴的显示标签大小
		@param poleY_font_name="宋体" 数值轴的显示标签字体
		@param poleY_font_style=Font.PLAIN 数值轴的显示标签风格
		@param poleY_font_size=14 数值轴的显示标签大小
		@param poleX_font_angle=CategoryLabelPositions.UP_45 目录轴的显示标签倾斜度
		@param poleXData_font_name="宋体" 目录轴数据字体 
		@param poleXData_font_style=Font.PLAIN 目录轴数据风格
		@param poleXData_font_size=14 目录轴数据大小
		@param flag=false 2D,3D标示，true为3D
		@return JFreeChart 返回JFreeChart对象
	 * */
	public static JFreeChart findPoleMap(String poleMapName,String poleXName,String poleYName,List<PoleMap> poleMapList,PlotOrientation dataOrientation,
			Float foregroundAlpha,Double axisOffset,Boolean poleXGridDisable,Color poleXGridColor,Boolean poleYGridDisable,Color poleYGridColor,
				Boolean barOutlineDisable,Double itemMargin,String itemLabel_font_name,Integer itemLabel_font_style,Integer itemLabel_font_size,
				Boolean itemLabelDisable,ItemLabelAnchor itemLabelAnchor,TextAnchor textAnchor,
				String poleMapItem_font_name,Integer poleMapItem_font_style,Integer poleMapItem_font_size,
				String poleX_font_name,Integer poleX_font_style,Integer poleX_font_size,
				String poleY_font_name,Integer poleY_font_style,Integer poleY_font_size,
				String poleMapName_font_name,Integer poleMapName_font_style,Integer poleMapName_font_size,
				CategoryLabelPositions poleX_font_angle,String poleXData_font_name,Integer poleXData_font_style,Integer poleXData_font_size,
				Boolean flag)
    {
		if(poleMapName==null||poleMapName.length()==0)
			poleMapName="无标题";//图表标题
		if(poleXName==null||poleXName.length()==0)
			poleXName="X轴";//分类轴显示标签
		if(poleYName==null||poleYName.length()==0)
			poleYName="Y轴";//数据轴显示标签
		if(dataOrientation==null)
			dataOrientation=PlotOrientation.VERTICAL;//数据方向
		if(foregroundAlpha==null||foregroundAlpha.toString().length()==0)
			foregroundAlpha=0.8f;//柱状图透明度
		if(axisOffset==null||axisOffset.toString().length()==0)
			axisOffset=5d;//坐标轴到数据区的间距
		if(poleXGridDisable==null)
			poleXGridDisable=false;//分类轴网格是否可见
		if(poleXGridColor==null)
			poleXGridColor=Color.red;//分类轴网格线条颜色
		if(poleYGridDisable==null)
			poleYGridDisable=true;//数据轴网格是否可见
		if(poleYGridColor==null)
			poleYGridColor=Color.red;//数据轴网格线条颜色
		if(barOutlineDisable==null)
			barOutlineDisable=false;//外廓线是否可见
		if(itemMargin==null||itemMargin.toString().length()==0)
			itemMargin=0.1d;//每个BAR之间的间隔
		if(itemLabel_font_name==null||itemLabel_font_name.length()==0)
			itemLabel_font_name="宋体";//柱状图上显示数据字体
		if(itemLabel_font_style==null||itemLabel_font_style.toString().length()==0)
			itemLabel_font_style=Font.PLAIN;//柱状图上显示数据样式
		if(itemLabel_font_size==null||itemLabel_font_size.toString().length()==0)
			itemLabel_font_size=12;//柱状图上显示数据大小
		if(itemLabelDisable==null)
			itemLabelDisable=false;//是否在柱状图上显示数据
		if(itemLabelAnchor==null)
			itemLabelAnchor=ItemLabelAnchor.OUTSIDE12;//柱状图上显示数据的方位
		if(textAnchor==null)
			textAnchor=TextAnchor.BASELINE_CENTER;//柱状图上显示数据的锚点
		if(poleMapName_font_name==null||poleMapName_font_name.length()==0)
			poleMapName_font_name="宋体";//图表标题字体
		if(poleMapName_font_style==null||poleMapName_font_style.toString().length()==0)
			poleMapName_font_style=Font.BOLD;//图表标题样式
		if(poleMapName_font_size==null||poleMapName_font_size.toString().length()==0)
			poleMapName_font_size=20;//图表标题大小
		if(poleMapItem_font_name==null||poleMapItem_font_name.length()==0)
			poleMapItem_font_name="宋体";//图例字体
		if(poleMapItem_font_style==null||poleMapItem_font_style.toString().length()==0)
			poleMapItem_font_style=Font.PLAIN;//图例风格
		if(poleMapItem_font_size==null||poleMapItem_font_size.toString().length()==0)
			poleMapItem_font_size=14;//图例大小
		if(poleX_font_name==null||poleX_font_name.length()==0)
			poleX_font_name="宋体";//目录轴的显示标签字体 
		if(poleX_font_style==null||poleX_font_style.toString().length()==0)
			poleX_font_style=Font.PLAIN;//目录轴的显示标签风格
		if(poleX_font_size==null||poleX_font_size.toString().length()==0)
			poleX_font_size=14;//目录轴的显示标签大小
		if(poleY_font_name==null||poleY_font_name.length()==0)
			poleY_font_name="宋体";//数值轴的显示标签字体
		if(poleY_font_style==null||poleY_font_style.toString().length()==0)
			poleY_font_style=Font.PLAIN;//数值轴的显示标签风格
		if(poleY_font_size==null||poleY_font_size.toString().length()==0)
			poleY_font_size=14;//数值轴的显示标签大小
		if(poleX_font_angle==null)
			poleX_font_angle=CategoryLabelPositions.UP_45;//目录轴的显示标签倾斜度
		if(poleXData_font_name==null||poleXData_font_name.length()==0)
			poleXData_font_name="宋体";//目录轴数据字体 
		if(poleXData_font_style==null||poleXData_font_style.toString().length()==0)
			poleXData_font_style=Font.PLAIN;//目录轴数据风格
		if(poleXData_font_size==null||poleXData_font_size.toString().length()==0)
			poleXData_font_size=14;//目录轴数据大小
		if(flag==null)
			flag=false;//2D,3D标示，true为3D
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		if(poleMapList!=null&&poleMapList.size()>0){
			for (PoleMap poleMap : poleMapList) {
				dataset.addValue(poleMap.getPoleYData(),poleMap.getPoleBarName(), poleMap.getPoleXData());				
			}
		}
        JFreeChart chart = null;
        if(flag){
        	chart=ChartFactory.createBarChart3D(
	        			poleMapName, // 图表标题
	        			poleXName, // 目录轴标签
	        			poleYName, // 数据轴标签
	        			dataset, // 柱状图数据
	        			PlotOrientation.VERTICAL,
	        			true,true,false
	        	  );
        }else{
        	chart=ChartFactory.createBarChart(
        			poleMapName, // 图表标题
        			poleXName, // 目录轴标签
        			poleYName, // 数据轴标签
        			dataset, // 柱状图数据
        			PlotOrientation.VERTICAL,
        			true,true,false
        	  );
        }
        
        //建一个图例  
        LegendTitle legendTitle = chart.getLegend(0);  
        //设置图例字体  
        legendTitle.setItemFont(new Font(poleMapItem_font_name,poleMapItem_font_style,poleMapItem_font_size));
        //获取柱状图plot对象
        CategoryPlot plot = chart.getCategoryPlot();
        //柱状图透明度
        plot.setForegroundAlpha(foregroundAlpha);
        // 坐标轴到数据区的间距
        plot.setAxisOffset(new RectangleInsets(axisOffset,axisOffset,axisOffset,axisOffset));
        //数据区的方向
        plot.setOrientation(dataOrientation);
        //分类轴网格是否可见
        plot.setDomainGridlinesVisible(poleXGridDisable);
        //分类轴网格线条颜色
        plot.setDomainGridlinePaint(poleXGridColor);
        //数据轴网格是否可见
        plot.setRangeGridlinesVisible(poleYGridDisable);
        //数据轴网格线条颜色
        plot.setRangeGridlinePaint(poleYGridColor);
        
        //取得横轴  
        CategoryAxis categoryAxis = plot.getDomainAxis();  
        //设置横轴的字体  
        categoryAxis.setLabelFont(new Font(poleX_font_name,poleX_font_style,poleX_font_size));  
        //设置分类标签以？度倾斜  
        categoryAxis.setCategoryLabelPositions(poleX_font_angle);  
        //设置分类标签字体  
        categoryAxis.setTickLabelFont(new Font(poleXData_font_name,poleXData_font_style,poleXData_font_size));  //X轴
        //取得纵轴  
        NumberAxis numberAxis = (NumberAxis) plot.getRangeAxis();  
        //设置纵轴的字体  
        numberAxis.setLabelFont(new Font(poleY_font_name,poleY_font_style,poleY_font_size));
        
      //柱状图的renderer对象
        BarRenderer barrenderer = (BarRenderer) plot.getRenderer();
     // 外廓线是否可见
		barrenderer.setDrawBarOutline(barOutlineDisable);
		//每个BAR之间的间隔
		barrenderer.setItemMargin(itemMargin);
		//柱状颜色
		if(poleMapList!=null&&poleMapList.size()>0){
			int i=0;
			for (PoleMap poleMap : poleMapList) {
				barrenderer.setSeriesPaint(i , poleMap.getPoleBarColor());
				i++;
			}
		}
		barrenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		//是否在柱状图上显示数据
		barrenderer.setBaseItemLabelsVisible(itemLabelDisable);
		//柱状图上显示数据字体
		barrenderer.setBaseItemLabelFont(new Font(itemLabel_font_name, itemLabel_font_style,itemLabel_font_size)); 
		//柱状图上显示的数据方位与锚点
		barrenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(itemLabelAnchor,textAnchor));
        // 定义字体格式  
        Font font = new java.awt.Font(poleMapName_font_name,poleMapName_font_style,poleMapName_font_size);  
        TextTitle title = new TextTitle(poleMapName);  
        title.setFont(font);  
        // 设置字体,非常关键不然会出现乱码的,下方的字体  
        chart.setTitle(title);
        return chart;
    }
	/**
	 * 饼图
		@param cakeMapName="无标题" 图标标题
		@param cakeHashMap=null 数据集合(使用默认颜色)
		@param isShowScale=false 是否显示比例,true为显示
		@param flag=false 2D,3D标示，true为3D
		@return JFreeChart 返回JFreeChart对象
	 * */
	public static JFreeChart findCakeMap(String cakeMapName,HashMap<String,Double> cakeHashMap,Boolean isShowScale,Boolean flag){
		return findCakeMap(cakeMapName,cakeHashMap,
				null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,isShowScale,flag);
	}
	/**
	 * 饼图
		@param cakeMapName="无标题" 图标标题
		@param cakeMapList=null 数据集合(自定义颜色)
		@param isShowScale=false 是否显示比例,true为显示
		@param flag=false 2D,3D标示，true为3D
		@return JFreeChart 返回JFreeChart对象
	 * */
	public static JFreeChart findCakeMap(String cakeMapName,List<CakeMap> cakeMapList,Boolean isShowScale,Boolean flag){
		return findCakeMap(cakeMapName,null,
				null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,cakeMapList,isShowScale,flag);
	}
	/**
	 * 饼图
		@param cakeMapName="无标题" 图标标题
		@param cakeHashMap=null 数据集合(使用默认颜色)
		@param backgroundColor=Color.WHITE 图片背景色
		@param borderColor=Color.WHITE 图形边框颜色
		@param borderWidth=0.1f 图形边框宽度
		@param foregroundAlpha=0.8f 饼图透明度
		@param circular=true 是否正圆
		@param startAngle=0 饼图初始角度
		@param explodePercentKey="无" 突出显示的数据块 名称
		@param explodePercentValue=0.1d 突出显示的数据块间距
		@param label_font_name="宋体" 标签字体 
		@param label_font_style=Font.ITALIC 标签字体样式
		@param label_font_size=14 标签字体大小
		@param item_font_name="宋体" 图例说明字体
		@param item_font_style=Font.PLAIN 图例说明字体样式
		@param item_font_size=14 图例说明字体大小
		@param cakeMapName_font_name="黑体" 标题字体
		@param cakeMapName_font_style=Font.BOLD 标题字体样式
		@param cakeMapName_font_size=20 标题字体大小
		@param cakeMapList=null 数据集合(自定义颜色)
		@param isShowScale=false 是否显示比例,true为显示
		@param flag=false 2D,3D标示，true为3D
		@return JFreeChart 返回JFreeChart对象
	 * */
	public static JFreeChart findCakeMap(String cakeMapName,HashMap<String,Double> cakeHashMap,
				Color backgroundColor,Color borderColor,Float borderWidth,Float foregroundAlpha,Boolean circular,
				Integer startAngle,String explodePercentKey,Double explodePercentValue,
				String label_font_name,Integer label_font_style,Integer label_font_size,
				String item_font_name,Integer item_font_style,Integer item_font_size,
				String cakeMapName_font_name,Integer cakeMapName_font_style,Integer cakeMapName_font_size,
				List<CakeMap> cakeMapList,Boolean isShowScale,Boolean flag){
		if(cakeMapName==null||cakeMapName.length()==0)
			cakeMapName="无标题";//图标标题
		if(backgroundColor==null)
			backgroundColor=Color.WHITE;//图片背景色
		if(borderColor==null)
			borderColor=Color.WHITE;//图形边框颜色
		if(borderWidth==null||borderWidth.toString().length()==0)
			borderWidth=0.1f;//图形边框宽度
		if(foregroundAlpha==null||foregroundAlpha.toString().length()==0)
			foregroundAlpha=0.7f;//饼图透明度
		if(circular==null)
			circular=false;//是否正圆
		if(startAngle==null||startAngle.toString().length()==0)
			startAngle=0;//饼图初始角度
		if(explodePercentKey==null||explodePercentKey.length()==0)
			explodePercentKey="无";//突出显示的数据块 名称
		if(explodePercentValue==null||explodePercentValue.toString().length()==0)
			explodePercentValue=0.1d;//突出显示的数据块间距
		if(label_font_name==null||label_font_name.length()==0)
			label_font_name="宋体";//标签字体 
		if(label_font_style==null||label_font_style.toString().length()==0)
			label_font_style=Font.ITALIC;//标签字体样式
		if(label_font_size==null||label_font_size.toString().length()==0)
			label_font_size=14;//标签字体大小
		if(item_font_name==null||item_font_name.length()==0)
			item_font_name="宋体";//图例说明字体
		if(item_font_style==null||item_font_style.toString().length()==0)
			item_font_style=Font.PLAIN;//图例说明字体样式
		if(item_font_size==null||item_font_size.toString().length()==0)
			item_font_size=14;//图例说明字体大小
		if(cakeMapName_font_name==null||cakeMapName_font_name.length()==0)
			cakeMapName_font_name="黑体";//标题字体
		if(cakeMapName_font_style==null||cakeMapName_font_style.toString().length()==0)
			cakeMapName_font_style=Font.BOLD;//标题字体样式
		if(cakeMapName_font_size==null||cakeMapName_font_size.toString().length()==0)
			cakeMapName_font_size=20;//标题字体大小
		if(isShowScale==null)
			isShowScale=false;//是否显示比例
		if(flag==null)
			flag=false;//2D,3D标示，true为3D
		// 创建饼图数据对象
        DefaultPieDataset dfp = new DefaultPieDataset();
        if(cakeHashMap!=null&&cakeHashMap.size()>0){
        	if(isShowScale==true){
        		Double sum=0.00d;
        		Iterator its=cakeHashMap.keySet().iterator();
        		while(its.hasNext()){
        			String name=its.next().toString();
        			if(name!=null&&name.length()>0){
        				if(cakeHashMap.get(name)!=null&&cakeHashMap.get(name).toString().length()>0)
        					sum+=cakeHashMap.get(name);        				
        			}
        		} 
        		Iterator it=cakeHashMap.keySet().iterator();
            	while(it.hasNext()){
            		String name=it.next().toString();
            		 dfp.setValue(formatScaleToString(name,cakeHashMap.get(name),sum), cakeHashMap.get(name));
            	}
        	}else{        		
        		Iterator it=cakeHashMap.keySet().iterator();
        		while(it.hasNext()){
        			String name=it.next().toString();
        			dfp.setValue(name, cakeHashMap.get(name));
        		}
        	}
        }
        Set<Color> tempSet=new HashSet();
        if(cakeMapList!=null&&cakeMapList.size()>0){
        	if(isShowScale==true){
        		Double sum=0.00d;
        		for (CakeMap cakeMap : cakeMapList) {
        			if(cakeMap!=null&&cakeMap.getCakeMapLabelValue()!=null&&
        						cakeMap.getCakeMapLabelValue().toString().length()>0)
        				sum+=cakeMap.getCakeMapLabelValue();
    			}
            	for (CakeMap cakeMap : cakeMapList) {
            		if(cakeMap!=null){
            			dfp.setValue(formatScaleToString(cakeMap.getCakeMapLabelName(),cakeMap.getCakeMapLabelValue(),sum),cakeMap.getCakeMapLabelValue());
            			tempSet.add(cakeMap.getCakeMapLabelColor());   
            		}
    			}
        	}else{        		
        		for (CakeMap cakeMap : cakeMapList) {
        			if(cakeMap!=null){
        				dfp.setValue(cakeMap.getCakeMapLabelName(),cakeMap.getCakeMapLabelValue());
        				tempSet.add(cakeMap.getCakeMapLabelColor());        				
        			}
    			}
        	}
        }
        JFreeChart chart = null;
        if(flag){
        	chart=ChartFactory.createPieChart3D(cakeMapName,dfp, true, true, true);        	
        }else{
        	chart=ChartFactory.createPieChart(cakeMapName,dfp, true, true, true);
        }
        // 图片背景色  
        chart.setBackgroundPaint(backgroundColor);
        if(flag){
        	// 取得3D饼图对象  
            PiePlot3D plot = (PiePlot3D) chart.getPlot();
            // 图形边框颜色  
            //plot.setBaseSectionOutlinePaint(Color.RED);  
             plot.setBaseSectionPaint(borderColor);  
            // 图形边框粗细  
            plot.setBaseSectionOutlineStroke(new BasicStroke(borderWidth));  
            int i=0;
            if(tempSet!=null&&tempSet.size()>0){
            	for (Color color : tempSet) {
            		 plot.setSectionPaint(i,color);//分类饼颜色
            		 plot.setSectionOutlinePaint(i,color);//分类饼边框颜色
            		 i++;
    			}
            }
            plot.setShadowPaint(Color.WHITE);
            plot.setShadowXOffset(10d);
            plot.setShadowYOffset(10d);
            // 指定图片的透明度(0.0-1.0)  
            plot.setForegroundAlpha(foregroundAlpha);  
            // 指定显示的饼图上圆形(false)还椭圆形(true)  
            plot.setCircular(circular);  
     
            // 设置第一个 饼块section 的开始位置，默认是12点钟方向  
            plot.setStartAngle(startAngle);  
            // 设置鼠标悬停提示  
            plot.setToolTipGenerator(new StandardPieToolTipGenerator());  
     
            // 设置突出显示的数据块  
            plot.setExplodePercent(explodePercentKey, explodePercentValue);  
            // 设置饼图各部分标签字体  
            plot.setLabelFont(new Font(label_font_name,label_font_style,label_font_size));  
        }else{
        	// 取得饼图plot对象  
        	PiePlot plot = (PiePlot) chart.getPlot(); 
        	 // 图形边框颜色  
            //plot.setBaseSectionOutlinePaint(Color.RED);  
             plot.setBaseSectionPaint(borderColor);  
            // 图形边框粗细  
            plot.setBaseSectionOutlineStroke(new BasicStroke(borderWidth));  
            int i=0;
            if(tempSet!=null&&tempSet.size()>0){
            	for (Color color : tempSet) {
            		 plot.setSectionPaint(i,color);//分类饼颜色
            		 plot.setSectionOutlinePaint(i,color);//分类饼边框颜色
            		 i++;
    			}
            }
            plot.setShadowXOffset(0d);
    		plot.setShadowYOffset(0d);
    		
            plot.setShadowPaint(Color.WHITE);
            // 指定图片的透明度(0.0-1.0)  
            plot.setForegroundAlpha(foregroundAlpha);  
            // 指定显示的饼图上圆形(false)还椭圆形(true)  
            plot.setCircular(circular);
            // 设置第一个 饼块section 的开始位置，默认是12点钟方向  
            plot.setStartAngle(startAngle);  
            // 设置鼠标悬停提示  
            plot.setToolTipGenerator(new StandardPieToolTipGenerator());
            // 设置突出显示的数据块  
            plot.setExplodePercent(explodePercentKey, explodePercentValue);  
            // 设置饼图各部分标签字体  
            plot.setLabelFont(new Font(label_font_name,label_font_style,label_font_size));  
        }
        // 设置分饼颜色  
       // plot.setSectionPaint(0,label_class_color);  
        // plot.setSectionPaint("2", new Color(144, 233, 144));  
        // 设置图例说明Legend上的文字  
        chart.getLegend().setItemFont(new Font(item_font_name, item_font_style, item_font_size));  
        // 定义字体格式  
        Font font = new java.awt.Font(cakeMapName_font_name,cakeMapName_font_style,cakeMapName_font_size);  
        TextTitle title = new TextTitle(cakeMapName);  
        title.setFont(font);  
        // 设置字体,非常关键不然会出现乱码的,下方的字体  
        chart.setTitle(title);
        return chart;
	}
	private static String formatScaleToString(String labelName,Double data1,Double data2){
		if(data1!=null&&data2!=null){
			if(data1==0d){
				return labelName+"(0%)";
			}
			if(data2==0){
				return labelName+"(0%)";
			}
			Double temp=(data1/data2)*100;
			int number=temp.toString().lastIndexOf(".");
			if(temp.toString().length()<(number+3)){
				String str=temp.toString().substring(0, temp.toString().length());
				return labelName+"("+str+"%)";
			}else{
				String str=temp.toString().substring(0, number+3);				
				return labelName+"("+str+"%)";			
			}
		}
		return labelName+"(0%)";
	}
	/**
	 * 折线图
		@param snapMapName="无标题" 图表标题
		@param snapXName="X轴" 目录轴的显示标签 
		@param snapYName="y轴" 数值轴的显示标签  
		@param List<SnapMap> snapList=null 数据集合
		@return JFreeChart 返回JFreeChart对象
	 * */
	public static JFreeChart findSnapMap(String snapMapName,String snapXName,String snapYName,List<SnapMap> snapList){
		return findSnapMap(snapMapName,snapXName,snapYName,snapList,null,null,null,
				null,null,null,null);
	}
	/**
	 * 折线图
		@param snapMapName="无标题" 图表标题
		@param snapXName="X轴" 目录轴的显示标签 
		@param snapYName="y轴" 数值轴的显示标签  
		@param List<SnapMap> snapList=null 数据集合
		@param flag=false 2D,3D标示，true为3D
		@return JFreeChart 返回JFreeChart对象
	 * */
	public static JFreeChart findSnapMap(String snapMapName,String snapXName,String snapYName,List<SnapMap> snapList,Boolean flag){
		return findSnapMap(snapMapName,snapXName,snapYName,snapList,null,null,null,
				null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,flag);
	}
	/**
	 * 折线图
		@param snapMapName="无标题" 图表标题
		@param snapXName="X轴" 目录轴的显示标签 
		@param snapYName="y轴" 数值轴的显示标签  
		@param List<SnapMap> snapList=null 数据集合
		@param snapMapName_font_name="黑体" 标题字体
		@param snapMapName_font_style=Font.ITALIC 标题风格
		@param snapMapName_font_size=22 标题大小
		@param snapMapItem_font_name="宋体" 图例字体
		@param snapMapItem_font_style=Font.BOLD 图例风格
		@param snapMapItem_font_size=14 图例大小
		@param flag=false 2D,3D标示，true为3D
		@return JFreeChart 返回JFreeChart对象
	 * */
	public static JFreeChart findSnapMap(String snapMapName,String snapXName,String snapYName,
			List<SnapMap> snapList,String snapMapName_font_name,Integer snapMapName_font_style,Integer snapMapName_font_size,
				String snapMapItem_font_name,Integer snapMapItem_font_style,Integer snapMapItem_font_size,Boolean flag){
		return findSnapMap(snapMapName,snapXName,snapYName,snapList,snapMapName_font_name,snapMapName_font_style,snapMapName_font_size,
				snapMapItem_font_name,snapMapItem_font_style,snapMapItem_font_size,null,null,null,
				null,null,null,null,flag);
	}
	/**
	 * 折线图
		@param snapMapName="无标题" 图表标题
		@param snapXName="X轴" 目录轴的显示标签 
		@param snapYName="y轴" 数值轴的显示标签  
		@param List<SnapMap> snapList=null 数据集合
		@param snapMapName_font_name="黑体" 标题字体
		@param snapMapName_font_style=Font.ITALIC 标题风格
		@param snapMapName_font_size=22 标题大小
		@param snapMapItem_font_name="宋体" 图例字体
		@param snapMapItem_font_style=Font.BOLD 图例风格
		@param snapMapItem_font_size=14 图例大小
		@param snapX_font_name="宋体" 目录轴的显示标签字体 
		@param snapX_font_style=Font.BOLD 目录轴的显示标签风格
		@param snapX_font_size=22 目录轴的显示标签大小
		@param snapY_font_name="宋体" 数值轴的显示标签字体
		@param snapY_font_style=Font.BOLD 数值轴的显示标签风格
		@param snapY_font_size=22 数值轴的显示标签大小
		@param snapX_font_angle=CategoryLabelPositions.UP_45 目录轴的显示标签倾斜度
		@param snapXData_font_name="宋体" 目录轴数据字体 
		@param snapXData_font_style=Font.BOLD 目录轴数据风格
		@param snapXData_font_size=22 目录轴数据大小
		@param fackgroundAlpha=0.9f 背景透明度（0~1） 
		@param foregroundAlpha=0.5f 前景透明度（0~1）
		@param flag=false 2D,3D标示，true为3D
		@return JFreeChart 返回JFreeChart对象
	 * */
	public static JFreeChart findSnapMap(String snapMapName,String snapXName,String snapYName,
			List<SnapMap> snapList,String snapMapName_font_name,Integer snapMapName_font_style,Integer snapMapName_font_size,
			String snapMapItem_font_name,Integer snapMapItem_font_style,Integer snapMapItem_font_size,
				String snapX_font_name,Integer snapX_font_style,Integer snapX_font_size,
					String snapY_font_name,Integer snapY_font_style,Integer snapY_font_size,
					CategoryLabelPositions snapX_font_angle,Boolean flag){
		return findSnapMap(snapMapName,snapXName,snapYName,snapList,snapMapName_font_name,snapMapName_font_style,snapMapName_font_size,
				snapMapItem_font_name,snapMapItem_font_style,snapMapItem_font_size,snapX_font_name,snapX_font_style,snapX_font_size,
				snapY_font_name,snapY_font_style,snapY_font_size,snapX_font_angle,null,null,null,null,null,flag);
	}
	/**
	 * 折线图
		@param snapMapName="无标题" 图表标题
		@param snapXName="X轴" 目录轴的显示标签 
		@param snapYName="y轴" 数值轴的显示标签  
		@param List<SnapMap> snapList=null 数据集合
		@param snapMapName_font_name="黑体" 标题字体
		@param snapMapName_font_style=Font.ITALIC 标题风格
		@param snapMapName_font_size=22 标题大小
		@param snapMapItem_font_name="宋体" 图例字体
		@param snapMapItem_font_style=Font.BOLD 图例风格
		@param snapMapItem_font_size=14 图例大小
		@param snapX_font_name="宋体" 目录轴的显示标签字体 
		@param snapX_font_style=Font.BOLD 目录轴的显示标签风格
		@param snapX_font_size=22 目录轴的显示标签大小
		@param snapY_font_name="宋体" 数值轴的显示标签字体
		@param snapY_font_style=Font.BOLD 数值轴的显示标签风格
		@param snapY_font_size=22 数值轴的显示标签大小
		@param snapX_font_angle=CategoryLabelPositions.UP_45 目录轴的显示标签倾斜度
		@param snapXData_font_name="宋体" 目录轴数据字体 
		@param snapXData_font_style=Font.BOLD 目录轴数据风格
		@param snapXData_font_size=22 目录轴数据大小
		@param fackgroundAlpha=0.9f 背景透明度（0~1） 
		@param foregroundAlpha=0.5f 前景透明度（0~1）
		@param flag=false 2D,3D标示，true为3D
		@return JFreeChart 返回JFreeChart对象
	 * */
	public static JFreeChart findSnapMap(String snapMapName,String snapXName,String snapYName,
			List<SnapMap> snapList,String snapMapName_font_name,Integer snapMapName_font_style,Integer snapMapName_font_size,
			String snapMapItem_font_name,Integer snapMapItem_font_style,Integer snapMapItem_font_size,
				String snapX_font_name,Integer snapX_font_style,Integer snapX_font_size,
					String snapY_font_name,Integer snapY_font_style,Integer snapY_font_size,
					CategoryLabelPositions snapX_font_angle,String snapXData_font_name,Integer snapXData_font_style,Integer snapXData_font_size,
					Float fackgroundAlpha,Float foregroundAlpha,Boolean flag){
		
		if(snapMapName==null||snapMapName.length()==0)
			snapMapName="无标题";//图表标题
		if(snapXName==null||snapXName.length()==0)
			snapXName="X轴";//目录轴的显示标签 
		if(snapYName==null||snapYName.length()==0)
			snapYName="y轴";//数值轴的显示标签  
		if(snapMapName_font_name==null||snapMapName_font_name.length()==0)
			snapMapName_font_name="黑体";//标题字体
		if(snapMapName_font_style==null||snapMapName_font_style.toString().length()==0)
			snapMapName_font_style=Font.ITALIC;//标题风格
		if(snapMapName_font_size==null||snapMapName_font_size.toString().length()==0)
			snapMapName_font_size=22;//标题大小
		if(snapMapItem_font_name==null||snapMapItem_font_name.length()==0)
			snapMapItem_font_name="宋体";//图例字体
		if(snapMapItem_font_style==null||snapMapItem_font_style.toString().length()==0)
			snapMapItem_font_style=Font.PLAIN;//图例风格
		if(snapMapItem_font_size==null||snapMapItem_font_size.toString().length()==0)
			snapMapItem_font_size=14;//图例大小
		if(snapX_font_name==null||snapX_font_name.length()==0)
			snapX_font_name="宋体";//目录轴的显示标签字体 
		if(snapX_font_style==null||snapX_font_style.toString().length()==0)
			snapX_font_style=Font.PLAIN;//目录轴的显示标签风格
		if(snapX_font_size==null||snapX_font_size.toString().length()==0)
			snapX_font_size=14;//目录轴的显示标签大小
		if(snapY_font_name==null||snapY_font_name.length()==0)
			snapY_font_name="宋体";//数值轴的显示标签字体
		if(snapY_font_style==null||snapY_font_style.toString().length()==0)
			snapY_font_style=Font.PLAIN;//数值轴的显示标签风格
		if(snapY_font_size==null||snapY_font_size.toString().length()==0)
			snapY_font_size=14;//数值轴的显示标签大小
		if(snapX_font_angle==null)
			snapX_font_angle=CategoryLabelPositions.UP_45;//目录轴的显示标签倾斜度
		
		if(snapXData_font_name==null||snapXData_font_name.length()==0)
			snapXData_font_name="宋体";//目录轴数据字体 
		if(snapXData_font_style==null||snapXData_font_style.toString().length()==0)
			snapXData_font_style=Font.PLAIN;//目录轴数据风格
		if(snapXData_font_size==null||snapXData_font_size.toString().length()==0)
			snapXData_font_size=14;//目录轴数据大小
		
		if(fackgroundAlpha==null||fackgroundAlpha.toString().length()==0)
			fackgroundAlpha=0.9f;//背景透明度（0~1） 
		if(foregroundAlpha==null||foregroundAlpha.toString().length()==0)
			foregroundAlpha=0.5f;//前景透明度（0~1）  
		if(flag==null)
			flag=false;//2D,3D标示，true为3D
		JFreeChart chart = null;
        if(flag){
        	//生成3D折线图（柱状图只改方法名createLineChart3D就可以了）  
            chart=ChartFactory.createLineChart3D (  
                    snapMapName,     //图表标题  
                    snapXName,             //目录轴的显示标签  
                    snapYName,             //数值轴的显示标签  
                    getDateSet(snapList),      //数据  
                    //PlotOrientation.HORIZONTAL, //图表方向水平  
                    PlotOrientation.VERTICAL,    //图表方向垂直  
                    true,             //是否显示图例  
                    true,            //是否显示工具提示  
                    false             //是否生成URL  
            );      	
        }else{
        	//生成折线图  
        	chart=ChartFactory.createLineChart (  
        			snapMapName,     //图表标题  
        			snapXName,             //目录轴的显示标签  
        			snapYName,             //数值轴的显示标签  
        			getDateSet(snapList),      //数据  
        			//PlotOrientation.HORIZONTAL, //图表方向水平  
        			PlotOrientation.VERTICAL,    //图表方向垂直  
        			true,             //是否显示图例  
        			true,            //是否显示工具提示  
        			false             //是否生成URL  
        	);  
        }
        //设置标题及标题字体  
        chart.setTitle(new TextTitle(snapMapName,new Font(snapMapName_font_name,snapMapName_font_style,snapMapName_font_size)));
        //建一个图例  
        LegendTitle legendTitle = chart.getLegend(0);  
        //设置图例字体  
        legendTitle.setItemFont(new Font(snapMapItem_font_name,snapMapItem_font_style,snapMapItem_font_size));  
        //获取折线图plot对象  
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        //plot.setWeight(10);
        //设置折线的颜色
        List<String> set=new ArrayList();
        HashMap<String,Color> hashMap=new HashMap();
        if(snapList!=null&&snapList.size()>0){
        	boolean flag1=false;
    		for (SnapMap snapMap : snapList) {
    			for (String string : set) {
        			if(string.equals(snapMap.getSnapLineName())){
        				flag1=true;
        				break;
        			}
    			}
    			if(flag1==false){
    				set.add(snapMap.getSnapLineName());
    				hashMap.put(snapMap.getSnapLineName(), snapMap.getSnapLineColor());    				
    			}
    			flag1=false;
    		}
        }
        if(hashMap.size()>0){
        	int tag=0;
        	for (String string : set) {
        		plot.getRenderer().setSeriesPaint(tag, hashMap.get(string));
        		tag++;
			}
        }
        //取得横轴  
        CategoryAxis categoryAxis = plot.getDomainAxis();  
        //设置横轴的字体  
        categoryAxis.setLabelFont(new Font(snapX_font_name,snapX_font_style,snapX_font_size));  
        //设置分类标签以？度倾斜  
        categoryAxis.setCategoryLabelPositions(snapX_font_angle);  
        //设置分类标签字体  
        categoryAxis.setTickLabelFont(new Font(snapXData_font_name,snapXData_font_style,snapXData_font_size));  //X轴
        //取得纵轴  
        NumberAxis numberAxis = (NumberAxis) plot.getRangeAxis();  
        //设置纵轴的字体  
        numberAxis.setLabelFont(new Font(snapY_font_name,snapY_font_style,snapY_font_size));  
        //设置背景透明度（0~1）  
        plot.setBackgroundAlpha(fackgroundAlpha);  
        //设置前景色透明度（0~1）  
        plot.setForegroundAlpha(foregroundAlpha);  
//        //输出文件  
//        FileOutputStream fos = new FileOutputStream("d:\\book.jpg");  
//        //用ChartUtilities工具输出  
//        ChartUtilities.writeChartAsJPEG(fos, chart, 800, 600);  
//        fos.close();
        return chart;
	}
	 private static CategoryDataset getDateSet(List<SnapMap> snapList) {  
        //提供生成折线图的数据  
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
        //生成复杂带图例的柱状图  
        if(snapList!=null && snapList.size()>0)
        for (SnapMap snapMap : snapList) {
        	dataset.addValue(snapMap.getSnapYData(),snapMap.getSnapLineName(),snapMap.getSnapXData());
		}
        return dataset;  
    }  
}
