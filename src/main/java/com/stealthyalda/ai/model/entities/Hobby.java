/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealthyalda.ai.model.entities;

/**
 *
 * @author WINDOWS
 */
public class Hobby {
    private int hobby_id;
    private String name;

    public int getHobby_id() {
        return hobby_id;
    }

    public void setHobby_id(int hobby_id) {
        this.hobby_id = hobby_id;
    }

    public String getHobby() {
        return name;
    }

    public void setHobby(String hobby) {
        this.name = hobby;
    }
    
}
