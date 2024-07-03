package com.student.system.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.student.system.util.Md5Util;
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
import com.student.system.domain.StudentscoreDO;
import com.student.system.service.StudentscoreService;
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
 * 学生打分表
 * 
 */
 
@Controller
@RequestMapping("/system/studentscore")
public class StudentscoreController {
	@Autowired
	private StudentscoreService studentscoreService;
	@Resource
	private WebChainCodeService chaincodeService;
	@Autowired
	UserService userService;
	@GetMapping()
	@RequiresPermissions("system:studentscore:studentscore")
	String Studentscore(){
	    return "system/studentscore/studentscore";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:studentscore:studentscore")
	public PageUtils list(@RequestParam Map<String, Object> params){
		UserDO user  = userService.get(ShiroUtils.getUser().getUserId());
		params.put("createuser",user.getUsername());
		//查询列表数据
        Query query = new Query(params);
		List<StudentscoreDO> studentscoreList = studentscoreService.list(query);
		int total = studentscoreService.count(query);
		PageUtils pageUtils = new PageUtils(studentscoreList, total);
		return pageUtils;
	}
	
	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:studentscore:edit")
	String edit(@PathVariable("id") Long id,Model model){
		StudentscoreDO studentscore = studentscoreService.get(id);
		model.addAttribute("studentscore", studentscore);
	    return "system/studentscore/edit";
	}
	
	/**
	 * 修改-打分
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:studentscore:edit")
	public R update( StudentscoreDO studentscore){
		if(studentscore.getPointcourequa()==null || "".equals(studentscore.getPointcourequa())
	|| studentscore.getPointcoursesatisfact()==null || "".equals(studentscore.getPointcoursesatisfact())
	|| studentscore.getPointexplanation()==null || "".equals(studentscore.getPointexplanation())
	|| studentscore.getPointhomeworkgrad()==null || "".equals(studentscore.getPointhomeworkgrad())
	|| studentscore.getPointhomeworklevel()==null || "".equals(studentscore.getPointhomeworklevel())
	|| studentscore.getPointknowledgerlevel()==null || "".equals(studentscore.getPointknowledgerlevel())
	|| studentscore.getPointteachqua()==null || "".equals(studentscore.getPointteachqua())
		){
			return R.error("分数不能为空，请补充！");
		}
		studentscoreService.update(studentscore);
		return R.ok();
	}
	
	/**
	 * 打分验证
	 */
	@PostMapping( "/check")
	@ResponseBody
	public R check(Long id){
		StudentscoreDO studentscoreDO = studentscoreService.get(id);
		if(studentscoreDO!=null){
			if("1".equals(studentscoreDO.getStatus())){
				BaseData studentscoreData = chaincodeService.queryChainCode(studentscoreDO.getTraceno(),0);
				if(studentscoreData.getData()==null || "".equals(studentscoreData.getData())){
					return R.error("验证失败,请确认区块链服务是否运行正常！");
				}
				String hash1 =StudentscoreDO.fromJSON(String.valueOf(studentscoreData.getData())).getStuhash();
				String hash2 = studentscoreDO.getStuhash();
				if(hash1.equals(hash2)){
					return R.ok("认证成功！");
				}else{
					return R.ok("认证失败！信息已被篡改");
				}
			}else{
				return R.error("验证失败，请先进行打分！");
			}
	}
		return R.error("信息不存在！请确认。");
	}
}