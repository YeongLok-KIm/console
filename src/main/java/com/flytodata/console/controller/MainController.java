package com.flytodata.console.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @RequestMapping(value="/")
    public String widgets() {
        return "index";
    }


    @RequestMapping(value="/login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request) {
        String userId = request.getParameter("uid");
        String userPw = request.getParameter("pw");
        ModelAndView mav = new ModelAndView();
        mav.addObject("userId", userId);
        mav.addObject("userPw",userPw);
        return mav;
    }


}

