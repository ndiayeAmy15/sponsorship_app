package sn.dev.sponsorship_app.entities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.DateFormat;


public class Utilisateur {
    private int id;
    private String prenom;
    private String nom;
    private String login;
    private String password;
    private int activate;
    private Role profil;

    public Utilisateur() {
    }

    public Utilisateur(int id, String prenom, String nom, String login, String password, int activate, Role profil) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.login = login;
        this.password = password;
        this.activate = activate;
        this.profil = profil;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getActivate() {
        return activate;
    }

    public void setActivate(int activate) {
        this.activate = activate;
    }

    public Role getProfil() {
        return profil;
    }

    public void setProfil(Role profil) {
        this.profil = profil;
    }
}
