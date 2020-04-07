/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.utils;

import static com.esprit.pidev.tgt.utils.Configuration.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author goldzeo
 */
public class DataSource {
    private static DataSource dataSource;
    private Connection connection;

    private DataSource() {// "private" :pour controler l'instanciation de cet objet
        try {
            connection = DriverManager.getConnection(URL, USER, MDP);            
            System.out.println("connexion etablie");

        } catch (SQLException e) {
            Alert.showErrorMessage("connexion echouée", "blablabla");
        }
    }

    public static DataSource getInstance() {
        dataSource = dataSource== null ? new DataSource() : dataSource;
        return dataSource ;
    }

    public Connection getConnection() {
        return connection;
    }
}
