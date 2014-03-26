package com.syuesoft.contstants;

import java.util.Date;

public class Contstants
{
	/**
	 * 员工级别
	 * */
	public interface EMPLOYEELEVEL{
		/**
		 * 父Key
		 * */
		public static final String EMPLOYEELEVELKEY="employeelevel";
	    /**
	     * 超级管理员帐号
	     */
	    public static final String ADMINISTRATOR = "administrator";

	    /**
	     * 企业管理员帐号
	     */
	    public static final String ADMIN = "enterprise-admin";
	    /**
	     * 普通员工
	     */
	    public static final String GENERAL = "general-staff";
	}

    /**
     * 当前登录用户
     */
    public static final String CUSTOMER = "CUSTOMER";

    /**
     * 当前登录用户权限
     */
    public static final String CURRUSERAUTH = "CURRUSERAUTH";

    public static final String CHECKED = "CHECKED";
    /**
     * 当前系统所属公司序号
     * */
    public static final String ENTERPRISEID="enterpriseId";
    /**
     * 当前系统所属公司名称
     * */
    public static final String ENTERPRISENAME="enterpriseName";
    /**
     * 所属系统
     * 
     */
    public interface SYSTEMTYPE
    {
        /**
         * 父key
         */
        public static final String SYSTEM = "system_";
        
        /**
         * 所有系统
         */
        public static final String ALL = "all";
        
        /**
         * 维修系统
         */
        public static final String WEIXIU = "weixiu";

        /**
         * 销售系统
         */
        public static final String XIAOSHOU = "xiaoshou";
    }
    
    /**
     * 菜单是否有子菜单
     * 
     */
    public interface CHILDMENU
    {
        /**
         * 无子菜单
         */
        public static final String CHILDMENU_NO = "0";

        /**
         * 有子菜单
         */
        public static final String CHILDMENU_YES = "1";
    }

    /**
     * 用户访问状态
     * 
     */
    public interface ACCESSSTATE
    {
        /**
         * 用户访问状态父key
         */
        public static final String PARENTKEY = "accessStat";

        /**
         * 启用
         */
        public static final String ENABLE = "qiy";

        /**
         * 禁用
         */
        public static final String DESABLE = "jiny";
    }

    /**
     * 是否为系统用户
     * 
     */
    public interface SYSTEMUSER
    {
        /**
         * 父KEY
         */
        public static final String PSTFYES = "stfYes";

        /**
         * 是
         */
        public static final String STFYES = "stfYes";

        /**
         * 否
         */
        public static final String STFNO = "stfNo";

    }

    /**
     * 性别
     * 
     */
    public interface SEXTYPE
    {
        /**
         * 父KEY
         */
        public static final String SEX = "sex";

        /**
         * 男
         */
        public static final String MAN = "M";

        /**
         * 女
         */
        public static final String WOMAN = "F";

    }

    /**
     * 文化程度
     * 
     */
    public interface WHCD
    {
        /**
         * 父KEY
         */
        public static final String WHCD = "Whcd";

        /**
         * 小学
         */
        public static final String PRIMARY = "primary";

        /**
         * 初中
         */
        public static final String MIDDLE = "middle";

        /**
         * 高中
         */
        public static final String HIGN = "high";

        /**
         * 技工
         */
        public static final String TECHEICIAN = "technician";

        /**
         * 中技
         */
        public static final String POLYTECHNI = "polytechni";

        /**
         * 中专
         */
        public static final String SPECIAL = "special";

        /**
         * 大专
         */
        public static final String COLLEGE = "college";

        /**
         * 本科
         */
        public static final String UNDERRGRADU = "undergradu";

        /**
         * 硕士
         */
        public static final String MASTER = "Master";

        /**
         * 博士
         */
        public static final String DOCTOR = "doctor";

        /**
         * 博士后
         */
        public static final String POSTDOCTOR = "postdoctor";
    }

    /**
     * 血型
     * 
     */
    public interface XX
    {
        /**
         * 父KEY
         */
        public static final String XX = "Xx";

        /**
         * A型
         */
        public static final String A = "A";

        /**
         * B型
         */
        public static final String B = "B";

        /**
         * O型
         */
        public static final String O = "O";

        /**
         * AB型
         */
        public static final String AB = "AB";
    }

    /**
     * 婚姻状况
     * 
     */
    public interface HYZK
    {
        /**
         * 父KEY
         */
        public static final String HYZK = "Hyzk";

        /**
         * 未婚
         */
        public static final String SINGLE = "single";

        /**
         * 已婚
         */
        public static final String MARRIED = "Married";
    }
    
    /**
     * 库存类别
     * 
     */
    public interface STOCKTYPE
    {
        /**
         * 父KEY
         */
        public static final String STOCKTYPE = "stockType";

        /**
         * 财务成本
         */
        public static final String STOCKTYPE1 = "stockType1";

        /**
         * 仓库成本
         */
        public static final String STOCKTYPE2 = "stockType2";
    }
    
    /**
     * 进销存分类方式
     * 
     */
    public interface STOCKCLASSIFY
    {
        /**
         * 父KEY
         */
        public static final String STOCKCLASSIFY = "stockClassify";

        /**
         * 品牌分类
         */
        public static final String STOCKCLASSIFY1 = "stockClassify1";

        /**
         * 配件仓库
         */
        public static final String STOCKCLASSIFY2 = "stockClassify2";
    }
    
    /**
     * 系统使用情况
     * 
     */
    public interface ZXQKF
    {
        /**
         * 父KEY
         */
        public static final String ZXQKF = "ZxqkF";

        /**
         * 使用中
         */
        public static final String INSERVICE = "inservice";

        /**
         * 已注销
         */
        public static final String LOGGEDOFF = "loggedoff";

        /**
         * 全部
         */
        public static final String ALL = "all";

    }

    /**
     * 删除标示，预约/保险估价标示
     * */
    public interface DELETE_TAG
    {
        /**
         * 删除，保险估价
         */
        public static final Short DELETEYES = 1;

        /**
         * 未删除，预约
         */
        public static final Short DELETENO = 0;
    }

    /**
     * 是否返工
     * */
    public interface WORK_TAG
    {
        /**
         * 返工
         */
        public static final Short WORKYES = 1;

        /**
         * 未返工
         */
        public static final Short WORKNO = 0;
    }

    /**
     * 转接车
     * */
    public interface NEARCAR_TAG
    {
        /**
         * 已转接车
         */
        public static final Short NEARCARYES = 1;

        /**
         * 未转接车
         */
        public static final Short NEARCARNO = 0;
    }

    /**
     * 假数据有无标示
     * */
    public interface FAKEDATA_TAG
    {
        /**
         * 有假数据
         */
        public static final Short FAKEDATAYES = 1;

        /**
         * 没有假数据
         */
        public static final Short FAKEDATANO = 0;
    }

    /**
     * 索赔，结算转收银
     * */
    public interface TOMONEY_TAG
    {
        /**
         * 父Key
         * */
        public static final String TOMONEY_TAGKEY = "zsy";
        /**
         * 已转收银
         * */
        public static final Short TOMONEYYES = 1;

        /**
         * 未转收银
         * */
        public static final Short TOMONEYNO = 0;
    }

    /**
     * 结算单分类
     * */
    public interface PRECLEARINGCLASS_TAG
    {
        /**
         * 父Key
         * */
        public static final String PRECLEARINGCLASSKEY = "balClass";

        /**
         * 维修结算单
         * */
        public static final String PRECLEARINGCLASSPRECLEARING = "carBalan";

        /**
         * 索赔结算单
         * */
        public static final String PRECLEARINGCLASSCLAIMANT = "claBalan";

        /**
         * 销售结算单
         * */
        public static final String PRECLEARINGCLASSSELL = "payBalan";

        /**
         * 所有记录
         * */
        public static final String PRECLEARINGCLASSALL = "balanAll";

        /**
         * 默认值
         * */
        public static final String PRECLEARINGCLASSINIT = PRECLEARINGCLASSALL;//carBalan 维修结算
    }

    /**
     * 工单状态
     * */
    public interface DOCUMENT_TAG
    {
        /**
         * 父Key
         * */
        public static final String DOCUMENTKEY = "rcptStatus";

        /**
         * 初始状态（预约申请/保险估价单转接车单）
         * 未派工状态
         * */
        public static final String DOCUMENTState0 = "0";

        /**
         * 待派工
         */
        public static final String DOCUMENTState1 = "1";

        /**
         * 派工
         */
        public static final String DOCUMENTState2 = "2";

        /**
         * 开工
         */
        public static final String DOCUMENTState3 = "3";

        /**
         * 维修中
         */
        public static final String DOCUMENTState4 = "4";

        /**
         * 等待零件
         */
        public static final String DOCUMENTState5 = "5";

        /**
         * 等待答复
         */
        public static final String DOCUMENTState6 = "6";

        /**
         * 质量检验
         */
        public static final String DOCUMENTState7 = "7";

        /**
         * 完工
         */
        public static final String DOCUMENTState8 = "8";

        /**
         * 洗车
         */
        public static final String DOCUMENTState9 = "9";

        /**
         * 等待交车
         */
        public static final String DOCUMENTState10 = "10";
        /**
         * 已转结算
         */
        public static final String DOCUMENTState11 = "11";

        /**
         * 已结算
         */
        public static final String DOCUMENTState12 = "12";

        /**
         * 收银
         */
        public static final String DOCUMENTState13 = "13";
        
        /**
         * 已转车间
         */
        public static final String DOCUMENTState14 = "14";

    }

    /**
     * 是否索赔,全额索赔
     * */
    public interface CLAIM_TAG
    {
        /**
         * 已索赔，全额索赔
         */
        public static final Short CLAIMYES = 1;

        /**
         * 未索赔，不全额索赔
         */
        public static final Short CLAIMNO = 0;

        /**
         * 不索赔
         */
        public static final Short NOCLAIM = -1;
    }

    /**
     * 审核状态
     * */
    public interface AUDIT_TAG
    {
        /**
         * 父Key
         * */
        public static final String AUDITKEY = "lookState";

        /**
         * 已审核
         */
        public static final String AUDITYESS = "lookStateYes";

        /**
         * 未审核
         */
        public static final String AUDITNOS = "lookStateNo";
    }
    
    /**
     * 在途状态
     * */
    public interface TRANSITTOSTATE
    {
        /**
         * 父Key
         * */
        public static final String TRANSITTOSTATEKEY = "transittostatekey";

        /**
         * 发货
         */
        public static final String DELIVERY = "delivery";

        /**
         * 在途
         */
        public static final String ONPASSAGE = "onpassage";
        
        /**
         * 到达
         */
        public static final String ARRIVE = "arrive";
    }
    

    /**
     * 服务项目
     * */
    public interface SERVICEITEM_TAG
    {
        /**
         * 父Key
         */
        public static final String SERVICEITEMKEY = "servicePro";
    }

    /**
     * 回访满意度
     * */
    public interface TALKBACKCONTENT_TAG
    {
        /**
         * 父Key
         */
        public static final String TALKBACKCONTENTKEY = "content";
    }

    /**
     * 是否来厂维修
     * */
    public interface WHETHERSERVICECOME_TAG
    {
        /**
         * 父Key
         */
        public static final String WHETHERSERVICECOMEKEY = "IsCome";
    }

    /**
     * 预约/保险估价确认状态
     * */
    public interface BESPEAKVALIDATESTATE_TAG
    {
        /**
         * 父Key
         */
        public static final String BESPEAKVALIDATESTATEKEY = "qrState";
    }

    /**
     * 转车间状态
     * */
    public interface TOWORKSHOP_TAG
    {
        /**
         * 父Key
         * */
        public static final String TOWORKSHOPKEY = "toWorkshop";

        /**
         * 已转车间
         */
        public static final String TOWORKSHOPYES = "toWorkshopYes";

        /**
         * 未转车间
         */
        public static final String TOWORKSHOPNO = "toWorkshopNo";
    }

    /**
     * 保养区分状态
     * */
    public interface MAINTAIN_TAG
    {
        /**
         * 父Key
         * */
        public static final String MAINTAINKEY = "maintFlg";

        /**
         * 是
         */
        public static final Short MAINTAINYES = 1;

        /**
         * 否
         */
        public static final Short MAINTAINNO = 0;
    }

    /**
     * 燃油情况
     * */
    public interface FUELTHING_TAG
    {
        /**
         * 父Key
         * */
        public static final String FUELTHINGKEY = "fuelThing";
    }

    /**
     * 旧件处理
     * */
    public interface OLDPARTS_TAG
    {
        /**
         * 父Key
         * */
        public static final String OLDPARTSKEY = "oldParts";
    }

    /**
     * 票据类型
     * */
    public interface RECEIPT_TAG
    {
        /**
         * 父Key
         * */
        public static final String RECEIPTKEY = "billType";
        
        /**
         * 增值发票
         */
        public static final String VALUEADDEDTAX="valueaddedtax";
        
        /**
         * 普通发票
         */
        public static final String COMMONTAX="commontax";
        
