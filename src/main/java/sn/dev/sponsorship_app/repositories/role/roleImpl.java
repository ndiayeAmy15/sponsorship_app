package sn.dev.sponsorship_app.repositories.role;

import sn.dev.sponsorship_app.DBConnection;
import sn.dev.sponsorship_app.entities.Role;
import sn.dev.sponsorship_app.entities.Utilisateur;

import java.sql.ResultSet;
import java.util.ArrayList;

public class roleImpl implements Irole{
    DBConnection db = new DBConnection();
    private ResultSet rs;
    @Override
    public Role getRoleByid(int id) {
        String sql = "select * from role where id=?";
        Role role = null;
        try {
            db.initPrepar(sql);
            db.getPstm().setInt(1,id);
            rs = db.executeSelect();
            if (rs.next()){
                role = new Role(rs.getInt("id"),rs.getString("name"),rs.getInt("etat"));
                db.closeConnection();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return role;
    }
    public ArrayList<Role> getAllRoles(){
        String sql = "select * from role where id <> 1";
        ArrayList<Role> roles = new ArrayList<>();
        try {
            db.initPrepar(sql);
            ResultSet rs = db.executeSelect();
//            roles = new ArrayList<Role>();
            while (rs.next()){
                Role role = new Role();
                role.setId(rs.getInt(1));
                role.setEtat(rs.getInt(3));
                role.setName(rs.getString(2));
                roles.add(role);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  roles;
        }
    }
