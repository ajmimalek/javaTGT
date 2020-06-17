/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.services;

import com.esprit.pidev.tgt.entities.Publication;
import com.esprit.pidev.tgt.utils.AlertMaker;
import com.esprit.pidev.tgt.utils.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Malek
 */
public class PublicationService implements IServices<Publication> {

    Connection connection = DataSource.getInstance().getConnection();

    @Override
    public void ajouter(Publication t) {
        try {
            String req = "ALTER TABLE publication AUTO_INCREMENT = " + Statement.RETURN_GENERATED_KEYS;
            String requete = "INSERT INTO publication (contenu,video,localisation,datePub,ratingPub,id_cat) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(requete);
            PreparedStatement ps1 = connection.prepareStatement(req);
            ps.setString(1, t.getContenu());
            ps.setString(2, t.getVideo());
            ps.setString(3, t.getLocalisation());
            ps.setTimestamp(4, t.getDatePub());
            ps.setFloat(5, 0);
            ps.setInt(6, t.getId_cat());
            ps1.execute(req);
            ps.executeUpdate();
            System.out.println("Publication ajoutée !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Publication t) {
        try {
            String requete = "DELETE FROM publication WHERE id_pub=?";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setInt(1, t.getId_pub());
            pst.executeUpdate();
            System.out.println("Publication" + t.getId_pub() + " supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Publication t) {
        try {
            String requete = "UPDATE publication SET contenu=? ,video=? ,localisation=?,id_cat=? WHERE id_pub=?";
            PreparedStatement st = connection.prepareStatement(requete);
            st.setString(1, t.getContenu());
            st.setString(2, t.getVideo());
            st.setString(3, t.getLocalisation());
            st.setInt(4, t.getId_cat());
            st.setInt(5, t.getId_pub());
            st.executeUpdate();
            System.out.println("Publication modifiée !");
            System.out.println(t);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<Publication> afficher() {
        List<Publication> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM publication";
            PreparedStatement pst = connection.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Publication p = new Publication(rs.getInt(1));
                p.setContenu(rs.getString("contenu"));
                p.setVideo(rs.getString("video"));
                p.setLocalisation(rs.getString("localisation"));
                p.setDatePub(rs.getTimestamp("datePub"));
                p.setRatingPub(rs.getFloat("ratingPub"));
                p.setId_cat(rs.getInt("id_cat"));
                list.add(p);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    public float calculRatingPub(Publication t) {
        float ratingPub = 0;
        try {
            String requete = "SELECT AVG(ratingComm) FROM Commentairepublication "
                    + "WHERE id_pub=?";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setInt(1, t.getId_pub());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                ratingPub = rs.getFloat(1);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return ratingPub;
    }

    public String getAddress() {
        String systemipaddress = null;
        try {
            URL url_name = new URL("http://bot.whatismyipaddress.com");
            BufferedReader sc = new BufferedReader(new InputStreamReader(url_name.openStream()));
            // reads system IPAddress 
            systemipaddress = sc.readLine().trim();
        } catch (IOException e) {
            systemipaddress = "Cannot Execute Properly";
            AlertMaker.showErrorMessage("ERREUR !", e.getCause().toString());
        }
        return systemipaddress;
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        }
    }

    public String parseAddress() throws IOException {
        String jsonString = "http://free.ipwhois.io/json/" + getAddress();
        JSONObject obj = readJsonFromUrl(jsonString);
        String city = obj.getString("city");
        return city;
    }

    public float calculCinqEtoiles(Publication t) {
        int nbcomm = 0;
        int nbcommcinq = 0;
        float result = 0;
        try {
            String requete = "SELECT count(*) FROM Commentairepublication "
                    + "WHERE id_pub=?";
            String requete1 = "SELECT count(*) FROM Commentairepublication "
                    + "WHERE id_pub=? AND ratingComm=5";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setInt(1, t.getId_pub());
            PreparedStatement pst1 = connection.prepareStatement(requete1);
            pst1.setInt(1, t.getId_pub());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                nbcomm = rs.getInt(1);
                if (nbcomm==0) {
                    nbcomm = 1;
                }
                System.out.println("nbcomm"+ nbcomm);
            }
            ResultSet rs1 = pst1.executeQuery();
            if (rs1.next()) {
                nbcommcinq = rs1.getInt(1);
                System.out.println("nbcommcinq"+ nbcommcinq);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println(nbcommcinq/nbcomm);
        result = nbcommcinq / nbcomm;
        return result;
    }

    public float calculQuatreEtoiles(Publication t) {
        int nbcomm = 0;
        int nbcommquatre = 0;
        try {
            String requete = "SELECT count(*) FROM Commentairepublication "
                    + "WHERE id_pub=?";
            String requete1 = "SELECT count(*) FROM Commentairepublication "
                    + "WHERE id_pub=? AND ratingComm=4";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setInt(1, t.getId_pub());
            PreparedStatement pst1 = connection.prepareStatement(requete1);
            pst1.setInt(1, t.getId_pub());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                nbcomm = rs.getInt(1);
                if (nbcomm==0) {
                    nbcomm = 1;
                }
            }
            ResultSet rs1 = pst1.executeQuery();
            if (rs1.next()) {
                nbcommquatre = rs1.getInt(1);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return nbcommquatre / nbcomm;
    }

    public float calculTroisEtoiles(Publication t) {
        int nbcomm = 0;
        int nbcommtrois = 0;
        try {
            String requete = "SELECT count(*) FROM Commentairepublication "
                    + "WHERE id_pub=?";
            String requete1 = "SELECT count(*) FROM Commentairepublication "
                    + "WHERE id_pub=? AND ratingComm=3";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setInt(1, t.getId_pub());
            PreparedStatement pst1 = connection.prepareStatement(requete1);
            pst1.setInt(1, t.getId_pub());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                nbcomm = rs.getInt(1);
                if (nbcomm==0) {
                    nbcomm = 1;
                }
            }
            ResultSet rs1 = pst1.executeQuery();
            if (rs1.next()) {
                nbcommtrois = rs1.getInt(1);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return nbcommtrois / nbcomm;
    }

    public float calculDeuxEtoiles(Publication t) {
        int nbcomm = 0;
        int nbcommdeux = 0;
        try {
            String requete = "SELECT count(*) FROM Commentairepublication "
                    + "WHERE id_pub=?";
            String requete1 = "SELECT count(*) FROM Commentairepublication "
                    + "WHERE id_pub=? AND ratingComm=2";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setInt(1, t.getId_pub());
            PreparedStatement pst1 = connection.prepareStatement(requete1);
            pst1.setInt(1, t.getId_pub());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                nbcomm = rs.getInt(1);
                if (nbcomm==0) {
                    nbcomm = 1;
                }
            }
            ResultSet rs1 = pst1.executeQuery();
            if (rs1.next()) {
                nbcommdeux = rs1.getInt(1);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return nbcommdeux / nbcomm;
    }

    public float calculUneEtoile(Publication t) {
        int nbcomm = 0;
        int nbcommun = 0;
        try {
            String requete = "SELECT count(*) FROM Commentairepublication "
                    + "WHERE id_pub=?";
            String requete1 = "SELECT count(*) FROM Commentairepublication "
                    + "WHERE id_pub=? AND ratingComm=1";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setInt(1, t.getId_pub());
            PreparedStatement pst1 = connection.prepareStatement(requete1);
            pst1.setInt(1, t.getId_pub());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                nbcomm = rs.getInt(1);
                if (nbcomm==0) {
                    nbcomm = 1;
                }
            }
            ResultSet rs1 = pst1.executeQuery();
            if (rs1.next()) {
                nbcommun = rs1.getInt(1);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return nbcommun / nbcomm;
    }
}
