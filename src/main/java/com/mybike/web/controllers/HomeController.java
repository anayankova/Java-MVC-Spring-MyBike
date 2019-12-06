package com.mybike.web.controllers;

import com.mybike.web.controllers.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController {

    @GetMapping("/")
    public ModelAndView getIndex() {
        return super.view("home/index");
    }

    @GetMapping("/home")
    public ModelAndView getHome() {
        return super.view("home/home");
    }
}
