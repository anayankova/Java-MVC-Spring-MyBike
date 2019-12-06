package com.mybike.web.controllers;

import com.mybike.service.models.EnduroCreateServiceModel;
import com.mybike.service.services.EnduroService;
import com.mybike.service.services.UserService;
import com.mybike.web.controllers.base.BaseController;
import com.mybike.web.models.EnduroCreateModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/bikes/create")
public class EnduroContoller extends BaseController {

    private final EnduroService enduroService;
    private final UserService userService;
    private final ModelMapper mapper;

    public EnduroContoller(EnduroService enduroService, UserService userService, ModelMapper mapper) {
        this.enduroService = enduroService;
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping("/enduro")
    public ModelAndView getCreateBike() {
        return super.view("bikes/create-enduro");
    }

    @PostMapping("/enduro")
    public ModelAndView createEnduro(@ModelAttribute EnduroCreateModel enduro, HttpSession session) {
        String username = getUsername(session);
        EnduroCreateServiceModel serviceModel = mapper.map(enduro, EnduroCreateServiceModel.class);
        enduroService.create(username, serviceModel);
        return super.redirect("/bikes/all");

    }
}
