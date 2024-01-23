package sn.dev.sponsorship_app.repositories.role;

import sn.dev.sponsorship_app.entities.Role;

import java.util.ArrayList;

public interface Irole {

    public Role getRoleByid(int id);
    public ArrayList<Role> getAllRoles();
}
