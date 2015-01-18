$ ( function ( ) {
    $(".title").click(function(){
        var parentLi = $(this).parent();
        if(parentLi.attr("class") == "active"){
            parentLi.removeClass("active");
        }else{
            $(".title").parent().removeClass("active");
            parentLi.addClass("active");
        }
    });

} ) ;

