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
public class Hardskill {
    private int hardskill_id;
    private String hardskill;

    public Hardskill(String hardskill) {
        this.hardskill = hardskill;
    }

    public Hardskill() {
    }

    public int getHardskill_id() {
        return hardskill_id;
    }

    public void setHardskill_id(int hardskill_id) {
        this.hardskill_id = hardskill_id;
    }

    public String getHardskill() {
        return hardskill;
    }

    public void setHardskill(String hardskill) {
        this.hardskill = hardskill;
    }
    
}
