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
            url : "/system/course/showcx",
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
                        if(result.data.courseDO != null && result.data.courseDO.traceno!="" && result.data.courseDO.traceno!=null) {
                            farmHtml += "<table class=\"hovertable\" id=\"scxx\">" +
                                "<tbody><tr>" +
                                "    <th colspan=\"2\">课程基本信息";
                            if (result.data.courseDO.isdel == "1") {
                                farmHtml += "<label id=\"isdel\" style=\"color: red;\">(已删除)</label>";
                            }
                            farmHtml += "</th></tr>" +
                                "<tr onmouseover=\"this.style.backgroundColor='#ffff66';\" onmouseout=\"this.style.backgroundColor='#d4e3e5';\" style=\"background-color: rgb(212, 227, 229);\">" +
                                "    <td><label>溯源码：</label></td><td>" + result.data.traceno + "</td>" +
                                "</tr>" +
                                "<tr onmouseover=\"this.style.backgroundColor='#ffff66';\" onmouseout=\"this.style.backgroundColor='#d4e3e5';\" style=\"background-color: rgb(212, 227, 229);\">" +
                                "    <td><label>课程名称：</label></td><td>" + result.data.courseDO.name + "</td>" +
                                "</tr>" +
                                "<tr onmouseover=\"this.style.backgroundColor='#ffff66';\" onmouseout=\"this.style.backgroundColor='#d4e3e5';\" style=\"background-color: rgb(212, 227, 229);\">" +
                                "    <td><label>课程讲师：</label></td><td>" + result.data.courseDO.teacher + "("+result.data.courseDO.teacherno+")</td>" +
                                "</tr>" +
                                "<tr onmouseover=\"this.style.backgroundColor='#ffff66';\" onmouseout=\"this.style.backgroundColor='#d4e3e5';\" style=\"background-color: rgb(212, 227, 229);\">" +
                                "    <td><label>开课日期：</label></td><td>" + result.data.courseDO.startdate + "</td>" +
                                "</tr>" +
                                "<tr onmouseover=\"this.style.backgroundColor='#ffff66';\" onmouseout=\"this.style.backgroundColor='#d4e3e5';\" style=\"background-color: rgb(212, 227, 229);\">" +
                                "    <td><label>结课日期：</label></td><td>" + result.data.courseDO.enddate + "</td>" +
                                "</tr>" +
                                "<tr onmouseover=\"this.style.backgroundColor='#ffff66';\" onmouseout=\"this.style.backgroundColor='#d4e3e5';\" style=\"background-color: rgb(212, 227, 229);\">" +
                                "    <td><label>评估标准：</label></td><td>" + result.data.courseDO.standtname + "("+result.data.courseDO.standtraceno+")</td>" +
                                "</tr>" +
                                "<tr onmouseover=\"this.style.backgroundColor='#ffff66';\" onmouseout=\"this.style.backgroundColor='#d4e3e5';\" style=\"background-color: rgb(212, 227, 229);\">" +
                                "    <td colspan=\"2\"><label>>>>>>>>>>>>>>>>>>>课程得分情况>>>>>>>>>>>>>>>>>></label></td>" +
                                "</tr>" +
                                "<tr onmouseover=\"this.style.backgroundColor='#ffff66';\" onmouseout=\"this.style.backgroundColor='#d4e3e5';\" style=\"background-color: rgb(212, 227, 229);\">" +
                                "    <td><label>知识掌握水平：</label></td><td>" + result.data.courseDO.avgknowledgerlevel + "</td>" +
                                "</tr>" +
                                "<tr onmouseover=\"this.style.backgroundColor='#ffff66';\" onmouseout=\"this.style.backgroundColor='#d4e3e5';\" style=\"background-color: rgb(212, 227, 229);\">" +
                                "    <td><label>讲解清晰度：</label></td><td>" + result.data.courseDO.avgexplanation + "</td>" +
                                "</tr>" +
                                "<tr onmouseover=\"this.style.backgroundColor='#ffff66';\" onmouseout=\"this.style.backgroundColor='#d4e3e5';\" style=\"background-color: rgb(212, 227, 229);\">" +
                                "    <td><label>课后作业合适度：</label></td><td>" + result.data.courseDO.avghomeworklevel + "</td>" +
                                "</tr>" +
                                "<tr onmouseover=\"this.style.backgroundColor='#ffff66';\" onmouseout=\"this.style.backgroundColor='#d4e3e5';\" style=\"background-color: rgb(212, 227, 229);\">" +
                                "    <td><label>作业批改完成度：</label></td><td>" + result.data.courseDO.avghomeworkgrad + "</td>" +
                                "</tr>" +
                                "<tr onmouseover=\"this.style.backgroundColor='#ffff66';\" onmouseout=\"this.style.backgroundColor='#d4e3e5';\" style=\"background-color: rgb(212, 227, 229);\">" +
                                "    <td><label>教师课件质量：</label></td><td>" + result.data.courseDO.avgcourequa + "</td>" +
                                "</tr>" +
                                "<tr onmouseover=\"this.style.backgroundColor='#ffff66';\" onmouseout=\"this.style.backgroundColor='#d4e3e5';\" style=\"background-color: rgb(212, 227, 229);\">" +
                                "    <td><label>教学互动质量：</label></td><td>" + result.data.courseDO.avgteachqua + "</td>" +
                                "</tr>" +
                                "<tr onmouseover=\"this.style.backgroundColor='#ffff66';\" onmouseout=\"this.style.backgroundColor='#d4e3e5';\" style=\"background-color: rgb(212, 227, 229);\">" +
                                "    <td><label>课程满意度：</label></td><td>" + result.data.courseDO.avgcoursesatisfact + "</td>" +
                                "</tr>" +
                                "<tr onmouseover=\"this.style.backgroundColor='#ffff66';\" onmouseout=\"this.style.backgroundColor='#d4e3e5';\" style=\"background-color: rgb(212, 227, 229);\">" +
                                "    <td><label>课程平均得分：</label></td><td>" + result.data.courseDO.avgcourse + "</td>" +
                                "</tr></tbody></table><hr>";
                            if(result.data.courseDO.studentscoreDOList.length>0 && result.data.courseDO.studentscoreDOList!=null){
                                farmHtml +="<div style='width: 100%;margin: 0 auto;padding-bottom: 120px;margin-left: -40px'><table style='width: 100%;' class=\"hovertable\" id=\"shengchan\">" +
                                    "<tbody><tr>" +
                                    "<th colspan=\"9\">课程打分记录表<label id=\"isdel\"  name=\"isdel\"  style=\"color: red;\"></label></th>" +
                                    "</tr>" +
                                    "<tr onmouseover=\"this.style.backgroundColor='#ffff66';\" onmouseout=\"this.style.backgroundColor='#d4e3e5';\" style=\"background-color: rgb(212, 227, 229);\">" +
                                    "    <td>打分人</td><td>知识掌握水平</td><td>讲解清晰度</td><td>课后作业合适度</td><td>作业批改完成度</td><td>教师课件质量</td><td>教学互动质量</td><td>课程满意度</td><td>课程得分</td>" +
                                    "</tr>" ;
                                for(i in result.data.courseDO.studentscoreDOList){
                                    if(result.data.courseDO.studentscoreDOList[i].status=='1'){
                                        farmHtml+="<tr onmouseover=\"this.style.backgroundColor='#ffff66';\" onmouseout=\"this.style.backgroundColor='#d4e3e5';\" style=\"background-color: rgb(212, 227, 229);\">" +
                                            "<td><label id=\"kiwino1\">"+result.data.courseDO.studentscoreDOList[i].stuhash+"</label></td>" +
                                            "<td><label id=\"kiwino1\">"+result.data.courseDO.studentscoreDOList[i].pointknowledgerlevel+"</label></td>" +
                                            "<td><label id=\"kiwino1\">"+result.data.courseDO.studentscoreDOList[i].pointexplanation+"</label></td>" +
                                            "<td><label id=\"kiwino1\">"+result.data.courseDO.studentscoreDOList[i].pointhomeworklevel+"</label></td>" +
                                            "<td><label id=\"kiwino1\">"+result.data.courseDO.studentscoreDOList[i].pointhomeworkgrad+"</label></td>" +
                                            "<td><label id=\"kiwino1\">"+result.data.courseDO.studentscoreDOList[i].pointcourequa+"</label></td>" +
                                            "<td><label id=\"kiwino1\">"+result.data.courseDO.studentscoreDOList[i].pointteachqua+"</label></td>" +
                                            "<td><label id=\"kiwino1\">"+result.data.courseDO.studentscoreDOList[i].pointcoursesatisfact+"</label></td>" +
                                            "<td><label id=\"kiwino1\">"+result.data.courseDO.studentscoreDOList[i].pointcourse+"</label></td>" +
                                            "</tr>";
                                    }
                                }
                                farmHtml+="</tbody></table></div>";
                            }
                        }
                        // farmHtml +="</th></tr></tbody></table>";
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