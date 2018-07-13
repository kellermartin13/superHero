/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.heroes.controller;

import com.sg.heroes.model.Location;
import com.sg.heroes.ops.HeroServiceLayer;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    @RequestMapping(value="/locations/confirmAdd", method=RequestMethod.POST)
    public String addLocation(HttpServletRequest req, Model data){
        Location l = new Location();
        l.setName(req.getParameter("name"));
        l.setDescription(req.getParameter("description"));
        l.setCountry(req.getParameter("country"));
        l.setCity(req.getParameter("city"));
        l.setAddress(req.getParameter("address"));
        l.setLatitude(req.getParameter("latitude"));
        l.setLongitude(req.getParameter("longitude"));
        service.addNewLocation(l);
        data.addAttribute("location", l);
        return "locations/addSuccess";
    }
    
    //LOCATION SEARCH
    @RequestMapping(value="/locations/get", method=RequestMethod.GET)
    public String locationSearch(Model data){
        data.addAttribute("locations", service.getAllLocations());
        return "locations/get";
    }
    
    //LOCATION DELETE
    @RequestMapping(value="/locations/delete/{locationId}", method=RequestMethod.GET)
    public String deleteLocation(Model data, @PathVariable ("locationId") Integer id){
        data.addAttribute("location", service.getLocation(id));
        return "locations/delete";
    }
    
    @RequestMapping(value="/locations/confirmDelete/{locationId}", method=RequestMethod.GET)
    public String confirmDelete(Model data, @PathVariable ("locationId") Integer id){
        String name = service.getLocation(id).getName();
        data.addAttribute("name", name);
        service.deleteLocation(id);
        return "locations/deleteSuccess";
    }
}
