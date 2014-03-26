function PushletEvent(e){this.arr=new Array,this.getSubject=function(){return this.get("p_subject")},this.getEvent=function(){return this.get("p_event")},this.put=function(e,t){return this.arr[e]=t},this.get=function(e){return this.arr[e]},this.toString=function(){var e="";for(var t in this.arr)e=e+t+"="+this.arr[t]+"\n";return e},this.toTable=function(){var e='<table border="1" cellpadding="3">',t='<div style="color:black; font-family:monospace; font-size:10pt; white-space:pre;">';for(var n in this.arr)e=e+"<tr><td bgColor=white>"+t+n+"</div></td><td bgColor=white>"+t+this.arr[n]+"</div></td></tr>";return e+="</table>",e};if(e)for(var t=0;t<e.attributes.length;t++)this.put(e.attributes[t].name,e.attributes[t].value)}function p_debug(e,t,n){if(e==0)return;PL.setDebug(!0),PL.debug(t,n),PL.setDebug(!1)}function p_embed(e){alert("Pushlet: p_embed() is no longer required for AJAX client")}function p_join(){PL.join()}function p_listen(e,t){PL.listen(e)}function p_join_listen(e){PL.joinListen(e)}function p_leave(){PL.leave()}function p_heartbeat(){PL.heartbeat()}function p_publish(e,t){var n=p_publish.arguments,r="",i="";for(var s=1;s<n.length;s++)s>1&&(i="&"),r=r+i+n[s]+"="+n[++s];PL.publish(e,r)}function p_subscribe(e,t){PL.subscribe(e,t)}function p_unsubscribe(e){PL.unsubscribe(e)}var PL={NV_P_FORMAT:"p_format=xml-strict",NV_P_MODE:"p_mode=pull",pushletURL:null,webRoot:null,sessionId:null,STATE_ERROR:-2,STATE_ABORT:-1,STATE_NULL:1,STATE_READY:2,STATE_JOINED:3,STATE_LISTENING:3,state:1,heartbeat:function(){PL._doRequest("hb")},join:function(){PL.sessionId=null,PL._doRequest("join",PL.NV_P_FORMAT+"&"+PL.NV_P_MODE)},joinListen:function(e){PL._setStatus("join-listen "+e),PL.sessionId=null;var t=PL.NV_P_FORMAT+"&"+PL.NV_P_MODE;e&&(t=t+"&p_subject="+e),PL._doRequest("join-listen",t)},leave:function(){PL._doRequest("leave")},listen:function(e){var t=PL.NV_P_MODE;e&&(t=t+"&p_subject="+e),PL._doRequest("listen",t)},publish:function(e,t){var n="p_subject="+e;t&&(n=n+"&"+t),PL._doRequest("publish",n)},subscribe:function(e,t){var n="p_subject="+e;t&&(n=n+"&p_label="+t),PL._doRequest("subscribe",n)},unsubscribe:function(e){var t;e&&(t="p_sid="+e),PL._doRequest("unsubscribe",t)},setDebug:function(e){PL.debugOn=e},_addEvent:function(e,t,n,r){var i=PL._getObject(e);if(i.addEventListener)return i.addEventListener(t,n,r),!0;if(i.attachEvent){var s=i.attachEvent("on"+t,n);return s}i["on"+t]=n},_doCallback:function(e,t){t?t(e):window.onEvent&&onEvent(e)},_doRequest:function(e,t){if(PL.state<0){PL._setStatus("died ("+PL.state+")");return}var n=!1;if(e=="join"||e=="join-listen")n=PL.state<PL.STATE_READY;else if(e=="leave")PL.state=PL.STATE_READY;else if(e=="refresh"){if(PL.state!=PL.STATE_LISTENING)return}else e=="listen"?n=PL.state<PL.STATE_JOINED:e=="subscribe"||e=="unsubscribe"?n=PL.state<PL.STATE_LISTENING:n=PL.state<PL.STATE_JOINED;if(n==1){PL._setStatus(e+" , waiting... state="+PL.state),setTimeout(function(){PL._doRequest(e,t)},100);return}var r=PL.pushletURL+"?p_event="+e;t&&(r=r+"&"+t),PL.sessionId!=null&&(r=r+"&p_id="+PL.sessionId,e=="p_leave"&&(PL.sessionId=null)),PL.debug("_doRequest",r),PL._getXML(r,PL._onResponse)},_getObject:function(e){return typeof e=="string"?document.getElementById(e):e},_getWebRoot:function(){if(PL.webRoot!=null)return PL.webRoot;var e=document.getElementsByTagName("head")[0],t=e.childNodes;for(var n=0;n<t.length;++n){var r=t.item(n).src;if(r){var i=r.indexOf("ajax-ph.js");if(i>=0){i=r.indexOf("lib"),PL.webRoot=r.substring(0,i);break}}}return PL.webRoot},_getXML:function(e,t){var n=new XMLHttpRequest;if(!n||n==null){alert("No browser XMLHttpRequest (AJAX) support");return}var r=t,i=!1;r&&(i=!0,n.onreadystatechange=function(){if(n.readyState==4)if(n.status==200)r(n.responseXML),n=null;else{var e=new PushletEvent;e.put("p_event","error"),e.put("p_reason","[pushlet] problem retrieving XML data:\n"+n.statusText),PL._onEvent(e)}}),n.open("GET",e,i),n.send(null);if(!r){if(n.status!=200){var s=new PushletEvent;return s.put("p_event","error"),s.put("p_reason","[pushlet] problem retrieving XML data:\n"+n.statusText),PL._onEvent(s),null}return n.responseXML}},_init:function(){PL._showStatus(),PL._setStatus("initializing..."),window.ActiveXObject&&!window.XMLHttpRequest&&(window.XMLHttpRequest=function(){var e=new Array("Msxml2.XMLHTTP.5.0","Msxml2.XMLHTTP.4.0","Msxml2.XMLHTTP.3.0","Msxml2.XMLHTTP","Microsoft.XMLHTTP");for(var t=0;t<e.length;t++)try{return new ActiveXObject(e[t])}catch(n){}return null}),!window.ActiveXObject&&window.XMLHttpRequest&&(window.ActiveXObject=function(e){switch(e.toLowerCase()){case"microsoft.xmlhttp":case"msxml2.xmlhttp":case"msxml2.xmlhttp.3.0":case"msxml2.xmlhttp.4.0":case"msxml2.xmlhttp.5.0":return new XMLHttpRequest}return null}),PL.pushletURL=PL._getWebRoot()+"pushlet.srv",PL._setStatus("initialized"),PL.state=PL.STATE_READY},_onEvent:function(e){PL.debug("_onEvent()",e.toString());var t=e.getEvent();if(t=="data")PL._setStatus("data"),PL._doCallback(e,window.onData);else{if(t=="refresh"){PL.state<PL.STATE_LISTENING&&PL._setStatus("not refreshing state="+PL.STATE_LISTENING);var n=e.get("p_wait");setTimeout(function(){PL._doRequest("refresh")},n);return}t=="error"?(PL.state=PL.STATE_ERROR,PL._setStatus("server error: "+e.get("p_reason")),PL._doCallback(e,window.onError)):t=="join-ack"?(PL.state=PL.STATE_JOINED,PL.sessionId=e.get("p_id"),PL._setStatus("connected"),PL._doCallback(e,window.onJoinAck)):t=="join-listen-ack"?(PL.state=PL.STATE_LISTENING,PL.sessionId=e.get("p_id"),PL._setStatus("join-listen-ack"),PL._doCallback(e,window.onJoinListenAck)):t=="listen-ack"?(PL.state=PL.STATE_LISTENING,PL._setStatus("listening"),PL._doCallback(e,window.onListenAck)):t=="hb"?(PL._setStatus("heartbeat"),PL._doCallback(e,window.onHeartbeat)):t=="hb-ack"?PL._doCallback(e,window.onHeartbeatAck):t=="leave-ack"?(PL._setStatus("disconnected"),PL._doCallback(e,window.onLeaveAck)):t=="refresh-ack"?PL._doCallback(e,window.onRefreshAck):t=="subscribe-ack"?(PL._setStatus("subscribed to "+e.get("p_subject")),PL._doCallback(e,window.onSubscribeAck)):t=="unsubscribe-ack"?(PL._setStatus("unsubscribed"),PL._doCallback(e,window.onUnsubscribeAck)):t=="abort"?(PL.state=PL.STATE_ERROR,PL._setStatus("abort"),PL._doCallback(e,window.onAbort)):t.match(/nack$/)&&(PL._setStatus("error response: "+e.get("p_reason")),PL._doCallback(e,window.onNack))}},_onResponse:function(e){PL.debug("_onResponse",e);var t=PL._rsp2Events(e);if(t==null){PL._setStatus("null events");return}delete e,PL.debug("_onResponse eventCnt=",t.length);for(i=0;i<t.length;i++)PL._onEvent(t[i])},_rsp2Events:function(e){if(!e||!e.documentElement)return null;var t=e.documentElement.getElementsByTagName("event"),n=new Array(t.length);for(i=0;i<t.length;i++)n[i]=new PushletEvent(t[i]);return n},statusMsg:"null",statusChanged:!1,statusChar:"|",_showStatus:function(){PL.statusChanged==1&&(PL.statusChar=="|"?PL.statusChar="/":PL.statusChar=="/"?PL.statusChar="--":PL.statusChar=="--"?PL.statusChar="\\":PL.statusChar="|",PL.statusChanged=!1),window.defaultStatus=PL.statusMsg,window.status=PL.statusMsg+"  "+PL.statusChar,timeout=window.setTimeout("PL._showStatus()",400)},_setStatus:function(e){PL.statusMsg="pushlet - "+e,PL.statusChanged=!0},timestamp:0,debugWindow:null,messages:new Array,messagesIndex:0,debugOn:!1,debug:function(e,t){if(PL.debugOn==0)return;var n="none";PL.debug.caller&&(n=PL.debug.caller.toString(),n=n.substring(9,n.indexOf(")")+1));var r="-"+n+": "+e+"="+t,i=new Date,s=i-PL.timestamp;s<1e4&&(r+=" ("+s+" msec)"),PL.timestamp=i;if(PL.debugWindow==null||PL.debugWindow.closed)PL.debugWindow=window.open("","p_debugWin","toolbar=no,scrollbars=yes,resizable=yes,width=600,height=400");PL.messages[PL.messagesIndex++]=r,PL.debugWindow.document.writeln("<html><head><title>Pushlet Debug Window</title></head><body bgcolor=#DDDDDD>");for(var o=0;o<PL.messagesIndex;o++)PL.debugWindow.document.writeln("<pre>"+o+": "+PL.messages[o]+"</pre>");PL.debugWindow.document.writeln("</body></html>"),PL.debugWindow.document.close(),PL.debugWindow.focus()}}