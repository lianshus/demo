$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/system/evalstandard/update",
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
			,
			knowledgerlevel : {
				required : true
			}
			,
			explanation : {
				required : true
			}
			,
			homeworklevel : {
				required : true
			}
			,
			homeworkgrad : {
				required : true
			}
			,
			courequa : {
				required : true
			}
			,
			teachqua : {
				required : true
			}
			,
			coursesatisfact : {
				required : true
			}

		},
		messages : {
			name : {
				required : icon + "不能为空"
			}
			,
			knowledgerlevel : {
				required : icon + "不能为空"
			}
			,
			explanation : {
				required : icon + "不能为空"
			}
			,
			homeworklevel : {
				required : icon + "不能为空"
			}
			,
			homeworkgrad : {
				required : icon + "不能为空"
			}
			,
			courequa : {
				required : icon + "不能为空"
			}
			,
			teachqua : {
				required : icon + "不能为空"
			}
			,
			coursesatisfact : {
				required : icon + "不能为空"
			}
		}
	})
}