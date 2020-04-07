/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.entities;

/**
 *
 * @author goldzeo
 */
public class Casting {
    
    private int id;
    private String titreCasting;

    public Casting(int id, String status) {
        this.id = id;
        this.titreCasting = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitreCasting() {
        return titreCasting;
    }

    public void setTitreCasting(String titreCasting) {
        this.titreCasting = titreCasting;
    }

    @Override
    public String toString() {
        return "Casting{" + "id=" + id + ", titreCasting=" + titreCasting + '}';
    }
    
    
}
