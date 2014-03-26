//屏蔽浏览器右键菜单
function clickIE4(){
        if (event.button==2){
                return false;
        }
}
 
function clickNS4(e){
        if (document.layers||document.getElementById&&!document.all){
                if (e.which==2||e.which==3){
                        return false;
                }
        }
}
 
function OnDeny(){
        if(event.ctrlKey || event.keyCode==78 && event.ctrlKey || event.altKey || event.altKey && event.keyCode==115){
                return false;
        }
}
 
if (document.layers){
        document.captureEvents(Event.MOUSEDOWN);
        document.onmousedown=clickNS4;
        document.onkeydown=OnDeny();
}else if (document.all&&!document.getElementById){
        document.onmousedown=clickIE4;
        document.onkeydown=OnDeny();
}
 
document.oncontextmenu=new Function("return false");
														
///////////////////////////////////////////////////////////////////////
/************************************禁用退格*************************************/
//处理键盘事件 禁止后退键（Backspace）密码或单行、多行文本框除外  
function forbidBackSpace(e) {  
    var ev = e || window.event; //获取event对象   
    var obj = ev.target || ev.srcElement; //获取事件源   
    var t = obj.type || obj.getAttribute('type'); //获取事件源类型   
    //获取作为判断条件的事件类型   
    var vReadOnly = obj.readOnly;  
    var vDisabled = obj.disabled;  
    //处理undefined值情况   
    vReadOnly = (vReadOnly == undefined) ? false : vReadOnly;  
    vDisabled = (vDisabled == undefined) ? true : vDisabled;  
    //当敲Backspace键时，事件源类型为密码或单行、多行文本的，   
    //并且readOnly属性为true或disabled属性为true的，则退格键失效   
    var flag1 = ev.keyCode == 8 && (t == "password" || t == "text" || t == "textarea") && (vReadOnly == true || vDisabled == true);  
    //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效   
    var flag2 = ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea";  
    //判断   
    if(flag2 || flag1) 
    	return false;  
}  
//禁止后退键 作用于Firefox、Opera  
document.onkeypress = forbidBackSpace;  
//禁止后退键  作用于IE、Chrome  
document.onkeydown = forbidBackSpace;  
/*********************************************************************************/
var editRow = undefined; // 表示处于编辑状态的行的索引

//主页状态栏时钟
function show(){
	var date = new Date(); //日期对象
	var now = "";
	now = date.getFullYear()+"-"; //读英文就行了
	if((date.getMonth()+1)<10){
		now = now + "0"+(date.getMonth()+1)+"-";
	}else{
		now = now + (date.getMonth()+1)+"-";//取月的时候取的是当前月-1如果想取当前月+1就可以了
	}
	if(date.getDate()<10){
		now = now + "0"+(date.getDate())+" ";
	}else{
		now = now + date.getDate()+" ";
	}
	if(date.getHours()<10){
		now = now + "0"+date.getHours()+":";
	}else{
		now = now + date.getHours()+":";
	}
	if(date.getMinutes()<10){
		now = now + "0"+date.getMinutes()+":";
	}else{
		now = now + date.getMinutes()+":";
	}
	if(date.getSeconds()<10){
		now = now + "0"+date.getSeconds()+"";
	}else{
		now = now + date.getSeconds()+"";
	}
	$('#nowDiv').text('当前时间: ' + now) //div的html是now这个字符串
	setTimeout("show()",1000); //设置过1000毫秒就是1秒，调用show方法
}

//添加tab选项卡
function addTab(id_, view_, title_, url_) {
	var tabs = $(view_).tabs("tabs");
    var length = tabs.length;
    var currTab;
    var id;
    var tag = false;
    if(length > 1){
    	if(length < 11){
		    for(var i=1;i<length;i++){
		        currTab = tabs[i];
		        id = currTab.panel('options').id;
		        if(id == id_){
		        	updateTabs(currTab, view_);
		        	return;
		        }
		    }
    	}else{
    	    alert("最多可以打开10个窗体");
    	    tag = true;
    	}
    }
    if(!tag){
    	addTabs(id_, view_, title_, url_);
    }
	tabClose();
}

function addTabs(id_, view_, title_, url_){
	$(view_).tabs('add', {
		id : id_,
		title : title_,
		border : false,
		content : createFrame(url_),
		fit : true,
		closable : true
	});
}

function updateTabs(currTab, view_){
	var index = $(view_).tabs('getTabIndex',currTab);
	$(view_).tabs('select', index);//选中并刷新
	var url = $(currTab.panel('options').content).attr('src');
	if(url != undefined && currTab.panel('options').title != 'Home') {
		$(view_).tabs('update',{
			tab:currTab,
			options:{
				content:createFrame(url)
			}
		})
	}
}
//创建iframe
function createFrame(url) {
	var s = '<iframe scrolling="auto" frameborder="0" src="' + url + '" style="width:100%;height:100%;"></iframe>';
	return s;
}

function tabClose() {
	// 双击关闭tab选项卡
	$(".tabs-inner").dblclick(function() {
		var title = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close', title);
	})
	// 为选项卡绑定右键
	$(".tabs-inner").bind('contextmenu', function(e) {
		$('#tm').menu('show', {
			left : e.pageX,
			top : e.pageY
		});

		var title = $(this).children(".tabs-closable").text();

		$('#tm').data("currtab", title);
		$('#tabs').tabs('select', title);
		return false;
	});
}

// 绑定右键菜单事件
function tabCloseEven() {
	// 刷新
	$('#tm-tabupdate').click(function() {
		var currTab = $('#tabs').tabs('getSelected');
		var url = $(currTab.panel('options').content).attr('src');
		if(url != undefined && currTab.panel('options').title != 'Home') {
			$('#tabs').tabs('update', {
				tab : currTab,
				options : {
					content : createFrame(url)
				}
			})
		}
	})
	// 关闭当前
	$('#tm-tabclose').click(function() {
		var currtab_title = $('#tm').data("currtab");
		$('#tabs').tabs('close', currtab_title);
	})
	// 关闭全部
	$('#tm-tabcloseall').click(function() {
		$('.tabs-inner span').each(function(i, n) {
			var t = $(n).text();
			if(t != 'Home') {
			    $('#tabs').tabs('close', t);
			}
		});
	});
	// 关闭其他
	$('#tm-tabcloseother').click(function() {
		var all = $('.tabs-selected').siblings();
		if (all.length == 0) {
			showMsg('优亿软件提示', '没有其他可关闭的选项了.', 'slide');
			return false;
		}
		all.each(function(i, n) {
			var t = $('a:eq(0) span', $(n)).text();
			if(t != 'Home') {
			    $('#tabs').tabs('close', t);
			}
		});
		return false;
	});
	// 退出
	$("#tm-exit").click(function() {
		$('#tm').menu('hide');
	});
}

function systemExitEven() {
	//注销登录
	$('#loginOut').click(function() {
		$.messager.confirm('优亿软件提示', '您确定要退出本次登录吗?', function(r) {
			if (r) {
				clearSession();
			}
		});
	})

	//退出登录
	$('#exit').click(function() {
		$.messager.confirm('优亿软件提示', '您确定要退出本系统吗?', function(r) {
			if (r) {
				clearSession();
			}
		});
	})
}

function clearSession(){
	location.href = 'login_dologout.action';
}

