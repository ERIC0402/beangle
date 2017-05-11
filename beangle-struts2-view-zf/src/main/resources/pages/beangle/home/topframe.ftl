[#ftl]
<div class="topframe">
	<div class="head">
		<div class="logo">
			<h2 class="floatleft"><a href="${b.url('/home')}"><img src="${base}/static/stylev4/logo/logo_school.png" /></a></h2>
			<h3 class="floatleft"><a href="${b.url('/home')}"><img src="${base}/static/stylev4/logo/logo_system.png" /></a></h3>
		</div>
		[#if user??]
		<div class="info">
			<div class="welcome">
				<div class="tool"  onmouseover="javascript:document.getElementById('downmenu').style.display='block'" onmouseout="javascript:document.getElementById('downmenu').style.display='none'">
					<a class="tool_btn" href="#" onclick="w.showhidediv('downmenu');" >${user.fullname}(${user.name})</a>
					<div class="downmenu" id="downmenu" style="display:none;">
						<a href="#" class="passw" onclick="w.modifyPassword(this)">修改密码</a><a href="${b.url('/security/my')}" target="_myinfo">个人资料</a>
					</div>
				</div>
				[#--
				<span>早上好！</span><a href="#" class="news">[新消息(9)]</a>
				--]
				<a href="${base}/logout.action" title="退出" class="logout"></a>
			</div>
		</div>
		[/#if]
	</div>
	<div class="menu">
		<div class="nav">
			<ul>
				[#list menus?if_exists as module]
				<li>
					<a href="${b.url('!menus?menu.id=${module.id}')}">[@c.i18nNameTitle module/]</a>
				</li>
				[/#list]
				[#--
				<li>
					<a href="demo_index_01.html">首页</a>
				</li>
				<li class="current">
					<a href="demo_lanmu01.html">基础模块范例</a>
				</li>
				--]
			</ul>
		</div>
		[#if user?? && (user.categories?size gt 1)]
		<div class="morediv" onmouseover="javascript:document.getElementById('morelist').style.display='block'" onmouseout="javascript:document.getElementById('morelist').style.display='none'">
			<p class="more" onclick="w.showhidediv('morelist');"></p>
			<div class="morelist" id="morelist" style="display:none;">
				<ul>
					<!--[if lte IE 6]><iframe class='navbug'></iframe><![endif]-->
					[#list user.categories as v]
						[#if v.id != categoryId]
							<li>
								<a href="${base}/home.action?security.categoryId=${v.id}">${v.title!}</a>
							</li>
						[/#if]
					[/#list]
				</ul>
			</div>
		</div>
		[/#if]
	</div>
	[#--
	<div class="notice" style="display:none;">
		<h3><span>系统帮助 </span></h3>
		<div>
			<a href="#">您正在使用的是正方软件设计参考标准第四版，内容更全。点击查看详细>></a>
		</div>
		<a class="close" title="隐藏"></a>
	</div>
	--]
</div>