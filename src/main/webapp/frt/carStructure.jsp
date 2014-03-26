<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/carStructure.css"></link>
<div style="width: 460px;height:100%;">
	<img src="${pageContext.request.contextPath}/images/CarBody.jpg"  usemap="#Face">
	<map name="Face">
		<!-- 第一行 -->
		<!-- 2C -->
		<div  class="div"
			style="position:absolute;top:5px;left:70px;width:5px;height:5px;">
			<span id="remark1"></span>
		</div>
		<area shape="poly" coords="70,5,96,4,94,14,94,19,71,19"
			onclick="addRemark(1,$('#remark1'));">
		<!-- 2D -->
		<div  class="div"
			style="position:absolute;top:5px;left:100px;width:5px;height:5px;">
			<span id="remark2"></span>
		</div>
		<area shape="rect" coords="97,0,127,34"
			onclick="addRemark(2,$('#remark2'));">
		<!-- 2E -->
		<div  class="div"
			style="position:absolute;top:5px;left:130px;width:5px;height:5px;">
			<span id="remark3"></span>
		</div>
		<area shape="rect" coords="127,0,150,34"
			onclick="addRemark(3,$('#remark3'));">
		<!-- 2F -->
		<div  class="div"
			style="position:absolute;top:5px;left:160px;width:5px;height:5px;">
			<span id="remark4"></span>
		</div>
		<area shape="rect" coords="150,0,176,34"
			onclick="addRemark(4,$('#remark4'));">
		<!-- 2G -->
		<div class="div"
			style="position:absolute;top:5px;left:190px;width:5px;height:5px;">
			<span id="remark5"></span>
		</div>
		<area shape="rect" coords="176,0,205,34"
			onclick="addRemark(5,$('#remark5'));">
		<!-- 2H -->
		<div class="div"
			style="position:absolute;top:5px;left:220px;width:5px;height:5px;">
			<span id="remark6"></span>
		</div>
		<area shape="rect" coords="205,0,240,34"
			onclick="addRemark(6,$('#remark6'));">
		<!-- 2I -->
		<div class="div"
			style="position:absolute;top:5px;left:250px;width:5px;height:5px;">
			<span id="remark7"></span>
		</div>
		<area shape="rect" coords="240,0,258,34"
			onclick="addRemark(7,$('#remark7'));">
		<!-- 2J -->
		<div class="div"
			style="position:absolute;top:5px;left:280px;width:5px;height:5px;">
			<span id="remark8"></span>
		</div>
		<area shape="rect" coords="258,0,290,34"
			onclick="addRemark(8,$('#remark8'));">
		<!-- 2K -->
		<div class="div"
			style="position:absolute;top:5px;left:310px;width:5px;height:5px;">
			<span id="remark9"></span>
		</div>
		<area shape="rect" coords="290,0,334,34"
			onclick="addRemark(9,$('#remark9'));">
		<!-- 2L -->
		<div class="div"
			style="position:absolute;top:5px;left:340px;width:5px;height:5px;">
			<span id="remark10"></span>
		</div>
		<area shape="rect" coords="334,0,360,34"
			onclick="addRemark(10,$('#remark10'));">
		<!-- 第二行 -->
		<!-- 3A -->
		<div class="div"
			style="position:absolute;top:35px;left:10px;width:5px;height:5px;">
			<span id="remark11"></span>
		</div>
		<area shape="rect" coords="0,47,25,60"
			onclick="addRemark(11,$('#remark11'));">
		<!-- 3B -->
		<div class="div"
			style="position:absolute;top:35px;left:40px;width:5px;height:5px;">
			<span id="remark12"></span>
		</div>
		<area shape="rect" coords="30,50,60,70"
			onclick="addRemark(12,$('#remark12'));">
		<!-- 3F -->
		<div class="div"
			style="position:absolute;top:35px;left:160px;width:5px;height:5px;">
			<span id="remark13"></span>
		</div>
		<area shape="rect" coords="145,35,169,52"
			onclick="addRemark(13,$('#remark13'));">
		<!-- 3G -->
		<div class="div"
			style="position:absolute;top:35px;left:190px;width:5px;height:5px;">
			<span id="remark14"></span>
		</div>
		<area shape="rect" coords="169,35,203,52"
			onclick="addRemark(14,$('#remark14'));">
		<!-- 3H -->
		<div class="div"
			style="position:absolute;top:35px;left:220px;width:5px;height:5px;">
			<span id="remark15"></span>
		</div>
		<area shape="rect" coords="203,35,216,52"
			onclick="addRemark(15,$('#remark15'));">
		<!-- 3I -->
		<div class="div"
			style="position:absolute;top:35px;left:250px;width:5px;height:5px;">
			<span id="remark16"></span>
		</div>
		<area shape="rect" coords="216,35,260,52"
			onclick="addRemark(16,$('#remark16'));">
		<!-- 3J -->
		<div class="div"
			style="position:absolute;top:35px;left:280px;width:5px;height:5px;">
			<span id="remark17"></span>
		</div>
		<area shape="rect" coords="260,35,280,52"
			onclick="addRemark(17,$('#remark17'));">
		<!-- 3K -->
		<div class="div"
			style="position:absolute;top:35px;left:310px;width:5px;height:5px;">
			<span id="remark18"></span>
		</div>
		<area shape="rect" coords="280,35,305,52"
			onclick="addRemark(18,$('#remark18'));">
		<!-- 第三行 -->
		<!-- 4A -->
		<div class="div"
			style="position:absolute;top:65px;left:10px;width:5px;height:5px;">
			<span id="remark19"></span>
		</div>
		<area shape="rect" coords="0,70,30,88"
			onclick="addRemark(19,$('#remark19'));">
		<!-- 4B -->
		<div class="div"
			style="position:absolute;top:65px;left:40px;width:5px;height:5px;">
			<span id="remark20"></span>
		</div>
		<area shape="rect" coords="30,50,70,88"
			onclick="addRemark(20,$('#remark20'));">
		<!-- 4C -->
		<div class="div"
			style="position:absolute;top:65px;left:70px;width:5px;height:5px;">
			<span id="remark21"></span>
		</div>
		<area shape="rect" coords="70,50,96,88"
			onclick="addRemark(21,$('#remark21'));">
		<!-- 4D -->
		<div class="div"
			style="position:absolute;top:65px;left:100px;width:5px;height:5px;">
			<span id="remark22"></span>
		</div>
		<area shape="rect" coords="96,50,124,88"
			onclick="addRemark(22,$('#remark22'));">
		<!-- 4E -->
		<div class="div"
			style="position:absolute;top:65px;left:130px;width:5px;height:5px;">
			<span id="remark23"></span>
		</div>
		<area shape="rect" coords="124,50,150,88"
			onclick="addRemark(23,$('#remark23'));">
		<!-- 4F -->
		<div class="div"
			style="position:absolute;top:65px;left:160px;width:5px;height:5px;">
			<span id="remark24"></span>
		</div>
		<area shape="rect" coords="150,50,176,88"
			onclick="addRemark(24,$('#remark24'));">
		<!-- 4G -->
		<div class="div"
			style="position:absolute;top:65px;left:190px;width:5px;height:5px;">
			<span id="remark25"></span>
		</div>
		<area shape="rect" coords="176,50,202,88"
			onclick="addRemark(25,$('#remark25'));">
		<!-- 4H -->
		<div class="div"
			style="position:absolute;top:65px;left:220px;width:5px;height:5px;">
			<span id="remark26"></span>
		</div>
		<area shape="rect" coords="202,50,228,88"
			onclick="addRemark(26,$('#remark26'));">
		<!-- 4I -->
		<div class="div"
			style="position:absolute;top:65px;left:250px;width:5px;height:5px;">
			<span id="remark27"></span>
		</div>
		<area shape="rect" coords="228,50,254,88"
			onclick="addRemark(27,$('#remark27'));">
		<!-- 4J -->
		<div class="div"
			style="position:absolute;top:65px;left:280px;width:5px;height:5px;">
			<span id="remark28"></span>
		</div>
		<area shape="rect" coords="254,50,280,88"
			onclick="addRemark(28,$('#remark28'));">
		<!-- 4K -->
		<div class="div"
			style="position:absolute;top:65px;left:310px;width:5px;height:5px;">
			<span id="remark29"></span>
		</div>
		<area shape="rect" coords="280,50,330,88"
			onclick="addRemark(29,$('#remark29'));">
		<!-- 4L -->
		<div class="div"
			style="position:absolute;top:65px;left:340px;width:5px;height:5px;">
			<span id="remark30"></span>
		</div>
		<area shape="rect" coords="330,50,360,85"
			onclick="addRemark(30,$('#remark30'));">
		<!-- 4M -->
		<div class="div"
			style="position:absolute;top:65px;left:370px;width:5px;height:5px;">
			<span id="remark31"></span>
		</div>
		<area shape="rect" coords="360,50,390,85"
			onclick="addRemark(31,$('#remark31'));">
		<!-- 4N -->
		<div class="div"
			style="position:absolute;top:65px;left:395px;width:5px;height:5px;">
			<span id="remark32"></span>
		</div>
		<area shape="rect" coords="390,50,410,85"
			onclick="addRemark(32,$('#remark32'));">
		<!-- 第四行 -->
		<!-- 5A -->
		<div class="div"
			style="position:absolute;top:95px;left:10px;width:5px;height:5px;">
			<span id="remark33"></span>
		</div>
		<area shape="rect" coords="0,88,25,104"
			onclick="addRemark(33,$('#remark33'));">
		<!-- 5B -->
		<div class="div"
			style="position:absolute;top:95px;left:40px;width:5px;height:5px;">
			<span id="remark34"></span>
		</div>
		<area shape="rect" coords="25,88,70,104"
			onclick="addRemark(34,$('#remark34'));">
		<!-- 5C -->
		<div class="div"
			style="position:absolute;top:95px;left:70px;width:5px;height:5px;">
			<span id="remark35"></span>
		</div>
		<area shape="rect" coords="70,88,96,120"
			onclick="addRemark(35,$('#remark35'));">
		<!-- 5D -->
		<div class="div"
			style="position:absolute;top:95px;left:100px;width:5px;height:5px;">
			<span id="remark36"></span>
		</div>
		<area shape="rect" coords="96,88,124,120"
			onclick="addRemark(36,$('#remark36'));">
		<!-- 5E -->
		<div class="div"
			style="position:absolute;top:95px;left:130px;width:5px;height:5px;">
			<span id="remark37"></span>
		</div>
		<area shape="rect" coords="124,88,150,120"
			onclick="addRemark(37,$('#remark37'));">
		<!-- 5F -->
		<div class="div"
			style="position:absolute;top:95px;left:160px;width:5px;height:5px;">
			<span id="remark38"></span>
		</div>
		<area shape="rect" coords="150,88,176,120"
			onclick="addRemark(38,$('#remark38'));">
		<!-- 5G -->
		<div class="div"
			style="position:absolute;top:95px;left:190px;width:5px;height:5px;">
			<span id="remark39"></span>
		</div>
		<area shape="rect" coords="176,88,202,120"
			onclick="addRemark(39,$('#remark39'));">
		<!-- 5H -->
		<div class="div"
			style="position:absolute;top:95px;left:220px;width:5px;height:5px;">
			<span id="remark40"></span>
		</div>
		<area shape="rect" coords="202,88,228,120"
			onclick="addRemark(40,$('#remark40'));">
		<!-- 5I -->
		<div class="div"
			style="position:absolute;top:95px;left:250px;width:5px;height:5px;">
			<span id="remark41"></span>
		</div>
		<area shape="rect" coords="228,88,254,120"
			onclick="addRemark(41,$('#remark41'));">
		<!-- 5J -->
		<div class="div"
			style="position:absolute;top:95px;left:280px;width:5px;height:5px;">
			<span id="remark42"></span>
		</div>
		<area shape="rect" coords="254,88,280,120"
			onclick="addRemark(42,$('#remark42'));">
		<!-- 5K -->
		<div class="div"
			style="position:absolute;top:95px;left:310px;width:5px;height:5px;">
			<span id="remark43"></span>
		</div>
		<area shape="rect" coords="293,88,330,122"
			onclick="addRemark(43,$('#remark43'));">
		<!-- 5L -->
		<div class="div"
			style="position:absolute;top:95px;left:340px;width:5px;height:5px;">
			<span id="remark44"></span>
		</div>
		<area shape="rect" coords="330,88,360,122"
			onclick="addRemark(44,$('#remark44'));">
		<!-- 5M -->
		<div class="div"
			style="position:absolute;top:95px;left:370px;width:5px;height:5px;">
			<span id="remark45"></span>
		</div>
		<area shape="rect" coords="360,88,390,122"
			onclick="addRemark(45,$('#remark45'));">
		<!-- 5N -->
		<div class="div"
			style="position:absolute;top:95px;left:395px;width:5px;height:5px;">
			<span id="remark46"></span>
		</div>
		<area shape="rect" coords="390,88,412,122"
			onclick="addRemark(46,$('#remark46'));">
		<!-- 第五行 -->
		<!-- 6A -->
		<div class="div"
			style="position:absolute;top:125px;left:10px;width:5px;height:5px;">
			<span id="remark47"></span>
		</div>
		<area shape="rect" coords="0,106,25,140"
			onclick="addRemark(47,$('#remark47'));">
		<!-- 6B-->
		<div class="div"
			style="position:absolute;top:125px;left:40px;width:5px;height:5px;">
			<span id="remark48"></span>
		</div>
		<area shape="rect" coords="25,106,70,125"
			onclick="addRemark(48,$('#remark48'));">
		<!-- 6F -->
		<div class="div"
			style="position:absolute;top:125px;left:160px;width:5px;height:5px;">
			<span id="remark49"></span>
		</div>
		<area shape="rect" coords="145,124,170,140"
			onclick="addRemark(49,$('#remark49'));">
		<!-- 6G -->
		<div class="div"
			style="position:absolute;top:125px;left:190px;width:5px;height:5px;">
			<span id="remark50"></span>
		</div>
		<area shape="rect" coords="170,124,203,140"
			onclick="addRemark(50,$('#remark50'));">
		<!-- 6H -->
		<div class="div"
			style="position:absolute;top:125px;left:220px;width:5px;height:5px;">
			<span id="remark51"></span>
		</div>
		<area shape="rect" coords="203,124,215,140"
			onclick="addRemark(51,$('#remark51'));">
		<!-- 6I -->
		<div class="div"
			style="position:absolute;top:125px;left:250px;width:5px;height:5px;">
			<span id="remark52"></span>
		</div>
		<area shape="rect" coords="215,124,258,140"
			onclick="addRemark(52,$('#remark52'));">
		<!-- 6J -->
		<div class="div"
			style="position:absolute;top:125px;left:280px;width:5px;height:5px;">
			<span id="remark53"></span>
		</div>
		<area shape="rect" coords="258,124,280,140"
			onclick="addRemark(53,$('#remark53'));">
		<!-- 6K -->
		<div class="div"
			style="position:absolute;top:125px;left:310px;width:5px;height:5px;">
			<span id="remark54"></span>
		</div>
		<area shape="rect" coords="280,124,306,140"
			onclick="addRemark(54,$('#remark54'));">
		<!-- 第六行 -->
		<!-- 7C -->
		<div class="div"
			style="position:absolute;top:155px;left:70px;width:5px;height:5px;">
			<span id="remark55"></span>
		</div>
		<area shape="rect" coords="70,140,100,171"
			onclick="addRemark(55,$('#remark55'));">
		<!-- 7D -->
		<div class="div"
			style="position:absolute;top:155px;left:100px;width:5px;height:5px;">
			<span id="remark56"></span>
		</div>
		<area shape="rect" coords="100,140,132,171"
			onclick="addRemark(56,$('#remark56'));">
		<!-- 7E -->
		<div class="div"
			style="position:absolute;top:155px;left:130px;width:5px;height:5px;">
			<span id="remark57"></span>
		</div>
		<area shape="rect" coords="132,140,150,171"
			onclick="addRemark(57,$('#remark57'));">
		<!-- 7F -->
		<div class="div"
			style="position:absolute;top:155px;left:160px;width:5px;height:5px;">
			<span id="remark58"></span>
		</div>
		<area shape="rect" coords="150,140,180,171"
			onclick="addRemark(58,$('#remark58'));">
		<!-- 7G -->
		<div class="div"
			style="position:absolute;top:155px;left:190px;width:5px;height:5px;">
			<span id="remark59"></span>
		</div>
		<area shape="rect" coords="180,150,210,171"
			onclick="addRemark(59,$('#remark59'));">
		<!-- 7H -->
		<div class="div"
			style="position:absolute;top:155px;left:220px;width:5px;height:5px;">
			<span id="remark60"></span>
		</div>
		<area shape="rect" coords="210,140,250,171"
			onclick="addRemark(60,$('#remark60'));">
		<!-- 7I -->
		<div class="div"
			style="position:absolute;top:155px;left:250px;width:5px;height:5px;">
			<span id="remark61"></span>
		</div>
		<area shape="rect" coords="250,140,260,171"
			onclick="addRemark(61,$('#remark61'));">
		<!-- 7J -->
		<div class="div"
			style="position:absolute;top:155px;left:280px;width:5px;height:5px;">
			<span id="remark62"></span>
		</div>
		<area shape="rect" coords="260,140,290,171"
			onclick="addRemark(62,$('#remark62'));">
		<!-- 7K -->
		<div class="div"
			style="position:absolute;top:155px;left:310px;width:5px;height:5px;">
			<span id="remark63"></span>
		</div>
		<area shape="rect" coords="290,140,320,171"
			onclick="addRemark(63,$('#remark63'));">
		<!-- 7L -->
		<div class="div"
			style="position:absolute;top:155px;left:340px;width:5px;height:5px;">
			<span id="remark64"></span>
		</div>
		<area shape="rect" coords="320,140,345,171"
			onclick="addRemark(64,$('#remark64'));">
	</map>
</div>