/**
 *  fileds
 *  r:必填
 *  maxl:最多字符
 *  minl:最少字符
 *  max:最大值
 *  min:最小值
 *  space:提示框向右偏移像素数
 *  focus:当验证值与显示不一致时，指定提示元素ID
 */
var Validator2 = {
		emsg : {r:"不能为空",sr:"请选择"},
	    fs : {
	      "alpha":{reg:/^[a-zA-Z\.\-]*$/, msg:"格式不正确！"},// 字母和点减号
	      "gh":{reg:/^[a-zA-Z\d]*$/, msg:"请输入字母或数字！"},// 工号
	      "alphanum":{reg:/^\w+$/, msg:"格式不正确！"},  // 字符
	      "unsigned":{reg:/^\d+$/, msg:"请输入正整数！"}, // 正整数
	      "positiveInteger":{reg:/^[0-9]*[1-9][0-9]*$/, msg:"格式不正确！"}, // 可以0开头数字，包括以零开头的数字
	      "integer":{reg:/^[\+\-]?\d*$/, msg:"请输入一个整数！"},// 整数可以有+-号
	      "integer2":{reg:/^\d*$/, msg:"请输入一个没有正负号得整数！"},// 无符号整数
	      "real":{reg:/^[\+\-]?\d*\.?\d*$/, msg:"请输入一个实数！"},// 实数
	      "unsignedReal":{reg:/^\d*\.?\d*$/, msg:"格式不正确！"},// 无符号实数
	      "ration":{reg:/^\d*\.?\d*\%?$/, msg:"格式不正确！"},// 可以是百分数
	      "email":{reg:/^[\w-\.]+\@[\w\.-]+\.[a-z]{2,4}$/, msg:"请输入正确的邮箱格式！"},// 邮箱
	      "phone":{reg:/^([\d]{3,4}-)?[\d]{6,12}(\*[\d]{1,4})?$/, msg:"电话号码格式不正确！<br>如：021-99999999*2222"},// 固定电话
	      "mphone":{reg:/^[\d]{11}$/, msg:"请输入正确的手机号码！"},// 手机号
	      "zip":{reg:/^[\d]{6}$/, msg:"请输入正确的邮编号码！"},// 邮政编码
	      "lxdh":{reg:/^(([\d]{3,4}-)?[\d]{6,12}(\*[\d]{1,4})?)$|^[\d]{11}$/, msg:"电话号码格式不正确！<br>如：021-99999999或13000000000"},// 固定电话手机号
	      "money":{reg:/^[\d]+(\.\d{1,2})?$/, msg:"请输入正确的金额！如：12.98"},
	      "score":{reg:/^\d+$/, msg:"请输入正确的分数！如：81"},
	      "year":{reg:/^19[\d]{2}$|^20[\d]{2}$/, msg:"请输入正确的年份格式1900-2099！"},
	      "month":{reg:/^[1-9]$|^1[012]$/, msg:"请输入正确的月份！<br/>如：1-12"},
	      "salaryField":{reg:/^[a][0][1-9]$|^[a][1234][\d]$|^[a][5][0]$/, msg:"请输入正确的格式！<br/>如：a01"}, // a01-a50
	      "gs":{reg:/^([1-9]+[\d]*(\.[05])?$)|^0.5$/, msg:"请输入非0的0.5的整倍数！<br/>如：0.5，1.0，1.5"},
	      "hour":{reg:/^0$|^(0)?[1-9]$|^1[0-9]$|^2[0-3]$/, msg:"请输入小时数0-23"},
	      "minute":{reg:/^0$|^(0)?[1-9]$|^[1-5][0-9]$/, msg:"请输入分钟数0-59"},
	      "resource":{reg:/^([a-zA-Z\d]+[\/])?[a-zA-Z\d]+$/, msg:"请输入正确的资源，如abc/edf！"},// 资源
	      "entry":{reg:/^([a-zA-Z\d]+[\/])?[a-zA-Z\d]+\.action(\?.*)?$/, msg:"请输入正确的入口，如abc/edf.action！"},// 入口
	      "dictCode":{reg:/^([A-Z\d]+[_])*[A-Z\d]+$/, msg:"请输入正确的字典代码，由大写字母、数字和下划线组成，如ABC_DEF_G01！"},
	      "ipfw":{reg:/^[\d\.\-,]*$/, msg:"IP范围用-分隔，多个条件用,号分隔<br>如：如192.168.0.12-192.168.0.200,172.28.0.10"},// 入口
	      "zzjgdm":{reg:/^[\d]{8}-[\d]$/, msg:"请输入正确的机构代码<br>如：65235468-9"},
	      "number":{reg:/^\d+$/, msg:"请输入正整数！"},
	      "numbers":{reg:/^([\d]+,)*[\d]+$/, msg:"请输入一个或多个数字，以','隔开."},
	      "varName": {reg:/^[a-zA-Z_]*$/, msg:"请输入正确的格式，只能包含大小写字母与下划线"},
  		  "sfz":{reg:/^[\d]{15}$|^[\d]{18}$|^[\d]{17}[X]$/, msg:"身份证号码应为15、18位或者17位+X！"},
	      "hanzi":{reg:/^[\u4e00-\u9fa5]+$/ ,msg:"只能输入汉字"},
	      "feihanzi":{reg:/^[^\u4e00-\u9fa5]+$/,msg:"只能输入非汉字"}
	    },
	    method : {
    		"r" : function (value, element, filed, v){
    			if (/^[\s　]*$/.test(value)) {if (element.attr("tagName") == "SELECT") {return v.emsg["sr"];} else {return v.emsg["r"];}}
    		},
    		"f" : function (value, element, filed, v){
    			if(value == "" && !filed.r){return "";}
    			var f = v.fs[filed["f"]];
    			if (f) {if (!f["reg"].exec(value)) {return f["msg"];}}
    		},
    		"reg" : function (value, element, filed, v){
    			var reg = filed["reg"];
    			if (!reg.exec(value)) {
    				var msg = filed["msg"];
    				if (!msg) {
    					msg = "格式错误！";
    				}
    				return msg;
    			}
    		},
    		"maxl" : function (value, element, filed, v){
    			if (element[0].tagName.toLowerCase() == "textarea") {
    				element.keyup(function() {
    					if (value.length > filed["maxl"]) {
    						element.val(value.substring(0, filed["maxl"]));
    					}
    				});
    			}
    			if (!filed["l"]) {
    				filed["l"] = "最多输入" + filed["maxl"] + "个字符";
    			}
    			if (value.length > filed["maxl"] * 1) {
    				return "字符长度不能大于" + filed["maxl"] + "<br>当前" + value.length + "个字符";
    			}
    		},
    		"minl" : function (value, element, filed, v){
    			if (value.length < filed["minl"] * 1) {
    				return "字符长度不能小于" + filed["minl"] + "个字符。";
    			}
    		},
    		"max" : function (value, element, filed, v){
    			value = value * 1;
    			if (value > filed["max"]) {
    				return "数值不能大于" + filed["mx"];
    			}
    		},
    		"min" : function (value, element, filed, v){
    			value = value * 1;
    			if (value < filed["min"]) {
    				return "数值不能小于" + filed["min"];
    			}
    		},
    		"same" : function(value, element, field, v){
    			var target = $name(field.same);
    			if(target.val() != value){
    				return "两次输入不一致。";
    			}
    		},
    		//"sfz":{reg:/^[\d]{15}$|^[\d]{18}$|^[\d]{17}[X]$/, msg:"身份证号码应为15、18位或者17位+X！"},
    		"sfz" : function(value, element, field, v){
    			var errormsg = "身份证号码格式错误！";
    			if(!/^[\d]{15}$|^[\d]{18}$|^[\d]{17}[X]$/.test(value)){
    				return errormsg;
    			}
    			var sum = 0;
    			for(var i = 1; i <= 17; i++){
    				sum += (Math.pow(2,18-i) % 11) * parseInt(value.charAt(i-1),11);
    			}
    			sum %= 11;
    			var valideCode  = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ];
    			sum = valideCode [sum];
    			sum = sum == 10 ? sum = "X" : sum + "";
    			var last = value.charAt(17).toUpperCase();
    			if(sum != last){
    				return "<label title='"+sum+"'>身份证号码输入有误，请仔细核对一下。</label>" ;
    			}
    		}
    },
    addMethod : function(name, fn, msg){
    	Validator2.method[name] = fn;
    	Validator2.emsg[name] = msg;
    }
}
function Validator2Str(){
	this.config  = {};
	this.required = function(){this.config.r = true;return this;}
	this.require = this.required;
	this.comment = function(str){this.config.l = str;return this;}
	this.match = function(format){this.config.f = format;return this;}
	this.range = function(min, max){this.config.min = min;this.config.max = max;return this;}
	this.maxl = function(maxl){this.config.maxl = maxl;return this;}
	this.minl = function(minl){this.config.minl = minl;return this;}
	this.select2 = function(select2){this.config.select2 = select2;return this;}
	this.same = function(name){this.config.same = name;return this;}
	this.sfz = function(){this.config.sfz = "1";this.config.l = "身份证号码应为15、18位或者17位+X！";return this;}
	this.getConfig = function(){return this.config;}
}
function validator2(fileds, formId){
	this.form = $(formId);
    this.fileds = fileds;
    this.divs={};
    this.emsg = Validator2.emsg;
    this.fs = Validator2.fs;
    this.method = Validator2.method;
    /**
	 * 主验证方法 r/required:是否必填 f:验证格式 min:最小字符数 max:最大字符数 reg:自定义验证表达式 msg:自定义验证表达式提示信息
	 */
	this.check = function(name) {
		if (name == "name")
			return "";
		var filed = this.fileds[name];
		var element = this.getElement(name);
		var value = this.getElementValue(element);
		for(var n in filed){
			if(this.method[n]){
				var msg = this.method[n](value, element, filed, this);
				if(msg && msg != ""){
					return msg;
				}
			}
		}
		return "";
	}
    this.showTips = new VShowTips();
    this.showTip = function(name) {
		var v = this;
		var filed = this.fileds[name];
		filed.name = name;
		var input = this.getElement(name);
	
		if (input.length == 0 || (input.attr("type") && input.attr("type").toUpperCase() == "RADIO")) {//单选框没有提示信息
			return;
		}
		if (!filed["space"]) {
			filed.space = 15;
		}
		if (filed["mx"]) {
			
		}
		var div = this.showTips.getTipsDiv("");
		//$("#main").append(div);
		//this.form.append(div);
		v.divs[name] = div;
		if (input.length == 0) {
			filed.checked = true;
		}
		v.showTips.setPosistion(filed, div, v);
		input.bind("focus", function() {
			div.css("z-index",99);
			var cont = div.find(".cont");
			if (filed["l"]) {
				cont.html(filed["l"]);//cont.html("提示：" + filed["l"]);
				cont.removeClass("error");
				var css = {};
				if (filed["width"]) {
					css = {
						width : filed["width"]
					};
				}
				v.showTips.space = filed.space;
				v.showTips.setPosistion(filed, div, v, css);
				div.fadeIn("fast");
			} else {
				div.fadeOut("fast");
				input.parent().find(".span_validator2").remove();
			}
		});
		input.bind("blur", function() {
			v.doCheck(name)
		});
	};
	this.entityName = function (){
        var fileds = this.fileds;
        for(var name in fileds){
            if(name.indexOf(".") > 0){
                return name.substring(0, name.indexOf("."));
            }
        }
    }
	
    this.doCheck = function(name){
        var v = this;
        var filed = v.fileds[name];
        if(!filed){
            return
        }
        var div = v.divs[name];
        var cont = div.find(".cont");
        var input = this.getElement(name);
        v.showTips.space = filed.space;
        v.showTips.setPosistion(filed, div, v);
        var msg = v.check(name);
        if(msg == ""){
            filed.checked = true;
            cont.removeClass("error");
            div.fadeOut("fast");
			input.parent().find(".span_validator2").remove();
        }else{
        	filed.checked = false;
        	cont.addClass("error");
        	cont.html(msg);
            v.showTips.setPosistion(filed, div, v);
            //div.css("z-index",100);
            div.show();
        }
    }
    this.exec = function(){
        var v = this;
        var fileds = this.fileds;
        var pass = true;
        var msgs = "";
        for(var name in fileds){
            var msg = v.check(name);
            if(msg != ""){
                pass = false;
            }
            if(msg.indexOf("show:", 0) == 0){
                msgs += msg.substr(5, msg.length) + "\n";
            }
            var input = this.getElement(name);
            input.blur();
        }
        if(msgs != "" || !pass){
            msgs += "表单填写有误，请检查后再提交！";
            if(v.reset){
                v.reset = false;
            }else{
                alert(msgs);
            }
        }
        return pass;
    }

    this.getElement = function(name){
    	var input = null;
        if(name.indexOf("#") == 0){
        	input = $(name);
        }else{
        	input = $("[name='" + name+"']");
        }
        return input;
    }
    
    this.getElementValue = function(element){
    	var value = element.val();
    	return value;
    }

    this.init = function(){
        var fileds = this.fileds;
        for(var name in fileds){
        	if(name == "name")continue;
        	var field = fileds[name];
        	if(field.required){
        		field.r = field.required;
        	}
        	if(field.maxlength){
        		field.max = field.maxlength;
        	}
            this.showTip(name)
        }
    }
    this.init();
}

