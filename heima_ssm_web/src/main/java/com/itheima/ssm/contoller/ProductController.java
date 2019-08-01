package com.itheima.ssm.contoller;

import com.itheima.ssm.domain.Product;
import com.itheima.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService iProductService;

    @RequestMapping("/findAll.do")
//    @RolesAllowed("ADMIN")
//    @Secured("ROLE_ADMIN")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView findAll() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        List<Product> ps = iProductService.findAll();
        modelAndView.addObject("productList", ps);
        modelAndView.setViewName("product-list1");
        return modelAndView;
    }

    @RequestMapping("/save.do")
//    @PreAuthorize("authentication.principal.username='tom'")  500错误
    public String save(Product product){
        iProductService.save(product);
        return "redirect:findAll.do"; //重定向这个要加冒号的
    }
}
