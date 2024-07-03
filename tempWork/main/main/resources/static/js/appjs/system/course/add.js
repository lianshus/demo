$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});

function getCheckedRoles() {
	var adIds = "";
	$("input:checkbox[name=student]:checked").each(function(i) {
		if (0 == i) {
			adIds = $(this).val();
		} else {
			adIds += ("," + $(this).val());
		}
	});
	return adIds;
}

function save() {
	var stu = getCheckedRoles();
	if(stu==""){
		alert("请选择学生！");
		return;
	}
	$("#students").val(stu);

	var standid = $("#selectEvalstandard option:selected").val();
	var standtname = $("#selectEvalstandard option:selected").text();
	if(standid=="" || standtname==""){
		alert("请选择评估标准！");
		return;
	}

	$.ajax({
		cache : true,
		type : "POST",
		url : "/system/course/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "不能为空"
			}
		}
	})
}

function queryEvalstandard(){
	if($("#selectEvalstandard").find("option:selected").val()!="" && $("#selectEvalstandard").find("option:selected").val()!=undefined){
		document.getElementById('standid').value=$("#selectEvalstandard").find("option:selected").val();
		document.getElementById('standtname').value=$("#selectEvalstandard").find("option:selected").text();
		debugger;
		document.getElementById('standtraceno').value=$("#selectEvalstandard").find("option:selected").attr("traceno");
	}else{
		document.getElementById('standid').value="";
		document.getElementById('standtname').value="";
		document.getElementById('standtraceno').value="";
	}
}