function easyuiExtend(){
	//扩展方法 解决congbox前面多出一个逗号无法获取值 
	$.extend($.fn.datagrid.defaults.editors.combobox, {
		        setValue : function(jq, value) {
		                var opts = $(jq).combobox('options');
		                if(opts.multiple&&value.indexOf(opts.separator)!=-1){//多选且不只一个值
		                        var values = value.split(opts.separator);
		                    $(jq).combobox("setValues", values);
		                }
		                else
		                $(jq).combobox("setValue", value);
		                }
	});
	//扩展方法 解决congbox前面多出一个逗号无法获取值 
	$.extend($.fn.datagrid.defaults.editors.combobox, {
		    getValue : function(jq) {
		        var opts = $(jq).combobox('options');
		        if(opts.multiple){
		            var values = $(jq).combobox('getValues');
		            if(values.length>0){
		                if(values[0]==''||values[0]==' '){
		                    return values.join(',').substring(1);//新增的时候会把空白当成一个值了，去掉
		                }
		            }
		            return values.join(',');
		        }
		        else
		            return $(jq).combobox("getValue");
		    },
		    setValue : function(jq, value) {
				if(value!=undefined){
		        var opts = $(jq).combobox('options');
		        if(opts.multiple&&value.indexOf(opts.separator)!=-1){//多选且不只一个值
		            var values = value.split(opts.separator);
		            $(jq).combobox("setValues", values);
		        }
		        else
		            $(jq).combobox("setValue", value);
				}
		    }
	});

	//扩展editor datetimebox组件
	 $.extend($.fn.datagrid.defaults.editors, {
	     datetimebox: {
	         init: function(container, options){
	             var input = $('<input class="easyuidatetimebox" style="width:140px;">').appendTo(container);
	             return input.datetimebox({
	                 formatter:function(date){
	                     return new Date(date).format("yyyy-MM-dd hh:mm:ss");
	                 }
	             });
	         },
	         getValue: function(target){
	             return $(target).parent().find('input.combo-value').val();
	        },
	         setValue: function(target, value){
	             $(target).datetimebox("setValue",value);
	         },
	         resize: function(target, width){
	            var input = $(target);
	            if ($.boxModel == true){
	                 input.width(width - (input.outerWidth() - input.width()));
	            } else {
	                 input.width(width);
	             }
	         }
	     }
	});
	 
	//时间格式化
	 Date.prototype.format = function(format){
	     /*
	      * eg:format="yyyy-MM-dd hh:mm:ss";
	      */
	     if(!format){
	         format = "yyyy-MM-dd hh:mm:ss";
	     }

	     var o = {
	             "M+": this.getMonth() + 1, // month
	             "d+": this.getDate(), // day
	             "h+": this.getHours(), // hour
	             "m+": this.getMinutes(), // minute
	             "s+": this.getSeconds(), // second
	            "q+": Math.floor((this.getMonth() + 3) / 3), // quarter
	            "S": this.getMilliseconds()
	             // millisecond
	    };

	    if (/(y+)/.test(format)) {
	         format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	     }

	     for (var k in o) {
	         if (new RegExp("(" + k + ")").test(format)) { 
	             format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" +o[k]).length));
	        }
	     }
	     return format;
	 };
	 
	 /**
		 * combobox是否选中的验证方法
		 */
		$.extend($.fn.combobox.defaults.rules, {   
		    isSelected : {   
		        validator: function(value, param){
		        	return value != $(param[0]).combobox('getValue');
		        },   
		        message: '请从下拉框中选择xxx'  
		    }   
		});  
		
		/**
		 * 扩展datagrid数据为空的显示效果
		 */
		$.fn.datagrid.defaults.onLoadSuccess = function (data){
			if(data.total == '0'){
				var body = $(this).data().datagrid.dc.body2;
				body.find('table tbody').append('<tr><td width="'+body.width()+'" style="height: 25px; text-align: center;" colspan="6">对不起，没有满足此查询条件的记录，请确认查询条件是否正确！</td></tr>');
			}
		}
		
		// 扩展动态添加删除editor
		$.extend($.fn.datagrid.methods, {
			addEditor : function(jq, param) {
				if (param instanceof Array) {
					$.each(param, function(index, item) {
						var e = $(jq).datagrid('getColumnOption', item.field);
						e.editor = item.editor;
					});
				} else {
					var e = $(jq).datagrid('getColumnOption', param.field);
					e.editor = param.editor;
				}
			},
			removeEditor : function(jq, param) {
				if (param instanceof Array) {
					$.each(param, function(index, item) {
						var e = $(jq).datagrid('getColumnOption', item);
						e.editor = {};
					});
				} else {
					var e = $(jq).datagrid('getColumnOption', param);
					e.editor = {};
				}
			}
		});
		
		//扩展键盘上下键选中行方法
		$.extend($.fn.datagrid.methods, {
			keyCtr : function (jq) {
				return jq.each(function () {
					var grid = $(this);
					grid.datagrid('getPanel').panel('panel').attr('tabindex', 1).bind('keydown', function (e) {
						switch (e.keyCode) {
						case 38: // up
							var selected = grid.datagrid('getSelected');
							if (selected) {
								var index = grid.datagrid('getRowIndex', selected);
								grid.datagrid('selectRow', index - 1);
							} else {
								var rows = grid.datagrid('getRows');
								grid.datagrid('selectRow', rows.length - 1);
							}
							break;
						case 40: // down
							var selected = grid.datagrid('getSelected');
							if (selected) {
								var index = grid.datagrid('getRowIndex', selected);
								grid.datagrid('selectRow', index + 1);
							} else {
								grid.datagrid('selectRow', 0);
							}
							break;
						}
					});
				});
			}
		});
		
		//扩展easyui datagrid的两个方法.动态添加和删除toolbar的项
		$.extend($.fn.datagrid.methods, {  
			addToolbarItem: function(jq, items){  
				return jq.each(function(){  
					var toolbar = $(this).parent().prev("div.datagrid-toolbar");
					for(var i = 0;i<items.length;i++){
						var item = items[i];
						if(item === "-"){
							toolbar.append('<div class="datagrid-btn-separator"></div>');
						}else{
							var btn=$("<a href=\"javascript:void(0)\"></a>");
							btn[0].onclick=eval(item.handler||function(){});
							btn.css("float","left").appendTo(toolbar).linkbutton($.extend({},item,{plain:true}));
						}
					}
					toolbar = null;
				});  
			},
			removeToolbarItem: function(jq, param){  
				return jq.each(function(){  
					var btns = $(this).parent().prev("div.datagrid-toolbar").children("a");
					var cbtn = null;
					if(typeof param == "number"){
						cbtn = btns.eq(param);
					}else if(typeof param == "string"){
						var text = null;
						btns.each(function(){
							text = $(this).data().linkbutton.options.text;
							if(text == param){
								cbtn = $(this);
								text = null;
								return;
							}
						});
					} 
					if(cbtn){
						var prev = cbtn.prev()[0];
						var next = cbtn.next()[0];
						if(prev && next && prev.nodeName == "DIV" && prev.nodeName == next.nodeName){
							$(prev).remove();
						}else if(next && next.nodeName == "DIV"){
							$(next).remove();
						}else if(prev && prev.nodeName == "DIV"){
							$(prev).remove();
						}
						cbtn.remove();	
						cbtn= null;
					}						
				});  
			} 				
		});
		
		//扩展easyui dialog的两个方法.动态添加和删除toolbar的项
		$.extend($.fn.dialog.methods, {  
			addToolbarItem: function(jq, items){  
				return jq.each(function(){  
					var toolbar = $(this).children("div.dialog-toolbar");
					for(var i = 0;i<items.length;i++){
						var temp = toolbar.children(":last").prev();
						var item = items[i];
						if(item === "-"){
							temp.after('<div class="dialog-tool-separator"></div>');
						}else{
							var btn=$("<a href=\"javascript:void(0)\"></a>");
							btn[0].onclick=eval(item.handler||function(){});
							btn.css("float","left").insertAfter(temp).linkbutton($.extend({},item,{plain:true}));
						}
					}
					toolbar = null;
				});  
			},
			removeToolbarItem: function(jq, param){  
				return jq.each(function(){  
					var btns = $(this).children("div.dialog-toolbar").children("a");
					var cbtn = null;
					if(typeof param == "number"){
						cbtn = btns.eq(param);
					}else if(typeof param == "string"){
						var text = null;
						btns.each(function(){
							text = $(this).data().linkbutton.options.text;
							if(text == param){
								cbtn = $(this);
								text = null;
								return;
							}
						});
					} 
					if(cbtn){
						var prev = cbtn.prev()[0];
						var next = cbtn.next()[0];
						if(prev && next && prev.nodeName == "DIV" && prev.nodeName == next.nodeName){
							$(prev).remove();
						}else if(next && next.nodeName == "DIV"){
							$(next).remove();
						}else if(prev && prev.nodeName == "DIV"){
							$(prev).remove();
						}
						cbtn.remove();	
						cbtn= null;
					}						
				});  
			} 				
		});
		
		//扩展easyui dialog的两个方法.动态添加和删除buttons的项
		$.extend($.fn.dialog.methods, {  
			addButtonsItem: function(jq, items){  
			return jq.each(function(){  
				var buttons = $(this).children("div.dialog-button");
				for(var i = 0;i<items.length;i++){
					var item = items[i];
					if(item === "-"){
						buttons.append('<div class="dialog-btn-separator"></div>');
					}else{
						var btn=$("<a href=\"javascript:void(0)\"></a>");
						btn[0].onclick=eval(item.handler||function(){});
						var temp = buttons.children("a:last")
						btn.css("float","left").insertAfter(temp).linkbutton($.extend({},item,{plain:true}));
					}
				}
				buttons = null;
			});  
		},
		removeButtonsItem: function(jq, param){  
			return jq.each(function(){  
				var btns = $(this).children("div.dialog-button").children("a");
				var cbtn = null;
				if(typeof param == "number"){
					cbtn = btns.eq(param);
				}else if(typeof param == "string"){
					var text = null;
					btns.each(function(){
						text = $(this).data().linkbutton.options.text;
						if(text == param){
							cbtn = $(this);
							text = null;
							return;
						}
					});
				} 
				if(cbtn){
					var prev = cbtn.prev()[0];
					var next = cbtn.next()[0];
					if(prev && next && prev.nodeName == "DIV" && prev.nodeName == next.nodeName){
						$(prev).remove();
					}else if(next && next.nodeName == "DIV"){
						$(next).remove();
					}else if(prev && prev.nodeName == "DIV"){
						$(prev).remove();
					}
						cbtn.remove();	
						cbtn= null;
					}						
				});  
			} 				
		});
		
		/**
		 * 扩展id pid构成tree结构
		 */
		$.fn.tree.defaults.loadFilter = function (data, parent) {
			var opt = $(this).data().tree.options;
			var idFiled,
			textFiled,
			parentField;
			if (opt.parentField) {
				idFiled = opt.idFiled || 'id';
				textFiled = opt.textFiled || 'text';
				parentField = opt.parentField;
				
				var i,
				l,
				treeData = [],
				tmpMap = [];
				
				for (i = 0, l = data.length; i < l; i++) {
					tmpMap[data[i][idFiled]] = data[i];
				}
				
				for (i = 0, l = data.length; i < l; i++) {
					if (tmpMap[data[i][parentField]] && data[i][idFiled] != data[i][parentField]) {
						if (!tmpMap[data[i][parentField]]['children'])
							tmpMap[data[i][parentField]]['children'] = [];
						data[i]['text'] = data[i][textFiled];
						tmpMap[data[i][parentField]]['children'].push(data[i]);
					} else {
						data[i]['text'] = data[i][textFiled];
						treeData.push(data[i]);
					}
				}
				return treeData;
			}
			return data;
		};
		
		//扩展序列化表单对象,把表单序列化成easyui需要的格式对象格式
		serializeObject = function(form) {
			var o = {};
			$.each(form.serializeArray(), function(index) {
				if (o[this['name']]) {
					o[this['name']] = o[this['name']] + "," + this['value'];
				} else {
					o[this['name']] = this['value'];
				}
			});
			return o;
		};
		
		
		$.fn.fromToJson = function() { 
			var o = {}; 
			var a = this.serializeArray(); 
			$.each(a, function() { 
			  if (o[this.name]) { 
			    if (!o[this.name].push) { 
			        o[this.name] = [ o[this.name] ]; 
			    } 
			    o[this.name].push(this.value || '');
			  } else { 
			    o[this.name] = this.value || '';
			  } 
			}); 
			return o; 
		}
}

