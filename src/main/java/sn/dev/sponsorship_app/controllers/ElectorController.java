package sn.dev.sponsorship_app.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sn.dev.sponsorship_app.App;
import sn.dev.sponsorship_app.DBConnection;
import sn.dev.sponsorship_app.entities.Role;
import sn.dev.sponsorship_app.entities.Utilisateur;
import sn.dev.sponsorship_app.repositories.role.Irole;
import sn.dev.sponsorship_app.repositories.role.roleImpl;
import sn.dev.sponsorship_app.tools.Notification;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ElectorController implements Initializable {
    DBConnection db = new DBConnection();

    private Utilisateur candidatS;

    public Utilisateur getCandidatS() {
        return candidatS;
    }

    public void setCandidatS(Utilisateur candidatS) {
        this.candidatS = candidatS;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadCandidat();
    }
    @FXML
    private TableColumn<Utilisateur, Integer> idC;

    @FXML
    private TableView<Utilisateur> loadC;

    @FXML
    private TableColumn<Utilisateur, String> nomC;

    @FXML
    private Button parrainer;

    @FXML
    private TableColumn<Utilisateur, String> prenomC;

    @FXML
    void selectCand(MouseEvent event) {
        setCandidatS( loadC.getSelectionModel().getSelectedItem());
        System.out.println(candidatS.getId());
    }

    public int verifPar(){
        int nbP=0;
        String sql="select count(sponsorship.elector) from sponsorship where sponsorship.elector ="+ App.getAuth().getId();
        try {
            db.initPrepar(sql);
            ResultSet rs = db.executeSelect();
           if(rs.next()){
               nbP=rs.getInt(1);
           }
            db.closeConnection();

        }catch (Exception e){
            throw new RuntimeException();
        }
        return nbP;
    }

    public void parrainage(){
        if(verifPar() != 0){
            Notification.NotifError("Erroe","vous avez deja parraine un candidat ");
        }else{
            String sql="insert into sponsorship values(null,?,?,?)";
            try{
                db.initPrepar(sql);
                db.getPstm().setDate(1, Date.valueOf(LocalDate.now()));
                db.getPstm().setInt(2,App.getAuth().getId());
                db.getPstm().setInt(3,candidatS.getId() );
                db.executeMaj();
                db.closeConnection();
                Notification.NotifSuccess("Success","Parrainage reussit");
            }catch (Exception e){
                throw new RuntimeException();
            }

        }

    }

    public ObservableList<Utilisateur> getCandidat(){
        ObservableList<Utilisateur> candidats= FXCollections.observableArrayList();
        String sql = "select * from user where profil=3";
        try {
            db.initPrepar(sql);
            ResultSet rs = db.executeSelect();
            while (rs.next()){
                Utilisateur cand = new Utilisateur();
                cand.setId(rs.getInt(1));
                cand.setPrenom(rs.getString(2));
                cand.setNom(rs.getString(3));
                candidats.add(cand);
            }
            db.closeConnection();
        }catch (Exception e){
            throw new RuntimeException();
        }
        return candidats;
    }
    public void loadCandidat() {
        ObservableList<Utilisateur> listeCand = getCandidat();
        for(Utilisateur u : listeCand){
            System.out.println(u.getId());
        }
        loadC.setItems(listeCand);
        idC.setCellValueFactory(new PropertyValueFactory<Utilisateur, Integer>("id"));
        nomC.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("nom"));
        prenomC.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("prenom"));
    }

}