        /**
         * 其他发票
         */
        public static final String OTHERTAX="othertax";

        /**
         * 转结算初始值
         * */
        public static final String RECEIPTINIT = "commontax";

        /**
         * 其他
         * */
//        public static final String RECEIPTOTHER = "3";
    }

    /**
     * 提醒分类
     * */
    public interface ADVICECLASS_TAG
    {
        /**
         * 父Key
         * */
        public static final String ADVICECLASSKEY = "servState";
    }

    /**
     * 预约状态
     * */
    public interface BESPEAKSTATE_TAG
    {
        /**
         * 父Key
         * */
        public static final String BESPEAKSTATEKEY = "yyState";

        /** 预约中 */
        public static final String BESPEAKSTATE_BESPEAKING = "bespeaking";

        /** 预约提前 */
        public static final String BESPEAKSTATE_BESPEAKBEF = "bespeakBef";

        /** 准时入厂 */
        public static final String BESPEAKSTATE_ONTIMEGO = "onTimeGo";

        /** 预约延误 */
        public static final String BESPEAKSTATE_BESPEAKOLD = "bespeakOld";

        /** 预约取消 */
        public static final String BESPEAKSTATE_BESPEAKOFF = "bespeakOff";

    }

    /**
     * 有无贵重物品
     * */
    public interface VALUABLESRES_TAG
    {
        /**
         * 父Key
         * */
        public static final String VALUABLESRESKEY = "valuables";

        /** 无 */
        public static final String VALUABLESRESNO = "valno";
    }

    /**
     * 预约分类
     * */
    public interface BESPEAKCLASS_TAG
    {
        /**
         * 父Key
         * */
        public static final String BESPEAKCLASSKEY = "yyType";

        /**
         * 主动预约
         */
        public static final String BESPEAKCLASSINITIATIVE = "1";

        /**
         * 被动预约
         */
        public static final String BESPEAKCLASSPASSIVITY = "0";
    }

    /**
     * 处理进度
     * */
    public interface PROCESSTATE_TAG
    {
        /**
         * 父Key
         * */
        public static final String PROCESSTATEKEY = "useState";

        /**
         * 已处理
         */
        public static final Short PROCESSTATEYES = 1;

        /**
         * 未处理
         */
        public static final Short PROCESSTATENO = 0;
    }

    /**
     * 结算状态
     * */
    public interface JIESUANSTATE
    {
        /**
         * 父Key
         * */
        public static final String JIESUAN = "jiesuan";

        /**
         * 未结算
         */
        public static final String NOJIESUAN = "1";

        /**
         * 预结算
         */
        public static final String YUJIESUAN = "2";

        /**
         * 正式结算
         */
        public static final String ZHJIESUAN = "3";

        /**
         * 全部记录
         */
        public static final String ALLLOG = "4";

        /**
         * 已结算
         */
        public static final String FAIJIESUAN = "5";
    }

//    /**
//     * 索赔性质默认值（转前台接车）
//     * */
//    public static final Short DEFAULTCLAIM = 2;
//
//    /**
//     * 收费性质默认值（转前台接车）
//     * */
//    public static final Short DEFAULTBALANCE = 1;

    /**
     * 公司信息设定
     * 
     */
    public interface COPANY_SET
    {
        /**
         * 公司信息
         */
        public static final String COPANYINFO = "company";

        /**
         * 短信参数
         */
        public static final String CMSPARAMETER = "cms";

        /**
         * 车辆控制
         */
        public static final String CARCONTROL = "carCon";

        /**
         * 客户控制
         */
        public static final String CUSTOMCONTROL = "cucon";

        /**
         * 接车控制
         */
        public static final String TOCARCONTROL = "jcarcon";
    }

    /**
     * 系统参数设定二
     * 
     */
    public interface SYSTEMPARAMETER_SET
    {
        /**
         * 系统安全
         */
        public static final String SYSTEMSAFE = "syssafe";

        /**
         * 会员参数
         */
        public static final String VIPPARAMETER = "vipparamet";

        /**
         * 前台参数
         */
        public static final String STAGEPARAMETER = "stgparamet";

        /**
         * 结算参数
         */
        public static final String BALANCEPARAMETER = "jsparamet";

        /**
         * 索赔参数
         */
        public static final String CLAIMPARAMETER = "spparamet";

        /**
         * 预约/保险估价
         */
        public static final String BESPOKEPRICE = "evaluateprict";

        /**
         * 车间参数
         */
        public static final String WORKSHOPPARAMETER = "deptparamet";

        /**
         * 客户档案
         */
        public static final String CUSTOMINFO = "custominfo";

        /**
         * 车辆档案
         */
        public static final String CARINFO = "carinfo";
    }

    /**
     * 参数设置
     * 
     */
    public interface PARAMETER_SET
    {
        /**
         * 1综合参数设置
         */
        public static final String COLLIGATES = "colligateS";

        /**
         * 2配件库存查询参数设置
         */
        public static final String PARTSTOCESEARCH = "partstockS";

        /**
         * 4入库、出库设置
         */
        public static final String INOUTDEPOT = "inoutdepot";

        /**
         * 5客户档案及车辆档案参数设置
         */
        public static final String ARCHIVESSE = "archivesSe";

        /**
         * 6索赔管理设置
         */
        public static final String INDEMNITYS = "indemnityS";

        /**
         * 7现场管理参数设置
         */
        public static final String SCENESET = "sceneSet";

        /**
         * 8回访管理参数设置
         */
        public static final String VISITSET = "visitSet";

        /**
         * 9密码安全策略
         */
        public static final String PASSWORDSE = "passwordSe";

        /**
         * 10交车结算参数设置
         */
        public static final String JOINCARACC = "joincaracc";

        /**
         * 11前台接车参数设置
         */
        public static final String STGCARPARA = "stgcarpara";

        /**
         * 12单据编号格式参数设置
         */
        public static final String NUMBERFRME = "numberfrme";

        /**
         * 13预约/保险估价单参数设置
         */
        public static final String EVALUATESE = "evaluateSe";
        
        /**
         * 14其他参数设置
         */
        public static final String OTHERPARAMETER = "otherparameter";
        
        /**
         * 15财务管理设置
         */
		public static final String FINANCEMANAGE = "financeManage";
    }

    /**
     * 综合参数设置类型
     */
    public interface COLLIGATES
    {
        /**
         * 系统管理员密码
         */
        public static final String COLPASSWORD = "COLPASSWORD";

        /**
         * 用友接口
         */
        public static final String INTERFACE = "INTERFACE";

        /**
         * 出库是否显示库存为零配件
         */
        public static final String ZEROPARTSHOW = "ZEROPARTSHOW";

        /**
         * 数据备份路径
         */
        public static final String DBBACKUPPATH = "DBBACKUPPATH";

        /**
         * 辅料费率
         */
        public static final String ACCESSORIESRATE = "ACCESSORIESRATE";

        /**
         * 员工业绩开始统计日期
         */
        public static final String COUNTEMPDATE = "COUNTEMPDATE";

        /**
         * 默认资料显示天数
         */
        public static final String DEFAULTSHOWDAY = "DEFAULTSHOWDAY";

        /**
         * 辅料费方式
         */
        public static final String ACCESSORIESWAY = "ACCESSORIESWAY";

        /**
         * 月营业统计方法
         */
        public static final String COUNTBUSINESS = "COUNTBUSINESS";

        /**
         * 车辆牌照格式
         */
        public static final String VEHICLELICENSE = "VEHICLELICENSE";

        /**
         * 工时单价
         */
        public static final String TASKTIMEPRICE = "TASKTIMEPRICE";

        /**
         * 按公式自动计算辅料费
         */
        public static final String RECKONFORMULA = "RECKONFORMULA";

        /**
         * 启用行管工时标准
         */
        public static final String TASKTIMESTANDARD = "TASKTIMESTANDARD";

        /**
         * 基础资料批量增加
         */
        public static final String ADDBATCH = "ADDBATCH";

        /**
         * 库存可为负数
         */
        public static final String STOCKMINUSNUM = "STOCKMINUSNUM";
    }

    /**
     * 配件库存查询参数设置类型
     */
    public interface PARTSTOCESEARCH
    {
        /**
         * 低下限报警色
         */
        public static final String FLOORALARMCOLOR = "FLOORALARMCOLOR";

        /**
         * 超上限报警色
         */
        public static final String UPPERALARMCOLOR = "UPPERALARMCOLOR";

        /**
         * 库存量零报警色
         */
        public static final String ZEROALARMCOLOR = "ZEROALARMCOLOR";

        /**
         * 显示年份\引擎\备注
         */
        public static final String SHOWMER = "SHOWMER";

        /**
         * 显示为零配件
         */
        public static final String SHOWZEROPART = "SHOWZEROPART";

        /**
         * 账面库存
         */
        public static final String BILLMONEYSTOCK = "BILLMONEYSTOCK";
    }

    /**
     * 入库、出库设置类型
     */
    public interface INOUTDEPOT
    {
        /**
         * 维修价格保留小数位数
         */
        public static final String SERVICEDECIMAL = "SERVICEDECIMAL";

        /**
         * 销售单汇总金额保留小数
         */
        public static final String SELLDECIMAL = "SELLDECIMAL";

        /**
         * 销售价格成本加价系数
         */
        public static final String SELLCOSTRATE = "SELLCOSTRATE";

        /**
         * 维修默认加价率
         */
        public static final String SERVICEUPRATE = "SERVICEUPRATE";

        /**
         * 销售默认加价率
         */
        public static final String SELLUPRATE = "SELLUPRATE";
        
        /**
         * 默认发票类型
         */
        public static final String INDEFRECEIPTTYPE = "INDEFRECEIPTTYPE";
        

        /**
         * 配件代码匹配顺序
         */
        public static final String PARTCODEORDER = "PARTCODEORDER";

        /**
         * 销售单价格基础
         */
        public static final String SELLBASEPRICE = "SELLBASEPRICE";
        
        /**
         * 成本保留小数位数
         */
        public static final String COSTDECIMAL = "COSTDECIMAL";
        
        /**
         * 销价为零配件销售、出库、库存按加价率计算
         */
        public static final String ZEROSELL = "ZEROSELL";

        /**
         * 出库单打印时间
         */
        public static final String PRINTDATE = "PRINTDATE";

        /**
         * 出库指定供应商
         */
        public static final String OUTDEPOTSUPP = "OUTDEPOTSUPP";

        /**
         * 答应验收
         */
        public static final String PRINTCHECK = "PRINTCHECK";

        /**
         * 配件出库可选择型号
         */
        public static final String OUTPARTMODEL = "OUTPARTMODEL";

        /**
         * 入库、出库日期可变更
         */
        public static final String INOUTDATECHANGE = "INOUTDATECHANGE";

        /**
         * 入库、出库经办人
         */
        public static final String INOUTOPERATOR = "INOUTOPERATOR";
        
        /**
         * 销售加装配件库
         */
        public static final String SELLADDPORT = "SELLADDPORT";
    }

    /**
     * 客户档案及车辆档案参数设置
     * 
     */
    public interface ARCHIVESSE
    {
        /**
         * 默认客户地区属性
         */
        public static final String DEFAULTAREA = "DEFAULTAREA";

        /**
         * 车辆保养提醒天数
         */
        public static final String MAINTAINDAY = "MAINTAINDAY";

        /**
         * 车辆流失定界为
         */
        public static final String MINAMBITDAY = "MINAMBITDAY";

        /**
         * 未进店维修
         */
        public static final String MAXAMBITDAY = "MAXAMBITDAY";

        /**
         * 默认车辆分类
         */
        public static final String VEHICLECLASSIFY = "VEHICLECLASSIFY";

        /**
         * 车辆首保提醒日期
         */
        public static final String FIRSTWARNDAY = "FIRSTWARNDAY";

        /**
         * 默认客户欠款金额
         */
        public static final String LIMITDEBT = "LIMITDEBT";

        /**
         * 车辆保险提醒天数
         */
        public static final String INSURANCEWARNDAY = "INSURANCEWARNDAY";

        /**
         * 首保公里数
         */
        public static final String FIRSTKILOMETRE = "FIRSTKILOMETRE";

        /**
         * 允许客户欠款次数
         */
        public static final String LIMITDEBTNUMBER = "LIMITDEBTNUMBER";

        /**
         * 保养短信发出后几天电话跟踪
         */
        public static final String LIMITDAYTAIL = "LIMITDAYTAIL";
    }

    /**
     * 索赔管理设置
     */
    public interface INDEMNITYS
    {
        /**
         * 索赔工时单价
         */
        public static final String CLAIMTIMEPRICE = "CLAIMTIMEPRICE";

        /**
         * 索赔管理费率
         */
        public static final String CLAIMMANGERRATE = "CLAIMMANGERRATE";

        /**
         * 工时提取金额
         */
        public static final String TIMEEXTRACTMONEY = "TIMEEXTRACTMONEY";

        /**
         * 索赔厂商代码
         */
        public static final String CLAIMMANUCODE = "CLAIMMANUCODE";

