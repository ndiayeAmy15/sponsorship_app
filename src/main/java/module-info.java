module sn.dev.sponsorship_app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires static lombok;
    requires TrayNotification;


    opens sn.dev.sponsorship_app to javafx.fxml;
    opens sn.dev.sponsorship_app.entities to javafx.base;
    exports sn.dev.sponsorship_app;
    exports sn.dev.sponsorship_app.controllers;
    opens sn.dev.sponsorship_app.controllers to javafx.fxml;
    exports sn.dev.sponsorship_app.tools;
    opens sn.dev.sponsorship_app.tools to javafx.fxml;

}