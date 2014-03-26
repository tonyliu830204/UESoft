package com.syuesoft.sell.noteManage.daoimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.BaseBean;
import com.syuesoft.sell.noteManage.dao.NoteManageDao;
import com.syuesoft.sell.noteManage.vo.NoteManageVo;
import com.syuesoft.util.FormatTime;
@Repository("noteManageDao")
public class NoteManageDaoImpl extends BaseDaoImpl<BaseBean> implements NoteManageDao {
	@Autowired
	private BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	
	public Datagrid getCustomInfo(NoteManageVo noteManageVo) throws Exception {
		Datagrid dg = new Datagrid();
		List<NoteManageVo> list = new ArrayList<NoteManageVo>();
		StringBuffer sql = new StringBuffer("SELECT A.custom_id,A.xs_custom_name,A.xs_custom_phone," +
				"A.xs_custom_telephone, A.xs_custom_birthday,A.xs_custom_address,A.xs_custom_area," +
				"(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =A.xs_custom_area) AS areaq," +
				"A.xs_custom_hide_level,g.xs_leva_name,A.xs_custom_deal ," +
				"(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =A.xs_custom_deal) AS deal," +
				"A.xs_custom_company,A.STF_ID,e.STF_NAME," +
				"e.STF_PHONE,b.xs_car_sel_data," +
				"c.xs_car_brand,(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =c.xs_car_brand) AS _brand," +
				"c.xs_car_model_id,d.xs_model_name,c.xs_car_licensePlate,c.xs_car_vin_number," +
				"f.insurer_end_date" +
				" FROM xs_custom_info A" +
				" LEFT JOIN xs_car_sell_info  B ON a.custom_id=b.custom_id  " +
				" LEFT JOIN xs_car_info C ON c.xs_car_id=b.xs_car_id " +
				" LEFT JOIN xs_car_model D ON D.xs_model_id=c.xs_car_model_id" +
				" LEFT JOIN bas_stuff E ON e.STF_ID=a.STF_ID " +
				" LEFT JOIN xs_sell_insurance F ON f.xs_car_sel_id=b.xs_car_sel_id" +
				" LEFT JOIN xs_custom_leva G ON  g.xs_leva_id=a.xs_custom_hide_level" +
				" where 1=1");
		if (noteManageVo.getEnterprise_id() != null
				&& !"".equals(noteManageVo.getEnterprise_id())) {
			sql.append(" and A.enterprise_id = '"
					+ noteManageVo.getEnterprise_id() + "'");
		}
		if (noteManageVo.getXsCustomHideLevel() != null
				&& !"".equals(noteManageVo.getXsCustomHideLevel())) {
			sql.append(" and A.xs_custom_hide_level = '"
					+ noteManageVo.getXsCustomHideLevel() + "'");
		}
		
		
		if (noteManageVo.getXsCustomBirthday() != null
				&& !"".equals(noteManageVo.getXsCustomBirthday())) {
					sql.append(" and A.xs_custom_birthday >= '" +noteManageVo.getXsCustomBirthday()+ "'");
		}
		if (noteManageVo.getXsCustomBirthday2() != null
				&& !"".equals(noteManageVo.getXsCustomBirthday2())) {
					sql.append(" and A.xs_custom_birthday <= '" +noteManageVo.getXsCustomBirthday2()+ "'");
		}
		if ((noteManageVo.getXsCustomBirthday() == null
				|| "".equals(noteManageVo.getXsCustomBirthday()))&&
				(noteManageVo.getXsCustomBirthday2() == null
						|| "".equals(noteManageVo.getXsCustomBirthday2()))) {
		sql.append(" and DATE(A.xs_custom_birthday) " +
				"between '"+FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, Integer.parseInt(basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, 
						Contstants.COLLIGATES.DEFAULTSHOWDAY,noteManageVo.getEnterprise_id()).getCiValue()))+"' and " +
								"'"+FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+"'");	
		}
		if (noteManageVo.getCarsellData() != null
				&& !"".equals(noteManageVo.getCarsellData())) {
			
					sql.append(" and b.xs_car_sel_data >='" + noteManageVo.getCarsellData() + "'");
				}
		if (noteManageVo.getCarsellData2() != null
				&& !"".equals(noteManageVo.getCarsellData2())) {
			
					sql.append(" and b.xs_car_sel_data <='" + noteManageVo.getCarsellData2() + "'");
				}
		if (noteManageVo.getInsurerEndDate() != null
				&& !"".equals(noteManageVo.getInsurerEndDate())) {
			
					sql.append(" and f.insurer_end_date >= '" +noteManageVo.getInsurerEndDate() + "'");
		} 
		if (noteManageVo.getInsurerEndDate2() != null
				&& !"".equals(noteManageVo.getInsurerEndDate2())) {
			
					sql.append(" and f.insurer_end_date <= '" + noteManageVo.getInsurerEndDate2() + "'");
		} 
		if (noteManageVo.getCustom_Name() != null
				&& !"".equals(noteManageVo.getCustom_Name())) {
			sql.append(" and A.xs_custom_name like '%"
					+ StringEscapeUtils.escapeSql(noteManageVo.getCustom_Name().trim()) + "%'");
		}
		if (noteManageVo.getXsCustomHideLevel() != null
				&& !"".equals(noteManageVo.getXsCustomHideLevel())) {
			sql.append(" and A.xs_custom_hide_level = '"
					+ noteManageVo.getXsCustomHideLevel() + "'");
		}
		if (noteManageVo.getXsCustomDeal() != null
				&& !"".equals(noteManageVo.getXsCustomDeal())) {
			sql.append(" and A.xs_custom_deal = '"
					+ noteManageVo.getXsCustomDeal() + "'");
		}
		if (noteManageVo.getTelephone() != null
				&& !"".equals(noteManageVo.getTelephone())) {
			sql.append(" and A.xs_custom_telephone = '"
					+ noteManageVo.getTelephone() + "'");
		}
		if (noteManageVo.getCarVinNumber() != null
				&& !"".equals(noteManageVo.getCarVinNumber())) {
			sql.append(" and c.xs_car_vin_number like '%"
					+ StringEscapeUtils.escapeSql(noteManageVo.getCarVinNumber().trim()) + "%'");
		}
		if (noteManageVo.getCarLicensePlate() != null
				&& !"".equals(noteManageVo.getCarLicensePlate())) {
			sql.append(" and c.xs_car_licensePlate like '%"
					+ StringEscapeUtils.escapeSql(noteManageVo.getCarLicensePlate().trim()) + "%'");
		}
		if (noteManageVo.getCarBrand() != null
				&& !"".equals(noteManageVo.getCarBrand())) {
			sql.append(" and c.xs_car_brand = '"
					+ noteManageVo.getCarBrand() + "'");
		}
		if (noteManageVo.getCarModelId() != null
				&& !"".equals(noteManageVo.getCarModelId())) {
			sql.append(" and c.xs_car_model_id = '"
					+ noteManageVo.getCarModelId() + "'");
		}
		if (noteManageVo.getStfId() != null
				&& !"".equals(noteManageVo.getStfId())) {
			sql.append(" and A.STF_ID = '"
					+ noteManageVo.getStfId() + "'");
		}
		if (noteManageVo.getXsCustomArea() != null
				&& !"".equals(noteManageVo.getXsCustomArea())) {
			sql.append(" and A.xs_custom_area = '"
					+ noteManageVo.getXsCustomArea() + "'");
		}
		List<Object[]> resultList = createSQLQuery(sql.toString(), noteManageVo
				.getPage(), noteManageVo.getRows());
		if (resultList != null && resultList.size() > 0) {
			SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
			Object[] obj = null;
			for (int i = 0; i < resultList.size(); i++) {
				NoteManageVo no = new NoteManageVo();
				obj = resultList.get(i);
				no.setCustomId(obj[0] != null ? Integer.parseInt(obj[0].toString()) : null);
				no.setCustom_Name(obj[1] != null ? obj[1].toString() : null);
				no.setXsCustomPhone(obj[2] != null ? obj[2].toString() : null);
				no.setTelephone(obj[3] != null ? obj[3].toString() : null);
				no.setXsCustomBirthday(obj[4] != null ? fmt.format(obj[4]) : null);
				no.setXsCustomAddress(obj[5] != null ? obj[5].toString() : null);
				no.setXsCustomArea(obj[6] != null ?  Integer.parseInt(obj[6].toString()) : null);
				no.setCustomArea(obj[7] != null ? obj[7].toString() : null);
				no.setXsCustomHideLevel(obj[8] != null ?  Integer.parseInt(obj[8].toString()) : null);
				no.setCustomLevel(obj[9] != null ? obj[9].toString() : null);
				no.setXsCustomDeal(obj[10] != null ?  Integer.parseInt(obj[10].toString()) : null);
				no.setCustomDeal(obj[11] != null ? obj[11].toString() : null);
				no.setXsCustomCompany(obj[12] != null ? obj[12].toString() : null);
				no.setStfId(obj[13] != null ?  Integer.parseInt(obj[13].toString()) : null);
				no.setStfName(obj[14] != null ? obj[14].toString() : null);
				no.setStfPhone(obj[15] != null ? obj[15].toString() : null);
				no.setCarsellData(obj[16] != null ? obj[16].toString() : null);
				no.setCarBrand(obj[17] != null ?Integer.parseInt( obj[17].toString()) : null);
				no.setCarBrandN(obj[18] != null ? obj[18].toString() : null);
				no.setCarModelId(obj[19] != null ?Integer.parseInt( obj[19].toString()) : null);
				no.setCarModelN(obj[20] != null ?obj[20].toString() : null);
				no.setCarLicensePlate(obj[21] != null ?obj[21].toString() : null);
				no.setCarVinNumber(obj[22] != null ?obj[22].toString() : null);
				no.setInsurerEndDate(obj[23] != null ?obj[23].toString() : null);
				list.add(no);
			}
		}
		int total = this.getSQLCount(sql.toString(), null);
		dg.setRows(list);
		dg.setTotal(total);
		return dg;
	}

}
