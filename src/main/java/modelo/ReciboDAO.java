package modelo;

import manager.SessionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public static List<Recibo> cargarListaRecibos(Connection conn, String idus) throws SQLException {
        String sql = "select * from Recibos where idUsuario = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, idus);

        ResultSet rs = stmt.executeQuery();

        List<Recibo> listaDeRecibos = new ArrayList<Recibo>();

        while (rs.next()) {
            Recibo reciboX = new Recibo(
                    rs.getString("id"),
                    rs.getDouble("monto"),
                    rs.getString("descripcion"),
                    rs.getString("idUsuario"),
                    rs.getString("tipo"),
                    LocalDate.parse(rs.getString("fecha")),
                    LocalDate.parse(rs.getString("fechaVencimiento")),
                    rs.getString("numRUT"),
                    rs.getString("moneda"),
                    rs.getDouble("iVA"),
                    rs.getDouble("subtotal"),
                    rs.getString("debitoTarjeta"));
            listaDeRecibos.add(reciboX);

        }


        rs.close();
        stmt.close();


        return listaDeRecibos;
    }

}