function easyuiValidate(){
	//扩展easyui的validator插件rules，支持更多类型验证
	/**
	* auther by God'Son
	* 
	* date : 2010 - 11 -29
	*/
	
	$.extend($.fn.validatebox.methods, {    
	    remove: function(jq, newposition){    
	        return jq.each(function(){    
	            $(this).removeClass("validatebox-text validatebox-invalid").unbind('focus').unbind('blur');  
	        });    
	    },  
	    reduce: function(jq, newposition){    
	        return jq.each(function(){    
	           var opt = $(this).data().validatebox.options;  
	           $(this).addClass("validatebox-text").validatebox(opt);  
	        });    
	    }     
	}); 

	$.extend($.fn.validatebox.defaults.rules, {
	    minLength : { // 判断最小长度
	        validator : function(value, param) {
	            return value.length >= param[0];
	        },
	        message : '最少输入 {0} 个字符。'
	    },
	    fixedLength : { // 判断最小长度
	    	validator : function(value, param) {
	    		return value.length == param[0];
	    	},
	    	message : 'xxx长度为 {0} 个字符。'
	    },
	    length:{validator:function(value,param){
	        var len=$.trim(value).length;
	            return len>=param[0]&&len<=param[1];
	        },
	            message:"输入内容长度必须介于{0}和{1}之间."
	        },
        maxLength:{validator:function(value,param){
	        var len=value.length;
	        	if(len>=param[0]){
	        		value=value.substring(0,param[0]);
	        	}
	            return len<param[0];
	        },
	            message:"输入内容长度最大为{0}."
	        },
	    phone : {// 验证电话号码
	        validator : function(value) {
	            return /^0\d{2,4}-?\d{7,8}$/i.test(value);
	        },
	        message : '请输入有效的固定电话（如：029-88888888）'
	    },
	    mobile : {// 验证手机号码
	        validator : function(value) {
	            return value==null?null:/^(13|15|18)\d{9}$/i.test(value);
	        },
	        message : '手机号码为11位数字'
	    },
	    bankAccount : {
	    	validator : function(value) {
	            return /^(95599|998801|998802|622525|622526|435744|435745|483536|528020|526855|622156|622155|356869|531659|622157|627066|627067|627068|627069)\d{10,13}$/i.test(value);
	        },
	        message : '银行账号格式不正确'
	    },
	    idcard : {// 验证身份证
	        validator : function(value) {
	            return value==null?null:/^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value);
	        },
	        message : '请输入有效的身份证号码'
	    },
	    intOrFloat : {// 验证整数或小数
	        validator : function(value) {
	            return /^\d+(\.\d+)?$/i.test(value);
	        },
	        message : '请输入数字，并确保格式正确'
	    },
	    currency : {// 验证货币
	        validator : function(value) {
	            return /^\d+(\.\d+)?$/i.test(value);
	        },
	        message : '货币格式不正确'
	    },
	    qq : {// 验证QQ,从10000开始
	        validator : function(value) {
	            return /^[1-9]\d{4,9}$/i.test(value);
	        },
	        message : 'QQ号码格式不正确'
	    },
	    integer : {// 验证整数
	        validator : function(value) {
	            return /^[+]?[1-9]+\d*$/i.test(value);
	        },
	        message : '请输入整数'
	    },
	    chinese : {// 验证中文
	        validator : function(value) {
	            return /^[\u0391-\uFFE5]+$/i.test(value);
	        },
	        message : '请输入中文'
	    },
	    english : {// 验证英语
	        validator : function(value) {
	            return /^[A-Za-z]+$/i.test(value);
	        },
	        message : '请输入英文'
	    },
	    unnormal : {// 验证是否包含空格和非法字符
	        validator : function(value) {
	            return /.+/i.test(value);
	        },
	        message : '输入值不能为空和包含其他非法字符'
	    },
	    username : {// 验证用户名
	        validator : function(value) {
	            return /^[a-zA-Z][a-zA-Z0-9_]{5,15}$/i.test(value);
	        },
	        message : '用户名不合法（字母开头，允许6-16字节，允许字母数字下划线）'
	    },
	    faxno : {// 验证传真
	        validator : function(value) {
//	            return /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/i.test(value);
	            return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
	        },
	        message : '请输入有效的传真号码（如：029-88888888）'
	    },
	    zip : {// 验证邮政编码
	        validator : function(value) {
	            return /^[1-9]\d{5}$/i.test(value);
	        },
	        message : '邮政编码为6位数字'
	    },
	    ip : {// 验证IP地址
	        validator : function(value) {
	            return /d+.d+.d+.d+/i.test(value);
	        },
	        message : 'IP地址格式不正确'
	    },
	    name : {// 验证姓名，可以是中文或英文
	            validator : function(value) {
	                return /^[\u0391-\uFFE5]+$/i.test(value)|/^\w+[\w\s]+\w+$/i.test(value);
	            },
	            message : '请输入姓名'
	    },
	    carNo:{
	        validator : function(value){
	            return /^[\u4E00-\u9FA5][\da-zA-Z]{6}$/.test(value); 
	        },
	        message : '车牌号码无效（例：粤J12350）'
	    },
	    carenergin:{
	        validator : function(value){
	            return /^[a-zA-Z0-9]{16}$/.test(value); 
	        },
	        message : '发动机型号无效(例：FG6H012345654584)'
	    },
	    email:{
	        validator : function(value){
	            return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value); 
	        },
	        message : '请输入有效的电子邮箱（如：abc@126.com）'    
	    },
	    msn:{
	        validator : function(value){
	            return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value); 
	        },
	        message : '请输入有效的msn账号(例：abc@hotnail(msn/live).com)'
	    },
	    monery:{//验证金钱格式
	        validator : function(value){
	            return /^([1-9][\d]{0,8}|0)(\.[\d]{1,2})?$/.test(value); 
	        },
	        message : '请输入有效的金额!正确格式为小数点前1-9位数字,小数点后1-2位数字,如10.00'
	    },
	   day:{//验证间隔天数
	        validator : function(value){
	            return /^([1-9][\d]{0,4}|0)(\.[\d]{0,1})?$/.test(value); 
	        },
	        message : '请输入有效的天数！'
	    },
	    interUrl:{
	        validator : function(value){
	            return /^((https|http|ftp)?:\/\/)+[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/.test(value); 
	        },
	        message : '请输入有效网址'
	    },
	    same:{
	        validator : function(value, param){
	            if($("#"+param[0]).val() != "" && value != ""){
	                return $("#"+param[0]).val() == value; 
	            }else{
	                return true;
	            }
	        },
	        message : '两次输入的密码不一致！'    
	    },
	    recipt:{//验证发票编号
	        validator : function(value){
            return /^[1-9][\d]{15,19}$/.test(value); 
        },
        message : '票据编号为16~20位数字！'
    },
	  //验证页面文本框只能输入数字,汉字,下划线
	    income:{
	    	validator : function(value){
	    	 return /^[-_]?[0-9_\u4e00-\u9fa5_[-_]]+$/.test(value); 
	        },
	        message : '请输入有效的收入名称(例：2000元-5000元)'   
	    },
	    //验证页面文本框只能输入汉字,字母或数字,小数点和空格，逗号
	    characterDigit:{
	    	validator : function(value){
	    	    return /^[\u4e00-\u9fa5 a-zA-Z0-9，,.-\/]+$/.test(value); 
	        },
	        message : '输入值不能包含特殊字符' 
	    },
	    //验证页面文本框只能输入汉字,字母或数字,括号
	    character_Digit:{
	    	validator : function(value){
	    	    return /^[\u4e00-\u9fa5 a-zA-Z0-9()\/]+$/.test(value); 
	        },
	        message : '输入值不能包含特殊字符' 
	    },
	    //验证页面文本框只能输入汉字,字母或数字
	    character:{
	    	validator : function(value){
	    	 return /^[\u4e00-\u9fa5_a-zA-Z0-9]+$/.test(value); 
	        },
	        message : '输入值不能包含特殊字符' 
	    },
	    characterDigitLength:{
	    	validator : function(value,param){
		    	var pattern = new RegExp("[\u4E00-\u9FA5a-zA-Z0-9_]")
		        if(!pattern.test(value)){
                    return false
		    	}else{
		        	var len=$.trim(value).length;
		            if(len<param[0]){
		            	return false;
		            }else if(len>param[1]){
		            	return false;
		            }else{
		            	return true;
		            }
		    	}
	        },
	        message : "输入内容长度必须介于{0}和{1}之间.不能包含特殊字符"
	    },
	    //验证特殊字符
	    specialCharacter:{
	    	validator : function(value){
		    	var pattern = new RegExp("[`~!@#$^&*()\-\_+=|{}':;',\\\\\[\\].<>/?\"%～·！￥……×（）——-、『』【】：；《》，。？/]")
		        if(!pattern.test(value)){
		    	     return false;
		    	}else{
		    	     return true;
		    	}
	        },
	        message : '输入值不能包含特殊字符' 
	    },
	   
	    //validatebox 多重校验
	    multiple : {
            validator : function(value, vtypes) {
                var returnFlag = true;
                var opts = $.fn.validatebox.defaults;
                for (var i = 0; i < vtypes.length; i++) {
                    var methodinfo = /([a-zA-Z_]+)(.*)/.exec(vtypes[i]);
                    var rule = opts.rules[methodinfo[1]];
                    if (value && rule) {
                        var parame = eval(methodinfo[2]);
                        if (!rule["validator"](value, parame)) {
                            returnFlag = false;
                            this.message = rule.message;
                            break;
                        }
                    }
                }
                return returnFlag;
            }
        },
        length : {
            validator : function(value, param) {
                this.message = '输入内容长度必须介于{0}和{1}之间.';
                var len = $.trim(value).length;
                if (param) {
                    for (var i = 0; i < param.length; i++) {
                        this.message = this.message.replace(new RegExp(
                                        "\\{" + i + "\\}", "g"), param[i]);
                    }
                }
                return len >= param[0] && len <= param[1];
            },
        message : '输入内容长度必须介于{0}和{1}之间.'
        },//只能输入字母数字vin号
        vinvalidator:{
	    	validator : function(value){
        	var pattern = new RegExp("^[a-zA-Z0-9]{17}$");
	    	return pattern.test(value); 
	        },
	        message : '格式不正确，只能输入17位的字母或数字！' 
	    },
      //只能输入字母数字vin号
        novalidator:{
	    	validator : function(value){
        	var pattern = new RegExp("^[a-zA-Z0-9]+$");
	    	return pattern.test(value); 
	        },
	        message : '格式不正确，只能输入字母或数字！' 
	    }
        
	});
}

