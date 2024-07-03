package com.student.system.domain;

import java.io.Serializable;
import java.util.Date;
import com.google.gson.Gson;


/**
 * 学生打分表
 */
public class StudentscoreDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//打分溯源码
	private String traceno;
	//哈希值
	private String stuhash;
	//课程编号
	private String coursetraceno;
	//课程名称
	private String coursename;
	//课程讲师编号
	private String teacherno;
	//课程讲师
	private String teacher;
	//评估标准id
	private Long standid;
	//评估标准编号
	private String standtraceno;
	//评估标准名称
	private String standtname;
	//知识掌握水平
	private Double pointknowledgerlevel;
	//讲解清晰度
	private Double pointexplanation;
	//课后作业合适度
	private Double pointhomeworklevel;
	//作业批改完成度
	private Double pointhomeworkgrad;
	//教师课件质量
	private Double pointcourequa;
	//教学互动质量
	private Double pointteachqua;
	//课程满意度
	private Double pointcoursesatisfact;
	//课程得分
	private Double pointcourse;
	//是否打分
	private String status;
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
	public String getBlock_key() {
		return block_key;
	}

	public void setBlock_key(String block_key) {
		this.block_key = block_key;
	}
	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	public static StudentscoreDO fromJSON(String json) {
		Gson gson = new Gson();
		return gson.fromJson(json, StudentscoreDO.class);
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
	 * 设置：打分溯源码
	 */
	public void setTraceno(String traceno) {
		this.traceno = traceno;
	}
	/**
	 * 获取：打分溯源码
	 */
	public String getTraceno() {
		return traceno;
	}
	/**
	 * 设置：哈希值
	 */
	public void setStuhash(String stuhash) {
		this.stuhash = stuhash;
	}
	/**
	 * 获取：哈希值
	 */
	public String getStuhash() {
		return stuhash;
	}
	/**
	 * 设置：课程编号
	 */
	public void setCoursetraceno(String coursetraceno) {
		this.coursetraceno = coursetraceno;
	}
	/**
	 * 获取：课程编号
	 */
	public String getCoursetraceno() {
		return coursetraceno;
	}
	/**
	 * 设置：课程名称
	 */
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	/**
	 * 获取：课程名称
	 */
	public String getCoursename() {
		return coursename;
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
	public void setPointknowledgerlevel(Double pointknowledgerlevel) {
		this.pointknowledgerlevel = pointknowledgerlevel;
	}
	/**
	 * 获取：知识掌握水平
	 */
	public Double getPointknowledgerlevel() {
		return pointknowledgerlevel;
	}
	/**
	 * 设置：讲解清晰度
	 */
	public void setPointexplanation(Double pointexplanation) {
		this.pointexplanation = pointexplanation;
	}
	/**
	 * 获取：讲解清晰度
	 */
	public Double getPointexplanation() {
		return pointexplanation;
	}
	/**
	 * 设置：课后作业合适度
	 */
	public void setPointhomeworklevel(Double pointhomeworklevel) {
		this.pointhomeworklevel = pointhomeworklevel;
	}
	/**
	 * 获取：课后作业合适度
	 */
	public Double getPointhomeworklevel() {
		return pointhomeworklevel;
	}
	/**
	 * 设置：作业批改完成度
	 */
	public void setPointhomeworkgrad(Double pointhomeworkgrad) {
		this.pointhomeworkgrad = pointhomeworkgrad;
	}
	/**
	 * 获取：作业批改完成度
	 */
	public Double getPointhomeworkgrad() {
		return pointhomeworkgrad;
	}
	/**
	 * 设置：教师课件质量
	 */
	public void setPointcourequa(Double pointcourequa) {
		this.pointcourequa = pointcourequa;
	}
	/**
	 * 获取：教师课件质量
	 */
	public Double getPointcourequa() {
		return pointcourequa;
	}
	/**
	 * 设置：教学互动质量
	 */
	public void setPointteachqua(Double pointteachqua) {
		this.pointteachqua = pointteachqua;
	}
	/**
	 * 获取：教学互动质量
	 */
	public Double getPointteachqua() {
		return pointteachqua;
	}
	/**
	 * 设置：课程满意度
	 */
	public void setPointcoursesatisfact(Double pointcoursesatisfact) {
		this.pointcoursesatisfact = pointcoursesatisfact;
	}
	/**
	 * 获取：课程满意度
	 */
	public Double getPointcoursesatisfact() {
		return pointcoursesatisfact;
	}
	/**
	 * 设置：课程得分
	 */
	public void setPointcourse(Double pointcourse) {
		this.pointcourse = pointcourse;
	}
	/**
	 * 获取：课程得分
	 */
	public Double getPointcourse() {
		return pointcourse;
	}
	/**
	 * 设置：是否打分
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：是否打分
	 */
	public String getStatus() {
		return status;
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
}
