/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.entities;

/**
 *
 * @author Nadhem
 */
public class Formation {

private int formation_id;
private int id;
private String titre_formation;
private String description_formation;
private String image;
private int duree_formation;
private int note;

    public Formation(int formation_id, int id, String titre_formation, String description_formation, String image, int duree_formation, int note) {
        this.formation_id = formation_id;
        this.id = id;
        this.titre_formation = titre_formation;
        this.description_formation = description_formation;
        this.image = image;
        this.duree_formation = duree_formation;
        this.note = note;
    }

     public Formation(int formation_id, String titre_formation) {
        this.formation_id = formation_id;
        this.titre_formation = titre_formation;
    }

    public Formation() {
      
    }
    

    public int getFormation_id() {
        return formation_id;
    }

    public void setFormation_id(int formation_id) {
        this.formation_id = formation_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre_formation() {
        return titre_formation;
    }

    public void setTitre_formation(String titre_formation) {
        this.titre_formation = titre_formation;
    }

    public String getDescription_formation() {
        return description_formation;
    }

    public void setDescription_formation(String description_formation) {
        this.description_formation = description_formation;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getDuree_formation() {
        return duree_formation;
    }

    public void setDuree_formation(int duree_formation) {
        this.duree_formation = duree_formation;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Formation{" + "formation_id=" + formation_id + ", titre_formation=" + titre_formation + ", description_formation=" + description_formation + ", image=" + image + ", duree_formation=" + duree_formation + ", note=" + note + '}';
    }


}
