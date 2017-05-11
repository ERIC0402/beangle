[#ftl/]
[#--
<div id="${tag.id}"></div>
<script type="text/javascript">
bar = bg.ui.toolbar("${tag.id}"[#if tag.title??],'${tag.title?replace("'","\"")}'[/#if]);
${tag.body}
bar.addHr();
</script>
--]
<script type="text/javascript">//${tag.parameters}
$(function (){
bar = bg.ui.toolbar("${tag.id}"[#if tag.title??],'${tag.title?replace("'","\"")}'[/#if]);
${tag.body}
//bar.addClearDiv();
});
</script>
<div id="${tag.id}" class="title_style_1 itoolbar_validator"></div>