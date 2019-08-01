package com.itheima.ssm.contoller;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        List<UserInfo> userList = iUserService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userList" , userList);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }

    @RequestMapping("/save.do")
    public String save(UserInfo userInfo){
        iUserService.save(userInfo);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById( String id) throws Exception {
        //查询所有用户信息，包括用户，角色信息，授权信息等
        //查询完返回到
        UserInfo user = iUserService.findById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user",user);
        modelAndView.setViewName("user-show");
        return modelAndView;
    }

    /*
    用户与角色添加关系
       根据id查出用户，根据id查出查出该id没有的角色。
       将其返回给  user-role-add 页面
     */
    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) String userId) throws Exception {
        UserInfo user = iUserService.findById(userId);
        List<Role> roleList = iUserService.findOtherRole(userId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.addObject("roleList", roleList);
        modelAndView.setViewName("user-role-add");

        return modelAndView;
    }

    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId",required = true) String userId, @RequestParam(name = "ids" ,required = true) String[] roleIds){
        iUserService.addRoleToUser(userId,roleIds);
        return "redirect:findAll.do";
    }
}
