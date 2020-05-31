/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.entities.CommentairePublication;
import com.esprit.pidev.tgt.services.CommentairePublicationService;
import java.util.Date;
import java.util.TimerTask;

/**
 *
 * @author ajmim_9xsk8tf
 */
public class FiltrageTask extends TimerTask{
    
    public static CommentairePublication cp = null;
    
    CommentairePublicationService cps = new CommentairePublicationService();
    @Override
    public void run() {
        System.out.println(new Date() + " Execution de ma tache");
        cps.filtrageCommentaires(cp);
    }
    
}
