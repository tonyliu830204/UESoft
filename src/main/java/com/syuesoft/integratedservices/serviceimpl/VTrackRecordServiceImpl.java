package com.syuesoft.integratedservices.serviceimpl;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.LinkedMap;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.syuesoft.fbk.vo.VTrackRecordVo;
import com.syuesoft.integratedservices.dao.VTrackRecordDao;
import com.syuesoft.integratedservices.service.VTrackRecordService;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;
import com.syuesoft.util.CreateJFreeChart;
import com.syuesoft.util.SnapMap;

@Service("vTrackRecordService")
public class VTrackRecordServiceImpl implements VTrackRecordService
{
    @Autowired
    private VTrackRecordDao vTrackRecordDAO;

    public VTrackRecordDao getvTrackRecordDAO()
    {
        return vTrackRecordDAO;
    }

    public void setvTrackRecordDAO(VTrackRecordDao vTrackRecordDAO)
    {
        this.vTrackRecordDAO = vTrackRecordDAO;
    }

    
    public String getAll(VTrackRecordVo vTrackRecordVo) throws Exception
    {
        return vTrackRecordDAO.getAll(vTrackRecordVo);
    }

    
    public List getScoreAndName(String carLicense) throws Exception
    {
        return vTrackRecordDAO.getScoreAndName(carLicense);
    }

    // 获取下拉框的项目名称
    
    public List getReturnSeverName() throws Exception
    {
        return vTrackRecordDAO.getReturnSeverName();
    }

    // 获取不固定列需要显示的的项目名称
    
    public List getName(VTrackRecordVo vTrackRecordVo) throws Exception
    {
        return vTrackRecordDAO.getName(vTrackRecordVo);
    }

    
    public List getData(String carLicense) throws Exception
    {
        return vTrackRecordDAO.getData(carLicense);
    }

    // 获取车品牌 (用于combox 下拉框)
    
    public List getCarbrand() throws Exception
    {
        return vTrackRecordDAO.getCarbrand();
    }

    // 获取员工名称 (用于combox 下拉框)
    
    public List getStuff() throws Exception
    {
        return vTrackRecordDAO.getStuff();
    }

    // 获取部门名称(用于combox 下拉框)
    
    public List getPartment() throws Exception
    {
        return vTrackRecordDAO.getPartment();
    }

    // 此方法概述:将前台转过来的回访日期分割截取成每一个月的时间段传给service
    
    public List getDateInfomation(VTrackRecordVo vTrackRecordVo)
            throws Exception
    {
        return vTrackRecordDAO.getDateInfomation(vTrackRecordVo);
    }

    
    public List getJfreeChartInfomation(VTrackRecordVo vTrackRecordVo)
            throws Exception
    {
        return vTrackRecordDAO.getJfreeChartInfomation(vTrackRecordVo);
    }
 // 此方法概述:将前台传过来的回访日期分割截取成每一个月的时间段传给service
	@SuppressWarnings("unchecked")
	