function bodySlopOver(){
	/**
	 * @author 
	 * 
	 * @requires jQuery,EasyUI
	 * 
	 * 防止panel/window/dialog组件超出浏览器边界
	 * @param left
	 * @param top
	 */
	var easyuiPanelOnMove = function(left, top) {
		var l = left;
		var t = top;
		if (l < 1) {
			l = 1;
		}
		if (t < 1) {
			t = 1;
		}
		var width = parseInt($(this).parent().css('width')) + 14;
		var height = parseInt($(this).parent().css('height')) + 14;
		var right = l + width;
		var buttom = t + height;
		var browserWidth = $(window).width();
		var browserHeight = $(window).height();
		if (right > browserWidth) {
			l = browserWidth - width;
		}
		if (buttom > browserHeight) {
			t = browserHeight - height;
		}
		$(this).parent().css({/* 修正面板位置 */
			left : l,
			top : t
		});
	};
	$.fn.dialog.defaults.onMove = easyuiPanelOnMove;
	$.fn.window.defaults.onMove = easyuiPanelOnMove;
	$.fn.panel.defaults.onMove = easyuiPanelOnMove;
	
	//释放iframe内存
	$.fn.panel.defaults = $.extend({},$.fn.panel.defaults,{onBeforeDestroy:function(){
		var frame=$('iframe', this);
		if(frame.length>0){
			frame[0].contentWindow.document.write('');
			frame[0].contentWindow.close();
			frame.remove();
			if($.browser.msie){
				CollectGarbage();
			}
		}
		}
	});
}



$(function() {
	
	//获取绝对路径
	var path = window.document.location.href;
	var pathname = window.document.location.pathname;
	var pos = path.indexOf(pathname);
	var localhostpath = path.substring(0,pos);
	var projectName = pathname.substring(0,pathname.substr(1).indexOf('/')+1);
	var rootPath = localhostpath+projectName;
	
	tabClose();
	tabCloseEven();
	show();//显示动态时间
	systemExitEven();
	easyuiExtend();
	
    $('#cp').menubutton({  
        menu: '#mb'
    });
	
	//扩展方法
	/**
	 * 禁止鼠标右键菜单
	 */
	//禁止datagrid右键菜单
	$.fn.datagrid.defaults.onRowContextMenu = function (e){
		e.preventDefault();
	}
	//禁止tree右键菜单
	$.fn.tree.defaults.onContextMenu = function (e, node){
		e.preventDefault();
	}
	//禁止treegrid右键菜单
	$.fn.treegrid.defaults.onContextMenu = function (e, row){
		e.preventDefault();
	}
	
	easyuiValidate();
	bodySlopOver();
});

//点击车辆档案提醒时触发
function carArchivesRemindClick(){
	$('#carArchivesRemind').dialog({
		closed : false
	});
}

// 右下角弹出提示消息
function showMsg(r, type) {
	if(arguments.length == 1){
		$.messager.show( {
			title : '优亿软件提示',
			msg : r.msg,
			showType : 'slide',
			timeout : 2000
		});
	}else{
		$.messager.show( {
			title : '优亿软件提示',
			msg : r,
			showType : type,
			timeout : 2000
		});
	}
}

// 屏幕正中弹出提示消息
function alertMsg(r, msgType) {
	if(arguments.length == 1){
		$.messager.alert('优亿软件提示', r.msg, 'info');
	}else{
		$.messager.alert('优亿软件提示', r, msgType);
	}
}

function add(id, row, field, begin, end){
	if (editRow == undefined){
		id.datagrid('insertRow',{
			index : 0,
			row : row
		});
		id.datagrid('beginEdit', 0);
		bindEnterInCloumn(id, 0, 0);
		if(arguments.length == 5){
			editorBindEvent(0, id, begin, end);
		}
		editRow = 0; // 设置编辑行的索引(不可编辑行)
		if(arguments.length == 3){
			save(id, field);
		}else{
			save(id);
		}
	}else{
		alertMsg('本系统仅支持单行添加！', 'warning');
	}
}

/**
 * 给行编辑绑定事件
 * @param rowIndex
 * @param datagridId
 * @param begin
 * @param end
 * @returns
 */
var editorBindEvent = function(rowIndex, datagridId, begin, end){
	var editors = datagridId.datagrid('getEditors', rowIndex);
	editors[begin].target.bind('keyup', function() {
		var value = makePy($(this).val());
		editors[end].target.val(value);
	});
}

