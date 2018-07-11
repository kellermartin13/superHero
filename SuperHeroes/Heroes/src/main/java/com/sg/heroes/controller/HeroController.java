/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.heroes.controller;

import com.sg.heroes.model.Hero;
import com.sg.heroes.model.Organization;
import com.sg.heroes.model.Power;
import com.sg.heroes.ops.HeroServiceLayer;
import java.util.ArrayList;
import java.util.List;
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
public class HeroController {

    private final HeroServiceLayer service;

    public HeroController(HeroServiceLayer service) {
        this.service = service;
    }

    @RequestMapping(value = "/heroes", method = RequestMethod.GET)
    public String displayHeroPage() {
        return "heroes/heroes";
    }

    @RequestMapping(value = "heroes/add", method = RequestMethod.GET)
    public String addHero(Model data) {
        data.addAttribute("powers", service.getAllPowers());
        data.addAttribute("orgs", service.getAllOrganizations());
        return "heroes/add";
    }

    @RequestMapping(value = "heroes/confirmAdd", method = RequestMethod.POST)
    public String addSuccess(HttpServletRequest req, Model data) {
        Hero hero = new Hero();
        hero.setName(req.getParameter("heroName"));
        hero.setDescription(req.getParameter("description"));
        String[] powerArr = req.getParameterValues("powerid");
        List<Power> powers = new ArrayList<>();
        if (powerArr.length > 0) {
            for (String p : powerArr) {
                Integer id = Integer.parseInt(p);
                powers.add(service.getPower(id));
            }
        }
        hero.setPowers(powers);
        service.addHero(hero);
        String[] orgArr = req.getParameterValues("orgId");
        List<Organization> orgs = new ArrayList<>();
        if (orgArr.length > 0) {
            for (String o : orgArr) {
                Integer id = Integer.parseInt(o);
                Organization org = service.getOrg(id);
                orgs.add(org);
                List<Hero> heroes = org.getHeroes();
                heroes.add(hero);
                org.setHeroes(heroes);
                service.updateOrg(org);
            }
        }
        data.addAttribute("hero", hero);
        data.addAttribute("orgs", orgs);
        return "heroes/addSuccess";
    }

    @RequestMapping(value = "/heroes/getAll", method = RequestMethod.GET)
    public String getAllHeroes(Model data) {
        data.addAttribute("heroes", service.getAllHeroes());
        return "heroes/getAll";
    }
    
    @RequestMapping(value = "/heroes/delete/{heroId}", method = RequestMethod.GET)
    public String deleteHero(@PathVariable("heroId") Integer id, Model data){
        data.addAttribute("hero", service.getHero(id));
        return "heroes/delete";
    }
    
    @RequestMapping(value = "/heroes/confirmDelete/{heroId}", method = RequestMethod.GET)
    public String confirmDelete(@PathVariable("heroId") Integer id, Model data){
        String name = service.getHero(id).getName();
        service.deleteHero(id);
        data.addAttribute("name", name);
        return "heroes/deleteSuccess";
    }
    
    @RequestMapping(value = "/heroes/update/{heroId}", method = RequestMethod.GET)
    public String updateHero(@PathVariable("heroId") Integer id, Model data){
        data.addAttribute("hero", service.getHero(id));
        return "heroes/update";
    }
}

