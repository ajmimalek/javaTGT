/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.models;

import javafx.scene.control.Button;

/**
 *
 * @author Malek
 */
public class CatégoriePublication {
    
    private int id_cat;
    private String nomCat;
    private Button update;
    private Button delete;

    public CatégoriePublication(int id_cat, String nomCat) {
        this.id_cat = id_cat;
        this.nomCat = nomCat;
        update = new Button("Modifier");
        delete = new Button("Supprimer");
    }

    public CatégoriePublication(int id_cat) {
        this.id_cat = id_cat;
    }
    
    public CatégoriePublication(String nomCat) {
        this.nomCat = nomCat;
    }

    public CatégoriePublication(String nomCat, Button update, Button delete) {
        this.nomCat = nomCat;
        this.update = update;
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

    @Override
    public String toString() {
        return "CatégoriePublication{" + "id_cat=" + id_cat + ", nomCat=" + nomCat + '}';
    }
     
}
