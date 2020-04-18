/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.services;
import com.esprit.pidev.tgt.entities.Cour;
import com.esprit.pidev.tgt.entities.Formation;
import com.esprit.pidev.tgt.utils.DataSource;
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

/**
 *
 * @author Nadhem
 */
public class FormationService implements IFormationService {
       private Statement statement;
       ArrayList<Formation> formations = new ArrayList<Formation>();
       public FormationService() {
        try {
            this.statement = DataSource.getInstance().getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(FormationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int save(Formation formation) throws SQLException {
        String req = "INSERT INTO `formation` (`formation_id`,`id`, `titre_formation`, `description_formation`, `image`, `duree_formation`, `note`) VALUES (NULL, 1, '"+formation.getTitre_formation()+"', '"+formation.getDescription_formation()+"', '"+formation.getImage()+"', '"+formation.getDuree_formation()+"', '"+formation.getNote()+"')";
        PreparedStatement preparedStatement= DataSource.getInstance().getConnection().prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.executeUpdate();
        ResultSet genKeysRs = preparedStatement.getGeneratedKeys();
        genKeysRs.next();
        int id = genKeysRs.getInt(1);
        System.out.println("houni");
        System.out.println(id);
        return id;
    }
    
    

    @Override
    public Formation findByUsername(String username) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<Formation> findAll() throws SQLException {
        List<Formation> list = new ArrayList<>();
        ResultSet result = statement.executeQuery("SELECT * FROM `formation`");
        while (result.next()) {
            list.add(extract(result));
        }
        return list;
    }
    
    @Override
    public List<Formation> Recherche(String val) throws SQLException {
        List<Formation> list = new ArrayList<>();
       String requete = "SELECT * FROM `formation` WHERE titre_formation like ?";
            PreparedStatement pst = DataSource.getInstance().getConnection().prepareStatement(requete);
            pst.setString(1,"%"+val+"%");
            pst.executeQuery();
            ResultSet result = pst.getResultSet();
            System.out.println("wsetl service");
            System.out.println(val);
            
        while (result.next()) {
            System.out.println("while");
            list.add(extract(result));
        }
        return list;
    }
    
    
    
    
    @Override
    public List<Formation> TrieTitreASC() throws SQLException {
        List<Formation> list = new ArrayList<>();
       String requete = "SELECT * FROM `formation` ORDER BY titre_formation ASC";
            PreparedStatement pst = DataSource.getInstance().getConnection().prepareStatement(requete);
            pst.executeQuery();
            ResultSet result = pst.getResultSet();
        while (result.next()) {
            list.add(extract(result));
        }
        return list;
    }
    
    
        
    @Override
    public List<Formation> TrieTitreDESC() throws SQLException {
        List<Formation> list = new ArrayList<>();
       String requete = "SELECT * FROM `formation` ORDER BY titre_formation DESC";
            PreparedStatement pst = DataSource.getInstance().getConnection().prepareStatement(requete);
            pst.executeQuery();
            ResultSet result = pst.getResultSet();
        while (result.next()) {
            list.add(extract(result));
        }
        return list;
    }
    
    
     @Override
    public List<Formation> getall() {
        String req = "select * from formation";
        try {
             PreparedStatement pt = DataSource.getInstance().getConnection().prepareStatement(req);
             ResultSet rs = pt.executeQuery(); 
System.out.println("3");
             while (rs.next()) {
                Formation f = new Formation();
                f.setFormation_id(rs.getInt(1));
                f.setTitre_formation(rs.getString(3));
                formations.add(f);
            }
            System.out.println("affichage etablie");
            return formations;
        } catch (SQLException ex) {
            Logger.getLogger(CourService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
     @Override
    public List<Formation> TrieDureeASC() throws SQLException {
        List<Formation> list = new ArrayList<>();
       String requete = "SELECT * FROM `formation` ORDER BY duree_formation ASC";
            PreparedStatement pst = DataSource.getInstance().getConnection().prepareStatement(requete);
            pst.executeQuery();
            ResultSet result = pst.getResultSet();
        while (result.next()) {
            list.add(extract(result));
        }
        return list;
    }
    
     @Override
    public List<Formation> TrieDureeDESC() throws SQLException {
        List<Formation> list = new ArrayList<>();
       String requete = "SELECT * FROM `formation` ORDER BY duree_formation DESC";
            PreparedStatement pst = DataSource.getInstance().getConnection().prepareStatement(requete);
            pst.executeQuery();
            ResultSet result = pst.getResultSet();
        while (result.next()) {
            list.add(extract(result));
        }
        return list;
    }
    
    
    public List<Formation> Participer(Formation f) throws SQLException {
        List<Formation> list1 = new ArrayList<>();
        System.out.println(f.getTitre_formation());
      String requete = "SELECT * FROM `formation` WHERE formation_id="+f.getFormation_id();
            PreparedStatement pst = DataSource.getInstance().getConnection().prepareStatement(requete);
            pst.executeQuery();
            ResultSet result = pst.getResultSet();
        while (result.next()) {
            list1.add(extract(result));
        }    
        return list1;
 
    }
    
    
    
    private Formation extract(ResultSet result)throws SQLException{
            int formation_id = result.getInt("formation_id");
            int id = result.getInt("id");
            String titre = result.getString("titre_formation");
            String description_formation = result.getString("description_formation");
            String image = result.getString("image");
            int duree_formation = result.getInt("duree_formation");
            int note = result.getInt("note");
            
        return new Formation (formation_id,id, titre, description_formation, image, duree_formation, note);
    }
    


  @Override
    public void supprimer(Formation f) {
        try {
            String requete = "DELETE FROM formation WHERE titre_formation=?";
            PreparedStatement pst = DataSource.getInstance().getConnection().prepareStatement(requete);
            pst.setString(1, f.getTitre_formation());
            pst.executeUpdate();
            System.out.println("Formation supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public boolean update(Formation f) {
       try {
            String requete = "UPDATE formation SET titre_formation='"+f.getTitre_formation()+"',description_formation='"+f.getDescription_formation()+"',image='"+f.getImage()+"' WHERE formation_id='"+f.getFormation_id()+"'";
            Statement st = DataSource.getInstance().getConnection().createStatement();
            st.executeUpdate(requete);
            System.out.println("Publication modifiée !");
            return statement.executeUpdate(requete)>0;

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }
    
    
      @Override
    public boolean updateNote(Formation f) {
       try {
            String requete = "UPDATE formation SET note='"+f.getNote()+"' WHERE formation_id='"+f.getFormation_id()+"'";
            Statement st = DataSource.getInstance().getConnection().createStatement();
            st.executeUpdate(requete);
            System.out.println("formation modifiée !");
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
    public void delete(Formation formation) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}

 
    

