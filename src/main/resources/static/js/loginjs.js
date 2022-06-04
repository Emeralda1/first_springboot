function logincheck(){
        var uname=$("input[name=username]").val();
        var pwd=$("input[name=password]").val();
        var user={"username":uname,"password":pwd}
        $.ajax({
            type:"POST",
            url:"/index/logincheck",
            data:user,
            dataType:'json',
            contentType : 'application/x-www-form-urlencoded;charset=utf-8',
            success:function (data){
                var arr=[];
                var i=0;
                for (k in data){
                    arr[i]=data[k];
                    i++;
                }
                if(arr[0]=='true'){
                    $("#loginform").submit();
                }
                else alert("账号或密码错误")
            }
    })
}