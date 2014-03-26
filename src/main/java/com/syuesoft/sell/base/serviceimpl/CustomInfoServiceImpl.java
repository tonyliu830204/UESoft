package com.syuesoft.sell.base.serviceimpl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.dao.BasStuffDao;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.BasStuff;
import com.syuesoft.sell.base.dao.BaseTagDAO;
import com.syuesoft.sell.base.dao.CustomInfoDAO;
import com.syuesoft.sell.base.dao.CustomLevaDAO;
import com.syuesoft.sell.base.service.CustomInfoService;
import com.syuesoft.sell.base.vo.CustomInfoVo;
import com.syuesoft.sell.model.XsChilddictionary;
import com.syuesoft.sell.model.XsCustomInfo;
import com.syuesoft.sell.model.XsCustomLeva;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.Msg;

@Service("customInfoService")
public class CustomInfoServiceImpl extends BaseLogServiceImpl  implements CustomInfoService {
	private CustomInfoDAO customInfoDAO;
	private BaseTagDAO baseTagDAO;
	private CustomLevaDAO customLevaDAO;
	private BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	@Autowired
	private BasStuffDao basStuffDao ;
	
	public Datagrid getPager(CustomInfoVo customInfoVo) throws Exception {
		Datagrid dg = new Datagrid();
		StringBuffer hql=new StringBuffer("from XsCustomInfo sell where 1=1 and sell.enterpriseId="+customInfoVo.getEnterpriseId());
		if(customInfoVo.getCode()!=null && !("".equals(customInfoVo.getCode()))){
			hql.append(" and sell.xsCustomCode like '%"+StringEscapeUtils.escapeSql(customInfoVo.getCode().trim())+"%'");
		}
		if(customInfoVo.getName()!=null && !("".equals(customInfoVo.getName()))){
			hql.append(" and sell.xsCustomName like '%"+StringEscapeUtils.escapeSql(customInfoVo.getName().trim())+"%'");
		}
		if(customInfoVo.getPhone()!=null && !("".equals(customInfoVo.getPhone()))){
			hql.append(" and sell.xsCustomPhone like '%"+StringEscapeUtils.escapeSql(customInfoVo.getPhone().trim())+"%'");
		}
		if(customInfoVo.getReceiptor()!=null && !("".equals(customInfoVo.getReceiptor()))){
			hql.append(" and sell.xsContactsPerson like '%"+StringEscapeUtils.escapeSql(customInfoVo.getReceiptor().trim())+"%'");
		}
		if(customInfoVo.getDealState()!=null && !("".equals(customInfoVo.getDealState()))){
			hql.append(" and sell.xsCustomDeal like '%"+StringEscapeUtils.escapeSql(customInfoVo.getDealState().toString().trim())+"%'");
		}
		if(customInfoVo.getStf()!=null && !("".equals(customInfoVo.getStf()))){
			hql.append(" and sell.stfId ="+customInfoVo.getStf());
		}
		if(customInfoVo.getInputdataStart()!=null && !("".equals(customInfoVo.getInputdataStart()))){
				hql.append(" and sell.xsCustomInputdata >='"+customInfoVo.getInputdataStart()+"'");
			}
		if(customInfoVo.getInputdataEnd()!=null && !("".equals(customInfoVo.getInputdataEnd()))){
				hql.append(" and sell.xsCustomInputdata <='"+customInfoVo.getInputdataEnd()+"'");
		}
		if((customInfoVo.getInputdataStart()==null || "".equals(customInfoVo.getInputdataStart()))
			&&(customInfoVo.getInputdataEnd()==null ||"".equals(customInfoVo.getInputdataEnd()))){
			hql.append(" and sell.xsCustomInputdata between " +
					"'"+FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, (basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,customInfoVo.getEnterpriseId()).getCiValue()))+"'" +
							" and '"+FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+"'");
		}
		
		if (customInfoVo.getSort() != null && !"".equals(customInfoVo.getSort().trim())
                && customInfoVo.getOrder() != null
                && !"".equals(customInfoVo.getOrder().trim()))
        {
		  hql.append(" order by sell." + customInfoVo.getSort() + " "
                    + customInfoVo.getOrder());
        }
		List<XsCustomInfo> lst=customInfoDAO.find(hql.toString(), customInfoVo.getPage(), customInfoVo.getRows());
		List<CustomInfoVo> rows =new ArrayList<CustomInfoVo>();
		if(lst!=null && lst.size()>0){
			for(XsCustomInfo custom:lst){
				CustomInfoVo cInfoVo=new CustomInfoVo();
				BeanUtils.copyProperties(custom, cInfoVo);
				setBean( cInfoVo);
				rows.add(cInfoVo);
			}
		}
		int total = customInfoDAO.getCount(hql.toString());
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}
	public void setBean(CustomInfoVo cInfoVo) throws  Exception{
		XsChilddictionary  child=null;
		if(cInfoVo.getXsCustomSex()!=null && !("".equals(cInfoVo.getXsCustomSex()))){
			child=baseTagDAO.findById(cInfoVo.getXsCustomSex());
			if(child!=null){
				cInfoVo.setSexName(child.getDataValue());
			}
		}
		if(cInfoVo.getXsCustomIncome()!=null && !("".equals(cInfoVo.getXsCustomIncome()))){
			child=baseTagDAO.findById(cInfoVo.getXsCustomIncome());
			if(child!=null){
				cInfoVo.setIncomeName(child.getDataValue());
			}
		}
		if(cInfoVo.getXsCustomOccupation()!=null && !("".equals(cInfoVo.getXsCustomOccupation()))){
			child=baseTagDAO.findById(cInfoVo.getXsCustomOccupation());
			if(child!=null){
				cInfoVo.setOccupationName(child.getDataValue());
			}
		}
		if(cInfoVo.getXsCustomDiploma()!=null && !("".equals(cInfoVo.getXsCustomDiploma()))){
			child=baseTagDAO.findById(cInfoVo.getXsCustomDiploma());
			if(child!=null){
				cInfoVo.setDiplomaName(child.getDataValue());
			}
		}
		
		if(cInfoVo.getXsCustomSource()!=null && !("".equals(cInfoVo.getXsCustomSource()))){
			child=baseTagDAO.findById(cInfoVo.getXsCustomSource());
			if(child!=null){
				cInfoVo.setSourceName(child.getDataValue());
			}
		}
		if(cInfoVo.getXsCustomArea()!=null && !("".equals(cInfoVo.getXsCustomArea()))){
			child=baseTagDAO.findById(cInfoVo.getXsCustomArea());
			if(child!=null){
				cInfoVo.setAreaName(child.getDataKey());
			}
		}
		if(cInfoVo.getStfId()!=null && !("".equals(cInfoVo.getStfId()))){
			List<BasStuff> us=baseTagDAO.getUserNameByStfId((long)(cInfoVo.getStfId()));
			if(us!=null && us.size()>0){
				cInfoVo.setStfName(us.get(0).getStfName());
			}
		}
		if(cInfoVo.getXsCustomProperty()!=null && !("".equals(cInfoVo.getXsCustomProperty()))){
			child=baseTagDAO.findById(cInfoVo.getXsCustomProperty());
			if(child!=null){
				cInfoVo.setPropertyName(child.getDataValue());
			}
		}
		if(cInfoVo.getXsCustomTrade()!=null && !("".equals(cInfoVo.getXsCustomTrade()))){
			child=baseTagDAO.findById(cInfoVo.getXsCustomTrade());
			if(child!=null){
				cInfoVo.setTradeName(child.getDataValue());
			}
		}
		if(cInfoVo.getXsCustomAfter()!=null && !("".equals(cInfoVo.getXsCustomAfter()))){
			child=baseTagDAO.findById(cInfoVo.getXsCustomAfter());
			if(child!=null){
				cInfoVo.setAfterName(child.getDataValue());
			}
		}
		if(cInfoVo.getXsCustomHideLevel()!=null && !("".equals(cInfoVo.getXsCustomHideLevel()))){
			XsCustomLeva leva=customLevaDAO.get(XsCustomLeva.class,cInfoVo.getXsCustomHideLevel());
			if(leva!=null){
				cInfoVo.setHideLevelName(leva.getLevaName());
			}
		}
		if(cInfoVo.getXsCustomContrastModel()!=null && !("".equals(cInfoVo.getXsCustomContrastModel()))){
			child=baseTagDAO.findById(cInfoVo.getXsCustomContrastModel());
			if(child!=null){
				cInfoVo.setContrastModelName(child.getDataValue());
			}
		}
		if(cInfoVo.getXsCustomReason()!=null && !("".equals(cInfoVo.getXsCustomReason()))){
			child=baseTagDAO.findById(cInfoVo.getXsCustomReason());
			if(child!=null){
				cInfoVo.setReasonName(child.getDataValue());
			}
		}
		if(cInfoVo.getXsCustomApplication()!=null && !("".equals(cInfoVo.getXsCustomApplication()))){
			child=baseTagDAO.findById(cInfoVo.getXsCustomApplication());
			if(child!=null){
				cInfoVo.setApplicationName(child.getDataValue());
			}
		}
		if(cInfoVo.getXsCustomOtherType()!=null && !("".equals(cInfoVo.getXsCustomOtherType()))){
			child=baseTagDAO.findById(cInfoVo.getXsCustomOtherType());
			if(child!=null){
				cInfoVo.setOtherTypeName(child.getDataValue());
			}
		}
		if(cInfoVo.getXsCustomDeal()!=null && !("".equals(cInfoVo.getXsCustomDeal()))){
			child=baseTagDAO.findById(cInfoVo.getXsCustomDeal());
			if(child!=null){
				cInfoVo.setCustomDealName(child.getDataValue());
			}
		}
	}
	
	
	@Log(systemName="销售系统", moduleName="客户档案",opertype="删除")
	public Msg deleteCustomInfo(CustomInfoVo customInfoVo)  throws Exception{	
		Msg msg = new Msg();
		List list=customInfoDAO.createSQLQuery(" select car.custom_id from xs_sell_car_reserve car" +
				" join xs_sell_flow_control flow on flow.xsfcontrol_code='"+Contstants.SELLFLOWCONTROLCODE.CONTROLCODE4+"'" +
				"and flow.xsfcontrol_document=car.reserve_code " +
				"where car.custom_id="+customInfoVo.getCustomId());
		List list2=customInfoDAO.createSQLQuery(" select sell.custom_id from xs_car_sell_info sell" +
				" join xs_sell_flow_control flow on flow.xsfcontrol_car_id=sell.xs_car_id " +
				"and flow.xsfcontrol_document=sell.sell_code " +
				"where sell.custom_id="+customInfoVo.getCustomId());
		if(list!=null && list.size()>0){
			msg.setMsg("该客户已存在预定单中，不可以删除！");
			msg.setSuccess(false);
		}else if(list2!=null && list2.size()>0){
			msg.setMsg("该客户已存在销售中，不可以删除！");
			msg.setSuccess(false);
		}else{
		XsCustomInfo customInfo=new XsCustomInfo();
			BeanUtils.copyProperties(customInfoVo, customInfo);
			customInfoDAO.delete(customInfo);
			setContent("删除【客户档案】编码为【"+customInfo.getXsCustomCode()+"】," +
					"客户姓名为【"+customInfo.getXsCustomName()+"】," +
							"身份证号为【"+customInfo.getXsCustomCredentials()+"】的信息！");
			msg.setMsg("删除成功！");
			msg.setSuccess(true);
		}
		return msg;
	}
	
	@Log(systemName="销售系统", moduleName="客户档案",opertype="新增")
	public Serializable addCustomInfo(CustomInfoVo customInfoVo) throws Exception {
			XsCustomInfo customInfo=new XsCustomInfo();
			BeanUtils.copyProperties(customInfoVo, customInfo);
			customInfo.setXsCustomCode(CreateID.createId("CustomInfo", Contstants.SELL_BILLSDEPLOY.CUSTOMTNFOR));
			XsChilddictionary sex = null;
			XsChilddictionary area = null;
			if(customInfo.getXsCustomSex()!=null && !customInfo.getXsCustomSex().equals("")){
				sex = baseTagDAO.findById(customInfo.getXsCustomSex());
			}
			if(customInfo.getXsCustomArea()!=null && !customInfo.getXsCustomArea().equals("")){
				area = baseTagDAO.findById(customInfo.getXsCustomArea());
			}
			BasStuff stuff=(BasStuff)basStuffDao.get(" from BasStuff where stfId="+customInfo.getStfId());
			setContent("新增【客户档案】编码为【"+customInfo.getXsCustomCode()+"】," +
					"客户姓名为【"+customInfo.getXsCustomName()+"】," +
					//"性别为【"+sex!=null ? sex.getDataValue() : ""+"】," +
					"身份证号为【"+customInfo.getXsCustomCredentials()+"】," +
					"手机号为【"+customInfo.getXsCustomTelephone()+"】," +
					"业务员为【"+stuff.getStfName()+"】,"
					//+"所在区域为【"+area!=null ? area.getDataKey() : ""+"】的信息！"
							);
			return customInfoDAO.save(customInfo);
			
	}
	
	@Log(systemName="销售系统", moduleName="客户档案",opertype="修改")
	public void updateCustomInfo(CustomInfoVo customInfoVo) throws Exception {
			XsCustomInfo customInfo = new XsCustomInfo();
			BeanUtils.copyProperties(customInfoVo, customInfo);
			customInfoDAO.merge(customInfo);
			XsChilddictionary sex=baseTagDAO.findById(customInfo.getXsCustomSex());
			XsChilddictionary area=baseTagDAO.findById(customInfo.getXsCustomArea());
			BasStuff stuff=(BasStuff)basStuffDao.get(" from BasStuff where stfId="+customInfo.getStfId());
			setContent("修改编码为【"+customInfo.getXsCustomCode()+"】的【客户档案】," +
					"客户姓名为【"+customInfo.getXsCustomName()+"】,性别为【"+sex.getDataValue()+"】," +
					"身份证号为【"+customInfo.getXsCustomCredentials()+"】," +
					"手机号为【"+customInfo.getXsCustomTelephone()+"】," +
					"业务员为【"+stuff.getStfName()+"】,所在区域为【"+area.getDataKey()+"】！");
	}
	public boolean findByNumber (CustomInfoVo customInfoVo)throws Exception{
			if(customInfoVo.getXsCustomNumber()!=null && !("".equals(customInfoVo.getXsCustomNumber()))){
				List<XsCustomInfo> lst=customInfoDAO.findByNumber(customInfoVo.getXsCustomNumber(),customInfoVo.getEnterpriseId());
				if(lst!=null && lst.size()>0){
					return true;
				}
			}
		return false;
	}
	public boolean findByNumberEdit (CustomInfoVo customInfoVo)throws Exception{
			if(customInfoVo.getXsCustomNumber()!=null && !("".equals(customInfoVo.getXsCustomNumber()))){
				List<XsCustomInfo> lst=customInfoDAO.findByNumber(customInfoVo.getXsCustomNumber(),customInfoVo.getCustomId(),customInfoVo.getEnterpriseId());
				if(lst!=null && lst.size()>0){
					return true;
				}
			}
		return false;
	}
	public CustomInfoDAO getCustomInfoDAO() {
		return customInfoDAO;
	}
	@Autowired
	public void setCustomInfoDAO(CustomInfoDAO customInfoDAO) {
		this.customInfoDAO = customInfoDAO;
	}
	public BaseTagDAO getBaseTagDAO() {
		return baseTagDAO;
	}
	@Autowired
	public void setBaseTagDAO(BaseTagDAO baseTagDAO) {
		this.baseTagDAO = baseTagDAO;
	}

	public CustomLevaDAO getCustomLevaDAO() {
		return customLevaDAO;
	}
	@Autowired
	public void setCustomLevaDAO(CustomLevaDAO customLevaDAO) {
		this.customLevaDAO = customLevaDAO;
	}
	
	public BasCompanyInformationSetDAO getBasCompanyInformationSetDAO() {
		return basCompanyInformationSetDAO;
	}
	@Autowired
	public void setBasCompanyInformationSetDAO(
			BasCompanyInformationSetDAO basCompanyInformationSetDAO) {
		this.basCompanyInformationSetDAO = basCompanyInformationSetDAO;
	}
	
	//销售作业：客户档案查询
	
	public Datagrid getCustomInfo(CustomInfoVo customInfoVo) throws Exception {
		Datagrid dg = new Datagrid();
		List<CustomInfoVo> list = new ArrayList<CustomInfoVo>();
		StringBuffer sql = new StringBuffer("SELECT c.xs_custom_code,c.xs_custom_name,c.xs_custom_address,c.xs_custom_phone,c.xs_custom_telephone,c.xs_contacts_person,"+
					 "c.xs_custom_sex,(SELECT ch.dataValue FROM  xs_childdictionary ch WHERE ch.child_id=c.xs_custom_sex)AS custom_sex_name,c.xs_custom_birthday,c.xs_custom_income,"+
					 "(SELECT ch.dataValue FROM  xs_childdictionary ch WHERE ch.child_id=c.xs_custom_income)AS custom_income_name,c.xs_custom_occupation,"+
					 "(SELECT ch.dataValue FROM  xs_childdictionary ch WHERE ch.child_id=c.xs_custom_occupation)AS custom_occupation_name,c.xs_custom_diploma,"+
					 "(SELECT ch.dataValue FROM  xs_childdictionary ch WHERE ch.child_id=c.xs_custom_diploma)AS custom_diploma_name,c.xs_custom_source,"+
					 "(SELECT ch.dataValue FROM  xs_childdictionary ch WHERE ch.child_id=c.xs_custom_source)AS custom_source_name,c.xs_custom_inputdata,c.xs_custom_area,"+
					 "(SELECT ch.dataKey FROM  xs_childdictionary ch WHERE ch.child_id=c.xs_custom_area)AS custom_area_name,c.xs_custom_company,c.xs_custom_zipcode,c.STF_ID,"+
					 "c.xs_custom_credentials,c.xs_custom_property,(SELECT ch.dataValue FROM  xs_childdictionary ch WHERE ch.child_id=c.xs_custom_property)AS custom_property_name,"+
					 "c.xs_custom_deal,(SELECT ch.dataValue FROM  xs_childdictionary ch WHERE ch.child_id=c.xs_custom_deal)AS custom_deal_name,c.xs_custom_trade,"+
					 "(SELECT ch.dataValue FROM  xs_childdictionary ch WHERE ch.child_id=c.xs_custom_trade)AS custom_trade_name,c.xs_custom_other,s.xs_car_id,B.STF_NAME " +
					 " FROM  xs_custom_info c"+
					 " JOIN xs_car_sell_info s ON c.custom_id=s.custom_id  " +
					 "  JOIN xs_car_info  car  ON car.xs_car_id=s.xs_car_id " +
					 "  JOIN bas_stuff B ON c.STF_ID = B.STF_ID" +
					 " where 1=1 ");
		if(customInfoVo.getEnterpriseId()!=null && !("".equals(customInfoVo.getEnterpriseId()))){
			sql.append(" and c.enterprise_id="+customInfoVo.getEnterpriseId());
			
		}
		if(customInfoVo.getXsCustomName()!=null && !("".equals(customInfoVo.getXsCustomName()))){
			sql.append("	and c.xs_custom_name like '%"+StringEscapeUtils.escapeSql(customInfoVo.getXsCustomName().trim())+"%'");
		}
		if(customInfoVo.getXsCustomPhone()!=null && !("".equals(customInfoVo.getXsCustomPhone()))){
			sql.append("    and  c.xs_custom_phone like '%"+StringEscapeUtils.escapeSql(customInfoVo.getXsCustomPhone().trim())+"%'");
		}
		if(customInfoVo.getXsContactsPerson()!=null && !("".equals(customInfoVo.getXsContactsPerson()))){
			sql.append("	and  c.xs_contacts_person like '%"+StringEscapeUtils.escapeSql(customInfoVo.getXsContactsPerson().trim())+"%'");
		}
		if(customInfoVo.getStfId()!=null && !("".equals(customInfoVo.getStfId()))){
			sql.append("	and  c.STF_ID ="+customInfoVo.getStfId());
		}
		if(customInfoVo.getXsCustomOccupation()!=null && !("".equals(customInfoVo.getXsCustomOccupation()))){
			sql.append("	and c.xs_custom_occupation="+customInfoVo.getXsCustomOccupation());
		}
		if(customInfoVo.getXsCustomDeal()!=null && !("".equals(customInfoVo.getXsCustomDeal()))){
			sql.append(" and  c.xs_custom_deal="+customInfoVo.getXsCustomDeal());	
		}
		if(customInfoVo.getXsCustomProperty()!=null && !("".equals(customInfoVo.getXsCustomProperty()))){
			sql.append(" and c.xs_custom_property="+customInfoVo.getXsCustomProperty());
		}
		if(customInfoVo.getXsCustomIncome()!=null && !("".equals(customInfoVo.getXsCustomIncome()))){
			sql.append(" and c.xs_custom_income="+customInfoVo.getXsCustomIncome());
		}
		if(customInfoVo.getXsCustomDiploma()!=null && !("".equals(customInfoVo.getXsCustomDiploma()))){
			sql.append(" and c.xs_custom_diploma="+customInfoVo.getXsCustomDiploma());
		}
		
		if(customInfoVo.getXsCustomSource()!=null && !("".equals(customInfoVo.getXsCustomSource()))){
			sql.append(" and c.xs_custom_source="+customInfoVo.getXsCustomSource());
		}
		if(customInfoVo.getXsCustomArea()!=null && !("".equals(customInfoVo.getXsCustomArea()))){
			sql.append(" and c.xs_custom_area="+customInfoVo.getXsCustomArea());
		}
		if(customInfoVo.getXsCustomTrade()!=null && !("".equals(customInfoVo.getXsCustomTrade()))){
			sql.append(" and c.xs_custom_trade="+customInfoVo.getXsCustomTrade());
		}
		if(customInfoVo.getCarVinNumber()!=null && !("".equals(customInfoVo.getCarVinNumber()))){
			sql.append(" and car.xs_car_vin_number like '%"+StringEscapeUtils.escapeSql(customInfoVo.getCarVinNumber().trim())+"%'");
		}
		if(customInfoVo.getCarOcn()!=null && !("".equals(customInfoVo.getCarOcn()))){
			sql.append(" and car.xs_car_ocn like '%"+StringEscapeUtils.escapeSql(customInfoVo.getCarOcn().trim())+"%'");
		}


		if (customInfoVo.getXsCustomInputdata() != null
				&& !customInfoVo.getXsCustomInputdata().equals("")) {
					sql.append(" and DATE(c.xs_custom_inputdata) >= '" +customInfoVo.getXsCustomInputdata() + "'");
				} 
		if (customInfoVo.getXsCustomInputdata2() != null
				&& !customInfoVo.getXsCustomInputdata2().equals("")) {
					sql.append(" and DATE(c.xs_custom_inputdata) <= '" +customInfoVo.getXsCustomInputdata2() + "'");
		}
		if((customInfoVo.getXsCustomInputdata() == null
				|| customInfoVo.getXsCustomInputdata().equals("")) 
				&& (customInfoVo.getXsCustomInputdata2() == null
				|| customInfoVo.getXsCustomInputdata2().equals(""))){
			sql.append(" and  Date(c.xs_custom_inputdata) between " +
					"'" +FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2,(basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES,Contstants.COLLIGATES.DEFAULTSHOWDAY,customInfoVo.getEnterpriseId()).getCiValue()))+"'"
					+" and '"+FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+"'");
		}

			
		List<Object[]> resultList = customInfoDAO.createSQLQuery(sql.toString(),customInfoVo.getPage(), customInfoVo.getRows());
		if (resultList != null && resultList.size() > 0) {
			for (int i = 0; i < resultList.size(); i++) {
				Object [] obj=resultList.get(i);
				CustomInfoVo cVo=new CustomInfoVo();
				if(obj[0]!=null && !("".equals(obj[0]))){
					cVo.setXsCustomCode(obj[0].toString());
				}
				if(obj[1]!=null && !("".equals(obj[1]))){
					cVo.setXsCustomName(obj[1].toString());
				}
				if(obj[2]!=null && !("".equals(obj[2]))){
					cVo.setXsCustomAddress(obj[2].toString());
				}
				if(obj[3]!=null && !("".equals(obj[3]))){
					cVo.setXsCustomPhone(obj[3].toString());
				}
				if(obj[4]!=null && !("".equals(obj[4]))){
					cVo.setXsCustomTelephone(obj[4].toString());
				}
				if(obj[5]!=null && !("".equals(obj[5]))){
					cVo.setXsContactsPerson(obj[5].toString());
				}
				if(obj[6]!=null && !("".equals(obj[6]))){
					cVo.setXsCustomSex((Integer)obj[6]);
				}
				if(obj[7]!=null && !("".equals(obj[7]))){
					cVo.setSexName(obj[7].toString());
				}
				if(obj[8]!=null && !("".equals(obj[8]))){
					cVo.setXsCustomBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(obj[8].toString()));
				}
				if(obj[9]!=null && !("".equals(obj[9]))){
					cVo.setXsCustomIncome((Integer)obj[9]);
				}
				if(obj[10]!=null && !("".equals(obj[10]))){
					cVo.setIncomeName(obj[10].toString());
				}
				if(obj[11]!=null && !("".equals(obj[11]))){
					cVo.setXsCustomOccupation((Integer)obj[11]);	
				}
				
				if(obj[12]!=null && !("".equals(obj[12]))){
					cVo.setOccupationName(obj[12].toString());
				}
				if(obj[13]!=null && !("".equals(obj[13]))){
					cVo.setXsCustomDiploma((Integer)obj[13]);
				}
				if(obj[14]!=null && !("".equals(obj[14]))){
					cVo.setDiplomaName(obj[14].toString());
				}
				if(obj[15]!=null && !("".equals(obj[15]))){
					cVo.setXsCustomSource((Integer)obj[15]);
				}
				if(obj[16]!=null && !("".equals(obj[16]))){
					cVo.setSourceName(obj[16].toString());
				}
				if(obj[17]!=null && !("".equals(obj[17]))){
					cVo.setXsCustomInputdata(obj[17].toString());
				}
				if(obj[18]!=null && !("".equals(obj[18]))){
					cVo.setXsCustomArea((Integer)obj[18]);
				}
				if(obj[19]!=null && !("".equals(obj[19]))){
					cVo.setAreaName(obj[19].toString());
				}
				if(obj[20]!=null && !("".equals(obj[20]))){
					cVo.setXsCustomCompany(obj[20].toString());
				}
				if(obj[21]!=null && !("".equals(obj[21]))){
					cVo.setXsCustomZipcode(obj[21].toString());
				}
				if(obj[22]!=null && !("".equals(obj[22]))){
					cVo.setStfId((Integer)obj[22]);
				}
				if(obj[23]!=null && !("".equals(obj[23]))){
					cVo.setXsCustomCredentials(obj[23].toString());
				}
				if(obj[24]!=null && !("".equals(obj[24]))){
					cVo.setXsCustomProperty((Integer)obj[24]);
				}
				if(obj[25]!=null && !("".equals(obj[25]))){
					cVo.setPropertyName(obj[25].toString());
				}
				if(obj[26]!=null && !("".equals(obj[26]))){
					cVo.setXsCustomDeal((Integer)obj[26]);
				}
				if(obj[27]!=null && !("".equals(obj[27]))){
					cVo.setCustomDealName(obj[27].toString());
				}
				if(obj[28]!=null && !("".equals(obj[28]))){
					cVo.setXsCustomTrade((Integer)obj[28]);
				}
				if(obj[29]!=null && !("".equals(obj[29]))){
					cVo.setTradeName(obj[29].toString());
				}
				if(obj[30]!=null && !("".equals(obj[30]))){
					cVo.setXsCustomOther(obj[30].toString());
				}
				if(obj[31]!=null && !("".equals(obj[31]))){
					cVo.setXsCarId((Integer)obj[31]);
				}
				if(obj[32]!=null && !("".equals(obj[32]))){
					cVo.setStfName(obj[32].toString());
				}
				list.add(cVo);	
			}
		}
		int total = customInfoDAO.getSQLCount(sql.toString(), null);
		dg.setRows(list);
		dg.setTotal(total);
		return dg;
	}
	
	public boolean isExtisCustomCard (CustomInfoVo customInfoVo) throws Exception{
		String sql="";
		if(customInfoVo.getCustomId()!=null&&!"".equals(customInfoVo.getCustomId())){
			sql="SELECT * FROM xs_custom_info c WHERE c.xs_custom_credentials='"+customInfoVo.getXsCustomCredentials()+"' " +
					"and c.custom_id !='"+customInfoVo.getCustomId()+"'" ;
		}else{
			sql="SELECT c.custom_id  FROM xs_custom_info c WHERE c.xs_custom_credentials='"+customInfoVo.getXsCustomCredentials()+"'" ;
		}
		if(customInfoVo.getEnterpriseId()!=null&&!"".equals(customInfoVo.getEnterpriseId())){
			sql+="and c.enterprise_id ="+customInfoVo.getEnterpriseId();
			
		}
		List<Object[]> cars=customInfoDAO.createSQLQuery(sql);
		if(cars!=null && cars.size()>0){
			return true;
		}
		return false;
	}
	
	public boolean isExtisCustomCardTwo(CustomInfoVo customInfoVo)
			throws Exception {
		List<Object[]> cars=customInfoDAO.createSQLQuery("SELECT * FROM xs_custom_info c WHERE c.xs_custom_credentials='"+customInfoVo.getXsCustomCredentials()+"' " +
				"and c.custom_id !='"+customInfoVo.getCustomId()+"'");
		if(cars!=null && cars.size()>0){
			return true;
		}
		return false;
	}
	
	
}
