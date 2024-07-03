package com.student.system.dao;

import com.student.system.domain.EvalstandardDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 评估标准
 */
@Mapper
public interface EvalstandardDao {

	EvalstandardDO get(Long id);
	EvalstandardDO getByTraceno(String traceno);

	List<EvalstandardDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(EvalstandardDO evalstandard);
	
	int update(EvalstandardDO evalstandard);
	
	int remove(Long id);
	int removeByTraceno(String traceno);

	int batchRemove(Long[] ids);
}
