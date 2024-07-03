$().ready(function() {
    validateRule();
    document.onkeydown = function (event) {
        if (event.keyCode == 13) //回车键的键值为13
        {
            $("#open_show").click();
            return false;
        }
    };
});

$(function () {
    laydate({
        elem : '#birth'
    });
});
$("#open_show").click(function () {
    $('#show').css('display','none');
    if($("#openshow").valid()){
        $.ajax({
            cache : true,
            type : "POST",
            url : "/system/evalstandard/showcx",
            data : {
                'traceno' : $('#traceno').val()
            },
            async : false,
            error : function(request) {
                parent.layer.alert("Connection error");
            },
            success : function(result) {
                if (result.code == 200 && result.data!="") {
                    $('#show').css('display','block');
                    var htmlStr="";
                    if(result.data != null && result.data.traceno!="" && result.data.traceno!=null){
                        var farmHtml = "";
                        $('#show').css('display','block');
                            farmHtml += "<table class=\"hovertable\" id=\"scxx\">" +
                                "<tbody><tr>" +
                                "    <th colspan=\"2\">评估标准基本信息";
                            if (result.data.isdel == "1") {
                                farmHtml += "<label id=\"isdel\" style=\"color: red;\">(已删除)</label>";
                            }
                            farmHtml += "</th></tr>" +
                                "<tr onmouseover=\"this.style.backgroundColor='#ffff66';\" onmouseout=\"this.style.backgroundColor='#d4e3e5';\" style=\"background-color: rgb(212, 227, 229);\">" +
                                "    <td><label>评估标准编号：</label></td><td>" + result.data.traceno + "</td>" +
                                "</tr>" +
                                "<tr onmouseover=\"this.style.backgroundColor='#ffff66';\" onmouseout=\"this.style.backgroundColor='#d4e3e5';\" style=\"background-color: rgb(212, 227, 229);\">" +
                                "    <td><label>评估标准名称：</label></td><td>" + result.data.name + "</td>" +
                                "</tr>" +
                                "<tr onmouseover=\"this.style.backgroundColor='#ffff66';\" onmouseout=\"this.style.backgroundColor='#d4e3e5';\" style=\"background-color: rgb(212, 227, 229);\">" +
                                "    <td colspan='2'><label>>>>>>>>>>>>>>>>>评估标准权重指标分配>>>>>>>>>>>>>>>></label></td>" +
                                "</tr>" +
                                "<tr onmouseover=\"this.style.backgroundColor='#ffff66';\" onmouseout=\"this.style.backgroundColor='#d4e3e5';\" style=\"background-color: rgb(212, 227, 229);\">" +
                                "    <td><label>知识掌握水平：</label></td><td>" + result.data.knowledgerlevel + "</td>" +
                                "</tr>" +
                                "<tr onmouseover=\"this.style.backgroundColor='#ffff66';\" onmouseout=\"this.style.backgroundColor='#d4e3e5';\" style=\"background-color: rgb(212, 227, 229);\">" +
                                "    <td><label>讲解清晰度：</label></td><td>" + result.data.explanation + "</td>" +
                                "</tr>" +
                                "<tr onmouseover=\"this.style.backgroundColor='#ffff66';\" onmouseout=\"this.style.backgroundColor='#d4e3e5';\" style=\"background-color: rgb(212, 227, 229);\">" +
                                "    <td><label>课后作业合适度：</label></td><td>" + result.data.homeworklevel + "</td>" +
                                "</tr>" +
                                "<tr onmouseover=\"this.style.backgroundColor='#ffff66';\" onmouseout=\"this.style.backgroundColor='#d4e3e5';\" style=\"background-color: rgb(212, 227, 229);\">" +
                                "    <td><label>作业批改完成度：</label></td><td>" + result.data.homeworkgrad + "</td>" +
                                "</tr>" +
                                "<tr onmouseover=\"this.style.backgroundColor='#ffff66';\" onmouseout=\"this.style.backgroundColor='#d4e3e5';\" style=\"background-color: rgb(212, 227, 229);\">" +
                                "    <td><label>教师课件质量：</label></td><td>" + result.data.courequa + "</td>" +
                                "</tr>" +
                                "<tr onmouseover=\"this.style.backgroundColor='#ffff66';\" onmouseout=\"this.style.backgroundColor='#d4e3e5';\" style=\"background-color: rgb(212, 227, 229);\">" +
                                "    <td><label>教学互动质量：</label></td><td>" + result.data.teachqua + "</td>" +
                                "</tr>" +
                                "<tr onmouseover=\"this.style.backgroundColor='#ffff66';\" onmouseout=\"this.style.backgroundColor='#d4e3e5';\" style=\"background-color: rgb(212, 227, 229);\">" +
                                "    <td><label>课程满意度：</label></td><td>" + result.data.coursesatisfact + "</td>" +
                                "</tr></tbody></table><hr>";

                        farmHtml = farmHtml.replace(new RegExp("undefined", "gm"), "").replace(new RegExp("null","gm"),"");
                        htmlStr+=farmHtml;
                        document.getElementById('htmlStr').innerHTML =htmlStr.replace(new RegExp("undefined","gm"),"");
                    }
                    else{
                        alert('未查到信息，请输入正确的追溯码！');
                        $('#show').css('display','none');
                        htmlStr="";
                        return;
                    }
                    if(htmlStr=="" ||  result.data == null ||result.data=="" ){
                        alert('未查到信息，请输入正确的追溯码！');
                        $('#show').css('display','none');
                        htmlStr="";
                        return;
                    }
                    document.getElementById('htmlStr').innerHTML =htmlStr.replace(new RegExp("undefined","gm"),"");
                }else{
                    alert('未查到信息，请输入正确的追溯码！');
                    $('#show').css('display','none');
                    htmlStr="";
                }
            },
            error : function() {
                alert("异常！");
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
function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#openshow").validate({
        rules : {
            traceno : {
                required : true
            }
        },
        messages : {
            traceno : {
                required : icon + "请输入溯源编号"
            }
        }
    })
}