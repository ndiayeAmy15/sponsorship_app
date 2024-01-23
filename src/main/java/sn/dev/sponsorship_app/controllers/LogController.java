package sn.dev.sponsorship_app.controllers;
import sn.dev.sponsorship_app.App;
import sn.dev.sponsorship_app.entities.Role;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import sn.dev.sponsorship_app.entities.Utilisateur;
import sn.dev.sponsorship_app.repositories.utilisateur.Iutilisateur;
import sn.dev.sponsorship_app.repositories.utilisateur.utilisateurImpl;
import sn.dev.sponsorship_app.tools.Notification;
import sn.dev.sponsorship_app.tools.Outils;
import tray.notification.TrayNotification;


public class LogController {

    @FXML

    private TextField logintxt;

    @FXML
    private PasswordField passwordtxt;
    private Iutilisateur userDao= new utilisateurImpl();
    TrayNotification tray = new TrayNotification();
    public static String userParams;
    @FXML
    private void enregistrer(ActionEvent event) {
        String login = logintxt.getText();
        String password = passwordtxt.getText();
        if(login.trim().equals("") || password.trim().equals("")){
            Notification.NotifError("Erreur","tous les champs sont obligatoire");
        }else {
            Utilisateur user = userDao.seConnecter(login, password);
            if(user != null){
                App.setAuth(user);
                try{
                    Notification.NotifSuccess("Success","connxion au site reussit");
                    if(user.getProfil().getName().equalsIgnoreCase("ADMIN")){
                        Outils.load(event," welcomme Admin","/pag/adminAcceuil.fxml");
                    } else if (user.getProfil().getName().equalsIgnoreCase("ELECTOR")){
                        Outils.load(event," welcomme Elector","/pag/electorAcceuil.fxml");
                    }else{
                        Outils.load(event," welcomme Candidat","/pag/candidatAcceuil.fxml");
                    }


                }catch (Exception e){
                    e.printStackTrace();
                }
            }else {
                Notification.NotifError("Erreur","les credential sont imcopentible");
            }
        }
    }

    @FXML
    void quitter(ActionEvent event) {

    }

}