//保存单个datagrid的编辑行
function save(id, field){
	id.datagrid('addToolbarItem', [ {
		'text' : '保存',
		'iconCls' : 'icon-save',
		'handler' : function (){
			if(field){
				var ed = id.datagrid('getEditor', {index:editRow, field: field});
				var value = $(ed.target).combobox('getValue');
				var text = $(ed.target).combobox('getText');
				if(id.datagrid('validateRow', editRow) && value != text){
					removeToolBar(id);
					id.datagrid('endEdit', editRow);
					editRow = undefined;
				}else{
					alertMsg('对不起，记录无法保存，请确认内容及格式是否正确！', 'warning');
				}	
			}else{
				if(id.datagrid('validateRow', editRow)){
					removeToolBar(id);
					id.datagrid('endEdit', editRow);
					editRow = undefined;
				}else{
					alertMsg('对不起，记录无法保存，请确认内容及格式是否正确！', 'warning');
				}
			}
		}
		},{
			'text' : '取消',
			'iconCls' : 'icon-undo',
			'handler' : function (){
				rollBack(id);
				editRow = undefined;
			}
	}]);
}

function remove(id, url1, url2){
	if(editRow != undefined){
		alertMsg('请先完成保存或更新操作！', 'warning');
	}else{
		var row = id.datagrid('getSelected');
		if(row){
			$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r) {
				if (r) {
					$.post(url1, row, function (r){
						if(r.success){
							//alertMsg(r.msg, 'info');
							id.datagrid({
								url : url2
							});
						}else{
							alertMsg(r.msg, 'info');
						}
					},'json');
				}
			});
		}else{
			alertMsg('对不起，请先选中要删除的记录！', 'warning');
		}
	}
}

function edit(id, url1, url2, field, begin, end){
	if (editRow != undefined) {
		alertMsg('本系统只支持单行操作！', 'warning');
	}else{
		var $rowData = id.datagrid('getSelected');
		if ($rowData) {
			var rowIndex = id.datagrid('getRowIndex', $rowData);
			editRow = rowIndex;
			id.datagrid('beginEdit', rowIndex);
			bindEnterInCloumn(id, rowIndex, 0);
			if(arguments.length == 6){
				editorBindEvent(rowIndex, id, begin, end);
			}
			if(arguments.length == 4){
				save(id, field);
			}else{
				save(id);
			}
		} else {
			alertMsg('对不起，请先选中要修改的记录！', 'warning');
		}
	}
}

function _search(formId, datagridId){
	if(editRow == undefined){
		datagridId.datagrid('load',serializeObject(formId));
	}else{
		alertMsg('请完成添加或修改操作！', 'slide');
	}
}

function removeToolBar(id){
	id.datagrid("removeToolbarItem","保存");
	id.datagrid("removeToolbarItem","取消");
}

function getDate(){
	var date = new Date(); //日期对象
	var now = "";
	now = date.getFullYear()+"-"; //读英文就行了
	now = now + (date.getMonth()+1)+"-";//取月的时候取的是当前月-1如果想取当前月+1就可以了
	now = now + date.getDate()+" ";
	return now;
}

//单个datagrid编辑完成时触发
function onAfterEdit(id, url1 , url2, rowIndex, rowData, changes){
	var inserted = id.datagrid('getChanges', 'inserted');
	var updated = id.datagrid('getChanges', 'updated');
	if (inserted.length < 1 && updated.length < 1) {
		editRow = undefined;
		id.datagrid('unselectAll');
		return;
	}
	var url = '';
	if (inserted.length > 0) {
		url = url1;
	}
	if (updated.length > 0) {
		url = url2;
	}
	if(id.datagrid('validateRow', rowIndex)){
		$.post(url, rowData,
		   function(r){
			if (r.success) {
				id.datagrid('acceptChanges');
				//alertMsg(r.msg ,'info');
				editRow = undefined;
				id.datagrid('load');
			} else {
				id.datagrid('beginEdit', rowIndex);
				editRow = rowIndex;
				alertMsg(r.msg, 'info');
				save(id);
			}
		}, "json");
	}
}

function rollBack(id){
	if (editRow != undefined) {
		id.datagrid('rejectChanges');// 回滚正在编辑未提交的数据
		id.datagrid('unselectAll');
		removeToolBar(id);
		editRow = undefined; // 设置编辑行索引为undefined(可以编辑行)
	} else {
		alertMsg('没有可取消的编辑行！', 'warning');
	}
}

/**
 * 绑定回车切换列的方法
 * @param id datagrid id
 * @param rowIndex datagrid 行索引
 * @param i 固定值0
 * @returns
 */
var bindEnterInCloumn = function (id, rowIndex, i){
	var ed = id.datagrid('getEditors', rowIndex);
	ed[i].target.select();
	if(ed[i].type == 'combobox'){
		ed[i].target.combo('textbox').select();
		//ed[i].target.combo('showPanel');
		ed[i].target.combo('textbox').keydown(function (e){
			if(e.keyCode == '13'){
				if(i != ed.length - 1){
					bindEnterInCloumn(id, rowIndex, i + 1);
				}
			}
		});
	}else{
		ed[i].target.keydown(function (e){
			if(e.keyCode == '13'){
				if(i != ed.length - 1){
					bindEnterInCloumn(id, rowIndex, i + 1);
				}else{
					if(ed[0].type=='combobox'){
						ed[0].target.combo('textbox').select();
						//ed[0].target.combo('showPanel');
					}else{
						ed[0].target.select();
					}
				}
			}
		});
	}
}

/**
 * 双击datagrid A一行数据时把此行数据copy至datagrid B 并删除A的此行数据，同时开启B的此行数据的编辑状态，并绑定事件
 * @param id
 * @param rowIndex
 * @returns
 */
var copyDateAndBindEvent = function (id, rowIndex, rowData,flag){
	id.datagrid('beginEdit', rowIndex);
	var ed = id.datagrid('getEditors', rowIndex);
	if(ed[0]){
		ed[0].target.numberbox('setValue', '1');
		ed[0].target.select();
		ed[0].target.click(function (){
			ed[0].target.select();
		});
		ed[0].target.keydown(function (e){
			if(e.keyCode == '13'){
				ed[1].target.select();
			}
		});
	}
	if(ed[1]){
		ed[1].target.click(function (){
			ed[1].target.select();
		});
		ed[1].target.keydown(function (e){
			if(e.keyCode == '13'){
				if(rowIndex < id.datagrid('getData').total - 1){
					var ed = id.datagrid('getEditors', rowIndex + 1);
					ed[0].target.select();
				}else{
					var ed = id.datagrid('getEditors', 0);
					if(ed[0]){
						ed[0].target.select();
					}
				}
			}
		});
	}
	if(ed[2]){
		ed[2].target.numberbox('setValue', ed[1].target.val());
		ed[0].target.bind('keyup', function() {
			var tag=flag;
			var num = ed[0].target.val();
			var price = ed[1].target.val();
			var amount = accMul(parseFloat(num), parseFloat(price));
			ed[2].target.numberbox('setValue', amount);
			if(rowData && rowData.partsNowCount){
				if(tag==true)
				if(num > rowData.partsNowCount){
					alertMsg('数量不能大于库存数', 'warning');
					ed[0].target.numberbox('setValue', rowData.partsNowCount);
					var num = ed[0].target.val();
					var price = ed[1].target.val();
					var amount = accMul(parseFloat(num), parseFloat(price));
					ed[2].target.numberbox('setValue', amount);
					ed[0].target.select();
				}
			}
		});
		ed[1].target.bind('keyup', function() {
			var num = ed[0].target.val();
			var price = ed[1].target.val();
			var amount = accMul(parseFloat(num), parseFloat(price));
			ed[2].target.numberbox('setValue', amount);
		});
	}
}

/**
 * 循环关闭所有编辑行
 * @param id
 * @returns
 */
var endEdit = function (id){
	var rows = id.datagrid('getRows');
	for(var i = 0; i < rows.length; i++){
		id.datagrid('endEdit', i);
	}
}

/**
 * 提交所有编辑行
 * @param id
 * @param url
 * @returns
 */
var accept = function (id, url){
	endEdit(id);
	//var effectRow = saveAll(id);
	var effectRow = id.datagrid('getData');
	if(effectRow){
		$.ajax({
		   type: 'post',
		   dataType: 'json',
		   url: url,
		   data: effectRow,
		   success: function(r){
			   if(r.success){
				   id.datagrid('acceptChanges');
			   }
		   },
		   error : function (r){
			   alertMsg(r); 
		   }
		});
	}
}


/**
 * 提交所有编辑行
 * @param id
 * @param url
 * @returns
 */
var acceptcommit = function (id, url){
	endEdit(id);
	var effectRow = saveAll(id);
	//var effectRow = id.datagrid('getData');
	if(effectRow){
		$.ajax({
		   type: 'post',
		   dataType: 'json',
		   url: url,
		   data: effectRow,
		   success: function(r){
			   if(r.success){
				   id.datagrid('acceptChanges');
			   }
		   },
		   error : function (r){
			   alertMsg(r); 
		   }
		});
	}
}

