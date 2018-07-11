/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.heroes.controller;

import com.sg.heroes.model.Sighting;
import com.sg.heroes.ops.HeroServiceLayer;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Keller Martin
 */
@Controller
public class indexController {
    
    private final HeroServiceLayer service;

    public indexController(HeroServiceLayer service) {
        this.service = service;
    }
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String displayHomePage(Model data) {
        List<Sighting> sightings = service.getRecentSightings();
        data.addAttribute("sightings", sightings);
        return "index";
    }
}
