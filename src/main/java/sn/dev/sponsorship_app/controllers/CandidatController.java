package sn.dev.sponsorship_app.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sn.dev.sponsorship_app.App;
import sn.dev.sponsorship_app.DBConnection;
import sn.dev.sponsorship_app.entities.Utilisateur;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class CandidatController implements Initializable {

    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadElecteurt();
    }
    DBConnection db = new DBConnection();
    @FXML
    private TableColumn<Utilisateur, Integer> idC;

    @FXML
    private TableView<Utilisateur> loadEP;

    @FXML
    private TableColumn<Utilisateur, String> nomC;

    @FXML
    private TableColumn<Utilisateur, String> prenomC;

    public ObservableList<Utilisateur> getElecteur(){
        ObservableList<Utilisateur> electeurs= FXCollections.observableArrayList();
        String sql = "select count(s.elector),u.nom,u.prenom from sponsorship s,user u where s.elector=u.id and s.candidate="+ App.getAuth().getId();
        try {
            db.initPrepar(sql);
            ResultSet rs = db.executeSelect();
            while (rs.next()){
                Utilisateur elect = new Utilisateur();
                elect.setId(rs.getInt(1));
                elect.setPrenom(rs.getString(2));
                elect.setNom(rs.getString(3));
                electeurs.add(elect);
            }
            db.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        return electeurs;
    }

    public void loadElecteurt() {
        ObservableList<Utilisateur> listeElect = getElecteur();
        loadEP.setItems(listeElect);
        idC.setCellValueFactory(new PropertyValueFactory<Utilisateur, Integer>("id"));
        nomC.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("nom"));
        prenomC.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("prenom"));
    }

}