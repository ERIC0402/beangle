[#ftl/]
<div class="title_style_1 navmenu2" id="${tag.id}">
	<div class="top_tool_bar" style="float: none">
		${tag.body!}
	</div>
	<div class="ClearDiv"></div>
</div>
<script type="text/javascript">$("#${tag.id} .span_1").last().remove();</script>
[#--
<div [#if tag.id??] id="${tag.id}"[/#if]>${tag.body!}</div>
--]
[#--
<div [#if tag.id??] id="${tag.id}"[/#if] class="navmenu" ><ul>${tag.body!}</ul></div>
--]