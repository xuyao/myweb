<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>51电影：免费高清电影_免费电视剧下载_免费高清电影下载在线观看网站_51电影</title>
<!--#include virtual="/keyword.shtml"-->
<!--#include virtual="/css_js.shtml"-->
<link rel="stylesheet" type="text/css" href="/style/info.css"/>
<script src="/js/jquery.qrcode.min.js" type="text/javascript" charset="utf-8"></script>
<script src="/js/down.js" type="text/javascript" charset="utf-8"></script>
<script>
function showpic(index,url){
    if($(eval("pic"+index)).css('display')=='block'){
		$(eval("wx"+index)).html('');
		$(eval("pic"+index)).css('display','none');
		}else{
		$(eval("wx"+index)).qrcode({width:160,height:160,correctLevel:0,text:url});
		$(eval("pic"+index)).css('display','block');
    }}
</script>
<style>
.bluebuttoncss {
     font-family: "tahoma", "宋体";
     font-size: 10pt; color: #003366;
     border: 0px #93bee2 solid;
     border-bottom: #93bee2 1px solid;
     border-left: #93bee2 1px solid;
     border-right: #93bee2 1px solid;
     border-top: #93bee2 1px solid;*/
     background-image:url(../images/blue_button_bg.gif);
     background-color: #ffffff;
     cursor: hand;
     font-style: normal ;
}
</style>
</head>
<base target= _blank>
<body>
	<div class="navbar navbar-fixed-top" style="_position: relative;_z-index: 10000;">
		<div class="navbar-inner">
			<div class="container">
				<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</a>
				<div class="nav-collapse">
					<!--#include virtual="/nav.shtml" -->
				</div>
			</div>
		</div>
	</div>
    
    <div class="top-div">
	    <div class="container clearfix">
			<!--#include virtual="/i_s/adtop.shtml" -->
		</div>
	</div>

 	<div class="hr tp-div-nexthr" style="margin-top: 0;margin-bottom: 0;padding: 0;"></div>
	<div class="container pb-15">
	<!-- Example row of columns -->
	<div class="row">
		<div class="span12">
  	<div class="label-div b-0 border-all pt-5 t-0" style="position: relative; padding-left: 0;">
		<div class="view-main t-5 pl-25 pr-25">
	    	<div class="view-content" style="margin-top:18px;">
	<div style="clear:both;padding-top:18px;">
	    	<div style="clear:both;padding-top:18px;">
	    	<p><b>${type}：&nbsp</b></p>
			<!--#include virtual="/ad/content_ad_03.shtml" -->
			<p style='font-size:14px;color:#F00'>
			<table border="1" class="bluebuttoncss">
				<tr>
				<#list ll as item>
					<#if (item_index+1)%6==0>
						<td><a href='${item.html_url}'><u>${item.name}</u></a>&nbsp;&nbsp;</td><tr>
					<#else>
						<td><a href='${item.html_url}'><u>${item.name}</u></a>&nbsp;&nbsp;</td>
					</#if>
				</#list>
				</tr>
			</table>
			</p>
	    	</div>
	    	</div>
		</div> 
	  	<!--[if IE 6]> <a class="follow-btn ie6png mmmzw"></a><![endif]-->
	  	
	  	</div>
	</div>

	<div class="t-0 label-div border-all pr-5 pb-10 pl-20 clearfix">
	</div>
	</div>

	     <div class="span4">
				<!--#include virtual="/ad/info_r1.shtml" -->
				<!--#include virtual="/ad/info_ad_r1.shtml" -->
				<!--#include virtual="/ad/info_r2.shtml" -->
				<!--#include virtual="/ad/info_ad_r2.shtml" -->
		</div>
	</div>
</div>
</div>
<!--#include virtual="/i_s/footer.shtml" -->
</body>
</html>