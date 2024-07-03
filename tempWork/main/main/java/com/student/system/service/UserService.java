package com.student.system.service;

import com.student.system.domain.UserDO;
import com.student.system.vo.UserVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public interface UserService {
	UserDO get(Long id);
	UserDO queryByLoginId(String loginId);
	List<UserDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(UserDO user);

	int update(UserDO user);

	int remove(Long userId);

	int removeByLoginId(String loginId);

	int batchremove(Long[] userIds);

	boolean exit(Map<String, Object> params);

	Set<String> listRoles(Long userId);

	int resetPwd(UserVO userVO,UserDO userDO) throws Exception;
	int adminResetPwd(UserVO userVO) throws Exception;

	/**
	 * 更新个人信息
	 * @param userDO
	 * @return
	 */
	int updatePersonal(UserDO userDO);
}