/*从list中移除*/
var removeFromList = function (id, url, flag){
	if(editRow != undefined){
		alertMsg('请先完成保存或更新操作！', 'warning');
	}else{
		var row = id.datagrid('getSelected');
		if(row){
			var index = id.datagrid('getRowIndex', row);
			$.ajax({
			   type: 'post',
			   dataType: 'json',
			   url: url,
			   data: row,
			   success: function(r){
				 if(r.success){
					 id.datagrid('deleteRow', index);
					 id.datagrid('acceptChanges');
					 var datas = id.datagrid('getData');
					 if(index == datas.total){
						 id.datagrid('selectRow', 0);
					 }else{
						 id.datagrid('selectRow', index);
					 }
					 if(flag){
						 repairPackageAmount();
					 }
				 }
			   }
			}).error(function (r){
				alertMsg(r);
			});
		}else{
			alertMsg('对不起，请先选中要删除的记录！', 'warning');
		}
	}
}

/*在list中更新一条记录*/
var editFromList = function (id){
	if (editRow != undefined) {
		alertMsg('本系统只支持单行操作！', 'warning');
	}else{
		var $rowData = id.datagrid('getSelected');
		if ($rowData) {
			var $rowIndex = id.datagrid('getRowIndex', $rowData);
			editRow = $rowIndex;
			id.datagrid('beginEdit', $rowIndex);
			id.datagrid('unselectRow',  $rowIndex);
			var ed = id.datagrid('getEditors', $rowIndex);
			ed[0].target.select();
			ed[0].target.bind('keyup', function() {
				var num = ed[0].target.val();
				var price = ed[1].target.val();
				var amount = accMul(parseFloat(num), parseFloat(price));
				ed[2].target.numberbox('setValue', amount);
			});
			ed[1].target.bind('keyup', function() {
				var num = ed[0].target.val();
				var price = ed[1].target.val();
				var amount = accMul(parseFloat(num), parseFloat(price));
				ed[2].target.numberbox('setValue', amount);
			});
			saveToList(id, '配件保存', '配件取消');
		} else {
			alertMsg('对不起，请先选中要修改的记录！', 'warning');
		}
	}
}

/*保存到list*/
var saveToList = function (id, text1, text2){
	id.datagrid('addToolbarItem', [ {
		'text' : text1,
		'iconCls' : 'icon-save',
		'handler' : function (){
			if(id.datagrid('validateRow')){
				id.datagrid('endEdit', editRow);
				removeToolBarList(id, text1, text2);
				editRow = undefined;
			}else{
				alertMsg('对不起，记录无法保存，请确认内容及格式是否正确！', 'warning');
			}	
		}
		},{
			'text' : text2,
			'iconCls' : 'icon-redo',
			'handler' : function (){
				rowBackList(id, text1, text2);
				editRow = undefined;
			}
	}]);
}

/*list编辑完成时触发*/
var onAfterEditList = function (id, rowIndex, rowData, url){
	var updated = id.datagrid('getChanges', 'updated');
	if(updated.length > 0){
		if(id.datagrid('validateRow', rowIndex)){
			$.ajax({
			   type: 'post',
			   dataType: 'json',
			   url: url,
			   data: rowData,
			   success: function(r){
				 if(r.success){
					 id.datagrid('updateRow', { index : rowIndex, row : rowData});
					 id.datagrid('acceptChanges');
					 repairPackageAmount();
				 }
			   }
			}).error(function (r){
				alertMsg(r);
			});
		}
	}
}

/*还原list操作*/
var rowBackList = function (id, text1, text2){
	if (editRow != undefined) {
		id.datagrid('rejectChanges');// 回滚正在编辑未提交的数据
		id.datagrid('unselectAll');
		removeToolBarList(id, text1, text2);
		editRow = undefined; // 设置编辑行索引为undefined(可以编辑行)
	} else {
		alertMsg('没有可取消的编辑行！', 'warning');
	}
}

var removeToolBarList = function (id, text1, text2){
	id.datagrid("removeToolbarItem", text1);
	id.datagrid("removeToolbarItem", text2);
}

/**
 * 保存所有变化的行
 * @param id
 * @returns
 */
var saveAll = function (id){
	if(id.datagrid('getChanges').length) {
		var inserted = id.datagrid('getChanges', 'inserted');
		var deleted = id.datagrid('getChanges', 'deleted');
		var updated = id.datagrid('getChanges', 'updated');
		var effectRow = new Object();
		if(inserted){
			effectRow['inserted'] = JSON.stringify(inserted);
		}
		if(deleted){
			effectRow['deleted'] = JSON.stringify(deleted);
		}
		if(updated){
			effectRow['updated'] = JSON.stringify(updated);
		}
		return effectRow;
	};
}

var saveAll1 = function (id,taxRate){
	if(id.datagrid('getChanges').length) {
		var inserted = id.datagrid('getChanges', 'inserted');
		var deleted = id.datagrid('getChanges', 'deleted');
		var updated = id.datagrid('getChanges', 'updated');
		var effectRow = new Object();
		if(inserted){
			effectRow['inserted'] = JSON.stringify(inserted);
		}
		if(deleted){
			effectRow['deleted'] = JSON.stringify(deleted);
		}
		if(updated){
			effectRow['updated'] = JSON.stringify(updated);
		}
		if(taxRate != null){
			effectRow['taxRate'] = JSON.stringify(taxRate);
		}
		return effectRow;
	};
}

var saveAll2 = function (id,effectRow){
	if(id.datagrid('getChanges').length) {
		var inserted = id.datagrid('getChanges', 'inserted');
		var deleted = id.datagrid('getChanges', 'deleted');
		var updated = id.datagrid('getChanges', 'updated');
		if(effectRow == null){
		    effectRow = new Object();
		}
		if(inserted){
			effectRow['inserted'] = JSON.stringify(inserted);
		}
		if(deleted){
			effectRow['deleted'] = JSON.stringify(deleted);
		}
		if(updated){
			effectRow['updated'] = JSON.stringify(updated);
		}
		return effectRow;
	};
}

/**
 * @author 
 * 
 * 增加formatString功能
 * 
 * 使用方法：sy.fs('字符串{0}字符串{1}字符串','第一个变量','第二个变量');
 * 
 * @returns 格式化后的字符串
 */
fs = function(str) {
	for ( var i = 0; i < arguments.length - 1; i++) {
		str = str.replace("{" + i + "}", arguments[i + 1]);
	}
	return str;
};

//加法
function accAdd(arg1, arg2) {
	var r1, r2, m;
	try {
		r1 = arg1.toString().split(".")[1].length
	} catch (e) {
		r1 = 0
	}
	try {
		r2 = arg2.toString().split(".")[1].length
	} catch (e) {
		r2 = 0
	}
	m = Math.pow(10, Math.max(r1, r2))
	return (arg1 * m + arg2 * m) / m
}
//减法
function accSub(arg1, arg2) {
	var r1, r2, m, n;
	try {
		r1 = arg1.toString().split(".")[1].length
	} catch (e) {
		r1 = 0
	}
	try {
		r2 = arg2.toString().split(".")[1].length
	} catch (e) {
		r2 = 0
	}
	m = Math.pow(10, Math.max(r1, r2));
	//last modify by deeka   
	//动态控制精度长度   
	n = (r1 >= r2) ? r1 : r2;
	return ((arg1 * m - arg2 * m) / m).toFixed(n);
}
//乘法
function accMul(arg1, arg2) {
	var m = 0, s1 = arg1.toString(), s2 = arg2.toString();
	try {
		m += s1.split(".")[1].length
	} catch (e) {
	}
	try {
		m += s2.split(".")[1].length
	} catch (e) {
	}
	return Number(s1.replace(".", "")) * Number(s2.replace(".", ""))
			/ Math.pow(10, m)
}
//除法
function accDiv(arg1, arg2) {
	var t1 = 0, t2 = 0, r1, r2;
	try {
		t1 = arg1.toString().split(".")[1].length
	} catch (e) {
	}
	try {
		t2 = arg2.toString().split(".")[1].length
	} catch (e) {
	}
	with (Math) {
		r1 = Number(arg1.toString().replace(".", ""))
		r2 = Number(arg2.toString().replace(".", ""))
		return (r1 / r2) * pow(10, t2 - t1);
	}
}

/**
 * 表单正则验证
 * @param type
 * @param value
 * @returns
 */
