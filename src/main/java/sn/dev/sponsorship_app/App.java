package sn.dev.sponsorship_app;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sn.dev.sponsorship_app.entities.Utilisateur;

import java.util.Objects;

public class App extends Application {

    public  static Utilisateur auth=new Utilisateur();

    public static Utilisateur getAuth() {
        return auth;
    }

    public static void setAuth(Utilisateur auth) {
        App.auth = auth;
    }

    @Override


    public void start(Stage stage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("/pag/log.fxml"));
        Scene scene = new Scene(parent);
        stage.setTitle("Page de connexion");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        DBConnection db = new DBConnection();
        db.getConnection();
        launch();
    }
}
