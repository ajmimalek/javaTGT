package com.esprit.pidev.tgt.services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.esprit.pidev.tgt.entities.Organisations;
import com.esprit.pidev.tgt.services.IOrganisationsService;
import com.esprit.pidev.tgt.utils.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author islem
 */
public   class OrganisationsService implements IOrganisationsService {
    private Statement statement;
    public OrganisationsService() {
        try {
            this.statement = DataSource.getInstance().getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CandidatService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    //************************************************AJOUTER ORGANISATION************************************************//
    
    /**
     *
     * @param O 
     */
    @Override
    public void ajouterOrganisations(Organisations O) {
        try {
            String requete = "INSERT INTO organisations (NomOrganisation,Apropos,AdresseOrganisation,tel_organisation,Email_Org,LoginOrganisation,PasswordOrganisation) VALUES (?,?,?,?,?,?,?)";
           PreparedStatement preparedStatement= DataSource.getInstance().getConnection().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            //pst.setInt   (1, O.getId());
            preparedStatement.setString(1, O.getNomOrganisation());
            preparedStatement.setString(2, O.getApropos());
            preparedStatement.setString(3, O.getAdresseOrganisation());
            preparedStatement.setInt   (4, O.getTel_organisation());
            preparedStatement.setString(5, O.getEmail_Org());
            preparedStatement.setString(6, O.getLoginOrganisation());
            preparedStatement.setString(7, O.getPasswordOrganisation());

            preparedStatement.executeUpdate();
            System.out.println("Organisation ajoutée !");

        } catch (SQLException ex) {
                       Logger.getLogger(OrganisationsService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
    
    //************************************************AFFICHER ORGANISATION************************************************//
    @Override
    public List<Organisations> afficherOrganisations() {
        List<Organisations> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM organisations";
            PreparedStatement preparedStatement= DataSource.getInstance().getConnection().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                list.add(new Organisations(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getInt(5),rs.getString(6),rs.getString(7),rs.getString(8)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
 
 
    //************************************************SUPPRIMER ORGANISATION************************************************//

   

    @Override
    public void supprimerOrganisations(Organisations O) throws SQLException  {
                       statement.executeUpdate("DELETE FROM `organisations` WHERE id =" + O.getId()); //To change body of generated methods, choose Tools | Templates.

    }
    
   //************************************************MODIFIER ORGANISATION************************************************// 
    
    @Override
    public void modifierOrganisations(Organisations O) {
        try {
            String requete = "UPDATE organisations SET NomOrganisation=? WHERE id=?";
            PreparedStatement preparedStatement= DataSource.getInstance().getConnection().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, O.getNomOrganisation());
            preparedStatement.setInt(2, O.getId());
            preparedStatement.executeUpdate();
            System.out.println("Organisation modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }    

    
       
// ------------------------------------------------------------------------

    
    @Override
    public List<Organisations> findAll() throws SQLException {
        List<Organisations> list = new ArrayList<>();
        ResultSet result = statement.executeQuery("SELECT * FROM `organisations`");
        while (result.next()) {
            list.add(extract(result));
        }
         return list;
        
    }        
     private Organisations extract(ResultSet result)throws SQLException{
        
            int id = result.getInt("id");
            String NomOrganisation = result.getString("NomOrganisation");
            int Apropos= result.getInt("Apropos");
            String AdresseOrganisation= result.getString("AdresseOrganisation");
            int tel_organisation= result.getInt("tel_organisation");
            String Email_Org= result.getString("Email_Org");
            String LoginOrganisation= result.getString("LoginOrganisation");
            String PasswordOrganisation= result.getString("PasswordOrganisation");

                  
            
        return new Organisations(id, NomOrganisation, Apropos, AdresseOrganisation, tel_organisation, Email_Org, LoginOrganisation, PasswordOrganisation);
    }
    
    
    

 

   
    
   
    
    
}
