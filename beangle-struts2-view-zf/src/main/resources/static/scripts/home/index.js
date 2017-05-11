function W(){
	this.modifyPassword = function (ele) {
		jQuery().colorbox(
		{
			iframe : true,
			width : "800px",
			height : "600px",
			href : base + "/security/password!edit.action"
		});
	}
	this.initNav = function (){
		$(".nav ul li a").click(function (){
			$(".nav .current").removeClass("current");
			$(this).parent().addClass("current");
			$(".location_level1").html(this.innerHTML);
			bg.ui.loading("leftmenfloatleft");
			$(".location .arrow, .location_level2, .location_level3").hide();
			return bg.Go(this,'left_menu',true);
		});
		if($(".nav .current").length == 0){
			$(".nav ul li a").first().click();
			$(".nav ul li").first().addClass("current");
		}
	}
	this.initMenus = function (){
		$(".hierarchy_03 li a").click(function (){
			bg.ui.loading("main");
			var h3 = $(this).parent().parent().prev("h3");
			$(".location_level2").html(h3.text());
			$(".location_level3").html($(this).text());
			$(".location .arrow, .location_level2, .location_level3").show();
		});
		$(".textlink h3").first().click();
	}
	this.showhidemenu = function(o,id)
	{
	    var sbtitle = this.getNext(o);
		if(sbtitle)
		{
	   		if(sbtitle.style.display=='block')
			{
	      	 	sbtitle.style.display = 'none';
	       		o.className = "open";
	   		}
			else
			{
	   			sbtitle.style.display = 'block';
	  			o.className = "close";
	   		}
		
			if(sbtitle.style.display=='none')
			{
	      	 	sbtitle.style.display = 'none';
	       		o.className = "open";
	   		}
			else
			{
	   			sbtitle.style.display = 'block';
	  			o.className = "close";
	   		}
			
		}
	}
	this.getNext = function(o)
	{
		while(o)
		{
			if(o.nextSibling.nodeType==1)
			{return o.nextSibling;}
			o=o.nextSibling;
		}
		return o;
	}	
	this.showhidediv = function(id)
	{
		var div = $("#" + id);
		if(div.css("display") == "none"){
			div.show();
		}else{
			div.hide();
		}
	}
	this.init = function (){
		var v = this;
		this.initNav();
	}
}
window.w = new W();
$(function (){
	w.init();
});