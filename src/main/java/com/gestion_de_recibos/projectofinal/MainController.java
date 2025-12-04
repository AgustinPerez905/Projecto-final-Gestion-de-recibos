package com.gestion_de_recibos.projectofinal;

import javafx.fxml.FXML;
import manager.SessionManager;

import java.io.IOException;

public class MainController {
    @FXML
    private void cargarrecibo () throws IOException {
        App.setRoot("cargarRecibo");
    }
    @FXML
    private void verRecibos () throws IOException {
        App.setRoot("verRecibos");
    }

    @FXML
    private void cerrarSesion() throws IOException {
        SessionManager.getInstance().cerrarSesion();
        App.setRoot("login");

    }

}
