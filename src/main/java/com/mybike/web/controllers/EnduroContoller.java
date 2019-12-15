package com.mybike.web.controllers;

import com.mybike.service.models.EnduroServiceModel;
import com.mybike.service.services.EnduroService;
import com.mybike.web.controllers.base.BaseController;
import com.mybike.web.models.EnduroCreateModel;
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
public class EnduroContoller extends BaseController {

    private final EnduroService enduroService;
    private final ModelMapper mapper;

    @GetMapping("/enduro")
    public ModelAndView getEnduro() {
        return super.view("bikes/bikes-enduro");
    }

    @GetMapping("/create/enduro")
    public ModelAndView getCreateEnduro() {
        return super.view("bikes/create-enduro");
    }

    @PostMapping("/create/enduro")
    public ModelAndView createEnduro(@ModelAttribute EnduroCreateModel enduro, Principal principal) throws Exception {
        String username = principal.getName();
        EnduroServiceModel serviceModel = mapper.map(enduro, EnduroServiceModel.class);
        enduroService.createEnduro(username, serviceModel);
        return super.redirect("/bikes/all");
    }

    @GetMapping("/delete/enduro/{id}")
    public String deleteEnduro(@PathVariable Long id, Model model) throws Exception {
        EnduroServiceModel enduroServiceModel = this.enduroService.findEnduroById(id);
        model.addAttribute("enduro", enduroServiceModel);
        model.addAttribute("enduroId", id);

        return "bikes/delete-enduro";
    }

    @PostMapping("/delete/enduro/{id}")
    public ModelAndView deleteEnduroConfirm(@PathVariable Long id) throws Exception {
        this.enduroService.deleteEnduro(id);

        return super.redirect("/bikes/all");
    }

    @GetMapping("/edit/enduro/{id}")
    public String editEnduro(@PathVariable Long id, Model model) throws Exception {
        EnduroServiceModel enduroServiceModel = this.enduroService.findEnduroById(id);
        model.addAttribute("enduro", enduroServiceModel);
        model.addAttribute("enduroId", id);

        return "bikes/edit-enduro";

    }

    @PostMapping("/edit/enduro/{id}")
    public ModelAndView editEnduroConfirm(@PathVariable Long id,
                                          @ModelAttribute EnduroCreateModel model) throws Exception {
        this.enduroService.editEnduro( id, this.mapper.map(model, EnduroServiceModel.class));

        return super.redirect("/bikes/all");
    }

}
