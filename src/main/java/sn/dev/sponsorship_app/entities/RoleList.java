package sn.dev.sponsorship_app.entities;

import javafx.scene.control.ListCell;

public class RoleList extends ListCell<Role> {

    protected void udpateItem(Role role, boolean empty){
        super.updateItem(role,empty);

        if (empty || role == null){
            setText(null);
        } else {
            setText(role.getName());
        }
    }
}
