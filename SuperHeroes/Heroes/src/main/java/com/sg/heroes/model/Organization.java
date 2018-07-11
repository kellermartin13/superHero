/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.heroes.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Keller Martin
 */
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString @EqualsAndHashCode
public class Organization {
    private Integer orgId;
    private String name;
    private String description;
    private Location location;
    private List<Hero> heroes;
}
