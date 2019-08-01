package com.itheima.ssm.contoller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrdersService iOrdersService;

    /*@RequestMapping("/findAll.do")
    public ModelAndView findAll() {
        List<Orders> all = iOrdersService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("ordersList", all);
        modelAndView.setViewName("orders-list");
        return modelAndView;
    }*/
//根据订单id 查询所有
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page" ,required = true ,defaultValue = "1") Integer page,  @RequestParam(name = "size",required = true ,defaultValue = "3") Integer size) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        List<Orders> all = iOrdersService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(all);
        modelAndView.addObject("pageinfo",pageInfo);
        modelAndView.setViewName("orders-page-list");
        return modelAndView;
    }


    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id" ,required = true )String id) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
            Orders orders =iOrdersService.findById(id);
            modelAndView.addObject("orders", orders);
            modelAndView.setViewName("orders-show");
            return modelAndView;

    }

}
