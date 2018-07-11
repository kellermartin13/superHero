/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.heroes.model;

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
public class Power {
    private Integer powerId;
    private String name;
    private String description;
}
