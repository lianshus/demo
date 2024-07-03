package com.student.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.student.common.utils.ShiroUtils;
import com.student.system.domain.UserDO;
import com.student.system.dao.EvalstandardDao;
import com.student.system.domain.EvalstandardDO;
import com.student.system.service.EvalstandardService;
import com.student.system.service.UserService;
import com.student.system.service.WebChainCodeService;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EvalstandardServiceImpl implements EvalstandardService {
	@Autowired
	private EvalstandardDao evalstandardDao;
	@Autowired
	UserService userService;
	@Resource
	private WebChainCodeService chaincodeService;
	@Override
	public EvalstandardDO get(Long id){
		return evalstandardDao.get(id);
	}
	@Override
	public EvalstandardDO getByTraceno(String traceno){
		return evalstandardDao.getByTraceno(traceno);
	}

	@Override
	public List<EvalstandardDO> list(Map<String, Object> map){
		return evalstandardDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return evalstandardDao.count(map);
	}

	@Transactional
	@Override
	public int save(EvalstandardDO evalstandard){
		UserDO user  = userService.get(ShiroUtils.getUser().getUserId());
		evalstandard.setCreateuser(user.getUsername());
		evalstandard.setCreatename(user.getName());
		Date t = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(df.format(t));
		evalstandard.setCreatetime(df.format(t));
		evalstandard.setUpdateuser("");
		evalstandard.setUpdatename("");
		evalstandard.setUpdatetime("");
		evalstandard.setIsdel("0");
		int count =	 evalstandardDao.save(evalstandard);
		if(count>0){
			//存储区块链
			evalstandard.setBlock_key(evalstandard.getTraceno());
			try{
				System.out.println("创建新区块请求开始");
				try{
					Thread t1 = new Thread(new Runnable(){
						public void run(){
							chaincodeService.invokeChaincode(evalstandard.toString());
						}});
					t1.start();
				}catch(Exception e){
					e.printStackTrace();
				}
			}catch(Exception e){
				System.out.println("创建新区块失败"+e);
			}
		}
		return count;
	}

	@Transactional
	@Override
	public int update(EvalstandardDO evalstandard){
		Date t = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(df.format(t));
		evalstandard.setUpdatetime(df.format(t));
		UserDO user  = userService.get(ShiroUtils.getUser().getUserId());
		evalstandard.setUpdateuser(user.getUsername());
		evalstandard.setUpdatename(user.getName());
		evalstandard.setIsdel("0");
		int count = evalstandardDao.update(evalstandard);
		if(count>0){
			try{
				System.out.println("更新区块请求开始");
				//存储区块链
				evalstandard.setBlock_key(evalstandard.getTraceno());
				try{
					Thread t1 = new Thread(new Runnable(){
						public void run(){
							chaincodeService.invokeChaincode(evalstandard.toString());
						}});
					t1.start();
				}catch(Exception e){
					e.printStackTrace();
				}
			}catch(Exception e){
				System.out.println("更新区块失败"+e);
			}
		}
		return count;
	}

	@Transactional
	@Override
	public int remove(Long id){
		EvalstandardDO evalstandard = evalstandardDao.get(id);
		int count =  evalstandardDao.remove(id);
		if(count>0){
			try{
				System.out.println("更新区块请求开始--删除");
				evalstandard.setIsdel("1");
				//存储区块链
				evalstandard.setBlock_key(evalstandard.getTraceno());
				try{
					Thread t1 = new Thread(new Runnable(){
						public void run(){
							chaincodeService.invokeChaincode(evalstandard.toString());
						}});
					t1.start();
				}catch(Exception e){
					e.printStackTrace();
				}
			}catch(Exception e){
				System.out.println("更新区块失败"+e);
			}
		}
		return count;
	}
	@Transactional
	@Override
	public int removeByTraceno(String traceno){
		return evalstandardDao.removeByTraceno(traceno);
	}

	@Transactional
	@Override
	public int batchRemove(Long[] ids){
		try{
			for(Long id : ids){
				remove(id);
			}
		}catch (Exception e){
			return -1;
		}
		return 1;
	}
}