	public List getSatisfaction(VTrackRecordVo vTrackRecordVo) throws Exception {
        Map map2 = new LinkedMap();
        List newlist = new ArrayList();
        String sql = "";
        String newtime = "";
        int index = 1;
        // 判断时间是否为空
        if (vTrackRecordVo.getReturnVisitDate() != null
                && !vTrackRecordVo.getReturnVisitDate().trim().equals(""))
        {
            String[] str = vTrackRecordVo.getReturnVisitDate().split(",");
            int year1 = Integer.parseInt(str[0].toString().trim()
                    .substring(0, 4));
            int month1 = Integer.parseInt(str[0].toString().trim()
                    .substring(5, 7));
            int year2 = Integer.parseInt(str[1].toString().trim()
                    .substring(0, 4));
            int month2 = Integer.parseInt(str[1].toString().trim()
                    .substring(5, 7));
            int day2 = Integer.parseInt(str[1].toString().trim()
                    .substring(str[1].lastIndexOf("-") + 1));
            int month = 0;

            // 判断月份是否为 4 , 6 , 9 , 11 月
        	
            // 很满意     verySatisfactory满意    satisfactory一般 commonly不满意 nosatisfactory很不满意  farSatisfactory无 no
            // 循环行数
            String[] strarry = { " ", "and bb.satis='verySatisfactory'",
                    "and bb.satis='satisfactory'",
                    "and bb.satis='commonly'",
                    "and bb.satis='nosatisfactory'",
                    "and bb.satis='farSatisfactory'",
                    "and bb.satis='no'" };
            String[] title = { "回访件数", "很满意", "满意", "一般", "不满意", "很不满","无" };
            for (int j = 0; j < strarry.length; j++)
            {
                Map map = new LinkedMap();
                map.put("biaoti", title[j]);
                String str0 = str[0] + "";
                // 获取两时间之间的月份数
                if (year1 > year2)
                {
                    month = (year1 - year2) * 12 + (month2 - month1)
                            + 1;
                }
                else
                {
                    month = (year2 - year1) * 12 + (month2 - month1)
                            + 1;
                }

                // 如果month>24 时 按两年计算 将较大的时间向前推两年
                if (month > 24)
                {
                    String m2 = "";
                    String d2 = "";
                    month = 24;
                    if (month2 < 10)
                    {
                        m2 = "0" + month2;
                    }
                    else
                    {
                        m2 = month2 + "";
                    }
                    ;
                    if (day2 < 10)
                    {
                        d2 = "0" + day2;
                    }
                    else
                    {
                        d2 = day2 + "";
                    }
                    ;
                    str0 = year2 - 2 + "-" + m2 + "-" + d2;
                }
                int str0year1 = Integer.parseInt(str0.substring(0, 4));
                int str0month1 = Integer.parseInt(str0.substring(5, 7));
                int str0day1 = Integer.parseInt(str0.substring(8));
                // 循环列数
                for (int i = 0; i < month; i++)
                {
                    String mapstr = "";
                    String bili = "";
                    int total = 0;
                    int score = 0;
                    if (str0month1 == 4 || str0month1 == 6
                            || str0month1 == 9 || str0month1 == 11)
                    {
                        if (str0month1 < 10)
                        {
                            newtime = str0year1 + "-0" + str0month1
                                    + "-30";
                        }
                        else
                        {
                            newtime = str0year1 + "-" + str0month1
                                    + "-30";
                        }
                        str0day1 = 30;
                    }

                    if (str0month1 == 1 || str0month1 == 3
                            || str0month1 == 5 || str0month1 == 7
                            || str0month1 == 8 || str0month1 == 10
                            || str0month1 == 12)
                    {
                        if (str0month1 < 10)
                        {
                            newtime = str0year1 + "-0" + str0month1
                                    + "-31";
                        }
                        else
                        {
                            newtime = str0year1 + "-" + str0month1
                                    + "-31";
                        }
                        str0day1 = 31;
                    }
                    if (str0month1 == 2)
                    {
                        if (str0month1 < 10)
                        {
                            newtime = str0year1 + "-0" + str0month1
                                    + "-28";
                        }
                        else
                        {
                            newtime = str0year1 + "-" + str0month1
                                    + "-28";
                        }
                        str0day1 = 28;
                    }
                    if (str0month1 > 12)
                    {
                        str0year1 = str0year1 + 1;
                        str0month1 = 1;
                    }
                    if (str0month1 < 10)
                    {
                        newtime = str0year1 + "-0" + str0month1 + "-01";
                    }
                    else
                    {
                        newtime = str0year1 + "-" + str0month1 + "-01";
                    }
                    str0 = newtime;
                    newtime = str0year1 + "-" + str0month1 + "-"
                            + str0day1;
                    sql =  "SELECT  COUNT(*)  FROM ( SELECT c.return_visit_date AS returnvisitDate,c.satisfaction AS satis,"+
            		"(SELECT datavalue  FROM xs_childdictionary A  WHERE A.datakey = c.satisfaction) AS satisName FROM  fbk_collect  c   where c.enterprise_id="+vTrackRecordVo.getEnterpriseId()+")bb " +
            		" WHERE bb.returnvisitDate  BETWEEN '"+ str0+ "' AND '"
                    + newtime+ "' " + strarry[j] + "";
                    List list  = vTrackRecordDAO.createSQLQuery(sql);
                    score = Integer.parseInt(list.get(0).toString());
                    // 获取map中的回访件数
                    if (map2.get("id" + i + "") != null)
                    {
                        mapstr = map2.get("id" + i + "").toString();
                        total = Integer.parseInt(mapstr.toString());
                    }
                    if (total > 0 && score > 0)
                    {
                        bili = score * 100 / total + "%";
                    }
                    if (total > 0 && score == 0)
                    {
                        bili = 0.00 + "%";
                    }
                    if (str0month1 < 10)
                    {
                        if (total > 0)
                        {
                            if (score * 100 / total == 0)
                            {
                                map.put("date" + str0year1 + "-" + "0"
                                        + str0month1, "");
                            }
                            else
                            {
                                map.put("date" + str0year1 + "-" + "0"
                                        + str0month1, bili);
                            }
                        }
                        else
                        {
                            if (mapstr.equals("0"))
                            {
                                map.put("date" + str0year1 + "-" + "0"
                                        + str0month1, "");
                            }
                            else
                            {
                                map.put("date" + str0year1 + "-" + "0"
                                        + str0month1, list.get(0));
                            }
                        }

                    }
                    else
                    {
                        if (total > 0)
                        {
                            if (score * 100 / total == 0)
                            {
                                map.put("date" + str0year1 + "-"
                                        + str0month1, "");
                            }
                            
                            else
                            {
                                map.put("date" + str0year1 + "-"
                                        + str0month1, bili);
                            }

                        }
                        else
                        {
                            if (mapstr.equals("0"))
                            {
                                map.put("date" + str0year1 + "-"
                                        + str0month1, "");
                            }
                            else
                            {
                                if (mapstr.equals("0"))
                                {
                                    map.put("date" + str0year1 + "-"
                                            + str0month1, "");
                                }
                                else
                                {
                                    map.put("date" + str0year1 + "-"
                                            + str0month1, list
                                            .get(0));
                                }
                            }
                        }
                    }
                    str0month1 = str0month1 + 1;
                    // 将回访件数至map
                    if (index == 1)
                    {
                        map2.put("id" + i + "", list.get(0));
                    }
                }// for 列
                newlist.add(map);
                index = 2;
            }// for 行
        }// if
        return newlist;
    
	}

