/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.entities;

import java.util.Objects;

/**
 *
 * @author Nadhem
 */
public class Cour {
    private int cour_id;
    private int formation_id;
    private String titre_cour;
    private String description_cour;
    private int duree_cour;
    private int note_cour;
    private String text_cour;
    private String image;

    public Cour(int cour_id, int formation_id, String titre_cour, String description_cour, int duree_cour, int note_cour, String text_cour, String image) {
        this.cour_id = cour_id;
        this.formation_id = formation_id;
        this.titre_cour = titre_cour;
        this.description_cour = description_cour;
        this.duree_cour = duree_cour;
        this.note_cour = note_cour;
        this.text_cour = text_cour;
        this.image = image;
    }

    public int getCour_id() {
        return cour_id;
    }

    public void setCour_id(int cour_id) {
        this.cour_id = cour_id;
    }

    public int getFormation_id() {
        return formation_id;
    }

    public void setFormation_id(int formation_id) {
        this.formation_id = formation_id;
    }

    public String getTitre_cour() {
        return titre_cour;
    }

    public void setTitre_cour(String titre_cour) {
        this.titre_cour = titre_cour;
    }

    public String getDescription_cour() {
        return description_cour;
    }

    public void setDescription_cour(String description_cour) {
        this.description_cour = description_cour;
    }

    public int getDuree_cour() {
        return duree_cour;
    }

    public void setDuree_cour(int duree_cour) {
        this.duree_cour = duree_cour;
    }

    public int getNote_cour() {
        return note_cour;
    }

    public void setNote_cour(int note_cour) {
        this.note_cour = note_cour;
    }

    public String getText_cour() {
        return text_cour;
    }

    public void setText_cour(String text_cour) {
        this.text_cour = text_cour;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Cour{" + "cour_id=" + cour_id + ", formation_id=" + formation_id + ", titre_cour=" + titre_cour + ", description_cour=" + description_cour + ", duree_cour=" + duree_cour + ", note_cour=" + note_cour + ", text_cour=" + text_cour + ", image=" + image + '}';
    }

    
    
    
    
}
