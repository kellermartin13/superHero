/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.heroes.controller;

import com.sg.heroes.model.Power;
import com.sg.heroes.ops.HeroServiceLayer;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Keller Martin
 */
@Controller
public class PowerController {
    
    private final HeroServiceLayer service;
    
    @Inject
    public PowerController(HeroServiceLayer service){
        this.service = service;
    }
    
   @RequestMapping(value="/powers", method=RequestMethod.GET)
   public String displayPowerPage() {
       return "powers/powers";
   }
   
   //ADD NEW POWER
   @RequestMapping(value="/powers/add", method=RequestMethod.GET)
   public String addPowerPage() {
       return "powers/add";
   }
   
   @RequestMapping(value="/addNewPower", method=RequestMethod.POST)
   public String addNewPower(HttpServletRequest req, Model data) {
       Power newPower = new Power();
       String name = req.getParameter("powerName");
       String description = req.getParameter("desc");
       newPower.setName(name);
       newPower.setDescription(description);
       service.addNewPower(newPower);
       data.addAttribute("name", newPower.getName());
       data.addAttribute("desc", newPower.getDescription());
       return "powers/addSuccess";
   }
   
   //GET ALL POWERS
   @RequestMapping(value="/powers/getAll", method=RequestMethod.GET)
   public String displayAllPage(Model data) {
       data.addAttribute("powers", service.getAllPowers());
       return "powers/getAll";
   }
   
   //UPDATE POWER
   @RequestMapping(value="/powers/update/{powerId}", method=RequestMethod.GET)
   public String updatePage(Model data, @PathVariable ("powerId") Integer id) {
       data.addAttribute("power", service.getPower(id));
       return "powers/update";
   }
   
   @RequestMapping(value="/powers/update", method=RequestMethod.POST)
   public String confirmUpdate(Model data, @ModelAttribute("power") Power power) {
       service.updatePower(power);
       data.addAttribute("power", power);
       return "powers/updateSuccess";
   }
   
   //DELETE POWER
   @RequestMapping(value="/powers/delete/{powerId}", method=RequestMethod.GET)
   public String deletePage(Model data, @PathVariable("powerId") Integer powerId) {
       data.addAttribute("power", service.getPower(powerId));
       return "powers/delete";
   }
   
   @RequestMapping(value="/powers/confirmDelete/{powerId}", method=RequestMethod.GET)
   public String confirmDelete(Model data, @PathVariable("powerId") Integer powerId) {
       data.addAttribute("power", service.getPower(powerId));
       service.deletePower(powerId);
       return "powers/deleteSuccess";
   }
   
   
}
