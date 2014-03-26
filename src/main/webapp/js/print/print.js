var LODOP; //声明为全局变量 

function prn1_preview() {	
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

function CreateOneFormPage(){
	LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));  
	LODOP.PRINT_INIT("打印控件功能演示_Lodop功能_表单一");
	LODOP.SET_PRINT_PAGESIZE(1, 0, 0, "A4") ; //A4纸张纵向打印
	LODOP.SET_PRINT_STYLE("FontSize",12);
    LODOP.SET_PRINT_STYLE("Bold", 1);
	LODOP.ADD_PRINT_TEXT(50,50,260,39,"打印页面部分内容");
	LODOP.ADD_PRINT_HTM(88,50,300,200,document.getElementById("StGoodsStorageReportDiv").innerHTML); 
//                  四个数值分别表示Top,Left,Width,Height
};	                     