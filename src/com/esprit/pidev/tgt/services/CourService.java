/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.services;
import com.esprit.pidev.tgt.entities.Cour;
import com.esprit.pidev.tgt.entities.Formation;
import com.esprit.pidev.tgt.utils.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Nadhem
 */
public class CourService implements ICourService {
       private Statement statement;
       public CourService() {
        try {
            this.statement = DataSource.getInstance().getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CourService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int save(Cour cour) throws SQLException {
String req = "INSERT INTO `cour` (`cour_id`,`formations_id`, `titre_cour`, `description_cour`, `duree_cour`, `note_cour`, `text_cour`,`image`) VALUES (NULL,'"+cour.getFormation_id()+"', '"+cour.getTitre_cour()+"', '"+cour.getDescription_cour()+"', '"+cour.getDuree_cour()+"', '"+cour.getNote_cour()+"', '"+cour.getText_cour()+"', '"+cour.getImage()+"')";        PreparedStatement preparedStatement= DataSource.getInstance().getConnection().prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.executeUpdate();
        ResultSet genKeysRs = preparedStatement.getGeneratedKeys();
        genKeysRs.next();
        int id = genKeysRs.getInt(1);
        return id;
    }
    
    

    @Override
    public Cour findByUsername(String username) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<Cour> findAll() throws SQLException {
        List<Cour> list = new ArrayList<>();
        ResultSet result = statement.executeQuery("SELECT * FROM `cour`");
        while (result.next()) {
            list.add(extract(result));
        }
        return list;
    }
    
    private Cour extract(ResultSet result)throws SQLException{
            int cour_id = result.getInt("cour_id");
            int formations_id = result.getInt("formations_id");
            String titre_cour = result.getString("titre_cour");
            String description_cour = result.getString("description_cour");
            int duree_cour = result.getInt("duree_cour");
            int note_cour = result.getInt("note_cour");
            String text_cour = result.getString("text_cour");            
            String image = result.getString("image");

            
        return new Cour (cour_id,formations_id, titre_cour, description_cour,duree_cour, note_cour, text_cour ,image);
    }
    

  @Override
    public void supprimer(Cour c) {
        try {
            String requete = "DELETE FROM cour WHERE titre_cour=?";
            PreparedStatement pst = DataSource.getInstance().getConnection().prepareStatement(requete);
            pst.setString(1, c.getTitre_cour());
            pst.executeUpdate();
            System.out.println("cour supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public boolean update(Cour c) {
       try {
            String requete = "UPDATE cour SET titre_cour='"+c.getTitre_cour()+"',description_cour='"+c.getDescription_cour()+"',image='"+c.getImage()+"' WHERE cour_id='"+c.getCour_id()+"'";
            Statement st = DataSource.getInstance().getConnection().createStatement();
            st.executeUpdate(requete);
            System.out.println("Cour modifiée !");
            return statement.executeUpdate(requete)>0;

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }
    
       @Override
    public boolean updateNote(Cour c) {
       try {
            String requete = "UPDATE cour SET note_cour='"+c.getNote_cour()+"' WHERE cour_id='"+c.getCour_id()+"'";
            Statement st = DataSource.getInstance().getConnection().createStatement();
            st.executeUpdate(requete);
            System.out.println("Cour modifiée !");
            return statement.executeUpdate(requete)>0;

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }
    
    @Override
    public void deleteAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Cour cour) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cour> findByFormationId(int id) throws SQLException {
        List<Cour> courses = findAll();
        
       
        return  courses.stream().filter(e -> e.getFormation_id()== id).collect(Collectors.toList());
    }
    
}

 
    

