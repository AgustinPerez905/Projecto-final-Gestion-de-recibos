package modelo;

import manager.SessionManager;

import javax.xml.stream.StreamFilter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReciboDAO {

    public static Recibo buscarReciboID (Connection conn, String id)throws SQLException {
        String sql = "SELECT * FROM Recibos where id = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, id);

        ResultSet rs = stmt.executeQuery();

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

        rs.close();
        stmt.close();

        return reciboX;
    }

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

    public static void modificarRecibo(Connection conn, Recibo reciboX) throws SQLException {
        String sql = "UPDATE Recibos SET monto = ?, descripcion = ?, tipo = ?, fecha = ?, fechaVencimiento = ?, numRUT= ?, moneda= ?, IVA= ?, subtotal= ?, debitoTarjeta = ? where id = ?;";

        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setDouble(1, reciboX.getMonto());
        stmt.setString(2, reciboX.getDescripcion());
        stmt.setString(3, reciboX.getTipo());
        stmt.setString(4, reciboX.getFecha().toString());
        stmt.setString(5, reciboX.getFechaVencimiento().toString());
        stmt.setString(6, reciboX.getNumRUT());
        stmt.setString(7, reciboX.getMoneda());
        stmt.setDouble(8, reciboX.getiVA());
        stmt.setDouble(9, reciboX.getSubtotal());
        stmt.setString(10, reciboX.getDebitoTarjeta());
        stmt.setString(11, reciboX.getId());


        int rs = stmt.executeUpdate();

        if (rs <= 0) {
            throw new SQLException("no se actualizo ");
        }

        stmt.close();



    }

    public static void borrarRecibo (Connection conn, String id) throws SQLException {
        String sql = "delete from Recibos where id = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, id);

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
