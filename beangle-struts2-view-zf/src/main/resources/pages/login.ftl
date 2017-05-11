[#ftl]
[@b.head bodyClass="login_bg" theme="xml"/]
[#if ((Session['loginFailureCount'])?default(0)>1)][#assign needCaptcha=true][#else][#assign needCaptcha=false][/#if]
<script type="text/javascript">
    var href = top.location.href;
    if(href.indexOf("login.action") < 0){
	top.location = top.location;
    }
    function displayMsgDiv() {
		var msgdiv = document.getElementById('msg_div');
		// msgdiv.className = "login_prompt";
	}
	function hideMsgDiv() {
		var msgdiv = document.getElementById('msg_div');
		msgdiv.className = "hide";
	}
	function displayMsgDiv1() {
		var msgdiv = document.getElementById('msg_div1');
		// msgdiv.className = "login_error";
	}
	function hideMsgDiv1() {
		var msgdiv = document.getElementById('msg_div1');
		msgdiv.className = "hide";
	}
	$(function (){
        $("#username").focus();
        var prompt_dl = $("#prompt_dl");
        if(prompt_dl.html() != ""){
        	prompt_dl.addClass("prompt_dl");
        }
	});
	
	function checkLogin(form){
		if(!form['username'].value){
			alert("用户名称不能为空");
			form['username'].focus();
			return false;
		}
		if(!form['password'].value){
			alert("密码不能为空");
			form['password'].focus();
			return false;
		}
		//[#if needCaptcha!false]
		if(!form['captcha'].value){
			alert("验证码不能为空");return false;
		}
		//[/#if]
		return true;
	}
	if("${locale.language}".indexOf("en")!=-1){document.getElementById('local_en').checked=true;}
	var username=beangle.cookie.get("username");
	if(null!=username){ $("#username").val(username);}
</script>
<div class="login_main">
	<div class="login_logo">
		<h2><a href="${base}/home.action"><img src="${base}/static/stylev4/logo/logo_school.png" /></a></h2>
		<h3><a href="${base}/home.action"><img src="${base}/static/stylev4/logo/logo_system.png" /></a></h3>
	</div>
	<div class="login_left">
		<img class="login_pic" src="${base}/static/stylev4/logo/login_pic.png" />
	</div>
	[@b.form name="loginForm"  id="loginForm" action="login" target="_top" onsubmit="return checkLogin(form)"]
	<div class="login_right">
		<div class="login">
			<div id="prompt_dl">[@b.messages textMode="1"/]</div>
			<div class="user">
				<label for=""> 用户名： </label>
				<input type="text" class="text_nor" name="username" id="username" style="width:170px" onFocus="displayMsgDiv()" onBlur="hideMsgDiv()" maxlength="30"/>
				<div id="msg_div" class="hide">
					<div class="prompcon">
						<p>
							提示风格、请输入账号
						</p>
					</div>
				</div>
			</div>
			<div class="passw">
				<label for=""> 密　码： </label>
				<input class="text_nor" type="password" id="password" name="password" style="width:170px"  onFocus="displayMsgDiv1()" onBlur="hideMsgDiv1()" maxlength="30"/>
				<div id="msg_div1" class="hide">
					<div class="prompcon">
						<p>
							报错风格、请输入密码
						</p>
					</div>
				</div>
			</div>
			[#if needCaptcha!false]
			<div class="yzm">
				<label for=""> 验证码： </label>
				<input id="captcha" name="captcha" class="text_nor" type="text" style="width:60px" maxlength="6"/>
				<img id="captchaImg" src="${b.url('/security/captcha')}" title="验证码,点击更换一张" onclick="bg.changeCaptcha(this)" width="60" height="25"/><a href="#" onclick="bg.changeCaptcha($('#captchaImg').get(0))">看不清
				<br />
				换一张</a>
			</div>
			[/#if]
			<div class="login_notice">
				<p style="padding-left:70px">
					<input name="keepLogin" type="checkbox" value="1" />
					两周内不用再登录
				</p>
			</div>
			<div class="btn">
				<input type="submit" class="btn_dl" value=""/>
				[#--
				<a href="#">忘记密码</a>
				--]
			</div>
			<div class="login_notice" style="padding-top:15px!important">
				<p>
					若防护级别高，则要允许与ActiveX控件的交互
				</p>
			</div>
		</div>
	</div>
	[/@b.form]
	<div class="login_copyright">
		<span>&copy;1999-2013 <a href="http://www.zfsoft.com" target="_blank">正方软件股份有限公司</a> <span>版权所有</span>&nbsp;&nbsp;联系电话：0571-89902828</span><a class="ver" href="#">Ver 1.0.0</a>
	</div>
</div>
[@b.foot/]
