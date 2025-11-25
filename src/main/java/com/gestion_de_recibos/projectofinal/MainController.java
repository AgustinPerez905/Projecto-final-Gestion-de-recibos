package com.gestion_de_recibos.projectofinal;

import javafx.fxml.FXML;

import java.io.IOException;

public class MainController {
    @FXML
    private void cargarrecibo () throws IOException {
        App.setRoot("cargarRecibo");
    }

}
