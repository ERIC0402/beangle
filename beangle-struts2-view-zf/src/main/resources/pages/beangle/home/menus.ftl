[#ftl]
<div class="typeleft floatleft" id="leftmenfloatleft">
		<script>
			$(function() {
				w.initMenus();
			})
		</script>
		[#list modules as m1 ]
      		[#if m1.parent?? && m1.parent.id == pmenu.id]
			<div class="textlink" id="">
				<h3 onclick="w.showhidemenu(this);" class="open"><span>[@c.i18nNameTitle m1/]</span></h3>
				<ul style="display:none" class="hierarchy_03">
			      	 	[#list modules as m2]
			      	 		[#if m2.entry?? && m2.code?starts_with(m1.code) && m1.code != m2.code]
								<li>[@b.a href="${(m2.entry)!}" target="main" class="none_03"]<span>[@c.i18nNameTitle m2/]</span>[/@]</a></li>
			      	 		[/#if]
			      	 	[/#list]
				</ul>
				</div>
	      	[/#if]
      	[/#list]
      	[#--
		<div class="textlink" id="">
			<h3 onclick="showhidediv(this);" class="open"><span>列表模块</span></h3>
			<ul style="display:none" class="hierarchy_03">
				<li>
					<a href="demo/demo_03.html" target="framecon" class="none_03"><span>标准列表</span></a>
				</li>
				<li>
					<a href="demo/demo_04.html" target="framecon" class="none_03"><span>新闻列表</span></a>
				</li>
				<li>
					<a href="demo/demo_05.html" target="framecon" class="none_03"><span>带审核的列表</span></a>
				</li>
				<li>
					<a href="demo/test.html" target="framecon" class="none_03"><span>表头固定的列表</span></a>
				</li>
				<li>
					<a href="demo/demo_48.html" target="framecon" class="none_03"><span>老版列表</span></a>
				</li>
				<li>
					<a href="demo/demo_33.html" target="framecon" class="none_03"><span>间隔线列表</span></a>
				</li>
				<li>
					<a href="demo/demo_36.html" target="framecon" class="none_03"><span>树型列表</span></a>
				</li>
				<li>
					<a href="demo/demo_48.html" target="framecon" class="none_03"><span>老版列表</span></a>
				</li>
				<li>
					<a href="demo/demo_67.html" target="framecon" class="none_03"><span>带查询条件的列表</span></a>
				</li>
			</ul>
		</div>
      	--]
	</div>