        /**
         * 索赔厂商名称
         */
        public static final String CLAIMMANUNAME = "CLAIMMANUNAME";

        /**
         * 图片对齐方式
         */
        public static final String PICTUREALIGN = "PICTUREALIGN";

        /**
         * 启用索赔
         */
        public static final String CLAIMENABLE = "CLAIMENABLE";

        /**
         * 启用厂家索赔定额
         */
        public static final String CLAIMENABLELIMIT = "CLAIMENABLELIMIT";

        /**
         * 索赔单不打印索赔材料及项目
         */
        public static final String NOPCLAIMPRODATUM = "NOPCLAIMPRODATUM";
    }

    /**
     * 现场管理参数设置
     */
    public interface SCENESET
    {
        /**
         * 车间修改维修项目
         */
        public static final String UPDATEREPAIRPRO = "UPDATEREPAIRPRO";

        /**
         * 现场管理自动显示在修车辆
         */
        public static final String SHOWREPAIRVEHIVLE = "SHOWREPAIRVEHIVLE";

        /**
         * 车间完工不能出料
         */
        public static final String FINISHUNABLESTUFF = "FINISHUNABLESTUFF";

        /**
         * 维修完工、仓库出料完成控制结算方式
         */
        public static final String CONTROLBALANCEWAY = "CONTROLBALANCEWAY";
    }

    /**
     *回访管理参数设置
     * 
     */
    public interface VISITSET
    {
        /**
         * 回访对应的结算性质
         */
        public static final String VISITBALANCENATURE = "VISITBALANCENATURE";
    }

    /**
     * 密码安全策略
     * 
     */
    public interface PASSWORDSE
    {
        /**
         * 密码最小长度
         */
        public static final String PASSWORDLENGTH = "PASSWORDLENGTH";

        /**
         * 密码有效天数
         */
        public static final String PASSWORDLIMITDAY = "PASSWORDLIMITDAY";

        /**
         * 密码输入错误限制次数
         */
        public static final String PASSWORDLIMITNUM = "PASSWORDLIMITNUM";

        /**
         * 密码复杂程度
         */
        public static final String PWCOMPLEXITY = "PWCOMPLEXITY";
    }

    /**
     * 交车结算参数设置
     * 
     */
    public interface JOINCARACC
    {
        /**
         * 打印结算单编号格式
         */
        public static final String NUMBERFORMAT = "NUMBERFORMAT";

        /**
         * 打印结算发票编号格式
         */
        public static final String BILLNUMBERFORMAT = "BILLNUMBERFORMAT";

        /**
         * 配件合并代码
         */
        public static final String PARTMERGECODE = "PARTMERGECODE";

        /**
         * 下次保养里程=本次保养里程+保养公里数
         */
        public static final String MAINTAINCOURSE = "MAINTAINCOURSE";

        /**
         * 最大保养间隔天数
         */
        public static final String MAINTAINSPACEDAY = "MAINTAINSPACEDAY";

        /**
         * 配件合并名称
         */
        public static final String PARTMERGENAME = "PARTMERGENAME";

        /**
         * 预结算后可否发料
         */
        public static final String STOREISSUE = "STOREISSUE";

        /**
         * 默认显示结算单类型
         */
        public static final String SHOWBALANCETYPE = "SHOWBALANCETYPE";

        /**
         * 预算单对应的付款方式
         */
        public static final String PAYMENTTYPE = "PAYMENTTYPE";

        /**
         * 配件毛利计算方式
         */
        public static final String PARTPROFITWAY = "PARTPROFITWAY";

        /**
         * 结算单打印配件编码
         */
        public static final String PRINTPARTCODE = "PRINTPARTCODE";

        /**
         * 标识符出料方式
         */
        public static final String IDENTIFIERWAY = "IDENTIFIERWAY";
        
        /**
         * 结算提醒
         */
        public static final String BALANCEWARN = "BALANCEWARN";
        
        /**
         * 结算单不打印索赔材料及项目
         */
        public static final String NOPRINTSTFFPRO = "NOPRINTSTFFPRO";
        
        /**
         * 结算不现实配件成本
         */
        public static final String NOSHOWPARTCOST = "NOSHOWPARTCOST";
        
        /**
         * 结算单不打印付款方式
         */
        public static final String NOSPINTPAYWAY = "NOSPINTPAYWAY";
        
        /**
         * 结算单打印竣工单     
         */
        public static final String PRINTFINISHBILL = "PRINTFINISHBILL";
        
        /**
         * 结算单打印出门证     
         */
        public static final String PRINTGOOUTOF = "PRINTGOOUTOF";
        
        /**
         * 结算外包启用        
         */
        public static final String BALANCEEPIBOLYEN = "BALANCEEPIBOLYEN";
        
        /**
         * 提醒维修应收款      
         */
        public static final String WARNRECEIVABLE = "WARNRECEIVABLE";
        
        /**
         * 结算单打印洗车单           
         */
        public static final String PRINTCLEARBILL = "PRINTCLEARBILL";
        
        /**
         * 结算配件材料免费                
         */
        public static final String BALANCEPARTFREE = "BALANCEPARTFREE";
        
        /**
         * 不能打印结算收款单                
         */
        public static final String NOPRINTMAKECOLLBILL = "NOPRINTMAKECOLLBILL";
        
        /**
         * 结算单不打印工时单价                
         */
        public static final String BALANCEBILLNOPRINT = "BALANCEBILLNOPRINT";
        
        /**
         * 结算单计算利润含税                
         */
        public static final String BALANCECOUNTDUTY = "BALANCECOUNTDUTY";
        
        /**
         * 结算单打印结算说明                    
         */
        public static final String BALANCEPRINTREMARK = "BALANCEPRINTREMARK";
        
        /**
         * 结算收款四舍五入保留小数位                  
         */
        public static final String BALMAKEUPODOWNNUM = "BALMAKEUPODOWNNUM";
        
        /**
         * 结算收款四舍五入保留小数位                  
         */
        public static final String NUMBERBIT = "NUMBERBIT";
        
        /**
         * 显示结算材料变化                       
         */
        public static final String SHOWBALANCECHANGE = "SHOWBALANCECHANGE";
        
        /**
         * 结算时，收款金额自动进位                       
         */
        public static final String RECEIVABLECARRYBIT = "RECEIVABLECARRYBIT";
        
    }
    
    /**
     * 前台接车参数设置
     * */
    public interface STGCARPARA
    {
        /** 
         * 派工单格式编号
         * */
        public static String WORKORDERFORMAT = "WORKORDERFORMAT";
        
        /** 
         * 保留空白行数
         * */
        public static String WHITESPACENUMBER = "WHITESPACENUMBER";
        
        /** 
         * 接车单默认完工工时(分钟)
         * */
        public static String FAINSHTIMES = "FAINSHTIMES";
        
        /** 
         * 派工单分单方式
         * */
        public static String SEPARATEBILLTYPE = "SEPARATEBILLTYPE";
        
        /** 
         * 默认维修类别
         * */
        public static String DEFAULTSERTYPE = "DEFAULTSERTYPE";
        
        /** 
         * 工单综合查询
         * */
        public static String WORKBILLSEARCH = "WORKBILLSEARCH";
        
        /** 
         * 接车单打印工时选择
         * */
        public static String CARBILLWORKT = "CARBILLWORKT";
        
        /** 
         * 接车单打印时，打印车间派工单
         * */
        public static String PRINGDEPARTWORKBILL = "PRINGDEPARTWORKBILL";
        
        /** 
         * 接车单打印时，打印车间检验单
         * */
        public static String PRINTDEPARTINSPE = "PRINTDEPARTINSPE";
        
        /** 
         * 接车单打印时，打印出门证
         * */
        public static String PRINTGOOUT = "PRINTGOOUT";
        
        /** 
         * 前台自动显示在线修理车辆
         * */
        public static String AUOTSHOWSERCAR = "AUOTSHOWSERCAR";
        
        /** 
         * 工单显示自编号
         * */
        public static String SHOWWORKBILLNUMBER = "SHOWWORKBILLNUMBER";
        
        /** 
         * 在部位中显示工单编号   
         * */
        public static String WORKBILLPORT = "WORKBILLPORT";
        
        /** 
         * 维修项目只能选择标准工时  
         * */
        public static String SERPSTANDARDTIME = "SERPSTANDARDTIME";
        
        /** 
         * 输入维修里程不能小于上次
         * */
        public static String DISTANCELOSSORI = "DISTANCELOSSORI";
        
        /** 
         * 派工单不打印预计交车时间
         * */
        public static String WORKBILLNOPROTIME = "WORKBILLNOPROTIME";
        
        /** 
         * 派工单不打印燃油及物品、故障描述
         * */
        public static String WORKBILLNOINFO = "WORKBILLNOINFO";
        
        /** 
         * 工时单价修改
         * */
        public static String WOKETIMEPRICE = "WOKETIMEPRICE";
        
        /** 
         * 工单套印     
         * */
        public static String WOKEBILLOVERPRI = "WOKEBILLOVERPRI";
        
        /** 
         * 维修项目输入部位     
         * */
        public static String SERPROINPORT = "SERPROINPORT";
        
        /** 
         * 维修材料输入部位     
         * */
        public static String SERSTUFFINPORT = "SERSTUFFINPORT";
        
        /** 
         * 前台接车显示全部时间      
         * */
        public static String SHOWALLTIMES = "SHOWALLTIMES";
        
        /** 
         * 不打印工单号      
         * */
        public static String NOPRINTWORKBILL = "NOPRINTWORKBILL";
        
        /** 
         * 前台接车取地址
         * */
        public static String CHECKADDRESS = "CHECKADDRESS";

        /**
         *  接车提醒 
         * */
        public static String FRTRECEPTIONCLEW = "FRTRECEPTIONCLEW";
        /**
         * 接车分部
         * */
        public static String DEFAULTRCPTBRANCH = "DEFAULTRCPTBRANCH";
    }

    /**
     * 单据编号格式参数设置
     * 
     */
    public interface NUMBERFRME
    {
        /**
         *  工单、结算单编码前置维修前置代码
         * */
        public static String SERVICEPRECODE = "SERVICEPRECODE";
        
        /**
         *  前置固定字符串
         * */
        public static String PREFIXCODE = "PREFIXCODE";
        
        /**
         *  单据号格式
         * */
        public static String RECEIPTFORMAT = "RECEIPTFORMAT";
        
        /**
         *  工单结算状态变更
         * */
        public static String WOCKBILLSTATE = "WOCKBILLSTATE";
    }
    
    /**
     * 单据号格式   选择
     */
    public interface RECEIPTFORMAT{
    	/**
    	 * 父节点
    	 */
    	public static String RECEIPTFORMATKEY="receiptformatkey";
    	
    	/**
    	 * 年份+月份
    	 */
    	public static String YEARADDMONTH="yearaddmonth";
    	
    	/**
    	 * 年份+月份+日期
    	 */
    	public static String YEARADDMONTHDAY="yearaddmonthday";
    }
    
    
    /**
     * 预约估价单参数设置
     * 
     */
    public interface EVALUATESE
    {
//        /**
//         * 预约估价单接车后处理方式
//         * */
//        public static String BESPEAKIFHORD = "BESPEAKIFHORD";
        
        /**
         *  预约估价单配件价格选择
         * */
        public static String BILLPRICTTYPE = "BILLPRICTTYPE";
        
//        /**
//         *  估价单不打印合计工时及价格
//         * */
//        public static String NOSTAMPWORKTPRICT = "NOSTAMPWORKTPRICT";
        
        /**
         * 转接车默认索赔性质
         * */
        public static final String CHANGECLAIMNATURE = "CHANGECLAIMNATURE";

        /**
         * 转接车默认收费性质
         * */
        public static final String CHANGECOLLECTNATURE = "CHANGECOLLECTNATURE";
       
        /**
         *预约进店时间
         **/
        public static final String RESEVATIONTIME = "RESEVATIONTIME";
        public static final String RESERVATIONTYPE = "RESERVATIONTYPE";
    }
    
    /**
     * 其他参数设置
    * @ClassName: OTHERPARAMETER 
    * @Description: TODO(这里用一句话描述这个类的作用) 
    * @author HeXin 
    * @date 2013-8-6 下午05:08:08 
    * @version 1.0
     */
    public interface OTHERPARAMETER
    {
        
        /**
         * 车间完工检查参数
         * */
        public static final String WORKSHOPVALIDATE="WORKSHOPVALIDATE";
        
        /**
         * 默认收款查询时间段(单位：天)
         * */
        public static final String BALANCETIMESECT="BALANCETIMESECT";
        
