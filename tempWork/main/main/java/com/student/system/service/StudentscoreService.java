package com.student.system.service;

import com.student.system.domain.StudentscoreDO;

import java.util.List;
import java.util.Map;

/**
 * 学生打分表
 * 
 */
public interface StudentscoreService {
	
	StudentscoreDO get(Long id);
	StudentscoreDO getByTraceno(String traceno);

	List<StudentscoreDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentscoreDO studentscore);
	
	int update(StudentscoreDO studentscore);
	
	int remove(Long id);
	int removeByTraceno(String traceno);

	int batchRemove(Long[] ids);
}
