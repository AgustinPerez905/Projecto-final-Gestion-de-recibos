package modelo;

import manager.SessionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReciboDAO {

    public static void insertarRecibo(Connection conn, Recibo reciboX) throws SQLException {
        String sql = "INSERT INTO Recibos (id, monto, descripcion, tipo, fecha, fechaVencimiento, numRUT, moneda, IVA, subtotal, debitoTarjeta, idUsuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, reciboX.getId());
        stmt.setDouble(2, reciboX.getMonto());
        stmt.setString(3, reciboX.getDescripcion());
        stmt.setString(4, reciboX.getTipo());
        stmt.setString(5, reciboX.getFecha().toString());
        stmt.setString(6, reciboX.getFechaVencimiento().toString());
        stmt.setString(7, reciboX.getNumRUT());
        stmt.setString(8, reciboX.getMoneda());
        stmt.setDouble(9, reciboX.getiVA());
        stmt.setDouble(10, reciboX.getSubtotal());
        stmt.setString(11, reciboX.getDebitoTarjeta());
        stmt.setString(12, SessionManager.getInstance().getUsuarioActual().getId());


        ResultSet rs = stmt.executeQuery();

        rs.close();
        stmt.close();
    }

}
