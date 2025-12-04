package com.gestion_de_recibos.projectofinal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import manager.ConexionManager;
import manager.SessionManager;
import modelo.Recibo;
import modelo.ReciboDAO;
import modelo.UsuarioDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;


public class CargarReciboController {
    Connection conexion = ConexionManager.getInstance().getConnection();
    @FXML
    private Spinner<Double> txt_monto;
    @FXML
    private TextField txt_descripcion;
    @FXML
    private TextField txt_tipo;
    @FXML
    private DatePicker dp_fecha;
    @FXML
    private DatePicker dp_fechaVenc;
    @FXML
    private TextField txt_numeroRUT;
    @FXML
    private TextField txt_moneda;
    @FXML
    private Spinner<Double> txt_IVA;
    @FXML
    private Spinner<Double> txt_subtotal;
    @FXML
    private TextField txt_debitotarjeta;

    @FXML
    private TableView<Recibo> tb_tableview;

    @FXML
    private TableColumn<Recibo, String> tc_monto;

    @FXML
    private TableColumn<Recibo, String> tc_descripcion;

    @FXML
    private TableColumn<Recibo, String> tc_tipo;

    @FXML
    private TableColumn<Recibo, String> tc_subtotal;



    private ObservableList<Recibo> listaRecbio = FXCollections.observableArrayList();


    @FXML
    private void initialize() throws IOException {
        SpinnerValueFactory<Double> valueFactoryMONTO = new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 99999);
        SpinnerValueFactory<Double> valueFactoryIVA = new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 99999);
        SpinnerValueFactory<Double> valueFactorySUBTOTAL = new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 99999);
        valueFactoryMONTO.setValue(1.0);
        valueFactoryIVA.setValue(1.0);
        valueFactorySUBTOTAL.setValue(1.0);
        txt_monto.setValueFactory(valueFactoryMONTO);
        txt_IVA.setValueFactory(valueFactoryIVA);
        txt_subtotal.setValueFactory(valueFactorySUBTOTAL);
        dp_fecha.setValue(LocalDate.now());

        tc_monto.setCellValueFactory(
                data -> new javafx.beans.property.SimpleStringProperty(String.valueOf(data.getValue().getMonto()))
                );

        tc_descripcion.setCellValueFactory(
                data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDescripcion())
        );

        tc_tipo.setCellValueFactory(
                data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTipo())
        );

        tc_subtotal.setCellValueFactory(
                data -> new javafx.beans.property.SimpleStringProperty(String.valueOf(data.getValue().getSubtotal()))
        );


        tb_tableview.setItems(listaRecbio);

    }

    @FXML
    private void cerrar() throws IOException {
        App.setRoot("main");

    }

    //para crear un recibo se tiene que contruir el objeto recibo y llamar a una funcion de reciboDAO que inserte el objeto en la base de datos

    @FXML
    public void cargarRecibo() throws IOException {
        Double monto = txt_monto.getValue();
        Double iVA = Double.valueOf(txt_IVA.getValue());
        Double subtotal = Double.valueOf(txt_subtotal.getValue());
        String idUs = SessionManager.getInstance().getUsuarioActual().getId();


        Recibo reciboX = new Recibo(monto,
                txt_descripcion.getText(),
                idUs,
                txt_tipo.getText(),
                dp_fecha.getValue(),
                dp_fechaVenc.getValue(),
                txt_numeroRUT.getText(),
                txt_moneda.getText(),
                iVA,
                subtotal,
                txt_debitotarjeta.getText());

        if (conexion != null) {
            try {
                ReciboDAO.insertarRecibo(conexion, reciboX);
                cargarRecibosMiniTV(monto,txt_descripcion.getText(),txt_tipo.getText(),subtotal);
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Exitoso");
                alerta.setContentText("recibo '" + reciboX.getId() + "' creado con exito");
                alerta.showAndWait();
                limpiarCampos();



            } catch (Exception e) {
                e.printStackTrace();
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error");
                alerta.setHeaderText("Se produjo un error");
                alerta.setContentText(e.getMessage() != null ? e.getMessage() : e.toString());
                alerta.showAndWait();

            }
        }

    }


    private void cargarRecibosMiniTV(double monto,String descripcion,String tipo,double subtotal) {
            Recibo recibox = new Recibo(monto, descripcion,tipo,subtotal);
            listaRecbio.add(recibox);
    }

    @FXML
    private void limpiarCampos() {
        txt_descripcion.clear();
        txt_tipo.clear();
        txt_moneda.clear();
        txt_debitotarjeta.clear();
        txt_numeroRUT.clear();
        dp_fecha.setValue(LocalDate.now());
        dp_fechaVenc.setValue(null);
        SpinnerValueFactory<Double> valueFactoryMONTO = new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 99999);
        SpinnerValueFactory<Double> valueFactoryIVA = new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 99999);
        SpinnerValueFactory<Double> valueFactorySUBTOTAL = new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 99999);
        valueFactoryMONTO.setValue(1.0);
        valueFactoryIVA.setValue(1.0);
        valueFactorySUBTOTAL.setValue(1.0);
        txt_monto.setValueFactory(valueFactoryMONTO);
        txt_IVA.setValueFactory(valueFactoryIVA);
        txt_subtotal.setValueFactory(valueFactorySUBTOTAL);


    }


}
