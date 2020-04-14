/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.services;

import com.esprit.pidev.tgt.entities.Candidat;
import com.esprit.pidev.tgt.entities.Casting;
import com.esprit.pidev.tgt.entities.Contrat;
import com.esprit.pidev.tgt.enumeration.TypeContrat;
import com.esprit.pidev.tgt.utils.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author goldzeo
 */


public class ContratService implements IContratService  {
     private CastingService castingService= new CastingService();
      private CandidatService candidatService= new CandidatService();
    private Statement statement;

    @Override
    public int save(Contrat contrat) throws SQLException {
       String req = "INSERT INTO `contrat` (`id`, `id_casting`, `id_candidat`, `salaire`, `typeContrat`, `dureeContart`) VALUES (NULL, '"+contrat.getId_casting().getId()+"', '"+contrat.getIdcandidat().getId()+"', '"+contrat.getSalaire()+"', '"+contrat.getTypeContrat()+"', '"+contrat.getDureeContrat()+"')";
        PreparedStatement preparedStatement= DataSource.getInstance().getConnection().prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.executeUpdate();
        
        ResultSet genKeysRs = preparedStatement.getGeneratedKeys();
        genKeysRs.next();
        int id = genKeysRs.getInt(1);
        return id;
    }

    @Override
    public List<Contrat> findAll() throws SQLException {
        List<Contrat> list = new ArrayList<>();
        ResultSet result = statement.executeQuery("SELECT * FROM `contrat`");
        while (result.next()) {
            list.add(extract(result));
        }
        return list;
    }

    @Override
    public void delete(Contrat contrat) throws SQLException {
       statement.executeUpdate("DELETE FROM `candidat` WHERE id =" + contrat.getId()); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Contrat findById(int id) throws SQLException {
         ResultSet result = statement.executeQuery("SELECT * FROM `contrat` WHERE id =" + id);
        return result.next()? extract(result) : null;
    }
    private Contrat extract(ResultSet result)throws SQLException{
            int id = result.getInt("id");
           Casting casting = castingService.findById(result.getInt("id_casting"));
           Candidat candidat = candidatService.findById(result.getInt("id_candidat"));
           float salaire= result.getFloat("salaire");
           String typeContrat= result.getString("typeContrat");
           int dureeContart= result.getInt("dureeContart");
            
        return new Contrat(id, casting, candidat, salaire, TypeContrat.stage, dureeContart);
    }
     @Override
    public boolean update(Contrat contrat) throws SQLException {
        String reqUpdate="UPDATE `contrat` SET `id`=`"+contrat.getId()+"`,`id_casting`=`"+contrat.getId_casting().getId()+"`,`id_candidat`=`"+contrat.getIdcandidat().getId()+"`,`salaire`=`"+contrat.getSalaire()+"`,`typeContrat`=`"+contrat.getTypeContrat()+"`,`dureeContart`=`"+contrat.getDureeContrat()+"` WHERE 1";
         return statement.executeUpdate(reqUpdate)>0; //To change body of generated methods, choose Tools | Templates.
    }
}
