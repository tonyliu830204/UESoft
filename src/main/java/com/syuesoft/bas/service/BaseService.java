package com.syuesoft.bas.service;

import java.util.List;
import com.syuesoft.base.vo.BasStorehouseVo;
import com.syuesoft.frt.vo.FrtPartsVo;
import com.syuesoft.model.BasChilddictionary;
import com.syuesoft.st.vo.StSellOrderVo;
import com.syuesoft.util.ComboBoxJson;
import com.syuesoft.util.ComboBoxJson2;

public interface BaseService
{

    public List<ComboBoxJson> findAllStorehouse(BasStorehouseVo basStorehouseVo) throws Exception; // 查询所有仓库

    public List<ComboBoxJson> findAllStuff(String q,int enterprise_id) throws Exception; // 查询员工

    public List<ComboBoxJson> findAllCarLicense(String q,Integer id) throws Exception; // 查找车辆牌照

    public List<ComboBoxJson2> findAllCustom() throws Exception; // 查询客户名称

    public List<ComboBoxJson> findAllCustom(String q,Integer id) throws Exception; // 查询客户名称

    public List<ComboBoxJson> findAllCarBrand(String q,Integer id) throws Exception; // 查询车辆品牌

    public List<ComboBoxJson2> findAllCarType() throws Exception; // 查询车辆型号

    public List<ComboBoxJson> findAllCarType(FrtPartsVo fpVo) throws Exception; // 查询车辆型号

    public List<ComboBoxJson> findAllCarType(Short cbrdId, String q,int enterprise_id)
            throws Exception; // 查询车辆型号

    public List<ComboBoxJson> findAllCarStyle(String q) throws Exception; // 查询车辆款式

    public List<ComboBoxJson> findAllCarStyle(Short ctypeId, String q,int enterprise_id)
            throws Exception; // 查询车辆款式

    public List<ComboBoxJson> findAllCarColor(String q,int enterprise_id) throws Exception; // 查询车身颜色

    public List<ComboBoxJson> findAllCarSellers(String q,int enterprise_id) throws Exception; // 查询车辆经销商

    public List<ComboBoxJson> findAllPartsBrand(String q,int enterpeise_id) throws Exception; // 查询配件品牌

    public List<ComboBoxJson> findAllPartsType(Short pbrdId, String q,Integer id)
            throws Exception; // 查询配件型号

    public List<ComboBoxJson> findAllPartsPosition(String q,int enterprise_id) throws Exception; // 查询配件部位

    public List<ComboBoxJson> findAllPartsState(int enterprise_id) throws Exception; // 查询所有配件产地

    public List<ComboBoxJson> findAllPartsState(FrtPartsVo fpVo) throws Exception; // 查询所有配件产地

    public List<ComboBoxJson> findAllPartsUnit(int interprise_id) throws Exception;// 查询配件单位

    public List<ComboBoxJson> findAllReptype() throws Exception; // 查询维修类别

    public List<ComboBoxJson> findAllRepairWork() throws Exception; // 查询维修工位

    public List<ComboBoxJson> findAllRepairGroup() throws Exception; // 查询维修班组

    public List<ComboBoxJson> findAllClaimManufacturers() throws Exception; // 查询索赔厂商

    public List<ComboBoxJson> findAllCustomNature(String q,int enterprise_id) throws Exception; // 查询客户性质

    public List<ComboBoxJson> findAllCustomGroup(String q,int enterprise_id) throws Exception; // 查询客户分类

    public List<ComboBoxJson> findAllCustomType(String q,int enterprise_id) throws Exception; // 查询客户类型

    public List<ComboBoxJson> findAllCustomArea(String q,int enterprise_id) throws Exception; // 查询客户区域

    public List<ComboBoxJson> findAllRelationCampany(String q, Boolean flag,int enterprise_id)
            throws Exception; // 查询相关单位(包含供应商和保险公司)

    public List<ComboBoxJson> findAllowCarType(String q) throws Exception; // 查询准驾车型

    public List<ComboBoxJson> findAllRelcampAttr(Integer nowEnterpriseId) throws Exception; // 查询(保险汽厂)属性

    public List<ComboBoxJson> findAllRepairType(String q,final int enterpriseId) throws Exception; // 查询收费性质

    public List<ComboBoxJson> findAllClaimsType(String q,final int enterpriseId) throws Exception; // 查询索赔分类

    public List<ComboBoxJson> baseListData(String key) throws Exception; // 从码表中去下拉框数据

    public List<BasChilddictionary> getBasChilddictionary(String key)
            throws Exception; // 从码表中去下拉框数据

    BasChilddictionary findChildById(String id) throws Exception;

    /**销售单模块    销售分类信息加载*/
	public List<ComboBoxJson> loadBasPartsSell(StSellOrderVo stSellOrderVo)throws Exception;
    
    /** 数据加载 */
    public List<ComboBoxJson> loadDataByChildId(final String parentId)
            throws Exception;

    List<ComboBoxJson> findAllPartsName(String q)throws Exception;

    List<ComboBoxJson> findAllBasRelationCampany(FrtPartsVo fpVo) throws Exception;
    
