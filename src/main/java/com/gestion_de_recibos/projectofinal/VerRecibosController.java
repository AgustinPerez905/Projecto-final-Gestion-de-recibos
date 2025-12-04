package com.gestion_de_recibos.projectofinal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import manager.ConexionManager;
import manager.SessionManager;
import modelo.Recibo;
import modelo.ReciboDAO;

import java.io.IOException;
import java.lang.annotation.Target;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class VerRecibosController {

    public String idReciboTabla;
    Connection conexion = ConexionManager.getInstance().getConnection();
    @FXML
    private TableView<Recibo> tb_tableview;

    @FXML
    private Button btn_borrarRecibo;

    @FXML
    private Button btn_modificarRecibo;

    @FXML
    private TableColumn<Recibo, String> tc_monto;
    @FXML
    private TableColumn<Recibo, String> tc_descripcion;
    @FXML
    private TableColumn<Recibo, String> tc_tipo;
    @FXML
    private TableColumn<Recibo, LocalDate> tc_fecha;
    @FXML
    private TableColumn<Recibo, LocalDate> tc_fechaVencimiento;
    @FXML
    private TableColumn<Recibo, String> tc_numeroRut;
    @FXML
    private TableColumn<Recibo, String> tc_moneda;
    @FXML
    private TableColumn<Recibo, String> tc_iva;
    @FXML
    private TableColumn<Recibo, String> tc_subtotal;
    @FXML
    private TableColumn<Recibo, String> tc_debitoTarjeta;

    private ObservableList<Recibo> observador = FXCollections.observableArrayList();

    @FXML
    private void borrarRecibo() throws IOException {
        try {
            if (tb_tableview.getSelectionModel().isEmpty()) {
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Mensaje");
                alerta.setContentText("Nesesitas seleccionar un elemento en la tabla");
                alerta.show();
                return;
            }

            Recibo itemClick = tb_tableview.getSelectionModel().getSelectedItem();
            idReciboTabla = itemClick.getId();


            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Quieres eliminar el recibo?");
            alerta.setContentText("Se eliminara el recibo seleccionado con el id: " + idReciboTabla);
            Optional<ButtonType> respuesta = alerta.showAndWait();

            if (respuesta.get() == ButtonType.OK) {
                ReciboDAO.borrarRecibo(conexion, idReciboTabla);
                observador.remove(itemClick);//actualiza la tabla
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    private void habilitarbotones() {
        btn_modificarRecibo.setDisable(false);
        btn_borrarRecibo.setDisable(false);

    }

    @FXML
    private void modificarRecibo() throws IOException {
        if (tb_tableview.getSelectionModel().isEmpty()) {

            return;
        }

        Recibo itemClick = tb_tableview.getSelectionModel().getSelectedItem();

        SessionManager.getInstance().setRecibo(itemClick);

        App.setRoot("modificarRecibo");

    }


    @FXML
    private void initialize() throws IOException {
        tc_monto.setCellValueFactory(
                data -> new javafx.beans.property.SimpleStringProperty(String.valueOf(data.getValue().getMonto()))
        );

        tc_descripcion.setCellValueFactory(
                data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDescripcion())
        );

        tc_tipo.setCellValueFactory(
                data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTipo())
        );

        tc_fecha.setCellValueFactory(
                data -> new javafx.beans.property.SimpleObjectProperty<LocalDate>(data.getValue().getFecha())
        );

        tc_fechaVencimiento.setCellValueFactory(
                data -> new javafx.beans.property.SimpleObjectProperty<LocalDate>(data.getValue().getFechaVencimiento())
        );

        tc_numeroRut.setCellValueFactory(
                data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNumRUT())
        );

        tc_moneda.setCellValueFactory(
                data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getMoneda())
        );

        tc_iva.setCellValueFactory(
                data -> new javafx.beans.property.SimpleStringProperty(String.valueOf(data.getValue().getiVA()))
        );

        tc_subtotal.setCellValueFactory(
                data -> new javafx.beans.property.SimpleStringProperty(String.valueOf(data.getValue().getSubtotal()))
        );

        tc_debitoTarjeta.setCellValueFactory(
                data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDebitoTarjeta())
        );


        tb_tableview.setItems(observador);

        try {

            observador.addAll(ReciboDAO.cargarListaRecibos(conexion, SessionManager.getInstance().getUsuarioActual().getId()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void cerrar() throws IOException {
        App.setRoot("main");

    }


}
