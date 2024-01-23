package sn.dev.sponsorship_app.repositories.utilisateur;

import sn.dev.sponsorship_app.DBConnection;
import sn.dev.sponsorship_app.entities.Utilisateur;
import sn.dev.sponsorship_app.entities.Role;

import sn.dev.sponsorship_app.repositories.role.Irole;
import sn.dev.sponsorship_app.repositories.role.roleImpl;

import java.sql.ResultSet;

public class utilisateurImpl implements Iutilisateur{
    private DBConnection db = new DBConnection();
    private ResultSet rs;
    @Override
    public Utilisateur seConnecter(String login, String password) {

        String sql = "select * from user where login=? and password =?";
        Utilisateur user = null;
        try {
            db.initPrepar(sql);
            db.getPstm().setString(1,login);
            db.getPstm().setString(2,password);
            rs = db.executeSelect();
            if (rs.next()){
                user = new Utilisateur();
                user.setId(rs.getInt("id"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString(2));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setActivate(rs.getInt("activate"));
                Irole iRole = new roleImpl();
                Role profil = iRole.getRoleByid(rs.getInt("profil"));
                user.setProfil(profil);
            }
            db.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }
}
