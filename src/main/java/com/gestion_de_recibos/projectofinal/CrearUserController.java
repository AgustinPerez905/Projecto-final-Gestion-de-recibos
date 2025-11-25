package com.gestion_de_recibos.projectofinal;

import java.io.IOException;
import java.sql.Connection;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import manager.ConexionManager;
import manager.SessionManager;
import modelo.Usuario;
import modelo.UsuarioDAO;
import org.mindrot.jbcrypt.BCrypt;

public class CrearUserController {

    Connection conexion = ConexionManager.getInstance().getConnection();
    @FXML
    private TextField txt_fullname;
    @FXML
    private TextField txt_username;
    @FXML
    private TextField txt_password;
    @FXML
    private TextField txt_repeatpassword;

    @FXML
    public void crearUsuario () throws IOException {
        String contraseñaEncriptada = BCrypt.hashpw(txt_password.getText(), BCrypt.gensalt(12));
        Usuario usuariox = new Usuario(txt_fullname.getText(),txt_username.getText(),contraseñaEncriptada);

        if (conexion != null) {
            try {
                UsuarioDAO.intertarUsuario(conexion, usuariox);
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Exitoso");
                alerta.setContentText("Usuario '" + usuariox.getNombreUser() + "' creado con exito");
                alerta.showAndWait();
                SessionManager.getInstance().setUsuarioActual(usuariox);

                App.setRoot("main");
            } catch (Exception e) {
                e.printStackTrace();
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error");
                alerta.setHeaderText("Se produjo un error");
                alerta.setContentText(e.getMessage() != null ? e.getMessage() : e.toString());
                alerta.showAndWait();
                return;
            }
        }



    }
    @FXML
    public static void cerrar () throws IOException {
        App.setRoot("main");

    }
}
