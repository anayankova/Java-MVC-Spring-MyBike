package com.mybike.web.controllers;

import com.mybike.service.models.BmxServiceModel;
import com.mybike.service.services.BmxService;
import com.mybike.web.controllers.base.BaseController;
import com.mybike.web.models.BmxCreateModel;
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
public class BmxController extends BaseController {

    private final BmxService bmxService;
    private final ModelMapper mapper;

    @GetMapping("/bmx")
    public ModelAndView getBmx() {
        return super.view("bikes/bikes-bmx");
    }

    @GetMapping("/create/bmx")
    public ModelAndView getCreateBmx() {
        return super.view("bikes/create-bmx");
    }

    @PostMapping("/create/bmx")
    public ModelAndView createBmx(@ModelAttribute BmxCreateModel bmx, Principal principal) throws Exception {
        String username = principal.getName();
        BmxServiceModel serviceModel = mapper.map(bmx, BmxServiceModel.class);
        bmxService.createBmx(username, serviceModel);
        return super.redirect("/bikes/all");
    }

    @GetMapping("/delete/bmx/{id}")
    public String deleteBmx(@PathVariable Long id, Model model) throws Exception {
        BmxServiceModel bmxServiceModel = this.bmxService.findBmxById(id);
        model.addAttribute("bmx", bmxServiceModel);
        model.addAttribute("bmxId", id);

        return "bikes/delete-bmx";
    }

    @PostMapping("/delete/bmx/{id}")
    public ModelAndView deleteBmxConfirm(@PathVariable Long id) throws Exception {
        this.bmxService.deleteBmx(id);

        return super.redirect("/bikes/all");
    }

    @GetMapping("/edit/bmx/{id}")
    public String editBmx(@PathVariable Long id, Model model) throws Exception {
        BmxServiceModel bmxServiceModel = this.bmxService.findBmxById(id);
        model.addAttribute("bmx", bmxServiceModel);
        model.addAttribute("bmxId", id);

        return "bikes/edit-bmx";

    }

    @PostMapping("/edit/bmx/{id}")
    public ModelAndView editBmxConfirm(@PathVariable Long id,
                                            @ModelAttribute BmxCreateModel model) throws Exception {
        this.bmxService.editBmx( id, this.mapper.map(model, BmxServiceModel.class));

        return super.redirect("/bikes/all");
    }
}
