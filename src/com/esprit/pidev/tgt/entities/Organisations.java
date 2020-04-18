/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.entities;

import java.util.Objects;

/**
 *
 * @author islem
 */
public class Organisations {
     private int id;
     private String NomOrganisation;
     private String Apropos;
     private String AdresseOrganisation;
     private int tel_organisation;
     private String Email_Org;
     private String LoginOrganisation;
     private String PasswordOrganisation;

    public Organisations() {
    }

    public Organisations(int id) {
        this.id = id;
    }

    public Organisations(int id, String NomOrganisation) {
        this.id = id;
        this.NomOrganisation = NomOrganisation;
    }

    public Organisations(String NomOrganisation, String Apropos, String AdresseOrganisation, int tel_organisation, String Email_Org, String LoginOrganisation, String PasswordOrganisation) {
        this.NomOrganisation = NomOrganisation;
        this.Apropos = Apropos;
        this.AdresseOrganisation = AdresseOrganisation;
        this.tel_organisation = tel_organisation;
        this.Email_Org = Email_Org;
        this.LoginOrganisation = LoginOrganisation;
        this.PasswordOrganisation = PasswordOrganisation;
    }

   

    public Organisations(int id, String NomOrganisation, String Apropos, String AdresseOrganisation, int tel_organisation, String Email_Org, String LoginOrganisation, String PasswordOrganisation) {
        this.id = id;
        this.NomOrganisation = NomOrganisation;
        this.Apropos = Apropos;
        this.AdresseOrganisation = AdresseOrganisation;
        this.tel_organisation = tel_organisation;
        this.Email_Org = Email_Org;
        this.LoginOrganisation = LoginOrganisation;
        this.PasswordOrganisation = PasswordOrganisation;
    }

    public Organisations(int id, String NomOrganisation, int Apropos, String AdresseOrganisation, int tel_organisation, String Email_Org, String LoginOrganisation, String PasswordOrganisation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.id;
        hash = 59 * hash + Objects.hashCode(this.NomOrganisation);
        hash = 59 * hash + Objects.hashCode(this.Apropos);
        hash = 59 * hash + Objects.hashCode(this.AdresseOrganisation);
        hash = 59 * hash + this.tel_organisation;
        hash = 59 * hash + Objects.hashCode(this.Email_Org);
        hash = 59 * hash + Objects.hashCode(this.LoginOrganisation);
        hash = 59 * hash + Objects.hashCode(this.PasswordOrganisation);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Organisations other = (Organisations) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomOrganisation() {
        return NomOrganisation;
    }

    public void setNomOrganisation(String NomOrganisation) {
        this.NomOrganisation = NomOrganisation;
    }

    public String getApropos() {
        return Apropos;
    }

    public void setApropos(String Apropos) {
        this.Apropos = Apropos;
    }

    public String getAdresseOrganisation() {
        return AdresseOrganisation;
    }

    public void setAdresseOrganisation(String AdresseOrganisation) {
        this.AdresseOrganisation = AdresseOrganisation;
    }

    public int getTel_organisation() {
        return tel_organisation;
    }

    public void setTel_organisation(int tel_organisation) {
        this.tel_organisation = tel_organisation;
    }

    

    public String getEmail_Org() {
        return Email_Org;
    }

    public void setEmail_Org(String Email_Org) {
        this.Email_Org = Email_Org;
    }

    public String getLoginOrganisation() {
        return LoginOrganisation;
    }

    public void setLoginOrganisation(String LoginOrganisation) {
        this.LoginOrganisation = LoginOrganisation;
    }

    public String getPasswordOrganisation() {
        return PasswordOrganisation;
    }

    public void setPasswordOrganisation(String PasswordOrganisation) {
        this.PasswordOrganisation = PasswordOrganisation;
    }

    @Override
    public String toString() {
        return "organisations{" + "id=" + id + ", NomOrganisation=" + NomOrganisation + ", Apropos=" + Apropos + ", AdresseOrganisation=" + AdresseOrganisation + ", tel_organisation=" + tel_organisation + ", Email_Org=" + Email_Org + ", LoginOrganisation=" + LoginOrganisation + ", PasswordOrganisation=" + PasswordOrganisation + '}';
    }
     
     
}