        /**
         * 默认结算查询时间段(单位：天)
         * */
        public static final String BALANACCOUNTSECT="BALANACCOUNTSECT";
        /**
         * 指定维修类别首保项
         * */
        public static final String FIRSTMAINTAIN="FIRSTMAINTAIN";
        /**
         * 默认配件品牌
         * */
    	public static final String DEFAULTPARTSBRAND="DEFAULTPARTSBRAND";
    	/**
    	 * 默认配件型号
    	 * */
    	public static final String DEFAULTPARTSTYPE="DEFAULTPARTSTYPE";
    	/**
    	 * 默认单位
    	 * */
    	public static final String DEFAULTPARTSUNIT="DEFAULTPARTSUNIT";
    }
//    /** 维修类别 ->首保 */
//    public static final Short SERVICETYPE_FIRSTMAINTAIN = 3;
    /**
     * 默认配件导入参数
     * */
    public interface DEFAULTPARTSPROPERTY{
    	/**默认品牌*/
    	public static final Short PARTSBRAND=3;
    	/**默认型号*/
    	public static final Short PARTSTYPE=301;
    	/**默认单位*/
    	public static final Short PARTSUNIT=1;
    	
    }
    
    /**
     * 付款方式
     */
    public interface GATHERINGWISE_FLG
    {
        /**
         * 父Key
         */
        public static final String GATHERINGWISEKEY = "paidway";

        /**
         * 维修预收款抵扣
         * */
        public static final String GATHERINGWISEASRECEPTION = "subscription";
        /**
         * 维修储备金抵扣
         * */
        public static final String GATHERINGWISEASOLDMONEY="oldMoney";
    }

    public interface PAIDWAY{
    	/**
         * 父Key
         */
        public static final String PAIDWAYKRY = "paidway";
        /** 现金*/
        public static final String CASH = "cash";
        /** 刷卡*/
        public static final String SWINGCASH = "swingcash";
        /** 转账*/
        public static final String TRANSFERACOUNT = "transferacount";
        /** 支票*/
        public static final String CHECK = "check";
        /** 汇款*/
        public static final String REMIT = "remit";
        /** 首保*/
        public static final String MAINTENANCE = "maintenance";
        /** 索赔*/
        public static final String CLAIMTYPE = "claimtype";
        /** 理赔*/
        public static final String PAYMENTTYPE = "paymenttype";
        /** 挂账*/
        public static final String CREDITTYPE = "credittype";
        /** 管理费*/
        public static final String MANANGEMENTCOST = "manangementcost";
        /** 预收款抵扣*/
        public static final String SUBSCRIPTION  = "subscription";
        /** 免费*/
        public static final String FREE = "free";
        /** 免单*/
        public static final String FREEORDER = "freeorder";
        /** 代金卷888*/
        public static final String voucher888   = "voucher888";
        /** 工时抵用卷*/
        public static final String TIMEOFFSET = "timeoffset";
        /** 保养抵用卷*/
        public static final String MAINTENANCEDEDUCTION = "maintenancededuction";
        /** 材料抵用卷*/
        public static final String MATERIALDEDUCTION = "materialdeduction";
        /** 高速公路*/
		public static final String VOUCHER = "voucher";
		/** 储备金抵扣*/
        public static final String OLDMONEY = "oldMoney";
        /** 代付收款*/
        public static final String PAYCASH = "payCash";
    }
    
    
    /**
     * 配件毛利润计算方法
     */
    public interface PROFITWAY
    {
        /**
         * 父Key
         */
        public static final String PROFITWAYKEY = "profitway";
    }

    /**
     * 配件毛利润计算方法
     */
    public interface IDENTIFIERWAY
    {
        /**
         * 父Key
         */
        public static final String IDENTIFIERWAYKEY = "identifierway";
    }

    /**
     * 密码强度
     * 
     * @author HeXin
     * 
     */
    public interface PWCOMPLEXITY
    {
        /**
         * 父Key
         * */
        public static final String PWCOMPLEXITYKEY = "complexity";

        /**
         * 低级别
         */
        public static final String LOWKEY = "elementary";

        /**
         * 中级别
         */
        public static final String MEDKEY = "intermediate";

        /**
         * 高级别
         */
        public static final String HIKEY = "highgrade";
    }
    
    /**
     * 财务管理
     * @author HeXin
     * 
     */
    public interface FINANCEMANAGE
    {
        /**
         * 日营业情况查询中成本查询是否含税
         * */
        public static final String WHETHERTAX = "WHETHERTAX";
    }
    
    /** 配件折扣率初始值 */
    public static final Double DISCOUNTRATE = 100d;

    /** 管理费率初始值 */
    public static final Double MANAGEMENTRATE = 0.00d;

    // /**VIN号长度*/
    // public static final Short CARVINLENGTH=17;
    // /**发动机号长度*/
    // public static final Short ENGINEIDLENGTH=20;
    // /**档案号长度*/
    // public static final Short ARCHIVERIDLENGTH=20;
    // /**驾驶证号长度*/
    // public static final Short DRIVERIDLENGTH=20;
    // /**银行账号长度*/
    // public static final Short BANKIDLENGTH=50;
    // /**税号长度*/
    // public static final Short DUTYIDLENGTH=50;
    // /**备注长度*/
    // public static final Short REMARKLENGTH=500;
    // /**人员姓名长度*/
    // public static final Short PERSONNAMELENGTH=20;
    // /**地址长度*/
    // public static final Short ADDRESSLENGTH=100;
    // /**身份证号长度*/
    // public static final Short PERSONIDLENGTH=18;

    /** 自定义项目全局配置 */
    /** 维修工时 */
    public static final Double REPITEMTIME = 1d;

    /** 内部工时 */
    public static final Double INTERNALTIME = 1d;

    /** 工时费 */
    public static final Double REPITEMAMOUNT = 0.00d;

    /** 计划项目名称 */
    public static final String DESIGNREPITEMNAME = "新计划项目";

    /** 维修项目名称 */
    public static final String SERVICEREPITEMNAME = "新维修项目";

    /** 索赔项目名称 */
    public static final String CLAIMANSREPITEMNAME = "新索赔项目";

    /*** 单号全局配置 */
    /** 预约申请单号 */
    public static final String BESPEAKDOCUMENTID = "YY";

    /** 保险估价单号 */
    public static final String INSUREDOCUMENTID = "BX";

    /** 前台接车单号 */
    public static final String PROSCENIUMNEARID = "JC";

    /** 维修预收款单号 */
    public static final String SERVICEBEFOREGATHERING = "CBJ";

    /** 维修应收款单号 */
    public static final String SERVICESUITGATHERING = "WXYSK";

    /** 索赔应收款单号 */
    public static final String CLAIMSSUITGATHERING = "SPYSK";

    /** 维修收款单号 */
    public static final String SERVICEGATHERING = "WXSK";

    /** 索赔收款单号 */
    public static final String CLAIMSGATHERING = "SPSK";

    /** 销售收款单号 */
    public static final String SELLGATHERING = "XSSK";

    /** 维修批量收款汇总单号 */
    public static final String SERVICEBATCHGATHERINGCOLLECT = "WXPLSKHZ";

    /** 索赔批量收款汇总单号 */
    public static final String CLAIMSBATCHGATHERINGCOLLECT = "SPPLSKHZ";

    /** 销售批量收款汇总单号 */
    public static final String SELLBATCHGATHERINGCOLLECT = "XSPLSKHZ";

    /** 维修批量收款单号 */
    public static final String SERVICEBATCHGATHERING = "WXPLSK";

    /** 索赔批量收款单号 */
    public static final String CLAIMSBATCHGATHERING = "SPPLSK";

    /** 销售批量收款单号 */
    public static final String SELLBATCHGATHERING = "XSPLSK";

    /** 维修代付应收款单号 */
    public static final String SERVICESUITSUBTITUTEGATHERING = "WXDFYSK";

    /** 索赔代付应收款单号 */
    public static final String CLAIMSSUITSUBTITUTEGATHERING = "SPDFYSK";

    /** 销售代付应收款单号 */
    public static final String SELLSUITSUBTITUTEGATHERING = "XSDFYSK";

    /** 维修代付批量应收款单号 */
    public static final String SERVICESUITSUBTITUTEBATCHGATHERING = "WXDFPLYSK";

    /** 索赔代付批量应收款单号 */
    public static final String CLAIMSSUITSUBTITUTEBATCHGATHERING = "SPDFPLYSK";

    /** 销售代付批量应收款单号 */
    public static final String SELLSUITSUBTITUTEBATCHGATHERING = "XSDFPLYSK";

    /** 维修代付收款单号 */
    public static final String SERVICESUBTITUTEGATHERING = "WXDFSK";

    /** 索赔代付收款单号 */
    public static final String CLAIMSSUBTITUTEGATHERING = "SPDFSK";

    /** 销售代付收款单号 */
    public static final String SELLSUBTITUTEGATHERING = "XSDFSK";

    /** 维修批量代付收款单号 */
    public static final String SERVICEBATCHSUBTITUTEGATHERING = "WXPLDFSK";

    /** 索赔批量代付收款单号 */
    public static final String CLAIMSBATCHSUBTITUTEGATHERING = "SPPLDFSK";

    /** 销售批量代付收款单号 */
    public static final String SELLBATCHSUBTITUTEGATHERING = "XSPLDFSK";

    /** 维修批量代付收款汇总单号 */
    public static final String SERVICEBATCHSUBSTITUTEGATHERINGCOLLECT = "WXPLDFSKHZ";

    /** 索赔批量代付收款汇总单号 */
    public static final String CLAIMSBATCHSUBSTITUTEGATHERINGCOLLECT = "SPPLDFSKHZ";

    /** 销售批量代付收款汇总单号 */
    public static final String SELLBATCHSUBSTITUTEGATHERINGCOLLECT = "XSPLDFSKHZ";

    /** 维修批量代付批量收款汇总单号 */
    public static final String SERVICEBATCHSUBSTITUTEBATCHGATHERINGCOLLECT = "WXPLDFPLSKHZ";

    /** 索赔批量代付批量收款汇总单号 */
    public static final String CLAIMSBATCHSUBSTITUTEBATCHGATHERINGCOLLECT = "SPPLDFPLSKHZ";

    /** 销售批量代付批量收款汇总单号 */
    public static final String SELLBATCHSUBSTITUTEBATCHGATHERINGCOLLECT = "XSPLDFPLSKHZ";

    public static final String DEFAULTPASSWORD = null;
    /**维修配件销售单号*/
    public static final String STSELLORDER="XSD";
    /**
     * 收款相关单号
     * */
    public interface BALANCEIDTYPE_TAG
    {
        /** 维修结算单号 */
        public static final String SERVICEBALANCEID = "JS";

        /** 索赔结算单号 */
        public static final String COUNTERCLAIMBALANCEID = "SLP";

        /** 销售结算单号 */
        public static final String SELLBALANCEID = "XSJS";
    }
    /**
     * 退料相关单号
     * */
    public interface QUITPARTS_TAG{
    	/**维修退料单号*/
    	public static final String SERVICEQUEITID = "GDTL";
    	/**销售退料单号*/
    	public static final String SELLQUITID = "XSTL";
    }
    /**
     * 收款类型
     * */
    public interface BATCHBALANCETYPE_TAG
    {
        public static final String BATCHBALANCETYPE_TAG = "payment";
        /** 维修收款 */
        public static final Short SERVICEBALANCE = 1;

        /** 索赔收款 */
        public static final Short COUNTERCLAIMBALANCE = 2;

        /** 销售收款 */
        public static final Short SELLBALANCE = 3;
    }

    /**
     * 有无收清，差额收款,是否代付,是否批量收款,批量标识
     * */
    public interface OPINIONFINISHED_TAG
    {
    	/**未收款*/
    	public static final Short UNPAYMENT = -1;
        /*** 收清，差额收款,代付，批量收款,已转批量 */
        public static final Short FINISHED = 0;

        /** 未收清，不差额收款,不代付，不批量收款,未转批量 */
        public static final Short UNFINISHED = 1;
    }

    /**
     * 动力分类
     * */
    public interface CARCLASS_TAG
    {
        /** 父Key */
        public static final String CARCLASSKEY = "carClass";
    }

    /**
     * 付款情况
     * */
    public interface PAYMENTTHING_TAG
    {
        /** 父Key */
        public static final String PAYMENTTHINGKEY = "paymentThing";

        /** 已付款 */
        public static final String PAYMENTTHINGYES = "paymentOld";

        /** 未付款 */
        public static final String PAYMENTTHINGNO = "paymentBefore";
    }

    /**
     *准时进厂时间偏移量 (单位:m)
     * */
    public static final Integer ONTIMEGOFACTORYOFFSET = 10;

    /**
     * 进厂时间延迟量（超过为预约取消）(单位:h)
     * */
    public static final Integer DELAYGOFACTORYMETE = 1;
    

    /**
     * 当天截止时间 （超过为预约取消）(单位:h:m)
     * */
    public static final Date TODAYENDTIME = new Date(2013, 7, 11, 18, 30, 00);

    // 销售系统基础数据
    public interface BASE_SELL
    {
    	/**是否付清*/
    	public static String SHIFOUFUQING = "shifoufuqing"; 
    	public static String YFQ = "yfq"; 
    	public static String WFQ = "wfq"; 
    	/** * 收款类型  */
    	
