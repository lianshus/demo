package com.student.system.service.impl;

import com.student.system.dao.CourseDao;
import com.student.system.dao.EvalstandardDao;
import com.student.system.domain.*;
import com.student.system.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.student.common.utils.ShiroUtils;
import com.student.system.dao.StudentscoreDao;
import com.student.system.service.StudentscoreService;
import com.student.system.service.UserService;
import com.student.system.service.WebChainCodeService;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentscoreServiceImpl implements StudentscoreService {
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
	public StudentscoreDO get(Long id){
		return studentscoreDao.get(id);
	}
	@Override
	public StudentscoreDO getByTraceno(String traceno){
		return studentscoreDao.getByTraceno(traceno);
	}

	@Override
	public List<StudentscoreDO> list(Map<String, Object> map){
		return studentscoreDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentscoreDao.count(map);
	}

	@Transactional
	@Override
	public int save(StudentscoreDO studentscore){
		return 1;
	}

	@Transactional
	@Override
	public int update(StudentscoreDO studentscore){
		Date t = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(df.format(t));
		studentscore.setUpdatetime(df.format(t));
		UserDO user  = userService.get(ShiroUtils.getUser().getUserId());
		studentscore.setUpdateuser(user.getUsername());
		studentscore.setUpdatename(user.getName());
		studentscore.setIsdel("0");
		String str = user.getUsername()+"";
		str+=String.valueOf(studentscore.getPointknowledgerlevel());
		str+=String.valueOf(studentscore.getPointexplanation());
		str+=String.valueOf(studentscore.getPointhomeworklevel());
		str+=String.valueOf(studentscore.getPointhomeworkgrad());
		str+=String.valueOf(studentscore.getPointcourequa());
		str+=String.valueOf(studentscore.getPointteachqua());
		str+=String.valueOf(studentscore.getPointcoursesatisfact());
		str+=String.valueOf(studentscore.getPointcourse());
		String hash = Md5Util.generateHash(str);
		studentscore.setStuhash(hash);
		studentscore.setStatus("1");
		EvalstandardDO evalstandard = evalstandardDao.get(studentscore.getStandid());
		BigDecimal arg1 = new BigDecimal(evalstandard.getCourequa()).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal arg2 = new BigDecimal(evalstandard.getCoursesatisfact()).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal arg3 = new BigDecimal(evalstandard.getExplanation()).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal arg4 = new BigDecimal(evalstandard.getHomeworkgrad()).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal arg5 = new BigDecimal(evalstandard.getHomeworklevel()).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal arg6 = new BigDecimal(evalstandard.getKnowledgerlevel()).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal arg7 = new BigDecimal(evalstandard.getTeachqua()).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal arg11 = new BigDecimal(studentscore.getPointcourequa()).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal arg12 = new BigDecimal(studentscore.getPointcoursesatisfact()).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal arg13 = new BigDecimal(studentscore.getPointexplanation()).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal arg14 = new BigDecimal(studentscore.getPointhomeworkgrad()).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal arg15 = new BigDecimal(studentscore.getPointhomeworklevel()).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal arg16 = new BigDecimal(studentscore.getPointknowledgerlevel()).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal arg17 = new BigDecimal(studentscore.getPointteachqua()).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal a1=arg1.multiply(arg11).setScale(2,BigDecimal.ROUND_HALF_UP);
		BigDecimal a2=arg2.multiply(arg12).setScale(2,BigDecimal.ROUND_HALF_UP);
		BigDecimal a3=arg3.multiply(arg13).setScale(2,BigDecimal.ROUND_HALF_UP);
		BigDecimal a4=arg4.multiply(arg14).setScale(2,BigDecimal.ROUND_HALF_UP);
		BigDecimal a5=arg5.multiply(arg15).setScale(2,BigDecimal.ROUND_HALF_UP);
		BigDecimal a6=arg6.multiply(arg16).setScale(2,BigDecimal.ROUND_HALF_UP);
		BigDecimal a7=arg7.multiply(arg17).setScale(2,BigDecimal.ROUND_HALF_UP);
		studentscore.setPointcourse(a1.add(a2.add(a3.add(a4.add(a5.add(a6.add(a7)))))).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
		int count = studentscoreDao.update(studentscore);
		if(count>0){
			//计算课程得分
			studentscoreDao.updateCoursePoint(studentscore.getCoursetraceno());
			try{
				System.out.println("更新区块请求开始");
				//存储区块链
				studentscore.setBlock_key(studentscore.getTraceno());
				try{
					Thread t1 = new Thread(new Runnable(){
						public void run(){
							chaincodeService.invokeChaincode(studentscore.toString());
						}});
					t1.start();
				}catch(Exception e){
					e.printStackTrace();
				}
			}catch(Exception e){
				System.out.println("更新区块失败"+e);
			}


			//存储区块链
			Course course1 = new Course();
			course1.setBlock_key(studentscore.getCoursetraceno());
			course1.setTraceno(studentscore.getCoursetraceno());
			course1.setName(studentscore.getCoursename());
			course1.setTeacherno(studentscore.getTeacherno());
			course1.setTeacher(studentscore.getTeacher());
			course1.setStandtraceno(studentscore.getStandtraceno());
			course1.setStandtname(studentscore.getStandtname());
			course1.setIsdel("0");
			CourseDO course = courseDao.getByTraceno(studentscore.getCoursetraceno());
			Map temp = new HashMap();
			temp.put("coursetraceno",studentscore.getCoursetraceno());
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
	public int remove(Long id){
		StudentscoreDO studentscore = studentscoreDao.get(id);
		int count =  studentscoreDao.remove(id);
		if(count>0){
			try{
				System.out.println("更新区块请求开始--删除");
				studentscore.setIsdel("1");
				//存储区块链
				studentscore.setBlock_key(studentscore.getTraceno());
				try{
					Thread t1 = new Thread(new Runnable(){
						public void run(){
							chaincodeService.invokeChaincode(studentscore.toString());
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
		return studentscoreDao.removeByTraceno(traceno);
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