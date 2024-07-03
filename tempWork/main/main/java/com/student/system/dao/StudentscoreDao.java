package com.student.system.dao;

import com.student.system.domain.StudentscoreDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 学生打分表
 */
@Mapper
public interface StudentscoreDao {

	StudentscoreDO get(Long id);
	StudentscoreDO getByTraceno(String traceno);

	List<StudentscoreDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(StudentscoreDO studentscore);
	
	int update(StudentscoreDO studentscore);
	int updateCourse(StudentscoreDO studentscore);
	int updateCoursePoint(String traceno);
	int remove(Long id);
	int removeByTraceno(String traceno);

	int batchRemove(Long[] ids);
}