	/**
	 * 获取维修时间段分析折线图信息 
	 * */
	
	public JFreeChart findServiceTimeAnalyzeSnapMap(VTrackRecordVo mtaVo)
			throws Exception {
	DatagridAnalyze dga=findCollect(mtaVo);
		List<VTrackRecordVo> rows=dga.getRows();
		List<SnapMap> snapList=new ArrayList();
		String snapName="维修时间段分析折线图";
		SnapMap sm=null;
		if(rows!=null&&rows.size()>0){
			if(mtaVo.getSelectedField()!=null&&mtaVo.getSelectedField().length()>0){
				if(mtaVo.getSelectedField().equals("eightBefore")){
					snapName="(08前)"+snapName;
				}else if(mtaVo.getSelectedField().equals("eightAndNine")){
					snapName="(08-09)"+snapName;
				}else if(mtaVo.getSelectedField().equals("nineAndTen")){
					snapName="(09-10)"+snapName;
				}else if(mtaVo.getSelectedField().equals("tenAndEleven")){
					snapName="(10-11)"+snapName;
				}else if(mtaVo.getSelectedField().equals("elevenAndTwelve")){
					snapName="(11-12)"+snapName;
				}else if(mtaVo.getSelectedField().equals("tweleveAndThirteen")){
					snapName="(12-13)"+snapName;
				}else if(mtaVo.getSelectedField().equals("thirteenAndFourteen")){
					snapName="(13-14)"+snapName;
				}else if(mtaVo.getSelectedField().equals("fourteenAndFifteen")){
					snapName="(14-15)"+snapName;
				}else if(mtaVo.getSelectedField().equals("fifteenAndSixteen")){
					snapName="(15-16)"+snapName;
				}else if(mtaVo.getSelectedField().equals("sixteenAndSeventeen")){
					snapName="(16-17)"+snapName;
				}else if(mtaVo.getSelectedField().equals("seventeenAndEighteen")){
					snapName="(17-18)"+snapName;
				}else if(mtaVo.getSelectedField().equals("enighteenAfter")){
					snapName="(18后)"+snapName;
				}
			}			
				for (VTrackRecordVo temp : rows) {
					
					
					
					sm=new SnapMap();
				/*	sm.setSnapYData(Double.parseDouble(temp.getSumCount().toString()));
					sm.setSnapXData(temp.getEnrolDate());*/
					sm.setSnapLineName("接待量折线");
					sm.setSnapLineColor(Color.red);
					snapList.add(sm);
				}	
		}
		/*
		 * snapMapName="无标题";//图表标题
			snapXName="X轴";//目录轴的显示标签 
			snapYName="y轴";//数值轴的显示标签  
			List<SnapMap> snapList=null;//构造折线数据
			snapMapName_font_name="黑体";//标题字体
			snapMapName_font_style=Font.ITALIC;//标题风格
			//snapMapName_font_size=22;//标题大小
			snapMapItem_font_name="宋体";//图例字体
			snapMapItem_font_style=Font.BOLD;//图例风格
			//snapMapItem_font_size=14;//图例大小
			snapX_font_name="宋体";//目录轴的显示标签字体 
			snapX_font_style=Font.BOLD;//目录轴的显示标签风格
			//snapX_font_size=22;//目录轴的显示标签大小
			snapY_font_name="宋体";//数值轴的显示标签字体
			snapY_font_style=Font.BOLD;//数值轴的显示标签风格
			//snapY_font_size=22;//数值轴的显示标签大小
			snapX_font_angle=CategoryLabelPositions.UP_45;//目录轴的显示标签倾斜度
			snapXData_font_name="宋体";//目录轴数据字体 
			snapXData_font_style=Font.BOLD;//目录轴数据风格
			//snapXData_font_size=22;//目录轴数据大小
			fackgroundAlpha=0.9f;//背景透明度（0~1） 
			foregroundAlpha=0.5f;//前景透明度（0~1）
			//flag=false;//2D,3D标示，true为3D
		 * */
		return CreateJFreeChart.findSnapMap(snapName, "时间段", "登记量", snapList,null,Font.BOLD,20,null,Font.PLAIN,14,
				null,Font.PLAIN,14,	null,Font.PLAIN ,14,CategoryLabelPositions.UP_45,null,Font.PLAIN,11,null,null,mtaVo.getIs3D());

}

	private DatagridAnalyze findCollect(VTrackRecordVo mtaVo) throws Exception {
		DatagridAnalyze dga=new DatagridAnalyze();
		dga.setRows(null);
		int total=vTrackRecordDAO.getSQLCount("", null);
		dga.setTotal(total);
		dga.setFooter(null);
		return dga;
	}


}