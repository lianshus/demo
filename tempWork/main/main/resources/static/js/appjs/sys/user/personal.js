$(function () {
    laydate({
        elem : '#birth'
    });
});
$("#pwd_save").click(function () {
    if($("#modifyPwd").valid()){
        $.ajax({
            cache : true,
            type : "POST",
            url :"/sys/user/resetPwd",
            data : $('#modifyPwd').serialize(),
            async : false,
            error : function(request) {
                parent.laryer.alert("Connection error");
            },
            success : function(data) {
                if (data.code == 0) {
                    parent.layer.alert("更新密码成功");
                } else {
                    parent.layer.alert(data.msg)
                }
            }
        });
    }
});
$("#person_save").click(function () {
    var urlStr="/sys/user/updatePeronal";
    if($("#basicInfoForm").valid()){
        $.ajax({
            cache : true,
            type : "POST",
            url :urlStr,
            data : $('#basicInfoForm').serialize(),
            async : false,
            error : function(request) {
                parent.laryer.alert("Connection error");
            },
            success : function(data) {
                if (data.code == 0) {
                    parent.layer.alert("保存成功");
                } else {
                    parent.layer.alert(data.msg)
                }
            }
        });
    }
});
function getHobbyStr(){
    var hobbyStr ="";
    $(".hobby").each(function () {
        if($(this).is(":checked")){
            hobbyStr+=$(this).val()+";";
        }
    });
   return hobbyStr;
}