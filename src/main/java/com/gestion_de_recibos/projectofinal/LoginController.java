package com.gestion_de_recibos.projectofinal;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;



import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import manager.ConexionManager;
import manager.SessionManager;
import modelo.Usuario;
import modelo.UsuarioDAO;

public class LoginController {
    Connection conexion = ConexionManager.getInstance().getConnection();
    @FXML
    private TextField txt_username;
    @FXML
    private PasswordField txt_password;
    @FXML
    private Label lbl_connection;
    @FXML
    private Button btn_ingresarUsuario;

    @FXML
    private void initialize() throws IOException {
        if (conexion == null) {
            btn_ingresarUsuario.setDisable(true);
            txt_password.setDisable(true);
            txt_username.setDisable(true);
            lbl_connection.setText("❌ No conectado a la base de datos");
            return;
        }

        lbl_connection.setText("✅ Base de datos conectada");
    }
    @FXML
    private void login() throws IOException {
        try {
            Usuario loggedUsuario = UsuarioDAO.login(conexion, txt_username.getText().toLowerCase(),
                    txt_password.getText());
            if (loggedUsuario == null)
                throw new Exception("Usuario o contraseña incorrectos");

            SessionManager.getInstance().setUsuarioActual(loggedUsuario);
            Alert alerta = new Alert(AlertType.INFORMATION);
            alerta.setTitle("Exito");
            alerta.setContentText("Login exitoso");
            alerta.showAndWait();
            App.setRoot("main");

        } catch (Exception e) {
            e.printStackTrace();
            Alert alerta = new Alert(AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setContentText(e.getMessage() != null ? e.getMessage() : e.toString());
            alerta.showAndWait();
        }
    }



    @FXML
    private void abrirCrearUser () throws IOException{
        App.setRoot("crearUser");

    }

}