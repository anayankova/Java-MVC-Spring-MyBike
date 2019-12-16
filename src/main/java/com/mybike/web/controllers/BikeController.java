package com.mybike.web.controllers;

import com.mybike.service.services.BmxService;
import com.mybike.service.services.CrosscountryService;
import com.mybike.service.services.DownhillService;
import com.mybike.service.services.EnduroService;
import com.mybike.web.controllers.base.BaseController;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/bikes")
@AllArgsConstructor
public class BikeController extends BaseController {

    private final EnduroService enduroService;
    private final DownhillService downhillService;
    private final CrosscountryService crosscountryService;
    private final BmxService bmxService;

    @GetMapping("")
    public ModelAndView getBikes() {
        return super.view("bikes/bikes");
    }

    @GetMapping("/create")
    public ModelAndView getCreateBike() {
        return super.view("bikes/create-bike");
    }

    @GetMapping("/all")
    public String getAllBikesByUsername(Model model, Principal principal) {
        String username = principal.getName();
        model.addAttribute("endurobikes",
                this.enduroService.getAllEnduroBikesByUsername(username));
        model.addAttribute("downhillbikes",
                this.downhillService.getAllDownhillBikesByUsername(username));
        model.addAttribute("crosscountrybikes",
                this.crosscountryService.getAllCrosscountryBikesByUsername(username));
        model.addAttribute("bmxbikes",
                this.bmxService.getAllBmxBikesByUsername(username));
        return "bikes/all-bikes";
    }

}
