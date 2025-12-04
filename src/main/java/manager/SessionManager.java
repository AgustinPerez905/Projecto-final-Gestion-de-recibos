package manager;

import modelo.Recibo;
import modelo.ReciboDAO;
import modelo.Usuario;

public class SessionManager {
    private static SessionManager instance;
    private Usuario usuarioActual;
    private Recibo recibo;

    private SessionManager() {
    }


    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }

        return instance;
    }

    public Recibo getRecibo() {
        return recibo;
    }

    public void setRecibo(Recibo recibo) {
        this.recibo = recibo;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public void cerrarSesion() {
        usuarioActual = null;
    }
}

