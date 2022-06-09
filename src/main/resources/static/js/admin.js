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
    $("#editusername").click(function (){
        var showname=$("input[name=showname]").val();
        var jsonob={'showname':showname};
        if(showname!=''){
            $.ajax({
                type: "POST",
                url: "/index/editshowname",
                dataType: 'json',
                contentType: 'application/x-www-form-urlencoded;charset=utf-8',
                data:jsonob,
                success: function (data) {
                    $('.ico-circular-user').click();
                }
            })
        }
        else {
            alert("昵称不能为空");
        }
    })

});
});


