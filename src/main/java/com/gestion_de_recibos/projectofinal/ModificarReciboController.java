package com.gestion_de_recibos.projectofinal;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import manager.ConexionManager;
import manager.SessionManager;
import modelo.Recibo;
import modelo.ReciboDAO;

import java.io.IOException;
import java.sql.Connection;


public class ModificarReciboController {
    Connection conexion = ConexionManager.getInstance().getConnection();
    Recibo reciboY = null;
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
    private void initialize() throws IOException {
        reciboY = SessionManager.getInstance().getRecibo();
        if (reciboY == null) {
            App.setRoot("verRecibos");
        }
        SpinnerValueFactory<Double> valueFactoryMONTO = new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 99999);
        SpinnerValueFactory<Double> valueFactoryIVA = new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 99999);
        SpinnerValueFactory<Double> valueFactorySUBTOTAL = new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 99999);
        valueFactoryMONTO.setValue(reciboY.getMonto());
        valueFactoryIVA.setValue(reciboY.getiVA());
        valueFactorySUBTOTAL.setValue(reciboY.getSubtotal());
        txt_monto.setValueFactory(valueFactoryMONTO);
        txt_IVA.setValueFactory(valueFactoryIVA);
        txt_subtotal.setValueFactory(valueFactorySUBTOTAL);
        txt_descripcion.setText(reciboY.getDescripcion());
        txt_tipo.setText(reciboY.getTipo());
        dp_fecha.setValue(reciboY.getFecha());
        dp_fechaVenc.setValue(reciboY.getFechaVencimiento());
        txt_numeroRUT.setText(reciboY.getNumRUT());
        txt_moneda.setText(reciboY.getMoneda());
        txt_debitotarjeta.setText(reciboY.getDebitoTarjeta());

    }

    @FXML
    private void cerrar() throws IOException {
        App.setRoot("verRecibos");

    }

    //para crear un recibo se tiene que contruir el objeto recibo y llamar a una funcion de reciboDAO que inserte el objeto en la base de datos


    public void cargarRecibo() throws IOException {
        Double monto = txt_monto.getValue();
        Double iVA = Double.valueOf(txt_IVA.getValue());
        Double subtotal = Double.valueOf(txt_subtotal.getValue());
        String idUs = SessionManager.getInstance().getUsuarioActual().getId();


        reciboY.modificarRecibo(monto,
                txt_descripcion.getText(),
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
                ReciboDAO.modificarRecibo(conexion, reciboY);
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Exitoso");
                alerta.setContentText("recibo '" + reciboY.getId() + "' modificado con exito");
                alerta.showAndWait();
                App.setRoot("verRecibos");


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


}
