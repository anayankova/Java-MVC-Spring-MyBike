package com.mybike.web.controllers;

import com.mybike.service.models.CrosscountryServiceModel;
import com.mybike.service.services.CrosscountryService;
import com.mybike.web.controllers.base.BaseController;
import com.mybike.web.models.CrosscountryCreateModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/bikes")
@AllArgsConstructor
public class CrosscountryController extends BaseController {

    private final CrosscountryService crosscountryService;
    private final ModelMapper mapper;

    @GetMapping("/crosscountry")
    public ModelAndView getCrosscountry() {
        return super.view("bikes/bikes-crosscountry");
    }

    @GetMapping("/create/crosscountry")
    public ModelAndView getCreateCrosscountry() {
        return super.view("bikes/create-crosscountry");
    }

    @PostMapping("/create/crosscountry")
    public ModelAndView createCrosscountry(@ModelAttribute CrosscountryCreateModel crosscountry, Principal principal) throws Exception {
        String username = principal.getName();
        CrosscountryServiceModel serviceModel = mapper.map(crosscountry, CrosscountryServiceModel.class);
        crosscountryService.createCrosscountry(username, serviceModel);
        return super.redirect("/bikes/all");
    }

    @GetMapping("/delete/crosscountry/{id}")
    public String deleteCrosscountry(@PathVariable Long id, Model model) throws Exception {
        CrosscountryServiceModel crosscountryServiceModel = this.crosscountryService.findCrosscountryById(id);
        model.addAttribute("xc", crosscountryServiceModel);
        model.addAttribute("crosscountryId", id);

        return "bikes/delete-crosscountry";
    }

    @PostMapping("/delete/crosscountry/{id}")
    public ModelAndView deleteCrosscountryConfirm(@PathVariable Long id) throws Exception {
        this.crosscountryService.deleteCrosscountry(id);

        return super.redirect("/bikes/all");
    }

    @GetMapping("/edit/crosscountry/{id}")
    public String editCrosscountry(@PathVariable Long id, Model model) throws Exception {
        CrosscountryServiceModel crosscountryServiceModel = this.crosscountryService.findCrosscountryById(id);
        model.addAttribute("xc", crosscountryServiceModel);
        model.addAttribute("crosscountryId", id);

        return "bikes/edit-crosscountry";

    }

    @PostMapping("/edit/crosscountry/{id}")
    public ModelAndView editCrosscountryConfirm(@PathVariable Long id,
                                            @ModelAttribute CrosscountryCreateModel model) throws Exception {
        this.crosscountryService.editCrosscountry( id, this.mapper.map(model, CrosscountryServiceModel.class));

        return super.redirect("/bikes/all");
    }
}