    /**入库单号   预加载*/
	public List<ComboBoxJson> loadStorageId(String q,int enterprise_id)throws Exception;
	
	/**获取系统默认参数*/
	public String findDefaultProperties(String type,String name)throws Exception;
	public String findDefaultProperties(String type,String name,int enterpriseId)throws Exception;

	/**获取工时单价*/
	public String getSinglePrice(String lince,int enterpriseId)throws Exception;
	public BasChilddictionary findChildBykey(String key);

	/**
	 * 查找指定企业的子店信息
	 * @param q 指定的父级企业序号
	 * @param q 下拉框查询条件
	 * @param flag true表示id和text皆名称，false标示id为编号，text为名称，默认为false 
	 * */
	public List<ComboBoxJson> findAllDistributeEnterprise(String enterpriseId,String q,Boolean flag)throws Exception;
	///////////////////////////////////////////以下为系统各种类型员工的查询/////////////////////////////////////////////////
	/**
	 * [1管理人员]管理人员查询
	 * @param q 下拉框查询条件
	 * @param flag true表示id和text皆姓名，false标示id为编号，text为姓名，默认为false
	 * @param enterpriseId 登陆用户所属公司id
	 * @exception Exception 总异常
	 * @return List<ComboBoxJson> 返回包含ComboBoxJson对象的集合
	 * */
	public List<ComboBoxJson> findManager(String q,Boolean flag,int enterpriseId)throws Exception;
	/**
	 * [2维修业务]业务员查询(维修客户档案)
	 * @param q 下拉框查询条件
	 * @param flag true表示id和text皆姓名，false标示id为编号，text为姓名，默认为false
	 * @param enterpriseId 登陆用户所属公司id
	 * @exception Exception 总异常
	 * @return List<ComboBoxJson> 返回包含ComboBoxJson对象的集合
	 * */
	public List<ComboBoxJson> findServiceOperationPerson(String q,Boolean flag,int enterpriseId)throws Exception;
	/**
	 * [3维修接待]接待员查询
	 * @param q 下拉框查询条件
	 * @param flag true表示id和text皆姓名，false标示id为编号，text为姓名，默认为false
	 * @param enterpriseId 登陆用户所属公司id
	 * @exception Exception 总异常
	 * @return List<ComboBoxJson> 返回包含ComboBoxJson对象的集合
	 * */
	public List<ComboBoxJson> findServiceReceivePerson(String q,Boolean flag,int enterpriseId)throws Exception;
	/**
	 * [4配件仓库]退料员查询
	 * @param q 下拉框查询条件
	 * @param flag true表示id和text皆姓名，false标示id为编号，text为姓名，默认为false
	 * @param enterpriseId 登陆用户所属公司id
	 * @exception Exception 总异常
	 * @return List<ComboBoxJson> 返回包含ComboBoxJson对象的集合
	 * */
	public List<ComboBoxJson> findPartsStorehousePerson(String q,Boolean flag,int enterpriseId)throws Exception;
	/**
	 * [5财务人员]收款人，开票人查询
	 * @param q 下拉框查询条件
	 * @param flag true表示id和text皆姓名，false标示id为编号，text为姓名，默认为false
	 * @param enterpriseId 登陆用户所属公司id
	 * @exception Exception 总异常
	 * @return List<ComboBoxJson> 返回包含ComboBoxJson对象的集合
	 * */
	public List<ComboBoxJson> findFinanceicalPerson(String q,Boolean flag,int enterpriseId)throws Exception;
	/**
	 * [6售后维修]维修员查询,维修技师查询
	 * @param q 下拉框查询条件
	 * @param flag true表示id和text皆姓名，false标示id为编号，text为姓名，默认为false
	 * @param enterpriseId 登陆用户所属公司id
	 * @exception Exception 总异常
	 * @return List<ComboBoxJson> 返回包含ComboBoxJson对象的集合
	 * */
	public List<ComboBoxJson> findAfterServiceMaintainPerson(String q,Boolean flag,int enterpriseId)throws Exception;
	/**
	 * [7配件采购]采购员查询
	 * @param q 下拉框查询条件
	 * @param flag true表示id和text皆姓名，false标示id为编号，text为姓名，默认为false
	 * @param enterpriseId 登陆用户所属公司id
	 * @exception Exception 总异常
	 * @return List<ComboBoxJson> 返回包含ComboBoxJson对象的集合
	 * */
	public List<ComboBoxJson> findPartsStockPerson(String q,Boolean flag,int enterpriseId)throws Exception;
	/**
	 * [8维修领料]领料员查询
	 * @param q 下拉框查询条件
	 * @param flag true表示id和text皆姓名，false标示id为编号，text为姓名，默认为false
	 * @param enterpriseId 登陆用户所属公司id
	 * @exception Exception 总异常
	 * @return List<ComboBoxJson> 返回包含ComboBoxJson对象的集合
	 * */
	public List<ComboBoxJson> findServiceMaterielPerson(String q,Boolean flag,int enterpriseId)throws Exception;
	/**
	 * [9保险人员]查询
	 * @param q 下拉框查询条件
	 * @param flag true表示id和text皆姓名，false标示id为编号，text为姓名，默认为false
	 * @param enterpriseId 登陆用户所属公司id
	 * @exception Exception 总异常
	 * @return List<ComboBoxJson> 返回包含ComboBoxJson对象的集合
	 * */
	public List<ComboBoxJson> findInsurePerson(String q,Boolean flag,int enterpriseId)throws Exception;
	/**
	 * [10车间检验]查询
	 * @param q 下拉框查询条件
	 * @param flag true表示id和text皆姓名，false标示id为编号，text为姓名，默认为false
	 * @param enterpriseId 登陆用户所属公司id
	 * @exception Exception 总异常
	 * @return List<ComboBoxJson> 返回包含ComboBoxJson对象的集合
	 * */
	public List<ComboBoxJson> findPlantProvePerson(String q,Boolean flag,int enterpriseId)throws Exception;
	/**
	 * [11维修三包]三包员查询
	 * @param q 下拉框查询条件
	 * @param flag true表示id和text皆姓名，false标示id为编号，text为姓名，默认为false
	 * @param enterpriseId 登陆用户所属公司id
	 * @exception Exception 总异常
	 * @return List<ComboBoxJson> 返回包含ComboBoxJson对象的集合
	 * */
	public List<ComboBoxJson> findServiceEgisAvailPerson(String q,Boolean flag,int enterpriseId)throws Exception;
	/**
	 * [12销售业务]业务员查询
	 * @param q 下拉框查询条件
	 * @param flag true表示id和text皆姓名，false标示id为编号，text为姓名，默认为false
	 * @param enterpriseId 登陆用户所属公司id
	 * @exception Exception 总异常
	 * @return List<ComboBoxJson> 返回包含ComboBoxJson对象的集合
	 * */
	public List<ComboBoxJson> findSellOperationPerson(String q,Boolean flag,int enterpriseId)throws Exception;
	/**
	 * [13整车仓库]查询
	 * @param q 下拉框查询条件
	 * @param flag true表示id和text皆姓名，false标示id为编号，text为姓名，默认为false
	 * @param enterpriseId 登陆用户所属公司id
	 * @exception Exception 总异常
	 * @return List<ComboBoxJson> 返回包含ComboBoxJson对象的集合
	 * */
	public List<ComboBoxJson> findCarStorehousePerson(String q,Boolean flag,int enterpriseId)throws Exception;
	/**
	 * [14整车采购]查询
	 * @param q 下拉框查询条件
	 * @param flag true表示id和text皆姓名，false标示id为编号，text为姓名，默认为false
	 * @param enterpriseId 登陆用户所属公司id
	 * @exception Exception 总异常
	 * @return List<ComboBoxJson> 返回包含ComboBoxJson对象的集合
	 * */
	public List<ComboBoxJson> findCarStockPerson(String q,Boolean flag,int enterpriseId)throws Exception;
	/**
	 * [15PDI检测]检验员查询
	 * @param q 下拉框查询条件
	 * @param flag true表示id和text皆姓名，false标示id为编号，text为姓名，默认为false
	 * @param enterpriseId 登陆用户所属公司id
	 * @exception Exception 总异常
	 * @return List<ComboBoxJson> 返回包含ComboBoxJson对象的集合
	 * */
	public List<ComboBoxJson> findPDIProvePerson(String q,Boolean flag,int enterpriseId)throws Exception;
	/**
	 * [16整车装潢]查询
	 * @param q 下拉框查询条件
	 * @param flag true表示id和text皆姓名，false标示id为编号，text为姓名，默认为false
	 * @param enterpriseId 登陆用户所属公司id
	 * @exception Exception 总异常
	 * @return List<ComboBoxJson> 返回包含ComboBoxJson对象的集合
	 * */
	public List<ComboBoxJson> findCarUpholsterPerson(String q,Boolean flag,int enterpriseId)throws Exception;
	/**
	 * [17客服人员]回访员查询
	 * @param q 下拉框查询条件
	 * @param flag true表示id和text皆姓名，false标示id为编号，text为姓名，默认为false
	 * @param enterpriseId 登陆用户所属公司id
	 * @exception Exception 总异常
	 * @return List<ComboBoxJson> 返回包含ComboBoxJson对象的集合
	 * */
	public List<ComboBoxJson> findVisitingPerson(String q,Boolean flag,int enterpriseId)throws Exception;
	/**
	 * 指定员工类别查询
	 * @param q 下拉框查询条件
	 * @param flag true表示id和text皆姓名，false标示id为编号，text为姓名，默认为false
	 * @param basJobProperty 工作属性序号(数值<=0查询所有类别员工)
	 * @param enterpriseId 登陆用户所属公司id
	 * @exception Exception 总异常
	 * @return List<ComboBoxJson> 返回包含ComboBoxJson对象的集合
	 * */
	public List<ComboBoxJson> findBasStuffClass(String q,Boolean flag,int basJobProperty,int enterpriseId)throws Exception;
	
	public void saveOrUpdate(Object obj) throws Exception;
	
	public Object get(String hql) throws Exception;
}