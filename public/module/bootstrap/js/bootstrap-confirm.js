(function ($) {
    $.fn.extend({
        //pass the options variable to the function
        confirmModal: function (options) {
            var html = '<div class="modal" id="confirmContainer"><div class="modal-header"><a class="close" data-dismiss="modal">×</a>' +
            '<h3>#Heading#</h3></div><div class="modal-body">' +
            '#Body#</div><div class="modal-footer">' +
            '<a href="#" class="btn btn-primary" id="confirmYesBtn">确认</a>' +
            '<a href="#" class="btn" data-dismiss="modal" id="confirmCancelBtn">关闭</a></div></div>';

            var defaults = {
                heading: '请确认',
                body:'Body contents',
                callback : null,
                cancelcallback:null
            };
            
            var options = $.extend(defaults, options);
            html = html.replace('#Heading#',options.heading).replace('#Body#',options.body);
            $(this).html(html);
            $(this).modal('show');
            var context = $(this); 
            $('#confirmYesBtn',this).click(function(){
                if(options.callback!=null)
                    options.callback();
          
                $(context).modal('hide');
            });
            $('#confirmCancelBtn',this).click(function(){
                if(options.cancelcallback!=null)
                    options.cancelcallback();
                $(context).modal('hide');
            });
        }
    });

})(jQuery);