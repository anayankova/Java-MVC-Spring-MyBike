package com.mybike.web.api.controllers;

import com.mybike.data.entities.enduro_enum.Brakes;
import com.mybike.data.entities.enduro_enum.Fork;
import com.mybike.data.entities.enduro_enum.Frame;
import com.mybike.data.entities.enduro_enum.Tires;
import com.mybike.web.api.models.EnduroResponseModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class EnduroApiController {
    @GetMapping(value = "/api/enduro")
    public List<EnduroResponseModel> getEnduroBikes(HttpSession session) {
        return List.of(
                new EnduroResponseModel("Enduro for race", Frame.SCOTT, Fork.FOX, Tires.TIRES26, Brakes.MAGURAMT7),
                new EnduroResponseModel("Enduro for trek1", Frame.TREK, Fork.ROCKSHOX, Tires.TIRES26, Brakes.SHIMANOXT),
                new EnduroResponseModel("Enduro for trek2", Frame.TREK, Fork.FOX, Tires.TIRES27, Brakes.SRAMCODER),
                new EnduroResponseModel("Enduro for trek3", Frame.TREK, Fork.ROCKSHOX, Tires.TIRES29, Brakes.SHIMANOXT),
                new EnduroResponseModel("Enduro for high mountain", Frame.SPECIALIZED, Fork.OHLINS, Tires.TIRES29, Brakes.SRAMCODER)
        );
    }
}