function VShowTips(){
    this.space = 15;
    this.setPosistion = function (filed, div, v, css){
        try{
            var input = null;
            if(filed.focus){
                input = v.getElement(filed.focus);
            }else{
                input = v.getElement(filed.name);;
            }
            var span = $("<span class='span_validator2'>");
            div.find(".error").length > 0 && (span.css("color", "red"));
            span.html(div.text());
            input.parent().find(".span_validator2").remove();
            input.parent().append(span);
            return;
            var s = "x:" + (input.offset().top - 5) + "   y:" + (input.offset().left + input.width() + 50);
            s += "\n" + div.css("position");
            div.css("position","absolute");
            $("#r").html(s);
            var top = input.offset().top;
            if(!$.browser.msie || $.browser.version == '8.0' || $.browser.version == '9.0'){
                top -=5;
            }
            if(css){
                div.css(css);
            }
            var left = input.offset().left + input.width() + this.space;
            if($(".typeright").length > 0){
            }
            //left -= $(".itoolbar_validator").next().offset().left;
            //top -= $(".itoolbar_validator").next().offset().top;
            //top-=45;
            div.css("top", top);
            div.css("left", left);
        }catch(e){}
    }

    this.getTipsDiv = function (msg){
        var s = '<div style="DISPLAY: none" class="validator_info"><div class="arr"></div><div class="info-pop-t"><b class="cr-l"></b><b class="cr-r"></b></div><div class="info-pop-c"><div class="cont">';
        s += msg;
        s += '</div></div><div class="info-pop-b"><b class="cr-l"></b><b class="cr-r"></b></div></div>';
        var div = $(s);
        return div;
    }
}
Validator2.addMethod("select2", function(value, element, param) {
	bg.select.selectAll(element.get(0));
});