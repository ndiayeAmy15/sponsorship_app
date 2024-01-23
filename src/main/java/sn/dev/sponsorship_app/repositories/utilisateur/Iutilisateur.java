package sn.dev.sponsorship_app.repositories.utilisateur;

import sn.dev.sponsorship_app.entities.Utilisateur;

public interface Iutilisateur {
    public Utilisateur seConnecter(String login,String password);
}
