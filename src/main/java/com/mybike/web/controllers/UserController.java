package com.mybike.web.controllers;

import com.mybike.service.models.LoginUserServiceModel;
import com.mybike.service.models.RegisterUserServiceModel;
import com.mybike.service.services.UserService;
import com.mybike.web.controllers.base.BaseController;
import com.mybike.web.models.RegisterUserModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequestMapping("/users")
@AllArgsConstructor
public class UserController extends BaseController {

    private final UserService userService;
    private final ModelMapper mapper;

    @GetMapping("/register")
    public ModelAndView getRegister() {
        return super.view("users/register");
    }

    @GetMapping("/login")
    public ModelAndView getLogin() {
        return super.view("users/login");
    }

    @GetMapping("/profile")
    public ModelAndView getProfile(Principal principal, ModelAndView modelAndView) {
        //modelAndView.addObject("model", this.mapper
                //.map(this.userService.findUserByUserName(principal.getName()), UserProfileModel.class));
        return super.view("users/profile", modelAndView);
    }

    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute RegisterUserModel model) throws Exception {
        RegisterUserServiceModel serviceModel = mapper.map(model, RegisterUserServiceModel.class);
        userService.register(serviceModel);
        return super.redirect("/users/login");
    }

    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute RegisterUserModel model, HttpSession session) throws Exception {
        RegisterUserServiceModel serviceModel = mapper.map(model, RegisterUserServiceModel.class);
        LoginUserServiceModel loginUserServiceModel = userService.login(serviceModel);
        session.setAttribute("user", loginUserServiceModel);
        return super.redirect("/home");
    }
}
