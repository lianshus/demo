package com.student.system.dao;

import com.student.system.domain.CourseDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 课程信息
 */
@Mapper
public interface CourseDao {

	CourseDO get(Long id);
	CourseDO getByTraceno(String traceno);

	List<CourseDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(CourseDO course);
	
	int update(CourseDO course);
	
	int remove(Long id);
	int removeByTraceno(String traceno);

	int batchRemove(Long[] ids);
}
