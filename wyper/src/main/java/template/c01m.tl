<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${title}</title>
<!--#include virtual="/keyword.shtml"--><!--#include virtual="/css_js.shtml"-->
<link rel="stylesheet" type="text/css" href="/style/info.css"/>
<script src="/js/jquery.qrcode.min.js" type="text/javascript" charset="utf-8"></script>
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
</head>
<body oncontextmenu="return false" ondragstart="return false" onselectstart="return false" onselect="document.selection.empty()" oncopy="document.selection.empty()" onbeforecopy="return false">
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
				<ul class="nav pull-right login-none nav-tips">
		<li class="ie6png divider-vertical" style="_width: 30px;_height: 40px;_background: url(images/icons.png) no-repeat 10px -106px;"><a class="dropdown-toggle clearfix" href="/login.html?url=aHR0cDovL3d3dy5iaW9kaXNjb3Zlci5jb20vbmV3cy5odG1sP3BhZ2U9Mg%3D%3D" style="padding: 7px 5px;"></a></li>
	</ul>
<div class="popup-div tips-div" style="position: absolute;z-index: 10000001;display: none;"></div>
<ul class="nav pull-right pl-20 myul">
</ul>
			</div>
		</div>
	</div>
    
    <div class="top-div">
    <div class="container clearfix">
		<!--#include virtual="/ad/m/adtop.shtml" -->
	</div>
	</div>
</div>
 	<div class="hr tp-div-nexthr" style="margin-top: 0;margin-bottom: 0;padding: 0;"></div>
	<div class="container pb-15">
	<!-- Example row of columns -->
	<div class="row">
		<div class="span12">
                        
  <div class="label-div b-30 border-all pt-5 t-20" style="position: relative; padding-left: 0;">
   <div style="margin-top: 14px;" class="clearfix pb-12 pl-25 pr-25">
						<h1 class="pull-left r-10">${title}</h1>
						<div class="pull-left pt-5 none-768" style="_margin-top:-33px; _margin-left:-10px;"> 
                        <span class="like-plug-gz clearfix pull-left r-15">
                        <span class="count pull-left t-5 d-none"></span>
                        </span>
                        </div>
	</div>
   
 
   
  <div style="margin-top: 4px;" class="clearfix pl-25 pr-25">
						<div class="pull-left">
				 
				<span class="r-15 pull-left"></span>
		</div> 
 </div> 
  
 <div class="view-main t-20 pl-25 pr-25">
 
    <div class="view-content" style="margin-top: 18px;">
				${content}
  </div>
  </div> 
   
  <!--[if IE 6]> <a class="follow-btn ie6png mmmzw"></a><![endif]--> 
</div>


<!---代码分享-->
<div class="t-10 label-div border-all pr-5   pb-10 pl-20 clearfix">
		<div class="share share-plug">
		<span style="height: 28px;line-height: 28px;color: #000;" class="pull-left r-10">分享到：</span>
			<div style="margin-top: 7px;" class="pull-left clearfix">
		    <a href="http://v.t.sina.com.cn/share/share.php?url=${url}&title=${title}" target="_blank" class="bio_button_sina pull-left ie6png r-10 ">新浪微博</a>
    		<a href="http://v.t.qq.com/share/share.php?url=${url}&title=${title}" target="_blank" class="bio_button_sina pull-left ie6png r-10 ">腾讯微博</a> 
			<a href="http://tieba.baidu.com/f/commit/share/openShareApi?url=${url}&title=hello&desc=&pic=" target="_blank" class="bio_button_sina pull-left ie6png r-10 ">百度贴吧</a>
			<a href="http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url=${url}" target="_blank" class="bio_button_sina pull-left ie6png r-10 ">QQ空间</a>
			<a class="bio_button_sina pull-left ie6png r-10" href="javascript:void(0)" onmouseover="showpic(0,'http://${urlm}');">
			微信扫一扫<div id="pic0" class="qr on" style="display:none"><br>
				<div id="wx0" style=" position:relative;z-index:99999;"/></div>
			</div></a> 
    	</div>
    	<div style="margin-top: 3px; margin-left: 20px;" class="pull-right">
    		<iframe width="136" scrolling="no" height="24" frameborder="0" src="http://widget.weibo.com/relationship/followbutton.php?language=zh_cn&amp;width=116&amp;height=24&amp;uid=2117966477&amp;style=2&amp;btn=red&amp;dpc=0" border="0" marginheight="0" marginwidth="0" allowtransparency="true"></iframe>
    	</div>					
    </div>
</div>
<!---代码分享-->

</div>
      <!--右侧代码-->  
       <div class="span4">
             <div class="t-20 mygz">
                 <dl>
				 <!--
                  <dd><p class="t">关注</p><p>1</p></dd>
                  <dd class="border"><p class="t">分享</p><p>0</p></dd>
                  <dd><p class="t">讨论</p><p>2</p></dd>
				  -->
                 <div class="mmclear"></div>                
                 </dl>
			</div>
        
			<!--#include virtual="/ad/m/info_r1.shtml" -->
			<!--#include virtual="/ad/m/info_ad_r1.shtml" -->
			<!--#include virtual="/ad/m/info_r2.shtml" -->
			<!--#include virtual="/ad/m/info_ad_r2.shtml" -->
		</div>
	</div>
</div>

<!--#include virtual="/ad/m/ad_footer.shtml" -->
</div>
<!--#include virtual="/i_s/footer.shtml" -->
</body>
</html>