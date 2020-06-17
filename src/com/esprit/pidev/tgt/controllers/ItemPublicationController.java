/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.entities.Publication;
import com.esprit.pidev.tgt.services.CommentairePublicationService;
import com.esprit.pidev.tgt.services.PublicationService;
import com.esprit.pidev.tgt.utils.AlertMaker;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author ajmim_9xsk8tf
 */
public class ItemPublicationController implements Initializable {

    @FXML // fx:id="item"
    private AnchorPane item; // Value injected by FXMLLoader
    @FXML
    private JFXButton btn_edit;
    @FXML
    private FontAwesomeIconView edit_icon;
    @FXML
    private JFXButton btn_delete;
    @FXML
    private FontAwesomeIconView delete_icon;
    @FXML
    private JFXButton comments_btn;
    @FXML
    private FontAwesomeIconView comments_icon;
    @FXML
    private Label contenu;
    @FXML
    private Label day;
    @FXML
    private Label month;
    @FXML
    private Label year;
    @FXML
    private Label time;
    @FXML
    private Label ratingPub;
    @FXML
    private Rating rating;
    @FXML
    private MediaView mediaView;
    @FXML
    private Label position;
    @FXML
    private JFXProgressBar cinq;

    @FXML
    private JFXProgressBar quatre;

    @FXML
    private JFXProgressBar trois;

    @FXML
    private JFXProgressBar deux;

    @FXML
    private JFXProgressBar un;
    

    PublicationService ps = new PublicationService();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn_edit.addEventHandler(MouseEvent.MOUSE_ENTERED, (event) -> {
            btn_edit.setStyle("-fx-text-fill: #cc1573");
            edit_icon.setStyle("-fx-fill: #cc1573");
        });
        btn_delete.addEventHandler(MouseEvent.MOUSE_ENTERED, (event) -> {
            btn_delete.setStyle("-fx-text-fill: #cc1573");
            delete_icon.setStyle("-fx-fill: #cc1573");
        });
        comments_btn.addEventHandler(MouseEvent.MOUSE_ENTERED, (event) -> {
            comments_btn.setStyle("-fx-text-fill: #cc1573");
            comments_icon.setStyle("-fx-fill: #cc1573");
        });
        btn_edit.addEventHandler(MouseEvent.MOUSE_EXITED, (event) -> {
            btn_edit.setStyle("-fx-text-fill: BLACK");
            edit_icon.setStyle("-fx-fill: BLACK");
        });
        btn_delete.addEventHandler(MouseEvent.MOUSE_EXITED, (event) -> {
            btn_delete.setStyle("-fx-text-fill: BLACK");
            delete_icon.setStyle("-fx-fill: BLACK");
        });
        comments_btn.addEventHandler(MouseEvent.MOUSE_EXITED, (event) -> {
            comments_btn.setStyle("-fx-text-fill: BLACK");
            comments_icon.setStyle("-fx-fill: BLACK");
        });
    }

    public void showPublication(Publication publication, Node[] nodes, int i, int last) {
        contenu.setText(publication.getContenu());
        String minutes = null;
        if (publication.getDatePub().toLocalDateTime().getMinute()<10) {
            minutes = "0"+publication.getDatePub().toLocalDateTime().getMinute();
        } else {
            minutes = Integer.toString(publication.getDatePub().toLocalDateTime().getMinute());
        }
        time.setText(Integer.toString(publication.getDatePub().toLocalDateTime().getHour()) + ":" + minutes );
        day.setText(Integer.toString(publication.getDatePub().toLocalDateTime().getDayOfMonth()));
        year.setText(Integer.toString(publication.getDatePub().toLocalDateTime().getYear()));
        String s = publication.getDatePub().toLocalDateTime().getMonth().getDisplayName(TextStyle.SHORT, Locale.FRANCE);
        month.setText(s.toUpperCase());
        position.setText(publication.getLocalisation());
        double val = publication.getRatingPub();
        val = val * 10;
        val = Math.round(val);
        val = val / 10;
        ratingPub.setText(Double.toString(val));
        rating.setRating(publication.getRatingPub());
        cinq.setProgress(Double.parseDouble(Float.toString(ps.calculCinqEtoiles(publication))));
        quatre.setProgress(ps.calculQuatreEtoiles(publication));
        trois.setProgress(ps.calculTroisEtoiles(publication));
        deux.setProgress(ps.calculDeuxEtoiles(publication));
        un.setProgress(ps.calculUneEtoile(publication));
      
        try {
            /**
             * ** Objets Media, MediaPlayer et MediaView *****
             */
            String path = null;
            if (publication.getVideo().startsWith("file:/")) {
                path = publication.getVideo();
            } else if (!publication.getVideo().startsWith("file")){
                path = "file:/C:/wamp64/www/TGTWeb/web/uploads/assets/"+publication.getVideo();
            }
            //Media
            Media media = new Media(path);
            //MediaPlayer
            MediaPlayer mediaplayer = new MediaPlayer(media);
            //MediaView
            mediaView.setMediaPlayer(mediaplayer);
            mediaView.addEventHandler(MouseEvent.MOUSE_ENTERED, (event) -> {
                mediaplayer.play();
            });
            mediaView.addEventHandler(MouseEvent.MOUSE_EXITED, (event) -> {
                mediaplayer.stop();
            });
        } catch (MediaException ex) {
            AlertMaker.showErrorMessage(ex);
            ps.supprimer(publication);
            item.getChildren().clear();
        }
        btn_delete.addEventHandler(ActionEvent.ACTION, (event) -> {
            Optional<ButtonType> result = AlertMaker.showConfirmationAlert("Supprimer Publication", "Voulez-vous vraiment supprimer cette publication ?");
            if (result.get() == ButtonType.OK) {
                ps.supprimer(publication);
                item.getChildren().clear();
                // nodes[i + 1] = nodes[i];
            }
        });
        btn_edit.addEventHandler(ActionEvent.ACTION, (event) -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/esprit/pidev/tgt/views/ModifPublication.fxml"));
                ModifPublicationController.publication = publication;
                ModifPublicationController controller = new ModifPublicationController();
                loader.setController(controller);
                Parent parent = loader.load();
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Modifier Publication");
                stage.getIcons().add(new Image("file:favicon.png"));
                stage.setScene(new Scene(parent));
                stage.show();
                controller.ModifierPublication(publication, stage, contenu, position, mediaView);
            } catch (IOException ex) {
                AlertMaker.showErrorMessage(ex);
            }
        });
        comments_btn.addEventHandler(ActionEvent.ACTION, (event) -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/esprit/pidev/tgt/views/Commentaires.fxml"));
                CommentairesController cc = new CommentairesController();
                CommentairePublicationService cps = new CommentairePublicationService();
                loader.setController(cc);
                Parent parent = loader.load();
                cc.oblist.addAll(cps.afficher(publication));
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Liste des commentaires");
                stage.getIcons().add(new Image("file:favicon.png"));
                stage.setScene(new Scene(parent));
                stage.show();
                cc.AjouterCommentaire(publication);
            } catch (IOException ex) {
                AlertMaker.showErrorMessage(ex);
            }
        });
    }

}
