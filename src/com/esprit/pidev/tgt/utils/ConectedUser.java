/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.utils;

import com.esprit.pidev.tgt.entities.Utilisateur;

/**
 *
 * @author goldzeo
 */
public class ConectedUser {
    public static Utilisateur utilisateur;

    public static Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public static void setUtilisateur(Utilisateur utilisateur) {
        ConectedUser.utilisateur = utilisateur;
    }
}
