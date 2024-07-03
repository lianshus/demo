package com.student.system.domain;

import java.io.Serializable;
import java.util.Date;
import com.google.gson.Gson;


/**
 * 评估标准
 */
public class EvalstandardDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//评估标准编号
	private String traceno;
	//评估标准名称
	private String name;
	//知识掌握水平
	private Double knowledgerlevel;
	//讲解清晰度
	private Double explanation;
	//课后作业合适度
	private Double homeworklevel;
	//作业批改完成度
	private Double homeworkgrad;
	//教师课件质量
	private Double courequa;
	//教学互动质量
	private Double teachqua;
	//课程满意度
	private Double coursesatisfact;
	//评估标准描述
	private String note;
	//是否删除0否1是
	private String isdel;
	//登记时间
	private String createtime;
	//操作人
	private String createuser;
	//创建人姓名
	private String createname;
	//修改日期
	private String updatetime;
	//修改人
	private String updateuser;
	//修改人姓名
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

	public static EvalstandardDO fromJSON(String json) {
		Gson gson = new Gson();
		return gson.fromJson(json, EvalstandardDO.class);
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
	 * 设置：评估标准编号
	 */
	public void setTraceno(String traceno) {
		this.traceno = traceno;
	}
	/**
	 * 获取：评估标准编号
	 */
	public String getTraceno() {
		return traceno;
	}
	/**
	 * 设置：评估标准名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：评估标准名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：知识掌握水平
	 */
	public void setKnowledgerlevel(Double knowledgerlevel) {
		this.knowledgerlevel = knowledgerlevel;
	}
	/**
	 * 获取：知识掌握水平
	 */
	public Double getKnowledgerlevel() {
		return knowledgerlevel;
	}
	/**
	 * 设置：讲解清晰度
	 */
	public void setExplanation(Double explanation) {
		this.explanation = explanation;
	}
	/**
	 * 获取：讲解清晰度
	 */
	public Double getExplanation() {
		return explanation;
	}
	/**
	 * 设置：课后作业合适度
	 */
	public void setHomeworklevel(Double homeworklevel) {
		this.homeworklevel = homeworklevel;
	}
	/**
	 * 获取：课后作业合适度
	 */
	public Double getHomeworklevel() {
		return homeworklevel;
	}
	/**
	 * 设置：作业批改完成度
	 */
	public void setHomeworkgrad(Double homeworkgrad) {
		this.homeworkgrad = homeworkgrad;
	}
	/**
	 * 获取：作业批改完成度
	 */
	public Double getHomeworkgrad() {
		return homeworkgrad;
	}
	/**
	 * 设置：教师课件质量
	 */
	public void setCourequa(Double courequa) {
		this.courequa = courequa;
	}
	/**
	 * 获取：教师课件质量
	 */
	public Double getCourequa() {
		return courequa;
	}
	/**
	 * 设置：教学互动质量
	 */
	public void setTeachqua(Double teachqua) {
		this.teachqua = teachqua;
	}
	/**
	 * 获取：教学互动质量
	 */
	public Double getTeachqua() {
		return teachqua;
	}
	/**
	 * 设置：课程满意度
	 */
	public void setCoursesatisfact(Double coursesatisfact) {
		this.coursesatisfact = coursesatisfact;
	}
	/**
	 * 获取：课程满意度
	 */
	public Double getCoursesatisfact() {
		return coursesatisfact;
	}
	/**
	 * 设置：评估标准描述
	 */
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * 获取：评估标准描述
	 */
	public String getNote() {
		return note;
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
	 * 设置：登记时间
	 */
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	/**
	 * 获取：登记时间
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
	 * 设置：修改日期
	 */
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	/**
	 * 获取：修改日期
	 */
	public String getUpdatetime() {
		return updatetime;
	}
	/**
	 * 设置：修改人
	 */
	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}
	/**
	 * 获取：修改人
	 */
	public String getUpdateuser() {
		return updateuser;
	}
	/**
	 * 设置：修改人姓名
	 */
	public void setUpdatename(String updatename) {
		this.updatename = updatename;
	}
	/**
	 * 获取：修改人姓名
	 */
	public String getUpdatename() {
		return updatename;
	}
}
