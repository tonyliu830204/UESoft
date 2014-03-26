function save(){
  if($('#password_').form('validate')){
	  $.ajax({
		   type: 'post',
		   dataType: 'json',
		   url: projectPath+'login_updatePassWord.action',
		   data: $('#password_').serialize(),
		   success: function(r){
			   alertMsg(r);
			   if(r.success){
				   cancel();
			   }
		   },
		   error : function (r){
			   if(r.readyState == '0' && r.status == '0'){
				   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
				   }
			   }
		  });
	  }
  }

  function cancel(){
	  $("#userPasswd").val("");
  $("#userPasswd1").val("");
  $("#userPasswd2").val("");
  $("#passWordLeval").val("");
  }

  function checkPassword(pwd,index){
	  var objLow=document.getElementById("pwdLow"+index);
  var objMed=document.getElementById("pwdMed"+index);
  var objHi=document.getElementById("pwdHi"+index);
  objLow.className="pwd-strength-box";
  objMed.className="pwd-strength-box";
  objHi.className="pwd-strength-box";
  if(pwd.length<6){
	  objLow.className="pwd-strength-box-low";
  }else{
	  var p1= (pwd.search(/[a-zA-Z]/)!=-1) ? 1 : 0;
	  var p2= (pwd.search(/[0-9]/)!=-1) ? 1 : 0;
	  var p3= (pwd.search(/[^A-Za-z0-9_]/)!=-1) ? 1 : 0;
	  var pa=p1+p2+p3;
	  if(pa==1){
	       objLow.className="pwd-strength-box-low";
	       $("#passWordLeval").val(parame1);
	  }else if(pa==2){
	       objMed.className="pwd-strength-box-med";
	       $("#passWordLeval").val(parame2);
	  }else if(pa==3){
	       objHi.className="pwd-strength-box-hi";
	       $("#passWordLeval").val(parame3);
	      }
	  }
  } 