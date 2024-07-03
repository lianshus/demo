package com.student.system.service;

import com.student.system.domain.EvalstandardDO;

import java.util.List;
import java.util.Map;

/**
 * 评估标准
 * 
 */
public interface EvalstandardService {
	
	EvalstandardDO get(Long id);
	EvalstandardDO getByTraceno(String traceno);

	List<EvalstandardDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(EvalstandardDO evalstandard);
	
	int update(EvalstandardDO evalstandard);
	
	int remove(Long id);
	int removeByTraceno(String traceno);

	int batchRemove(Long[] ids);
}
