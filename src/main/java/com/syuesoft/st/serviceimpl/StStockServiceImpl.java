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
* @Title: StStockServiceImpl.java 
* @Package com.syuesoft.st.serviceimpl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author HeXin  
* @date 2013-9-27 下午04:59:46 
* @version V1.0   
*/
package com.syuesoft.st.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.st.dao.StStockDAO;
import com.syuesoft.st.service.StStockService;
import com.syuesoft.st.vo.StockPartsVo;
import com.syuesoft.util.FormatDate;
import com.syuesoft.util.Json;

/** 
 * @ClassName: StStockServiceImpl 
 * @Description: TODO(进销存) 
 * @author HeXin 
 * @date 2013-9-27 下午04:59:46 
 * @version 1.0 
 */
@Service("stStockServiceImpl")
public class StStockServiceImpl implements StStockService
{
    @Autowired
    private StStockDAO stStockDAO;
    
    public Json loadStockSummary(StockPartsVo stockPartsVo) throws Exception
    {
        Json json = new Json();
        List<StockPartsVo> list=new ArrayList<StockPartsVo>();
        int total = 0;
        //企业编号,默认为1
        String company = stockPartsVo.getEnterpriseId().toString();
        if(stockPartsVo.getStomDateStart()==null || "".equals(stockPartsVo.getStomDateStart())){
            stockPartsVo.setStomDateStart(FormatDate.getFistDay());
        }
        if(stockPartsVo.getStomDateEnd()==null || "".equals(stockPartsVo.getStomDateEnd())){ 
            stockPartsVo.setStomDateEnd(FormatDate.getEndDay());
        }
        if(stockPartsVo.getStoreId()==null){ 
            stockPartsVo.setStoreId("");
        }
        if(stockPartsVo.getStockType()==null){ 
            stockPartsVo.setStockType("2");
        }else{
            if(stockPartsVo.getStockType().equals(Contstants.STOCKTYPE.STOCKTYPE2)){
                stockPartsVo.setStockType("2");
            }else{
                stockPartsVo.setStockType("1");
            }
        }
        if(stockPartsVo.getTypeWay()==null){ 
            stockPartsVo.setTypeWay("1");
        }else{
            if(stockPartsVo.getTypeWay().equals(Contstants.STOCKCLASSIFY.STOCKCLASSIFY2)){
                stockPartsVo.setTypeWay("2");
            }else{
                stockPartsVo.setTypeWay("1");
            }
        }
        if(stockPartsVo.getStoreModelId()==null){ 
            stockPartsVo.setStoreModelId("");
        }
        List<Object> params = new ArrayList<Object>();
        params.add(0,stockPartsVo.getStomDateStart());
        params.add(1,stockPartsVo.getStomDateEnd());
        params.add(2,stockPartsVo.getStockType());
        params.add(3,stockPartsVo.getStoreId());
        params.add(4,stockPartsVo.getTypeWay());
        params.add(5,stockPartsVo.getStoreModelId());
        params.add(6,company);
        params.add(7,stockPartsVo.getPage());
        params.add(8,stockPartsVo.getRows());
        StringBuffer procedureName=new StringBuffer(" { CALL Invoicing2_stock_(?,?,?,?,?,?,?,?,?) } ");
        List<Object[]> resultList = stStockDAO.getProcedureQuery(procedureName.toString(), params);
        if(resultList!=null&&!"".equals(resultList)){
            Object[] obj=null;
            for (int i = 0; i < resultList.size(); i++) {
                StockPartsVo stockPartsVo_=new StockPartsVo();
                obj=resultList.get(i);
                stockPartsVo_.setStomDateStart(stockPartsVo.getStomDateStart());
                stockPartsVo_.setStomDateEnd(stockPartsVo.getStomDateEnd());
                if(obj[0]!=null&&!"".equals(obj[0])){
                    stockPartsVo_.setPartsId("");
                }
                if(obj[1]!=null&&!"".equals(obj[1])){
                    stockPartsVo_.setPartsName("");   
                }
                if(obj[2]!=null&&!"".equals(obj[2])){
                    stockPartsVo_.setStoreId(obj[2].toString());
                }
                if(obj[3]!=null&&!"".equals(obj[3])){
                    stockPartsVo_.setStoreName(obj[3].toString());
                }
                if(obj[4]!=null&&!"".equals(obj[4])){
                    stockPartsVo_.setPartsLibrary(obj[4].toString());
                }
                if(obj[5]!=null&&!"".equals(obj[5])){
                    stockPartsVo_.setStypeId(obj[5].toString());
                }
                if(obj[6]!=null&&!"".equals(obj[6])){
                    stockPartsVo_.setPriorCount(obj[6].toString());
                }
                if(obj[7]!=null&&!"".equals(obj[7])){
                    stockPartsVo_.setPriorSellAmount(obj[7].toString());
                }
                if(obj[8]!=null&&!"".equals(obj[8])){
                    stockPartsVo_.setPriorCostAmount(obj[8].toString());
                }
                if(obj[9]!=null&&!"".equals(obj[9])){
                    stockPartsVo_.setCurrentInCount(obj[9].toString());
                }
                if(obj[10]!=null&&!"".equals(obj[10])){
                    stockPartsVo_.setCurrentInSellAmount(obj[10].toString());
                }
                if(obj[11]!=null&&!"".equals(obj[11])){
                    stockPartsVo_.setCurrentInCostAmount(obj[11].toString());
                }
                if(obj[12]!=null&&!"".equals(obj[12])){
                    stockPartsVo_.setCurrentOutCount(obj[12].toString());
                }
                if(obj[13]!=null&&!"".equals(obj[13])){
                    stockPartsVo_.setCurrentOutSellAmount(obj[13].toString());
                }
                if(obj[14]!=null&&!"".equals(obj[14])){
                    stockPartsVo_.setCurrentOutCostAmount(obj[14].toString());
                }
                if(obj[15]!=null&&!"".equals(obj[15])){
                    stockPartsVo_.setCurrentOutCount1(obj[15].toString());
                }
                if(obj[16]!=null&&!"".equals(obj[16])){
                    stockPartsVo_.setCurrentOutSellAmount1(obj[16].toString());
                }
                if(obj[17]!=null&&!"".equals(obj[17])){
                    stockPartsVo_.setCurrentOutCostAmount1(obj[17].toString());
                }
                if(obj[18]!=null&&!"".equals(obj[18])){
                    stockPartsVo_.setCurrentOutCount2(obj[18].toString());
                }
                if(obj[19]!=null&&!"".equals(obj[19])){
                    stockPartsVo_.setCurrentOutSellAmount2(obj[19].toString());
                }
                if(obj[20]!=null&&!"".equals(obj[20])){
                    stockPartsVo_.setCurrentOutCostAmount2(obj[20].toString());
                }
                if(obj[21]!=null&&!"".equals(obj[21])){
                    stockPartsVo_.setCurrentCancelCount(obj[21].toString());
                }
                if(obj[22]!=null&&!"".equals(obj[22])){
                    stockPartsVo_.setCurrentCancelSellAmount(obj[22].toString());
                }
                if(obj[23]!=null&&!"".equals(obj[23])){
                    stockPartsVo_.setCurrentCancelCostAmount(obj[23].toString());
                }
                if(obj[24]!=null&&!"".equals(obj[24])){
                    stockPartsVo_.setCurrentMaterialCount(obj[24].toString());
                }
                if(obj[25]!=null&&!"".equals(obj[25])){
                    stockPartsVo_.setCurrentMaterialSellAmount(obj[25].toString());
                }
                if(obj[26]!=null&&!"".equals(obj[26])){
                    stockPartsVo_.setCurrentMaterialCostAmount(obj[26].toString());
                }
                if(obj[27]!=null&&!"".equals(obj[27])){
                    stockPartsVo_.setCurrentMaterialCount1(obj[27].toString());
                }
                if(obj[28]!=null&&!"".equals(obj[28])){
                    stockPartsVo_.setCurrentMaterialSellAmount1(obj[28].toString());
                }
                if(obj[29]!=null&&!"".equals(obj[29])){
                    stockPartsVo_.setCurrentMaterialCostAmount1(obj[29].toString());
                }
                if(obj[30]!=null&&!"".equals(obj[30])){
                    stockPartsVo_.setCurrentCheckCount(obj[30].toString());
                }
                if(obj[31]!=null&&!"".equals(obj[31])){
                    stockPartsVo_.setCurrentCheckSellAmount(obj[31].toString());
                }
                if(obj[32]!=null&&!"".equals(obj[32])){
                    stockPartsVo_.setCurrentCheckCostAmount(obj[32].toString());
                }
                if(obj[33]!=null&&!"".equals(obj[33])){
                    stockPartsVo_.setSurplusCount(obj[33].toString());
                }
                if(obj[34]!=null&&!"".equals(obj[34])){
                    stockPartsVo_.setSurplusSellAmount(obj[34].toString());
                }
                if(obj[35]!=null&&!"".equals(obj[35])){
                    stockPartsVo_.setSurplusCostAmount(obj[35].toString());
                }
                if(obj[36]!=null&&!"".equals(obj[36])){
                    total = Integer.parseInt(obj[36].toString());
                }
                stockPartsVo_.setIconCls("");
                stockPartsVo_.setState("closed");
                stockPartsVo_.setChildMenu(null);
                list.add(stockPartsVo_);
            }
        }
        json.setRows(list);
        json.setTotal(total);
        return json;
    }
     
   
    public List<StockPartsVo> loadStock(StockPartsVo stockPartsVo) throws Exception
    {
        //企业编号,默认为1
        String company = stockPartsVo.getEnterpriseId().toString();
        if(stockPartsVo.getStockType().equals(Contstants.STOCKTYPE.STOCKTYPE2)){
            stockPartsVo.setStockType("2");
        }else{
            stockPartsVo.setStockType("1");
        }
        if(stockPartsVo.getTypeWay().equals(Contstants.STOCKCLASSIFY.STOCKCLASSIFY2)){
            stockPartsVo.setTypeWay("2");
        }else{
            stockPartsVo.setTypeWay("1");
        }
        List<StockPartsVo> list=new ArrayList<StockPartsVo>();
        List<Object> params = new ArrayList<Object>();
        params.add(0,stockPartsVo.getStomDateStart());
        params.add(1,stockPartsVo.getStomDateEnd());
        params.add(2,stockPartsVo.getStockType());
        params.add(3,stockPartsVo.getStoreId());
        params.add(4,stockPartsVo.getTypeWay());
        params.add(5,stockPartsVo.getStoreModelId());
        params.add(6,company);
        params.add(7,stockPartsVo.getPage());
        params.add(8,stockPartsVo.getRows());
        StringBuffer procedureName=new StringBuffer(" { CALL Invoicing2_stock_(?,?,?,?,?,?,?,?,?) } ");
        List<Object[]> resultList = stStockDAO.getProcedureQuery(procedureName.toString(), params);
        
        if(resultList!=null&&!"".equals(resultList)){
            Object[] obj=null;
            for (int i = 0; i < resultList.size(); i++) {
                StockPartsVo stockPartsVo_=new StockPartsVo();
                obj=resultList.get(i);
                stockPartsVo_.setStomDateStart(stockPartsVo.getStomDateStart());
                stockPartsVo_.setStomDateEnd(stockPartsVo.getStomDateEnd());
                if(obj[0]!=null&&!"".equals(obj[0])){
                    stockPartsVo_.setPartsId(obj[0].toString());
                }
                if(obj[1]!=null&&!"".equals(obj[1])){
                    stockPartsVo_.setPartsName(obj[1].toString());   
                }
                if(obj[2]!=null&&!"".equals(obj[2])){
                    stockPartsVo_.setStoreId(obj[2].toString());
                }
                if(obj[3]!=null&&!"".equals(obj[3])){
                    stockPartsVo_.setStoreName(obj[3].toString());
                }
                if(obj[4]!=null&&!"".equals(obj[4])){
                    stockPartsVo_.setPartsLibrary(obj[4].toString());
                }
                if(obj[5]!=null&&!"".equals(obj[5])){
                    stockPartsVo_.setStypeId(obj[5].toString());
                }
                if(obj[6]!=null&&!"".equals(obj[6])){
                    stockPartsVo_.setPriorCount(obj[6].toString());
                }
                if(obj[7]!=null&&!"".equals(obj[7])){
                    stockPartsVo_.setPriorSellAmount(obj[7].toString());
                }
                if(obj[8]!=null&&!"".equals(obj[8])){
                    stockPartsVo_.setPriorCostAmount(obj[8].toString());
                }
                if(obj[9]!=null&&!"".equals(obj[9])){
                    stockPartsVo_.setCurrentInCount(obj[9].toString());
                }
                if(obj[10]!=null&&!"".equals(obj[10])){
                    stockPartsVo_.setCurrentInSellAmount(obj[10].toString());
                }
                if(obj[11]!=null&&!"".equals(obj[11])){
                    stockPartsVo_.setCurrentInCostAmount(obj[11].toString());
                }
                if(obj[12]!=null&&!"".equals(obj[12])){
                    stockPartsVo_.setCurrentOutCount(obj[12].toString());
                }
                if(obj[13]!=null&&!"".equals(obj[13])){
                    stockPartsVo_.setCurrentOutSellAmount(obj[13].toString());
                }
                if(obj[14]!=null&&!"".equals(obj[14])){
                    stockPartsVo_.setCurrentOutCostAmount(obj[14].toString());
                }
                if(obj[15]!=null&&!"".equals(obj[15])){
                    stockPartsVo_.setCurrentOutCount1(obj[15].toString());
                }
                if(obj[16]!=null&&!"".equals(obj[16])){
                    stockPartsVo_.setCurrentOutSellAmount1(obj[16].toString());
                }
                if(obj[17]!=null&&!"".equals(obj[17])){
                    stockPartsVo_.setCurrentOutCostAmount1(obj[17].toString());
                }
                if(obj[18]!=null&&!"".equals(obj[18])){
                    stockPartsVo_.setCurrentOutCount2(obj[18].toString());
                }
                if(obj[19]!=null&&!"".equals(obj[19])){
                    stockPartsVo_.setCurrentOutSellAmount2(obj[19].toString());
                }
                if(obj[20]!=null&&!"".equals(obj[20])){
                    stockPartsVo_.setCurrentOutCostAmount2(obj[20].toString());
                }
                if(obj[21]!=null&&!"".equals(obj[21])){
                    stockPartsVo_.setCurrentCancelCount(obj[21].toString());
                }
                if(obj[22]!=null&&!"".equals(obj[22])){
                    stockPartsVo_.setCurrentCancelSellAmount(obj[22].toString());
                }
                if(obj[23]!=null&&!"".equals(obj[23])){
                    stockPartsVo_.setCurrentCancelCostAmount(obj[23].toString());
                }
                if(obj[24]!=null&&!"".equals(obj[24])){
                    stockPartsVo_.setCurrentMaterialCount(obj[24].toString());
                }
                if(obj[25]!=null&&!"".equals(obj[25])){
                    stockPartsVo_.setCurrentMaterialSellAmount(obj[25].toString());
                }
                if(obj[26]!=null&&!"".equals(obj[26])){
                    stockPartsVo_.setCurrentMaterialCostAmount(obj[26].toString());
                }
                if(obj[27]!=null&&!"".equals(obj[27])){
                    stockPartsVo_.setCurrentMaterialCount1(obj[27].toString());
                }
                if(obj[28]!=null&&!"".equals(obj[28])){
                    stockPartsVo_.setCurrentMaterialSellAmount1(obj[28].toString());
                }
                if(obj[29]!=null&&!"".equals(obj[29])){
                    stockPartsVo_.setCurrentMaterialCostAmount1(obj[29].toString());
                }
                if(obj[30]!=null&&!"".equals(obj[30])){
                    stockPartsVo_.setCurrentCheckCount(obj[30].toString());
                }
                if(obj[31]!=null&&!"".equals(obj[31])){
                    stockPartsVo_.setCurrentCheckSellAmount(obj[31].toString());
                }
                if(obj[32]!=null&&!"".equals(obj[32])){
                    stockPartsVo_.setCurrentCheckCostAmount(obj[32].toString());
                }
                if(obj[33]!=null&&!"".equals(obj[33])){
                    stockPartsVo_.setSurplusCount(obj[33].toString());
                }
                if(obj[34]!=null&&!"".equals(obj[34])){
                    stockPartsVo_.setSurplusSellAmount(obj[34].toString());
                }
                if(obj[35]!=null&&!"".equals(obj[35])){
                    stockPartsVo_.setSurplusCostAmount(obj[35].toString());
                }
                if(obj[36]!=null&&!"".equals(obj[36])){
                    stockPartsVo_.setPartsModelId(obj[36].toString());
                }
                if(obj[37]!=null&&!"".equals(obj[37])){
                    stockPartsVo_.setPartsModelName(obj[37].toString());
                }
                list.add(stockPartsVo_);
            }
        }
        return list;
    }
    
}
