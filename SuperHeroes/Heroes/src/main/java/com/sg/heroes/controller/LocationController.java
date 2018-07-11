/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.heroes.controller;

import com.sg.heroes.ops.HeroServiceLayer;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Keller Martin
 */
@Controller
public class LocationController {
    
    private final HeroServiceLayer service;
    
    @Inject
    public LocationController(HeroServiceLayer service){
        this.service = service;
    }
    
    @RequestMapping(value="/locations", method=RequestMethod.GET)
    public String displayLocationPage() {
        return "locations/locations";
    }
    
    //ADD NEW LOCATION
    @RequestMapping(value="/locations/add", method=RequestMethod.GET)
    public String addLocationPage(){
        return "locations/add";
    }
}