    	/**成交障碍*/
    	public static String IMPEDE = "impede"; 
    	
    	
    	/**
    	 * 登记单处理状态\跟踪结果  
    	 */
    	public static String REGISTERSTATE = "registerState"; 
    	public static String RETURNRESAULT = "01"; 
    	/**
    	 * 收款类型   
    	 */
    	public static String SHOUKUANLEIXING = "shoukuanleixing"; 
    	public static String JSSK = "JSSK";   	//结算收款
    	public static String YSK = "ysk";		//预收款
    	public static String DBSK = "DBSK";		//调拨收款
    	/**对账类型*/
    	public static String ACCOUNTTYPES = "accounttypes";
    	public static String SUPPLIERTYPES = "suppliertypes";  //供应商对账
    	public static String SALETYPES = "saletypes";			//分销商对账
    	/**跟踪状态 */
    	 public static String RETURNSTATUS = "returnstatus";
    	/**是否月结*/
    	 public static String month_statement01="1";//已月结
    	 public static String  month_statement02 ="2";//反月结
    	
        /** 地区设定 */
        public static String BASE_AREA = "setArea";

        /** 客户来源 */
        public static String BASE_SOURCE = "customeSource";

        /** 行业分类 */
        public static String BASE_TRADECLASSIFY = "tradeClassify";
        /**从事职业 */
        public static String BASE_WORK = "vocationNature";

        /** 个人收入 */
        public static String BASE_INCOME = "income";

        /** 车辆用途 */
        public static String BASE_CARPURPOSE = "carPurpose";

        /** 对比车型 */
        public static String BASE_CONTRASTMODEL = "contrastModel";

        /** 其他分类 */
        public static String BASE_OTHERCLASSIFY = "otherClassify";

        /** 学历设置 */
        public static String BASE_EDUCATIONAL = "educational";
        /** 系统参数设置 */
        public static String BASE_PARAMETER = "sellParameter";

        /** 选择理由 */
        public static String BASE_CHOICECAUSE = "choiceCause";

        /** 成交状态 */
        public static String BASE_DEALSTATE = "dealState";
        public static String AS = "as";					//成交客户
        public static String SS = "ss";					//潜在客户(转入跟踪系统)
        public static String SWA = "swa";				//预定客户
        public static String DS = "ds";					//放弃客户
        public static String DE = "de";					//购他车客户
        public static String DW = "dw";					//准放弃客户

//        public static String SS2 = "26";				//潜在客户(转入跟踪系统)
//        public static String SWA3 = "27";				//预定客户
//        public static String DS4 = "28";				//放弃客户
//        public static String DE5 = "29";				//购他车客户
//        public static String DW6 = "30";				//准放弃客户
//      public static String AS1 = "25";				//成交客户
        /**放弃跟踪原因  */
        public static String ABANDON_REASON = "abandonreson";

        /** 客户性质 */
        public static String BASE_CUSTOMNATURE = "customeNature";

        /** 性别 */
        public static String BASE_SEX = "sex";

        /** 大客户 */
        public static String BASE_CUSTOMTYPE = "customType";

        

        /** 车身颜色 */
        public static String BASE_CARCOLOR = "carColor";
        /** 保险险种 父key */
        public static String INSURANCETYPE = "insuranceType";
        

        /** 内饰色 */
        public static String BASE_ORNAMENTCOLOR = "ornamentColor";

        /** 分销状态 */
        public static String BASE_DISTRIBUTESTATE = "distributeState";

        /** 供货商 */
        public static String BASE_SUPPLIER = "supplierClassify";

        /** 代交寄存车类型 */
        public static String BASE_CARTYPE = "carType";

        /** 供应商分类 */
        public static String BASE_SUPPLIERCLASS = "supplierClassify";

        /** 车辆品牌 */
        public static String BASE_CARBRAND = "carBrand";

        /** 订单状态 */
        public static String ORDER_STATE = "orderState";  //父key
        public static String YUDINGZHONG = "yudingzhong";	//预定中
        public static String YIJIAOCHE = "yijiaoche";		//已交车
        public static String YIJIEZHUAN = "yijiezhuan";		//已调配
        public static String QUXIAO = "quxiao";				//取消
        
        /**订单类型*/
        public static String ORDERTYPE = "ordertype";  //父key
        public static String COMMONORDER = "commonorder";
        public static String EMERGENTORDER = "emergentorder";
        public static String OTHERORDER = "otherorder";
        

        /** 级别 */
        public static String LEVEL = "level";

        /** 潜在客户等级 */
        public static String CUSTOMPROPERTY = "customproperty";   //父key
        public static String YIFUDINGJIN = "yifudingjin";
		public static String LIANGZHOUNEIDINGCHE = "liangzhouneidingche";
        public static String YIYUENEIDINGCHE = "yiyueneidingche";
        public static String SANYUENEIDINGCHE = "sanyueneidingche";
        public static String YITICHE = "yitiche";
        public static String YOUYIXIANG = "youyixiang";
        public static String QITA = "qita";
        /** 付款方式 */
        public static String PAYMENTWAY = "paymentWay";
        
        /** 审核状态 父key */
        public static String ADUIT = "aduit";
        /** 审核状态=已审核 */
        public static String ADUIT1 = "yishenhe";
        /** 审核状态=未审核 */
        public static String ADUIT2 = "weishenhe";

        /** 车辆性质 */
        public static String CARPROPERTY = "carproperty";

        /** 车辆排量 */
        public static String OUTPUTOLUME = "outputVolume";

        /** 气氛 */
        public static String ATMOSPHERE = "atmosphere";

        /** 跟踪方式 */
        public static String TRACINGWAY = "tracingway";

        /** 附加属性四(仓库) */
        public static String ATTACHFOUR = "attachFour";
        /** 入库单导入默认仓库 */
        public static String  pack= "01";

        /** 付款 */
        public static String PAYSTATE = "payState";

        /** 排列(入库单查询) */
        public static String SORTSTATE = "sortState";

        /** 发票类型 */
        public static String INVOICETYPE = "invoiceType";
        public static String zzfp="1";
        public static String puTong="2";
        /** 渠道 */
        public static String CHANNEL = "channel";

        /** 方式 */
        public static String VISIT = "visit";

        /** 出库分类 */
        public static String OUTSTORAGE = "outstorage";

        /** 客户类型 */
        public static String CUSTOMTYPE = "customtype";
        public static String lingshoukh = "250";     //零售客户
        public static String dakh = "251";			//大客户
        
        /**PDI检测内容*/
        public static String PDICHECK = "pdicheck";

        /** PDI检测情况 */
        public static String PDISTATUS = "pdistatus";

        /** 付讫状态 */
        public static String PAYMENTSTATE = "payment_state";
        /** 付讫状态=是 */
        public static String PAYMENTSTATE1 = "paid";
        /** 付讫状态=否 */
        public static String PAYMENTSTATE2 = "unpaid";



        /** 满意度 */
        public static String CONSULTDEGREE = "consultDegree";
        public static String henmanyi = "01";
        public static String manyi = "02";
        public static String yiban = "03";
        public static String bumanyi = "04";
        public static String henbumanyi = "05";
        public static String wu = "06";

        /** 通话情况 */
        public static String CALLSTATE = "callState";

        /** 回访进度 */
        public static String CONSULTRATE = "consultRate";

        /** 购买其他产品 */
        public static String PAYMENTSTATE267 = "267";

        /** 意向不强 */
        public static String PAYMENTSTATE268 = "268";

        /** 无购买意向 */
        public static String PAYMENTSTATE269 = "269";

        /** 其他原因 */
        public static String PAYMENTSTATE270 = "270";

        /** 评价 */
        public static String PROJECTEVALUATE = "projectEvaluate";
        public static String PROJECTEVALUATE1="01";//很好
        public static String PROJECTEVALUATE2="02";
        public static String PROJECTEVALUATE3="03";
        public static String PROJECTEVALUATE4="04";
        public static String PROJECTEVALUATE5="05";

        /** 评分 */
        public static String PROJECTSCORE = "projectScore";
       
        /** 结算分类 */
        public static String ACCOUNTTYPE = "account_type";
        /** 结算分类=保险 */
        public static String ACCOUNTTYPE1 = "01";
        /** 结算分类 =代办*/
        public static String ACCOUNTTYPE2 = "02";
        /** 结算分类 =装潢*/
        public static String ACCOUNTTYPE3 = "03";
        /** 结算分类=销售 */
        public static String ACCOUNTTYPE4 = "04";

    	/**车辆库龄日期*/
        public static String CARAGEDATE="carAgeDate";

        /** 款项类型 */
        public static String KXLX = "kxlx";
        public static String ZC = "zc";
        public static String SR = "sr";
        public static String DK = "dk";
        /** 维护项目 */
        public static String SERVICINGPRO = "servicingPro";
    	
        /** 保单分类     */
        public static String SAFETYPE="safeType";

        /** 银行分类     */
        public static String BANKTYPE="bankType";
        /**库存状态*/
        public static String INSTORETYPE ="instoreType";
        /**库存状态=入库*/
        public static String INSTORETYPE3 ="01";
        /**库存状态=调拨*/
        public static String INSTORETYPE1 ="04";
        /**库存状态=调退*/
        public static String INSTORETYPE2 ="05";
        /**装潢备注*/
        public static String  ZHREMARK="zhRemark";
        /**装潢备注=销售*/
        public static String  ZHREMARK1="01";
        /**装潢备注=赠送*/
        public static String  ZHREMARK2="02";
        /**装潢备注=其他*/
        public static String  ZHREMARK3="03";
        /**合格证状态*/
        public static String  CERTIFICATESTATE="certificateState";
        /**成交几率*/
        public static String BUYPROBABILITY="buyProbability";
        /**完工状态*/
        public static String FINISHWORK="finishwork";
        /**分销商仓库*/
        public static String DISTRIBUTORINSTORE="distributorInstore";
        //销售班组
        public static String SELLTEAM="sellTeam";
        /**车辆规格*/
        public static String CARNORMS="carNorms";
        /**工具包*/
        public static String TOOLCASE="toolCase";
        /**脚垫*/
        public static String FOOTD="footd";
    }
    /**
     * 车辆档案销售状态key
     */
    public interface SELLSTATE
    {
    	 /** 销售状态 */
        public static String BASE_SELLSTATE = "sellState";   //父key
    	 /**档案新增*/
        public static String SELLADD = "01";
        /**计划调车*/
        public static String PLANSHUNTING  = "02";
        /**在库待销*/	
        public static String  FORSALE= "05";
        /**调换出库*/
        public static String  SWAPOUT= "06";
        /**客户预定*/
        public static String RESERVE = "07";
        /**销售未出*/
        public static String NOTSOLD = "08";
        /**销售出库*/
        public static String SELLOUT = "09";
        /**售后服务*/
        public static String  AFTERSELL= "10";
        /**车辆退厂*/
        public static String CARBACK = "11";
        /**二级分销*/
        public static String DISTRi= "03";
        /**操作中*/
      //  public static String MAKING = "12";
        
    }
    /**
     * 分销状态key
     */
    public interface DISTRIBUTESTATE
    {
    	 /**本公司*/
        public static String COMPANY = "01";
    }
    /**
     * 库存状态
     */
    public interface INSTORETYPE
    {
    	 /**入库*/
        public static String INSTORE = "01";
        /**出库*/
        public static String INSTOREOUT = "02";
        /**	退厂*/
        public static String CARBACK = "03";
        /**调拨*/
        public static String ALLOCATELIN = "04";
        /**调退*/
        public static String ALLOCATELOUT = "05";
    }
    /**
     *库存类型
     */
    public interface INSTORESTYLE
    {
    	/**父key*/
        public static String PARENTINSTORE = "instoreStyle";
    	
    	/**退厂*/
        public static String BACK = "01";
        /**	出库*/
        public static String OUT = "02";
        /**	移库*/
        public static String MOVE = "03";
        /**再次入库*/
        public static String SECONDINSTORE = "04";
        /**操作中*/
       // public static String MAKING = "05";
    }
    /**
     * 付讫状态
     */
    public interface PAYMENTSTATE
    {
    	/**父key*/
    	public static String PAYMENTSTATE="paymentstate";
    	
    	/**已付款*/
        public static String PAID = "paid";
        
        /**未付款*/
        public static String UNPAID = "unpaid";
    }
    /**
     * 销售审核
     */
    public interface ADUIT
    {
        /**
         * 父Key
         * */
        public static String ADUIT = "aduit";
        
        /**
         * 销售未审核
         * */
        public static String WEISHENHE = "weishenhe";
        
        /**
         * 销售已审核
         * */
        public static String YISHENHE = "yishenhe";
    }
    
    /**
     * 完工状态
     */
    public interface FINISHWORK
    {
        /**
         * 父Key
         * */
        public static String FINISHWORK = "finishwork";
        
        /**
         * 未开工
         * */
        public static String NOKAIFINISH = "nokaifinish";
        
        /**
         * 已开工但未完工
         * */
        public static String NOFINISH = "nofinish";
        
        /**
         * 已完工
         * */
        public static String YIFINISH = "yifinish";
    }
    
