package com.gestion_de_recibos.projectofinal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

public class VerRecibosController {
    Connection conexion = ConexionManager.getInstance().getConnection();
    @FXML
    private TableView<Recibo> tb_tableview;

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
    private void

}
