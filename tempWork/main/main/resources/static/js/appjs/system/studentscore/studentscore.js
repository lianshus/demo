
var prefix = "/system/studentscore"
$(function() {
	load();
});

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/list", // 服务器数据的加载地址
						iconSize : 'outline',
						toolbar : '#exampleToolbar',
						striped : true, // 设置为true会有隔行变色效果
						dataType : "json", // 服务器返回的数据类型
						pagination : true, // 设置为true会在底部显示分页条
						singleSelect : false, // 设置为true将禁止多选
						pageSize : 10, // 如果设置了分页，每页数据条数
						pageNumber : 1, // 如果设置了分布，首页页码
						showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset,
					            searchName:$('#searchName').val()
							};
						},
						columns : [
								{
									checkbox : true
								},
																{
									field : 'stuhash', 
									title : '哈希值' 
								},
																{
									field : 'coursetraceno', 
									title : '课程编号' 
								},
																{
									field : 'coursename', 
									title : '课程名称' 
								},
																{
									field : 'teacher', 
									title : '课程讲师' 
								},
																{
									field : 'standtname', 
									title : '评估标准名称' 
								},
																{
									field : 'pointknowledgerlevel', 
									title : '知识掌握水平' 
								},
																{
									field : 'pointexplanation', 
									title : '讲解清晰度' 
								},
																{
									field : 'pointhomeworklevel', 
									title : '课后作业合适度' 
								},
																{
									field : 'pointhomeworkgrad', 
									title : '作业批改完成度' 
								},
																{
									field : 'pointcourequa', 
									title : '教师课件质量' 
								},
																{
									field : 'pointteachqua', 
									title : '教学互动质量' 
								},
																{
									field : 'pointcoursesatisfact', 
									title : '课程满意度' 
								},
																{
									field : 'pointcourse', 
									title : '课程得分' 
								},
																{
									field : 'status', 
									title : '是否打分' ,
									align : 'center',
									formatter : function(value, row, index) {
										if (value == '0') {
											return '<span class="label label-danger">未打分</span>';
										} else if (value == '1') {
											return '<span class="label label-primary">已打分</span>';
										}
									}
								},
																{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										if(row.status=='0'){
											return '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="打分" onclick="edit(\''
												+ row.id
												+ '\')"><i class="fa fa-edit"></i></a> ';
										}else if(row.status=='1'){
											return '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="验证打分"  mce_href="#" onclick="check(\''
											+ row.id
											+ '\')"><i class="fa fa-cloud-download"></i></a> ';
										}
									}
								} ]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function edit(id) {
	layer.open({
		type : 2,
		title : '课程打分',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '550px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}
function check(id) {
	layer.confirm('确定要到区块链验证此条打分记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/check",
			type : "post",
			data : {
				'id' : id
			},
			success : function(r) {
				if (r.code==0) {
					layer.alert(r.msg);
					reLoad();
				}else{
					layer.alert(r.msg);
				}
			}
		});
	})
}