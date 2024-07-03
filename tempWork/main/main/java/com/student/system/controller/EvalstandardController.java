package com.student.system.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
import com.student.system.domain.EvalstandardDO;
import com.student.system.service.EvalstandardService;
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
 * 评估标准
 * 
 */
 
@Controller
@RequestMapping("/system/evalstandard")
public class EvalstandardController {
	@Autowired
	private EvalstandardService evalstandardService;
	@Resource
	private WebChainCodeService chaincodeService;
	@Autowired
	UserService userService;
	@GetMapping()
	@RequiresPermissions("system:evalstandard:evalstandard")
	String Evalstandard(){
	    return "system/evalstandard/evalstandard";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:evalstandard:evalstandard")
	public PageUtils list(@RequestParam Map<String, Object> params){
		UserDO user  = userService.get(ShiroUtils.getUser().getUserId());
		params.put("createuser",user.getUsername());
		//查询列表数据
        Query query = new Query(params);
		List<EvalstandardDO> evalstandardList = evalstandardService.list(query);
		int total = evalstandardService.count(query);
		PageUtils pageUtils = new PageUtils(evalstandardList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:evalstandard:add")
	String add(){
	    return "system/evalstandard/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:evalstandard:edit")
	String edit(@PathVariable("id") Long id,Model model){
		EvalstandardDO evalstandard = evalstandardService.get(id);
		model.addAttribute("evalstandard", evalstandard);
	    return "system/evalstandard/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:evalstandard:add")
	public R save( EvalstandardDO evalstandard){
		SnowFlake snowFlake = new SnowFlake(0,0);
		evalstandard.setTraceno(String.valueOf(snowFlake.nextId()));
		BigDecimal arg1 = new BigDecimal(evalstandard.getCourequa()).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal arg2 = new BigDecimal(evalstandard.getCoursesatisfact()).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal arg3 = new BigDecimal(evalstandard.getExplanation()).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal arg4 = new BigDecimal(evalstandard.getHomeworkgrad()).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal arg5 = new BigDecimal(evalstandard.getHomeworklevel()).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal arg6 = new BigDecimal(evalstandard.getKnowledgerlevel()).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal arg7 = new BigDecimal(evalstandard.getTeachqua()).setScale(2, BigDecimal.ROUND_HALF_UP);
		if(arg1.add(arg2.add(arg3.add(arg4.add(arg5.add(arg6.add(arg7)))))).compareTo(BigDecimal.ONE)!=0){
			return R.error("各项权重指标的和为1，请修改！");
		}
		if(evalstandardService.save(evalstandard)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:evalstandard:edit")
	public R update( EvalstandardDO evalstandard){
		BigDecimal arg1 = new BigDecimal(evalstandard.getCourequa()).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal arg2 = new BigDecimal(evalstandard.getCoursesatisfact()).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal arg3 = new BigDecimal(evalstandard.getExplanation()).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal arg4 = new BigDecimal(evalstandard.getHomeworkgrad()).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal arg5 = new BigDecimal(evalstandard.getHomeworklevel()).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal arg6 = new BigDecimal(evalstandard.getKnowledgerlevel()).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal arg7 = new BigDecimal(evalstandard.getTeachqua()).setScale(2, BigDecimal.ROUND_HALF_UP);
		if(arg1.add(arg2.add(arg3.add(arg4.add(arg5.add(arg6.add(arg7)))))).compareTo(BigDecimal.ONE)!=0){
			return R.error("各项权重指标的和为1，请修改！");
		}
		evalstandardService.update(evalstandard);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:evalstandard:remove")
	public R remove( Long id){
		if(evalstandardService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:evalstandard:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		evalstandardService.batchRemove(ids);
		return R.ok();
	}
	@GetMapping("/open")
	String open(Model model) {
		return "system/evalstandard/open";
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
		EvalstandardDO evalstandard = new EvalstandardDO();
		try{
			BaseData evalstandardData = chaincodeService.queryChainCode(traceno,2);
			evalstandard = EvalstandardDO.fromJSON(String.valueOf(evalstandardData.getData()));
		}catch(Exception e){
			e.printStackTrace();
		}
		BaseData resultData = new BaseData()
				.setCode(ResultCode.SUCCESS)
				.setMessage("SUCCESS")
				.setData(evalstandard);
		return resultData;
	}
}
