package com.student.system.dao;

import com.student.system.domain.UserDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 */
@Mapper
public interface UserDao {

	UserDO get(Long userId);

	UserDO queryByLoginId(String loginId);

	List<UserDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(UserDO user);
	
	int update(UserDO user);

	int updateByLoginId(UserDO user);

	int remove(Long userId);
	int removeByLoginId(String loginId);

	int batchRemove(Long[] userIds);
	
	Long[] listAllDept();

}
