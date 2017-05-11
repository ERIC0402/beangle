[#ftl/]
[#assign slash=tag.parameters['slash']!2/]
[#if tag.id??]
<div class="ui-widget messages" id="${tag.id}" style="display: none;">
[#if tag.hasActionMessages()]
<div class="actionMessage">
	<div> 
		<p>
			[#list tag.actionMessages as message]
			<div style="line-height: 18px;">
				<span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span>
				${message!}
			</div>
			[/#list]
		</p>
	</div>
</div>
[/#if]
[#if tag.hasActionErrors()]
	[#assign messagePosition="center"/]
	[#assign messageTile="错误信息"/]
	[#if slash?string != "0"][#assign slash="100"/][/#if]
	<div class="actionError">
		<div> 
			<p>
				[#list tag.actionErrors as message]
					<div style="line-height: 18px;" class="ui-state-error">
					<span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span>
					${message!}
					</div>
				[/#list]
			</p>
		</div>
	</div>
[/#if]
</div>
[#if tag.parameters['textMode']??]
<script type="text/javascript">
$(function (){
	var label = $("<font>");
	label.text($("#${tag.id}").text());
	$("#${tag.id}").parent().append(label);
	$("#${tag.id}").remove();
});
</script>
[#elseif slash?string == "0"]
<script type="text/javascript">
$("#${tag.id}").show();
</script>
[#else]
<script type="text/javascript">
	bg.message("${tag.id}", "${(slash)!}", "${messageTile!'提示信息'}", "${messagePosition!'top'}");
	//setTimeout(function(){var msgdiv=document.getElementById('${tag.id}');if(msgdiv) msgdiv.style.display="none";},${(tag.parameters['slash'])!}*1000);
</script>
[/#if]
[/#if]