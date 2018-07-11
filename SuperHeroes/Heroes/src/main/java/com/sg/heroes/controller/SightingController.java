/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.heroes.controller;

import com.sg.heroes.model.Hero;
import com.sg.heroes.model.Location;
import com.sg.heroes.model.Sighting;
import com.sg.heroes.ops.HeroServiceLayer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Keller Martin
 */
@Controller
public class SightingController {

    private HeroServiceLayer service;

    public SightingController(HeroServiceLayer service) {
        this.service = service;
    }

    @RequestMapping(value = "/sightings", method = RequestMethod.GET)
    public String displaySightingPage() {
        return "sightings/sightings";
    }

    @RequestMapping(value = "/sightings/get", method = RequestMethod.GET)
    public String getChoice(Model data) {
        data.addAttribute("heroes", service.getAllHeroes());
        data.addAttribute("locations", service.getAllLocations());
        data.addAttribute("sightings", service.getAllSightings());
        return "sightings/get";
    }

    @RequestMapping(value = "/sightings/getByHero", method = RequestMethod.GET)
    public String getByHero(Model data, HttpServletRequest req) {
        List<String> dates = new ArrayList<>();
        List<Sighting> sightings = 
                service.getSightingsByHeroId(Integer.parseInt(req.getParameter("heroId")));
        for (Sighting s : sightings) {
            dates.add(s.getDate().toLocalDate().toString());
        }
        data.addAttribute("sightings", sightings);
        data.addAttribute("heroes", service.getAllHeroes());
        data.addAttribute("locations", service.getAllLocations());
        data.addAttribute("dates", dates);
        return "sightings/get";
    }

    @RequestMapping(value = "/sightings/getByLocation", method = RequestMethod.GET)
    public String getByLocation(Model data, HttpServletRequest req) {
        List<Sighting> sightings
                = service.getSightingsByLocationId(Integer.parseInt(req.getParameter("locationId")));
        List<String> dates = new ArrayList<>();
        for (Sighting s : sightings) {
            dates.add(s.getDate().toLocalDate().toString());
        }
        data.addAttribute("sightings", sightings);
        data.addAttribute("heroes", service.getAllHeroes());
        data.addAttribute("locations", service.getAllLocations());
        data.addAttribute("dates", dates);
        return "sightings/get";
    }

    @RequestMapping(value = "/sightings/getByDate", method = RequestMethod.GET)
    public String getByDate(Model data, HttpServletRequest req) {
        String date = req.getParameter("date");
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .parseStrict()
                .appendPattern("uuuu-MM-dd")
                .toFormatter()
                .withResolverStyle(ResolverStyle.STRICT);
        LocalDate ld = LocalDate.parse(date, formatter);
        data.addAttribute("sightings", service.getSightingsByDate(ld));
        data.addAttribute("heroes", service.getAllHeroes());
        data.addAttribute("locations", service.getAllLocations());
        data.addAttribute("date", date);
        return "sightings/get";
    }

    @RequestMapping(value = "/sightings/add", method = RequestMethod.GET)
    public String addSighting(Model data) {
        data.addAttribute("heroes", service.getAllHeroes());
        return "sightings/add";
    }

    @RequestMapping(value = "/sightings/confirmAdd", method = RequestMethod.POST)
    public String confirmAdd(Model data, HttpServletRequest req) {
        Sighting sight = new Sighting();

        //heroes
        String[] heroArr = req.getParameterValues("heroId");
        List<Hero> heroes = new ArrayList<>();
        for (String s : heroArr) {
            Integer id = Integer.parseInt(s);
            heroes.add(service.getHero(id));
        }
        sight.setHeroes(heroes);

        //date
        String date = req.getParameter("date");
        String time = req.getParameter("time");
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .parseStrict()
                .appendPattern("uuuu-MM-dd")
                .toFormatter()
                .withResolverStyle(ResolverStyle.STRICT);
        LocalDate ld = LocalDate.parse(date, formatter);
        LocalTime lt = LocalTime.parse(time);
        LocalDateTime ldt = LocalDateTime.of(ld, lt);
        sight.setDate(ldt);

        //location
        Location l = new Location();
        l.setName(req.getParameter("locName"));
        l.setCountry(req.getParameter("country"));
        l.setCity(req.getParameter("city"));
        l.setAddress(req.getParameter("address"));
        l.setDescription("locDesc");
        service.addNewLocation(l);
        sight.setLocation(l);

        data.addAttribute("sighting", sight);
        data.addAttribute("date", date);
        data.addAttribute("time", time);

        return "sightings/addSuccess";
    }
}
