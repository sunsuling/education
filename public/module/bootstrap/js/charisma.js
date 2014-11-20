$(document).ready(function(){
	//themes, change CSS with JS
	//default theme(CSS) is cerulean, change it if needed
	var current_theme = $.cookie('current_theme')==null ? 'classic' :$.cookie('current_theme');
	switch_theme(current_theme);
	
	$('#themes a[data-value="'+current_theme+'"]').find('i').addClass('icon-ok');
				 
	$('#themes a').click(function(e){
		e.preventDefault();
		current_theme=$(this).attr('data-value');
		$.cookie('current_theme',current_theme,{expires:365});
		switch_theme(current_theme);
		$('#themes i').removeClass('icon-ok');
		$(this).find('i').addClass('icon-ok');
	});
	
	
	function switch_theme(theme_name)
	{

		$('#bs-css').attr('href','/assets/module/bootstrap/css/bootstrap-'+theme_name+'.css');
	}
	
	//ajax menu checkbox
	$('#is-ajax').click(function(e){
		$.cookie('is-ajax',$(this).prop('checked'),{expires:365});
	});
	$('#is-ajax').prop('checked',$.cookie('is-ajax')==='true' ? true : false);
	
	//disbaling some functions for Internet Explorer
	if($.browser.msie)
	{
		$('#is-ajax').prop('checked',false);
		$('#for-is-ajax').hide();
		$('#toggle-fullscreen').hide();
		$('.login-box').find('.input-large').removeClass('span10');
		
	}
	
	
	//highlight current / active link
	$('ul.main-menu li a').each(function(){
		if($($(this))[0].href==String(window.location))
			$(this).parent().addClass('active');
	});
	
	//establish history variables
	var
		History = window.History, // Note: We are using a capital H instead of a lower h
		State = History.getState(),
		$log = $('#log');

	//bind to State Change
	History.Adapter.bind(window,'statechange',function(){ // Note: We are using statechange instead of popstate
		var State = History.getState(); // Note: We are using History.getState() instead of event.state
		$.ajax({
			url:State.url,
			success:function(msg){
				$('#content').html($(msg).find('#content').html());
				$('#loading').remove();
				$('#content').fadeIn();
				docReady();
			}
		});
	});
	
	//ajaxify menus
	$('a.ajax-link').click(function(e){
		if($.browser.msie) e.which=1;
		if(e.which!=1 || !$('#is-ajax').prop('checked') || $(this).parent().hasClass('active')) return;
		e.preventDefault();
		if($('.btn-navbar').is(':visible'))
		{
			$('.btn-navbar').click();
		}
		$('#loading').remove();
		$('#content').fadeOut().parent().append('<div id="loading" class="center">Loading...<div class="center"></div></div>');
		var $clink=$(this);
		History.pushState(null, null, $clink.attr('href'));
		$('ul.main-menu li.active').removeClass('active');
		$clink.parent('li').addClass('active');	
	});
	
	//animating menus on hover
	$('ul.main-menu li:not(.nav-header)').hover(function(){
		$(this).animate({'margin-left':'+=5'},300);
	},
	function(){
		$(this).animate({'margin-left':'-=5'},300);
	});
	
	//other things to do on document ready, seperated for ajax calls
	docReady();
});
		
		
function docReady(){
	//prevent # links from moving to top
	$('a[href="#"][data-top!=true]').click(function(e){
		e.preventDefault();
	});
	
	//rich text editor
	$('.cleditor').cleditor({
	        width:        500, // width not including margins, borders or padding
	        height:       250, // height not including margins, borders or padding
	        controls:     // controls to add to the toolbar
	          "bold italic underline strikethrough subscript superscript | font size " +
	          "style | color highlight removeformat | bullets numbering | outdent " +
	          "indent | alignleft center alignright justify | undo redo | " +
	          "rule image link unlink | cut copy paste pastetext | print source",
	        colors:       // colors in the color popup
	          "FFF FCC FC9 FF9 FFC 9F9 9FF CFF CCF FCF " +
	          "CCC F66 F96 FF6 FF3 6F9 3FF 6FF 99F F9F " +
	          "BBB F00 F90 FC6 FF0 3F3 6CC 3CF 66C C6C " +
	          "999 C00 F60 FC3 FC0 3C0 0CC 36F 63F C3C " +
	          "666 900 C60 C93 990 090 399 33F 60C 939 " +
	          "333 600 930 963 660 060 366 009 339 636 " +
	          "000 300 630 633 330 030 033 006 309 303",
	        fonts:        // font names in the font popup
	        	'宋体,新宋体,仿宋,楷体,仿宋_GB2312,'+  
		        '楷体_GB2312,黑体,华文细黑,华文楷体,华文宋体,华文中宋,'+  
		        '华文仿宋,华文彩云,华文琥珀,华文隶书,华文行楷,华文新魏,'+  
		        '方正舒体,方正姚体,细明体,新细明体,微软雅黑,微软正黑,'+	
	          "Arial,Arial Black,Comic Sans MS,Courier New,Narrow,Garamond," +
	          "Georgia,Impact,Sans Serif,Serif,Tahoma,Trebuchet MS,Verdana",
	        sizes:        // sizes in the font size popup
	          "1,2,3,4,5,6,7",
	        styles:       // styles in the style popup
	          [["Paragraph", "<p>"], ["Header 1", "<h1>"], ["Header 2", "<h2>"],
	            ["Header 3", "<h3>"],  ["Header 4","<h4>"],  ["Header 5","<h5>"],
	            ["Header 6","<h6>"]],
	        useCSS:       false, // use CSS to style HTML when possible (not supported in ie)
	        docType:      // Document type contained within the editor
	          '<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">',
	        docCSSFile:   // CSS file used to style the document contained within the editor
	          "",
	        bodyStyle:    // style to assign to document body contained within the editor
	          "margin:4px; font:10pt Arial,Verdana; cursor:text"
	      });
		
		
		
	
	
	
	$.datepicker.regional['zh-CN'] = {  
		      clearText: '清除',  
		      clearStatus: '清除已选日期',  
		      closeText: '关闭',  
		      closeStatus: '不改变当前选择',  
		      prevText: '<上月',  
		      prevStatus: '显示上月',  
		      prevBigText: '<<',  
		      prevBigStatus: '显示上一年',  
		      nextText: '下月>',  
		      nextStatus: '显示下月',  
		      nextBigText: '>>',  
		      nextBigStatus: '显示下一年',  
		      currentText: '今天',  
		      currentStatus: '显示本月',  
		      monthNames: ['一月','二月','三月','四月','五月','六月', '七月','八月','九月','十月','十一月','十二月'],  
		      monthNamesShort: ['一','二','三','四','五','六', '七','八','九','十','十一','十二'],  
		      monthStatus: '选择月份',  
		      yearStatus: '选择年份',  
		      weekHeader: '周',  
		      weekStatus: '年内周次',  
		      dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  
		      dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],  
		      dayNamesMin: ['日','一','二','三','四','五','六'],  
		      dayStatus: '设置 DD 为一周起始',  
		      dateStatus: '选择 m月 d日, DD',  
		      dateFormat: 'yy-mm-dd',  
		      firstDay: 1,  
		      initStatus: '请选择日期',  
		      isRTL: false  
		    };  
	$.datepicker.setDefaults($.datepicker.regional['zh-CN']); 
	
	//datepicker
	$('.datepicker').datepicker({
		dateFormat:"yy-mm-dd",
		changeMonth: true,
		changeYear: true
	});
	$('.datepicker').datepicker("option", "dateFormat", "yy-mm-dd");
	
	//notifications
	$('.noty').click(function(e){
		e.preventDefault();
		var options = $.parseJSON($(this).attr('data-noty-options'));
		noty(options);
	});


	//uniform - styler for checkbox, radio and file input
	$("input:checkbox, input:radio, input:file").not('[data-no-uniform="true"],#uniform-is-ajax').uniform();

	//chosen - improves select
	$('[data-rel="chosen"],[rel="chosen"]').chosen();

	//tabs
	$('#myTab a:first').tab('show');
	$('#myTab a').click(function (e) {
	  e.preventDefault();
	  $(this).tab('show');
	});

	//makes elements soratble, elements that sort need to have id attribute to save the result
	$('.sortable').sortable({
		revert:true,
		cancel:'.btn,.box-content,.nav-header',
		update:function(event,ui){
			//line below gives the ids of elements, you can make ajax call here to save it to the database
			//console.log($(this).sortable('toArray'));
		}
	});

	//slider
	$('.slider').slider({range:true,values:[10,65]});

	//tooltip
	$('[rel="tooltip"],[data-rel="tooltip"]').tooltip({"placement":"bottom",delay: { show: 400, hide: 200 }});

	

	//popover
	$('[rel="popover"],[data-rel="popover"]').popover();

	

	



	//gallery controlls container animation
	$('ul.gallery li').hover(function(){
		$('img',this).fadeToggle(1000);
		$(this).find('.gallery-controls').remove();
		$(this).append('<div class="well gallery-controls">'+
							'<p><a href="#" class="gallery-edit btn"><i class="icon-edit"></i></a> <a href="#" class="gallery-delete btn"><i class="icon-remove"></i></a></p>'+
						'</div>');
		$(this).find('.gallery-controls').stop().animate({'margin-top':'-1'},400,'easeInQuint');
	},function(){
		$('img',this).fadeToggle(1000);
		$(this).find('.gallery-controls').stop().animate({'margin-top':'-30'},200,'easeInQuint',function(){
				$(this).remove();
		});
	});


	//gallery image controls example
	//gallery delete
	$('.thumbnails').on('click','.gallery-delete',function(e){
		e.preventDefault();
		//get image id
		//alert($(this).parents('.thumbnail').attr('id'));
		$(this).parents('.thumbnail').fadeOut();
	});
	//gallery edit
	$('.thumbnails').on('click','.gallery-edit',function(e){
		e.preventDefault();
		//get image id
		//alert($(this).parents('.thumbnail').attr('id'));
	});

	//gallery colorbox
	$('.thumbnail a').colorbox({rel:'thumbnail a', transition:"elastic", maxWidth:"95%", maxHeight:"95%"});

	//gallery fullscreen
	$('#toggle-fullscreen').button().click(function () {
		var button = $(this), root = document.documentElement;
		if (!button.hasClass('active')) {
			$('#thumbnails').addClass('modal-fullscreen');
			if (root.webkitRequestFullScreen) {
				root.webkitRequestFullScreen(
					window.Element.ALLOW_KEYBOARD_INPUT
				);
			} else if (root.mozRequestFullScreen) {
				root.mozRequestFullScreen();
			}
		} else {
			$('#thumbnails').removeClass('modal-fullscreen');
			(document.webkitCancelFullScreen ||
				document.mozCancelFullScreen ||
				$.noop).apply(document);
		}
	});

	//tour
	if($('.tour').length && typeof(tour)=='undefined')
	{
		var tour = new Tour();
		tour.addStep({
			element: ".span10:first", /* html element next to which the step popover should be shown */
			placement: "top",
			title: "Custom Tour", /* title of the popover */
			content: "You can create tour like this. Click Next." /* content of the popover */
		});
		tour.addStep({
			element: ".theme-container",
			placement: "left",
			title: "Themes",
			content: "You change your theme from here."
		});
		tour.addStep({
			element: "ul.main-menu a:first",
			title: "Dashboard",
			content: "This is your dashboard from here you will find highlights."
		});
		tour.addStep({
			element: "#for-is-ajax",
			title: "Ajax",
			content: "You can change if pages load with Ajax or not."
		});
		tour.addStep({
			element: ".top-nav a:first",
			placement: "bottom",
			title: "Visit Site",
			content: "Visit your front end from here."
		});
		
		tour.restart();
	}

	//datatable
	$('.datatable').dataTable({
			"sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span12'i><'span12 center'p>>",
			"sPaginationType": "bootstrap",
			"oLanguage": {
			"sLengthMenu": "_MENU_ records per page"
			}
		} );
	$('.btn-close').click(function(e){
		e.preventDefault();
		$(this).parent().parent().parent().fadeOut();
	});
	$('.btn-minimize').click(function(e){
		e.preventDefault();
		var $target = $(this).parent().parent().next('.box-content');
		if($target.is(':visible')) $('i',$(this)).removeClass('icon-chevron-up').addClass('icon-chevron-down');
		else 					   $('i',$(this)).removeClass('icon-chevron-down').addClass('icon-chevron-up');
		$target.slideToggle();
	});
	$('.btn-setting').click(function(e){
		e.preventDefault();
		$('#myModal').modal('show');
	});

}
