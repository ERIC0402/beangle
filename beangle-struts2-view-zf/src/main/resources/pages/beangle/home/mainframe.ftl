[#ftl]
<div class="type_mainframe">
	[#--
	[@b.div id="left_menu" class="left_menu_p" style="margin-right:10px;" href="!menus?menu.id=${(menus?first.id)!}"/]
	--]
	[@b.div id="left_menu" class="left_menu_p" style="margin-right:10px;" /]
	<div  id="leftBotton" class="btn_hide_on">
		<button onclick="changeWin();return false;"></button>
	</div>
	<div id="rightBotton" class="btn_hide_off" style="display:none;">
		<button  onclick="changeWin();return false;"></button>
	</div>
	<div id="right" class="typeright floatright">
		<!--左边隐藏后class名切换成typeright_hidden-->
		<!--选项卡-->
		<div class="tab_cur">
			<p class="location">
				<span>您的当前位置:</span>&nbsp;<a class="location_level1"></a><label class="arrow">&nbsp;&gt;&gt;&nbsp;</label><a class="location_level2"></a><label class="arrow">&nbsp;&gt;&gt;&nbsp;</label><a class="location_level3"></a>
			</p>
		</div>
		<!--内容区主体-->
		<div id="main" class="typecon _ajax_target">
			[#include "welcome.ftl"/]
		</div>
	</div>
</div>
<script type="text/javascript">
	function changeWin() {
		if ($("#left_menu").attr('class') != "hide") {
			$("#left_menu").attr('class','hide');
			$("#left_menu").hide();
			$("#right").css('width',"99%");
			$("#leftBotton").css('display','none');
			$("#rightBotton").css('display','block');
			
		} else {
			$("#left_menu").attr('class','left_menu_p');
			$("#left_menu").show();
			$("#right").attr('class','typeright floatright');
			$("#right").css('width',"82%");
			$("#leftBotton").css('display','block');
			$("#rightBotton").css('display','none');
		}
	}
</script>