    /** 销售状态  （使用上面定义的） */
  /*  public interface BASE_SELLSTATE
    {
        
        *//**
         * 档案新增
         * *//*
        public static String State01 = "01";
        
        *//**
         * 计划调车
         * *//*
        public static String State02 = "02";
        
        *//**
         * 在库待销
         * *//*
        public static String State05 = "05";
        
        *//**
         * 调换出库
         * *//*
        public static String State06 = "06";
        
        *//**
         * 客户预定
         * *//*
        public static String State07 = "07";
        
        *//**
         * 销售未出
         * *//*
        public static String State08 = "08";
        
        *//**
         * 销售出库
         * *//*
        public static String State09 = "09";
        
        *//**
         * 售后服务
         * *//*
        public static String State10 = "10";
        
        *//**
         * 车辆退厂
         * *//*
        public static String State11 = "11";
    }*/
    
    /**
     * 是否封号
     * */
    public interface CLOSELODIN
    {
        /**
         * 父Key
         * */
        public static final String CLOSELODINKEY = "closeLogin";

        /**
         * 是
         */
        public static final String CLOSELODINYES = "1";

        /**
         * 否
         */
        public static final String CLOSELODINNO = "0";
    }

    /**
     * dateGrid控件打印设置
     * 
     */
    public interface PRINTTABLEKEY
    {
        /**
         * 人事资料设置dateGrid
         */
        public static final String personnelSumTable = "personnelSumTable";
    }

    /**
     * 套打模板设置
     * 
     */
    public interface TEMPLETPRTINGKEY
    {
        /**
         * 人事资料套打模板A
         */
        public static final String personneltempletA = "inbursaryA";
    }
    /** 调拨分类 */
    public interface ALLOCATETYPE
    {

        /** 调拨分类 */
        public static String ALLOCATETYPE = "allocateType";
        /** 调拨分类=分销调拨 */
        public static final String FENXIAO = "fenXiao";
        /** 调拨分类=换车调拨 */
        public static final String HUANCHE = "huanche";
    }
    /**
     * 接车提醒参数
     * */
    public interface FRTRECEPTIONCLEW{
    	/**接车提醒参数Key*/
    	public static final String FRTRECEPTIONCLEWKEY="rcptClew";
    	/**显示未来厂天数*/
    	public static final String FRTRECEPTIONCLEW1="rcptClew1";
    	/**显示上次维修信息*/
    	public static final String FRTRECEPTIONCLE2="rcptClew2";
    	/**显示维修建议及提醒*/
    	public static final String FRTRECEPTIONCLEW3="rcptClew3";
    	/**显示欠款提醒*/
    	public static final String FRTRECEPTIONCLEW4="rcptClew4";
    	/**显示年检，年审信息*/
    	public static final String FRTRECEPTIONCLEW5="rcptClew5";
    	/**显示保险，交强险信息*/
    	public static final String FRTRECEPTIONCLEW6="rcptClew6";
    	/**显示会员到期提醒*/
    	public static final String FRTRECEPTIONCLEW7="rcptClew7";
    }
    
    /**
     * 销售转结算类型
     * @author zhangbin
     *
     */
    public interface SELLAccount
    {
        /**
         * 父key
         */
        public static String ACCOUNTTYPE = "account_type";
        /**
         * 保险
         */
        public static final String ACCOUNTTYPE1 = "01";
        /**
         * 代办
         */
        public static final String ACCOUNTTYPE2 = "02";
        
        /**
         * 装潢
         */
        public static final String ACCOUNTTYPE3 = "03";
        
        /**
         * 销售
         * */
        public static final String ACCOUNTTYPE4 = "04";
    }
	/**
	 * 销售参数设置
	 * @author zhangbin
	 */
	public interface SELLPARAMETER_SET {
		/**
		 * 1综合参数设置
		 */
		public static final String SELLPARAM= "sellparam";

		/**
		 * 2客户管理与提醒设置
		 */
		public static final String CLIENTANDREMIND = "clientandremind";
		/**
		 * 3车辆销售及出库设置
		 */
		public static final String SELLANDWAREHOUSE = "sellandwarehouse";

		/**
		 * 4短信账号设置
		 */
		public static final String NOTEIDSET = "noteidset";

		/**
		 * 5厂家返利设置
		 */
		public static final String VENDERSET = "venderset";

		/**
		 * 6编号及打印设置
		 */
		public static final String NUMANDPRINT = "numandprint";

		/**
		 * 7密码安全策略
		 */
		public static final String PAWSAFE= "PAWSAFE";

	
	}

	/**
	 * 综合参数设置类型
	 * @author zhangbin
	 * 
	 */
	public interface SELLPARAM {
		/**
		 * 系统管理员密码
		 */
		public static final String SELLPASSWORD = "SELLPASSWORD";
		/**
		 * 备份路径
		 */
		public static final String DATABACKUPPATH = "DATABACKUPPATH";
		/**
		 * 增值税率
		 */
		public static final String TAXRATE = "TAXRATE";
		/**
		 * 即时消息开启
		 */
		public static final String ISMESSAGE = "ISMESSAGE";
		/**
		 * 更改经办
		 */
		public static final String UPDATEUSERS = "UPDATEUSERS";
		/**
		 *基础资料批量增加
		 */
		public static final String BASEDATAADD = "BASEDATAADD";
		/**
		 * 自动显示单据天数
		 */
		public static final  String BILLSDAY="BILLSDAY";
		/**
		 * 终检日期自定义
		 */
		public static final  String ZJDATA="ZJDATA";
		/**
		 * 库存查询排序方式
		 */
		public static final  String STOCKORDER="STOCKORDER";
		/**
		 *车辆库龄日期 
		 */
		public static final  String CARAGE="CARAGE";
		/**
		 * 库龄报警天数
		 */
		public static final  String CARAGEDAY="CARAGEDAY";
		/**
		 * 库龄报警颜色
		 */
		public static final  String CARAGECOLOUR="CARAGECOLOUR";
		/**
		 * 不显示代办项目的成本
		 */
		public static final  String DBCOST="DBCOST";
		/**
		 *公式计算中介优惠
		 */
		public static final  String COUNTZJ="COUNTZJ";
		/**
		 * 按订单颜色调配车辆
		 */
		public static final  String MIXCAR="MIXCAR";
		
	}
	/**
	 * 2客户管理与提醒设置
	 * @author zhangbin
	 */
	public interface CLIENTANDREMIND {
		/**
		 * 默认客户属性
		 */
		public static final  String  CLIENTPROPERTY="CLIENTPROPERTY";
		/**
		 * 预定客户提醒天数
		 */
		public static final  String RESERVEDAY="RESERVEDAY";
		/**
		 *客户生日提醒天数
		 */
		public static final  String BIRTHDAYREMIND="BIRTHDAYREMIND";
		/**
		 * 保险到期提醒天数
		 */
		public static final  String INSUREREMIND="INSUREREMIND";
		/**
		 *潜在客户预约提醒天数
		 */
		public static final  String CLIENTORDERDAY="CLIENTORDERDAY";
	
		/**
		 * 潜在客户默认跟踪天数
		 */
		public static final  String  CLIENTTRACKDAY="CLIENTTRACKDAY";
		/**
		 * 客户资料必修输入客户名称和电话一
		 */
		public static final  String CLIENTTELL="CLIENTTELL";
		/**
		 * 客户跟踪管理中必修输入下次预约日期
		 */
		public static final  String  CLIENTTRACKDATA="CLIENTTRACKDATA";
		/**
		 *  客户跟踪管理中选择成交障碍
		 */
		public static final  String CLIENTTRACKMANG="CLIENTTRACKMANG";
		/**
		 * 回访修改最后回访日期
		 */
		public static final  String LASTDAY="LASTDAY";	
		/**
		 *车辆首报提醒天数 
		 */
		public static final  String FIRSTDAY="FIRSTDAY";
	}
	
	/**
	 * 3车辆销售及出库设置
	 * @author zhangbin
	 */
	public interface SELLANDWAREHOUSE{
		/**
		 *车辆销售及出库 
		 */
		public static final  String CARSELLWAREHOUSE="CARSELLWAREHOUSE";
		/**
		 *启用限价销售 
		 */
		public static final  String PIRCESELL="PIRCESELL";
		/**
		 *车辆销售需根据订单销售
		 */
		public static final  String CARSELLDD="CARSELLDD";
		/**
		 *车辆出库时转到维修系统
		 */
		public static final  String ZHUANWEIX="ZHUANWEIX";

	}
	/**
	 * 4短信账号设置
	 * @author zhangbin
	 */
	public interface NOTEIDSET{
		/**
		 *短信通道选择
		 */
		public static final  String NOTEGALLERY="NOTEGALLERY";
		/**
		 *账号
		 */
		public static final  String ACCOUNTNUMBER="ACCOUNTNUMBER";
		/**
		 *密码
		 */
		public static final  String NOTEPASSWORD="NOTEPASSWORD";
		/**
		 *测试手机号码
		 */
		public static final  String TESTTELL="TESTTELL";
		/**
		 *主(被)叫号码
		 */
		public static final  String TELLNUM="TELLNUM";
		
	}
	/**
	 * 厂家返利设置
	 * @author zhangbin
	 *
	 */
	public interface VENDERSET{
		/**
		 * 厂家返利一
		 */
		public static final  String  VENDERONE="VENDERONE";
		
		/**
		 * 厂家返利二
		 */
		public static final  String VENDERTWO="VENDERTWO";
		/**
		 * 厂家返利三
		 */
		public static final  String VENDERTHREE="VENDERTHREE";
		/**
		 * 厂家返利四
		 */
		public static final  String VENDERFOUR="VENDERFOUR";
		/**
		 * 厂家返利五
		 */
		public static final  String VENDERFIVE="VENDERFIVE";
		/**
		 * 厂家返利六
		 */
		public static final  String VENDERSIX="VENDERSIX";
		/**
		 * 厂家返利七
		 */
		public static final  String VENDERSEVEN="VENDERSEVEN";
		/**
		 * 厂家返利八
		 */
		public static final  String VENDEREIGHT="VENDEREIGHT";
	
		
	}
	
	/**
	 * 6编号及打印设置
	 * @author zhangbin
	 */
	public interface NUMANDPRINT{
		/**
		 * 预订单打印格式编号
		 */
		public static final  String PRINTNUM="PRINTNUM";
		/**
		 * 预订单号开始
		 */
		public static final  String PRINTNUMBEGIN="PRINTNUMBEGIN";
		/**
		 * 销售单打印格式编号
		 */
		public static final  String SELLPRINT="SELLPRINT";
		
	}
	/**
	 * 配件使用状态,客户使用状态
	 * */
	public interface ONOROFF{
	/**启用*/
	public static final Boolean ONOROFFYES=true;
	/**禁用*/
	public static final Boolean ONOROFFNO=false;
}
	 /**
     * 7密码安全策略
     * @author zhangbin
     */
    public interface PAWSAFE
    {
        /**
         * 密码最小长度
         */
        public static final String PAWLENGTH = "PAWLENGTH";

        /**
         * 密码有效天数
         */
        public static final String PAWLIMITDAY = "PAWLIMITDAY";

        /**
         * 密码输入错误限制次数
         */
        public static final String PAWLIMITNUM = "PAWLIMITNUM";

        /**
         * 密码复杂程度
         */
        public static final String PAWCOMPLEXITY = "PAWCOMPLEXITY";
    }
    /**
     * 索赔性质是否收款,是否转索赔标识(更改需同步基础资料)
     * */
    public interface CLAIMSTYPETOMOENY{
    	/**收款,转索赔*/
    	public static final Byte CLAIMSTYPETOMOENYYES=1;
    	/**不收款,不转索赔*/
    	public static final Byte CLAIMSTYPETOMOENYNO=0;
    }
    /**
     * 维修类别中维修或保养标识(更改需同步基础资料)
     * */
    public interface SERVICESORT{
    	/**保养*/
    	public static final Byte SERVICESORTASMAINTAIN=1;
    	/**维修*/
    	public static final Byte SERVICESORTASSERVICE=0;
    }
    
    /**
     * 
    * @ClassName: OPERATIONALCONTROL 
    * @Description: TODO(业务控制权限) 
    * @author HeXin 
    * @date 2013-8-14 下午03:25:05 
    * @version 1.0
     */
    public interface OPERATIONALCONTROL{
        /**业务控制权限**/
        public static final String OPERATIONALCONTROL = "operationalControl";
        
        /**短信发送**/
        public static final String MSN = "1";
        /**审核权限**/
        public static final String EXAMINE = "2";
        /**基础资料**/
        public static final String BASE = "3";
        /**仓库**/
        public static final String STOREHOUSE = "4";
        /**结算**/
        public static final String STAGE = "5";
        /**折扣**/
        public static final String REBATE = "6";
        /**客户关怀中心**/
        public static final String CUSTOMERCARE = "7";
    }
    
