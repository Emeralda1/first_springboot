var check=true
function signupcheck(){
    var uname=$("input[name=username]").val();
    var pwd=$("input[name=password]").val();
    var repwd=$("input[name=repassword]").val();
    var email=$("input[name=email]").val();
    var showname=$("input[name=showname]").val();
    var nullcheck=true;
    if(uname==''||pwd==''||repwd==''||email==''||showname==''){
        nullcheck=false;
        alert("请填写完整");
    }
    else nullcheck=true;
    if (pwd!=repwd) {$("input[name=repassword]").css('border-color','#ff6928');
        $("input[name=repassword]").parent().prev().find("span").eq(0).after
        ("<span className=\"text\" style=\"font-size: 12px\">两次密码输入不一致</span>");
    }
    if (pwd==repwd&&nullcheck&&check) {
        var user = {"username": uname, "password": pwd,"email": email, "showname": showname}
        $.ajax({
            type: "POST",
            url: "/index/signupcheck",
            data: user,
            dataType: 'json',
            contentType: 'application/x-www-form-urlencoded;charset=utf-8',
            success: function (data) {
                var arr = [];
                var i = 0;
                for (k in data) {
                    arr[i] = data[k];
                    i++;
                }
                if (arr[0] == 'true'&&arr[1]=='true') {
                    alert("注册成功")
                    $("#signupform").submit();
                }
                if (arr[0]=='false'){
                    $("input[name=username]").css('border-color','#ff6928');
                    $("input[name=username]").parent().prev().find("span").eq(0).after
                    ("<span className=\"text\" style=\"font-size: 12px\">此用户名已被注册</span>");
                }
                if (arr[1]=='false'){
                    $("input[name=email]").css('border-color','#ff6928');
                    $("input[name=email]").parent().prev().find("span").eq(0).after
                    ("<span className=\"text\" style=\"font-size: 12px\">此邮箱已被注册</span>");
                }
            }
        })
    }
}
$(document).ready(function (){
    $("input[name=repassword]").focus(function (){
        $(this).css('border-color','');
        $(this).parent().prev().find("span").eq(1).remove();
    })

    $("input[name=email]").focus(function(){
        $(this).css('border-color','');
        $(this).parent().prev().find("span").eq(1).remove();
    });
    $("input[name=email]").blur(function(){
        var email = $("input[name=email]").val();
        var myreg = /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
        if(myreg.test(email))
        {
        }
        else if(email == ""){
            $("input[name=email]").css('border-color','#ff6928');
            $("input[name=email]").parent().prev().find("span").eq(0).after
            ("<span className=\"text\" style=\"font-size: 12px\">请填写邮箱地址</span>");
            check=false
        }else if(!myreg.test(email)){
            $("input[name=email]").css('border-color','#ff6928');
            $("input[name=email]").parent().prev().find("span").eq(0).after
            ("<span className=\"text\" style=\"font-size: 12px\">请输入有效的邮箱地址</span>");
            check=false
        }

    });
    $("input[name=showname]").focus(function(){
        $(this).css('border-color','');
        $(this).parent().prev().find("span").eq(1).remove();
    });
    $("input[name=showname]").blur(function(){
        var showname=$("input[name=showname]").val();
        if(showname=''){
            $("input[name=showname]").css('border-color','#ff6928');
            $("input[name=showname]").parent().prev().find("span").eq(0).after
            ("<span className=\"text\" style=\"font-size: 12px\">昵称不能为空</span>");
            check=false
        }
    });
    $("input[name=username]").focus(function(){
        $(this).css('border-color','');
        $(this).parent().prev().find("span").eq(1).remove();
    });
    $("input[name=username]").blur(function(){
        var username=$("input[name=username]").val();
        var myreg=/^[a-zA-Z][a-zA-Z0-9_]{4,15}$/;
        if(!myreg.test(username)){
            $("input[name=username]").css('border-color','#ff6928');
            $("input[name=username]").parent().prev().find("span").eq(0).after
            ("<span className=\"text\" style=\"font-size: 12px\">用户名不合法</span>");
            check=false
        }
    });
    $("input[name=password]").focus(function(){
        $(this).css('border-color','');
        $(this).parent().prev().find("span").eq(1).remove();
    });
    $("input[name=password]").blur(function(){
        var password=$("input[name=password]").val();
        var myreg=/^(?=.*[0-9])(?=.*[a-zA-Z])(.{1,})$/;
        if(!myreg.test(password)){
            $("input[name=password]").css('border-color','#ff6928');
            $("input[name=password]").parent().prev().find("span").eq(0).after
            ("<span className=\"text\" style=\"font-size: 12px\">密码必须包含字母和数字</span>");
            check=false
        }
        else if (password.length<=8){
            $("input[name=password]").css('border-color','#ff6928');
            $("input[name=password]").parent().prev().find("span").eq(0).after
            ("<span className=\"text\" style=\"font-size: 12px\">密码过短</span>");
            check=false
        }
        else if (password.length>=16){
            $("input[name=password]").css('border-color','#ff6928');
            $("input[name=password]").parent().prev().find("span").eq(0).after
            ("<span className=\"text\" style=\"font-size: 12px\">密码过长</span>");
            check=false
        }
    });
})

