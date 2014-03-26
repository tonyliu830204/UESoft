package com.syuesoft.integratedservices.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.fbk.vo.ComboxVo;
import com.syuesoft.fbk.vo.ComboxidVo;
import com.syuesoft.fbk.vo.VTrackRecordVo;
import com.syuesoft.integratedservices.service.VTrackRecordService;
import com.syuesoft.model.BasCarBrand;
import com.syuesoft.model.BasRepairGroup;
import com.syuesoft.model.BasStuff;
import com.syuesoft.model.FbkDcserveyName;

@Controller
@Scope("prototype")
public class VTrackRecordAction extends BaseAction implements
        ModelDriven<VTrackRecordVo>
{

    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 1L;

    private VTrackRecordVo vTrackRecordVo = new VTrackRecordVo();

    @Autowired
    private VTrackRecordService vTrackRecordService;

    public VTrackRecordService getvTrackRecordService()
    {
        return vTrackRecordService;
    }

    public void setvTrackRecordService(VTrackRecordService vTrackRecordService)
    {
        this.vTrackRecordService = vTrackRecordService;
    }

    
    public VTrackRecordVo getModel()
    {
        return vTrackRecordVo;
    }

    private JFreeChart chart;

    public JFreeChart getChart()
    {
        return chart;
    }

    public void setChart(JFreeChart chart)
    {
        this.chart = chart;
    }

    HttpSession session = ServletActionContext.getRequest().getSession();
    
	 // 此方法概述:将前台转过来的回访日期分割截取成每一个月的时间段传给service
    public void getSatisfaction()
    {
        try
        {
/*            session.setAttribute("returnVisitDate", vTrackRecordVo
                    .getReturnVisitDate());*/
        	vTrackRecordVo.setEnterpriseId(getNowEnterpriseId());
            List list = vTrackRecordService.getSatisfaction(vTrackRecordVo);
            super.writeJson(list);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    /*
     * 获取所有的 跟踪记录信息 以及 与之 对应的跟踪项目统计的信息
     */
    @SuppressWarnings("unchecked")
    public void getAll()
    {
		try{
			vTrackRecordVo.setEnterpriseId(getNowEnterpriseId());
			if(vTrackRecordVo.getFlag()!=null && vTrackRecordVo.getFlag()==true){
				String ss=vTrackRecordService.getAll(vTrackRecordVo);
				super.writeJson(JSON.parseObject(ss));
			}else{
				String source=vTrackRecordService.getAll(vTrackRecordVo);
				super.writeJson(source);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
    }

    // 获取下拉列表框的的项目名称
    public String getReturnSeverName()
    {
        List list = new ArrayList();
        try
        {
            List vlist = vTrackRecordService.getReturnSeverName();
            if (vlist.size() > 0)
            {
                for (int i = 0; i < vlist.size(); i++)
                {
                    ComboxVo cvo = new ComboxVo();
                    FbkDcserveyName fname = (FbkDcserveyName) vlist.get(i);
                    cvo.setId(fname.getServeyName());
                    cvo.setName(fname.getServeyName());
                    list.add(cvo);
                }
            }
            super.writeJson(list);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    // 获取不固定列需要显示的的项目名称
    public String getName()
    {
        List list = new ArrayList();
        try
        {
            Object[] obj = null;
            List vlist = vTrackRecordService.getName(vTrackRecordVo);
            super.writeJson(vlist);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    // 获取车品牌 (用于combox 下拉框)
    public String getCarbrand()
    {
        try
        {
            List list = new ArrayList();
            List<BasCarBrand> cblist = vTrackRecordService.getCarbrand();
            if (cblist.size() > 0)
            {
                for (BasCarBrand basCarBrand : cblist)
                {
                    ComboxVo vo = new ComboxVo();
                    vo.setId(basCarBrand.getCbrdName());
                    vo.setName(basCarBrand.getCbrdName());
                    list.add(vo);
                }
            }
            super.writeJson(list);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    // 获取员工名称 (用于combox 下拉框)
    public String getStuff()
    {
        try
        {
            List list = new ArrayList();
            List<BasStuff> bslist = vTrackRecordService.getStuff();
            if (bslist.size() > 0)
            {
                for (BasStuff basStuff : bslist)
                {
                    ComboxidVo vo = new ComboxidVo();
                    vo.setId(basStuff.getStfId());
                    vo.setText(basStuff.getStfName());
                    list.add(vo);
                }
            }
            super.writeJson(list);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    // 获取部门名称(用于combox 下拉框)
    public String getPartment()
    {
        try
        {
            List list = new ArrayList();
            List<BasRepairGroup> bdlist = vTrackRecordService.getPartment();
            if (bdlist.size() > 0)
            {
                for (BasRepairGroup basRepairGroup : bdlist)
                {
                    ComboxVo vo = new ComboxVo();
                    vo.setId(basRepairGroup.getRepgrpName());
                    vo.setName(basRepairGroup.getRepgrpName());
                    list.add(vo);
                }
            }
            super.writeJson(list);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    // 此方法概述:将前台转过来的回访日期分割截取成每一个月的时间段传给service
    public String getDateInfomation()
    {
        try
        {

            session.setAttribute("ReturnVisitDate", vTrackRecordVo
                    .getReturnVisitDate());
            List list = vTrackRecordService.getDateInfomation(vTrackRecordVo);
            super.writeJson(list);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
	/**
	 *获取维修时间段分析折线图信息
	 * @throws Exception 
	 * */
	public void findServiceTimeAnalyzeSnapMap() throws Exception{
		try {
			this.getResponse().setContentType("image/jpeg; charset=UTF-8");
			ChartUtilities.writeChartAsJPEG(this.getResponse().getOutputStream(),vTrackRecordService.findServiceTimeAnalyzeSnapMap(vTrackRecordVo), 1100, 360);			
		} catch (Exception e) {
			//logger.error("获取维修时间段分析折线图信息失败！", e);
			e.printStackTrace();
		}
	}
    // JfreeChart图形报表
    public String getJfreeChart()
    {
        if (session.getAttribute("ReturnVisitDate") != null
                && !session.getAttribute("ReturnVisitDate").equals(""))
        {
            List list = null;
            try
            {
                VTrackRecordVo vTrackRecordVo = new VTrackRecordVo();
                vTrackRecordVo.setReturnVisitDate(session.getAttribute(
                        "ReturnVisitDate").toString());
                list = vTrackRecordService
                        .getJfreeChartInfomation(vTrackRecordVo);

                chart = ChartFactory.createBarChart("客户满意度统计图", "统计月份", "回访数",
                        createCategoryDataset(list), PlotOrientation.VERTICAL,
                        true, false, false);
                chart.setBackgroundPaint(new GradientPaint(0.0F, 0.0F,
                        Color.LIGHT_GRAY, 10000F, 0.0F, Color.YELLOW));

                chart.getRenderingHints().put(
                        RenderingHints.KEY_TEXT_ANTIALIASING,
                        RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);

                Font font = new Font("黑体", Font.TRUETYPE_FONT, 18);

                TextTitle title = chart.getTitle();
                title.setFont(new Font("SimSun", Font.BOLD, 24));
                title.setMargin(5.0, 0, 5.0, 0);

                LegendTitle legend = (LegendTitle) chart.getLegend();
                legend.setItemFont(font);

                Plot plot = chart.getPlot();
                plot.setNoDataMessage("没有数据显示");
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
                valueAxis.setStandardTickUnits(NumberAxis
                        .createIntegerTickUnits());
                valueAxis.setLabelFont(font);
                valueAxis.setLabelPaint(Color.RED);
                valueAxis.setTickLabelFont(font);
                valueAxis.setTickLabelPaint(Color.RED);

                BarRenderer barrenderer = (BarRenderer) categoryplot
                        .getRenderer();
                barrenderer.setDrawBarOutline(false);// 设置外廓线不可见

                barrenderer
                        .setLegendItemToolTipGenerator(new StandardCategorySeriesLabelGenerator(
                                "Tooltip: {0}"));

                barrenderer
                        .setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
                barrenderer.setBaseItemLabelFont(font);
                barrenderer.setBaseItemLabelsVisible(true);
                barrenderer
                        .setBasePositiveItemLabelPosition(new ItemLabelPosition(
                                ItemLabelAnchor.OUTSIDE12,
                                TextAnchor.BASELINE_CENTER));
                barrenderer.setItemMargin(0.0);

                CategoryAxis categoryaxis = categoryplot.getDomainAxis();
                categoryaxis
                        .setCategoryLabelPositions(CategoryLabelPositions.UP_45);
                categoryaxis.setLabelFont(font);
                categoryaxis.setLabelPaint(Color.RED);
                categoryaxis.setTickLabelFont(font);
                categoryaxis.setTickLabelPaint(Color.RED);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        return SUCCESS;

    }

    // 给JfreeChart设值
    private CategoryDataset createCategoryDataset(List list)
    {
        DefaultCategoryDataset categorydataset = new DefaultCategoryDataset();
        if (list.size() > 0)
        {
            List maplist = new ArrayList();
            int a = 0;
            String b = "";
            String c = "";
            for (int i = 0; i < list.size(); i++)
            {
                Map map = (Map) list.get(i);
                // Map map2 = (Map)map.get(i);
                for (int j = 0; j < map.size(); j++)
                {
                    Map map2 = (Map) map.get("id" + j);
                    if (map2.get("score" + j) != null)
                    {
                        a = Integer.parseInt(map2.get("score" + j).toString());
                    }
                    else
                    {
                        a = 0;
                    }
                    if (map2.get("pingjia" + j) != null)
                    {
                        b = map2.get("pingjia" + j).toString();
                    }
                    if (map2.get("date" + j) != null)
                    {
                        c = map2.get("date" + j) + "";
                    }
                    categorydataset.addValue(a, b, c);
                }

            }

        }
        return categorydataset;
    }

}
