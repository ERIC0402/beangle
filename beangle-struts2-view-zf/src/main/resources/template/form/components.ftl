[#ftl]
[#macro switch label="" name="" value=false notnull=false]
<input type="checkbox" [#if notnull]onclick="return false;" title="必填字段，不能取消。"[#else]onclick="$name('${name}').val(this.checked == true? 1 : 0);"[/#if] id="switch${name}" [#if value || notnull]checked="checked"[/#if]/>[#if label != ""]<label for="switch${name}">${label}</label>[/#if]
<input type="hidden" name="${name}" value="${(value || notnull)?string}"/>
[/#macro]
