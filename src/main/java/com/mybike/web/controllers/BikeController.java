package com.mybike.web.controllers;

import com.mybike.service.services.EnduroService;
import com.mybike.web.controllers.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/bikes")
public class BikeController extends BaseController {

    private final EnduroService enduroService;

    public BikeController(EnduroService enduroService) {
        this.enduroService = enduroService;
    }

    @GetMapping("")
    public ModelAndView getBikes() {
        return super.view("bikes/bikes");
    }

    @GetMapping("/create")
    public ModelAndView getCreateBike() {
        return super.view("bikes/create-bike");
    }

    @GetMapping("/all")
    public String getAllBikesByUsername(Model model, HttpSession session) {
        String username = getUsername(session);
        model.addAttribute("endurobikes",
                this.enduroService.getAllEnduroBikesByUsername(username));
        return "bikes/all-bikes";
    }

}
