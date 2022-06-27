$(document).ready(function (){
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
    $("span[name=exp]").each(function () {
        var level=$(this).text()
        var levellist=[5,50,150,150,250,350,600,800,1000,1500,2000,2500,3000,3500,4000,5000,999999]
        for (var i=1;i<levellist.length+1;i++){
            if (parseInt(level)<=levellist[i-1]){
                level=i;
                break;
            }
        }
        $("span[name=gap]").text(eval(levellist[i]-$(this).text())+"经验值")
        $(this).text(level)
    })
})