var validateForm = function (type, value){
	if(type == 'phone'){
		return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
	}
	if(type == 'faxno'){
		return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
	}
	if(type == 'url'){
		return /^((https|http|ftp|rtsp|mms)?:\/\/)+[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/.test(value);
	}
	if(type == 'email'){
		return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value); 
	}
	if(type == 'zip'){
		return /^[1-9]\d{5}$/i.test(value);
	}
	if(type == 'mobile'){
	    return /^(13|15|18)\d{9}$/i.test(value);
	}
	if(type == 'gtzerointeger'){
		return /^([1-9][0-9]*)$/.test(value);
	}
}

/**系统初始化时间（年-月-日）**/
function getSystemTime()
{
	var mydate=new Date();
	var str = mydate.getFullYear() + "-";
	if((mydate.getMonth()+1+'').length==1){
		str +="0"+(mydate.getMonth() + 1) + "-";
	}else{
		str += (mydate.getMonth() + 1) + "-";
	}
	if((mydate.getDate()+'').length==1){
		str +="0"+ mydate.getDate(); 
	}else{
		str += mydate.getDate();
	}
	return str;
}





/**系统初始化时间（年-月-日  时：分）**/
function getSystemTime_y_m_d_h_m()
{
	var now= new Date();     
    var year=now.getFullYear();     
    var month=now.getMonth()+1;     
    var day=now.getDate();     
    var hour=now.getHours();
    var minute=now.getMinutes();     
    if((year+'').length==1){
    	year='0'+year;
    }
    if((month+'').length==1){
    	month='0'+month;
    }
    if((day+'').length==1){
    	day='0'+day;
    }
    if((hour+'').length==1){
    	hour='0'+hour;
    }
    if((minute+'').length==1){
    	minute='0'+minute;
    }
    return year+"-"+month+"-"+day+" "+hour+":"+minute;
}



 /**系统初始化时间（年-月-日  时：分：秒）**/
function getSystemTime2()
{
	var now= new Date();     
    var year=now.getFullYear();     
    var month=now.getMonth()+1;     
    var day=now.getDate();     
    var hour=now.getHours();
    var minute=now.getMinutes();     
    var second=now.getSeconds(); 
    if((year+'').length==1){
    	year='0'+year;
    }
    if((month+'').length==1){
    	month='0'+month;
    }
    if((day+'').length==1){
    	day='0'+day;
    }
    if((hour+'').length==1){
    	hour='0'+hour;
    }
    if((minute+'').length==1){
    	minute='0'+minute;
    }
    if((second+'').length==1){
    	second='0'+second;
    }
    return year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
}


/**
 * 双击datagrid A一行数据时把此行数据copy至datagrid B 并删除A的此行数据，同时开启B的此行数据的编辑状态，并绑定事件
 * @param id
 * @param rowIndex
 * @returns
 */
var copyDateAndBindEventAndTwo = function (id, rowIndex, rowData,price){
	id.datagrid('beginEdit', rowIndex);
	var ed = id.datagrid('getEditors', rowIndex);
	if(ed[0]){
		if(rowData.itemCost == undefined){
		   ed[0].target.numberbox('setValue',  rowData.repitemTime);
		}
		ed[0].target.select();
		ed[0].target.click(function (){
			ed[0].target.select();
		});
		ed[0].target.keydown(function (e){
			if(e.keyCode == '13'){
				ed[1].target.select();
			}
		});
	}
	if(ed[1]){
		ed[1].target.click(function (){
			ed[1].target.select();
		});
		ed[1].target.keydown(function (e){
			if(e.keyCode == '13'){
				if(rowIndex < id.datagrid('getData').total - 1){
					var ed = id.datagrid('getEditors', rowIndex + 1);
					ed[0].target.select();
				}else{
					var ed = id.datagrid('getEditors', 0);
					if(ed[0]){
						ed[0].target.select();
					}
				}
			}
		});
	}
	if(ed[2]){
		var num = ed[0].target.val();
		var amount = accMul(parseFloat(num), parseFloat(price));
		ed[2].target.numberbox('setValue',amount);
		ed[0].target.bind('keyup', function() {
			var num1 = ed[0].target.val();
			amount1 = accMul(parseFloat(num1), parseFloat(price))
			ed[2].target.numberbox('setValue', amount1);
			if(rowData && rowData.partsNowCount){
				if(num > rowData.partsNowCount){
					alertMsg('数量不能大于库存数', 'warning');
					ed[0].target.numberbox('setValue', rowData.partsNowCount);
				}
			}
		});
	}
}
/**
 * 单击datagrid 一行数据时开启此行数据的编辑状态，并绑定事件
 * @param id
 * @param rowIndex
 * @returns
 */
var copyDateAndBindEventAndThree = function (id, rowIndex, rowData){
	id.datagrid('beginEdit', rowIndex);
	var ed = id.datagrid('getEditors', rowIndex);
	if(ed[1]){
		ed[1].target.numberbox('setValue',  ed[1].target.val());
		ed[1].target.select();
		ed[1].target.click(function (){
			ed[1].target.select();
		});
		ed[1].target.keydown(function (e){
			if(e.keyCode == '13'){
				ed[2].target.select();
			}
		});
	}
	if(ed[2]){
		ed[2].target.click(function (){
			ed[2].target.select();
		});
		if(ed[4].target.val()==1){
			ed[2].target.numberbox('setValue',  ed[2].target.val());
		}else{
			ed[2].target.keydown(function (e){
				if(e.keyCode == '13'){
					if(rowIndex < id.datagrid('getData').total - 1){
						var ed = id.datagrid('getEditors', rowIndex + 1);
						ed[1].target.select();
					}else{
						var ed = id.datagrid('getEditors', 0);
						if(ed[1]){
							ed[1].target.select();
						}
					}
				}
			});			
		}
	}
	var amountMoney=(ed[3].target.val()/ed[1].target.val());
	if(ed[3]){
		ed[3].target.numberbox('setValue',ed[3].target.val());
		ed[1].target.bind('keyup', function() {
			var num = ed[1].target.val();
			var price = amountMoney;
			var amount = accMul(parseFloat(num), parseFloat(price));
			ed[3].target.numberbox('setValue', amount);
			if(rowData && rowData.partsNowCount){
				if(num > rowData.partsNowCount){
					alertMsg('数量不能大于库存数', 'warning');
					ed[1].target.numberbox('setValue', rowData.partsNowCount);
				}
			}
		});
		ed[3].target.bind('keyup', function() {
			var num = ed[3].target.val();
			ed[3].target.numberbox('setValue',  num);
			var price = amountMoney;
			var amount =(parseFloat(num)/parseFloat(price));
			ed[1].target.numberbox('setValue', amount);
			if(rowData && rowData.partsNowCount){
				if(num > rowData.partsNowCount){
					alertMsg('数量不能大于库存数', 'warning');
					ed[1].target.numberbox('setValue', rowData.partsNowCount);
				}
			}
		});
	}
}
/**
 * 查找默认车牌照格式
 */
function findCarLicenseFormat(id){
	$.ajax({
	   type: 'post',
	   dataType: 'json',
	   url: 'frtOptionsAction!findDefaultCarLicenseFormat.action',
	   success: function(r){
	     	$('#'+id).combobox('setValue', r);
	   }
	});
}

/**
 * 查找默认索赔厂商编号
 * 
 */
function findDefaultClaimsCompanyId(id){
	$.ajax({
	   type: 'post',
	   dataType: 'json',
	   url: 'frtOptionsAction!findDefaultClaimsCompanyId.action',
	   success: function(r){
	     	$('#'+id).combobox('setValue', r);
	   }
	});
}

/**
 * 查找默认客户地区属性
 * 
 */
function findDefaultCustomAreaId(id){
	$.ajax({
	   type: 'post',
	   dataType: 'json',
	   url: 'frtOptionsAction!findDefaultCustomAreaId.action',
	   success: function(r){
	     	$('#'+id).combobox('setValue', r);
	   }
	});
}
/**
 *控制表单所有控件
 *tag为true,启用表单所有控件，启用校验
 *tag为false,禁用表单所有控件，禁用校验
 *form为表单名称
 * */
function requiredAsForm(tag,form){
	var	flag;
	if(tag==true){
		flag=false;
	}else{
		flag=true;
	}
	$("#"+form+" input.easyui-combobox").combobox({required:tag,disabled:flag});
	$("#"+form+" input.easyui-datetimebox").datetimebox({required:tag,disabled:flag});
	$("#"+form+" input.easyui-numberbox").numberbox({required:tag});
	$("#"+form+" input.easyui-numberbox").numberbox('setValue','');
	$("#"+form+" input.easyui-numberbox").numberbox("validate");
	$("#"+form+" input.easyui-validatebox").validatebox({required:tag});
	$("#"+form+" input.easyui-validatebox").validatebox("validate");
	$("#"+form+" input").prop("disabled", flag);
	$("#"+form+" textarea").prop("disabled",flag);
}
/*默认资料显示天数*/7
function getStartDate(id){
	var ds;
	var today=new Date();
	$.ajax({
		   type: 'post',
		   dataType: 'json',
		   url:'basCompanyInformationSetAction!loadCiValue.action',  
		   success: function(r){
				ds=r.ciValue;
				if(ds==null||ds==""||ds==undefined){
					ds=6;
				}
				
					today=new Date(today.setDate(today.getDate()-ds)).format("yyyy-MM-dd");
				
				id.val(today);
		   },
		   error : function (r){
			   if(r.readyState == '0' && r.status == '0'){
				   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
			   }
		   }
		   
		});
	}

