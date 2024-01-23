package sn.dev.sponsorship_app.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sn.dev.sponsorship_app.DBConnection;
import sn.dev.sponsorship_app.entities.Role;
import sn.dev.sponsorship_app.entities.RoleList;
import sn.dev.sponsorship_app.entities.Utilisateur;
import sn.dev.sponsorship_app.repositories.role.Irole;
import sn.dev.sponsorship_app.repositories.role.RoleService;
import sn.dev.sponsorship_app.repositories.role.roleImpl;
import sn.dev.sponsorship_app.tools.Notification;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    private DBConnection db= new DBConnection();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();
        getRoles();
    }
    @FXML
    private Button ajouter;

    @FXML
    private TableColumn<Utilisateur, Integer> etatC;
    @FXML
    private TableColumn<Utilisateur, Integer> idC;

    @FXML
    private TableView<Utilisateur> listUser;

    @FXML
    private TableColumn<Utilisateur, String> loginC;

    @FXML
    private Button modifier;

    @FXML
    private TableColumn<Utilisateur, String> nomC;

    @FXML
    private TextField nomtxt;

    @FXML
    private TextField passwordtxt;

    @FXML
    private TableColumn<Utilisateur, String> prenomC;

    @FXML
    private TextField prenomtxt;

    @FXML
    private TableColumn<Utilisateur, String> profilC;

    @FXML

    private ComboBox<Role> profiltxt;

    @FXML
    private Button selectionner;

    @FXML
    private Button supprimer;
    private RoleService roleService;
    @FXML
    private TextField logintxt;
    @FXML

    //recuper les user de la base de donne
    public ObservableList<Utilisateur> getUsers(){
        ObservableList<Utilisateur> users= FXCollections.observableArrayList();
        String sql = "select * from user where  profil=2 or profil=3";
        try {
            db.initPrepar(sql);
            ResultSet rs = db.executeSelect();
            while (rs.next()){
                Utilisateur user = new Utilisateur();
                user.setId(rs.getInt(1));
                user.setPrenom(rs.getString(2));
                user.setNom(rs.getString(3));
                user.setLogin(rs.getString(4));
                Irole iRole = new roleImpl();
                int idRole=rs.getInt("profil");
                Role profil = iRole.getRoleByid(idRole);
                user.setProfil(profil);
                users.add(user);
            }
            db.closeConnection();
        }catch (Exception e){
            throw new RuntimeException();
        }
        return users;
    }
    //recuper les user de la base de donne
    public void getRoles(){
//       Irole irole= new roleImpl();
        roleService = new RoleService();
       List<Role> roles = roleService.getAllRole();
       ObservableList<Role> listeRoles=FXCollections.observableArrayList(roles);
       profiltxt.setItems(listeRoles);

       profiltxt.setCellFactory(param -> new RoleList());
       profiltxt.setButtonCell(new RoleList());

    }


    public void loadTable() {
        ObservableList<Utilisateur> listeU = getUsers();
        listUser.setItems(listeU);
        idC.setCellValueFactory(new PropertyValueFactory<Utilisateur, Integer>("id"));
        nomC.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("nom"));
        prenomC.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("prenom"));
        loginC.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("login"));
    }
    @FXML
    void add(ActionEvent event) throws SQLException {
        String sql = "insert into user(id,prenom,nom,login,password,profil,activate) values(null,?,?,?,?,?,?)";
        Utilisateur us = new Utilisateur();
        try {
            db.initPrepar(sql);
            db.getPstm().setString(1,prenomtxt.getText().trim());
            db.getPstm().setString(2,nomtxt.getText().trim());
            db.getPstm().setString(3, logintxt.getText().trim());
            db.getPstm().setString(4,passwordtxt.getText().trim());
            Role profilSelected = profiltxt.getValue();
            if (profilSelected != null){
                db.getPstm().setInt(5,profilSelected.getId());
            }


            db.getPstm().setInt(6,1);
            int ok = db.executeMaj();
            db.closeConnection();
            loadTable();
            if(ok==1){
                Notification.NotifSuccess("Success","user ajouter succes");
            }else {
                Notification.NotifError("Error","user ajouter error");

            }
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    @FXML
    void remove(ActionEvent event) {

    }

    @FXML
    void update(ActionEvent event) {

    }
}

