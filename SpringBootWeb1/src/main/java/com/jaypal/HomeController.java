package com.jaypal;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(){
        return "index";
    }

//    @RequestMapping("add")
//    public String add(HttpServletRequest request, HttpSession session){ //servlet way of handling data
//        int num1 = Integer.parseInt(request.getParameter("num1"));
//        int num2 = Integer.parseInt(request.getParameter("num2"));
//        int result = num1 + num2;
//        session.setAttribute("result", result);
//        System.out.println(result);
//        return "result.jsp";
//    }

    @RequestMapping("add")
    public ModelAndView add(@RequestParam("num1") int num1, @RequestParam("num2") int num2, ModelAndView mv){ //jstl way of handling data
        int result = num1 + num2;
        mv.addObject("result",result);
        mv.setViewName("result");
        return mv;
    }
}
