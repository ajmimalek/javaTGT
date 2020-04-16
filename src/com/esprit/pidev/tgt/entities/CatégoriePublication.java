/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.entities;

import com.jfoenix.controls.JFXButton;

/**
 *
 * @author Malek
 */
public class CatégoriePublication {
    
    private int id_cat;
    private String nomCat;
    private JFXButton delete;

    public CatégoriePublication(int id_cat, String nomCat, JFXButton delete) {
        this.id_cat = id_cat;
        this.nomCat = nomCat;
        this.delete = delete;
    }

    public CatégoriePublication(int id_cat) {
        this.id_cat = id_cat;
    }
    
    public CatégoriePublication(String nomCat) {
        this.nomCat = nomCat;
    }

    public CatégoriePublication(String nomCat, JFXButton delete) {
        this.nomCat = nomCat;
        this.delete = delete;
    }

    public int getId_cat() {
        return id_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    public String getNomCat() {
        return nomCat;
    }

    public void setNomCat(String nomCat) {
        this.nomCat = nomCat;
    }

    public JFXButton getDelete() {
        return delete;
    }

    public void setDelete(JFXButton delete) {
        this.delete = delete;
    }
    
    

    @Override
    public String toString() {
        return "CatégoriePublication{" + "id_cat=" + id_cat + ", nomCat=" + nomCat + '}';
    }
     
}
