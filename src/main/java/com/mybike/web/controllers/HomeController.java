package com.mybike.web.controllers;

import com.mybike.service.services.AuthenticatedUserService;
import com.mybike.web.controllers.base.BaseController;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@AllArgsConstructor
public class HomeController extends BaseController {

    private final AuthenticatedUserService authenticatedUserService;

    @GetMapping("/")
    public ModelAndView getIndex() {
        return super.view("home/index");
    }

    @GetMapping("/home")
    public ModelAndView getHome() {
        List<String> authorities = authenticatedUserService.getRoles();
        return super.view("home/home");
    }
}
