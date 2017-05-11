[#ftl]
[#if !(request.getHeader('x-requested-with')??) && !Parameters['x-requested-with']??]
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="${locale.language}" xml:lang="${locale.language}">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	[#--<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />--]
	<meta http-equiv="Content-Language" content="UTF-8" />
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>
	<meta http-equiv="content-style-type" content="text/css"/>
	<meta http-equiv="content-script-type" content="text/javascript"/>
	[#--
	<meta content="all" name="robots" />
	<meta name="author" content="作者信息" />
	<meta name="Copyright" content="版权信息" />
	<meta name="description" content="站点介绍" />
	<meta name="keywords" content="站点关键词" />
	<link rel="stylesheet" href="${base}/static/stylev4/css/public.css" type="text/css" media="all" />
	<link rel="stylesheet" href="${base}/static/stylev4/css/module.css" type="text/css" media="all" />
	<link rel="stylesheet" href="${base}/static/stylev4/css/skin_blue.css" type="text/css" media="all" />
	--]
	<title>[#if tag.parameters['title']??]${tag.parameters['title']} - [/#if]${(systemVersion.name)!} ${(systemVersion.version)!}</title>
	<script type="text/javascript">var base = "${base!}", contextPath = "${base!}", JSESSIONID = "${Session['id']!}";</script>
	<script type="text/javascript" src="${base}/static/scripts/comm/jquery-latest.js"></script>
	<script type="text/javascript" src="${base}/static/scripts/history/json2.js"></script>
	<script type="text/javascript" src="${base}/static/scripts/jquery-ui/jquery-ui-1.9.0.custom.min.js"></script>
	<link id="jquery_theme_link" rel="stylesheet" href="${base}/static/scripts/jquery-ui/custom-theme/jquery-ui-1.9.0.custom.css" type="text/css"/>
	<script type="text/javascript" src="${base}/struts/js/plugins/jquery.subscribe.js"></script>
	<script type="text/javascript" src="${base}/static/scripts/history/history.adapter.jquery.min.js"></script>
	<script type="text/javascript" src="${base}/static/scripts/comm/jquery.struts2-3.1.0.min.js"></script>
	<script type="text/javascript" src="${base}/static/scripts/history/history.js"></script>
	<script type="text/javascript" src="${base}/static/scripts/history/history.html4.js"></script>
	<script type="text/javascript" src="${base}/static/scripts/beangle/beangle-2.4.6.js"></script>
	<script type="text/javascript" src="${base}/static/scripts/beangle/beangle-ui-2.4.6.js"></script>
	<link id="beangle_theme_link" href="${base}/static/themes/${b.theme.ui}/beangle-ui.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${base}/static/scripts/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${base}/static/scripts/colorbox/jquery-colorbox-1.3.17.1.js"></script>
	<link href="${base}/static/scripts/colorbox/colorbox.css" rel="stylesheet" type="text/css" />
	[#--
	<script type="text/javascript" src="${base}/static/scripts/comm/jquery.blockUI.js"></script>
	<script type="text/javascript" src="${base}/static/scripts/my97/WdatePicker-4.72.js"></script>
	--]
	<link rel="stylesheet" href="${base}/static/stylev4/css/public.css" type="text/css" media="all" />
	<link rel="stylesheet" href="${base}/static/stylev4/css/module.css" type="text/css" media="all" />
	<link rel="stylesheet" href="${base}/static/stylev4/css/skin_blue.css" type="text/css" media="all" />
	<link rel="shortcut icon" href="${base}/static/stylev4/logo/favicon.ico" type="image/x-icon" />
	[#--
	<link href="${base}/static/themes/${b.theme.ui}/style_comm.css" rel="stylesheet" type="text/css" />
	<link href="${base}/static/themes/${b.theme.ui}/main.css" rel="stylesheet" type="text/css" />
	--]
	<script type="text/javascript">
	jQuery(document).ready(function () {jQuery.scriptPath = "${base}/struts/";jQuery.struts2_jquery.minSuffix = "";	jQuery.ajaxSettings.traditional = true;jQuery.ajaxSetup ({cache: false});});
	</script>
	${tag.body}
	<!--[if IE 6]>
	<script src="${base}/static/scripts/comm/ie6comm.js"></script>
	<script>
	DD_belatedPNG.fix('img');
	</script>
	<![endif]-->
</head>
<body class="${tag.parameters['bodyClass']!}">
[/#if]
