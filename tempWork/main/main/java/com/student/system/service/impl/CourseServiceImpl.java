package com.student.system.service.impl;

import com.student.system.dao.EvalstandardDao;
import com.student.system.dao.StudentscoreDao;
import com.student.system.domain.*;
import com.student.system.util.Md5Util;
import com.student.system.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.student.common.utils.ShiroUtils;
import com.student.system.dao.CourseDao;
import com.student.system.service.CourseService;
import com.student.system.service.UserService;
import com.student.system.service.WebChainCodeService;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private StudentscoreDao studentscoreDao;
	@Autowired
	private EvalstandardDao evalstandardDao;
	@Autowired
	UserService userService;
	@Resource
	private WebChainCodeService chaincodeService;
	@Override
	public CourseDO get(Long id){
		return courseDao.get(id);
	}
	@Override
	public CourseDO getByTraceno(String traceno){
		return courseDao.getByTraceno(traceno);
	}

	@Override
	public List<CourseDO> list(Map<String, Object> map){
		return courseDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return courseDao.count(map);
	}

	@Transactional
	@Override
	public int save(CourseDO course){
		UserDO user  = userService.get(ShiroUtils.getUser().getUserId());
		course.setCreateuser(user.getUsername());
		course.setCreatename(user.getName());
		Date t = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		course.setCreatetime(df.format(t));
		course.setTeacher(user.getName());
		course.setTeacherno(user.getUsername());
		course.setUpdateuser("");
		course.setUpdatename("");
		course.setUpdatetime("");
		course.setIsdel("0");
		int count =	 courseDao.save(course);
		if(count>0){
			//分批插入student
			//String[] stus = course.getStudents().split(",");
			for(String stusername:course.getStudents()){
				UserDO userDO = userService.queryByLoginId(stusername);
				StudentscoreDO studentscoreDO = new StudentscoreDO();
				SnowFlake snowFlake = new SnowFlake(0,0);
				studentscoreDO.setTraceno(String.valueOf(snowFlake.nextId()));
				studentscoreDO.setStuhash("");
				studentscoreDO.setCoursetraceno(course.getTraceno());
				studentscoreDO.setCoursename(course.getName());
				studentscoreDO.setTeacherno(course.getTeacherno());
				studentscoreDO.setTeacher(course.getTeacher());
				studentscoreDO.setStandid(course.getStandid());
				studentscoreDO.setStandtraceno(course.getStandtraceno());
				studentscoreDO.setStandtname(course.getStandtname());
				studentscoreDO.setCreateuser(stusername);
				studentscoreDO.setCreatename(userDO.getName());
				studentscoreDO.setCreatetime(df.format(t));
				/*studentscoreDO.setPointknowledgerlevel(0.00);
				studentscoreDO.setPointexplanation(0.00);
				studentscoreDO.setPointhomeworklevel(0.00);
				studentscoreDO.setPointhomeworkgrad(0.00);
				studentscoreDO.setPointcourequa(0.00);
				studentscoreDO.setPointteachqua(0.00);
				studentscoreDO.setPointcoursesatisfact(0.00);
				studentscoreDO.setPointcourse(0.00);*/
				studentscoreDO.setStatus("0");
				studentscoreDO.setIsdel("0");
				studentscoreDO.setCreatetime(df.format(t));
				studentscoreDao.save(studentscoreDO);
			}
			//存储区块链
			Course course1 = new Course();
			course1.setBlock_key(course.getTraceno());
			course1.setTraceno(course.getTraceno());
			course1.setName(course.getName());
			course1.setTeacherno(course.getTeacherno());
			course1.setTeacher(course.getTeacher());
			course1.setStandtraceno(course.getStandtraceno());
			course1.setStandtname(course.getStandtname());
			course1.setIsdel("0");
			Map temp = new HashMap();
			temp.put("coursetraceno",course.getTraceno());
			temp.put("status","1");
			List<StudentscoreDO> studentscoreDOList = studentscoreDao.list(temp);
			course.setStudentscoreDOList(studentscoreDOList);
			EvalstandardDO evalstandardDO = evalstandardDao.get(course.getStandid());
			course1.setEvalstandardDO(evalstandardDO);
			course1.setCourseDO(course);
			try{
				System.out.println("创建新区块请求开始");
				try{
					Thread t1 = new Thread(new Runnable(){
						public void run(){
							chaincodeService.invokeChaincode(course1.toString());
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
	public int update(CourseDO course){
		Date t = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(df.format(t));
		course.setUpdatetime(df.format(t));
		UserDO user  = userService.get(ShiroUtils.getUser().getUserId());
		course.setUpdateuser(user.getUsername());
		course.setUpdatename(user.getName());
		course.setIsdel("0");
		int count = courseDao.update(course);
		if(count>0){
			//更新学生选课表
			StudentscoreDO studentscoreDO = new StudentscoreDO();
			studentscoreDO.setCoursetraceno(course.getTraceno());
			studentscoreDO.setCoursename(course.getName());
			studentscoreDao.updateCourse(studentscoreDO);
			//存储区块链
			Course course1 = new Course();
			course1.setBlock_key(course.getTraceno());
			course1.setTraceno(course.getTraceno());
			course1.setName(course.getName());
			course1.setTeacherno(course.getTeacherno());
			course1.setTeacher(course.getTeacher());
			course1.setStandtraceno(course.getStandtraceno());
			course1.setStandtname(course.getStandtname());
			course1.setIsdel("0");
			Map temp = new HashMap();
			temp.put("coursetraceno",course.getTraceno());
			temp.put("status","1");
			List<StudentscoreDO> studentscoreDOList = studentscoreDao.list(temp);
			course.setStudentscoreDOList(studentscoreDOList);
			EvalstandardDO evalstandardDO = evalstandardDao.get(course.getStandid());
			course1.setEvalstandardDO(evalstandardDO);
			course1.setCourseDO(course);
			try{
				System.out.println("创建新区块请求开始");
				try{
					Thread t1 = new Thread(new Runnable(){
						public void run(){
							chaincodeService.invokeChaincode(course1.toString());
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
/*
	@Transactional
	@Override
	public int remove(Long id){
		CourseDO course = courseDao.get(id);
		int count =  courseDao.remove(id);
		return count;
	}
	@Transactional
	@Override
	public int removeByTraceno(String traceno){
		return courseDao.removeByTraceno(traceno);
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
	}*/
}