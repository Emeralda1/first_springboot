//----------------------------------------------------------------------------------function
$(document).ready(function (){
$(function(){
    //----------------------------------------------------------------------------------sider
    $('.sider-toggle').click(function(){
        $('.sider').toggle();
    });
    $('.sider-list li > a').click(function(){
        $('.sider-group').hide();
        if($(this).hasClass('active')){
            $(this).removeClass('active');
            $(this).next().hide();
        }else{
            $('.sider-list li > a').removeClass('active');
            $(this).addClass('active');
            $(this).next().show();
        };
    });

});
});