/**
 *扩展序列化表单对象,默认初始化时间，
 *参数form为需要序列化的form表单，
 *参数flag为判断是否初始化 
 * */
var serializeObjectByflag = function(form,flag){
	var o = {};
	$.each(form.serializeArray(),function(index){
		if(o[this['name']]){
			o[this['name']]=o[this['name']]+","+this['value'];
		}else{
			o[this['name']]=this['value'];
		}
	});
	if(flag!=null){
		o['flag']=''+flag+'';
	}
	return o;
}

//添加初始化时间方法二dateId1,dateId2 分别为时间控件的两个id $('#xxx')
var initSearchDate = function(dateId1,dateId2){
	if((dateId1.val()==null || dateId1.val()=="" )&& (dateId2.val()==null || dateId2.val()=="")){
		$.ajax({
			   type: 'post',
			   dataType: 'json',
			   url:'sellUtilAction!initDateAndSearch.action',  
			   success: function(r){
					if(r.success){
						dateId1.val(r.obj.q);
						dateId2.val(r.obj.key);
					}else{
						alertMsg(r);
					}
			   },
			   error : function (r){
				   if(r.readyState == '0' && r.status == '0'){
					   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
				   }
			   }
			});
	}else{
	}
}

//添加初始化时间方法一dateId1,dateId2 分别为时间控件的两个id $('#xxx')
var addInitDate = function(dateId1,dateId2){
	if((dateId1.val()==null || dateId1.val()=="" )&& (dateId2.val()==null || dateId2.val()=="")){
		//初试时间
		dateId1.val(getStartDate(dateId1));
		dateId2.val(getSystemTime());
	}
} 

/*默认资料显示天数包时分秒*/
function getStartTime(id){
	var ds;
	var today=new Date();
	$.ajax({
		   type: 'post',
		   dataType: 'json',
		   url:'basCompanyInformationSetAction!loadCiValue.action',  
		   success: function(r){
				ds=r.ciValue;
				if(ds!=null && ds!=''){
					today=new Date(today.setDate(today.getDate()-ds)).format("yyyy-MM-dd");
				}else{
					today=new Date().format("yyyy-MM-dd");
				}
				id.val(today);
		   },
		   error : function (r){
			   if(r.readyState == '0' && r.status == '0'){
				   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
			   }
		   }
		   
		});
}
/*默认结算查询时间段*/
function loadPreClrTime(id){
	var ds;
	var today=new Date();
	$.ajax({
		   type: 'post',
		   dataType: 'json',
		   url:'basCompanyInformationSetAction!loadPreClrTime.action',  
		   success: function(r){
				ds=r.ciValue;
				if(ds!=null && ds!=''){
					today=new Date(today.setDate(today.getDate()-ds)).format("yyyy-MM-dd");
				}else{
					today=new Date().format("yyyy-MM-dd");
				}
				id.val(today);
		   },
		   error : function (r){
			   if(r.readyState == '0' && r.status == '0'){
				   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
			   }
		   }
		   
		});
}
/*默认结算查询包时分秒*/
function loadPreClrTime1(id){
	var ds;
	var today=new Date();
	$.ajax({
		   type: 'post',
		   dataType: 'json',
		   url:'basCompanyInformationSetAction!loadPreClrTime.action',  
		   success: function(r){
				ds=r.ciValue;
				if(ds!=null && ds!=''){
					today=new Date(today.setDate(today.getDate()-ds)).format("yyyy-MM-dd");
				}else{
					today=new Date().format("yyyy-MM-dd");
				}	
				id.val(today);
		   },
		   error : function (r){
			   if(r.readyState == '0' && r.status == '0'){
				   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
			   }
		   }
		   
		});
}
/*默认收款查询时间段*/
function loadPercharge(id){
	var ds;
	var today=new Date();
	$.ajax({
		   type: 'post',
		   dataType: 'json',
		   url:'basCompanyInformationSetAction!loadPercharge.action',  
		   success: function(r){
				ds=r.ciValue;
				if(ds!=null && ds!=''){
					today=new Date(today.setDate(today.getDate()-ds)).format("yyyy-MM-dd");
				}else{
					today=new Date().format("yyyy-MM-dd");
				}
				
				id.val(today);
		   },
		   error : function (r){
			   if(r.readyState == '0' && r.status == '0'){
				   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
			   }
		   }
		   
		});
}

/*默认提醒日期*/
function loadRemainder(id,url){
	var ds;
	var today=new Date();
	$.ajax({
		   type: 'post',
		   dataType: 'json',
		   url:url,  
		   success: function(r){
				ds=r.ciValue;
				if(ds!=null && ds!=''){
					today=new Date(today.setDate(today.getDate()+ds)).format("yyyy-MM-dd");				
				}else{
					today=new Date().format("yyyy-MM-dd");		
				}
				id.val(today);
		   },
		   error : function (r){
			   if(r.readyState == '0' && r.status == '0'){
				   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
			   }
		   }
		   
		});
}
/**
 * 去除输入项左右两端空格
 */
function trim(str){ //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}

function ajaxLoading(){
    $("<div class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo("body");
    $("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候。。。").appendTo("body").css({display:"block",left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2});
}

function ajaxLoadEnd(){
     $(".datagrid-mask").remove();
     $(".datagrid-mask-msg").remove();            
} 


/**
 * 
 * @param treeGridId           treegrid的ID属性值
 * @param formId               提交的form表单的ID属性值
 * @param url                  提交的URL
 * @param isReLoad             是否清空查询条件
 * @param isDouble             是否需要清空一次treegrid再加载数据
 * @return
 */
function reLoadTreeGrid(treeGridId, formId, url, isReLoad, isDouble)
{
	var treegrid = null;
	var data = null;
	var options = null;
	var pager = null;
	var pagination = null;
	if(formId != null){
		data = serializeObject($('#'+formId+''));
	}
	if(treeGridId != null){
		treegrid = $('#'+treeGridId+'');
		options = treegrid.treegrid("options");
		pager = treegrid.treegrid("getPager");
		if(pager != null){
			pagination = pager.data("pagination"); 
			if(pagination != null){
				var options1 = pagination.options;
				data.rows = options1.pageSize;
				if(isReLoad == true){
					data.page = 1;
					options1.pageNumber = 1;
				}else if(isReLoad == false){
					data.page = 1;
					data.page = options1.pageNumber;
				}else{
					data.page = 1;
					options1.pageNumber = 1;
				}
			}
		}
		if(url != null){
			options.url = url;
		}
		if(isReLoad == true){
			options.queryParams={};
		}else if(isReLoad == false){
			options.queryParams=data;
		}else{
			options.queryParams={};
		}
	}else{
		alert("请指定treegrid");
		return;
	}
	if(isDouble == true){
	    loadTreeGrid(treegrid, data, url);
	}else{
		loadTreeGrid2(treegrid, data, url);
	}
}

/**
 * 
 * @param treeGridId        treegrid的ID属性值
 * @param url               提交的URL
 * @param isDouble          是否需要清空一次treegrid再加载数据
 * @return
 */
function initTreeGridPager(treeGridId, url, isDouble)
{
	var treegrid = null;
	var options = null;
	if(treeGridId != null){
		treegrid = $('#'+treeGridId+'');
		options = treegrid.treegrid("options");
		treegrid.treegrid('getPager').pagination({
		    onSelectPage: function (pageNumber, pageSize) {
		        $(this).pagination('loading');
		        delete options.queryParams['id'];
		        var params = options.queryParams;
		        params.page = pageNumber;
		        params.rows = pageSize;
		        if(isDouble == true){
		        	loadTreeGrid(treegrid, params, url);
		    	}else{
		    		loadTreeGrid2(treegrid, params, url);
		    	}
		        $(this).pagination('loaded');
		    }
		});
		options.url = url;
	}else{
		alert("请指定treegrid");
		return;
	}
}

function loadTreeGrid(treegrid, data, url){
	$.post(url,data,function(r){
		treegrid.treegrid('loadData',{total:0,rows:[]});
		treegrid.treegrid('loadData',r); 
	},'json');
}

function loadTreeGrid2(treegrid, data, url){
	$.post(url,data,function(r){
		treegrid.treegrid('loadData',r); 
	},'json');
}

Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}