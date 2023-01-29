package com.flytodata.console.controller.auth;

import com.flytodata.console.comm.util.RandomAESKeyGen;
import com.flytodata.console.comm.util.TranFormat;
import com.flytodata.console.entity.User;
import com.flytodata.console.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public ModelAndView signup(HttpServletRequest request) {
        String name = request.getParameter("name").trim();
        String email = request.getParameter("email").trim();
        String password = request.getParameter("password").trim();
        String confirm_password = request.getParameter("confirm_password").trim();
        String terms_and_policy = request.getParameter("terms_and_policy");


        String signupResultMsg = "";
        String signupResultCode = "OK";
        if(TranFormat.isValidEmail(email)==false){
            signupResultMsg =  "Ivalid email!!";
        }else if(password.equals(confirm_password)==false){
            signupResultMsg =  "The password and confirm password do not match";
        }

        try {

            User user = this.userService.findUserByEmail(email);
            if (user != null) {
                signupResultMsg = "Aleady exist email!!";
            } else {

                long nano = System.currentTimeMillis(); // 1491968593191
                String sec = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(nano); // 2017-04-12 12:43:13.191
                String sec3 = sec.substring(20);
                String str_nano = String.valueOf(nano);
                String msec6 = str_nano.substring(6);

                //random 3자리 + sec 마지막 3자리 + msec 7자리 + random 3자리로 구성
                String api_key = Math.round(Math.random() * (999 - 100 + 1) + 100) + sec3 + msec6 + Math.round(Math.random() * (999 - 100 + 1) + 100);
                String api_secret = RandomAESKeyGen.generate2Base64(80);

                User new_user = User.builder()
                        .name(name)
                        .email(email)
                        .password(password)
                        .api_key(api_key)
                        .api_secret(api_secret)
                        .active(true)
                        .build();

                this.userService.save(new_user);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        ModelAndView mav = new ModelAndView();
        //mav.addObject("signupResultMsg",signupResultMsg);
        mav.setViewName("landing");
        return mav;
    }
}
