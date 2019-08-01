package com.itheima.ssm.contoller;

import com.itheima.ssm.domain.SysLog;
import com.itheima.ssm.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/syslog")
public class SysLogController {

    @Autowired
    private ISysLogService iSysLogService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        List<SysLog> sysLogs =  iSysLogService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("sysLogs",sysLogs);
        modelAndView.setViewName("syslog-list");
        return modelAndView;
    }
}