    /**短信发送**/
    public interface MSN{
        /**前台接待**/
        public static final String MSNA = "MSNA";
        /**车辆档案查询**/
        public static final String MSNB = "MSNB";
        /**客户联系人查询**/
        public static final String MSNC = "MSNC";
        /**客户档案查询**/
        public static final String MSND = "MSND";
        /**交车结算**/
        public static final String MSNE = "MSNE";
        /**短信息接受**/
        public static final String MSNF = "MSNF";
    }
    
    /**审核权限**/
    public interface EXAMINE{
        /**部门经理审批**/
        public static final String EXAMINEA = "EXAMINEA";
        /**财务审批**/
        public static final String EXAMINEB = "EXAMINEB";
        /**总经理审批**/
        public static final String EXAMINEC = "EXAMINEC";
    }

    /**基础资料**/
    public interface BASE{
        /**全部**/
        public static final String BASEA = "BASEA";
        /**业务员**/
        public static final String BASEB = "BASEB";
        /**维修员**/
        public static final String BASEC = "BASEC";
        /**采购员**/
        public static final String BASED = "BASED";
        /**可修改客户资料的成本出库属性、最高欠款金额、最高欠款次数**/
        public static final String BASEE = "BASEE";
        /**可修改客户资料的客户销价系数属性**/
        public static final String BASEF = "BASEF";
    }
    
    /**仓库**/
    public interface STOREHOUSE{
        /**预出库先开后补**/
        public static final String STOREHOUSEA = "STOREHOUSEA";
        /**出库价控制**/
        public static final String STOREHOUSEB = "STOREHOUSEB";
        /**入库单审核**/
        public static final String STOREHOUSEC = "STOREHOUSEC";
        /**预出库只开不补**/
        public static final String STOREHOUSED = "STOREHOUSED";
        /**出库单、销售单中不现实配件的成本信息**/
        public static final String STOREHOUSEE = "STOREHOUSEE";
    }
    
    /**结算**/
    public interface STAGE{
        /**结算时可修改配件索赔**/
        public static final String THESTAGEA = "THESTAGEA";
        /**正式结算修改**/
        public static final String THESTAGEB = "THESTAGEB";
        /**结算时不允许修改仓库出料**/
        public static final String THESTAGEC = "THESTAGEC";
        /**正式结算工时修改**/
        public static final String THESTAGED = "THESTAGED";
        /**结算收银**/
        public static final String THESTAGEE = "THESTAGEE";
        /**不现实正式结算**/
        public static final String THESTAGEF = "THESTAGEF";
        /**结算单预结算**/
        public static final String THESTAGEG = "THESTAGEG";
        /**最小结算权限**/
        public static final String THESTAGEH = "THESTAGEH";
        /**只允许选择已定义的维修项目**/
        public static final String THESTAGEI = "THESTAGEI";
        /**结算单正式结算**/
        public static final String THESTAGEJ = "THESTAGEJ";
        /**可以修改配件价格**/
        public static final String THESTAGEK = "THESTAGEK";
        /**不现实配件销价**/
        public static final String THESTAGEL = "THESTAGEL";
        /**正式结算后查看出料显示及维修显示**/
        public static final String THESTAGEM = "THESTAGEM";
    }
    
    /**折扣**/
    public interface REBATE{
        /**工时折扣**/
        public static final String DISCOUNTA = "DISCOUNTA";
        /**材料折扣**/
        public static final String DISCOUNTB = "DISCOUNTB";
        /**合计折扣**/
        public static final String DISCOUNTC = "DISCOUNTC";
        /**分项工时折扣**/
        public static final String DISCOUNTD = "DISCOUNTD";
        /**分项材料折扣**/
        public static final String DISCOUNTE = "DISCOUNTE";
        /**计协费修改**/
        public static final String DISCOUNTF = "DISCOUNTF";
        /**单据经办人修改**/
        public static final String DISCOUNTG = "DISCOUNTG";
        /**发票其他**/
        public static final String DISCOUNTH = "DISCOUNTH";
        /**回访不满意跟踪**/
        public static final String DISCOUNTI = "DISCOUNTI";
        /**工时分配权限**/
        public static final String DISCOUNTJ = "DISCOUNTJ";
        /**汽车厂商**/
        public static final String DISCOUNTK = "DISCOUNTK";
        /**工时材料分项折扣**/
        public static final String DISCOUNTL = "DISCOUNTL";
        /**保险公司**/
        public static final String DISCOUNTM = "DISCOUNTM";
        /**打印出门证**/
        public static final String DISCOUNTN = "DISCOUNTN";
        /**不允许查看结算单利润及工单综合查询成本**/
        public static final String DISCOUNTO = "DISCOUNTO";
    }
    
    /**客户关怀中心**/
    public interface CUSTOMERCARE{
        /**保养提醒**/
        public static final String CUSTOMCAREA = "CUSTOMCAREA";
        /**年检提醒、年审提醒**/
        public static final String CUSTOMCAREB = "CUSTOMCAREB";
        /**首保提醒**/
        public static final String CUSTOMCAREC = "CUSTOMCAREC";
        /**保险提醒、交强提醒**/
        public static final String CUSTOMCARED = "CUSTOMCARED";
        /**生日提醒**/
        public static final String CUSTOMCAREE = "CUSTOMCAREE";
        /**会员到期提醒**/
        public static final String CUSTOMCAREF = "CUSTOMCAREF";
        /**二维提醒**/
        public static final String CUSTOMCAREG = "CUSTOMCAREG";
        /**待遇属性**/
        public static final String CUSTOMCAREH = "CUSTOMCAREH";
    }
    
    /**
    * @ClassName: OUTSTOCKPRICE 
    * @Description: TODO(出库价控制) 
    * @author HeXin 
    * @date 2013-8-16 下午04:21:05 
    * @version 1.0
     */
    public interface OUTSTOCKPRICE{
        /**父key**/
        public static final String outstockprice = "outstockprice";
        
        /**不接入**/
        public static final String nojoinup = "nojoinup";
        
        /**全部接入**/
        public static final String alljoinup = "alljoinup";
        
        /**接入配件资料**/
        public static final String partsjoinup = "partsjoinup";
    }
    
    /**
    * @ClassName: HELPCOST 
    * @Description: TODO(计协费修改) 
    * @author HeXin 
    * @date 2013-8-16 下午04:25:44 
    * @version 1.0
     */
    public interface HELPCOST{
        /**父key**/
        public static final String helpCost = "helpCost";
        
        /**修改**/
        public static final String helpupdate = "helpupdate";
        
        /**查询**/
        public static final String helpselect = "helpselect";
    }
    /**
     * 接车分部
     * */
    public interface RCPTBRANCH{
    	/**
    	 * 接车分部Key
    	 * */
    	public static final String RCPTBRANCHKEY="rcptbranch";
    }
    
    /**
     * 车辆加装
     * */
    public interface JZZT{
        /**
         * 车辆加装Key
         * */
        public static final String JZZT="JZZT";
        
        /**
         * 未加装
         * */
        public static final String wjz="wjz";
        
        /**
         * 已申请加装
         * */
        public static final String sqjz="sqjz";
        
        /**
         * 已加装
         * */
        public static final String yjz="yjz";
    }
    /**
     *  预约估价单配件价格选择(同步系统参数设定)
     * */
    public interface BILLPRICTTYPE{
    	 /**维修价格*/
    	 public static final String SERVICEPRICE="servicePrice";
    	 /**索赔价格*/
    	 public static final String CLAIMSPRICE="claimsPrice";
    	 /**销售价格*/
    	 public static final String SELLPRICE="sellPrice";
    }
    
    
    /**
     * 销售价格选择
     */
    public interface SELLPRICETYPE
    {
        /**
         * 父key
         */
        public static final String SELLPRICETYPEKEY = "sellpricetype";
        
        public static final String SELLPRICE="sellprice";
        
		public static final String SERVICEPRICE="serviceprice";
        
        public static final String POINTPRICETYPE="pointpricetype";
        
        public static final String SPECIALPRICETYPE="specialpricetype";
        
    }
    
    
    /**
     * 加价率选择
     */
    public interface ADDPRICERATE
    {
        /**
         * 父key
         */
        public static final String ADDPRICERATEKEY = "addpricerate";
        
        /**剃度加价率*/
		public static final String GRADADDPRICERATE="gradaddpricerate";
        
        /**型号加价率*/
        public static final String TYPEADDPRICERATE="typeaddpricerate";
        
    }
    public interface ACCESSORIESWAY{
    	/**
    	 * 父key
    	 * */
    	public static final String ACCESSORIESWAYKEY="accessoriesway";
    	/**无*/
    	public static final String NOTHING="nothing";
    	/**工时费*/
    	public static final String TIMEAMOUNT="timeAmount";
    	/**材料费*/
    	public static final String PARTSAMOUNT="partsAmount";
    	/**总费用合计*/
    	public static final String SUMAMOUNT="sumAmount";
    }
    
    /**
     * 库存零配件显示
     */
    public interface STOCKZEROPART{
    	
    	 /**
         * 父key
         */
        public static final String STOCKZEROPARTKEY = "stockzeropart";
    	
        /**显示*/
		public static final String STOCKZEROPARTSHOW="stockZeroPartShow";
        
        /**不显示*/
        public static final String ZERONOSHOW="zeroNoShow";
    	
    }
    
    /**
     * 标准工时常量
     */
    public interface STANDERWORKHOUR{
    	/**
    	 * 标准项目工时
    	 */
    	public static final String STANDERWORKHOURKEY="1";
    	
    }
    
    /**
     * 收款分类
     */
    public interface GATHECLASSFICAT{
    	/**
    	 * 收款分类
    	 */
    	public static final String GATHECLASSFICATING="gatheclassficating";
    	/**
    	 * 配件销售
    	 */
    	public static final String PARTSSELL="partsSell";
    	/**
    	 * 配件赠送
    	 */
    	public static final String PARTSPERSENT="partspersent";
    	/**
    	 * 精品销售
    	 */
    	public static final String COMPTITIVESELL="comptitivesell";
    	/**
    	 * 精品赠送
    	 */
    	public static final String COMPETITIVEPERSENT="competitivepersent";
    	/**
    	 * 车间领用
    	 */
    	public static final String CARROOMUSE="carroomuse";
    	/**
    	 * 耗材领用
    	 */
    	public static final String DEPLETEUSE="depleteuse";
    	
    	/**
    	 * 办公领用
    	 */
    	public static final String WORKUSE="workuse";
    	
    }
    /**
     * 销售项目各种工单号
     * */
    public interface SELLDOCUMENTID{
    	
    	
    }
    
    /**
     *销售系统： 单号开头字母的配置
     * 
     */
    public interface SELL_BILLSDEPLOY{
    	/**企业信息编号*/
    	public static final String ENTERPRISE="CO";
    	 /** 调拨单单号 */
        public static final String SELLALLOCATEl = "DBDHZ";
        /** 调退单单号 */
        public static final String SELLBACK = "DTDHZ";
        /** 数据字典单号 */
        public static final String CHILDDICTIONARY = "BASE";
        /** 车辆档案：车辆编号 */
        public static final String CARINFOR = "CLDA";
        /** 车辆型号编号 */
        public static final String CARMODEL = "CLXH";
        /** 客户编号 */
        public static final String CUSTOMTNFOR = "KHBH";
        /** 客户等级编号 */
        public static final String CUSTOMLEVA = "KHDJ";
        /** 代办项目编号 */
        public static final String DBPROJECT = "DBXM";
        /** 分销商编号 */
        public static final String DISTRIBUTORINFOR = "FXS";
        /** 保险公司编号*/
        public static final String INSURERINFOR = "BXGS";
        /** 贷款银行编号*/
        public static final String PROVIDEBANK = "DKYH";
        /** 回访进度编号   */
        public static final String REPAY = "HFJD";
        /** 回访项目编号 */
        public static final String REPAYPROJECT = "HFXM";
        /**供应商编号 */
        public static final String SUPPLIERINFOR = "GYS";
        /**装潢赠送项目编号 */
        public static final String ZSPROJECT = "ZHZS";
        /**收款单单号 */
        public static final String COLLECTIONS  = "SKDH";
        /**（供应商/分销商）对账单单号  */
        public static final String SUPPLIERTRADERAMOUNT = "DZDH";
        /**入库单单号  */
        public static final String INSTOREHOUSE = "RKDH";
        /**移库单单号  */
        public static final String INSTOREMOVE = "YKDH";
        /**出库单单号  */
        public static final String INSTOREOUT = "CKDH";
        /**退厂单单号*/
        public static final String RETREAT= "TCDH";
        
