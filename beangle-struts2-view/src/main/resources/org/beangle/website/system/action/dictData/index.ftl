[#ftl]
[@b.head/]
[#include "../nav.ftl"/]
[@b.form name="dictDataSearchForm"  action="!search" target="dictDatalist" title="ui.searchForm" theme="search"]
	[@b.textfields names="dictData.code;代码,dictData.name;名称,dictData.dictType.name;类型名称"/]
	[@b.select name="dictData.dictType.id" label="字典类型" value="" empty="..." items=dictTypes option="id,name"/]
	[@b.select name="dictData.enabled" label="common.status" value="" empty="..." items={'1':'启用','0':'禁用'}/]
[/@]
[@b.div id="dictDatalist" href="!search" /]
[@b.foot/]