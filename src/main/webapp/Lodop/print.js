var LODOP; //声明为全局变量 


function CreateOneFormPage(){
	LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));  
	LODOP.PRINT_INIT("打印控件功能演示_Lodop功能_表单一");
	LODOP.SET_PRINT_STYLE("FontSize",18);
	LODOP.SET_PRINT_STYLE("Bold",1);
	LODOP.ADD_PRINT_TEXT(50,231,260,39,"打印页面部分内容");
	LODOP.ADD_PRINT_HTM(88,200,350,600,document.getElementById("StGoodsStorageReportDiv").innerHTML);
};	  


function prn1_preview() {	
	alert('111');
	CreateOneFormPage();	
	LODOP.PREVIEW();	
};

function prn1_print() {		
	CreateOneFormPage();
	LODOP.PRINT();	
};

function prn1_printA() {		
	CreateOneFormPage();
	LODOP.PRINTA(); 	
};	

function prn1_manage() {	
	CreateOneFormPage();
	LODOP.PRINT_SETUP();	
};	
