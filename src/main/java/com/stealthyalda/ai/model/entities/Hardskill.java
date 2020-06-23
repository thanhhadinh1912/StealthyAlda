/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealthyalda.ai.model.entities;

/**
 * @author WINDOWS
 */
public class Hardskill {
    private int hardskillId;
    private String name;

    public Hardskill(String hardskill) {
        this.name = hardskill;
    }

    public Hardskill() {
    }

    public int getHardskillId() {
        return hardskillId;
    }

    public void setHardskillId(int hardskillId) {
        this.hardskillId = hardskillId;
    }

    public String getHardskill() {
        return name;
    }

    public void setHardskill(String hardskill) {
        this.name = hardskill;
    }

}
