package com.student.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.student.system.dao.EvalstandardDao;
import com.student.system.domain.Course;
import com.student.system.domain.EvalstandardDO;
import com.student.system.util.SnowFlake;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import com.student.system.domain.CourseDO;
import com.student.system.service.CourseService;
import com.student.common.utils.PageUtils;
import com.student.common.utils.Query;
import com.student.common.utils.R;
import javax.annotation.Resource;
import com.student.system.service.WebChainCodeService;
import com.student.system.util.BaseData;
import com.student.system.util.ResultCode;
import com.student.common.utils.ShiroUtils;
import com.student.system.domain.UserDO;
import com.student.system.service.UserService;

/**
 * 课程信息
 * 
 */
 
@Controller
@RequestMapping("/system/course")
public class CourseController {
	@Autowired
	private CourseService courseService;
	@Autowired
	private EvalstandardDao evalstandardDao;
	@Resource
	private WebChainCodeService chaincodeService;
	@Autowired
	UserService userService;
	@GetMapping()
	@RequiresPermissions("system:course:course")
	String Course(){
	    return "system/course/course";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:course:course")
	public PageUtils list(@RequestParam Map<String, Object> params){
		UserDO user  = userService.get(ShiroUtils.getUser().getUserId());
		params.put("createuser",user.getUsername());
		//查询列表数据
        Query query = new Query(params);
		List<CourseDO> courseList = courseService.list(query);
		int total = courseService.count(query);
		PageUtils pageUtils = new PageUtils(courseList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:course:add")
	String add(Model model) {
		List<EvalstandardDO> evalstandardList = evalstandardDao.list(null);
		model.addAttribute("evalstandardList", evalstandardList);
		Map m = new HashMap();
		m.put("deptId","3");
		List<UserDO> students = userService.list(m);
		model.addAttribute("students", students);
	    return "system/course/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:course:edit")
	String edit(@PathVariable("id") Long id,Model model){
		CourseDO course = courseService.get(id);
		model.addAttribute("course", course);
	    return "system/course/edit";
	}

	@GetMapping("/read/{id}")
	String read(@PathVariable("id") Long id,Model model){
		CourseDO course = courseService.get(id);
		model.addAttribute("course", course);
	    return "system/course/read";
	}
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:course:add")
	public R save( CourseDO course){
		SnowFlake snowFlake = new SnowFlake(0,0);
		course.setTraceno(String.valueOf(snowFlake.nextId()));
//		判断课程名称
		Map m = new HashMap();
		m.put("name",course.getName());
		List<CourseDO> courseDOList = courseService.list(m);
		if(courseDOList!=null && courseDOList.size()>0){
			return R.error("该课程已存在，请修改！");
		}
		if(courseService.save(course)>0){
			return R.ok();
		}
		return R.error();
	}
/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:course:edit")
	public R update(CourseDO course){
		courseService.update(course);
		return R.ok();
	}
/*
	*//**
	 * 删除
	 *//*
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:course:remove")
	public R remove( Long id){
		if(courseService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	*//**
	 * 删除
	 *//*
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:course:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		courseService.batchRemove(ids);
		return R.ok();
	}*/
	@GetMapping("/open")
	String open(Model model) {
		return "system/course/open";
	}
	/**
	 * 信息溯源查询
	 * @param traceno
	 * @return
	 */
	@RequiresPermissions("system:showcx:showcx")
	@ResponseBody
	@RequestMapping("/showcx")
	public BaseData openshowcx(String traceno){
		Course course = new Course();
		try{
			BaseData courseData = chaincodeService.queryChainCode(traceno,1);
			course = Course.fromJSON(String.valueOf(courseData.getData()));
		}catch(Exception e){
			e.printStackTrace();
		}
		BaseData resultData = new BaseData()
				.setCode(ResultCode.SUCCESS)
				.setMessage("SUCCESS")
				.setData(course);
		return resultData;
	}
}
