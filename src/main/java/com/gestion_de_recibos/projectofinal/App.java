package com.gestion_de_recibos.projectofinal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import manager.ConexionManager;

import java.io.IOException;
import java.sql.Connection;

public class App extends Application {

    Connection conexion = ConexionManager.getInstance().initConexion();

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"));

        stage.setScene(scene);
        stage.show();
    }
    private static Scene scene;
    public static void setRoot(String fxml) throws IOException {
        Parent root = loadFXML(fxml);
        scene.setRoot(root);
        ((Stage) scene.getWindow()).sizeToScene();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {

        launch();
    }
}