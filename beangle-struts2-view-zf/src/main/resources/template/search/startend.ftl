[#ftl/]
<div class="item item2">
<script type="text/javascript">beangle.ui.load("My97DatePicker");</script>
[#list tag.dates as date]
<label for="${date.id}">${date.label}[#if date_index==0]：[/#if]</label>
<input style="width: 32%;" type="text" id="${date.id}" [#if date.title??]title="${date.title}"[/#if] class="Wdate" onFocus="WdatePicker({dateFmt:'${tag.format}'[#if date.maxDate??],maxDate:'${date.maxDate}'[/#if][#if date.minDate??],minDate:'${date.minDate}'[/#if]})" name="${date.name}" value="${(date.value)?if_exists}" ${tag.parameterString}/>
[/#list]
</div>
