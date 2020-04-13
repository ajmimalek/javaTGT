/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Malek
 */
public class DataSource {
    private final String login = "root";
    private final String mdp = "";
    private final String url = "jdbc:mariadb://localhost:3306/tgtbd";
    private Connection connection;
    //2éme étape: Créer une attribut static du meme type de la classe
    private static DataSource instance;
    
    // Premiere etape de singleton : rendre constructeur privée.
    private DataSource() {
        try {
            connection = DriverManager.getConnection(url,login,mdp);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    //3éme étape: Créer une méthode de la classe pour assurer la monoconnexion
    public static DataSource getInstance(){
        if (instance==null){
            instance = new DataSource();
        }
        return instance;
    }
    
    public Connection getConnection() {
        return connection;
    }
    
}
