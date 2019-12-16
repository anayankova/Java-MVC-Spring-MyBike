package com.mybike.web.controllers;

import com.mybike.service.models.RoadServiceModel;
import com.mybike.service.services.RoadService;
import com.mybike.web.controllers.base.BaseController;
import com.mybike.web.models.RoadCreateModel;
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
public class RoadController extends BaseController {

    private final RoadService roadService;
    private final ModelMapper mapper;

    @GetMapping("/road")
    public ModelAndView getRoad() {
        return super.view("bikes/bikes-road");
    }

    @GetMapping("/create/road")
    public ModelAndView getCreateRoad() {
        return super.view("bikes/create-road");
    }

    @PostMapping("/create/road")
    public ModelAndView createRoad(@ModelAttribute RoadCreateModel bmx, Principal principal) throws Exception {
        String username = principal.getName();
        RoadServiceModel serviceModel = mapper.map(bmx, RoadServiceModel.class);
        roadService.createRoad(username, serviceModel);
        return super.redirect("/bikes/all");
    }

    @GetMapping("/delete/road/{id}")
    public String deleteRoad(@PathVariable Long id, Model model) throws Exception {
        RoadServiceModel roadServiceModel = this.roadService.findRoadById(id);
        model.addAttribute("road", roadServiceModel);
        model.addAttribute("roadId", id);

        return "bikes/delete-road";
    }

    @PostMapping("/delete/road/{id}")
    public ModelAndView deleteRoadConfirm(@PathVariable Long id) throws Exception {
        this.roadService.deleteRoad(id);

        return super.redirect("/bikes/all");
    }

    @GetMapping("/edit/road/{id}")
    public String editRoad(@PathVariable Long id, Model model) throws Exception {
        RoadServiceModel roadServiceModel = this.roadService.findRoadById(id);
        model.addAttribute("road", roadServiceModel);
        model.addAttribute("roadId", id);

        return "bikes/edit-road";

    }

    @PostMapping("/edit/road/{id}")
    public ModelAndView editRoadConfirm(@PathVariable Long id,
                                       @ModelAttribute RoadCreateModel model) throws Exception {
        this.roadService.editRoad( id, this.mapper.map(model, RoadServiceModel.class));

        return super.redirect("/bikes/all");
    }
}
