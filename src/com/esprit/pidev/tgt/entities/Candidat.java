/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.entities;

import java.util.Objects;

/**
 *
 * @author goldzeo
 */
public class Candidat {
    
    private int id;
    private Casting  casting;
    private Entretien entretient;
    private String nomC;
    private int cinCondidat;
    private String cv;
    private String motivation;
    private String mailaddress;
    private int tel;

    public Candidat(int id, Casting casting, Entretien entretient, String nomC, int cinCondidat, String cv, String motivation, String mailaddress, int tel) {
        this.id = id;
        this.casting = casting;
        this.entretient = entretient;
        this.nomC = nomC;
        this.cinCondidat = cinCondidat;
        this.cv = cv;
        this.motivation = motivation;
        this.mailaddress = mailaddress;
        this.tel = tel;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Casting getCasting() {
        return casting;
    }

    public void setCasting(Casting casting) {
        this.casting = casting;
    }

    public Entretien getEntretient() {
        return entretient;
    }

    public void setEntretient(Entretien entretient) {
        this.entretient = entretient;
    }

    public String getNomC() {
        return nomC;
    }

    public void setNomC(String nomC) {
        this.nomC = nomC;
    }

    public int getCinCondidat() {
        return cinCondidat;
    }

    public void setCinCondidat(int cinCondidat) {
        this.cinCondidat = cinCondidat;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public String getMailaddress() {
        return mailaddress;
    }

    public void setMailaddress(String mailaddress) {
        this.mailaddress = mailaddress;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "Candidat{" + "id=" + id + ", casting=" + casting + ", entretient=" + entretient + ", nomC=" + nomC + ", cinCondidat=" + cinCondidat + ", cv=" + cv + ", motivation=" + motivation + ", mailaddress=" + mailaddress + ", tel=" + tel + '}';
    }
    
    
    
}
