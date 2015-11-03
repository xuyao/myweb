<div class="label-main clearfix" style="padding-left: 16px;">
		<#list ll as item>
	      <div class="video-list b-30 pull-left pr-20">
	       		 <div class="clearfix">
	              <div class="video-img">
	                  <a href="${item.html_url}">
	                      <img class="lazyloadimg" src="${item.pic_url}" style="width:150px;height:203px"/>
	                    </a>
	               </div>
	           </div>
	            <p class="fc666 t-5" style="margin-bottom:0px; height:50px;">${item.name}</p>
	            <div class="clearfix t-10"></div>
	        </div>
		</#list>
</div> 