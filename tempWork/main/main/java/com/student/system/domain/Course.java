package com.student.system.domain;

import com.google.gson.Gson;

import java.io.Serializable;


/**
 * 课程信息
 */
public class Course implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//课程编号
	private String traceno;
	//课程名称
	private String name;
	//课程讲师编号
	private String teacherno;
	//课程讲师
	private String teacher;
	//评估标准编号
	private String standtraceno;
	//评估标准名称
	private String standtname;
	//是否删除0否1是
	private String isdel;
	private String block_key;
	private EvalstandardDO evalstandardDO;
	private CourseDO courseDO;
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

	public static Course fromJSON(String json) {
		Gson gson = new Gson();
		return gson.fromJson(json, Course.class);
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getTraceno() {
		return traceno;
	}

	public void setTraceno(String traceno) {
		this.traceno = traceno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeacherno() {
		return teacherno;
	}

	public void setTeacherno(String teacherno) {
		this.teacherno = teacherno;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getStandtraceno() {
		return standtraceno;
	}

	public void setStandtraceno(String standtraceno) {
		this.standtraceno = standtraceno;
	}

	public String getStandtname() {
		return standtname;
	}

	public void setStandtname(String standtname) {
		this.standtname = standtname;
	}

	public String getIsdel() {
		return isdel;
	}

	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}

	public EvalstandardDO getEvalstandardDO() {
		return evalstandardDO;
	}

	public void setEvalstandardDO(EvalstandardDO evalstandardDO) {
		this.evalstandardDO = evalstandardDO;
	}

	public CourseDO getCourseDO() {
		return courseDO;
	}

	public void setCourseDO(CourseDO courseDO) {
		this.courseDO = courseDO;
	}
}