/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.entities.Utilisateur;
import com.esprit.pidev.tgt.services.CompteService;
import com.esprit.pidev.tgt.services.UtilisateurService;
import com.esprit.pidev.tgt.utils.AlertMaker;
import com.esprit.pidev.tgt.utils.ConectedUser;
import com.esprit.pidev.tgt.utils.Rooting;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 * FXML Controller class
 *
 * @author Seif Henchir
 */
public class RecupMdpController implements Initializable {

 private UtilisateurService utilisateurService = new UtilisateurService();
    private CompteService compteService = new CompteService();
    @FXML
    private AnchorPane ancorPane;
    @FXML
    private TextField numtel;
    @FXML
    private JFXButton recuperer;
    @FXML
    private TextField username;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

 public static void sendMail(String recepient) throws Exception {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();

        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "25");

        //Your gmail address
        String myAccountEmail = "saifhenchir@gmail.com";
        //Your gmail password
        String password = "Chikos<_24702115";

        //Create a session with account credentials
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        //Prepare email message
        Message message = prepareMessage(session, myAccountEmail, recepient);

        //Send mail
        Transport.send(message);
        System.out.println("Message sent successfully");
    }
        private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("votre mot de passe est ");
            
            String htmlCode = "<h1> votre mot de passe est :"+ConectedUser.getUtilisateur().getCompte().getPassword()+" </h1> <br/> <h2><b>Next Line </b></h2>";
            message.setContent(htmlCode, "text/html");
            return message;
        } catch (Exception ex) {
            //Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @FXML
    private void btnrecuperer(ActionEvent event) throws Exception {
        try{
        Utilisateur utilisateur = utilisateurService.findByUsername(this.username.getText());
        
        if (utilisateur!= null && utilisateur.getNumTel().equals(this.numtel.getText()))
                {
           
            ConectedUser.setUtilisateur(utilisateur);
 
        }
        }catch(SQLException ex){
        
        }
        sendMail("saifhenchir@gmail.com");
    }
    

    
}
