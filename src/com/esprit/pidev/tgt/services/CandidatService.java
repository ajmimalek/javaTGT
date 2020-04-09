/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.services;

import com.esprit.pidev.tgt.entities.Candidat;
import com.esprit.pidev.tgt.entities.Casting;
import com.esprit.pidev.tgt.entities.Entretien;
import com.esprit.pidev.tgt.enumeration.Role;
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
 * @author goldzeo
 */
public class CandidatService  implements ICandidatService{
    
       private IEntretientService entretientService = new EntretientService();
    private CastingService castingService= new CastingService();
    private Statement statement;
    public CandidatService() {
        try {
            this.statement = DataSource.getInstance().getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CandidatService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int save(Candidat candidat) throws SQLException {
        String req = "INSERT INTO `candidat` (`id`, `id_casting`, `id_entretient`, `nomC`, `cinCondidat`, `cv`, `motivation`, `mailaddress`, `tel`) VALUES (NULL, 1, 1, '"+candidat.getNomC()+"', '"+candidat.getCinCondidat()+"', '"+candidat.getCv()+"', '"+candidat.getMotivation()+"', '"+candidat.getMailaddress()+"', '"+candidat.getTel()+"')";
        PreparedStatement preparedStatement= DataSource.getInstance().getConnection().prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.executeUpdate();
        
        ResultSet genKeysRs = preparedStatement.getGeneratedKeys();
        genKeysRs.next();
        int id = genKeysRs.getInt(1);
        return id;
    }
    
     @Override
    public Candidat findById(int id) throws SQLException {
        ResultSet result = statement.executeQuery("SELECT * FROM `candidat` WHERE id =" + id);
        return result.next()? extract(result) : null;
    }

    @Override
    public Candidat findByUsername(String username) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<Candidat> findAll() throws SQLException {
        List<Candidat> list = new ArrayList<>();
        ResultSet result = statement.executeQuery("SELECT * FROM `candidat`");
        while (result.next()) {
            list.add(extract(result));
        }
        return list;
    }
    
    private Candidat extract(ResultSet result)throws SQLException{
        
            int id = result.getInt("id");
            Casting casting = castingService.findById(result.getInt("id_casting"));
            Entretien entretient = entretientService.findById(result.getInt("id_entretient"));
            String nomC = result.getString("nomC");
            int cinCondidat= result.getInt("cinCondidat");
            String cv= result.getString("cv");
            String motivation= result.getString("motivation");
            String mailaddress= result.getString("mailaddress");
            int tel= result.getInt("tel");
            
        return new Candidat(id, casting, entretient, nomC, cinCondidat, cv, motivation, mailaddress, tel);
    }
    
    public void affecterDate(Candidat condidat)throws SQLException {
        int id= entretientService.save(condidat.getEntretient());
        condidat.getEntretient().setId(id);
        update(condidat);
    }


    @Override
    public boolean update(Candidat condidat) throws SQLException {
        String reqUpdate="UPDATE `candidat` SET `id`='"+condidat.getId()+"',`id_casting`='"+condidat.getCasting().getId()+"',`id_entretient`='"+condidat.getEntretient().getId()+"',`nomC`='"+condidat.getNomC()+"',`cinCondidat`='"+condidat.getCinCondidat()+"',`cv`='"+condidat.getCv()+"',`motivation`='"+condidat.getMotivation()+"',`mailaddress`='"+condidat.getMailaddress()+"',`tel`='"+condidat.getTel()+"' WHERE `id` ='"+condidat.getId()+"'";
         return statement.executeUpdate(reqUpdate)>0; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Candidat condidat) throws SQLException {
          statement.executeUpdate("DELETE FROM `candidat` WHERE id =" + condidat.getId()); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
