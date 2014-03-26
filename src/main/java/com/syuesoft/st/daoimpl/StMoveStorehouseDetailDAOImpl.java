package com.syuesoft.st.daoimpl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.model.StMoveStorehouseDetail;
import com.syuesoft.st.dao.StMoveStorehouseDetailDAO;
import com.syuesoft.st.vo.StMoveStorehouseVo;
/**
 * 移仓调拨单明细dao实现类
 * @author SuMing
 */
@Repository("stMoveStorehouseDetailDAO")
public class StMoveStorehouseDetailDAOImpl extends BaseDaoImpl<StMoveStorehouseDetail> implements StMoveStorehouseDetailDAO{

	/**
	 * 通过移仓单单号    加载移仓单明细信息
	 */
	public List<StMoveStorehouseVo> searchStMoveStorehouseDetailByMsdmId(final String msdmId)throws Exception
	{
		List<StMoveStorehouseVo> list = new ArrayList<StMoveStorehouseVo>();
		StringBuffer sb= new StringBuffer("SELECT st_move_storehouse_detail.MSD_PARTSID,");
		sb.append(" frt_parts.PARTS_NAME,bas_parts_unit.PUNIT_NAME, parts_change_price.PARTS_NOW_COUNT,");
		sb.append(" st_move_storehouse_detail.MSD_CNT, st_move_storehouse_detail.MSD_NOCAST,");
		sb.append(" st_move_storehouse_detail.MSD_NOCAST_AMONT, frt_parts.PARTS_LIBRARY,");
		sb.append(" st_move_storehouse_detail.MSD_REMARK,st_move_storehouse_detail.MSD_INDEX");
		sb.append(" FROM st_move_storehouse_detail,frt_parts,bas_parts_unit,");
		sb.append(" parts_change_price WHERE st_move_storehouse_detail.MSD_PARTSID=frt_parts.PARTS_ID");
		sb.append(" AND frt_parts.PUNIT_NAME=bas_parts_unit.PUNIT_ID AND parts_change_price.PARTS_ID=st_move_storehouse_detail.MSD_PARTSID");
		sb.append(" AND parts_change_price.STORE_ID IN(SELECT st_move_storehouse_main.MSDM_OUT_STOREHOUSE_ID");
		sb.append(" FROM st_move_storehouse_detail,st_move_storehouse_main WHERE st_move_storehouse_detail.MSDM_ID=st_move_storehouse_main.MSDM_ID");
		if(msdmId!=null&&!msdmId.trim().equals("")){
			sb.append(" AND st_move_storehouse_detail.MSDM_ID='"+msdmId.trim()+"'");
	        sb.append(" GROUP BY st_move_storehouse_main.MSDM_ID) AND st_move_storehouse_detail.MSDM_ID='"+msdmId.trim()+"'");
		}
		List<Object[]> resultList=createSQLQuery(sb.toString());
		if(resultList!=null&&resultList.size()>0){
			Object[] obj=null;
			for (int i = 0; i < resultList.size(); i++) {
				StMoveStorehouseVo smsvo=new StMoveStorehouseVo();
				obj=(Object[]) resultList.get(i);
				if(obj[0]!=null&&!obj[0].equals(""))
					smsvo.setPartsId(obj[0].toString());
				if(obj[1]!=null&&!obj[1].equals(""))
					smsvo.setPartsName(obj[1].toString());
				if(obj[2]!=null&&!obj[2].equals(""))
					smsvo.setPunitName(obj[2].toString());
				if(obj[3]!=null&&!obj[3].equals(""))
					smsvo.setPartsNowCount(obj[3].toString());
				if(obj[4]!=null&&!obj[4].equals(""))
					smsvo.setMsdCnt(obj[4].toString());
				if(obj[5]!=null&&!obj[5].equals(""))
					smsvo.setNotaxCast(obj[5].toString());
				if(obj[6]!=null&&!obj[6].equals(""))
					smsvo.setMsdNocastAmont(obj[6].toString());
				if(obj[7]!=null&&!obj[7].equals(""))
					smsvo.setPartsLibrary(obj[7].toString());
				if(obj[8]!=null&&!obj[8].equals(""))
					smsvo.setMsdRemark(obj[8].toString());
				if(obj[9]!=null&&!obj[9].equals(""))
					smsvo.setMsdIndex(obj[9].toString());
				list.add(smsvo);
			}
		}
		return list;
	}
	
}