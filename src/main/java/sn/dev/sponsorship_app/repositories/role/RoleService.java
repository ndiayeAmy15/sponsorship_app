package sn.dev.sponsorship_app.repositories.role;

import sn.dev.sponsorship_app.DBConnection;
import sn.dev.sponsorship_app.entities.Role;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleService {
    private DBConnection db = new DBConnection();

    private ResultSet rs;

    public List<Role> getAllRole()
    {
        List<Role> roles = new ArrayList<>();
        String sql = "SELECT * FROM role WHERE id <> 1";
        try {
            db.initPrepar(sql);
            rs = db.executeSelect();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int etat = rs.getInt("etat");
                Role role = new Role(id, name, etat);
                roles.add(role);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return roles;
    }
}
