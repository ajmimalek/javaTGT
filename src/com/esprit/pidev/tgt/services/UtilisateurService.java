/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.services;

import com.esprit.pidev.tgt.entities.Compte;
import com.esprit.pidev.tgt.entities.Utilisateur;
import com.esprit.pidev.tgt.enumeration.Genre;
import com.esprit.pidev.tgt.utils.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Seif Henchir
 */
public class UtilisateurService  implements IUtilisateurService{
    
    private Statement statement;
    private CompteService compteService = new CompteService();


    public UtilisateurService() {
        try {
            this.statement = DataSource.getInstance().getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public int save(Utilisateur utilisateur) throws SQLException {
        //testerl'existence d'un compte dans la BD pour assurer la contrainte de cle etrangere
        
        int i = compteService.save(utilisateur.getCompte());
        utilisateur.getCompte().setId(i);
        String req =  "INSERT INTO utilisateur (nom,prenom,dateNaissance,genre,numTel,email,avatarUrl,idCompte) VALUES ('"+utilisateur.getNom()+"', '"+utilisateur.getPrenom()+"', '"+utilisateur.getDateNaissance()+"', '"+utilisateur.getGenre()+"', '"+utilisateur.getNumTel()+"', '"+utilisateur.getEmail()+"', '"+utilisateur.getAvatarUrl()+"', '"+utilisateur.getCompte().getId()+"')";
        PreparedStatement preparedStatement = DataSource.getInstance().getConnection().prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.executeUpdate();
        ResultSet genKeysRs = preparedStatement.getGeneratedKeys();
        genKeysRs.next();
        return genKeysRs.getInt(1);
    }

    @Override
    public List<Utilisateur> findAll() throws SQLException {
        List<Utilisateur> list = new ArrayList<>();
        ResultSet result = statement.executeQuery("SELECT * FROM `utilisateur`");
        while (result.next()) {
            list.add(extractUtilisateur(result));
        }
        return list;
    }

    @Override
    public Utilisateur findById(int id) throws SQLException {
        ResultSet result = statement.executeQuery("SELECT * FROM `utilisateur` WHERE id =" + id);
        return result.next()? extractUtilisateur(result) : null;
    }
    
    private Utilisateur extractUtilisateur(ResultSet result)throws SQLException{
        
            int id = result.getInt("id");
            String nom = result.getString("nom");
            String prenom = result.getString("prenom");
            LocalDate dateNaissance = result.getDate("dateNaissance").toLocalDate();
            Genre genre = Genre.valueOf(result.getString("genre"));
            String numTel = result.getString("numTel");
            String email = result.getString("email");
            String avatarUrl = result.getString("avatarUrl");
            Compte compte = compteService.findById(result.getInt("idCompte"));
        return new Utilisateur(id, nom, prenom, dateNaissance, genre, numTel, email, avatarUrl, compte);
    }

    @Override
    public boolean update(Utilisateur utilisateur) throws SQLException {
         String reqUpdate="UPDATE `utilisateur` SET `nom`='"+utilisateur.getNom()+"',`prenom`='"+utilisateur.getPrenom()+"',`numTel`='"+utilisateur.getNumTel()+"',`email`='"+utilisateur.getEmail()+"' WHERE id = '"+utilisateur.getId()+"'"; 
        return statement.executeUpdate(reqUpdate)>0; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Utilisateur utilisateur) throws SQLException {
         statement.executeUpdate("DELETE FROM `utilisateur` WHERE id =" + utilisateur.getId()); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     public Utilisateur findByUsername(String username) throws SQLException {
         Compte compte = compteService.findByUsername(username);
         Utilisateur utilisateur = null;
         if(compte!=null){
        ResultSet result = statement.executeQuery("SELECT * FROM `utilisateur` WHERE idCompte = '" +compte.getId() +"'");
        System.out.println(result);
        utilisateur = result.next()? extractUtilisateur(result) : null;}
         
        return utilisateur; 
    }

}
