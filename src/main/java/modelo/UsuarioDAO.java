package modelo;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    public static void intertarUsuario(Connection conn, Usuario usuariox) throws SQLException {

        String sql = "INSERT INTO Usuario (id, nombreComp, nombreUser, contrasenia) VALUES (?, ?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, usuariox.getId());
        stmt.setString(2, usuariox.getNombreComp());
        stmt.setString(3, usuariox.getNombreUser());
        stmt.setString(4, usuariox.getContrasenia());

        ResultSet rs = stmt.executeQuery();

        rs.close();
        stmt.close();


    }

    public static Usuario getUsuario(Connection conn, String nombUser) throws SQLException {
        Usuario usuariox = null;

        String sql = "select * from Usuario where nombreUser = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, nombUser);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            usuariox = new Usuario(
                    rs.getString("id"),
                    rs.getString("nombreComp"),
                    rs.getString("nombreUser"),
                    rs.getString("contrasenia")
            );

        }

        rs.close();
        stmt.close();

        return usuariox;
    }

    public static Usuario login (Connection conn, String username, String password) throws SQLException {
        Usuario usuariox = getUsuario(conn,username);
        if (!username.equals("") && !password.equals("") && usuariox != null && BCrypt.checkpw(password,usuariox.getContrasenia())) {
            return usuariox;
        }
        return null;
    }


}





