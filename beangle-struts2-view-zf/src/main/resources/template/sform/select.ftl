[#ftl]
[#if tag.parameters["numode"]??]
<select name="${tag.name}">
	<option value="">请选择..</option>
	[#list tag.items as v]
		[#assign optionText]${tag.parameters["perfix"]!}${v}${tag.parameters["surfix"]!}[/#assign]
		<option [#if optionText == tag.value]selected[/#if]>${optionText}</option>
	[/#list]
</select>
[#else]
	<select id="${tag.id}" [#if tag.title??]title="${tag.title}"[/#if] name="${tag.name}"${tag.parameterString}>${tag.body}[#if tag.empty??]<option value="">${tag.empty}</option>[/#if][#list tag.items as item]<option value="${item[tag.keyName]}"[#if tag.isSelected(item)]selected="selected"[/#if]>${item[tag.valueName]!}</option>[/#list]</select>[#if tag.comment??]<label class="comment">${tag.comment}</label>[/#if]
[/#if]
