package com.mybike.web.controllers;

import com.mybike.service.models.DownhillServiceModel;
import com.mybike.service.services.DownhillService;
import com.mybike.web.controllers.base.BaseController;
import com.mybike.web.models.DownhillCreateModel;
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
public class DownhillController extends BaseController {

    private final DownhillService downhillService;
    private final ModelMapper mapper;

    @GetMapping("/downhill")
    public ModelAndView getDownhill() {
        return super.view("bikes/bikes-downhill");
    }

    @GetMapping("/create/downhill")
    public ModelAndView getCreateDownhill() {
        return super.view("bikes/create-downhill");
    }

    @PostMapping("/create/downhill")
    public ModelAndView createDownhill(@ModelAttribute DownhillCreateModel downhill, Principal principal) throws Exception {
        String username = principal.getName();
        DownhillServiceModel serviceModel = mapper.map(downhill, DownhillServiceModel.class);
        downhillService.createDownhill(username, serviceModel);
        return super.redirect("/bikes/all");
    }

    @GetMapping("/delete/downhill/{id}")
    public String deleteDownhill(@PathVariable Long id, Model model) throws Exception {
        DownhillServiceModel downhillServiceModel = this.downhillService.findDownhillById(id);
        model.addAttribute("dh", downhillServiceModel);
        model.addAttribute("downhillId", id);

        return "bikes/delete-downhill";
    }

    @PostMapping("/delete/downhill/{id}")
    public ModelAndView deleteDownhillConfirm(@PathVariable Long id) throws Exception {
        this.downhillService.deleteDownhill(id);

        return super.redirect("/bikes/all");
    }

    @GetMapping("/edit/downhill/{id}")
    public String editDownhill(@PathVariable Long id, Model model) throws Exception {
       DownhillServiceModel downhillServiceModel = this.downhillService.findDownhillById(id);
        model.addAttribute("dh", downhillServiceModel);
        model.addAttribute("downhillId", id);

        return "bikes/edit-downhill";

    }

    @PostMapping("/edit/downhill/{id}")
    public ModelAndView editDownhillConfirm(@PathVariable Long id,
                                          @ModelAttribute DownhillCreateModel model) throws Exception {
        this.downhillService.editDownhill( id, this.mapper.map(model, DownhillServiceModel.class));

        return super.redirect("/bikes/all");
    }
}