        /**月度购进计划：采购编号  */
        public static final String SELLPURCHASE = "CGDH";
        /**短信编号 */
        public static final String NOTE= "DXBH";
        /** 来店\电客户登记汇总编号 */
        public static final String CUSTOMSALESMAN= "LDDJHZ";
        /** 来店\电客户登记明细编号*/
        public static final String CUSTOMSALESMANDETAIL= "LDDJMX";
        /**预订单号*/
        public static final String CARRESERVE= "CLYD";
        /**车辆加装单号*/
        public static final String CARINSTALL= "CLJZ";
        /**销售单号 */
        public static final String SELLCODE= "XSDH";
        /**加装配件出库单号*/
        public static final String CARINSTALLOUT= "JZPJCK";
        /**开票单号 */
        public static final String INVOICECODE= "KPDH";
        /**装潢单号*/
        public static final String ZHPROJECT= "ZHDH";
        /**代办单号*/
        public static final String DBDH= "DBDH";
        /**结算单号*/
        public static final String ACCOUNT= "JSDH";
        /**退车单退款单号*/
    	public static final String BACKTOCARREFUNDMENT="TCK";
    	/**退车单号*/
    	public static final String BACKTOCAR="TC";
    	/**预订单退款单号*/
    	public static final String CARRESERVEREFUNDMENT="YDK";
    	/**潜在客户跟踪明细单号*/
    	public static final String CUSTOMTRACING="GZ";
        
    }
    
    /**
     * 车辆销售流程控制码
     * */
    public interface SELLFLOWCONTROLCODE{
    	/**档案新增*/
    	public static final Double CONTROLCODE1=1d;
    	/**采购订单*/
    	public static final Double CONTROLCODE2=2d;
    	/**入库*/
    	public static final Double CONTROLCODE3=3d;
    	/**预订单*/
    	public static final Double CONTROLCODE4=4d;
    	/**调配*/
    	public static final Double CONTROLCODE5=5d;
    	/**销售*/
    	public static final Double CONTROLCODE6=6d;
    	/**结算*/
    	public static final Double CONTROLCODE7=7d;
    	/**收银*/
    	public static final Double CONTROLCODE8=8d;
    	/**出库*/
    	public static final Double CONTROLCODE9=9d;
    	/**退车*/
    	public static final Double CONTROLCODE10=10d;
    }
    /**
     * 退车分类
     * */
    public interface QUITCARTYPE{
    	/**父key*/
    	public static final String QUITCARTYPEKEY="quitCar";
    	/**车辆损坏*/
    	public static final String CARATTAINT="carAttaint";
    	/**转购其他车*/
    	public static final String TOOTHERCAR="toOtherCar";
    	/**其他*/
    	public static final String OTHERMATTER="otherMatter";
    }
    /**
     * 接收回访
     * */
    public interface RECIPTVISIT{
    	/**父key*/
    	public static final String RECIPTRETURNVISIT="reciptReturnvisit";
    	/**	接受*/
    	public static final String CARATTAINT="receive";
    	/**拒绝*/
    	public static final String TOOTHERCAR="refuse";
    } 
    /**
     * 通化情况
     * */
    public interface callCondition{
    	/**父key*/
    	public static final String 	CALLSITUATION="callSituation";
    	/**	正常*/
    	public static final String 	NORMALCALL="normalCall";
    	/**未听*/
    	public static final String 	NOTLISTEN="notListen";
    	/**关机*/
    	public static final String 	SHUTDOWN="shutDown";
    	/**	联系不上*/
    	public static final String 	NOCONNCETION="noConncetion";
    	/**		拒绝*/
    	public static final String 	REFUSECALL="refuseCall";
    	/**	客人忙*/
    	public static final String 	CUSTOMBUSY="customBusy";
    	/**	转来电提醒*/
    	public static final String 	TURNCALL="turnCall";
    	/**	空号*/
    	public static final String 	SPACENUMBER="spaceNumber";
    	/**	号码错误*/
    	public static final String 	ERRORNUMBER="errorNumber";
    	/**	不是本人接听*/
    	public static final String 	NOANSWER="noAnswer";
    }
    /**
     * 	投诉类型
     * */
    public interface complaintStyle{
    	/**父key*/
    	public static final String 	COMPLAINTTYPE="complaintType";
    	/**服务态度*/
    	public static final String 	SERVICEATITUDE="serviceAtitude";
    	/**服务质量*/
    	public static final String 	SERVICEQUALITY="serviceQuality";
    	/**操作规范*/
    	public static final String 	OPERATIONSTANDARD="operationStandard";
    	/**配件质量*/
    	public static final String 	PARTSQUALITY="partsQuality";
    	/**	维修质量*/
    	public static final String 	REPAIRQUALITY="repairQuality";
    	/**	交车时间*/
    	public static final String 	DELIVERYTIME="deliveryTime";
    	/**	三包索赔*/
    	public static final String 	THREEPACKS="threePacks";
    	/**	环境设施*/
    	public static final String 	ENVIRONMENT="environment";
    	/**	价格问题*/
    	public static final String 	PRICEPROBLEM="priceProblem";
    	/**其他*/
    	public static final String 	OTHERCOMPLAIN="otherComplain";
    }
    /**
     * 	投诉程度
     * */
    public interface compalinDegree{
    	/**父key*/
    	public static final String 	COMPALINDEGREE="compalinDegree";
    	/**强*/
    	public static final String 	DEGREE1="degree1";
    	/**中*/
    	public static final String 	DEGREE2="degree2";
    	/**弱*/
    	public static final String 	DEGREE3="degree3";
    }
    /**
     * 	评分
     * */
    public interface score{
    	/**父key*/
    	public static final String 	SCORE="score";
    	/**1*/
    	public static final String 	ONE="one";
    	/**2*/
    	public static final String 	TWO="two";
    	/**3*/
    	public static final String 	THREE="three";
    	/**4*/
    	public static final String 	FOUR="four";
    	/**5*/
    	public static final String 	FIVE="five";
    	/**6*/
    	public static final String 	SIX="six";
    	/**7*/
    	public static final String 	SEVEN="seven";
    	/**8*/
    	public static final String 	EIGHT="eight";
    	/**9*/
    	public static final String 	NINE="nine";
    	/**10*/
    	public static final String 	TEN="ten";
    }
    /**
     * 	评价
     * */
    public interface EVALUATE{
    	/**父key*/
    	public static final String 	EVALUATE="evaluate";
    	/**很好*/
    	public static final String 	EVALUATE1="evaluate1";
    	/**好*/
    	public static final String 	EVALUATE2="evaluate2";
    	/**一般*/
    	public static final String 	EVALUATE3="evaluate3";
    	/**不好*/
    	public static final String 	EVALUATE4="evaluate4";
    	/**很不好*/
    	public static final String 	EVALUATE5="evaluate5";
    	
    }
    /**
     * 	满意程度
     * */
    public interface SATISFACTION{
    	/**父key*/
    	public static final String 	SATISFACTIONDEGREE="satisfactionDegree";
    	/**很满意*/
    	public static final String 	VERYSATISFACTORY="verySatisfactory";
    	/**	满意*/
    	public static final String 	SATISFACTORY="satisfactory";
    	/**一般*/
    	public static final String 	COMMONLY="commonly";
    	/**不满意*/
    	public static final String 	NOSATISFACTORY="nosatisfactory";
    	/**很不满意*/
    	public static final String 	FARSATISFACTORY="farSatisfactory";
    	/**无*/
    	public static final String 	NO="no";
    	
    	
    }

    /**
     * 车辆附件类型
     * @author zhangbin
     *
     */
    public interface ATTACHMENTTYPES{
    	/**父key*/
    	public static final String 	ATTACHMENTTYPE="attachmentType";
    	/**录入*/
    	public static final String 	LURU="luRu";
    	/**领取*/
    	public static final String 	LINGQU="lingQu";
    	/**借出*/
    	public static final String 	JIECHU="jieChu";
    	/**归还*/
    	public static final String 	GUIHUAN="guiHuan";
    }

    /**
     * 	回访类型
     * */
    public interface VISITGROPNAME{
    	/**父key*/
    	public static final String 	VISITTYPENAME="visitTypeName";
    	/**保养提醒*/
    	public static final String 	MAINTENREMINDER="maintenReminder";
    	/**	年检年审提醒*/
    	public static final String 	ANNUALREMAINDER="annualRemainder";
    	/**首保提醒*/
    	public static final String 	FIRSTMAINREMAINDER="firstMainRemainder";
    	/**保险,交强险提醒*/
    	public static final String 	INNSUREREMINDER="innsureReminder";
    	/**	生日提醒*/
    	public static final String 	BIRTHREMINDER="birthReminder";
    	/**会员到期提醒*/
    	public static final String 	VIPREMINDER="vipReminder";
    	
    }
    /**
     * 	提醒结果
     * */
    public interface REMAINDERESULT{
    	/**父key*/
    	public static final String 	TXRESAULT="txResault";
    	/**无*/
    	public static final String 	NORESULT="noResult";
    	/**	里程未到已提醒*/
    	public static final String 	DISTENCEREMAINDER="distenceRemainder";
    	/**已他店保养*/
    	public static final String 	MAINTENCEOTHER="maintenceOther";
    	/**未接*/
    	public static final String 	REFUSEANSWER="refuseAnswer";
    	/**	停机*/
    	public static final String 	TURNOFF="turnOff";
    	/**关机*/
    	public static final String 	SHUTOFF="shutOff";
    	/**转呼*/
    	public static final String 	TURNOTHER="turnOther";
    	/**有事改期联系*/
    	public static final String 	VISITOTHERDAY="visitOtherDay";
    	/**拒访*/
    	public static final String 	REFUSEVISIT="refuseVisit";
    	/**车已卖*/
    	public static final String 	CARSELL="carSell";	
    }
    /**
     * 	流失去向
     * */
    public interface CARLOST{
    	/**父key*/
    	public static final String 	CARLOST="carLost";
    	/**修理厂*/
    	public static final String 	REPAIRFACTORY="repairFactory";
    	/**	外地*/
    	public static final String 	OTHERPLACE="otherPlace";
    }
    
    /**
     * 会员卡状态
     */
    public interface HYKZT{
        /**父key*/
        public static final String  HYKZT="hykzt";
        /**正常*/
        public static final String  ZC="zc";
        /**到期*/
        public static final String  DQ="dq";
        /**挂失*/
        public static final String  GS="gs";
        /**无效*/
        public static final String  WX="wx";
        /**注销*/
        public static final String  ZX="zx";
        /**冻结*/
        public static final String  DJ="dj";
        /**解冻*/
        public static final String  JD="jd";
    }
    /**系统管理员受限标示*/
    public static final boolean administratorTag=true;
    /**
     * 工作属性
     * */
    public interface BASJOBPROPERTY{
    	/**1管理人员*/
    	public static final int JOBPROPERTY1=1;
    	/**2维修业务*/
    	public static final int JOBPROPERTY2=2;
    	/**3维修接待*/
    	public static final int JOBPROPERTY3=3;
    	/**4配件仓库*/ 
    	public static final int JOBPROPERTY4=4;
    	/**5财务人员*/ 
    	public static final int JOBPROPERTY5=5;
    	/**6售后维修*/ 
    	public static final int JOBPROPERTY6=6;
    	/**7配件采购*/ 
    	public static final int JOBPROPERTY7=7;
    	/**8维修领料*/ 
    	public static final int JOBPROPERTY8=8;
    	/**9保险人员*/ 
    	public static final int JOBPROPERTY9=9;
    	/**10车间检验*/ 
    	public static final int JOBPROPERTY10=10;
    	/**11维修三包*/ 
    	public static final int JOBPROPERTY11=11;
    	/**12销售业务*/ 
    	public static final int JOBPROPERTY12=12;
    	/**13整车仓库 */ 
    	public static final int JOBPROPERTY13=13;
    	/**14整车采购 */ 
    	public static final int JOBPROPERTY14=14;
    	/**15PDI检测*/ 
    	public static final int JOBPROPERTY15=15;
    	/**16整车装潢*/
    	public static final int JOBPROPERTY16=16;
    	/**17客服人员*/
    	public static final int JOBPROPERTY17=17;
    }
    
    public interface VIPOPERATETYPE{
        /**父节点*/
        public static final String VIPOPERATETYPE="vipOperateType";
        /**注销*/
        public static final String OPERATE1="operate1";
        /**挂失*/
        public static final String OPERATE2="operate2";
        /**冻结*/
        public static final String OPERATE3="operate3";
        /**解冻*/ 
        public static final String OPERATE4="operate4";
        /**续会*/ 
        public static final String OPERATE5="operate5";
        /**退会*/ 
        public static final String OPERATE6="operate6";
    }
    
    //预出库处理方式
    public interface DEALSTYLE{
    	
    	public static final String OUTPER="outper";
    	/**先开后补*/
        public static final String FRISTOPEN="fristopen";
		/**只开不补*/
	    public static final String OPENNOTB="opennotb";
    }
    
    public interface INTEGRALTYPE{
        /**父节点*/
        public static final String INTEGRALTYPE="integralType";
        /**维修积分*/
        public static final String INTEGRALTYPE1="integralType1";
        /**销售积分*/
        public static final String INTEGRALTYPE2="integralType2";
    }
    
    //账目出入方式
    public interface ACCOUNTTYPE{
        /**父节点*/
        public static final String ACCOUNTTYPE="accountType";
        /**收入*/
        public static final String ACCOUNTTYPE1="income";
        /**支出*/
        public static final String ACCOUNTTYPE2="defray";
    }
}