package com.student.system.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;


/**
 * 课程信息
 */
public class CourseDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//课程编号
	private String traceno;
	//课程名称
	private String name;
	//课程描述
	private String note;
	//课程讲师编号
	private String teacherno;
	//课程讲师
	private String teacher;
	//开课日期
	private String startdate;
	//结课日期
	private String enddate;
	//评估标准id
	private Long standid;
	//评估标准编号
	private String standtraceno;
	//评估标准名称
	private String standtname;
	//知识掌握水平
	private Double avgknowledgerlevel;
	//讲解清晰度
	private Double avgexplanation;
	//课后作业合适度
	private Double avghomeworklevel;
	//作业批改完成度
	private Double avghomeworkgrad;
	//教师课件质量
	private Double avgcourequa;
	//教学互动质量
	private Double avgteachqua;
	//课程满意度
	private Double avgcoursesatisfact;
	//课程平均得分
	private Double avgcourse;
	//是否删除0否1是
	private String isdel;
	//课程创建时间
	private String createtime;
	//操作人
	private String createuser;
	//创建人姓名
	private String createname;
	//更新时间
	private String updatetime;
	//更新人
	private String updateuser;
	//更新人姓名
	private String updatename;
	private String block_key;
	List<String> students;
	private List<StudentscoreDO> studentscoreDOList;
	public String getBlock_key() {
		return block_key;
	}

	public List<String> getStudents() {
		return students;
	}

	public void setStudents(List<String> students) {
		this.students = students;
	}

	public void setBlock_key(String block_key) {
		this.block_key = block_key;
	}
	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	public static CourseDO fromJSON(String json) {
		Gson gson = new Gson();
		return gson.fromJson(json, CourseDO.class);
	}
	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：课程编号
	 */
	public void setTraceno(String traceno) {
		this.traceno = traceno;
	}
	/**
	 * 获取：课程编号
	 */
	public String getTraceno() {
		return traceno;
	}
	/**
	 * 设置：课程名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：课程名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：课程描述
	 */
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * 获取：课程描述
	 */
	public String getNote() {
		return note;
	}
	/**
	 * 设置：课程讲师编号
	 */
	public void setTeacherno(String teacherno) {
		this.teacherno = teacherno;
	}
	/**
	 * 获取：课程讲师编号
	 */
	public String getTeacherno() {
		return teacherno;
	}
	/**
	 * 设置：课程讲师
	 */
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	/**
	 * 获取：课程讲师
	 */
	public String getTeacher() {
		return teacher;
	}
	/**
	 * 设置：开课日期
	 */
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	/**
	 * 获取：开课日期
	 */
	public String getStartdate() {
		return startdate;
	}
	/**
	 * 设置：结课日期
	 */
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	/**
	 * 获取：结课日期
	 */
	public String getEnddate() {
		return enddate;
	}
	/**
	 * 设置：评估标准id
	 */
	public void setStandid(Long standid) {
		this.standid = standid;
	}
	/**
	 * 获取：评估标准id
	 */
	public Long getStandid() {
		return standid;
	}
	/**
	 * 设置：评估标准编号
	 */
	public void setStandtraceno(String standtraceno) {
		this.standtraceno = standtraceno;
	}
	/**
	 * 获取：评估标准编号
	 */
	public String getStandtraceno() {
		return standtraceno;
	}
	/**
	 * 设置：评估标准名称
	 */
	public void setStandtname(String standtname) {
		this.standtname = standtname;
	}
	/**
	 * 获取：评估标准名称
	 */
	public String getStandtname() {
		return standtname;
	}
	/**
	 * 设置：知识掌握水平
	 */
	public void setAvgknowledgerlevel(Double avgknowledgerlevel) {
		this.avgknowledgerlevel = avgknowledgerlevel;
	}
	/**
	 * 获取：知识掌握水平
	 */
	public Double getAvgknowledgerlevel() {
		return avgknowledgerlevel;
	}
	/**
	 * 设置：讲解清晰度
	 */
	public void setAvgexplanation(Double avgexplanation) {
		this.avgexplanation = avgexplanation;
	}
	/**
	 * 获取：讲解清晰度
	 */
	public Double getAvgexplanation() {
		return avgexplanation;
	}
	/**
	 * 设置：课后作业合适度
	 */
	public void setAvghomeworklevel(Double avghomeworklevel) {
		this.avghomeworklevel = avghomeworklevel;
	}
	/**
	 * 获取：课后作业合适度
	 */
	public Double getAvghomeworklevel() {
		return avghomeworklevel;
	}
	/**
	 * 设置：作业批改完成度
	 */
	public void setAvghomeworkgrad(Double avghomeworkgrad) {
		this.avghomeworkgrad = avghomeworkgrad;
	}
	/**
	 * 获取：作业批改完成度
	 */
	public Double getAvghomeworkgrad() {
		return avghomeworkgrad;
	}
	/**
	 * 设置：教师课件质量
	 */
	public void setAvgcourequa(Double avgcourequa) {
		this.avgcourequa = avgcourequa;
	}
	/**
	 * 获取：教师课件质量
	 */
	public Double getAvgcourequa() {
		return avgcourequa;
	}
	/**
	 * 设置：教学互动质量
	 */
	public void setAvgteachqua(Double avgteachqua) {
		this.avgteachqua = avgteachqua;
	}
	/**
	 * 获取：教学互动质量
	 */
	public Double getAvgteachqua() {
		return avgteachqua;
	}
	/**
	 * 设置：课程满意度
	 */
	public void setAvgcoursesatisfact(Double avgcoursesatisfact) {
		this.avgcoursesatisfact = avgcoursesatisfact;
	}
	/**
	 * 获取：课程满意度
	 */
	public Double getAvgcoursesatisfact() {
		return avgcoursesatisfact;
	}
	/**
	 * 设置：课程平均得分
	 */
	public void setAvgcourse(Double avgcourse) {
		this.avgcourse = avgcourse;
	}
	/**
	 * 获取：课程平均得分
	 */
	public Double getAvgcourse() {
		return avgcourse;
	}
	/**
	 * 设置：是否删除0否1是
	 */
	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}
	/**
	 * 获取：是否删除0否1是
	 */
	public String getIsdel() {
		return isdel;
	}
	/**
	 * 设置：课程创建时间
	 */
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	/**
	 * 获取：课程创建时间
	 */
	public String getCreatetime() {
		return createtime;
	}
	/**
	 * 设置：操作人
	 */
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}
	/**
	 * 获取：操作人
	 */
	public String getCreateuser() {
		return createuser;
	}
	/**
	 * 设置：创建人姓名
	 */
	public void setCreatename(String createname) {
		this.createname = createname;
	}
	/**
	 * 获取：创建人姓名
	 */
	public String getCreatename() {
		return createname;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	/**
	 * 获取：更新时间
	 */
	public String getUpdatetime() {
		return updatetime;
	}
	/**
	 * 设置：更新人
	 */
	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}
	/**
	 * 获取：更新人
	 */
	public String getUpdateuser() {
		return updateuser;
	}
	/**
	 * 设置：更新人姓名
	 */
	public void setUpdatename(String updatename) {
		this.updatename = updatename;
	}
	/**
	 * 获取：更新人姓名
	 */
	public String getUpdatename() {
		return updatename;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public List<StudentscoreDO> getStudentscoreDOList() {
		return studentscoreDOList;
	}

	public void setStudentscoreDOList(List<StudentscoreDO> studentscoreDOList) {
		this.studentscoreDOList = studentscoreDOList;
	}
}
