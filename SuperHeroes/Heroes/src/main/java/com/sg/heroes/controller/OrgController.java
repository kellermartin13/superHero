/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.heroes.controller;

import com.sg.heroes.model.Hero;
import com.sg.heroes.model.Location;
import com.sg.heroes.model.Organization;
import com.sg.heroes.ops.HeroServiceLayer;
import java.util.ArrayList;
import java.util.List;
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
public class OrgController {

    private final HeroServiceLayer service;

    @Inject
    public OrgController(HeroServiceLayer service) {
        this.service = service;
    }

    @RequestMapping(value = "/orgs", method = RequestMethod.GET)
    public String displayOrgPage() {
        return "orgs/orgs";
    }

    @RequestMapping(value = "/orgs/add", method = RequestMethod.GET)
    public String addOrgsPage(Model data) {
        data.addAttribute("heroes", service.getAllHeroes());
        return "orgs/add";
    }

    @RequestMapping(value = "/addNewOrg", method = RequestMethod.POST)
    public String addOrg(HttpServletRequest req, Model data) {
        Location l = new Location();
        l.setName(req.getParameter("orgName"));
        l.setCountry(req.getParameter("country"));
        l.setCity(req.getParameter("city"));
        l.setAddress(req.getParameter("address"));
        l.setDescription("Primary location of " + req.getParameter("orgName"));
        service.addNewLocation(l);
        
        Organization newOrg = new Organization();
        newOrg.setName(req.getParameter("orgName"));
        newOrg.setDescription(req.getParameter("description"));
        
        List<Integer> idList = new ArrayList<>();
        String[] heroArr = req.getParameterValues("heroId");
        for(String string : heroArr){
            idList.add(Integer.parseInt(string));
        }
        List<Hero> heroList = new ArrayList<>();
        for(Integer currentId : idList){
            heroList.add(service.getHero(currentId));
        }
        
        newOrg.setHeroes(heroList);
        newOrg.setLocation(l);
        service.addNewOrg(newOrg);
        
        data.addAttribute("org", newOrg);
        return "orgs/addSuccess";
    }

    @RequestMapping(value = "/orgs/getAll", method = RequestMethod.GET)
    public String getAllOrgs(Model data) {
        List<Organization> orgs = service.getAllOrganizations();
        data.addAttribute("orgs", orgs);
        return "orgs/getAll";
    }
    
    @RequestMapping(value = "/orgs/update/{orgId}", method = RequestMethod.GET)
    public String updateOrg(Model data, @PathVariable ("orgId") Integer id){
        data.addAttribute("org", service.getOrg(id));
        return "orgs/update";
    }
    
    @RequestMapping(value = "/orgs/update", method = RequestMethod.POST)
    public String confirmUpdate(Model data, @ModelAttribute("org") Organization org){
        service.updateOrg(org);
        data.addAttribute("org", org);
        return "orgs/updateSuccess";
    }
    
    @RequestMapping(value = "/orgs/delete/{orgId}", method = RequestMethod.GET)
    public String deleteOrg(Model data, @PathVariable("orgId") Integer id) {
        data.addAttribute("org", service.getOrg(id));
        return "orgs/delete";
    }
    
    @RequestMapping(value = "/orgs/confirmDelete/{orgId}", method = RequestMethod.GET)
    public String confirmDelete(@PathVariable("orgId") Integer id){
        service.deleteOrg(id);
        return "orgs/deleteSuccess";
    }
}
