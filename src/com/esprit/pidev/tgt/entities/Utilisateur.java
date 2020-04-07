/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.entities;

import com.esprit.pidev.tgt.enumeration.Genre;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author goldzeo
 */
public class Utilisateur {
private int id;
private String nom;
private String prenom;
private LocalDate dateNaissance;
private Genre genre;
private String numTel;
private String email;
private String avatarUrl;
private Compte compte;  

    public Utilisateur(int id, String nom, String prenom, LocalDate dateNaissance, Genre genre, String numTel, String email, String avatarUrl, Compte compte) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.genre = genre;
        this.numTel = numTel;
        this.email = email;
        this.avatarUrl = avatarUrl;
        this.compte = compte;
    }

    public Utilisateur(String nom, String prenom, LocalDate dateNaissance, Genre genre, String numTel, String email, String avatarUrl, Compte compte) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.genre = genre;
        this.numTel = numTel;
        this.email = email;
        this.avatarUrl = avatarUrl;
        this.compte = compte;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance + ", genre=" + genre + ", numTel=" + numTel + ", email=" + email + ", avatarUrl=" + avatarUrl + ", compte=" + compte + '}';
    }

    
   

}
