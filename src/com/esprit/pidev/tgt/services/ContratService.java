///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.esprit.pidev.tgt.services;
//
//import com.esprit.pidev.tgt.entities.Contrat;
//import com.esprit.pidev.tgt.utils.DataSource;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// *
// * @author goldzeo
// */
//
//
//public class ContratService implements IContratService  {
//     private IContratService contratService = new ContratService();
//    private Statement statement;
//
//    @Override
//    public int save(Contrat contrat) throws SQLException {
//       //String req = "INSERT INTO `candidat` (`id`, `id_casting`, `id_entretient`, `nomC`, `cincontrat`, `cv`, `motivation`, `mailaddress`, `tel`) VALUES (NULL, 1, 1, '"+candidat.getNomC()+"', '"+candidat.getCincontrat()+"', '"+candidat.getCv()+"', '"+candidat.getMotivation()+"', '"+candidat.getMailaddress()+"', '"+candidat.getTel()+"')";
//        PreparedStatement preparedStatement= DataSource.getInstance().getConnection().prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
//        preparedStatement.executeUpdate();
//        
//        ResultSet genKeysRs = preparedStatement.getGeneratedKeys();
//        genKeysRs.next();
//        int id = genKeysRs.getInt(1);
//        return id;
//    }
//
//    @Override
//    public List<Contrat> findAll() throws SQLException {
//        List<Contrat> list = new ArrayList<>();
//        ResultSet result = statement.executeQuery("SELECT * FROM `candidat`");
//        while (result.next()) {
//            list.add(extract(result));
//        }
//        return list;
//    }
//
//    @Override
//    public boolean update(Contrat contrat) throws SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void delete(Contrat contrat) throws SQLException {
//       statement.executeUpdate("DELETE FROM `candidat` WHERE id =" + contrat.getId()); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void deleteAll() throws SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public Contrat findById(int id) throws SQLException {
//         ResultSet result = statement.executeQuery("SELECT * FROM `candidat` WHERE id =" + id);
//        return result.next()? extract(result) : null;
//    }
//    private Contrat extract(ResultSet result)throws SQLException{
//        
//            int id = result.getInt("id");
//            String nomC = result.getString("nomC");
//            int cincontrat= result.getInt("cincontrat");
//            String cv= result.getString("cv");
//            String motivation= result.getString("motivation");
//            String mailaddress= result.getString("mailaddress");
//            int tel= result.getInt("tel");
//            
//        return new Contrat();
//    }
//     @Override
//    public boolean update(Contrat contrat) throws SQLException {
//        String reqUpdate="UPDATE `candidat` SET `id`='"+contrat.getId()+"',`id_casting`='"+contrat.getCasting().getId()+"',`id_entretient`='"+contrat.getEntretient().getId()+"',`nomC`='"+contrat.getNomC()+"',`cincontrat`='"+contrat.getCincontrat()+"',`cv`='"+contrat.getCv()+"',`motivation`='"+contrat.getMotivation()+"',`mailaddress`='"+contrat.getMailaddress()+"',`tel`='"+contrat.getTel()+"' WHERE `id` ='"+contrat.getId()+"'";
//         return statement.executeUpdate(reqUpdate)>0; //To change body of generated methods, choose Tools | Templates.
//    }
//}
